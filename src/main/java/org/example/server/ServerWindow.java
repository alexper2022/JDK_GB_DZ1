package org.example.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import static org.example.service.IOFile.fileRead;
import static org.example.service.IOFile.fileWrite;

public class ServerWindow extends JFrame {
    private static final int WIDTH = 350;
    private static final int HEIGHT = 400;
    private static final String FILE_PATH_DB = "messages.txt";
    private static final Map<String, char[]> usersConnect = new HashMap<>();
    static JTextArea textArea = new JTextArea(1, 9);
    JScrollPane textAreaPanel = new JScrollPane(textArea);
    boolean serverStart = false;
    JButton btnStart = new JButton("Пуск");
    JButton btnStop = new JButton("Стоп");
    JPanel panelButtons = new JPanel(new GridLayout(1, 2));

    public ServerWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int widthSizeSystem = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int heightSizeSystem = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        setLocation(widthSizeSystem - WIDTH - 50, heightSizeSystem - HEIGHT - 50);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        panelButtons.add(btnStart);
        panelButtons.add(btnStop);
        add(panelButtons, BorderLayout.SOUTH);
//        textAreaPanel.add(textArea);
//        textAreaPanel.add(scrollBar);
        add(textAreaPanel);
        setVisible(true);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverStart = true;
                textArea.append("Сервер запущен\n");
                repaint();
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverStart = false;
                textArea.append("Сервер становлен\n");
                repaint();
            }
        });

    }

    public static void sendMessage(String user, String message) {
        textArea.append(user + " : " + message + "\n");
//        ClientWindow.newMessage(user, message);
        fileWrite(FILE_PATH_DB, user + " : " + message + "\n");
    }

    public static String getAllMessages(String user) {
        if (usersConnect.containsKey(user)) {
            return fileRead(FILE_PATH_DB);
        }
        return "Вы не авторизованы";
    }

    public boolean isServerStart() {
        return serverStart;
    }

    public boolean conectServer(String login, char[] password) {
        if (serverStart && !usersConnect.containsKey(login)) {
            usersConnect.put(login, password);
            textArea.append("Подключился пользователь: " + login + "\n");
            repaint();
            return true;
        }
        return false;
    }

    public boolean isConnectUser(String user, char[] password) {
        return usersConnect.containsKey(user);
    }
}
