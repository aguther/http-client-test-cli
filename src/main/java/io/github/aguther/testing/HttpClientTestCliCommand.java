package io.github.aguther.testing;

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

  private final GithubLowLevelClient githubLowLevelClient;
  private final GithubApiClient githubApiClient;

  @Inject
  public HttpClientTestCliCommand(
      GithubLowLevelClient githubLowLevelClient,
      GithubApiClient githubApiClient
  ) {
    this.githubLowLevelClient = githubLowLevelClient;
    this.githubApiClient = githubApiClient;
  }

  public void run() {
    logger.atInfo().log("HttpClientTestCliCommand starting");

    var lowLevelReleases = githubLowLevelClient.fetchReleases();
    var apiReleases = githubApiClient.fetchReleases();

    logger.atInfo().log("Fetching releases with low-level client: {}", githubLowLevelClient.fetchReleases().toString());
    logger.atInfo().log("Fetching releases with api client: {}", githubApiClient.fetchReleases().toString());

    logger.atInfo().log("HttpClientTestCliCommand finished");
  }
}
