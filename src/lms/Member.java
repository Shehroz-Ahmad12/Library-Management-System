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

public class Member extends Person implements Serializable {
private String memberID;
private Date startDate;

public Member() {
	
}
public Member(String name, String cnic, String email, String phoneNo, Address address, String memberID, Date startDate) {
	super( name, cnic,  email, phoneNo,  address);
	this.memberID = memberID;
	this.startDate =new Date( startDate);
}


public String getMemberID() {
	return memberID;
}

public void setMemberID(String memberID) {
	this.memberID = memberID;
}

public Date getStartDate() {
	return startDate;
}

public void setStartDate(Date startDate) {
	this.startDate = startDate;
}

// display member details
public void display() {
	System.out.println("***MEMBER DETAILS***");
	System.out.println("Member ID: "+this.memberID);
	System.out.println("Member Name: "+this.getName());
	System.out.println("CNIC: "+this.getCnic());
	System.out.println("Email: "+this.getEmail());
	System.out.println("Phone No.: "+this.getPhoneNo());
	System.out.println("\nAddress Information: ");
	this.getAddress().display();
	System.out.println("Starting Date of Membership: ");
	startDate.display();
}

// check membership date of member and also end date of membership after 5 years

public static void checkMembershipDate(String name) {
	boolean flag=false;
	ArrayList<Member> MemberList = readMemberData();
	Date startDate=null;	
	for(int i=0;i<MemberList.size();i++) {
		if(MemberList.get(i).getName().equalsIgnoreCase(name)) {
			flag=true;
			startDate=new Date(MemberList.get(i).startDate);
		}
	}
	if(flag==false) {
		System.out.println("\n***MEMBER DOES NOT EXIST***\n");
	}
	if(startDate!=null) {
		System.out.print("\nStarting date: ");
		startDate.display();
		System.out.print("\tEnding Date: ");
		Date endDate=new Date(startDate);
		endDate.setYear(startDate.getYear()+5);
		endDate.display();
		}
}

public static void displayAllMembers() {
	ArrayList<Member> MemberList = readMemberData();
	boolean flag=false;
	for(int i=0;i<MemberList.size();i++) {
		flag=true;
		System.out.print("\n\nMember ID: "+MemberList.get(i).getMemberID());
		System.out.print("\tMember name: "+MemberList.get(i).getName());
		Member.checkMembershipDate(MemberList.get(i).getName());
		
	}
	if(flag==false) {
		System.out.println("\n***NO RECORD FOUND***\n");

	}
	writeMemberData(MemberList);
}


// Add member to a file

public void addMember() {  
		ArrayList<Member> MemberList = readMemberData();
		MemberList.add(this);
		writeMemberData(MemberList);
		System.out.println("\n***MEMBER ADDED SUCCESSFULLY***\n");

		
}

// read from file
public static ArrayList<Member> readMemberData(){
	ArrayList<Member> MemberList = new ArrayList<Member>(0);
	ObjectInputStream inputStream = null;
	try
	{
	inputStream = new ObjectInputStream(new FileInputStream("Members.ser"));
		boolean EOF = false;
		while(!EOF) {
			try {
				Member myObj = (Member) inputStream.readObject();
				MemberList.add(myObj);
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
            
	return MemberList;
}

//write to file
public static void writeMemberData(ArrayList<Member> MemberList) {
	ObjectOutputStream outputStream = null; 

	try {
		
		outputStream = new ObjectOutputStream(new FileOutputStream("Members.ser"));
		
		for(int i = 0 ; i < MemberList.size() ; i++) {
			outputStream.writeObject(MemberList.get(i));
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

// search and return member from file

public static Member searchMember(String name) {
	Member searchedMember=null;
	 
		ArrayList<Member> MemberList = readMemberData();
		
		for(int i = 0 ; i < MemberList.size() ; i++) {
			if(MemberList.get(i).getName().equalsIgnoreCase(name) ||
					MemberList.get(i).getName().contains(name)) {
				searchedMember=MemberList.get(i);
			}
		}		
		writeMemberData(MemberList);

		return searchedMember;
}

// Delete member from file

public static void deleteMember(String name) {	 
	ArrayList<Member> MemberList = readMemberData();
	boolean flag=false;
	
	for(int i = 0 ; i < MemberList.size() ; i++) {
		if(MemberList.get(i).getName().equalsIgnoreCase(name)) {
			MemberList.remove(i);
			flag=true;
			System.out.println("\n***MEMBER DELETED SUCCESSFULLY***\n");
		}
	}
	if (flag==false) {
		System.out.println("\n***MEMBER COULD NOT BE FOUND***\n");

	}
	writeMemberData(MemberList);
}

}
