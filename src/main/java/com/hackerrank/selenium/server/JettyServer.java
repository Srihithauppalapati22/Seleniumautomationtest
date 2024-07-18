package com.hackerrank.selenium.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyServer {
    private Server server;
    private int port;

    public JettyServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            //1. Creating the server on port 8080
            server = new Server(port);

            //2. Creating the WebAppContext for the created content
            WebAppContext ctx = new WebAppContext();
            ctx.setResourceBase("website");
            ctx.setContextPath("/");

            //3. Setting servlet
            server.setHandler(ctx);

            //4. Starting the Server
            server.start();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}