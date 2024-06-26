package controllers;


import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Stream;

import Data_Access_Object.DAO_Agent;

import Data_Access_Object.DAO_Export;

import Data_Access_Object.DAO_Role;
import Data_Access_Object.DAO_Sales_Report;
import Data_Access_Object.DAO_Sales_Report_Detail;
import Database.JDBCUtil;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;

import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.i18n.I18N;
import io.github.palexdev.materialfx.utils.FXCollectors;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Agent;

import models.Export;

import models.Role;
import models.Sales_Report;
import models.Sales_Report_Detail;

public class MainScreenController implements Initializable  {
	Stage window;
	private boolean canChange ;
	private int timeChange;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	@FXML
	private AnchorPane darkPane,detailMenuPane,menuPane;
	
	@FXML 
	private ImageView menuImg ; 
	
	@FXML 
	private MFXButton addBtn,editBtn;
	
	@FXML
	private MFXTableView<Agent> table;
	Dialog<ButtonType> dialog = new Dialog<>();
	
	@FXML
	private MFXButton homepageBtn,agencyBtn ,goodsBtn ,exportBtn ,reportBtn ,settingBtn;
	
	@FXML
	private Label nameLbl, posLbl;
	
    @FXML
    private MFXButton logoutBtn;
    
    @FXML
    private MFXButton settingAccBtn;
	
	private String username;
	
	static int Id_User;
	
	@FXML
	private Label totalAgent,totalGoods;
	
	@FXML
	private Label user_name = new Label();

	@FXML
	private Label user_position = new Label();
	
	MathContext mc = new MathContext(5, RoundingMode.HALF_UP);
	CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
	
	@FXML
	BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

	
	@FXML
	private Label revenue;
	@FXML
	private Label totalReceipt;
	
@SuppressWarnings("unchecked")
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	 
	Role role = DAO_Role.getInstance().selectByCondition2(" User_Id = " + CurrentUser.userId);
	
	
	
	if(!role.isRole1()) agencyBtn.setDisable(true);
	
	if(!role.isRole2()) goodsBtn.setDisable(true);
	
	if(!role.isRole3()) exportBtn.setDisable(true);
	
	if(!role.isRole4()) reportBtn.setDisable(true);
	
	//if(!role.isRole5()) settingBtn.setDisable(true);
	
	setupScreen();
	setupTable();
	setupTabChange ();
	setupReport();
    setupChart();
    
    revenue();
	//setupUsernameLabel("Default Username");
}

private void revenue() {
	LocalDate tempt = LocalDate.now();
	
int year = tempt.getYear();
    
    
    int month = tempt.getMonthValue();
    double sum = 0;
    int receipt = 0;
    
	ArrayList<Export> srs = DAO_Export.getInstance().selectAll();
    for(int j = 0;j< srs.size();j++) {
    	
    	if(srs.get(j).getExport_Date().getMonth() + 1 == month && srs.get(j).getExport_Date().getYear() + 1900 == year) {
    		
    		sum += srs.get(j).getExport_TotalMoney();
    		
    		receipt ++;
    		
    		
    		
    		
    	}
    }
    revenue.setText(sum + "");
	totalReceipt.setText(receipt + "");
	return;
}

private void setupChart() {
	XYChart.Series<String, Number> doanhThuSeries = new XYChart.Series<>();
    doanhThuSeries.setName("Sales");

    

    
    
    

   
   
    
    
    LocalDate tempt = LocalDate.now();
    int year = tempt.getYear();
    
    
    int month = tempt.getMonthValue();
    
    
    for(int i = 1;i< month ;i++) {
    	
    	
    	
    	ArrayList<Sales_Report> srs = DAO_Sales_Report.getInstance().selectAll();
        for(int j = 0;j< srs.size();j++) {
        	
        	if(srs.get(j).getSalesReport_Date().getMonth() + 1 == i && srs.get(j).getSalesReport_Date().getYear() + 1900 == year) {
        		
        		ArrayList<Sales_Report_Detail>  bc = DAO_Sales_Report_Detail.getInstance().selectByCondition( " IdSalesReport =  " + srs.get(j).getSalesReport_Id());
        		
        		double sum = 0;
        		
        		for (Sales_Report_Detail sales_Report_Detail : bc) {
					sum += sales_Report_Detail.getTotalMoney();
				}
        		doanhThuSeries.getData().add(new XYChart.Data<>(  ""+i, sum));
        		
        		break;
        		
        	}
        }
    	
    }
    for(int i = month ;i<= 12;i++) {
    	doanhThuSeries.getData().add(new XYChart.Data<>(  ""+i, 0));
    }
    
   
    
    

    
    barChart.getData().addAll(doanhThuSeries);
	
}

