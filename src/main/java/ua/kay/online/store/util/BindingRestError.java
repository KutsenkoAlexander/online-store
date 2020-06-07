package ua.kay.online.store.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public final class BindingRestError {

    private final String reason;
    private final String name;
    private final String message;
    private final String location;

    public BindingRestError(String reason, String message, String name, String location) {
        super();
        this.reason = reason;
        this.name = name;
        this.message = message;
        this.location = location;
    }

    public String getReason() {
        return reason;
    }


    public String getMessage() {
        return message;
    }


    public String getName() {
        return name;
    }


    public String getLocation() {
        return location;
    }


    @Override
    public String toString() {
        return "RestError [reason=" + reason + ", message=" + message + ", name=" + name + ", location=" + location
                + "]";
    }
}
