package xsgzgl.xsxx.general.xsxxgl;

import java.util.HashMap;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;

/**
 * 学生信息 - 通用管理FORM
 * lt
 * 2013-1-7
 */
public class XsxxtyglForm extends CommForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String xh      ;//学号             
	private String xm      ;//姓名             
	private String xb      ;//性别             
	private String sfzh    ;//身份证号         
	private String nj      ;//年级             
	private String xjztm   ;//学籍状态         
	private String bz      ;//备注             
	private String zd6     ;//潍坊学院--个人简历（从小学填起）
	private String xz      ;//学制             
	private String xydm    ;//学院代码         
	private String xymc    ;//学院名称         
	private String zydm    ;//专业代码         
	private String zymc    ;//专业名称         
	private String bjdm    ;//班级代码(在校)   
	private String bjmc    ;//班级名称         
	private String dqszj   ;//当前所在级       
	private String xy      ;//学院名称         
	private String bmdm    ;//部门代码         
	private String xzb     ;//行政班           
	private String ssbh    ;//宿舍编号         
	private String qsdh    ;//寝室电话         
	private String sjhm    ;//手机号码         
	private String lxdzxx  ;//电子邮箱         
	private String lxdh    ;//联系电话         
	private String mm      ;//密码             
	private String mz      ;//民族                 
	private String xjh     ;//学籍号           
	private String csd     ;//出生地           
	private String hkxz    ;//户口性质         
	private String jrzzmmrq;//加入政治面貌日期 
	private String sfhq    ;//是否华侨         
	private String ksh     ;//高考考生号       
	private String csds    ;//出生地省         
	private String csdshi  ;//出生地市         
	private String csdxian ;//出生地县      
	private String yhdm;
	private String yhkh    ;//银行卡号         
	private String csrq    ;//出生日期
	private String jgshen  ;//籍贯省
	private String jgshi   ;//籍贯市
	private String jgxian  ;//籍贯县
	private String zzmm    ;
	private String syds    ;//生源地省
	private String sydshi  ;//生源地市
	private String sydx    ;//生源地县
	private String xhstr ;  //多个学号
	private String kslb ;  //考生类别
	private String sfzx ;//是否在校
	
	//显示区列表字段
	private String xsqdm ;//               
	private String xsqmc ;//  显示区名称   
	private String szhs  ;//  所占行数     
	private String zpxs  ;//  照片显示     
	private String zpwz  ;//  照片位置     
	private String zpszhs;//  照片所占行数 
	private String xssx  ;//  显示顺序     
	private String sfzk  ;//  是否展开     
	private String sfqy  ;//  是否启用     
	private String sfzd  ;//  是否置顶     
	private HashMap<String, String> valueMap ;
	private String tableName;
	private String pk ;
	private String[] pkValue;
	private String jtcy1_xm   ;
	private String jtcy1_gx   ;  
	private String jtcy1_gzdz ;
	private String jtcy1_lxdh1;
	private String jtcy1_lxdh2;
	private String jtcy2_xm   ;
	private String jtcy2_gx   ;  
	private String jtcy2_gzdz ; 
	private String jtcy2_lxdh1;
	private String jtcy2_lxdh2;
	private String jtcy3_xm   ;
	private String jtcy3_gx   ;   
	private String jtcy3_gzdz ;  
	private String jtcy3_lxdh1;
	private String jtcy3_lxdh2;
	private String lxdh1;
	private String lxdh2;
	private String email;
	private String jtszd;
	private String gnmk;
	private String userType;
	private String kl;
	
	private String zw;//职务
	private String grjl;//个人简历
	private String zd1;//主要事迹
	private String zd2;//获奖情况
	private String zd3;//微信
	private String zd4;//微博
	private String zd5;//入学前单位
	private ExportModel exportModel = new ExportModel();
	
	
	private String bysj;
	private String gj;
	private String xslb;
	private String rxqdw;
	private String sfjh;
	private String zjdm;
	private String xx;
	private String jtcygc;
	private String ccqj;
	private String xwzsxxdz;
	private String rows;
	private String [] xhlist;
	private String zwmc;
	/**
	 * @return the zwmc
	 */
	public String getZwmc() {
		return zwmc;
	}
	/**
	 * @param zwmc要设置的 zwmc
	 */
	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}
	/**
	 * @return the xhlist
	 */
	public String[] getXhlist() {
		return xhlist;
	}
	/**
	 * @param xhlist要设置的 xhlist
	 */
	public void setXhlist(String[] xhlist) {
		this.xhlist = xhlist;
	}
	/**
	 * @return the rows
	 */
	public String getRows() {
		return rows;
	}
	/**
	 * @param rows要设置的 rows
	 */
	public void setRows(String rows) {
		this.rows = rows;
	}
	//填充excle
	private String xn;
	private String xq;
	
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the xhstr
	 */
	public String getXhstr() {
		return xhstr;
	}
	/**
	 * @param xhstr要设置的 xhstr
	 */
	public void setXhstr(String xhstr) {
		this.xhstr = xhstr;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXjztm() {
		return xjztm;
	}
	public void setXjztm(String xjztm) {
		this.xjztm = xjztm;
	}
	public String getZd6() {
		return zd6;
	}
	public void setZd6(String zd6) {
		this.zd6 = zd6;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getDqszj() {
		return dqszj;
	}
	public void setDqszj(String dqszj) {
		this.dqszj = dqszj;
	}
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getXzb() {
		return xzb;
	}
	public void setXzb(String xzb) {
		this.xzb = xzb;
	}
	public String getSsbh() {
		return ssbh;
	}
	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}
	public String getQsdh() {
		return qsdh;
	}
	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getLxdzxx() {
		return lxdzxx;
	}
	public void setLxdzxx(String lxdzxx) {
		this.lxdzxx = lxdzxx;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getXjh() {
		return xjh;
	}
	public void setXjh(String xjh) {
		this.xjh = xjh;
	}
	public String getCsd() {
		return csd;
	}
	public void setCsd(String csd) {
		this.csd = csd;
	}
	public String getHkxz() {
		return hkxz;
	}
	public void setHkxz(String hkxz) {
		this.hkxz = hkxz;
	}
	public String getJrzzmmrq() {
		return jrzzmmrq;
	}
	public void setJrzzmmrq(String jrzzmmrq) {
		this.jrzzmmrq = jrzzmmrq;
	}
	public String getSfhq() {
		return sfhq;
	}
	public void setSfhq(String sfhq) {
		this.sfhq = sfhq;
	}
	public String getKsh() {
		return ksh;
	}
	public void setKsh(String ksh) {
		this.ksh = ksh;
	}
	public String getCsds() {
		return csds;
	}
	public void setCsds(String csds) {
		this.csds = csds;
	}
	public String getCsdshi() {
		return csdshi;
	}
	public void setCsdshi(String csdshi) {
		this.csdshi = csdshi;
	}
	public String getCsdxian() {
		return csdxian;
	}
	public void setCsdxian(String csdxian) {
		this.csdxian = csdxian;
	}
	public String getYhkh() {
		return yhkh;
	}
	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}
	public String getXsqdm() {
		return xsqdm;
	}
	public void setXsqdm(String xsqdm) {
		this.xsqdm = xsqdm;
	}
	public String getXsqmc() {
		return xsqmc;
	}
	public void setXsqmc(String xsqmc) {
		this.xsqmc = xsqmc;
	}
	public String getSzhs() {
		return szhs;
	}
	public void setSzhs(String szhs) {
		this.szhs = szhs;
	}
	public String getZpxs() {
		return zpxs;
	}
	public void setZpxs(String zpxs) {
		this.zpxs = zpxs;
	}
	public String getZpwz() {
		return zpwz;
	}
	public void setZpwz(String zpwz) {
		this.zpwz = zpwz;
	}
	public String getZpszhs() {
		return zpszhs;
	}
	public void setZpszhs(String zpszhs) {
		this.zpszhs = zpszhs;
	}
	public String getXssx() {
		return xssx;
	}
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	public String getSfzk() {
		return sfzk;
	}
	public void setSfzk(String sfzk) {
		this.sfzk = sfzk;
	}
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	public String getSfzd() {
		return sfzd;
	}
	public void setSfzd(String sfzd) {
		this.sfzd = sfzd;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getJgshen() {
		return jgshen;
	}
	public void setJgshen(String jgshen) {
		this.jgshen = jgshen;
	}
	public String getJgshi() {
		return jgshi;
	}
	public void setJgshi(String jgshi) {
		this.jgshi = jgshi;
	}
	public String getJgxian() {
		return jgxian;
	}
	public void setJgxian(String jgxian) {
		this.jgxian = jgxian;
	}
	public String getZzmm() {
		return zzmm;
	}
	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}
	public HashMap<String, String> getValueMap() {
		return valueMap;
	}
	public void setValueMap(HashMap<String, String> valueMap) {
		this.valueMap = valueMap;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String[] getPkValue() {
		return pkValue;
	}
	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}
	public String getJtcy1_xm() {
		return jtcy1_xm;
	}
	public void setJtcy1_xm(String jtcy1Xm) {
		jtcy1_xm = jtcy1Xm;
	}
	public String getJtcy1_gx() {
		return jtcy1_gx;
	}
	public void setJtcy1_gx(String jtcy1Gx) {
		jtcy1_gx = jtcy1Gx;
	}
	public String getJtcy1_gzdz() {
		return jtcy1_gzdz;
	}
	public void setJtcy1_gzdz(String jtcy1Gzdz) {
		jtcy1_gzdz = jtcy1Gzdz;
	}
	public String getJtcy1_lxdh1() {
		return jtcy1_lxdh1;
	}
	public void setJtcy1_lxdh1(String jtcy1Lxdh1) {
		jtcy1_lxdh1 = jtcy1Lxdh1;
	}
	public String getJtcy1_lxdh2() {
		return jtcy1_lxdh2;
	}
	public void setJtcy1_lxdh2(String jtcy1Lxdh2) {
		jtcy1_lxdh2 = jtcy1Lxdh2;
	}
	public String getJtcy2_xm() {
		return jtcy2_xm;
	}
	public void setJtcy2_xm(String jtcy2Xm) {
		jtcy2_xm = jtcy2Xm;
	}
	public String getJtcy2_gx() {
		return jtcy2_gx;
	}
	public void setJtcy2_gx(String jtcy2Gx) {
		jtcy2_gx = jtcy2Gx;
	}
	public String getJtcy2_gzdz() {
		return jtcy2_gzdz;
	}
	public void setJtcy2_gzdz(String jtcy2Gzdz) {
		jtcy2_gzdz = jtcy2Gzdz;
	}
	public String getJtcy2_lxdh1() {
		return jtcy2_lxdh1;
	}
	public void setJtcy2_lxdh1(String jtcy2Lxdh1) {
		jtcy2_lxdh1 = jtcy2Lxdh1;
	}
	public String getJtcy2_lxdh2() {
		return jtcy2_lxdh2;
	}
	public void setJtcy2_lxdh2(String jtcy2Lxdh2) {
		jtcy2_lxdh2 = jtcy2Lxdh2;
	}
	public String getJtcy3_xm() {
		return jtcy3_xm;
	}
	public void setJtcy3_xm(String jtcy3Xm) {
		jtcy3_xm = jtcy3Xm;
	}
	public String getJtcy3_gx() {
		return jtcy3_gx;
	}
	public void setJtcy3_gx(String jtcy3Gx) {
		jtcy3_gx = jtcy3Gx;
	}
	public String getJtcy3_gzdz() {
		return jtcy3_gzdz;
	}
	public void setJtcy3_gzdz(String jtcy3Gzdz) {
		jtcy3_gzdz = jtcy3Gzdz;
	}
	public String getJtcy3_lxdh1() {
		return jtcy3_lxdh1;
	}
	public void setJtcy3_lxdh1(String jtcy3Lxdh1) {
		jtcy3_lxdh1 = jtcy3Lxdh1;
	}
	public String getJtcy3_lxdh2() {
		return jtcy3_lxdh2;
	}
	public void setJtcy3_lxdh2(String jtcy3Lxdh2) {
		jtcy3_lxdh2 = jtcy3Lxdh2;
	}
	public String getLxdh1() {
		return lxdh1;
	}
	public void setLxdh1(String lxdh1) {
		this.lxdh1 = lxdh1;
	}
	public String getLxdh2() {
		return lxdh2;
	}
	public void setLxdh2(String lxdh2) {
		this.lxdh2 = lxdh2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJtszd() {
		return jtszd;
	}
	public void setJtszd(String jtszd) {
		this.jtszd = jtszd;
	}
	public String getSyds() {
		return syds;
	}
	public void setSyds(String syds) {
		this.syds = syds;
	}
	public String getSydshi() {
		return sydshi;
	}
	public void setSydshi(String sydshi) {
		this.sydshi = sydshi;
	}
	public String getSydx() {
		return sydx;
	}
	public void setSydx(String sydx) {
		this.sydx = sydx;
	}
	public String getGnmk() {
		return gnmk;
	}
	public void setGnmk(String gnmk) {
		this.gnmk = gnmk;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public void setKl(String kl) {
		this.kl = kl;
	}
	public String getKl() {
		return kl;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public String getGrjl() {
		return grjl;
	}
	public void setGrjl(String grjl) {
		this.grjl = grjl;
	}
	public String getZd1() {
		return zd1;
	}
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	public String getZd2() {
		return zd2;
	}
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public String getZd3() {
		return zd3;
	}
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	public String getZd4() {
		return zd4;
	}
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}
	public String getZd5() {
		return zd5;
	}
	public void setZd5(String zd5) {
		this.zd5 = zd5;
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
	public String getBysj() {
		return bysj;
	}
	public void setBysj(String bysj) {
		this.bysj = bysj;
	}
	public String getGj() {
		return gj;
	}
	public void setGj(String gj) {
		this.gj = gj;
	}
	public String getXslb() {
		return xslb;
	}
	public void setXslb(String xslb) {
		this.xslb = xslb;
	}
	public String getRxqdw() {
		return rxqdw;
	}
	public void setRxqdw(String rxqdw) {
		this.rxqdw = rxqdw;
	}
	public String getSfjh() {
		return sfjh;
	}
	public void setSfjh(String sfjh) {
		this.sfjh = sfjh;
	}
	public String getZjdm() {
		return zjdm;
	}
	public void setZjdm(String zjdm) {
		this.zjdm = zjdm;
	}
	public String getXx() {
		return xx;
	}
	public void setXx(String xx) {
		this.xx = xx;
	}
	public String getJtcygc() {
		return jtcygc;
	}
	public void setJtcygc(String jtcygc) {
		this.jtcygc = jtcygc;
	}
	public String getCcqj() {
		return ccqj;
	}
	public void setCcqj(String ccqj) {
		this.ccqj = ccqj;
	}
	public String getXwzsxxdz() {
		return xwzsxxdz;
	}
	public void setXwzsxxdz(String xwzsxxdz) {
		this.xwzsxxdz = xwzsxxdz;
	}
	/**
	 * @return the kslb
	 */
	public String getKslb() {
		return kslb;
	}
	/**
	 * @param kslb要设置的 kslb
	 */
	public void setKslb(String kslb) {
		this.kslb = kslb;
	}
	public String getSfzx() {
		return sfzx;
	}
	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}
	
}
