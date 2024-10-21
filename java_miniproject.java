//author: PSS
import java.util.Scanner;
class java_miniproject {
    static int[] user_input() {
        int[] dob = new int[4];
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------");
        System.out.println("Enter 1 to select DOB= YYdlcMMdlcDD (International format)");
        System.out.println("Enter 2 to select DOB= DDdlcMMdlcYY (Indian format)");
        System.out.println("Enter 3 to select DOB= MMdlcDDdlcYY (US format)");
        System.out.println("Enter 4 to select AGE= YYdlcMMdlcDD");
        System.out.println("Allowed dlc are '-' or '/' ");
        System.out.println("----------------------------------------------------------");
        System.out.print("Enter selection: ");
        String x = sc.nextLine();
        int sel=0;
        try {
            sel = Integer.parseInt(x);
        }
        catch(NumberFormatException err){
            System.out.println("Enter a valid selection!");
            user_input();
        }

        if(sel!=4) {
            System.out.print("Enter DOB: ");
        }
        else{
            System.out.print("Enter AGE: ");
        }
        String inp = sc.nextLine();
        System.out.println("----------------------------------------------------------");
        String[] input = new String[3];
        if(inp.contains("-") || inp.contains("/")){
            input = inp.trim().split("-|\\/");

        }
        else{
            System.out.println("Enter a valid dlc!");
            user_input();
        }

        try {
            switch (sel) {
                case 1:
                    dob[0] = Integer.parseInt(input[0]);
                    dob[1] = Integer.parseInt(input[1]);
                    dob[2] = Integer.parseInt(input[2]);
                    break;
                case 2:
                    dob[0] = Integer.parseInt(input[2]);
                    dob[1] = Integer.parseInt(input[1]);
                    dob[2] = Integer.parseInt(input[0]);
                    break;
                case 3:
                    dob[0] = Integer.parseInt(input[2]);
                    dob[1] = Integer.parseInt(input[0]);
                    dob[2] = Integer.parseInt(input[1]);
                    break;
                case 4:
                    dob[0] = Integer.parseInt(input[0]);
                    dob[1] = Integer.parseInt(input[1]);
                    dob[2] = Integer.parseInt(input[2]);
                    break;
            }
        }
        catch (NumberFormatException | ArrayIndexOutOfBoundsException err){
            System.out.println("Enter a valid input!");
            user_input();
        }
            dob[3] = (sel == 4) ? 1 : 0;

        return dob;
    }

    static int[] valid_input() {
        int valid = 0;
        int[] input = new int[4];
        boolean leap_year;
        while (valid == 0) {
            input = user_input();
            leap_year = ((input[0] % 4 == 0) && (input[0] % 100 != 0 || input[0] % 400 == 0));
            if (input[3] == 0) {
                if ((input[2] == 29 && !leap_year) || (input[1] % 2 == 0 && input[2] > 30 && input[1] != 2) || (input[1] % 2 != 0 && input[2] > 31 && input[1]!=2 )|| (input[0] < 0 || input[1] < 0 || input[2] < 0)) {
                    System.out.println("Enter a valid DOB!");
                    input = user_input();
                }
                else {
                    valid = 1;
                }
            }
            else if(input[3]==1){
                if((input[0] < 0 || input[1] < 0 || input[2] < 0)){
                    System.out.println("Enter a valid AGE!");
                    input = user_input();
                }
                else {
                    valid = 1;
                }
            }

        }
        return input;

    }

    public static void main(String[] args) {
        int[] input = valid_input();
        int y = input[0];
        int m = input[1];
        int d = input[2];
        int present_year = 2024;
        int present_date = 20;
        int present_month = 10;
        int years = 0;
        int months = 0;
        int days = 0;
        int leap_days = 0;
            int i = 0;
            for (i = y; i <= present_year; i++) {
                if ((i % 4 == 0) && (i % 100 != 0 || i % 400 == 0)) {
                    leap_days++;
                }
            }
            if (y >= present_year) {
                years = 0;
                months = 0;
                days = 0;
            } else {
                if (m < present_month) {
                    years = present_year - y;
                } else {
                    if (present_year != y && present_month != m) {
                        years = present_year - y - 1;
                        present_month = present_month + 12;
                    } else {
                        years = present_year - y;
                        present_month = present_month;
                    }
                }

                if (d <= present_date) {
                    days = present_date - d;
                    months = present_month - m;
                } else {
                    if (m % 2 == 0) {
                        days = (30 - d) + present_date;
                        months = present_month - m - 1;
                    } else if (m % 2 == 1) {
                        days = (31 - d) + present_date;
                        months = present_month - m - 1;
                    } else if (m == 2) {
                        days = (28 - d) + present_date + leap_days;
                        months = present_month - m - 1;
                    }
                }
            }
            if(input[3]==0) {
                System.out.println("Your age is: " + years + "years " + months + "months " + days + "days");
            }
            else{
                System.out.println("Your DOB is: "+days+"/"+months+"/"+years);
            }
            System.out.println("----------------------------------------------------------");
    }
}

