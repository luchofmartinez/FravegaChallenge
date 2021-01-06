package steps;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class BE_API_Test {

    @Test
    public void testApi01(){

        baseURI = "https://api.openbrewerydb.org";

        RequestSpecification request = RestAssured.given();

        Response response =  request.param("query","lagunitas").
                when().get("/breweries/autocomplete").
                then().extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<HashMap<String, String>> listBreweries = jsonPath.getList("$.");


        for(int i=0; i < listBreweries.size(); i++){
            HashMap<String, String> brewery = listBreweries.get(i);
            String name = brewery.get("name");
            String id = brewery.get("id");

            if(name.equals("Lagunitas Brewing Co")) {

                baseURI = "https://api.openbrewerydb.org/breweries/" + id;

                RequestSpecification request01 = RestAssured.given();
                Response response01 = request01.request(Method.GET, "/");
                JsonPath jsonPath1 = response01.jsonPath();

                if(jsonPath1.get("state").equals("California")){
                    Assert.assertEquals(761, jsonPath1.getInt("id"));
                    Assert.assertEquals("Lagunitas Brewing Co", jsonPath1.getString("name"));
                    Assert.assertEquals("1280 N McDowell Blvd", jsonPath1.getString("street"));
                    Assert.assertEquals("7077694495", jsonPath1.getString("phone"));
                }
            }
        }
    }
}