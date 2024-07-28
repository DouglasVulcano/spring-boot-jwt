package io.github.douglas.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.douglas.security.entities.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
