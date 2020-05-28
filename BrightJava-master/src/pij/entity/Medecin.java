/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pij.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author DELL
 */
public class Medecin implements Serializable{
    private int id ; 
    private int nCIN;
    private String nonMedecin;
    private String prenomMedecin;
    private String telephone;
    private String email; 
    private int specialite ; 

    public int getSpecialite() {
        return specialite;
    }

    public void setSpecialite(int specialite) {
        this.specialite = specialite;
    }

    public Medecin(int nCIN, String nonMedecin, String prenomMedecin, String telephone,  int specialite,String email) {
       
        this.nCIN = nCIN;
        this.nonMedecin = nonMedecin;
        this.prenomMedecin = prenomMedecin;
        this.telephone = telephone;
        this.specialite = specialite;
        
        this.email = email;
    }
  

    public Medecin() {
    }

    public Medecin(int id, int nCIN, String nonMedecin, String prenomMedecin, String telephone, int specialite, String email) {
        this.id = id;
        this.nCIN = nCIN;
        this.nonMedecin = nonMedecin;
        this.prenomMedecin = prenomMedecin;
        this.telephone = telephone;
        this.email = email;
        
          this.specialite=specialite;
     
    }
     
     public Medecin( String nonMedecin, String prenomMedecin, String telephone, int specialite, String email) {
      
        this.nonMedecin = nonMedecin;
        this.prenomMedecin = prenomMedecin;
        this.telephone = telephone;
        this.email = email;
        
          this.specialite=specialite;
     
    }
   
    
    
    

    public int getId() {
        return id;
    }

    public int getnCIN() {
        return nCIN;
    }

    public String getNonMedecin() {
        return nonMedecin;
    }

    public String getPrenomMedecin() {
        return prenomMedecin;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setnCIN(int nCIN) {
        this.nCIN = nCIN;
    }

    public void setNonMedecin(String nonMedecin) {
        this.nonMedecin = nonMedecin;
    }

    public void setPrenomMedecin(String prenomMedecin) {
        this.prenomMedecin = prenomMedecin;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + this.nCIN;
        hash = 67 * hash + Objects.hashCode(this.nonMedecin);
        hash = 67 * hash + Objects.hashCode(this.prenomMedecin);
        hash = 67 * hash + Objects.hashCode(this.telephone);
        hash = 67 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medecin other = (Medecin) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nCIN != other.nCIN) {
            return false;
        }
        if (!Objects.equals(this.nonMedecin, other.nonMedecin)) {
            return false;
        }
        if (!Objects.equals(this.prenomMedecin, other.prenomMedecin)) {
            return false;
        }
        if (!Objects.equals(this.telephone, other.telephone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Medecin{" + "id=" + id + ", nCIN=" + nCIN + ", nonMedecin=" + nonMedecin + ", prenomMedecin=" + prenomMedecin + ", telephone=" + telephone + ", email=" + email + ", specialite=" + specialite + '}';
    }
    
    
    
    
}

