package Project;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.sql.SQLException;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.constants.endpoints.IEndPoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
@Test
public class Project extends BaseAPIClass{

	@Test
	public void addSingleProjectWithCreatedTest() throws SQLException, IOException
	{
		String expSuccessfullMsg="Successfully Added";
		String projectName="Idea"+jLib.getRandomNum();
		//String baseUri = fLib.getDataFromPropertyFile("BASEUri");
		//create an object to pojo class
		pObj=new ProjectPojo(projectName,"Created","Nisha",0);

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
		Object actProjectName = res.jsonPath().get("projectName");
		Assert.assertEquals(expSuccessfullMsg, actMsg);
		Assert.assertEquals(projectName, actProjectName);

		//Verify the projectName in DB layer

		boolean flag = dbLib.executeQueryVerifyAndGetData("select * from project", 4, projectName);

		Assert.assertTrue("project in DB is not verified",flag);


	}

	@Test
	public void addProjectWitONGoingStatus() throws SQLException, IOException
	{
		String expSuccessfullMsg="Successfully Added";
		String projectName="Idea"+jLib.getRandomNum();
		//String baseUri = fLib.getDataFromPropertyFile("BASEUri");
		//create an object to pojo class
		pObj=new ProjectPojo(projectName,"ongoing","Nisha",0);

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
		Object actProjectName = res.jsonPath().get("projectName");
		Assert.assertEquals(expSuccessfullMsg, actMsg);
		Assert.assertEquals(projectName, actProjectName);

		//Verify the projectName in DB layer

		boolean flag = dbLib.executeQueryVerifyAndGetData("select * from project", 4, projectName);

		Assert.assertTrue("project in DB is not verified",flag);
	}

	@Test
	public void addProjectWithCompletedStatus() throws SQLException
	{
		String expSuccessfullMsg="Successfully Added";
		String projectName="Idea"+jLib.getRandomNum();
		//String baseUri = fLib.getDataFromPropertyFile("BASEUri");
		//create an object to pojo class
		pObj=new ProjectPojo(projectName,"completed","Nisha",0);

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
		Object actProjectName = res.jsonPath().get("projectName");
		Assert.assertEquals(expSuccessfullMsg, actMsg);
		Assert.assertEquals(projectName, actProjectName);

		//Verify the projectName in DB layer

		boolean flag = dbLib.executeQueryVerifyAndGetData("select * from project", 4, projectName);

		Assert.assertTrue("project in DB is not verified",flag);

	}

	@Test
	public void addDuplicateProject() throws SQLException
	{
		String projectName="Idea"+jLib.getRandomNum();
		//String baseUri = fLib.getDataFromPropertyFile("BASEUri");
		//create an object to pojo class
		pObj=new ProjectPojo(projectName,"ongoing","Nisha",0);

		//Verify the projectName in API layer
		Response res = given()
				.spec(specReqObj)
				.body(pObj)
				.when()
				.post(IEndPoint.ADDProj);
		res.then()
		.assertThat()
		.time(Matchers.lessThan(9000L))
		.spec(SpecResObj)
		.log().all();
		
		String projName=res.jsonPath().get("projectName");
		pObj=new ProjectPojo(projName,"ongoing","Nisha",0);

		//Verify the projectName in API layer
		Response res1 = given()
				.spec(specReqObj)
				.body(pObj)
				.when()
				.post(IEndPoint.ADDProj);
		res1.then()
		.assertThat()
		.time(Matchers.lessThan(9000L))
		.spec(SpecResObj)
		.statusCode(409)
		.log().all();
		//Verify the projectName in DB layer
		String expSuccessfullMsg1="The Project Name :"+projName+" Already Exists";
		String actMessage = res1.jsonPath().get("message");
		Assert.assertEquals(expSuccessfullMsg1, actMessage);
		
		boolean flag = dbLib.executeQueryVerifyAndGetData("select * from project", 4, projectName);

		Assert.assertTrue("project in DB is not verified",flag);

	}

	@Test
	public void addProjectWithOutName() throws SQLException
	{
		String expSuccessfullMsg="Successfully Added";
		String projectName="Idea"+jLib.getRandomNum();
		//String baseUri = fLib.getDataFromPropertyFile("BASEUri");
		//create an object to pojo class
		pObj=new ProjectPojo("completed","Nisha",0);

		//Verify the projectName in API layer
		Response res = given()
				.spec(specReqObj)
				.body(pObj)
				.when()
				.post(IEndPoint.ADDProj);
		res.then()
		.assertThat().statusCode(502)
		.assertThat().time(Matchers.lessThan(9000L))
		.spec(SpecResObj)
		.log().all();

	}

	@Test
	public void addProjectWithNull() throws SQLException
	{
		String expSuccessfullMsg="Successfully Added";
		String projectName=null;
		//String baseUri = fLib.getDataFromPropertyFile("BASEUri");
		//create an object to pojo class
		pObj=new ProjectPojo(projectName,"completed","Nisha",0);

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

		//Verify the projectName in DB layer

		boolean flag = dbLib.executeQueryVerifyAndGetData("select * from project", 4, projectName);

		Assert.assertTrue("project in DB is not verified",flag);

	}
}

