package com.codebits.codemichigan.michiganoutdoors.data.type_adapters;

import android.location.Location;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by joshuakovach on 10/3/14.
 */
public class LocationTypeAdapter extends TypeAdapter<Location> {
    @Override
    public void write(JsonWriter out, Location location) throws IOException {
        if (location == null) {
            out.nullValue();
        } else {
            out.beginObject();
            out.name("latitude").value(location.getLatitude());
            out.name("longitude").value(location.getLongitude());
            out.endObject();
        }
    }

    @Override
    public Location read(JsonReader in) throws IOException {
        Location location = new Location("");
        in.beginObject();
        while (in.hasNext()) {
            String s = in.nextName();
            if (s.equals("latitude")) {
                location.setLatitude(in.nextDouble());
            } else if (s.equals("longitude")) {
                location.setLongitude(in.nextDouble());
            } else {
                in.nextBoolean();
            }
        }
        in.endObject();

        return location;
    }
}
