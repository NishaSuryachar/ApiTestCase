package com.ninza.hrm.api.employeetest;
import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.pojoclass.EmployeePojo;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.constants.endpoints.IEndPoint;
import static io.restassured.RestAssured.*;
import java.io.IOException;
import java.sql.SQLException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;


public class EmployeeTest extends BaseAPIClass{

	 
	
	@Test
	public void addEmployeTest() throws SQLException, IOException
	{
		
		String projectName="Airtel_"+jLib.getRandomNum();
        String userName="user10_"+jLib.getRandomNum();
        String baseUri = fLib.getDataFromPropertyFile("BASEUri");
		//Api-1===>add a project inside server
		ProjectPojo pObj=new ProjectPojo(projectName,"Created","Nisha",12);

		given()
		.spec(specReqObj)
		.body(pObj)
		.when()
		.post(IEndPoint.ADDProj)
		.then()
		.spec(SpecResObj)
		.log().all();


		//API-2 add employee to same project
		EmployeePojo empObj=new EmployeePojo("TestEngineer","09/04/1994","nishams228@gmail.com",
				userName,4,"9123456789",projectName,"ROLE_EMPLOYEE",userName);
		given()
		.spec(specReqObj)
		.body(empObj)
		.when()
		.post(IEndPoint.ADDEmp)
		.then()
		.assertThat().statusCode(201)
		.and()
		.time(Matchers.lessThan(9000L))
		.spec(SpecResObj)
		.log().all();
		
		//verify Employee Name in DB
		dbLib.getConnection(baseUri, userName, baseUri);
		boolean flag = dbLib.executeQueryVerifyAndGetData("select * from employee", 5,userName);
		Assert.assertTrue("project in DB is not verified",flag);
		Assert.assertTrue("Employee in DB is not verified",flag);
		
	}
	@Test
	public void addEmployeWithoutEmailTest() throws SQLException, IOException
	{
		 String baseUri = fLib.getDataFromPropertyFile("BASEUri");
		String projectName="Airtel_"+jLib.getRandomNum();
        String userName="user10_"+jLib.getRandomNum();
		//Api-1===>add a project inside server
		ProjectPojo pObj=new ProjectPojo(projectName,"Created","Nisha",12);

		given()
		.spec(specReqObj)
		.body(pObj)
		.when()
		.post(IEndPoint.ADDProj)
		.then()
		.spec(SpecResObj)
		.log().all();


		//API-2 add employee to same project
		EmployeePojo empObj=new EmployeePojo("TestEngineer","09/04/1998","",
				userName,4,"9123456789",projectName,"ROLE_EMPLOYEE",userName);
		
		given()
		.spec(specReqObj)
		.body(empObj)
		.when()
		.post(IEndPoint.ADDEmp)
		.then()
		.spec(SpecResObj)
		.assertThat().statusCode(500)
		.spec(SpecResObj)
		.log().all();
		
	}

}
