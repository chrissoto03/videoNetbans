/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author chris
 */
public class ServerMulti {
    private static final int PUERTO = 5000;
    private static int contadorClientes = 0;

    public static void main(String[] args) {
        
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor TCP iniciado...");
            System.out.println("Esperando clientes en el puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();
                
                contadorClientes++;
                int numeroCliente = contadorClientes;
                System.out.println();
                System.out.println("Cliente #" + numeroCliente + " conectado.");
                System.out.println("Dirección IP: "+ cliente.getInetAddress().getHostAddress());

                Thread hiloCliente = new Thread(() -> {atenderCliente(cliente, numeroCliente);});
                hiloCliente.start();
            }

        } catch (Exception e) {
            System.out.println("Error en el servidor TCP: " + e.getMessage());
        }
    }

    private static void atenderCliente(
            Socket cliente,
            int numeroCliente
    ) {

        try (
            Socket socketCliente = cliente;

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(),true)) {
            
            salida.println("Bienvenido. Usted es el cliente #" + numeroCliente);
            salida.println("Escriba mensajes o escriba 'salir' " + "para finalizar la conexión.");

            String mensajeCliente;
            while ((mensajeCliente = entrada.readLine()) != null) {
                System.out.println("Cliente #" + numeroCliente+ " dice: " + mensajeCliente);
                if (mensajeCliente.equalsIgnoreCase("salir")) {
                    salida.println("Conexión finalizada por el servidor.");
                    break;
                }
                salida.println("Servidor recibió del cliente #"+ numeroCliente+ ": " + mensajeCliente);
            }

        } catch (Exception e) {
            System.out.println("Error con el cliente #" + numeroCliente + ": " + e.getMessage());

        } finally {
            System.out.println("Cliente #" + numeroCliente + " desconectado.");
        }
    }
}