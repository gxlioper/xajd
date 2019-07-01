/**
 * @部门:学工产品事业部
 * @日期：2018-2-8 下午03:45:18 
 */  
package com.zfsoft.xgxt.hdgl.hdjdsh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxDao;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxForm;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动阶段审核ao.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-02-08 03:43
 */
public class HdjdshDao extends SuperDAOImpl<HdxxForm>{

	/**
	 * @description	： TODO
	 * @author 		：GavinShow[1426]
	 * @date 		：2018-1-18 下午04:25:36
	 * @param t
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HdxxForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @description	： 审核列表
	 * @author 		：GavinShow[1426]
	 * @date 		：2018-1-18 下午04:25:36
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HdxxForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();

		sql.append(" select t.* from (");
		sql.append(" select t1.*,t4.hdlxmc,decode(t1.bmlx,'0','组队','个人') bmlxmc,");
		sql.append("        hdkssj||' 至 '||hdjssj hdsj,t5.jdid,t5.jdlx,t6.jdmc ,t2.num ybmrs, t7.zjzgh ");
		sql.append(" from xg_hdgl_hdxxb t1");
		sql.append(" left join (select hdid,count(1) num from xg_hdgl_hdryb where shzt = '1' group by hdid) t2 on t1.hdid = t2.hdid");
		sql.append(" left join xg_hdgl_hdlxdmb t4 on t1.hdlx = t4.hdlxdm");
		sql.append(" left join xg_hdgl_hdjdb t5 on t1.hdid = t5.hdid and t5.dqjdbj = '1'");
		sql.append(" left join xg_hdgl_jdglb t6 on t5.jdid = t6.jdid");
		sql.append(" left join xg_hdgl_hdzjtcyb t7 on t1.hdid = t7.hdid and t5.jdid = t7.jdid ");
		sql.append(" ) t where jdid is not null ");
		sql.append(" and zjzgh = '"+user.getUserName()+"' ");

		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @description	： TODO
	 * @author 		：GavinShow[1426]
	 * @date 		：2018-1-18 下午04:25:36
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(HdxxForm.class);
		super.setKey("sqid");
		super.setTableName("xg_hdgl_hdblsqb");
		
	}

	//是否未打分
	public boolean sfwdf(String hdsqid,String jdid,String zgh){
		StringBuilder sql = new StringBuilder();
		sql.append("select fs from XG_HDGL_ZPZJGXB where hdsqid = ? and jdid = ? and zgh = ?");
		String fs = dao.getOneRs(sql.toString(),new String[]{hdsqid,jdid,zgh},"fs");
		return StringUtils.isNull(fs);
	}
	/**
	 * 判断活动是否是需要打分的活动
	 * @param hdid
	 * @return
	 */
	public boolean isExist(String hdid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) isExist from XG_HDGL_HDJDB t where hdid = ? and sfsldf = '1'");
		String s = dao.getOneRs(sql.toString(),new String[]{hdid},"isExist");
		return StringUtils.isNull(s) ? false : Integer.parseInt(s) > 0 ? true : false;
	}
	/**
	 *  获取活动阶段审核个人列表.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-02-10 16:38
	 * @param model
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw Exception
	 */
	public List<HashMap<String,String>> getHdjdshGrList(HdxxForm model,User user) throws Exception {

		String hdid = model.getHdid();
		Map<String,String> hdjdInfo;
		String jdid = model.getJdid();
		if(StringUtils.isNotNull(jdid)){
			hdjdInfo = getHdjdInfoWithJdid(hdid,jdid);
		}else {
			hdjdInfo = getCurrentHdjdInfo(hdid);
		}
		String prejdid = hdjdInfo.get("prejdid");
		String currentjdid = hdjdInfo.get("jdid");
		StringBuilder sql = new StringBuilder();
		String[] inputV;
		//活动是打分活动
		boolean sfdfhd = isExist(hdid);
		sql.append("select a.*,rownum r from (");
		//上一阶段是学生阶段
		if("1".equals(hdjdInfo.get("prejdlx"))){
			sql.append(" SELECT a.SQID,a.HDID,a.XH,c.XM,a.BMSJ,a.ZW,b.JDID,b.SHZT, ");
			if(sfdfhd){
				sql.append("e.zf,e.pjf,e.ydfzjs,");
			}
			sql.append(" CASE WHEN b.JDID IS NULL THEN '未提交' ");
			sql.append(" ELSE '已提交' END tjztmc, ");
			sql.append(" decode(b.SHZT,'5','审核中','3','退回','2','不通过','1','通过','') shztmc ");
			sql.append(" ,row_number() over(partition by a.sqid order by a.sqid) rn ");
			sql.append(" FROM XG_HDGL_HDRYB a ");
			sql.append(" LEFT JOIN XG_HDGL_XSJDXXB b "); //学生1个阶段只会有1条记录
			sql.append(" ON a.HDID = b.HDID AND a.SQID = b.HDSQID AND b.JDID = ? ");
			sql.append(" LEFT JOIN XSXXB c ON a.XH = c.XH ");
			//活动是打分活动
			if(sfdfhd){
				sql.append(" left join (");
				sql.append(" select sum(fs) zf, ROUND(sum(fs)/count(case when fs is not null then 1 end),2) pjf,count(case when fs is not null then 1 end) ydfzjs,hdsqid,jdid");
				sql.append(" from XG_HDGL_ZPZJGXB a group by hdsqid,jdid) e");
				sql.append(" on a.sqid = e.hdsqid and e.jdid = '"+currentjdid+"'");
				sql.append(" left join XG_HDGL_ZPZJGXB f on a.sqid = f.hdsqid  ");
			}
			sql.append(" WHERE a.HDID = ? ");
			//活动是打分活动,是否是打分环节
			if(sfdfhd && "1".equals(hdjdInfo.get("sfsldf"))){
				sql.append(" and f.zgh = '"+user.getUserName()+"' ");
			}

			inputV = new String[]{prejdid,hdid};
		} else {//上一阶段是老师阶段
			sql.append(" SELECT distinct a.SQID,a.HDID,a.XH,c.XM,a.BMSJ,a.ZW,d.SHZT, ");
			if(sfdfhd){
				sql.append("e.zf,e.pjf,e.ydfzjs,");
			}
			sql.append(" CASE WHEN (b.JDID IS NOT NULL AND b.shzt = '1' ) THEN '已提交' ");
			sql.append(" ELSE '未提交' END tjztmc, ");
			sql.append(" decode(d.SHZT,'5','审核中','3','退回','2','不通过','1','通过','') shztmc ");
			if(sfdfhd && !"1".equals(hdjdInfo.get("sfsldf"))){
				sql.append(",(select count(1) from XG_HDGL_HDRYB where nextjdid = '"+currentjdid+"') zrs");
			}
			sql.append(" ,row_number() over(partition by a.sqid order by a.sqid) rn ");
			sql.append(" FROM XG_HDGL_HDRYB a ");

			sql.append(" LEFT JOIN (select * FROM XG_HDGL_JSJDSHXXB WHERE (jdid,SHSJ) IN ( ");
			sql.append(" SELECT jdid,max(SHSJ) FROM XG_HDGL_JSJDSHXXB ");
			sql.append(" GROUP BY HDID,JDID,HDSQID)) b ");

			sql.append(" ON a.HDID = b.HDID AND a.SQID = b.HDSQID AND b.JDID = ? ");
			sql.append(" LEFT JOIN XSXXB c ON a.XH = c.XH ");
			sql.append(" LEFT JOIN XG_HDGL_JSJDSHXXB d ");
			sql.append(" ON a.HDID = d.HDID AND a.SQID = d.HDSQID AND d.JDID = ? ");
			//活动是打分活动
			if(sfdfhd){
				sql.append(" left join (");
				sql.append(" select t.* from (");
				sql.append(" select a.hdsqid,a.jdid,zf,pjf,ydfzjs,row_number() over(partition by hdsqid order by to_number(jdsx) desc ) rn");
				sql.append(" from (select hdsqid,jdid,sum(a.fs) zf, ROUND(sum(a.fs)/count(case when fs is not null then 1 end),2) pjf,count(case when fs is not null then 1 end) ydfzjs");
				sql.append("       from XG_HDGL_ZPZJGXB a");
				sql.append("       group by hdsqid, jdid) a");
				sql.append(" left join xg_hdgl_hdjdb b on a.jdid = b.jdid) t where t.rn <= '1'");
				sql.append(" ) e on a.sqid = e.hdsqid ");
			}

			if(sfdfhd && "1".equals(hdjdInfo.get("sfsldf"))){
				sql.append(" left join XG_HDGL_ZPZJGXB f on a.sqid = f.hdsqid ");
			}

			sql.append(" WHERE a.HDID = ? ");
			if(sfdfhd){
				sql.append(" AND a.NEXTJDID = '"+currentjdid+"'");
			}

			//判断该环节是否是打分环节
			if("1".equals(hdjdInfo.get("sfsldf"))){
				sql.append(" and f.zgh = '"+user.getUserName()+"' ");
			}
			if(sfdfhd){
				sql.append(" order by e.pjf desc");
			}

			inputV = new String[]{prejdid,currentjdid,hdid};
		}
		sql.append(") a where 1=1 and rn = 1");
		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 *  获取活动阶段审核队伍列表.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-02-10 16:38
	 * @param t
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getHdjdshDwList(HdxxForm t,User user) throws Exception {

		String hdid = t.getHdid();
		Map<String,String> hdjdInfo;
		String jdid = t.getJdid();
		if(StringUtils.isNotNull(jdid)){
			hdjdInfo = getHdjdInfoWithJdid(hdid,jdid);
		}else {
			hdjdInfo = getCurrentHdjdInfo(hdid);
		}
		StringBuilder sql = new StringBuilder();
		String [] inputV;
		//活动是打分活动
		boolean sfdfhd = isExist(hdid);
		String prejdid = hdjdInfo.get("prejdid");
		String currentjdid = hdjdInfo.get("jdid");
		sql.append("select a.*,rownum r from (");
		//上一阶段是学生阶段
		if("1".equals(hdjdInfo.get("prejdlx"))){
			sql.append(" SELECT a.SQID,a.HDID,a.XH,c.XM,a.dwid,a.BMSJ,a.ZW,b.JDID,b.SHZT, ");
			if(sfdfhd){
				sql.append("e.zf,e.pjf,e.ydfzjs,");
			}
			sql.append(" CASE WHEN b.JDID IS NULL THEN '未提交' ");
			sql.append(" ELSE '已提交' END tjztmc, ");
			sql.append(" decode(b.SHZT,'5','审核中','3','退回','2','不通过','1','通过','') shztmc ");
			sql.append(" ,row_number() over(partition by a.sqid order by a.sqid) rn ");
			sql.append(" FROM XG_HDGL_ZDHDRYB a ");
			sql.append(" LEFT JOIN XG_HDGL_ZDJDXXB b ");
			sql.append(" ON a.HDID = b.HDID AND a.SQID = b.HDSQID AND b.JDID = ? ");
			sql.append(" LEFT JOIN XSXXB c ON a.XH = c.XH ");
			//活动是打分活动
			if(sfdfhd){
				sql.append(" left join (");
				sql.append(" select sum(fs) zf, ROUND(sum(fs)/count(case when fs is not null then 1 end),2) pjf,count(case when fs is not null then 1 end) ydfzjs,hdsqid,jdid");
				sql.append(" from XG_HDGL_ZPZJGXB a group by hdsqid,jdid) e");
				sql.append(" on a.sqid = e.hdsqid and e.jdid = '"+currentjdid+"'");
				sql.append(" left join XG_HDGL_ZPZJGXB f on a.sqid = f.hdsqid ");
			}
			sql.append(" WHERE a.HDID = ? and a.dwzw = '1' ");
			//活动是打分活动,是否是打分环节
			if(sfdfhd && "1".equals(hdjdInfo.get("sfsldf"))){
				sql.append(" and f.zgh = '"+user.getUserName()+"' ");
			}

			inputV = new String[]{prejdid,hdid};
		}else {//上一阶段是老师阶段
			sql.append(" SELECT a.SQID,a.HDID,a.XH,c.XM,a.dwid,a.BMSJ,a.ZW,b.JDID,d.SHZT, ");
			if(sfdfhd){
				sql.append("e.zf,e.pjf,e.ydfzjs,");
			}
			sql.append(" CASE WHEN (b.JDID IS NOT NULL AND b.shzt = '1' ) THEN '已提交' ");
			sql.append(" ELSE '未提交' END tjztmc, ");
			sql.append(" decode(d.SHZT,'5','审核中','3','退回','2','不通过','1','通过','') shztmc ");

			if(sfdfhd && !"1".equals(hdjdInfo.get("sfsldf"))){
				sql.append(",(select count(1) from XG_HDGL_ZDHDRYB where nextjdid = '"+currentjdid+"') zrs");
			}
			sql.append(" ,row_number() over(partition by a.sqid order by a.sqid) rn ");
			sql.append(" FROM XG_HDGL_ZDHDRYB a LEFT JOIN ");

//			sql.append("  XG_HDGL_JSJDSHXXB b ");
			sql.append(" (select * FROM XG_HDGL_JSJDSHXXB WHERE (jdid,SHSJ) IN ( ");
			sql.append(" SELECT jdid,max(SHSJ) FROM XG_HDGL_JSJDSHXXB ");
			sql.append(" GROUP BY HDID,JDID,HDSQID)) b ");

			sql.append(" ON a.HDID = b.HDID AND a.SQID = b.HDSQID AND b.JDID = ? ");
			sql.append(" LEFT JOIN XSXXB c ON a.XH = c.XH ");
			sql.append(" LEFT JOIN XG_HDGL_JSJDSHXXB d ");
			sql.append(" ON a.HDID = d.HDID AND a.SQID = d.HDSQID AND d.JDID = ? ");

			//活动是打分活动
			if(sfdfhd){
				sql.append(" left join (");
				sql.append(" select t.* from (");
				sql.append(" select a.hdsqid,a.jdid,zf,pjf,ydfzjs,row_number() over(partition by hdsqid order by to_number(jdsx) desc ) rn");
				sql.append(" from (select hdsqid,jdid,sum(a.fs) zf, ROUND(sum(a.fs)/count(case when fs is not null then 1 end),2) pjf,count(case when fs is not null then 1 end) ydfzjs");
				sql.append("       from XG_HDGL_ZPZJGXB a");
				sql.append("       group by hdsqid, jdid) a");
				sql.append(" left join xg_hdgl_hdjdb b on a.jdid = b.jdid) t where t.rn <= '1'");
				sql.append(" ) e on a.sqid = e.hdsqid ");
			}
			if(sfdfhd && "1".equals(hdjdInfo.get("sfsldf"))){
				sql.append(" left join XG_HDGL_ZPZJGXB f on a.sqid = f.hdsqid  ");
			}

			sql.append(" WHERE a.HDID = ? and a.dwzw = '1' ");
			//活动是打分活动
//			if(sfdfhd){
//				sql.append(" AND a.NEXTJDID = '"+currentjdid+"'");
//			}

			//判断该环节是否是打分环节
			if("1".equals(hdjdInfo.get("sfsldf"))){
				sql.append(" and f.zgh = '"+user.getUserName()+"' ");
			}
			if(sfdfhd){
				sql.append(" order by e.pjf desc");
			}
			inputV = new String[]{prejdid,currentjdid,hdid};
		}
		sql.append(") a where 1=1  and rn = 1");

		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 *  根据活动id获取当前活动阶段信息.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-02-11 11:48
	 * @param hdid
	 * @return java.util.HashMap<java.lang.String,java.lang.String>
	 * @throw
	 */
	public HashMap<String,String> getCurrentHdjdInfo(String hdid) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.*,b.jdmc,(SELECT JDID FROM XG_HDGL_HDJDB WHERE HDID = a.HDID AND JDSX = (a.JDSX - 1) ");
		sql.append(" ) prejdid,(SELECT JDLX FROM XG_HDGL_HDJDB WHERE HDID = a.HDID AND JDSX = (a.JDSX - 1)) prejdlx ");
		sql.append(" ,(SELECT JDID FROM XG_HDGL_HDJDB WHERE HDID = a.HDID AND JDSX = (a.JDSX + 1)) nextjdid");
		sql.append(" FROM XG_HDGL_HDJDB a ");
		sql.append(" LEFT JOIN XG_HDGL_JDGLB b ON a.JDID = b.JDID");
		sql.append(" WHERE a.HDID = ? AND a.DQJDBJ = '1' ");

		return dao.getMapNotOut(sql.toString(),new String[]{hdid});
	}

	/**
	 *  根据活动id，阶段id，获取指定活动阶段信息.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-02-11 11:48
	 * @param hdid
	 * @return java.util.HashMap<java.lang.String,java.lang.String>
	 * @throw
	 */
	public HashMap<String,String> getHdjdInfoWithJdid(String hdid,String jdid) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.*,b.jdmc,(SELECT JDID FROM XG_HDGL_HDJDB WHERE HDID = a.HDID AND JDSX = (a.JDSX - 1) ");
		sql.append(" ) prejdid,(SELECT JDLX FROM XG_HDGL_HDJDB WHERE HDID = a.HDID AND JDSX = (a.JDSX - 1)) prejdlx ");
		sql.append(",(SELECT JDID FROM XG_HDGL_HDJDB WHERE HDID = a.HDID AND JDSX = (a.JDSX + 1)) nextjdid");
		sql.append(" FROM XG_HDGL_HDJDB a ");
		sql.append(" LEFT JOIN XG_HDGL_JDGLB b ON a.JDID = b.JDID");
		sql.append(" WHERE a.HDID = ? AND a.JDID = ? ");

		return dao.getMapNotOut(sql.toString(),new String[]{hdid,jdid});
	}

	/**
	 *  获取活动申请id.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-07 17:51
	 * @param model
	 * @return java.lang.String
	 * @throw
	 */
	public String getHdsqid(HdxxForm model) {

		String bmlx = model.getBmlx();
		StringBuilder sql = new StringBuilder();
		String hdsqid;
		if("1".equals(bmlx)){//个人
			sql.append("SELECT sqid FROM XG_HDGL_HDRYB WHERE HDID = ? ");
			sql.append("AND XH = ?");
			hdsqid = dao.getOneRs(sql.toString(),new String[]{model.getHdid(),model.getXh()},"sqid");
		} else {//组队
			sql.append("SELECT sqid FROM XG_HDGL_ZDHDRYB WHERE HDID = ? ");
			sql.append("AND DWID = ? AND DWZW = '1' AND XH = ? ");
			hdsqid = dao.getOneRs(sql.toString(),new String[]{model.getHdid(),model.getDwid(),model.getXh()},"sqid");
		}
		return hdsqid;
	}

	/**
	 *  查询已完成活动阶段信息列表.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-08 14:37
	 * @param model
	 * @param hdsqid
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getYwcHdjdshCyList(HdxxForm model, String hdsqid,String jdsx) {

		String bmlx = model.getBmlx();
		String hdid = model.getHdid();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT a.*,b.XDTH,b.mc,b.jb,b.zddw,b.zdjs,b.nrjs,c.SHYJ,d.JDMC,b.FJID xsfjid,c.FJID jsfjid,e.fs ");
		sql.append(" FROM XG_HDGL_HDJDB a ");
		sql.append("LEFT JOIN  ");
		if("1".equals(bmlx)){//个人
			sql.append("XG_HDGL_XSJDXXB");
		}else {//组队
			sql.append("XG_HDGL_ZDJDXXB");
		}
		sql.append(" b ON a.HDID = b.HDID AND a.JDID = b.JDID AND b.HDSQID = ? LEFT JOIN ");
//		sql.append(" LEFT JOIN XG_HDGL_JSJDSHXXB c  "); 取最新一条
		sql.append(" (select * FROM XG_HDGL_JSJDSHXXB WHERE SHSJ IN ( ");
		sql.append(" SELECT max(SHSJ) FROM XG_HDGL_JSJDSHXXB ");
		sql.append(" GROUP BY HDID,JDID,HDSQID)) c ");

		sql.append("  ON a.HDID = c.HDID AND a.JDID = c.JDID AND c.HDSQID = ? ");
		sql.append(" LEFT JOIN XG_HDGL_JDGLB d ON a.JDID = d.JDID");

		sql.append(" left join (");
		sql.append(" select hdsqid,jdid, wm_concat(nvl(b.xm,a.zgh)||'：'||a.fs||' ') fs");
		sql.append(" from XG_HDGL_ZPZJGXB a");
		sql.append(" left join fdyxxb b on a.zgh = b.zgh ");
		sql.append(" group by a.hdsqid,a.jdid");
		sql.append(" ) e on a.jdid = e.jdid and e.hdsqid = ?");

		sql.append(" WHERE a.HDID = ? AND a.JDSX < ? ORDER BY a.JDSX");
		return dao.getListNotOut(sql.toString(),new String[]{hdsqid,hdsqid,hdsqid,hdid,jdsx});
	}

	/**
	 *  查询活动阶段信息，包含各阶段待处理人数.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-15 18:02
	 * @param model
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getHdjdList(HdxxForm model,User user) throws Exception {

		HashMap<String,String> info = getCurrentHdjdInfo(model.getHdid());
		//是否打分活动
		boolean flag = isExist(model.getHdid());
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t.*,b.JDMC, ");
		sql.append("(select count(distinct a.xh) from  ");
		if("1".equals(model.getBmlx())){
			sql.append("XG_HDGL_XSJDXXB a");
			//是否打分活动
			//是否是打分环节
			if(flag && "1".equals(info.get("sfsldf"))){
				sql.append(" left join XG_HDGL_ZPZJGXB b on a.hdsqid = b.hdsqid ");
				sql.append(" left join xg_hdgl_hdryb c on a.hdsqid = c.sqid");
			}
		}else {
			sql.append("XG_HDGL_ZDJDXXB a");
			//是否打分活动
			//是否是打分环节
			if(flag && "1".equals(info.get("sfsldf"))){
				sql.append(" left join XG_HDGL_ZPZJGXB b on a.hdsqid = b.hdsqid ");
				sql.append(" left join XG_HDGL_ZDHDRYB c on a.hdsqid = c.sqid");
			}
		}

		sql.append(" WHERE a.hdid = t.hdid and a.jdid = t.prejdid and a.shzt = '5'");
		//是否打分活动
		//是否是打分环节
		if(flag && "1".equals(info.get("sfsldf"))){
			sql.append(" and b.zgh = '"+user.getUserName()+"' and b.fs is null ");
			sql.append(" and b.jdid = c.nextjdid");
		}
		sql.append(" ) dclrs1");
		sql.append(" ,(select count(1) from XG_HDGL_JSJDSHXXB a ");
		if(flag){
			if("1".equals(model.getBmlx())){
				//个人
				sql.append(" left join xg_hdgl_hdryb b on a.hdsqid = b.sqid");
			} else {
				//组队
				sql.append(" left join XG_HDGL_ZDHDRYB b on a.hdsqid = b.sqid");
			}
			//是否是打分环节
			if("1".equals(info.get("sfsldf"))){
				sql.append(" left join XG_HDGL_ZPZJGXB c on a.hdsqid = c.hdsqid ");
			}
		}
		sql.append(" WHERE a.hdid = t.hdid and a.jdid = t.prejdid and a.shzt = '1'");
		if(flag){
			sql.append(" and t.jdid = b.nextjdid ");
			//是否是打分环节
			if("1".equals(info.get("sfsldf"))){
				sql.append(" and c.zgh = '"+user.getUserName()+"' and c.fs is null ");
			}
		}
		sql.append(" ) dclrs2 ");
		sql.append("from (select a.hdid,a.jdid,a.jdsx,a.jdlx,a.dqjdbj, ");
		sql.append("(select jdid from XG_HDGL_HDJDB where HDID = a.HDID and dqjdbj = '1') currentjdid,");
		sql.append("(SELECT JDID FROM XG_HDGL_HDJDB WHERE HDID = a.HDID AND JDSX = (a.JDSX - 1)) prejdid, ");
		sql.append("(SELECT JDLX FROM XG_HDGL_HDJDB WHERE HDID = a.HDID AND JDSX = (a.JDSX - 1)) prejdlx ");
		sql.append("from XG_HDGL_HDJDB a) t ");
		sql.append("left join XG_HDGL_JDGLB b on t.jdid = b.jdid ");
		sql.append("where t.hdid = ? order by t.jdsx");

		return dao.getListNotOut(sql.toString(), new String[]{model.getHdid()} );
	}

	/**
	 *  更新学生阶段审核信息表审核状态.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-19 17:06
	 * @param prejdid
	 * @param model
	 * @return boolean
	 * @throw Exception
	 */
	public boolean updateXsjdShzt(String prejdid, HdxxForm model) throws Exception {

		String sql = "UPDATE XG_HDGL_XSJDXXB SET SHZT = ? WHERE HDID = ? AND JDID = ? AND XH = ?";
		return dao.runUpdate(sql,new String[]{model.getShzt(),model.getHdid(),prejdid,model.getXh()});
	}

	/**
	 *  更新组队阶段审核信息表审核状态.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-19 17:08
	 * @param prejdid
	 * @param model
	 * @return boolean
	 * @throw Exception
	 */
	public boolean updateZdjdShzt(String prejdid, HdxxForm model) throws Exception {

		String sql = "UPDATE XG_HDGL_ZDJDXXB SET SHZT = ? WHERE HDID = ? AND JDID = ? AND DWID = ? AND XH = ?";
		return dao.runUpdate(sql,new String[]{model.getShzt(),model.getHdid(),prejdid,model.getDwid(),model.getXh()});
	}

	/**
	 * 专家打分表改更新
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveDf(HdxxForm model,User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update XG_HDGL_ZPZJGXB set fs = ? where hdsqid = ? and jdid = ? and zgh = ?");
		return dao.runUpdate(sql.toString(),new String[]{model.getDf(),model.getSqid(),model.getJdid(),user.getUserName()});
	}

	public int dfIsFinish(HdxxForm model){
		String s = "select count(case when fs is null then '1' end) isfinish from XG_HDGL_ZPZJGXB where hdsqid = ? and jdid = ?";
		String a = dao.getOneRs(s,new String[]{model.getSqid(),model.getJdid()},"isfinish");
		return StringUtils.isNull(a) ? 0 : Integer.parseInt(a);
	}

	public boolean updateJd(HdxxForm model) throws Exception {
		HashMap<String,String> map = getCurrentHdjdInfo(model.getHdid());
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO XG_HDGL_JSJDSHXXB (JDID,HDID,ZGH,SHYJ,SHSJ,SHZT,JXID,XF,HDSQID) ");
		sql.append("VALUES (?,?,?,?,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'),?,?,?,?) ");
		return dao.runUpdate(sql.toString(),new String[]{model.getJdid(),model.getHdid(),model.getZgh(),model.getShyj(),
				"1",model.getJxid(), model.getXf(),model.getHdsqid()});
	}
	//更新成员阶段
	public boolean updateCyjd(String nextJdid,String sqid) throws Exception {
		String sb = "update xg_hdgl_hdryb set nextjdid = ? where sqid = ? ";
		return dao.runUpdate(sb,new String[]{nextJdid,sqid});
	}
	//更新组队成员阶段
	public boolean updateZdCyjd(String nextJdid,String sqid) throws Exception {
		String sb = "update XG_HDGL_ZDHDRYB set nextjdid = ? where sqid = ? ";
		return dao.runUpdate(sb,new String[]{nextJdid,sqid});
	}
	/**
	 * 获取当前阶段和跳转的阶段之间的所有阶段
	 * @param currentJdid
	 * @param nextJdid
	 * @return
	 */
	public List<String> getZjjd(String currentJdid,String nextJdid,String hdid) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select jdid");
		sql.append(" from XG_HDGL_HDJDB a ");
		sql.append(" where hdid = ? ");
		sql.append(" and jdsx > (select jdsx from XG_HDGL_HDJDB t where a.hdid = hdid and jdid = ?)");
		sql.append(" and jdsx < (select jdsx from XG_HDGL_HDJDB t where a.hdid = hdid and jdid = ?)");
		return dao.getList(sql.toString(),new String[]{hdid,currentJdid,nextJdid},"jdid");
	}
	/**
	 *  向教师阶段审核信息表插入审核信息数据.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-19 17:09
	 * @param jdid
	 * @param model
	 * @return boolean
	 * @throw Exception
	 */
	public boolean insertJsjdInfo(String jdid, HdxxForm model) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO XG_HDGL_JSJDSHXXB (JDID,HDID,ZGH,SHYJ,SHSJ,SHZT,JXID,XF,HDSQID,PFFZ,DF) ");
		sql.append("VALUES (?,?,?,?,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'),?,?,?,?,?,?) ");
		return dao.runUpdate(sql.toString(),new String[]{jdid,model.getHdid(),model.getZgh(),model.getShyj(),
				model.getShzt(),model.getJxid(), model.getXf(),model.getHdsqid(),model.getPsfs(),model.getDf()});
	}

	/**
	 *  根据活动id查询奖项列表.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-22 10:09
	 * @param hdid
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getJxList(String hdid) {

		String sql = "SELECT JXDM,JXMC FROM XG_HDGL_HDJXB WHERE HDID = ?";
		return dao.getListNotOut(sql,new String[]{hdid});
	}

	public List<HashMap<String,String>> getJdList(String hdid,String currentJdid){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.jdid,b.jdmc ");
		sql.append(" from XG_HDGL_HDJDB a");
		sql.append(" left join XG_HDGL_JDGLB b on a.jdid = b.jdid ");
		sql.append(" where a.hdid = ? ");
		sql.append(" and a.jdsx > (select jdsx from XG_HDGL_HDJDB where hdid = ? and jdid = ? )");
		sql.append(" order by a.jdsx");

		return dao.getListNotOut(sql.toString(),new String[]{hdid,hdid,currentJdid});
	}
	/**
	 *  查询指定活动阶段审核信息.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-28 11:04
	 * @param model
	 * @return java.util.HashMap<java.lang.String,java.lang.String>
	 * @throw
	 */
	public HashMap<String,String> getHdjdShInfo(HdxxForm model) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM (SELECT * FROM XG_HDGL_JSJDSHXXB ");
		sql.append(" WHERE HDID = ? AND JDID = ? AND HDSQID = ? ORDER BY SHSJ DESC) ");
		sql.append(" WHERE ROWNUM = 1 ");
		return dao.getMapNotOut(sql.toString(),new String[]{model.getHdid(),model.getJdid(),model.getHdsqid()});
	}

	/**
	 *  判断是否专家团成员.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-29 16:22
	 * @param hdid
	 * @param userName
	 * @return boolean
	 * @throw
	 */
	public boolean isZjtcy(String hdid, String userName) {

		String sql = "SELECT count(zjzgh) count FROM XG_HDGL_HDZJTCYB WHERE HDID = ? AND ZJZGH = ?";
		String count = dao.getOneRs(sql,new String[]{hdid,userName},"count");
		return  Integer.parseInt(count) > 0;
	}
	
	/**
	 * @description	： 判断是否是最后一级
	 * @author 		： lj（1282）
	 * @date 		：2018-4-10 下午03:29:36
	 * @param hdid
	 * @param jdid
	 * @return
	 */
	public boolean isLastNode(String hdid,String jdid){
		String sql = " select count(1) num from (select jdid,hdid,jdsx,row_number() over (partition by hdid order by jdsx desc) rn from xg_hdgl_hdjdb) where rn = 1 and hdid = ? and jdid = ?";
		String result = dao.getOneRs(sql, new String[]{hdid,jdid}, "num");
		return Integer.valueOf(result) > 0;
	}
	
	/**
	 * @description	： 插入组队结果信息
	 * @author 		： lj（1282）
	 * @date 		：2018-4-10 下午04:05:02
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean insertZdjgxx(HdxxForm model) throws Exception{
		String sql = " insert into xg_hdgl_zdhdxxb(hdid,dwid,jxid,hdxf,hddf) values(?,?,?,?,?)";
		return dao.runUpdate(sql, new String[]{model.getHdid(),model.getDwid(),model.getJxid(),model.getXf(),model.getDf()});
	}
	
}
