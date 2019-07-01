/**
 * @部门:学工产品事业部
 * @日期：2016-6-1 上午11:13:48 
 */  
package com.zfsoft.xgxt.qgzx.xsgwnew.sq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学
 * @类功能描述: 学生岗位申请 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-6-1 上午11:13:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsgwsqDao extends SuperDAOImpl<XsgwsqForm>{
	
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsgwsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsgwsqForm t, User user)
			throws Exception {
		
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select distinct a.*, t.rddc ");
        if("10335".equals(Base.xxdm)){
        	sql.append(" ,t.dcmc ");
        }else{
			sql.append(" ,t4.dcmc ");
        }
		sql.append(" from (select a.*, c.xydm, c.zydm, c.bjdm, c.nj, c.xymc, c.zymc, c.bjmc, c.xm, c.sjhm, c.sfzh, c.xb, c.zzmmmc, c.mzmc, c.jtdzxx, ");
		sql.append(" decode(w.bmlb, '1', '校级', '5', '院级') bmlb , ");
        sql.append(" (select count(1) from xg_qgzx_xsgwsqb sq where sq.shzt != '0' and sq.shzt != '3' and sq.gwdm = a.gwdm) sqrs, ");
        sql.append(" (case when a.xn is not null then a.xn else b.xn end) zhxn, b.yrdwmc, b.yrdwdm, (case when a.gwdm is null then '待分配' else b.gwmc end) gwmc, b.gwxzmc, b.xqrs, b.zgrs, b.gwms, b.gwxzdm, b.sfyxgw, b.gwkssj, b.gwjssj ");
        sql.append(" from (select a.sqbh sqbh, a.xn, a.xh xh, a.splc splc, a.sqsj sqsj, a.sqly sqly, a.shzt shzt, a.gwdm gwdm, ");
        sql.append(" decode(a.shzt, '0', '未提交', '1', '通过', '2', '不通过', '3', '已退回', '5', '审核中', a.shzt) shztmc ");
        sql.append(" from xg_qgzx_xsgwsqb a ");
        sql.append(" union all ");
        sql.append(" select b.sqbh sqbh, '' xn, b.xh xh, '' splc, b.sgsj sqsj, '' sqly, '99' shzt, b.gwdm gwdm, '无' shztmc ");
        sql.append(" from xg_qgzx_new_xsgwxxb b ");
        sql.append(" where b.sqbh is null and zgzt = 'zg') a ");
        sql.append(" left join view_xg_qgzx_gwxxb b ");
        sql.append(" on a.gwdm = b.gwdm ");
        sql.append(" left join view_xsxxb c ");
        sql.append(" on a.xh = c.xh ");
        
        sql.append(" left join ZXBZ_XXBMDM w on w.bmdm=b.yrdwdm ");
        sql.append(" where 1 = 1 ");
        sql.append(" and a.shzt <> 9) a ");
        if("10335".equals(Base.xxdm)){
        	sql.append(" left join view_knsjgb_fqxrd t on a.xh = t.xh ");
        }else{
	        sql.append(" left join ( select xh, xn, rddc from ( ");
	        sql.append("  select xh, xn, rddc, row_number() over(partition by xh, xn order by xq desc) rn ");
	        sql.append("  from XG_XSZZ_NEW_KNSJGB t) where rn = 1 ");
	        sql.append(" ) t ");
	        sql.append(" on a.xh = t.xh ");
	        sql.append(" and a.xn = t.xn ");
	        sql.append(" left join xg_xszz_new_kndcdmb t4 ");
	        sql.append(" on t.rddc = t4.dcdm ");
        }
        sql.append(" where (a.yrdwdm = (select szbm from yhb where yhm = 'zf01') or 1 = 1) ");
        sql.append(" )a where 1=1 "); 	
        sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by sqsj desc");
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(XsgwsqForm.class);
		super.setTableName("xg_qgzx_xsgwsqb");
		super.setKey("sqbh");	
	}
	
	@Override
	public XsgwsqForm getModel(XsgwsqForm model) throws Exception {
		String sqly = null;
		String sqsj = null;
		String xh = null;
		String shzt = null;
		String sffcap = null;
		String sfzqscy = null;
		String sqbh=null;
		String gwdm = model.getGwdm();
		if (StringUtils.isNotNull(model.getSqbh())) {
			String[] sqbArr = dao.getOneRs(
					"select sqly,sqsj,sffcap,sfzqscy,xh,shzt,gwdm,sqbh from xg_qgzx_xsgwsqb where sqbh=?",
					new String[] { model.getSqbh() }, new String[] {"sqly", "sqsj", "sffcap", "sfzqscy", "xh", "shzt", "gwdm"});
			sqly = sqbArr[0];
			sqsj = sqbArr[1];
			sffcap = sqbArr[2];
			sfzqscy = sqbArr[3];
			xh = sqbArr[4];
			shzt = sqbArr[5];
			gwdm = sqbArr[6];
			sqbh=model.getSqbh();
			
		}
		if(gwdm != null) {		
			StringBuffer sql = new StringBuffer();
			sql
					.append("select b.xn,b.yrdwmc,b.yrdwdm,b.gwmc,b.gwxzmc,b.xqrs,b.zgrs,b.knsrs knss,b.gwdm,b.gwms,b.bz,b.gwyqryxg,b.gwryyq,b.gwcjsx,b.sfsgwsqsxzmc from view_xg_qgzx_gwxxb b where b.gwdm = ? ");
			model = super
					.getModel(sql.toString(), new String[] { gwdm });
		}
		model.setSqly(sqly);
		model.setSqsj(sqsj);
		model.setXh(xh);
		model.setShzt(shzt);
		model.setSffcap(sffcap);
		model.setSfzqscy(sfzqscy);
		model.setGwdm(gwdm);
		model.setSqbh(sqbh);
		return model;	
	}
	
	
	/**
	 * 
	 * @描述: 勤工明细
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 上午09:57:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getQgmxList(String id){
		
		String sql = "select * from XG_QGZX_QGSQMXB where xh=? order by to_number(px)";
		
		return dao.getListNotOut(sql, new String[]{id});
	}
	
	/**
	 * 
	 * @描述:TODO获取审批流程ID
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 上午10:05:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.xsgwsqsplc splc from xg_qgzx_csszb b ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	/**
	 * 
	 * @描述: 重复验证
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 上午10:11:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getExist(XsgwsqForm model) throws Exception {

		String sql = " select count(1) count from xg_qgzx_xsgwsqb where xh = ? and shzt = '5' ";
		
		String count = dao.getOneRs(sql, new String[]{model.getXh()}, "count");
		
		return Integer.parseInt(count)>0 ? true:false;
	}
	
	/**
	 * 
	 * @描述: 删除勤工明细
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 上午10:25:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delQgmx(String id) throws Exception{
		String sql = "delete from XG_QGZX_QGSQMXB where xh=?";
		return dao.runUpdate(sql, new String[]{id});
	}
	
	/**
	 * 
	 * @描述: 保存勤工明细
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 上午10:27:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveQgmx(List<String[]> params) throws SQLException{
		
		String sql = "insert into xg_qgzx_qgsqmxb(xh,qgrq,qgmx,px) values (?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 
	 * @描述: 获取参数配置
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-6 上午10:17:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCsszb() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.xsxsgws,b.sfkfxsgwsq,b.xsgwsqkssj,b.xsgwsqjssj,b.xsgwsqsplc,b.xsgws,b.RSKZJB,b.qxfw  from xg_qgzx_csszb b ");
		return dao.getMapNotOut(sql.toString(), new String[] {});
	}
	
	/**
	 * 
	 * @描述: 岗位信息查询列表
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:35:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGwPageList(XsgwsqForm model,
			User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String userStatus = user.getUserStatus();
		StringBuilder sql = new StringBuilder();
		sql.append(" select (case when to_number(b.xqrs)<=to_number(b.zgrs) then 'N' else 'Y' end)sfksq,b.xn,b.yrdwmc,b.gwmc,b.gwxzmc,b.xqrs,b.zgrs,b.knsrs knss,b.gwdm,b.sqrs,b.gwyxs from view_xg_qgzx_gwxxb b ");
		sql.append(" left join ZXBZ_XXBMDM a on a.bmdm = b.yrdwdm where b.sfyxgw='是' and 1=1 ");
		// sql.append(searchTjByUser);
		// 如果是学生用户 加入默认条件
		if ("stu".equalsIgnoreCase(userStatus)) {
			sql.append(" and xn = '" + Base.currXn + "'");
		
		} else if ("jd".equalsIgnoreCase(userStatus) 
				|| "bzr".equalsIgnoreCase(userStatus)
				|| "fdy".equalsIgnoreCase(userStatus) 
				|| "xy".equalsIgnoreCase(userStatus)
				){
			sql.append(" and (yrdwdm='");
			sql.append(user.getUserDep());
			sql.append("' or a.bmmc like '%学生处%')");
		}
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);		
	}
	
	/**
	 * 
	 * @描述:获取学生岗位总数
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:36:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwdm
	 * @param xh
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getXszggwsl(String gwdm, String xh) throws SQLException {
		String sql = " select count(1) sl from xg_qgzx_xsgwxxb a where a.gwdm = '"
				+ gwdm + "' and xh='" + xh + "' and a.zgzt='zg' ";
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @描述:学生是否在此岗位
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:37:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwdm
	 * @param xh
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getXssqsl(String gwdm, String xh) throws SQLException {
		String sql = " select count(1) sqsl from xg_qgzx_xsgwsqb a where a.gwdm = '"
				+ gwdm + "' and xh='" + xh + "'  and a.shzt =? ";
		String sl = dao.getOneRs(sql.toString(),
				new String[] { Constants.YW_SHZ }, "sqsl");
		Integer slI = StringUtils.isNull(sl) ? 0 : Integer.parseInt(sl);
		return slI;
	}
	
	/**
	 * 
	 * @描述: 获取审批岗位的顺序
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:38:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @param spgw
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getGwSpXh(String splc, String spgw) {
		StringBuffer sb = new StringBuffer();
		sb.append("select xh from xg_xtwh_spbz where splc=? and spgw=?");
		return dao.getOneRs(sb.toString(), new String[] { splc, spgw }, "xh");
	}
	
	/**
	 * 
	 * @描述:根据岗位代码获取岗位需求人数、困难生数，在岗总人数，在岗困难生数
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:39:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwdm
	 * @return
	 * @throws SQLException
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getGwxx(String gwdm) throws SQLException {
		StringBuilder sql = new StringBuilder();
		if("10335".equals(Base.xxdm)){
			sql.append(" select c.xqrs,c.sfsgwsqsxz,nvl(c.knsrs,0) knsrs,count(distinct a.xh)gwrs,count(distinct b.xh)zgknss  from xg_qgzx_gwxxb c  left join xg_qgzx_xsgwxxb a on c.gwdm = a.gwdm and a.zgzt ='zg' left join view_knsjgb_fqxrd b on a.xh =b.xh where c.gwdm = ?  group by c.xqrs,c.sfsgwsqsxz,c.knsrs");
		}else{
			sql.append(" select c.xqrs,c.sfsgwsqsxz,nvl(c.knsrs,0) knsrs,count(distinct a.xh)gwrs,count(distinct b.xh)zgknss  from xg_qgzx_gwxxb c  left join xg_qgzx_xsgwxxb a on c.gwdm = a.gwdm and a.zgzt ='zg' left join xg_xszz_new_knsjgb b on a.xh =b.xh and c.xn = b.xn where c.gwdm =?    group by c.xqrs,c.sfsgwsqsxz,c.knsrs");
		}
		return dao.getMapNotOut(sql.toString(), new String[] { gwdm });
	}
	
	/**
	 * 
	 * @描述:是否是困难生
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:40:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwdm
	 * @param xh
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int isKns(String gwdm, String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		if("10335".equals(Base.xxdm)){
			sql.append(" select count(1) num from view_knsjgb_fqxrd e where e.xh = ? ");
			params.add(xh);	
		}else{
			sql.append(" select count(1) num from xg_xszz_new_knsjgb e where e.xh = ? ");
			sql.append(" and e.xn = ? ");
			params.add(xh);
			params.add(Base.currXn);
			if ("xq".equals(SQZQ)) {
				sql.append(" and e.xq = ? ");
				params.add(Base.currXq);
			}
		}
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		return Integer.parseInt(num);
	}
	
	/**
	 * 
	 * @描述:获取审核岗位审核通过申请此岗位 的人数
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:42:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @param shgw
	 * @param sqgw
	 * @return
	 * Integer 返回类型 
	 * @throws
	 */
	public Integer getGwShtgRs(String splc, String shgw, String sqgw) {
		// 获取当前审核岗位的序号
		String xh = getGwSpXh(splc, shgw);
		// Integer xhI=StringUtils.paseStringToInteger(xh)-1;
		// 权限审核通过的人数
		StringBuffer sb = new StringBuffer();
		// sb.append("select count(*) rs  from XG_QGZX_XSGWSQB a  left join XG_XTWH_SHZTB b  on b.ywid = a.sqbh  where a.splc=? and b.gwid = ? and a.shgw=? and b.shzt=?");
		// sb.append("select count(1) rs from xg_qgzx_xsgwsqb where shgw =? and gwdm=?");
		sb.append(" select count(1) rs from ( ");
		sb.append("    select a.*,b.xh lcxh,nvl(d.zgzt,'zg') zgzt from xg_qgzx_xsgwsqb a ");
		sb.append("    left join xg_xtwh_spbz b on a.splc=b.splc and a.shgw=b.spgw ");
		sb.append("    left join xg_qgzx_xsgwxxb d on a.xh = d.xh and a.gwdm = d.gwdm ");
		sb.append("    where a.gwdm=? and a.splc=? and a.shzt in ('5','1')) ");
		sb.append(" where lcxh>=? and zgzt = 'zg' ");
		String shtgrs = dao.getOneRs(sb.toString(), new String[] { sqgw, splc,
				xh }, "rs");
		Integer shtgrsI = StringUtils.paseStringToInteger(shtgrs);

		// 申请的岗位已有人数（前面已经有判断学生是否已经在岗，所以不用过滤某学生）
		StringBuffer gwrs = new StringBuffer();
		gwrs.append("select count(1) rs from xg_qgzx_xsgwxxb where gwdm=? and zgzt = ? and sjly=?");
		String sqgwyyrs = dao.getOneRs(gwrs.toString(), new String[] { sqgw,
				"zg", "0" }, "rs");
		Integer sqgwyyrsI = StringUtils.paseStringToInteger(sqgwyyrs);
		return shtgrsI + sqgwyyrsI;
	}
	
	/**
	 * 
	 * @描述:获取学生申请的数量
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:44:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @param xh
	 * @param shgw
	 * @return
	 * Integer 返回类型 
	 * @throws
	 */
	public Integer getTgRs(String splc, String xh, String shgw) {
		// 获取当前审核岗位的序号
		String gwxh = getGwSpXh(splc, shgw);
		// 审核学生通过的人数
		StringBuffer sb = new StringBuffer();
		// sb.append("select count(1) rs from xg_qgzx_xsgwsqb where xh = ? and shgw =?");
		sb.append(" select count(1) rs from (");
		sb.append("    select a.*,b.xh lcxh,nvl(d.zgzt,'zg') zgzt from xg_qgzx_xsgwsqb a ");
		sb.append("    left join xg_xtwh_spbz b on a.splc=b.splc and a.shgw=b.spgw ");
		sb.append("    left join xg_qgzx_gwxxb c on c.gwdm=a.gwdm  ");
		sb.append("    left join xg_qgzx_xsgwxxb d on a.xh = d.xh and a.gwdm = d.gwdm ");
		sb.append("    where c.sfsgwsqsxz='1' and a.xh=? and a.splc=? and a.shzt in ('5','1'))");
		sb.append(" where lcxh>=? and zgzt = 'zg' ");
		String shtgrs = dao.getOneRs(sb.toString(), new String[] { xh, splc,
				gwxh }, "rs");
		// 结果维护学生增加的人数
		StringBuffer sbzg = new StringBuffer();
		sbzg.append("select count(1) rs from xg_qgzx_xsgwxxb c where sfsgwsqsxz='1' and xh = ? and zgzt = ? and sjly=?");
		String jgkZjrs = dao.getOneRs(sbzg.toString(), new String[] { xh, "zg",
				"0" }, "rs");
		Integer tgrsI = StringUtils.isNull(shtgrs) ? 0 : Integer
				.parseInt(shtgrs);
		Integer jgkZjrsI = StringUtils.isNull(jgkZjrs) ? 0 : Integer
				.parseInt(jgkZjrs);
		return tgrsI + jgkZjrsI;
	}
	
	public boolean isHaveRecordForSq(String xh, String xn) {
		String sql = "select count(1) num from xg_qgzx_xsgwsqb where xh = ? and (xn = ? or shzt <> 2)";
		String num = dao.getOneRs(sql, new String[]{xh,xn}, "num");
		return Integer.valueOf(num)>0;	
	}
	
	public HashMap<String,String> getGwxxMap(String gwdm) {
		StringBuilder sql = new StringBuilder();	
		sql.append(" select b.xn,b.yrdwmc,b.yrdwdm,b.gwmc,b.gwxzmc,b.xqrs,b.zgrs,b.knsrs knss,b.gwdm,b.gwms,b.bz,b.gwyqryxg,b.gwryyq,b.gwcjsx,b.sfsgwsqsxzmc from view_xg_qgzx_gwxxb b where b.gwdm = ? ");
		String[] inputValue = {gwdm};
		return dao.getMapNotOut(sql.toString(), inputValue);	
	}
	
}
