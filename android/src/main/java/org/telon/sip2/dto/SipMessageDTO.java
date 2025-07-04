package org.telon.sip2.dto;

import com.google.gson.Gson;

import org.pjsip.pjsua2.SipHeaderVector;

import java.util.HashMap;
import java.util.Map;

public class SipMessageDTO {

    private String targetUri;
    private Map<String, String> headers;
    private String contentType;
    private String body;

    public String getTargetUri() {
        return targetUri;
    }

    public void setTargetUri(String targetUri) {
        this.targetUri = targetUri;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String toJson () {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static SipMessageDTO fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, SipMessageDTO.class);
    }

    public static SipMessageDTO fromMap(Map<String, Object> data) {
        SipMessageDTO result = new SipMessageDTO();

        if (data.containsKey("targetURI")) {
            Object value = data.get("targetURI");
            if (value instanceof String) {
                result.setTargetUri((String) value);
            }
        }
        if (data.containsKey("headers")) {
            Object headersData = data.get("headers");
            if (headersData instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> headersMap = (Map<String, Object>) headersData;
                Map<String, String> headers = new HashMap<>();

                for (Map.Entry<String, Object> entry : headersMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        headers.put(key, (String) value);
                    }
                }

                result.setHeaders(headers);
            }
        }
        if (data.containsKey("contentType")) {
            Object value = data.get("contentType");
            if (value instanceof String) {
                result.setContentType((String) value);
            }
        }
        if (data.containsKey("body")) {
            Object value = data.get("body");
            if (value instanceof String) {
                result.setBody((String) value);
            }
        }

        return result;
    }

}
