package api;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ApiTestWithoutDataProviders {

    protected CloseableHttpClient client;
    protected CloseableHttpResponse response;

    @BeforeMethod
    public void buildClient(){
        // Arrange
        client = HttpClientBuilder.create().build();
    }

    @Test
    public void userEndpointReturns401() throws IOException {
        // Act
        response = client.execute(new HttpGet("https://api.github.com/user"));
        int actualStatusCode = response.getStatusLine().getStatusCode();

        // Assert
        Assert.assertEquals(actualStatusCode, HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void userFollowersEndpointReturns401() throws IOException {
        // Act
        response = client.execute(new HttpGet("https://api.github.com/user/followers"));
        int actualStatusCode = response.getStatusLine().getStatusCode();

        // Assert
        Assert.assertEquals(actualStatusCode, 401);
    }

    @Test
    public void notificationsFollowersEndpointReturns401() throws IOException {
        // Act
        response = client.execute(new HttpGet("https://api.github.com/notifications"));
        int actualStatusCode = response.getStatusLine().getStatusCode();

        // Assert
        Assert.assertEquals(actualStatusCode, 401);
    }


    @AfterMethod
    public void cleanup() throws IOException {
        client.close();
        response.close();
    }
}
