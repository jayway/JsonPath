package com.jayway.jsonpath.matchers;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJsonString;
import static com.jayway.jsonpath.matchers.helpers.ResourceHelpers.resource;
import static com.jayway.jsonpath.matchers.helpers.TestingMatchers.MATCH_TRUE_TEXT;
import static com.jayway.jsonpath.matchers.helpers.TestingMatchers.MISMATCHED_TEXT;
import static com.jayway.jsonpath.matchers.helpers.TestingMatchers.withPathEvaluatedTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Test;

public class IsJsonStringTest
{
    private static final String BOOKS_JSON = resource("books.json");


    @Test
    public void shouldMatchJsonStringEvaluatedToTrue() {
        assertThat(BOOKS_JSON, isJsonString(withPathEvaluatedTo(true)));
    }

    @Test
    public void shouldNotMatchJsonStringEvaluatedToFalse() {
        assertThat(BOOKS_JSON, not(isJsonString(withPathEvaluatedTo(false))));
    }

    @Test
    public void shouldNotMatchInvalidJsonString() {
        assertThat("invalid-json", not(isJsonString(withPathEvaluatedTo(true))));
        assertThat("invalid-json", not(isJsonString(withPathEvaluatedTo(false))));
    }

    @Test
    public void shouldBeDescriptive() {
        Matcher<String> matcher = isJsonString(withPathEvaluatedTo(true));
        Description description = new StringDescription();
        matcher.describeTo(description);
        assertThat(description.toString(), startsWith("is json"));
        assertThat(description.toString(), containsString(MATCH_TRUE_TEXT));
    }

    @Test
    public void shouldDescribeMismatchOfValidJson() {
        Matcher<String> matcher = isJsonString(withPathEvaluatedTo(true));
        Description description = new StringDescription();
        matcher.describeMismatch(BOOKS_JSON, description);
        assertThat(description.toString(), containsString(MISMATCHED_TEXT));
    }

    @Test
    public void shouldDescribeMismatchOfInvalidJson() {
        Matcher<String> matcher = isJsonString(withPathEvaluatedTo(true));
        Description description = new StringDescription();
        matcher.describeMismatch("invalid-json", description);
        assertThat(description.toString(), containsString("\"invalid-json\""));
    }
}