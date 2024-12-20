import java.util.Scanner;
class java_miniproject{
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
        int sel = 0;
        try {
            sel = Integer.parseInt(x);
            if (sel > 4) {
                System.out.println("Enter a valid selection!");
                System.exit(0);

            }
        } catch (NumberFormatException err) {
            System.out.println("Enter a valid selection!");
            System.exit(0);

        }

        if (sel != 4) {
            System.out.print("Enter DOB: ");
        } else {
            System.out.print("Enter AGE: ");
        }
        String inp = sc.nextLine();
        System.out.println("----------------------------------------------------------");
        String[] input = new String[3];
        if (inp.contains("-") || inp.contains("/")) {
            input = inp.trim().split("-|\\/");

        } else {
            System.out.println("Enter a valid dlc!");
            System.exit(0);
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
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException err) {
            System.out.println("Enter a valid input!");
            System.exit(0);
        }
        dob[3] = (sel == 4) ? 1 : 0;

        return dob;
    }

    static int[] valid_input() {
        int valid = 0;
        int[] input = new int[4];
        boolean leap_year;
        input = user_input();
        leap_year = ((input[0] % 4 == 0) && (input[0] % 100 != 0 || input[0] % 400 == 0));
        if (input[3] == 0) {
            if ((input[2] == 29 && !leap_year) || ((input[1]==9 || input[1]==11 || input[1] % 2 == 0) && input[2] > 30 && input[1] != 2) || (( input[1] == 8 || input[1]==10 || input[1]==12 || input[1] % 2 != 0) && input[2] > 31 && input[1]!=2 )|| (input[0] < 0 || input[1] < 0 || input[2] < 0)) {
                System.out.println("Enter a valid DOB!");
                System.exit(0);
            }
        }
        else if(input[3]==1){
            if((input[0] < 0 || input[1] < 0 || input[2] < 0)){
                System.out.println("Enter a valid AGE!");
                System.exit(0);
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
        int present_date = 16;
        int present_month = 11;
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
        }
        else {
            if (m < present_month) {
                years = present_year - y;
            }
            else {
                if (present_year != y && present_month != m) {
                    years = present_year - y - 1;
                    present_month = present_month + 12;
                }
                else {
                    if(d<present_date){
                        years = present_year - y;}
                    else{
                        years = present_year-y-1;
                    }
                }
            }

            if (d <= present_date) {
                days = present_date - d;
                if(present_month==m){
                    months=present_month;
                }
                else {
                    months = present_month - m;
                }
            } else {
                if(m!=present_month) {
                    if (m == 9 || m == 11 || m % 2 == 0) {
                        days = (30 - d) + present_date;
                    }
                    else if (m == 8 || m == 10 || m == 12 || m % 2 == 1) {
                        days = (31 - d) + present_date;
                    }
                    else if (m == 2) {
                        days = (28 - d) + present_date + leap_days;
                    }
                    months = present_month - m - 1;
                }
                else{
                    months = present_month;
                    days = (30 - d) + present_date;
                }
            }
        }
        if(input[3]==0) {
            days++;
            System.out.println("Your age is: " + years + "years " + months + "months " + days + "days");
        }
        else{
            System.out.println("Your DOB is: "+days+"/"+months+"/"+years);
        }
        System.out.println("----------------------------------------------------------");
    }
}

