package tarea5PSP;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {

    Cliente cliente;
    @FXML
    TextField txtPuerto;
    @FXML
    TextArea txtArea;
    @FXML
    TextField txtIP;
    @FXML
    TextField txtAumentarStock;
    @FXML
    TextField txtDisminuirStock;

    @FXML
    private void conectar(ActionEvent event) {

        if (txtIP.getText().equals("localhost")) {

            if (!txtPuerto.getText().equals("")) {
                txtArea.appendText("******************************************\r\n"
                        + //
                        "\r\n"
                        + //
                        "*PSP - Tarea Individual 4 - Cliente / Servidor*\r\n"
                        + //
                        "\r\n"
                        + //
                        "******************************************\r\n"
                        + //
                        "\r\n"
                        + //
                        "* Miriam Gallardo González-Amor *\r\n"
                        + //
                        "\r\n"
                        + //
                        "******************************************\r\n"
                        + //
                        "\r\n"
                        + //
                        "* 53772609N     *                     \r\n");
                try {
                    cliente = new Cliente(txtIP.getText(), Integer.parseInt(txtPuerto.getText()));
                    cliente.start();
                    txtArea.appendText("Conexión establecida con el servidor");

                } catch (Exception exception) {

                }
            }
        } else {
            txtArea.setText("Introduce la IP Correcta");
        }
    }

    @FXML
    private void consultarStock2(ActionEvent event) {
        try {
            cliente.setBotonPulsado(1);
            txtArea.appendText("El stock actual es: " + cliente.getStock());

        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void aumentarStock2(ActionEvent event) {

        try {
            cliente.setBotonPulsado(2);
            int cantidadAumentar = Integer.parseInt(txtAumentarStock.getText());
            cliente.setCantidad(cantidadAumentar);
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void disminuirStock2(ActionEvent event) {
        cliente.setBotonPulsado(3);
        int cantidadDisminuir = Integer.parseInt(txtDisminuirStock.getText());
        try {
            cliente.setCantidad(cantidadDisminuir);
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.setProperty("javax.net.ssl.trustStore", "clienteTrustedCerts.jks");

        System.setProperty("javax.net.ssl.trustStorePassword", "claveClienteSecreta");
    }

}
