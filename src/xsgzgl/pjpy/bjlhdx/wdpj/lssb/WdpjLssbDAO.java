package xsgzgl.pjpy.bjlhdx.wdpj.lssb;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-11 上午10:17:45</p>
 */
public class WdpjLssbDAO extends CommDAO{

	// ------------------------老师上报信息查询 begin --------------------------------
	/**
	 * 获取老师上报班级学生申请项目情况
	 * author qlj
	 * @param myForm
	 * @param model
	 * @param user
	 * @return ArrayList<String[]>
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getWdpjLssbList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		DAO dao=DAO.getInstance();
		
		// 综测dao
		PjpyZhcpDAO zhcpDAO=new PjpyZhcpDAO();
		// 评奖基本设置信息
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		StringBuilder sql=new StringBuilder();
		// 项目代码
		String xmdm=model.getXmdm();
		
		// --------------------------查询字段 begin-------------------------------
	
		String[]colList=new String[]{"xh","xm","nj","bjmc","zd3"};

		// 智育排名字段 根据基本设置中综测排名的设置出(1,2,3,4,5,6,7 种情况)
		String[]zypmList=zhcpDAO.getZyPmArr("en");// 根据配置信息动态获取
		
		colList=dao.unionArray(colList, zypmList);

		String[]other=new String[]{"zd1"};
		
		colList=dao.unionArray(colList, other);
		
		// 综测排名字段 根据基本设置中综测排名的设置出(1,2,3,4,5,6,7 种情况)
		String[]zcpmList=zhcpDAO.getZcPmArr("en");// 根据配置信息动态获取
		
		colList=dao.unionArray(colList, zcpmList);
		
		other=new String[]{"sqqk","pkValue"};
		
		colList=dao.unionArray(colList, other);
		
		// --------------------------查询字段 end-------------------------------
		
		String bjdm=model.getBjdm();
		
		String pjxn=jbszModel.getPjxn();
		
		String pjxq=jbszModel.getPjxq();
		
		String pjnd=jbszModel.getPjnd();
		
		sql.append(" (select a.*, ");
		// shztsplit 字段中存在tg 那么审核状态为已审核 。。。。
		sql.append(" case when shztsplit like '%tg%' then 'ysh' ");
		sql.append(" when shztsplit like 'th%' then  'xcs' ");
		sql.append(" when shztsplit like '%th%' then  'th' ");
		sql.append(" when shztsplit is null then 'wsq' else 'ysq'  end sqqk, ");
		
		sql.append(" b.pkValue   from ( ");
		
		// ------------------------综合测评信息 being---------------------------
		sql.append(" select a.xh,a.xm,a.nj,a.xydm, ");
		sql.append(" a.xymc,a.zydm,a.zymc, a.bjdm, ");
		sql.append(" a.bjmc,b.zd1,b.zd3,b.zcfnjxypm, b.zcfnjzypm,b.zcfbjpm, ");
		sql.append(" b.zyfnjxypm, b.zyfnjzypm,b.zyfbjpm, b.zyfcpzpm,b.cpzpm ");
		sql.append(" from xg_view_pjpy_pjryk a ");
		sql.append(" left join xg_pjpy_zhcpb b on a.xh = b.xh ");
		sql.append(" where bjdm = ?  and xn = ? ");
		sql.append(" and xq = ? and nd = ?)a ");
		// ------------------------综合测评信息 end ---------------------------
		
		sql.append(" left join ");
		
		// ------------------------评奖申请信息 begin------------------------
		sql.append(" (select b.*, ");
		sql.append(" b.xh||'!!@@!!'|| b.xmdm||'!!@@!!'|| b.pjxn||'!!@@!!'|| b.pjxq||'!!@@!!'|| b.pjnd  ");
		sql.append(" pkValue from xg_pjpy_pjxmsqb b ");
		sql.append("  where b.pjxn=? ");
		sql.append("  and b.pjxq=? and b.pjnd=? ");
		sql.append("  and b.xmdm=?)b on a.xh=b.xh");
		// ------------------------评奖申请信息 end------------------------	
	
		// ------------------------评奖审核信息 begin------------------------
		sql.append(" left join (");

		
		sql.append(" select a.xh,a.pjxn, a.pjxq,a.pjnd,a.xmdm, ");
		sql.append(" max(a.shztsplit) shztsplit  from ( ");
		sql.append(" select a.xh, a.pjxn,  a.pjxq, a.pjnd, a.xmdm, ");
		sql.append(" to_char(WM_CONCAT(shzt) over(partition by a.pjxn, ");
		sql.append(" a.pjxq, a.pjnd, a.xh, a.xmdm order by d.xh)) shztsplit ");
		sql.append(" from (select a.*  from xg_pjpy_pjxmshb a) a, ");
		sql.append(" xg_xtwh_spgw b, ");
		sql.append(" xg_pjpy_pjxmwhb c, ");
		sql.append(" xg_xtwh_spbz d ");
		sql.append(" where a.xtgwid = b.id ");
		sql.append(" and a.xmdm = c.xmdm ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and c.lcid = d.splc ");
		sql.append(" and a.xtgwid = d.spgw ");
		sql.append(" and a.pjxn=? and a.pjxq=? and a.pjnd=? and a.xmdm=?");
		sql.append(" ) a group by xh, pjxn, pjxq, pjnd, xmdm ");
		sql.append(" )d on a.xh=d.xh )");
		
		// ------------------------评奖审核信息 end------------------------
		
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "",
				new String[]{bjdm,pjxn,pjxq,pjnd,pjxn,pjxq,pjnd,xmdm,pjxn,pjxq,pjnd,xmdm} , colList , null);
	}
	// ------------------------老师上报信息查询 end --------------------------------	
	
	// ------------------------其它详细信息 begin --------------------------------	
	/**
	 * 学生成绩信息(获取学生成绩信息列表)
	 * author qlj
	 * @param myForm
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getXscjList(
			String xh) {
		
		// 评奖基本设置信息
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		DAO dao = DAO.getInstance();
		// 评奖周期
		String pjzq=jbszModel.getPjzq();
		// 评奖学年
		String xn=jbszModel.getPjxn();
		// 评奖学期
		String xq=jbszModel.getPjxq();

		StringBuilder sql = new StringBuilder();
		
		// --------------------查询语句 begin-------------------------
		sql.append(" select xn,xq,kcmc,kcxz,cj,  ");
		sql.append(" (select xqmc from xqdzb b where a.xq=b.xqdm )xqmc ");
		sql.append(" from cjb  a where xh=? ");
		// --------------------查询语句 end-------------------------		
		
		// ----------------输入参数 begin---------------------
		List<String>inputV=new ArrayList<String>();
		
		inputV.add(xh);
		// 根据评奖周期获取成绩表数据
		if("xn".equalsIgnoreCase(pjzq)){// 学年
			sql.append(" and  xn=? ");
			inputV.add(xn);
		}else{ // 学期
			sql.append(" and xn=? ");
			sql.append(" and xq=? ");
			inputV.add(xn);
			inputV.add(xq);
		}
		// ----------------输入参数 end---------------------
		
		sql.append(" order by xn,xq ");// 排序
		
		return dao.getList(sql.toString(),inputV.toArray(new String[]{}), new String[] {
				"xn", "xqmc", "kcmc", "kcxz", "cj" });
	}
	// ------------------------其它详细信息 begin --------------------------------
	
	
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
}
