package ua.kay.monolith.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class RestErrorResponse {
    private final String type;
    private final int status;
    private final String code;
    private final List<BindingRestError> errors;
    private final String requestId; // for future use, should be null for now

    public RestErrorResponse(String type, int status, String code, List<BindingRestError> errors, String requestId) {
        this.status = status;
        this.type = type;
        this.code = code;
        this.errors = errors;
        this.requestId = requestId;
    }

    public String getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public List<BindingRestError> getErrors() {
        return errors;
    }

    public String getRequestId() {
        return requestId;
    }

    @Override
    public String toString() {
        return "RestErrorResponse [type=" + type + ", status=" + status + ", code=" + code + ", errors=" + errors
                + ", requestId=" + requestId + "]";
    }
}
