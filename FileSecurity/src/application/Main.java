package application;
	
import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label; 

import javafx.stage.FileChooser; 
import java.io.File;

import java.util.HashMap;

import java.nio.file.Files;
import java.security.MessageDigest;
import java.math.BigInteger;

public class Main extends Application {
	private Label dosya_lb = new Label("lütfen dosya seçin");
	String file_name = "-";
	HashMap<String, String> depo1 = new HashMap<>();
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Pane root = new Pane();
			
			Scene scene = new Scene(root,1280,720);
			
			
			String a = "0";
			if (1 == 10)
			{
				a = "Veritabanı : Bağlandı";
			}
			else
			{
				a = "Veritabanı : Bağlı Değil";
			}
			
			
			Label db_onay = new Label(a);
			
			db_onay.setLayoutX(100.0);
			db_onay.setLayoutY(20.0);
			root.getChildren().add(db_onay);
			
			Label cikti_lbl = new Label("---secilen dosya---");
			
			cikti_lbl.setLayoutX(100);
			cikti_lbl.setLayoutY(40);
			root.getChildren().add(cikti_lbl);	
			
			Label output = new Label("output : ");
			
			output.setLayoutX(100);
			output.setLayoutY(60);
			root.getChildren().add(output);
			
			Label query = new Label("Sorgulanan dosya yok");
			
			query.setLayoutX(1000);
			query.setLayoutY(500);
			root.getChildren().add(query);
			
			
			
			//db_onay.setLayoutX(100.0);
			//db_onay.setLayoutY(40.0);
			//root.getChildren().add(dosya_lb);
			
//----------------------------v------ekle--------------v--------------------------
			
			Button file_add = new Button("ekle");
			
			file_add.setLayoutX(100.0);
			file_add.setLayoutY(600.0);
			
			file_add.setOnAction(e -> {
				
				String hashstr1 = "-";
		        
		        
		        FileChooser fileChooser = new FileChooser();
		        fileChooser.setTitle("Lütfen Dosya Seçin");
		        
		       
		        fileChooser.getExtensionFilters().addAll(
		            
		            new FileChooser.ExtensionFilter("Tüm Dosyalar", "*.*")
		        );
		        
		        
		        File selectedFile = fileChooser.showOpenDialog(primaryStage); // dosyayı alıyor
		        
		        
		        if (selectedFile != null) {
		            
		            dosya_lb.setText("Seçilen Dosya: " + selectedFile.getAbsolutePath());
		            cikti_lbl.setText("Seçilen Dosya: " + selectedFile.getAbsolutePath());
		            try{
		            	 //byte[] data = Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath()));
			            byte[] data = Files.readAllBytes(selectedFile.toPath());
			            byte[] hash = MessageDigest.getInstance("SHA-256").digest(data);
			            hashstr1 = new BigInteger(1, hash).toString(16);
			            
			            
			            
			            
			            file_name = selectedFile.getName();
			            depo1.put(file_name, hashstr1);
			            output.setText("output : "+depo1);
			            
		            }
		            catch (Exception ex) 
		            {
		            	ex.printStackTrace();
					}
		         
		            
		        } 
		        else 
		        {
		            
		            dosya_lb.setText("Dosya seçimi iptal edildi.");
		        }
				});
			
			

			root.getChildren().add(file_add);
			
//---------------v--------sorgu----------v-----------------------------------------
			
			Button sorgu_btn = new Button("sorgula");
			
			sorgu_btn.setLayoutX(1100.0);
			sorgu_btn.setLayoutY(600.0);
			root.getChildren().add(sorgu_btn);
			
			sorgu_btn.setOnAction(e -> {
				
				String hashstr2 = "-";
				
				FileChooser fileChooser = new FileChooser();
		        fileChooser.setTitle("Lütfen Dosya Seçin");
		        
		       
		        fileChooser.getExtensionFilters().addAll(
		            
		            new FileChooser.ExtensionFilter("Tüm Dosyalar", "*.*")
		        );
		        
		        
		        File selectedFile = fileChooser.showOpenDialog(primaryStage); // dosyayı alıyor
		        
		        
		        if (selectedFile != null) {
		            
		            dosya_lb.setText("Seçilen Dosya: " + selectedFile.getAbsolutePath());
		            cikti_lbl.setText("Seçilen Dosya: " + selectedFile.getAbsolutePath());
		            try{
		            	 //byte[] data = Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath()));
			            byte[] data = Files.readAllBytes(selectedFile.toPath());
			            byte[] hash = MessageDigest.getInstance("SHA-256").digest(data);
			            hashstr2 = new BigInteger(1, hash).toString(16);
			            
			            
			            
			            
			            file_name = selectedFile.getName();
			            
			            if (depo1.containsValue(hashstr2))
			            {
			            	query.setText("dosya değiştirilmedi");
			            }
			            else
			            {
			            	query.setText("böyle bir dosya sistemde kayıt edilmedi");
			            }
			            
		            }
		            catch (Exception ex) 
		            {
		            	ex.printStackTrace();
					}
		         
		            
		        } 
		        else 
		        {
		            
		            dosya_lb.setText("Dosya seçimi iptal edildi.");
		        }
				
			});
			
			
			
			
			
			
			
			
			
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("FileSecurity");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}

