package steps;

import clients.RestClient;
import io.qameta.allure.Step;
import models.PostDto;
import org.aeonbits.owner.ConfigFactory;
import utils.IConfigurationVariables;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

public class ApiSteps {
    RestClient restClient = new RestClient();
    IConfigurationVariables config = ConfigFactory.create(IConfigurationVariables.class);

    @Step("Get all posts by userId {userId}")
    public List<PostDto> getPostByUserId(final int userId){
        return asList(restClient.sendGetRequest(format(config.filterPostByUserIdEndPoint(), userId),PostDto[].class));
    }

    @Step("Get all posts by resourceId {resourceId}")
    public PostDto getPostByResourceId(final int resourceId){
        return restClient.sendGetRequest(format(config.filterPostByIdEndPoint(), resourceId), PostDto.class);
    }

    @Step("Get all posts")
    public List<PostDto> getAllPosts(){
        return asList(restClient.sendGetRequest(PostDto[].class));
    }
}
