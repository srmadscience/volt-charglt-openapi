package chargingdemoprocs;

import org.voltdb.VoltTable;

public class ReportQuotaUsageStatus {

    public byte statusCode;

    public long balance;

    public long sessionId;

    public long currentlyAllocated;

    public ReportQuotaUsageStatus(byte statusCode, VoltTable[] results) {
        this.statusCode = statusCode;

        VoltTable userBalanceRow = results[results.length - 2];
        VoltTable currentlyAllocatedRow = results[results.length - 1];

        userBalanceRow.advanceRow();
        currentlyAllocatedRow.advanceRow();

        balance = userBalanceRow.getLong("BALANCE");
        sessionId = userBalanceRow.getLong("SESSIONID");

        currentlyAllocated = currentlyAllocatedRow.getLong("ALLOCATED_AMOUNT");

    }

}
