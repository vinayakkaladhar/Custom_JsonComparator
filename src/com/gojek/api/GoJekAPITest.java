package com.gojek.api;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Listeners(TestNGListener.class)
public class GoJekAPITest {

    @Test(dataProvider = "requestURL", dataProviderClass = APIDataProvider.class)
    public void testAPIs(String baseReq, String newReq, ITestContext context) {
        try {
            
            context.setAttribute("baseObj", baseReq);
            context.setAttribute("newObj", newReq);
            
            String baseResponse = given().when().get(baseReq).thenReturn().body().asString();
            String newResponse = given().when().get(newReq).thenReturn().body().asString();

            JSONObject baseObj = null;
            JSONObject newObj = null;

            try {
                baseObj = new JSONObject(baseResponse);
                newObj = new JSONObject(newResponse);
            } catch (Exception e){
                baseObj = new JSONObject().put("key", new JSONArray(baseResponse));
                newObj = new JSONObject().put("key", new JSONArray(newResponse));
            }
            Assert.assertTrue(APIComparator.jsonCompareObject(baseObj, newObj));
        }catch (Exception e) {
            Assert.fail();
            
        }
    }
}
