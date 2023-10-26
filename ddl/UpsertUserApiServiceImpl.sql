file -inlinebatch END_OF_BATCH

-- run6
DROP PROCEDURE UpsertUserApiServiceImpl_run6 IF EXISTS;
CREATE PROCEDURE FROM CLASS api.UpsertUserApiServiceImpl_run6;

END_OF_BATCH
