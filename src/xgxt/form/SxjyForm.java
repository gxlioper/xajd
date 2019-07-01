package xgxt.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class SxjyForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String XH;
	private String xmdm;
	private String xydm;
	private String month;
	private String xxplandm;
	private String isModi;
	private String plansId;
	private String saveXxplandm;
	private String saveXyjhdm;
	private String saveJyMotifdm;
	private String saveHdchdm;
	private String xxplanId;
	private String xyzjdm;
	private String xyjhdm;
	private String xyplandm;
	private String summarizesId;
	private String jyMotifdm;
	private String jyPlotdm;
	private String place;
	private String joinstudent;
	private String format;
	private String errMsg;
	private String nowYear;
	private String xxstuffdm;
	private String dxdm;
	private String xsbj;
	private String year;
	private String belongToItem;
	private String xh;
	private String	testPage;
	private String	xyV;
	private String formatChoose;
	private String tutorshipperson;
	private String bjgb;
	private String bjdm;
	private String	bjV;
	private String	xyjhTJSJD;
	private String	xyjhzjTJSJD;
	private String	hdchTJSJD;
	private String	hdzjTJSJD;
	private String	hdzjhbTJSJD;
	private String	zzxxjlTJSJD;
	private String	compare;
	private String	data;
	private String zxm;
	private String [] mkdm;
	private String [] fz;
	private String [] qz;
	private String	zydm;
	private String nj;
	private String xm;
	private String xb;
	private String xn;
	private String xq;
	private String nd;
	private String xy;
	private String zy;
	private String bj;
	private String jhzl;
	private String jpzw;
	private String pjdj;
	private String zgh;
	private String pyxm;
	private String zwdm;
	private String fbnd;
	private String fbyf;
	//问卷流水号
	private String sjlsh;
	//问卷名
	private String sjm;
	//问卷说明
	private String sjsm;
	//问卷显示标记
	private String sjxsbj;
	//试题编号
	private String stbh;
	//试题类型
	private String stlx;
	//试题内容
	private String stnr;
	//试题答案
	private String stda;
	//试题显示标记
	private String stxsbj;
	//加入时间
	private String jrsj;
	//试题答案子选项
	private String stzda;
	//组卷试题编号
	private String [] stbhList;
	//任职情况
	private String rzqk;
	//通用
	Pages pages = new Pages();
	private String rdsj;
	private String rdsjdks;      //入党时间段开始
	private String rdsjdjs;      //入党时间段结束
	private String jbdm;      //入党时间段结束
	
	private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
	
	private ArrayList<String[]> xmList;
	public ArrayList<String[]> getXmList() {
		return xmList;
	}

	public void setXmList(ArrayList<String[]> xmList) {
		this.xmList = xmList;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String[] getFz() {
		return fz;
	}

	public void setFz(String[] fz) {
		this.fz = fz;
	}

	public String[] getMkdm() {
		return mkdm;
	}

	public void setMkdm(String[] mkdm) {
		this.mkdm = mkdm;
	}

	public String getData() {
		return data;
	}

	public String getZxm() {
		return zxm;
	}

	public void setZxm(String zxm) {
		this.zxm = zxm;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTutorshipperson() {
		return tutorshipperson;
	}

	public void setTutorshipperson(String tutorshipperson) {
		this.tutorshipperson = tutorshipperson;
	}

	public String getXyV() {
		return xyV;
	}

	public void setXyV(String xyV) {
		this.xyV = xyV;
	}

	public String getTestPage() {
		return testPage;
	}

	public void setTestPage(String testPage) {
		this.testPage = testPage;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getBelongToItem() {
		return belongToItem;
	}

	public void setBelongToItem(String belongToItem) {
		this.belongToItem = belongToItem;
	}

	public String getXsbj() {
		return xsbj;
	}

	public void setXsbj(String xsbj) {
		this.xsbj = xsbj;
	}

	public String getJyMotifdm() {
		return jyMotifdm;
	}

	public void setJyMotifdm(String jyMotifdm) {
		this.jyMotifdm = jyMotifdm;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getXxplandm() {
		return xxplandm;
	}

	public void setXxplandm(String xxplandm) {
		this.xxplandm = xxplandm;
	}

	public String getIsModi() {
		return isModi;
	}

	public void setIsModi(String isModi) {
		this.isModi = isModi;
	}

	public String getPlansId() {
		return plansId;
	}

	public void setPlansId(String plansId) {
		this.plansId = plansId;
	}

	public String getSaveXxplandm() {
		return saveXxplandm;
	}

	public void setSaveXxplandm(String saveXxplandm) {
		this.saveXxplandm = saveXxplandm;
	}

	public String getXxplanId() {
		return xxplanId;
	}

	public void setXxplanId(String xxplanId) {
		this.xxplanId = xxplanId;
	}

	public String getXyzjdm() {
		return xyzjdm;
	}

	public void setXyzjdm(String xyzjdm) {
		this.xyzjdm = xyzjdm;
	}

	public String getXyjhdm() {
		return xyjhdm;
	}

	public void setXyjhdm(String xyjhdm) {
		this.xyjhdm = xyjhdm;
	}

	public String getXyplandm() {
		return xyplandm;
	}

	public void setXyplandm(String xyplandm) {
		this.xyplandm = xyplandm;
	}

	public String getSummarizesId() {
		return summarizesId;
	}

	public void setSummarizesId(String summarizesId) {
		this.summarizesId = summarizesId;
	}

	public String getSaveXyjhdm() {
		return saveXyjhdm;
	}

	public void setSaveXyjhdm(String saveXyjhdm) {
		this.saveXyjhdm = saveXyjhdm;
	}

	public String getSaveJyMotifdm() {
		return saveJyMotifdm;
	}

	public void setSaveJyMotifdm(String saveJyMotifdm) {
		this.saveJyMotifdm = saveJyMotifdm;
	}

	public String getSaveHdchdm() {
		return saveHdchdm;
	}

	public void setSaveHdchdm(String saveHdchdm) {
		this.saveHdchdm = saveHdchdm;
	}

	public String getJyPlotdm() {
		return jyPlotdm;
	}

	public void setJyPlotdm(String jyPlotdm) {
		this.jyPlotdm = jyPlotdm;
	}

	public String getJoinstudent() {
		return joinstudent;
	}

	public void setJoinstudent(String joinstudent) {
		this.joinstudent = joinstudent;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getNowYear() {
		return nowYear;
	}

	public void setNowYear(String nowYear) {
		this.nowYear = nowYear;
	}

	public String getXxstuffdm() {
		return xxstuffdm;
	}

	public void setXxstuffdm(String xxstuffdm) {
		this.xxstuffdm = xxstuffdm;
	}

	public String getDxdm() {
		return dxdm;
	}

	public void setDxdm(String dxdm) {
		this.dxdm = dxdm;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFormatChoose() {
		return formatChoose;
	}

	public void setFormatChoose(String formatChoose) {
		this.formatChoose = formatChoose;
	}

	public String getBjgb() {
		return bjgb;
	}

	public void setBjgb(String bjgb) {
		this.bjgb = bjgb;
	}

	public String getXH() {
		return XH;
	}

	public void setXH(String xh) {
		XH = xh;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjV() {
		return bjV;
	}

	public void setBjV(String bjV) {
		this.bjV = bjV;
	}

	public String getHdchTJSJD() {
		return hdchTJSJD;
	}

	public void setHdchTJSJD(String hdchTJSJD) {
		this.hdchTJSJD = hdchTJSJD;
	}

	public String getHdzjhbTJSJD() {
		return hdzjhbTJSJD;
	}

	public void setHdzjhbTJSJD(String hdzjhbTJSJD) {
		this.hdzjhbTJSJD = hdzjhbTJSJD;
	}

	public String getHdzjTJSJD() {
		return hdzjTJSJD;
	}

	public void setHdzjTJSJD(String hdzjTJSJD) {
		this.hdzjTJSJD = hdzjTJSJD;
	}

	public String getXyjhTJSJD() {
		return xyjhTJSJD;
	}

	public void setXyjhTJSJD(String xyjhTJSJD) {
		this.xyjhTJSJD = xyjhTJSJD;
	}

	public String getXyjhzjTJSJD() {
		return xyjhzjTJSJD;
	}

	public void setXyjhzjTJSJD(String xyjhzjTJSJD) {
		this.xyjhzjTJSJD = xyjhzjTJSJD;
	}

	public String getZzxxjlTJSJD() {
		return zzxxjlTJSJD;
	}

	public void setZzxxjlTJSJD(String zzxxjlTJSJD) {
		this.zzxxjlTJSJD = zzxxjlTJSJD;
	}

	public String getCompare() {
		return compare;
	}

	public void setCompare(String compare) {
		this.compare = compare;
	}

	public String[] getQz() {
		return qz;
	}

	public void setQz(String[] qz) {
		this.qz = qz;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public String getJhzl() {
		return jhzl;
	}

	public void setJhzl(String jhzl) {
		this.jhzl = jhzl;
	}

	public String getJpzw() {
		return jpzw;
	}

	public void setJpzw(String jpzw) {
		this.jpzw = jpzw;
	}

	public String getPjdj() {
		return pjdj;
	}

	public void setPjdj(String pjdj) {
		this.pjdj = pjdj;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getPyxm() {
		return pyxm;
	}

	public void setPyxm(String pyxm) {
		this.pyxm = pyxm;
	}

	public String getZwdm() {
		return zwdm;
	}

	public void setZwdm(String zwdm) {
		this.zwdm = zwdm;
	}

	public String getFbnd() {
		return fbnd;
	}

	public void setFbnd(String fbnd) {
		this.fbnd = fbnd;
	}

	public String getFbyf() {
		return fbyf;
	}

	public void setFbyf(String fbyf) {
		this.fbyf = fbyf;
	}

	public String getJrsj() {
		return jrsj;
	}

	public void setJrsj(String jrsj) {
		this.jrsj = jrsj;
	}

	public String getSjlsh() {
		return sjlsh;
	}

	public void setSjlsh(String sjlsh) {
		this.sjlsh = sjlsh;
	}

	public String getSjm() {
		return sjm;
	}

	public void setSjm(String sjm) {
		this.sjm = sjm;
	}

	public String getSjsm() {
		return sjsm;
	}

	public void setSjsm(String sjsm) {
		this.sjsm = sjsm;
	}

	public String getSjxsbj() {
		return sjxsbj;
	}

	public void setSjxsbj(String sjxsbj) {
		this.sjxsbj = sjxsbj;
	}

	public String getStbh() {
		return stbh;
	}

	public void setStbh(String stbh) {
		this.stbh = stbh;
	}

	public String getStda() {
		return stda;
	}

	public void setStda(String stda) {
		this.stda = stda;
	}

	public String getStlx() {
		return stlx;
	}

	public void setStlx(String stlx) {
		this.stlx = stlx;
	}

	public String getStnr() {
		return stnr;
	}

	public void setStnr(String stnr) {
		this.stnr = stnr;
	}

	public String getStxsbj() {
		return stxsbj;
	}

	public void setStxsbj(String stxsbj) {
		this.stxsbj = stxsbj;
	}

	public String getStzda() {
		return stzda;
	}

	public void setStzda(String stzda) {
		this.stzda = stzda;
	}

	public String[] getStbhList() {
		return stbhList;
	}

	public void setStbhList(String[] stbhList) {
		this.stbhList = stbhList;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getRzqk() {
		return rzqk;
	}

	public void setRzqk(String rzqk) {
		this.rzqk = rzqk;
	}

	public String getRdsj() {
		return rdsj;
	}

	public void setRdsj(String rdsj) {
		this.rdsj = rdsj;
	}

	public String getRdsjdks() {
		return rdsjdks;
	}

	public void setRdsjdks(String rdsjdks) {
		this.rdsjdks = rdsjdks;
	}

	public String getRdsjdjs() {
		return rdsjdjs;
	}

	public void setRdsjdjs(String rdsjdjs) {
		this.rdsjdjs = rdsjdjs;
	}

	public String getJbdm() {
		return jbdm;
	}

	public void setJbdm(String jbdm) {
		this.jbdm = jbdm;
	}

	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}

	/**
	 * @param searchModel要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
}
