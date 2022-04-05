import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {
	
	public static void main(String[] args) {
		
		BufferedReader tastiera; //buffer per l'input da tastiera
		DataOutputStream outVersoServer; //stream di output
		BufferedReader inDalServer; //stream di input
		
		try {
			Socket socket = new Socket("127.0.0.1",2000); //apro socket specificando ip e numero di porta del server
			tastiera= new BufferedReader(new InputStreamReader(System.in)); //InputStreamReader trasforma uno stream di byte in uno stream di caratteri. BufferedReader ci permette di leggere riga per riga
			outVersoServer= new DataOutputStream(socket.getOutputStream()); //il client pu� scrivere sull'OutputStream
			inDalServer= new BufferedReader(new InputStreamReader(socket.getInputStream())); //il client pu� ricevere dati dal server leggendo dall'InputStream
			String messaggioDelServer= inDalServer.readLine(); //lettura dell'InputStream tramite metodo readLine
			System.out.println("Messaggio del server: "+ messaggioDelServer);
			System.out.println("Messaggio per il server: ");
			String messaggioPerServer= tastiera.readLine(); //inserimento di una stringa da tastiera
			outVersoServer.writeBytes(messaggioPerServer+"\r\n"); //scrittura sull'OutputStream tramite metodo writeBytes. La concatenazione dei caratteri di controllo di fine riga "\r\n" � necessaria per l'utilizzo del metodo readLine()
			messaggioDelServer= inDalServer.readLine();
			System.out.println("Messaggio del Server: "+ messaggioDelServer);
			System.out.println("Chiusura connessione");
			socket.close(); //chiusura della connessione
		} catch (UnknownHostException e) { //gestione dell'eccezione UnkonwnHostException (host sconosciuto) 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("errore connessione con il server");
			e.printStackTrace();
		}

    }

}
