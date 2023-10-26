file -inlinebatch END_OF_BATCH

-- run1
DROP PROCEDURE DelUserApiServiceImpl_run1 IF EXISTS;
CREATE PROCEDURE FROM CLASS api.DelUserApiServiceImpl_run1;

END_OF_BATCH
