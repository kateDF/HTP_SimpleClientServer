package by.htp.client.run;

import by.htp.client.console.ConsoleClient;
import by.htp.client.console.FailedConnectionException;
import by.htp.common.entity.Book;

public class MainClient {

	public static void main(String[] args) {
		ConsoleClient client = new ConsoleClient();
		try {
			client.connect("localhost", 9595);

			Book book = client.getBook(3);
			System.out.println(book);

			client.disconnect();

		} catch (FailedConnectionException e) {
			System.out.println("Sorry, you cannot connect to server");
			e.printStackTrace();
		}

	}

}
