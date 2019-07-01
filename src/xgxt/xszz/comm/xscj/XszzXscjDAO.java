package xgxt.xszz.comm.xscj;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.xszz.XszzTyForm;

public class XszzXscjDAO {
	
	/**
	 * 获取评定时间
	 * @param xmdm
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String> getPdsj(String xmdm){
		DAO dao=DAO.getInstance();
		String sql="select pdsj from xszz_zzxmb where xmdm=? ";
		return dao.getMap(sql, new String[]{xmdm}, new String[]{"pdsj"});
	}
	
	/**
	 * 获取学生成绩
	 * @param tyForm
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getXscj(XszzTyForm tyForm){
		DAO dao=DAO.getInstance();
		String sql=" select (select xqmc from xqdzb where xq=xqdm)xqmc,xq,kcmc,cj,kcxz from cjb where xn=? and xh=? order by xq";
		return dao.getList(sql, new String[]{tyForm.getXn(),tyForm.getXh()}, new String[]{"xqmc","kcmc","cj","kcxz"});
	}
	
	/**
	 * 获取项目表名
	 * @param XszzTyForm xszzForm
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String> getXmb(XszzTyForm xszzForm){
		DAO dao=DAO.getInstance();
		String sql=" select xmb from xszz_zzxmb where xmdm=? ";
		String xmdm=xszzForm.getXmdm();
		return dao.getMap(sql, new String[]{xmdm}, new String[]{"xmb"});
	}
	
	/**
	 * 获取外设表学年
	 * @param xszzForm
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String>getWsbXn(XszzTyForm xszzForm){
		DAO dao=DAO.getInstance();
		String sql=" select xn,sqsj from xszz_comm_zzwsb where xmdm||sqsj||xh=? ";
		String xmdm=xszzForm.getXmdm();
		String sqsj=xszzForm.getSqsj();
		String xh=xszzForm.getXh();
		return dao.getMap(sql, new String[]{xmdm+sqsj+xh}, new String[]{"xn","sqsj"});
	}
	
	/**
	 * 获取项目学年
	 * @param xszzForm
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String>getXmbXn(XszzTyForm xszzForm){
		DAO dao=DAO.getInstance();
		String sql=" select xn,sqsj from "+xszzForm.getXmb()+" where sqsj||xh=? ";
		String sqsj=xszzForm.getSqsj();
		String xh=xszzForm.getXh();
		return dao.getMap(sql, new String[]{sqsj+xh}, new String[]{"xn","sqsj"});
	}
}
