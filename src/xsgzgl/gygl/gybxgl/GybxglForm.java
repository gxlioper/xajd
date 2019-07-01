package xsgzgl.gygl.gybxgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xsgzgl.gygl.comm.GyglNewForm;

public class GybxglForm extends GyglNewForm {

	
	private static final long serialVersionUID = 1L;

	private String pk;			//主键：学号+报修时间
	private String lddm;		//楼栋代码
	private String qsh;			//寝室号
	private String cwh;			//床位号
	private String xh;			//学号
	private String bxnr;		//报修内容
	private String bxsj;		//报修时间
	private String jjcd;		//紧急程度
	private String bxlb;		//保修类别
	private String qwwxsj_ks;	//期望维修时间
	private String qwwxsj_js;	//期望维修时间
	private String lxdh;		//联系电话
	private String clzt;		//处理状态
	private String bclyy;		//不处理原因
	private String wxry;		//维修人员
	private String wxsj;		//维修时间
	private String wxfy;		//维修费用
	private String wxnr;		//维修内容
	private String mycd;		//满意程度
	private String pj;			//评价
	private String zbclyy;		//暂不处理原因
	private String bxlbzxdm;
	private String yhm;
	private String fpzt;        //黑龙江农垦【分配状态】
	private String fpbm;        //黑龙江农垦【分配部门】
	private String flag;        //标志，查询时，用于区分【管理】菜单还是【分配】菜单
	private String filepath;
	private String fpxq;        //苏州卫生职业技术学院【分配校区】
	
	/**
	 * @return the fpxq
	 */
	public String getFpxq() {
		return fpxq;
	}
	/**
	 * @param fpxq要设置的 fpxq
	 */
	public void setFpxq(String fpxq) {
		this.fpxq = fpxq;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the fpzt
	 */
	public String getFpzt() {
		return fpzt;
	}
	/**
	 * @param fpzt要设置的 fpzt
	 */
	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}
	/**
	 * @return the fpbm
	 */
	public String getFpbm() {
		return fpbm;
	}
	/**
	 * @param fpbm要设置的 fpbm
	 */
	public void setFpbm(String fpbm) {
		this.fpbm = fpbm;
	}
	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flag要设置的 flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	private ExportModel exportModel = new ExportModel();
	
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getCwh() {
		return cwh;
	}
	public void setCwh(String cwh) {
		this.cwh = cwh;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getBxnr() {
		return bxnr;
	}
	public void setBxnr(String bxnr) {
		this.bxnr = bxnr;
	}
	public String getBxsj() {
		return bxsj;
	}
	public void setBxsj(String bxsj) {
		this.bxsj = bxsj;
	}
	public String getJjcd() {
		return jjcd;
	}
	public void setJjcd(String jjcd) {
		this.jjcd = jjcd;
	}
	public String getBxlb() {
		return bxlb;
	}
	public void setBxlb(String bxlb) {
		this.bxlb = bxlb;
	}
	public String getQwwxsj_ks() {
		return qwwxsj_ks;
	}
	public void setQwwxsj_ks(String qwwxsjKs) {
		qwwxsj_ks = qwwxsjKs;
	}
	public String getQwwxsj_js() {
		return qwwxsj_js;
	}
	public void setQwwxsj_js(String qwwxsjJs) {
		qwwxsj_js = qwwxsjJs;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getClzt() {
		return clzt;
	}
	public void setClzt(String clzt) {
		this.clzt = clzt;
	}
	public String getBclyy() {
		return bclyy;
	}
	public void setBclyy(String bclyy) {
		this.bclyy = bclyy;
	}
	public String getWxry() {
		return wxry;
	}
	public void setWxry(String wxry) {
		this.wxry = wxry;
	}
	public String getWxsj() {
		return wxsj;
	}
	public void setWxsj(String wxsj) {
		this.wxsj = wxsj;
	}
	public String getWxfy() {
		return wxfy;
	}
	public void setWxfy(String wxfy) {
		this.wxfy = wxfy;
	}
	public String getWxnr() {
		return wxnr;
	}
	public void setWxnr(String wxnr) {
		this.wxnr = wxnr;
	}
	public String getMycd() {
		return mycd;
	}
	public void setMycd(String mycd) {
		this.mycd = mycd;
	}
	public String getPj() {
		return pj;
	}
	public void setPj(String pj) {
		this.pj = pj;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public void setZbclyy(String zbclyy) {
		this.zbclyy = zbclyy;
	}
	public String getZbclyy() {
		return zbclyy;
	}
	/**
	 * @return the bxlbzxdm
	 */
	public String getBxlbzxdm() {
		return bxlbzxdm;
	}
	/**
	 * @param bxlbzxdm要设置的 bxlbzxdm
	 */
	public void setBxlbzxdm(String bxlbzxdm) {
		this.bxlbzxdm = bxlbzxdm;
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
	
}
