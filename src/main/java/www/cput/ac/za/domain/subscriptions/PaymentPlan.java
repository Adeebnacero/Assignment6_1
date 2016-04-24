package www.cput.ac.za.domain.subscriptions;

import java.io.Serializable;

/**
 * Created by Adeebo on 2016/04/23.
 */

public class PaymentPlan implements Serializable {

    private int clubID;
    private double term;
    private double amount;
    private double balance;

    public PaymentPlan() {
    }

    public int getClubID() {
        return clubID;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public double getTerm() {
        return term;
    }

    public PaymentPlan(Builder builder) {

        this.clubID = builder.clubID;
        this.term = builder.term;
        this.amount = builder.amount;
        this.balance = builder.balance;
    }

    public static class Builder {

        private int clubID;
        private double term;
        private double amount;
        private double balance;

        public Builder clubID(int value) {
            this.clubID = value;
            return this;
        }

        public Builder amount(double value) {
            this.amount = value;
            return this;
        }

        public Builder balance(double value) {
            this.balance = value;
            return this;
        }

        public Builder term(double value) {
            this.term = value;
            return this;
        }

        public Builder copy(PaymentPlan value) {

            this.clubID = value.clubID;
            this.term = value.term;
            this.amount = value.amount;
            this.balance = value.balance;
            return this;
        }

        public PaymentPlan build() {
            return new PaymentPlan(this);
        }
    }
}
