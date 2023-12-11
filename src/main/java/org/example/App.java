package org.example;


import org.example.client.ClientWindow;
import org.example.server.ServerWindow;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ServerWindow server = new ServerWindow();
        new ClientWindow(server);
        new ClientWindow(server);
    }
}
