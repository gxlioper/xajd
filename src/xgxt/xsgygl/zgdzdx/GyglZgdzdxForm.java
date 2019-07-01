package xgxt.xsgygl.zgdzdx;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;
/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国地质大学（武汉）公寓管理ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-06</p>
 */
public class GyglZgdzdxForm extends ActionForm {

	private static final long serialVersionUID = 9118963723091084951L;
	private String xiaoqu; //校区
	private String lddm;//楼栋代码
	private String cs;//层数	
	private String cfjs;//每层房间数
	private String jcws;//每间床位数
	private String sfbz;//每间收费标准
	private String xh;//学号
	private String xm;
	private String xb;
	private String xymc;
	private String zymc;
	private String bjmc;
	private String fpbz;//分配标志(房间开放对象类型)
	private String chsfbl;//层号是否补零
	private String fjhws;//房间号位数
	private String qshbpgz;//寝室号编排规则
	private String ssbh;//宿舍编号
	private String qsh;//寝室号(房间号)
	private String xbxd;//楼栋性别限定
	private String nj;//年级
	private String xydm;//学院代码
	private String zydm;//专业代码
	private String bjdm;//班级代码
	private String oracleItem;//
	private String sql;//
	private String sfbz1;//每间收费标准（查询用）
	private String sfbz2;//每间收费标准（查询用）
	private String oldCondiSqlValue;
	private String conditionSqlValue;
	private String xqdm;//校区代码
	private String tb;//图表
	private String qsdh;//寝室电话
	private String sffqfj;
	private String bz;//备注
	private String ksh;//考生号（学号）
	private String yqdm;//园区代码
	private String sfzz;//是否在职
    private String lxdh;//联系电话
    private String rzrq;//任职日期
    private String lzrq;//离职日期
    private String dzyx;//电子邮箱
    private String lz;//楼长
    private String cz;//层长
    private String rq;//日期
	private String hdnr;//活动内容
	private String hdmc;//活动名称
	private String zzdw;//组织单位
	private String sj;//时间
	private String cjry;//参加人员
	private String xn;//学年
	private String xq;//学期
	private String cwh;//床位号
	private String zbdw;//主办单位
	private String fzrxm;//负责人姓名
	private String fzrlxfs;//负责人联系方式、
	private String jsrxm;//经手人姓名
	private String jsrlxfs;//经手人联系方式
	private String hddd;//活动地点
	private String hdksrq;//活动开始日期（日）
	private String hdjsrq;//活动结束日期（日）
	private String hdkssj;//活动开始时间（时）
	private String hdjssj;//活动结束时间（时）
	private String cjrs;//参加人数
	private String sqrdlm;//申请人登陆名
	private String sqrxm;//申请人姓名
	private String yesNo;//审核标志
	private String nd;//年度
	private String imgNj = "";
	private String imgXy = "";
	private String lc;//楼层
	public String getLc() {
		return lc;
	}
	public void setLc(String lc) {
		this.lc = lc;
	}
	public String getImgNj() {
		return imgNj;
	}
	public void setImgNj(String imgNj) {
		this.imgNj = imgNj;
	}
	public String getImgXy() {
		return imgXy;
	}
	public void setImgXy(String imgXy) {
		this.imgXy = imgXy;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = DealString.toGBK(nd);
	}
	public String getCjrs() {
		return cjrs;
	}
	public void setCjrs(String cjrs) {
		this.cjrs = DealString.toGBK(cjrs);
	}
	public String getFzrlxfs() {
		return fzrlxfs;
	}
	public void setFzrlxfs(String fzrlxfs) {
		this.fzrlxfs = DealString.toGBK(fzrlxfs);
	}
	public String getFzrxm() {
		return fzrxm;
	}
	public void setFzrxm(String fzrxm) {
		this.fzrxm = DealString.toGBK(fzrxm);
	}
	public String getHddd() {
		return hddd;
	}
	public void setHddd(String hddd) {
		this.hddd = DealString.toGBK(hddd);
	}
	public String getHdjsrq() {
		return hdjsrq;
	}
	public void setHdjsrq(String hdjsrq) {
		this.hdjsrq = DealString.toGBK(hdjsrq);
	}
	public String getHdksrq() {
		return hdksrq;
	}
	public void setHdksrq(String hdksrq) {
		this.hdksrq = DealString.toGBK(hdksrq);
	}
	public String getJsrlxfs() {
		return jsrlxfs;
	}
	public void setJsrlxfs(String jsrlxfs) {
		this.jsrlxfs = DealString.toGBK(jsrlxfs);
	}
	public String getJsrxm() {
		return jsrxm;
	}
	public void setJsrxm(String jsrxm) {
		this.jsrxm = DealString.toGBK(jsrxm);
	}
	public String getSqrdlm() {
		return sqrdlm;
	}
	public void setSqrdlm(String sqrdlm) {
		this.sqrdlm = DealString.toGBK(sqrdlm);
	}
	public String getSqrxm() {
		return sqrxm;
	}
	public void setSqrxm(String sqrxm) {
		this.sqrxm = DealString.toGBK(sqrxm);
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = DealString.toGBK(yesNo);
	}
	public String getZbdw() {
		return zbdw;
	}
	public void setZbdw(String zbdw) {
		this.zbdw = DealString.toGBK(zbdw);
	}
	public String getCwh() {
		return cwh;
	}
	public void setCwh(String cwh) {
		this.cwh = DealString.toGBK(cwh);
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = DealString.toGBK(xn);
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = DealString.toGBK(xq);
	}
	public String getCjry() {
		return cjry;
	}
	public void setCjry(String cjry) {
		this.cjry = DealString.toGBK(cjry);
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = DealString.toGBK(sj);
	}
	public String getHdmc() {
		return hdmc;
	}
	public void setHdmc(String hdmc) {
		this.hdmc = DealString.toGBK(hdmc);
	}
	public String getHdnr() {
		return hdnr;
	}
	public void setHdnr(String hdnr) {
		this.hdnr = DealString.toGBK(hdnr);
	}
	public String getZzdw() {
		return zzdw;
	}
	public void setZzdw(String zzdw) {
		this.zzdw = DealString.toGBK(zzdw);
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = DealString.toGBK(rq);
	}
	public String getCz() {
		return cz;
	}
	public void setCz(String cz) {
		this.cz = DealString.toGBK(cz);
	}
	public String getLz() {
		return lz;
	}
	public void setLz(String lz) {
		this.lz = DealString.toGBK(lz);
	}
	public String getSfzz() {
		return sfzz;
	}
	public void setSfzz(String sfzz) {
		this.sfzz = DealString.toGBK(sfzz);
	}
	public String getYqdm() {
		return yqdm;
	}
	public void setYqdm(String yqdm) {
		this.yqdm = DealString.toGBK(yqdm);
	}
	public String getKsh() {
		return ksh;
	}
	public void setKsh(String ksh) {
		this.ksh = DealString.toGBK(ksh);
	}
	public String getOldCondiSqlValue() {
		return oldCondiSqlValue;
	}
	public void setOldCondiSqlValue(String oldCondiSqlValue) {
		this.oldCondiSqlValue = DealString.toGBK(oldCondiSqlValue);
	}
	public String getOracleItem() {
		return oracleItem;
	}
	public void setOracleItem(String oracleItem) {
		this.oracleItem = DealString.toGBK(oracleItem);
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = DealString.toGBK(sql);
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = DealString.toGBK(nj);
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = DealString.toGBK(xydm);
	}
	public String getXbxd() {
		return xbxd;
	}
	public void setXbxd(String xbxd) {
		this.xbxd = DealString.toGBK(xbxd);
	}
	public String getSsbh() {
		return ssbh;
	}
	public void setSsbh(String ssbh) {
		this.ssbh = DealString.toGBK(ssbh);
	}
	public String getJcws() {
		return jcws;
	}
	public void setJcws(String jcws) {
		this.jcws = DealString.toGBK(jcws);
	}
	public String getCfjs() {
		return cfjs;
	}
	public void setCfjs(String cfjs) {
		this.cfjs = DealString.toGBK(cfjs);
	}
	public String getCs() {
		return cs;
	}
	public void setCs(String cs) {
		this.cs = DealString.toGBK(cs);
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = DealString.toGBK(lddm);
	}
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = DealString.toGBK(xiaoqu);
	}
	public String getFpbz() {
		return fpbz;
	}
	public void setFpbz(String fpbz) {
		this.fpbz = DealString.toGBK(fpbz);
	}
	public String getSfbz() {
		return sfbz;
	}
	public void setSfbz(String sfbz) {
		this.sfbz = DealString.toGBK(sfbz);
	}
	public String getChsfbl() {
		return chsfbl;
	}
	public void setChsfbl(String chsfbl) {
		this.chsfbl = DealString.toGBK(chsfbl);
	}
	public String getFjhws() {
		return fjhws;
	}
	public void setFjhws(String fjhws) {
		this.fjhws = DealString.toGBK(fjhws);
	}
	public String getQshbpgz() {
		return qshbpgz;
	}
	public void setQshbpgz(String qshbpgz) {
		this.qshbpgz = qshbpgz;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = DealString.toGBK(qsh);
	}
    public String getSfbz1() {
		return sfbz1;
	}
	public void setSfbz1(String sfbz1) {
		this.sfbz1 = DealString.toGBK(sfbz1);
	}
	public String getSfbz2() {
		return sfbz2;
	}
	public void setSfbz2(String sfbz2) {
		this.sfbz2 = DealString.toGBK(sfbz2);
	}
	public String getConditionSqlValue() {
		return conditionSqlValue;
	}
	public void setConditionSqlValue(String conditionSqlValue) {
		this.conditionSqlValue = conditionSqlValue;
	}
	public String getXqdm() {
		return xqdm;
	}
	public void setXqdm(String xqdm) {
		this.xqdm = DealString.toGBK(xqdm);
	}
	public String getTb() {
		return tb;
	}
	public void setTb(String tb) {
		this.tb = DealString.toGBK(tb);
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = DealString.toGBK(bz);
	}
	public String getQsdh() {
		return qsdh;
	}
	public void setQsdh(String qsdh) {
		this.qsdh = DealString.toGBK(qsdh);
	}
	public String getSffqfj() {
		return sffqfj;
	}
	public void setSffqfj(String sffqfj) {
		this.sffqfj = DealString.toGBK(sffqfj);
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = DealString.toGBK(bjdm);
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = DealString.toGBK(zydm);
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = DealString.toGBK(xh);
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = DealString.toGBK(xm);
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = DealString.toGBK(dzyx);
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = DealString.toGBK(lxdh);
	}
	public String getLzrq() {
		return lzrq;
	}
	public void setLzrq(String lzrq) {
		this.lzrq = DealString.toGBK(lzrq);
	}
	public String getRzrq() {
		return rzrq;
	}
	public void setRzrq(String rzrq) {
		this.rzrq = DealString.toGBK(rzrq);
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = DealString.toGBK(bjmc);
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = DealString.toGBK(xb);
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = DealString.toGBK(xymc);
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = DealString.toGBK(zymc);
	}
	public String getHdjssj() {
		return hdjssj;
	}
	public void setHdjssj(String hdjssj) {
		this.hdjssj = DealString.toGBK(hdjssj);
	}
	public String getHdkssj() {
		return hdkssj;
	}
	public void setHdkssj(String hdkssj) {
		this.hdkssj = DealString.toGBK(hdkssj);
	}

}
