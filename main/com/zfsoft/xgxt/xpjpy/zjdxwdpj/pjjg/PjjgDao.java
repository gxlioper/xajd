/**
 * @部门:学工产品1部
 * @日期：2017-3-13 下午01:59:43 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 新评奖评优_我的评奖_评奖结果
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-3-13 下午01:59:43 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjjgDao extends SuperDAOImpl<PjjgForm> {
	/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zjdx_pjpy_pjjgb");
		super.setKey("id");
		super.setClass(PjjgForm.class);
	}

	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	*/

	@Override
	public List<HashMap<String, String>> getPageList(PjjgForm t)
			throws Exception {
	
		
		return null;
		// TODO 自动生成方法存根
	}
	
	/**
	 *  评奖结果查询
	 *  作者： Meng.Wei[工号:1186]
	 *  日期：2017-3-14 下午07:17:27
	 */
	public List<HashMap<String, String>> getPageList(PjjgForm t, User user)
		throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//数据范围
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.id,a.xh,d.xm,d.xb,");
		sql.append("decode(a.cpnj, null, d.nj, a.cpnj) nj,");
		sql.append("decode(a.cpxydm, null, d.xydm, a.cpxydm) xydm,");
		sql.append("decode(a.cpxymc, null, d.xymc, a.cpxymc) xymc,");
		sql.append("decode(a.cpzydm, null, d.zydm, a.cpzydm) zydm,");
		sql.append("decode(a.cpzymc, null, d.zymc, a.cpzymc) zymc,");
		sql.append("decode(a.cpbjdm, null, d.bjdm, a.cpbjdm) bjdm,");
		sql.append("decode(a.cpbjmc, null, d.bjmc, a.cpbjmc) bjmc,");
		sql.append("a.xn,a.xmdm,a.xmmc,a.xmje,a.sqsj,a.sqly,a.ylzd1,a.ylzd2,a.ylzd3,a.ylzd4,a.ylzd5 fjid,");
