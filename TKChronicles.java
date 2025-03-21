//project start

package java_mini_project;

import java.util.*;

class Book {
    String name, description;
    float price;
    int stock, originalStock;

    Book(String name, String description, float price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.originalStock = stock;
    }
}

public class TKChronicles {
    static ArrayList<Book> cart = new ArrayList<>();
    static ArrayList<String> purchaseHistory = new ArrayList<>();
    static Book[] books = {
        new Book("Sherlock Holmes: The Complete Collection, Vol. 1", "The first volume of legendary detective stories.", 1499, 8),
        new Book("Sherlock Holmes: The Complete Collection, Vol. 2", "The second volume continues thrilling adventures.", 1499, 6),
        new Book("Sherlock Holmes: The Complete Collection, Vol. 3", "The final volume brings more brilliant cases.", 1499, 9),
        new Book("A Game of Thrones (A Song of Ice and Fire, Vol. 1)", "The first book in the epic series of Westeros.", 1899, 8),
        new Book("A Clash of Kings (A Song of Ice and Fire, Vol. 2)", "The battle for the throne intensifies.", 1899, 7),
        new Book("A Storm of Swords (A Song of Ice and Fire, Vol. 3)", "The third volume takes you deeper into battle.", 1899, 6),
        new Book("Me Before You", "A heartwarming love story of Louisa and Will.", 899, 9),
        new Book("The Fault in Our Stars", "A touching love story of two teenagers.", 799, 3),
        new Book("The Seven Husbands of Evelyn Hugo", "A tale of glamour, secrets, and love.", 1299, 5),
        new Book("It Ends with Us", "A powerful story about love and resilience.", 1099, 4),
        new Book("Ikigai: The Japanese Secret to a Long and Happy Life", "Find purpose and meaning in life.", 1199, 10),
        new Book("The Psychology of Money", "Understanding wealth and investing.", 1499, 7),
        new Book("The Monk Who Sold His Ferrari", "A story of self-discovery and happiness.", 999, 5),
        new Book("The Great Gatsby", "A classic tale of love and the American Dream.", 1099, 4),
        new Book("The Night Circus", "A magical rivalry in a mystical circus.", 1499, 6)
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to TK Chronicles!");
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        System.out.println("Hello, " + username + "! Let's begin.");

        while (true) {
            System.out.println("\n1. Buy Books\n2. Check Cart\n3. Purchase History\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1: buyBooks(sc); break;
                case 2: checkCart(sc); break;
                case 3: showPurchaseHistory(); break;
                case 4: resetStock(); System.out.println("Thank you for visiting TK Chronicles!"); return;
                default: System.out.println("Wrong choice, choose again.");
            }
        }
    }

    static void buyBooks(Scanner sc) {
        while (true) {
            System.out.println("\nAvailable Books:");
            for (int i = 0; i < books.length; i++) {
                System.out.println((i + 1) + ". " + books[i].name + " - ₹" + books[i].price + " (Stock: " + books[i].stock + ")");
            }
            System.out.println((books.length + 1) + ". Back to Main Menu");
            
            System.out.print("Select a book: ");
            int bookChoice = sc.nextInt();
            if (bookChoice == books.length + 1) return;
            if (bookChoice < 1 || bookChoice > books.length) {
                System.out.println("Invalid choice, try again.");
                continue;
            }
            
            Book selectedBook = books[bookChoice - 1];
            System.out.println("\nYou selected: " + selectedBook.name);
            System.out.println("Description: " + selectedBook.description);
            System.out.println("Price: ₹" + selectedBook.price);
            System.out.println("Available Copies: " + selectedBook.stock);
            
            System.out.println("1. Buy Now\n2. Add to Cart\n3. Back to Book List");
            System.out.print("Choose an option: ");
            int action = sc.nextInt();
            
            if (action == 3) continue;
            
            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            if (qty <= 0 || qty > selectedBook.stock) {
                System.out.println("Invalid quantity! Try again.");
                continue;
            }
            
            if (action == 1) {
                processPurchase(selectedBook, qty);
            } else {
                cart.add(new Book(selectedBook.name, "", selectedBook.price, qty));
                System.out.println("Added to cart.");
            }
        }
    }
    
    static void processPurchase(Book book, int qty) {
        book.stock -= qty;
        String bill = "\n***** BILL *****\n" + "Book: " + book.name + "\nQuantity: " + qty + "\nTotal: ₹" + (book.price * qty) + "\nThank you for your purchase!";
        System.out.println(bill);
        purchaseHistory.add(bill);
    }
    
    static void checkCart(Scanner sc) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("\nYour Cart:");
        float total = 0;
        for (Book book : cart) {
            System.out.println(book.name + " - ₹" + book.price + " x " + book.stock);
            total += book.price * book.stock;
        }
        System.out.println("Total: ₹" + total);
        System.out.println("1. Checkout\n2. Back to Main Menu");
        if (sc.nextInt() == 1) checkout();
    }

    static void checkout() {
        System.out.println("\n***** BILL *****");
        float total = 0;
        for (Book book : cart) {
            System.out.println(book.name + " - ₹" + book.price + " x " + book.stock);
            total += book.price * book.stock;
        }
        System.out.println("Total: ₹" + total + "\nThank you for your purchase!");
        purchaseHistory.add("Total: ₹" + total);
        cart.clear();
    }
    
    static void showPurchaseHistory() {
        if (purchaseHistory.isEmpty()) System.out.println("No purchases made yet.");
        else for (String bill : purchaseHistory) System.out.println(bill);
    }
    
    static void resetStock() {
        for (Book book : books) book.stock = book.originalStock;
    }
}

//end of project 
