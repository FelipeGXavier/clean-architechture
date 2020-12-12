package com.demo.clean.demo.blog.unit;

import com.demo.clean.blog.domain.CommentBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentBodyTest {

    @Test
    @DisplayName("Valid comment")
    public void validComment() {
        var comment = "LGTM!";
        assertEquals(CommentBody.of(comment).toString().length(), comment.length());
    }

    @Test
    @DisplayName("Null comment")
    public void nullComment() {
        String comment = null;
        var exception = Assertions.assertThrows(IllegalArgumentException.class, () -> CommentBody.of(comment));
        assertEquals(exception.getMessage(), "Invalid Comment body");
    }

    @Test
    @DisplayName("Comment without minimum length")
    public void withoutMinLength() {
        String comment = "";
        var exception = Assertions.assertThrows(IllegalArgumentException.class, () -> CommentBody.of(comment));
        assertEquals(exception.getMessage(), "Invalid Comment body");
    }

    @Test
    @DisplayName("Comment exceed maximum length")
    public void exceedMaximumLength() {
        String comment = "P5OPU7xVUmXUCT6iPW2FB0A4XOQfMCDcBDeh1gEf9G5GHoZbpjr9gbsMD7GLmVv102z5SCqID3WoblesV7QBuqehb6kuEaDKDL1ozfpXoUMlCtzkY7Tl0Mt2eVzxopu9xh7VvaeeeATc51Z20jIDrjPsK6AqeYWZ3zdgSfFKWOazM7HpQ8VVz6iEte1v8UMwQOplaoV4mwT8uHetuNeTh5PHHO04D39M8Ntu3wMYZg2NaePAVVUBbmGyuPb3eAy5y7MdWjPAMoHo0V2CiekcJBacDy7htjhfNcStB82A1kBzb4m7gOq0AuJMDJG5nqHAPHQGeEGA9xBkSuq0tJ9GhUMyOlPZypQbKjIVTbb8RlM7MYPx8vq2yfKGBdqVp5TINe6n8DBiQxk7dbi6RrkvDpBTGK85nlsFm3gWRx617TBqX6qevPYjw1GP9kUkLSRnd2D7hFPL1yfPotYkxGBfV85UtlKu39dXdYVbK6iFhOOePshVvTumKNYVP1tMO6IvMnSRdnDmb9CUjTNzF7ZZ5w3iXmdKk5l4UixHTuELvPiXIXdDV54BHAlKhZRoLk7W0RYg3UQHFxTNNIj5WVxwAvhLqUwlX82ZiskwSY1wmivuFN4LOv7PdCI2KoHNR8UaMqRilp8kSqb027v7467tb7hRQ9BGNRCHFKefsc761QgU3GZPOPXFHR6JBE6gLYsoheDSKNQfAo8WXNjDxsw8SbH3adGbx2IWHAXvpCqPBaTdBm5qJJ3d38S8Ktr3OHzJVsf2jt5JldZrGgiawWJ9Jxhyvi63bsqyGMPvg2vJFUoERQwmAJfKVqFiIx9GKUGSy7EOS20L7S30NUEyAMvFH1Es6Jp9Pzc4Lum8zkIGCSnOi0KnN8VUgFFQwicReL6PxqvKgMcVn6OK0hJZsVByycyiFYusuDehPoHzPBBwSmW6TPoxbt0g5PhfDV70infkuN1Vcklv37nQXeQeS9zjrB2q7CQo70melOcMPCcrs";
        var exception = Assertions.assertThrows(IllegalArgumentException.class, () -> CommentBody.of(comment));
        assertEquals(exception.getMessage(), "Invalid Comment body");
    }

    @Test
    @DisplayName("Comment with muted words")
    public void mutedWords() {
        String comment = "LGTM! foo";
        var exception = Assertions.assertThrows(IllegalArgumentException.class, () -> CommentBody.of(comment));
        assertEquals(exception.getMessage(), "Invalid Comment body");
    }
}
