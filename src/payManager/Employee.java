package payManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class Employee implements Payable {
    private ArrayList<String> payRecords = new ArrayList<String>();
    private ArrayList<Float> allPays = new ArrayList<Float>();
    private int ID;
    private String firstName, lastName, address, phone, email, payResults, payRecordsString;
    private float pay, expenses;
    private LocalDate date;
    private boolean dateInvalid;
    
    public Employee(int id, String fN, String lN, String a, String p, String e){
        this.ID = id;
        this.firstName = fN;
        this.lastName = lN;
        this.address = a;
        this.phone = p;
        this.email = e;
    }

    public String getAllEmployeeData(){
        return ("\nID: " + this.ID + " First name: " + this.firstName + " Last name: " + this.lastName + " Address: " + address + " Phone: " + phone + " Email: " + email);
    }

    public String getEmployeeData(){
        return ("\nID: " + this.ID + " Name: " + this.firstName + " " + this.lastName);
    }

    public String givePay(float h, float s) {
        Pay employeePay = new Pay(h, s);

        if (payRecords.size() > 0){
            date = employeePay.getBillingDate();
            dateValidation();

            while (dateInvalid){
                date = employeePay.getBillingDate();
                dateValidation();
            }
        }else {
            date = employeePay.getBillingDate();
        }

        this.pay = employeePay.getPayAmount();
        allPays.add(this.pay);

        payResults = "\nBilling date: " + date + " Gross pay: $" + this.pay;
        payRecords.add(payResults);

        return (payResults);
    }

    public ArrayList<String> getPayRecordsList(){
        return payRecords;
    }

    public ArrayList<Float> getAllPaysList(){
        return allPays;
    }

    public String getEmployeePays(){
        for (String aPayRecord : payRecords){
            payRecordsString = (payRecordsString + "\n" + aPayRecord);
        }

        return payRecordsString;
    }

    @Override
    public float getExpenses(){
        for (Float aPay : allPays){
            this.expenses = this.expenses + aPay;
        }

        return this.expenses;
    }

    public void dateValidation(){
        int i = 0;
        for (String aPayRecord : payRecords){
            String[] words = aPayRecord.split(" ");

            String payRecordDate = words[2];
            String dateString = date.toString();

            if (payRecordDate.equals(dateString)){
                dateInvalid = true;
                System.out.println("\nInvalid date, employee already has a pay on this date.");
                break;
            }else {
                i++;
            }
        }
        if (i == payRecords.size()){
            dateInvalid = false;
        }
    }
}
