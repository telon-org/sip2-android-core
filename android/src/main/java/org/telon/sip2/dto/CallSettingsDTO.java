package org.telon.sip2.dto;

import com.google.gson.Gson;
import java.util.Map;

public class CallSettingsDTO {
    private Integer audioCount;
    private Integer videoCount;
    private Integer flag;
    private Integer requestKeyframeMethod;

    public Integer getAudioCount() {
        return audioCount;
    }

    public Integer getVideoCount() {
        return videoCount;
    }

    public Integer getFlag() {
        return flag;
    }

    public Integer getRequestKeyframeMethod() {
        return requestKeyframeMethod;
    }

    public void setAudioCount(Integer audioCount) {
        this.audioCount = audioCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public void setRequestKeyframeMethod(Integer requestKeyframeMethod) {
        this.requestKeyframeMethod = requestKeyframeMethod;
    }

    public String toJson () {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static CallSettingsDTO fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, CallSettingsDTO.class);
    }

    public static CallSettingsDTO fromMap(Map<String, Object> data) {
        CallSettingsDTO result = new CallSettingsDTO();

        if (data.containsKey("audioCount")) {
            Object value = data.get("audioCount");
            if (value instanceof Integer) {
                result.setAudioCount((Integer) value);
            } else if (value instanceof Number) {
                result.setAudioCount(((Number) value).intValue());
            }
        }
        if (data.containsKey("videoCount")) {
            Object value = data.get("videoCount");
            if (value instanceof Integer) {
                result.setVideoCount((Integer) value);
            } else if (value instanceof Number) {
                result.setVideoCount(((Number) value).intValue());
            }
        }
        if (data.containsKey("flag")) {
            Object value = data.get("flag");
            if (value instanceof Integer) {
                result.setFlag((Integer) value);
            } else if (value instanceof Number) {
                result.setFlag(((Number) value).intValue());
            }
        }
        if (data.containsKey("requestKeyframeMethod")) {
            Object value = data.get("requestKeyframeMethod");
            if (value instanceof Integer) {
                result.setRequestKeyframeMethod((Integer) value);
            } else if (value instanceof Number) {
                result.setRequestKeyframeMethod(((Number) value).intValue());
            }
        }

        return result;
    }

}
