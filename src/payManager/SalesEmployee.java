package payManager;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class SalesEmployee extends Employee{
    private Scanner myScanner = new Scanner(System.in);
    private float commissionRate, sales;
    private String payResults;
    private float payAmount, salesAmount; 

    public SalesEmployee(int id, String fN, String lN, String a, String p, String e, float cR){
        super(id, fN, lN, a, p, e);
        this.commissionRate = cR;
    }

    @Override
    public String givePay(float h, float s){     
        System.out.print("\nSales: ");
        sales = myScanner.nextFloat();

        Pay employeePay = new Pay(h, s);
        employeePay.saleEmployeePay(this.commissionRate, sales);
        LocalDate date = employeePay.getBillingDate();
        this.payAmount = employeePay.getPayAmount();
        this.salesAmount = employeePay.getSalesAmount();

        float totalAmount = this.payAmount + this.salesAmount;

        payResults = ("\nBilling date: " + date + " Gross pay: $" + this.payAmount + " Sales amount: " + this.salesAmount);
        ArrayList<String> payRecords = super.getPayRecordsList();
        payRecords.add(payResults);

        ArrayList<Float> allPays = super.getAllPaysList();
        allPays.add(totalAmount);

        return (payResults);
    }
}
