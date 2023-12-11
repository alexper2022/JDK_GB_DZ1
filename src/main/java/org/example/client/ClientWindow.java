package org.example.client;

import org.example.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.server.ServerWindow.sendMessage;

public class ClientWindow extends JFrame {
    private final int WIDTH = 350;
    private final int HEIGHT = 400;
    JPanel connectPanel = new JPanel(new GridLayout(2, 3));
    JTextField loginField = new JTextField("Login");
    JPasswordField passwordField = new JPasswordField("password");
    JTextField ipField = new JTextField("127.0.0.1");
    JTextField portField = new JTextField("8189");
    JButton btnConnect = new JButton("Connect");
    JPanel chatPanel = new JPanel(new GridLayout(1, 4));
    JTextArea textArea = new JTextArea();
    JScrollPane chatScrollPane = new JScrollPane(textArea);
    JTextField chatTextField = new JTextField();
    JButton btnSendMessage = new JButton("Отправить");
    String user;
    char[] password;

    public ClientWindow(ServerWindow server) {
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int widthSizeSystem = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int heightSizeSystem = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
//        System.out.printf("Разрешение экрана %d x %d\n", widthSizeSystem, heightSizeSystem);
//        System.out.printf("Окно клиента создано с координатами X=%d Y=%d \n", widthSizeSystem / 2 - WIDTH / 2, heightSizeSystem / 2 - HEIGHT / 2);
        setLocation(widthSizeSystem / 2 - WIDTH / 2, heightSizeSystem / 2 - HEIGHT / 2);
        setSize(WIDTH, HEIGHT);
        connectPanel.add(ipField);
        connectPanel.add(portField);
        connectPanel.add(new JLabel());
        connectPanel.add(loginField);
        connectPanel.add(passwordField);
        connectPanel.add(btnConnect);
        add(connectPanel, BorderLayout.NORTH);
        add(chatScrollPane);
        chatPanel.add(chatTextField);
        chatPanel.add(btnSendMessage);
        add(chatPanel, BorderLayout.SOUTH);
        chatPanel.setVisible(false);
        setResizable(false);
        setVisible(true);

        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.isServerStart() && server.conectServer(loginField.getText(), passwordField.getPassword())) {
                    user = loginField.getText();
                    password = passwordField.getPassword();
                    connectPanel.setVisible(false);
                    add(chatPanel, BorderLayout.SOUTH);
                    chatPanel.setVisible(true);
                    repaint();
                    textArea.append(server.getAllMessages(user));
//                    textArea.append("Cоединение успешно установлено!\n");
                } else {
                    textArea.append("Cоединение не установлено!\n");
                }
            }

        });
//        textArea.addAncestorListener();
        btnSendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.isServerStart() && server.isConnectUser(user, password)) {
                    sendMessage(user, chatTextField.getText());
//                    textArea.setText("");
                    textArea.removeAll();
                    chatTextField.setText("");
                    textArea.append(server.getAllMessages(user));
                    repaint();
                } else {
                    textArea.append("Cоединение не установлено!\n");
                }

            }
        });
    }
}

