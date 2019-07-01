package xgxt.bdsz;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;

public class BdzdForm extends ActionForm {
	private static final long serialVersionUID = -7917976625139621588L;

	//	自定义表单
	private String[] arrZd;

	private String[] arrZdz;
	
	private String[] arrZdlx;
	
	private String[] arrZdcd;
	
	private String[] pkV;
	
	private String[] opid;     //下拉框选项代码
	
	private String[] opmc;     //下拉框选项名称
	
	private HashMap<String, ArrayList<HashMap<String, String>>> opslist;
	
	private String[] arrSfnum;
	
	private String[] arrSfnull;
	
	private String[] arrXzf;

	private String[] arrZdid;

	private String[] arrZdmc;
	
	private String[] arrSfbt;
	
	
	public String[] getArrZd() {
		return arrZd;
	}

	public void setArrZd(String[] arrZd) {
		this.arrZd = arrZd;
	}

	public String[] getArrZdz() {
		return arrZdz;
	}

	public void setArrZdz(String[] arrZdz) {
		this.arrZdz = arrZdz;
	}

	public String[] getArrZdlx() {
		return arrZdlx;
	}

	public void setArrZdlx(String[] arrZdlx) {
		this.arrZdlx = arrZdlx;
	}

	public String[] getArrZdcd() {
		return arrZdcd;
	}

	public void setArrZdcd(String[] arrZdcd) {
		this.arrZdcd = arrZdcd;
	}

	public String[] getPkV() {
		return pkV;
	}

	public void setPkV(String[] pkV) {
		this.pkV = pkV;
	}

	public String[] getOpid() {
		return opid;
	}

	public void setOpid(String[] opid) {
		this.opid = opid;
	}

	public String[] getOpmc() {
		return opmc;
	}

	public void setOpmc(String[] opmc) {
		this.opmc = opmc;
	}

	public HashMap<String, ArrayList<HashMap<String, String>>> getOpslist() {
		return opslist;
	}

	public void setOpslist(
			HashMap<String, ArrayList<HashMap<String, String>>> opslist) {
		this.opslist = opslist;
	}

	public String[] getArrSfnull() {
		return arrSfnull;
	}

	public void setArrSfnull(String[] arrSfnull) {
		this.arrSfnull = arrSfnull;
	}

	public String[] getArrSfnum() {
		return arrSfnum;
	}

	public void setArrSfnum(String[] arrSfnum) {
		this.arrSfnum = arrSfnum;
	}

	public String[] getArrXzf() {
		return arrXzf;
	}

	public void setArrXzf(String[] arrXzf) {
		this.arrXzf = arrXzf;
	}

	public String[] getArrSfbt() {
		return arrSfbt;
	}

	public void setArrSfbt(String[] arrSfbt) {
		this.arrSfbt = arrSfbt;
	}

	public String[] getArrZdid() {
		return arrZdid;
	}

	public void setArrZdid(String[] arrZdid) {
		this.arrZdid = arrZdid;
	}

	public String[] getArrZdmc() {
		return arrZdmc;
	}

	public void setArrZdmc(String[] arrZdmc) {
		this.arrZdmc = arrZdmc;
	}
}
