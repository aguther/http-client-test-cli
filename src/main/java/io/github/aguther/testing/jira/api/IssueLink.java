package io.github.aguther.testing.jira.api;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record IssueLink(
    String id,
    IssueLinkType type,
    @Nullable IssueLinkInfo inwardIssue,
    @Nullable IssueLinkInfo outwardIssue
) {

}
