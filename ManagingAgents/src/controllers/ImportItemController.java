package controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Data_Access_Object.DAO_Items;
import Data_Access_Object.DAO_Unit;
import Database.JDBCUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Items;

import models.Unit;






public class ImportItemController implements Initializable {
	@FXML
	private MFXButton cancelBtn;

	@FXML
	private MFXTextField medicine_dosage;

	@FXML
	private MFXTextField medicine_duration;

	

	@FXML
	private MFXTextField medicine_quantity;

	@FXML
	private MFXButton okBtn;

	@FXML
	private AnchorPane scenePane;
	
	
	
	@FXML
	private MFXTextField quantityTv, priceTv;
	@FXML
	private MFXComboBox<Items> nameTv;
	@FXML
	private Label unitLabel,mainLabel;

	private AlertMessage alertMessage = new AlertMessage();
	private Stage window;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	float Rate = 0;
	public void setupButton() {
		cancelBtn.setOnMouseClicked(event -> {
			CurrentExport.isCancel = true;
			window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
		});

		okBtn.setOnMouseClicked(event -> {
			
				if (quantityTv.getText().isEmpty() || nameTv.getText().isEmpty()
						|| priceTv.getText().isEmpty() ) {
					alertMessage.errorMessage("Please fill all blank fields!");
					return;
				} else {
					
					if(DetailImportHelper.isExport == true) {
					Items it = DAO_Items.getInstance().selectByCondition2(" Items_Id  = " +nameTv.getValue().getItems_Id() );
					System.out.print("00000" + nameTv.getValue().getItems_Unit() );
					if(it.getItems_Quantity() < Integer.parseInt(quantityTv.getText())) {
						alertMessage.errorMessage("Exceeds available stock quantity");
						return;
					}
					}
					CurrentExport.isCancel = false;
					DetailImportHelper.IdItems = nameTv.getValue().getItems_Id();
					DetailImportHelper.Items_Quantity = Integer.parseInt(quantityTv.getText());
					DetailImportHelper.IdUnit = nameTv.getValue().getItems_Unit();
					DetailImportHelper.Items_Price = Double.parseDouble(priceTv.getText());
					DetailImportHelper.itemName = nameTv.getValue().getItems_Name();
					DetailImportHelper.unitName = unitLabel.getText();
					
					
					
				}
			
			
			window = (Stage) okBtn.getScene().getWindow();
			window.close();
		});
	}

	public void setupScreen() throws SQLException {
		connection = JDBCUtil.getConnection();
		String query = "SELECT Items_Id, Items_Name, IdUnit,Items_Price   FROM ITEMS WHERE Items_Status = "+ 1;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			List<Items> dvts = new ArrayList<Items>();
			while (resultSet.next()) {
				int Items_Id = resultSet.getInt("Items_Id");
				String Items_Name = resultSet.getString("Items_Name");
				int IdUnit = resultSet.getInt("IdUnit");
				double Items_Price = resultSet.getDouble("Items_Price");
				dvts.add(new Items(Items_Id,Items_Name,IdUnit,Items_Price,0));
				
			}
			
			if (DetailImportHelper.isExport == true && DetailImportHelper.isAdd == true) {
			    List<Items> itemsToRemove = new ArrayList<>();
			    for (int i = 0; i < DetailImportHelper.exportDetail.size(); i++) {
			        for (Items it : dvts) {
			            if (it.getItems_Id() == DetailImportHelper.exportDetail.get(i).getIdItem()) {
			                itemsToRemove.add(it);
			            }
			        }
			    }
			    dvts.removeAll(itemsToRemove);
			}
			
			else if (DetailImportHelper.isAdd == true) {
				List<Items> itemsToRemove = new ArrayList<>();
			    for (int i = 0; i < DetailImportHelper.importDetail.size(); i++) {
			        for (Items it : dvts) {
			            if (it.getItems_Id() == DetailImportHelper.importDetail.get(i).getIdItem()) {
			                itemsToRemove.add(it);
			            }
			        }
			    }
			    dvts.removeAll(itemsToRemove);
			}
			
			
			
			
			nameTv.setItems(FXCollections.observableArrayList(dvts));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		connection.close();
		
		
		
		
		
		try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        String sql = "SELECT * FROM FIXED_VALUES";
	        System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);
	        if (rs.next()) {
	            
	        	
	        	Rate = rs.getFloat("Rate");
	        	
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		
		
		
		nameTv.setOnAction(event -> {
            Items selectedValue = nameTv.getSelectionModel().getSelectedItem();
            Unit unit = DAO_Unit.getInstance().selectByCondition2(" Unit_Id = " + selectedValue.getItems_Unit() );
            
            
            
            unitLabel.setText(unit.getUnit_Name());
            if(DetailImportHelper.isExport != true)
            priceTv.setText(selectedValue.getItems_Price() + "");
            else {
            	
            	BigDecimal bd = new BigDecimal(Double.toString(selectedValue.getItems_Price() * Rate));           	
            	bd = bd.setScale(3, RoundingMode.HALF_UP);
            	priceTv.setText(bd.toString() + "");
            }
            
            
        });
		
		
		if(DetailImportHelper.isAdd == false) {
			System.out.print("sdfsdfsdfdsfsd-" +DetailImportHelper.IdItems);
			for(int i= 0;i<nameTv.getItems().size();i++) {
				System.out.print("sdfsdfsdfdsfsd-" +nameTv.getItems().get(i).getItems_Id());
				if(nameTv.getItems().get(i).getItems_Id() == DetailImportHelper.IdItems) {
					nameTv.setValue(nameTv.getItems().get(i));
					nameTv.setText(nameTv.getValue().getItems_Name());
				}
				
			}
			Unit unit = DAO_Unit.getInstance().selectByCondition2(" Unit_Id = " + DetailImportHelper.IdUnit );
            unitLabel.setText(unit.getUnit_Name());
            quantityTv.setText(DetailImportHelper.Items_Quantity + "");
            priceTv.setText(nameTv.getValue().getItems_Price() + "");
			
		}
		
		
		if(DetailImportHelper.isExport == true) {
			mainLabel.setText("Export item");
			priceTv.setDisable(true);
			priceTv.setText(DetailImportHelper.Items_Price  + "");
		}
		System.out.print(DetailImportHelper.isExport);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CurrentExport.isCancel = true;
		// TODO Auto-generated method stub
		try {
			setupScreen();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setupButton();
	}

}
