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
            /*String baseResponse = given().when().get(baseReq).thenReturn().body().asString();
            String newResponse = given().when().get(newReq).thenReturn().body().asString();*/

            String baseResponse = "{\n" +
                    "    \"color\":[\n" +
                    "        {\n" +
                    "          \"name\": \"e2\",\n" +
                    "          \"id\": 2\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"name\": \"e3\",\n" +
                    "          \"id\": 2\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"name\": \"e1\",\n" +
                    "          \"id\": 1\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"name\": \"e4\",\n" +
                    "          \"id\": 4\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"fruit\": \"Apple\",\n" +
                    "    \"size\": \"Large\"\n" +
                    "}";
            String newResponse = "{\n" +
                    "    \"color\":[\n" +
                    "        {\n" +
                    "          \"name\": \"e3\",\n" +
                    "          \"id\": 2\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"name\": \"e1\",\n" +
                    "          \"id\": 2\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"name\": \"e4\",\n" +
                    "          \"id\": 4\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"name\": \"e2\",\n" +
                    "          \"id\": 2\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"fruit\": \"Apple\",\n" +
                    "    \"size\": \"Large\"\n" +
                    "}";

            context.setAttribute("baseObj", baseReq);
            context.setAttribute("newObj", newReq);

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
            e.printStackTrace();
        }
    }
}
