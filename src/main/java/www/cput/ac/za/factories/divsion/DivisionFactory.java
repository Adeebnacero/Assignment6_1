package www.cput.ac.za.factories.divsion;

import java.util.Date;
import www.cput.ac.za.domain.division.Division;


/**
 * Created by Adeebo on 2016/04/19.
 */
public class DivisionFactory {

    public static Division getDivision(String dStatus, String st){

        return new Division.Builder()
                .divisionStatus(dStatus)
                .status(st)
                .date(new Date())
                .build();
    }
}