//		sql.append("decode(a.sjly, '1', '流程数据', '2', '结果增加数据', '0', '导入') sjly,");
		sql.append("a.sjly,");
		sql.append("a.lxdm,c.lxmc,a.xzdm,b.xzmc,a.lylcywid,a.bjdw,d.xz,d.sfzh,d.zd5,d.zzmm,d.zzmmmc,d.mz,");
		/*后加的五个字段*/
		sql.append("a.wysp,a.ssdh,a.gzzw,a.cjkyqk,a.dwrs,");
		sql.append("d.mzmc,d.yhmc,d.yhkh,d.sjhm,d.rxrq,d.jtdzxx,d.jtyb ");
		sql.append("from xg_zjdx_pjpy_pjjgb a ");
		sql.append("left join xg_zjdx_pjpy_xmxz b on b.xzdm = a.xzdm ");
		sql.append("left join xg_zjdx_pjpy_xmlx c on c.lxdm = a.lxdm ");
		sql.append("left join view_xsxxb d on d.xh = a.xh ");
		sql.append(" )t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append("order by xn,sqsj desc ");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 项目类型保存判断
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午07:17:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select lxdm,lxmc from  xg_zjdx_pjpy_xmlx order by lxmc ");
		String[] input = {};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @描述: 项目性质list
	 * @作者：  Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午07:17:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxz() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select xzdm,xzmc from xg_zjdx_pjpy_xmxz order by xzmc ");
		String[] input = {};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @描述: 增加保存判断
	 * @作者：  Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午07:16:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForAddSave(PjjgForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_zjdx_pjpy_pjjgb where xh = ? and xn = ? and xmmc = ? ");
		String[] inputV = new String[] { model.getXh(),model.getXn(), model.getXmmc().trim()};
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}
	
	/**
	 * @描述: 修改保存判断
	 * @作者：  Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午07:16:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForUpdateSave(PjjgForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_zjdx_pjpy_pjjgb where xh = ? and xn = ? and xmmc = ? and id <> ? ");
		String[] inputV = new String[] { model.getXh(),model.getXn(), model.getXmmc().trim(), model.getId()};
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}
	
	/**
	 * @描述: 学生基本信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午07:14:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getPjjgInfo(String xh,String xn){
		List<String> params = new ArrayList<String>();
		params.add(xh);
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.XH,a.XM,a.XB,a.XZ,a.XJZTM,a.SFZH,a.rxrq,a.ZZMM,a.ZZMMMC,a.MZ,a.MZMC,a.DZYX,a.SJHM,a.jtdzxx,a.jtyb, ");
		sql.append(" nvl(c.CPXYDM, a.XYDM) XYDM,nvl(c.CPXYMC, a.XYMC) XYMC,nvl(c.CPZYDM, a.ZYDM) ZYDM,nvl(c.CPZYMC, a.ZYMC) ZYMC, ");
		sql.append(" nvl(c.CPBJDM, a.BJDM) BJDM,nvl(c.CPBJMC, a.BJMC) BJMC,nvl(c.CPNJ, a.NJ) NJ ");
		sql.append(" from view_xsbfxx a ");
		sql.append(" left join (select * ");
		sql.append(" from (select a.*, ");
		sql.append(" row_number() over(partition by xh, xn order by sqsj desc) rn ");
		sql.append(" from xg_zjdx_pjpy_pjjgb a ");
		sql.append(" where xn = (select xn from xg_zjdx_pjpy_csszb)) ");
		sql.append(" where rn = 1) c ");
		sql.append(" on a.xh = c.xh ");
		sql.append(" where rownum = 1 ");
		sql.append(" and a.xh = ? ");
		if(!StringUtil.isNull(xn)){
			params.add(xn);
			sql.append(" and c.xn = ? ");
		}
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	/**
	 * @描述: 评奖结果信息单个查看
	 * @作者：  Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午07:15:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOnePjjgList(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xn,a.xmmc,a.xmje,a.sqsj,a.sqly,a.cpnj,a.cpxymc,a.cpzymc,a.cpbjmc,a.cpxydm,a.cpzydm,a.cpbjdm,a.ylzd1,a.ylzd5, ");
		sql.append(" a.wysp,a.ssdh,a.gzzw, a.grxxjl,a.cjkyqk,a.dwrs, ");
		sql.append(" (select xzmc from xg_zjdx_pjpy_xmxz b where a.xzdm = b.xzdm) xzmc, ");
		sql.append(" (select lxmc from xg_zjdx_pjpy_xmlx c where a.lxdm = c.lxdm) lxmc ");
		sql.append(" from xg_zjdx_pjpy_pjjgb a ");
		sql.append(" where id = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { id });
	}
	
	/**
	 * @描述: 增加学生用
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-22 上午11:19:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjXs(PjjgForm model, User user)
		throws Exception {
		/*生成高级查询相关条件、条件值*/
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from ( ");
		sql.append("select t1.XH,t1.XM,t1.XB, ");
		sql.append("nvl(t5.XYDM, t1.XYDM) XYDM,nvl(t5.XYMC, t1.XYMC) XYMC,nvl(t5.ZYDM, t1.ZYDM) ZYDM,nvl(t5.ZYMC, t1.ZYMC) ZYMC,nvl(t5.bjdm, t1.bjdm) bjdm,nvl(t5.BJMC, t1.BJMC) BJMC,nvl(t5.NJ, t1.NJ) NJ,");
		sql.append("t1.SFZH,t1.LXDH,t1.XZ,t1.SJHM,t1.RXRQ,t1.YHKH,t1.YHDM,t2.YHMC,t1.CSRQ,t1.MZ,t1.MZMC,t1.ZZMM,t1.ZZMMMC  ");
		sql.append("from view_xsbfxx t1 ");
		sql.append("left join dmk_yh t2 on t1.yhdm = t2.yhdm ");
		sql.append("join (select t3.xh, t4.* from xg_zjdx_pjpy_cpmdb t3 ");
		sql.append("left join view_njxyzybj_all t4 on t3.bjdm = t4.bjdm ");
		sql.append("where xn in (select xn from xg_zjdx_pjpy_csszb)) t5 ");
		sql.append("on t1.xh = t5.xh where t5.bjdm is not null ");
		sql.append(") a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 回滚结果表的申请记录
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-26 下午03:44:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delJgb(String ywid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_zjdx_pjpy_pjjgb where sjly='1' and lylcywid = ?");
		return dao.runDelete(sql.toString(), new String[]{ywid});
	}
	
	/**
	 * @描述: 按学院查询指定周期项目的获得名额
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-6-5 下午02:00:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZzmeByXy(String xmdm, String xn) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) tgrs,t3.xydm from xg_zjdx_pjpy_pjjgb t1 ");
		sql.append("left join xg_zjdx_pjpy_cpmdb t2 on t1.xh=t2.xh and t1.xn = t2.xn ");
		sql.append("left join view_njxyzybj_all t3 on t2.bjdm = t3.bjdm ");
		sql.append("where t1.xn = ? and t1.xmdm = ?");
		sql.append("group by t3.xydm");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xmdm});
	}
	
	/**
	 * @描述: 按全校查询指定周期项目的获得名额
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-5 下午02:01:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZzmeByQx(String xmdm, String xn) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) tgrs from xg_zjdx_pjpy_pjjgb t1 ");
		sql.append("where t1.xn = ? and t1.xmdm = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xmdm});
	}

	/**
	 *  调用生成优秀学生的存储过程.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-01-04 15:26
	 * @param proName
	 * @return boolean
	 * @throw Exception
	 */
	public boolean scyxxs(String proName) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("call ");
		sql.append(proName);
		sql.append("()");
		return dao.runProcuder(sql.toString(), new String[]{});
	}

	/**
	 * @描述: 获取平均成绩
	 * @日期：2018-1-3 下午05:23:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * Map<String,String> 返回类型
	 * @throws
	 */
	public Map<String, String> getBjgcjNum(String xh,String xn,String xq){
		StringBuilder sql =  new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(xh);
		sql.append("select count(*) num from view_zhhcjb  where cj <60 and xh=?");

		if(StringUtils.isNotNull(xn)){
			sql.append(" and xn = ?");
			params.add(xn);
		}

		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ?");
			params.add(xq);
		}
		return dao.getMap(sql.toString(), params.toArray(new String[params.size()]), new String[]{"num"});
	}

	/**
	 * @描述: 取评奖结果最新4条数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-3 下午05:36:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getHznydxPjpyMap(String xh){
		StringBuilder sql =  new StringBuilder();
		sql.append(" select sqsjs,xmmc,bjdw from ( select t1.bjdw,(select xzmc from xg_zjdx_pjpy_xmxz b where t1.xzdm = b.xzdm) xmxzmc,t1.xn,t2.xqmc,t1.xmmc,t1.xmje,t1.sqsj , to_char(to_date(t1.sqsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyy-MM-dd') sqsjs ");
		sql.append("from xg_zjdx_pjpy_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.xn desc,t1.xq desc ) where ROWNUM <= 4 ");
		return dao.getArrayList(sql.toString(), new String[]{xh}, new String[]{"sqsjs","xmmc","bjdw"});
	}

	/**
	 * @描述: 评奖结果数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-4 上午11:12:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkByXhMap(String xh){
		StringBuilder sql =  new StringBuilder();
		sql.append("select t1.bjdw,(select xzmc from xg_zjdx_pjpy_xmxz b where t1.xzdm = b.xzdm) xmxzmc,t1.xn,t2.xqmc,t1.xmmc,t1.xmje,t1.sqsj , to_char(to_date(t1.sqsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyy-MM-dd') sqsjs ");
		sql.append("from xg_zjdx_pjpy_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq = t2.xqdm where t1.xh = ? order by t1.xn desc,t1.xq desc");
		return dao.getArrayList(sql.toString(), new String[]{xh}, new String[]{"xn","xqmc","xmmc","xmje","bjdw","xmxzmc","sqsj","sqsjs"});
	}
	
	/**
	 * @描述: 取学生最近一条申请信息，主要是取
	 *【外语水平、宿舍电话、担任社会工作职务、个人学习经历、参加科研情况、对设奖单位的认识】
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-31 下午05:04:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getLatestSqxx(String xh) throws Exception{
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.* from xg_zjdx_pjpy_xmsq t1 ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t2 on t1.xmdm = t2.xmdm ");
		sql.append("left join xg_zjdx_pjpy_xmlx t3 on t2.lxdm = t3.lxdm ");
		sql.append("where t1.xh = ? ");
		sql.append("order by t1.sqsj desc ");
		sql.append(") where rownum = 1");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
}
