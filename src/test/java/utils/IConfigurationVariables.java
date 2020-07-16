package utils;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.DisableFeature;
import static org.aeonbits.owner.Config.LoadPolicy;
import static org.aeonbits.owner.Config.Sources;
import static org.aeonbits.owner.Config.LoadType;

import static org.aeonbits.owner.Config.DisableableFeature.PARAMETER_FORMATTING;

@DisableFeature(PARAMETER_FORMATTING)
@LoadPolicy(LoadType.FIRST)
@Sources("classpath:qa.properties")
public interface IConfigurationVariables extends Config {
    public String baseUrl();
    public String expextedPostsSource();
    public String filterPostByUserIdEndPoint();
    public String filterPostByIdEndPoint();
}