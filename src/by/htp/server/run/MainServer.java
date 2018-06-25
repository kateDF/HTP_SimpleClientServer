package by.htp.server.run;

import java.io.IOException;

import by.htp.server.console.ConsoleServer;

public class MainServer {

	public static void main(String[] args) {
		ConsoleServer server = new ConsoleServer();
		try {
			System.out.println("Server started...");

			server.listen(9595);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
				System.out.println("Server stopped...");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
