package com.coscale.sdk.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coscale.sdk.client.commons.Options;
import com.coscale.sdk.client.exceptions.CoscaleApiException;
import com.coscale.sdk.client.utils.MapperSupport;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * ApiClient is used to make HTTP requests to CoScale API client
 * 
 * @author cristi
 *
 */
public class ApiClient {

    /** Credentials used for authentication. */
    private final Credentials credentials;

    /** The client application id. */
    private final String APPID;

    /** API_PATH, API_VERSION for request URL. */
    private final String API_PATH = "api";
    private String API_VERSION = "v1";

    /** URL path separator */
    private String PATH_DIV = "/";

    /** The API base URL. */
    private String API_BASE_URL = "https://api.coscale.com";

    /** The name of the HTTP header that contains the authorization token. */
    private static final String AUTH_HEADER = "HTTPAuthorization";

    /** AUTH_RETRIES is the number of login attempts before failing. */
    private static final int AUTH_RETRIES = 3;

    /** Trigger a timeout if the API connection takes more than x milliseconds. */
    private static int API_CONN_TIMEOUT_MS = 15 * 1000;

    /** Trigger a timeout if the API read takes more than x milliseconds. */
    private static int API_READ_TIMEOUT_MS = 15 * 1000;

    /** SOURCE will identify the source of the created resources */
    private static String SOURCE = "Java SDK";

    /** Received after the login, and used as HTTPAuthorization on next calls. */
    private String token;

    private String userAgent = "";

    /** Json deserialization error counter. */
    private int jsonDeserializationExceptions;

    /**
     * ApiClient constructor.
     *
     * @param appId
     *            The CoScale Application id.
     * @param credentials
     *            Api authentication informations.
     */
    public ApiClient(String appId, Credentials credentials) {
        this(appId, credentials, "Default Agent String");
    }

    /**
     * ApiClient Constructor.
     * 
     * @param appId
     *            The CoScale Application id.
     * @param credentials
     *            API authentication informations.
     * @param agentString
     *            HTTP "User-Agent" header
     */
    public ApiClient(String appId, Credentials credentials, String agentString) {
        this.credentials = credentials;
        this.APPID = appId;
        this.userAgent = agentString;
    }

    /**
     * getApiPath is used to get the API path.
     *
     * @return String containing the API path.
     */
    public String getApiPath() {
        return API_PATH;
    }

    /**
     * getApiVersion is used to get the API version which will be used to build
     * the request URL.
     *
     * @return String containing the API version.
     */
    public String getApiVersion() {
        return API_VERSION;
    }

    /**
     * getBaseURL is used to get the CoScale API domain.
     *
     * @return String containing the CoScale domain.
     */
    public String getBaseURL() {
        return API_BASE_URL;
    }

    /**
     * setBaseURL is used to set a new API base URL. Is for Testing purpose.
     * 
     * @param url
     */
    public void setBaseURL(String url) {
        this.API_BASE_URL = url;
    }

    /**
     * return the default connection timeout in ms.
     * 
     * @return
     */
    public int getConnectionTimeout() {
        return API_CONN_TIMEOUT_MS;
    }

    /**
     * Change the default connection timeout.
     * 
     * @param timeout
     *            in ms.
     */
    public void setConnectionTimeout(int timeout) {
        API_CONN_TIMEOUT_MS = timeout;
    }

    /**
     * return the default read timeout in ms.
     * 
     * @return
     */
    public int getReadTimeout() {
        return API_READ_TIMEOUT_MS;
    }

    /**
     * Change the default read timeout.
     * 
     * @param timeout
     *            in ms.
     */
    public void setReadTimeout(int timeout) {
        API_READ_TIMEOUT_MS = timeout;
    }

    /**
     * Get the SOURCE String which mark the created resources.
     * 
     * @return
     */
    public static String getSource() {
        return SOURCE;
    }

    /**
     * Set the Source String which mark the created resources.
     * 
     * @param source
     */
    public void setSource(String source) {
        SOURCE = source;
    }

    /**
     * getDeserializationExceptions is used to get the number of JSON
     * deserialization errors.
     * 
     * @return integer representing the number of JSON deserialization errors.
     */
    public int getDeserializationExceptions() {
        return jsonDeserializationExceptions;
    }

