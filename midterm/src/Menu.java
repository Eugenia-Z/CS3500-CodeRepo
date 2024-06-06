public class Menu {
  private Patient[] patients;
  private Doctor[] doctors;
  private Bill[] bills;

  public Menu() {
    this.patients = new Patient[]{};
    this.doctors = new Doctor[]{};
    this.bills = new Bill[]{};
  }

  public Menu(Patient[] patients, Doctor[] doctors, Bill[] bills) {
    this.patients = patients;
    this.doctors = doctors;
    this.bills = bills;
  }

  public Patient[] getPatients() {
    return patients;
  }

  public void setPatients(Patient[] patients) {
    this.patients = patients;
  }

  public Doctor[] getDoctors() {
    return doctors;
  }

  public void setDoctors(Doctor[] doctors) {
    this.doctors = doctors;
  }

  public void addPatient(Patient addedPatient){
    int i;
    int length = this.patients.length;
    Patient[] newPatients = new Patient[length+1];

    for (i = 0; i < length; i++)
      newPatients[i] = this.patients[i];
    newPatients[length] = addedPatient;
    this.patients = newPatients;
  }

  private int findPatient(Patient deletedPatient)
  {
    int i;
    int length = this.patients.length;
    for (i = 0; i < length; i++)
      if (this.patients[i] == deletedPatient)
        return i;
    return -1;
  }

  public void deletePatient(Patient patient){
    int position = findPatient(patient);
    int length = this.patients.length;
    Patient[] newPatients = new Patient[length-1];
    if (position == -1) {
      System.out.println("Patient not found");
    }
    int i;
    for (i = 0; i < position; i++){
      newPatients[i] = this.patients[i];
    }
    for (i = position; i <length - 1; i++){
      newPatients[i] = this.patients[i + 1];
    }
    this.patients = newPatients;
  }

  public String printBill(String patientID){
    for (Bill bill : this.bills){
      if(bill.getID() == patientID){
        return bill.toString();
      }
    }
    return "The patient does not exist";
  }
}
