package dev.vini2003.fretando.client;

import dev.vini2003.fretando.common.object.Address;
import dev.vini2003.fretando.common.object.Cargo;
import dev.vini2003.fretando.common.object.Request;
import dev.vini2003.fretando.common.object.controller.RequestController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateRequestDialog extends JDialog {
    private JTextField originStreetField, originCityField, destinationStreetField, destinationCityField;
    private JTextField cargoLengthField, cargoWidthField, cargoHeightField, cargoWeightField;
    private JButton createButton;

    public CreateRequestDialog(JFrame parent) {
        super(parent, "Create Request", true);

        setSize(300, 400);
        setLayout(new FlowLayout());

        originStreetField = new JTextField(20);
        originCityField = new JTextField(20);
        destinationStreetField = new JTextField(20);
        destinationCityField = new JTextField(20);
        cargoLengthField = new JTextField(20);
        cargoWidthField = new JTextField(20);
        cargoHeightField = new JTextField(20);
        cargoWeightField = new JTextField(20);

        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRequest();
            }
        });

        add(new JLabel("Origin Street:"));
        add(originStreetField);
        add(new JLabel("Origin City:"));
        add(originCityField);
        add(new JLabel("Destination Street:"));
        add(destinationStreetField);
        add(new JLabel("Destination City:"));
        add(destinationCityField);
        add(new JLabel("Cargo Length:"));
        add(cargoLengthField);
        add(new JLabel("Cargo Width:"));
        add(cargoWidthField);
        add(new JLabel("Cargo Height:"));
        add(cargoHeightField);
        add(new JLabel("Cargo Weight:"));
        add(cargoWeightField);
        add(createButton);
    }

    private void createRequest() {
        Address origin = new Address();
        origin.setStreet(originStreetField.getText());
        origin.setCity(originCityField.getText());

        Address destination = new Address();
        destination.setStreet(destinationStreetField.getText());
        destination.setCity(destinationCityField.getText());

        Cargo cargo = new Cargo();
        cargo.setLength(Double.parseDouble(cargoLengthField.getText()));
        cargo.setWidth(Double.parseDouble(cargoWidthField.getText()));
        cargo.setHeight(Double.parseDouble(cargoHeightField.getText()));
        cargo.setWeight(Double.parseDouble(cargoWeightField.getText()));

        Request request = new Request();
        request.setOrigin(origin);
        request.setDestination(destination);
        request.setCargo(cargo);

        RequestController.create(request);
        dispose();
    }
}
