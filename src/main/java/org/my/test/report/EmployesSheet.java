package org.my.test.report;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.my.test.domain.Employee;
import org.my.test.domain.Skills;
import org.my.test.repository.EmployeeRepository;

/**
 * Первая страница отчета
 * @author Игорь
 */
public class EmployesSheet implements IBaseReportSheet {
    Workbook    wb;
    Sheet       sheet;
    Row         row;
    ReportUtil  util;
    
    void createCaption(){
        row = sheet.createRow(0);
        Cell cell = row.createCell(3);
        cell.setCellStyle(util.getStyle("caption"));
        cell.setCellValue("Список сотрудников");
    }
    
     void createHeader(){
        row = sheet.createRow(3);

        row.createCell(1).setCellValue("№");
        row.createCell(2).setCellValue("Полное имя");
        row.createCell(3).setCellValue("Сокращенное имя");
        row.createCell(4).setCellValue("Возраст");
        row.createCell(5).setCellValue("Город");
        row.createCell(6).setCellValue("Навыки: название");
        row.createCell(7).setCellValue("Навыки: описание");
        for(int i = 1; i < 8; i++)
            row.getCell(i).setCellStyle(util.getStyle("header"));

        sheet.setColumnWidth(2, 30*256);
        sheet.setColumnWidth(3, 25*256);
        sheet.setColumnWidth(4, 8*256);
        sheet.setColumnWidth(5, 17*256);
        sheet.setColumnWidth(6, 17*256);
        sheet.setColumnWidth(7, 40*256);
    }
    
    @Override
    public void addSheet(Workbook wb, EmployeeRepository emRep)
{       this.wb = wb;
        util = new ReportUtil(wb);
        sheet = wb.createSheet("Сотрудники");
        
        createCaption();
        createHeader();
  
        // Начальная строка
        short counter = 4;
        // №
        int emp_num = 1;
        
        for(Employee e : emRep.findAll()){
            row = sheet.createRow(counter++);
            row.createCell(1).setCellValue(emp_num++);
            row.createCell(2).setCellValue(e.getFull_name());
            row.createCell(3).setCellValue(e.getShort_name());
            row.createCell(4).setCellValue(e.getAge());
            row.createCell(5).setCellValue(e.getCity());
            row.createCell(6);
            row.createCell(7);
            for(int i = 1; i < 8; i++)
                row.getCell(i).setCellStyle(util.getStyle("new_entry"));
            for(Skills s : e.getSkills()){
                row = sheet.createRow(counter++);
                row.createCell(6).setCellValue(s.getName());
                row.createCell(7).setCellValue(s.getDescription());
            }
        }
        
        for (Row row : sheet) {
            if(row.getRowNum() > 3 )
            for (Cell cell : row) {
                cell.setCellStyle(util.getStyle("plain"));
            }
        }
    }
}
