/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encriptadores;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author jesus
 */
public class Encriptacion {
    
    
    private static final String CLAVE_ENCRIPTACION = "amiguito";

    public static String encriptar(String texto) {
        if (texto == null || texto.isEmpty()) {
            throw new IllegalArgumentException("Texto no puede ser nulo o vacio");
        }
        
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(CLAVE_ENCRIPTACION);
        return textEncryptor.encrypt(texto);
    }

    public static String desencriptar(String textoEncriptado) {
        System.out.println(textoEncriptado);
        if (textoEncriptado == null || textoEncriptado.isEmpty()) {
            throw new IllegalArgumentException("Texto no puede ser nulo o vacio2");
        }
        
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(CLAVE_ENCRIPTACION);
        try {
            return textEncryptor.decrypt(textoEncriptado);
        } catch (EncryptionOperationNotPossibleException e) {
            throw new RuntimeException("Error al decifrar el texto", e);
        }
    }
}