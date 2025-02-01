package io.github.aguther.testing.jira.api;

import io.micronaut.serde.annotation.Serdeable;
import java.util.List;

@Serdeable
public record SearchIssueResult(
    String startAt,
    String maxResults,
    String total,
    List<Issue> issues
) {

}
