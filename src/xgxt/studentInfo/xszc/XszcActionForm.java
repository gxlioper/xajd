package xgxt.studentInfo.xszc;

import org.apache.struts.action.ActionForm;
import xgxt.utils.Pages;

public class XszcActionForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Pages pages = new Pages();
	
	private String pk;
	private String[] primarykey_cbv;
	private String[] xnArr;
	private String[] xqArr;
	private String xh;
	private String zczt;
	private String xn;
	private String xq;
	private String yy;
	private String type;
	private String save_zczt;
	private String save_yy;
	private String save_xn;
	private String save_xq;
	private String save_xh;
	private String save_fdysh;
	private String save_fdyyj;
	
	private String queryequals_xn;
	private String queryequals_nd;
	private String queryequals_fdysh;
	private String queryequals_nj;
	private String querylike_xh;
	private String querylike_xm;
	private String queryequals_xydm;
	private String queryequals_zydm;
	private String queryequals_bjdm;
	private String queryequals_zczt;
	private String pkValue;
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getQueryequals_xn() {
		return queryequals_xn;
	}
	public void setQueryequals_xn(String queryequalsXn) {
		queryequals_xn = queryequalsXn;
	}
	public String getQueryequals_nd() {
		return queryequals_nd;
	}
	public void setQueryequals_nd(String queryequalsNd) {
		queryequals_nd = queryequalsNd;
	}
	public String getQueryequals_fdysh() {
		return queryequals_fdysh;
	}
	public void setQueryequals_fdysh(String queryequalsFdysh) {
		queryequals_fdysh = queryequalsFdysh;
	}
	public String getQueryequals_nj() {
		return queryequals_nj;
	}
	public void setQueryequals_nj(String queryequalsNj) {
		queryequals_nj = queryequalsNj;
	}
	public String getQuerylike_xh() {
		return querylike_xh;
	}
	public void setQuerylike_xh(String querylikeXh) {
		querylike_xh = querylikeXh;
	}
	public String getQuerylike_xm() {
		return querylike_xm;
	}
	public void setQuerylike_xm(String querylikeXm) {
		querylike_xm = querylikeXm;
	}
	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}
	public void setQueryequals_xydm(String queryequalsXydm) {
		queryequals_xydm = queryequalsXydm;
	}
	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}
	public void setQueryequals_zydm(String queryequalsZydm) {
		queryequals_zydm = queryequalsZydm;
	}
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequalsBjdm) {
		queryequals_bjdm = queryequalsBjdm;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String[] getPrimarykey_cbv() {
		return primarykey_cbv;
	}
	public void setPrimarykey_cbv(String[] primarykeyCbv) {
		primarykey_cbv = primarykeyCbv;
	}
	public String[] getXnArr() {
		return xnArr;
	}
	public void setXnArr(String[] xnArr) {
		this.xnArr = xnArr;
	}
	public String[] getXqArr() {
		return xqArr;
	}
	public void setXqArr(String[] xqArr) {
		this.xqArr = xqArr;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getZczt() {
		return zczt;
	}
	public void setZczt(String zczt) {
		this.zczt = zczt;
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
	public String getYy() {
		return yy;
	}
	public void setYy(String yy) {
		this.yy = yy;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSave_zczt() {
		return save_zczt;
	}
	public void setSave_zczt(String saveZczt) {
		save_zczt = saveZczt;
	}
	public String getSave_yy() {
		return save_yy;
	}
	public void setSave_yy(String saveYy) {
		save_yy = saveYy;
	}
	public String getSave_xn() {
		return save_xn;
	}
	public void setSave_xn(String saveXn) {
		save_xn = saveXn;
	}
	public String getSave_xq() {
		return save_xq;
	}
	public void setSave_xq(String saveXq) {
		save_xq = saveXq;
	}
	public String getSave_xh() {
		return save_xh;
	}
	public void setSave_xh(String saveXh) {
		save_xh = saveXh;
	}
	public String getQueryequals_zczt() {
		return queryequals_zczt;
	}
	public void setQueryequals_zczt(String queryequalsZczt) {
		queryequals_zczt = queryequalsZczt;
	}
	public String getSave_fdysh() {
		return save_fdysh;
	}
	public void setSave_fdysh(String saveFdysh) {
		save_fdysh = saveFdysh;
	}
	public String getSave_fdyyj() {
		return save_fdyyj;
	}
	public void setSave_fdyyj(String saveFdyyj) {
		save_fdyyj = saveFdyyj;
	}
	
}
