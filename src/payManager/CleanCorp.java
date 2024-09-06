package payManager;

import java.util.ArrayList;
import java.util.Scanner;

public class CleanCorp {
    private Scanner myScanner = new Scanner(System.in);
    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private ArrayList<Subcontractor> subcontractors = new ArrayList<Subcontractor>();
    private boolean run = true, invalidSelection = true, emailInvalid = true, employeePayment = false, subcontractorPayment = false;
    private int menuSelect, employeeType, id, findID;
    private String n, d, fN, lN, a, p, e;
    private float cR;

    public CleanCorp(){
        mainMenuHandle();
    }

    public void mainMenuHandle(){
        while(run){
            System.out.print("\n1- Add employee \n2- Give employee pay \n3- Add subcontractor \n4- Pay subcontractor bill \n5- List pays by employee \n6- List bills by subcontractor \n7- List all expenses  \n8- Exit \n \nSelect option: ");
            menuSelect = myScanner.nextInt();
            myScanner.nextLine();

            switch (menuSelect){
                case 1:
                    chooseEmployeeType();
                    break;
                case 2:
                    payEmployeeHandle();
                    break;
                case 3:
                    addSubcontractor();
                    break;
                case 4:
                    paySubcontractorHandle();
                    break;
                case 5:
                    listEmployeePaysHandle();
                    break;
                case 6:
                    listSubcontractorBillsHandle();
                    break;
                case 7:
                    listExpensesHandle();
                    break;
                case 8:
                    run = false;
                    break;
                default:
                    System.out.println("\nInvalid input, enter a number from 1 - 8.");
                    break;
            }
        }
    }

    public void chooseEmployeeType(){
        invalidSelection = true;

        while (invalidSelection){
            System.out.print("\n1- regular employee \n2- sales employee \n3- Quit to main menu \n \nSelect option: ");
            employeeType = myScanner.nextInt();
            myScanner.nextLine();
    
            if (employeeType == 1 || employeeType == 2){ 
                addEmployee();
                if (employeeType == 1) {
                    Employee anEmployee = new Employee(id, fN, lN, a, p, e);
                    employees.add(anEmployee);
                }else {
                    boolean rateInvalid = true;

                    while (rateInvalid){
                        System.out.print("\nCommission rate: ");
                        cR = myScanner.nextFloat();
            
                        if (cR > 25 || cR < 0){
                           System.out.println("\nCommission rate invalid enter a number between 25 - 0"); 
                        }else {
                            rateInvalid = false;
                        }
                    }

                    SalesEmployee aSalesEmployee = new SalesEmployee(id, fN, lN, a, p, e, cR);
                    employees.add(aSalesEmployee);
                }
                invalidSelection = false;
            }else if (employeeType == 3){
                invalidSelection = false;
            }else {
                System.out.println("Invalid input, enter a number from 1 - 3.");
            }
        }
    }

    public void addEmployee(){
        id = employees.size() + 1;

        boolean invalidName = true;

        while (invalidName){
            System.out.print("\nFirst name: ");
            fN = (myScanner.nextLine()).trim();
    
            System.out.print("\nLast name: ");
            lN = (myScanner.nextLine()).trim();
            
            if (fN.length() > 0 && lN.length() > 0){
                invalidName = false;
            }else {
                System.out.println("\nInvalid name length, enter a minimum of 1 character");
            }
        }
    
        requestContactInfo();
    }

    public void payEmployeeHandle(){
        if (employees.size() == 0){
            System.out.println("\nYou have no employees. Add an employee in the menu.");
            mainMenuHandle();
        }

        for (Employee aEmployee : employees) {
            String employeeDate = aEmployee.getEmployeeData();
            System.out.print("\n" + employeeDate + "\n \n1- Give pay \n2- Next employee \n3- Quit to main menu \n \nSelect option: ");
            int payMenuSelect = myScanner.nextInt();

            if (payMenuSelect == 1){
                boolean salaryInvalid = true;
                float salary = 0f;

                while (salaryInvalid){
                    System.out.print("\nSalary: ");
                    salary = myScanner.nextFloat();
                    
                    if (salary > 0){
                        salaryInvalid = false;
                    }else {
                        System.out.println("\nInvalid salary must be greater then 0");
                    }
                }
             
                float hours = 0f;
                String payData;

                boolean hoursInvalid = true;

                while(hoursInvalid){
                    System.out.print("\nHours: ");
                    hours = myScanner.nextFloat();
                    myScanner.nextLine();  

                    if (hours <= 40 && hours > 0){
                        hoursInvalid = false;
                    } else{
                        System.out.println("\nInvalid number of hours must be above 0 and less then 40.");
                    }
                }

                payData = aEmployee.givePay(hours, salary);        
                System.out.println(payData);

                employeePayment = true;
            }else if (payMenuSelect == 3){
                mainMenuHandle();
            }
        }
    }

