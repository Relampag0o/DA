package org.example;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PrimaryController {
    public Connection c;
    DatabaseMetaData databaseMetaData;

    @FXML
    ComboBox comboBox;
    @FXML
    TableView tableV;

    @FXML
    TextField selectField;

    @FXML
    TextField whereField;


    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }


    public void initialize() {
        openConnection();
        fillCombo();


    }

    public void openConnection() {
        String db = "w3schools";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mariadb://" + host + ":" + port;
        String user = "root";
        String password = "5856101097";
        try {
            this.c = DriverManager.getConnection(urlConnection, user, password);
            System.out.println("Connected");
            databaseMetaData = c.getMetaData();

        } catch (SQLException e) {
            //showSQLError(e);
            e.printStackTrace();
        }
    }

    public void fillCombo() {
        Statement st = null;
        try {
            st = c.createStatement();
            st.executeQuery("USE " + "w3schools");
            ResultSet tables = databaseMetaData.getTables("w3schools", null, null, null);
            while (tables.next()) {
                comboBox.getItems().add(tables.getString("TABLE_NAME"));
            }


            st.close();
            tables.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void submit() {
        Statement st = null;
        tableV.getColumns().clear();
        try {
            st = c.createStatement();
            if (comboBox.getValue() != null) {
                String table = comboBox.getValue().toString();
                String[] selectParts = selectField.getText().split(",");
                // controlling the columns that will be displayed based on the select query:
                if (selectParts[0].contains("*")) {
                    ResultSet columnNames = databaseMetaData.getColumns("w3schools", null, table, null);
                    while (columnNames.next()) {
                        TableColumn<Object, Object> column = new TableColumn<>(columnNames.getString("COLUMN_NAME"));
                        column.setPrefWidth(88);
                        tableV.getColumns().add(column);

                    }
                    // making sure users don't need to add the '' on the whereClause:
                    String whereClause = whereField.getText();
                    String[] parts = whereClause.split("=");
                    whereClause = parts[0] + "'" + parts[1] + "'";
                    String sql = "SELECT " + selectField.getText() + " FROM " + table + " WHERE " + whereField.getText() + ";";
                    ResultSet query = st.executeQuery(sql);
                    ResultSetMetaData meta = query.getMetaData();
                    while (query.next()) {
                        List<Object> rowData = new ArrayList<>();
                        for (int i = 1; i <= meta.getColumnCount(); i++) {
                            rowData.add(query.getObject(i));
                        }
                        tableV.getItems().add(rowData);
                    }

                } else
                    System.out.println("..");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


}
