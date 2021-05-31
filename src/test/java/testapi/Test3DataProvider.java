package testapi;

import com.tngtech.java.junit.dataprovider.*;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(DataProviderRunner.class)
public class Test3DataProvider {

    @DataProvider
    public static Object[][] zipCodeAndPlaces(){
        return new Object[][] {
                {"us" , "90210", "Beverly Hills"},
                {"hu" , "1011", "Budapest"},
                {"au" , "0200", "Australian National University"}
        };
    }

    @Test
    @UseDataProvider("zipCodeAndPlaces")
    public void requestApi_checkInResponse_expectedPlaceName(String countryCode, String zipCode, String placeName){

        given().
                pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
        when().
                get("http://api.zippopotam.us/{countryCode}/{zipCode}").
        then().
                assertThat().
                statusCode(200).
                body("places[0].'place name'", equalTo(placeName));
    }
}