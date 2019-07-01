package xsgzgl.pjpy.xzgyzyjsxy.wdpj.xssq;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_学生申请_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class WdpjXssqDAO extends xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqDAO {

	// ==================执行查询操作 begin==============================
	
	public ArrayList<String[]> getWdpjXssqList(PjpyGeneralForm myForm,WdpjXssqModel model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszForm=PjpyGeneralForm.getJbszModel();
		
		MakeQuery mQuery=new MakeQuery();
		String[]colList={"xmlx","xmxz"};
		String[]colLikeList={"xmmc"};
		mQuery.makeQuery(colList, colLikeList, model);
		
		StringBuilder sql=new StringBuilder();
		
		String pjxn=jbszForm.getPjxn();
		
		String pjxq= jbszForm.getPjxq();
		
		String pjnd = jbszForm.getPjnd();
		
		String xh=user.getUserName();
		
		sql.append(" select rownum r,a.*,case when c.sfsh='ysh' then 'ysh' ");
		sql.append(" when b.xmdm is null then 'wsq' else 'ysq' end sqqk,  ");
		sql.append(" case when a.sfsh='no' then '无需审核' ");
		sql.append("  when c.shzt is null then '尚未申请' else c.shzt end shzt, ");
		
		sql.append(" b.xh||'!!@@!!'|| b.xmdm||'!!@@!!'|| b.pjxn||'!!@@!!'|| b.pjxq||'!!@@!!'|| b.pjnd pkValue ");
		sql.append(" from (select xmmc,xmdm,sfsh,xmlx, ");
		sql.append(" case when xmlx='01' then '奖学金' else '荣誉称号' end lxmc, ");
		sql.append(" (select xzmc from xg_pjpy_xmxzb b where a.xmxz=b.xzdm )xzmc,xmxz, ");
		sql.append(" (select fwmc from xg_pjpy_xmfwb b where a.xmfw=b.fwdm )fwmc, ");
		sql.append(" xmfw,a.pjxn,a.pjxq,a.pjnd ");
		sql.append(" from xg_pjpy_pjxmwhb a where pjxn = ? and pjxq = ? ");
		sql.append(" and pjnd = ? and sqfs = 'xssq' ) a");
		
		sql.append(" left join (select * from xg_pjpy_pjxmsqb where xh=? ");
		sql.append(" and pjxn=? and pjxq=? and pjnd=? ) b on a.xmdm=b.xmdm ");
		sql.append(" and a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd ");
		
		sql.append(" left join (select a.xh,a.pjxn,a.pjxq,a.pjnd,a.xmdm, ");
		sql.append(" replace(a.shzt, ',', '->') shzt, ");
		sql.append(" case when sfsh>0 then 'ysh' else 'wsh' end sfsh from ( ");
		sql.append(" select a.xh,a.pjxn,a.pjxq,a.pjnd,a.xmdm,max(a.shzt)shzt, sum(sfsh)sfsh ");
		sql.append(" from (select a.xh,a.pjxn,a.pjxq,a.pjnd,a.xmdm, ");
		sql.append(" (case when a.shzt ='tg' or a.shzt='btg' then 1 else 0 end )sfsh, ");
		sql.append(" to_char(WM_CONCAT( mc||'('||shxx||')') over(partition by a.pjxn, ");
		sql.append(" a.pjxq,a.pjnd,a.xh,a.xmdm order by d.xh)) shzt ");
		
		
		sql.append(" from (select a.*,  ");
		sql.append(" case when shzt='wsh' then '未审核' ");
		sql.append("  when shzt='tg' then '通过' ");
		sql.append("  when shzt='btg' then '不通过' ");
		sql.append("  when shzt='th' then '退回' ");
		sql.append("  when shzt='xcs' then '需重审' end shxx ");
		sql.append(" from xg_pjpy_pjxmshb a )a, ");
		sql.append(" xg_xtwh_spgw b,xg_pjpy_pjxmwhb c,xg_xtwh_spbz d  ");
		sql.append(" where a.xh = ?  and a.xtgwid=b.id and  ");
		sql.append(" a.xmdm=c.xmdm and a.pjxn=c.pjxn and a.pjxq=c.pjxq and a.pjnd=c.pjnd ");
		sql.append(" and c.lcid=d.splc and a.xtgwid=d.spgw) a ");
		sql.append(" group by xh, pjxn, pjxq, pjnd, xmdm) a ");
		sql.append(" )c on a.xmdm=c.xmdm and a.pjxn=c.pjxn and a.pjxq=c.pjxq and a.pjnd=c.pjnd ");
		
		
		return CommonQueryDAO.commonQuery(sql.toString(),mQuery.getQueryString(),
				dao.unionArray(new String[] {pjxn, pjxq, pjnd, xh,pjxn,pjxq,pjnd, xh },mQuery.getInputList()), 
				new String[] { "xmmc", "lxmc", "xzmc","shzt","sqqk","xmdm","pkValue" }, myForm);
	}
	
	public HashMap<String,String>getXssqMap(String xmdm,String xh){
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		String pjxn = jbszModel.getPjxn();
		
		String pjxq = jbszModel.getPjxq();
		
		String pjnd = jbszModel.getPjnd();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from xg_pjpy_pjxmsqb ");
		sql.append(" where xh=? and pjxn=? and pjxq=? and pjnd=?  and xmdm=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh,pjxn,pjxq,pjnd,xmdm});
		
	}
	
	public boolean saveWdpjShInfo(String xmdm,String xh) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		String pjxn = jbszModel.getPjxn();
		
		String pjxq = jbszModel.getPjxq();
		
		String pjnd = jbszModel.getPjnd();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into xg_pjpy_pjxmshb(xmdm,pjxn,pjxq,pjnd,xh,xtgwid) ");
		sql.append(" select b.xmdm,b.pjxn,b.pjxq,b.pjnd,b.xh,c.spgw ");
		sql.append(" from xg_pjpy_pjxmwhb a, xg_pjpy_pjxmsqb b,xg_xtwh_spbz c ");
		sql.append(" where a.xmdm = b.xmdm ");
		sql.append(" and a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq ");
		sql.append(" and a.pjnd = b.pjnd ");
		sql.append(" and a.lcid=c.splc ");
		sql.append(" and b.xh=? ");
		sql.append(" and b.pjxn=? ");
		sql.append(" and b.pjxq=? ");
		sql.append(" and b.pjnd=? ");
		sql.append(" and b.xmdm=? ");
		
		return dao.runUpdate(sql.toString(),new String[]{xh,pjxn,pjxq,pjnd,xmdm});
		
	}
	
	public List<HashMap<String,String>> getXssqByZq(WdpjXssqModel model,
			User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		String xh=user.getUserName();
		
		sql.append(" select a.*, ");
		sql.append(" case when b.xmlx='01' then '奖学金' else '荣誉称号' end xmlx, ");
		sql.append(" (select xzmc from xg_pjpy_xmxzb c where b.xmxz=c.xzdm)xmxz, ");
		sql.append(" (select fwmc from xg_pjpy_xmfwb c where b.xmfw=c.fwdm)fwmc ");
		sql.append("  from( ");
		sql.append(" select a.xh,a.pjxn,a.pjxq,a.pjnd,a.xmmc,a.xmje,  ");
		sql.append(" a.sqsj,a.xmdm,replace(a.shzt,',','->')shlc from(  ");
		sql.append(" select a.xh, a.pjxn, a.pjxq, a.pjnd, b.xmmc, b.xmje, c.sqsj, a.xmdm,  ");
		sql.append(" to_char(WM_CONCAT(e.mc||'('||shxx||')')over(partition by a.pjxn,  ");
		sql.append(" a.pjxq,a.pjnd,a.xh,a.xmdm  order by a.xh))shzt ");
		sql.append(" from (select a.*,  ");
		sql.append(" case when shzt='wsh' then '未审核' ");
		sql.append("  when shzt='tg' then '通过' ");
		sql.append("  when shzt='btg' then '不通过' ");
		sql.append("  when shzt='th' then '退回' ");
		sql.append("  when shzt='xcs' then '需重审' end shxx ");
		sql.append(" from xg_pjpy_pjxmshb a )a, ");
		sql.append(" xg_pjpy_pjxmwhb b,xg_pjpy_pjxmsqb c,xg_xtwh_spbz d,xg_xtwh_spgw e   ");
		sql.append(" where a.xh =? and a.xmdm=b.xmdm and a.xh=c.xh   ");
		sql.append(" and a.xmdm=c.xmdm and a.pjxn=c.pjxn and   ");
		sql.append(" a.pjxq=c.pjxq and a.pjnd=c.pjnd and b.lcid=d.splc and a.xtgwid=e.id and d.spgw=e.id)a   ");
		
		sql.append(" union select xh, pjxn, pjxq, pjnd, xmmc, xmje, sqsj, xmdm,'无需审核' shzt  ");
		sql.append(" from (select a.xh,a.xmdm,a.pjxn,a.pjxq,  ");
		sql.append(" a.pjnd,b.xmmc, b.xmje,a.sqsj,b.sfsh  ");
		sql.append(" from xg_pjpy_pjxmsqb a, xg_pjpy_pjxmwhb b  ");
		sql.append(" where  a.pjxn = b.pjxn  and a.pjxq = b.pjxq and a.pjnd = b.pjnd  ");
		sql.append(" and a.xmdm = b.xmdm) a  ");
		sql.append(" where sfsh = 'no'  ");
		sql.append(" and xh= ?)a,xg_pjpy_pjxmwhb b where a.xmdm=b.xmdm ");
		
		return dao.getListNotOut(sql.toString(), new String[] {xh,xh});
		
	}
	
	public List<HashMap<String,String>>getXssqList(String xmdm,User user){
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		String pjxn=jbszModel.getPjxn();
		
		String pjxq=jbszModel.getPjxq();
		
		String pjnd=jbszModel.getPjnd();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_pjpy_pjxmsqb ");
		
		sql.append(" where pjxn=? and pjxq=? and pjnd = ? ");
		
		sql.append(" and xmdm=? ");
		
		return dao.getListNotOut(sql.toString(), new String[]{pjxn,pjxq,pjnd,xmdm});
		
	}
	
	public List<HashMap<String,String>>getXssqList(String xmdm[],User user){
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		String pjxn=jbszModel.getPjxn();
		
		String pjxq=jbszModel.getPjxq();
		
		String pjnd=jbszModel.getPjnd();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select b.xmdm,b.xmmc from xg_pjpy_pjxmsqb a,xg_pjpy_pjxmwhb b ");
		
		sql.append(" where a.xmdm=b.xmdm and a.pjxn=b.pjxn and a.pjxq=b.pjxq ");
		
		sql.append(" and a.pjxn=? and a.pjxq=? and a.pjnd = ? ");
		
		sql.append(" and a.xmdm in ( ");

		for(int i=0;i<xmdm.length;i++){
			
			if(i!=0){
				
				sql.append(",");
			}
			sql.append(" ? ");
			
		}
		
		sql.append(" ) ");
		
		String[]inputV={pjxn,pjxq,pjnd};
		
		return dao.getListNotOut(sql.toString(),dao.unionArray(inputV, xmdm));
		
	}
	
	// ==================执行查询操作 end==============================
}
