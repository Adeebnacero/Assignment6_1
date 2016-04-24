package www.cput.ac.za.factories.player;

import www.cput.ac.za.domain.player.NextOfKin;

/**
 * Created by Adeebo on 2016/04/23.
 */
public class NextOfKinFactory {

    public static NextOfKin getNextOfKin(int cID, String nm, String sNM, String nNm, String sNm,String cNum){

        return new NextOfKin.Builder()
                    .clubID(cID)
                    .name(nm)
                    .surname(sNM)
                    .nName(nNm)
                    .nSurname(sNm)
                    .contactNumber(sNm)
                    .build();
    }
}
