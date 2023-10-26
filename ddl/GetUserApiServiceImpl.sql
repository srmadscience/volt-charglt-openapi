file -inlinebatch END_OF_BATCH

-- run3
DROP PROCEDURE GetUserApiServiceImpl_run3 IF EXISTS;
CREATE PROCEDURE FROM CLASS api.GetUserApiServiceImpl_run3;

END_OF_BATCH
