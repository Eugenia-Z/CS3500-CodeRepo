
public class Doctor extends Person
{
    private String speciality;;

    public Doctor()
    {
        speciality = "";
    }

    public Doctor(String first, String middle, String last, String spl)
    {
        super(first,middle, last);
        speciality = spl;
    }

    public void setSpeciality(String spl)
    {
        speciality = spl;
    }

    public String getSpeciality()
    {
        return speciality;
    }

    public String toString()
    {
        return super.toString() + " " + speciality;
    }
}
