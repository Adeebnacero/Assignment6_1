package www.cput.ac.za.factories.subscriptions;

import www.cput.ac.za.domain.subscriptions.Subscriptions;

/**
 * Created by Adeebo on 2016/04/23.
 */
public class SubscriptionFactory {

    public static Subscriptions getSubscription(int id, String dStatus, double amt, double instal){

        return new Subscriptions.Builder()
                .clubID(id)
                .divisionStatus(dStatus)
                .amount(amt)
                .installment(instal)
                .build();

    }
}
