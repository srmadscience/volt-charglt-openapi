package chargingdemoprocs;

import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/* This file is part of VoltDB.
 * Copyright (C) 2008-2022 VoltDB Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;
import org.voltdb.VoltType;
import org.voltdb.client.ClientResponse;
import org.voltdb.client.ClientResponseWithPartitionKey;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

public abstract class VoltAPIProcedure extends VoltProcedure {

    public static final int RESPONSE_CREATED = 201;
    public static final int RESPONSE_BAD_REQUEST = 400;
    public static final int RESPONSE_UNAUTHORIZED = 401;
    public static final int RESPONSE_FORBIDDEN = 403;
    public static final int RESPONSE_METHOD_NOT_ALLOWED = 405;
    public static final int RESPONSE_CONFLICT = 409;
    public static final int RESPONSE_INTERNAL_SERVER_ERROR = 500;
    public static final int RESPONSE_UNKNOWN = 0;

    public static final byte RESPONSE_VOLT_PROC_OK = 9;
    public static final String RESPONSE_VOLT_PROC_OK_STRING = "9";
    public static final byte RESPONSE_VOLT_PROC_FK_NOT_FOUND = 10;
    public static final byte RESPONSE_VOLT_PROC_NOT_OK = 11;
    public static final String RESPONSE_VOLT_PROC_NOT_OK_STRING = "11";

    public static final byte RESPONSE_VOLT_ALREADY_LOCKED = 12;
    public static final String RESPONSE_VOLT_ALREADY_LOCKED_STRING = "12";
    
    public static final byte RESPONSE_VOLT_NOT_FOUND = 14;
    public static final String RESPONSE_VOLT_NOT_FOUND_STRING = "14";

 
    Gson json = null;

    

    @SuppressWarnings("unused")
    protected String toJson(Object thingsToMakeIntoJson) {
        if (thingsToMakeIntoJson == null) {
            return "";
        }
        initJson();
        return json.toJson(thingsToMakeIntoJson);
    }

    @SuppressWarnings("unused")
    protected Object fromJson(String objectThatIsJson, Type type) throws Exception {
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
        if (json == null) {
            json = new Gson();
        }
    }
    
    @SuppressWarnings("unused")
    protected VoltTable[] castObjectToVoltTableArray(Object thingsToCast, int statusCode, String optionalMessage) {

        VoltTable[]  returnArray = new VoltTable[1];
        returnArray[0] = new VoltTable(new VoltTable.ColumnInfo("PAYLOAD", VoltType.STRING)
                                      ,new VoltTable.ColumnInfo("STATUS_CODE", VoltType.INTEGER)
                                      ,new VoltTable.ColumnInfo("OPTIONAL_MESSAGE", VoltType.STRING));
        returnArray[0].addRow(toJson(thingsToCast), statusCode, optionalMessage);
        this.setAppStatusCode(RESPONSE_VOLT_PROC_OK);
        return returnArray;
        }

    @SuppressWarnings("unused")
    protected VoltTable[] castObjectArrayToVoltTableArray(Object[] thingsToCast, int statusCode, String optionalMessage) {

        VoltTable[]  returnArray = new VoltTable[thingsToCast.length];
        returnArray[0] = new VoltTable(new VoltTable.ColumnInfo("PAYLOAD", VoltType.STRING)
                                      ,new VoltTable.ColumnInfo("STATUS_CODE", VoltType.INTEGER)
                                      ,new VoltTable.ColumnInfo("OPTIONAL_MESSAGE", VoltType.STRING));
        
        for (int i=0;i < thingsToCast.length; i++) {
            returnArray[0].addRow(toJson(thingsToCast[i]), statusCode, optionalMessage);
        }
        
        this.setAppStatusCode(RESPONSE_VOLT_PROC_OK);
        return returnArray;
        }

  
}
