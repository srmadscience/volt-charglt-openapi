file -inlinebatch END_OF_BATCH

-- run
DROP PROCEDURE AddCreditApiServiceImpl_run IF EXISTS;
CREATE PROCEDURE FROM CLASS api.AddCreditApiServiceImpl_run;

END_OF_BATCH
