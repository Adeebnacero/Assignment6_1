package www.cput.ac.za.domain.subscriptions;

import java.io.Serializable;

/**
 * Created by Adeebo on 2016/04/23.
 */
public class Subscriptions implements Serializable {

    private int clubID;
    private String divisionStatus;
    private double amount;
    private double installment;

    public Subscriptions() {
    }

    public double getAmount() {
        return amount;
    }

    public int getClubID() {
        return clubID;
    }

    public String getDivisionStatus() {
        return divisionStatus;
    }

    public double getInstallment() {
        return installment;
    }

    public Subscriptions(Builder builder) {

        this.clubID = builder.clubID;
        this.divisionStatus = builder.divisionStatus;
        this.amount = builder.amount;
        this.installment = builder.installment;
    }

    public static class Builder {

        private int clubID;
        private String divisionStatus;
        private double amount;
        private double installment;

        public Builder amount(double value) {
            this.amount = value;
            return this;
        }

        public Builder clubID(int value) {
            this.clubID = value;
            return this;
        }

        public Builder divisionStatus(String value) {
            this.divisionStatus = value;
            return this;
        }

        public Builder installment(double value) {
            this.installment = value;
            return this;
        }

        public Builder copy(Subscriptions value) {
            this.clubID = value.clubID;
            this.amount = value.amount;
            this.divisionStatus = value.divisionStatus;
            this.installment = value.installment;
            return this;
        }

        public Subscriptions build() {
            return new Subscriptions(this);
        }

    }
}
