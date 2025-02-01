package io.github.aguther.testing.jira;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record JiraIssue(
    String id,
    String key,
    JiraIssueFields fields
) {

}
