public class Person
{
    private String firstName; 	//store the first name
    private String lastName;  	//store the last name
    private String middleName;  //store the middle name

        //Default constructor;
        //Initialize firstName, middleName, and lastName to empty string
        //Postcondition: firstName = ""; middleName = ""; lastName = "";
    public Person()
    {
        firstName = "";
        middleName = "";
        lastName = "";
    }

      //Constructor with parameters
      //Set firstName, middleName, and lastName according to the parameters
      //Postcondition: firstName = first; middleName = middle; lastName = last;
    public Person(String first, String middle, String last)
    {
        firstName = first;
        middleName = middle;
        lastName = last;
    }

        //copy constructor
    public Person(Person other)
    {
        firstName = new String(other.firstName);
        middleName = new String(other.middleName);
        lastName = new String(other.lastName);
    }

        //Method to output the first name, middle name, and last name
        //in the form firstName middleName lastName
    public String toString()
    {
          return (firstName + " " + middleName + " " + lastName);
    }

          //Method to set firstName, middle name, and lastName according to
          //the parameters
          //Postcondition: firstName = first; middleName = middle;
          //               lastName = last;
    public void setName(String first, String middle, String last)
    {
        firstName = first;
        middleName = middle;
        lastName = last;
    }

    public void setFirstName(String first)
    {
        firstName = first;
    }

    public void setMiddleName(String middle)
    {
        middleName = middle;
    }

    public void setLastName(String last)
    {
          lastName = last;
    }

        //Method to return firstName
        //Postcondition: the value of firstName is returned
    public String getFirstName()
    {
        return firstName;
    }

          //Method to return  middleName
        //Postcondition: the value of middleName is returned
    public String getMiddleName()
    {
        return middleName;
    }

        //Method to return the lastName
        //Postcondition: the value of lastName is returned
    public String getLastName()
    {
        return lastName;
    }

    boolean isLastName(String last)
    {
        return (lastName.equals(last));
    }

    boolean isFirstName(String first)
    {
        return (firstName.equals(first));
    }

    boolean isMiddleName(String middle)
    {
        return (middleName.equals(middle));
    }

    public void makeCopy(Person other)
    {
         firstName = other.firstName;
         middleName = other.middleName;
         lastName = other.lastName;
    }

        //Method to return a copy of time
        //Postcondition: A copy of the object is created
        //               and a reference of the copy is returned
    public Person getCopy()
    {
        Person temp = new Person();

        temp.firstName = firstName;
        temp.middleName = middleName;
        temp.lastName = lastName;

        return temp;
    }

    public boolean equals(Person other)
    {
         return (other.firstName.equals(firstName)
                && other.middleName.equals(middleName)
                && other.lastName.equals(lastName));
    }
}