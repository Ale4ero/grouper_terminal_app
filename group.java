import java.util.Scanner;




public class group {

    public static void main(String[] args) {
        //scanner
        Scanner scan = new Scanner(System.in);

        //TITLE
        System.out.println("\n-----GROUPER SIMULATOR-----");

        int[][] rv = createNewRV();
        showRV(rv);
        // createGuest();
        
        boolean quit = false;
        
        
        //while user doesnt choose to exit, create new guest and prompt menu
        while (!quit){
            int emptySeats = 0;
            int choice = menu(scan);
            if(choice == 1){
                int partySize = createGuest();

                //check all 6 rows for empty seats, 1-7 because check row subtracts 1 to row to match array index
                for (int i = 1; i < 7; i++){
                    emptySeats += checkRow(i, rv);
                }

                //if partysize is less than or equal to emptySeats: group
                if(partySize <= emptySeats){
                    groupGuest(rv, partySize, scan);
                }else System.out.println("Not enough space on RV for this party!");
                
            } 
            else if(choice == 2) showRV(rv);
            else if(choice == 3){
                System.out.println("RV sent with "+emptySeats+" empties.");
                rv = createNewRV();
                System.out.println("New RV is in station.");
                showRV(rv);
            }
            else quit = true;
        }

        scan.close();
        System.out.println("Good Bye!");
        
    }

    

    //menu method returns the choice to the main mehtod
    public static int menu(Scanner scan){
        
        //menu output
        System.out.println("\nChoose option:");
        System.out.println("Next guest : 1");
        System.out.println("Show RV : 2");
        System.out.println("Send RV : 3");
        System.out.println("Stop sim : 0\n");

        //store user input
        int choice =  scan.nextInt();
        
        return choice;
    }



    //method to create a guest 
    public static int createGuest(){
        Guest guest = new Guest();
        guest.talk();
        return guest.partySize;
    }



    //method to create a new rv
    public static int [][] createNewRV(){
        int [][] rideVehicle = new int [6][4];
        return rideVehicle;
    }



    //method to show rv
    public static void showRV(int [][] rv){
        System.out.print("\n\t\t\t\t ----RIDE VEHICLE----");
        for(int i = 0; i < 6; i++){
            System.out.print("\n");
            if(i == 0)System.out.print("\t\t\t\t   _______________\n");
            for(int j = 0; j < 4; j++){
                int rowNum = i+1;
                if(j == 0) System.out.print("\t\t\t\t"+ rowNum +" |");
                System.out.print("["+rv[i][j]+"]");
                if( j == 3) System.out.print("|");
                else System.out.print(",");
            }
            if(i == 2) System.out.print("\n");
        }
        System.out.println("\n\t\t\t\t   ---------------\n");
        System.out.print("\n");
    }



    //method to group guests
    public static int[][] groupGuest(int [][] rv, int partySize, Scanner scan){
    
        int [][] newRV = rv;
        int leftToGroup = partySize;
        
        int amt;
        int row;

        boolean wrongOrder = true;

        int input = 0;

        

        while (leftToGroup > 0){
            
            wrongOrder = true;
            showRV(newRV);
            
            
            

            while (wrongOrder == true){
                System.out.println("Group the party: "+leftToGroup+" members left to group.");
                //scan for users order
                input = scan.nextInt();
                wrongOrder = checkOrder(input, leftToGroup, newRV);
            }

            
            //get amt of people and row from users order
            amt = input / 10;
            row = input % 10;


            //fill row
            newRV = fillRVRow(row, amt, newRV);
            System.out.println("You grouped "+amt+" out of the "+leftToGroup+" left.");

            //subtract amt grouped from leftToGroup
            leftToGroup-=amt;

            //repeat until leftToGroup is 0
        }
                
        
        showRV(newRV);
        System.out.println("Whole party grouped.");
        
        return newRV;
    }


    //method to check if users order is wrong. if wrong return true otherwise false
    public static boolean checkOrder(int order, int leftToGroup, int [][] rv){
        
        //must check if order input valid


        //if input is not two digits
        if(order < 10 || order > 64){
            System.out.println("Error! Return a two digit input. (First digit[number of guest], second digit[which row])");
            return true;
        }

        int amt = order / 10;
        int row = order % 10;

        System.out.println("you want "+amt+" to row "+row);

        //if first digit is greater than 4 or number of guests left in party
        if (amt > leftToGroup || amt > 4){
            System.out.println("Grouped too many people!");
            return true;
        }
        //if row doesnt exist
        if(row > 6 || row < 1){
            System.out.println("Row does not exists!");
            return true;
        }

        int emptySeats = checkRow(row, rv);

        //if order amt is more than available seats in row
        if(amt > emptySeats){
            System.out.println("Not enough space in that row. Only "+emptySeats+" spots, you want "+amt+".");
            return true;  
        }

        //order is good! continue...
        return false;
    }

    //method to check available seats
    public static int checkRow(int row, int[][] rv){
        int[][] currentRV = rv;
        int emptySeats = 0;
        for (int j = 3; j > -1;j--){
            if (currentRV[row-1][j] == 0){
                emptySeats = j+1; 
                break;
            }
        }

        return emptySeats;
    }


    //method to fill row in a rv
    public static int [][] fillRVRow(int row, int amt, int[][] rv){
        int [][] newRV = rv;

        if(newRV[row-1][0] == 1){
            System.out.println("RV is already full!");
            return newRV;
        }

        //fill the seat all the way down 
        for(int i = 0; i<amt;i++){
            int j = 3;
            while(newRV[row-1][j]==1) j--;

            newRV[row-1][j] = 1;
        }

        return newRV;
    }


    
}


