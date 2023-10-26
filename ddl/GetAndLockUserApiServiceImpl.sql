file -inlinebatch END_OF_BATCH

-- run2
DROP PROCEDURE GetAndLockUserApiServiceImpl_run2 IF EXISTS;
CREATE PROCEDURE FROM CLASS api.GetAndLockUserApiServiceImpl_run2;

END_OF_BATCH
