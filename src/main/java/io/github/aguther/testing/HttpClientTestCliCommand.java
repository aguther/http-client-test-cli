package io.github.aguther.testing;

import io.github.aguther.testing.jira.JiraApiClient;
import io.micronaut.configuration.picocli.PicocliRunner;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;

@Command(
    name = "http-client-test-cli",
    mixinStandardHelpOptions = true
)
public class HttpClientTestCliCommand implements Runnable {

  private static final Logger logger = LoggerFactory.getLogger(HttpClientTestCliCommand.class);

  public static void main(String[] args) {
    PicocliRunner.run(HttpClientTestCliCommand.class, args);
  }

  private final JiraApiClient jiraApiClient;

  @Inject
  public HttpClientTestCliCommand(
      JiraApiClient jiraApiClient
  ) {
    this.jiraApiClient = jiraApiClient;
  }

  public void run() {
    logger.atInfo().log("HttpClientTestCliCommand starting");

    try {
      var resultGetIssue = jiraApiClient.getIssue("CPG-3");
      if (resultGetIssue == null) {
        logger.atError().log("Issue not found");
      } else {
        logger.atInfo().log("Issue: {}", resultGetIssue);
      }

      var resultGetIssueAsJson = jiraApiClient.getIssueAsJson("CPG-3");
      if (resultGetIssueAsJson == null) {
        logger.atError().log("Issue not found");
      } else {
        logger.atInfo().log("Issue: {}", resultGetIssueAsJson);
      }

      var resultSearchIssue = jiraApiClient.searchIssue("summary~'Implementation'");
      logger.atInfo().log("Issues found: {}", resultSearchIssue.total());

    } catch (Exception e) {
      logger.atError().log("Error while searching for issues", e);
    }

    logger.atInfo().log("HttpClientTestCliCommand finished");
  }
}
