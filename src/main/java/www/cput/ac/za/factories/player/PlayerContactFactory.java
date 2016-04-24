package www.cput.ac.za.factories.player;

import www.cput.ac.za.domain.player.PlayerContact;
import java.util.Date;

/**
 * Created by Adeebo on 2016/04/23.
 */
public class PlayerContactFactory {

    public static PlayerContact getPlayerContact(int id, String cType, String cNumber, String state, Date dt){

        return new PlayerContact.Builder()
                .clubID(id)
                .contactType(cType)
                .contactNumber(cNumber)
                .status(state)
                .date(dt)
                .build();
    }
}
