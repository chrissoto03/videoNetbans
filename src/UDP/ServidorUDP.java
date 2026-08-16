/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.net.*;
/**
 *
 * @author tonyM
 */
public class ServidorUDP {
    
    public static void main(String[] args) {
        
        final int PUERTO = 6000;
        
        try(DatagramSocket socket = new DatagramSocket(PUERTO)){
            System.out.println("Seervidor UPT iniciado... ");
            System.out.println("Esperando datagramas en el puerto "+ PUERTO);
            
            byte[] bufferEntrada = new byte[1024];
            
            while(true)
            {
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, 
                    bufferEntrada.length);
            socket.receive(paqueteEntrada);
            
            String mensaje = new String(paqueteEntrada.getData(), 
                    0, paqueteEntrada.getLength());
            InetAddress direccionCliente = paqueteEntrada.getAddress();
            int puertoCliente = paqueteEntrada.getPort();
            
            System.out.println("Mensaje recibido: "+mensaje);
            System.out.println("Desde: "+direccionCliente+" : "+puertoCliente);
           
            String respuesta;
            
                if (mensaje.equalsIgnoreCase("salir")) 
                {
                    respuesta = "Servidor UDP finalizado.";
                    byte[] bufferSalida = respuesta.getBytes();
                    
                    DatagramPacket paqueteSalida = new DatagramPacket(
                    bufferSalida, bufferSalida.length, 
                            direccionCliente, 
                            puertoCliente
                    );
                    socket.send(paqueteSalida);
                    break;
                }else
                {
                    respuesta = "Servidor recibio por UDP "+mensaje;
                    byte[] bufferSalida = respuesta.getBytes();
                    
                    DatagramPacket paqueteSalida = new DatagramPacket(
                    bufferSalida, bufferSalida.length, 
                            direccionCliente, 
                            puertoCliente
                    );
                    socket.send(paqueteSalida);
                }
            }
            System.out.println("Servidor UDP detendio...");
        } catch (Exception e) {
            System.out.println("Error en el servidor UDP");
        }
        
    }
    
    
}
