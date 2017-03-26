package org.my.test.report;

import org.apache.poi.ss.usermodel.Workbook;
import org.my.test.repository.EmployeeRepository;

/**
 *
 * @author Игорь
 */
public interface IBaseReportSheet {

    public void addSheet(Workbook wb, EmployeeRepository emRep);
}
