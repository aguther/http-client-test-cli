package io.github.aguther.testing.jira;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record JiraIssueFields(
    String summary
) {

}
