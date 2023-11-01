package com.c195_software_ii__advanced_java_concepts_pa.Utilities;

import com.c195_software_ii__advanced_java_concepts_pa.DAO.AppointmentDBImpl;
import com.c195_software_ii__advanced_java_concepts_pa.Models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class LoginAlert {

    public static void LoginAlert() throws SQLException {

        // Get Local Date and Time
        LocalDate localDate = LocalDateTime.now(ZoneId.systemDefault()).toLocalDate();
        LocalTime localTime = LocalDateTime.now(ZoneId.systemDefault()).toLocalTime();

        // Declare and get all appointments
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        allAppointments = AppointmentDBImpl.getAllAppointments();

        // Declare Appointment Variables
        int appointmentIDAlert = 0;
        LocalDate appointmentDateAlert = null;
        LocalTime appointmentTimeAlert = null;
        long timeDifference = 20;

        // Declare alertString for Alert
        String alertString;

        for(Appointment appointment : allAppointments) {
            LocalDate appointmentDate = appointment.getStartTime().toLocalDateTime().toLocalDate();
            LocalTime appointmentTime = appointment.getStartTime().toLocalDateTime().toLocalTime();

            if ((appointmentDate == localDate) && (ChronoUnit.MINUTES.between(appointmentTime, localTime) < timeDifference))
                timeDifference = ChronoUnit.MICROS.between(appointmentTime, localTime);
                appointmentIDAlert = appointment.getAppointmentID();
                appointmentDateAlert = appointmentDate;
                appointmentTimeAlert = appointmentTime;
            }
        if (timeDifference <= 15) { // Appointment within 15 minutes
            alertString = "Upcoming appointment in " + timeDifference + " minutes\n"
                    + "Appointment ID: " + appointmentIDAlert + "\n"
                    + "Date: " + appointmentDateAlert + "\n"
                    + "Time: " + appointmentTimeAlert;
        }
        else { // no upcoming appointment
            alertString = "There are no upcoming appointments";
        }

        //Issue alert Prompt
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertString);
        alert.showAndWait();

        // Prevents Table list duplication bug
        allAppointments.removeAll();
    }

}