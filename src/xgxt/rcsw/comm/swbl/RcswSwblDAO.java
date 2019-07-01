package xgxt.rcsw.comm.swbl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;

public class RcswSwblDAO extends CommDAO{
	
	/**
	 * 根据项目代码获取
	 * 项目设置表详细信息
	 * method getXmszb
	 * @param model
	 * @return HashMap<String,String>
	 * @author 裘力俊
	 */
	public HashMap<String,String>getXmszb(RcswSwblForm model){
		DAO dao=DAO.getInstance();
		String xmdm=model.getXmdm();
		StringBuilder sql=new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		sql.append(" select xmdm,xmmc,xmlx,shlc,xmfw,rssz,rssz,sfqy,sffj,lyb,sfsc ");
		sql.append(" from xg_rcsw_xmszb where 1=1 ");
		if(!Base.isNull(xmdm)){
			sql.append(" and xmdm=? ");
			inputV.add(xmdm);
		}
		
		String[]output={"xmdm","xmmc","xmlx","shlc","xmfw","rssz","rssz","sfqy","sffj","lyb","sfsc"};
		return dao.getMap(sql.toString(), inputV.toArray(new String[]{}), output);
	}
	
	/**
	 * 根据项目代码获取
	 * 该项目需显示字段
	 * method getXmszb
	 * @param model
	 * @return HashMap<String,String>
	 * @author 裘力俊
	 */
	public List<HashMap<String,String>>getXszdList(RcswSwblForm model){
		
		DAO dao=DAO.getInstance();
		String xmdm=model.getXmdm();
		StringBuilder sql=new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" select xmdm,lyb,zd,zdm,zdlx,zdsx,lrxz,method from xg_rcsw_swzdszb where 1=1 ");
		
		if(!Base.isNull(xmdm)){
			sql.append(" and xmdm=? ");
			inputV.add(xmdm);
		}
		
