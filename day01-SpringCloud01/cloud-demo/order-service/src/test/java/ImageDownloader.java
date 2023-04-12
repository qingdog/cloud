import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import org.springframework.http.client.SimpleClientHttpRequestFactory;


public class ImageDownloader {
    public static void main(String[] args) throws IOException {
        String baseUrl = "https://static5.hentai-cosplays.com/upload/20211208/249/254696/";
        String fileExtension = ".jpg";
        int startIndex = 1;
        int endIndex = 64;
        String outputDirectory = "/path/to/output/directory/";

        // 设置代理
        String proxyHost = "127.0.0.1";
        int proxyPort = 10809;
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);

//        RestTemplate restTemplate = new RestTemplate();
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        for (int i = startIndex; i <= endIndex; i++) {
            String url = baseUrl + i + fileExtension;
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] imageBytes = response.getBody();
                String fileName = i + fileExtension;
                String filePath = outputDirectory + fileName;
                try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                    outputStream.write(imageBytes);
                }
                System.out.println("Downloaded " + fileName + " to " + outputDirectory);
            }
        }
    }
}