package www.cput.ac.za.domain.division;

import java.io.Serializable;

/**
 * Created by Adeebo on 2016/04/19.
 */
public class Apparel implements Serializable{

    private int clubID;
    private String socks;
    private String tShirtSize;
    private String trackSuitSize;
    private String jacketSize;
    private Double totalAmount;

    private Apparel(){

    }

    public int getClubID() {
        return clubID;
    }

    public String getSocks() {
        return socks;
    }

    public String gettShirtSize() {
        return tShirtSize;
    }

    public String getTrackSuitSize() {
        return trackSuitSize;
    }

    public String getJacketSize() {
        return jacketSize;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Apparel(Builder builder){

        this.clubID = builder.clubID;
        this.socks = builder.socks;
        this.tShirtSize = builder.tShirtSize;
        this.trackSuitSize = builder.trackSuitSize;
        this.jacketSize = builder.jacketSize;
        this.totalAmount = builder.totalAmount;
    }

    public static class Builder{

        private int clubID;
        private String socks;
        private String tShirtSize;
        private String trackSuitSize;
        private String jacketSize;
        private Double totalAmount;

        public Builder clubID(int value){
            this.clubID = value;
            return this;
        }

        public Builder socks(String value){
            this.socks = value;
            return this;
        }

        public Builder tShirtSize(String value){
            this.tShirtSize = value;
            return this;
        }

        public Builder trackSuitSize(String value){
            this.trackSuitSize = value;
            return this;
        }

        public Builder jacketSize(String value){
            this.jacketSize = value;
            return this;
        }

        public Builder totalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder copy(Apparel value){
            this.clubID = value.clubID;
            this.socks = value.socks;
            this.tShirtSize = value.tShirtSize;
            this.jacketSize = value.jacketSize;
            this.totalAmount = value.totalAmount;
            return this;
        }

        public Apparel build(){
            return new Apparel(this);
        }
    }
}