		sql.append(" order by lyb,to_number(zdsx) asc ");
		String[]output={"xmdm","lyb","zd","zdm","zdlx","zdsx","lrxz","method"};
		return dao.getList(sql.toString(), inputV.toArray(new String[]{}), output);
	}
	
	public boolean saveSwblSq(List<HashMap<String,String>>sqzdList ,RcswSwblForm model) throws Exception{
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		StringBuilder insertSql=new StringBuilder();
		ArrayList<String>inputV=new ArrayList<String>();
		sql.append(" insert into xg_rcsw_swsqb(xmdm,xh,xn,xq,nd,sqsj,scwj ");
		
		String xmdm=model.getXmdm();
		String xh=model.getXh();
		String xn=Base.currXn;
		String xq=Base.currXq;
		String nd=Base.currNd;
		String sqsj=GetTime.getNowTime2();
		inputV.add(xmdm);
		inputV.add(xh);
		inputV.add(xn);
		inputV.add(xq);
		inputV.add(nd);
		inputV.add(sqsj);
		inputV.add(model.getScwj());
		for(int i=0;i<sqzdList.size();i++){
			HashMap<String,String>sqzdMap=sqzdList.get(i);
			sql.append(",");
			sql.append(sqzdMap.get("zd"));
			insertSql.append(",?");
			String selmethod = "get"
					+ sqzdMap.get("zd").substring(0, 1).toUpperCase()
					+ sqzdMap.get("zd")
							.substring(1, sqzdMap.get("zd").length());
			String str;
			try {
				str = (String)RcswSwblForm.class.getMethod(selmethod,
						(Class[]) null).invoke(null,(Object[]) null);
				str = Base.isNull(str) ? "" : str;
				inputV.add(str);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} 
			
		}
		sql.append(" )values(?,?,?,?,?,?,? ");
		sql.append(insertSql);
		sql.append(" )");
		return dao.runUpdate(sql.toString(), inputV);
	}
	
	/**
	 * 获取结果查询信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXsSwblList(RcswSwblForm model)throws Exception{
		
		String xmdm=model.getXmdm();
		StringBuilder sql=new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		sql.append(" select rownum r,xmdm||'!!@@!!'||xh||'!!@@!!'||sqsj pkValue,xmdm,xn,nd,sqsj,xh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc ");
		sql.append(" ,(select xqmc from xqdzb where xqdm=xq)xqmc,xq ");
		sql.append("  from (select a.xmdm,a.xn,a.xq,a.nd,a.sqsj,a.xh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc, ");
		sql.append(" b.bjdm,b.bjmc from xg_rcsw_swsqb a left join view_xsjbxx b on a.xh=b.xh)where 1=1 ");
		if(Base.isNull(xmdm)){
			sql.append(" and xmdm=? ");
			inputV.add(xmdm);
		}
		String[]colList={"pkValue","xn","xqmc","nd","xh","nj","xymc","zymc","bjmc","sqsj"};
		return CommonQueryDAO.commonQuery(sql.toString(), "", inputV.toArray(new String[]{}), colList, model);
	}
	
	/**
	 * 删除事务办理信息
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean delSwblxx(RcswSwblForm model) throws Exception{
		
		String[]pkValue=model.getPrimary_key();
		String[]delSql=new String[pkValue.length*3];
		int n=0;
		for(int i=0;i<pkValue.length;i++){
			//删除子项目表信息
			delSql[n]=" delete from xg_rcsw_swsqzxb where xmdm||'!!@@!!'||xh||'!!@@!!'||sqsj= '"+pkValue[i]+"'";
			//删除事务申请表信息
			delSql[++n]=" delete from xg_rcsw_swsqb where xmdm||'!!@@!!'||xh||'!!@@!!'||sqsj= '"+pkValue[i]+"'";
			//删除事务审核表信息
			delSql[++n]=" delete from xg_rcsw_swshb where xmdm||'!!@@!!'||xh||'!!@@!!'||sqsj= '"+pkValue[i]+"'";
			n++;
		}
		
		return saveArrDate(delSql);
	}
	
	public HashMap<String,String>getSwblMap(RcswSwblForm model){
		DAO dao=DAO.getInstance();
		String pkValue=model.getPkValue();
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.*,b.nj,b.xymc,b.zymc,b.bjmc,b.xm ");
		sql.append("from xg_rcsw_swsqb a left join view_xsjbxx b on a.xh=b.xh where  a.xmdm||'!!@@!!'||a.xh||'!!@@!!'||a.sqsj= ? ");
		String[]outPut={"xmdm","xn","xq","nd","sqsj","xh","nj","xymc","zymc","bjmc","xm","scwj"};
		
		return dao.getMap(sql.toString(), new String[]{pkValue}, outPut);
	}
	
	public boolean updateSwbl(List<HashMap<String,String>>sqzdList ,RcswSwblForm model) throws Exception{
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		ArrayList<String>inputV=new ArrayList<String>();
		sql.append(" update  xg_rcsw_swsqb set ");
		sql.append(" scwj=? ");
		
		String xmdm=model.getXmdm();
		String xh=model.getXh();
		String sqsj=GetTime.getNowTime2();

		inputV.add(model.getScwj());
		for(int i=0;i<sqzdList.size();i++){
			HashMap<String,String>sqzdMap=sqzdList.get(i);
			sql.append(",");
			sql.append(sqzdMap.get("zd"));
			sql.append(" = ? ");
			
			String selmethod = "get"
					+ sqzdMap.get("zd").substring(0, 1).toUpperCase()
					+ sqzdMap.get("zd")
							.substring(1, sqzdMap.get("zd").length());
			String str;
			try {
				str = (String)RcswSwblForm.class.getMethod(selmethod,
						(Class[]) null).invoke(null,(Object[]) null);
				str = Base.isNull(str) ? "" : str;
				inputV.add(str);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} 
			
		}
		sql.append(" where xmdm||'!!@@!!'||xh||'!!@@!!'||sqsj='");
		sql.append(xmdm);
		sql.append("!!@@!!");
		sql.append(xh);
		sql.append("!!@@!!");
		sql.append(sqsj);
		sql.append("'");
		return dao.runUpdate(sql.toString(), inputV);
	}
	
	  
    public boolean saveXmsh(RcswSwblForm model) throws Exception{
    	
    	StringBuilder sql=new StringBuilder();
    	sql.append(" insert into xg_rcsw_swshb(xmdm,sqsj,xh,xtgwid) ");
    	sql.append(" select '"+model.getXmdm()+"' xmdm,'"+GetTime.getNowTime2()+"' sqsj ");
    	sql.append(" ,'"+model.getXh()+"' xh,spgw from  xg_xtwh_spbz ");
    	sql.append(" where splc=(select shlc from xg_rcsw_xmszb where xmdm='001' and rownum=1) ");	
    
    	return saveArrDate(new String[]{sql.toString()});
    }
    
    /**
     * 审核界面信息
     * @param model
     * @return
     * @throws Exception
     */
    public List<String[]> getSpxxInfo(RcswSwblForm model) throws Exception{
    	
    	String xmdm=model.getXmdm();
    	String gwid=model.getShgw();
    	String shjb=model.getShjb();
    	StringBuilder sql=new StringBuilder();
    	List<String>inputV=new ArrayList<String>();
    	
    	sql.append(" select rownum r,a.xmdm||'!!@@!!'||b.xh||'!!@@!!'||a.sqsj pkValue,a.xn,a.xq,a.sqsj,a.xtgwid,a.shzt,b.xh,b.xm,b.xb,b.nj,b.xydm, ");
    	sql.append(" (select xqmc from xqdzb where xq=xqdm)xqmc,b.zydm,b.bjdm,b.xymc,b.zymc,b.bjmc from ( ");
    	sql.append(" select a.xmdm,a.xh,a.xn,a.xq,a.sqsj,b.xtgwid,b.shzt from    ");
    	if(!Base.isNull(shjb) && "1".equalsIgnoreCase(shjb)){
    		
    		sql.append(" xg_rcsw_swshb ");
    	}else if(!"1".equalsIgnoreCase(shjb)){
    		int xh=Integer.parseInt(shjb);
    		xh--;
	    	sql.append(" (select * from xg_rcsw_swshb a where xmdm=? and xtgwid=? ");
	    	sql.append(" and exists (select * from xg_rcsw_swshb b where exists ");
	    	sql.append(" (select 1 from xg_xtwh_spbz c where b.xtgwid=c.spgw and xh=? ");
;	    	sql.append(" and splc=(select shlc from xg_rcsw_xmszb where xmdm=?) ");
	    	sql.append("  )and shzt='通过' and a.xmdm=b.xmdm and a.xh=b.xh and a.sqsj=b.sqsj)) ");
	    	inputV.add(xmdm);
	    	inputV.add(gwid);
	    	inputV.add(String.valueOf(xh));
	    	inputV.add(xmdm);
    	}
    	sql.append("  b left join xg_rcsw_swsqb a on a.xmdm=b.xmdm and a.xh=b.xh and a.sqsj=b.sqsj ");
    	sql.append(" where exists (select 1 from xg_rcsw_xmszb b where xmdm=? and a.xmdm=b.xmdm and  b.xtgwid=? ");
    	sql.append(" ))a left join view_xsjbxx b on a.xh=b.xh  ");
    	
    	
    	inputV.add(xmdm);
    	inputV.add(gwid);
    	String[]outPut={"pkValue","xn","xqmc","xh","xm","xb","nj","xymc","zymc","bjmc","sqsj","shzt"};
    	return CommonQueryDAO.commonQuery(sql.toString(), "", inputV.toArray(new String[]{}), outPut, model);
    }
    
    /**
     * 根据用户名获取当前模块
     * 所涉及到的用户所在审批岗位
     * @param model
     * @return List<HashMap<String,String>>
     */
    public List<HashMap<String,String>>getUserSpgw(RcswSwblForm model){
    	DAO dao=DAO.getInstance();
    	StringBuilder sql=new StringBuilder();
    	String xmdm=model.getXmdm();
    	User user=model.getUser();
    	String userName=user.getUserName();
    	sql.append(" select a.id,a.mc,b.xh from  ( ");
    	sql.append("  select spgw,xh from xg_xtwh_spbz a where exists ");
    	sql.append(" (select 1  from xg_rcsw_xmszb b where xmdm=?  ");
    	sql.append("  and a.splc=b.shlc) and exists(  ");
    	sql.append(" select 1 from xg_xtwh_spgwyh b where b.spyh=? and a.spgw=b.spgw)  ");
    	sql.append(" )b left join  xg_xtwh_spgw a on a.id=b.spgw ");
    	
    	String[]inputV={xmdm,userName};
    	String[]outPut={"id","mc","xh"};
    	return dao.getList(sql.toString(), inputV, outPut);
    }
    
    /**
     * 修改审核状态
     * @param model
     * @return
     * @throws Exception
     */
    public boolean updateShzt(RcswSwblForm model) throws Exception {
		StringBuilder sql = new StringBuilder();

		sql.append(" update xg_rcsw_swshb set shzt=?,shyj=?,shsj=? ");
		sql.append(" where xmdm||'!!@@!!'||xh||'!!@@!!'||sqsj =? ");
		sql.append(" and xtgwid=? ");

		List<String[]> inputV = new ArrayList<String[]>();
		String[] pkValue = model.getPrimary_key();
		String shzt = model.getShzt();
		String gwid= model.getShgw();
		String shyj= model.getShyj();
		String shsj= GetTime.getNowTime2();
		for (int i = 0; i < pkValue.length; i++) {
			String[] values = {  shzt ,shyj,shsj,pkValue[i],gwid};
			inputV.add(values);
		}
		return saveArrDate(sql.toString(), inputV, "xg_rcsw_swshb", model
				.getUser());
	}
    
    public List<HashMap<String,String>>getShxxInfo(RcswSwblForm model){
    	DAO dao=DAO.getInstance();
    	StringBuilder sql=new StringBuilder();
    	String pkValue=model.getPkValue();
    	String xmdm=model.getXmdm();
    	sql.append(" select xmdm,sqsj,xh,shzt,shsj,shyj,shr,id xmid,mc gwmc from ");
    	sql.append(" ( select * from xg_rcsw_swshb where xmdm||'!!@@!!'||xh||'!!@@!!'||sqsj=? )a"); 
    	sql.append(" left join ( select * from xg_xtwh_spgw a where exists( ");
    	sql.append("  select  1 from xg_xtwh_spbz b where splc=(select shlc from xg_rcsw_xmszb where xmdm=?) ");
    	sql.append(" and a.id=b.spgw))b on a.xtgwid=b.id ");
    	String[]outPut={"shzt","shsj","shyj","shr","gwmc","xmid"};
    	return dao.getList(sql.toString(), new String[]{pkValue,xmdm}, outPut);
    }
    
    /**
     * 获取项目设置结果集(分页)
     * @param model
     * @return
     * @throws Exception
     */
    public List<String[]>getXmszList(RcswSwblForm model) throws Exception{
    	StringBuilder sql=new StringBuilder();
    	
    	
    	
    	sql.append(" select xmdm pkValue,xmdm,xmmc,sqzq,swjssj,shjssj,sfqy,mc lcmc from ");
    	sql.append(" xg_rcsw_xmszb a left join xg_xtwh_splc b on a.shlc=b.id  ");
    	String[]colList={"pkValue","xmdm","xmmc","sqzq","swjssj","shjssj","sfqy","lcmc"};
    	
    	return CommonQueryDAO.commonQuery(sql.toString(), "", new String[]{}, colList, model);
    }
}
