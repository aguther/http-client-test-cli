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
      var resultGetIssue = jiraApiClient.getIssue("CPG-1");
      if (resultGetIssue == null) {
        logger.atError().log("Issue not found");
      } else {
        logger.atInfo().log("Issue: {}", resultGetIssue);
      }

      var resultGetIssueAsJson = jiraApiClient.getIssueAsJson("CPG-1");
      if (resultGetIssueAsJson == null) {
        logger.atError().log("Issue not found");
      } else {
        logger.atInfo().log("Issue: {}", resultGetIssueAsJson);
      }

      var createIssueResult = jiraApiClient.createIssue(
          "{\n"
              + "  \"fields\": {\n"
              + "    \"project\": {\n"
              + "      \"key\": \"CPG\"\n"
              + "    },\n"
              + "    \"summary\": \"This is a issue that was created via REST API\",\n"
              + "    \"description\": {\n"
              + "      \"type\": \"doc\",\n"
              + "      \"version\": 1,\n"
              + "      \"content\": [\n"
              + "        {\n"
              + "          \"type\": \"paragraph\",\n"
              + "          \"content\": [\n"
              + "            {\n"
              + "              \"type\": \"text\",\n"
              + "              \"text\": \"Creating of an issue using project keys and issue type names using the REST API\"\n"
              + "            }\n"
              + "          ]\n"
              + "        }\n"
              + "      ]\n"
              + "    },\n"
              + "    \"issuetype\": {\n"
              + "      \"name\": \"Task\"\n"
              + "    }\n"
              + "  }\n"
              + "}\n"
      );
      if (createIssueResult == null) {
        logger.atError().log("Failed to create issue");
      } else {
        logger.atInfo().log("Issue: {}", createIssueResult);
      }

      var resultSearchIssue = jiraApiClient.searchIssue("summary~'Implementation'");
      logger.atInfo().log("Issues found: {}", resultSearchIssue.total());

    } catch (Exception e) {
      logger.atError().log("Error while searching for issues", e);
    }

    logger.atInfo().log("HttpClientTestCliCommand finished");
  }
}
