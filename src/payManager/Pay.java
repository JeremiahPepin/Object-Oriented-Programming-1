package payManager;

import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.Scanner;

public class Pay {
    private Scanner myScanner = new Scanner(System.in);
    private float hours, salary, sales, amount, commissionRate, salesAmount;
    private LocalDate date = LocalDate.now();
    private int year, month, day;

    public Pay(float h, float s){
        this.hours = h;
        this.salary = s;
    }

    public void saleEmployeePay(float cR, float s){
        this.commissionRate = cR;
        this.sales = s;
    }

    public LocalDate getBillingDate(){
        boolean invalidDate = true;

        System.out.print("\nDo you want to add a custom date? y/n: ");
        String dateOption = ((myScanner.nextLine()).trim()).toLowerCase();

        if (dateOption.equals("y")){
            while(invalidDate) {
                System.out.print("\nEnter year (yyyy): ");
                this.year = myScanner.nextInt();
    
                System.out.print("\nEnter month (mm): ");
                this.month = myScanner.nextInt();
    
                System.out.print("\nEnter day (dd): ");
                this.day = myScanner.nextInt();
                myScanner.nextLine();
    
                try {
                    date = LocalDate.of(this.year, this.month, this.day);
                    invalidDate = false;
                }catch (DateTimeException e){
                    System.out.println("\nInvalid date.");
                }
            }
        }
        return date;
    } 

    public float getPayAmount(){
        amount = (this.hours * this.salary);

        return amount;
    }

    public float getSalesAmount(){
        salesAmount = this.commissionRate * this.sales;
  
        return salesAmount;
    }
}
