package xgxt.dtjs.ntzy.xyhd;

import xgxt.dtjs.gdby.tygl.BasicExtendForm;
import xgxt.utils.Pages;

public class NtzyXyhdForm extends BasicExtendForm{
	private Pages pages = new Pages();
	private String save_sqdw;
	private String save_kssj;
	private String save_zfzr;
	private String save_dd;
	private String save_xcfzr1;
	private String save_xcfzr2;
	private String save_fzr1dh;
	private String save_fzr2dh;
	private String save_hdnr;
	private String save_cyrs;
	private String save_id;
	private String save_hdfa;
	private String save_sqdwyj;
	private String save_ssbm;
	
	private String queryequals_ssbm;
	private String querylike_sqdw;
	private String querylike_zfzr;
	private String queryequals_cyrs;
	private String queryequals_sqr;
	
	private String cyrs;     //参与人数
	private String fzr2dh;     //负责人2电话
	private String xysh;     //学院审核
	private String xxsh;     //学校审核
	private String zfzr;     //总负责人
	private String xcfzr1;     //现场负责人1
	private String hdnr;     //活动内容
	private String xcfzr2;     //现场负责人2
	private String id;     //主键
	private String fzr1dh;     //负责人1电话
	private String hdfa;     //活动方案
	private String sqsj;     //申请时间
	private String kssj;     //开始时间
	private String sqdwyj;     //申请单位意见
	private String xyshyj;     //校园审核意见
	private String sqdw;     //申请单位
	private String dd;     //地点
	private String xxshyj;     //学校审核意见
	private String hour1;
	private String minute1;
	private String hour2;
	private String minute2;
	private String day;
	
	public String getQueryequals_sqr() {
		return queryequals_sqr;
	}
	public void setQueryequals_sqr(String queryequalsSqr) {
		queryequals_sqr = queryequalsSqr;
	}
	public String getHour1() {
		return hour1;
	}
	public void setHour1(String hour1) {
		this.hour1 = hour1;
	}
	public String getMinute1() {
		return minute1;
	}
	public void setMinute1(String minute1) {
		this.minute1 = minute1;
	}
	public String getHour2() {
		return hour2;
	}
	public void setHour2(String hour2) {
		this.hour2 = hour2;
	}
	public String getMinute2() {
		return minute2;
	}
	public void setMinute2(String minute2) {
		this.minute2 = minute2;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getQuerylike_sqdw() {
		return querylike_sqdw;
	}
	public void setQuerylike_sqdw(String querylikeSqdw) {
		querylike_sqdw = querylikeSqdw;
	}
	public String getQuerylike_zfzr() {
		return querylike_zfzr;
	}
	public void setQuerylike_zfzr(String querylikeZfzr) {
		querylike_zfzr = querylikeZfzr;
	}
	public String getQueryequals_cyrs() {
		return queryequals_cyrs;
	}
	public void setQueryequals_cyrs(String queryequalsCyrs) {
		queryequals_cyrs = queryequalsCyrs;
	}
	public String getQueryequals_ssbm() {
		return queryequals_ssbm;
	}
	public void setQueryequals_ssbm(String queryequalsSsbm) {
		queryequals_ssbm = queryequalsSsbm;
	}
	public String getSave_ssbm() {
		return save_ssbm;
	}
	public void setSave_ssbm(String saveSsbm) {
		save_ssbm = saveSsbm;
	}
	public String getCyrs() {
		return cyrs;
	}
	public void setCyrs(String cyrs) {
		this.cyrs = cyrs;
	}
	public String getFzr2dh() {
		return fzr2dh;
	}
	public void setFzr2dh(String fzr2dh) {
		this.fzr2dh = fzr2dh;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getZfzr() {
		return zfzr;
	}
	public void setZfzr(String zfzr) {
		this.zfzr = zfzr;
	}
	public String getXcfzr1() {
		return xcfzr1;
	}
	public void setXcfzr1(String xcfzr1) {
		this.xcfzr1 = xcfzr1;
	}
	public String getHdnr() {
		return hdnr;
	}
	public void setHdnr(String hdnr) {
		this.hdnr = hdnr;
	}
	public String getXcfzr2() {
		return xcfzr2;
	}
	public void setXcfzr2(String xcfzr2) {
		this.xcfzr2 = xcfzr2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFzr1dh() {
		return fzr1dh;
	}
	public void setFzr1dh(String fzr1dh) {
		this.fzr1dh = fzr1dh;
	}
	public String getHdfa() {
		return hdfa;
	}
	public void setHdfa(String hdfa) {
		this.hdfa = hdfa;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getSqdwyj() {
		return sqdwyj;
	}
	public void setSqdwyj(String sqdwyj) {
		this.sqdwyj = sqdwyj;
	}
	public String getXyshyj() {
		return xyshyj;
	}
	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}
	public String getSqdw() {
		return sqdw;
	}
	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
	}
	public String getDd() {
		return dd;
	}
	public void setDd(String dd) {
		this.dd = dd;
	}
	public String getXxshyj() {
		return xxshyj;
	}
	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}
	public String getSave_id() {
		return save_id;
	}
	public void setSave_id(String saveId) {
		save_id = saveId;
	}
	public String getSave_sqdw() {
		return save_sqdw;
	}
	public void setSave_sqdw(String saveSqdw) {
		save_sqdw = saveSqdw;
	}
	public String getSave_kssj() {
		return save_kssj;
	}
	public void setSave_kssj(String saveKssj) {
		save_kssj = saveKssj;
	}
	public String getSave_zfzr() {
		return save_zfzr;
	}
	public void setSave_zfzr(String saveZfzr) {
		save_zfzr = saveZfzr;
	}
	public String getSave_dd() {
		return save_dd;
	}
	public void setSave_dd(String saveDd) {
		save_dd = saveDd;
	}
	public String getSave_xcfzr1() {
		return save_xcfzr1;
	}
	public void setSave_xcfzr1(String saveXcfzr1) {
		save_xcfzr1 = saveXcfzr1;
	}
	public String getSave_xcfzr2() {
		return save_xcfzr2;
	}
	public void setSave_xcfzr2(String saveXcfzr2) {
		save_xcfzr2 = saveXcfzr2;
	}
	public String getSave_fzr1dh() {
		return save_fzr1dh;
	}
	public void setSave_fzr1dh(String saveFzr1dh) {
		save_fzr1dh = saveFzr1dh;
	}
	public String getSave_fzr2dh() {
		return save_fzr2dh;
	}
	public void setSave_fzr2dh(String saveFzr2dh) {
		save_fzr2dh = saveFzr2dh;
	}
	public String getSave_hdnr() {
		return save_hdnr;
	}
	public void setSave_hdnr(String saveHdnr) {
		save_hdnr = saveHdnr;
	}
	public String getSave_cyrs() {
		return save_cyrs;
	}
	public void setSave_cyrs(String saveCyrs) {
		save_cyrs = saveCyrs;
	}
	public String getSave_hdfa() {
		return save_hdfa;
	}
	public void setSave_hdfa(String saveHdfa) {
		save_hdfa = saveHdfa;
	}
	public String getSave_sqdwyj() {
		return save_sqdwyj;
	}
	public void setSave_sqdwyj(String saveSqdwyj) {
		save_sqdwyj = saveSqdwyj;
	}
}
