package com.example.http.model;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import lombok.Builder;

@Builder
public record HttpRoute(String uri, HttpMethod method, Handler<RoutingContext> handler) { }
