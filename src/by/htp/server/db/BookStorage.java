package by.htp.server.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import by.htp.common.entity.Book;

public class BookStorage {

	public static final String FILE_BOOK = "resources/books.txt";
	public static final String DELIMETER = "/";
	public static final String UNKNOWN_BOOK_NAME = "unknown";

	public Book findBookById(String stringId) {
		int id = Integer.valueOf(stringId.trim());
		List<Book> allBooks = getAllBooks();

		for (Book elem : allBooks) {
			if (id == elem.getId()) {
				return elem;
			}
		}
		return new Book(0, UNKNOWN_BOOK_NAME);
	}

	private List<Book> getAllBooks() {
		List<String> allBooksAsString = readLines(FILE_BOOK);
		List<Book> allBooks = new ArrayList<>();

		for (String elem : allBooksAsString) {
			String[] fields = elem.split(DELIMETER);
			Book book = new Book(Integer.parseInt(fields[0]), fields[1]);
			allBooks.add(book);
		}
		return allBooks;
	}

	private static List<String> readLines(String fileName) {
		List<String> contents = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {

			String line = reader.readLine();

			while (line != null) {
				contents.add(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contents;
	}

}
