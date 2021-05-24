package testapi;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test2 {

    @Test
    public void requestUsZipCode90210_checkStatusCode_expectedHttp200(){

        given().
        when().
                get("http://api.zippopotam.us/us/90210").
        then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void requestUsZipCode90210_logRequestResponseDetails(){

        given().
        when().
                get("http://api.zippopotam.us/us/90210").
        then().
                assertThat().
                contentType(ContentType.JSON);
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectedBeverlyHills(){

        given().
                log().all();
        when().
                get("http://api.zippopotam.us/us/90210").
        then().
                log().body();

    }

    @Test
    public void requestUsZipCode90210_check_expected(){

        given().
                log().all();
        when().
                get("http://api.zippopotam.us/us/90210").
        then().
                log().body();
    }

    @Test
    public void requestUsZipCode90210_checkStateNameInResponse_expectedCalifornia(){

        given().
        when().
                get("http://api.zippopotam.us/us/90210").
        then().
                assertThat().
                body("places[0].'state'", equalTo("California"));
    }

    @Test
    public void requestUsZipCode90210_checkListOfPlaceNames_expectedContainsBeverlyHills(){

        given().
        when().
                get("http://api.zippopotam.us/us/90210").
        then().
                assertThat().
                body("places.'place name'", not(hasItem("Beversly Hills")));
    }

    @Test
    public void requestUsZipCode90210_checkNumberOfPlaceNames_expectedOne(){

        given().
        when().
                get("http://api.zippopotam.us/us/90210").
        then().
                assertThat().
                body("places.'place name'", hasSize(1));
    }
}
