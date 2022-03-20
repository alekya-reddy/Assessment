package com.application.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestUtils {

	public static void pullScreenshot(WebDriver driver, String filePath,String fileName) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;

		// file format
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		// required path and in desired format
		FileUtils.copyFile(source, new File(filePath+"\\"+fileName+".jpeg"));
	}

	public static void readExcelData(String filePath, String fileName, String sheetName) throws IOException {

		File src = new File(filePath + "\\" + fileName);// get the location of your file

		FileInputStream finput = new FileInputStream(src);// get the access to that particular file

		XSSFWorkbook workbook = new XSSFWorkbook(finput);// now you got the access to that workbook

		XSSFSheet sheet = workbook.getSheet(sheetName);// workbook.getSheetAt(0);

		for (int i = 0; i <= sheet.getLastRowNum(); i++) {// rows

			for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {// columns

				XSSFCell cell = sheet.getRow(i).getCell(j);// 0 0 - specific cell access

				System.out.print(cell.getStringCellValue() + " | ");

				System.out.println();

			}

		}

	}

	public static void writeDataToExcel(String filePath, String fileName, String sheetName, String[] dataToWrite)
			throws IOException {

		File src = new File(filePath + "\\" + fileName);

		FileInputStream finput = new FileInputStream(src);

		XSSFWorkbook workbook = new XSSFWorkbook(finput);

		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();// 3-0

		Row row = sheet.getRow(0);

		Row newRow = sheet.createRow(rowCount + 1);

		for (int j = 0; j < row.getLastCellNum(); j++) {

			Cell cell = newRow.createCell(j);

			cell.setCellValue(dataToWrite[j]);

		}

		finput.close();

		FileOutputStream outputStream = new FileOutputStream(src);

		workbook.write(outputStream);

		outputStream.close();

	}

	public static String generateRandomText() {

		String str = "testinguuserdemo";
		StringBuilder sb = new StringBuilder();

		Random random = new Random();

		int length = 2;
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(str.length());
			char randomChar = str.charAt(index);
			sb.append(randomChar);
		}

		String randomString = sb.toString();
		return randomString;
	}

}
