package www.cput.ac.za.domain.player;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Adeebo on 2016/04/24.
 */
public class PlayerStatement implements Serializable {

    private int clubID;
    private String firstName;
    private String lastName;
    private double subscriptions;
    private double apparelFee;
    private double deductions;
    private double paymentPlan;
    private double totalDue;
    private Date paymentDueDate;

    public PlayerStatement(){}

    public double getApparelFee() {
        return apparelFee;
    }

    public int getClubID() {
        return clubID;
    }

    public double getDeductions() {
        return deductions;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getPaymentDueDate() {
        return paymentDueDate;
    }

    public double getPaymentPlan() {
        return paymentPlan;
    }

    public double getSubscriptions() {
        return subscriptions;
    }

    public double getTotalDue() {
        return totalDue;
    }

    public PlayerStatement(Builder builder){

        this.clubID = builder.clubID;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.subscriptions = builder.subscriptions;
        this.apparelFee = builder.apparelFee;
        this.deductions = builder.deductions;
        this.paymentPlan = builder.paymentPlan;
        this.totalDue = builder.totalDue;
        this.paymentDueDate = builder.paymentDueDate;
    }

    public static class Builder{

        private int clubID;
        private String firstName;
        private String lastName;
        private double subscriptions;
        private double apparelFee;
        private double deductions;
        private double paymentPlan;
        private double totalDue;
        private Date paymentDueDate;

        public Builder apparelFee(double value) {
            this.apparelFee = value;
            return this;
        }

        public Builder clubID(int value) {
            this.clubID = value;
            return this;
        }

        public Builder deductions(double value) {
            this.deductions = value;
            return this;
        }

        public Builder firstName(String value) {
            this.firstName = value;
            return this;
        }

        public Builder lastName(String value) {
            this.lastName = value;
            return this;
        }

        public Builder paymentDueDate(Date value) {
            this.paymentDueDate = value;
            return this;
        }

        public Builder paymentPlan(double paymentPlan) {
            this.paymentPlan = paymentPlan;
            return this;
        }

        public Builder subscriptions(double value) {
            this.subscriptions = value;
            return this;
        }

        public Builder totalDue(double value) {
            this.totalDue = value;
            return this;
        }

        public Builder copy (PlayerStatement value){

            this.clubID = value.clubID;
            this.firstName = value.firstName;
            this.lastName = value.lastName;
            this.subscriptions = value.subscriptions;
            this.apparelFee = value.apparelFee;
            this.deductions = value.deductions;
            this.paymentPlan = value.paymentPlan;
            this.totalDue = value.totalDue;
            this.paymentDueDate = value.paymentDueDate;
            return this;
        }

        public PlayerStatement build(){
            return new PlayerStatement(this);
        }
    }

}
