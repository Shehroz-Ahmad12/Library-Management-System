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

public class BookIssue implements Serializable {
	private Member member;
	private Book bookIssued;
	private Date issueDate;
	private Date returnDate;
	private double bill;
	
	public BookIssue() {
		
	}
	public BookIssue(Member member, Book bookIssued, Date issueDate) {
		super();
		this.member = member;
		this.bookIssued =bookIssued;
		this.issueDate = new Date(issueDate);
		bill=bookIssued.getCost();
	}
	

	public Date getIssueDate() {
		return issueDate;
	}


	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}


	public Date getReturnDate() {
		return returnDate;
	}

// Set Return Date base on issue Date
	public void setReturnDate(Date issueDate) {
		int day=issueDate.getDay(); 
		int month=issueDate.getMonth(); 
		int year=issueDate.getYear();
		if(month==12) {
			month=1;
			year++;
		}
		else if(day>28 && month==1) {
			month=2;
			day=28;
		}
		else if(day>30) {
			day=30;
			month++;
		}
		else {
			month++;
		}
		
		returnDate=new Date(day,month,year);
			}

//Calculate total bill to of a member by adding cost of all the books issued by him
// Bill will decrease when a person returns the book
	
	public static double calculateBill(String name) {
		double bill1=0;
		ArrayList<BookIssue> BooksIssuedList = readBookIssueData();
		for(int i=0;i<BooksIssuedList.size();i++) {
			if(BooksIssuedList.get(i).member.getName().equalsIgnoreCase(name)) {
				bill1+=BooksIssuedList.get(i).bill;
			}
		}
		writeBookIssueData(BooksIssuedList);
		return bill1;
	}	
	
	
	
	public double calculateFine(int days) {
		double fine=0;
		if (days>0) {			
		fine=10*days;}
		return fine;
		
	}
	
// Issue books and add them to issued books list
// Also setting its quantity to library if quantity is 0 then all the copies 
// of that book are already issued to members
	
	public void IssueBook() { 
	
		ArrayList<BookIssue> BooksIssuedList = readBookIssueData();
		if(this.bookIssued.getQuantity()>0) {
			Book.decreaseBookQuantity(this.bookIssued);
			BooksIssuedList.add(this);
			System.out.println("\n***BOOK ISSUED SUCCESSFULLY***\n");

		}else {
			System.out.println("*****BOOK UNAVAILABLE****");
		}
		
		writeBookIssueData(BooksIssuedList);
	}
	
	
// Return book and setting book quantity in library
	
	public static void returnBook(String book, String member, int days) {
		ArrayList<BookIssue> BooksIssuedList = readBookIssueData();
		boolean flag=false;
		for(int i=0;i<BooksIssuedList.size();i++) {
			if(BooksIssuedList.get(i).bookIssued.getTitle().equalsIgnoreCase(book) &&
				BooksIssuedList.get(i).member.getName().equalsIgnoreCase(member)) {
				
				flag=true;
				Book.increaseBookQuantity(BooksIssuedList.get(i).bookIssued);
				double fine=BooksIssuedList.get(i).calculateFine(days);
				System.out.println("Your bill is: "+BooksIssuedList.get(i).bill+" Rs");
				System.out.println("Fine is: "+fine+" Rs");
				double total=BooksIssuedList.get(i).bill+fine;
				System.out.println("Total Amount is: "+total+" Rs");
				BooksIssuedList.remove(i);
				System.out.println("\n***BOOK RETURNED SUCCESSFULY***\n");
			}
		}
		if(flag==false) {
			System.out.println("\n***NO RECORD FOUND***\n");
		}
		writeBookIssueData(BooksIssuedList);	
	}
	
	
	// Display books all the books issued to members
	
	public static void displayBooksIssued() {
		boolean flag=false;
		ArrayList<BookIssue> BooksIssuedList = readBookIssueData();
		for(int i=0;i<BooksIssuedList.size();i++) {
			flag=true;
			System.out.print("\n\nBook Name: "+BooksIssuedList.get(i).bookIssued.getTitle());
			System.out.println("\tIssued By: "+BooksIssuedList.get(i).member.getName());				
			System.out.print("Issued Date: ");
			BooksIssuedList.get(i).issueDate.display();
			System.out.print("\t\tReturn Date: ");
			BooksIssuedList.get(i).returnDate.display();
		}
		if(flag==false) {
			System.out.println("***NO RECORD FOUND***");
		}
		writeBookIssueData(BooksIssuedList);	
	}	
	
	
	
// Read Issued Books Data from file
	
public static ArrayList<BookIssue> readBookIssueData(){
	ArrayList<BookIssue> BooksIssuedList = new ArrayList<BookIssue>(0);
	ObjectInputStream inputStream = null;
	try
	{
	inputStream = new ObjectInputStream(new FileInputStream("BooksIssued.ser"));
		boolean EOF = false;
		while(!EOF) {
			try {
				BookIssue myObj = (BookIssue) inputStream.readObject();
				BooksIssuedList.add(myObj);
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
            
	return BooksIssuedList;
}

// Write Issued books Data to file
public static void writeBookIssueData(ArrayList<BookIssue> BooksIssuedList) {
ObjectOutputStream outputStream = null; 

try {
	
	outputStream = new ObjectOutputStream(new FileOutputStream("BooksIssued.ser"));
	
	for(int i = 0 ; i < BooksIssuedList.size() ; i++) {
		outputStream.writeObject(BooksIssuedList.get(i));
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
}}
	
	

}
