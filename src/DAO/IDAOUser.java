/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import models.User;

/**
 *
 * @author rahma
 */
public interface IDAOUser {

    public boolean creerCompte();
    public boolean modifierCompte(int id, User user);
    public boolean supprimerCompte();
    public User consulterCompte(int id);
    public boolean modifierMotDePasse(String newPass);
    
}

