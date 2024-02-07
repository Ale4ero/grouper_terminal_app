import java.util.Random;
public class Guest{
        String [] people = {"Jeff", "Ted", "Ben", "Craig", "Emma", "Josh", "Dan", "Noah", "Patricia", "Mary", "James", "Isa", "Joe", "Greg"};
        String name;
        int partySize;

        int [] normal = {1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 4, 5, 5, 6, 6, 7, 7, 8, 9};

        public void talk(){
            System.out.println("\n------------------");
            System.out.println("Guest Name: "+ name);
            System.out.println("Party of "+ partySize);
            System.out.println("------------------\n");
        }

        //Constructor
        public Guest(){
            Random rand = new Random();
            this.partySize = normal[rand.nextInt(normal.length)];
            this.name = people[rand.nextInt(people.length)];
        }
    }
