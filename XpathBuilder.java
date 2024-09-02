import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class MQListenerHelper {

    // Existing method for loading response files
    @Value("${response.files.directory}")
    private String responseFilesDirectory;

    public String extractAccountId(String xmlMessage) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xmlMessage)));

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        XPathExpression expression = xpath.compile("/yourXmlRootElement/accountId"); // Adjust the XPath as per your XML structure

        return (String) expression.evaluate(document, XPathConstants.STRING);
    }

    public String loadResponseFromFile(String fileName) throws Exception {
        Path path = Paths.get(responseFilesDirectory, fileName);
        return new String(Files.readAllBytes(path));
    }

    // Other methods ...
}
