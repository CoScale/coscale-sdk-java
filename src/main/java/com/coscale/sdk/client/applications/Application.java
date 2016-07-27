package com.coscale.sdk.client.applications;

import com.google.common.base.MoreObjects;

import javax.annotation.Nullable;

public class Application {

    @Nullable
    public Long id;

    @Nullable
    public Long version;

    @Nullable
    public String name;

    @Nullable
    public String appId;

    @Nullable
    public Long ownerId;

    @Nullable
    public ApplicationState state;

    @Nullable
    public boolean invoiceYearly;

    @Nullable
    public Long startType;

    @Nullable
    public Long endTimeFreeTrial;

    @Nullable
    public Long planId;

    public Application(Long id, Long version, String name, String appId, Long ownerId, ApplicationState state, boolean invoiceYearly, Long startType, Long endTimeFreeTrial, Long planId) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.appId = appId;
        this.ownerId = ownerId;
        this.state = state;
        this.invoiceYearly = invoiceYearly;
        this.startType = startType;
        this.endTimeFreeTrial = endTimeFreeTrial;
        this.planId = planId;
    }

    public Application() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("version", version).add("name", name).add("appId", appId)
                .add("ownerId", ownerId).add("state", state).add("invoiceYearly", invoiceYearly).add("startType", startType)
                .add("endTimeFreeTrial", endTimeFreeTrial).add("planId", planId).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        if (invoiceYearly != that.invoiceYearly) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (appId != null ? !appId.equals(that.appId) : that.appId != null) return false;
        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (startType != null ? !startType.equals(that.startType) : that.startType != null) return false;
        if (endTimeFreeTrial != null ? !endTimeFreeTrial.equals(that.endTimeFreeTrial) : that.endTimeFreeTrial != null)
            return false;
        return planId != null ? planId.equals(that.planId) : that.planId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (appId != null ? appId.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (invoiceYearly ? 1 : 0);
        result = 31 * result + (startType != null ? startType.hashCode() : 0);
        result = 31 * result + (endTimeFreeTrial != null ? endTimeFreeTrial.hashCode() : 0);
        result = 31 * result + (planId != null ? planId.hashCode() : 0);
        return result;
    }
}
