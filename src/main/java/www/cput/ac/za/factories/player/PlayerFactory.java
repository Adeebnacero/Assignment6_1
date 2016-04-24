package www.cput.ac.za.factories.player;

import www.cput.ac.za.domain.player.Player;

/**
 * Created by Adeebo on 2016/04/19.
 */
public class PlayerFactory {

    public static Player getPlayer(int id, String name, String surname){

        return new Player.Builder()
                .clubID(id)
                .firstName(name)
                .lastName(surname)
                .build();
    }
}
