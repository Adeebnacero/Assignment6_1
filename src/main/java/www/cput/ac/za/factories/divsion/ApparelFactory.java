package www.cput.ac.za.factories.divsion;

import www.cput.ac.za.domain.division.Apparel;

/**
 * Created by Adeebo on 2016/04/19.
 */
public class ApparelFactory {

    public static Apparel getApparel(int id,String sock, String tShirt, String trackSuit, String jacket,Double total){

        return new Apparel.Builder()
                .clubID(id)
                .socks(sock)
                .tShirtSize(tShirt)
                .trackSuitSize(trackSuit)
                .jacketSize(jacket)
                .totalAmount(total)
                .build();

    }
}

