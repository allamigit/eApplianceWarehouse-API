package e_appliance_warehouse.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import e_appliance_warehouse.table.SalesOrder;

public class SalesOrderExcelUtil {
	
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<SalesOrder> saleOrder;
    
	public SalesOrderExcelUtil(List<SalesOrder> saleOrder) {
		this.saleOrder = saleOrder;
		workbook = new XSSFWorkbook();
	}
    
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
    	sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) cell.setCellValue((Integer) value);
        if (value instanceof Double) cell.setCellValue((Double) value);
        if (value instanceof Boolean) cell.setCellValue((Boolean) value);
        if (value instanceof String) cell.setCellValue((String) value);
        if (value instanceof Date) cell.setCellValue((Date) value);
        cell.setCellStyle(style);
    }
    
	private void headerRow() {
    	sheet = workbook.createSheet("Sales Orders List");
    	CellStyle style = workbook.createCellStyle();
    	XSSFFont font = workbook.createFont();
    	font.setBold(true);
    	font.setFontHeight(12);
    	style.setFont(font);

    	Row row = sheet.createRow(0);
    	createCell(row, 0, "Order ID", style);
    	createCell(row, 1, "Order Date", style);
    	createCell(row, 2, "Customer Name", style);
    	createCell(row, 3, "Total Amount", style);
    	createCell(row, 4, "Created By", style);
    	createCell(row, 5, "Billing Status", style);
    	
    }
	
	private void dataRows() {
		int rowCount = 1;
		 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);
                
        for (SalesOrder orders : saleOrder) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, orders.getOrderId(), style);
            createCell(row, columnCount++, orders.getOrderDate().toString(), style);
            //createCell(row, columnCount++, orders.getCustomerName(), style);
            createCell(row, columnCount++, orders.getTotalAmount(), style);
            //createCell(row, columnCount++, orders.getUser().getFirstName() + " " + orders.getUser().getLastName(), style);
            createCell(row, columnCount++, orders.getBillingStatus()?"Billed":"Not Billed", style);             
        }
	}
	
	public void generateExcel(HttpServletResponse response) throws IOException {
		headerRow();
		dataRows();
		
		ServletOutputStream outputData = response.getOutputStream();
        workbook.write(outputData);
        workbook.close();
         
        outputData.close();
	}

}
