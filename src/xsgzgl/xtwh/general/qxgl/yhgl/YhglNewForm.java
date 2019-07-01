package xsgzgl.xtwh.general.qxgl.yhgl;

import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.AutoArrayList;
import xsgzgl.comm.form.CommForm;
import xsgzgl.xtwh.general.qxgl.GnmkModel;

import java.util.HashMap;
import java.util.List;

public class YhglNewForm extends CommForm{

	private String yhm;		//用户名
	private String xm;		//姓名
	private String kl;		//密码
	private String zdm;		//组代码
	private String szbm;	//所在部门
	private String dwdm;	//单位代码
	private String qx;		//启用
	private String sffz;	//是否分组
	private String sffdy;   //是否辅导员
	private String sfbzr;   //是否班主任
	private String fpzt;
	private String sfbl;	//是否思政可见（辽宁机电个性化）
	private String pkValue; //
	private String yhgnqx;

	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getKl() {
		return kl;
	}
	public void setKl(String kl) {
		this.kl = kl;
	}
	public String getZdm() {
		return zdm;
	}
	public void setZdm(String zdm) {
		this.zdm = zdm;
	}
	public String getSzbm() {
		return szbm;
	}
	public void setSzbm(String szbm) {
		this.szbm = szbm;
	}
	public String getDwdm() {
		return dwdm;
	}
	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}
	public String getQx() {
		return qx;
	}
	public void setQx(String qx) {
		this.qx = qx;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getSffz() {
		return sffz;
	}
	public void setSffz(String sffz) {
		this.sffz = sffz;
	}
	/**
	 * @return the sffdy
	 */
	public String getSffdy() {
		return sffdy;
	}
	/**
	 * @param sffdy要设置的 sffdy
	 */
	public void setSffdy(String sffdy) {
		this.sffdy = sffdy;
	}
	/**
	 * @return the sfbzr
	 */
	public String getSfbzr() {
		return sfbzr;
	}
	/**
	 * @param sfbzr要设置的 sfbzr
	 */
	public void setSfbzr(String sfbzr) {
		this.sfbzr = sfbzr;
	}
	public String getFpzt() {
		return fpzt;
	}
	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-6 下午04:45:53 
	 * @return		: the sfbl
	 */
	public String getSfbl() {
		return sfbl;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-6 下午04:45:53 
	 * @param 		：sfbl the sfbl to set
	 */
	public void setSfbl(String sfbl) {
		this.sfbl = sfbl;
	}

	public String getYhgnqx() {
		return yhgnqx;
	}

	public void setYhgnqx(String yhgnqx) {
		this.yhgnqx = yhgnqx;
	}
}
