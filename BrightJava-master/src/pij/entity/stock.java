/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.entity;

/**
 *
 * @author HP
 */
public class stock {
     int id;
    String type;
     public stock(){
         
     }
     public stock(int id, String type) {
        this.id = id;
        this.type = type;
       
    }

    public stock(String type) {
        this.type = type;
       
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
@Override
    public String toString() {
        return " Type de Stock = "+ type ;
    
    
    }
    public Object get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    
}
