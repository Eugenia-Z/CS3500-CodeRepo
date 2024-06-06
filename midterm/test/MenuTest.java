import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MenuTest {
  Menu testMenu;
  Patient patient1, patient2, patient3, patient4, patient5;
  Bill bill;

  @Before
  public void setUp() throws Exception {
    patient1 = new Patient("12345","first1", "middle1", "last1", 1, 1, 1, "first", "middle", "last", "speciality", 2, 2, 2, 3, 3, 3);
    patient2 = new Patient("12345","first2", "middle2", "last2", 1, 1, 1, "first", "middle", "last", "speciality", 2, 2, 2, 3, 3, 3);
    patient3 = new Patient("12345","first3", "middle3", "last3", 1, 1, 1, "first", "middle", "last", "speciality", 2, 2, 2, 3, 3, 3);
    patient4 = new Patient("12345","first4", "middle4", "last4", 1, 1, 1, "first", "middle", "last", "speciality", 2, 2, 2, 3, 3, 3);
    patient5 = new Patient("12345","first5", "middle5", "last5", 1, 1, 1, "first", "middle", "last", "speciality", 2, 2, 2, 3, 3, 3);
    Patient[] testPatients = new Patient[]{patient1,patient2,patient3,patient4,patient5};

    Doctor[] testDoctors = new Doctor[]{};

    bill = new Bill();
    bill.setID("12345");
    bill.setPharmacyCharges(245.50);
    bill.setRoomRent(2500);
    bill.setDoctorFee(3500.38);
    Bill[] testBills = new Bill[]{bill};

    testMenu = new Menu(testPatients,testDoctors, testBills);
  }

  @Test
  public void deletePatient() {
    this.testMenu.deletePatient(patient1);
    assertEquals(4, this.testMenu.getPatients().length);
    assertEquals(patient2, this.testMenu.getPatients()[0]);
    assertEquals(patient3, this.testMenu.getPatients()[1]);
    assertEquals(patient4, this.testMenu.getPatients()[2]);
    assertEquals(patient5, this.testMenu.getPatients()[3]);
  }

  @Test
  public void addPatient() {
    this.testMenu.addPatient(patient1);
    assertEquals(6, this.testMenu.getPatients().length);
    assertEquals(patient1, this.testMenu.getPatients()[0]);
    assertEquals(patient2, this.testMenu.getPatients()[1]);
    assertEquals(patient3, this.testMenu.getPatients()[2]);
    assertEquals(patient4, this.testMenu.getPatients()[3]);
    assertEquals(patient5, this.testMenu.getPatients()[4]);
    assertEquals(patient1, this.testMenu.getPatients()[5]);
  }

  @Test
  public void printBill() {
    double pharmacyCharges = 245.50;
    double roomRent = 2500;
    double doctorFee = 3500.38;
    String str =  String.format("%s%.2f%n%s%.2f%n%s%.2f%n%s %n%s%.2f%n",
            "Pharmacy Charges: $", pharmacyCharges,
            "Room Charges:     $", roomRent,
            "Doctor's Fees:    $", doctorFee,
            "______________________________ ",
            "Total Charges:    $",
            (pharmacyCharges + roomRent + doctorFee));
    assertEquals(str, this.testMenu.printBill("12345"));
  }
}