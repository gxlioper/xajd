/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-10-30 下午01:59:03</p>
 */
package xgxt.xsgygl.zjgszyjsxy;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class GyglZjgszyDAO {
	public ArrayList<HashMap<String, String>> dao_gyTzzQueryTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colListCN = null;
		// 必须与方法dao_gyTzzQuery中的输出表列一致
		colListCN = new String[]{"主键" ,"楼栋", "部门", "职务", "姓名", "班级", "所在寝室", "入职日期","离职日期"};
		for (int i = 0; i < colListCN.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]> dao_gyTzzQuery(GyTzzModel tzzModel){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xqdm = tzzModel.getXqdm();
		String lddm = tzzModel.getLddm();
		String xh   = tzzModel.getXh();
		String xm   = tzzModel.getXm();
//		String pk   = "lddm||xh||zw||rzrq";
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xqdm)?"":" and xqdm='"+xqdm+"' ");
		querry.append(Base.isNull(lddm)||"null".equalsIgnoreCase(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%' ");
		String[] colList = new String[]{"pk","ldmc","bm","zw","xm","bjmc","szqs","rzrq","lzrq"};
		String  sql = " select lddm||xh||zw||rzrq pk,ldmc,bm,zw,xm,bjmc,nvl(szqs,'暂未入住')szqs,rzrq,lzrq from view_gytzzxx " +querry;
		result = dao.rsToVator(sql, new String[]{},colList);
		return result;
	}
	public boolean dao_gyTzzAdd(GyTzzModel tzzModel) throws Exception{
		DAO dao      = DAO.getInstance();
		String xh    = tzzModel.getXh().trim();
		String lddm  = tzzModel.getLddm();
		String bm    = tzzModel.getBm();
		String zw    = tzzModel.getZw().trim();
		String ssbh  = tzzModel.getSsbh();
		String lxdh  = tzzModel.getLxdh();
		String rzrq  = tzzModel.getRzrq();
		String lzrq  = tzzModel.getLzrq();
		String bz    = tzzModel.getBz();
		String pk    = "lddm||xh||zw||rzrq";
		String pkValue = lddm+xh+zw+rzrq;
		boolean done = false;
		if("empty".equalsIgnoreCase(dao.returntag("select * from gytzzxxb where "+pk+" =? ",new String[]{pkValue}))){
			String sql   = " insert into gytzzxxb(lddm,xh,bm,zw,ssbh,lxdh,rzrq,lzrq,bz)values(?,?,?,?,?,?,?,?,?)";
			done =  dao.runUpdate(sql, new String[]{lddm,xh,bm,zw,ssbh,lxdh,rzrq,lzrq,bz});
		}
		return done;
	}
	public HashMap<String,String> dao_getTzzXx(String pkValue){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new  HashMap<String,String>();
		String pk   = "lddm||xh||zw||rzrq";
		String sql = " select * from view_gytzzxx where  "+pk+"= ?";		
		map = dao.getMap(sql,new String[]{pkValue}, new String[]{"xh","xm","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","lddm","ldmc","bm","zw","ssbh","lxdh","rzrq","lzrq","bz"});
		return map;
	}
	public boolean dao_gyTzzModi(GyTzzModel tzzModel,String pkValue) throws Exception{
		DAO dao       = DAO.getInstance();
		String lddm  = tzzModel.getLddm();
		String bm    = tzzModel.getBm();
		String zw    = tzzModel.getZw().trim();		
		String lxdh  = tzzModel.getLxdh();
		String rzrq  = tzzModel.getRzrq();
		String lzrq  = tzzModel.getLzrq();
		String bz    = tzzModel.getBz();
		String pk    = "lddm||xh||zw||rzrq";
		boolean done = false;
		String sql = " update gytzzxxb set lddm=?,bm=?,zw=?,lxdh=?,rzrq=?,lzrq=?,bz=? where "+pk+"=? ";
		done = dao.runUpdate(sql, new String[]{lddm,bm,zw,lxdh,rzrq,lzrq,bz,pkValue});
		return done ;
	}
	public boolean dao_gyTzzDel(String pkValue) throws Exception{
		DAO dao       = DAO.getInstance();
		String pk    = "lddm||xh||zw||rzrq";
		boolean done = false;
		String sql = "delete from gytzzxxb where "+pk+"= ? ";
		done = dao.runUpdate(sql,new String[]{pkValue});
		return done;
	}
	public boolean dao_mjqsAdd(MjqsModel mjqsModel) throws Exception{
		DAO dao       = DAO.getInstance();
		String xn     = mjqsModel.getXn();
		String xq     = mjqsModel.getXq();
		//String lddm   = mjsqModel.getLddm();
	    String ssbh   = mjqsModel.getSsbh();
		String rq     = mjqsModel.getRq();
		String bz     = mjqsModel.getBz();
		String pk     = "xn||xq||ssbh";
		String pkValue = xn+xq+ssbh; 
		boolean done = false;
		if("empty".equalsIgnoreCase(dao.returntag("select * from mjqspbb where "+pk+" =? ",new String[]{pkValue}))){
			String sql    = " insert into mjqspbb(xn,xq,lrsj,ssbh,bz)values(?,?,?,?,?)";
			done = dao.runUpdate(sql,new String[]{xn,xq,rq,ssbh,bz});
		}
		return done;
	}
	public ArrayList<HashMap<String, String>> dao_getmjqsSearchTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colListCN = null;
		// 必须与方法dao_mjqsQuery中的输出表列一致
		colListCN = new String[]{"主键" ,"学年", "学期", "楼栋名称", "寝室号", "录入时间"};
		for (int i = 0; i < colListCN.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]> dao_mjqsQuery(MjqsModel mjqsModel){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xn = mjqsModel.getXn();
		String xq = mjqsModel.getXq();
		String lddm   = mjqsModel.getLddm();
		String xqdm   = mjqsModel.getXqdm();
		String qsh  = mjqsModel.getQsh();
		//String pk   = "xn||xq||ssbh";
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(lddm)||"null".equalsIgnoreCase(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh= '"+qsh+"' ");
		querry.append(Base.isNull(xqdm)?"":" and xqdm= '"+xqdm+"' ");
		String[] colList = new String[]{"pk","xn","xqmc","ldmc","qsh","lrsj"};
		String  sql = " select xn||xq||ssbh pk,xn,xqmc,ldmc,qsh,lrsj from view_mjqspbxx  " +querry;
		result = dao.rsToVator(sql, new String[]{},colList);
		return result;
	}
	public HashMap<String,String> dao_getMjqsXx(String pkValue){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new  HashMap<String,String>();
		String pk   = "xn||xq||ssbh";
		String sql = " select xn,xq,lddm,ssbh,lrsj rq,bz from view_mjqspbxx where  "+pk+"= ?";		
		map = dao.getMap(sql,new String[]{pkValue}, new String[]{"xn","xq","lddm","ssbh","rq","bz"});
		return map;
	}
	public boolean dao_mjqsModi(MjqsModel mjqsModel,String pkValue) throws Exception{
		DAO dao = DAO.getInstance();
		String xn     = mjqsModel.getXn();
		String xq     = mjqsModel.getXq();
		//String lddm   = mjsqModel.getLddm();
	    String ssbh   = mjqsModel.getSsbh();
		String rq     = mjqsModel.getRq();
		String bz     = mjqsModel.getBz();
		String pk     = "xn||xq||ssbh";
		boolean done = false;
		String sql    = " update mjqspbb set xn=?,xq=?,lrsj=?,ssbh=?,bz=? where "+pk+" =?";
		done = dao.runUpdate(sql,new String[]{xn,xq,rq,ssbh,bz,pkValue});
		return done;
	}
	public boolean dao_mjqsDel(String pkValue) throws Exception{
		DAO dao       = DAO.getInstance();
		String pk    = "xn||xq||ssbh";
		boolean done = false;
		String sql = "delete from mjqspbb where "+pk+"= ? ";
		done = dao.runUpdate(sql,new String[]{pkValue});
		return done;
	}
	
}
