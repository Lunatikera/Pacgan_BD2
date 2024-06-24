/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.pacgan_negociobd;

import insertadores.insertarBeneficiario;

/**
 *
 * @author Usuario
 */
public class Pacgan_NegocioBD {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        insertarBeneficiario ins = new insertarBeneficiario();
        ins.insertarBeneficiarios();
    }
}
