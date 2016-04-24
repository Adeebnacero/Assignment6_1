package www.cput.ac.za.domain.division;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Adeebo on 2016/04/19.
 */
public class Division implements Serializable{

    private int clubID;
    private String divisionStatus;
    private String status;
    private Date date;

    private Division(){

    }

    public int getClubID() {
        return clubID;
    }

    public String getDivisionStatus() {
        return divisionStatus;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public Division (Builder builder){

        this.clubID = builder.clubID;
        this.divisionStatus = builder.divisionStatus;
        this.status = builder.status;
        this.date = builder.date;
    }

    public static class Builder{

        private int clubID;
        private String divisionStatus;
        private String status;
        private Date date;

        public Builder clubID(int value){
            this.clubID = value;
            return this;
        }

        public Builder divisionStatus(String value){
            this.divisionStatus = value;
            return this;
        }

        public Builder status(String value){
            this.status = value;
            return this;
        }

        public Builder date(Date value){
            this.date = value;
            return this;
        }

        public Builder copy(Division value){
            this.clubID = value.clubID;
            this.divisionStatus = value.divisionStatus;
            this.status = value.status;
            this.date = value.date;
            return this;
        }

        public Division build(){
            return new Division(this);
        }
    }
}
