
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

/*
* PLAN: each popcorn is a obj. name, cost, stock 3tuple. multidimensional array
* of
* row/col indicies. similar architecture to last assignment (session while
* loop, check for if
* admin session).
* popcorn class with newCorn method, purchase method, restock method,
*
*
* Plan for table: String[][], x,y. fill static slots, then add popcorn. box
* drawing seperate
*/

import java.util.Scanner; //Import the Scanner Class

public class VendingMachine {
    public static void main(String[] args) throws Exception {
        int itemTotal;
        int intBuffer, x, y;

        float costTotal;

        boolean auth = false;
        boolean session = false;

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

        ///// LOGIN
        /*
         * System.out.println(
         * "Welcome to Garrett's Vending Machine!");
         * while (!session && !auth) {
         * System.out.println("Press 1 to make purchase, press 0 to enter admin mode.");
         * intBuffer = scnr.nextInt();
         * 
         * switch (intBuffer) {
         * case 0:
         * System.out.println("Please enter password.");
         * stringBuffer = scnr.next(); // doesn't actually validate anything, for fun
         * if (stringBuffer != null) {
         * auth = true;
         * } else {
         * System.out.println("Bad password.");
         * }
         * session = true;
         * break;
         * 
         * case 1:
         * session = true;
         * break;
         * 
         * default:
         * System.out.println("Sorry.");
         * break;
         * }
         * 
         * }
         */
        ///// BUYING SESSION

        for (Popcorn[] row : catalog) {
            for (Popcorn column : row) {
                System.out.printf("| %-23s %5.2f (%-1d)", column.name, column.cost, column.stock);
            }
            System.out.print("|\n");
        }
        scnr.close();

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

    public void Purchase(int amount) {
        this.stock = this.stock - amount;
    }

    public void Restock() {
        this.stock = 5;
    }

}
