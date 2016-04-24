package www.cput.ac.za.domain.playerBuilderTesting;

import org.testng.Assert;
import org.testng.annotations.Test;
import www.cput.ac.za.domain.division.Apparel;
import www.cput.ac.za.domain.division.Division;
import www.cput.ac.za.domain.player.Player;
import www.cput.ac.za.domain.player.PlayerAddress;
import www.cput.ac.za.domain.player.PlayerContact;
import www.cput.ac.za.domain.subscriptions.Subscriptions;

/**
 * Created by Adeebo on 2016/04/20.
 */
public class BuilderTest {

    @Test
    public void testPlayerBuildPass() throws Exception {

        Player p1 = new Player.Builder()
                .lastName("Nacerodien")
                .build();

        System.out.println("This is the correct value: " + p1.getLastName());

        //Test to see if value passed is correct - PASS TEST
        Assert.assertEquals("Nacerodien", p1.getLastName());
    }
    @Test
    public void testPlayerBuildFail() throws Exception {

        Player p2 = new Player.Builder()
                .lastName("Nacerodien")
                .build();

        //Test to see if value passed is correct - FAIL TEST
        Assert.assertEquals("Surname",p2.getLastName());
    }

    @Test
    public void testPlayerContactBuildPass() throws Exception {

        PlayerContact pc1 = new PlayerContact.Builder()
                .contactNumber("0217613373")
                .build();

        System.out.println("This is the correct value: " + pc1.getContactNumber());

        //Test to see if value passed is correct - PASS TEST
        Assert.assertEquals("0217613373", pc1.getContactNumber());
    }

    @Test
    public void testPlayerContactBuildFail() throws Exception {

        PlayerContact pc2 = new PlayerContact.Builder()
                .contactNumber("0217613373")
                .build();

        //Test to see if value passed is correct - FAIL TEST
        Assert.assertEquals("0866060000",pc2.getContactNumber());
    }

    @Test
    public void testPlayerAddressBuildPass() throws Exception {

        PlayerAddress pa1 = new PlayerAddress.Builder()
                .area("Kenwyn")
                .build();

        System.out.println("This is the correct value: " + pa1.getArea());

        //Test to see if value passed is correct - PASS TEST
        Assert.assertEquals("Kenwyn", pa1.getArea());
    }

    @Test
    public void testPlayerAddressBuildFail() throws Exception {

        PlayerAddress pa2 = new PlayerAddress.Builder()
                .area("Kenwyn")
                .build();

        //Test to see if value passed is correct - FAIL TEST
        Assert.assertEquals("Crawford",pa2.getArea());
    }

    @Test
    public void testDivisionBuildPass() throws Exception {

        Division d1 = new Division.Builder()
                .divisionStatus("SENIOR")
                .build();

        System.out.println("This is the correct value: " + d1.getDivisionStatus());

        //Test to see if value passed is correct - PASS TEST
        Assert.assertEquals("SENIOR", d1.getDivisionStatus());
    }

    @Test
    public void testDivisionBuildFail() throws Exception {

        Division d2 = new Division.Builder()
                .divisionStatus("SENIOR")
                .build();

        //Test to see if value passed is correct - FAIL TEST
        Assert.assertEquals("JUNIOR",d2.getDivisionStatus());
    }

    @Test
    public void testSubscriptionBuildPass() throws Exception {

        Subscriptions s1 = new Subscriptions.Builder()
                .amount(1500.00)
                .build();

        System.out.println("This is the correct value: " + s1.getAmount());

        //Test to see if value passed is correct - PASS TEST
        Assert.assertEquals(1500.00, s1.getAmount());
    }

    @Test
    public void testSubscriptionBuildFail() throws Exception {

        Subscriptions s2 = new Subscriptions.Builder()
                .amount(1500.00)
                .build();

        //Test to see if value passed is correct - FAIL TEST
        Assert.assertEquals(2000.00,s2.getAmount());
    }

    @Test
    public void testApparelBuildPass() throws Exception {

        Apparel a1 = new Apparel.Builder()
                .jacketSize("Small")
                .build();

        System.out.println("This is the correct value: " + a1.getJacketSize());

        //Test to see if value passed is correct - PASS TEST
        Assert.assertEquals("Small", a1.getJacketSize());
    }

    @Test
    public void testApparelBuildFail() throws Exception {

        Apparel a2 = new Apparel.Builder()
                .jacketSize("Small")
                .build();

        //Test to see if value passed is correct - FAIL TEST
        Assert.assertEquals("Large",a2.getJacketSize());
    }


}
