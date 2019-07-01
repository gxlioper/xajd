package xgxt.xsxx.pdk;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.comm.XsxxCommForm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class MoralCardDAO extends DAO {
	
	
	/**
	 * 查询学生基本信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStudents(XsxxCommForm model,String query,String[] input,String[] colList) throws Exception{
		//String zxxs = "and (sfyby = '否' or sfyby is null) and (sfzx = '在校' or sfzx is null)";//在校生过滤条件
		Pages pages = model.getPages();
		//最大记录数
		pages.setMaxRecord(Integer.parseInt(getOneRs("select count(1) c from view_xsbfxx a where 1=1 "+query, input, "c")));
		
		//查询结果集
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,xm,nj,xymc,zymc,bjmc,xz from (select a.xh,a.xm,a.nj,a.xymc,a.zymc,a.bjmc,a.xz,rownum r from view_xsbfxx a where 1=1 ");
		sql.append(query);
		//sql.append(zxxs);
		sql.append(" and rownum <=");
		sql.append((pages.getStart() + pages.getPageSize()));
		sql.append(" ) where r > "); 
		sql.append(pages.getStart());
		
		return rsToVator(sql.toString(), input, colList);
	}
	
	
	
	/**
	 * 根据学号查询德育等第
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getDyddListByXh(String xh){
		
		String sql = "select * from xg_xsxx_dyddb where xh=? order by to_number(xssx)";
		
		return getListNotOut(sql, new String[]{xh});
	}
	
	
	
	/**
	 * 根据学号删除德育等第
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean clearDyddByXh(String xh) throws Exception{
		
		String sql = "delete from xg_xsxx_dyddb where xh=?";
		
		return runUpdate(sql, new String[]{xh});
	}
	
	
	/**
	 * 德育等第保存
	 * @param xh
	 * @param xqmc
	 * @param pjjg
	 * @return
	 * @throws SQLException
	 */
	public boolean saveDydd(String xh,String[] xqmc,String[] pjjg,String[] xssx) throws SQLException{
		
		if (xqmc.length > 0){
			String[] sqlArr = new String[xqmc.length];
			for (int i = 0 ; i < xqmc.length; i++){
				
				if (StringUtils.isNotNull(xqmc[i])){
				StringBuilder sql = new StringBuilder();
				
				sql.append("insert into xg_xsxx_dyddb values ('")
				   .append(xh)
				   .append("','")
				   .append(xqmc[i])
				   .append("','")
				   .append(pjjg[i])
				   .append("','")
				   .append(xssx[i])
				   .append("')");
				
				sqlArr[i] = sql.toString();
				}
			}
			
			int[] result = runBatch(sqlArr);
			return checkBatch(result);
		} else {
			return true;
		}
	}
	
	
	
	/**
	 * 奖惩记录列表
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getJcjlListByXh(String xh){
		
		String sql = "select * from xg_xsxx_jcjlb where xh=?";
		
		return getListNotOut(sql, new String[]{xh});
	}
	
	
	/**
	 * 根据学号删除奖惩记录
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean clearJcjlByXh(String xh) throws Exception{
		
		String sql = "delete from xg_xsxx_jcjlb where xh=?";
		
		return runUpdate(sql, new String[]{xh});
	}
	
	
	
	/**
	 * 奖惩记录保存
	 * @param xh
	 * @param rq
	 * @param zy
	 * @param bz
	 * @return
	 * @throws SQLException
	 */
	public boolean saveJcjl(String xh,String[] rq,String[] zy,String[] bz) throws SQLException{

		if (rq.length > 0){
			String[] sqlArr = new String[rq.length];
			
			for (int i = 0 ; i < rq.length ; i++){
				StringBuilder sql = new StringBuilder();
				
				sql.append("insert into xg_xsxx_jcjlb(xh,rq,zy,bz) values ('")
				   .append(xh)
				   .append("','")
				   .append(rq[i])
				   .append("','")
				   .append(zy[i])
				   .append("','")
				   .append(bz[i])
				   .append("')");
				
				sqlArr[i] = sql.toString();
			}
			
			int[] result = runBatch(sqlArr);
			return checkBatch(result);
		} else {
			return true;
		}
	}
	
	
	/**
	 * 根据学号查询审核通过的奖学金记录(浙江工业职业特殊格式)
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getPjpyList(String xh){
		
		//String sql = "select hdsj sqsj,xmmc jxjmc,bz jxjlbmc from xg_pjpy_pjlsxxb where xh=?";//old
		//评奖评优new
		String sql ="select to_char(to_date(a.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') sqsj,a.xmmc,(select xqmc from xqdzb xqb where xqb.xqdm = a.xq) xqmc,a.xn,a.ylzd2 hjwh,b.xmlxmc from xg_pjpy_new_pjjgb a left join xg_pjpy_new_xmlx b on a.lxdm=b.xmlxdm where xh=?";
		return getListNotOut(sql, new String[]{xh});
	}
	
	
	/**
	 * 根据学号查询审核通过的违纪记录
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getWjcfList(String xh){
		//old
		//String sql = "select a.cfsj,b.cflbmc,a.cfwh||' '||c.cfyymc bz from wjcfb a left join cflbdmb b on a.cflb = b.cflbdm left join cfyydmb c on a.cfyy=c.cfyydm where a.xxsh='通过' and xh=?";
		//new
		String sql ="select to_char(to_date(cfsj,'yyyy-mm-dd'),'yyyy-mm-dd') cfsj,cfwh,cflbmc,cfyymc from xg_wjcf_wjcfb where xh=? ";
		return getList(sql, new String[]{xh}, new String[]{"cfsj","cfwh","cflbmc","cfyymc"});
	}
	
	
	/**
	 * 根据学号查询荣誉称号记录
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getRychListByXh(String xh){
		String sql = "select a.rychhdsj,b.rychmc,a.xxyj||' '||b.rychmc bz from xsrychb a left join rychdmb b on b.rychdm=a.rychdm where a.xxsh='通过' and a.xh=?";
		
		return getList(sql, new String[]{xh}, new String[]{"rychhdsj","rychmc","bz"});
	}
	/**
	 * 根据学号查询解除违纪处分记录
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getJcWjcfByXh(String xh){
		//String sql = "select jcsj,jcjg,jcwh||' '||jcjg bz from wjcf_zjlg_lxckb where xxsh='通过' and xh=?";
		String sql ="select to_char(to_date(jcsj,'yyyy-mm-dd'),'yyyy-mm-dd') jcsj,jcwh,cflbmc,jcyj from xg_wjcf_wjcfb where xh=? and jcsj is not null";
		
		return getList(sql, new String[]{xh}, new String[]{"jcsj","jcwh","cflbmc","jcyj"});
	}
	
	/**
	 * 根据学号查询学生资助记录
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getXszzByXh(String xh){
		String sql ="select to_char(to_date(a.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') sqsj,a.xmmc,(select lbmc from xg_xszz_new_zzxmlbb c where a.lbdm=c.lbdm) lbmc from xg_xszz_new_zzxmjgb a where xh=?";
		
		return getList(sql, new String[]{xh}, new String[]{"sqsj","xmmc","lbmc"});
	}
	
	
	/**
	 * 学生学籍信息
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getXjxxList(String[] xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xh,xm,xb,xymc,zymc,bjmc,ksh,sfzh,csrq,zzmmmc,mzmc,sydsmc,zyfx from view_xsxxb where ");
		   
		for (int i = 0 ; i < xh.length ; i++){
			sql.append(" xh=? ");
			
			if (i != xh.length-1){
				sql.append(" or ");
			}
		}
		   
		return getListNotOut(sql.toString(), xh);
	}
	
	
	/**
	 * 德育等第查询
	 */
	public List<String[]> getDyddList(XsxxCommForm model,String query,String[] input){
		//查询结果集
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"xh","xm","nj","xymc","zymc","bjmc","xqmc","pjjg"};
		
		sql.append("select a.*,rownum r from(")
		   .append("select t.xh,v.xm,v.nj,v.xydm,v.xymc,v.zydm,")
		   .append("v.zymc,v.bjdm,v.bjmc,t.xqmc,t.pjjg,")
		   .append("t.xssx,c.count from xg_xsxx_dyddb t ")
		   .append("left join view_xsjbxx v on t.xh=v.xh ")
		   .append("left join (select xh,count(1) count from ")
		   .append("xg_xsxx_dyddb group by xh) c on t.xh = c.xh ")
		   .append("order by xh,xssx) a where 1=1 ")
		   .append(query);
		
		return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(), input,colList);
	}
	
}
