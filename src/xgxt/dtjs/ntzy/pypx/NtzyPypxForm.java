package xgxt.dtjs.ntzy.pypx;

import xgxt.dtjs.gdby.tygl.BasicExtendForm;
import xgxt.utils.Pages;

public class NtzyPypxForm extends BasicExtendForm{
	private Pages pages = new Pages();
	
	private String sqxm; //申请项目

	private String save_sqsm;//申请说明
	
	private String save_grjl;//个人简历
	
	private String save_hjqk;//获奖情况
	
	private String queryequals_mk;//模块
	
	private String save_tzbmc;//团支部名称
	
	private String save_szyxtzz;//所在院系团组织
	
	private String save_zygz;//主要工作
	
	private String save_sqr;//申请人
	
	private String save_ssbm;//所属部门
	
	private String querylike_sqr;
	
	private String queryequals_ssbm;
	
	private String querylike_tzbmc;
	
	public String getQuerylike_sqr() {
		return querylike_sqr;
	}

	public void setQuerylike_sqr(String querylikeSqr) {
		querylike_sqr = querylikeSqr;
	}

	public String getQueryequals_ssbm() {
		return queryequals_ssbm;
	}

	public void setQueryequals_ssbm(String queryequalsSsbm) {
		queryequals_ssbm = queryequalsSsbm;
	}

	public String getQuerylike_tzbmc() {
		return querylike_tzbmc;
	}

	public void setQuerylike_tzbmc(String querylikeTzbmc) {
		querylike_tzbmc = querylikeTzbmc;
	}

	public String getSave_sqr() {
		return save_sqr;
	}

	public void setSave_sqr(String saveSqr) {
		save_sqr = saveSqr;
	}

	public String getSave_ssbm() {
		return save_ssbm;
	}

	public void setSave_ssbm(String saveSsbm) {
		save_ssbm = saveSsbm;
	}

	public String getSave_zygz() {
		return save_zygz;
	}

	public void setSave_zygz(String saveZygz) {
		save_zygz = saveZygz;
	}

	public String getSave_tzbmc() {
		return save_tzbmc;
	}

	public void setSave_tzbmc(String saveTzbmc) {
		save_tzbmc = saveTzbmc;
	}

	public String getSave_szyxtzz() {
		return save_szyxtzz;
	}

	public void setSave_szyxtzz(String saveSzyxtzz) {
		save_szyxtzz = saveSzyxtzz;
	}

	public String getQueryequals_mk() {
		return queryequals_mk;
	}

	public void setQueryequals_mk(String queryequalsMk) {
		queryequals_mk = queryequalsMk;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getSqxm() {
		return sqxm;
	}

	public void setSqxm(String sqxm) {
		this.sqxm = sqxm;
	}

	public String getSave_sqsm() {
		return save_sqsm;
	}

	public void setSave_sqsm(String saveSqsm) {
		save_sqsm = saveSqsm;
	}

	public String getSave_grjl() {
		return save_grjl;
	}

	public void setSave_grjl(String saveGrjl) {
		save_grjl = saveGrjl;
	}

	public String getSave_hjqk() {
		return save_hjqk;
	}

	public void setSave_hjqk(String saveHjqk) {
		save_hjqk = saveHjqk;
	}
	
	
}
