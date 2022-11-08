package com.example.buscarip.controller;

import com.example.buscarip.Exports;
import com.example.buscarip.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.*;

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

    @FXML
    private TableView tableView;
    @FXML
    private TableView tableCiudad;
    // Las columnas , son esenciales ya que sin ellas no podremos añadirle valores a la Tabla . En nuestro caso añadiremos una columna de Ip , dirección y Código Postal .
    @FXML
    private TableColumn<Root,String> colDireccion = new TableColumn<>("Direccion");
    @FXML
    private  TableColumn<Root,String> colCiudad = new TableColumn<>("Ciudad");
    @FXML
    private  TableColumn<Root,String> colPostal = new TableColumn<>("postal");
    @FXML
    private  TableColumn<String, String> colDirFiltrada = new TableColumn<>("City");
    //Arraylist de Ips para guardar todas las Ips que vaya añadiendo el usuario .
    public static ArrayList<Root> comboIp = new ArrayList<>();
    public static ArrayList<Root> ipFiltrada = new ArrayList<>();
    public static ArrayList<Root> ipsSeleccionadas = new ArrayList<>();
    // El observable es como un ArrayList , pero para la clase FX
    public static ArrayList<String> direccionIp = new ArrayList<>() ;
    ObservableList<String> opcionesList ;
    ObservableList<String> ipList ;
    ObservableList<String> arrayCity;
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
    @FXML
    private TextField txtFiltrarCiudad ;

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
            ResultSet rs  = null ;
            if (  ciudadesConsulta.equals("")){
                rs = stmt.executeQuery("SELECT * FROM datos");
            }else{
                rs = stmt.executeQuery("SELECT * FROM DATOS ORDER BY FIELD (CIUDAD,"+ciudadesConsulta+") DESC, ciudad");
            }
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
                comboIp.add(ip);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    //Inicializamos el combobox con valores del Observable y la tabla con ip , city y postal
        opcionesList= FXCollections.observableArrayList(opciones);
        select.setItems(opcionesList);
        for (int i = 0; i < comboIp.size() ; i ++){
            direccionIp.add(comboIp.get(i).getIp());
        }
        ipList= FXCollections.observableArrayList(direccionIp);
        selectIp.setItems(ipList);
        tableIps= FXCollections.observableArrayList();
        colDireccion.setCellValueFactory(new PropertyValueFactory("ip"));
        colCiudad.setCellValueFactory(new PropertyValueFactory("city"));
        colPostal.setCellValueFactory(new PropertyValueFactory("postal"));
        colDirFiltrada.setCellValueFactory(new PropertyValueFactory("City"));
    }
    
    public void setIp(ActionEvent actionEvent) {
        try {
            Connection con = null;
            String url1= "jdbc:mysql://localhost:3306/BD_ips";
            String user= "root";
            String password  = "root";
            // create a connection to the database
            con = DriverManager.getConnection(url1, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs  = null ;
            if (  ciudadesConsulta.equals("")){
                rs = stmt.executeQuery("SELECT * FROM datos");
            }else{
                rs = stmt.executeQuery("SELECT * FROM DATOS ORDER BY FIELD (CIUDAD,"+ciudadesConsulta+") DESC, ciudad");
            }
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
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        String ipSelecionada = selectIp.getValue().toString();
        for (int i = 0; i < comboIp.size(); i++){
            if (ipSelecionada.equals(comboIp.get(i).getIp())){
                ipsSeleccionadas.add(comboIp.get(i));
                lblCapital.setText(comboIp.get(i).getCapital());
                lblLatitud.setText(Double.toString(comboIp.get(i).getLatitude()));
                lblPais.setText(comboIp.get(i).getCountry());
                lblLongitud.setText(Double.toString(comboIp.get(i).getLongitude()));
                lblCodigoPostal.setText(comboIp.get(i).getPostal());
                lblCity.setText(comboIp.get(i).getCity());
                lblRegion.setText(comboIp.get(i).getRegion());
                lblDireccion.setText(comboIp.get(i).getIp());
                lblPrefijo.setText(comboIp.get(i).getCallingCode());
                tableIps.add(comboIp.get(i));
                tableView.setItems(tableIps);
            }
        }
    }
    String ciudadesConsulta = "" ;
    public void filtrarCity(ActionEvent actionEvent) {
        arrayCity.add(txtFiltrarCiudad.getText());
        String ciudades = "";

        for (int i = 0 ; i < arrayCity.size() ; i++){
               ciudades +="'"+ arrayCity.get(i)+"',";
        }
        ciudadesConsulta = ciudades.replaceFirst(".$", "");
        System.out.println(ciudadesConsulta);
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
            export.convertJson(txtNombreArchivo.getText(), ipsSeleccionadas);
        }else if (select.getSelectionModel().getSelectedItem().equals("TXT")){
            export.convertTXT(txtNombreArchivo.getText(), ipsSeleccionadas);
        }else if (select.getSelectionModel().getSelectedItem().equals("XML")){
            export.convertXML(txtNombreArchivo.getText(), ipsSeleccionadas);
        }else if(select.getSelectionModel().getSelectedItem().equals("TODO")){
            export.convertBin();
            export.convertXML(txtNombreArchivo.getText(), ipsSeleccionadas);
            export.convertJson(txtNombreArchivo.getText(), ipsSeleccionadas);
            export.convertTXT(txtNombreArchivo.getText(), ipsSeleccionadas);
        }
        ;
    }
}
