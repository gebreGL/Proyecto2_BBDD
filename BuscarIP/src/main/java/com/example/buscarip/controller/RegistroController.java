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
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.*;

/**
 * Clase que controla la interfaz gráfica de registro de usuarios.
 * @author Pablo & Gebre
 */
public class RegistroController implements Initializable{

    @FXML
    private Stage stage;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContra;

    @FXML
    private PasswordField txtContra2;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtCorreo;


    /**
     * Método público que ingresa a un nuevo usuario en la base de datos.
     * @param actionEvent evento que se ejecuta para poder llevar a cabo el método.
     * @throws IOException
     */
    public void registro(ActionEvent actionEvent) throws IOException {

        final String user = "root";
        final String url= "jdbc:mysql://localhost:3306/CREDENCIALES";
        final String pass  = "root";

        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            if ((!txtUsuario.getText().isEmpty() && !txtContra.getText().isEmpty()) &&
                    (txtContra.getText().equals(txtContra2.getText())) && !txtNombre.getText().isEmpty()
                    && !txtApellidos.getText().isEmpty() && !txtCorreo.getText().isEmpty()) {

                boolean seguir = true;
                boolean comprobar = false;

                con = DriverManager.getConnection(url, user, pass);
                stm = con.createStatement();
                rs = stm.executeQuery("SELECT * FROM DATOS_C;");

                while (rs.next() && seguir) {
                    if (txtUsuario.getText().equals(rs.getString(1))) {
                        JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre",
                                "AVISO", JOptionPane.WARNING_MESSAGE);
                        seguir = false;
                        comprobar = true;
                    } else if (txtCorreo.getText().equals(rs.getString(5))) {
                        JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese correo",
                                "AVISO", JOptionPane.WARNING_MESSAGE);
                        seguir = false;
                        comprobar = true;
                    }
                }

                if (!comprobar) {

                    con = DriverManager.getConnection(url, user, pass);
                    stm = con.createStatement();
                    stm.executeUpdate("INSERT INTO DATOS_C VALUES ('"+ txtUsuario.getText() +"','"+ cifra(txtContra.getText(), 6)
                            +"','"+ txtNombre.getText() +"','"+ txtApellidos.getText() +"','"+ txtCorreo.getText() +"');");

                    JOptionPane.showMessageDialog(null, "Credenciales válidas",
                            "Validado", JOptionPane.INFORMATION_MESSAGE);
                    Node source = (Node) actionEvent.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                    Stage st = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginview.fxml"));
                    Scene scene3 = new Scene(fxmlLoader.load());
                    st.setScene(scene3);
                    st.show();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Credenciales no válidas",
                        "AVISO", JOptionPane.WARNING_MESSAGE);
            }
        } catch (HeadlessException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Método público que cifra una cadena de texto tomando la posición indicada
     * en la variable del segundo parámetro del método en función del abecedario en inglés.
     * @param texto variable de servicio String que almacena un texto a cifrar.
     * @param codigo variable de servicio int que establece el número de posiciones
     *        que se rota en función de las posiciones del abecedario inglés.
     * @return devuelve un String cifrado.
     */
    public static String cifra(String texto, int codigo) {
        StringBuilder cifrado = new StringBuilder();
        codigo = codigo % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) + codigo) > 'z') {
                    cifrado.append((char) (texto.charAt(i) + codigo - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + codigo));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) + codigo) > 'Z') {
                    cifrado.append((char) (texto.charAt(i) + codigo - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + codigo));
                }
            }
        }
        return cifrado.toString();
    }

    /**
     * Método publico que permite cambiar a la interfaz gráfica del login.
     * @param actionEvent evento que se ejecuta para poder llevar a cabo el método.
     * @throws Throwable
     */
    public void irInicio(ActionEvent actionEvent) throws Throwable {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Stage st = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginview.fxml"));
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
}
