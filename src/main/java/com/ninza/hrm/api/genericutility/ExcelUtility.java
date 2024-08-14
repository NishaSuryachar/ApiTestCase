package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcelFile(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(".\\testData\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return data;
	}

	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis=new FileInputStream(".\\testData\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		return rowCount;
	}
	
	public void setDataIntoExcelFile(String sheetName,int rowNum,int cellNum,String CellData) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(".\\testData\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(CellData);
		
		FileOutputStream fos=new FileOutputStream(".\\testData\\TestData.xlsx");
		wb.write(fos);
		wb.close();
	}

}
