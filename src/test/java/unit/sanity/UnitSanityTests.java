package unit.sanity;

import app.UserManager;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "sanity")
public class UnitSanityTests {


    public void sanityStorageGetsCreatedANdIsEmpty(){
        UserManager um = new UserManager();
        Assert.assertTrue(um.getAllUsers().isEmpty());
    }

    public void sanityTest2(){
        System.out.println("2nd unit.sanity test");
    }
}
