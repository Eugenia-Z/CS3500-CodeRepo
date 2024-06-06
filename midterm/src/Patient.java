
public class Patient extends Person
{
    private String ID;
    private Date dateOfBirth;
    private Doctor attendingPhysicain;
    private Date admitDate;
    private Date dischargeDate;


    public Patient()
    {
        super("", "","");
        ID = "";

        dateOfBirth = new Date(1, 1, 1900);
        attendingPhysicain = new Doctor();
        admitDate = new Date(1, 1, 1900);
        dischargeDate = new Date(1, 1, 1900);
    }

    public Patient(String id, String fName, String mName, String lName,
                int bDay, int bMth, int bYear,
                String docFrName, String docMiName, String docLaName, String docSpl,
                int admDay, int admMth, int admYear,
                int disChDay, int disChMth, int disChYear)
    {
        super(fName, mName, lName);
        ID = id;
        dateOfBirth = new Date(bDay, bMth, bYear);
        attendingPhysicain = new Doctor(docFrName, docMiName, docLaName,docSpl);
        admitDate = new Date(admDay, admMth, admYear);
        dischargeDate = new Date(disChDay, disChMth, disChYear);
    }

    public void setInfo(String id, String fName, String mName, String lName,
                 int bDay, int bMth, int bYear,
                 String docFrName, String docMiName, String docLaName, String docSpl,
                 int admDay, int admMth, int admYear,
                 int disChDay, int disChMth, int disChYear)
    {
        ID = id;
        setName(fName,mName, lName);
        dateOfBirth.setDate(bDay, bMth, bYear);
        attendingPhysicain.setName(docFrName, docMiName, docLaName);
        attendingPhysicain.setSpeciality(docSpl);
        admitDate.setDate(admDay, admMth, admYear);
        dischargeDate.setDate(disChDay, disChMth, disChYear);
    }

    public void setID(String id)
    {
        ID = id;
    }

    public String getID()
    {
        return ID;
    }

    public void setBirthDate(int bDay, int bMth, int bYear)
    {
        dateOfBirth.setDate(bDay, bMth, bYear);
    }

    public int getBirthDay()
    {
        return dateOfBirth.getDay();
    }

    public  int getBirthMonth()
    {
        return dateOfBirth.getMonth();
    }

    public int getBirthYear()
    {
        return dateOfBirth.getYear();
    }

    public void setDoctorName(String fName, String lName, String mName)
    {
        attendingPhysicain.setName(fName,mName, lName);
    }

    public void setDoctorSpl(String spl)
    {
        attendingPhysicain.setSpeciality(spl);
    }

    public String getDoctorFName()
    {
        return attendingPhysicain.getFirstName();
    }

    public String getDoctorLName()
    {
        return attendingPhysicain.getLastName();
    }

    public String getDoctorSpl()
    {
        return attendingPhysicain.getSpeciality();
    }

    public void setAdmDate(int admDay, int admMth, int admYear)
    {
        admitDate.setDate(admDay, admMth, admYear);
    }

    public int getAdmDay()
    {
        return admitDate.getDay();
    }

    public int getAdmMonth()
    {
        return admitDate.getMonth();
    }

    public int getAdmYear()
    {
        return admitDate.getYear();
    }

    public void setDisDate(int disDay, int disMth, int disYear)
    {
        dischargeDate.setDate(disDay, disMth, disYear);
    }

    public int getDisDay()
    {
        return dischargeDate.getDay();
    }

    public int getDisMonth()
    {
        return dischargeDate.getMonth();
    }

    public int getDisYear()
    {
        return dischargeDate.getYear();
    }

    public String toString()
    {
        return "Patient: " + super.toString() + "\n"
               + "Attending Physician: " + attendingPhysicain.toString()
               + "\n" +"Admit Date: " + admitDate.toString()
               + "\n" + "Discharge Date: " +  dischargeDate.toString()
               + "\n\n";
    }
}
