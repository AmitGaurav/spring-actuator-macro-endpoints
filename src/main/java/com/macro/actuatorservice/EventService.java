package com.macro.actuatorservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

@Service
public class EventService {

	private static final File file = new File("C:/work-related/workspaces/Workspace-Java/bva/gs-actuator-service/complete/src/main/resources/Maths.xlsm");
	
	private static Map<String, String> sumOfNumbersResults;
	private static Map<String, String> modeOfNumbersResults;
	
	//TODO: this will go to a config file .csv or something similar >>>>>>>>>>>>>>>>>>>>>>
	static{
		sumOfNumbersResults = new HashMap<String, String>();
		sumOfNumbersResults.put("sumOfNumbers" , "I6");
		
		modeOfNumbersResults = new HashMap<String, String>();
		modeOfNumbersResults.put("modeOfNumbers" , "I11");
//		modeOfNumbersResults.put("modeOfNumbers2" , "I12");
	}
	
	private static final Map<String, Macro> EVENT_NAME_MACRO_NAME_MAP = 
	    Collections.unmodifiableMap(new HashMap<String, Macro>() {
			private static final long serialVersionUID = -4933934966576161727L;

		{ 
			put("sumOfNumbers", new Macro("Sum_Click", 
	        								sumOfNumbersResults, 
	        								"C:/work-related/workspaces/Workspace-Java/bva/gs-actuator-service/complete/src/main/resources/Maths.xlsm"));
	        put("modeOfNumbers", new Macro("Mode_Click", 
	        								modeOfNumbersResults, 
	        								"C:/work-related/workspaces/Workspace-Java/bva/gs-actuator-service/complete/src/main/resources/Maths.xlsm"));
	    }});
	
	// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	public Parameters raiseEvent(Parameters parameters){
		
		if(null == parameters){
			return null;
		}
		
		callExcelMacro(file, EVENT_NAME_MACRO_NAME_MAP.get(parameters.getEventName()).getMacroName());
		
		Parameters resParameters = new Parameters();
		resParameters.setEventName(parameters.getEventName());
		resParameters.getParam().add(fetchResultFromExcel(EVENT_NAME_MACRO_NAME_MAP.get(parameters.getEventName()).getMacroFilePath(), parameters.getEventName()));
		return resParameters;
	}
	
   private static void callExcelMacro(File file, String macroName) {
        ComThread.InitSTA(true);
        final ActiveXComponent excel = new ActiveXComponent("Excel.Application");
        try{
        	excel.setProperty("EnableEvents", new Variant(false));

            Dispatch workbooks = excel.getProperty("Workbooks").toDispatch();
            Dispatch workBook = Dispatch.call(workbooks, "Open", file.getAbsolutePath()).toDispatch();

            // Calls the macro
            Variant V1 = new Variant( file.getName() + macroName);
            Variant result = Dispatch.call(excel, "Run", new Variant(macroName));

            // Saves and closes
            Dispatch.call(workBook, "Save");

            com.jacob.com.Variant f = new com.jacob.com.Variant(true);
            Dispatch.call(workBook, "Close", f);

            System.out.println("....done.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excel.invoke("Quit", new Variant[0]);
            ComThread.Release();
        }
    }
   
   private Event fetchResultFromExcel(final String path, String eventName){
	   Cell cell = null;
	   Workbook workbook = null;
	   try {
		   FileInputStream inputStream = new FileInputStream(new File(path));
		   workbook = new XSSFWorkbook(inputStream);
		   Sheet sheet = workbook.getSheetAt(0);
		   CellAddress cellAddress = new CellAddress(EVENT_NAME_MACRO_NAME_MAP.get(eventName).getResults().get(eventName));
		   Row row = sheet.getRow(cellAddress.getRow());
		   cell = row.getCell(cellAddress.getColumn());
		    
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	   return new Event(eventName, cell.getNumericCellValue());
	   
   }
   
}
