import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiClientServer {

	    public static void main(String[] args) {
	        final int PORT = 12000;
	        ServerSocket serverSocket = null;

	        try {
	            serverSocket = new ServerSocket(PORT);
	            System.out.println("Serveur en attente de connexions...");

	            while (true) {
	                Socket clientSocket = serverSocket.accept();
	                System.out.println("Nouvelle connexion acceptée.");

	                // Création d'un thread pour gérer le client
	                ClientHandler clientHandler = new ClientHandler(clientSocket);
	                Thread thread = new Thread(clientHandler);
	                thread.start();
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (serverSocket != null && !serverSocket.isClosed()) {
	                    serverSocket.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}