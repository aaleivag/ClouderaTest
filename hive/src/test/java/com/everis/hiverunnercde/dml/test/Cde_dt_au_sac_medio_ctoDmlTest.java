package com.everis.hiverunnercde.dml.test;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.klarna.hiverunner.HiveShell;
import com.klarna.hiverunner.StandaloneHiveRunner;
import com.klarna.hiverunner.annotations.HiveSQL;

@RunWith(StandaloneHiveRunner.class)
public class Cde_dt_au_sac_medio_ctoDmlTest {

	@HiveSQL(files = { "ddl/tablas/cde_dt_au_sac_medio_cto/staging-cde-sac-crte-cde_dt_au_sac_medio_cto_hca.hql",
			"ddl/tablas/cde_dt_au_sac_medio_cto/staging-cde-sac-crte-cde_dt_au_sac_medio_cto.hql",
			"scripts/landing-ta_medio_cto.hql" }, autoStart = false)
	private HiveShell hiveShell;

	@Before
	public void before() {

		hiveShell.addSetupScript("create database cdeexp");
		hiveShell.addSetupScript("create database bu_sac");
		hiveShell.setHiveConfValue("var", "${hiveconf:hadoop.tmp.dir}");
		hiveShell.setHiveConfValue("PATH_AMBIENTE", "${hiveconf:hadoop.tmp.dir}");
		hiveShell.setHiveConfValue("ANO", "2016");
		hiveShell.setHiveConfValue("MES", "06");
		hiveShell.setHiveConfValue("DIA", "02");
		hiveShell.setHiveConfValue("HORA", "12");
		hiveShell.setHiveConfValue("MIN", "00");
		hiveShell.setHiveConfValue("SEG", "00");

		hiveShell.start();
	}

	@Test
	public void cargaDatos() {
		hiveShell.insertInto("bu_sac", "TA_MEDIO_CTO").withAllColumns()
				.addRowsFromDelimited(
						new File(ClassLoader.getSystemResource("datos/ta_medio_cto.txt").getPath()), "|", "null")
				.commit();
		hiveShell.execute(new File(
				ClassLoader.getSystemResource("dml/cde_dt_au_sac_medio_cto/staging-cde-sac-ins-cde_dt_au_sac_medio_cto.hql").getPath()));

		List<Object[]> source = hiveShell.executeStatement("select * from bu_sac.TA_MEDIO_CTO");
		List<Object[]> target = hiveShell.executeStatement("select * from cdeexp.CDE_DT_AU_SAC_MEDIO_CTO_HCA");

		Assert.assertEquals(source.size(), target.size());
	}
}