    /**
     * doHttpRequest is used to make a API call.
     *
     * @param method
     *            used by request.
     * @param uri
     *            of the API call.
     * @param data
     *            in string format to pass to the request.
     * @return
     * @throws IOException
     */
    private String doHttpRequest(String method, String uri, String payload, boolean authenticate)
            throws IOException {
        URL url;
        HttpURLConnection conn = null;
        int responseCode = -1;

        try {
            url = new URL(uri);
            conn = (HttpURLConnection) url.openConnection();

            // Set connection timeout.
            conn.setConnectTimeout(API_CONN_TIMEOUT_MS);
            conn.setReadTimeout(API_READ_TIMEOUT_MS);

            // Setup the connection.
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod(method);
            conn.setRequestProperty("Accept", "application/json");

            conn.setUseCaches(false);

            // add request headers.
            conn.setRequestProperty("User-Agent", this.userAgent);

            if (authenticate) {
                conn.setRequestProperty(AUTH_HEADER, this.token);
            }

            if (payload != null && ("POST".equals(method) || "PUT".equals(method))) {
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Content-Length", Integer.toString(payload.length()).trim());
            }

            if (payload != null) {
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(payload);

                wr.flush();
                wr.close();
            }

            // Check the response code.
            responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {

                String errorMessage = convertStreamToString(conn.getErrorStream());

                throw new CoscaleApiException(responseCode, "Failed : HTTP error code : "
                        + responseCode + " msg: " + conn.getResponseMessage() + " url: "
                        + conn.getURL() + " method " + conn.getRequestMethod() + " error message "
                        + errorMessage);
            }

            return convertStreamToString(conn.getInputStream());

        } catch (IOException e) {
            if (conn != null) {
                // return also the response from the API
                String errorMessage = convertStreamToString(conn.getErrorStream());
                String message = e.getMessage();
                if (errorMessage.length() > 0) {
                    message += " error " + errorMessage;
                }
                throw new CoscaleApiException(responseCode, message, e);
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * login will get the token used to authenticate the requests on CoScale
     * API.
     * 
     * @throws IOException
     */
    private void login() throws IOException {
        String uri = credentials.usesToken() ? getAppRequestURL("/login/")
                : getGlobalRequestURL("/users/login/");
        Credentials.TokenHelper data = call("POST", uri, credentials,
                new TypeReference<Credentials.TokenHelper>() {
                }, false);

        token = data.token;
    }

    /**
     * callWithAuth is used to make requests that require Authentication on
     * CoScale API.
     * 
     * @param method
     *            request HTTP method.
     * @param url
     *            for the request.
     * @param obj
     *            object with data for the request. This parameter can be null.
     * @param valueType
     *            is the type expected.
     * @return The Object received as a result for the request.
     * @throws IOException
     */
    public <T> T callWithAuth(String method, String endpoint, Object obj, TypeReference<T> valueType)
            throws IOException {
        // Not authenticated yet, try login.
        if (this.token == null) {
            login();
        }

        // Do the actual request.
        int tries = 0;
        int responseCode = 0;
        while (tries++ <= AUTH_RETRIES) {
            if (this.token == null) {
                login();
            }
            try {
                return call(method, getAppRequestURL(endpoint), obj, valueType, true);
            } catch (CoscaleApiException e) {
                if (e.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    this.token = null; // will trigger new login
                } else {
                    throw e;
                }
            }
        }
        throw new CoscaleApiException(responseCode, "Failed to get authentication right");
    }

    /**
     * call is used to make a API call.
     *
     * @param method
     *            request HTTP method.
     * @param url
     *            for the request.
     * @param obj
     *            object with data for the request. This parameter can be null.
     * @param valueType
     *            is the type expected.
     * @return The Object received as a result for the request.
     * @throws IOException
     */
    public <T> T call(String method, String url, Object obj, TypeReference<T> valueType,
            boolean auth) throws IOException {
        try {

            String res = this.doHttpRequest(method, url, objectToJson(obj), auth);
            if (res.length() == 0) {
                return null;
            }
            return MapperSupport.getInstance().readValue(res, valueType);
        } catch (JsonMappingException | JsonParseException e) {
            jsonDeserializationExceptions++;
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * getAppRequestURL will construct the URL for a request using the end point
     * provided. This method will be used to construct the URL for a specific
     * application since the resulted URL will contain the application Id.
     * 
     * @param endpoint
     *            String representing the API end point of the request.
     * @return String containing the URL.
     */
    public String getAppRequestURL(String endpoint) {
        return getRequesUrl(endpoint, null, false);
    }

    /**
     * getGlobalRequestURL will construct the URL for a request using the end
     * point provided. This will generate a URL for requests that doesn't
     * contain the application Id. Should be used for example in login process.
     * 
     * @param endpoint
     *            String representing the API end point of the request.
     * @return String containing the URL.
     */
    public String getGlobalRequestURL(String endpoint) {
        return getRequesUrl(endpoint, null, true);
    }

    /**
     * getRequesUrl will build the URL for a request using the end point
     * provided.
     *
     * @param endpoint
     *            String representing the API end point of the request.
     * @param options
     *            Optional query parameters
     * @param globalApi
     *            if is set true, application Id will not be include into URL.
     * @return
     */
    public String getRequesUrl(String endpoint, Options options, boolean globalApi) {

        StringBuilder sb = new StringBuilder();
        sb.append(getBaseURL());
        sb.append(PATH_DIV);
        sb.append(getApiPath());
        sb.append(PATH_DIV);
        sb.append(getApiVersion());
        if (!globalApi) {
            sb.append(PATH_DIV);
            sb.append("app");
            sb.append(PATH_DIV);
            sb.append(APPID);
        }
        sb.append(endpoint);

        if (options != null) {
            sb.append(options.query());
        }

        return sb.toString();
    }

    /**
     * convertStreamToString is used to take the HTTP response stream and
     * convert it to a string.
     *
     * @param is
     *            The input stream to be converted.
     * @return String represented the input stream conversion to String.
     */
    private static String convertStreamToString(java.io.InputStream is) {
        if (is == null) {
            return "";
        }
        try {
            return new java.util.Scanner(is).useDelimiter("\\A").next();
        } catch (java.util.NoSuchElementException e) {
            return "";
        }
    }

    /**
     * objectToJson converts a Java object to JSON format.
     *
     * @param obj
     *            generic type.
     * @return String object representing the JSON serialization.
     * @throws IOException
     */
    private <T> String objectToJson(T obj) throws IOException {
        if (obj == null) {
            return null;
        } else {
            try {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                MapperSupport.getInstance().writeValue(b, obj);
                return b.toString();
            } catch (JsonMappingException | JsonGenerationException e) {
                // jsonSerializationExceptions++;
                throw e;
            }
        }
    }
}