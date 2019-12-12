package api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static org.apache.http.entity.ContentType.getOrDefault;

public class WebServiceTestSoftAssert {


    @Test(description = "this test will fail on purpose to show a hard assert failure")
    public void hardAssertStopsImmediately() throws IOException {
        // Arrange
        CloseableHttpClient client = HttpClientBuilder.create().build();

        // Act
        CloseableHttpResponse response = client.execute(new HttpGet("https://api.github.com/"));


        // Assert
        System.out.println("First assert");
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);

        System.out.println("Second assert");
        Assert.assertEquals(getOrDefault(response.getEntity()).getMimeType(), "application/xml");

        System.out.println("Third assert");
        Assert.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(), "UTF-8");

        client.close();
        response.close();
    }

    @Test(description = "this test will fail on purpose to show a soft assert failure")
    public void softAssertContinuesToTheEnd() throws IOException {
        // Arrange
        CloseableHttpClient client = HttpClientBuilder.create().build();
        SoftAssert sa = new SoftAssert();

        // Act
        CloseableHttpResponse response = client.execute(new HttpGet("https://api.github.com/"));


        // Assert
        System.out.println("First assert");
        sa.assertEquals(response.getStatusLine().getStatusCode(), 404);

        System.out.println("Second assert");
        sa.assertEquals(getOrDefault(response.getEntity()).getMimeType(), "application/xml");

        System.out.println("Third assert");
        sa.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(), "UTF-8");


        client.close();
        response.close();

        sa.assertAll();
    }


}
