package com.codebits.codemichigan.michiganoutdoors.data.type_adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by joshuakovach on 10/3/14.
 */
public class BooleanAsIntTypeAdapter extends TypeAdapter<Boolean> {
    @Override public void write(JsonWriter out, Boolean value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value);
        }
    }
    @Override public Boolean read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        switch (peek) {
            case BOOLEAN:
                return in.nextBoolean();
            case NULL:
                in.nextNull();
                return null;
            case NUMBER:
                return in.nextInt() != 0;
            case STRING:
                return in.nextString().equalsIgnoreCase("1");
            default:
                throw new IllegalStateException("Expected BOOLEAN or NUMBER but was " + peek);
        }
    }
}
