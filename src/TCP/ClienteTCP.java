/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author chris
 */
public class ClienteTCP {
    
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PUERTO = 5000;
        
        try(Socket socket = new Socket(HOST, PUERTO)) 
        {
            System.out.println("Conectado al servidor TCP..");
            
            BufferedReader entrada = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(),true);
            
            Scanner teclado = new Scanner(System.in);
            String mensaje;
            
            do {                
                System.out.println("Escriba un mensaje para el servidor: ");
                mensaje = teclado.nextLine();
                
                salida.println(mensaje);
                String respuesta = entrada.readLine();
                System.out.println("Respuesta del servidor: "+respuesta);
            } while (!mensaje.equalsIgnoreCase("salir"));
                       
        } catch (Exception e) {
            System.out.println("Error del cliente TCP... " +e.getMessage());
        }
    }
    
    
}
