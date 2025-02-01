package io.github.aguther.testing.jira.api;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record IssueProject(
    String id,
    String key,
    String name
) {

}
