package a3parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	TransformerFactory tf = TransformerFactory.newInstance();
		
	public void readXML(File inputFile) {		
	
		Document inputDocument;
				
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			inputDocument = db.parse(inputFile);
			NodeList nodes = inputDocument.getElementsByTagName("DOC");			
			writeToFile(nodes);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public void writeToFile(NodeList nodes) throws ParserConfigurationException, TransformerException {
		
		DocumentBuilder builder = dbf.newDocumentBuilder();
		
		for (int i = 0; i < nodes.getLength(); i++) {
			
			Document doc = builder.newDocument();
			doc.appendChild(nodes.item(i));
			
			Transformer t = tf.newTransformer();
	        t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        t.setOutputProperty(OutputKeys.INDENT, "yes");
	        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	        
	        DOMSource source = new DOMSource(doc); 
	        File myFile = new File("extract/1/" + String.valueOf(i) + ".html");
	        
	        StreamResult console = new StreamResult(System.out);
	        StreamResult file = new StreamResult(myFile);
	       
	        t.transform(source, console);
	        t.transform(source, file);
	        
		}
		
		System.out.println("Done.");
	}

}
