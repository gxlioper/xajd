/**
 * @部门:学工产品事业部
 * @日期：2013-4-20 下午01:13:54 
 */  
package com.zfsoft.xgxt.xszz.knsjg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.Globals;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 困难生结果增加，删除，修改
 * @作者： Administrator 
 * @时间： 2013-4-20 下午01:13:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KnsjgDao extends SuperDAOImplExtend<KnsjgForm>{
	
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	
	@Override
	public List<HashMap<String, String>> getPageList(KnsjgForm t)
			throws Exception {
		return null;
	}
	
	
	/**
	 * 使用高级查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(KnsjgForm t, User user)
			throws Exception {

		//生成高级查询相关条件、条件值 
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder("");
		/*sql.append("select * from (  ");
		sql.append(" select t1.*,t2.xm,t2.xb,t2.nj,t2.xymc,t2.zymc,t2.bjmc,t2.zzmmmc,t2.mzmc,t2.yhmc,t2.yhkh,t2.sfzh,t3.xqmc,t4.dcmc,t2.xydm,t2.zydm,t2.bjdm ");
		sql.append(" from xg_xszz_new_knsjgb t1  ");
		sql.append(" left join view_xsxxb t2 on t1.xh=t2.xh  ");
		sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm  ");
		sql.append(" left join xg_xszz_new_kndcdmb t4 on t1.rddc=t4.dcdm  ");
		sql.append(" ) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);*/
		/*sql.append("select t.* from ( ");
		sql.append("select a.*, b.jtdz,b.jtdh ,b.jtrs , b.sfgc , " +
				"decode(b.sfgc , '1' , '是' ,'0' , '否' , '') sfgcmc ,  b.sfdq , " +
				"decode(b.sfdq , '1' , '是' ,'0' , '否' , '') sfdqmc , b.lszn ," +
				"decode(b.lszn , '1' , '是' ,'0' , '否' , '') lsznmc , b.jtrjsr,  " +
				"rownum xl from VIEW_NEW_DC_XSZZ_KNSRDCX a left join xg_xszz_new_jtqkdcb b on a.XH = b.xh ");
		sql.append(") t where 1=1 ");*/
		
		String view = "VIEW_NEW_DC_XSZZ_KNSRDCX";
		if("10052".equals(Base.xxdm)){
			view = "VIEW_NEW_DC_XSZZ_KNSRDCX_10052";
		}
		//浙江传媒
		else if(Globals.XXDM_ZJCMXY.equals(Base.xxdm)){
			view="view_new_dc_xszz_knsrdcx_zjcm";
		}
		//陕西师范
		else if(Globals.XXDM_SXSFDX.equals(Base.xxdm)){
			view="view_new_dc_xszz_knsrdcx_sxsf";
		}
		//浙江经济职业技术学院
		else if(Globals.XXDM_ZJJJZYJSXY.equals(Base.xxdm)){
			view="view_new_dc_xszz_knsrdcx_zjjj";
		}
		//江西航空个性化
		else if("13871".equals(Base.xxdm)){
			view="view_new_dc_xszz_knsrdcx_jxhk";
		}
		//浙江大学
		else if(Globals.XXDM_ZJDX.equals(Base.xxdm)){
			StringBuilder sqlstr = new StringBuilder();
			sqlstr.append(" (select *");
			sqlstr.append(" from (select a.*,b.zzxx,");
			sqlstr.append(" row_number() over(partition by a.xh order by xn desc, xq desc) rn");
			sqlstr.append(" from VIEW_NEW_DC_XSZZ_KNSRDCX a");
			sqlstr.append(" left join (");
			sqlstr.append(" select xh, wm_concat(zzxx) zzxx");
			sqlstr.append(" from (select xh,");
			sqlstr.append(" xn || decode(xq, '01', '秋冬', '02', '春夏', '') || ',' || xmmc || ',' ||");
			sqlstr.append(" sum(to_number(je)) || '元' zzxx");
			sqlstr.append(" from xg_xszz_new_zzxmjgb");
			sqlstr.append(" where ( regexp_replace(je,'^[-\\+]?\\d+(\\.\\d+)?$','') is  null and je is not null)");
			sqlstr.append(" group by xh, xn, xq, xmmc");
			sqlstr.append(" order by xh, xn, xq, xmmc)");
			sqlstr.append(" group by xh");
			sqlstr.append(" ) b on a.xh = b.xh");
			sqlstr.append(" )");
			sqlstr.append(" where rn = 1");
			sqlstr.append(" and sfzx = '在校'");
			sqlstr.append(" and nvl(sfqxrd, '0') <> '1'");
			sqlstr.append(" )");
			sqlstr.append(" ");
			sqlstr.append(" ");
			view=sqlstr.toString();//"(select * from (select a.*,row_number()over(partition by xh order by xn desc,xq desc) rn from VIEW_NEW_DC_XSZZ_KNSRDCX a) where rn = 1 and sfzx = '在校' and nvl(sfqxrd,'0')<>'1' )";
		}
		
		//河北建材
		else if(Globals.XXDM_HBJCZYJSXY.equals(Base.xxdm)){
			StringBuilder sqlstr = new StringBuilder();
			sqlstr.append(" (select *");
			sqlstr.append(" from (select a.*,(a.xn||' '||(SELECT xqmc FROM xqdzb WHERE xqdm = a.xq)) rdsj,t.* ");
			sqlstr.append(" from VIEW_NEW_DC_XSZZ_KNSRDCX a");
			sqlstr.append(" left join (SELECT yjshyj,ejshyj,t1.ywid FROM");
			sqlstr.append(" (SELECT yjshyj,ywid FROM");
			sqlstr.append(" (SELECT ywid,shyj yjshyj,row_number() over(partition by ywid order by shsj) rn");
			sqlstr.append(" FROM xg_xtwh_shztb");
			sqlstr.append(" WHERE shzt = '1') a");
			sqlstr.append(" WHERE a.rn = 1) t1");
			sqlstr.append(" LEFT JOIN");
			sqlstr.append(" (SELECT ejshyj,ywid FROM");
			sqlstr.append(" (SELECT ywid,shyj ejshyj,row_number() over(partition by ywid order by shsj) rn");
			sqlstr.append(" FROM xg_xtwh_shztb");
			sqlstr.append("  WHERE shzt = '1') b");
			sqlstr.append(" WHERE b.rn = 2) t2");
			sqlstr.append(" ON t1.ywid = t2.ywid) t");
			sqlstr.append(" ON t.ywid = a.lylcywid)");
			sqlstr.append(" ) ");
			
			view=sqlstr.toString();
		}
		//杭州师范大学个性化
		else if("10346".equals(Base.xxdm)){
			view="view_new_dc_xszz_knsrdcx_10346";
		}
		
		sql.append("select a.*,t2.sydm,t3.symc, rownum xl from "+view+" a ");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t2.bjdm=a.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t3 on t3.sydm = t2.sydm ");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		//非江西航空排序
		if(!"13871".equals(Base.xxdm)){
			sql.append(" order by sqsj desc");
		}else{
			//江西航空排序
			sql.append(" order by sqsj desc,xydm,bjdm,knpx");
		}
		
		return getPageList(t, sql.toString(), inputV);
	}
		
	/**
	 * 
	 * @描述:增加操作唯一性判断（学号，学年，学期）
	 * @作者：maxh
	 * @日期：2013-4-23 上午09:18:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public String checkExistForSave(KnsjgForm model) {
		StringBuilder sql = new StringBuilder("select count(1) num from xg_xszz_new_knsjgb where xh=? and xn=? and xq=? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXh(),model.getXn(),model.getXq()}, "num");
		return num;
		
	}
	/**
	 * 
	 * @描述:修改操作唯一性判断（学号，学年，学期）
	 * @作者：maxh
	 * @日期：2013-4-23 上午09:18:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public String checkExistForUpdate(KnsjgForm model) {
		String sql="select count(1) num from xg_xszz_new_knsjgb where xh=? and xn=? and xq=?  and guid<>?";
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXh(),model.getXn(),model.getXq(),model.getGuid()}, "num");
		return num;
	
	}
	
	/**
	 * 
	 * @描述:获得学生认定信息
	 * @作者：maxh
	 * @日期：2013-4-23 下午03:07:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param wjsj
	 * @param request
	 * @return
	 * List<String[]> 返回类型 
	 * @throws
	 */
	 
	public Map<String, String> getOneKnsjgList(String  guid) {
		
		StringBuilder sql = null;
		//
		if("13871".equals(Base.xxdm)){
			sql = new StringBuilder("select dcmc,sqly,sqsj,ylzd2,ylzd3,xqmc,xn,yymc,ylzd5,sqlydm,knpx from xg_view_xszz_new_knsjgb_jxhk where guid=? ");
		}
		//杭州师范大学个性化
		else if("10346".equals(Base.xxdm)){
			sql = new StringBuilder("select a.dcmc,a.sqly,a.sqsj,a.ylzd2,a.ylzd3,a.xqmc,a.xn,a.yymc,a.ylzd5,a.ylzd4,a.sqlydm,b.mc jtknlxmc,c.mc gdxfplxmc from xg_view_xszz_new_knsjgb a ");
			sql.append(" left join xg_knsrd_jtknlx b on a.ylzd5 = b.dm left join xg_knsrd_gdxfplx c on a.ylzd4 = c.dm");
			sql.append(" where a.guid=?");
		}
		//中国美术学院个性化
		else if("10355".equals(Base.xxdm)){
			sql = new StringBuilder("select a.dcmc,a.sqly,a.sqsj,a.ylzd2,a.ylzd3,a.xqmc,a.xn,a.yymc,a.ylzd5,a.sqlydm,b.jtrjnsr from xg_view_xszz_new_knsjgb a,XG_XSZZ_NEW_KNSJGB b where a.xh = b.xh and a.xn = b.xn and a.guid= ? ");	
		} else {
			sql = new StringBuilder("select dcmc,sqly,sqsj,ylzd2,ylzd3,xqmc,xn,yymc,ylzd5,sqlydm from xg_view_xszz_new_knsjgb where guid=? ");				
		}
		return dao.getMapNotOut(sql.toString(),new String[]{guid});
	}
	
	/**
	 * 
	 * @描述:TODO(获取取消困难生资格)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-28 上午11:07:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgguid
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneKnsqxjlList(String  guid) {
		StringBuilder sql = new StringBuilder(" select czr,czsj,qxyy from xg_xszz_new_knsqxjl where jgguid=? ");			
		return dao.getMapNotOut(sql.toString(),new String[]{guid});
	} 

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_knsjgb");
		super.setKey("guid");
		
	}
	

	
	/**
	 * 
	 * @描述:按学号查询全部困难生信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-25 下午03:23:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getKnsInfoList(String xh){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select xn,xq,xqmc,sqsj,rddc,dcmc,guid,ylzd3,case when rn = 1 and sfqxrd='0' then '' else '已取消' end sfqxrd from ( ");
		sql.append( " select t1.xn,t1.xq,t2.xqmc,t1.sqsj,t1.rddc,t3.dcmc,t1.guid,t1.ylzd3, sfqxrd" );
		sql.append(" ,row_number()over(partition by xh order by xn desc,xq desc) rn");
		sql.append( " from xg_xszz_new_knsjgb t1 " );
		sql.append( " left join xqdzb t2 on t1.xq=t2.xqdm " );
		sql.append( " left join xg_xszz_new_kndcdmb t3 on t1.rddc=t3.dcdm " );
		sql.append( " where t1.xh = ? " );	
		sql.append(" ) order by xn desc,xq desc");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	
	/**
	 * 
	 * @描述:困难生结果表-学年
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsjgXn() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct xn dm,xn mc  ");
		sb.append(" from xg_xszz_new_knsjgb order by xn");
		return dao.getListNotOut(sb.toString(), null);
	}
	
	/**
	 * 
	 * @描述:困难生结果表-学期
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsjgXq() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct xq dm,b.xqmc mc  ");
		sb.append(" from xg_xszz_new_knsjgb a ");
		sb.append("  left join xqdzb b on a.xq=b.xqdm order by xq");
		return dao.getListNotOut(sb.toString(), null);
	}
	
	
	/**
	 * 通过学号查询困难生结果
	 * @param xh
	 * @return
	 */
	public List<String[]> getKnsjgList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.xn,b.xqmc,c.dcmc,a.sqsj from xg_xszz_new_knsjgb a  ");
		sql.append(" left join xqdzb b on a.xq=b.xqdm  ");
		sql.append(" left join xg_xszz_new_kndcdmb c on a.rddc=c.dcdm  ");
		sql.append(" where a.xh=? ");
		sql.append(" order by xn desc,xq");
		String[] input = new String[] {xh};
		String[] output = new String[]{"xn","xqmc","dcmc","sqsj"};
		ArrayList<String[]> rs = dao.rsToVator(sql.toString(), input,output);
		return rs;
	}

	/**
	 * 
	 * @描述: 获取学生困难评定情况
	 * @作者：honglin
	 * @日期：2013-5-4
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXsknsjg (String xh ,String xn ,String xq){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		sql.append("select t1.*,t2.dcmc from xg_xszz_new_knsjgb t1 left join xg_xszz_new_kndcdmb t2 on t1.rddc=t2.dcdm where t1.xh=? ");
		
		if(StringUtils.isNotBlank(xn)){
			sql.append(" and t1.xn = ? ");
			params.add(xn);
		}
		
		if(StringUtils.isNotBlank(xq)&&!Globals.XXDM_ZJCMXY.equals(Base.xxdm) && "xq".equals(SQZQ)){
			sql.append(" and t1.xq = ? ");
			params.add(xq);
		}
		
		sql.append(" and rownum =1 order by t1.xn desc,t1.xq desc ");
		
		
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[params.size()]));
	}
	
	/**
	 * 
	 * @描述:勤工助学内获取困难生认定档次
	 * @作者：cq [工号：785]
	 * @日期：2013-10-8 下午03:45:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getKnsrddc (String xh, String xn){
		
		String sql = " select dcmc from xg_xszz_new_knsjgb t1 left join xg_xszz_new_kndcdmb t2 on t1.rddc=t2.dcdm where rownum = 1 and xn = ? and xh = ? ";
		
		return dao.getMapNotOut(sql, new String[]{xn,xh});
		
	}

	/**
	 * 
	 * @描述:删除结果数据回滚
	 * @作者：945
	 * @日期：2013-11-29 下午04:03:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delJgb(String ywid) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String[] values = new String[]{ywid};
		sql.append("delete from ");
		sql.append(" xg_xszz_new_knsjgb ");
		sql.append(" where sjly='1' and ");
		sql.append(" lylcywid = ? ");
		
		return dao.runDelete(sql.toString(), values);
	}
	

	/** 
	 * @描述:困难生结果表-周期
	 * @作者：cq [工号：785]
	 * @日期：2013-10-15 上午11:54:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKnsjgZq() {

		StringBuilder sb = new StringBuilder();
		sb.append(" select xn||' '||(select xqmc from xqdzb b where a.xq=b.xqdm) zqmc, xn||xq zqdm ");
		sb.append(" from (select  distinct a.xn,a.xq from xg_xszz_new_knsjgb a order by a.xn,a.xq) a ");
		return dao.getListNotOut(sb.toString(), null);
		
	}
	
	/**
	 * 
	 * @描述:困难生结果表-周期
	 * @作者：陶钢军 [1075]
	 * @日期：2014-7-1 下午02:23:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsjgZqList() {

		StringBuilder sb = new StringBuilder();
		sb.append(" select xn||' '||(select xqmc from xqdzb b where a.xq=b.xqdm) zqmc, xn||','||xq zqdm ");
		sb.append(" from (select  distinct a.xn,a.xq from xg_xszz_new_knsjgb a order by a.xn,a.xq) a ");
		return dao.getListNotOut(sb.toString(), null);		
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：陶钢军 [1075]
	 * @日期：2014-7-1 下午03:18:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqxn
	 * @param sqxq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsjgForXnXq(String xn,
			String xq) {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*,b.xm from xg_xszz_new_knsjgb a join view_xsjbxx b on a.xh=b.xh where a.xn=? and a.xq=? ");
		return dao.getListNotOut(sb.toString(), new String[] { xn, xq });
	}
	/**
	 * 
	 * @描述:删除结果数据
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-3 上午11:55:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delKnsjg(String xn,String xq,String xh) throws Exception{
		String sql = "delete from xg_xszz_new_knsjgb where xn=? and xq=? and xh=? ";
		return dao.runUpdate(sql, new String[]{xn,xq,xh});
	}
	
	/**
	 * 获取困难生档次代码
	 * @return
	 */
	public List<HashMap<String,String>> getKnsdcList(){
		String sql = "select dcdm dm,dcmc mc from xg_xszz_new_kndcdmb";
		return  dao.getListNotOut(sql, new String[] {});	
	}
	
	/**
	 * 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2015-12-3 下午08:32:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getKnspd(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.sjly   from xg_xszz_new_knsjgb t where t.xh = ? and t.xn = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xn});
	}
	
	/**
	 * @描述: 困难生结果库中困难与特困的比例
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-11-21 上午09:00:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> knstksPercent(KnsjgForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append(" select xymc, kn, tk, round((tk / kn), 2) * 100 || '%' bl ");
		sql.append(" from (select xymc,sum(case  when rddc = '1' then 1 else 0 end) kn, sum(case when rddc = '2' then 1 else 0 end) tk");
		sql.append(" from view_knsjgb_fqxrd group by xymc ) where 1 = 1 ");
		if(xgxt.utils.String.StringUtils.isNotNull(model.getYlzd5())){
			sql.append(" and xymc like '%'||?||'%' ");
			params.add(model.getYlzd5());
		}
		return getPageList(model, sql.toString(),params.toArray(new String[]{}));
	}
	
	/**
	 * @描述: 浙江大学学生资助申请表非取消认定的困难生信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-11-25 上午10:35:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getKnsxx (String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from view_knsjgb_fqxrd where xh = ? ");
		sql.append(" and rownum =1 order by sqsj desc ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	
	public List<HashMap<String, String>> getKnsjgXy(KnsjgForm t, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct xydm,xymc from (");
		getKnsjgList(sql);
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" )");
		return  dao.getListNotOut(sql.toString(),inputV);
	}
	
	public List<HashMap<String, String>> getKnsjgListByxy(KnsjgForm t, User user,String xydm) throws Exception {
		//生成高级查询相关条件、条件值 
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,rownum R from (");
		getKnsjgList(sql);
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" and xydm = '"+xydm+"'");
		sql.append(" order by rddc,xh ");
		sql.append(" ) t");
		return  dao.getListNotOut(sql.toString(),inputV);
	}
	
public List<HashMap<String, String>> getKnsjgBj(KnsjgForm t, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct bjdm,bjmc from (");
		getKnsjgList(sql);
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" )");
		return  dao.getListNotOut(sql.toString(),inputV);
	}
	
	public List<HashMap<String, String>> getKnsjgListBybj(KnsjgForm t, User user,String bjdm) throws Exception {
		//生成高级查询相关条件、条件值 
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,rownum R from (");
		getKnsjgList(sql);
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" and bjdm = '"+bjdm+"'");
		sql.append(" order by rddc,xh ");
		sql.append(" ) t");
		return  dao.getListNotOut(sql.toString(),inputV);
	}
	
	private void getKnsjgList(StringBuilder sql){
		sql.append("select * from (");
		sql.append("select t1.xh, t2.xm, t1.xn, t1.xq, t2.xb, t1.rddc, t4.dcmc, t3.bjdm, t3.bjmc,");
		sql.append("       t2.mz, t3.zydm, t3.zymc, t3.xydm, t3.xymc, t3.nj, n.fdyxm,");
		sql.append("       decode(t5.ylzd1, '1', '是', '0', '否', t5.ylzd1) ylzd1mc,");
		sql.append("       decode(t5.sfgc, '1', '是', '0', '否', t5.sfgc) sfgcmc");
		sql.append("  from XG_XSZZ_NEW_KNSJGB t1");
		sql.append("  left join xsxxb t2");
		sql.append("    on t1.xh = t2.xh");
		sql.append("  left join view_njxyzybj_all t3");
		sql.append("    on t2.bjdm = t3.bjdm");
		sql.append("  left join xg_xszz_new_kndcdmb t4");
		sql.append("    on t1.rddc = t4.dcdm");
		sql.append("  left join xg_xszz_new_jtqkdcb t5");
		sql.append("    on t1.xh = t5.xh ");
		sql.append("  left join (select a.bjdm, WM_CONCAT(b.xm) fdyxm from fdybjb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) n ");
		sql.append("    on t2.bjdm = n.bjdm ) ");
		sql.append("  where 1=1 ");
	}
	
	public List<String> getNj() throws Exception {
		String sql="select distinct nj from view_xsjbxx where xydm is not null and nj is not null order by nj";
		return  dao.getList(sql, new String[]{}, "nj");
	}
	
	public List<String> getXy() throws Exception {
		String sql="select distinct xydm,xymc from view_xsjbxx where xydm is not null and nj is not null order by xydm";
		return  dao.getList(sql, new String[]{}, "xymc");
	}
	
	public List<HashMap<String, String>> getKnstj_12688(KnsjgForm t){
		StringBuilder sql=new StringBuilder();
		String xn=t.getXn();
		String xq=t.getXq();
		sql.append(" select * from (select t.nj, t.xydm, t.xymc, nvl(t.rs,'0') rs, t.tks, t.pks, nvl2(rs,to_char(pks/rs*100,'fm990.00')||'%','')pksbfb from  ");
		sql.append(" (select t1.*,t2.rs,nvl(t3.tks,'0')tks,nvl(t3.pks,'0')pks from  ");
		sql.append(" (select nvl(to_char(nj),'nullnj') nj, nvl(xydm,'nullxydm') xydm, xymc from ");
		sql.append(" (select nj from view_xsjbxx where xydm is not null and nj is not null group by nj) a, ");
		sql.append(" (select xydm,xymc from view_xsjbxx where xydm is not null and nj is not null group by xydm,xymc) b ");
		sql.append(" group by cube(nj,(xydm,xymc)) order by nj,xydm) t1 ");
		sql.append(" left join ");
		sql.append(" (select count(1)rs,nvl(to_char(nj),'nullnj') nj, nvl(xydm,'nullxydm') xydm  ");
		sql.append(" from view_xsjbxx where nj is not null and xydm is not null group by cube(xydm,nj) order by nj,xydm ");
		sql.append(" ) t2 on t1.nj=t2.nj and t1.xydm=t2.xydm ");
		sql.append(" left join ");
		sql.append(" (select to_number(substr(rddc, -10, 5)) tks,(to_number(substr(rddc, -5, 5))+to_number(substr(rddc, -10, 5)))pks,nj,xydm from ");
		sql.append(" (select nvl(to_char(nj),'nullnj') nj, nvl(xydm,'nullxydm') xydm, lpad(sum(rddc),10,'0')rddc from ");
		sql.append(" (select a.xh,c.xydm,c.nj,decode(d.dcmc,'家庭经济特别困难','100000','1') rddc from XG_XSZZ_NEW_KNSJGB a ");
		sql.append(" left join XSXXB b on a.xh=b.xh ");
		sql.append(" left join VIEW_NJXYZYBJ_ALL c on b.bjdm = c.bjdm ");
		sql.append(" left join  XG_XSZZ_NEW_KNDCDMB d on a.rddc=d.dcdm ");
		sql.append(" where xn=? and xq=? and c.xydm is not null) ");
		sql.append(" group by cube (xydm,nj))) t3 on t1.nj=t3.nj and t1.xydm=t3.xydm) t ");
		sql.append(" order by nj,xydm) ");
		return dao.getListNotOut(sql.toString(),new String[]{xn, xq});
	}
	
	public List<HashMap<String, String>> getKnstj2_12688(KnsjgForm t){
		StringBuilder sql=new StringBuilder();
		String xn=t.getXn();
		String xq=t.getXq();
		sql.append(" select t.*,to_char(ratio_to_report(tks) over ()*100,'fm990.00')||'%' tksbfb,to_char(ratio_to_report(pks) over ()*100,'fm990.00')||'%'  pksbfb from "); 
		sql.append("(select t1.xydm, nvl(t2.pks,0) pks, nvl(t2.tks,0) tks from ");
		sql.append("(select xydm from view_xsjbxx where xydm is not null and nj is not null group by xydm)t1 "); 
		sql.append("left join "); 
		sql.append("(select to_number(substr(rddc, -10, 5)) tks,(to_number(substr(rddc, -5, 5))+to_number(substr(rddc, -10, 5)))pks,xydm from ");
		sql.append("(select xydm, lpad(sum(rddc),10,'0')rddc from ");
		sql.append("(select a.xh,c.xydm,c.nj,decode(d.dcmc,'家庭经济特别困难','100000','1') rddc from XG_XSZZ_NEW_KNSJGB a ");
		sql.append("left join XSXXB b on a.xh=b.xh ");
		sql.append("left join VIEW_NJXYZYBJ_ALL c on b.bjdm = c.bjdm ");
		sql.append("left join  XG_XSZZ_NEW_KNDCDMB d on a.rddc=d.dcdm ");
		sql.append("where xn= ? and xq= ? and c.xydm is not null) ");
		sql.append("group by xydm)) t2 on t1.xydm=t2.xydm ");
		sql.append("order by t1.xydm)t ");
		return dao.getListNotOut(sql.toString(),new String[]{xn, xq});
	}
	
	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 * @描述: 获取徐州工程导出的困难生结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-26 上午11:33:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXzyyExportData(KnsjgForm t, User user) throws Exception{
		//生成高级查询相关条件、条件值 
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql=new StringBuilder();
        sql.append("select a.*, rownum xl from view_qzyy_knsdc a where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 * @描述: 浙江中医药个性化导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-26 上午11:33:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjzyyExportData(KnsjgForm t, User user) throws Exception{
		//生成高级查询相关条件、条件值 
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String view = "view_zjzyy_knsdc";
		if("11842".equals(Base.xxdm)){			
			view = "view_xg_knsdc_11842";
		}else if("12309".equals(Base.xxdm)){
			view = "view_xg_knsdc_12309";
		}
		else if("10098".equals(Base.xxdm)){
			view = "view_xg_knsdc_10098";
		}
		StringBuilder sql=new StringBuilder();
        sql.append("select a.*, rownum xl from ");
        sql.append(view);
        sql.append(" a where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by xl,sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/** 
	 * @描述:获取学生困难认定档次(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-18 上午09:53:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXsrddc(String xh){
		String sql = "select * from (select xh,rddc,sqsj,rddcmc,rownum rn from (select a.xh,a.rddc,a.sqsj,b.dcmc rddcmc from xg_xszz_new_knsjgb a left join xg_xszz_new_kndcdmb b on a.rddc = b.dcdm where a.xh = ? order by a.sqsj desc)) where rn = 1";
		return dao.getMap(sql, new String[]{xh}, new String[]{"rddcmc","sqsj"});
	}
	
	/**
	 * 
	 * @描述: 获取学院名称
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-18 上午10:20:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXymc(String xydm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct xymc  from view_njxyzybj where xydm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xydm}, "xymc");
	}
	
	/**
	 * @description	： 困难生认定导出
	 * @author 		： lj（1282）
	 * @date 		：2018-4-24 下午05:04:40
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>> knsrddc(KnsjgForm t,User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.* from (select a.*,'居民身份证' sfzlxmc,to_char(to_date(a.sqsj,'yyyy-MM-dd HH24:mi:ss'),'yyyyMMdd') rdsj,a.sqly rdyy,t7.shyj njrdyj,t8.shyj bjrdyj from view_new_dc_xszz_knsrdcx a ");
		sql.append(" left join (select * from ( select t.*,row_number() over(partition by t.ywid order by t.shsj desc) rn from xg_xtwh_shztb t ) where rn = '1' ) t7 on a.lylcywid= t7.ywid ");
		sql.append(" left join (select * from ( select t.*,row_number() over(partition by t.ywid order by t.shsj desc) rn from xg_xtwh_shztb t ) where rn = '2' ) t8 on a.lylcywid = t8.ywid) a");
		sql.append(" where 1 = 1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by a.xn,a.xq,a.sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @description	： 困难生家庭信息导出
	 * @author 		： lj（1282）
	 * @date 		：2018-4-25 上午10:55:29
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> jtxxdc(KnsjgForm t,User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from view_ahjzdx_knsdc");
		sql.append(" a where 1 = 1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by a.xh desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @description	： TODO
	 * @author 		： lj（1282）
	 * @date 		：2018-4-25 下午02:14:11
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> knsjdlkdc(KnsjgForm t,User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (select a.*,'居民身份证' sfzlxmc,b.pyccmc,'' sfjdlk  from view_new_dc_xszz_knsrdcx a ");
		sql.append(" left join xg_xsxx_pyccdmb b on a.pycc = b.pyccdm)");
		sql.append(" a where 1 = 1");	
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by a.xn,a.xq,a.sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}

	public HashMap<String, String> getKnsInfo(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.dcmc from xg_xszz_new_knsjgb t1 left join xg_xszz_new_kndcdmb t2 on t1.rddc=t2.dcdm where rownum = 1 and xn = ? and xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xn});
	}
}