private void setupReport() {
	ArrayList<Export> hdpks =  DAO_Export.getInstance().selectAll();
	for(int i =0;i<hdpks.size();i++) {
		 Date currentDate = hdpks.get(i).getExport_Date();
		 
			
	        
	    LocalDate a = LocalDate.of(currentDate.getYear()+ 1900,currentDate.getMonth()+1,currentDate.getDate());
	    
	    LocalDate b = LocalDate.of(currentDate.getYear()+ 1900,currentDate.getMonth()+1,a.lengthOfMonth());
	    
		Date date = Date.valueOf(b);
		
		Sales_Report tempt= DAO_Sales_Report.getInstance().selectByCondition2(" SalesReport_Date = '" +date +"'");
		if(tempt.getSalesReport_Id()== -1 && a.getMonth()!= LocalDate.now().getMonth()) {
			
			Sales_Report sr = new Sales_Report(0,date);
			DAO_Sales_Report.getInstance().Add(sr);
			Sales_Report sr2 = DAO_Sales_Report.getInstance().selectAll().getLast();
			ArrayList<Agent> agents = DAO_Agent.getInstance().selectAll();
			
			
			for(int k = 0;k< agents.size();k++) {
				Sales_Report_Detail srd = new Sales_Report_Detail(0,sr2.getSalesReport_Id(),agents.get(k).getAgent_Id(),0,0,0);
				DAO_Sales_Report_Detail.getInstance().Add(srd);
				
			}
			
			
			
			
			
			for(int j =0;j<hdpks.size();j++) {
				 
				if(hdpks.get(j).getExport_Date().getMonth() == date.getMonth() && hdpks.get(j).getExport_Date().getYear() == date.getYear()) {
					Sales_Report_Detail srd2 = DAO_Sales_Report_Detail.getInstance().selectByCondition2("IdSalesReport = " + sr2.getSalesReport_Id() + " and IdAgent = " + hdpks.get(j).getId_Agent());
					srd2.setAmountExport(srd2.getAmountExport()+ 1);
					srd2.setTotalMoney( srd2.getTotalMoney() + hdpks.get(j).getExport_TotalMoney());
					DAO_Sales_Report_Detail.getInstance().Update(srd2);
				}
				
				
			}
			
			
		}
		
	}
	
}

	private void setupTabChange() {
		System.out.print(CurrentUser.userId);
		
		homepageBtn.setOnAction(event -> {
			window = (Stage)menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainScreen.fxml"));
            Parent root = null;
			try {
				root = loader.load();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Scene scene = new Scene(root);
            window.setTitle("Managing Agents");
            window.getIcons().add(new Image("/images/mylogo.png"));
            window.setScene(scene);
            
        });
		
		
		goodsBtn.setOnAction(event -> {
			window = (Stage)menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GoodsScreen.fxml"));
            Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Scene scene = new Scene(root);
            window.setTitle("Managing Agents");
            window.getIcons().add(new Image("/images/mylogo.png"));
            window.setScene(scene);
            
        });
		exportBtn.setOnAction(event -> {
			window = (Stage)menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ExportScreen.fxml"));
            Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Scene scene = new Scene(root);
            window.setTitle("Managing Agents");
            window.getIcons().add(new Image("/images/mylogo.png"));
            window.setScene(scene);
            
        });
		reportBtn.setOnAction(event -> {
			window = (Stage)menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ReportScreen.fxml"));
            Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Scene scene = new Scene(root);
            window.setTitle("Managing Agents");
            window.getIcons().add(new Image("/images/mylogo.png"));
            window.setScene(scene);
            
        });
		settingBtn.setOnAction(event -> {
			window = (Stage)menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SettingScreen.fxml"));
            Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Scene scene = new Scene(root);
            window.setTitle("Managing Agents");
            window.getIcons().add(new Image("/images/mylogo.png"));
            window.setScene(scene);
            
        });
		agencyBtn.setOnAction(event -> {
			window = (Stage)menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AgencyScreen.fxml"));
            Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Scene scene = new Scene(root);
            window.setTitle("Managing Agents");
            window.getIcons().add(new Image("/images/mylogo.png"));
            window.setScene(scene);
            
        });
		
		
}

	public void setupScreen() {
		
			

		connection = JDBCUtil.getConnection();
		String query = "SELECT COUNT(*) AS total FROM AGENT where Agent_Status = 1";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int total = resultSet.getInt("total");
				totalAgent.setText(String.valueOf(total));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
		}
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		connection = JDBCUtil.getConnection();
		String query2 = "SELECT COUNT(*) AS total FROM ITEMS where Items_Status = 1";
		try {
			preparedStatement = connection.prepareStatement(query2);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int total = resultSet.getInt("total");
				totalGoods.setText(String.valueOf(total));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		user_name.setText(CurrentUser.userName);
		user_name.autosize();
		Role role = DAO_Role.getInstance().selectByCondition2(" User_Id = " + CurrentUser.userId);
		user_position.setText(role.getPosition());
		user_position.autosize();
		
		
		
		darkPane.setVisible(false);


	    javafx.animation.FadeTransition fadeTransition=new javafx.animation.FadeTransition(Duration.seconds(0.39),darkPane);
	    fadeTransition.setFromValue(1);
	    fadeTransition.setToValue(0);
	    fadeTransition.play();

	    javafx.animation.TranslateTransition translateTransition=new javafx.animation.TranslateTransition(Duration.seconds(0.39),detailMenuPane);
	    translateTransition.setByX(-600);
	    translateTransition.play();

	   
	    
	    canChange = true;
	    menuPane.setOnMouseEntered(event -> {
	    	timeChange += 1;
	    	if(canChange&& timeChange ==1) {
	    		menuImg.setVisible(false);
	    		darkPane.setVisible(true);
	        
	        javafx.animation.FadeTransition fadeTransition1=new javafx.animation.FadeTransition(Duration.seconds(0.39),darkPane);
	        //fadeTransition1.setFromValue(0);
	        //fadeTransition1.setToValue(0.1);
	        fadeTransition1.play();
	        
	        

	        javafx.animation.TranslateTransition translateTransition1=new javafx.animation.TranslateTransition(Duration.seconds(0.01),detailMenuPane);
	        translateTransition1.setByX(+600);
	        translateTransition1.setOnFinished(event1 -> {
	            canChange = false;
	            
	            
	        });
	        translateTransition1.play();
	        
	        
	        
	        
	        
	       
	    	}
	        
	    });
	    detailMenuPane.setOnMouseExited(event -> {
	    	
	    	if(!canChange && event.getX()> 100) {
	    		
	        javafx.animation.FadeTransition fadeTransition1=new javafx.animation.FadeTransition(Duration.seconds(0.39),darkPane);
	        //fadeTransition1.setFromValue(0.15);
	        //fadeTransition1.setToValue(0);
	        fadeTransition1.play();

	        fadeTransition1.setOnFinished(event1 -> {
	        	darkPane.setVisible(false);
	        });


	        javafx.animation.TranslateTransition translateTransition1=new javafx.animation.TranslateTransition(Duration.seconds(0.39),detailMenuPane);
	        translateTransition1.setByX(-600);
	        translateTransition1.setOnFinished(event1 -> {
	        	canChange = true;
	            timeChange = 0;
	            menuImg.setVisible(true);
	            
	        });
	        translateTransition1.play();
	    	}
	    });
	    

	}
	
	public void setupUsernameLabel(String username) {
	    if (nameLbl != null) {
	        nameLbl.setText(username);
	    } else {
	        System.out.println("Biến nameLbl bị null");
	    }
	}
	
	public void setupTable() {
		
	}
	private double x = 0;
    private double y = 0;

    
    public void logout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginScreen.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) logoutBtn.getScene().getWindow();
            stage.setScene(scene);
            
            stage.setX((Screen.getPrimary().getVisualBounds().getWidth() - stage.getWidth()) / 2);
            stage.setY((Screen.getPrimary().getVisualBounds().getHeight() - stage.getHeight()) / 2);
            stage.show(); 
        }
    }
    
   
	
}

                                      

