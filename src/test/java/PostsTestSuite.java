import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import models.PostDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import steps.ApiSteps;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.empty;
import static storage.PostStorage.filterPostsByUserId;
import static storage.PostStorage.filterPostsByResourceId;
import static storage.PostStorage.getExpectedPosts;


public class PostsTestSuite {

    ApiSteps apiSteps = new ApiSteps();

    @ValueSource(ints  = {1, 6})
    @Description
    @ParameterizedTest(name = "Get posts by userId parameter")
    @TmsLink(value = "TC-01")
    public void verifyPostsByUserId(final int userId) {
        List<PostDto> actualPosts = apiSteps.getPostByUserId(userId);
        List<PostDto> expectedPosts = filterPostsByUserId(userId);
        assertThat("actual post list contains unexpected posts", actualPosts, is(expectedPosts));
    }

    @ParameterizedTest(name = "Get posts with negative userId parameter")
    @ValueSource(ints  = {-1, 1000})
    @TmsLink(value = "TC-02")
    public void verifyPostsByUserIdNegative(final int userId) {
        List<PostDto> actualPosts = apiSteps.getPostByUserId(userId);
        assertThat("actual post list contains unexpected posts", actualPosts, empty());
    }

    @ParameterizedTest(name = "Get posts by resourceId parameter")
    @ValueSource(ints  = {1, 6})
    @TmsLink(value = "TC-03")
    public void verifyPostsByResourceId(final int resourceId) {
        PostDto actualPosts = apiSteps.getPostByResourceId(resourceId);
        PostDto expectedPosts = filterPostsByResourceId(resourceId);
        assertThat("actual post list contains unexpected posts", actualPosts,  is(expectedPosts));
    }

    @ParameterizedTest(name = "Get posts by negative resourceId parameter")
    @ValueSource(ints  = {0, 900})
    @TmsLink(value = "TC-04")
    public void verifyPostsByResourceIdNegative(final int resourceId) {
        PostDto actualPost = apiSteps.getPostByResourceId(resourceId);
        assertThat("actual post list contains unexpected posts", actualPost, is(PostDto.builder().build()));
    }

    @Test
    @DisplayName("Get all available posts")
    @TmsLink(value = "TC-05")
    public void verifyPostsByUserId() {
        List<PostDto> actualPosts = apiSteps.getAllPosts();
        List<PostDto> expectedPosts = getExpectedPosts();
        assertThat("actual post list contains unexpected posts", actualPosts, is(expectedPosts));
    }
}
