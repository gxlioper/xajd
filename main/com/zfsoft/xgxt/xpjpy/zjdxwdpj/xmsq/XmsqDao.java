/**
 * @部门:学工产品(1)部
 * @日期：2017-5-12 下午05:00:51 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖_奖项申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-5-12 下午05:00:34 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmsqDao extends SuperDAOImpl<XmsqForm>{

	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	*/
	@Override
	protected void setTableInfo() {
		/*主表为项目申请表*/
		super.setTableName("xg_zjdx_pjpy_xmsq");
		super.setKey("id");
		super.setClass(XmsqForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XmsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XmsqForm t, User user)
			throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//数据范围
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select t1.*,");
		sql.append("                      t5.xm,");
		sql.append("                      nvl(t3.nj, t5.nj) nj,");
		sql.append("                      nvl(t3.xydm, t5.xydm) xydm,");
		sql.append("                      nvl(t3.xymc, t5.xymc) xymc,");
		sql.append("                      nvl(t3.zydm, t5.zydm) zydm,");
		sql.append("                      nvl(t3.zymc, t5.zymc) zymc,");
		sql.append("                      nvl(t3.bjdm, t5.bjdm) bjdm,");
		sql.append("                      nvl(t3.bjmc, t5.bjmc) bjmc,");
		sql.append("                      case when t4.sqkg = 1 and sysdate between to_date(nvl(t4.sqkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi')");
		sql.append("                        and to_date(nvl(t4.sqjssj, '2020-01-01 00:00'), 'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end isopen,");
		sql.append("                      t4.xmmc,t4.lxdm,t4.xmje,t4.xssx,t4.xmsm,t6.lxmc,");
		sql.append("                      decode(t1.shzt, '0', '未提交', '1', '通过', '2', '不通过', '3', '已退回', '5', '审核中', t1.shzt) shztmc");
		sql.append("                from xg_zjdx_pjpy_xmsq t1");
		sql.append("                left join xg_zjdx_pjpy_cpmdb t2 on t1.xh = t2.xh and t1.xn = t2.xn");
		sql.append("                left join view_njxyzybj_all t3 on t2.bjdm = t3.bjdm");
		sql.append("                left join xg_zjdx_pjpy_pjxmb t4 on t1.xmdm = t4.xmdm");
		sql.append("                left join view_xsbfxx t5 on t1.xh = t5.xh");
		sql.append("                left join xg_zjdx_pjpy_xmlx t6 on t4.lxdm = t6.lxdm");
		sql.append(")a where 1 = 1 and lxmc != '荣誉称号'");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	/**
	 * @描述: 开放申请且没有申请过的奖项
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-15 下午08:27:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getKsqInfoList(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		XmwhService xmwhService = new XmwhService();
		sql.append("select t1.* from xg_zjdx_pjpy_pjxmb t1 ");
		sql.append("left join xg_zjdx_pjpy_xmlx t2 on t1.lxdm = t2.lxdm ");
		sql.append("where not exists (select 1 from xg_zjdx_pjpy_xmsq t3 where t1.xmdm = t3.xmdm and t3.xh = ? and t3.shzt != '3') ");
		sql.append("and xn = '"+xmwhService.getCsszMap().get("xn")+"' ");
		sql.append("and t1.sqkg = '1' ");
		sql.append("and( sysdate between to_date(nvl(t1.sqkssj, '2018-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and to_date(nvl(t1.sqjssj, '2038-01-01 00:00'), 'yyyy-mm-dd hh24:mi') ) ");
		String sql_sqlx = " and t2.lxmc != '荣誉称号' ";
		sql.append(sql_sqlx);
		sql.append("order by to_number(t1.xssx) ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * @描述: 本周期已申请的项目
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 上午09:38:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getYsqInfoList(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		XmwhService xmwhService = new XmwhService();
		sql.append("select t1.* from xg_zjdx_pjpy_pjxmb t1 ");
		sql.append("left join xg_zjdx_pjpy_xmlx t2 on t1.lxdm = t2.lxdm ");
		sql.append("where exists (select 1 from xg_zjdx_pjpy_xmsq t3 where t1.xmdm = t3.xmdm and t3.xh = ? and t3.shzt != '3') ");
		sql.append("and xn = '"+xmwhService.getCsszMap().get("xn")+"' ");
		String sql_sqlx = " and t2.lxmc != '荣誉称号' ";
		sql.append(sql_sqlx);
		sql.append("order by to_number(t1.xssx) ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * @描述: 根据xmdm返回不可兼得项目
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 上午11:20:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjdxm(String xmdm) throws Exception{
		if(StringUtil.isNull(xmdm)){
			throw new SystemException("查询参数为空！");
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.xmdm,t2.xmmc,t2.xmje from (");
		sql.append(" select bjdxmdm xmdm from xg_zjdx_pjpy_jdszb where xmdm = ?");
		sql.append(" union ");
		sql.append(" select xmdm from xg_zjdx_pjpy_jdszb where bjdxmdm = ?");
		sql.append(" ) t1 left join xg_zjdx_pjpy_pjxmb t2 on t1.xmdm = t2.xmdm");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm,xmdm});
	}
	
	/**
	 * @描述: 批量获取奖项信息、学生信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 下午04:04:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	/*查询学生学号和学院代码*/
	public HashMap<String,String> getPjxmXsxxMap(XmsqForm t) throws Exception {
	    StringBuilder sql = new StringBuilder();
	    sql.append("select t2.xh,t4.xydm from xg_zjdx_pjpy_cpmdb t2 ");
	    sql.append("left join view_njxyzybj_all t4 on t2.bjdm = t4.bjdm ");
	    sql.append("where t2.xh = ? and t2.xn = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getXh(),t.getXn()});
	}
	/*查询奖学金金额上限*/
	public List<String> getXzjx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_jesxxmb ");
		return dao.getList(sql.toString(), new String[]{}, "xmmc");
	}
	/*已申请人数*/
	public String getYsqXs(String xmxx) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select nvl(count(1), 0) ysqrs ");
		sql.append("from (select t1.xmdm, t4.xydm, t3.xmmc, t2.xh ");
		sql.append("from xg_zjdx_pjpy_xmsq t1 ");
		sql.append("left join xg_zjdx_pjpy_cpmdb t2 on t1.xh = t2.xh and t1.xn = t2.xn ");
		sql.append("left join view_njxyzybj_all t4 on t2.bjdm = t4.bjdm ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t3 on t1.xmdm = t3.xmdm ");
		sql.append("where t1.shzt in ('1', '5')) a where a.xmdm||a.xydm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xmxx}, "ysqrs");
	}
	/*项目人数设置表里的总人数*/
	public String getPjxmRsxxsx(String xmxx) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select to_number(nvl(t1.zzme,0))zzme from xg_pjpy_new_rsszb t1 ");
		sql.append("where t1.xmdm||t1.bmdm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xmxx}, "zzme");
	}
	/*获得评奖项目信息*/
	public List<HashMap<String,String>> getPjxmXsxxList(String[] sqidArr) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.id, t1.xmdm, t2.xm, t2.xh, t3.xmmc, t3.shlc from xg_zjdx_pjpy_xmsq t1 ");
		sql.append("left join xsxxb t2 on t1.xh = t2.xh ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t3 on t1.xmdm = t3.xmdm ");
		sql.append("where ");
		for (int i = 0; i < sqidArr.length; i++) {
			sql.append(" t1.id = ? ");
			if(i < sqidArr.length - 1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), sqidArr);
	}
	/*提交项目人数信息*/
	public List<HashMap<String,String>> getPjxmRsxx(String[] sqidArr) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xmdm, t4.xydm, t3.xmmc, count(1) xmtjrs ");
		sql.append("from xg_zjdx_pjpy_xmsq t1 ");
		sql.append("left join xg_zjdx_pjpy_cpmdb t2 on t1.xh = t2.xh and t1.xn = t2.xn ");
		sql.append("left join view_njxyzybj_all t4 on t2.bjdm = t4.bjdm ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t3 on t1.xmdm = t3.xmdm ");
		sql.append("where ");
		for (int i = 0; i < sqidArr.length; i++) {
			sql.append("t1.id = ? ");
			if(i < sqidArr.length - 1){
				sql.append("or ");
			}
		}
		sql.append("group by t1.xmdm,t4.xydm,t3.xmmc ");
		return dao.getListNotOut(sql.toString(), sqidArr);
	}
	
	/**
	 * @描述: 判断是否不可兼得
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 下午05:35:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotbkjd(String xmdm,String xn,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num");
		sql.append(" from xg_zjdx_pjpy_xmsq t");
		sql.append(" where xmdm in (select bjdxmdm from xg_zjdx_pjpy_jdszb where xmdm = ?)");
		sql.append(" and t.xn = ?");
		sql.append(" and t.xh = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{xmdm,xn,xh}, "num");
		int sl = Integer.parseInt(num);
		return sl>0 ? false : true;
	}
	
	
	/**
	 * @描述: 获取不可兼得项目名称
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 下午05:38:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getbkjdMc(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select replace(wm_concat(xmmc), ';', ',') bkjdmc");
		sql.append(" from xg_zjdx_pjpy_pjxmb");
		sql.append(" where xmdm in (select bjdxmdm");
		sql.append(" from xg_zjdx_pjpy_jdszb");
		sql.append(" where xmdm = ?)");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "bkjdmc");
	}
	
	/**
	 * @描述: 评奖信息单独查询
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-19 上午08:41:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getPjInfo(String xh,String xn){
		List<String> params = new ArrayList<String>();
		params.add(xh);
		StringBuffer sql = new StringBuffer();
		sql.append("select a.XH,a.XM,a.XB,nvl(c.XYDM, a.XYDM) XYDM,nvl(c.XYMC, a.XYMC) XYMC,nvl(c.ZYDM, a.ZYDM) ZYDM,nvl(c.ZYMC, a.ZYMC) ZYMC,");
		sql.append("case when c.bjmc is null then a.bjdm else c.bjdm end bjdm, nvl(c.BJMC, a.BJMC) BJMC,nvl(c.NJ, a.NJ) NJ, ");
		sql.append("a.SFZH,a.LXDH,a.XZ,a.DZYX,a.LXDZXX,a.SJHM,a.MM,a.SSBHZD,a.SSCHZD,a.SSBH,a.QSDH,a.SSCH,a.ZSRQ,a.ZSJZRQ, ");
		sql.append("a.SFXYQGZX,a.XGYX,a.YHKH,a.YKTH,a.XMPY,a.SYD,a.CSRQ,a.MZ,a.MZMC,a.ZZMM,a.ZZMMMC,a.CYM,a.SG,a.TZ,a.TC, ");
		sql.append("a.KSLB,a.RXFS,a.PYFS,a.PYCC,a.BZ,a.XSZP,a.WHCD,a.RXQDW,a.JTDH,a.JRGQTSJ,a.JRGCDSJ,a.JTCYGC,a.JG,a.JLCFJL, ");
		sql.append("a.JTDZ,a.JTYB,a.XX,a.JKZKDM,a.JKZK,a.AH,a.SFDK,a.JTJJQK,a.SHGXXM1,a.SHGXGX1,a.SHGXGZDW1,a.SHGXZW1,a.SHGXDWDH1, ");
		sql.append("a.SHGXSJHM1,a.SHGXXM2,a.SHGXGX2,a.SHGXGZDW2,a.SHGXZW2,a.SHGXDWDH2,a.SHGXSJHM2,a.JTQKJJ,a.BYXX,a.KH,a.RXRQ, ");
		sql.append("a.FDYXM,a.GKCJ,a.QQHM,a.HKXZ,a.HKSZD,a.ZYJB,a.SSYQ,a.SSYQMC,a.SSLD,a.JTDZS,a.JTDZX,a.SFZSB,a.SFZFX,a.ZJDM, ");
		sql.append("a.ZJMC,a.SFYBY,a.BYNY,a.ZW,a.XXFX,a.THBS,a.DYBJ,a.SHBJ,a.XZXM,a.XXSZD,a.XW,a.XWZSBH,a.XWZSXLH,a.BJYJL,a.ZSBH, ");
		sql.append("a.ZSXLH,a.ZYFX,a.ZYLB,a.FXZY,a.FXZYFX,a.BXXS,a.BXLX,a.XXXS,a.CSD,a.PYFX,a.DQSZJ,a.ZSJJ,a.KSH,a.ZSLB,a.GJ, ");
		sql.append("a.SFJH,a.CCQJ,a.BYZFFZTDM,a.BYZFFZTMC,a.XWZSXXDZ,a.JGS,a.JGSHI,a.JGX,a.RXNJ,a.NFBY,a.SFZC,a.DASFYL,a.DAYLYY, ");
		sql.append("a.YXDM,a.SFZZ,a.SFSF,a.SFDL,a.DXHWP,a.BYSJ,a.ZXWYYZDM,a.WYDJ,a.JSJDJ,a.YZBM,a.SHZW,a.JYPX,a.XMSJ,a.ZGZS, ");
		sql.append("a.LXDZ,a.JLJN,a.SYBZ1,a.SYBZ2,a.SYBZ3,a.XLDM,a.ZKZH,a.SFCJ,a.GRJL,a.XSLB,a.XSLBMC,a.XSLX,a.XSLXMC,a.SFBYS, ");
		sql.append("a.YHDM,a.YHMC,a.DAH,a.YLBXH,a.RXQDWDH,a.RXQDWDZ,a.RXQDWYB,a.GZBX,a.SFGAT,a.SFSSMZ,a.SFZD,a.HKSHEN,a.HKSHI, ");
		sql.append("a.HKXIAN,a.ZCSXHM,a.RXQWHCD,a.TBSJ,a.BXXZ,a.SFTB,a.SFYQRZS,a.QTYY,a.SFSFS,a.BYZH,a.SYDS,a.XJH,a.JRZZMMRQ,a.SFHQ, ");
		sql.append("a.CSDS,a.CSDSHI,a.CSDXIAN,a.ZD1,a.ZD2,a.ZD3,a.ZD4,a.ZD5,a.ZD6,a.SYDMC,a.YXMC,a.XWMC,a.ZXWYYZMC,a.XLMC,a.ZSLBMC, ");
		sql.append("a.PYFSMC,a.SFZBLX,a.XJLBDM,a.XJLB,a.XJLBMC,a.XJZTM,a.SFZX,a.ZYFXMC,a.SYDSMC,a.YDLBM,a.YDLBMC,a.SYDQMC,a.JTDZXX,a.JGMC,");
		sql.append("a.HKSZDMC,a.CSDMC from view_xsxxb a left join xg_zjdx_pjpy_cpmdb b on a.xh = b.xh left join view_njxyzybj_all c ");
		sql.append("on b.bjdm = c.bjdm where rownum =1 and a.xh = ? ");
		if(StringUtils.isNotNull(xn)){
			params.add(xn);
			sql.append(" and b.xn = ? ");
		}
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * @描述: 根据学号查询学生参评基本信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-19 上午08:56:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCpbjListByXh(String xh, String xn){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xn,a.xh,a.bjdm,b.bjmc,b.zydm,b.zymc,b.xydm,b.xymc,b.nj from xg_zjdx_pjpy_cpmdb a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm ");
		sql.append(" where a.xh = ? and a.xn = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xn});
	}
	
	/**
	 * @描述: 查询申请记录所对应审核记录列表
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-19 上午09:47:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getSpjlList(String id){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.guid guid,t1.ywid shid,t1.shzt,t1.shsj,t1.shr,t1.shyj,t2.xmdm,t2.xmmc,t3.xm,t4.mc gwmc,");
		sql.append(" decode(t1.shzt,'0', '未审核','1','通过','2','不通过','3','退回','4','需重审') shztmc from xg_xtwh_shztb t1");
		sql.append(" left join xg_zjdx_pjpy_pjxmb t2 on t1.zd2 = t2.xmdm left join yhb t3 on t1.shr = t3.yhm");
		sql.append(" left join xg_xtwh_spgw t4 on t1.gwid = t4.id where t1.ywid = ? order by shsj asc");
		return dao.getListNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * @描述: 按项目查询审核表中的记录数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-1 下午04:45:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getFlowData(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) count from xg_zjdx_pjpy_xmsq where xmdm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "count");
	}

	
	/**
	 * 调getmodel方法
	 */
	public XmsqForm getModel(XmsqForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xmmc,t2.lxdm,t2.xzdm,t2.xmje from xg_zjdx_pjpy_xmsq t1 ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t2 on t1.xmdm = t2.xmdm ");
		sql.append("where t1.id = ?");
		return super.getModel(t, sql.toString(), new String[]{t.getId()});
	}


	/**
	 * @描述: 取学生最近一条申请信息，主要是取外语水平、宿舍电话、担任社会工作职务、个人学习经历、参加科研情况、对设奖单位的认识
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-12 下午07:43:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getLatestSqxx(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.* from xg_zjdx_pjpy_xmsq t1 ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t2 on t1.xmdm = t2.xmdm ");
		sql.append("left join xg_zjdx_pjpy_xmlx t3 on t2.lxdm = t3.lxdm ");
		sql.append("where t1.xh = ? ");
		sql.append("and t3.lxmc != '荣誉称号' ");
		sql.append("order by t1.sqsj desc ");
		sql.append(") where rownum = 1");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}

	/**
	 *  根据年级，专业代码查询年级专业人数.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-12-28 19:49
	 * @param nj
	 * @param zydm
	 * @return java.lang.String
	 * @throw
	 */
	public String getNjzyrs(String nj, String zydm) {
		String sql = "SELECT count(1) count FROM VIEW_XSJBXX WHERE NJ = ? AND zydm = ?";
		return dao.getOneRs(sql,new String[]{nj,zydm},"count");
	}
	
	/**
	 * @描述: 根据班级代码和学年查询当前班级人数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-4 上午09:34:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjrs(String bjdm, String xn) {
		String sql = " select count(1) bjrs from xg_zjdx_pjpy_cpmdb where bjdm = ? and xn = ?";
		return dao.getOneRs(sql, new String[]{bjdm,xn}, "bjrs");
	}
	
	/**
	 * @描述: 取审核信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-4 上午09:48:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSpxxInfo(String guid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, b.shyj ejshyj,b.shzt ejshzt,b.zd2 ejtjdcdm ,b.zd3 ertjdcmc, c.shyj sjshyj,c.shzt sjshzt,c.zd2 sjtjdcdm,c.zd3 sjtjdcmc from(select a.* from(select a.ywid,a.shyj yjshyj,a.shzt yjshzt,a.zd2 yjtjdc,row_number() over(partition by a.ywid order by a.shsj desc) rn from xg_xtwh_shztb a   where a.ywid=?)a where a.rn=3)a left join");
		sql.append("  (select b.* ,row_number() over(partition by b.ywid order by b.shsj desc) rn from xg_xtwh_shztb b  where b.ywid=?)b on a.ywid=b.ywid and b.rn=2");
		sql.append(" left join (select  c.*,row_number() over(partition by c.ywid order by c.shsj desc) rn from xg_xtwh_shztb c   where c.ywid=?)c on a.ywid=c.ywid and c.rn=1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{guid,guid,guid});
	}
}
