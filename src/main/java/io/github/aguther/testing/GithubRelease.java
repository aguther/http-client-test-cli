package io.github.aguther.testing;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record GithubRelease(String name, String url, String tag_name, GithubAuthor author) {

}
