package by.htp.common.entity;

import java.io.Serializable;

public class Book implements Serializable {

	private int id;
	private String title;

	public Book() {

	}

	public Book(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book: " + id + ", title: " + title;
	}

}
