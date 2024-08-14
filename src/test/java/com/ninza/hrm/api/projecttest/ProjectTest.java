package com.ninza.hrm.api.projecttest;
import static io.restassured.RestAssured.*;
import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.genericutility.DatabaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.constants.endpoints.IEndPoint;
import java.io.IOException;
import java.sql.SQLException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class ProjectTest extends BaseAPIClass{

	

	@Test
	public void addSingleProjectWithCreatedTest() throws SQLException, IOException
	{
		String expSuccessfullMsg="Successfully Added";
		String projectName="Idea"+jLib.getRandomNum();
		//String baseUri = fLib.getDataFromPropertyFile("BASEUri");
		//create an object to pojo class
		pObj=new ProjectPojo(projectName,"Created","Nisha",12);

		//Verify the projectName in API layer
		Response res = given()
				.spec(specReqObj)
				.body(pObj)
				.when()
				.post(IEndPoint.ADDProj);
		res.then()
		.assertThat().statusCode(201)
		.assertThat().time(Matchers.lessThan(9000L))
		.spec(SpecResObj)
		.log().all();
		
		String actMsg = res.jsonPath().get("msg");
		Assert.assertEquals(expSuccessfullMsg, actMsg);

		//Verify the projectName in DB layer

		boolean flag = dbLib.executeQueryVerifyAndGetData("select * from project", 4, projectName);

		Assert.assertTrue("project in DB is not verified",flag);


	}

	@Test(dependsOnMethods = "addSingleProjectWithCreatedTest")
	public void addDuplicateProjectWithSameNameTest() throws IOException
	{
		String baseUri = fLib.getDataFromPropertyFile("BASEUri");
		given()
		.spec(specReqObj)
		.body(pObj)
		.when()
		.post(IEndPoint.ADDProj)
		.then()
		.assertThat().statusCode(409)
		.spec(SpecResObj)
		.log().all();
	}

	
}
