package com.everis.hiverunnercde.ddl.tablas.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.klarna.hiverunner.HiveShell;
import com.klarna.hiverunner.StandaloneHiveRunner;
import com.klarna.hiverunner.annotations.HiveSQL;

@RunWith(StandaloneHiveRunner.class)
public class Cde_dt_au_sac_medio_ctoDdlTest {
	
	@HiveSQL(files = {
			"ddl/tablas/cde_dt_au_sac_medio_cto/staging-cde-sac-crte-cde_dt_au_sac_medio_cto_hca.hql",
			"ddl/tablas/cde_dt_au_sac_medio_cto/staging-cde-sac-crte-cde_dt_au_sac_medio_cto.hql" }, autoStart = false)
	private HiveShell hiveShell;
	
	@Before
	public void before() {

		hiveShell.addSetupScript("create database cdeexp");
		hiveShell.setHiveConfValue("var", "${hiveconf:hadoop.tmp.dir}");
		hiveShell.start();
		hiveShell.executeQuery("use cdeexp");
	}

	@Test
	public void cde_dt_au_sac_medio_cto_hca() {
		String tabla = "cde_dt_au_sac_medio_cto_hca";
		List<String> actual = hiveShell.executeQuery("show tables like '"+ tabla + "'");
		Assert.assertEquals(actual.get(0), tabla);
	}
	
	@Test
	public void cde_dt_au_sac_medio_cto() {
		String tabla = "cde_dt_au_sac_medio_cto";
		List<String> actual = hiveShell.executeQuery("show tables like '"+ tabla + "'");
		Assert.assertEquals(actual.get(0), tabla);
	}
	
}
