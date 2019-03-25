package Chat;
import java.rmi.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class RChatClient {

	String name;
	RemoteChat rmChat;
	boolean end = false;

	public RChatClient(String n) throws Exception {
		RemoteChat rmchat;
		this.name =n;
		rmchat = (RemoteChat) Naming.lookup("rmi://localhost/Service");
		this.rmChat = rmchat.registerUser(name);

		// start a thread that reads the messages from the remote object
		MessageReader updater = new MessageReader(this.rmChat);
		updater.start();

	}
// gets input recursively from user 
	public void getInput() {
		String msg = "";
		InputStreamReader ins = new InputStreamReader(System.in);
		BufferedReader userinput = new BufferedReader(ins);

		System.out.printf("Dear client %s, write your message or write 'quit' to terminate:\n", this.name);
		while (true) {
			try {
				msg = userinput.readLine();
				if (!(msg.equals("quit"))) {

					// this sends the message send to remote server
					rmChat.send(msg);

				} else {
					end = true;
					break;
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Please pass an argument as name");
			return;
		}

		RChatClient client = new RChatClient(args[0]);
		client.getInput();

	}

	class MessageReader extends Thread {

		RemoteChat input;

		public MessageReader(RemoteChat userChats) {
			input = userChats;
		}

		public void run() {
			while (true) {
				// for ending the statement sleep for 5 seconds
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				ArrayList<String> ms = null;
				try {
					ms = input.recieve();
				} catch (RemoteException e) {
					
					e.printStackTrace();
				}
				if (ms != null && ms.size() > 0) {
					for (String m : ms) {
						System.out.println(m);
					}
				}

				if (end) {
					break;
				}
			}
		}

	}
}