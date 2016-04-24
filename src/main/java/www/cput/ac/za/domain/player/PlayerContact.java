package www.cput.ac.za.domain.player;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Adeebo on 2016/04/19.
 */
public class PlayerContact implements Serializable{

    private int clubID;
    private String contactType;
    private String contactNumber;
    private String status;
    private Date date;

    private PlayerContact(){

    }

    public int getClubID() {
        return clubID;
    }

    public String getContactType() {
        return contactType;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public PlayerContact(Builder builder){

        this.clubID = builder.clubID;
        this.contactType = builder.contactType;
        this.contactNumber = builder.contactNumber;
        this.status = builder.status;
        this.date = builder.date;
    }

    public static class Builder{

        private int clubID;
        private String contactType;
        private String contactNumber;
        private String status;
        private Date date;

        public Builder clubID(int value){
            this.clubID =value;
            return this;
        }

        public Builder contactType(String value){
            this.contactType =value;
            return this;
        }

        public Builder contactNumber(String value){
            this.contactNumber =value;
            return this;
        }

        public Builder status(String value){
            this.status =value;
            return this;
        }

        public Builder date(Date value){
            this.date =value;
            return this;
        }

        public Builder copy(PlayerContact value){

            this.clubID = value.clubID;
            this.contactType = value.contactType;
            this.contactNumber = value.contactNumber;
            this.status = value.status;
            this.date = value.date;
            return this;
        }

        public PlayerContact build(){
            return new PlayerContact(this);
        }
    }


}
