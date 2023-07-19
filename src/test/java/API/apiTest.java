package API;


import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;


public class apiTest {
    private static String bearerToken;

    List<getAllUsersResponse> allUsers = new LinkedList<>();
    private List<CreateUserResponse> users = new LinkedList<>();

    @Test
    public void autoToken() {

        Specifications.installSpec(Specifications.reqSpec(), Specifications.responseSpec(201));
        Authorization user = new Authorization("toktomovanurajym@gmail.com", "LfOn5WcjPIfN");
        token token = given()
                .body(user)
                .post("/api/tester/login")
                .then().log().all()
                .extract().as(token.class);
        bearerToken = token.getAccessToken();

    }

    @Test(priority = 1)
    public void createUser() {
        Specifications.installSpec(Specifications.reqSpec(), Specifications.responseSpec(201));
        Faker faker = new Faker();
        for (int i = 0; i < 12; i++) {

            String pass = faker.internet().password();
            CreateUserRequest user = new CreateUserRequest("som", faker.internet().emailAddress(), faker.name().name(), pass, pass,
                    faker.name().lastName(), faker.name().username());

            CreateUserResponse response = given()
                    .header("Authorization", "Bearer " + bearerToken)
                    .body(user)
                    .post("/api/automationTask/create")
                    .then().log().all()
                    .extract().as(CreateUserResponse.class);
            users.add(response);

        }
    }

    @Test(priority = 2)
    public void getOneTest() {
        Specifications.installSpec(Specifications.reqSpec(), Specifications.responseSpec(201));
        getOneUser user = new getOneUser(users.get(5).email);
        getOneUserResponse response = given()
                .header("Authorization", "Bearer " + bearerToken)
                .body(user)
                .post("/api/automationTask/getOne")
                .then().log().all()
                .extract().as(getOneUserResponse.class);


    }

    @Test(priority = 3)
    public void getAllUsers() {
        Specifications.installSpec(Specifications.reqSpec(), Specifications.responseSpec(200));
                 allUsers = given()
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/automationTask/getAll")
                .then().log().all()
                .extract().body().jsonPath().getList(".", getAllUsersResponse.class);
        System.out.println(allUsers.get(4).id);

        List<getAllUsersResponse> sortedUsers = allUsers.stream()
                .sorted(Comparator.comparing(user -> user.name, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        for (getAllUsersResponse user : sortedUsers) {
            System.out.println(user.name);
        }


    }
    @Test(priority = 4)
    public void deleteUsers(){
        Specifications.installSpec(Specifications.reqSpec(), Specifications.responseSpec(200));
        for(getAllUsersResponse response : allUsers){
            given()
                    .header("Authorization", "Bearer " + bearerToken)
                    .when()
                    .delete("/api/automationTask/deleteOne/" + response.id)
                    .then().log().all();
        }

        }

    @Test(priority = 5)
    public void emptyList(){
        Specifications.installSpec(Specifications.reqSpec(), Specifications.responseSpec(200));
        List<getAllUsersResponse> users = given()
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/automationTask/getAll")
                .then().log().all()
                .extract().body().jsonPath().getList(".", getAllUsersResponse.class);
        Assert.assertEquals(0,users.size());

    }


}
