package API;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification reqSpec(){
        return new RequestSpecBuilder()
                .setBaseUri("https://testslotegrator.com")
                .setContentType(ContentType.JSON)
                .build();
    }
    public  static ResponseSpecification responseSpec(int status){
        return  new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }
    public static void installSpec(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification=request;
        RestAssured.responseSpecification=response;

    }
}
