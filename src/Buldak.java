import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;

class Buldak extends Ramen{
    public Buldak(int id, String name, int price){
        super(id, name, price);
    }
    @Override
    public void add() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Buldak (id, name, price, available) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, super.getId());
            preparedStatement.setString(2, super.getName());
            preparedStatement.setInt(3, super.getPrice());
            preparedStatement.setBoolean(4, super.isAvailable());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(InputMismatchException e){
            System.out.println("Id and price should contain only numbers, try again!");
        }
    }
    public void update() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Buldak SET name=?, price=? WHERE id=?")) {
            preparedStatement.setString(1, this.getName());
            preparedStatement.setInt(2, this.getPrice());
            preparedStatement.setInt(3, this.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}