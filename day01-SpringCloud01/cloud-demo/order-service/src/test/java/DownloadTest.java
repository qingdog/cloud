import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class DownloadTest {
    @Autowired
    private RestTemplate restTemplate;
    @Test
    public void downloads() {
        System.out.println(false);
    }
}
