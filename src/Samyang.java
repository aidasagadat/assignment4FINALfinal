import java.sql.PreparedStatement;
import java.sql.SQLException;

class Samyang extends Ramen{
    public Samyang(int id, String name, int price) throws SQLException {
        super(id, name, price);
    }
    @Override
    public void add() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Buldak (id, name, price, available) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, super.getId());
            preparedStatement.setString(2, super.getName());
            preparedStatement.setInt(3, super.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {}
    }
}
