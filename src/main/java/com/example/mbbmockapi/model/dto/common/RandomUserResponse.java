package com.example.mbbmockapi.model.dto.common;

import java.util.List;

public record RandomUserResponse(
        List<Result> results
) {
    public record Result(
            Name name,
            String email
    ) {}

    public record Name(
            String title,
            String first,
            String last
    ) {}
}
