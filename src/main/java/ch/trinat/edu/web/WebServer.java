package ch.trinat.edu.web;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class WebServer {
    private static final int fNumberOfThreads = 100;
    private static final Executor fThreadpool = Executors.newFixedThreadPool(fNumberOfThreads);

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        try (ServerSocket socket = new ServerSocket(8083)){
            System.out.println("open browser and enter url: \nhttp://localhost:8083");
            while (true) {
                System.out.println("wait for accept");
                final Socket connection = socket.accept();

                Runnable task = new Runnable() {

                    @Override
                    public void run() {handleRequest(connection);}};

                fThreadpool.execute(task);
            }

        } catch (Exception e) {
            e.printStackTrace();		}			// TODO: handle exception
    }


    public static void handleRequest(Socket s) {
        try (PrintWriter out = new PrintWriter(s.getOutputStream(),true)){
            String webServerAddress = s.getInetAddress().toString();
            System.out.println("New Connection:\n"+webServerAddress);

            printHeader(s);

            System.out.println("HTTP/1.0 200");
            System.out.println("Content-type: text/html");
            System.out.println("Server-name: myJavaServer");

            String response = "<html>\n "
                    + "<head>\n" + "<title>My Java Web Server</title></head>\n" + "<h1>Welcome to my Java Web Server!</h1>\n"
                    + "<p>Server Time: " + getCurrentTimeStamp()
                    + "</p>\n" + "</html>\n";

            System.out.println("Content-length: " + response.length());
            System.out.println("");
            System.out.println(response);
            System.out.flush();
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed respond to client request: " + e.getMessage());
        }
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    private static void printHeader(Socket s) {
        int c;
        StringBuffer raw = new StringBuffer();
        try {

            do {
                c = s.getInputStream().read();
                raw.append((char)c);
            } while (s.getInputStream().available() > 0);
            System.out.println(raw.toString());
        }catch (Exception e) {
            // TODO: handle exception
        }
    }

}