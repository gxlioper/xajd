package com.zfsoft.xgxt.xsxx.xjyd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.exception.SystemException;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 学籍异动
 * @作者： Qilm[工号:964]
 * @时间： 2013-11-28 上午09:40:48 
 * @版本： V5.12.20
 */
public class XjydDao extends SuperDAOImpl<XjydForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XjydForm t)
			throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XjydForm t, User user)
			throws Exception {
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("dm_xjlb");
		super.setKey("xjlbdm");
		super.setClass(XjydForm.class);
		
	}


	@Override
	public XjydForm getModel(XjydForm model) throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT xjlbdm,  ");
		sql.append("        xjlbmc,  ");
		sql.append("        sfyxj,  ");
		sql.append("        case  ");
		sql.append("          when sfyxj ='1' then '无学籍' ");
		sql.append("        else '有学籍' ");
		sql.append("        end sfyxjmc, ");
		sql.append("        sfzx, ");
		sql.append("        case  ");
		sql.append("          when sfzx ='1' then '不在校' ");
		sql.append("        else '在校' ");
		sql.append("        end sfzxmc ");
		sql.append("   FROM DM_XJLB ");
		sql.append("   WHERE 1=1 ");

		if (!StringUtil.isNull(model.getXjlbmc())) {
			params.add(model.getXjlbmc());
			sql.append(" and xjlbmc = ? ");
		}
		
		if (!StringUtil.isNull(model.getXjlbdm())) {
			params.add(model.getXjlbdm());
			sql.append(" and xjlbdm = ? ");
		}
		return this.getModel(model, sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception  
	 * @描述:学籍异动类别列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-28 上午10:10:38
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXjlbPageList(XjydForm model) throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT xjlbdm,  ");
		sql.append("        xjlbmc,  ");
		sql.append("        sfyxj,  ");
		sql.append("        sfnz,   ");
		sql.append("        case  ");
		sql.append("          when sfyxj ='1' then '无学籍' ");
		sql.append("        else '有学籍' ");
		sql.append("        end sfyxjmc, ");
		sql.append("        sfzx, ");
		sql.append("        case  ");
		sql.append("          when sfzx ='1' then '不在校' ");
		sql.append("        else '在校' ");
		sql.append("        end sfzxmc ");
		sql.append("   FROM DM_XJLB ");
		sql.append("   WHERE 1=1 ");

		if (!StringUtil.isNull(model.getXjlbmc())) {
			params.add(model.getXjlbmc());
			sql.append(" and xjlbmc like '%'||?||'%'");
		}
		
		if (!StringUtil.isNull(model.getXjlbdm())) {
			params.add(model.getXjlbdm());
			sql.append(" and xjlbdm = ? ");
		}
		return getPageList(model, sql.toString(),
				params.toArray(new String[] {}));
	}

	/**
	 * @描述:增加学籍异动类别
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-28 下午02:21:17
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveXjydlb(XjydForm model) {
		
		//保存SQL
		StringBuilder sql=new StringBuilder();
		sql.append(" insert into ");
		sql.append(" DM_XJLB(xjlbdm,Xjlbmc,Sfyxj,Sfzx)");
		sql.append(" values ");
		sql.append(" ( ?,?,?,? )");
		
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql.toString(), 
					new String[] {model.getXjlbdm(),
						model.getXjlbmc(),
						model.getSfyxj(),
						model.getSfzx()});
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * @描述:删除学籍异动类别
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-28 下午02:21:17
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public int delXjydlb(String values) {
		
		String[] valuess = values.split(",");
		
		//保存SQL
		StringBuilder sql=new StringBuilder();
		int num = 0;
		sql.append(" delete from ");
		sql.append(" DM_XJLB ");
		sql.append(" where 1 =1 ");

		if (!StringUtil.isNull(values)) {
			sql.append(" and xjlbdm in ( ");
			for(int i=0; i<valuess.length; i++){
				if(i==0){
					sql.append("'"+valuess[i]+"'");
				}else{
					sql.append(",'"+valuess[i]+"'");
				}
			}
			sql.append(" )");
		}		
		
		try {
			num = dao.runDelete(sql.toString(), new String[] {});
			
		} catch (Exception e) {
			e.printStackTrace();
			num = 0;
		}
		return num;
	}

	/**
	 * @描述:学籍异动类别审核配置列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-28 下午03:48:08
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception  
	 */
	public List<HashMap<String, String>> getXjlbShpzPageList(XjydForm model) throws Exception {

		StringBuilder sql = new StringBuilder();

		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		sql.append(" select * from ( ");
		sql.append("   select t1.*,  ");
		sql.append("        t2.xjlbmc,  ");
		sql.append("        t2.sfyxj,  ");
		sql.append("        t2.sfzx,  ");
		sql.append("        case  ");
		sql.append("          when t2.sfyxj ='1' then '无学籍' ");
		sql.append("        else '有学籍' ");
		sql.append("        end sfyxjmc, ");
		sql.append("        case  ");
		sql.append("          when t2.sfzx ='1' then '不在校' ");
		sql.append("        else '在校' ");
		sql.append("        end sfzxmc, ");
		sql.append("        case  ");
		sql.append("          when t1.sfksq ='0' then '开启' ");
		sql.append("        else '关闭' ");
		sql.append("        end sfksqmc, ");		
		sql.append(" 		case ");
		sql.append("   			when t1.sfksq <> '0' then '' ");
		sql.append("   			when t1.sqjssj is not null then t1.sqkssj || ' ～ '|| t1.sqjssj ");
		sql.append("   			when t1.sqkssj is not null then t1.sqkssj||  ' ～ ' ");
		sql.append("   			else '' end sqqzsj, ");		
		sql.append("        decode(e.lcxx, '', '无需审核',e.mc || '：' || replace(e.lcxx, ';', '->')) shlcmc, ");
		sql.append("        case  ");
		sql.append("          when t1.SFTJBJ ='0' then '需要' ");
		sql.append("        else '不需要' ");
		sql.append("        end sftjbjmc, ");
		sql.append("        case  ");
		sql.append("          when t1.LRQZSJ ='1' then '需要' ");
		sql.append("        else '不需要' ");
		sql.append("        end lrqzsjmc ");
		sql.append("   from xg_xsxx_xjyd_xjydlbb t1 ");
		sql.append("   left join dm_xjlb t2 ");
		sql.append("        on t1.xjlbdm = t2.xjlbdm ");
		sql.append("   left join (select splc, mc, lcxx ");
		sql.append("        from (select splc, ");
		sql.append("                     a.mc, ");
		sql.append("                     to_char(WM_CONCAT(c.mc) ");
		sql.append("                             over(partition by splc order by xh)) lcxx, ");
		sql.append("                     xh, ");
		sql.append("                     row_number() over(partition by splc order by xh desc) as ddd ");
		sql.append("              from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c ");
		sql.append("              where djlx = 'xsxx' ");
		sql.append("                 and a.id = b.splc ");
		
		sql.append("                and b.spgw = c.id) b ");
		sql.append("         where ddd = 1) e ");
		sql.append("        on t1.shlcid = e.splc ");
		sql.append(" ) a where 1=1 ");
		
		if (!StringUtil.isNull(model.getXjlbdm())) {
			sql.append(" and xjlbdm = '"+ model.getXjlbdm() +"'");
		}
		if (!StringUtil.isNull(model.getXjlbmc())) {
			sql.append(" and xjlbmc like '%"+ model.getXjlbmc() +"%' ");
		}
		sql.append(searchTj);		

		
		return getPageList(model, sql.toString(),inputV);
	}

	/** 
	 * @描述:学籍异动类别列表（全部/已设定）
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-28 下午04:46:37
	 * @param flg : 0:全部异动列表；1：可申请的异动；2：可增加的异动列表
	 * @param ydlbdm : 退回的异动类别代码
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXjlbList(String flg, String ydlbdm) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.xjlbdm, t1.xjlbmc, t1.sfyxj, t1.sfzx ");
		sql.append("   from dm_xjlb t1 ");
		if("1".equals(flg)){
			sql.append("  inner join XG_XSXX_XJYD_XJYDLBB t2 ");
			sql.append("     on t1.xjlbdm = t2.xjlbdm ");
			sql.append("    and t2.sfksq = '0' ");
			sql.append("    and ((t2.SQKSSJ is not null and t2.SQKSSJ <= to_char(sysdate,'yyyy-mm-dd')) or t2.SQKSSJ is null) ");
			sql.append("    and ((t2.SQJSSJ is not null and t2.SQJSSJ >= to_char(sysdate,'yyyy-mm-dd')) or t2.SQJSSJ is null) ");
			
			// 退回异动类别代码存在
			if(StringUtils.isNotEmpty(ydlbdm)){
				sql.append(" union ");
				sql.append("  select xjlbdm, xjlbmc, sfyxj, sfzx ");
				sql.append("   from dm_xjlb  ");
				sql.append("     where xjlbdm = '" + ydlbdm + "' ");
			}
		}else if("2".equals(flg)){
			sql.append("    where  not exists (select xjlbdm from XG_XSXX_XJYD_XJYDLBB t2 where t1.xjlbdm = t2.xjlbdm) ");		
		}
		sql.append("   order by xjlbdm ");		
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/** 
	 * @描述:增加学籍异动类别审核配置
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-29 上午08:40:06
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveXjydlbShpz(XjydForm model) {

		
		//保存SQL
		StringBuilder sql=new StringBuilder();
		sql.append(" insert into ");
		sql.append(" XG_XSXX_XJYD_XJYDLBB (xjlbdm , shlcid , sftjbj , sfksq , sqkssj , sqjssj , lrqzsj, xzsfkq, xxsfkq)");
		sql.append(" values ");
		sql.append(" ( ?,?,?,?,?,?,?,?,? )");
		
		boolean flag = false;
		if(!"0".equals(model.getSfksq())){
			model.setSqkssj("");
			model.setSqjssj("");
		}
		try {
			flag = dao.runUpdate(sql.toString(), 
					new String[] {model.getXjlbdm(),
						model.getShlcid(),
						model.getSftjbj(),
						model.getSfksq(),
						model.getSqkssj(),
						model.getSqjssj(),
						model.getLrqzsj(),
						model.getXzsfkq(),
						model.getXxsfkq()});
			
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 
	 * @描述:学籍异动类别审核配置
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-29 上午08:47:53
	 * @param myForm
	 * @return
	 * XjydForm 返回类型 
	 * @throws Exception  
	 */
	public XjydForm getModelShpz(XjydForm model) throws Exception {
		

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT t2.xjlbdm,  ");
		sql.append("   t2.xjlbmc,  ");
		sql.append("   t2.sfyxj,  ");
		sql.append("   case  ");
		sql.append("     when  t2.sfyxj ='1' then '无学籍' ");
		sql.append("   else '有学籍' ");
		sql.append("   end sfyxjmc, ");
		sql.append("   t2.sfzx, ");
		sql.append("   case  ");
		sql.append("     when t2.sfzx ='1' then '不在校' ");
		sql.append("   else '在校' ");
		sql.append("   end sfzxmc, ");
		sql.append("   t1.shlcid, ");
		sql.append("   t1.sftjbj, ");
		sql.append("   t1.sfksq, ");
		sql.append("   t1.sqkssj, ");
		sql.append("   t1.sqjssj, ");
		sql.append("   t1.lrqzsj ");
		if("10511".equals(Base.xxdm)) {
			sql.append(",t1.xzsfkq, t1.xxsfkq ");
		}
		sql.append(" FROM DM_XJLB t2 ");
		sql.append(" left join XG_XSXX_XJYD_XJYDLBB t1 ");
		sql.append(" on t1.xjlbdm = t2.xjlbdm ");
		sql.append(" WHERE 1=1 ");
				
		if (!StringUtil.isNull(model.getXjlbdm())) {
			params.add(model.getXjlbdm());
			sql.append(" and t2.xjlbdm = ? ");
		}
		return this.getModel(model, sql.toString(), params.toArray(new String[]{}));
	}

	/** 
	 * @描述:修改学籍异动类别审核配置
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-29 上午08:55:36
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updXjydlbShpz(XjydForm model) {
		
		//更新SQL
		StringBuilder sql=new StringBuilder();
		sql.append(" update ");
		sql.append(" XG_XSXX_XJYD_XJYDLBB ");
		sql.append(" set ");
		sql.append(" SHLCID = ? , ");
		sql.append(" SFTJBJ = ? , ");
		sql.append(" SFKSQ = ? , ");
		sql.append(" SQKSSJ = ? , ");
		sql.append(" SQJSSJ = ? ,  ");
		sql.append(" lrqzsj = ? , ");
		sql.append(" XZSFKQ = ? , ");
		sql.append(" XXSFKQ = ? ");
		sql.append(" where ");
		sql.append(" XJLBDM = ?  ");

		if(!"0".equals(model.getSfksq())){
			model.setSqkssj("");
			model.setSqjssj("");
		}
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql.toString(), 
					new String[] {model.getShlcid(),
						model.getSftjbj(),
						model.getSfksq(),
						model.getSqkssj(),
						model.getSqjssj(),
						model.getLrqzsj(),
						model.getXzsfkq(),
						model.getXxsfkq(),
						model.getXjlbdm()});
			
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/** 
	 * @描述:删除学籍异动类别审核配置
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-29 上午08:59:07
	 * @param values
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int delXjydlbShpz(String values) {

		//保存SQL
		StringBuilder sql=new StringBuilder();
		int num = 0;
		sql.append(" delete from ");
		sql.append(" XG_XSXX_XJYD_XJYDLBB ");
		sql.append(" where 1 =1 ");

		if (!StringUtil.isNull(values)) {
			sql.append(" and xjlbdm in ("+ values + ")");
		}		
		
		try {
			num = dao.runDelete(sql.toString(), new String[] {});
			
		} catch (Exception e) {
			e.printStackTrace();
			num = 0;
		}
		return num;
	}
	
	/**
	 * 
	 * @描述:根据学籍类别代码返回学籍类别信息
	 * @作者：cq [工号：785]
	 * @日期：2014-4-30 下午03:24:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xjlbdm
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getXjydInfor(String xjlbdm){
		
		if(!StringUtil.isNull(xjlbdm)){
					
			StringBuilder sql = new StringBuilder();
			
			sql.append(" select * from XG_XSXX_XJYD_XJYDLBB where xjlbdm = ? ");
			
			return dao.getMapNotOut(sql.toString(), new String []{xjlbdm});
		}
		
		logger.debug("学籍类别代码为空！");
		throw new SystemException("学籍类别代码为空！");
		
		
	}
	
	/**
	 * 
	 * @描述:查看类别代码在学生信息当中是否使用
	 * @作者：cq [工号：785]
	 * @日期：2014-8-29 下午04:46:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lbdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String lbdmExistXsxxb (String lbdm){
		
		String sql = "select count(1) count from xsxxb a where xjlbdm = ? or xjlb = ? ";
		
		return dao.getOneRs(sql, new String []{lbdm,lbdm}, "count");
		
	}
	
	
	/**
	 * 
	 * @描述:查看类别代码在异动结果当中是否使用
	 * @作者：cq [工号：785]
	 * @日期：2014-8-29 下午05:05:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lbdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String lbdmExistXjydjgb (String lbdm){
		
		String sql = "select count(1) count from xg_xsxx_xjydjgb a where a.ydqxjlb = ? or a.ydlbdm = ? ";
		
		return dao.getOneRs(sql, new String []{lbdm,lbdm}, "count");
		
	}
	
	
	/**
	 * 
	 * @描述:查看类别代码在异动申请当中是否使用
	 * @作者：cq [工号：785]
	 * @日期：2014-8-29 下午05:17:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lbdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String lbdmExistXjydsqb (String lbdm){
		
		String sql = "select count(1) count from xg_xsxx_xjydsqb a where ydlbdm = ? ";
		
		return dao.getOneRs(sql, new String []{lbdm}, "count");
		
	}
	
	
	/**
	 * 
	 * @描述:查询类别名称是否已被使用
	 * @作者：cq [工号：785]
	 * @日期：2014-9-9 上午09:34:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lbmc
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String lbmcExistLbmc(String lbmc){
		
		String sql = "select count(1) count from dm_xjlb where xjlbmc = ?";
		
		return dao.getOneRs(sql, new String []{lbmc}, "count");
	}

	/** 
	 * @描述:查询类别代码是否已被使用
	 * @作者：cq [工号：785]
	 * @日期：2014-9-10 上午09:25:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lbdm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String lbdmExists(String lbdm) {
		
		String sql = "select count(1) count from dm_xjlb where xjlbdm = ? ";

		return dao.getOneRs(sql, new String[]{lbdm}, "count");
	}
	
	
}
