package com.example.buscarip.controller;

import com.example.buscarip.Exports;
import com.example.buscarip.model.*;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IpController implements Initializable {
    //La API  que utilizaremos sirve para geolocalizar una IP y saber donde se ubica
    private String finalURL = "https://ipwho.is/";
    // Importaremos todos los botones , labels tablas y el combobox .
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnGuardar ;
    @FXML
    private  TextField txtNombreArchivo ;
    @FXML
    private ObservableList<Root> tableIps;
    private ObservableList<Flag> tableIpsBanderas;
    @FXML
    private TableView tableView;
    // Las columnas , son esenciales ya que sin ellas no podremos añadirle valores a la Tabla . En nuestro caso añadiremos una columna de Ip , dirección y Código Postal .
    @FXML
    private TableColumn<Root,String> colDireccion = new TableColumn<>("Direccion");
    @FXML
    private TableColumn<Flag,String> colPostal = new TableColumn<>("img");
    @FXML
    private  TableColumn<Root,String> colCiudad = new TableColumn<>("Ciudad");
    //Arraylist de Ips para guardar todas las Ips que vaya añadiendo el usuario .
    public static ArrayList<Root> ips = new ArrayList<>();
    // El observable es como un ArrayList , pero para la clase FX
    public static ArrayList<String> direccionIp = new ArrayList<>() ;
    ObservableList<String> opcionesList ;
    ObservableList<String> ipList ;
    // En este observable guardaremos las opciones del combo que son xml , txt , bin , json
    // y todo para guardar todos los archivos
    @FXML
    ArrayList<String> opciones= new ArrayList<>(Arrays.asList("XML","TXT","BIN","JSON","TODO")) ;
    @FXML
    private TextField txtbuscarIp;
    @FXML
    private Label lblPais;
    @FXML
    private Label lblCodigoPostal;
    @FXML
    private Label lblLatitud;
    @FXML
    private Label lblLongitud;
    @FXML
    private Label lblRegion;
    @FXML
    private Label lblCapital;
    @FXML
    private Label lblPrefijo;
    @FXML
    private Label lblCity;
    @FXML
    private Label lblDireccion;
    @FXML
    private Root ip = new Root();
    @FXML
    ComboBox select = new ComboBox() ;
    @FXML
    ComboBox selectIp = new ComboBox() ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection con = null;
            String url1= "jdbc:mysql://localhost:3306/BD_ips";
            String user= "root";
            String password  = "root";
            // create a connection to the database
            con = DriverManager.getConnection(url1, user, password);
            Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM datos");
            while (rs.next()){
                String direccionIp = rs.getString("IP") ;
                String codePostal = rs.getString("POSTAL") ;
                String city = rs.getString("ciudad") ;
                String country  = rs.getString("PAIS") ;
                String  callingCode = rs.getString("prefijo") ;
                String capital = rs.getString("capital") ;
                Double latitude  = rs.getDouble("latitud") ;
                String  region = rs.getString("region") ;
                Double longitude = rs.getDouble("longitud") ;
                Root ip = new Root( country,  callingCode,  capital,  city,  direccionIp,  latitude,  codePostal,  region , longitude)  ;
                ips.add(ip);
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    //Inicializamos el combobox con valores del Observable y la tabla con ip , city y postal
        opcionesList= FXCollections.observableArrayList(opciones);
        select.setItems(opcionesList);
        for (int i = 0 ; i < ips.size() ; i ++){
            direccionIp.add(ips.get(i).getIp());
        }
        ipList= FXCollections.observableArrayList(direccionIp);
        selectIp.setItems(ipList);
        tableIps= FXCollections.observableArrayList();
        tableIpsBanderas = FXCollections.observableArrayList();
        colDireccion.setCellValueFactory(new PropertyValueFactory("ip"));
        colCiudad.setCellValueFactory(new PropertyValueFactory("city"));
        colPostal.setCellValueFactory(new PropertyValueFactory("postal"));
    }
    
    public void setIp(ActionEvent actionEvent) {
        String ipSelecionada = selectIp.getValue().toString();
        for (int i = 0 ; i < ips.size();  i++){
            if (ipSelecionada.equals(ips.get(i).getIp())){
                lblCapital.setText(ips.get(i).getCapital());
                lblLatitud.setText(Double.toString(ips.get(i).getLatitude()));
                lblPais.setText(ips.get(i).getCountry());
                lblLongitud.setText(Double.toString(ips.get(i).getLongitude()));
                lblCodigoPostal.setText(ips.get(i).getPostal());
                lblCity.setText(ips.get(i).getCity());
                lblRegion.setText(ips.get(i).getRegion());
                lblDireccion.setText(ips.get(i).getIp());
                lblPrefijo.setText(ips.get(i).getCallingCode());
                tableIps.add(ips.get(i));
                tableView.setItems(tableIps);
            }
        }
    }


    //En la clase exports , tenemos todos lo métodos mediante los que se convierten los archivos
    Exports export = new Exports();
    // El string lo hacemos Estático para que podamos acceder desdos los directorios
    public static String nombreArchivo ;
    public void guardarDatos(ActionEvent actionEvent){
        nombreArchivo = txtNombreArchivo.getText();
        if (select.getSelectionModel().getSelectedItem().equals("BIN")){
            export.convertBin();
        }else if (select.getSelectionModel().getSelectedItem().equals("JSON")){
            export.convertJson(txtNombreArchivo.getText(),ips);
        }else if (select.getSelectionModel().getSelectedItem().equals("TXT")){
            export.convertTXT(txtNombreArchivo.getText(),ips);
        }else if (select.getSelectionModel().getSelectedItem().equals("XML")){
            export.convertXML(txtNombreArchivo.getText(),ips);
        }else if(select.getSelectionModel().getSelectedItem().equals("TODO")){
            export.convertBin();
            export.convertXML(txtNombreArchivo.getText(),ips);
            export.convertJson(txtNombreArchivo.getText(),ips);
            export.convertTXT(txtNombreArchivo.getText(),ips);

        }
        ;
    }
}
