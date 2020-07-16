package storage;

import io.qameta.allure.Step;
import models.PostDto;
import utils.IConfigurationVariables;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.aeonbits.owner.ConfigFactory.create;
import static utils.FileHelper.readFile;
import static utils.JsonUtil.fromJson;

public class PostStorage {

    @Step("Get expected post from file")
    public static List<PostDto> getExpectedPosts(){
        return stream(fromJson(readFile(new File(
                create(IConfigurationVariables.class).expextedPostsSource())), PostDto[].class))
                .collect(Collectors.toList());
    }

    @Step("Filter expected posts by resourceId {resourceId}")
    public static PostDto filterPostsByResourceId(final int resourceId){
        return getExpectedPosts().stream().filter(post -> post.getId().equals(resourceId)).findFirst().orElseThrow(
                () -> new IllegalArgumentException(format("Cann't find any postt with resourceId [%s]", resourceId)));
    }

    @Step("Filter expected posts by userId {userId}")
    public static List<PostDto> filterPostsByUserId(final int userId){
        return getExpectedPosts().stream().filter(post -> post.getUserId().equals(userId)).collect(toList());
    }
}
