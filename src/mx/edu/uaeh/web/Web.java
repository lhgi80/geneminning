/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uaeh.web;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Luis
 */
public class Web {
    private String direccionURL;

    public String getDireccionURL() {
        return direccionURL;
    }

    public void setDireccionURL(String direccionURL) {
        this.direccionURL = direccionURL;
    }
    
    public String getWebsiteText() {
        String salida="";
        try {
            URL  url = new URL(direccionURL);
            URLConnection urlc = url.openConnection();
            BufferedInputStream buffer = new BufferedInputStream(urlc.getInputStream());
            StringBuilder builder = new StringBuilder();
            int byteRead;
            while ((byteRead = buffer.read()) != -1)
                builder.append((char) byteRead);

            buffer.close();

            //System.out.println(builder.toString());
            //System.out.println("The size of the web page is " + builder.length() + " bytes.");

            salida=builder.toString();

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return salida;
    }

}
