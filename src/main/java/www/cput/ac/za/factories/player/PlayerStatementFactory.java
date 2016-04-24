package www.cput.ac.za.factories.player;

import www.cput.ac.za.domain.player.PlayerStatement;

import java.util.Date;

/**
 * Created by Adeebo on 2016/04/24.
 */
public class PlayerStatementFactory {

    public static PlayerStatement getPlayerStatement(int id, String name, String surname, double subs, double appFee, double deduc, double payPlan, double totDue,Date payDue){

        return new PlayerStatement.Builder()
                .clubID(id)
                .firstName(name)
                .lastName(surname)
                .subscriptions(subs)
                .apparelFee(appFee)
                .deductions(deduc)
                .paymentPlan(payPlan)
                .totalDue(totDue)
                .paymentDueDate(payDue)
                .build();
    }
}

