package io.github.aguther.testing.jira;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.AUTHORIZATION;
import static io.micronaut.http.HttpHeaders.USER_AGENT;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

@Client(id = "jira")
@Header(name = USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = ACCEPT, value = "application/json")
@Header(name = AUTHORIZATION, value = "Basic ${jira.token}")
public interface JiraApiClient {

  @Get("/rest/api/3/search")
  @SingleResult
  JiraSearchIssueResult searchIssue(@QueryValue String jql);

  @Get("/rest/api/3/issue/{issueIdOrKey}")
  @SingleResult
  JiraIssue getIssue(String issueIdOrKey);
}
