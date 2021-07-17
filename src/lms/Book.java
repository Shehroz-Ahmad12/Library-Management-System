package lms;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Book implements Serializable {
	private String bookID;
	private String title;
	private String author;
	private String genre;
	private int quantity;
	private double cost;
	
	public Book() {
		
	}
	
	public Book(String bookID, String title, String author, String genre, int quantity, double cost) {
		this.bookID = bookID;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.quantity = quantity;
		this.cost=cost;
	}
	
	public Book(Book b) {
		this.bookID=b.bookID;
		this.title=b.title;
		this.author=b.author;
		this.genre=b.genre;
		this.quantity=b.quantity;
		this.cost=b.cost;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void display() {
		System.out.println("***BOOK DETAILS");
		System.out.println("Book ID: "+this.bookID);
		System.out.println("Book Name: "+this.title);
		System.out.println("Author Name: "+this.author);
		System.out.println("Genre : "+this.genre);
		System.out.println("Available Books: "+this.quantity);
		System.out.println("Issue Cost: "+this.cost);
	}
	
public static void displayAllBooks() {	
	ArrayList<Book> BookList = readBookData();
	boolean flag=false;
	for(int i=0;i<BookList.size();i++) {
		flag=true;
		System.out.print("\nID: "+BookList.get(i).getBookID()+"\t");
		System.out.print("\tBook name: "+BookList.get(i).getTitle()+"\t");
		System.out.print("Author: "+BookList.get(i).getAuthor()+"\t");
		System.out.print("Genre: "+BookList.get(i).getGenre());
	}
	if(flag==false) {
		System.out.println("\n***NO RECORD FOUND***\n");
	}
	writeBookData(BookList);
}

// Adding book in file
	
	public void addBook() { 
			ArrayList<Book> BookList = readBookData();
			BookList.add(this);
			writeBookData(BookList);
			System.out.println("\n***BOOK ADDED SUCCESSFULLY***\n");

		}
	
// Reading data from file
	public static ArrayList<Book> readBookData(){
		ArrayList<Book> BookList = new ArrayList<Book>(0);
		ObjectInputStream inputStream = null;
		try
		{
		inputStream = new ObjectInputStream(new FileInputStream("Books.ser"));
			boolean EOF = false;
			while(!EOF) {
				try {
					Book myObj = (Book) inputStream.readObject();
					BookList.add(myObj);
					//System.out.println("Read: " + myObj.getName());
				} catch (ClassNotFoundException e) {
					//System.out.println("Class not found");
				} catch (EOFException end) {
					EOF = true;
				}
                        }
                }
		 catch(FileNotFoundException e) {
			//System.out.println("Cannot find file");
		} catch (IOException e) {
			//System.out.println("IO Exception while opening stream");
			//e.printStackTrace();
		} finally { 
			try {
				if(inputStream != null)
					inputStream.close( );
			} catch (IOException e) {
				System.out.println("IO Exception while closing file");
			}
		}
                
		return BookList;
	}
	
	
//Writing data to file

public static void writeBookData(ArrayList<Book> BookList) {
	ObjectOutputStream outputStream = null; 

	try {
		
		outputStream = new ObjectOutputStream(new FileOutputStream("Books.ser"));
		
		for(int i = 0 ; i < BookList.size() ; i++) {
			outputStream.writeObject(BookList.get(i));
		}
	} catch(IOException e) {
		System.out.println("IO Exception while opening file");
	} finally { 
		try {
			if(outputStream != null) {
				outputStream.close();								
			}

		} catch (IOException e) {
			System.out.println("IO Exception while closing file");
		}
	}
}

// Searching book from file

public static Book searchBook(String name) {
	Book searchedBook=null;
	 
		ArrayList<Book> BookList = readBookData();
		for(int i = 0 ; i < BookList.size(); i++) {
			if(BookList.get(i).getTitle().equalsIgnoreCase(name) || BookList.get(i).getTitle().contains(name)) {
				searchedBook=BookList.get(i);
			}
		}
		writeBookData(BookList);

		return searchedBook;
		
}

// Searching a books of specific genre

public static void searchBookByGenre(String genre) {
	boolean flag=false;
	ArrayList<Book> BookList = readBookData();
	for(int i = 0 ; i < BookList.size(); i++) {
		if(BookList.get(i).getGenre().equalsIgnoreCase(genre)) {
			flag=true;
			System.out.print("\nBook name: "+BookList.get(i).getTitle());
			System.out.print("\tAuthor: "+BookList.get(i).getAuthor());
		}
	}
	if (flag==false) {
		System.out.println("\n***NO BOOK IN THIS CATEGORY***\n");

	}
	writeBookData(BookList);
	
}

// Deleting book from file
public static void deleteBook(String name) {	 
	ArrayList<Book> BookList = readBookData();
	boolean flag=false;
	for(int i = 0 ; i < BookList.size() ; i++) {
		if(BookList.get(i).getTitle().equalsIgnoreCase(name)) {
			BookList.remove(i);
			flag=true;
			System.out.println("\n***BOOK DELETED SUCCESSFULLY***\n");
		}
	}
	if (flag==false) {
		System.out.println("\n***BOOK DOES NOT FOUND***\n");
	}
	writeBookData(BookList);
}
	

// After returning book setting book quantity
	public static void increaseBookQuantity(Book b) {
		ArrayList<Book> BookList = readBookData();
		for(int i = 0 ; i < BookList.size() ; i++) {
			if(BookList.get(i).getTitle().equalsIgnoreCase(b.getTitle())) {
				BookList.get(i).setQuantity(BookList.get(i).getQuantity()+1);
			}
		}
		writeBookData(BookList);
	}
	
// Setting book quantity after issuing
	public static void decreaseBookQuantity(Book b) {
		ArrayList<Book> BookList = readBookData();
		for(int i = 0 ; i < BookList.size() ; i++) {
			if(BookList.get(i).getTitle().equalsIgnoreCase(b.getTitle())) {
				BookList.get(i).setQuantity(BookList.get(i).getQuantity()-1);
			}
		}
		writeBookData(BookList);
	}
}
