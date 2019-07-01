package xsgzgl.pjpy.bjlhdx.wdpj.xssq;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

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
	
		sql.append(" select rownum r,a.*, ");
		sql.append(" case when shztsplit like '%tg%' then 'ysh' ");
		sql.append(" when shztsplit like 'th%' then  'xcs' ");
		sql.append(" when shztsplit like '%th%' then  'th' ");
		sql.append(" when b.xmdm is null then 'wsq'  else  'ysq'  end sqqk, ");
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
		sql.append(" replace(a.shzt, ',', '->') shzt,shztsplit from ( ");
		sql.append(" select a.xh,a.pjxn,a.pjxq,a.pjnd,a.xmdm,max(a.shzt)shzt, max(a.shztsplit) shztsplit ");
		sql.append(" from (select a.xh,a.pjxn,a.pjxq,a.pjnd,a.xmdm, ");
		sql.append(" (case when a.shzt ='tg' or a.shzt='btg' then 1 when  a.shzt = 'th' then 2  when  a.shzt = 'xcs' then 3 else 0 end )sfsh, ");
		sql.append(" to_char(WM_CONCAT( mc||'('||shxx||')') over(partition by a.pjxn, ");
		sql.append(" a.pjxq,a.pjnd,a.xh,a.xmdm order by d.xh)) shzt, ");
		
		sql.append(" to_char(WM_CONCAT(shzt) over(partition by a.pjxn, ");
		sql.append("  a.pjxq, a.pjnd, a.xh, a.xmdm order by d.xh)) shztsplit ");
		
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
	
	public boolean updateShzt(HashMap<String, String> valueMap, User user) {
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszForm=PjpyGeneralForm.getJbszModel();
		
		String xh=valueMap.get("xh");
		
		String xn=jbszForm.getPjxn();
		
		String xq= jbszForm.getPjxq();
		
		String nd = jbszForm.getPjnd();
		
		String xmdm=valueMap.get("xmdm");
		
		StringBuilder sql=new StringBuilder();
		
		boolean flag= false;
		
		sql.append(" update xg_pjpy_pjxmshb a set shzt='xcs' ");
		sql.append(" where pjxn =?  and pjxq =?  and pjnd =? and xh = ?  and xmdm = ? ");
		sql.append(" and exists(select * from xg_xtwh_spbz b where splc=( ");
		sql.append(" select lcid from xg_pjpy_pjxmwhb ");
		sql.append(" where xmdm = ? and pjxn = ?  and pjxq = ? ");
		sql.append(" and pjnd = ?) and xh='1'  and a.xtgwid=b.spgw ) ");
		
		try {
			
			flag = dao.runUpdate(sql.toString(), new String[]{xn,xq,nd,xh,xmdm,xmdm,xn,xq,nd});
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return flag;
		
	}

	public HashMap<String,String>getXsshInfoMap(String xmdm,String xh){
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		String pjxn = jbszModel.getPjxn();
		
		String pjxq = jbszModel.getPjxq();
		
		String pjnd = jbszModel.getPjnd();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from xg_pjpy_pjxmshb ");
		sql.append(" where xh=? and pjxn=? and pjxq=? and pjnd=?  and xmdm=?  and (shzt='xcs' or shzt='th') ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh,pjxn,pjxq,pjnd,xmdm});
		
	}
	// ==================执行查询操作 end==============================
}
