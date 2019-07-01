/**
 * @部门:学工产品事业部
 * @日期：2017-4-18 下午08:17:35 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优-项目设置-条件设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-4-18 下午08:17:53 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjszDao extends SuperDAOImpl<TjszForm>{
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
	}
	
	private static String CHAR_DH = ",";
	
	/**
	 * 普通查询方法
	 * Meng.Wei[工号:1186]
	 */
	public List<HashMap<String, String>> getPageList(TjszForm model)
			throws Exception {
		return null;
	}

	/**
	 * 高级查询方法
	 * Meng.Wei[工号:1186]
	 */
	public List<HashMap<String, String>> getPageList(TjszForm model,
			User user) throws Exception {
		return null;
	}
	
	/**
	 * @描述: 按项目查询审核表中的记录数
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-19 上午09:26:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getFlowData(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_zjdx_pjpy_xmsq where xmdm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "count");
	}
	
	/**
	 * @描述: 查询 条件库表 所有数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-19 上午11:25:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllTj() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_tjkb order by tjmc ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 查询 条件关系表 所有数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-19 上午11:25:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllGx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_tjgxb ");
		return dao.getListNotOut(sql.toString(), null);
	}
	
	/**
	 * @描述: 查询 条件关系对照表 所有数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-19 上午11:25:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllTjGx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_tjgxdzb ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 查询 项目条件表 所有数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-19 上午11:25:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTjsz(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_xmtjb a ");
		sql.append("where a.xmdm = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}
	
	/**
	 * @描述: 获取代码、名称列表
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录: 
	 * @param sql
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDmMc(String bm,String dm,String mc) throws Exception {
		String sql = "select distinct " + dm + "," + mc + " from " + bm + " order by " + dm;
		return dao.getListNotOut(sql, null);
	}
	
	/**
	 * @描述: 更新保存，先删除
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-19 下午02:54:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean runDeal(String xmdm, List<TjszForm> list)
		throws Exception {
		int[] result = null;
		
		List<String> sqlList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		sb.append("delete from xg_pjpy_new_xmtjb ");
		sb.append(" where xmdm='");
		sb.append(xmdm);
		sb.append("'");
		sqlList.add(sb.toString());
		for (TjszForm form : list) {
			sb = new StringBuffer();
			String tjz = form.getTjz();
			if (tjz != null) {
				tjz = tjz.trim();
			} else {
				tjz = "";
			}
			sb.append("insert into xg_pjpy_new_xmtjb");
			sb.append("(xmdm,tjdm,yyfw,gxdm,tjz,ylzq)");
			sb.append(" values('");
			sb.append(xmdm);
			sb.append("','");
			sb.append(URLDecoder.decode(URLDecoder.decode(form.getTjdm(),"UTF-8"),"UTF-8"));
			sb.append("','");
			sb.append(form.getYyfw());
			sb.append("','");
			sb.append(form.getGxdm());
			sb.append("','");
			sb.append(URLDecoder.decode(URLDecoder.decode(form.getTjz(),"UTF-8"),"UTF-8"));
			sb.append("','");
			sb.append(form.getYlzq());
			sb.append("')");
						
			sqlList.add(sb.toString());
		}
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
	
	/**
	 * @描述: 删除
	 * @作者：Meng.Wei[工号:1186]
	 * @日期：2017-4-19 下午02:53:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param tjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delDeal(String xmdm, String tjdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_new_xmtjb a  ");
		sql.append(" where a.xmdm=? and a.tjdm=? ");
		String[] input = { xmdm, tjdm };
		return dao.runUpdate(sql.toString(), input);
	}
	
	/**
	 * @描述: 查询已设置条件值,包含关联属性
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 上午10:10:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTjszGl(String xmdm,String xh)
		throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_xmtjb a,xg_pjpy_new_tjkb b,xg_pjpy_new_tjgxb c,xg_pjpy_new_tjgxdzb d  ");
		sql.append("  where a.tjdm=b.tjdm and a.gxdm=c.gxdm and b.tjdm=d.tjdm and c.gxdm=d.gxdm ");
		sql.append(" and a.xmdm=?");
		if(xh != null && !xh.trim().equals("")){
			CsszForm csszModel = new CsszDao().getModel();
			sql.append(" and (a.yyfw='all' or a.yyfw in(select lxdm from xg_pjpy_new_tsxsb where xh='");
			sql.append(xh);
			sql.append("')");			
			sql.append("  or a.yyfw in(");
			sql.append(" select distinct a.lbdm from xg_xtwh_bjdlb a,xg_xtwh_bjlbb b,xg_zjdx_pjpy_cpmdb c ");
			sql.append(" where a.lbdm=b.lbdm and b.bjdm=c.bjdm and xh='");
			sql.append(xh);
			sql.append("' and xn='");
			sql.append(csszModel.getXn());
			sql.append("')");
			sql.append(")");
		}
		String[] inputValue = { xmdm };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * @描述: 综测条件项目
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 上午10:32:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZhcpTjxm(){
		StringBuilder sql = new StringBuilder();
		sql.append("select xmdm dm, xn, xn || xmmc mc ");
		sql.append("from (select a.xmdm,a.xn,a.xmmc,a.fjdm,px, ");
		sql.append("case when xmlx = '0' then result when xmlx = '1' then zxfz || '-' || zdfz end result, ");
		sql.append("decode(a.xmlx, '0', '等级', '1', '分值', a.xmlx) xmlx from xg_zjdx_pjpy_zcxmb a ");
		sql.append("left join (select xmdm, max(result) result from (select xmdm,mc,wm_concat(mc) over(partition by xmdm order by px) result ");
		sql.append("from xg_zjdx_pjpy_zcxmxxb t) s group by xmdm) b on a.xmdm = b.xmdm ");
		sql.append("START WITH a.fjdm = 'top' CONNECT BY PRIOR a.xmdm = a.fjdm ORDER SIBLINGS BY to_number(px)) t ");
		sql.append("where (t.fjdm = 'top' or exists (select 1 ");
		sql.append("from xg_zjdx_pjpy_zcxmb t2 where t.fjdm = t2.xmdm and t2.fjdm = 'top')) ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 获得所有   思想政治素质等级评价{优秀、良好、合格、不合格}
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-3 下午04:46:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSxzzszDjList()throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select pjdjdm dm,pjdjmc mc from xg_pjpy_new_pjdj where pjxmmc = '思想政治素质等级评价' order by to_number(pjdjdm) desc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 获得所有   思想政治素质等级评价{优秀、良好、免测、及格、不及格}
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-3 下午04:47:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTzjkDjList()throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select pjdjdm dm,pjdjmc mc from xg_pjpy_new_pjdj where pjxmmc = '体质健康等级评价' order by to_number(pjdjdm) desc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
}
