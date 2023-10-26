package api;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;
import org.voltdb.VoltType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import io.swagger.client.model.*;

//BEGIN User Import Code
          

//END User Import Code
 
public class GetUserApiServiceImpl_run3 extends VoltProcedure {

    
    public static final int RESPONSE_CREATED = 201;
    public static final int RESPONSE_BAD_REQUEST = 400;
    public static final int RESPONSE_UNAUTHORIZED = 401;
    public static final int RESPONSE_FORBIDDEN = 403;
    public static final int RESPONSE_METHOD_NOT_ALLOWED = 405;
    public static final int RESPONSE_CONFLICT = 409;
    public static final int RESPONSE_INTERNAL_SERVER_ERROR = 500;
    public static final int RESPONSE_UNKNOWN = 0;

    
    public static final byte RESPONSE_VOLT_PROC_OK = 9;
    public static final byte RESPONSE_VOLT_PROC_FK_NOT_FOUND = 10;
    public static final byte RESPONSE_VOLT_PROC_NOT_OK = 11;


    Gson json = null;
    int logSeqno = 0;



    public static final SQLStmt logCall = new SQLStmt("INSERT INTO logtable VALUES (?,NOW,?,?,?,?,?);");

    //BEGIN User SQL Code
          

    //END User SQL Code
 
    public VoltTable[] run(java.lang.Long userId)
        {
        int statusCode = RESPONSE_UNKNOWN;
        logSeqno = 0;
        String statusDesc = "";

        // Return object
        javax.ws.rs.core.Response objectToReturn = null;



 
        //BEGIN User Logic Code
          

        //END User Logic Code
 
        return castObjectToVoltTableArray(objectToReturn,statusCode,statusDesc);
        }

    @SuppressWarnings("unused")
    private void logMessage(String partitionKey, String eventType, String eventPK, String payload) {
        voltQueueSQL(logCall, partitionKey, getUniqueId(), logSeqno++, eventType,eventPK,payload);
        voltExecuteSQL(false);
    }
    private VoltTable[] castObjectToVoltTableArray(Object thingsToCast, int statusCode, String optionalMessage) {

        VoltTable[]  returnArray = new VoltTable[1];
        returnArray[0] = new VoltTable(new VoltTable.ColumnInfo("PAYLOAD", VoltType.STRING)
                                      ,new VoltTable.ColumnInfo("STATUS_CODE", VoltType.INTEGER)
                                      ,new VoltTable.ColumnInfo("OPTIONAL_MESSAGE", VoltType.STRING));
        returnArray[0].addRow(toJson(thingsToCast), statusCode, optionalMessage);
        this.setAppStatusCode(RESPONSE_VOLT_PROC_OK);
        return returnArray;
        }

    private String toJson(Object thingsToMakeIntoJson) {
        initJson();
        return json.toJson(thingsToMakeIntoJson);
        }

    private Object fromJson(String objectThatIsJson, Type type) throws Exception {
        initJson();
        Object theObject = null;
        try {
            theObject = json.fromJson(objectThatIsJson, type);
        } catch (Exception e) {
            throw new Exception("Unable to parse JSON:" + e.getMessage());
        }

        return theObject;
        }

    private void initJson() {
        if (json == null)
            {
            json = new Gson();
            }
        }

    //BEGIN User Extra Code
          

    //END User Extra Code
 
}
