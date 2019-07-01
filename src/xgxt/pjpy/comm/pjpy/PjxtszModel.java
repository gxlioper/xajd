package xgxt.pjpy.comm.pjpy;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.String.StringUtils;

/**
 * <p>评奖系统设置对象</p>
 * @author 鲁大
 */
public class PjxtszModel {

	private String pjxn;
	
	private String pjxq;
	
	private String pjnd;
	
	private String pjxqmc;
	
	private String rsszfs;
	
	private String zczq;
	
	public static PjxtszModel pjxtszModel;
	
	static {
		/*pjxtszModel = new PjxtszModel();
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = dao.getMapNotOut("select a.*,(select b.xqmc from xqdzb b where b.xqdm=a.pjxq) pjxqmc from xg_pjpy_xtszb a where rownum=1", new String[]{});
		
		if (null != map && StringUtils.isNotNull(map.get("pjxn"))){
			pjxtszModel.setPjxn(map.get("pjxn"));
			pjxtszModel.setPjnd(map.get("pjnd"));
			pjxtszModel.setPjxq(map.get("pjxq"));
			pjxtszModel.setRsszfs(map.get("rsszfs"));
			pjxtszModel.setPjxqmc(map.get("pjxqmc"));
		}else{
			map = dao.getMapNotOut("select a.dqxn,a.dqxq,a.dqnd,(select b.xqmc from xqdzb b where b.xqdm=a.dqxq) xqmc from xtszb a where rownum=1", new String[]{});
			pjxtszModel.setPjxn(map.get("dqxn"));
			pjxtszModel.setPjnd(map.get("dqnd"));
			pjxtszModel.setPjxq(map.get("dqxq"));
			pjxtszModel.setRsszfs("xx");
			pjxtszModel.setPjxqmc(map.get("xqmc"));
		}*/
		PjxtszModel.setPjxtszModel();
	}
	

	public String getPjnd() {
		return pjnd;
	}


	public void setPjnd(String pjnd) {
		this.pjnd = pjnd;
	}


	public String getPjxn() {
		return pjxn;
	}


	public void setPjxn(String pjxn) {
		this.pjxn = pjxn;
	}


	public String getPjxq() {
		return pjxq;
	}


	public void setPjxq(String pjxq) {
		this.pjxq = pjxq;
	}


	public String getRsszfs() {
		return rsszfs;
	}


	public void setRsszfs(String rsszfs) {
		this.rsszfs = rsszfs;
	}


	public String getPjxqmc() {
		return pjxqmc;
	}


	private void setPjxqmc(String pjxqmc) {
		this.pjxqmc = pjxqmc;
	}

	public static void setPjxtszModel() {
		
		pjxtszModel = new PjxtszModel();
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = dao.getMapNotOut("select a.*,(select b.xqmc from xqdzb b where b.xqdm=a.pjxq) pjxqmc from xg_pjpy_xtszb a where rownum=1", new String[]{});
		
		if (null != map && StringUtils.isNotNull(map.get("pjxn"))){
			pjxtszModel.setPjxn(map.get("pjxn"));
			pjxtszModel.setPjnd(map.get("pjnd"));
			pjxtszModel.setPjxq(map.get("pjxq"));
			pjxtszModel.setRsszfs(map.get("rsszfs"));
			pjxtszModel.setPjxqmc(map.get("pjxqmc"));
			pjxtszModel.setZczq(map.get("pjzq"));
		}else{
			map = dao.getMapNotOut("select a.dqxn,a.dqxq,a.dqnd,(select b.xqmc from xqdzb b where b.xqdm=a.dqxq) xqmc from xtszb a where rownum=1", new String[]{});
			pjxtszModel.setPjxn(map.get("dqxn"));
			pjxtszModel.setPjnd(map.get("dqnd"));
			pjxtszModel.setPjxq(map.get("dqxq"));
			pjxtszModel.setRsszfs("xx");
			pjxtszModel.setPjxqmc(map.get("xqmc"));
		}
		
		ZhcpJbszForm.setZcjbszModel();
		//PjxtszModel.pjxtszModel = pjxtszModel;
	}


	public String getZczq() {
		return zczq;
	}


	public void setZczq(String zczq) {
		this.zczq = zczq;
	}
}
