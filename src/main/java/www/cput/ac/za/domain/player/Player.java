package www.cput.ac.za.domain.player;

import www.cput.ac.za.domain.PersonInterface;
import java.io.Serializable;

/**
 * Created by Adeebo on 2016/04/19.
 */
public class Player implements Serializable,PersonInterface {

    private int clubID;
    private String firstName;
    private String lastName;

    private Player() {

    }

    public int getClubID(){
        return clubID;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public Player(Builder builder){

        this.clubID = builder.clubID;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public static class Builder <T extends Builder> {

        private int clubID;
        private String firstName;
        private String lastName;

        public Builder clubID(int value){
            this.clubID = value;
            return this;
        }

        public Builder firstName(String value){
            this.firstName = value;
            return this;
        }

        public Builder lastName(String value){
            this.lastName = value;
            return this;
        }

        public Builder copy (Player value){
            this.clubID = value.clubID;
            this.firstName = value.firstName;
            this.lastName = value.lastName;
            return this;
        }

        public Player build(){
            return new Player(this);
        }
    }
}
