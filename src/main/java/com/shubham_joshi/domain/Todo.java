package com.shubham_joshi.domain;

import lombok.Builder;

@Builder
public record Todo(String title, ItemStatus status, int order) {}
