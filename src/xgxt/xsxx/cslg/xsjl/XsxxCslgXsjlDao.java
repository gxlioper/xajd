package xgxt.xsxx.cslg.xsjl;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;

public class XsxxCslgXsjlDao {

	/**
	 * 获取学期名称
	 */
	public HashMap<String,String>getXqmc(String xq){
		DAO dao=DAO.getInstance();
		String sql="select xqmc from xqdzb where xqdm=? ";
		HashMap<String,String>hashMap=dao.getMap(sql, new String[]{xq},new String[]{"xqmc"});
		return hashMap;
	}
	
	/**
	 * 查询单个学习经历
	 * 
	 */
	public List<String[]>getXxjlXx(String pkValue){
		String sql="(select * from xsxx_cslg_xxjlb where xh = ?)";
		return CommonQueryDAO.commonQueryNotFy(sql, "", new String[]{pkValue}, new String[]{"xh","qssj","zzsj","jzr","bz","dwmc"}, null);
		
	}
	
	/**
	 * 删除学生所有学业经历
	 * @throws Exception 
	 */
	public void deleteXxjl(XsxxCslgXsjlForm form) throws Exception{
		DAO dao=DAO.getInstance();
		String[] primarykey=form.getPrimarykey_cbv();
		String []strArr=new String[primarykey.length];
		for(int i=0;i<primarykey.length;i++){
			strArr[i]="delete from xsxx_cslg_xxjlb where xh='"+primarykey[i]+"'";
		}
		dao.runBatch(strArr);
	}
	
	/**
	 * 单个删除学生所有学业经历
	 * @throws Exception 
	 */
	public void delXxjl(XsxxCslgXsjlForm form) throws Exception{
		DAO dao=DAO.getInstance();
		String sql="delete from xsxx_cslg_xxjlb where xh=?";
		dao.runUpdate(sql, new String[]{form.getXh()});
	}
	
	/**
	 * 查询统计信息
	 */
	public List<HashMap<String,String>> getTjList(String[] input ,String sb){
		DAO dao=DAO.getInstance();
		String sql=" select xydm,zydm,bjdm,xh,xm,nj,bjmc,zymc,xymc,qssj,zzsj,dwmc,jzr from (select a.bjdm,a.zydm,a.xydm,a.xh,a.xm,a.nj,a.bjmc,a.zymc,a.xymc,b.qssj,b.zzsj,b.dwmc,b.jzr from view_xsxx_cslg_xxjlb a ,xsxx_cslg_xxjlb b where a.xh=b.xh  )a  ";
		return dao.getList(sql+sb,input, new String[]{"xh","xm","nj","bjmc","zymc","xymc","qssj","zzsj","dwmc","jzr"});
	}
	
}
