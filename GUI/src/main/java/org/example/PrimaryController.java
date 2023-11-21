package org.example;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class PrimaryController {
    public Connection c;
    DatabaseMetaData databaseMetaData;

    @FXML
    ComboBox comboBox;
    @FXML
    TableView tableV;
    private ObservableList<ObservableList<Object>> data = FXCollections.observableArrayList();


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
        String urlConnection = "jdbc:mariadb://" + host + ":" + port + "?AllowMultiQueries=True";
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
        data.clear();
        try {
            st = c.createStatement();
            if (comboBox.getValue() != null) {
                String table = comboBox.getValue().toString();
                String[] selectParts = selectField.getText().split(",");
                // controlling the columns that will be displayed based on the select query:
                if (selectParts[0].contains("*")) {
                    ResultSet columnNames = databaseMetaData.getColumns("w3schools", null, table, null);
                    while (columnNames.next()) {
                        TableColumn<ObservableList<Object>, Object> column = new TableColumn<>(columnNames.getString("COLUMN_NAME"));

                        // i copied this code from the AI. got no clue about how it works.
                        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<Object>, Object>, ObservableValue<Object>>() {
                            @Override
                            public ObservableValue<Object> call(TableColumn.CellDataFeatures<ObservableList<Object>, Object> features) {
                                int index = tableV.getColumns().indexOf(column);
                                if (index >= 0 && index < features.getValue().size()) {
                                    return new ReadOnlyObjectWrapper<>(features.getValue().get(index));
                                } else {
                                    return new ReadOnlyObjectWrapper<>(null);
                                }
                            }
                        });

                        column.setPrefWidth(88);
                        tableV.getColumns().add(column);
                    }

                    String sql = "";
                    if (whereField.getText().isEmpty())
                        sql = "SELECT " + selectField.getText() + " FROM " + table + ";";
                    else
                        sql = "SELECT " + selectField.getText() + " FROM " + table + " WHERE " + whereField.getText() + ";";

                    System.out.println(sql);
                    ResultSet query = st.executeQuery(sql);
                    ResultSetMetaData meta = query.getMetaData();
                    while (query.next()) {
                        ObservableList<Object> rowData = FXCollections.observableArrayList();
                        for (int i = 1; i <= meta.getColumnCount(); i++) {
                            System.out.println(query.getObject(i));
                            rowData.add(query.getObject(i));
                        }
                        data.add(rowData);
                    }
                    tableV.setItems(data);

                } else {
                    String sql = "SELECT " + selectField.getText() + " FROM " + table;
                    if (!whereField.getText().isEmpty()) {
                        sql += " WHERE " + whereField.getText() + ";";
                    }
                    System.out.println("THIS IS THE SECOND CASE SOUT" + sql);
                    ResultSet query = st.executeQuery(sql);
                    ResultSetMetaData meta = query.getMetaData();

                    for (int i = 1; i <= meta.getColumnCount(); i++) {
                        final int columnIndex = i;

                        TableColumn<ObservableList<Object>, Object> column = new TableColumn<>(meta.getColumnName(i));

                        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<Object>, Object>, ObservableValue<Object>>() {
                            @Override
                            public ObservableValue<Object> call(TableColumn.CellDataFeatures<ObservableList<Object>, Object> features) {
                                return new ReadOnlyObjectWrapper<>(features.getValue().get(columnIndex - 1));
                            }
                        });

                        column.setPrefWidth(88);
                        tableV.getColumns().add(column);
                    }

                    while (query.next()) {
                        ObservableList<Object> row = FXCollections.observableArrayList();
                        for (int j = 1; j <= meta.getColumnCount(); j++) {
                            row.add(query.getObject(j));
                        }
                        tableV.getItems().add(row);
                    }

                    query.close();
                    st.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();


        }

    }
}
