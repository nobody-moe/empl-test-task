package org.my.test.report;

import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.my.test.repository.EmployeeRepository;

/**
 * Вторая страница отчета
 * @author Игорь
 */
public class CitiesSheet implements IBaseReportSheet {
    
    Workbook    wb;
    Sheet       sheet;
    Row         row;
    ReportUtil  util;
    
    void createCaption(){
        row = sheet.createRow(0);
        Cell cell = row.createCell(3);
        cell.setCellValue("Список навыков по городам");
        cell.setCellStyle(util.getStyle("caption"));
    }
    
     void createHeader(){
        row = sheet.createRow(3);
        row.createCell(1).setCellValue("№");
        row.createCell(2).setCellValue("Город");
        row.createCell(3).setCellValue("Навыки");
        row.createCell(4).setCellValue("Уникальные навыки");
        for(int i = 1; i < 5; i++)
            row.getCell(i).setCellStyle(util.getStyle("header"));
        sheet.setColumnWidth(2, 25*256);
        sheet.setColumnWidth(3, 30*256);
        sheet.setColumnWidth(4, 40*256);
    }
    
    @Override
    public void addSheet(Workbook wb ,  EmployeeRepository emRep){
        this.wb = wb;
        util = new ReportUtil(wb);
        sheet = wb.createSheet("Города");
        
        createCaption();
        createHeader();
        
        List<Object[]> result = emRep.findCitiesAndSkills();
        
        // найти уникальные навыки
        HashMap<String, Integer> skillsCount = new HashMap<>();
        for(Object[] o: result){
            int n = 1;
            if(skillsCount.containsKey(o[1]))
                n = 2;
            skillsCount.put((String) o[1], n);
        }
        
        // Начальная строка
        short counter = 4;
        // №
        int rec_num = 1;

        String city_old = "";
        String city_new;
        String skill;
        boolean new_entry = false;
        for(Object[] m: result){
            row = sheet.createRow(counter++);
            city_new = (String)m[0];
            skill    = (String)m[1];
            if( !city_new.equals(city_old) ){
                new_entry = true;
                row = sheet.createRow(counter++);
                row.createCell(1).setCellValue(rec_num++);
                row.createCell(2).setCellValue(city_new);
                city_old = city_new;
            }

            row.createCell(3).setCellValue(skill);
            if(skillsCount.get(skill) == 1)
                row.createCell(4).setCellValue(skill);
            if(new_entry){
                for(int i = 1; i < 5; i++)
                    if(row.getCell(i) != null)
                        row.getCell(i).setCellStyle(util.getStyle("new_entry"));
                    else {
                       row.createCell(i).setCellStyle(util.getStyle("new_entry")); 
                    }      
                new_entry = false;
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
