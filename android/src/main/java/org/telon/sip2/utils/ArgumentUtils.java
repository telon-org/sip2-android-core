package org.telon.sip2.utils;

import android.content.Intent;
import android.util.Log;
import com.google.gson.*;
import com.google.gson.internal.LazilyParsedNumber;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class ArgumentUtils {

    public static Object fromJson(String json) {
        return fromJson(new JsonParser().parse(json));
    }

    private static Object fromJson(JsonElement el) {
        if (el instanceof JsonObject) {
            return fromJsonObject(el.getAsJsonObject());
        } else if (el instanceof JsonArray) {
            return fromJsonArray(el.getAsJsonArray());
        } else {
            return fromJsonPrimitive(el.getAsJsonPrimitive());
        }
    }

    private static Map<String, Object> fromJsonObject(JsonObject object) {
        Map<String, Object> result = new HashMap<>();

        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            Object value = fromJson(entry.getValue());
            result.put(entry.getKey(), value);
        }

        return result;
    }

    private static Object fromJsonPrimitive(JsonPrimitive object) {
        if (object.isString()) {
            return object.getAsString();
        } else if (object.isNumber()) {
            return object.getAsNumber();
        } else if (object.isBoolean()) {
            return object.getAsBoolean();
        }

        return null;
    }

    private static List<Object> fromJsonArray(JsonArray arr) {
        List<Object> result = new ArrayList<>();

        for (JsonElement el : arr) {
            Object item = fromJson(el);
            result.add(item);
        }

        return result;
    }

    public static String dumpIntentExtraParameters(Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            return "empty extras";
        }

        Set<String> keys = intent.getExtras().keySet();
        Map<String, Object> data = new HashMap<>(keys.size());

        for (String key : keys) {
            data.put(key, intent.getExtras().get(key));
        }

        Gson gson = new Gson();
        return gson.toJson(data);
    }

}
