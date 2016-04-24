package www.cput.ac.za.domain.player;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Adeebo on 2016/04/19.
 */
public class PlayerAddress implements Serializable {

    private int clubID;
    private String firstName;
    private String lastName;
    private Long number;
    private String street;
    private String area;
    private String postalCode;
    private String postalAddress;
    private Date date;
    private String state;

    private PlayerAddress() {
    }

    public int getClubID() {
        return clubID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getNumber() {
        return number;
    }

    public String getStreet(){
        return street;
    }

    public String getArea(){
        return area;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public Date getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public String getAddress(){

        postalAddress = this.number + " " + this.street + "\n" + this.area + "\n" + this.postalCode;
        return postalAddress;
    }

    public PlayerAddress(Builder builder) {

        this.clubID = builder.clubID;
        this.number = builder.number;
        this.street = builder.street;
        this.area = builder.area;
        this.postalCode = builder.postalCode;
        this.postalAddress = builder.postalAddress;
        this.date = builder.date;
        this.state = builder.state;
    }

    public static class Builder{

        private int clubID;
        private String firstName;
        private String lastName;
        private Long number;
        private String street;
        private String area;
        private String postalCode;
        private String postalAddress;
        private Date date;
        private String state;

        public Builder id(int value) {
            this.clubID = value;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder date(Date value) {
            this.date = value;
            return this;
        }

        public Builder number(Long value) {
            this.number = value;
            return this;
        }

        public Builder street(String value) {
            this.street = value;
            return this;
        }

        public Builder area(String value) {
            this.area = value;
            return this;
        }

        public Builder postalCode(String value) {
            this.postalCode = value;
            return this;
        }

        public Builder state(String value) {
            this.state = value;
            return this;
        }

        public Builder postalAddress(long number,String street,String area, String postalCode) {

            this.number = number;
            this.street = street;
            this.area = area;
            this.postalCode = postalCode;
            return this;
        }

        public Builder copy(PlayerAddress value) {

            this.clubID = value.clubID;
            this.firstName = value.firstName;
            this.lastName = value.lastName;
            this.number = value.number;
            this.street = value.street;
            this.area = value.area;
            this.postalAddress = value.postalAddress;
            this.state = value.state;
            this.date = value.date;
            this.postalCode = value.postalCode;
            return this;
        }

        public PlayerAddress build() {
            return new PlayerAddress(this);
        }
    }
}
