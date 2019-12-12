package api;
import baseclasses.WebServiceBaseClass;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static org.apache.http.entity.ContentType.getOrDefault;

public class WebServiceTestWithSetup extends WebServiceBaseClass {


    @Test
    public void statusIs200() {
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test
    public void typeIsJson() {
        // Assert
        Assert.assertEquals(getOrDefault(response.getEntity()).getMimeType(), "application/json");
    }

    @Test
    public void charSetIsUtf8() {
        // Assert
        Assert.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(), "UTF-8");
    }


}
