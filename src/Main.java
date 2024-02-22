import java.sql.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Connection cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
            Statement statement = cn.createStatement();
            String thistable = "CREATE TABLE IF NOT EXISTS Buldak " +
                    "(id INTEGER NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "price INTEGER NOT NULL, " +
                    "available BOOLEAN NOT NULL);" +
                    "CREATE TABLE IF NOT EXISTS Samyang " +
                    "(id INTEGER NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "price INTEGER NOT NULL, " +
                    "available BOOLEAN NOT NULL);" +
                    "CREATE TABLE IF NOT EXISTS Seafood " +
                    "(id INTEGER NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "price INTEGER  NOT NULL, " +
                    "available BOOLEAN NOT NULL);" +
                    "CREATE TABLE IF NOT EXISTS Kimchi " +
                    "(id INTEGER NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "price INTEGER NOT NULL, " +
                    "available BOOLEAN NOT NULL);" +
                    "CREATE TABLE IF NOT EXISTS Neogur " +
                    "(id INTEGER NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "price INTEGER NOT NULL, " +
                    "available BOOLEAN NOT NULL);" +
                    "CREATE TABLE IF NOT EXISTS orders " +
                    "(id INTEGER NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "price INTEGER NOT NULL);";
            statement.executeUpdate(thistable);
            statement.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Welcome to Ramen Shop Management System!");
        while (true) {
            try {
                System.out.println("Menu:");
                System.out.println("1) Add a new product");
                System.out.println("2) Update product's information");
                System.out.println("3) Print all available products");
                System.out.println("4) Sell a product");
                System.out.println("5) Return a product");
                System.out.println("6) Delete a product");
                System.out.println("7) Exit");
                System.out.println("Enter your choice:");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        System.out.println("Enter a ramen category:");
                        System.out.println("1) Buldak \n2) Samyang \n3) Seafood \n4) Kimchi \n5) Neogur");
                        choice = scanner.nextLine();
                        System.out.println("Enter product id:");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Ramen newRamen = findRamenbyID(id, true);
                        if (newRamen== null) {
                            System.out.println("Enter product name:");
                            String name = scanner.nextLine();
                            System.out.println("Enter product price:");
                            int price = scanner.nextInt();
                            scanner.nextLine();
                            switch (choice){
                                case "1":
                                    Buldak newProduct = new Buldak(id, name, price);
                                    newProduct.add();
                                    System.out.println("Ramen added successfully.");
                                    break;
                                case "2":
                                    Samyang newProduct2 = new Samyang(id, name, price);
                                    newProduct2.add();
                                    System.out.println("Ramen added successfully.");
                                    break;
                                case "3":
                                    Seafood newProduct3 = new Seafood(id, name, price);
                                    newProduct3.add();
                                    System.out.println("Ramen added successfully.");
                                    break;
                                case "4":
                                    Kimchi newProduct4 = new Kimchi(id, name, price);
                                    newProduct4.add();
                                    System.out.println("Ramen added successfully.");
                                    break;
//                                case "5":
//                                    Neogur newProduct5 = new Neogur(id, name, price);
//                                    newProduct5.add();
//                                    System.out.println("Ramen added successfully.");
//                                    break;
                                default:
                                    System.out.println("Errorrrr! Enter again!");
                            }
                        } else {
                            System.out.println("This ramen ID is already exists.");
                            break;
                        }


                        break;
                    case "2":
                        System.out.println("Enter a ramen category:");
                        System.out.println("1) Buldak \n2) Samyang \n3) Seafood \n4) Kimchi \n5) Neogur");
                        choice = scanner.nextLine();
                        System.out.println("Enter product ID to edit it's characteristics:");
                        int new_id = scanner.nextInt();
                        scanner.nextLine();
                        Ramen foundRamen = findRamenbyID(new_id, true);
                        if (foundRamen!= null) {
                            System.out.println("Enter a new name:");
                            String new_name = scanner.nextLine();
                            System.out.println("Enter a new price:");
                            int new_price = scanner.nextInt();
                            scanner.nextLine();
                            switch (choice) {
                                case "1":
                                    Buldak newProduct = new Buldak(new_id, new_name, new_price);
                                    newProduct.update();
                                    System.out.println("Ramen info updated successfully. If not, please check(you may enter wrong id!)");
                                    break;
                                case "2":
                                    Samyang newProduct2 = new Samyang(new_id, new_name, new_price);
                                    newProduct2.update();
                                    System.out.println("Ramen info updated successfully. If not, please check(you may enter wrong id!)");
                                    break;
                                case "3":
                                    Seafood newProduct3 = new Seafood(new_id, new_name, new_price);
                                    newProduct3.update();
                                    System.out.println("Ramen info updated successfully. If not, please check(you may enter wrong id!)");
                                    break;
                                case "4":
                                    Kimchi newProduct4 = new Kimchi(new_id, new_name, new_price);
                                    newProduct4.update();
                                    System.out.println("Ramen info updated successfully. If not, please check(you may enter wrong id!)");
                                    break;
//                                case "5":
//                                    Neogur newProduct5 = new Neogur(new_id, new_name, new_price);
//                                    newProduct5.update();
//                                    System.out.println("Ramen info updated successfully. If not, please check(you may enter wrong id!)");
//                                    break;
                                default:
                                    System.out.println("Errorrrr! Enter again!");
                            }
                        } else {
                            System.out.println("Ramen not found.");
                        }

                        break;
                    case "3":
                        List<Ramen> ramens = showAllRamens(true);
                        System.out.println("Ramens:");
                        for (Ramen ramen : ramens) {
                            System.out.println(ramen.getId() + ": " + ramen.getName() + " costs " + ramen.getPrice());
                        }
                        break;
                    case "4":
                        System.out.println("Enter ramen Id:");
                        int ramenID = scanner.nextInt();
                        scanner.nextLine();
                        Ramen foundRamen1 = findRamenbyID(ramenID, true);
                        if (foundRamen1!= null) {
                            foundRamen1.add();
                            System.out.println("Ramen is sold successfully");
                        } else {
                            System.out.println("Ramen not found.");
                        }
                        break;
                    case "5":
                        System.out.println("Enter ramen Id:");
                        int ramenID1 = scanner.nextInt();
                        scanner.nextLine();
                        Ramen foundRamen2 = findRamenbyID(ramenID1, false);
                        if (foundRamen2 != null) {
                            foundRamen2.returnRamen();
                            System.out.println("Ramen is returned successfully");
                        } else {
                            System.out.println("Ramen not found.");
                        }
                        break;
                    case "6":
                        System.out.println("Enter ramen Id:");
                        int ramenID2 = scanner.nextInt();
                        scanner.nextLine();
                        Ramen foundRamen3 = findRamenbyID(ramenID2, true);
                        if (foundRamen3!= null) {
                            foundRamen3.deleteRamen();
                            System.out.println("Ramen is deleted from base successfully");
                        } else {
                            System.out.println("Ramen not found.");
                        }
                        break;
                    case "7":
                        System.out.println("Exiting the Shop Management System. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Errorrrr! Enter again!");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Enter a number for id and price please");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



    }
    private static Ramen findRamenbyID(int ramenID, boolean available) {
        for (Ramen ramen: showAllRamens(available)) {
            if (ramen.getId()==ramenID) {

                return ramen;
            }
        }
        return null;
    }
    private static List<Ramen> showAllRamens(boolean available1) {
        List<Ramen> ramens = new ArrayList<>();
        String sql = "SELECT * FROM ( " +
                "SELECT * FROM Buldak WHERE available = " + available1 +
                " UNION ALL SELECT * FROM samyang WHERE available = " + available1 +
                " UNION ALL SELECT * FROM seafood WHERE available = " + available1 +
                " UNION ALL SELECT * FROM kimchi WHERE available = " + available1 +
                " UNION ALL SELECT * FROM neogur WHERE available = " + available1 +
                ") AS ramen_union;";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Ramen ramen = new Ramen();
                ramen.setId(resultSet.getInt("id"));
                ramen.setName(resultSet.getString("name"));
                ramen.setPrice(resultSet.getInt("price"));
                ramens.add(ramen);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ramens;
    }
}