
import excepciones.NegocioException;
import insertadores.InsertarEstatusPago;
import insertadores.insertarBeneficiario;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author jesus
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            
            
            InsertarEstatusPago InsertarEstatusPago = new InsertarEstatusPago();
            
            
            InsertarEstatusPago.insertarTiposDeEstatusPredeterminados();
        } catch (NegocioException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
