/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.my.test.report;

import java.util.HashMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Утилиты для форматирования отчета
 * @author Игорь
 */
public class ReportUtil {
    Workbook wb;
    HashMap<String, CellStyle> styles = new HashMap<>();
    
    public ReportUtil(Workbook w){
        wb = w;
        
        Font font = wb.createFont();
        CellStyle style = wb.createCellStyle();
        font.setBold(true);
        font.setFontHeightInPoints((short)14);
        style.setFont(font);
        styles.put("caption", style);
        
        font = wb.createFont();
        font.setBold(true);
        style = wb.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor((short)57);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styles.put("header", style);
        
        font = wb.createFont();
        font.setBold(false);
        style = wb.createCellStyle();
        style.setFont(font);
        styles.put("plain", style);
        
        style = wb.createCellStyle();
        style.setFillForegroundColor((short)31);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styles.put("new_entry", style);
    }
    
    public CellStyle getStyle(String name){
        if( styles.containsKey(name) )
            return styles.get(name);
        return wb.createCellStyle();
    }
    
    public void setBold(Cell cell){      
        Font font = wb.createFont();
        font.setBold(true);
        CellStyle style = cell.getCellStyle();
        style.setFont(font);
        cell.setCellStyle(style);
    }
    
    public void setPlain(Cell cell){      
        Font font = wb.createFont();
        font.setBold(false);
        CellStyle style = cell.getCellStyle();
        style.setFont(font);
        cell.setCellStyle(style);
    }
}
