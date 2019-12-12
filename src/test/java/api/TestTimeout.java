package api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static org.apache.http.entity.ContentType.getOrDefault;

public class TestTimeout {

    CloseableHttpClient client;
    CloseableHttpResponse response;

    @BeforeMethod(timeOut = 800) // enough time - will pass
    public void setup(){
        // Arrange
        client = HttpClientBuilder.create().build();

    }
    @Test(timeOut = 500) // not enough time - will fail
    public void testIsTooSlow() throws IOException {

        // Act
        CloseableHttpResponse response = client.execute(new HttpGet("https://api.github.com/"));

        // Assert
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);

    }

    @AfterMethod
    public void cleanup() throws IOException {
        if(client != null && response != null){
            client.close();
            response.close();
        }
    }


}
