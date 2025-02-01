package io.github.aguther.testing.jira;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;

@ConfigurationProperties(JiraConfiguration.PREFIX)
@Requires(property = JiraConfiguration.PREFIX)
public record JiraConfiguration(
    String url,
    String token
) {

  public static final String PREFIX = "jira";
}
