file -inlinebatch END_OF_BATCH

-- run4
DROP PROCEDURE ReportQuotaUsageApiServiceImpl_run4 IF EXISTS;
CREATE PROCEDURE FROM CLASS api.ReportQuotaUsageApiServiceImpl_run4;

END_OF_BATCH
