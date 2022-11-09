package com.example.buscarip.controller;

import com.example.buscarip.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author Pablo & Gebre
 * Clase pública creada para controlar y responder a las interacciones
 * que hace el usuario en la interfaz gráfica asociada ("loginview.fxml").
 */
public class LoginController implements Initializable{

    /**
     * Variable privada Stage perteneciente a JavaFX asociada al escenario de la interfaz.
     */
    @FXML
    private Stage stage = new Stage();

    /**
     * Variable privada TextField perteneciente a JavaFX asociada al texto reservado
     * para el nombre de usuario en el log-in.
     */
    @FXML
    private TextField txtUsuario;

    /**
     * Variable privada PasswordField perteneciente a JavaFX asociada al texto reservado
     * para la contraseña del usuario en el log-in.
     */
    @FXML
    private PasswordField txtPass;


    /**
     * Método público que se encarga de darle un valor a un atributo de tipo Stage.
     * @param stage variable de servicio Stage.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Método público que descifra una cadena de texto retomando la posición original de las letras
     * de la cadena en función del abecedario en inglés.
     * @param texto variable de servicio String que almacena un texto a descifrar.
     * @param codigo variable de servicio int que establece el número de posiciones
     *               que se rota en función de las posiciones del abecedario inglés.
     * @return devuelve un String descifrado.
     */
    public static String descifra(String texto, int codigo) {
        StringBuilder cifrado = new StringBuilder();
        codigo = codigo % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) - codigo) < 'a') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) - codigo) < 'A') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            }
        }
        return cifrado.toString();
    }

    /**
     * Método público que permite cambiar a la interfaz gráfica de registro.
     * @param actionEvent evento que se ejecuta para poder llevar a cabo el método.
     * @throws IOException
     */
    public void irRegistroVent(ActionEvent actionEvent) throws IOException {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Stage st = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("registroview.fxml"));
        Scene scene3 = new Scene(fxmlLoader.load());
        st.setScene(scene3);
        st.show();
    }

    /**
     * Método público que es el primero en ejecutarse dentro de la clase, y lo hace d forma automática.
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    /**
     * Método público que permite cambiar a la interfaz gráfica del login.
     * @param evento evento que se ejecuta para poder llevar a cabo el método.
     * @throws IOException
     * @throws SQLException
     */
    public void login(ActionEvent evento) throws IOException, SQLException {
        String usuarioCliente = txtUsuario.getText();
        String passCliente = txtPass.getText();

        final String user = "root";
        final String url= "jdbc:mysql://localhost:3306/CREDENCIALES";
        final String pass  = "root";

        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(url, user, pass);
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM DATOS_C;");

            if (!usuarioCliente.isEmpty() && !passCliente.isEmpty()) {
                boolean seguir = true;
                boolean comprobar = false;

                while (rs.next() && seguir) {
                    if (rs.getString(1).equals(usuarioCliente) && descifra(rs.getString(2), 6).equals(passCliente)) {
                        JOptionPane.showMessageDialog(null, "Validado correctamente",
                                "Validación", JOptionPane.INFORMATION_MESSAGE);
                        Node source = (Node) evento.getSource();
                        Stage stage = (Stage) source.getScene().getWindow();
                        stage.close();
                        Stage st = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainview.fxml"));
                        Scene scene4 = new Scene(fxmlLoader.load());
                        st.setScene(scene4);
                        st.show();
                        seguir = false;
                        comprobar = true;
                    }
                }
                if (!comprobar) {
                    JOptionPane.showMessageDialog(null, "Credenciales inválidas",
                            "AVISO", JOptionPane.WARNING_MESSAGE);
                }


            } else {
                JOptionPane.showMessageDialog(null, "ERROR: No coinciden dichos campos", "AVISO", JOptionPane.WARNING_MESSAGE);
            }
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
    }
}
