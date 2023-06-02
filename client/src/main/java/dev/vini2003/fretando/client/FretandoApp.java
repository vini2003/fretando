package dev.vini2003.fretando.client;

import javax.swing.*;
import java.awt.*;

import dev.vini2003.fretando.common.object.Request;
import dev.vini2003.fretando.common.object.controller.RequestController;
import dev.vini2003.fretando.common.object.controller.base.*;

public class FretandoApp {

    private JFrame mainFrame;
    private JList<Request> requestList;
    private JButton createButton;

    public FretandoApp() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Fretando App");
        mainFrame.setSize(400, 200);
        mainFrame.setLayout(new FlowLayout());

        requestList = new JList<>(new DefaultListModel<>());
        requestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        requestList.addListSelectionListener(e -> editRequest(requestList.getSelectedValue()));

        createButton = new JButton("+");
        createButton.addActionListener(e -> createRequest());

        mainFrame.add(requestList);
        mainFrame.add(createButton);
        mainFrame.setVisible(true);
    }

    private void editRequest(Request request) {
        new EditRequestDialog(mainFrame, request).setVisible(true);
        refreshRequests();
    }

    private void createRequest() {
        new CreateRequestDialog(mainFrame).setVisible(true);
        refreshRequests();
    }

    private void refreshRequests() {
        DefaultListModel<Request> model = (DefaultListModel<Request>) requestList.getModel();
        model.clear();
        for (Request request : RequestController.getAll()) {
            model.addElement(request);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FretandoApp().prepareGUI());
    }
}
