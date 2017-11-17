import java.util.Date;

public class Student {
	public String LastName, FirstName;
	private Date Birthday;
	
	Student(){ 
		LastName = FirstName = "NA";
		Birthday = new Date();
	};
	
	Student( String Last, String First, Date BDate)
	{
		LastName = Last;
		FirstName = First;
		Birthday = BDate;
	};

	public String FullName(){
		String Full;
		Full = FirstName + " " + LastName;
		return(Full);
	}
	
	public String toString(){
		String fullStr;
		fullStr = String.format("%s,%s,%s", LastName, FirstName, Birthday.toString());
		
		return fullStr;
	}
}
