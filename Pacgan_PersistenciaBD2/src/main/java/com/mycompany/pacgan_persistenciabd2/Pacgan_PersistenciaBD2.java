/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.pacgan_persistenciabd2;

import daos.BeneficiarioDAO;
import daos.ConexionBD;
import entidades.BeneficiarioEntidad;
import excepciones.PersistenciaException;
import interfaces.IBeneficiarioDAO;
import interfaces.IConexionBD;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Pacgan_PersistenciaBD2 {

    public static void main(String[] args) {
        IConexionBD conexionBD = new ConexionBD();
        IBeneficiarioDAO beneficiario = new BeneficiarioDAO(conexionBD);
        BigDecimal valor = new BigDecimal("100.50");

    BeneficiarioEntidad beneficiarioEntidad = new BeneficiarioEntidad("93i9839", "Carlos", "Rios", "Burgueño", valor, "Charls", "123", null, null);

        try {
            beneficiario.agregarBeneficiario (beneficiarioEntidad);
        } catch (PersistenciaException ex) {
            Logger.getLogger(Pacgan_PersistenciaBD2.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
