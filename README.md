select "FORMSETID","TRNNAPOLXML","TRNNAPOLTYPE","TRNNAPOLSIZE" from

DMKR_ASLINE.TRNS where "FORMSETID" = :1

 

Select * from table(dbms_xplan.display);

 

-------------------------------------------------------------------------------------------------------

Plan hash value: 70732189


----------------------------------------------------------------------------------------------

| Id  | Operation                   | Name           | Rows  | Bytes | Cost (%CPU)| Time     |

----------------------------------------------------------------------------------------------

|   0 | SELECT STATEMENT            |                |     1 |   120 |     3   (0)| 00:00:01 |

|   1 |  TABLE ACCESS BY INDEX ROWID| TRNS           |     1 |   120 |     3   (0)| 00:00:01 |

|*  2 |   INDEX UNIQUE SCAN         | TRNS_FORMSETID |     1 |       |     2   (0)| 00:00:01 |

----------------------------------------------------------------------------------------------


Predicate Information (identified by operation id):

---------------------------------------------------


   2 - access("FORMSETID"=SYS_OP_C2C(:1))
