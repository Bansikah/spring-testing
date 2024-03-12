package com.bansikah.springtesting.post;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;


    @BeforeEach
    void setUp() {
        List<Post> posts = List.of(
                new Post(1, 1, "Hello, World!", "This is my first post", null));
   postRepository.saveAll(posts);
    }

    @Test
    public void returnPostByTitle() {
        Post post = postRepository.findByTitle("Hello, World!");
        assertThat(post).isNotNull();
    }
}