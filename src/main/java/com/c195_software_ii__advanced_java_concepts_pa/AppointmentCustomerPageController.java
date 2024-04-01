package com.c195_software_ii__advanced_java_concepts_pa;

import com.c195_software_ii__advanced_java_concepts_pa.DAO.*;
import com.c195_software_ii__advanced_java_concepts_pa.Models.Appointment;
import com.c195_software_ii__advanced_java_concepts_pa.Models.Contact;
import com.c195_software_ii__advanced_java_concepts_pa.Models.Customer;
import com.c195_software_ii__advanced_java_concepts_pa.Models.FirstLevelDivision;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppointmentCustomerPageController implements Initializable {

    /* --Stage Declarations-- */
    Stage  stage;
    Parent scene;

    /* --Appointments List Declarations-- */
    ObservableList<Contact>     contactList  = FXCollections.observableArrayList();
    ObservableList<Appointment> appointmentList          = FXCollections.observableArrayList();
    ObservableList<Appointment> monthlyTableAppointments = FXCollections.observableArrayList();
    ObservableList<Appointment> weeklyTableAppointments  = FXCollections.observableArrayList();

    /* --Customer List Declarations-- */

    ObservableList<Customer>           customerList = FXCollections.observableArrayList();
    ObservableList<FirstLevelDivision> divisionList = FXCollections.observableArrayList();

    DateTimeFormatter tableFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a v");

    /* --Appointment FXML Declarations-- */
    @FXML private Tab          appointmentsTab;
    @FXML private ToggleGroup  MonthlyWeeklyTG;
    @FXML private ToggleButton monthlyViewButton;
    @FXML private ToggleButton weeklyViewButton;
    @FXML private Label        weekMonthLabel;
    @FXML private DatePicker   datePicker;
    @FXML private TableView  <Appointment>          appointmentTableView;
    @FXML private TableColumn<Appointment, Integer> appointmentIDCol;
    @FXML private TableColumn<Appointment, String>  titleCol;
    @FXML private TableColumn<Appointment, String>  descriptionCol;
    @FXML private TableColumn<Appointment, String>  locationCol;
    @FXML private TableColumn<Appointment, String>  contactCol;
    @FXML private TableColumn<Appointment, String>  typeCol;
    @FXML private TableColumn<Appointment, String>  startDateTimeCol;
    @FXML private TableColumn<Appointment, String>  endDateTimeCol;
    @FXML private TableColumn<Appointment, Integer> customerIDCol;
    @FXML private TableColumn<Appointment, Integer> UserIDCol;
    @FXML private Button newAppointmentButton;
    @FXML private Button updateAppointmentButton;
    @FXML private Button deleteAppointmentButton;
    @FXML private Button appointmentlogoutButton;

    /* --Customer FXML Declarations-- */
    @FXML private Tab customersTab;
    @FXML private TableView  <Customer>          customerTableView;
    @FXML private TableColumn<Customer, Integer> customerCustomerIDCol;
    @FXML private TableColumn<Customer, String>  customerCustomerNameCol;
    @FXML private TableColumn<Customer, String>  customerCustomerAddressCol;
    @FXML private TableColumn<Customer, String>  customerPostalCodeCol;
    @FXML private TableColumn<Customer, String>  customerPhoneNumberCol;
    @FXML private TableColumn<Customer, Integer> customerDivisionIDCol;
    @FXML private Button newCustomerButton;
    @FXML private Button updateCustomerButton;
    @FXML private Button deleteCustomerButton;
    @FXML private Button customerLogoutButton;

    /* --Appointment Tab-- */
    @FXML
    void onActionMonthlyViewButton(ActionEvent event) {
        monthlyViewButton.setSelected(true); // Prevents button from being un-clicked
    }

    @FXML
    void onActionWeeklyViewButton(ActionEvent event) {
        weeklyViewButton.setSelected(true); // Prevents button from being un-clicked
    }

    /**
     * Enables and disables appointment update and delete buttons if there's an active selection or not.
     * @param event Mouse click event
     */
    @FXML
    public void onMouseTableClick(MouseEvent event) {
        if (!(appointmentTableView.getSelectionModel().getSelectedCells().isEmpty())) { // If selection made, enable buttons
            updateAppointmentButton.setDisable(false);
            deleteAppointmentButton.setDisable(false);
        }
        else { // if no selection, disable buttons
            updateAppointmentButton.setDisable(true);
            deleteAppointmentButton.setDisable(true);
        }
    }

    @FXML
    void onActionCreateNewAppointment(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("Appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Appointment.fxml"));
        loader.load();

        AppointmentController appointmentController = loader.getController();
        appointmentController.sendAppointment(appointmentTableView.getSelectionModel().getSelectedItem());

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            AppointmentDBImpl.deleteAppointment(appointmentTableView.getSelectionModel().getSelectedItem().getAppointmentID());

            updateAppointmentButton.setDisable(true);
            deleteAppointmentButton.setDisable(true);
            appointmentList.clear();
            appointmentList.setAll(AppointmentDBImpl.getAllAppointments());
            appointmentTableView.refresh();

        }
    }

    @FXML
    void onActionAppointmentLogout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to logout?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("UserLoginForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

            // Disables Confirmation dialog when X button pressed after logout.
            stage.setOnCloseRequest(event1 -> {
                JDBC.closeConnection();
                System.exit(0);
            });
        }
    }

    /* --Customer Tab-- */
    private void addDivisionName(ObservableList<Customer> customerList, ObservableList<FirstLevelDivision> divisionList) {
        for (Customer customer : customerList) {
            for (FirstLevelDivision division : divisionList) {
                if (customer.getDivisionID() == division.getDivisionID()) {
                    customer.setDivisionName(division.getDivisionName());
                }
            }
        }
    }

    /**
     * Enables and disables customer update and delete buttons if there's an active selection or not.
     * @param event Mouse click event
     */
    @FXML
    public void onMouseListClick(MouseEvent event) {
        if (!(customerTableView.getSelectionModel().getSelectedItems().isEmpty())) {
            updateCustomerButton.setDisable(false);
            deleteCustomerButton.setDisable(false);
        }
        else {
            updateCustomerButton.setDisable(true);
            deleteCustomerButton.setDisable(true);
        }
    }

    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Customer.fxml"));
        loader.load();

        CustomerController customerController = loader.getController();
        customerController.sendCustomer(customerTableView.getSelectionModel().getSelectedItem());

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this customer and all associated appointments?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            for (Appointment appointment : appointmentList) {
                if (appointment.getCustomerID() == customerTableView.getSelectionModel().getSelectedItem().getCustomerID()) {
                    AppointmentDBImpl.deleteAppointment(appointment.getAppointmentID());
                }
            }
            CustomerDBImpl.deleteCustomer(customerTableView.getSelectionModel().getSelectedItem().getCustomerID());

            updateCustomerButton.setDisable(true);
            deleteCustomerButton.setDisable(true);
            appointmentList.clear();
            customerList.clear();
            appointmentList.setAll(AppointmentDBImpl.getAllAppointments());
            customerList.setAll(CustomerDBImpl.getAllCustomers());
            appointmentTableView.refresh();
            customerTableView.refresh();
        }
    }

    @FXML
    void onActionCustomerLogout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to logout?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("UserLoginForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

            // Disables Confirmation dialog when X button pressed after logout.
            stage.setOnCloseRequest(event1 -> {
                JDBC.closeConnection();
                System.exit(0);
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            // clear tableAppointments list then retrieve upon initializing controller
            appointmentList.clear();
            contactList.clear();
            appointmentList.setAll(AppointmentDBImpl.getAllAppointments());
            contactList.setAll(ContactDBImpl.getContacts());
            appointmentTableView.setItems(appointmentList);

            // Fill appointmentTableView Columns
            appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            titleCol        .setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionCol  .setCellValueFactory(new PropertyValueFactory<>("description"));
            locationCol     .setCellValueFactory(new PropertyValueFactory<>("location"));
            typeCol         .setCellValueFactory(new PropertyValueFactory<>("type"));
            customerIDCol   .setCellValueFactory(new PropertyValueFactory<>("customerID"));
            UserIDCol       .setCellValueFactory(new PropertyValueFactory<>("userID"));

            contactCol.setCellValueFactory(cellData -> {
                for (Contact contact : contactList) {
                    if (cellData.getValue().getContactID() == contact.getContactID()) {
                        return new SimpleStringProperty(contact.getContactName());
                    }
                }
                return null;
            });

            startDateTimeCol.setCellValueFactory(cellData -> {
                String formattedStartTime = cellData.getValue().getDateTimeStart().withZoneSameInstant(ZoneId.systemDefault()).format(tableFormat);
                return new SimpleStringProperty(formattedStartTime);
            });

            endDateTimeCol.setCellValueFactory(cellData -> {
                String formattedEndTime = cellData.getValue().getDateTimeEnd().withZoneSameInstant(ZoneId.systemDefault()).format(tableFormat);
                return new SimpleStringProperty(formattedEndTime);
            });


            // clear customerList then retrieve customers to customerList upon initialize
            // Name of customer's FirstLevelDivision for user-friendliness.
            customerList.clear();
            divisionList.clear();
            customerList.setAll(CustomerDBImpl.getAllCustomers());
            divisionList.setAll(FirstLevelDivisionDBImpl.getFirstLevelDivisions());
            addDivisionName(customerList, divisionList);
            customerTableView.setItems(customerList);

            // insert customer names into customerName list from customerList
            customerCustomerIDCol     .setCellValueFactory(new PropertyValueFactory<>("customerID"));
            customerCustomerNameCol   .setCellValueFactory(new PropertyValueFactory<>("customerName"));
            customerCustomerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            customerPostalCodeCol     .setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            customerPhoneNumberCol    .setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            customerDivisionIDCol     .setCellValueFactory(new PropertyValueFactory<>("divisionName"));

        // if any queries fail.
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
