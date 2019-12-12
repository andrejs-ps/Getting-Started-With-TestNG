package app;
import org.testng.annotations.DataProvider;

public class CommonApiDataProviders {

    @DataProvider
    public static Object[][] endpointsRequiringAuthentication(){
        return new Object[][] {
                {"user"},
                {"user/followers"},
                {"notifications"}
                // etc
        };
    }
}
