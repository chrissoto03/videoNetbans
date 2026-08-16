/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author tonyM
 */
public class ClienteUDP {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PUERTO_SERVIDOR = 6000;
        
        try (DatagramSocket socket = new DatagramSocket()){
            InetAddress direccionServidor = InetAddress.getByName(HOST);
            Scanner teclado = new Scanner(System.in);
            
            String mensaje;
            
            do {                
                System.out.println("Escriba un mensaje para enviar por UDP");
                mensaje = teclado.nextLine();
                
                byte[] bufferSalida = mensaje.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(
                bufferSalida, 
                        bufferSalida.length, 
                        direccionServidor, 
                        PUERTO_SERVIDOR
                );
                socket.send(paqueteEnvio);
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket paqueteRespuesta = new DatagramPacket(
                bufferEntrada, bufferEntrada.length
                );
                socket.receive(paqueteRespuesta);
                String respuesta = new String(
                paqueteRespuesta.getData(), 0, 
                        paqueteRespuesta.getLength());
                System.out.println("Respuesta del servidor: "+respuesta);
            
            } while (!mensaje.equalsIgnoreCase("salir"));
        } catch (Exception e) {
            System.out.println("Error en el cliente UDP: "+e.getMessage());
        }
    }
}
