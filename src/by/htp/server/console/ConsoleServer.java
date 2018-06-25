package by.htp.server.console;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import by.htp.common.entity.Book;
import by.htp.server.db.BookStorage;

public class ConsoleServer {

	private ServerSocket server;

	private BookStorage bs;

	public void listen(int port) throws IOException {
		server = new ServerSocket(port);
		bs = new BookStorage();
		while (true) {
			try {
				Socket client = server.accept();
				System.out.println("Client connected...");

				InputStream is = client.getInputStream();
				int bytes = is.available();
				System.out.println("bytes available: " + bytes);
				byte[] buffer = new byte[1024];
				is.read(buffer);
				String id = new String(buffer);
				System.out.println("Data recieved: " + id);

				ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
				Book book = bs.findBookById(id);
				oos.writeObject(book);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void close() throws IOException {
		server.close();
	}

}
