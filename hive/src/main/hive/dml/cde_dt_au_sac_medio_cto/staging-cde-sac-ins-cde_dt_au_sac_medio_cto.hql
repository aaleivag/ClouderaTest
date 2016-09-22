ALTER TABLE cdeexp.cde_dt_au_sac_medio_cto_hca
ADD IF NOT EXISTS PARTITION(DATA_DATE_PART = ${hiveconf:ANO}${hiveconf:MES}${hiveconf:DIA}, 
               DATA_TIMESTAMP_PART = ${hiveconf:HORA}${hiveconf:MIN}${hiveconf:SEG})
LOCATION '${hiveconf:PATH_AMBIENTE}/cde/explotacion/cde_dt_au_sac_medio_cto_hca/${hiveconf:ANO}/${hiveconf:MES}/${hiveconf:DIA}/${hiveconf:HORA}${hiveconf:MIN}${hiveconf:SEG}';


INSERT INTO cdeexp.cde_dt_au_sac_medio_cto_hca
PARTITION(DATA_DATE_PART = ${hiveconf:ANO}${hiveconf:MES}${hiveconf:DIA}, 
          DATA_TIMESTAMP_PART = ${hiveconf:HORA}${hiveconf:MIN}${hiveconf:SEG}) 
SELECT 
	TA_MEDIO_CTO.COD_MEDIO_CONTACTO			,
	TA_MEDIO_CTO.DESC_MEDIO_CONTACTO       	,
	TA_MEDIO_CTO.ORDEN       				,
	''                                      ,
	'56'                                    ,
	'SAC'                                   ,
	'SAC'                                   ,
	'control_M'                             ,
	current_timestamp                       ,
	'0035'                                  ,
	'P_CDE'

FROM 
	BU_SAC.TA_MEDIO_CTO
WHERE TA_MEDIO_CTO.DATA_DATE_PART = ${hiveconf:ANO}${hiveconf:MES}${hiveconf:DIA};

ALTER TABLE cdeexp.cde_dt_au_sac_medio_cto 
DROP IF EXISTS PARTITION(DATA_DATE_PART = ${hiveconf:ANO}${hiveconf:MES}${hiveconf:DIA});

ALTER TABLE cdeexp.cde_dt_au_sac_medio_cto 
ADD IF NOT EXISTS PARTITION(DATA_DATE_PART = ${hiveconf:ANO}${hiveconf:MES}${hiveconf:DIA})
LOCATION '${hiveconf:PATH_AMBIENTE}/cde/explotacion/cde_dt_au_sac_medio_cto_hca/${hiveconf:ANO}/${hiveconf:MES}/${hiveconf:DIA}/${hiveconf:HORA}${hiveconf:MIN}${hiveconf:SEG}';
