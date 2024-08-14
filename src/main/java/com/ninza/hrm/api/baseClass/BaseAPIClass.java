package com.ninza.hrm.api.baseClass;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericutility.DatabaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.pojoclass.ProjectPojo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {


	public JavaUtility jLib=new JavaUtility();
	public FileUtility fLib=new FileUtility();
	public DatabaseUtility dbLib=new DatabaseUtility();
    public static RequestSpecification specReqObj;
    public static ResponseSpecification SpecResObj;
    public ProjectPojo pObj;
	@BeforeSuite
	public void configBS() throws SQLException, IOException
	{
		dbLib.getConnection1();
		System.out.println("======connect to DB=======");
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		//builder.setAuth(basic("username", "password"));
		//builder.addHeader("", "");
		builder.setBaseUri(fLib.getDataFromPropertyFile("BASEUri"));
		specReqObj = builder.build();
	    
	    ResponseSpecBuilder resBuilder=new ResponseSpecBuilder();
	    resBuilder.expectContentType(ContentType.JSON);
	    SpecResObj = resBuilder.build();
	}

	
	
	@AfterSuite
	public void configAS() throws SQLException
	{
		dbLib.closeConnection();
		System.out.println("=======disconnect from DB=======");
	}
}
