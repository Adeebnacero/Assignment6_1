package www.cput.ac.za.factories.player;

import java.util.Date;
import www.cput.ac.za.domain.player.PlayerAddress;

/**
 * Created by Adeebo on 2016/04/19.
 */
public class PlayerAddressFactory {

    public static PlayerAddress getAddress(Long num, String str, String ar, String pCode){

        return new PlayerAddress.Builder()
                .number(num)
                .street(str)
                .area(ar)
                .postalCode(pCode)
                .date(new Date())
                .build();
    }
}
