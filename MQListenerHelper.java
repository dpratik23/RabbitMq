import org.springframework.beans.factory.annotation.Value;

public class MQListenerHelper {

    @Value("${response.files.directory}")
    private String responseFilesDirectory;

    public String loadResponseFromFile(String fileName) throws Exception {
        Path path = Paths.get(responseFilesDirectory, fileName);
        return new String(Files.readAllBytes(path));
    }
}
