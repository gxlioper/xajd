package xsgzgl.pjpy.szgyyq.mypj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyMypjDAO extends CommDAO {

	/**
	 * 获得读书活动统计列表(学生)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getStuDshdTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.pf, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyq_dshdjzb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'dshd') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * 获得语言表达统计列表(学生)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getStuYybdTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.pf, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyq_yybdjzb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'yybd') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * 获得IVT论坛统计列表(学生)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getStuIvtltTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.pf, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyq_ivtltb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'ivtlt') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * 获得文体活动统计列表(学生)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getStuWthdTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.pf, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyq_xthddjb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'wthd') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * 获得组织能力统计列表(学生)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getStuZznlTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.hdpf, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyc_zznlfzb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'zznl') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * 获得社会实践统计列表(学生)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getStuShsjTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.shfz, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyc_shsjfzb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'shsj') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * 获得5S分统计列表(学生)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getStuFiveSTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.jjf, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.fz, 0) zzf, a.jjf,a.sqf, a.xysh, a.xxsh ");
		sql.append("from szyc_5sb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'wsmk') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"jjf", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * 获得综合素质分分统计列表(学生)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getStuZhczfTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.pf, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyq_dshdjzb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'dshd') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * 获取学校评奖统计列表
	 * @param model
	 * @param user
	 * @return
	 * @author gaobb
	 */
	public List<HashMap<String,String>> getXxMyPjTjList(User user){
		String userType=user.getUserType();
		String view_xsjbxx=Base.xsxxb;
		//条件
		String tj=" a where xn='"+Base.currXn+"' and xq='"+Base.currXq+"' ";
		String tj_5s=tj;//5s没有班主任审核
		if("xy".equals(userType)){
			tj+=" and bzrsh='通过' and (xysh='未审核' or xysh='需重审') and exists (select 1 from view_xsjbxx x where x.xh=a.xh and x.xydm='"+user.getUserDep()+"'))" +
					" group by xn,xq ";
			tj_5s+=" and (xysh='未审核' or xysh='需重审') and exists (select 1 from "+view_xsjbxx+" x where x.xh=a.xh and x.xydm='"+user.getUserDep()+"')) " +
					" group by xn,xq ";
		}else{
			tj+=" and xysh='通过' and xxsh='未审核' ) group by xn,xq ";
			tj_5s=tj;
		}
		//申诉投诉条件
		String sststj = " a where xn='" + Base.currXn + "' and xq='" + Base.currXq + "' ";
		sststj += " and clr is null ";
		if("xy".equals(userType)){
			sststj+=" and exists (select 1 from "+view_xsjbxx+" x where x.xh=a.xh and x.xydm='"+user.getUserDep()+"')) ";
		}else{
			sststj+=" ) ";
		}

		StringBuffer sql=new StringBuffer();
		sql.append("select a.xmlx,a.xmmc,nvl(d.xshrs,'0') xshrs,nvl(b.ssrs,'0') ssrs,nvl(c.tsrs,'0') tsrs from ");
		
		sql.append("("); 
		sql.append("select '读书活动' xmmc,'szyq_dshdjzb' xmlx from dual ");
		sql.append("union all  ");
		sql.append("select '语言表达' xmmc,'szyq_yybdjzb' xmlx from dual ");
		sql.append("union all  ");
		sql.append("select 'ITV论坛' xmmc,'szyq_ivtltb' xmlx from dual ");
		sql.append("union all  ");
		sql.append("select '文体活动' xmmc,'szyq_xthddjb' xmlx from dual ");
		sql.append("union all  ");
		sql.append("select '组织能力' xmmc,'szyc_zznlfzb' xmlx from dual ");
		sql.append("union all  ");
		sql.append("select '社会实践' xmmc,'szyc_shsjfzb' xmlx from dual ");
		sql.append("union all  ");
		sql.append("select '5S' xmmc,'szyc_5sb' xmlx from dual ");
		sql.append(") a "); 
		
		sql.append("left join "); 
		sql.append("("); 
		sql.append("select '读书活动' xmmc,'szyq_dshdjzb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyq_dshdjzb ").append(tj);
		sql.append("union all  ");
		sql.append("select '语言表达' xmmc,'szyq_yybdjzb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyq_yybdjzb ").append(tj);
		sql.append("union all  ");
		sql.append("select 'ITV论坛' xmmc,'szyq_ivtltb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyq_ivtltb ").append(tj);
		sql.append("union all  ");
		sql.append("select '文体活动' xmmc,'szyq_xthddjb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyq_xthddjb ").append(tj);
		sql.append("union all  ");
		sql.append("select '组织能力' xmmc,'szyc_zznlfzb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyc_zznlfzb ").append(tj);
		sql.append("union all  ");
		sql.append("select '社会实践' xmmc,'szyc_shsjfzb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyc_shsjfzb ").append(tj);
		sql.append("union all  ");
		sql.append("select '5S' xmmc,'szyc_5sb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyc_5sb ").append(tj_5s);
		sql.append(") d on a.xmlx=d.xmlx "); 
		
		sql.append("left join "); 
		sql.append("(select xmlx,count(1) ssrs from (select distinct xn,xq,xh,xmlx from xg_pjpy_szgyyq_xsssb "+sststj+" group by xmlx) b on a.xmlx=b.xmlx ");
		sql.append("left join ");
		sql.append("(select xmlx,count(1) tsrs from (select distinct xn,xq,xh,xmlx from xg_pjpy_szgyyq_xstsb "+sststj+" group by xmlx) c on a.xmlx=c.xmlx ");
		
		DAO dao = DAO.getInstance();
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xmlx","xmmc","xshrs","ssrs","tsrs"});
	}
	
	/**
	 * 获取班主任评奖统计列表
	 * @param model
	 * @param user
	 * @return
	 * @author gaobb
	 */
	public List<HashMap<String,String>> getBzrMyPjTjList(User user){
		//条件
		String tj=" a where xn='"+Base.currXn+"' and xq='"+Base.currXq+"' "+
		    " and exists (select 1 from "+Base.xsxxb+" x,bzrbbb y where x.bjdm=y.bjdm and x.xh=a.xh and y.zgh='"+user.getUserName()+"')) group by xn,xq ";
		//字段
		String field=" count(case when bzrsh='未审核' then 1 when bzrsh='需重审' then 1 end) xshrs,count(case when xysh='通过' then 1 end) xyshtgrs, " +//需审核人数,学院审核通过人数，学校审核通过人数
				"count(case when xxsh='通过' then 1 end) xxshtgrs";
 
		StringBuffer sql=new StringBuffer();
		sql.append("select a.* from ( ");
		sql.append("select '读书活动' xmmc,'szyq_dshdjzb' xmlx,"+field+" from ( select distinct xn,xq,xh,bzrsh,xysh,xxsh from szyq_dshdjzb ").append(tj);
		sql.append("union all  ");
		sql.append("select '语言表达' xmmc,'szyq_yybdjzb' xmlx,"+field+" from ( select distinct xn,xq,xh,bzrsh,xysh,xxsh from szyq_yybdjzb ").append(tj);
		sql.append("union all  ");
		sql.append("select 'ITV论坛' xmmc,'szyq_ivtltb' xmlx,"+field+" from ( select distinct xn,xq,xh,bzrsh,xysh,xxsh from szyq_ivtltb ").append(tj);
		sql.append("union all  ");
		sql.append("select '文体活动' xmmc,'szyq_xthddjb' xmlx,"+field+" from ( select distinct xn,xq,xh,bzrsh,xysh,xxsh from szyq_xthddjb ").append(tj);
		sql.append("union all  ");
		sql.append("select '组织能力' xmmc,'szyc_zznlfzb' xmlx,"+field+" from ( select distinct xn,xq,xh,bzrsh,xysh,xxsh from szyc_zznlfzb ").append(tj);
		sql.append("union all  ");
		sql.append("select '社会实践' xmmc,'szyc_shsjfzb' xmlx,"+field+" from ( select distinct xn,xq,xh,bzrsh,xysh,xxsh from szyc_shsjfzb ").append(tj);
		sql.append(") a "); 
		
		DAO dao = DAO.getInstance();
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xmlx", "xmmc", "xshrs", "xyshtgrs", "xxshtgrs" });
	}
	
	/**
	 * 处理统计为空的情况
	 * @param list
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>> xxbzrMyPjCheckEmpty(List<HashMap<String,String>>  list,String type){
		String[] xmmcs;
		if("bzr".equals(type)){
			xmmcs=new String[]{"读书活动","语言表达","ITV论坛","文体活动","组织能力","社会实践"};
		}else{
			xmmcs=new String[]{"读书活动","语言表达","ITV论坛","文体活动","组织能力","社会实践","5S"};
		}
		boolean mark=false;
		for(int i=0;i<xmmcs.length;i++){
			mark=false;
			for(int j=0;j<list.size();j++){
				if(xmmcs[i].equals(list.get(j).get("xmmc"))){
					mark=true;
					break;
				}
			}
			if(!mark){
				HashMap<String,String> map=new HashMap<String, String>();
				map.put("xmmc", xmmcs[i]);
				map.put("xshrs", "0");
				map.put("ssrs", "0");
				map.put("tsrs", "0");
				list.add(map);
			}
		}
		return list;
	}
	
}
