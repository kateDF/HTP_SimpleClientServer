package by.htp.client.console;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import by.htp.common.entity.Book;

public class ConsoleClient {

	private String title;
	private String type;
	private Socket socket;

	public void connect(String host, int port) throws FailedConnectionException {

		try {
			socket = new Socket(host, port);
		} catch (IOException e) {
			throw new FailedConnectionException("Cannot conect", e);
		}
	}

	public void disconnect() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Book getBook(int id) {
		sendBookRequest(id);
		Book book = recieveBookResponse();
		book.setId(id);
		return book;
	}

	private void sendBookRequest(int id) {
		try {
			OutputStream os = socket.getOutputStream();
			os.write((id + "").getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Book recieveBookResponse() {
		Book book = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			book = (Book) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return book;
	}

}
