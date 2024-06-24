/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encriptadores;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author jesus
 */
public class Encriptacion {
    
      private static final String CLAVE_ENCRIPTACION = "amiguito";

    public static String encriptar(String texto) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(CLAVE_ENCRIPTACION);
        return textEncryptor.encrypt(texto);
    }

    public static String desencriptar(String textoEncriptado) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(CLAVE_ENCRIPTACION);
        return textEncryptor.decrypt(textoEncriptado);
    }

    
}
