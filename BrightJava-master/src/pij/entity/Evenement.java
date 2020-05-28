/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.entity;

/**
 *
 * @author YURI
 */
public class Evenement {
    
    
       private int ref ;
    private int nbrPart ;
    private String titre ;
    private String description ;
    private String date ;   //Date.valueOf("yyyy-mm-dd")
    private String ville ;
    private int categorie_id;

    public Evenement(int ref, int nbrPart, String titre, String description, String date, String ville, int categorie_id) {
        this.ref = ref;
        this.nbrPart = nbrPart;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.ville = ville;
        this.categorie_id = categorie_id;
    }

    public int getNbrPart() {
        return nbrPart;
    }

    public void setNbrPart(int nbrPart) {
        this.nbrPart = nbrPart;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }
    

    public Evenement(int ref, int nbPart, String titre, String description, String date, String ville) {
        this.ref = ref;
        this.nbrPart = nbPart;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.ville = ville;
    }

    public Evenement(int nbPart, String titre, String description, String date, String ville, int cat_id) {
        this.nbrPart = nbPart;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.ville = ville;
        this.categorie_id=cat_id;
    }

    public Evenement() {
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public int getNbPart() {
        return nbrPart;
    }

    public void setNbPart(int nbPart) {
        this.nbrPart = nbPart;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Evenement{" + "ref=" + ref + ", nbPart=" + nbrPart + ", titre=" + titre + ", description=" + description + ", date=" + date + ", ville=" + ville + '}';
    }
    
}
