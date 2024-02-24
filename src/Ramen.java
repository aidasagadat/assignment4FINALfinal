import org.postgresql.util.PSQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;

class Ramen{
    private String name;
    private int id;
    private int price;
    private boolean available;
    Connection connection;
    public Ramen() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(InputMismatchException e){
            System.out.println("Id and price should contain only numbers, try again!");
        }
    }
    public Ramen(int id, String name, int price){
        this();
        setId(id);
        setName(name);
        setPrice(price);
        setAvailable(true);
    }
    public void setId(int id) {
        this.id=id;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public void add() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders (id,name,price) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, price);
            updateRamenAvailability(getId(), false);
            preparedStatement.executeUpdate();
        } catch (PSQLException e){
            System.out.println("Please enter a new ID, it's already in our base");
        } catch (SQLException e) {
            System.out.println("something wrong");
        }
    }
    public void returnRamen() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM orders WHERE id=?")) {
            preparedStatement.setInt(1, getId());
            preparedStatement.executeUpdate();
            updateRamenAvailability(id, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void updateRamenAvailability(int ramenID, boolean available) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE buldak SET available=? WHERE buldak.id=?;" +
                "UPDATE samyang SET available=? WHERE samyang.id=?;" +
                "UPDATE seafood SET available=? WHERE seafood.id=?;" +
                "UPDATE kimchi SET available=? WHERE kimchi.id=?;" +
                "UPDATE neogur SET available=? WHERE neogur.id=?;")) {
            preparedStatement.setBoolean(1, available);
            preparedStatement.setInt(2, ramenID);
            preparedStatement.setBoolean(3, available);
            preparedStatement.setInt(4, ramenID);
            preparedStatement.setBoolean(5, available);
            preparedStatement.setInt(6, ramenID);
            preparedStatement.setBoolean(7, available);
            preparedStatement.setInt(8, ramenID);
            preparedStatement.setBoolean(9, available);
            preparedStatement.setInt(10, ramenID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteRamen() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM buldak WHERE id=?;" +
                "DELETE FROM samyang WHERE id=?;" +
                "DELETE FROM seafood WHERE id=?;" +
                "DELETE FROM kimchi WHERE id=?;" +
                "DELETE FROM neogur WHERE id=?;")) {
            preparedStatement.setInt(1, getId());
            preparedStatement.setInt(2, getId());
            preparedStatement.setInt(3, getId());
            preparedStatement.setInt(4, getId());
            preparedStatement.setInt(5, getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
