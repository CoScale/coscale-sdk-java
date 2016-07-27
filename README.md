
# coscale-sdk-java

Java SDK for integrating apps with CoScale

Java bindings for the [CoScale API](http://docs.coscale.com/)

 - [Installation](#installation)
 - [Resources](#resources)
 - [Authentication](#authentication)
 - [Usage](#usage)
 - [Subject](#subject)
 - [Error handling](#error-handling)
 - [Advanced Configuration](#advanced-configuration)


## Installation

You can download the jar binary from [here] (https://github.com/CoScale/coscale-sdk-java/releases).

### maven

To use the client add the project declaration to your `pom.xml`:

```xml
<dependency>
  <groupId>com.coscale.sdk-java</groupId>
  <artifactId>coscale-sdk-java</artifactId>
  <version>1.1.0</version>
</dependency>
```

## Resources

Resources this API supports:

- [Metrics](#metrics)
- [Data](#data)
- [Servers](#servers)
- [Events](#events)
- [Requests](#requests)


## Authentication

In order to communicate with the CoScale API an authorization token is required.
There are 2 ways to get an authorization token (you have to pick your favorite option):
1. Authenticating with your email and password
2. Authenticating with an access token. You can generate a token by accessing "Users" from the left sidebar and clicking on "Access tokens".

```java
// Authentication with your email and password.
Credentials credentials = Credentials.Password("user@coscale.com", "passwd");
// Or Authenticating with an access token generated from the CoScale web interface.
Credentials credentials = Credentials.Token("xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx");

// Instantiate ApiFactory with the Application Id and the credentials.
// The application ID can be retrieved from the URL, it's the UUID after /app
// e.g. https://app.coscale.com/app/xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx/dashboard/view/-1/
ApiFactory apiFactory = new ApiFactory("xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx", credentials);
```


## Usage

### Metrics

```java
// Create a metric
MetricsApi metricsApi = apiFactory.getMetricsApi();
MetricInsert metricInsert = new MetricInsert("Client Test Metric",
        "Client Test Metric Description", DataType.DOUBLE, SubjectType.APPLICATION,
        "req/s", 60);
   
Metric metric = metricsApi.insert(metricInsert);


// Create a metric group. The last parameter is the parent id which is optional.
MetricGroupInsert insert = new MetricGroupInsert("Name of the metric group",
        "The description", "The Type", SubjectType.APPLICATION, null);
MetricGroup metricGroup = metricsApi.insertMetricGroup(insert);


// Add a metric into a metric group.
metricsApi.addMetricToGroup(metric.id, metricGroup.id);

// Add a group into another group.
metricsApi.addGroupToGroup(childMetricGroup.id, parentMetricGroup.id);

// Is possible to filter the Metrics. e.g. filter by metric name:
Options options = new Builder().selectBy("name", "Average subscription rate").build();
metricsApi.all(options);
```

### Data

_Insert data for the metrics._

```java
// DataInsertBuilder is used to Build the InsertData object.
DataInsertBuilder builder = new DataInsertBuilder();

// DataInsertBuilder can store data for multiple metrics. It also supports adding multiple 
// values for the same metric but each one should have a different time stamp.
// e.g. builder.addDoubleData(<metric id>, <timestamp>, <value>);

builder.addDoubleData(metric.id, 0, 3.3); // For the given metric, insert value 3.3 at the current timestamp

builder.addDoubleData(metric.id, -120, 4.3); // For the given metric, insert value 4.3 at the timestamp 120 seconds ago

builder.addDoubleData(metric.id, 1462816055, 4.8); // For the given metric, insert value 4.8 at the unix timestamp 1462816055

// Make the insert request. The first parameter is the Subject Id which in this case is "a", meaning that the data
// is inserted for an application. For more details about subjectId check [Subject](#subject) section.
Msg response = apiFactory.getDataApi().insert("a", builder.build());

// The data can be inserted also for a server
String subjectId = "s" + server.id;
Msg response = apiFactory.getDataApi().insert(subjectId, builder.build());

```

### Servers

```java
// Insert a new Server.
ServerInsert serverInsert = new ServerInsert("Server Test Name", "Description", "Type", null);
Server server = apiFactory.getServersApi().insert(serverInsert);

// To get a server by the name we can add a filter.
Options filter = new Builder().selectBy("name", "Ubuntu Server").build();
apiFactory.getServersApi().all(filter);

// Get the server attributes.
Options options = new Options.Builder().expand("attributes").build();
Server server = serversApi.getServer(server.id, options);
// or
List<Server> servers = serversApi.all(options);
```

### Events

```java
// Insert a new Event.
EventsApi eventsApi = apiFactory.getEventsApi();
EventInsert eventInsert = new EventInsert("Event name");
Event event = eventsApi.insert(eventInsert);

// Insert data for a event. Subject type "a" means that the event if for the application.
EventDataInsert data = new EventDataInsert("Alert", -120l, 0l, null, "a");
EventData eventData = eventsApi.insertData(event.id, data));

// Notice that the timestamps can be negative meaning seconds ago
// or positive values meaning unix timastamp format. Zero means now.
EventData eventData = eventsApi.allData(event.id, -300l, 0l));

// Get data for a event by id.
EventData eventData = eventsApi.getData(event.id, eventData.id);

// Delete data for a event by ids.
Msg msg = eventsApi.deleteData(event.id, eventData.id);
```

### Requests

```java
// Get a request by id.
Request request = requestsApi.get(request.id);

// Get all Requests.
List<Request> requests = requestsApi.all();

// Get all Requests and filter by attributes.
Options options = new Options.Builder().selectBy("name", "example.coscale.com").build();
List<Request> requests = requestsApi.all(options);
```

## Subject

A metric or a event is defined on either a "SERVER" or "APPLICATION". 
This allows for metric per server or on the whole application.

The Subject Id is used to identify the server or the application. The format should be the following:
```java
// For application.
dataApi.insert("a", data);
// For server is required the server id.
dataApi.insert("s123", data);

// Insert event data for a specific server/application.
String subjectId = "s" + server.id;
EventDataInsert data = new EventDataInsert("Event message", -120l, 0l, null, subjectId);
EventData data = eventsApi.insertData(event.id, data));
```

## Error handling

If there is an unsuccessful response then an CoscaleApiException will be thrown.
The exception will have attached the status code of the response.

```java
try {
    Msg response = apiFactory.getDataApi().insert("a", builder.build());
} catch (CoscaleApiException e) {
    if (e.statusCode == HttpURLConnection.HTTP_NOT_FOUND) {
        System.out.println("Not found. " + e.getMessage());
    } else if (e.statusCode == 409) {
    	// See response message for explanation (e.g. duplicate entries: 2 server with the same name).
    } else if (e.statusCode == -1) {
    	// Get the inner exception. 
        System.out.println(e.getCause().getLocalizedMessage());
    }
}
```

## Advanced Configuration

### Source

The default source for resources is "Java SDK". This can be changed using:

```java
apiFactory.getApiClient().setSource("New Source");
```

### Timeouts

The default connection and request timeouts are set to 15000 ms by default.
These times can be changed using:

```java
apiFactory.getApiClient().setConnectionTimeout(1000);
apiFactory.getApiClient().setReadTimeout(1000);
```

