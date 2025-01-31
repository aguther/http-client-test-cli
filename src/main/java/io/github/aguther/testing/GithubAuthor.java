package io.github.aguther.testing;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record GithubAuthor(String login, String url) {

}
