/**
 * @部门:学工产品事业部
 * @日期：2013-12-16 上午10:01:27 
 */  
package com.zfsoft.xgxt.xsxx.bbzc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学期报到注册
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-16 上午10:01:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XqbdzcDao extends SuperDAOImpl<XqbdzcForm> {
	
	
	private String tab = "view_xsjbxx";
	
	public XqbdzcDao(){
		
		if(Base.xxdm.equals(Globals.XXDM_ZJCMXY)){
			tab = "view_xsbfxx";
		}
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XqbdzcForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XqbdzcForm t, User user)
			throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (")
		//.append("select a.xh, a.xm , a.nj , a.xb , a.bjmc  , b.id , b.zcsj , b.zcr ,")
		.append("select a.* , b.id ,b.wbdlbdm,b.yjbdsj,c.wbdlbmc, b.zcsj , b.zcr , (case when length(b.wbdyy)>10 then substr(b.wbdyy,0,10)||'...' else b.wbdyy end)wbdyystr,");
		//sql.append("b.wbdyy,(select aa.csrq from xsxxb aa where aa.xh = a.xh ) csrq , ")
		sql.append("b.wbdyy, ")
		.append("(select nvl2(sum(zd1) , '有欠费' , '无欠费') from RCSW_CWSJB where xn = a.xn and xq = a.xq and xh = a.xh ) sfqf , ")
		.append("(select xqmc from xqdzb where xqdm = a.xq) xqmc ,")
		.append(" decode(b.zczt,NULL , '-1' , '0' , '0' , '1' , '1') zczt,");
		//浙江传媒
		if("11647".equalsIgnoreCase(Base.xxdm)){
			sql.append("decode(b.zczt,NULL , '未处理' ,'0','未报到', '1' , '已报到') zcztmc");
		}
		else{
			sql.append("decode(b.zczt,NULL ,'未处理' ,'0','未注册', '1' , '已注册') zcztmc");
		}
		sql.append(" ")
		.append("from (select a.*, '" + t.getSearchXn() + "' xn, '"+ t.getSearchXq() +"' xq from "+tab+" a) a  ")
		.append(" left join xg_xsxx_bdzcb b on a.xh = b.xh and a.xq = b.xq and a.xn = b.xn ")
		.append("  left join xg_xsxx_bdzc_wbdlb c on b.wbdlbdm = c.wbdlbdm ")
		.append(") t1 where 1=1 ")
		.append(searchTjByUser).append(" ").append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getBdzcList(XqbdzcForm t, User user)
	throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select id,xh,zczt from (")
		//.append("select a.xh, a.xm , a.nj , a.xb , a.bjmc  , b.id , b.zcsj , b.zcr ,")
		.append("select a.* , b.id , b.zcsj , b.zcr , (case when length(b.wbdyy)>10 then substr(b.wbdyy,0,10)||'...' else b.wbdyy end)wbdyystr,");
		//sql.append("b.wbdyy,(select aa.csrq from xsxxb aa where aa.xh = a.xh ) csrq , ")
		sql.append("b.wbdyy, ")
		.append("(select nvl2(sum(zd1) , '有欠费' , '无欠费') from RCSW_CWSJB where xn = a.xn and xq = a.xq and xh = a.xh ) sfqf , ")
		.append("(select xqmc from xqdzb where xqdm = a.xq) xqmc ,")
		.append(" decode(b.zczt,NULL , '-1' , '0' , '0' , '1' , '1') zczt,");
		//浙江传媒
		if("11647".equalsIgnoreCase(Base.xxdm)){
			sql.append("decode(b.zczt,NULL,'未处理' ,'0','未报到', '1' , '已报到') zcztmc");
		}
		else{
			sql.append("decode(b.zczt,NULL,'未处理' ,'0','未注册' , '1' , '已注册') zcztmc");
		}
		sql.append(" ")
		.append("from (select a.*, '" + t.getSearchXn() + "' xn, '"+ t.getSearchXq() +"' xq from "+tab+" a) a  ")
		.append(" left join xg_xsxx_bdzcb b on a.xh = b.xh and a.xq = b.xq and a.xn = b.xn ")
		.append(") t1 where 1=1 ")
		.append(searchTjByUser).append(" ").append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
}
	/**
	 * 
	 * @描述:查询未报到学生数
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-10 上午11:27:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String[] getWzcArr(XqbdzcForm t, User user)
	throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (")
		//.append("select a.xh, a.xm , a.nj , a.xb , a.bjmc  , b.id , b.zcsj , b.zcr ,")
		.append("select a.* , b.id , b.zcsj , b.zcr , (case when length(b.wbdyy)>10 then substr(b.wbdyy,0,10)||'...' else b.wbdyy end)wbdyystr,");
		//sql.append("b.wbdyy,(select aa.csrq from xsxxb aa where aa.xh = a.xh ) csrq , ")
		sql.append("b.wbdyy, ")
		.append("(select nvl2(sum(zd1) , '有欠费' , '无欠费') from RCSW_CWSJB where xn = a.xn and xq = a.xq and xh = a.xh ) sfqf , ")
		.append("(select xqmc from xqdzb where xqdm = a.xq) xqmc ,")
		.append(" decode(b.zczt,NULL , '-1' , '0' , '0' , '1' , '1') zczt,");
		//浙江传媒
		if("11647".equalsIgnoreCase(Base.xxdm)){
			sql.append("decode(b.zczt,NULL , '未处理' ,'0','未报到' , '1' , '已报到') zcztmc");
		}
		else{
			sql.append("decode(b.zczt,NULL , '未处理' ,'0','未注册' , '1' , '已注册') zcztmc");
		}
		sql.append(" ")
		.append("from (select a.*, '" + t.getSearchXn() + "' xn, '"+ t.getSearchXq() +"' xq from "+tab+" a) a  ")
		.append(" left join xg_xsxx_bdzcb b on a.xh = b.xh and a.xq = b.xq and a.xn = b.xn ")
		.append(") t1 where 1=1 and zczt='-1' ")
		.append(searchTjByUser).append(" ").append(searchTj);
        return dao.getArray(sql.toString(), inputV, "xh");
}
	
	/**
	 * 
	 * @描述 获取学期注册信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-16 下午07:26:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getXqzcxx(String xh , String xn , String xq)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (")
		//.append("select a.xh, a.xm , a.nj , a.xb , a.bjmc  , b.id , b.zcsj , b.zcr ,")
		.append("select a.* , b.id ,b.wbdyy, b.zcsj , b.zcr ,c.wbdlbmc,b.yjbdsj,b.wbdlbdm,")
		.append("nvl2(b.xn , b.xn ,'"+ xn +"') xn , ")
		.append("nvl2(b.xq , b.xq ,'" + xq + "') xq , ")
		.append("(select nvl2(sum(zd1) , '有欠费' , '无欠费') from RCSW_CWSJB where xn = nvl2(b.xn , b.xn ,'" + xn + "') and xq = nvl2(b.xq , b.xq ,'" + xq + "') and xh = a.xh ) sfqf , ")
		.append("(select xqmc from xqdzb where xqdm = nvl2(b.xq , b.xq ,'" + xq + "')) xqmc ,")
		.append(" decode(b.zczt,NULL , '-1' , '0' , '0' , '1' , '1') zczt,");
		//浙江传媒
		if("11647".equalsIgnoreCase(Base.xxdm)){
			sql.append("decode(b.zczt,NULL , '未处理' , '1' , '已报到' , '0' , '未报到') zcztmc");
		}
		else{
			sql.append("decode(b.zczt,NULL , '未处理' , '1' , '已注册' , '0' , '未注册') zcztmc");
		}
		sql.append(" ")
		.append("from "+tab+" a ")
		.append(" left join (select * from xg_xsxx_bdzcb where (xn,xq) in (select dqxn,dqxq from xtszb)) b on a.xh = b.xh ")
		.append("  left join  xg_xsxx_bdzc_wbdlb  c on c.wbdlbdm = b.wbdlbdm ) t1  ")
		.append(" where xn = ? and xq = ?  and xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xn , xq , xh});
	}
	/**
	 * 获取注册信息
	 */
	public String getZczt(String xh , String xn , String xq)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) zczt from xg_xsxx_bdzcb ");
		sql.append(" where xh = ? and xn = ? and xq = ? and zczt = '1'  ");
		return dao.getOneRs(sql.toString(), new String[]{xh, xn, xq}, "zczt");
	}
	
	
	public List<HashMap<String , String>> getCwsjList(String xh , String xn , String xq){
		
		String sql = "select t.* , (select xqmc from xqdzb where xqdm = t.xq )  xqmc from RCSW_CWSJB t where t.xn = ? and t.xq = ? and t.xh = ? ";
		
		return dao.getListNotOut(sql, new String[]{xn , xq , xh});
		
	}
	
    public boolean deleteZcxx(XqbdzcForm model) throws Exception{
		
		String sql = "delete from XG_XSXX_BDZCB where xh=? and xn=? and xq=?";
		
		return dao.runUpdate(sql, new String[]{model.getXh(),model.getXn(),model.getXq()});
		
	}
	
	/**
	 * 
	 * @描述:批量注册
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-27 上午10:37:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param plids
	 * @param xn
	 * @param xq
	 * @param zcr
	 * @param zcsj
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean plXqzc(String[] wzcxsArr , String xn , String xq , String zcr , String zcsj , XqbdzcForm t, User user, String type) throws Exception{
		//勾选注册
		if("ids".equalsIgnoreCase(type)){
			if(wzcxsArr == null || wzcxsArr.length == 0)
				return false;
			List<String[]> delParams = new ArrayList<String[]>();
			String delSql = "delete from xg_xsxx_bdzcb where xh=? and xn=? and xq=?";
			for (int i = 0; i < wzcxsArr.length; i++) {
				delParams.add(new String[]{wzcxsArr[i] , xn , xq });
			}
			dao.runBatch(delSql,delParams);
			String sql = "insert into xg_xsxx_bdzcb(xh , xn , xq , zczt , zcsj , zcr) values (?,?,?,?,?,?)";
			List<String[]> params = new ArrayList<String[]>();
			for (int i = 0; i < wzcxsArr.length; i++) {
				params.add(new String[]{wzcxsArr[i], xn , xq , "1" , zcsj , zcr });
			}
			super.dao.runBatch(sql, params);
			
			return true;
		}
		//批量注册
		else if("all".equalsIgnoreCase(type)){
			String searchTj = SearchService.getSearchTj(t.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
			
			String[] first = new String[]{xn,xq,zcsj,zcr,xn,xq};
			String[] second = SearchService.getTjInput(t.getSearchModel());
			String[] inputV = (String[]) ArrayUtils.addAll(first, second);
			String[] delInputV = (String[]) ArrayUtils.addAll( new String[]{xn,xq}, second);
			
			StringBuilder delSql = new StringBuilder();
			delSql.append("delete from xg_xsxx_bdzcb t where exists(select xh from (")
				.append("select a.* ,nvl(b.xn,?) xn, nvl(b.xq,?) xq, b.zcsj , b.zcr, ")
				.append(" decode(b.zczt,NULL , '-1' , '0' , '0' , '1' , '1') zczt ")
				.append("from "+tab+" a  ")
				.append(" left join xg_xsxx_bdzcb b on a.xh = b.xh")
				.append(") t1 where t1.zczt = '-1' and t.xh = t1.xh ")
				.append(searchTjByUser).append(" ").append(searchTj).append(")");
			dao.runUpdate(delSql.toString(), delInputV);

			StringBuilder sql = new StringBuilder();
			sql.append("insert into xg_xsxx_bdzcb(xh , xn , xq , zczt , zcsj , zcr) select xh,?,?,'1',?,? from (")
				.append("select a.* ,nvl(b.xn,?) xn, nvl(b.xq,?) xq, b.zcsj , b.zcr, ")
				.append(" decode(b.zczt,NULL , '-1' , '0' , '0' , '1' , '1') zczt ")
				.append("from "+tab+" a  ")
				.append(" left join xg_xsxx_bdzcb b on a.xh = b.xh")
				.append(") t1 where zczt='-1' ")
				.append(searchTjByUser).append(" ").append(searchTj);
	
			dao.runUpdate(sql.toString(), inputV);
	
			return true;
		}
		
		return false;
	}
	/**
	 * 
	 * @描述:未报到原因维护
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 下午03:01:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param plids
	 * @param xn
	 * @param xq
	 * @param zcr
	 * @param zcsj
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean wbdyywh(XqbdzcForm model) throws Exception{
		String[] ids = model.getPlIds().split(",");
		List<String[]> delParams = new ArrayList<String[]>();
		String delSql = "delete from xg_xsxx_bdzcb where xh=? and xn=? and xq=?";
		for (int i = 0; i < ids.length; i++) {
			delParams.add(new String[]{ids[i] , model.getXn(),model.getXq() });
		}
		dao.runBatch(delSql,delParams);
		String sql = "insert into xg_xsxx_bdzcb(xh,xn,xq,zczt,wbdlbdm,yjbdsj,wbdyy) values(?,?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < ids.length; i++) {
			params.add(new String[]{ids[i],model.getXn(),model.getXq(),"0",model.getWbdlbdm(),model.getYjbdsj(),model.getWbdyy()});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @描述:按照职工号查询已设置常用意见
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 下午03:44:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param gnid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCyyyList(User user) {
		String sql = "select * from xg_bdzc_wbdcyyyb where zgh=?";
		return dao.getListNotOut(sql, new String[]{user.getUserName()});
	}
	/**
	 * 
	 * @描述:删除常用原因
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 下午04:17:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param gnid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delCyyy(User user) throws Exception{
		String sql = "delete from xg_bdzc_wbdcyyyb where zgh=?";
		return dao.runUpdate(sql, new String[]{user.getUserName()});
	}
	/**
	 * 
	 * @描述:常用原因保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 下午04:20:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param gnid
	 * @param shyj
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveCyyy(User user,String[] cyyy) throws SQLException{
		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
		
		for (String str : cyyy){
			if (StringUtil.isNull(str))
				continue;
			param = new String[]{user.getUserName(),str};
			params.add(param);
		}
		String sql = "insert into xg_bdzc_wbdcyyyb(id,zgh,cyyy) values (sys_guid(),?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("id");
		super.setClass(XqbdzcForm.class);
		super.setTableName("xg_xsxx_bdzcb");
	}

	/**
	 * 
	 * @描述:查询未处理、未报到学生数
	 * @作者：黄辰霁
	 * @日期：2015-12-15 上午11:27:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @param zczt
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getBdzcListCount(XqbdzcForm t, User user, String zczt)
	throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) num from (")
		//.append("select a.xh, a.xm , a.nj , a.xb , a.bjmc  , b.id , b.zcsj , b.zcr ,")
		.append("select a.* , b.id , b.zcsj , b.zcr , (case when length(b.wbdyy)>10 then substr(b.wbdyy,0,10)||'...' else b.wbdyy end)wbdyystr,");
		//sql.append("b.wbdyy,(select aa.csrq from xsxxb aa where aa.xh = a.xh ) csrq , ")
		sql.append("b.wbdyy, ")
		.append("(select nvl2(sum(zd1), '有欠费', '无欠费') from RCSW_CWSJB where xn = a.xn and xq = a.xq and xh = a.xh ) sfqf , ")
		.append("(select xqmc from xqdzb where xqdm = a.xq) xqmc ,")
		.append(" decode(b.zczt,NULL, '-1', '0', '0', '1', '1') zczt,");
		//浙江传媒
		if("11647".equalsIgnoreCase(Base.xxdm)){
			sql.append("decode(b.zczt,NULL, '未处理', '0', '未报到', '1', '已报到') zcztmc");
		}
		else{
			sql.append("decode(b.zczt,NULL, '未处理', '0', '未注册', '1', '已注册') zcztmc");
		}
		sql.append(" ")
		.append("from (select a.*, '" + t.getSearchXn() + "' xn, '"+ t.getSearchXq() +"' xq from "+tab+" a) a  ")
		.append(" left join xg_xsxx_bdzcb b on a.xh = b.xh and a.xq = b.xq and a.xn = b.xn ")
		.append(") t1 where 1=1 ");
		
		if(StringUtils.isNotNull(zczt)){
			sql.append(" and zczt='"+ zczt +"' ");
		}
		sql.append(searchTjByUser).append(" ").append(searchTj);
		
		return dao.getOneRs(sql.toString(), inputV, "num");
	}

	/**
	 * @throws Exception 
	 * @描述:批量撤销
	 * @作者：黄辰霁
	 * @日期：2015-12-16 下午01:54:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plCxzc(String xn ,String xq, XqbdzcForm t, User user ) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String[] delInputV = (String[]) ArrayUtils.addAll( new String[]{xn,xq}, inputV);
		
		StringBuilder delSql = new StringBuilder();
		delSql.append("delete from xg_xsxx_bdzcb t where exists(select xh from (")
			.append("select a.* ,nvl(b.xn,?) xn, nvl(b.xq,?) xq, b.zcsj , b.zcr, ")
			.append(" decode(b.zczt,NULL , '-1' , '0' , '0' , '1' , '1') zczt ")
			.append("from "+tab+" a  ")
			.append(" left join xg_xsxx_bdzcb b on a.xh = b.xh")
			.append(") t1 where t1.zczt = '1' and t.xh = t1.xh")
			.append(searchTjByUser).append(" ").append(searchTj).append(")");
		
		dao.runUpdate(delSql.toString(), delInputV);
		
		return true;
	}
	public List<HashMap<String, String>> getWbdlb(){
		StringBuffer sql=new StringBuffer();
		sql.append(" select wbdlbdm,wbdlbmc from xg_xsxx_bdzc_wbdlb  order by wbdlbdm asc");
		return dao.getListNotOut(sql.toString(),new String[]{});
	}
}