    public void addSubcontractor(){
        id = subcontractors.size() + 1;

        boolean invalidName = true;

        while (invalidName){
            System.out.print("\nName: ");
            n = (myScanner.nextLine()).trim();
            
            if (n.length() > 0){
                invalidName = false;
            }else {
                System.out.println("\nInvalid name length, enter a minimum of 1 character");
            }
        }
        System.out.print("\ndescription:");
        d = (myScanner.nextLine()).trim();

        requestContactInfo();

        Subcontractor aSubcontractor = new Subcontractor(id, n, d, a, p, e);
        subcontractors.add(aSubcontractor);
    }

    public void paySubcontractorHandle(){
        if (subcontractors.size() == 0){
            System.out.println("\nYou have no subcontractors. Add an subcontractor in the menu.");
            mainMenuHandle();
        }

        String billData;

        for (Subcontractor aSubcontractor : subcontractors){
            String subcontractorData = aSubcontractor.getSubcontractorData();
            System.out.print("\n" + subcontractorData + "\n \n1- Pay bill \n2- Next subcontractor \n3-Quit to main menu \n \nSelect option: ");
            int billMenuSelect = myScanner.nextInt();

            if (billMenuSelect == 1){
                boolean amountInvalid = true;
                float amount = 0f;

                while (amountInvalid){
                    System.out.print("\nAmount: ");
                    amount = myScanner.nextFloat();

                    if (amount > 0){
                        amountInvalid = false;
                    }else {
                        System.out.println("\nInvalid amount enter a number greater then 0");
                    }
                }
               
                billData = aSubcontractor.giveBill(amount);
                System.out.println(billData);

                subcontractorPayment = true;
            }else if (billMenuSelect == 3){
                mainMenuHandle();
            }
        }
    }

    public void listEmployeePaysHandle(){
        if (employeePayment == false){
            System.out.println("\nYou have no employee payments. Pay an employee in the menu.");
            mainMenuHandle();
        }

        validateID();
     
        Employee selectedEmployee = employees.get(findID);

        String selectedEmployeeData = selectedEmployee.getEmployeeData();
        String selectedEmployeePays = selectedEmployee.getEmployeePays();

        System.out.println("\n" + selectedEmployeeData + "\n" + selectedEmployeePays);
    }

    public void listSubcontractorBillsHandle(){
        if (subcontractorPayment == false){
            System.out.println("\nYou have no subcontractor payments. Pay a subcontractor in the menu.");
            mainMenuHandle();
        }

        validateID();

        Subcontractor selectedSubcontractor = subcontractors.get(findID - 1);

        String selectedSubcontractorData = selectedSubcontractor.getSubcontractorData();
        String selectedSubcontractorBills = selectedSubcontractor.getSubcontractorBills();

            System.out.println("\n" + selectedSubcontractorData + "\n" + selectedSubcontractorBills);
    }

    public void listExpensesHandle(){
        if (employeePayment == false && subcontractorPayment == false){
            System.out.println("\nYou have no expenses. Pay an employee or subcontractor in the menu.");
            mainMenuHandle();
        }

        for (Employee aEmployee : employees) {
            System.out.println("\n" + aEmployee.getEmployeeData() + " Total amount: " + aEmployee.getExpenses());
        }

        for (Subcontractor aSubcontractor : subcontractors) {
            System.out.println("\n" + aSubcontractor.getSubcontractorData() + " Total amount: " + aSubcontractor.getExpenses());
        }
    }

    public void validateID(){
        boolean invalidID = true;

        while (invalidID){
            System.out.print("\nEnter ID: ");
            findID = myScanner.nextInt();
            findID = findID - 1;
            if (findID > employees.size() || findID < 0){
                System.out.println("\nInvalid ID, enter an existing ID number");
            }else {
                invalidID = false;
            }
        }
    }

    public void requestContactInfo(){
        emailInvalid = true;

        System.out.print("\nAddress: ");
        a = (myScanner.nextLine()).trim();

        System.out.print("\nPhone: ");
        p = (myScanner.nextLine()).trim();

        while(emailInvalid) {
            System.out.print("\nEmail: ");
            e = (myScanner.nextLine()).trim();

            if (!(e.contains("@") && e.contains(".com"))) {
                System.out.println("\nThe email is not valid. ");
            }else {
                emailInvalid = false;
            }
        }
    }
}
