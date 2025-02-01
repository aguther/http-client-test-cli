package io.github.aguther.testing.jira.api;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Issue(
    String id,
    String key,
    IssueFields fields
) {

}
