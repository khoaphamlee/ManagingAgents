package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Data_Access_Object.DAO_Sales_Report;
import Data_Access_Object.DAO_Sales_Report_Detail;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.BooleanFilter;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.FloatFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class SaleReportDialogController implements Initializable {

	
	@FXML
	private MFXTextField dateTV;

	@FXML
	private MFXButton cancelBtn,excelBtn;

	private AlertMessage alertMessage = new AlertMessage();
	 
	@FXML
	private MFXTableView<Sales_Report_Detail>  reportTable;
	
	
	
	
	private ObservableList<Sales_Report_Detail> ctbcts = FXCollections.observableArrayList();
	
	private Stage window;
	MathContext mc = new MathContext(5, RoundingMode.HALF_UP);
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupScreen();
		setupButton();
		setupTable();
	}

	private void setupTable() {
		
	
		ctbcts =  DAO_Sales_Report_Detail.getInstance().selectByCondition3(" IdSalesReport = " + CurrentSaleReport.id);
		
		double sum = 0;
		for(int i =0;i<ctbcts.size();i++) {
			sum += ctbcts.get(i).getTotalMoney();
		}
		
		for(int i =0;i<ctbcts.size();i++) {
			ctbcts.get(i).setSalesReportDetail_Id(i+1);
			
			
			BigDecimal bd = new BigDecimal(Double.toString(ctbcts.get(i).getTotalMoney()/ sum));           	
        	bd = bd.setScale(3, RoundingMode.HALF_UP);
        	float newRate = Float.parseFloat(bd.toString());
        	
			ctbcts.get(i).setRate( newRate);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		MFXTableColumn<Sales_Report_Detail> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(Sales_Report_Detail::getSalesReportDetail_Id));
		MFXTableColumn<Sales_Report_Detail> agentColumn = new MFXTableColumn<>("Agent", true, Comparator.comparing(Sales_Report_Detail::getAgentName));
		MFXTableColumn<Sales_Report_Detail> agnetIdColumn = new MFXTableColumn<>("Agent Id", true, Comparator.comparing(Sales_Report_Detail::getIdAgent));
		MFXTableColumn<Sales_Report_Detail> numberColumn = new MFXTableColumn<>("Amount export", true, Comparator.comparing(Sales_Report_Detail::getAmountExport));
		MFXTableColumn<Sales_Report_Detail> totalColumn = new MFXTableColumn<>("Total money", true, Comparator.comparing(Sales_Report_Detail::getTotalMoney));
		MFXTableColumn<Sales_Report_Detail> rateColumn = new MFXTableColumn<>("Rate", true, Comparator.comparing(Sales_Report_Detail::getRate));
		
		
		

		// Set row cell factories
		idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Sales_Report_Detail::getSalesReportDetail_Id));
		agentColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Sales_Report_Detail::getAgentName));
		agnetIdColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Sales_Report_Detail::getIdAgent));
		numberColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Sales_Report_Detail::getAmountExport));
		totalColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Sales_Report_Detail::getTotalMoney));
		rateColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Sales_Report_Detail::getRate));
		
		
		// Add columns to the table
		reportTable.getTableColumns().addAll(idColumn,agentColumn,agnetIdColumn,numberColumn,totalColumn,rateColumn);
		reportTable.getFilters().addAll(
		        new IntegerFilter<>("ID", Sales_Report_Detail::getSalesReportDetail_Id),
		        new StringFilter<>("Agent", Sales_Report_Detail::getAgentName),
		        new IntegerFilter<>("Agent Id", Sales_Report_Detail::getIdAgent),
		        new IntegerFilter<>("Amount export", Sales_Report_Detail::getAmountExport),
		        new DoubleFilter<>("Total money", Sales_Report_Detail::getTotalMoney),
		        new FloatFilter<>("Rate", Sales_Report_Detail::getRate)
		);
		// Add filters to the table
		
		reportTable.setItems(ctbcts);
		reportTable.autosize();
		reportTable.autosizeColumns();
		reportTable.autosizeColumnsOnInitialization();
		
		dateTV.setText(CurrentSaleReport.date.toString());
		dateTV.setEditable(false);
	}

	private void setupButton() {
		// TODO Auto-generated method stub
		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
		});
		
		excelBtn.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
			File file = fileChooser.showSaveDialog(excelBtn.getScene().getWindow());
			if (file != null) {
				try (Workbook workbook = new XSSFWorkbook()) {
					Sheet sheet = workbook.createSheet("Monthly Revenue Report");

					// Add an empty row at the top
					sheet.createRow(0);

					// Create the title row "Revenue Report"
					Row titleRow = sheet.createRow(1);
					Cell titleCell = titleRow.createCell(1);
					
					titleCell.setCellValue("Revenue Report - " + (CurrentSaleReport.date.getMonth()+1) + "/" + (CurrentSaleReport.date.getYear()+1900));
							

					// Merge cells for the title row
					CellRangeAddress titleRegion = new CellRangeAddress(1, 1, 1, 6);
					sheet.addMergedRegion(titleRegion);

					CellStyle titleStyle = workbook.createCellStyle();
					Font titleFont = workbook.createFont();
					titleFont.setBold(true);
					titleFont.setFontHeightInPoints((short) 16);
					titleStyle.setFont(titleFont);
					titleStyle.setAlignment(HorizontalAlignment.CENTER);
					titleStyle.setBorderBottom(BorderStyle.THIN);
					titleStyle.setBorderTop(BorderStyle.THIN);
					titleStyle.setBorderLeft(BorderStyle.THIN);
					titleStyle.setBorderRight(BorderStyle.THIN);

					titleCell.setCellStyle(titleStyle); // Apply the style to the cell
					setBordersToMergedCells(sheet, titleRegion, titleStyle);// Apply borders to merged cells

					// Create the date row
					Row dateRow = sheet.createRow(2);
					Cell dateCell = dateRow.createCell(1);
					
					dateCell.setCellValue("Report Date: " + CurrentSaleReport.date.getDate() + "/"
							+ (CurrentSaleReport.date.getMonth()+1) + "/" + (CurrentSaleReport.date.getYear() + 1900));

					// Merge cells for the date row
					CellRangeAddress dateRegion = new CellRangeAddress(2, 2, 1, 4);
					sheet.addMergedRegion(dateRegion);

					CellStyle dateStyle = workbook.createCellStyle();
					Font dateFont = workbook.createFont();
					dateFont.setBold(true);
					dateFont.setFontHeightInPoints((short) 14);
					dateStyle.setFont(dateFont);
					dateStyle.setAlignment(HorizontalAlignment.CENTER);
					dateStyle.setBorderBottom(BorderStyle.THIN);
					dateStyle.setBorderTop(BorderStyle.THIN);
					dateStyle.setBorderLeft(BorderStyle.THIN);
					dateStyle.setBorderRight(BorderStyle.THIN);

					dateCell.setCellStyle(dateStyle); // Apply the style to the cell
					setBordersToMergedCells(sheet, dateRegion, dateStyle); // Apply borders to merged cells

					// Create the header row
					Row headerRow = sheet.createRow(3);
					CellStyle headerStyle = workbook.createCellStyle();
					Font headerFont = workbook.createFont();
					headerFont.setFontHeightInPoints((short) 14);
					headerFont.setBold(true);
					headerStyle.setFont(headerFont);
					headerStyle.setBorderBottom(BorderStyle.THIN);
					headerStyle.setBorderTop(BorderStyle.THIN);
					headerStyle.setBorderLeft(BorderStyle.THIN);
					headerStyle.setBorderRight(BorderStyle.THIN);

					String[] headers = { "Id","Agent","Agent Id","Amount export","Total money","Rate"};
					for (int i = 0; i < headers.length; i++) {
						Cell cell = headerRow.createCell(i + 1);
						cell.setCellValue(headers[i]);
						cell.setCellStyle(headerStyle);
					}

					// Add data to the table and apply border to all cells
					CellStyle cellStyle = workbook.createCellStyle();
					Font cellFont = workbook.createFont();
					cellFont.setFontHeightInPoints((short) 14);
					cellStyle.setFont(cellFont);
					cellStyle.setBorderBottom(BorderStyle.THIN);
					cellStyle.setBorderTop(BorderStyle.THIN);
					cellStyle.setBorderLeft(BorderStyle.THIN);
					cellStyle.setBorderRight(BorderStyle.THIN);

					int rowIndex = 6; // Start from the fourth row
					for (Sales_Report_Detail report : ctbcts) {
						Row row = sheet.createRow(rowIndex++);
						Cell cell0 = row.createCell(1);
						cell0.setCellValue(report.getSalesReportDetail_Id());
						cell0.setCellStyle(cellStyle);

						Cell cell1 = row.createCell(2);
						cell1.setCellValue(report.getAgentName());
						cell1.setCellStyle(cellStyle);

						Cell cell2 = row.createCell(3);
						cell2.setCellValue(report.getIdAgent());
						cell2.setCellStyle(cellStyle);

						Cell cell3 = row.createCell(4);
						cell3.setCellValue(report.getAmountExport());
						cell3.setCellStyle(cellStyle);
						Cell cell4 = row.createCell(5);
						cell4.setCellValue(report.getTotalMoney());
						cell4.setCellStyle(cellStyle);
						
						Cell cell5 = row.createCell(6);
						cell5.setCellValue(report.getRate());
						cell5.setCellStyle(cellStyle);
					}

					for (int i = 1; i <= headers.length; i++) {
						sheet.autoSizeColumn(i);
					}

					try (FileOutputStream fos = new FileOutputStream(file)) {
						workbook.write(fos);
						alertMessage.successMessage(
								"Export Successful\n" + "Data exported successfully to " + file.getAbsolutePath());
					}

					Desktop.getDesktop().open(file);

				} catch (Exception e) {
					alertMessage.errorMessage(
							"Export Error\n" + "An error occurred while exporting data: " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
		
	}
	private static void setBordersToMergedCells(Sheet sheet, CellRangeAddress region, CellStyle style) {
	    RegionUtil.setBorderTop(style.getBorderTop(), region, sheet);
	    RegionUtil.setBorderRight(style.getBorderRight(), region, sheet);
	    RegionUtil.setBorderBottom(style.getBorderBottom(), region, sheet);
	    RegionUtil.setBorderLeft(style.getBorderLeft(), region, sheet);
	}
	
	public void setupScreen() {
		
	}

	// Load data from the database
	
}
