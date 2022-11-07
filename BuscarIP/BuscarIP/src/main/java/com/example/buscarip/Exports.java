package com.example.buscarip;

import com.example.buscarip.controller.IpController;
import com.example.buscarip.model.Root;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;

public class Exports {
    //Método que convierte a XML , se le pasa un nombre con el que se guardará y un arrayList de IPS
    public static void convertXML(String fileName, ArrayList<Root> ipList) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document documento = implementation.createDocument(null, "ips", null);
            documento.setXmlVersion("1.0");

            for (int i = 0 ; i < ipList.size();i++) {
                Element ipElement = documento.createElement("Ip");
                Element name = documento.createElement("Dirección");
                Text textName = documento.createTextNode(ipList.get(i).getIp());
                name.appendChild(textName);
                ipElement.appendChild(name);

                // Creamos la ciudad
                Element ciudadad = documento.createElement("Ciudadad");
                Text textCity = documento.createTextNode(ipList.get(i).getCity());
                ciudadad.appendChild(textCity);
                ipElement.appendChild(ciudadad);

                //Creamos el elemento de codigo postal
                Element codePostal = documento.createElement("codePostal");
                Text textCodePostal = documento.createTextNode(ipList.get(i).getPostal());
                codePostal.appendChild(textCodePostal);
                ipElement.appendChild(codePostal);

                // y creamos el elemento de capital
                Element Capital = documento.createElement("Capital");
                Text textCapital = documento.createTextNode(ipList.get(i).getCapital());
                Capital.appendChild(textCapital);
                ipElement.appendChild(Capital);
                documento.getDocumentElement().appendChild(ipElement);
                Source source = new DOMSource(documento);
                Result result = new StreamResult(new File("..\\BuscarIP\\src\\Archivos\\"+IpController.nombreArchivo + ".xml" ));
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(source, result);
            }
            System.out.println("Se creó un archivo llamado "+fileName+".xml");
        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //Método que convierte a formato BIN
    public void convertBin() {
        try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream("..\\BuscarIP\\src\\Archivos\\"+IpController.nombreArchivo + ".bin"));) {
            for (int i = 0; i < IpController.allIps.size(); i++) {
                escritor.writeObject(IpController.allIps.get(i));
            }
            System.out.println("Se creó un archivo llamado "+IpController.nombreArchivo+".bin");
        } catch (IOException ex) {
            System.err.println(ex);
            System.out.println("No se creó el archivo");
        }
    }
    //Método que convierte a JSON , se le pasa un nombre con el que se guardará y un arrayList de IPS
    public void convertJson(String txtNombreArchivo, ArrayList<Root> ips){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("..\\BuscarIP\\src\\Archivos\\ "+txtNombreArchivo+".json"), ips);
            System.out.println("Se creó un archivo llamado " + txtNombreArchivo + ".json");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //Método que convierte a TXT , se le pasa un nombre con el que se guardará y un arrayList de IPS
    public void convertTXT(String txtNombreArchivo , ArrayList<Root> ips){
        try (PrintWriter escritor = new PrintWriter("..\\BuscarIP\\src\\Archivos\\"+txtNombreArchivo+".txt")) {
            for (int i = 0; i <ips.size(); i++) {
                escritor.println(ips.get(i).toString());
            }
            System.out.println("Se creó un archivo llamado "+txtNombreArchivo+" .txt");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    }

