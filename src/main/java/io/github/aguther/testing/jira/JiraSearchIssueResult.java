package io.github.aguther.testing.jira;

import io.micronaut.serde.annotation.Serdeable;
import java.util.List;

@Serdeable
public record JiraSearchIssueResult(
    String startAt,
    String maxResults,
    String total,
    List<JiraIssue> issues
) {

}
