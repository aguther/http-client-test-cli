package io.github.aguther.testing.jira.api;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record IssuePriority(
    String id,
    String name
) {

}
