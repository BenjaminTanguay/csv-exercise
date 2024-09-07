package com.example.http.route;

import com.example.dao.Dao;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DataFilterRequestHandler implements Handler<RoutingContext> {

    private final Dao dao;

    @Inject
    public DataFilterRequestHandler(Dao dao) {
        this.dao = dao;
    }

    @Override
    public void handle(RoutingContext routingContext) {
        log.info("HTTP Request in on uri {}", routingContext.request().uri());


        Map<String, String> filters = new HashMap<>();
        routingContext.queryParams().forEach(entry -> filters.put(entry.getKey(), entry.getValue()));


        List<Map<String, String>> result = dao.filterRecords(filters);

        JsonArray response = convertListToJsonArray(result);

        log.debug("HTTP Request out on uri {}: ", routingContext.request().uri(), response.encodePrettily());
        routingContext.response()
                .putHeader("content-type", "application/json")
                .end(response.toBuffer());
    }

    private static JsonArray convertListToJsonArray(List<Map<String, String>> arrayElements) {
        JsonArray jsonArray = new JsonArray();

        for (Map<String, String> arrayElement : arrayElements) {
            JsonObject jsonObject = JsonObject.mapFrom(arrayElement);
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
