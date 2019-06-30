import org.w3c.dom.Document;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
 
/**
* Created by Meraj on 08/04/2017.
*/
public class XsltTrasfromation {
 
  private static Document document;
 
  public static void main(String[] args) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
 
    File xml = new File("persons.xml");
    File xsl = new File("persons.xsl");
 
    DocumentBuilder builder = factory.newDocumentBuilder();
    document = builder.parse(xml);
 
    // Use a Transformer for output
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    StreamSource style = new StreamSource(xsl);
    Transformer transformer = transformerFactory.newTransformer(style);
 
    DOMSource source = new DOMSource(document);
    StreamResult result = new StreamResult(System.out);
    transformer.transform(source, result);
  }
}

