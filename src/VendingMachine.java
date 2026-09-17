
/**
 * Garrett's popcorn vending machine

Garrett's popcorn is looking to launch a vending machine that sells their world renowned popcorn.  The vending machine will contain a total of 9 products. 
The products are listed below in the chart along with purchase price.  To select an item, the end-user will enter the product row along with the column letter.
The program should enforce that only product row numbers and column letters can be entered. The end-user will be able to continously select up to  as they would 
like until they enter a sentinel value.
   

At the conslusion of selecting all items, the program will display the total number of items purchased and the total cost.

**Hints**
- Make use of two dimensional arrays.
- Consider parallel arrays



Your program should operate similarly to the program shown in the .gif below
The .gif below show three iterations of running the program

![Alt text](https://instructorc.github.io/site/slides/java/images/ds/program_4_sample_output.gif "Program 4 Execution Example")



| Product Row | Column P               | Column N                       | Column R           |
|-------------|------------------------|-----------------------------------------------------|
| 0           | Garrett Mix ($14.99)   |  Pecan Carmel Crisp ($10.99)   | Plain ($6.99)      |
| 1           | Caramel Crisp ($16.99) |  Cashew Carmel Crisp ($9.99)   | Buttery ($8.99     |
| 2           | Cheese Corn ($12.99)   |  Almond Carmel Crisp ($11.99)  | Sweet Corn ($7.99)  


 */

import java.util.Scanner; //Import the Scanner Class

public class VendingMachine {
    public static void main(String[] args) throws Exception {
        int itemTotal = 0;
        int x, y;

        double costTotal = 0;

        boolean auth = false;
        boolean session = false;

        // Popcorn = {string name, double price, int stock = 5}
        Popcorn garrett = new Popcorn("Garrett Mix", 14.99);
        Popcorn caramel = new Popcorn("Caramel Crisp", 16.99);
        Popcorn cheese = new Popcorn("Cheese Corn", 12.99);
        Popcorn pecan = new Popcorn("Pecan Caramel Crisp", 10.99);
        Popcorn cashew = new Popcorn("Cashew Caramel Crisp", 09.99);
        Popcorn almond = new Popcorn("Almond Caramel Crisp", 11.99);
        Popcorn plain = new Popcorn("Plain", 06.99);
        Popcorn buttery = new Popcorn("Buttery", 08.99);
        Popcorn sweet = new Popcorn("Sweet", 07.99);

        Popcorn[][] catalog = { { garrett, caramel, cheese }, { pecan, cashew, almond }, { plain, buttery, sweet } };

        Scanner scnr = new Scanner(System.in);

        int intBuffer;

        ///// LOGIN
        System.out.println("Welcome to Garrett's Vending Machine!");

        while (!session && !auth) {
            System.out.println("Press 1 to make purchase, press 0 to enter admin mode.");
            intBuffer = scnr.nextInt();

            switch (intBuffer) {
                case 0:
                    System.out.println("Please enter password.");
                    scnr.next(); // doesn't actually validate anything, just for fun
                    auth = true;
                    session = true;
                    break;

                case 1:
                    session = true;
                    break;

                default:
                    System.out.println("Sorry.");
                    break;
            }

        }

        ///// BUYING SESSION
        while (session) {

            // Printing initial welcome
            intBuffer = 1;

            System.out.printf("| %-4s| %-31s| %-31s| %-31s|%n", "Row", "Column 1", "Column 2", "Column 3");
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------");
            for (Popcorn[] row : catalog) {
                System.out.printf("| %-4s", intBuffer);
                for (Popcorn column : row) {
                    System.out.printf("| %-21s %5.2f (%-1d)", column.name, column.cost, column.stock);
                }
                System.out.print("|\n");
                intBuffer++;
            }

            // Choosing item based on x,y coordinate in array
            System.out.println("\nPlease type the row of the item you would like to select, or 0 if you are done.");
            x = scnr.nextInt() - 1; // accounts for 0 index

            if (x == -1) {
                session = false;
                break; // this is the exit statement for the loop.
            }

            System.out.println("Please type the column of the item you would like to select.");
            y = scnr.nextInt() - 1;

            if (x >= catalog.length || y >= catalog[x].length || catalog[x][y].stock == 0) {
                System.out.println("Bad choice, pick again."); // to prevent access outside array boundaries
            } else {
                costTotal = costTotal + Purchase(catalog[x][y]); // purchase method: takes in Popcorn, returns double.
                itemTotal++; // adjusts stock inside method
            }

        }

        System.out
                .println("\nSummary of items purchased\n*****************************\n" + "You purchased " + itemTotal
                        + " items for $" + costTotal);
        if (auth) {
            System.out.println("|Restocked: ");
            System.out.printf("|Restock Total:        $%5.2f%n", Restock(catalog)); // Restock method: takes in
                                                                                    // Popcorn[][] outputs double. loops
                                                                                    // and prints + resets stocks lower
                                                                                    // <= 3
        }

        scnr.close();

    }

    static double Restock(Popcorn[][] catalog) {
        double restockCost = 0;
        for (Popcorn[] row : catalog) {
            for (Popcorn column : row) {
                if (column.stock <= 3) {
                    restockCost = restockCost + (5 - column.stock) * (column.cost / 2);
                    System.out.printf("|%-21s|$%5.2f|%n", column.name, restockCost);
                    column.stock = 5;

                }

            }
        }
        return restockCost;

    }

    static double Purchase(Popcorn selection) {
        System.out.printf("Purchased: %-21s|$%5.2f|%n", selection.name, selection.cost);
        selection.stock--;
        return selection.cost;
    }

}

class Popcorn {
    String name;
    double cost;
    int stock;

    public Popcorn(String name, double cost) {
        this.name = name;
        this.cost = cost;
        this.stock = 5;

    }

}
