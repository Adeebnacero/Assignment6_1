package www.cput.ac.za.factories.subscriptions;

import www.cput.ac.za.domain.subscriptions.PaymentPlan;

/**
 * Created by Adeebo on 2016/04/23.
 */
public class PaymentPlanFactory{

        public static PaymentPlan getPaymentPlan(int id, double terms, double amt, double bal){

            return new PaymentPlan.Builder()
                    .clubID(id)
                    .term(terms)
                    .amount(amt)
                    .balance(bal)
                    .build();
        }
}
