package io.github.aguther.testing.jira.api;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record IssueLinkType(
    String id,
    String name
) {

}
