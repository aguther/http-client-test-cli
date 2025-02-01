package io.github.aguther.testing.jira;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.AUTHORIZATION;
import static io.micronaut.http.HttpHeaders.USER_AGENT;

import io.github.aguther.testing.jira.api.Issue;
import io.github.aguther.testing.jira.api.SearchIssueResult;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.retry.annotation.Retryable;

@Client(id = "jira")
@Header(name = USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = ACCEPT, value = "application/json")
@Header(name = AUTHORIZATION, value = "Basic ${jira.token}")
public interface JiraApiClient {

  @Get("/rest/api/3/search")
  @SingleResult
  SearchIssueResult searchIssue(@QueryValue String jql);

  @Get("/rest/api/3/issue/{issueIdOrKey}")
  @SingleResult
  @Retryable
  Issue getIssue(String issueIdOrKey);

  @Get("/rest/api/3/issue/{issueIdOrKey}")
  @SingleResult
  @Retryable
  JsonNode getIssueAsJson(String issueIdOrKey);

  @Post("/rest/api/3/issue")
  JsonNode createIssue(@Body String issueBody);
}
