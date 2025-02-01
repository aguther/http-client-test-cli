package io.github.aguther.testing.jira.api;

import io.micronaut.json.tree.JsonNode;
import io.micronaut.serde.annotation.Serdeable;
import java.util.List;

@Serdeable
public record IssueFields(
    String summary,
    JsonNode description,
    IssueStatus status,
    String created,
    String updated,
    List<IssueLink> issuelinks,
    List<String> labels,
    IssuePriority priority,
    IssueProject project
) {

}
