
import java.util.*;

public class HospitalMain
{
  static Scanner console = new Scanner(System.in);

  public static void main(String[] args)
  {
    Patient newPatient = new Patient();
    Bill bill = new Bill();

    String str1, str2, str3;

    System.out.print("Enter patient's ID: ");
    str1 = console.next();
    System.out.println();

    newPatient.setID(str1);
    bill.setID(str1);

    System.out.print("Enter patient's first name: ");
    str1 = console.next();
    System.out.println();

    System.out.print("Enter patient's middle name: ");
    str2 = console.next();
    System.out.println();

    System.out.print("Enter patient's last name: ");
    str3 = console.next();
    System.out.println();

    newPatient.setName(str1,str2,str3);

    System.out.print("Enter doctor's first name: ");
    str1 = console.next();
    System.out.println();

    System.out.print("Enter doctor's middle name: ");
    str2 = console.next();
    System.out.println();
    console.nextLine();

    System.out.print("Enter doctor's last name: ");
    str3 = console.next();
    System.out.println();
    console.nextLine();

    newPatient.setDoctorName(str1, str2, str3);

    System.out.print("Enter doctor's speciality: ");
    str1 = console.nextLine();
    System.out.println();

    newPatient.setDoctorSpl(str1);

    newPatient.setAdmDate(4, 15, 2009);
    newPatient.setDisDate(4, 21, 2009);

    bill.setPharmacyCharges(245.50);
    bill.setRoomRent(2500);
    bill.setDoctorFee(3500.38);

    System.out.println(newPatient);
    System.out.println(bill);
  }
}
