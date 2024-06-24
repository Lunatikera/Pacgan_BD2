/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insertadores;

import daos.BeneficiarioDAO;
import dtos.BeneficiarioDTO;
import excepciones.NegocioException;
import interfaces.IAgregarBeneficiarioBO;
import interfaces.IBeneficiarioDAO;
import interfaces.IinsertarBeneficiario;
import java.math.BigDecimal;
import negocio.AgregarBeneficiarioBO;

/**
 *
 * @author jesus
 */
public class insertarBeneficiario implements IinsertarBeneficiario{

    @Override
    public void insertarBeneficiarios() {

        // Beneficiario 1
        BeneficiarioDTO beneficiario1 = new BeneficiarioDTO();
        beneficiario1.setClaveContrato("1234567890");
        beneficiario1.setNombre("Juan");
        beneficiario1.setApellidoPA("Perez");
        beneficiario1.setApellidoMA("Gomez");
        beneficiario1.setSaldo(new BigDecimal(1000000));  // Saldo de un millón
        beneficiario1.setNombreUsuario("juanito123");
        beneficiario1.setContraseña("contrasena123");
        beneficiario1.setBeneficiarioPagoIds(null);
        beneficiario1.setBeneficiarioCuentaIds(null);

// Beneficiario 2
        BeneficiarioDTO beneficiario2 = new BeneficiarioDTO();
        beneficiario2.setClaveContrato("0987654321");
        beneficiario2.setNombre("Maria");
        beneficiario2.setApellidoPA("Lopez");
        beneficiario2.setApellidoMA("Garcia");
        beneficiario2.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario2.setNombreUsuario("marialopez");
        beneficiario2.setContraseña("contrasena456");
        beneficiario2.setBeneficiarioPagoIds(null);
        beneficiario2.setBeneficiarioCuentaIds(null);

// Beneficiario 3
        BeneficiarioDTO beneficiario3 = new BeneficiarioDTO();
        beneficiario3.setClaveContrato("1357924680");
        beneficiario3.setNombre("Pedro");
        beneficiario3.setApellidoPA("Gonzalez");
        beneficiario3.setApellidoMA("Martinez");
        beneficiario3.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario3.setNombreUsuario("pedrogonzalez");
        beneficiario3.setContraseña("contrasena789");
        beneficiario3.setBeneficiarioPagoIds(null);
        beneficiario3.setBeneficiarioCuentaIds(null);

// Beneficiario 4
        BeneficiarioDTO beneficiario4 = new BeneficiarioDTO();
        beneficiario4.setClaveContrato("2468135790");
        beneficiario4.setNombre("Luis");
        beneficiario4.setApellidoPA("Hernandez");
        beneficiario4.setApellidoMA("Sanchez");
        beneficiario4.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario4.setNombreUsuario("luisito");
        beneficiario4.setContraseña("contrasena246");
        beneficiario4.setBeneficiarioPagoIds(null);
        beneficiario4.setBeneficiarioCuentaIds(null);

// Beneficiario 5
        BeneficiarioDTO beneficiario5 = new BeneficiarioDTO();
        beneficiario5.setClaveContrato("3692581470");
        beneficiario5.setNombre("Ana");
        beneficiario5.setApellidoPA("Ramirez");
        beneficiario5.setApellidoMA("Flores");
        beneficiario5.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario5.setNombreUsuario("anarami");
        beneficiario5.setContraseña("contrasena369");
        beneficiario5.setBeneficiarioPagoIds(null);
        beneficiario5.setBeneficiarioCuentaIds(null);

// Beneficiario 6
        BeneficiarioDTO beneficiario6 = new BeneficiarioDTO();
        beneficiario6.setClaveContrato("4815162342");
        beneficiario6.setNombre("Carolina");
        beneficiario6.setApellidoPA("Santos");
        beneficiario6.setApellidoMA("Lopez");
        beneficiario6.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario6.setNombreUsuario("carito");
        beneficiario6.setContraseña("contrasena481");
        beneficiario6.setBeneficiarioPagoIds(null);
        beneficiario6.setBeneficiarioCuentaIds(null);

// Beneficiario 7
        BeneficiarioDTO beneficiario7 = new BeneficiarioDTO();
        beneficiario7.setClaveContrato("5738291046");
        beneficiario7.setNombre("Javier");
        beneficiario7.setApellidoPA("Diaz");
        beneficiario7.setApellidoMA("Gutierrez");
        beneficiario7.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario7.setNombreUsuario("javi");
        beneficiario7.setContraseña("contrasena573");
        beneficiario7.setBeneficiarioPagoIds(null);
        beneficiario7.setBeneficiarioCuentaIds(null);

// Beneficiario 8
        BeneficiarioDTO beneficiario8 = new BeneficiarioDTO();
        beneficiario8.setClaveContrato("6897023145");
        beneficiario8.setNombre("Sofia");
        beneficiario8.setApellidoPA("Ruiz");
        beneficiario8.setApellidoMA("Fernandez");
        beneficiario8.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario8.setNombreUsuario("sofi");
        beneficiario8.setContraseña("contrasena689");
        beneficiario8.setBeneficiarioPagoIds(null);
        beneficiario8.setBeneficiarioCuentaIds(null);

// Beneficiario 9
        BeneficiarioDTO beneficiario9 = new BeneficiarioDTO();
        beneficiario9.setClaveContrato("7048159326");
        beneficiario9.setNombre("Daniel");
        beneficiario9.setApellidoPA("Martinez");
        beneficiario9.setApellidoMA("Santana");
        beneficiario9.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario9.setNombreUsuario("dani");
        beneficiario9.setContraseña("contrasena704");
        beneficiario9.setBeneficiarioPagoIds(null);
        beneficiario9.setBeneficiarioCuentaIds(null);

// Beneficiario 10
        BeneficiarioDTO beneficiario10 = new BeneficiarioDTO();
        beneficiario10.setClaveContrato("8192037465");
        beneficiario10.setNombre("Elena");
        beneficiario10.setApellidoPA("Gomez");
        beneficiario10.setApellidoMA("Hernandez");
        beneficiario10.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario10.setNombreUsuario("elena");
        beneficiario10.setContraseña("contrasena819");
        beneficiario10.setBeneficiarioPagoIds(null);
        beneficiario10.setBeneficiarioCuentaIds(null);

// Beneficiario 11
        BeneficiarioDTO beneficiario11 = new BeneficiarioDTO();
        beneficiario11.setClaveContrato("9283746510");
        beneficiario11.setNombre("Gabriel");
        beneficiario11.setApellidoPA("Luna");
        beneficiario11.setApellidoMA("Ortega");
        beneficiario11.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario11.setNombreUsuario("gabo");
        beneficiario11.setContraseña("contrasena928");
        beneficiario11.setBeneficiarioPagoIds(null);
        beneficiario11.setBeneficiarioCuentaIds(null);

// Beneficiario 12
        BeneficiarioDTO beneficiario12 = new BeneficiarioDTO();
        beneficiario12.setClaveContrato("1038475629");
        beneficiario12.setNombre("Isabel");
        beneficiario12.setApellidoPA("Vargas");
        beneficiario12.setApellidoMA("Perez");
        beneficiario12.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario12.setNombreUsuario("isav");
        beneficiario12.setContraseña("contrasena103");
        beneficiario12.setBeneficiarioPagoIds(null);
        beneficiario12.setBeneficiarioCuentaIds(null);

// Beneficiario 13
        BeneficiarioDTO beneficiario13 = new BeneficiarioDTO();
        beneficiario13.setClaveContrato("1157913582");
        beneficiario13.setNombre("Roberto");
        beneficiario13.setApellidoPA("Gomez");
        beneficiario13.setApellidoMA("Flores");
        beneficiario13.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario13.setNombreUsuario("robgom");
        beneficiario13.setContraseña("contrasena115");
        beneficiario13.setBeneficiarioPagoIds(null);
        beneficiario13.setBeneficiarioCuentaIds(null);

// Beneficiario 14
        BeneficiarioDTO beneficiario14 = new BeneficiarioDTO();
        beneficiario14.setClaveContrato("1264938295");
        beneficiario14.setNombre("Laura");
        beneficiario14.setApellidoPA("Hernandez");
        beneficiario14.setApellidoMA("Rojas");
        beneficiario14.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario14.setNombreUsuario("laurarojas");
        beneficiario14.setContraseña("contrasena126");
        beneficiario14.setBeneficiarioPagoIds(null);
        beneficiario14.setBeneficiarioCuentaIds(null);

// Beneficiario 15
        BeneficiarioDTO beneficiario15 = new BeneficiarioDTO();
        beneficiario15.setClaveContrato("1349281765");
        beneficiario15.setNombre("Carlos");
        beneficiario15.setApellidoPA("Martinez");
        beneficiario15.setApellidoMA("Garcia");
        beneficiario15.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario15.setNombreUsuario("carlosmg");
        beneficiario15.setContraseña("contrasena134");
        beneficiario15.setBeneficiarioPagoIds(null);
        beneficiario15.setBeneficiarioCuentaIds(null);

// Beneficiario 16
        BeneficiarioDTO beneficiario16 = new BeneficiarioDTO();
        beneficiario16.setClaveContrato("1452796318");
        beneficiario16.setNombre("Daniela");
        beneficiario16.setApellidoPA("Perez");
        beneficiario16.setApellidoMA("Lopez");
        beneficiario16.setSaldo(new BigDecimal(1000000)); // Saldo de un millón
        beneficiario16.setNombreUsuario("danip");
        beneficiario16.setContraseña("contrasena145");
        beneficiario16.setBeneficiarioPagoIds(null);
        beneficiario16.setBeneficiarioCuentaIds(null);

// Beneficiario 17
        BeneficiarioDTO beneficiario17 = new BeneficiarioDTO();
        beneficiario17.setClaveContrato("1526378490");
        beneficiario17.setNombre("Mario");
        beneficiario17.setApellidoPA("Gutierrez");
        beneficiario17.setApellidoMA("Santos");
        beneficiario17.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario17.setNombreUsuario("mariosantos");
        beneficiario17.setContraseña("contrasena152");
        beneficiario17.setBeneficiarioPagoIds(null);
        beneficiario17.setBeneficiarioPagoIds(null);
        beneficiario17.setBeneficiarioCuentaIds(null);

// Beneficiario 18
        BeneficiarioDTO beneficiario18 = new BeneficiarioDTO();
        beneficiario18.setClaveContrato("1638492057");
        beneficiario18.setNombre("Veronica");
        beneficiario18.setApellidoPA("Castro");
        beneficiario18.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario18.setNombreUsuario("veronicastro");
        beneficiario18.setContraseña("veronica1234");
        beneficiario18.setBeneficiarioPagoIds(null);
        beneficiario18.setBeneficiarioCuentaIds(null);

        // Beneficiario 19
        BeneficiarioDTO beneficiario19 = new BeneficiarioDTO();
        beneficiario19.setClaveContrato("01020304");
        beneficiario19.setNombre("Claudia");
        beneficiario19.setApellidoPA("Shein");
        beneficiario19.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario19.setNombreUsuario("sheinClau");
        beneficiario19.setContraseña("claudia1234");
        beneficiario19.setBeneficiarioPagoIds(null);
        beneficiario19.setBeneficiarioCuentaIds(null);

        // Beneficiario 20
        BeneficiarioDTO beneficiario20 = new BeneficiarioDTO();
        beneficiario20.setClaveContrato("01020304");
        beneficiario20.setNombre("pou");
        beneficiario20.setApellidoPA("cafe");
        beneficiario20.setApellidoMA("bolita");
        beneficiario20.setSaldo(new BigDecimal(1000000));// Saldo de un millón
        beneficiario20.setNombreUsuario("pousillo");
        beneficiario20.setContraseña("1234pousillo");
        beneficiario20.setBeneficiarioPagoIds(null);
        beneficiario20.setBeneficiarioCuentaIds(null);

        try {

            IBeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO(); // instancia válida de IBeneficiarioDAO
            AgregarBeneficiarioBO agregarBeneficiarioBO = new AgregarBeneficiarioBO(beneficiarioDAO);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario1);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario2);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario3);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario4);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario5);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario6);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario7);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario8);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario9);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario10);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario11);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario12);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario13);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario14);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario15);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario16);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario17);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario18);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario19);
            agregarBeneficiarioBO.agregarBeneficiario(beneficiario20);

            System.out.println("Beneficiarios insertados correctamente.");
        } catch (NegocioException e) {
            System.err.println("Error al insertar beneficiarios: " + e.getMessage());
            e.printStackTrace(); // Esta línea imprime la traza de la excepción
        }

    }
}
