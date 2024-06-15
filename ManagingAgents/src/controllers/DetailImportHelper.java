package controllers;

import datatable.ExportDetailTable;
import datatable.ImportDetailTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DetailImportHelper {
	public static int Import_Detail_Id = 0;
	public static int IdImport = 0;
	public static String itemName = null;
	public static String unitName = null;
	public static int IdUnit = 0;
	public static int IdItems = 0;
	public static double Items_Price = 0;
	public static double Total_Money = 0;
	public static int Items_Quantity = 0;
	public static ObservableList<ImportDetailTable>  importDetail = FXCollections.observableArrayList();
	public static ObservableList<ExportDetailTable>  exportDetail = FXCollections.observableArrayList();
	public static boolean isAdd = true;
	
	
	public static boolean isExport = false;
}
