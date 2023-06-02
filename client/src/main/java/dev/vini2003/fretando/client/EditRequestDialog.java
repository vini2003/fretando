package dev.vini2003.fretando.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dev.vini2003.fretando.common.object.Request;
import dev.vini2003.fretando.common.object.controller.base.*;

public class EditRequestDialog extends JDialog {
    private JTextField originStreetField, originCityField, destinationStreetField, destinationCityField;
    private JTextField cargoLengthField, cargoWidthField, cargoHeightField, cargoWeightField;
    private JButton updateButton;
    private AbstractController<Request> requestController;
    private Request request;

    public EditRequestDialog(JFrame parent, Request request) {
        super(parent, "Edit Request", true);
        this.requestController = requestController;
        this.request = request;
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

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRequest();
            }
        });

        originStreetField.setText(request.getOrigin().getStreet());
        originCityField.setText(request.getOrigin().getCity());
        destinationStreetField.setText(request.getDestination().getStreet());
        destinationCityField.setText(request.getDestination().getCity());
        cargoLengthField.setText(Double.toString(request.getCargo().getLength()));
        cargoWidthField.setText(Double.toString(request.getCargo().getWidth()));
        cargoHeightField.setText(Double.toString(request.getCargo().getHeight()));
        cargoWeightField.setText(Double.toString(request.getCargo().getWeight()));

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
        add(updateButton);
    }

    private void updateRequest() {
        request.getOrigin().setStreet(originStreetField.getText());
        request.getOrigin().setCity(originCityField.getText());
        request.getDestination().setStreet(destinationStreetField.getText());
        request.getDestination().setCity(destinationCityField.getText());
        request.getCargo().setLength(Double.parseDouble(cargoLengthField.getText()));
        request.getCargo().setWidth(Double.parseDouble(cargoWidthField.getText()));
        request.getCargo().setHeight(Double.parseDouble(cargoHeightField.getText()));
        request.getCargo().setWeight(Double.parseDouble(cargoWeightField.getText()));

        requestController.update(request);
        dispose();
    }
}
