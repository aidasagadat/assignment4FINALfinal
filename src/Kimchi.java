import java.sql.PreparedStatement;
import java.sql.SQLException;

class Kimchi extends Ramen{
    public Kimchi(int id, String name, int price) throws SQLException {
        super(id, name, price);
    }
    @Override
    public void add() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Kimchi (id, name, price, available) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, super.getId());
            preparedStatement.setString(2, super.getName());
            preparedStatement.setInt(3, super.getPrice());
            preparedStatement.setBoolean(4, super.isAvailable());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Kimchi SET name=?, price=? WHERE id=?")) {
            preparedStatement.setString(1, this.getName());
            preparedStatement.setInt(2, this.getPrice());
            preparedStatement.setInt(3, this.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}