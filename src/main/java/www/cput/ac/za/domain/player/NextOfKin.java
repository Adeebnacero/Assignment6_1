package www.cput.ac.za.domain.player;

import java.io.Serializable;

/**
 * Created by Adeebo on 2016/04/23.
 */
public class NextOfKin  implements Serializable {

    private int clubID;
    private String name;
    private String surname;
    private String nName;
    private String nSurname;
    private String contactNumber;

    public NextOfKin(){}

    public int getClubID() {
        return clubID;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getName() {
        return name;
    }

    public String getnName() {
        return nName;
    }

    public String getnSurname() {
        return nSurname;
    }

    public String getSurname() {
        return surname;
    }

    public NextOfKin(Builder builder){

        this.clubID = builder.clubID;
        this.name = builder.name;
        this.surname = builder.surname;
        this.nName = builder.nSurname;
        this.contactNumber = builder.contactNumber;

    }

    public static class Builder{

        private int clubID;
        private String name;
        private String surname;
        private String nName;
        private String nSurname;
        private String contactNumber;

        public Builder clubID(int value) {
            this.clubID = value;
            return this;
        }

        public Builder contactNumber(String value) {
            this.contactNumber = value;
            return this;
        }

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder nName(String value) {
            this.nName = value;
            return this;
        }

        public Builder nSurname(String value) {
            this.nSurname = value;
            return this;
        }

        public Builder surname(String value) {
            this.surname = value;
            return this;
        }

        public Builder copy (NextOfKin value){

            this.clubID = value.clubID;
            this.name = value.name;
            this.surname = value.surname;
            this.nName = value.nSurname;
            this.contactNumber = value.contactNumber;
            return this;
        }

        public NextOfKin build(){
            return new NextOfKin(this);
        }
    }
}
