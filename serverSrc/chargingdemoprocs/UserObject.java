package chargingdemoprocs;

import org.voltdb.VoltTable;

public class UserObject {

    public long userId;
    public byte  statusCode;
    
    public UserObject(long userId, byte statusCode, VoltTable[] voltTables) {
        super();
        this.userId = userId;
        this.statusCode = statusCode;
    }

   
}
