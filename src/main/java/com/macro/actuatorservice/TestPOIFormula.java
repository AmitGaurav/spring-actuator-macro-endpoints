package com.macro.actuatorservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.util.CellReference;

public class TestPOIFormula {

	public static void main(String[] args) {

//		File file = new File("C:/work-related/Cisco-BVA/Maths.xlsm");
//		File file = new File("C:/work-related/Cisco-BVA/Maths.xlsx");
//		File file = new File("C:/work-related/Cisco-BVA/Maths.xlsb");
//		File file = new File("C:/work-related/Cisco-BVA/Easy-Copy.xlsb");
		File file = new File("C:/work-related/Cisco-BVA/Easy-Copy.xlsm");
		
		FileInputStream fis;
		XSSFWorkbook workbook = null;
		XSSFCellStyle lockedStyle = null;
		try {
			fis = new FileInputStream(file);
			workbook = /* new HSSFWorkbook(fis); */ new XSSFWorkbook(fis);
			
			fis.close();
			
			lockedStyle = workbook.getCellStyleAt(0);
			System.out.println(lockedStyle);
//            lockedStyle.setLocked(false); 
			
			XSSFSheet sheet = workbook.getSheetAt(0);

			FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

//			CellReference cellReference = new CellReference("M7");
			CellReference cellReference = new CellReference("D32");  // D32 =>(Blank) 
																	 // N372=>(Null Pointer) 
																	 // O373=>(Null Pointer)
																	 // P372=>(Null Pointer)
																	 // J48=>(Null Pointer)
																	 // K48=>(Null Pointer)
																	 // J31=>(Blank) 
																	 // O32=>(Blank) 
																	 // O59=>(Null Pointer)
			XSSFRow row = sheet.getRow(cellReference.getRow());
			XSSFCell cell = row.getCell(cellReference.getCol());

			System.out.println("Cell Type: "+cell.getCellType());
			if (cell.getCellType() == CellType.FORMULA) {
				System.out.println(cell.getCellFormula());
//				System.out.println(cell.getArrayFormulaRange());
				System.out.println(cell.getCachedFormulaResultType());
				System.out.println("==== ** ==== ** ==== ** ====");
				switch (formulaEvaluator.evaluateFormulaCell(cell)) {
				case BOOLEAN:
					System.out.println(cell.getBooleanCellValue());
					break;
				case NUMERIC:
					System.out.println(cell.getNumericCellValue());
					break;
				case STRING:
					System.out.println(cell.getRichStringCellValue());
					System.out.println(cell.getStringCellValue());
					break;
				default:
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	private static List findFormulasInRow(XSSFSheet sheet, XSSFRow hssfRow, int startCellNum, int endCellNum) {
//		}

}
