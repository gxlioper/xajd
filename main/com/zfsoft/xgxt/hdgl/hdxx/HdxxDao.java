/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.hdxx;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.hdgl.hdjdsh.HdjdshDao;
import org.apache.commons.lang3.ArrayUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @className	： HdxxDao
 * @description	： 活动信息dao(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-30 下午05:40:14
 * @version 	V1.0 
 */

public class HdxxDao extends SuperDAOImpl<HdxxForm>{

    public final static String random = "0";
    public final static String byTime = "1";
    public final static String randomAndByTime = "2";
    private HdjdshDao hdjdshDao = new HdjdshDao();
	/**
	 * @description	： TODO
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-30 下午05:40:41
	 * @param t
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HdxxForm t) throws Exception{		
		return null;
		
	}

	/**
	 * @description	： 查询活动列表
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-30 下午05:40:41
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HdxxForm t, User user)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		String pxfs = t.getPxfs();
		String[] jt_user = null;
		String userName = user.getUserName();
		sql.append(" select t.*");
		if(pxfs.equals("zjjb")){
			sql.append(",row_number() over(partition by t.hdbmzt order by t.hdkssj desc) rn ");
		}
		sql.append(" from (");
		sql.append(" select t1.*,t2.num ybmrs,t4.hdlxmc, xx.hdbq, xx.hdbqmc ,xxx.nlbq,xxx.nlbqmc,");
		if(pxfs.equals("wcj")){
			sql.append("t5.jdmc,t5.jdid,t5.jdsx,t5.jdlx,t8.jdsx xsjdsx,t8.jdid xsjdid,t8.jdmc xsjdmc,t8.shzt,");
			//sql.append("t9.dwzw,(case when t9.dwzw is not null then '1' else '0' end) sfzd,");
			sql.append("t10.dwid,t10.jdid xszdjdid,t10.jdsx xszdjdsx,t10.jdmc xszdjdmc,t10.shzt zdshzt,t11.dwzw,t11.hdpp zdhdpp,t15.hdpp,");
            sql.append("t11.shzt zdhdbmshzt,t15.shzt hdbmshzt,");
			sql.append("t6.jdid nextjdid,t6.jdmc nextjdmc,t6.jdlx nextjdlx,");
			sql.append("t13.jdid zdprejdid,t13.shzt zdpreshzt,t13.jdlx zdprejdlx,");
			sql.append("t14.jdid zdnextjdid,t14.jdmc zdnextjdmc,t14.jdlx zdnextjdlx,");
			sql.append("t9.jdid prejdid,t9.shzt preshzt,t9.jdlx prejdlx,t12.jdid firstjdid,t12.jdmc firstjdmc,");
			sql.append("(case when t8.shzt = '2' then (select b.jdmc from xg_hdgl_hdjdb a left join xg_hdgl_jdglb b on a.jdid = b.jdid where a.hdid = t1.hdid and a.jdsx = to_char(to_number(nvl(t8.jdsx,0))+1)) else '' end) btgjdmc,");
			sql.append("(case when t8.shzt = '2' then (select a.jdid from xg_hdgl_hdjdb a left join xg_hdgl_jdglb b on a.jdid = b.jdid where a.hdid = t1.hdid and a.jdsx = to_char(to_number(nvl(t8.jdsx,0))+1)) else '' end) btgjdid,");
			sql.append("(case when t10.shzt = '2' then (select b.jdmc from xg_hdgl_hdjdb a left join xg_hdgl_jdglb b on a.jdid = b.jdid where a.hdid = t1.hdid and a.jdsx = to_char(to_number(nvl(t10.jdsx,0))+1)) else '' end) zdbtgjdmc,");
			sql.append("(case when t10.shzt = '2' then (select a.jdid from xg_hdgl_hdjdb a left join xg_hdgl_jdglb b on a.jdid = b.jdid where a.hdid = t1.hdid and a.jdsx = to_char(to_number(nvl(t10.jdsx,0))+1)) else '' end) zdbtgjdid,");
		}
		sql.append(" (case when to_date(t1.bmkssj,'yyyy-mm-dd hh24:mi:ss') < sysdate and to_date(t1.bmjssj,'yyyy-mm-dd hh24:mi:ss') > sysdate then 'bm'");
		sql.append(" when to_date(t1.bmkssj,'yyyy-mm-dd hh24:mi:ss') > sysdate then 'wks'");
		sql.append(" when to_date(t1.bmjssj,'yyyy-mm-dd hh24:mi:ss') < sysdate then 'yjs'");	   
		sql.append(" end) bmztmc,");
		sql.append(" (CASE WHEN to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') > t1.hdjssj THEN '2'");
	    sql.append(" WHEN to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') < t1.hdkssj THEN '1'");
	    sql.append(" ELSE '0' END) hdbmzt,row_number() over(partition by t1.hdid order by t1.hdid) rnum");
		sql.append(" from xg_hdgl_hdxxb t1 ");

		sql.append(" left join (select t1.jgid, replace(wm_concat(t1.hdbq), ';', ',') hdbq, ");
		sql.append("  replace(wm_concat(t2.hdbqmc), ';', ',') hdbqmc ");
		sql.append("  from xg_hdgl_hdbqglb t1 left join xg_hdgl_hdbqdmb t2 on t1.hdbq = t2.hdbqdm ");
		sql.append("  group by t1.jgid) xx on t1.hdid = xx.jgid ");

		sql.append(" left join (select t1.jgid, replace(wm_concat(t1.nlbq), ';', ',') nlbq, ");
		sql.append("  replace(wm_concat(t2.nlbqmc), ';', ',') nlbqmc ");
		sql.append("  from xg_hdgl_nlbqglb t1 left join xg_hdgl_nlbqdmb t2 on t1.nlbq = t2.nlbqdm ");
		sql.append("  group by t1.jgid) xxx on t1.hdid = xxx.jgid ");

		sql.append(" left join (select hdid,count(1) num from xg_hdgl_hdryb group by hdid) t2 on t1.hdid = t2.hdid");
		sql.append(" left join xg_hdgl_hdlxdmb t4 on t1.hdlx = t4.hdlxdm");
		//根据系统当前时间获取该活动的阶段
		if(pxfs.equals("wcj")){
			sql.append(" left join (");
			sql.append(" select a.hdid,c.jdmc,b.jdid,b.jdsx,b.jdlx from xg_hdgl_hdxxb a");
			sql.append(" left join XG_HDGL_HDJDB b on a.hdid = b.hdid");
			sql.append(" left join xg_hdgl_jdglb c on b.jdid = c.jdid");
			//sql.append(" left join XG_HDGL_XSJDXXB d on d.jdid = b.jdid");
			//sql.append(" where to_date(b.jdkssj,'yyyy-mm-dd hh24:mi:ss') <= sysdate and to_date(b.jdjssj,'yyyy-mm-dd hh24:mi:ss') >= sysdate and b.jdlx = '1') t5 on t1.hdid = t5.hdid");
			sql.append(" where b.dqjdbj = '1') t5 on t1.hdid = t5.hdid");
			sql.append(" left join (select * from (select a.jdid,b.jdsx,a.hdid,a.xh,a.shzt,c.jdmc,row_number() over(partition by a.xh,a.hdid order by b.jdsx desc) rn");
			sql.append(" from xg_hdgl_xsjdxxb a");
			sql.append(" left join xg_hdgl_hdjdb b");
			sql.append(" on a.hdid = b.hdid");
			sql.append(" and a.jdid  = b.jdid");
			sql.append(" left join xg_hdgl_jdglb c on a.jdid = c.jdid");
			sql.append(" ) where rn = 1 and xh = ?) t8 on t1.hdid = t8.hdid");
			
			sql.append(" left join");
			sql.append(" (select * from");
			sql.append(" (select xh,hdid,jdid,shzt,jdlx,row_number() over(partition by xh,hdid order by jdsx desc) rn from");
			sql.append(" (select a.sqid,a.xh, a.hdid, b.jdid,b.jdsx,'1' jdlx,b.shzt");
			sql.append("	                from xg_hdgl_hdryb a");
			sql.append("	                left join (select a.hdsqid,a.xh,a.hdid,a.jdid,b.jdsx,a.shzt from xg_hdgl_xsjdxxb a,xg_hdgl_hdjdb b where a.hdid = b.hdid and a.jdid = b.jdid) b"); 
			sql.append("	                  on a.sqid = b.hdsqid");
			sql.append("	              union all select a.sqid,a.xh, a.hdid,c.jdid,c.jdsx,'2' jdlx,c.shzt");
			sql.append("	                from xg_hdgl_hdryb a");
			sql.append("	                left join (select a.hdsqid,a.xh,a.hdid,a.jdid,b.jdsx,a.shzt from (select a.*,b.xh from xg_hdgl_jsjdshxxb a,xg_hdgl_hdryb b where a.hdsqid = b.sqid)a,xg_hdgl_hdjdb b where a.hdid = b.hdid and a.jdid = b.jdid) c");
			sql.append("	                  on a.sqid = c.hdsqid) where jdid is not null and shzt is not null) where rn = 2 and xh = ?) t9");
			sql.append("	             on t1.hdid = t9.hdid");
			
			sql.append(" left join");
			sql.append(" (select * from");		 
			sql.append(" (select xh,hdid,jdid,shzt,jdlx,dwid,row_number() over(partition by xh,hdid order by jdsx desc) rn from");
			sql.append(" (select a.sqid,a.xh, a.hdid, b.jdid,b.jdsx,'1' jdlx,b.shzt,a.dwid");
			sql.append("	                from (select * from xg_hdgl_zdhdryb where dwzw = '1') a");
			sql.append("	                left join (select a.hdsqid,a.xh,a.hdid,a.jdid,b.jdsx,a.shzt from xg_hdgl_zdjdxxb a,xg_hdgl_hdjdb b where a.hdid = b.hdid and a.jdid = b.jdid) b"); 
			sql.append("	                  on a.sqid = b.hdsqid");
			sql.append("	              union all select a.sqid,a.xh, a.hdid,c.jdid,c.jdsx,'2' jdlx,c.shzt,a.dwid");
			sql.append("	                from (select * from xg_hdgl_zdhdryb where dwzw = '1') a");
			sql.append("	                left join (select a.hdsqid,a.xh,a.hdid,a.jdid,b.jdsx,a.shzt from (select a.*,b.xh from xg_hdgl_jsjdshxxb a,xg_hdgl_zdhdryb b where a.hdsqid = b.sqid and b.dwzw = '1')a,xg_hdgl_hdjdb b where a.hdid = b.hdid and a.jdid = b.jdid) c");
			sql.append("	                  on a.sqid = c.hdsqid) where jdid is not null and shzt is not null) a where rn = 2 and (xh = ? or exists (select 1 from xg_hdgl_zdhdryb b where a.dwid = b.dwid and b.xh = ?))) t13");
			sql.append("	             on t1.hdid = t13.hdid");
			
			sql.append(" left join (select a.hdid,a.jdid,b.jdmc from xg_hdgl_hdjdb a,xg_hdgl_jdglb b where a.jdid = b.jdid and a.jdsx = '1') t12 on t1.hdid = t12.hdid");
			sql.append(" left join (select * from (select a.hdid,a.jdid,a.jdlx,b.jdmc,row_number() over(partition by a.hdid order by a.jdsx asc) rn from xg_hdgl_hdjdb a left join xg_hdgl_jdglb b on a.jdid = b.jdid where not exists(select 1 from xg_hdgl_xsjdxxb c where a.hdid = c.hdid and a.jdid = c.jdid and c.xh = ?) and not exists(select 1 from (select a.*,b.xh from xg_hdgl_jsjdshxxb a left join xg_hdgl_hdryb b on a.hdsqid = b.sqid where a.shzt <> '3') c where a.hdid = c.hdid and a.jdid = c.jdid and c.xh = ? )");
			
			sql.append("and not exists (select 1");
			sql.append("        from (select a.*");
			sql.append("                from xg_hdgl_jsjdshxxb a");
			sql.append("                left join (select a.* from xg_hdgl_zdjdxxb a where exists (select 1 from xg_hdgl_zdhdryb b where a.dwid = b.dwid and b.xh = ?)) b");
			sql.append("                   on a.hdsqid = b.hdsqid");
			sql.append("               where a.shzt <> '3' and exists (select 1 from xg_hdgl_hdryb c where a.hdsqid = b.hdsqid and c.xh = ?)) d");
			sql.append("       where a.hdid = d.hdid");
			sql.append("         and a.jdid = d.jdid)");
			
			sql.append(" and not exists (select 1");
			sql.append("         from (select a.*");
			sql.append("                 from xg_hdgl_zdjdxxb a");
			sql.append("                  where exists (select 1 from xg_hdgl_zdhdryb b where a.dwid = b.dwid and b.xh = ?)");
			sql.append("               ) e");
			sql.append("        where a.hdid = e.hdid");
			sql.append("          and a.jdid = e.jdid)");
                      
               
			
			
			sql.append(" ) where rn = 1) t6 on t1.hdid = t6.hdid");
			
			sql.append(" left join (select * from (select a.hdid,a.jdid,a.jdlx,b.jdmc,row_number() over(partition by a.hdid order by a.jdsx asc) rn from xg_hdgl_hdjdb a left join xg_hdgl_jdglb b on a.jdid = b.jdid where not exists(select 1 from xg_hdgl_zdjdxxb c where a.hdid = c.hdid and a.jdid = c.jdid and (c.xh = ? or exists (select 1 from xg_hdgl_zdhdryb d where c.dwid = d.dwid and d.xh = ?))) and not exists(select 1 from (select a.*,b.xh,b.dwid from xg_hdgl_jsjdshxxb a left join xg_hdgl_zdhdryb b on a.hdsqid = b.sqid where a.shzt <> '3' and b.dwzw = '1') c where a.hdid = c.hdid and a.jdid = c.jdid and (c.xh = ? or exists (select 1 from xg_hdgl_zdhdryb d where c.dwid = d.dwid and d.xh = ?)))) where rn = 1) t14 on t1.hdid = t14.hdid");
			
			sql.append(" left join (select * from (select a.jdid,a.dwid,b.jdsx,a.hdid,a.xh,a.shzt,c.jdmc,row_number() over(partition by a.xh,a.hdid order by b.jdsx desc) rn");
			sql.append(" from xg_hdgl_zdjdxxb a");
			sql.append(" left join xg_hdgl_hdjdb b");
			sql.append(" on a.hdid = b.hdid");
			sql.append(" and a.jdid  = b.jdid");
			sql.append(" left join xg_hdgl_jdglb c on a.jdid = c.jdid) a");
			//sql.append(" )");
			sql.append(" where rn = 1 and (xh = ? or exists (select 1 from xg_hdgl_zdhdryb b where a.dwid = b.dwid and b.xh = ?))) t10 on t1.hdid = t10.hdid");
			sql.append(" left join xg_hdgl_zdhdryb t11 on t1.hdid = t11.hdid and t11.xh = ?");
            sql.append(" left join xg_hdgl_hdryb t15 on t1.hdid = t15.hdid and t15.xh = ?");

			
//			sql.append(" left join (select hdid,dwzw from xg_hdgl_zdhdryb where xh = ?) t9 on t1.hdid = t9.hdid");
		}
		sql.append(" where 1 = 1");
		sql.append(" and t1.hdzt = '1'");
		if(pxfs.equals("zxsx")){
			sql.append(" and not exists(select 1 from xg_hdgl_hdryb t7 where t1.hdid = t7.hdid and t7.xh = ?)");
			sql.append(" and not exists(select 1 from xg_hdgl_zdhdryb t8 where t1.hdid = t8.hdid and t8.xh = ?)");
			sql.append(" order by t1.fbsj desc");
			jt_user = new String[]{userName,userName};
		}else if(pxfs.equals("zjjb")){
			sql.append(" and not exists(select 1 from xg_hdgl_hdryb t7 where t1.hdid = t7.hdid and t7.xh = ?)");
			sql.append(" and not exists(select 1 from xg_hdgl_zdhdryb t8 where t1.hdid = t8.hdid and t8.xh = ?)");
			jt_user = new String[]{userName,userName};
		}else if(pxfs.equals("wcj")){
			sql.append(" and (exists(select 1 from xg_hdgl_hdryb t7 where t1.hdid = t7.hdid and t7.xh = ? and t7.shzt <> '0' and t7.shzt is not null)");
			sql.append(" or exists(select 1 from xg_hdgl_zdhdryb t8 where t1.hdid = t8.hdid and t8.xh = ? and t8.shzt <> '0' and t8.shzt is not null))");
			sql.append(" order by t1.hdkssj desc");
			jt_user = new String[]{userName,userName,userName,userName,userName,userName,userName,userName,userName,userName,userName,userName,userName,userName,userName,userName,userName,userName,userName};
		}
		sql.append(" ) t where t.rnum = 1");
		if(StringUtils.isNotNull(t.getHdlx())){
			sql.append(" and t.hdlx='" + t.getHdlx() +"'");
		}
		if(StringUtils.isNotNull(t.getHdmc())){
			sql.append(" and t.hdmc like '%" + t.getHdmc() + "%' ");
		}
		if(pxfs.equals("zjjb")){
			sql.append(" order by t.hdbmzt asc");
		}
		return getPageList(t, sql.toString(), jt_user);
	}

	/**
	 * 按时间倒序查询未开始、未报名的活动
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> zxHdxxList(HdxxForm t,User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select  t.*,case when (to_char(sysdate, 'yyyy-MM-dd hh24:mm:ss')>t.hdkssj) then '已开始' ");
		sql.append(" when (to_char(sysdate, 'yyyy-MM-dd hh24:mm:ss')>t.hdjssj) then '已结束' ");
		sql.append(" else '未开始' end hdztmc  from ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.* from Xg_Hdgl_Hdxxb a where a.bmdx = '本校公开报名' union ");
		sql.append(" select a.* from Xg_Hdgl_Hdxxb a where a.bmdx = '特定学院报名' and a.bmtddx = ? union ");
		sql.append(" select a.* from Xg_Hdgl_Hdxxb a where a.bmdx = '特定书院报名' and a.bmtddx = ? ");
		sql.append(" ) a where a.hdzt = '1' and a.hdjssj>to_char(sysdate,'yyyy-MM-dd hh24:mm:ss') ");
		sql.append(" union select * from Xg_Hdgl_Hdxxb where bmsf='0' ");
		sql.append(" and hdjssj>to_char(sysdate, 'yyyy-MM-dd hh24:mm:ss')  ) t");
		sql.append(" order by t.fbsj desc ");
		String[] input = new String[]{user.getUserDep(),user.getUserSyDep()};
		return getPageList(t,sql.toString(),input);
	}
	
	/**
	 * @description	： 获取活动详情列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-2-1 下午05:04:53
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public  List<HashMap<String, String>> getHdxqPageList(HdxxForm t, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		//String pxfs = t.getPxfs();
		//String[] jt_user = null;
		//String userName = user.getUserName();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.num ybmrs,t4.hdlxmc, c.hdbq, c.hdbqmc ,d.nlbq,d.nlbqmc ");
		sql.append(" from xg_hdgl_hdxxb t1 ");
		sql.append(" left join (select hdid,count(1) num from xg_hdgl_hdryb group by hdid) t2 on t1.hdid = t2.hdid ");
		sql.append(" left join xg_hdgl_hdlxdmb t4 on t1.hdlx = t4.hdlxdm ");

		sql.append(" left join (select a.jgid, replace(wm_concat(a.hdbq), ';', ',') hdbq, ");
		sql.append("  replace(wm_concat(b.hdbqmc), ';', ',') hdbqmc ");
		sql.append("  from xg_hdgl_hdbqglb a left join xg_hdgl_hdbqdmb b on a.hdbq = b.hdbqdm ");
		sql.append("  group by a.jgid) c on t1.hdid = c.jgid ");

		sql.append(" left join (select a.jgid, replace(wm_concat(a.nlbq), ';', ',') nlbq, ");
		sql.append("  replace(wm_concat(b.nlbqmc), ';', ',') nlbqmc ");
		sql.append("  from xg_hdgl_nlbqglb a left join xg_hdgl_nlbqdmb b on a.nlbq = b.nlbqdm ");
		sql.append("  group by a.jgid) d on t1.hdid = d.jgid ");

		sql.append(" where 1 = 1");
        if(StringUtils.isNotNull(t.getHdlx())){
            sql.append(" and t1.hdlx='" + t.getHdlx() +"'");
        }
        if(StringUtils.isNotNull(t.getHdmc())){
        	sql.append(" and t1.hdmc like '%" + t.getHdmc() + "%' ");
		}
//		if(pxfs.equals("zxsx")){
//			sql.append(" and not exists(select 1 from xg_hdgl_hdryb t7 where t1.hdid = t7.hdid and t7.xh = ?)");
//			sql.append(" order by t1.fbsj desc");
//			jt_user = new String[]{userName};
//		}else if(pxfs.equals("zjfb")){
//			sql.append(" and not exists(select 1 from xg_hdgl_hdryb t7 where t1.hdid = t7.hdid and t7.xh = ?)");
//			sql.append(" and to_date(t1.hdkssj,'yyyy-mm-dd hh24:mi:ss') < sysdate order by t1.hdkssj desc");
//			jt_user = new String[]{userName};
//		}else{
//			sql.append(" and exists(select 1 from xg_hdgl_hdryb t7 where t1.hdid = t7.hdid and t7.xh = ?)");
//			jt_user = new String[]{userName};
//		}
		sql.append(" order by t1.FBSJ DESC ) t where 1 = 1 ");
        String[] input = new String[]{};

        if(!isAdmin(user)){//不是管理员
            sql.append(" and fbr = ? ");
            input = new String[]{user.getUserName()};
        }

        sql.append(" order by fbsj desc ");
		return getPageList(t, sql.toString(), input);
	}

	public boolean isAdmin(User user){
		StringBuilder userSql = new StringBuilder();
		userSql.append(" select zdm from yhb where yhm = ? ");
		String zdm = dao.getOneRs(userSql.toString(),new String[]{user.getUserName()},"zdm");
		String[] zdms = zdm.split(",");
		boolean isAdmin = false;
		for(int i=0;i<zdms.length;i++){
			StringBuilder check = new StringBuilder();
			check.append(" select zmc from yhzb where zdm = ? ");
			String zmc = dao.getOneRs(check.toString(),new String[]{zdms[i]},"zmc");
			if(zmc.equals("超级管理员")){
				isAdmin = true;
			}
		}
		return isAdmin;
	}

	/**
	 * @description	： TODO
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-30 下午05:40:41
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(HdxxForm.class);
		super.setKey("hdid");
		super.setTableName("XG_HDGL_HDXXB");
		
	}
	
	/**
	 * @description	： 报名
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-31 上午11:44:43
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveBm(HdxxForm model) throws Exception{
		String hdid = model.getHdid();
		HdxxForm hdxx = getModel(hdid);
		List<String> in = new ArrayList<String>();

		in.add("cy");
		in.add(model.getGzjl());
		in.add(model.getZyzjl());
		in.add(model.getYynl());
		in.add(model.getZwms());
		in.add(model.getZyjn());
		in.add(model.getBmfj());
		if("0".equals(hdxx.getBmsfsh())){
			in.add("1");
		}else {
			in.add("5");//报名审核状态设为审核中
		}
		in.add(hdid);
		in.add(model.getXh());

		in.add(hdid);
		in.add(model.getXh());
		in.add("cy");
		in.add(model.getGzjl());
		in.add(model.getZyzjl());
		in.add(model.getYynl());
		in.add(model.getZwms());
		in.add(model.getZyjn());
		in.add(model.getBmfj());
		if("0".equals(hdxx.getBmsfsh())){
		    in.add("1");
        }else {
            in.add("5");//报名审核状态设为审核中
        }
		//String sql = "insert into xg_hdgl_hdryb(hdid,xh,zw,gzjl,zyzjl,yynl,zwms,zyjn,bmfj,shzt) values (?,?,?,?,?,?,?,?,?,?)";
		StringBuilder sql = new StringBuilder();
		sql.append("merge into xg_hdgl_hdryb t1 ");
		sql.append("using (select '");
		sql.append(hdid);
		sql.append("' hdid,'");
		sql.append(model.getXh());
		sql.append("' xh from dual ) t2 on (t1.hdid = t2.hdid and t1.xh = t2.xh) ");
		sql.append("WHEN MATCHED THEN update set zw = ?,gzjl = ?,zyzjl = ?,yynl = ?,zwms = ?,zyjn = ?,bmfj = ?,shzt = ? where hdid = ? and xh = ? ");
		sql.append("WHEN NOT MATCHED THEN insert (hdid,xh,zw,gzjl,zyzjl,yynl,zwms,zyjn,bmfj,shzt) values (?,?,?,?,?,?,?,?,?,?)");
//		if("0".equals(hdxx.getBmsfsh())){
//			sql = "insert into xg_hdgl_hdryb(hdid,xh,zw,gzjl,zyzjl,yynl,zwms,zyjn,shzt) values (?,?,?,?,?,?,?,?,?)";
//			in.add("5");//报名审核状态设为审核中
//		}
		return dao.runUpdate(sql.toString(), in.toArray(new String[in.size()]));
	}
	//获取报名表单简历单项字段
	public List<HashMap<String,String>> getBtZdList(String hdid){
		String sql = "select a.*,(select 1 from XG_HDGL_BMMDBTZD where hdid = ? and zd = a.zd) sfbt from XG_HDGL_BMMDJLDXZDB a order by sx";
		return dao.getListNotOut(sql,new String[]{hdid});
	}
	public boolean updateZd(String[] btzd,String hdid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from XG_HDGL_BMMDBTZD where hdid = ?");
		dao.runUpdate(sql.toString(),new String[]{hdid});
		sql = new StringBuilder();
		sql.append("insert all");
		for(int i=0;i<btzd.length;i++){
			sql.append(" into XG_HDGL_BMMDBTZD(hdid,zd) values('"+hdid+"','"+btzd[i]+"') ");
		}
		sql.append(" select 1 from dual ");
		return dao.runUpdate(sql.toString(),new String[]{});
	}
	//是否报名
	public boolean sfbm(String hdid,User user){
		String sql = "select count(1) isExist from xg_hdgl_hdryb where hdid = ? and xh = ?";
		return !"0".equals(dao.getOneRs(sql,new String[]{hdid,user.getUserName()},"isExist"));
	}
	//组队活动是否报名
	public boolean sfzdbm(String hdid,String xh){
		String sql =  "select count(1) isExist from xg_hdgl_zdhdryb where hdid = ? and xh = ?";
		return !"0".equals(dao.getOneRs(sql,new String[]{hdid,xh},"isExist"));
	}
	//获取活动报名人数
	public int bmrs(String hdid){
		String sql = "select nvl(count(1),0) bmrs from xg_hdgl_hdryb where hdid = ? group by hdid ";
		String bmrs = dao.getOneRs(sql,new String[]{hdid},"bmrs");
		return StringUtils.isNull(bmrs)? 0 : Integer.parseInt(bmrs);
	}
	/**
	 * @description	： 获取活动信息
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-2-1 上午10:58:00
	 * @return
	 */
	public HashMap<String, String> getHdxx(HdxxForm model){
		StringBuilder sb = new StringBuilder();
//		sb.append(" select a.*,b.hdlxmc,c.hdbq,d.rs from xg_hdgl_hdxxb a left join xg_hdgl_hdlxdmb b on a.hdlx = b.hdlxdm");
//		sb.append(" left join (select a.hdid,replace(wm_concat(b.hdbq),';',',') hdbq from xg_hdgl_hdxxb a left join xg_hdgl_hdbqglb b on a.hdid = b.jgid group by a.hdid) c on a.hdid = c.hdid");
//		sb.append(" left join (select hdid,count(xh) rs from xg_hdgl_hdryb group by hdid) d on a.hdid = d.hdid");
//		sb.append(" where a.hdid = ?");
		sb.append("select a.*, b.hdlxmc, c.hdbq,c.hdbqmc, d.rs, d1.shtgrs, d2.pprs,a.bmlx, ");
		sb.append("  f.nlbq, f.nlbqmc, i.jzlxmc , g.mc zxkclxmc,h.mc zjrzcmc ,bm.bmmc, ");
		sb.append(" (case when to_date(a.bmkssj,'yyyy-mm-dd hh24:mi:ss') < sysdate and to_date(a.bmjssj,'yyyy-mm-dd hh24:mi:ss') > sysdate then 'bm'");
		sb.append(" when to_date(a.bmkssj,'yyyy-mm-dd hh24:mi:ss') > sysdate then 'wks'");
		sb.append(" when to_date(a.bmjssj,'yyyy-mm-dd hh24:mi:ss') < sysdate then 'yjs'");
		sb.append(" end) bmztmc ");
		sb.append("from xg_hdgl_hdxxb a ");
		sb.append("left join xg_hdgl_hdlxdmb b on a.hdlx = b.hdlxdm ");
		sb.append("left join (select a.hdid, replace(wm_concat(b.hdbq), ';', ',') hdbq,replace(wm_concat(t.hdbqmc), ';', ',') hdbqmc ");
		sb.append("from xg_hdgl_hdxxb a ");
		sb.append("left join xg_hdgl_hdbqglb b on a.hdid = b.jgid ");
		sb.append("left join xg_hdgl_hdbqdmb t on b.hdbq = t.hdbqdm group by a.hdid) c on a.hdid = c.hdid ");

		sb.append("left join (select t3.jgid, replace(wm_concat(t3.nlbq), ';', ',') nlbq, ");
		sb.append("  replace(wm_concat(t4.nlbqmc), ';', ',') nlbqmc ");
		sb.append("  from xg_hdgl_nlbqglb t3 left join xg_hdgl_nlbqdmb t4 on t3.nlbq = t4.nlbqdm ");
		sb.append("  group by jgid) f on a.hdid = f.jgid ");
		sb.append("left join xg_hdgl_jzlxdmb i on a.jzlx = i.jzlxdm ");
		sb.append("left join xg_hdgl_zxkclxdmb g on a.zxkclx = g.dm ");
		sb.append("left join xg_hdgl_zjrzcdmb h on a.zjrzc = h.dm ");

		if("0".equals(model.getBmlx())){
            sb.append(" left join (select hdid,count(xh) rs from XG_HDGL_ZDHDRYB group by hdid) d on a.hdid = d.hdid ");
            sb.append(" left join (select hdid,count(xh) shtgrs from XG_HDGL_ZDHDRYB where shzt = '1' group by hdid) d1 on a.hdid = d1.hdid ");
            sb.append(" left join (select hdid,count(xh) pprs from XG_HDGL_ZDHDRYB where shzt = '1'and hdpp ='1' group by hdid) d2 on a.hdid = d2.hdid ");
        } else {
            sb.append(" left join (select hdid, count(xh) rs from xg_hdgl_hdryb group by hdid) d on a.hdid = d.hdid ");
            sb.append(" left join (select hdid, count(xh) shtgrs from xg_hdgl_hdryb where shzt = '1' group by hdid) d1 on a.hdid = d1.hdid ");
            sb.append(" left join (select hdid, count(xh) pprs from xg_hdgl_hdryb where shzt = '1'and hdpp ='1' group by hdid) d2 on a.hdid = d2.hdid ");
        }
		sb.append("left join ZXBZ_XXBMDM bm on a.bmtddx = bm.bmdm");
		sb.append(" where a.hdid = ? ");
		return dao.getMapNotOut(sb.toString(), new String[]{model.getHdid()});
	}
	//获取报名申请信息，审核查看用
	public HashMap<String,String> getBmSqxx(String sqid,String bmlx){
		StringBuilder sql = new StringBuilder();
		if("0".equals(bmlx)){
			sql.append("select * from XG_HDGL_ZDHDRYB where sqid = ? ");
		} else {
			sql.append("select * from xg_hdgl_hdryb where sqid = ? ");
		}

		return dao.getMapNotOut(sql.toString(),new String[]{sqid});
	}
	//获取报名申请信息，单人报名用（重新报名）
	public HashMap<String,String> getBmSqxx(HdxxForm t){
		StringBuilder sql = new StringBuilder();

		sql.append("select * from xg_hdgl_hdryb where xh = ? and hdid = ?");

		return dao.getMapNotOut(sql.toString(),new String[]{t.getXh(),t.getHdid()});
	}
	/**
	 * @description	： 根据活动id获取活动参与人员
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-2-2 上午09:22:45
	 * @return
	 */
	public List<HashMap<String,String>> getCyList(HdxxForm t, User user) throws Exception{
//		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
//		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String shzt = t.getShzt();
		String cxzd = t.getCxzd();
		String hdid = t.getHdid();
		String bmlx = t.getBmlx();
		
		String[] hdids = new String[]{hdid};
		String[] shzts = null;
		String[] cxzds = null;
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select t.* from (");
		sb.append(" select a.sqid,a.hdid,a.xh,a.bmsj,a.shzt,decode(a.shzt,'0','待审核','1','已通过','2','已拒绝','5','审核中',shzt) shztmc,");
		sb.append("decode(a.hdpp,'0','未派票','1','已派票',a.hdpp) hdpp,");
		sb.append(" b.xm,b.xydm,b.xymc,b.nj,b.bjdm,b.bjmc,b.zydm,b.zymc,b.xb,c.bmlx");
		if("0".equals(bmlx)){
            sb.append(" ,decode(a.dwzw,'0','队员','1','队长',' ') cymc");
		    sb.append(" ,a.dwid");
			sb.append(" from xg_hdgl_zdhdryb a");
		}else{
			sb.append(" from xg_hdgl_hdryb a");
		}
		sb.append(" left join view_xsbfxx b on a.xh = b.xh");
		sb.append(" left join xg_hdgl_hdxxb c on a.hdid = c.hdid");
		sb.append(" ) t");
		sb.append(" where 1=1");
		sb.append(" and t.hdid = ?");
		if(StringUtils.isNotNull(shzt)){
			sb.append(" and t.shzt = ?");
			shzts = new String[]{shzt};
		}
		if(StringUtils.isNotNull(cxzd)){
			sb.append(" and (t.xh like '%' || ? || '%'");
			sb.append(" or");
			sb.append(" t.xm like '%' || ? || '%')");
			cxzds = new String[]{cxzd,cxzd};
		}
		if("0".equals(bmlx)){
			sb.append(" order by dwid,bmsj");
		}
//		sb.append(searchTjByUser);
//		sb.append(searchTj);
		return getPageList(t, sb.toString(), StringUtils.joinStrArr(hdids,shzts,cxzds));
	}

	/**
	 * 获取有票的成员
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> gethasPCyList(HdxxForm t, User user) throws Exception{
		String hdid = t.getHdid();
		String[] hdids = new String[]{hdid};
		StringBuilder sb = new StringBuilder();
		sb.append("select * from(");
		sb.append(" select a.hdid,c.hdmc,a.xh,b.xm,'个人' bmlx,'' dwid,'' dwzw from xg_hdgl_hdryb a  ");
		sb.append(" left join view_xsbfxx b on a.xh = b.xh ");
		sb.append(" left join xg_hdgl_hdxxb c on a.hdid = c.hdid where a.shzt = '1' and a.hdpp = '1'  ");
		sb.append("  union select a.hdid,c.hdmc,a.xh,b.xm,'组队' bmlx,a.dwid , (case when a.dwzw = '1' then '队长' else '队员' end) dwzw from xg_hdgl_zdhdryb a ");
		sb.append(" left join view_xsbfxx b on a.xh = b.xh ");
		sb.append(" left join xg_hdgl_hdxxb c on a.hdid = c.hdid where a.shzt = '1' and a.hdpp = '1'  ");
		sb.append(") where 1 = 1 and hdid = ? ");
		return getPageList(t, sb.toString(), StringUtils.joinStrArr(hdids));
	}
	/**
	 * @description	： 保存审核状态
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-2-5 上午11:38:14
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public boolean updateShzt(HdxxForm t) throws Exception{
		String[] ids = t.getIds();
		String shzt = t.getShzt();
		String shyj = t.getShyj();
		String userName = t.getXh();
		List<String[]> list = new ArrayList<String[]>();
		for(String id : ids){
			String[] strArr = new String[]{shzt,userName,shyj,id};
			list.add(strArr);
		}
		StringBuilder sb = new StringBuilder();
		if(t.getBmlx().equals("1")){
			sb.append("update xg_hdgl_hdryb set shzt = ?,shr = ?,shsj = to_char(sysdate,'YYYY-MM-dd hh24:mm:ss'),shyj = ? where sqid = ?");
		}else{
			sb.append("update xg_hdgl_zdhdryb set shzt = ?,shr = ?,shsj = to_char(sysdate,'YYYY-MM-dd hh24:mm:ss'),shyj = ? where sqid = ?");
		}
		return dao.runBatchBoolean(sb.toString(), list);
	}
	
	/**
	 * @description	：  获取该活动已通过人数
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-2-6 上午09:25:06
	 * @return
	 */
	public Integer getPeoplePermitted(String hdid){
		String sql = "select count(xh) rn from xg_hdgl_hdryb a where hdid = ? and shzt = '1'";
		String rs = dao.getOneRs(sql, new String[]{hdid}, "rn");
		return Integer.valueOf(rs);
	}
	
	/**
	 * @description	： 获取人数
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-2-6 上午10:27:14
	 * @param hdid
	 * @return
	 */
	public Integer getRs(String hdid){
		String sql = "select nvl(bmrs,'0') bmrs from xg_hdgl_hdxxb a where hdid = ?";
		String rs = dao.getOneRs(sql, new String[]{hdid}, "bmrs");
		return Integer.valueOf(rs);
	}
	
	/**
	 * @description	： 获取活动阶段信息(个人类型)
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-2-6 下午04:25:05
	 * @param t
	 * @param user
	 * @return
	 */
	public HashMap<String,String> getHdjdInfo(HdxxForm t,User user){
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.hdid,c.guid,c.xdth,c.shzt,c.fjid,c.mc,c.jb,c.zddw,c.zdjs,c.nrjs,a.hdmc,a.hdkssj,a.hdjssj,a.hddd,a.hb,b.hdlxmc,d.num jds,a.bmlx,e.jdid thjdid,e.shyj thshyj,a.sflx");
		sb.append(" from xg_hdgl_hdxxb a");
		sb.append(" left join xg_hdgl_hdlxdmb b on a.hdlx = b.hdlxdm");
		sb.append(" left join xg_hdgl_xsjdxxb c on a.hdid = c.hdid and c.jdid = ? and c.xh = ?");
		sb.append(" left join (select count(1) num,hdid from XG_HDGL_HDJDB group by hdid) d on a.hdid = d.hdid");
		sb.append(" left join (select * from (select a.jdid,a.hdsqid,a.shyj,row_number() over(partition by a.hdsqid order by a.shsj desc) rn from xg_hdgl_jsjdshxxb a left join xg_hdgl_hdjdb b on a.jdid = b.jdid where a.shzt = '3') where rn = 1) e on c.hdsqid = e.hdsqid");
		sb.append(" where a.hdid = ?");
		return dao.getMapNotOut(sb.toString(), new String[]{t.getJdid(),user.getUserName(),t.getHdid()});
	}

	/**
	 *  教师获取活动阶段信息.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-02-10 15:07
	 * @param t
	 * @return java.util.HashMap<java.lang.String,java.lang.String>
	 * @throw
	 */
	public HashMap<String,String> getHdjdInfo(HdxxForm t){
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.hdid,a.hdmc,a.hdkssj,a.hdjssj,a.hddd,a.hb,a.bmlx,b.hdlxmc,d.num jds,a.sflx from xg_hdgl_hdxxb a");
		sb.append(" left join xg_hdgl_hdlxdmb b on a.hdlx = b.hdlxdm");
		sb.append(" left join (select count(1) num,hdid from XG_HDGL_HDJDB group by hdid) d on a.hdid = d.hdid");
		sb.append(" where a.hdid = ?");
		return dao.getMapNotOut(sb.toString(), new String[]{t.getHdid()});

	}
	
	/**
	 * @description	： 获取活动阶段
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-2-7 上午08:42:13
	 * @param t
	 * @return
	 */
	public List<HashMap<String,String>> getHdjdList(HdxxForm t){
		String sql = "select a.jdid,a.jdsx,b.jdmc,a.dqjdbj from XG_HDGL_HDJDB a left join XG_HDGL_JDGLB b on a.jdid = b.jdid where a.hdid = ? order by a.jdsx asc";
		return dao.getListNotOut(sql, new String[]{t.getHdid()} );		
	}

	/**
	 *  根据活动id修改活动状态，用于下架活动.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-02-06 15:33
	 * @param hdid
	 * @return boolean
	 * @throw Exception
	 */
	public boolean hdxjById(String hdid) throws Exception {

		String sql = "UPDATE xg_hdgl_hdxxb SET HDZT = '0' WHERE HDID = ?";
		return dao.runUpdate(sql,new String[]{hdid});
	}

	/**
	 *  获取活动管理列表数据.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-02-05 15:00
	 * @param t
	 * @param user
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	public  List<HashMap<String, String>> getHdglPageList(HdxxForm t, User user) throws Exception {

		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();

		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.num ybmrs,t4.hdlxmc,");
		sql.append("CASE hdzt WHEN '1' THEN (");
		sql.append(" CASE WHEN to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') > t1.hdjssj THEN '已结束' ");
		sql.append(" WHEN to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') < t1.hdkssj THEN '未开始' ");
		sql.append(" ELSE '进行中' END ) ");
		sql.append(" ELSE '已下架' END hdjxzt ");
		sql.append(" from xg_hdgl_hdxxb t1");
		sql.append(" left join (select hdid,count(1) num from xg_hdgl_hdryb group by hdid) t2 on t1.hdid = t2.hdid");
		sql.append(" left join xg_hdgl_hdlxdmb t4 on t1.hdlx = t4.hdlxdm");
		sql.append(" ) t where 1 = 1 ");

        String bmsql = "select * from YHB t where yhm = ? and (zdm like '%0001' or zdm = '0001' or zdm like '%0001,%') and qx = '1'";
        HashMap<String,String> yhmap = dao.getMapNotOut(bmsql,new String[]{user.getUserName()});//查看用户是否为管理员
		if(yhmap.size()==0){//不是管理员
		    sql.append(" and fbr =  '"+user.getUserName()+"'");
        }

		sql.append(searchTj);

		return getPageList(t, sql.toString(), inputV);
	}
	
	
	/**
	 * @description	： 更新学生阶段填写
	 * @author 		： lj（1282）
	 * @date 		：2018-2-7 下午02:17:32
	 * @param t
	 * @return
	 * @throws Exception 
	 */
	public boolean updateStudentStage(HdxxForm t) throws Exception{
		String sql;
		String[] params;
		String bmlx = t.getBmlx();
		//是否打分活动
		boolean sfdfhd = hdjdshDao.isExist(t.getHdid());
		if(StringUtils.isNull(t.getGuid())){

			HashMap<String,String> map = getCurrentHdjdInfo(t.getHdid(),t.getJdid());
			String sqid;
			String sb;
			if(bmlx.equals("1")){
				sqid = getHdsqid(t);
				sql = "insert into XG_HDGL_XSJDXXB(jdid,hdid,xh,xdth,fjid,sqsj,shzt,hdsqid,mc,jb,zddw,zdjs,nrjs) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				params = new String[]{t.getJdid(),t.getHdid(),t.getXh(),t.getXdth(),t.getFjid(),GetTime.getTimeByFormat("yyyy-MM-dd HH24:mi:ss"),"5",sqid
				,t.getMc(),t.getJb(),t.getZddw(),t.getZdjs(),t.getNrjs()};

				sb = "update xg_hdgl_hdryb set nextjdid = ? where sqid = ? ";
			} else {
				sqid = getHdsqidForZd(t);
				sql = "insert into xg_hdgl_zdjdxxb(jdid,hdid,dwid,xh,xdth,fjid,sqsj,shzt,hdsqid,mc,jb,zddw,zdjs,nrjs) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				params = new String[]{t.getJdid(),t.getHdid(),t.getDwid(),t.getXh(),t.getXdth(),t.getFjid(),GetTime.getTimeByFormat("yyyy-MM-dd HH24:mi:ss"),"5",sqid
				,t.getMc(),t.getJb(),t.getZddw(),t.getZdjs(),t.getNrjs()};

				sb = "update XG_HDGL_ZDHDRYB set nextjdid = ? where sqid = ? ";
			}

			if(sfdfhd){
				dao.runUpdate(sb,new String[]{map.get("nextjdid"),sqid});
				//申请绑定打分专家（3个）
				zpzjbd(t.getHdid(),sqid,t.getJdid());
			}
		}else{
			if(bmlx.equals("1")){
				sql = "update XG_HDGL_XSJDXXB set xdth = ?,fjid = ?,sqsj = ?,shzt = ?,mc=?,jb=?,zddw=?,zdjs=?,nrjs=? where guid = ?";
				params = new String[]{t.getXdth(),t.getFjid(),GetTime.getTimeByFormat("yyyy-MM-dd HH24:mi:ss"),"5",t.getMc(),t.getJb(),t.getZddw(),t.getZdjs(),t.getNrjs(),t.getGuid()};
			}else{
				sql = "update xg_hdgl_zdjdxxb set xdth = ?,fjid = ?,sqsj = ?,shzt = ?,mc=?,jb=?,zddw=?,zdjs=?,nrjs=? where guid = ?";
				params = new String[]{t.getXdth(),t.getFjid(),GetTime.getTimeByFormat("yyyy-MM-dd HH24:mi:ss"),"5",t.getMc(),t.getJb(),t.getZddw(),t.getZdjs(),t.getNrjs(),t.getGuid()};
			}
		}
		 return dao.runUpdate(sql, params);
	}

	/**
	 * 获取下一阶段
	 * @param hdid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getCurrentHdjdInfo(String hdid,String jdid) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.*,b.jdmc,(SELECT JDID FROM XG_HDGL_HDJDB WHERE HDID = a.HDID AND JDSX = (a.JDSX + 1) ");
		sql.append(" ) nextjdid");
		sql.append(" FROM XG_HDGL_HDJDB a ");
		sql.append(" LEFT JOIN XG_HDGL_JDGLB b ON a.JDID = b.JDID");
		sql.append(" WHERE a.HDID = ? AND a.jdid = ? ");

		return dao.getMapNotOut(sql.toString(),new String[]{hdid,jdid});
	}
	/**
	 * 申请绑定打分专家（最多3个）
	 * @param hdid 活动id
	 * @param hdsqid 申请id
	 * @param jdid 当前阶段id
	 * @return
	 * @throws Exception
	 */
	public boolean zpzjbd(String hdid, String hdsqid,String jdid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.jdid from (");
		sql.append(" select a.jdid,a.hdid,row_number() over(partition by hdid order by to_number(jdsx)) rn");
		sql.append(" from XG_HDGL_HDJDB a where a.jdsx > ");
		sql.append(" (select jdsx from XG_HDGL_HDJDB where hdid = a.hdid and jdid = ?)");
//		sql.append(" and sfsldf = '1'");
		sql.append(" ) t where t.rn <='1' and t.hdid = ?");
		//找到需要打分的阶段
		String nextDfJd = dao.getOneRs(sql.toString(),new String[]{jdid,hdid},"jdid");
		//为需要打分的阶段的每个申请的绑定最多3个打分专家
//		HashMap<String,List<String>> jdZjMap = new HashMap<String, List<String>>();
//		for(String s : jdids){
			List<String> zjzghs = new ArrayList<String>(3);
			sql = new StringBuilder();
			//取得每个阶段分配的所有教师
			sql.append("select zjzgh from XG_HDGL_HDZJTCYB where hdid = ? and jdid = ?");
			List<String> zghAll = dao.getList(sql.toString(),new String[]{hdid,nextDfJd},"zjzgh");

			HashSet<Integer> numSet = new HashSet<Integer>();
            if(zghAll.size() < 3){
                this.randomNum(0,zghAll.size(),zghAll.size(),numSet);
            }else{
                this.randomNum(0,zghAll.size(),3,numSet);
            }
			Iterator iterator = numSet.iterator();
			while (iterator.hasNext()){
				zjzghs.add(zghAll.get((Integer) iterator.next()));
			}
//			jdZjMap.put(nextDfJd,zjzghs);
//		}
		//先删后插
		sql = new StringBuilder();
		sql.append(" delete from XG_HDGL_ZPZJGXB where hdsqid = ? and jdid = ?");
		dao.runUpdate(sql.toString(),new String[]{hdsqid,nextDfJd});
		sql = new StringBuilder();
		sql.append("insert into XG_HDGL_ZPZJGXB(hdsqid,zgh,jdid) values(?,?,?)");
		List<String[]> params = new ArrayList<String[]>();
//		for(String key : jdZjMap.keySet()){
//			List<String> zghList = jdZjMap.get(key);
			for(int j=0;j<zjzghs.size();j++){
				String[] param = {hdsqid,zjzghs.get(j),nextDfJd};
				params.add(param);
			}
//		}

		return dao.runBatchBoolean(sql.toString(),params);
	}
	//生成n个指定范围的不重复的随机数
	private void randomNum(int min, int max, int n,HashSet<Integer> set){
		if (n > (max - min + 1) || max < min) {
			return;
		}
		while(true){
			// 调用Math.random()方法
			int num = (int) (Math.random() * (max - min)) + min;
			set.add(num);// 将不同的数存入HashSet中
			if(set.size() >= n){
				break;
			}
		}
	}

	/**
	 *  获取专家团成员列表.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-02-07 14:15
	 * @param hdxxForm
	 * @param sffp 是否分配，0：未分配，1：已分配
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getZjtcyList(HdxxForm hdxxForm,String sffp) throws Exception {

		String searchTj = SearchService.getSearchTj(hdxxForm.getSearchModel());
		String[] inputV = SearchService.getTjInput(hdxxForm.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String[] ids = hdxxForm.getIds();
		List<String> paraArray = new ArrayList<String>();
		sql.append(" select  distinct a.* ");
		sql.append(" from (select a.yhm,");
		sql.append(" a.xm,");
		sql.append(" b.bmmc,");
		sql.append(" a.szbm bmdm,");
		sql.append(" decode(c.xb, '1', '男', '2', '女', c.xb) xb");
		sql.append(" from yhb a");
		sql.append(" left join zxbz_xxbmdm b");
		sql.append(" on a.szbm = b.bmdm");
		sql.append(" left join fdyxxb c");
		sql.append(" on a.yhm = c.zgh) a");
		sql.append("  where 1 = 1");

		//其实按照这种逻辑，如果多选，根本就无需向后台传入ids，因为根本就不用
		//暂未优化
		if(ids.length ==1){
			sql.append(" and ");
			if("1".equals(sffp)){
				sql.append("exists (");
			}else {
				sql.append("not exists (");
			}
			sql.append("  SELECT 1 FROM XG_HDGL_HDZJTCYB WHERE zjzgh = a.YHM ");
			
			sql.append(" and jdid = ?");
			paraArray.add(hdxxForm.getJdid());
			
			sql.append(" AND hdid IN (");
			for (int i = 0; i < ids.length; i++) {
				sql.append("?");
				paraArray.add(ids[i]);
				if(i != ids.length-1){
					sql.append(",");
				}
			}
			sql.append(")");
			for (int i = 0; i < inputV.length; i++) {
				paraArray.add(inputV[i]);
			}
			
			sql.append(")");
		}
		
		inputV = paraArray.toArray(new String[]{});
		sql.append(searchTj);
		return getPageList(hdxxForm, sql.toString(), inputV);
	}

	/**
	 *  专家团成员保存分配.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-02-08 17:13
	 * @param ids
	 * @param zghs
	 * @return boolean
	 * @throw
	 */
	public boolean zjtcyFp(String[] ids, String[] zghs,String jdid) throws Exception {

		List<String[]> paraList = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO xg_hdgl_hdzjtcyb t1 ");
		sql.append(" USING (SELECT ? AS hdid,? AS zjzgh, ? as jdid FROM dual) t2  ");
		sql.append(" ON (t1.hdid = t2.hdid and t1.zjzgh = t2.zjzgh and t1.jdid = t2.jdid)  ");
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append(" INSERT (hdid,zjzgh,jdid) VALUES (t2.hdid,t2.zjzgh,t2.jdid)  ");

		for(String id:ids){
			for(String zjzgh:zghs){
				paraList.add(new String[]{id,zjzgh,jdid});
			}
		}
		return dao.runBatchBoolean(sql.toString(),paraList);
	}

	/**
	 *  专家团成员取消分配..
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-02-08 17:39
	 * @param hdid
	 * @param zghs
	 * @return boolean
	 * @throw Exception
	 */
	public boolean zjtcyQxfp(String hdid, String[] zghs,String jdid) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append(" delete from xg_hdgl_hdzjtcyb where zjzgh in(");
		for (int i = 0; i < zghs.length; i++) {
			sql.append("?");
			if(i != zghs.length-1){
				sql.append(",");
			}
		}
		sql.append(" ) and hdid = ? ");
		sql.append(" and jdid = ? ");
		String [] input = ArrayUtils.addAll(zghs,hdid,jdid);
		return dao.runUpdate(sql.toString(), input);
	}

	/**
	 * 获取当前活动已有队伍数
	 * @param hdid
	 * @return
	 */
	public int getDws(String hdid){
		String sql = "select count(1) num from (select distinct(dwid) from XG_HDGL_ZDHDRYB where hdid = ?)";
		String num = dao.getOneRs(sql,new String[]{hdid},"num");
		return StringUtil.isNull(num) ? 0 : Integer.parseInt(num);
	}

	/**
	 * 获取队伍人数
	 * @param hdid
	 * @param dwid
	 * @return
	 */
	public int getDwrs(String hdid,String dwid){
		String sql = "select count(1) num from XG_HDGL_ZDHDRYB where hdid = ? and dwid = ?";
		String num = dao.getOneRs(sql,new String[]{hdid,dwid},"num");
		return StringUtil.isNull(num) ? 0 : Integer.parseInt(num);
	}
	/**
	 * @description	： 获取队伍编号
	 * @author 		： lj（1282）
	 * @date 		：2018-2-9 下午02:47:27
	 * @return
	 */
	/*public Integer getDwbh(String hdid){
		String sql = "select count(1) num from (select distinct(dwid) from XG_HDGL_ZDHDRYB where hdid = ?)";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{}, "num"));
	}*/
	public String getDwid(HdxxForm form,String xh) throws Exception{
	    String sql = "select dwid from XG_HDGL_ZDHDRYB where hdid = ? and xh = ? ";
	    return dao.getOneRs(sql,new String[]{form.getHdid(),xh},"dwid");
    }
	/**
	 * @description	： 插入组队成员
	 * @author 		： lj（1282）
	 * @date 		：2018-2-9 下午03:26:43
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean insertZdCy(HdxxForm model,String xhs[]) throws Exception{
		List<String> inValue = new ArrayList<>();
		List<String> dzValue = new ArrayList<>();
		dzValue.add(model.getHdid());
		dzValue.add(model.getDwid());
		dzValue.add(model.getDwzw());
		dzValue.add(model.getXh());
		dzValue.add(GetTime.getTimeByFormat("yyyy-MM-dd HH24:mi:ss"));
		dzValue.add("1");//已审核
		dzValue.add(model.getGzjl());
		dzValue.add(model.getZyzjl());
		dzValue.add(model.getYynl());
		dzValue.add(model.getZwms());
		dzValue.add(model.getZyjn());
		dzValue.add(model.getBmfj());
		dzValue.add("1");//已派票
		inValue.addAll(dzValue);

		dzValue.set(2,"0");
		for(int i=0;i<xhs.length;i++){
			dzValue.set(3,xhs[i]);
			inValue.addAll(dzValue);
		}
		StringBuilder sql = new StringBuilder();
		sql.append("insert all into xg_hdgl_zdhdryb(hdid,dwid,dwzw,xh,bmsj,shzt,gzjl,zyzjl,yynl,zwms,zyjn,bmfj,hdpp) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		for(int i=0;i<xhs.length;i++){
			sql.append(" into xg_hdgl_zdhdryb(hdid,dwid,dwzw,xh,bmsj,shzt,gzjl,zyzjl,yynl,zwms,zyjn,bmfj,hdpp) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		}
		sql.append(" SELECT 1 FROM DUAL");
		return dao.runUpdate(sql.toString(),inValue.toArray(new String[inValue.size()]));

	}

	public boolean insertZdCy(HdxxForm model) throws Exception{
		String sql = "insert into xg_hdgl_zdhdryb(hdid,dwid,dwzw,xh,bmsj,shzt,gzjl,zyzjl,yynl,zwms,zyjn,bmfj,hdpp) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return dao.runUpdate(sql, new String[]{model.getHdid(),model.getDwid(),model.getDwzw()
				,model.getXh(),GetTime.getTimeByFormat("yyyy-MM-dd HH24:mi:ss"),(model.getDwzw().equals("1")?"1":"1")//设置审核状态为已审核
				,model.getGzjl(),model.getZyzjl(),model.getYynl(),model.getZwms(),model.getZyjn(),"1"});
	}
	
	/**
	 * @description	： 获取队伍列表
	 * @author 		： lj（1282）
	 * @date 		：2018-2-10 下午03:20:31
	 * @param hdid TODO
	 * @return
	 */
	public List<HashMap<String,String>> getDwList(String userName, String hdid){
		String sql = "select a.dwid,a.hdid,a.xh,b.xm dzxm ";
		sql += ",(select count(xh) from xg_hdgl_zdhdryb group by hdid,dwid having hdid = a.hdid and dwid = a.dwid) rs ";
		sql += " from xg_hdgl_zdhdryb a left join view_xsbfxx b on a.xh = b.xh where a.dwzw = '1' and hdid = ? and a.xh <> ? order by dwid";
		return dao.getListNotOut(sql, new String[]{hdid,userName});
	}
	
	/**
	 * @description	： 根据活动id和学号获取活动申请id
	 * @author 		： lj（1282）
	 * @date 		：2018-2-11 下午02:56:29
	 * @param model
	 * @return
	 */
	public String getHdsqid(HdxxForm model){
		String sql = "select sqid from xg_hdgl_hdryb where hdid = ? and xh = ?";
		return dao.getOneRs(sql, new String[]{model.getHdid(),model.getXh()}, "sqid");
	}
	
	/**
	 * @description	： 根据活动id和学号获取组队活动申请id
	 * @author 		： lj（1282）
	 * @date 		：2018-2-25 下午03:16:19
	 * @param model
	 * @return
	 */
	public String getHdsqidForZd(HdxxForm model){
		String sql = "select sqid from xg_hdgl_zdhdryb where hdid = ? and xh = ?";
		return dao.getOneRs(sql, new String[]{model.getHdid(),model.getXh()}, "sqid");
	}
	
	/**
	 * @description	： 获取组队活动信息
	 * @author 		： lj（1282）
	 * @date 		：2018-2-25 下午04:13:25
	 * @param t
	 * @param user
	 * @return
	 */
	public HashMap<String,String> getHdjdInfoForZd(HdxxForm t,User user){
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.hdid,c.guid,c.xdth,c.fjid,c.shzt,a.hdmc,a.hdkssj,a.hdjssj,a.hddd,a.hb,b.hdlxmc,d.num jds,a.bmlx,e.dwid,f.jdid thjdid,f.shyj thshyj ");
		sb.append(" ,a.sflx ");
		sb.append(" from xg_hdgl_hdxxb a ");
		sb.append(" left join xg_hdgl_hdlxdmb b on a.hdlx = b.hdlxdm");
		sb.append(" left join xg_hdgl_zdjdxxb c on a.hdid = c.hdid and c.jdid = ? and c.xh = ?");
		sb.append(" left join (select count(1) num,hdid from XG_HDGL_HDJDB group by hdid) d on a.hdid = d.hdid");
		sb.append(" left join (select hdid,dwid from xg_hdgl_zdhdryb where hdid = ? and xh = ?) e on a.hdid = e.hdid");
		sb.append(" left join (select * from (select a.jdid,a.hdsqid,a.shyj,row_number() over(partition by a.hdsqid order by a.shsj desc) rn from xg_hdgl_jsjdshxxb a left join xg_hdgl_hdjdb b on a.jdid = b.jdid where a.shzt = '3') where rn = 1) f on c.hdsqid = f.hdsqid");
		sb.append(" where a.hdid = ?");
		return dao.getMapNotOut(sb.toString(), new String[]{t.getJdid(),user.getUserName(),t.getHdid(),user.getUserName(),t.getHdid()});
	}
	
	/**
	 * @description	： 插入评论
	 * @author 		： lj（1282）
	 * @date 		：2018-3-5 上午10:49:53
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean insertPl(HdxxForm t,User user) throws Exception{
		String plid = UniqID.getInstance().getUniqIDHash();
		//SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String plsj = getCurrentTime();
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into xg_hdgl_hdplb(hdid,plid,plrid,plrxm,plsj,plnr,hfbj,hfplid) values(?,?,?,?,?,?,?,?)");
		return dao.runUpdate(sb.toString(), new String[]{t.getHdid(),plid,user.getUserName(),user.getRealName(),plsj,t.getPlnr(),"0",plid});
	}
	
	/**
	 * @description	： 获取评论内容
	 * @author 		： lj（1282）
	 * @date 		：2018-3-5 下午03:30:39
	 * @param t
	 * @param user
	 * @return
	 */
	public HashMap<String,String> getPlnrByUser(HdxxForm t,User user) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(" select plid,plnr from xg_hdgl_hdplb where hdid = ? and plrid = ? and hfbj = '0'");
		return dao.getMapNotOut(sb.toString(), new String[]{t.getHdid(),user.getUserName()});
	}
	
	/**
	 * @description	： 更新评论内容
	 * @author 		： lj（1282）
	 * @date 		：2018-3-5 下午04:09:50
	 * @return
	 * @throws Exception 
	 */
	public boolean updatePlnr(HdxxForm t) throws Exception{
		String sql = "update xg_hdgl_hdplb set plnr = ? where plid = ?";
		return dao.runUpdate(sql, new String[]{t.getPlnr(),t.getPlid()});
	}
	
	/**
	 * @description	： 获取活动评论list
	 * @author 		： lj（1282）
	 * @date 		：2018-3-8 上午10:39:43
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getHdplList(HdxxForm t) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.*,nvl(a.dzs,0) as dzss,(case when length(plnr) > 10 then substr(plnr,1,10) || '..' else plnr end) plnrr from xg_hdgl_hdplb a where a.hdid = ? order by a.plsj desc");
		return  getPageList(t, sb.toString(), new String[]{t.getHdid()});
	}
	
	/**
	 * @description	： 获取活动评论信息
	 * @author 		： lj（1282）
	 * @date 		：2018-3-9 上午09:11:46
	 * @param plid
	 * @return
	 */
	public HashMap<String,String> getHdPlInfo(String plid){
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from xg_hdgl_hdplb a ");
		sb.append(" left join xg_hdgl_hdxxb b on a.hdid = b.hdid where plid = ?");
		return dao.getMapNotOut(sb.toString(), new String[]{plid});
	}
	
	/**
	 * @description	： 回复评论
	 * @author 		： lj（1282）
	 * @date 		：2018-3-9 上午11:19:48
	 * @param t
	 * @return
	 * @throws Exception 
	 */
	public boolean replyPl(HdxxForm t,User user) throws Exception{
		String plid = UniqID.getInstance().getUniqIDHash();
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into xg_hdgl_hdplb(hdid,plid,plrid,plrxm,plsj,plnr,hfbj,hfrid,hfrxm,hfplid) values(?,?,?,?,?,?,?,?,?,?)");
		return dao.runUpdate(sb.toString(), new String[]{t.getHdid(),plid,user.getUserName(),user.getRealName(),getCurrentTime(),t.getPlnr(),t.getHfbj(),t.getHfrid(),t.getHfrxm(),t.getHfplid()});
	}
	
	/**
	 * @description	： TODO
	 * @author 		： lj（1282）
	 * @date 		：2018-3-9 下午03:32:42
	 * @return
	 * @throws Exception 
	 */
	public boolean delPl(String plid) throws Exception{
		String sql = "delete from xg_hdgl_hdplb where plid = ?";
		return dao.runUpdate(sql, new String[]{plid});
	}
	
	/**
	 * @description	： 获取当前时间
	 * @author 		： lj（1282）
	 * @date 		：2018-3-9 上午11:32:22
	 * @return
	 */
	public String getCurrentTime(){
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String plsj = sm.format(new Date());
		return plsj;
	}
	

	/**
	 *  获取队伍列表.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-08 17:52
	 * @param hdxxForm
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getDwList(HdxxForm hdxxForm) {
		StringBuilder sql = new StringBuilder("SELECT a.*,b.XM,decode(a.dwzw,'1','队长','成员') dwzwmc FROM xg_hdgl_zdhdryb a ");
		sql.append("LEFT JOIN XSXXB b ON a.xh = b.xh WHERE a.HDID = ? AND a.DWID = ?");
		return dao.getListNotOut(sql.toString(),new String[]{hdxxForm.getHdid(),hdxxForm.getDwid()});
	}

	/**
	 *  获取个人活动报名信息.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-09 16:42
	 * @param model
	 * @return java.util.HashMap<java.lang.String,java.lang.String>
	 * @throw
	 */
	public HashMap<String,String> getGrbmxx(HdxxForm model) {

		StringBuilder sql = new StringBuilder("SELECT a.*,b.xm FROM XG_HDGL_HDRYB a ");
		sql.append("LEFT JOIN XSXXB b ON a.xh = b.xh ");
		sql.append("WHERE a.HDID = ? AND a.XH = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{model.getHdid(),model.getXh()});
	}
	
	/**
	 * @description	： 点赞
	 * @author 		： lj（1282）
	 * @date 		：2018-3-13 下午04:57:42
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean dz(HdxxForm model) throws Exception{
		String sql = "update xg_hdgl_hdplb set dzs = to_char(to_number(nvl(dzs,0)) + 1) where plid = ?";
		return dao.runUpdate(sql, new String[]{model.getPlid()});
	}
	
	/**
	 * @description	： 获取下阶段活动信息
	 * @author 		： lj（1282）
	 * @date 		：2018-3-15 下午03:07:26
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getNextHdInfo(HdxxForm t) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.hdid,c.guid,c.shyj,a.hdmc,a.hdkssj,a.hdjssj,a.hddd,a.hb,b.hdlxmc,d.num jds,a.bmlx, ");
		sb.append(" decode(c.shzt,'1','通过','2','不通过','3','退回', '审核中') shztmc,c.shzt,");
		sb.append(" decode(e.shzt,'1','通过','2','不通过','3','退回', '审核中') preshztmc,");
		sb.append(" e.shzt preshzt,");
		sb.append(" f.shztmc pjshztmc,f.shyj pjshyj,f.jxmc,f.xf,f.pffz,f.jdmc pjjdmc");
		sb.append(" from xg_hdgl_hdxxb a");
		sb.append(" left join xg_hdgl_hdlxdmb b");
		sb.append(" on a.hdlx = b.hdlxdm");
		sb.append(" left join (select * from (select a.*,rownum rn from (select a.hdid,a.shyj,a.shzt,a.guid from xg_hdgl_jsjdshxxb a,xg_hdgl_hdryb b where a.hdsqid = b.sqid and a.jdid = ? and a.hdid = ? and b.xh = ? order by a.shsj desc)a) where rn = 1) c");
		sb.append(" on a.hdid = c.hdid ");
		sb.append(" left join (select count(1) num,hdid from XG_HDGL_HDJDB group by hdid) d on a.hdid = d.hdid");
		sb.append(" left join (select shzt,hdid from xg_hdgl_xsjdxxb where xh = ? and hdid = ? and jdid = ?) e");
		sb.append(" on a.hdid = e.hdid");
		sb.append(" left join (select * from (");
		sb.append(" select a.hdsqid,a.hdid,a.jdid,a.shzt,decode(a.shzt,'1','通过','2','不通过','3','退回', '审核中') shztmc,a.shyj,b.jxmc,a.xf,a.pffz,d.jdmc,");
		sb.append(" row_number() over(partition by a.hdsqid,a.hdid order by a.shsj desc) rn from xg_hdgl_jsjdshxxb a ");
		sb.append(" left join xg_hdgl_hdryb c on a.hdsqid = c.sqid");
		sb.append(" left join xg_hdgl_jdglb d on a.jdid = d.jdid");
		sb.append(" left join xg_hdgl_hdjxb b on a.hdid = b.hdid and a.jxid = b.jxdm where c.xh = ? and c.hdid = ?) where rn = 1) f ");	
		sb.append(" on a.hdid = f.hdid");
		sb.append(" where a.hdid = ?");
		return dao.getMapNotOut(sb.toString(), new String[]{t.getJdid(),t.getHdid(),t.getXh(),t.getXh(),t.getHdid(),t.getPrejdid(),t.getXh(),t.getHdid(),t.getHdid()});
		
		
	}
	
	/**
	 * @description	： TODO
	 * @author 		： lj（1282）
	 * @date 		：2018-3-26 下午03:12:07
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getNextHdInfoForZd(HdxxForm t) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.hdid,c.guid,c.shyj,a.hdmc,a.hdkssj,a.hdjssj,a.hddd,a.hb,b.hdlxmc,d.num jds,a.bmlx, ");
		sb.append(" decode(c.shzt,'1','通过','2','不通过','3','退回', '审核中') shztmc,c.shzt,");
		sb.append(" decode(e.shzt,'1','通过','2','不通过','3','退回', '审核中') preshztmc,");
		sb.append(" e.shzt preshzt,");
		sb.append(" f.shztmc pjshztmc,f.shyj pjshyj,f.jxmc,f.xf,f.pffz,f.jdmc pjjdmc");
		sb.append(" from xg_hdgl_hdxxb a");
		sb.append(" left join xg_hdgl_hdlxdmb b");
		sb.append(" on a.hdlx = b.hdlxdm");
		sb.append(" left join (select * from (select a.*,rownum rn from (select a.hdid,a.shyj,a.shzt,a.guid from xg_hdgl_jsjdshxxb a,xg_hdgl_zdhdryb b where a.hdsqid = b.sqid and a.jdid = ? and a.hdid = ? and (b.xh = ? or exists (select 1 from xg_hdgl_zdhdryb c where b.dwid = c.dwid and c.xh = ?)) order by a.shsj desc)a) where rn = 1)  c");
		sb.append(" on a.hdid = c.hdid ");
		sb.append(" left join (select count(1) num,hdid from XG_HDGL_HDJDB group by hdid) d on a.hdid = d.hdid");
		sb.append(" left join (select a.shzt,a.hdid from xg_hdgl_zdjdxxb a where a.hdid = ? and a.jdid = ? and (a.xh = ? or exists (select 1 from xg_hdgl_zdhdryb c where a.dwid = c.dwid and c.xh = ?))) e");
		sb.append(" on a.hdid = e.hdid");
		
		sb.append(" left join (select * from (");
		sb.append(" select a.hdsqid,a.hdid,a.jdid,a.shzt,decode(a.shzt,'1','通过','2','不通过','3','退回', '审核中') shztmc,a.shyj,b.jxmc,a.xf,a.pffz,d.jdmc,");
		sb.append(" row_number() over(partition by a.hdsqid,a.hdid order by a.shsj desc) rn from xg_hdgl_jsjdshxxb a ");
		sb.append(" left join (select * from xg_hdgl_zdhdryb a where a.xh = ? or exists (select 1 from xg_hdgl_zdhdryb b where a.dwid = b.dwid and b.xh = ?)) c on a.hdsqid = c.sqid");
		sb.append(" left join xg_hdgl_jdglb d on a.jdid = d.jdid");
		sb.append(" left join xg_hdgl_hdjxb b on a.hdid = b.hdid and a.jxid = b.jxdm where c.hdid = ?) where rn = 1) f ");	
		sb.append(" on a.hdid = f.hdid");
		
		sb.append(" where a.hdid = ?");
		return dao.getMapNotOut(sb.toString(), new String[]{t.getJdid(),t.getHdid(),t.getXh(),t.getXh(),t.getHdid(),t.getPrejdid(),t.getXh(),t.getXh(),t.getXh(),t.getXh(),t.getHdid(),t.getHdid()});
		
		
	}
	
	/**
	 * @description	： 获取活动标签
	 * @author 		： lj（1282）
	 * @date 		：2018-4-2 下午05:03:37
	 * @param hdid
	 * @return
	 * @throws SQLException 
	 */
	public List<String> getHdbq(String hdid) throws SQLException{
		String sql = "select hdbq from xg_hdgl_hdbqglb where jgid = ?";
		return dao.getList(sql, new String[]{hdid}, "hdbq");
	}
	
	/**
	 * @description	： 根据活动id删除阶段
	 * @author 		： lj（1282）
	 * @date 		：2018-4-3 下午05:01:33
	 * @param hdid
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteJdByHdId(String hdid) throws Exception{
		String sql = "delete from xg_hdgl_hdjdb where hdid = ?";
		return dao.runDelete(sql, new String[]{hdid}) > 0;
	}
	
	/**
	 * @description	： 根据活动id删除阶段名称
	 * @author 		： lj（1282）
	 * @date 		：2018-4-3 下午05:20:07
	 * @param hdid
	 * @return
	 * @throws Exception
	 */
	public boolean deleteJdmcByHdid(String hdid) throws Exception{
		String sql = "delete from xg_hdgl_jdglb where hdid = ?";
		return dao.runDelete(sql, new String[]{hdid}) > 0;
	}
	
	/**
	 * @description	： 根据活动id插入阶段节点
	 * @author 		： lj（1282）
	 * @date 		：2018-4-4 上午09:07:59
	 * @param form
	 * @return
	 * @throws SQLException 
	 */
	public boolean insertHdJdById(HdxxForm form) throws SQLException{
		String[] jdids = form.getIds();
		String[] jdmcs = form.getJdmcs();
		List<String[]> list = new ArrayList<String[]>();
		for(int i = 0; i < jdids.length; i++){
			String[] strArr = new String[2];
			strArr[0] = jdids[i];
			strArr[1] = jdmcs[i];
			list.add(strArr);
		}
		String sql = "insert into xg_hdgl_jdglb(jdid,jdmc) values(?,?)";
		return dao.runBatch(sql, list).length > 0;
	}
	
	/**
	 * @description	： 根据活动id插入阶段关联表
	 * @author 		： lj（1282）
	 * @date 		：2018-4-4 上午11:07:54
	 * @param form
	 * @return
	 * @throws SQLException
	 */
	public boolean insertJdglByHdId(HdxxForm form) throws SQLException{
		String hdid = form.getHdid();
		String[] jdids = form.getJdids();
		String[] jdkssjs = form.getJdkssjs();
		String[] jdjssjs = form.getJdjssjs();
		String[] hdlxs = form.getJdlxs();
		String[] sfsljxs = form.getSfsljxs();
		String[] sfslxfs = form.getSfslxfs();
		String[] sfsldfs = form.getSfsldfs();
		String[] sftjs = form.getSftjs();
		List<String[]> hdjdList = new ArrayList<String[]>();
		List<String[]> hdglList = new ArrayList<String[]>();
		
		for(int i = 0; i < jdids.length; i++){
			String[] strArr = new String[10];
			strArr[0] = jdids[i];
			strArr[1] = hdid;
			strArr[2] = String.valueOf(i+1);
			strArr[3] = jdkssjs[i];
			strArr[4] = jdjssjs[i];
			strArr[5] = hdlxs[i];
			strArr[6] = sfsljxs[i];
			strArr[7] = sfslxfs[i];
			strArr[8] = sfsldfs[i];
			strArr[9] = sftjs[i];
			hdjdList.add(strArr);
		}
		String sql = "insert into xg_hdgl_hdjdb(jdid,hdid,jdsx,jdkssj,jdjssj,jdlx,sfsljx,sfslxf,sfsldf,sftj) values(?,?,?,?,?,?,?,?,?,?)";
		boolean result = dao.runBatch(sql, hdjdList).length > 0;
		if(result){
			String[] jdmcs = form.getJdmcs();
			for(int i= 0; i < jdids.length; i++){
				String[] strArr = new String[2];
				strArr[0] = jdids[i];
				strArr[1] = jdmcs[i];
				hdglList.add(strArr);
			}
			String sql_hdgl = "insert into xg_hdgl_jdglb(jdid,jdmc) values(?,?)";
			result = dao.runBatch(sql_hdgl, hdglList).length > 0;
		}
		return result;
	}
	
	/**
	 * @description	： 统计阶段内的人数
	 * @author 		： lj（1282）
	 * @date 		：2018-4-8 上午09:31:51
	 * @param form
	 * @return
	 */
	public boolean countJdrs(HdxxForm form){
		String sql = " select count(1) rn from (select hdid from xg_hdgl_xsjdxxb where hdid = ? union all select hdid from xg_hdgl_jsjdshxxb where hdid = ?)";
		String hdid = form.getHdid();
		return Integer.valueOf(dao.getOneRs(sql, new String[]{hdid,hdid}, "rn")) > 0;
	}
	
	/**
	 * @description	： 删除阶段
	 * @author 		： lj（1282）
	 * @date 		：2018-4-8 上午11:53:21
	 * @param form
	 * @throws Exception
	 */
	public void deleteJds(HdxxForm form) throws Exception{
		String hdid = form.getHdid();
		String jdgl_delete = " delete from xg_hdgl_jdglb a where exists (select 1 from xg_hdgl_hdjdb b where a.jdid = b.jdid and b.hdid = ?) ";
		dao.runDelete(jdgl_delete, new String[]{hdid});
		String hdjd_delete = " delete from xg_hdgl_hdjdb where hdid = ?";
		dao.runDelete(hdjd_delete, new String[]{hdid});
	}
	
	/**
	 * @description	： 获取活动阶段列表
	 * @author 		： lj（1282）
	 * @date 		：2018-4-8 下午03:05:39
	 * @return
	 */
	public List<HashMap<String,String>> getHdjdLists(HdxxForm form) throws Exception{
		String sql = " select a.*,decode(a.sfsldf,'0','否','1','是') sfsldfmc,decode(a.sfsljx,'0','否','1','是') sfsljxmc" +
				",decode(a.sfslxf,'0','否','1','是') sfslxfmc,b.jdmc" +
				",decode(a.sftj,'0','否','1','是') sftjmc"+
				" from xg_hdgl_hdjdb a left join xg_hdgl_jdglb b on a.jdid = b.jdid where a.hdid = ?";
		return dao.getListNotOut(sql, new String[]{form.getHdid()});
	}
	
	/**
	 * @description	： 获取结果列表
	 * @author 		： lj（1282）
	 * @date 		：2018-4-11 下午02:50:47
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPageListForResult(HdxxForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.hdmc,a.jxmc,a.xf,a.df,a.xb from");
		sql.append(" (select * from (select a.jdid,a.hdid,c.jxid,c.xf,c.df,b.jdsx,d.bmlx,d.hdmc,a.hdsqid,a.shzt,e.xh,f.xm,f.xydm,f.bjdm,f.zydm,f.nj,f.xymc,f.bjmc,f.zymc,f.xb,g.jxmc,");
		sql.append(" row_number() over(partition by a.hdid, a.hdsqid order by b.jdsx desc) rn");
		sql.append(" from xg_hdgl_jsjdshxxb a");
		sql.append(" left join xg_hdgl_hdjdb b");
		sql.append(" on a.hdid = b.hdid and a.jdid = b.jdid");
		sql.append(" left join (select hdid, hdsqid, jxid, xf,df from xg_hdgl_jsjdshxxb where jxid is not null or xf is not null or xf is not null) c on a.hdsqid = c.hdsqid");
		sql.append(" left join xg_hdgl_hdxxb d on a.hdid = d.hdid");
		sql.append(" left join xg_hdgl_xsjdxxb e on a.hdsqid = e.hdsqid");
		sql.append(" left join view_xsbfxx f on e.xh = f.xh");
		sql.append(" left join xg_hdgl_hdjxb g on c.jxid = g.jxdm and c.hdid = g.hdid where d.bmlx = '1') where rn = 1) a");
		sql.append(" where exists (select 1 from (select * from (select hdid,jdid,row_number() over(partition by hdid order by jdsx desc) rn from xg_hdgl_hdjdb) where rn = 1) e  where a.hdid = e.hdid and a.jdid = e.jdid) and a.shzt = '1' or a.shzt='2' ");
		sql.append(" union all select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.hdmc,a.jxmc,a.hdxf xf,a.hddf df,a.xb");
		sql.append(" from (select b.xh,a.hdxf,a.hddf,c.jxmc,d.xm,d.xydm,d.zydm,d.bjdm,d.nj,d.xymc,d.zymc,d.bjmc,f.hdmc,d.xb");
		sql.append(" from xg_hdgl_zdhdxxb a");
		sql.append(" left join xg_hdgl_zdhdryb b on a.dwid = b.dwid and a.hdid = b.hdid");
		sql.append(" left join xg_hdgl_hdjxb c on a.hdid = c.hdid and a.jxid = c.jxdm");
		sql.append(" left join view_xsbfxx d on b.xh = d.xh");
		sql.append(" left join xg_hdgl_hdxxb f on a.hdid = f.hdid) a");
		sql.append(" union all select a.xh,b.xm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,a.hdmc,a.hdjx jxmc,a.hdxf xf,a.hddf df,b.xb from xg_hdgl_hdbljgb a left join view_xsxxb b on a.xh = b.xh");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	public List<HashMap<String, String>> getPageListForHdxjgByStu(HdxxForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(x.xh) as hdgs,x.xh,x.xm,x.xydm,x.xymc,x.zydm,x.zymc,x.bjdm,x.bjmc,x.zybj,x.zybjmc,x.xb,x.sydm,x.symc from (");
		sql.append(" select t.*,t2.sydm,t2.symc from (select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.zybj,a.zybjmc,a.hdmc,a.jxmc,a.xf,a.xb from");
		sql.append(" (select * from (select a.jdid,a.hdid,c.jxid,c.xf,b.jdsx,d.bmlx,d.hdmc,a.hdsqid,a.shzt,e.xh,f.xm,f.xydm,f.bjdm,f.zydm,f.nj,f.xymc,f.bjmc,f.zymc,f.zybjmc,f.zybj,f.xb,g.jxmc,");
		sql.append(" row_number() over(partition by a.hdid, a.hdsqid order by b.jdsx desc) rn");
		sql.append(" from xg_hdgl_jsjdshxxb a");
		sql.append(" left join xg_hdgl_hdjdb b");
		sql.append(" on a.hdid = b.hdid and a.jdid = b.jdid");
		sql.append(" left join (select hdid, hdsqid, jxid, xf from xg_hdgl_jsjdshxxb where jxid is not null or xf is not null) c on a.hdsqid = c.hdsqid");
		sql.append(" left join xg_hdgl_hdxxb d on a.hdid = d.hdid");
		sql.append(" left join xg_hdgl_hdbqglb h on d.hdid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%活动%'");
		sql.append(" left join xg_hdgl_xsjdxxb e on a.hdsqid = e.hdsqid");
		sql.append(" left join view_xsbfxx f on e.xh = f.xh");
		sql.append(" left join xg_hdgl_hdjxb g on c.jxid = g.jxdm and c.hdid = g.hdid where d.bmlx = '1') where rn = 1) a");
		sql.append(" where exists (select 1 from (select * from (select hdid,jdid,row_number() over(partition by hdid order by jdsx desc) rn  from xg_hdgl_hdjdb) where rn = 1) e  where a.hdid = e.hdid and a.jdid = e.jdid) and a.shzt = '1'");
		sql.append(" union all select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.zybj,a.zybjmc,a.hdmc,a.jxmc,a.hdxf xf,a.xb");
		sql.append(" from (select b.xh,a.hdxf,c.jxmc,d.xm,d.xydm,d.zydm,d.bjdm,d.zybj,d.zybjmc,d.nj,d.xymc,d.zymc,d.bjmc,f.hdmc,d.xb");
		sql.append(" from xg_hdgl_zdhdxxb a");
		sql.append(" left join xg_hdgl_zdhdryb b on a.dwid = b.dwid and a.hdid = b.hdid");
		sql.append(" left join xg_hdgl_hdjxb c on a.hdid = c.hdid and a.jxid = c.jxdm");
		sql.append(" left join view_xsbfxx d on b.xh = d.xh");
		sql.append(" left join xg_hdgl_hdxxb f on a.hdid = f.hdid ");
		sql.append(" left join xg_hdgl_hdbqglb h on f.hdid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%活动%') a");
		sql.append(" union all select a.xh,b.xm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.zybj,b.zybjmc,a.hdmc,a.hdjx jxmc,a.hdxf xf,b.xb from xg_hdgl_hdbljgb a left join view_xsxxb b on a.xh = b.xh");
		sql.append(" left join xg_hdgl_hdbqglb h on a.jgid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%活动%'");
		sql.append(" ) t left join XG_XTWH_SYBJGLB t1 on t.bjdm = t1.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t2 on t1.sydm = t2.sydm ");
		sql.append(" where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" ) x group by x.xh,x.xm,x.xydm,x.xymc,x.zydm,x.zymc,x.bjdm,x.bjmc,x.xb,x.zybj,x.zybjmc,x.sydm,x.symc");

		return getPageList(t, sql.toString(), inputV);
	}

	//学生端查询
	public List<HashMap<String, String>> getPageListForHdxjgForStu(HdxxForm t, User user) throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		/*String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");*/
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct hdmc,x.xh,x.xm,x.xydm,x.xymc,x.zydm,x.zymc,x.bjdm,x.bjmc,x.zybj,x.zybjmc,x.xb,x.sydm,x.symc,x.xf,x.jxmc from (");
		sql.append(" select t.*,t2.sydm,t2.symc from (select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.zybj,a.zybjmc,a.hdmc,a.jxmc,a.xf,a.xb from");
		sql.append(" (select * from (select a.jdid,a.hdid,c.jxid,c.xf,b.jdsx,d.bmlx,d.hdmc,a.hdsqid,a.shzt,e.xh,f.xm,f.xydm,f.bjdm,f.zydm,f.nj,f.xymc,f.bjmc,f.zymc,f.zybjmc,f.zybj,f.xb,g.jxmc,");
		sql.append(" row_number() over(partition by a.hdid, a.hdsqid order by b.jdsx desc) rn");
		sql.append(" from xg_hdgl_jsjdshxxb a");
		sql.append(" left join xg_hdgl_hdjdb b");
		sql.append(" on a.hdid = b.hdid and a.jdid = b.jdid");
		sql.append(" left join (select hdid, hdsqid, jxid, xf from xg_hdgl_jsjdshxxb where jxid is not null or xf is not null) c on a.hdsqid = c.hdsqid");
		sql.append(" left join xg_hdgl_hdxxb d on a.hdid = d.hdid");
		sql.append(" left join xg_hdgl_hdbqglb h on d.hdid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%活动%'");
		sql.append(" left join xg_hdgl_xsjdxxb e on a.hdsqid = e.hdsqid");
		sql.append(" left join view_xsbfxx f on e.xh = f.xh");
		sql.append(" left join xg_hdgl_hdjxb g on c.jxid = g.jxdm and c.hdid = g.hdid where d.bmlx = '1') where rn = 1) a");
		sql.append(" where exists (select 1 from (select * from (select hdid,jdid,row_number() over(partition by hdid order by jdsx desc) rn  from xg_hdgl_hdjdb) where rn = 1) e  where a.hdid = e.hdid and a.jdid = e.jdid) ");// and a.shzt = '1'
		sql.append(" union all select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.zybj,a.zybjmc,a.hdmc,a.jxmc,a.hdxf xf,a.xb");
		sql.append(" from (select b.xh,a.hdxf,c.jxmc,d.xm,d.xydm,d.zydm,d.bjdm,d.zybj,d.zybjmc,d.nj,d.xymc,d.zymc,d.bjmc,f.hdmc,d.xb");
		sql.append(" from xg_hdgl_zdhdxxb a");
		sql.append(" left join xg_hdgl_zdhdryb b on a.dwid = b.dwid and a.hdid = b.hdid");
		sql.append(" left join xg_hdgl_hdjxb c on a.hdid = c.hdid and a.jxid = c.jxdm");
		sql.append(" left join view_xsbfxx d on b.xh = d.xh");
		sql.append(" left join xg_hdgl_hdxxb f on a.hdid = f.hdid ");
		sql.append(" left join xg_hdgl_hdbqglb h on f.hdid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%活动%') a");
		sql.append(" union all select a.xh,b.xm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.zybj,b.zybjmc,a.hdmc,a.hdjx jxmc,a.hdxf xf,b.xb from xg_hdgl_hdbljgb a left join view_xsxxb b on a.xh = b.xh");
		sql.append(" left join xg_hdgl_hdbqglb h on a.jgid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%活动%'");
		sql.append(" ) t left join XG_XTWH_SYBJGLB t1 on t.bjdm = t1.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t2 on t1.sydm = t2.sydm ");
		sql.append(" where xh = '"+user.getUserName()+"' ");
		/*sql.append(searchTjByUser);*/
		sql.append(searchTj);
		sql.append(" ) x");

		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getPageListForHdxjg(HdxxForm t) throws Exception{
//		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
//		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.hdmc,a.jxmc,a.xf,a.xb from");
		sql.append(" (select * from (select a.jdid,a.hdid,c.jxid,c.xf,b.jdsx,d.bmlx,d.hdmc,a.hdsqid,a.shzt,e.xh,f.xm,f.xydm,f.bjdm,f.zydm,f.nj,f.xymc,f.bjmc,f.zymc,f.xb,g.jxmc,");
		sql.append(" row_number() over(partition by a.hdid, a.hdsqid order by b.jdsx desc) rn");
		sql.append(" from xg_hdgl_jsjdshxxb a");
		sql.append(" left join xg_hdgl_hdjdb b");
		sql.append(" on a.hdid = b.hdid and a.jdid = b.jdid");
		sql.append(" left join (select hdid, hdsqid, jxid, xf from xg_hdgl_jsjdshxxb where jxid is not null or xf is not null) c on a.hdsqid = c.hdsqid");
		sql.append(" left join xg_hdgl_hdxxb d on a.hdid = d.hdid");
		sql.append(" left join xg_hdgl_hdbqglb h on d.hdid = h.jgid ");
		sql.append(" left join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%活动%'");
		sql.append(" left join xg_hdgl_xsjdxxb e on a.hdsqid = e.hdsqid");
		sql.append(" left join view_xsbfxx f on e.xh = f.xh");
		sql.append(" left join xg_hdgl_hdjxb g on c.jxid = g.jxdm and c.hdid = g.hdid where d.bmlx = '1') where rn = 1) a");
		sql.append(" where exists (select 1 from (select * from (select hdid,jdid,row_number() over(partition by hdid order by jdsx desc) rn  from xg_hdgl_hdjdb) where rn = 1) e  where a.hdid = e.hdid and a.jdid = e.jdid) and a.shzt = '1'");
		sql.append(" union all select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.hdmc,a.jxmc,a.hdxf xf,a.xb");
		sql.append(" from (select b.xh,a.hdxf,c.jxmc,d.xm,d.xydm,d.zydm,d.bjdm,d.nj,d.xymc,d.zymc,d.bjmc,f.hdmc,d.xb");
		sql.append(" from xg_hdgl_zdhdxxb a");
		sql.append(" left join xg_hdgl_zdhdryb b on a.dwid = b.dwid and a.hdid = b.hdid");
		sql.append(" left join xg_hdgl_hdjxb c on a.hdid = c.hdid and a.jxid = c.jxdm");
		sql.append(" left join view_xsbfxx d on b.xh = d.xh");
		sql.append(" left join xg_hdgl_hdxxb f on a.hdid = f.hdid ");
		sql.append(" left join xg_hdgl_hdbqglb h on f.hdid = h.jgid ");
		sql.append(" left join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%活动%') a");
		sql.append(" union all select a.xh,b.xm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,a.hdmc,a.hdjx jxmc,a.hdxf xf,b.xb from xg_hdgl_hdbljgb a left join view_xsxxb b on a.xh = b.xh");
		sql.append(" left join xg_hdgl_hdbqglb h on a.jgid = h.jgid ");
		sql.append(" left join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%活动%'");
		sql.append(" ) t where 1 = 1 ");
//		sql.append(searchTjByUser);
//		sql.append(searchTj);
		sql.append(" and t.xh=?");
		return getPageList(t, sql.toString(), new String[]{t.getXh()});
	}
	public List<HashMap<String, String>> getPageListForJzjgByStu(HdxxForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(x.xh) as hdgs,x.xh,x.xm,x.xydm,x.xymc,x.zydm,x.zymc,x.bjdm,x.bjmc,x.zybj,x.zybjmc,x.xb,x.sydm,x.symc from (");
		sql.append(" select t.*,t2.sydm,t2.symc from (select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.zybj,a.zybjmc,a.hdmc,a.jxmc,a.xf,a.xb from");
		sql.append(" (select * from (select a.jdid,a.hdid,c.jxid,c.xf,b.jdsx,d.bmlx,d.hdmc,a.hdsqid,a.shzt,e.xh,f.xm,f.xydm,f.bjdm,f.zydm,f.zybj,f.zybjmc,f.nj,f.xymc,f.bjmc,f.zymc,f.xb,g.jxmc,");
		sql.append(" row_number() over(partition by a.hdid, a.hdsqid order by b.jdsx desc) rn");
		sql.append(" from xg_hdgl_jsjdshxxb a");
		sql.append(" left join xg_hdgl_hdjdb b");
		sql.append(" on a.hdid = b.hdid and a.jdid = b.jdid");
		sql.append(" left join (select hdid, hdsqid, jxid, xf from xg_hdgl_jsjdshxxb where jxid is not null or xf is not null) c on a.hdsqid = c.hdsqid");
		sql.append(" left join xg_hdgl_hdxxb d on a.hdid = d.hdid");
		sql.append(" left join xg_hdgl_hdbqglb h on d.hdid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%讲座%'");
		sql.append(" left join xg_hdgl_xsjdxxb e on a.hdsqid = e.hdsqid");
		sql.append(" left join view_xsbfxx f on e.xh = f.xh");
		sql.append(" left join xg_hdgl_hdjxb g on c.jxid = g.jxdm and c.hdid = g.hdid where d.bmlx = '1') where rn = 1) a");
		sql.append(" where exists (select 1 from (select * from (select hdid,jdid,row_number() over(partition by hdid order by jdsx desc) rn  from xg_hdgl_hdjdb) where rn = 1) e  where a.hdid = e.hdid and a.jdid = e.jdid) and a.shzt = '1'");
		sql.append(" union all select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.zybj,a.zybjmc,a.hdmc,a.jxmc,a.hdxf xf,a.xb");
		sql.append(" from (select b.xh,a.hdxf,c.jxmc,d.xm,d.xydm,d.zydm,d.bjdm,d.nj,d.xymc,d.zymc,d.bjmc,d.zybj,d.zybjmc,f.hdmc,d.xb");
		sql.append(" from xg_hdgl_zdhdxxb a");
		sql.append(" left join xg_hdgl_zdhdryb b on a.dwid = b.dwid and a.hdid = b.hdid");
		sql.append(" left join xg_hdgl_hdjxb c on a.hdid = c.hdid and a.jxid = c.jxdm");
		sql.append(" left join view_xsbfxx d on b.xh = d.xh");
		sql.append(" left join xg_hdgl_hdxxb f on a.hdid = f.hdid ");
		sql.append(" left join xg_hdgl_hdbqglb h on f.hdid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%讲座%') a");
		sql.append(" union all select a.xh,b.xm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.zybj,b.zybjmc,a.hdmc,a.hdjx jxmc,a.hdxf xf,b.xb from xg_hdgl_hdbljgb a left join view_xsxxb b on a.xh = b.xh");
		sql.append(" left join xg_hdgl_hdbqglb h on a.jgid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%讲座%'");
		sql.append(" ) t left join XG_XTWH_SYBJGLB t1 on t.bjdm = t1.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t2 on t1.sydm = t2.sydm ");
		sql.append(" where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" ) x group by x.xh,x.xm,x.xydm,x.xymc,x.zydm,x.zymc,x.bjdm,x.bjmc,x.xb,x.zybj,x.zybjmc,x.sydm,x.symc");
		return getPageList(t, sql.toString(), inputV);
	}

	public List<HashMap<String, String>> getPageListForJzjgForStu(HdxxForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct hdmc,x.xh,x.xm,x.xydm,x.xymc,x.zydm,x.zymc,x.bjdm,x.bjmc,x.zybj,x.zybjmc,x.xb,x.symc,x.sydm,x.xf,x.jxmc from (");
		sql.append(" select t.*,t2.sydm,t2.symc from (select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.zybj,a.zybjmc,a.hdmc,a.jxmc,a.xf,a.xb from");
		sql.append(" (select * from (select a.jdid,a.hdid,c.jxid,c.xf,b.jdsx,d.bmlx,d.hdmc,a.hdsqid,a.shzt,e.xh,f.xm,f.xydm,f.bjdm,f.zydm,f.zybj,f.zybjmc,f.nj,f.xymc,f.bjmc,f.zymc,f.xb,g.jxmc,");
		sql.append(" row_number() over(partition by a.hdid, a.hdsqid order by b.jdsx desc) rn");
		sql.append(" from xg_hdgl_jsjdshxxb a");
		sql.append(" left join xg_hdgl_hdjdb b");
		sql.append(" on a.hdid = b.hdid and a.jdid = b.jdid");
		sql.append(" left join (select hdid, hdsqid, jxid, xf from xg_hdgl_jsjdshxxb where jxid is not null or xf is not null) c on a.hdsqid = c.hdsqid");
		sql.append(" left join xg_hdgl_hdxxb d on a.hdid = d.hdid");
		sql.append(" left join xg_hdgl_hdbqglb h on d.hdid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%讲座%'");
		sql.append(" left join xg_hdgl_xsjdxxb e on a.hdsqid = e.hdsqid");
		sql.append(" left join view_xsbfxx f on e.xh = f.xh");
		sql.append(" left join xg_hdgl_hdjxb g on c.jxid = g.jxdm and c.hdid = g.hdid where d.bmlx = '1') where rn = 1) a");
		sql.append(" where exists (select 1 from (select * from (select hdid,jdid,row_number() over(partition by hdid order by jdsx desc) rn  from xg_hdgl_hdjdb) where rn = 1) e  where a.hdid = e.hdid and a.jdid = e.jdid) ");//and a.shzt = '1'
		sql.append(" union all select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.zybj,a.zybjmc,a.hdmc,a.jxmc,a.hdxf xf,a.xb");
		sql.append(" from (select b.xh,a.hdxf,c.jxmc,d.xm,d.xydm,d.zydm,d.bjdm,d.zybj,d.zybjmc,d.nj,d.xymc,d.zymc,d.bjmc,f.hdmc,d.xb");
		sql.append(" from xg_hdgl_zdhdxxb a");
		sql.append(" left join xg_hdgl_zdhdryb b on a.dwid = b.dwid and a.hdid = b.hdid");
		sql.append(" left join xg_hdgl_hdjxb c on a.hdid = c.hdid and a.jxid = c.jxdm");
		sql.append(" left join view_xsbfxx d on b.xh = d.xh");
		sql.append(" left join xg_hdgl_hdxxb f on a.hdid = f.hdid ");
		sql.append(" left join xg_hdgl_hdbqglb h on f.hdid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%讲座%') a");
		sql.append(" union all select a.xh,b.xm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.zybj,b.zybjmc,a.hdmc,a.hdjx jxmc,a.hdxf xf,b.xb from xg_hdgl_hdbljgb a left join view_xsxxb b on a.xh = b.xh");
		sql.append(" left join xg_hdgl_hdbqglb h on a.jgid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%讲座%'");
		sql.append(" ) t left join XG_XTWH_SYBJGLB t1 on t.bjdm = t1.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t2 on t1.sydm = t2.sydm ");
		sql.append(" where xh = '"+user.getUserName()+"' ");
//		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" ) x");
		return getPageList(t, sql.toString(), inputV);
	}

	public List<HashMap<String, String>> getPageListForJzjg(HdxxForm t) throws Exception{
//		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
//		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.hdmc,a.jxmc,a.xf,a.xb from");
		sql.append(" (select * from (select a.jdid,a.hdid,c.jxid,c.xf,b.jdsx,d.bmlx,d.hdmc,a.hdsqid,a.shzt,e.xh,f.xm,f.xydm,f.bjdm,f.zydm,f.nj,f.xymc,f.bjmc,f.zymc,f.xb,g.jxmc,");
		sql.append(" row_number() over(partition by a.hdid, a.hdsqid order by b.jdsx desc) rn");
		sql.append(" from xg_hdgl_jsjdshxxb a");
		sql.append(" left join xg_hdgl_hdjdb b");
		sql.append(" on a.hdid = b.hdid and a.jdid = b.jdid");
		sql.append(" left join (select hdid, hdsqid, jxid, xf from xg_hdgl_jsjdshxxb where jxid is not null or xf is not null) c on a.hdsqid = c.hdsqid");
		sql.append(" left join xg_hdgl_hdxxb d on a.hdid = d.hdid");
		sql.append(" left join xg_hdgl_hdbqglb h on d.hdid = h.jgid ");
		sql.append(" left join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%讲座%'");
		sql.append(" left join xg_hdgl_xsjdxxb e on a.hdsqid = e.hdsqid");
		sql.append(" left join view_xsbfxx f on e.xh = f.xh");
		sql.append(" left join xg_hdgl_hdjxb g on c.jxid = g.jxdm and c.hdid = g.hdid where d.bmlx = '1') where rn = 1) a");
		sql.append(" where exists (select 1 from (select * from (select hdid,jdid,row_number() over(partition by hdid order by jdsx desc) rn  from xg_hdgl_hdjdb) where rn = 1) e  where a.hdid = e.hdid and a.jdid = e.jdid) and a.shzt = '1'");
		sql.append(" union all select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.hdmc,a.jxmc,a.hdxf xf,a.xb");
		sql.append(" from (select b.xh,a.hdxf,c.jxmc,d.xm,d.xydm,d.zydm,d.bjdm,d.nj,d.xymc,d.zymc,d.bjmc,f.hdmc,d.xb");
		sql.append(" from xg_hdgl_zdhdxxb a");
		sql.append(" left join xg_hdgl_zdhdryb b on a.dwid = b.dwid and a.hdid = b.hdid");
		sql.append(" left join xg_hdgl_hdjxb c on a.hdid = c.hdid and a.jxid = c.jxdm");
		sql.append(" left join view_xsbfxx d on b.xh = d.xh");
		sql.append(" left join xg_hdgl_hdxxb f on a.hdid = f.hdid ");
		sql.append(" left join xg_hdgl_hdbqglb h on f.hdid = h.jgid ");
		sql.append(" left join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%讲座%') a");
		sql.append(" union all select a.xh,b.xm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,a.hdmc,a.hdjx jxmc,a.hdxf xf,b.xb from xg_hdgl_hdbljgb a left join view_xsxxb b on a.xh = b.xh");
		sql.append(" left join xg_hdgl_hdbqglb h on a.jgid = h.jgid ");
		sql.append(" left join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%讲座%'");
		sql.append(" ) t where 1 = 1 ");
//		sql.append(searchTjByUser);
//		sql.append(searchTj);
		sql.append(" and t.xh=?");
		return getPageList(t, sql.toString(), new String[]{t.getXh()});
	}
	
	/**
	 * @description	： 设置活动类型和标签
	 * @author 		： lj（1282）
	 * @date 		：2018-4-12 下午04:57:21
	 * @return
	 * @throws Exception 
	 */
	public boolean szHdLx(HdxxForm t) throws Exception{
		String sql = "update xg_hdgl_hdxxb set hdlx = ?,sflx = ? where hdid = ?";
		return dao.runUpdate(sql, new String[]{t.getHdlx(),t.getSflx(),t.getHdid()});
	}
	
	/**
	 * @description	： 删除活动标签
	 * @author 		： lj（1282）
	 * @date 		：2018-4-12 下午05:03:58
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public boolean deleteHdbq(HdxxForm t) throws Exception{
		String sql = "delete from xg_hdgl_hdbqglb where jgid = ?";
		return dao.runUpdate(sql, new String[]{t.getHdid()});
	}
	
	/**
	 * @description	： 插入活动标签
	 * @author 		： lj（1282）
	 * @date 		：2018-4-12 下午05:11:45
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public boolean insertHdbq(HdxxForm t) throws SQLException{
		String sql = "insert into xg_hdgl_hdbqglb values(?,?)";
		String hdid = t.getHdid();
		String[] hdbqs = t.getHdbqs();
		List<String[]> list = new ArrayList<String[]>();
		for(String hdbq : hdbqs){
			String[] str = new String[2];
			str[0] = hdid;
			str[1] = hdbq;
			list.add(str);
		}
		return dao.runBatchBoolean(sql, list);
	}

	
	public List<HashMap<String, String>> getHdlistByXh(String xh) {
		String sql = "select xh,hdlxmc,shsj,hdmc,zxf from view_xg_hdgl_xshdxx  where xh = ? union all select ''xh,hdlxmc,'' shsj,''hdmc,0 zxf from xg_hdgl_hdlxdmb where hdlxmc not in (select distinct hdlxmc from view_xg_hdgl_xshdxx where xh=?)";
		return dao.getListNotOut(sql, new String[]{xh,xh});
	}

	public String getdsCount(String xh) {
		String sql = " select count(1) rn from xg_dekt_dsglb where xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "rn");
	}
	public String getjsCount(String xh) {
		String sql = " select count(1) rn from xg_dekt_jspjglb where xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "rn");
	}

	public String getjzCount(String xh) {
		String sql = " select count(1) rn from view_xg_hdgl_xshdxx a left join xg_hdgl_hdbqglb b on a.hdid = b.jgid left join xg_hdgl_hdbqdmb c on b.hdbq = c.hdbqdm where c.hdbqmc like '%讲座%' and a.xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "rn");
	}

	public String gethdCount(String xh) {
		String sql = " select count(1) rn from view_xg_hdgl_xshdxx a left join xg_hdgl_hdbqglb b on a.hdid = b.jgid left join xg_hdgl_hdbqdmb c on b.hdbq = c.hdbqdm where c.hdbqmc like '%活动%' and a.xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "rn");
	}

	public List<HashMap<String, String>> getHdXflistByXh(String xh) {
		String sql = " select hdlxmc,sum(zxf) zxf from (select * from view_xg_hdgl_xshdxx a where a.xh = ?) group by hdlxmc union all select hdlxmc,0 zxf from xg_hdgl_hdlxdmb where hdlxmc not in (select distinct hdlxmc from view_xg_hdgl_xshdxx where xh=?)";
		return dao.getListNotOut(sql, new String[]{xh,xh});
	}

	public HashMap<String, String> getNjPmByXh(String xh) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.xh,a.zxf, rank() over(partition by b.nj order by a.zxf desc) || '/' || c.njrs zxfnjpm");
		sb.append(" from (select xh, sum(zxf) zxf from view_xg_hdgl_xshdxx group by xh) a left join view_xsjbxx b");
		sb.append(" on a.xh = b.xh left join (select nj, count(xh) njrs from view_xsjbxx group by nj) c");
		sb.append(" on b.nj = c.nj where a.xh = ?");
		return dao.getMapNotOut(sb.toString(), new String[]{xh});		
	}

	public List<HashMap<String, String>> getHdslistByXh(String xh) {
		StringBuilder sql=new StringBuilder();
		sql.append("select c.NLBQMC hdlxmc,NVL(COUNT(c.NLBQMC),0) hds from view_xg_hdgl_xshdxx a ");
		sql.append("LEFT JOIN XG_HDGL_NLBQGLB b ON a.HDID=b.JGID ");
		sql.append("RIGHT JOIN XG_HDGL_NLBQDMB c ON b.NLBQ=c.NLBQDM ");
		sql.append("where xh=? ");
		sql.append("GROUP BY c.NLBQMC ");
		sql.append("UNION ALL ");
		sql.append("select a.NLBQMC hdlxmc,0 hds FROM XG_HDGL_NLBQDMB a ");
		sql.append("WHERE a.NLBQDM not in (select distinct  b.NLBQ from view_xg_hdgl_xshdxx a INNER JOIN XG_HDGL_NLBQGLB b on a.HDID=b.JGID ");
		sql.append("where xh=?) ");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xh});
	}
	
	/**
	 * @description	： 获取活动奖项
	 * @author 		： lj（1282）
	 * @date 		：2018-4-23 下午03:31:15
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getHdjxs(HdxxForm form) throws Exception{
		String sql = " select * from xg_hdgl_hdjxb where hdid = ?";
		return dao.getListNotOut(sql, new String[]{form.getHdid()});
	}
	
	/**
	 * @description	： 删除活动奖项
	 * @author 		： lj（1282）
	 * @date 		：2018-4-23 下午04:39:25
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteHdjx(HdxxForm form) throws Exception{
		String sql = " delete from xg_hdgl_hdjxb where hdid = ?";
		return dao.runDelete(sql, new String[]{form.getHdid()}) > 0;
	}
	
	/**
	 * @description	： 插入活动奖项
	 * @author 		： lj（1282）
	 * @date 		：2018-4-23 下午04:44:17
	 * @param form
	 * @return
	 * @throws SQLException 
	 */
	public boolean insertHdjx(HdxxForm form) throws SQLException{
		String[] jxmcs = form.getJxmcs();
		String[] jxsms = form.getJxsms();
		String hdid = form.getHdid();
		String sql = " insert into xg_hdgl_hdjxb(hdid,jxdm,jxmc,jxsm)values(?,?,?,?)";
		List<String[]> list = new ArrayList<String[]>();
		for(int i = 0; i < jxmcs.length; i++){
			String[] arr = new String[4];
			arr[0] = hdid;
			arr[1] = "0"+i+1;
			arr[2] = jxmcs[i];
			if(null != jxsms && jxsms.length > 0 && null != jxsms[i]){				
				arr[3] = jxsms[i];
			}else{
				arr[3] = null;
			}
			list.add(arr);
		}
		return dao.runBatchBoolean(sql, list);
	}

    /**
     * 获取派票人员集合
     * @param form
     * @return
     */
	public List<HashMap<String,String>> getPpPersonList(HdxxForm form){
	    StringBuilder sql = new StringBuilder();
	    String[] param = {form.getHdid(),form.getPpnum()};
        sql.append("select * from (select a.sqid,a.hdid,a.xh,a.bmsj,a.shzt,b.xm from ");
        if("0".equals(form.getBmlx())){
            sql.append(" XG_HDGL_ZDHDRYB a");
        } else {
            sql.append(" XG_HDGL_HDRYB a");
        }
        sql.append(" left join view_xsbfxx b on a.xh = b.xh");
        sql.append(" where a.shzt = '1'");//已审核状态
        sql.append(" and a.hdpp = '0'");//派票状态
        sql.append(" and a.hdid = ?");
	    if(random.equals(form.getPptype())){
            //随机抽取
            sql.append(" order by DBMS_RANDOM.VALUE)");
        } else if(byTime.equals(form.getPptype())){
            //先到先得
            sql.append(" order by a.bmsj)");
        }
		sql.append(" where rownum <= ?");
	    return dao.getListNotOut(sql.toString(),param);
    }

	public List<HashMap<String,String>> getPpPersonList2(HdxxForm form){
		StringBuilder sql = new StringBuilder();
		int bmzzrs = Integer.parseInt(form.getBmzzrs());//最早报名人数
		int ppnum = Integer.parseInt(form.getPpnum());//派票人数
		String[] param = {form.getHdid(),form.getBmzzrs(),form.getHdid(),form.getHdid(),form.getBmzzrs(),String.valueOf(ppnum-bmzzrs)};
		String tableName = "";
		if("0".equals(form.getBmlx())){
			tableName = " XG_HDGL_ZDHDRYB ";
		} else {
			tableName = " XG_HDGL_HDRYB ";
		}
		sql.append("select a.*");
		sql.append("  from (select a.sqid, a.hdid, a.xh, a.bmsj, a.shzt, b.xm");
		sql.append("          from "+tableName+" a");
		sql.append("          left join view_xsbfxx b");
		sql.append("            on a.xh = b.xh");
		sql.append("         where a.shzt = '1'");  //已审核
        sql.append("           and a.hdpp = '0'"); //未派票
		sql.append("           and a.hdid = ?");
		sql.append("         order by a.bmsj) a");
		sql.append("  where rownum <= ? ");
		sql.append(" union all ");
		sql.append("select *");
		sql.append("  from (select a.sqid, a.hdid, a.xh, a.bmsj, a.shzt, b.xm");
		sql.append("          from "+tableName+" a");
		sql.append("          left join view_xsbfxx b");
		sql.append("            on a.xh = b.xh");
		sql.append("         where a.shzt = '1'");
        sql.append("           and a.hdpp = '0'"); //未派票
		sql.append("           and a.hdid = ?");
		sql.append("           and not exists (select 1");
		sql.append("                     from (select sqid");
		sql.append("                             from (select a.sqid");
		sql.append("                                     from "+tableName+" a");
		sql.append("                                    where a.shzt = '1'");
        sql.append("                                      and a.hdpp = '0'"); //未派票
		sql.append("                                      and a.hdid = ?");
		sql.append("                                    order by a.bmsj) x");
		sql.append("                            where rownum <= ?) t");
		sql.append("                    where t.sqid = a.sqid)");
		sql.append("         order by DBMS_RANDOM.VALUE)");
		sql.append(" where rownum <= ?");
		return dao.getListNotOut(sql.toString(),param);
	}
    public String getYshrs(HdxxForm form){
        StringBuilder sql = new StringBuilder();
        String tableName = "";
        if("0".equals(form.getBmlx())){
            tableName = " XG_HDGL_ZDHDRYB ";
        } else {
            tableName = " XG_HDGL_HDRYB ";
        }
        sql.append("select count(1) num from "+tableName+" where shzt = '1' and hdid = ?");
        String num = dao.getOneRs(sql.toString(),new String[]{form.getHdid()},"num");
        return StringUtil.isNull(num)?"0":num;
    }
	public String getYpprs(HdxxForm form){
		StringBuilder sql = new StringBuilder();
		String tableName = "";
		if("0".equals(form.getBmlx())){
			tableName = " XG_HDGL_ZDHDRYB ";
		} else {
			tableName = " XG_HDGL_HDRYB ";
		}
		sql.append("select count(1) num from "+tableName+" where shzt = '1' and hdpp = '1' and hdid = ?");
		String num = dao.getOneRs(sql.toString(),new String[]{form.getHdid()},"num");
		return StringUtil.isNull(num)?"0":num;
	}
    public boolean setPp(List<String> p,String bmlx) throws SQLException {
        StringBuilder sql = new StringBuilder();
        if("0".equals(bmlx)){
            sql.append(" update XG_HDGL_ZDHDRYB set hdpp = '1' where sqid = ?");
        } else {
            sql.append(" update XG_HDGL_HDRYB set hdpp = '1' where sqid = ?");
        }

        String[] sqid;
        List<String[]> param = new ArrayList<String[]>();
        for(String s : p){
            sqid = new String[1];
            sqid[0] = s;
            param.add(sqid);
        }
        int[] is = dao.runBatch(sql.toString(),param);

        return is == null ? false : true;
    }
	
	/**
	 * @描述: 根据活动id获取活动相关信息
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-11 下午04:54:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hdid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getHdxxByHdid(String hdid)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, b.hdlxmc,decode(a.bmlx,'0','组织','1','个人',a.bmlx)bmlxmc from xg_hdgl_hdxxb a ");
		sql.append("left join xg_hdgl_hdlxdmb b on a.hdlx = b.hdlxdm ");
		sql.append("where a.hdid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{hdid});
	}
	
	/**
	 * @描述: 阶段成员信息
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-11 下午06:05:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hdid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJdcyxxList(String hdid)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum num, hdmc, jdmc, yyshrs, jdsx, hdid, jdid ");
		sql.append("from (select a.hdid, a.hdmc, c.jdmc, b.jdsx, count(d.zjzgh) yyshrs,b.jdid ");
		sql.append("from xg_hdgl_hdxxb a ");
		sql.append("left join xg_hdgl_hdjdb b on a.hdid = b.hdid ");
		sql.append("left join xg_hdgl_jdglb c on b.jdid = c.jdid ");
		sql.append("left join xg_hdgl_hdzjtcyb d on a.hdid = d.hdid and d.jdid = b.jdid ");
		sql.append("group by a.hdid, a.hdmc, c.jdmc, b.jdsx,b.jdid order by to_number(b.jdsx)) ");
		sql.append("where hdid = ?");
		return dao.getListNotOut(sql.toString(), new String[]{hdid});
	}

    /**
     * 获取活动类型list，画面下拉检索用
     * @return
     */
	public List<HashMap<String, String>> getHdlx(){
        StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from XG_HDGL_HDLXDMB t ");
		return dao.getListNotOut(sql.toString(),new String[]{});
	}

	public List<HashMap<String,String>> getJdxx(String hdid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_HDGL_HDJDB where hdid=? order by jdsx");
		return dao.getListNotOut(sql.toString(),new String[]{hdid});
	}

	/**
	 * 获得有效的报名不需要审核的活动列表用于自动派票
	 * @return
	 */
	public List<HashMap<String,String>> getHdxxList(){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT DISTINCT a.HDID,a.BMKSSJ,a.BMJSSJ,a.PPFS,a.ZZBMRS,a.BMRS FROM XG_HDGL_HDXXB a ");
		sql.append("INNER JOIN XG_HDGL_HDRYB b ON a.HDID=b.HDID ");
		sql.append("WHERE HDZT='1' AND BMSFSH='0' AND '1' NOT IN (SELECT c.HDPP FROM XG_HDGL_HDRYB c WHERE c.HDID=a.HDID)");
		return dao.getListNotOut(sql.toString(),new String[]{});
	}

	/**
	 * 获得活动审核通过人员
	 * @param hdId
	 * @return
	 */

	public List<String> getHdryList(String hdId) throws SQLException {
		String sql="SELECT SQID FROM XG_HDGL_HDRYB WHERE SHZT='1' AND HDID=? ORDER BY BMSJ ASC";
		return dao.getList(sql,new String[]{hdId},"sqid");
	}

	public void pp(List<String[]> idList) throws SQLException {
		String sql="UPDATE XG_HDGL_HDRYB SET HDPP=1 WHERE SQID = ?";
		dao.runBatch(sql,idList);
	}

	public List<HashMap<String,String>> getBmRys(String dthdid){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.hdid,a.xh,a.bmsj from xg_hdgl_hdryb a left join xg_hdgl_hdxxb b on a.hdid = b.hdid where a.shzt='1' and a.hdpp='1' ");
		sql.append(" union ");
		sql.append(" select a.hdid,a.xh,a.bmsj  from xg_hdgl_zdhdryb a left join xg_hdgl_hdxxb b on a.hdid = b.hdid where a.shzt='1' and a.hdpp='1' ");
		sql.append(" ) where 1=1 and hdid= ? ");
		return dao.getListNotOut(sql.toString(),new String[]{dthdid});
	}

	/**
	* @Author llf[1754]
	* @Description 判断报名是否审核
	* @Date 17:25 2019/11/1
	* @Param [sqid]
	* @return boolean
	**/
	public boolean checkIsSh(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select shzt from（select sqid,shzt from xg_hdgl_hdryb union select sqid,shzt from xg_hdgl_zdhdryb） where sqid = ?");
		String shzt = dao.getOneRs(sql.toString(),new String[]{sqid},"shzt");
		if("1".equals(shzt)||"2".equals(shzt)){
			return true; //已审核（1：已通过，2：已拒绝
		}
		return false;
	}
}
