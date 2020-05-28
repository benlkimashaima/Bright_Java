/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.services;



import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author AHMED
 */
public class TextFieldValidation {

    public static boolean isValidEmail(TextField tf) {
        boolean b = false;
        String pattern = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        if (tf.getText().matches(pattern)) {
            b = true;
        }
        return b;
    }

    public static boolean isValidEmail(TextField tf, Label lb, String errorMessage) {
        boolean b = true;
        String msg = null;
        tf.getStyleClass().remove("error");
        if (!isValidEmail(tf)) {
            b = false;
            msg = errorMessage;
            tf.getStyleClass().add("error");
        }
        lb.setText(msg);
        return b;
    }
    
    public static boolean isPasswordMatched(PasswordField tf1, PasswordField tf2){
        boolean b = false;
        if(tf1.getText().equals(tf2.getText()))
            b=true;
        return b ;
    }
    
        public static boolean isPasswordMatched(PasswordField tf1, PasswordField tf2, Label lb, String errorMessage){
        boolean b = false;
        String msg=null;
        tf2.getStyleClass().remove("error");
        if(!isPasswordMatched(tf1, tf2)){
            b=false;
            msg=errorMessage;
            tf2.getStyleClass().add("error");
        }
        lb.setText(msg);
        return b;
    }

}
