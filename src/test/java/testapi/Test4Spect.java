package testapi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Test4Spect {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responSpec;

    @BeforeClass
    public static void createRequestAndResponseSpecification(){

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                build();

        responSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void requestUsZipCode90210_checkPlaceName_expectedStatus200_usingRequestSpec(){

        given().
                spec(requestSpec).
        when().
                get("us/90210").
        then().
                statusCode(200);
    }

    @Test
    public void request_checkPlaceInResponse_expectedBeverlyHills_usingResponseSpec(){

        given().
                spec(requestSpec).
        when().
                get("us/90210").
        then().
                spec(responSpec).
        and().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }
}
