package clients;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import utils.IConfigurationVariables;

import static io.restassured.RestAssured.given;

public class RestClient {
    protected Response response;
    protected ValidatableResponse json;
    protected RequestSpecification request;
    IConfigurationVariables config = ConfigFactory.create(IConfigurationVariables.class);

    public RestClient() {
        request = new RequestSpecBuilder().addHeader("X-ACCEPTANCE-TEST", "TRUE")
                .setConfig(new RestAssuredConfig().sslConfig(new SSLConfig().allowAllHostnames()
                        .relaxedHTTPSValidation("TLSv1.2")).logConfig(new LogConfig()))
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addHeader("X-ACCEPTANCE-TEST", "TRUE")
                .setBaseUri(config.baseUrl()).build();
    }

    //GET
    public <T> T  sendGetRequest(String endPoint, Class<T> returnClass) {
        return  given().spec(request).get(endPoint).as(returnClass);
    }

    public <T> T  sendGetRequest(Class<T> returnClass) {
        return  given().spec(request).get().as(returnClass);
    }

    //POST
    public <T> T sendPostRequest(String endPoint, Object body, Class<T> returnClass) {
        return given().spec(request).body(body).post(endPoint).as(returnClass);
    }
}