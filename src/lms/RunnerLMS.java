package lms;
import java.util.Scanner;
public class RunnerLMS {

	public static void main(String[] args) {
		Scanner input =new Scanner(System.in);
		int option=0;
		
		do {
			System.out.println("\n**************MAIN MENU***********\n");
			System.out.println("Press 1 to Enter Books Menu ");
			System.out.println("Press 2 to Enter Members Menu");
			System.out.println("Press 3 to Enter Books Issue Menu");
			System.out.println("Press 4 to See Credits");
			System.out.println("Press 5 to Quit");
			option=input.nextInt();
			
			switch(option) {
			case 1:
				int booksOption=0;
				String bookname;
				do {
				System.out.println("\n************BOOKS MENU************\n");
				System.out.println("Press 1 to Add Book ");
				System.out.println("Press 2 to Delete Book ");
				System.out.println("Press 3 to Search Book by Name ");
				System.out.println("Press 4 to Search Book by Genre");
				System.out.println("Press 5 to Display all Books ");
				System.out.println("Press 6 to go back Main Menu");
				booksOption=input.nextInt();
				switch(booksOption)	{
				
				case 1:
					String ID;
					String title;
					String author;
					String genre;
					int quantity;
					double cost;
					input.nextLine();   	// This statement is to move to next line after taking int 
											// input so the program won't take two inputs 
					System.out.println("\n******ADD BOOK MENU******\n");
					System.out.println("Enter Book ID: ");
					ID=input.nextLine();
					//System.out.println("*******Add Book Menu****");
					System.out.println("Enter Book Name: ");
					title=input.nextLine();
					System.out.println("Enter Author Name: ");
					author=input.nextLine();
					System.out.println("Enter Genre: ");
					genre=input.nextLine();
					System.out.println("Enter Quantity(int): ");
					quantity=input.nextInt();
					System.out.println("Enter Issue Cost(double): ");
					cost=input.nextDouble();
					Book b1 = new Book(ID,title,author,genre,quantity,cost);
					b1.display();
					b1.addBook();
					break;
				case 2:
					input.nextLine();
					System.out.println("\n******BOOK DELETION******\n");
					System.out.println("Enter Book Name to delete: ");
					bookname=input.nextLine();
					Book.deleteBook(bookname);
					break;
				case 3:
					System.out.println("\n******BOOK SEARCH******\n");
					System.out.println("Enter Book Name to Search: ");
					input.nextLine();
					bookname=input.nextLine();
					Book bw=Book.searchBook(bookname);
					if(bw!=null) {
						System.out.println("\n");
						bw.display();
						System.out.println("\n");
					}
					else {
						System.out.println("\n***BOOK DOES NOT FOUND***\n");
					}					
					break;
				case 4:
					input.nextLine();
					System.out.println("\n******BOOK SEARCH******\n");
					System.out.println("Enter Genre to Search: ");
					genre=input.nextLine();
					Book.searchBookByGenre(genre);
					System.out.println("\n");
					break;
				case 5:
					System.out.println("\n***BOOKS***\n");
					Book.displayAllBooks();
					System.out.println("\n");
					break;
				case 6:
					System.out.println("\n***GOING BACK***\n");
					break;
				
				default:
					System.out.println("Wrong Option!!!");
					
				}
				}while(booksOption!=6);
			break;
								
			case 2:
				int membersOption=0;
				String memberName;
				do {
				System.out.println("\n************MEMBER MENU************\n");
				System.out.println("Press 1 to Add Member ");
				System.out.println("Press 2 to Delete Member ");
				System.out.println("Press 3 to Search Member ");
				System.out.println("Press 4 to Check Membership of a Member");
				System.out.println("Press 5 to Display All Members ");
				System.out.println("Press 6 to go back Main Menu ");
				membersOption=input.nextInt();
				
				switch(membersOption) {
				case 1:			
					input.nextLine();

					System.out.println("\n******ADD MEMBER******\n");
					String memberID,name,cnic,phoneNO,email;
					Address address;
					int houseNo,StreetNo;
					String sector,city,country;
					Date startDate;
					int day, month,year;
					System.out.println("Enter Member ID: ");
					memberID=input.nextLine();
					System.out.println("Enter Member Name: ");
					name=input.nextLine();
					System.out.println("Enter Cnic: ");
					cnic=input.nextLine();
					System.out.println("Enter Phone No: ");
					phoneNO=input.nextLine();
					System.out.println("Enter Email: ");
					email=input.nextLine();
					System.out.println("\n***Address Details***\n");
					System.out.println("Enter House no(int): ");
					houseNo=input.nextInt();
					System.out.println("Enter Street no(int): ");
					StreetNo=input.nextInt();
					input.nextLine();
					System.out.println("Enter Sector : ");
					sector=input.nextLine();
					System.out.println("Enter City: ");
					city=input.nextLine();
					System.out.println("Enter Country: ");
					country=input.nextLine();
					System.out.println("\n***Date Details***\n");
					System.out.println("Enter Day(int): ");
					day=input.nextInt();
					System.out.println("Enter Month(int): ");
					month=input.nextInt();
					System.out.println("Enter Year(int): ");
					year=input.nextInt();
					startDate=new Date(day,month,year);
					address=new Address(houseNo,StreetNo,sector,city,country);
					Member m1=new Member(name,cnic,email,phoneNO,address,memberID,startDate);
					m1.addMember();
					break;
				case 2:
					input.nextLine();

					System.out.println("\n******DELETE MEMBER******\n");
					System.out.println("Enter member name");
					memberName=input.nextLine();
					Member.deleteMember(memberName);
					System.out.println("\n");
					break;
				case 3:
					input.nextLine();

					System.out.println("\n******SEARCH MEMBER******\n");
					System.out.println("Enter member name: ");
					memberName=input.nextLine();
					Member mw;
					mw=Member.searchMember(memberName);
					if (mw!=null) {
						System.out.println("\n");
						mw.display();
						System.out.println("\n");
					}
					else {
						System.out.println("\n***MEMBER NOT FOUND***\n");
					}
					
					break;
				case 4: 
					input.nextLine();
					System.out.println("\n******CHECK MEMBERSHIP DATE******\n");
					System.out.println("Enter member name: ");
					memberName=input.nextLine();
					Member.checkMembershipDate(memberName);
					System.out.println("\n");
					break;
				case 5:
					System.out.println("\n***MEMBERS***\n");
					Member.displayAllMembers();
					System.out.println("\n");
					break;
				case 6:
					System.out.println("\n***GOING BACK***\n");
					break;
				default:
					System.out.println("Wrong option");
					
				}
			}while(membersOption!=6);
			break;
			case 3:
				String issueBookName;
				String memberName1;
				int bookIssueOption=0;
				do {
				System.out.println("\n************BOOK ISSUE MENU************\n");
				System.out.println("Press 1 to Issue Book ");
				System.out.println("Press 2 to Return Book ");
				System.out.println("Press 3 to Calculate Bill ");
				System.out.println("Press 4 to Display all Books Issued to Members ");
				System.out.println("Press 5 to go back Main Menu ");
				bookIssueOption=input.nextInt();
				switch(bookIssueOption) {
				case 1:
					input.nextLine();
					System.out.println("\n******BOOK ISSUE OPTION******\n");
					System.out.println("Enter book name: ");
					issueBookName=input.nextLine();
					System.out.println("Enter Member name: ");
					memberName1=input.nextLine();
					Book book1= Book.searchBook(issueBookName);
					Member member1=Member.searchMember(memberName1);
					if (book1!=null && member1!=null) {
						System.out.println("\n***ISSUE DATE DETAILS***\n");
						System.out.println("Enter Day: ");
						int day=input.nextInt();
						System.out.println("Enter Month: ");
						int month=input.nextInt();
						System.out.println("Enter Year: ");
						int year=input.nextInt();
						Date stDate=new Date(day,month,year);
					
						BookIssue bi=new BookIssue(member1,book1,stDate);
						bi.setReturnDate(stDate);
						bi.IssueBook();}
					else {
						System.out.println("***BOOK OR MEMBER NOT FOUND***");
					}
					break;
				case 2: 
					input.nextLine();
					System.out.println("\n******BOOK RETURN******\n");
					System.out.println("Enter book name: ");
					issueBookName=input.nextLine();
					System.out.println("Enter member name: ");
					memberName1=input.nextLine();
					System.out.println("Enter number of days exceeding return date: ");
					int days=input.nextInt();		
					BookIssue.returnBook(issueBookName,memberName1,days);
					break;
				case 3: 
					input.nextLine();
					System.out.println("\n******CALCULATE BILL******\n");
					System.out.println("Enter Member name: ");
					memberName1=input.nextLine();
					double bill=BookIssue.calculateBill(memberName1);
					System.out.println("\nBill is: "+ bill+" Rs");
					break;
				case 4:
					System.out.println("\n***ISSUED BOOKS***\n");
					BookIssue.displayBooksIssued();
					System.out.println("\n");
					break;
				case 5:
					System.out.println("\n***GOING BACK***\n");
					break;
				default: 
					System.out.println("Wrong option");
				
				}
				
				
				}while(bookIssueOption!=5);
				break;
				
			case 4:
				System.out.println("\n******CREDITS******\n");
				System.out.println("Shehroz Ahmad");
				System.out.println("SP19-BCS-032");
				System.out.println("Library Management System");
				System.out.println("Object Oriented Programming");
				break;
			case 5:
				System.out.println("***Quitting***");
			default:
				System.out.println("Wrong Option!!!");
				break;
			}}while(option!=5);
		
		
		
}
}