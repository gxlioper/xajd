package xgxt.pjpy.cqdzgc;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class PjpyCqdzgcDao {
	
	/**
	 * 获取学生的 各门课的学分绩点
	 * @param form
	 * return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getXyxx(PjpyCqdzgcForm form){
		DAO dao=DAO.getInstance();
		String pkValue=form.getPkValue();
		String sql=" select xh,kcmc,xf,jd from view_cjb where xh||xn||xq=? ";
		return dao.getList(sql, new String[]{pkValue}, new String[]{"xh","kcmc","xf","jd"});
	}
	
	/**
	 * 获取学生品德基本分
	 * List<HashMap<String,String>>
	 */
	public HashMap<String,String> getXsPdjbf(PjpyCqdzgcForm form){
		DAO dao=DAO.getInstance();
		String pkValue=form.getPkValue();
		StringBuffer sb=new StringBuffer();
		sb.append("select sum(a.bcnr) fs from  py_bdsz_bcnr a,view_cqdzgc_pdcxjbf b where tabname='view_cqdzgc_pdcxjbf' and");
		sb.append(" b.xh||b.xn||b.xq||b.nd||b.zcdm =a.zbid and b.xh||b.xn||b.xq=? and a.zdid='fs'");
		return dao.getMap(sb.toString(), new String[]{pkValue}, new String[]{"fs"});
	}
	
	/**
	 * 获取学生品德附加分
	 * List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getXsPdfjf(PjpyCqdzgcForm form){
		DAO dao=DAO.getInstance();
		String pkValue=form.getPkValue();
		StringBuffer sb=new StringBuffer();
		sb.append("select b.zcdm dm,a.bcnr fs  from  py_bdsz_bcnr a,view_cqdzgc_pdcxfjf b where tabname='view_cqdzgc_pdcxfjf' and");
		sb.append(" b.xh||b.xn||b.xq||b.nd||b.zcdm =a.zbid and b.xh||b.xn||b.xq=? and a.zdid='fs'");
		return dao.getList(sb.toString(), new String[]{pkValue}, new String[]{"dm","fs"});
	}
	
	/**
	 * 获取学生品德扣分
	 * List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getXsPdkf(PjpyCqdzgcForm form){
		DAO dao=DAO.getInstance();
		String pkValue=form.getPkValue();
		StringBuffer sb=new StringBuffer();
		sb.append("select  b.zcdm dm,a.bcnr fs  from  py_bdsz_bcnr a,view_cqdzgc_pdcxkf b where tabname='view_cqdzgc_pdcxkf' and");
		sb.append(" b.xh||b.xn||b.xq||b.nd||b.zcdm =a.zbid and b.xh||b.xn||b.xq=? and a.zdid='fs'");
		return dao.getList(sb.toString(), new String[]{pkValue}, new String[]{"dm","fs"});
	}
	
	/**
	 * 获取学生科技文体基本分
	 * List<HashMap<String,String>>
	 */
	public HashMap<String,String> getXsKjwtjbf(PjpyCqdzgcForm form){
		DAO dao=DAO.getInstance();
		String pkValue=form.getPkValue();
		StringBuffer sb=new StringBuffer();
		sb.append("select   sum(a.bcnr) fs  from  py_bdsz_bcnr a,view_cqdzgc_kjwtszjbf b where tabname='view_cqdzgc_kjwtszjbf' and");
		sb.append(" b.xh||b.xn||b.xq||b.nd||b.zcdm =a.zbid and b.xh||b.xn||b.xq=? and a.zdid='fs'");
		return dao.getMap(sb.toString(), new String[]{pkValue}, new String[]{"fs"});
	}
	
	/**
	 * 获取学生科技文体附加分
	 * List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getXsKjwtfjf(PjpyCqdzgcForm form){
		DAO dao=DAO.getInstance();
		String pkValue=form.getPkValue();
		StringBuffer sb=new StringBuffer();
		sb.append("select  b.zcdm dm,a.bcnr fs  from  py_bdsz_bcnr a,view_cqdzgc_kjwtszfjf b where tabname='view_cqdzgc_kjwtszfjf' and");
		sb.append(" b.xh||b.xn||b.xq||b.nd||b.zcdm =a.zbid and b.xh||b.xn||b.xq=? and a.zdid='fs'");
		return dao.getList(sb.toString(), new String[]{pkValue}, new String[]{"dm","fs"});
	}
	
	/**
	 * 获取学生科技文体基本分
	 * List<HashMap<String,String>>
	 */
	public HashMap<String,String> getXsShsjjbf(PjpyCqdzgcForm form){
		DAO dao=DAO.getInstance();
		String pkValue=form.getPkValue();
		StringBuffer sb=new StringBuffer();
		sb.append("select   sum(a.bcnr) fs  from  py_bdsz_bcnr a,view_cqdzgc_shsjjbf b where tabname='view_cqdzgc_shsjjbf' and");
		sb.append(" b.xh||b.xn||b.xq||b.nd||b.zcdm =a.zbid and b.xh||b.xn||b.xq=? and a.zdid='fs'");
		return dao.getMap(sb.toString(), new String[]{pkValue}, new String[]{"fs"});
	}
	
	/**
	 * 获取学生科技文体附加分
	 * List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getXsShsjfjf(PjpyCqdzgcForm form){
		DAO dao=DAO.getInstance();
		String pkValue=form.getPkValue();
		StringBuffer sb=new StringBuffer();
		sb.append("select  b.zcdm dm,a.bcnr fs  from  py_bdsz_bcnr a,view_cqdzgc_shsjfjf b where tabname='view_cqdzgc_shsjfjf' and");
		sb.append(" b.xh||b.xn||b.xq||b.nd||b.zcdm =a.zbid and b.xh||b.xn||b.xq=? and a.zdid='fs'");
		return dao.getList(sb.toString(), new String[]{pkValue}, new String[]{"dm","fs"});
	}
	
	/**
	 * 获取学生学业信息
	 * 
	 */
	public HashMap<String,String>getXsxyxx(PjpyCqdzgcForm form){
		DAO dao=DAO.getInstance();
		String pkValue=form.getPkValue();
		StringBuffer sb=new StringBuffer();
		sb.append("select fs,pjxfjd from view_cqdzgc_xybxf where xh||xn||xq=?");
		return dao.getMap(sb.toString(), new String[]{pkValue}, new String[]{"fs","pjxfjd"});
	
	}
	
	/**
	 * 获取学生综合测评信息
	 */
	public HashMap<String,String>getXszcxx(PjpyCqdzgcForm form){
		DAO dao=DAO.getInstance();
		String pkValue=form.getPkValue();
		String sql=" select xh,xm,xymc,zymc,bjmc,pm from view_pjpy_zhszcpb where xh||xn||xq=? and jb='1' ";
		return dao.getMap(sql, new String[]{pkValue}, new String[]{"xh","pm","xymc","zymc","bjmc","xm"});
		
	}
}
