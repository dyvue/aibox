/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package aibox;

import aibox.view.Layout;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author  Romadhan Edy, Bagas Ardiansyah, Marlinus, Abdul Latif
 * @version 1.0
 */
public class Aibox extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Layout ui = new Layout();
        ui.setVisible(true);   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
