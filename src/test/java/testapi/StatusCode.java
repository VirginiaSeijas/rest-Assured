package testapi;

import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class StatusCode {

    @Test
    public void requestUsZipCode90210_checkStatusCode_expectedHttp200(){

        given().
        when().
                get("http://api.zippopotam.us/us/90210").
        then().
        assertThat().
        statusCode(200);
    }
}
