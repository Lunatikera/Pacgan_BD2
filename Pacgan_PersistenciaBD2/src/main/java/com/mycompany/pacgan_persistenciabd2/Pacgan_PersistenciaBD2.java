/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.pacgan_persistenciabd2;

import daos.BeneficiarioDAO;
import entidades.BeneficiarioEntidad;
import excepciones.PersistenciaException;


/**
 *
 * @author Usuario
 */
public class Pacgan_PersistenciaBD2 {

    public static void main(String[] args) throws PersistenciaException {
        System.out.println("Hello World!");
        BeneficiarioDAO bene = new BeneficiarioDAO();
        BeneficiarioEntidad b = new BeneficiarioEntidad();
        bene.agregarBeneficiario(b);
    }
}
