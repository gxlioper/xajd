/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-7-13 上午11:03:21</p>
 */
package xgxt.sztz.nbzyjsxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.sztz.dao.SztzDao;
import xgxt.utils.RowidToPk;

public class SztzNbzyDAO {
	/**
	 * 获取学生基本信息方法
	 * @param xh
	 * @return
	 */
	public HashMap<String,String> getStuInfo(String xh){
		DAO dao= DAO.getInstance();
		String sql = "select xh,xb,xm,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,zzmmmc,sfzh,jg,csrq,mzmc,rxrq from view_xsxxb where xh=? ";
		return dao.getMap(sql,new String[]{xh},new String[]{"xh","xb","xm","nj","xydm","zydm","bjdm","xymc","zymc","bjmc","zzmmmc","sfzh","jg","csrq","mzmc","rxrq"});
	}
	/**获取学生拓展信息方法*/
	public HashMap<String,String> getTzxxInfo(String pkValue){
		DAO dao = DAO.getInstance();
		String sql = "select * from view_nbzy_tzcjxx where pkey=?";
		String[] cols = new String[]{"cj","bz","sfjlxm","pkey","xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","zzmmmc","sfzh","jg","csrq","mzmc","xn","xq","kmdm","kmmc","xmmc","sj"};
		return dao.getMap(sql,new String[]{pkValue},cols);
	}
	/**拓展信息添加保存
	 * @throws Exception */
	public boolean dao_tzInfoAddSave(TzInfoAddModel model) throws Exception{
		DAO dao   = DAO.getInstance();
		boolean done = false;
		String pk = "xh||xn||xq||kmdm||xmmc||sj";
		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String xmmc = model.getXmmc();
		String kmdm = DealString.toString(model.getKmdm());
		String sfjl = model.getSfjlxm();
		String cj   = model.getCj();
		String sj   = model.getSj();
		String bz   = model.getBz();
		String sql = "select count(*)cout from nbzy_tzcjxxb where "+pk+"=?";
		String exist = dao.getOneRs(sql,new String[]{xh+xn+xq+kmdm+xmmc+sj},"cout");
		if("0".equalsIgnoreCase(exist)){
			sql = "insert into nbzy_tzcjxxb(xh,xn,xq,kmdm,xmmc,sj,cj,bz,sfjlxm)values(?,?,?,?,?,?,?,?,?)  ";
			done = dao.runUpdate(sql,new String []{xh,xn,xq,kmdm,xmmc,sj,cj,bz,sfjl});
		}
		return done;
	}
	/**获取表头公用方法*/
	public ArrayList<HashMap<String, String>> dao_getQrrTit(String[] enCols,String[] cnCols){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < enCols.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", enCols[i]);
			map.put("cn", cnCols[i]);
			result.add(map);
			map = null;
		}
		return result;	  
	}
	public ArrayList<HashMap<String, String>> dao_getTzInfoTit(){
		String[]enCols = {"pkey","r","xn","xq","xh","xm","xb","xymc","bjmc","kmmc","xmmc","sj","sfjlxm"};
		String[]cnCols = {"主键","行号","学年","学期","学号","姓名","性别","院系","班级","所属科目","项目","参与时间","是否奖励项目"};
		return dao_getQrrTit(enCols, cnCols);
	}
	public ArrayList<String[]> dao_tzInfoQuerry(TzInfoQuerryModel model){
		DAO     dao    = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String[] colList       = null;
		StringBuffer querry    = new StringBuffer(" where 1=1 ");
		String nj  = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xh   = model.getXh();
		String xn   = model.getXn();
		String xq   = model.getXq();
		querry.append((Base.isNull(nj)?"":" and nj = '"+nj+"' "));
		querry.append((Base.isNull(xydm)?"":" and xydm = '"+xydm+"' "));
		querry.append((Base.isNull(zydm)?"":" and zydm = '"+zydm+"'  "));
		querry.append((Base.isNull(bjdm)?"":" and bjdm = '"+bjdm+"'  "));
		querry.append((Base.isNull(xh)?"":" and xh = '"+xh+"'  "));    	
		querry.append((Base.isNull(xm)?"":" and xm like '%"+xm+"%' ")); 
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"'");
		querry.append(Base.isNull(xq)?"": "and xq='"+xq+"'");
		colList = new String[]{"pkey","r","xn","xq","xh","xm","xb","xymc","bjmc","kmmc","xmmc","sj","sfjlxm"};				   	
		String sql = " select pkey,rownum r,xn,(select xqmc from xqdzb b where b.xqdm=a.xq)xq,xh,xm,xb,xymc,bjmc,kmmc,xmmc,sj,sfjlxm from view_nbzy_tzcjxx a";
		rs = dao.rsToVator(sql+querry.toString() , new String[]{},colList);
		return rs;
	}
	/**拓展信息修改保存
	 * @throws Exception */
	public boolean dao_tzInfoModiSave(TzInfoAddModel model,String pkValue) throws Exception{
		DAO dao   = DAO.getInstance();
		boolean done = false;
		String xn = DealString.toGBK(model.getXn());
		String xq = DealString.toGBK(model.getXq());
		String xmmc = DealString.toGBK(model.getXmmc());
		String kmdm = DealString.toGBK((model.getKmdm()));
		String sfjl = DealString.toGBK(model.getSfjlxm());
		String cj   = DealString.toGBK(model.getCj());
		String sj   = DealString.toGBK(model.getSj());
		String bz   = DealString.toGBK(model.getBz());
		String sql = "update  nbzy_tzcjxxb set xn=?,xq=?,kmdm=?,xmmc=?,sj=?,cj=?,bz=?,sfjlxm=? where rowid=?";
		done = dao.runUpdate(sql,new String []{xn,xq,kmdm,xmmc,sj,cj,bz,sfjl,pkValue});			
		return done;
	}	
	public boolean dao_tzInfoDel(String delPk) throws SQLException{
		DAO dao = DAO.getInstance();
		String[] pkValueA = delPk.split("!!");
		String[] delPkSql  = new String[pkValueA.length];
		for (int i = 0; i < pkValueA.length; i++) {
			delPkSql[i] = Base.isNull(pkValueA[i])?"":"delete nbzy_tzcjxxb where rowid= '"+RowidToPk.rowidToPK(pkValueA[i])+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(delPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	public ArrayList<HashMap<String, String>> dao_getTzPrintTit(){
		String[]enCols = {"xh","xm","xb","xymc","zymc","bjmc","cout"};
		String[]cnCols = {"学号","姓名","性别","院系","专业","班级","参与项目数",};
		return dao_getQrrTit(enCols, cnCols);
	}
	public ArrayList<String[]> dao_tzPrintQuerry(TzInfoQuerryModel model){
		DAO     dao    = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String[] colList       = null;
		StringBuffer querry    = new StringBuffer(" where 1=1 ");
		String nj  = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xh   = model.getXh();
		querry.append((Base.isNull(nj)?"":" and nj = '"+nj+"' "));
		querry.append((Base.isNull(xydm)?"":" and xydm = '"+xydm+"' "));
		querry.append((Base.isNull(zydm)?"":" and zydm = '"+zydm+"'  "));
		querry.append((Base.isNull(bjdm)?"":" and bjdm = '"+bjdm+"'  "));
		querry.append((Base.isNull(xh)?"":" and a.xh = '"+xh+"'  "));    	
		querry.append((Base.isNull(xm)?"":" and a.xm like '%"+xm+"%' ")); 
		querry.append(" order by xh");
		colList = new String[]{"xh","xm","xb","xymc","zymc","bjmc","cout"};			   	
		String sql = " select a.xh,a.xm,a.nj,a.xb,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,nvl(b.cout,0)cout from view_xsjbxx a left join (select count(xh)cout,xh from nbzy_tzcjxxb group by xh)b on a.xh=b.xh ";
		rs = dao.rsToVator(sql+querry.toString() , new String[]{},colList);
		return rs;
	}
	/**获取在校期间奖励情况*/
	public ArrayList<String[]> dao_tzJlxm(String xh){
		DAO     dao    = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String[] colList       = null;
		
		colList = new String[]{"xmmc","sj","bz"};			   	
		String sql = " select xmmc,sj,bz from view_nbzy_tzcjxx where sfjlxm ='是' and xh=? ";
		rs = dao.rsToVator(sql , new String[]{xh},colList);
		return rs;
	}
	/**获取拓展科目列表*/
	public String[] getKmdm() throws Exception{
		SztzDao sztzDao=new SztzDao();
		String[] kmdm  = sztzDao.getRs("select kmdm from sztz_kmdmb order by kmdm", new String[]{},"kmdm");//素质拓展科目列表
		return kmdm;
	}
	/**获取素质拓展记录集*/
	public Vector<Object> dao_getTzInfoListPrint(String xh) throws Exception{
		DAO dao       = DAO.getInstance();
		Vector<Object> rs  = new Vector<Object>();
		String[] kmdm = getKmdm();
		for(int i=0;i<kmdm.length;i++){
			HashMap<String, Object> map = new HashMap<String, Object>();
			StringBuffer sql=new StringBuffer();
			List xmList = null;
			sql.append(" select xn||' 学年 '||(select xqmc from xqdzb b where b.xqdm=a.xq)||' 学期 '||xmmc||' '||cj xmjl from view_nbzy_tzcjxx a ");			
			sql.append(" where (sfjlxm='否' or sfjlxm is null) and xh=? and kmdm=? ");
			String[] colList=new String[]{"xmjl"};
			xmList = dao.getList(sql.toString(), new String[] {xh,kmdm[i]}, colList);
			map.put("xmList", xmList);
			map.put("kmList", dao.getList("select kmm from sztz_kmdmb where kmdm='"+kmdm[i]+"'", new String[] {}, new String[]{"kmm"})); 
			rs.add(map);
		}
		return rs;
	}
}
