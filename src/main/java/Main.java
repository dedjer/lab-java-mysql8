import java.sql.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Entering main...");

        Main main = new Main();

        try {
            main.run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void run() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://10.0.1.12:3306/db_example?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true",
                "dbuser",
                "password");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM db_example.vehicle");

        System.out.println("Printing schema for table: " + resultSet.getMetaData().getTableName(1));

        int columnCount = resultSet.getMetaData().getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.println(i + " " + resultSet.getMetaData().getColumnName(i));
        }

        System.out.println("Searching for vehicle.");

        boolean vehicleFound = false;

        while (resultSet.next()) {
            Long vehicle_id = resultSet.getLong("vehicle_id");

            if (vehicle_id == 1) {
                String year = resultSet.getString("year");
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");

                System.out.println("Vehicle found.");

                System.out.println("Vehicle ID: " + vehicle_id);
                System.out.println("Year: " + year);
                System.out.println("Make: " + make);
                System.out.println("Model: " + model);
                vehicleFound = true;

                break;
            }
        }

        if (!vehicleFound) {
            System.out.println("Vehicle not found. Inserting a new user.");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO db_example.vehicle (vehicle_id, year, make, model) VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, 1);
            preparedStatement.setString(2, "year");
            preparedStatement.setString(3, "make");
            preparedStatement.setString(4, "model");
            preparedStatement.executeUpdate();
        }
    }
}
