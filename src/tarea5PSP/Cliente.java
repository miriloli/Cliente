package tarea5PSP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Cliente extends Thread {

    private String direccionIP;
    private int puerto;
    private DataInputStream in;
    private DataOutputStream out;
    private int stock;
    private int botonPulsado;
    private int cantidad;

    public Cliente(String direccionIP, int puerto) {
        this.direccionIP = direccionIP;
        this.puerto = puerto;
        this.stock = 0;

    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int opcionElegida) {
        this.cantidad = opcionElegida;
    }

    public int getBotonPulsado() {
        return botonPulsado;
    }

    public void setBotonPulsado(int botonPulsado) {
        this.botonPulsado = botonPulsado;
    }

    public void run() {
        try {
            //Creamos un socket seguro para establecer la conexion con el servidor
            SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) socketFactory.createSocket(direccionIP, puerto);
            boolean conectado=true;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            while (conectado==true) {
                if (botonPulsado == 1) {
                    solicitudConsultarStock();
                    botonPulsado=0;
                }
                else if(botonPulsado==2){
                        solicitudAumentarStock();
                        botonPulsado=0;
                        }
                else if(botonPulsado==3) {
                    solicitudDisminuirStock();
                    botonPulsado=0;
                }
            }
        } catch (Exception exception) {

        }

    }

    public void solicitudAumentarStock() throws IOException {
        out.writeInt(cantidad);
        stock = in.readInt();
    }

    public void solicitudDisminuirStock() throws IOException {
        out.writeInt(-cantidad);
        stock = in.readInt();
    }

    public void solicitudConsultarStock() throws IOException {
        out.writeInt(0);
        stock = in.readInt();
    }

    public int getStock() {
        return stock;
    }

}
