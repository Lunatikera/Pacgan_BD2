/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.pacgan_persistenciabd2;

import daos.BeneficiarioDAO;
import entidades.BeneficiarioEntidad;
import excepciones.PersistenciaException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Pacgan_PersistenciaBD2 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        BeneficiarioDAO beneDAO = new BeneficiarioDAO();
        try {
            beneDAO.agregarBeneficiario(new BeneficiarioEntidad());
        } catch (PersistenciaException ex) {
            Logger.getLogger(Pacgan_PersistenciaBD2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Soleyne1804
}
