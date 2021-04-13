package com.sinric.esp32_android_app;

import java.time.Instant;
import java.util.List;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

public class InfluxDBWrites {

    private static char[] token = "XKGbhSKhGFiGATUD9rk9GphKjvJOsbdJSxbAJE8Al24oHN4GLSIcTpYYnV2VZ_5YgWc_094i1E6q5vKxAmT4hQ==".toCharArray();
    private static String org = "5dbd651117628225";
    private static String bucket = "215b2aa6f4279d45";

    public static void main(final String[] args) {

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086", token, org, bucket);

        WriteApi writeApi = influxDBClient.getWriteApi();

        Point point = Point.measurement("temperature")
                .addTag("location", "west")
                .addField("value", 55D)
                .time(System.currentTimeMillis(), WritePrecision.MS);

        writeApi.writePoint(point);

        influxDBClient.close();
    }

}
