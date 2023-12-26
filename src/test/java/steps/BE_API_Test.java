package steps;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

public class BE_API_Test {

    @Test
    public void testApi01(){
        baseURI = "https://api.openbrewerydb.org/breweries";

        RequestSpecification request = RestAssured.given();

        Response response = request.queryParam("query","lagunitas").request(Method.GET, "/autocomplete");

        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.get("id").toString());

    }

    @Test
    public void testApi02(){
        baseURI = "https://api.openbrewerydb.org/breweries/5ae467af-66dc-4d7f-8839-44228f89b596";

        RequestSpecification request = RestAssured.given();

        Response response = request.queryParams("state", "California").request(Method.GET, "/");

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals("5ae467af-66dc-4d7f-8839-44228f89b596", jsonPath.getString("id"));
        Assert.assertEquals("101 North Brewing Company", jsonPath.getString("name"));
        Assert.assertEquals("1304 Scott St Ste D", jsonPath.getString("address_1"));
        Assert.assertEquals("7077534934", jsonPath.getString("phone"));
    }
}

/*
"id" = 761
"name" = "Lagunitas Brewing Co"
"street" = "1280 N McDowell Blvd"
"phone" = "7077694495"
*/
