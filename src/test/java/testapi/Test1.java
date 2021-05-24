package testapi;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Test1 {

    @Test
    public void requestUsZipCode90210_checkPlaceInResponse_expectedBeverllyHills(){

        given().
        when().
                get("http://api.zippopotam.us/us/90210").
        then().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }
}
