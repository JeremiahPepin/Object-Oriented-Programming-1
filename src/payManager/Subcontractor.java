package payManager;

import java.util.ArrayList;
import java.time.LocalDate;

public class Subcontractor implements Payable {
    private ArrayList<String> billRecords = new ArrayList<String>();
    private ArrayList<Float> allBills = new ArrayList<Float>();
    private int ID;
    private String name, description, address, phone, email, billRecordsString;
    private float amount, expenses;
    private LocalDate date;
    private boolean dateInvalid;
    
    public Subcontractor(int id, String n, String d, String a, String p, String e){
        this.ID = id;
        this.name = n;
        this.description = d;
        this.address = a;
        this.phone = p;
        this.email = e;
    }

    public String getAllSubcontractorData(){
        return ("\nID: " + this.ID + " Name: " + this.name + " Description: " + this.description + " Address: " + address + " Phone: " + phone + " Email: " + email);
    }

    public String getSubcontractorData(){
        return ("\nID: " + this.ID + " Name: " + this.name + " Description: " + this.description);
    }

    public String giveBill(float a){
        this.amount = a;
        Bill subcontractorBill = new Bill(amount);

        if (billRecords.size() > 0){
            date = subcontractorBill.getBillingDate();
            dateValidation();

            while (dateInvalid){
                date = subcontractorBill.getBillingDate();
                dateValidation();
            }
        }else {
            date = subcontractorBill.getBillingDate();
        }

        allBills.add(this.amount);

        float billAmount = subcontractorBill.getBill();
        String bill = "\nBilling date: " + date + " Amount: "+ billAmount;
        billRecords.add(bill);
        
        return bill;
    }

    public String getSubcontractorBills(){
        for (String aBillRecord : billRecords){
            billRecordsString = (billRecordsString + "\n" + aBillRecord);
        }

        return billRecordsString;
    }


    @Override
    public float getExpenses(){
        for (Float aBill : allBills){
            this.expenses = this.expenses + aBill;
        }

        return this.expenses;
    }

    public void dateValidation(){
        int i = 0;

        for (String aBillRecord : billRecords){
            String[] words = aBillRecord.split(" ");

            String billRecordDate = words[2];
            String dateString = date.toString();

            if (billRecordDate.equals(dateString)){
                dateInvalid = true;
                System.out.println("\nInvalid date, employee already has a pay on this date.");
                break;
            }else {
                i++;
            }
        }

        if (i == billRecords.size()){
            dateInvalid = false;
        }
    }
}
