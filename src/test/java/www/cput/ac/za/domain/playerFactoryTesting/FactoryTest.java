package www.cput.ac.za.domain.playerFactoryTesting;

import junit.framework.Assert;
import org.testng.annotations.Test;
import www.cput.ac.za.domain.division.Apparel;
import www.cput.ac.za.domain.division.Division;
import www.cput.ac.za.domain.player.NextOfKin;
import www.cput.ac.za.domain.player.Player;
import www.cput.ac.za.domain.subscriptions.PaymentPlan;
import www.cput.ac.za.domain.subscriptions.Subscriptions;
import www.cput.ac.za.factories.divsion.ApparelFactory;
import www.cput.ac.za.factories.divsion.DivisionFactory;
import www.cput.ac.za.factories.player.NextOfKinFactory;
import www.cput.ac.za.factories.player.PlayerFactory;
import www.cput.ac.za.factories.subscriptions.PaymentPlanFactory;
import www.cput.ac.za.factories.subscriptions.SubscriptionFactory;


/**
 * Created by Adeebo on 2016/04/24.
 */
public class FactoryTest {

    @Test
    public void testCreatePlayer() throws Exception {
        Player pf1 = PlayerFactory.getPlayer(001,"Adeeb","Nacerodien");
        Assert.assertEquals("Adeeb",pf1.getFirstName());
    }

    @Test
    public void testUpdatePlayer() throws Exception {
        Player pf1 = PlayerFactory.getPlayer(001,"test@test.com", "Milan");
        Player newPf1 = new Player
                .Builder()
                .copy(pf1)
                .firstName("Alberto")
                .build();
        Assert.assertEquals("Alberto",newPf1.getFirstName());
        Assert.assertEquals("Milan",newPf1.getLastName());
    }

    @Test
    public void testCreateNextOfKin() throws Exception {
        NextOfKin pf1 = NextOfKinFactory.getNextOfKin(001,"Jack", "Jones", "Bob","Dylan","0876548767");
        Assert.assertEquals("Jack",pf1.getName());
    }

    @Test
    public void testUpdateNextOfKin() throws Exception {
        NextOfKin nK1 = NextOfKinFactory.getNextOfKin(001,"Jack", "Jones", "Bob","Dylan","0876548767");
        NextOfKin newnK1 = new NextOfKin
                .Builder()
                .copy(nK1)
                .name("Alberto")
                .build();
        Assert.assertEquals("Alberto",newnK1.getName());
        Assert.assertEquals("0718697414",newnK1.getContactNumber());
    }

    @Test
    public void testCreateDivision() throws Exception {
        Division pf1 = DivisionFactory.getDivision("SENIOR","ACTIVE");
        Assert.assertEquals("SENIOR",pf1.getStatus());
    }

    @Test
    public void testUpdateDivision() throws Exception {
        Division df1 = DivisionFactory.getDivision("SENIOR", "ACTIVE");
        Division newDf1 = new Division
                .Builder()
                .copy(df1)
                .status("JUNIOR")
                .build();
        Assert.assertEquals("JUNIOR",newDf1.getDivisionStatus());
        Assert.assertEquals("ACTIVE",newDf1.getStatus());
    }

    @Test
    public void testCreateApparel() throws Exception {
        Apparel pf1 = ApparelFactory.getApparel(001,"M", "M", "L", "M",350.00);
        Assert.assertEquals("M",pf1.getJacketSize());
    }

    @Test
    public void testUpdateApparel() throws Exception {
        Apparel a1 = ApparelFactory.getApparel(001,"M", "M", "L", "M",350.00);
        Apparel newA1 = new Apparel
                .Builder()
                .copy(a1)
                .socks("L")
                .build();
        Assert.assertEquals("L",newA1.getSocks());
        Assert.assertEquals("L",newA1.getTrackSuitSize());
    }

    @Test
    public void testCreatePayment() throws Exception {
        PaymentPlan pf1 = PaymentPlanFactory.getPaymentPlan(001, 10.5, 1000.00, 750.00);
        Assert.assertEquals(10.5,pf1.getTerm());
    }

    @Test
    public void testUpdatePayment() throws Exception {
        PaymentPlan pp1 = PaymentPlanFactory.getPaymentPlan(001, 10.5, 1000.00, 750.00);
        PaymentPlan newPp1 = new PaymentPlan
                .Builder()
                .copy(pp1)
                .term(6)
                .build();
        Assert.assertEquals(6,newPp1.getTerm());
        Assert.assertEquals(1000.00,newPp1.getAmount());
    }

    @Test
    public void testCreateSubscription() throws Exception {
        Subscriptions sf1 = SubscriptionFactory.getSubscription(001, "SENIOR", 450.00, 5.5);
        Assert.assertEquals(5.5,sf1.getInstallment());
    }

    @Test
    public void testUpdateSubscription() throws Exception {
        Subscriptions sf1 = SubscriptionFactory.getSubscription(001, "SENIOR", 450.00, 5.5);
        Subscriptions newPf1 = new Subscriptions
                .Builder()
                .copy(sf1)
                .amount(200.00)
                .build();
        Assert.assertEquals(200.00,sf1.getAmount());
        Assert.assertEquals(5.5,sf1.getInstallment());
    }
}
