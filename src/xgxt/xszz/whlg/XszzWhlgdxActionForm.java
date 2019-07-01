
package xgxt.xszz.whlg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 武汉理工大学ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-09</p>
 */
public class XszzWhlgdxActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xh;    //
	private String xn;
	private String xm;    //
	private String xb;
	private String nj;    //
	private String xz;
	private String csrq;
	private String sfzh;
	private String mzmc;
	private String zzmmmc;
	private String xydm;  //
	private String xymc;  //
	private String zydm;  //
	private String zymc;  //
	private String bjdm;  //
	private String bjmc;  //
	private String rxqhk;
	private String jtrks;
	private String byxx;
	private String grtc;
	private String sfgc;
	private String sfdq;
	private String sflszn;
	private String jtxxtxdz;
	private String jtyzbm;
	private String jtlxdh;
	private String jtcy1_xm;
	private String jtcy1_nl;
	private String jtcy1_gx;
	private String jtcy1_gzdw;
	private String jtcy1_zy;
	private String jtcy1_sr;
	private String jtcy1_jkzk;
	private String jtcy2_xm;
	private String jtcy2_nl;
	private String jtcy2_gx;
	private String jtcy2_gzdw;
	private String jtcy2_zy;
	private String jtcy2_sr;
	private String jtcy2_jkzk;
	private String jtcy3_xm;
	private String jtcy3_nl;
	private String jtcy3_gx;
	private String jtcy3_gzdw;
	private String jtcy3_zy;
	private String jtcy3_sr;
	private String jtcy3_jkzk;
	private String jtcy4_xm;
	private String jtcy4_nl;
	private String jtcy4_gx;
	private String jtcy4_gzdw;
	private String jtcy4_zy;
	private String jtcy4_sr;
	private String jtcy4_jkzk;
	private String jtcy5_xm;
	private String jtcy5_nl;
	private String jtcy5_gx;
	private String jtcy5_gzdw;
	private String jtcy5_zy;
	private String jtcy5_sr;
	private String jtcy5_jkzk;
	private String jtcy6_xm;
	private String jtcy6_nl;
	private String jtcy6_gx;
	private String jtcy6_gzdw;
	private String jtcy6_zy;
	private String jtcy6_sr;
	private String jtcy6_jkzk;
	private String jtrjnsr;
	private String jtmytgshf;
	private String xsbxnyhzzqk;
	private String jtzszrzh;
	private String jttfywsj;
	private String jtcyycjnmrndnlr;
	private String jtcysyqk;
	private String qtqk;
	private String mzbm_xxtxdz;
	private String mzbm_lxdh;
	private String mzbm_yzbm;
	
	/** COMMON   */
	private String doType = "";
	private String action = "";
	private String tableName = "";
	private String realTable = "";
	private List<HashMap<String,String>> columnCN = new ArrayList<HashMap<String,String>>();
	// 查询单个信息时使用
	 private String pkVal = "";//作为主键查询
	 
	/**家庭经济困难生申请字段      BEGIN*/
	private String ghlmkh ="";          
	private String zxlxdh ="";
	private String is_shbzx ="";
	private String is_shjp ="";
	private String is_lszn ="";
	private String is_stcj ="";
	private String is_zrzh ="";
	private String is_pysq ="";
	private String is_dq ="";
	private String is_dznsx ="";
	private String is_fmxg ="";
	private String is_qtys ="";
	private String sqsj ="";     
	private String mzpy_tjdc ="";
	private String mzpy_csly ="";
	private String rdjd_xyyj ="";
	private String rdjd_xyyj_pc ="";
	private String rdjd_xxyj ="";
	private String rdjd_xxyj_pc ="";
	/**家庭经济困难生申请字段       END*/
	//
	/** 国家助学贷款申请字段   BEGIN */
	private String byxxmc ="";   //毕业学校名称
	private String jkrxm ="";
	private String xl ="";       //学历
	private String jtzz ="";
	private String jtdh ="";
	private String sfdkyjs ="";
	private String byqx ="";
	private String gzdw ="";
	private String jkr_jtxxzz = ""; //借款人家庭详细住址
	private String dwdh ="";
	private String dwdz ="";
	private String dwyb ="";
	private String yddh ="";
	private String email ="";
	private String hyzk ="";
	private String po_xm ="";
	private String po_lxdh ="";
	private String po_gzdw ="";
	private String po_dwdz ="";	
	private String lxr_xm ="";
	private String lxr_xb ="";
	private String lxr_yjkrgx ="";
	private String lxr_csrq ="";
	private String lxr_zw ="";
	private String lxr_jtlxdh ="";	
	private String lxr_sfzh ="";
	private String lxr_dwlxdh ="";
	private String lxr_gzdw_dz ="";
	private String lxr_dwyb ="";
	private String jkr_yb ="";
	private String jkr_dh ="";
	private String jkr_fq_xm ="";
	private String jkr_fq_zy ="";	
	private String jkr_fq_sfzh ="";
	private String jkr_mq_xm ="";
	private String jkr_mq_zy ="";
	private String jkr_mq_sfzh ="";
	private String yhmc ="";
	private String bz ="";
	/** 国家助学贷款申请字段   END */
	//
	/**国家助学贷款审批  BEGIN*/
	private String sqdkzje = "";
	private String xzfdkzje = "";
	private String zsfdkzje = "";
	private String nd_one = "";
	private String  nd_one_fkje = "";
	private String nd_two = "";
	private String nd_two_fkje = "";
	private String nd_three  = "";
	private String nd_three_fkje  = "";
	private String nd_four     = "";
	private String nd_four_fkje  = "";
	private String nd_five    = "";
	private String nd_five_fkje  = "";
	private String dkqx_months  = "";
	private String dkqx_kssj   = "";
	private String dkqx_jssj  = "";
	private String xdyyj  = "";
	private String kzyj  = "";
	private String yqspryj  = "";
	private String zxnx;   //在校年限
	
	//查询视图时要用到
	private String rxnf = "";   //入学年份
	private String bynf = "";   //毕业年份
	private String xxmc = "";   //学校名称
	private String jtysr = "";  //家庭月收入
	private String ssdh  = "";  //宿舍电话 
	/**国家助学贷款审批  END*/
	
	
	/**还款协议特有字段 		BEGIN*/
	private String jkr  = "";
	private String yxzjhm   = "";
	private String  zs  = "";
	//private String gzdw   = "";
	private String  yzbm   = "";
	private String lxdh   = "";
	private String  htbh   = "";
	private String  jzrq   = "";
	private String  fkje  = "";
    private String   jf_lkxxsj   = "";
    private String  jf_lkxx_yy  = "";
    private String  jflkxx_mc   = "";
    private String fk_fs   = "";
    private String  fk_jtfs   = "";
    private String fjq   = "";
    private String fklx_kssj   = "";
    private String   fklx_jssj  = "";
    private String   fklxys   = "";
    private String  fkbjlx_kssj  = "";
    private String   fkbjlx_jssj  = "";
    private String  fkbjlxys   = "";
    private String   zffm   = "";
    private String  zfh   = "";
	
	/**还款协议特有字段 		END*/
    
    /**资助项目 特有字段 BEGIN*/
    private String nd;
    private String xmdm;
    private String xmmc;
    private String sqly;
    private String xysh;
    private String xyshyj;
    private String xyshsj;
    private String xypzje;
    private String xxsh;
    private String xxshyj;
    private String xxshsj;
    private String xxpzje;
    private String sfyycjcshzyhd;
    private String sfyysqgjzxdkhqgzx;
    private String sfjq;
    private String sfge;
    private String sfcj;
    private String sfjls;
    private String sfly;
    private String sfzb;
    private String jtcy1_nsr;
    private String jtcy2_nsr;
    private String jtcy3_nsr;
    private String jtcy4_nsr;
    private String jtcy5_nsr;
    private String xszbdszqk;
    private String jtzszrzhqk;
    private String jtzstfywsj;
    private String mzbm_txdz;
    private String sfkns;
    private String syd;
    private String ssbh;
    private String qsdh;
    private String rxny;
    private String kh;
    private String lydq;
    /**资助项目 特有字段 END*/
    public String getRxny() {
		return rxny;
	}
	public void setRxny(String rxny) {
		this.rxny = rxny;
	}
    public String getQsdh() {
		return qsdh;
	}
	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}
	public String getDkqx_jssj() {
		return dkqx_jssj;
	}
	public void setDkqx_jssj(String dkqx_jssj) {
		this.dkqx_jssj = dkqx_jssj;
	}
	public String getDkqx_kssj() {
		return dkqx_kssj;
	}
	public void setDkqx_kssj(String dkqx_kssj) {
		this.dkqx_kssj = dkqx_kssj;
	}

	public String getDkqx_months() {
		return dkqx_months;
	}

	public void setDkqx_months(String dkqx_months) {
		this.dkqx_months = dkqx_months;
	}

	public String getKzyj() {
		return kzyj;
	}

	public void setKzyj(String kzyj) {
		this.kzyj = kzyj;
	}

	public String getNd_five() {
		return nd_five;
	}

	public void setNd_five(String nd_five) {
		this.nd_five = nd_five;
	}

	public String getNd_five_fkje() {
		return nd_five_fkje;
	}

	public void setNd_five_fkje(String nd_five_fkje) {
		this.nd_five_fkje = nd_five_fkje;
	}

	public String getNd_four() {
		return nd_four;
	}

	public void setNd_four(String nd_four) {
		this.nd_four = nd_four;
	}

	public String getNd_four_fkje() {
		return nd_four_fkje;
	}

	public void setNd_four_fkje(String nd_four_fkje) {
		this.nd_four_fkje = nd_four_fkje;
	}

	public String getNd_one() {
		return nd_one;
	}

	public void setNd_one(String nd_one) {
		this.nd_one = nd_one;
	}

	public String getNd_one_fkje() {
		return nd_one_fkje;
	}

	public void setNd_one_fkje(String nd_one_fkje) {
		this.nd_one_fkje = nd_one_fkje;
	}

	public String getNd_three() {
		return nd_three;
	}

	public void setNd_three(String nd_three) {
		this.nd_three = nd_three;
	}

	public String getNd_three_fkje() {
		return nd_three_fkje;
	}

	public void setNd_three_fkje(String nd_three_fkje) {
		this.nd_three_fkje = nd_three_fkje;
	}

	public String getNd_two() {
		return nd_two;
	}

	public void setNd_two(String nd_two) {
		this.nd_two = nd_two;
	}

	public String getNd_two_fkje() {
		return nd_two_fkje;
	}

	public void setNd_two_fkje(String nd_two_fkje) {
		this.nd_two_fkje = nd_two_fkje;
	}

	public String getSqdkzje() {
		return sqdkzje;
	}

	public void setSqdkzje(String sqdkzje) {
		this.sqdkzje = sqdkzje;
	}

	public String getXdyyj() {
		return xdyyj;
	}

	public void setXdyyj(String xdyyj) {
		this.xdyyj = xdyyj;
	}

	public String getXzfdkzje() {
		return xzfdkzje;
	}

	public void setXzfdkzje(String xzfdkzje) {
		this.xzfdkzje = xzfdkzje;
	}

	public String getYqspryj() {
		return yqspryj;
	}

	public void setYqspryj(String yqspryj) {
		this.yqspryj = yqspryj;
	}

	public String getZsfdkzje() {
		return zsfdkzje;
	}

	public void setZsfdkzje(String zsfdkzje) {
		this.zsfdkzje = zsfdkzje;
	}


	
	public String getByqx() {
		return byqx;
	}

	public void setByqx(String byqx) {
		this.byqx = byqx;
	}

	public String getByxxmc() {
		return byxxmc;
	}

	public void setByxxmc(String byxxmc) {
		this.byxxmc = byxxmc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getDwdh() {
		return dwdh;
	}

	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}

	public String getDwdz() {
		return dwdz;
	}

	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}

	public String getDwyb() {
		return dwyb;
	}

	public void setDwyb(String dwyb) {
		this.dwyb = dwyb;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGzdw() {
		return gzdw;
	}

	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}

	public String getHyzk() {
		return hyzk;
	}

	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}

	public String getJkr_dh() {
		return jkr_dh;
	}

	public void setJkr_dh(String jkr_dh) {
		this.jkr_dh = jkr_dh;
	}

	public String getJkr_fq_sfzh() {
		return jkr_fq_sfzh;
	}

	public void setJkr_fq_sfzh(String jkr_fq_sfzh) {
		this.jkr_fq_sfzh = jkr_fq_sfzh;
	}

	public String getJkr_fq_xm() {
		return jkr_fq_xm;
	}

	public void setJkr_fq_xm(String jkr_fq_xm) {
		this.jkr_fq_xm = jkr_fq_xm;
	}

	public String getJkr_fq_zy() {
		return jkr_fq_zy;
	}

	public void setJkr_fq_zy(String jkr_fq_zy) {
		this.jkr_fq_zy = jkr_fq_zy;
	}

	public String getJkr_mq_sfzh() {
		return jkr_mq_sfzh;
	}

	public void setJkr_mq_sfzh(String jkr_mq_sfzh) {
		this.jkr_mq_sfzh = jkr_mq_sfzh;
	}

	public String getJkr_mq_xm() {
		return jkr_mq_xm;
	}

	public void setJkr_mq_xm(String jkr_mq_xm) {
		this.jkr_mq_xm = jkr_mq_xm;
	}

	public String getJkr_mq_zy() {
		return jkr_mq_zy;
	}

	public void setJkr_mq_zy(String jkr_mq_zy) {
		this.jkr_mq_zy = jkr_mq_zy;
	}

	public String getJkr_yb() {
		return jkr_yb;
	}

	public void setJkr_yb(String jkr_yb) {
		this.jkr_yb = jkr_yb;
	}

	public String getJkrxm() {
		return jkrxm;
	}

	public void setJkrxm(String jkrxm) {
		this.jkrxm = jkrxm;
	}

	public String getJtdh() {
		return jtdh;
	}

	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}

	public String getJtzz() {
		return jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	public String getLxr_csrq() {
		return lxr_csrq;
	}

	public void setLxr_csrq(String lxr_csrq) {
		this.lxr_csrq = lxr_csrq;
	}

	public String getLxr_dwlxdh() {
		return lxr_dwlxdh;
	}

	public void setLxr_dwlxdh(String lxr_dwlxdh) {
		this.lxr_dwlxdh = lxr_dwlxdh;
	}

	public String getLxr_dwyb() {
		return lxr_dwyb;
	}

	public void setLxr_dwyb(String lxr_dwyb) {
		this.lxr_dwyb = lxr_dwyb;
	}

	public String getLxr_gzdw_dz() {
		return lxr_gzdw_dz;
	}

	public void setLxr_gzdw_dz(String lxr_gzdw_dz) {
		this.lxr_gzdw_dz = lxr_gzdw_dz;
	}

	public String getLxr_jtlxdh() {
		return lxr_jtlxdh;
	}

	public void setLxr_jtlxdh(String lxr_jtlxdh) {
		this.lxr_jtlxdh = lxr_jtlxdh;
	}

	public String getLxr_sfzh() {
		return lxr_sfzh;
	}

	public void setLxr_sfzh(String lxr_sfzh) {
		this.lxr_sfzh = lxr_sfzh;
	}

	public String getLxr_xb() {
		return lxr_xb;
	}

	public void setLxr_xb(String lxr_xb) {
		this.lxr_xb = lxr_xb;
	}

	public String getLxr_xm() {
		return lxr_xm;
	}

	public void setLxr_xm(String lxr_xm) {
		this.lxr_xm = lxr_xm;
	}

	public String getLxr_yjkrgx() {
		return lxr_yjkrgx;
	}

	public void setLxr_yjkrgx(String lxr_yjkrgx) {
		this.lxr_yjkrgx = lxr_yjkrgx;
	}

	public String getLxr_zw() {
		return lxr_zw;
	}

	public void setLxr_zw(String lxr_zw) {
		this.lxr_zw = lxr_zw;
	}

	public String getPo_dwdz() {
		return po_dwdz;
	}

	public void setPo_dwdz(String po_dwdz) {
		this.po_dwdz = po_dwdz;
	}

	public String getPo_gzdw() {
		return po_gzdw;
	}

	public void setPo_gzdw(String po_gzdw) {
		this.po_gzdw = po_gzdw;
	}

	public String getPo_lxdh() {
		return po_lxdh;
	}

	public void setPo_lxdh(String po_lxdh) {
		this.po_lxdh = po_lxdh;
	}

	public String getPo_xm() {
		return po_xm;
	}

	public void setPo_xm(String po_xm) {
		this.po_xm = po_xm;
	}

	public String getSfdkyjs() {
		return sfdkyjs;
	}

	public void setSfdkyjs(String sfdkyjs) {
		this.sfdkyjs = sfdkyjs;
	}

	public String getXl() {
		return xl;
	}

	public void setXl(String xl) {
		this.xl = xl;
	}

	public String getYddh() {
		return yddh;
	}

	public void setYddh(String yddh) {
		this.yddh = yddh;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
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
	public String getByxx() {
		return byxx;
	}
	public void setByxx(String byxx) {
		this.byxx = byxx;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getGrtc() {
		return grtc;
	}
	public void setGrtc(String grtc) {
		this.grtc = grtc;
	}
	public String getJtcy1_gx() {
		return jtcy1_gx;
	}
	public void setJtcy1_gx(String jtcy1_gx) {
		this.jtcy1_gx = jtcy1_gx;
	}
	public String getJtcy1_gzdw() {
		return jtcy1_gzdw;
	}
	public void setJtcy1_gzdw(String jtcy1_gzdw) {
		this.jtcy1_gzdw = jtcy1_gzdw;
	}
	public String getJtcy1_jkzk() {
		return jtcy1_jkzk;
	}
	public void setJtcy1_jkzk(String jtcy1_jkzk) {
		this.jtcy1_jkzk = jtcy1_jkzk;
	}
	public String getJtcy1_nl() {
		return jtcy1_nl;
	}
	public void setJtcy1_nl(String jtcy1_nl) {
		this.jtcy1_nl = jtcy1_nl;
	}
	public String getJtcy1_sr() {
		return jtcy1_sr;
	}
	public void setJtcy1_sr(String jtcy1_sr) {
		this.jtcy1_sr = jtcy1_sr;
	}
	public String getJtcy1_xm() {
		return jtcy1_xm;
	}
	public void setJtcy1_xm(String jtcy1_xm) {
		this.jtcy1_xm = jtcy1_xm;
	}
	public String getJtcy1_zy() {
		return jtcy1_zy;
	}
	public void setJtcy1_zy(String jtcy1_zy) {
		this.jtcy1_zy = jtcy1_zy;
	}
	public String getJtcy2_gx() {
		return jtcy2_gx;
	}
	public void setJtcy2_gx(String jtcy2_gx) {
		this.jtcy2_gx = jtcy2_gx;
	}
	public String getJtcy2_gzdw() {
		return jtcy2_gzdw;
	}
	public void setJtcy2_gzdw(String jtcy2_gzdw) {
		this.jtcy2_gzdw = jtcy2_gzdw;
	}
	public String getJtcy2_jkzk() {
		return jtcy2_jkzk;
	}
	public void setJtcy2_jkzk(String jtcy2_jkzk) {
		this.jtcy2_jkzk = jtcy2_jkzk;
	}
	public String getJtcy2_nl() {
		return jtcy2_nl;
	}
	public void setJtcy2_nl(String jtcy2_nl) {
		this.jtcy2_nl = jtcy2_nl;
	}
	public String getJtcy2_sr() {
		return jtcy2_sr;
	}
	public void setJtcy2_sr(String jtcy2_sr) {
		this.jtcy2_sr = jtcy2_sr;
	}
	public String getJtcy2_xm() {
		return jtcy2_xm;
	}
	public void setJtcy2_xm(String jtcy2_xm) {
		this.jtcy2_xm = jtcy2_xm;
	}
	public String getJtcy2_zy() {
		return jtcy2_zy;
	}
	public void setJtcy2_zy(String jtcy2_zy) {
		this.jtcy2_zy = jtcy2_zy;
	}
	public String getJtcy3_gx() {
		return jtcy3_gx;
	}
	public void setJtcy3_gx(String jtcy3_gx) {
		this.jtcy3_gx = jtcy3_gx;
	}
	public String getJtcy3_gzdw() {
		return jtcy3_gzdw;
	}
	public void setJtcy3_gzdw(String jtcy3_gzdw) {
		this.jtcy3_gzdw = jtcy3_gzdw;
	}
	public String getJtcy3_jkzk() {
		return jtcy3_jkzk;
	}
	public void setJtcy3_jkzk(String jtcy3_jkzk) {
		this.jtcy3_jkzk = jtcy3_jkzk;
	}
	public String getJtcy3_nl() {
		return jtcy3_nl;
	}
	public void setJtcy3_nl(String jtcy3_nl) {
		this.jtcy3_nl = jtcy3_nl;
	}
	public String getJtcy3_sr() {
		return jtcy3_sr;
	}
	public void setJtcy3_sr(String jtcy3_sr) {
		this.jtcy3_sr = jtcy3_sr;
	}
	public String getJtcy3_xm() {
		return jtcy3_xm;
	}
	public void setJtcy3_xm(String jtcy3_xm) {
		this.jtcy3_xm = jtcy3_xm;
	}
	public String getJtcy3_zy() {
		return jtcy3_zy;
	}
	public void setJtcy3_zy(String jtcy3_zy) {
		this.jtcy3_zy = jtcy3_zy;
	}
	public String getJtcy4_gx() {
		return jtcy4_gx;
	}
	public void setJtcy4_gx(String jtcy4_gx) {
		this.jtcy4_gx = jtcy4_gx;
	}
	public String getJtcy4_gzdw() {
		return jtcy4_gzdw;
	}
	public void setJtcy4_gzdw(String jtcy4_gzdw) {
		this.jtcy4_gzdw = jtcy4_gzdw;
	}
	public String getJtcy4_jkzk() {
		return jtcy4_jkzk;
	}
	public void setJtcy4_jkzk(String jtcy4_jkzk) {
		this.jtcy4_jkzk = jtcy4_jkzk;
	}
	public String getJtcy4_nl() {
		return jtcy4_nl;
	}
	public void setJtcy4_nl(String jtcy4_nl) {
		this.jtcy4_nl = jtcy4_nl;
	}
	public String getJtcy4_sr() {
		return jtcy4_sr;
	}
	public void setJtcy4_sr(String jtcy4_sr) {
		this.jtcy4_sr = jtcy4_sr;
	}
	public String getJtcy4_xm() {
		return jtcy4_xm;
	}
	public void setJtcy4_xm(String jtcy4_xm) {
		this.jtcy4_xm = jtcy4_xm;
	}
	public String getJtcy4_zy() {
		return jtcy4_zy;
	}
	public void setJtcy4_zy(String jtcy4_zy) {
		this.jtcy4_zy = jtcy4_zy;
	}
	public String getJtcy5_gx() {
		return jtcy5_gx;
	}
	public void setJtcy5_gx(String jtcy5_gx) {
		this.jtcy5_gx = jtcy5_gx;
	}
	public String getJtcy5_gzdw() {
		return jtcy5_gzdw;
	}
	public void setJtcy5_gzdw(String jtcy5_gzdw) {
		this.jtcy5_gzdw = jtcy5_gzdw;
	}
	public String getJtcy5_jkzk() {
		return jtcy5_jkzk;
	}
	public void setJtcy5_jkzk(String jtcy5_jkzk) {
		this.jtcy5_jkzk = jtcy5_jkzk;
	}
	public String getJtcy5_nl() {
		return jtcy5_nl;
	}
	public void setJtcy5_nl(String jtcy5_nl) {
		this.jtcy5_nl = jtcy5_nl;
	}
	public String getJtcy5_sr() {
		return jtcy5_sr;
	}
	public void setJtcy5_sr(String jtcy5_sr) {
		this.jtcy5_sr = jtcy5_sr;
	}
	public String getJtcy5_xm() {
		return jtcy5_xm;
	}
	public void setJtcy5_xm(String jtcy5_xm) {
		this.jtcy5_xm = jtcy5_xm;
	}
	public String getJtcy5_zy() {
		return jtcy5_zy;
	}
	public void setJtcy5_zy(String jtcy5_zy) {
		this.jtcy5_zy = jtcy5_zy;
	}
	public String getJtcy6_gx() {
		return jtcy6_gx;
	}
	public void setJtcy6_gx(String jtcy6_gx) {
		this.jtcy6_gx = jtcy6_gx;
	}
	public String getJtcy6_gzdw() {
		return jtcy6_gzdw;
	}
	public void setJtcy6_gzdw(String jtcy6_gzdw) {
		this.jtcy6_gzdw = jtcy6_gzdw;
	}
	public String getJtcy6_jkzk() {
		return jtcy6_jkzk;
	}
	public void setJtcy6_jkzk(String jtcy6_jkzk) {
		this.jtcy6_jkzk = jtcy6_jkzk;
	}
	public String getJtcy6_nl() {
		return jtcy6_nl;
	}
	public void setJtcy6_nl(String jtcy6_nl) {
		this.jtcy6_nl = jtcy6_nl;
	}
	public String getJtcy6_sr() {
		return jtcy6_sr;
	}
	public void setJtcy6_sr(String jtcy6_sr) {
		this.jtcy6_sr = jtcy6_sr;
	}
	public String getJtcy6_xm() {
		return jtcy6_xm;
	}
	public void setJtcy6_xm(String jtcy6_xm) {
		this.jtcy6_xm = jtcy6_xm;
	}
	public String getJtcy6_zy() {
		return jtcy6_zy;
	}
	public void setJtcy6_zy(String jtcy6_zy) {
		this.jtcy6_zy = jtcy6_zy;
	}
	public String getJtcysyqk() {
		return jtcysyqk;
	}
	public void setJtcysyqk(String jtcysyqk) {
		this.jtcysyqk = jtcysyqk;
	}
	public String getJtcyycjnmrndnlr() {
		return jtcyycjnmrndnlr;
	}
	public void setJtcyycjnmrndnlr(String jtcyycjnmrndnlr) {
		this.jtcyycjnmrndnlr = jtcyycjnmrndnlr;
	}
	public String getJtlxdh() {
		return jtlxdh;
	}
	public void setJtlxdh(String jtlxdh) {
		this.jtlxdh = jtlxdh;
	}
	public String getJtmytgshf() {
		return jtmytgshf;
	}
	public void setJtmytgshf(String jtmytgshf) {
		this.jtmytgshf = jtmytgshf;
	}
	public String getJtrjnsr() {
		return jtrjnsr;
	}
	public void setJtrjnsr(String jtrjnsr) {
		this.jtrjnsr = jtrjnsr;
	}
	public String getJtrks() {
		return jtrks;
	}
	public void setJtrks(String jtrks) {
		this.jtrks = jtrks;
	}
	public String getJttfywsj() {
		return jttfywsj;
	}
	public void setJttfywsj(String jttfywsj) {
		this.jttfywsj = jttfywsj;
	}
	public String getJtxxtxdz() {
		return jtxxtxdz;
	}
	public void setJtxxtxdz(String jtxxtxdz) {
		this.jtxxtxdz = jtxxtxdz;
	}
	public String getJtyzbm() {
		return jtyzbm;
	}
	public void setJtyzbm(String jtyzbm) {
		this.jtyzbm = jtyzbm;
	}
	public String getJtzszrzh() {
		return jtzszrzh;
	}
	public void setJtzszrzh(String jtzszrzh) {
		this.jtzszrzh = jtzszrzh;
	}
	public String getMzbm_lxdh() {
		return mzbm_lxdh;
	}
	public void setMzbm_lxdh(String mzbm_lxdh) {
		this.mzbm_lxdh = mzbm_lxdh;
	}
	public String getMzbm_xxtxdz() {
		return mzbm_xxtxdz;
	}
	public void setMzbm_xxtxdz(String mzbm_xxtxdz) {
		this.mzbm_xxtxdz = mzbm_xxtxdz;
	}
	public String getMzbm_yzbm() {
		return mzbm_yzbm;
	}
	public void setMzbm_yzbm(String mzbm_yzbm) {
		this.mzbm_yzbm = mzbm_yzbm;
	}
	public String getMzmc() {
		return mzmc;
	}
	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getQtqk() {
		return qtqk;
	}
	public void setQtqk(String qtqk) {
		this.qtqk = qtqk;
	}
	public String getRxqhk() {
		return rxqhk;
	}
	public void setRxqhk(String rxqhk) {
		this.rxqhk = rxqhk;
	}
	public String getSfdq() {
		return sfdq;
	}
	public void setSfdq(String sfdq) {
		this.sfdq = sfdq;
	}
	public String getSfgc() {
		return sfgc;
	}
	public void setSfgc(String sfgc) {
		this.sfgc = sfgc;
	}
	public String getSflszn() {
		return sflszn;
	}
	public void setSflszn(String sflszn) {
		this.sflszn = sflszn;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
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
	public String getXsbxnyhzzqk() {
		return xsbxnyhzzqk;
	}
	public void setXsbxnyhzzqk(String xsbxnyhzzqk) {
		this.xsbxnyhzzqk = xsbxnyhzzqk;
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
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
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
	public String getZzmmmc() {
		return zzmmmc;
	}
	public void setZzmmmc(String zzmmmc) {
		this.zzmmmc = zzmmmc;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getGhlmkh() {
		return ghlmkh;
	}
	public void setGhlmkh(String ghlmkh) {
		this.ghlmkh = ghlmkh;
	}
	public String getIs_dq() {
		return is_dq;
	}
	public void setIs_dq(String is_dq) {
		this.is_dq = is_dq;
	}
	public String getIs_dznsx() {
		return is_dznsx;
	}
	public void setIs_dznsx(String is_dznsx) {
		this.is_dznsx = is_dznsx;
	}
	public String getIs_fmxg() {
		return is_fmxg;
	}
	public void setIs_fmxg(String is_fmxg) {
		this.is_fmxg = is_fmxg;
	}
	public String getIs_lszn() {
		return is_lszn;
	}
	public void setIs_lszn(String is_lszn) {
		this.is_lszn = is_lszn;
	}
	public String getIs_pysq() {
		return is_pysq;
	}
	public void setIs_pysq(String is_pysq) {
		this.is_pysq = is_pysq;
	}
	public String getIs_qtys() {
		return is_qtys;
	}
	public void setIs_qtys(String is_qtys) {
		this.is_qtys = is_qtys;
	}
	public String getIs_shbzx() {
		return is_shbzx;
	}
	public void setIs_shbzx(String is_shbzx) {
		this.is_shbzx = is_shbzx;
	}
	public String getIs_shjp() {
		return is_shjp;
	}
	public void setIs_shjp(String is_shjp) {
		this.is_shjp = is_shjp;
	}
	public String getIs_stcj() {
		return is_stcj;
	}
	public void setIs_stcj(String is_stcj) {
		this.is_stcj = is_stcj;
	}
	public String getIs_zrzh() {
		return is_zrzh;
	}
	public void setIs_zrzh(String is_zrzh) {
		this.is_zrzh = is_zrzh;
	}
	public String getMzpy_csly() {
		return mzpy_csly;
	}
	public void setMzpy_csly(String mzpy_csly) {
		this.mzpy_csly = mzpy_csly;
	}
	public String getMzpy_tjdc() {
		return mzpy_tjdc;
	}
	public void setMzpy_tjdc(String mzpy_tjdc) {
		this.mzpy_tjdc = mzpy_tjdc;
	}
	public String getRdjd_xxyj() {
		return rdjd_xxyj;
	}
	public void setRdjd_xxyj(String rdjd_xxyj) {
		this.rdjd_xxyj = rdjd_xxyj;
	}
	public String getRdjd_xxyj_pc() {
		return rdjd_xxyj_pc;
	}
	public void setRdjd_xxyj_pc(String rdjd_xxyj_pc) {
		this.rdjd_xxyj_pc = rdjd_xxyj_pc;
	}
	public String getRdjd_xyyj() {
		return rdjd_xyyj;
	}
	public void setRdjd_xyyj(String rdjd_xyyj) {
		this.rdjd_xyyj = rdjd_xyyj;
	}
	public String getRdjd_xyyj_pc() {
		return rdjd_xyyj_pc;
	}
	public void setRdjd_xyyj_pc(String rdjd_xyyj_pc) {
		this.rdjd_xyyj_pc = rdjd_xyyj_pc;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getZxlxdh() {
		return zxlxdh;
	}
	public void setZxlxdh(String zxlxdh) {
		this.zxlxdh = zxlxdh;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDoType() {
		return doType;
	}
	public void setDoType(String doType) {
		this.doType = doType;
	}
	
	public String setResult(boolean result){
		return (result == true) ? "ok" : "no";
	}

	public List<HashMap<String, String>> getColumnCN() {
		return columnCN;
	}

	public void setColumnCN(List<HashMap<String, String>> columnCN) {
		this.columnCN = columnCN;
	}

	public String getPkVal() {
		return pkVal;
	}

	public void setPkVal(String pkVal) {
		this.pkVal = pkVal;
	}

	public String getJkr_jtxxzz() {
		return jkr_jtxxzz;
	}

	public void setJkr_jtxxzz(String jkr_jtxxzz) {
		this.jkr_jtxxzz = jkr_jtxxzz;
	}	
	
/**##############################################################################################
 * #############################################################################################
 *											METHOD FOR DEAL GBK ENCODE
 */
	/**
	 * 公共的字段
	 */
	private void doCommon_GBK(){
		xymc = DealString.toGBK(xymc);
		xm = DealString.toGBK(xm);
		bjmc = DealString.toGBK(bjmc);
		xb = DealString.toGBK(xb);
		zzmmmc = DealString.toGBK(zzmmmc);
		zymc = DealString.toGBK(zymc);
		mzmc = DealString.toGBK(mzmc);	
	}
	
	/**
	 * 困难生独有字段
	 *
	 */
	public void do_JtjjKns_GBK(){
		this.doCommon_GBK();
		ghlmkh = DealString.toGBK(ghlmkh);        
		zxlxdh = DealString.toGBK(zxlxdh);
		is_shbzx =DealString.toGBK(is_shbzx);
		is_shjp =DealString.toGBK(is_shjp);
		is_lszn =DealString.toGBK(is_lszn);
		is_stcj =DealString.toGBK(is_stcj);
		is_zrzh =DealString.toGBK(is_zrzh);
		is_pysq =DealString.toGBK(is_pysq);
		is_dq   =DealString.toGBK(is_dq); 
		is_dznsx =DealString.toGBK(is_dznsx);
		is_fmxg =DealString.toGBK(is_fmxg);
		is_qtys = DealString.toGBK(is_qtys);
		mzpy_tjdc =DealString.toGBK(mzpy_tjdc);
		mzpy_csly =DealString.toGBK(mzpy_csly);
		rdjd_xyyj =DealString.toGBK(rdjd_xyyj);
		rdjd_xyyj_pc =DealString.toGBK(rdjd_xyyj_pc);
		rdjd_xxyj =DealString.toGBK(rdjd_xxyj);
		rdjd_xxyj_pc =DealString.toGBK(rdjd_xxyj_pc);
		//pkVal
		pkVal = DealString.toGBK(pkVal);
	}
	
	/**
	 * 国家助学贷款独有
	 */
	public void do_Gjzxdk_GBK(){
		this.doCommon_GBK();
		 byxxmc = DealString.toGBK(byxxmc); 
		 jkrxm  = DealString.toGBK(jkrxm); 
		 xl  = DealString.toGBK(xl); 
		 jtzz  = DealString.toGBK(jtzz); 
		 jtdh  = DealString.toGBK(jtdh); 
		 sfdkyjs  = DealString.toGBK(sfdkyjs); 
		 byqx  = DealString.toGBK(byqx); 
		 gzdw =  DealString.toGBK(gzdw); 
		 jkr_jtxxzz = DealString.toGBK(jkr_jtxxzz); ; //借款人家庭详细住址
		 dwdh =  DealString.toGBK(dwdh); 
		 dwdz =  DealString.toGBK(dwdz); 
		 dwyb =  DealString.toGBK(dwyb); 
		 yddh  = DealString.toGBK(yddh); 
		 email  = DealString.toGBK(email); 
		 hyzk  = DealString.toGBK(hyzk); 
		 po_xm  = DealString.toGBK(po_xm); 
		 po_lxdh =  DealString.toGBK(po_lxdh); 
		 po_gzdw  = DealString.toGBK(po_gzdw); 
		 po_dwdz =  DealString.toGBK(po_dwdz); 		
		 lxr_xm  = DealString.toGBK(lxr_xm); 
		 lxr_xb  = DealString.toGBK(lxr_xb); 
		 lxr_yjkrgx  = DealString.toGBK(lxr_yjkrgx); 
		 lxr_csrq =  DealString.toGBK(lxr_csrq); 
		 lxr_zw =  DealString.toGBK(lxr_zw); 
		 lxr_jtlxdh =  DealString.toGBK(lxr_jtlxdh); 	
		 lxr_sfzh =  DealString.toGBK(lxr_sfzh); 
		 lxr_dwlxdh =  DealString.toGBK(lxr_dwlxdh); 
		 lxr_gzdw_dz =  DealString.toGBK(lxr_gzdw_dz); 
		 lxr_dwyb  = DealString.toGBK(lxr_dwyb); 
		 jkr_yb  = DealString.toGBK(jkr_yb); 
		 jkr_dh  = DealString.toGBK(jkr_dh); 
		 jkr_fq_xm = DealString.toGBK(jkr_fq_xm); 
		 jkr_fq_zy =  DealString.toGBK(jkr_fq_zy); 	
		 jkr_fq_sfzh =  DealString.toGBK(jkr_fq_sfzh); 
		 jkr_mq_xm  = DealString.toGBK(jkr_mq_xm); 
		 jkr_mq_zy =  DealString.toGBK(jkr_mq_zy); 
		 jkr_mq_sfzh  = DealString.toGBK(jkr_mq_sfzh); 
		 yhmc  = DealString.toGBK(yhmc); 
		 bz  = DealString.toGBK(bz); 
	}

	/**
	 * 国家助学贷款审批独有
	 */
	public void do_Gjdk_Sp_GBK(){
		this.doCommon_GBK();
		//this.do_JtjjKns_GBK();
		//this.do_Gjzxdk_GBK();
		rxnf = DealString.toGBK(rxnf); 
		bynf = DealString.toGBK(bynf); 
		ghlmkh	 = DealString.toGBK(ghlmkh); 
		jtysr = DealString.toGBK(jtysr); 
		xxmc = DealString.toGBK(xxmc); 
		ssdh = DealString.toGBK(ssdh); 
		xl = DealString.toGBK(xl); 
		jkr_jtxxzz = DealString.toGBK(jkr_jtxxzz); 
		jkr_yb = DealString.toGBK(jkr_yb); 
		jkr_fq_xm = DealString.toGBK(jkr_fq_xm); 
        jkr_fq_zy = DealString.toGBK(jkr_fq_zy); 
        jkr_fq_sfzh = DealString.toGBK(jkr_fq_sfzh); 
        jkr_mq_xm = DealString.toGBK(jkr_mq_xm); 
        jkr_mq_zy = DealString.toGBK(jkr_mq_zy); 
        jkr_mq_sfzh = DealString.toGBK(jkr_mq_sfzh); 
        jtdh = DealString.toGBK(jtdh); 
		
		sqdkzje = DealString.toGBK(sqdkzje); 
		xzfdkzje = DealString.toGBK(xzfdkzje); 
		zsfdkzje = DealString.toGBK(zsfdkzje); 
		nd_one = DealString.toGBK(nd_one); 
		nd_one_fkje = DealString.toGBK(nd_one_fkje); 
		nd_two = DealString.toGBK(nd_two); 
		nd_two_fkje = DealString.toGBK(nd_two_fkje); 
		nd_three = DealString.toGBK(nd_three); 
		nd_three_fkje = DealString.toGBK(nd_three_fkje); 
		nd_four = DealString.toGBK(nd_four); 
		nd_four_fkje = DealString.toGBK(nd_four_fkje); 
		nd_five = DealString.toGBK(nd_five); 
		nd_five_fkje = DealString.toGBK(nd_five_fkje); 
		dkqx_months = DealString.toGBK(dkqx_months); 
		dkqx_kssj = DealString.toGBK(dkqx_kssj); 
		dkqx_jssj = DealString.toGBK(dkqx_jssj); 
		xdyyj = DealString.toGBK(xdyyj); 
		kzyj = DealString.toGBK(kzyj); 
		yqspryj = DealString.toGBK(yqspryj); 
		zxnx =  DealString.toGBK(zxnx); 
		/**
		 * 查询视图时用到
		 */
		xxmc = DealString.toGBK(xxmc); 
	}

	/**国家助学贷款还款协议*/
	public void do_Gjdk_Fkxy_GBK(){
		this.doCommon_GBK();
	      jkr  =  DealString.toGBK(jkr); 
	      yxzjhm  =  DealString.toGBK(yxzjhm); 
	      zs    =  DealString.toGBK(zs); 
	      gzdw  =  DealString.toGBK(gzdw); 
	      yzbm  =  DealString.toGBK(yzbm); 
	      lxdh  =  DealString.toGBK(lxdh); 
	      htbh  =  DealString.toGBK(htbh); 
	      jzrq  =  DealString.toGBK(jzrq); 
	      fkje  =  DealString.toGBK(fkje); 
	     // jf_lkxxsj  =  DealString.toGBK(zxnx); 
	      jf_lkxx_yy  =  DealString.toGBK(jf_lkxx_yy); 
	      jflkxx_mc  =  DealString.toGBK(jflkxx_mc); 
	      fk_fs  =  DealString.toGBK(fk_fs); 
	      fk_jtfs  =  DealString.toGBK(fk_jtfs); 
	      fjq   =  DealString.toGBK(fjq); 
	     // fklx_kssj   =  DealString.toGBK(zxnx); 
	     // fklx_jssj  =  DealString.toGBK(zxnx); 
	      fklxys  =  DealString.toGBK(fklxys); 
	     // fkbjlx_kssj  =  DealString.toGBK(zxnx); 
	      //fkbjlx_jssj  =  DealString.toGBK(zxnx); 
	      fkbjlxys   =  DealString.toGBK(fkbjlxys); 
	      zffm   =  DealString.toGBK(zffm); 
	      zfh   =  DealString.toGBK(zfh); 
	}
	
	/**资助项目维护*/
	public void do_Zzxm_GBK(){
		//this.nd = DealString.toGBK(nd);
		//this.xmdm = DealString.toGBK(xmdm);
		this.doCommon_GBK();
		this.xmmc = DealString.toGBK(xmmc);
		this.sqly = DealString.toGBK(sqly);
		this.xysh = DealString.toGBK(xysh);
		this.xyshyj = DealString.toGBK(xyshyj);
		this.xyshsj = DealString.toGBK(xyshsj);
		this.xypzje = DealString.toGBK(xypzje);
		this.xxsh = DealString.toGBK(xxsh);
		this.xxshyj = DealString.toGBK(xxshyj);
		this.xxshsj = DealString.toGBK(xxshsj);
		this.xxpzje = DealString.toGBK(xxpzje);
		this.lxdh = DealString.toGBK(lxdh);
		this.rxqhk = DealString.toGBK(rxqhk);
		this.jtzz = DealString.toGBK(jtzz);
		this.yzbm = DealString.toGBK(yzbm);
		this.jtlxdh = DealString.toGBK(jtlxdh);
		this.sfyycjcshzyhd = DealString.toGBK(sfyycjcshzyhd);
		this.sfyysqgjzxdkhqgzx = DealString.toGBK(sfyysqgjzxdkhqgzx);
		this.sfjq = DealString.toGBK(sfjq);
		this.sfge = DealString.toGBK(sfge);
		this.sfdq = DealString.toGBK(sfdq);
		this.sfcj = DealString.toGBK(sfcj);
		this.sfjls = DealString.toGBK(sfjls);
		this.sfly = DealString.toGBK(sfly);
		this.sfzb = DealString.toGBK(sfzb);
		this.jtcy1_xm = DealString.toGBK(jtcy1_xm);
		this.jtcy1_nl = DealString.toGBK(jtcy1_nl);
		this.jtcy1_gx = DealString.toGBK(jtcy1_gx);
		this.jtcy1_gzdw = DealString.toGBK(jtcy1_gzdw);
		this.jtcy1_zy = DealString.toGBK(jtcy1_zy);
		this.jtcy1_nsr = DealString.toGBK(jtcy1_nsr);
		this.jtcy1_jkzk = DealString.toGBK(jtcy1_jkzk);
		this.jtcy2_xm = DealString.toGBK(jtcy2_xm);
		this.jtcy2_nl = DealString.toGBK(jtcy2_nl);
		this.jtcy2_gx = DealString.toGBK(jtcy2_gx);
		this.jtcy2_gzdw = DealString.toGBK(jtcy2_gzdw);
		this.jtcy2_zy = DealString.toGBK(jtcy2_zy);
		this.jtcy2_nsr = DealString.toGBK(jtcy2_nsr);
		this.jtcy2_jkzk = DealString.toGBK(jtcy2_jkzk);
		this.jtcy3_xm = DealString.toGBK(jtcy3_xm);
		this.jtcy3_nl = DealString.toGBK(jtcy3_nl);
		this.jtcy3_gx = DealString.toGBK(jtcy3_gx);
		this.jtcy3_gzdw = DealString.toGBK(jtcy3_gzdw);
		this.jtcy3_zy = DealString.toGBK(jtcy3_zy);
		this.jtcy3_nsr = DealString.toGBK(jtcy3_nsr);
		this.jtcy3_jkzk = DealString.toGBK(jtcy3_jkzk);
		this.jtcy4_xm = DealString.toGBK(jtcy4_xm);
		this.jtcy4_nl = DealString.toGBK(jtcy4_nl);
		this.jtcy4_gx = DealString.toGBK(jtcy4_gx);
		this.jtcy4_gzdw = DealString.toGBK(jtcy4_gzdw);
		this.jtcy4_zy = DealString.toGBK(jtcy4_zy);
		this.jtcy4_nsr = DealString.toGBK(jtcy4_nsr);
		this.jtcy4_jkzk = DealString.toGBK(jtcy4_jkzk);
		this.jtcy5_xm = DealString.toGBK(jtcy5_xm);
		this.jtcy5_nl = DealString.toGBK(jtcy5_nl);
		this.jtcy5_gx = DealString.toGBK(jtcy5_gx);
		this.jtcy5_gzdw = DealString.toGBK(jtcy5_gzdw);
		this.jtcy5_zy = DealString.toGBK(jtcy5_zy);
		this.jtcy5_nsr = DealString.toGBK(jtcy5_nsr);
		this.jtcy5_jkzk = DealString.toGBK(jtcy5_jkzk);
		this.jtrjnsr = DealString.toGBK(jtrjnsr);
		this.xszbdszqk = DealString.toGBK(xszbdszqk);
		this.jtzszrzhqk = DealString.toGBK(jtzszrzhqk);
		this.jtzstfywsj = DealString.toGBK(jtzstfywsj);
		this.qtqk = DealString.toGBK(qtqk);
		this.mzbm_txdz = DealString.toGBK(mzbm_txdz);
		this.mzbm_yzbm = DealString.toGBK(mzbm_yzbm);
		this.mzbm_lxdh = DealString.toGBK(mzbm_lxdh);
		this.sfkns = DealString.toGBK(sfkns);
		this.syd = DealString.toGBK(syd);
		this.lydq = DealString.toGBK(lydq);
	}
	public String getRealTable() {
		return realTable;
	}

	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getBynf() {
		return bynf;
	}

	public void setBynf(String bynf) {
		this.bynf = bynf;
	}

	public String getJtysr() {
		return jtysr;
	}

	public void setJtysr(String jtysr) {
		this.jtysr = jtysr;
	}

	public String getRxnf() {
		return rxnf;
	}

	public void setRxnf(String rxnf) {
		this.rxnf = rxnf;
	}

	public String getSsdh() {
		return ssdh;
	}

	public void setSsdh(String ssdh) {
		this.ssdh = ssdh;
	}

	public String getXxmc() {
		return xxmc;
	}

	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}

	public String getZxnx() {
		return zxnx;
	}

	public void setZxnx(String zxnx) {
		this.zxnx = zxnx;
	}

	public String getFjq() {
		return fjq;
	}

	public void setFjq(String fjq) {
		this.fjq = fjq;
	}

	public String getFk_fs() {
		return fk_fs;
	}

	public void setFk_fs(String fk_fs) {
		this.fk_fs = fk_fs;
	}

	public String getFk_jtfs() {
		return fk_jtfs;
	}

	public void setFk_jtfs(String fk_jtfs) {
		this.fk_jtfs = fk_jtfs;
	}

	public String getFkbjlx_jssj() {
		return fkbjlx_jssj;
	}

	public void setFkbjlx_jssj(String fkbjlx_jssj) {
		this.fkbjlx_jssj = fkbjlx_jssj;
	}

	public String getFkbjlx_kssj() {
		return fkbjlx_kssj;
	}

	public void setFkbjlx_kssj(String fkbjlx_kssj) {
		this.fkbjlx_kssj = fkbjlx_kssj;
	}

	public String getFkbjlxys() {
		return fkbjlxys;
	}

	public void setFkbjlxys(String fkbjlxys) {
		this.fkbjlxys = fkbjlxys;
	}

	public String getFkje() {
		return fkje;
	}

	public void setFkje(String fkje) {
		this.fkje = fkje;
	}

	public String getFklx_jssj() {
		return fklx_jssj;
	}

	public void setFklx_jssj(String fklx_jssj) {
		this.fklx_jssj = fklx_jssj;
	}

	public String getFklx_kssj() {
		return fklx_kssj;
	}

	public void setFklx_kssj(String fklx_kssj) {
		this.fklx_kssj = fklx_kssj;
	}

	public String getFklxys() {
		return fklxys;
	}

	public void setFklxys(String fklxys) {
		this.fklxys = fklxys;
	}

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public String getJf_lkxx_yy() {
		return jf_lkxx_yy;
	}

	public void setJf_lkxx_yy(String jf_lkxx_yy) {
		this.jf_lkxx_yy = jf_lkxx_yy;
	}

	public String getJf_lkxxsj() {
		return jf_lkxxsj;
	}

	public void setJf_lkxxsj(String jf_lkxxsj) {
		this.jf_lkxxsj = jf_lkxxsj;
	}

	public String getJflkxx_mc() {
		return jflkxx_mc;
	}

	public void setJflkxx_mc(String jflkxx_mc) {
		this.jflkxx_mc = jflkxx_mc;
	}

	public String getJkr() {
		return jkr;
	}

	public void setJkr(String jkr) {
		this.jkr = jkr;
	}

	public String getJzrq() {
		return jzrq;
	}

	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getYxzjhm() {
		return yxzjhm;
	}

	public void setYxzjhm(String yxzjhm) {
		this.yxzjhm = yxzjhm;
	}

	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	public String getZffm() {
		return zffm;
	}

	public void setZffm(String zffm) {
		this.zffm = zffm;
	}

	public String getZfh() {
		return zfh;
	}

	public void setZfh(String zfh) {
		this.zfh = zfh;
	}

	public String getZs() {
		return zs;
	}

	public void setZs(String zs) {
		this.zs = zs;
	}

	public String getJtcy1_nsr() {
		return jtcy1_nsr;
	}

	public void setJtcy1_nsr(String jtcy1_nsr) {
		this.jtcy1_nsr = jtcy1_nsr;
	}

	public String getJtcy2_nsr() {
		return jtcy2_nsr;
	}

	public void setJtcy2_nsr(String jtcy2_nsr) {
		this.jtcy2_nsr = jtcy2_nsr;
	}

	public String getJtcy3_nsr() {
		return jtcy3_nsr;
	}

	public void setJtcy3_nsr(String jtcy3_nsr) {
		this.jtcy3_nsr = jtcy3_nsr;
	}

	public String getJtcy4_nsr() {
		return jtcy4_nsr;
	}

	public void setJtcy4_nsr(String jtcy4_nsr) {
		this.jtcy4_nsr = jtcy4_nsr;
	}

	public String getJtcy5_nsr() {
		return jtcy5_nsr;
	}

	public void setJtcy5_nsr(String jtcy5_nsr) {
		this.jtcy5_nsr = jtcy5_nsr;
	}

	public String getJtzstfywsj() {
		return jtzstfywsj;
	}

	public void setJtzstfywsj(String jtzstfywsj) {
		this.jtzstfywsj = jtzstfywsj;
	}

	public String getJtzszrzhqk() {
		return jtzszrzhqk;
	}

	public void setJtzszrzhqk(String jtzszrzhqk) {
		this.jtzszrzhqk = jtzszrzhqk;
	}

	public String getMzbm_txdz() {
		return mzbm_txdz;
	}

	public void setMzbm_txdz(String mzbm_txdz) {
		this.mzbm_txdz = mzbm_txdz;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getSfcj() {
		return sfcj;
	}

	public void setSfcj(String sfcj) {
		this.sfcj = sfcj;
	}

	public String getSfge() {
		return sfge;
	}

	public void setSfge(String sfge) {
		this.sfge = sfge;
	}

	public String getSfjls() {
		return sfjls;
	}

	public void setSfjls(String sfjls) {
		this.sfjls = sfjls;
	}

	public String getSfjq() {
		return sfjq;
	}

	public void setSfjq(String sfjq) {
		this.sfjq = sfjq;
	}

	public String getSfkns() {
		return sfkns;
	}

	public void setSfkns(String sfkns) {
		this.sfkns = sfkns;
	}

	public String getSfly() {
		return sfly;
	}

	public void setSfly(String sfly) {
		this.sfly = sfly;
	}

	public String getSfyycjcshzyhd() {
		return sfyycjcshzyhd;
	}

	public void setSfyycjcshzyhd(String sfyycjcshzyhd) {
		this.sfyycjcshzyhd = sfyycjcshzyhd;
	}

	public String getSfyysqgjzxdkhqgzx() {
		return sfyysqgjzxdkhqgzx;
	}

	public void setSfyysqgjzxdkhqgzx(String sfyysqgjzxdkhqgzx) {
		this.sfyysqgjzxdkhqgzx = sfyysqgjzxdkhqgzx;
	}

	public String getSfzb() {
		return sfzb;
	}

	public void setSfzb(String sfzb) {
		this.sfzb = sfzb;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSyd() {
		return syd;
	}

	public void setSyd(String syd) {
		this.syd = syd;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXszbdszqk() {
		return xszbdszqk;
	}

	public void setXszbdszqk(String xszbdszqk) {
		this.xszbdszqk = xszbdszqk;
	}

	public String getXxpzje() {
		return xxpzje;
	}

	public void setXxpzje(String xxpzje) {
		this.xxpzje = xxpzje;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getXxshsj() {
		return xxshsj;
	}

	public void setXxshsj(String xxshsj) {
		this.xxshsj = xxshsj;
	}

	public String getXxshyj() {
		return xxshyj;
	}

	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}

	public String getXypzje() {
		return xypzje;
	}

	public void setXypzje(String xypzje) {
		this.xypzje = xypzje;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getXyshsj() {
		return xyshsj;
	}

	public void setXyshsj(String xyshsj) {
		this.xyshsj = xyshsj;
	}

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}

	public String getSsbh() {
		return ssbh;
	}

	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}
	public String getKh() {
		return kh;
	}
	public void setKh(String kh) {
		this.kh = kh;
	}
	public String getLydq() {
		return lydq;
	}
	public void setLydq(String lydq) {
		this.lydq = lydq;
	}
}
