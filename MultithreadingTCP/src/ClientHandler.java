import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Initialisation des flux de lecture et d'�criture
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                // Inversion de la cha�ne de caract�res
                String reversedString = reverseString(inputLine);

                // Simuler un traitement long avec Thread.sleep
                Thread.sleep(2000);

                // Envoi de la cha�ne invers�e au client
                writer.println(reversedString);
            }

            // Fermeture des flux et du socket
            reader.close();
            writer.close();
            clientSocket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}