import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;

public class Server {
	
	
	public static void main(String[] args) {
		
		BufferedReader inDalClient;
		DataOutputStream outVersoClient;
		
		try {
			ServerSocket serverSocket=new ServerSocket(2000); //creazione di un'istanza della classa java.net.ServerSocket, va specificato il numero di porta su cui rimanere in ascolto. Realizza il connection socket
			System.out.println("server avviato correttamente");
			Socket socket=serverSocket.accept(); //ritorno del metodo accept, � un metodo bloccante (non succede nulla finch� non arriva la richiesta)
			System.out.println("comunicazione avvenuta correttamente"); //arriviamo qui solo se il client fa richiesta
			System.out.println(socket); //stampa le caratteristiche dell'oggetto socket
			inDalClient= new BufferedReader(new InputStreamReader(socket.getInputStream())); //il server pu� ricevere dati dal client leggendo dall'InputStream
			outVersoClient= new DataOutputStream(socket.getOutputStream()); //il server pu� scrivere sull'OutputStream
			outVersoClient.writeBytes("Ciao Client"+"\r\n"); //La concatenazione dei caratteri di controllo di fine riga "\r\n" � necessaria per l'utilizzo del metodo readLine()
			String messaggioDelClient= inDalClient.readLine(); //lettura dell'InputStream tramite metodo readLine
			System.out.println("Messaggio del client: "+ messaggioDelClient);
			String timeString = new Timestamp(System.currentTimeMillis()).toString(); //ottengo la data e l'ora corrente trasformandola in stringa tramite metodo toString. La trasformo in stringa in modo da poter utilizzare il metodo writeBytes
			outVersoClient.writeBytes(timeString+"\r\n"); //scrittura sull'OutputStream tramite metodo writeBytes.
			System.out.println("Chiusura connessione");
			socket.close(); //chiusura della connessione
		}
		catch (BindException e) { //gestione dell'eccezione BindException (porta in cui si sta mettondo in ascolto il server occupata)
			// TODO Auto-generated catch block
			System.err.println("porta occupata");
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("errore avvio server sulla porta 2000");
			e.printStackTrace();
		}

    }

}
