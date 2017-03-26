package org.my.test.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.my.test.repository.EmployeeRepository;

/**
 * Create report Workbook and Sheets
 * @author Игорь
 */
public class ReportWorkbook {
    public URI makeReport(String fileName, EmployeeRepository emRep) throws FileNotFoundException, IOException{
        // Create workbook
        Workbook wb = new HSSFWorkbook();
        
        // Create Employes report
        new EmployesSheet().addSheet(wb, emRep);
        
        // Create Cities report
        new CitiesSheet().addSheet(wb, emRep);
        
        // Save workbook and return Uri
        FileOutputStream fileOut = new FileOutputStream(fileName);
        wb.write(fileOut);
        fileOut.close();
        return new File(fileName).toPath().toUri();
    }
}
