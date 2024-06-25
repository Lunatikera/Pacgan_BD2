/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package excepciones;

/**
 *
 * @author triny
 */
public class PresentacionException extends Exception {

    /**
     * Creates a new instance of <code>PresentacionException</code> without
     * detail message.
     */
    public PresentacionException() {
    }

    /**
     * Constructs an instance of <code>PresentacionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PresentacionException(String msg) {
        super(msg);
    }
}
