package com.ninza.hrm.api.genericutility;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.List;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonUtils {

	
	FileUtility fLib=new FileUtility();
	/**
	 * get the jsondata from based on json complex xpath
	 * @param res
	 * @param jsonXpath
	 * @return
	 */
	public String getDataOnJsonPath(Response res,String jsonXpath)
	{
		List<Object> lst = JsonPath.read(res.asString(), jsonXpath);
		return lst.get(0).toString();
	}

	/**
	 * get the xmldata from based on xml complex xpath
	 * @param res
	 * @param xmlXpath
	 * @return
	 */
	public String getDataOnXpath(Response res,String xmlXpath)
	{
		return res.xmlPath().get(xmlXpath);
	}

	/**
	 * verify the data in jsonbody based on jsonpath
	 * @param res
	 * @param jsonXpath
	 * @param expectedData
	 * @return
	 */
	public boolean verifyDataOnJsonPath(Response res,String jsonXpath,String expectedData)
	{
		List<String> lst=JsonPath.read(res.asString(),jsonXpath);
		boolean flag=false;
		for(String str:lst)
		{
			if(str.equals(expectedData))
			{
				System.out.println(expectedData+"is available==PASS");
				flag=true;
			}
		}
		if(flag==false)
		{
			System.out.println(expectedData+"is not available==FAIL");
		}
		return false;
	}

	public String getAccessToken() throws IOException
	{
		Response res = given()
				.formParam("client_id", fLib.getDataFromPropertyFile("client_id"))
				.formParam("client_secret", fLib.getDataFromPropertyFile("client_secret"))
				.formParam("grant_type",fLib.getDataFromPropertyFile("grant_type") )
				.when().
				post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
		res.then().log().all();

		String tokenId = res.jsonPath().get("access_token");
		return tokenId;

	}
}
