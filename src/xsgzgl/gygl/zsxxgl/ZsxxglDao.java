package xsgzgl.gygl.zsxxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.gygl.comm.GyglNewService;
import xsgzgl.gygl.tsgl.TsglForm;

import common.Globals;
public class ZsxxglDao extends GyglNewDAO {
	DAO dao = DAO.getInstance();

	private static String xsxxb_ntzydx = "( select * from view_xsbfxx "
			+ "where (sfyby = '否' or sfyby is null) and (sfzx = '在校' or sfzx is null) )";

	/**
	 * 
	 * @描述:获取可退宿列表
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-15 下午03:41:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKtsList(ZsxxglForm zf, User user)
			throws Exception {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");

		StringBuffer sb = new StringBuffer();
		sb.append(" select * from ( ");
		sb
				.append(" select t.nj || '_' || t.xydm || '_' || t.zydm njxyzy, t.nj,t.xymc,t.xydm,t.zymc,t.zydm,count(*) dlxrs");
		sb.append(" from view_xsbfxx t");
		sb.append(" inner join view_xg_gygl_new_cwxx cwxx");
		sb.append(" on t.xh=cwxx.xh");
		sb.append(" where 1=1");
		sb.append(" and t.sfzx='不在校'");
		sb.append(" group by t.nj,t.xymc,t.xydm,t.zymc,t.zydm");
		sb.append(" ) a where 1=1 ");
		sb.append(searchTjByUser);
		ZsxxglDaoForPage zfp = new ZsxxglDaoForPage();
		return zfp.getPageListOld(zf, sb.toString(), new String[] {});
	}
	/**
	 * @描述: 获得离校未退宿学生信息列表
	 * @作者： 孟威[工号：1186]
	 * @日期：	2015-12-29 上午09:34:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zf
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLxxsList(ZsxxglForm zf, User user)
	throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.ldmc, a.qsh, a.cwh, t.xh,a.qsxb, t.xm, t.bjmc,t.zydm,t.xydm,t.nj");
		sb.append("  from view_xsxxb t");
		sb.append(" inner join view_xg_gygl_new_cwxx a");
		sb.append(" on t.xh = a.xh");
		sb.append(" where 1 = 1");
		sb.append(" and t.sfzx = '不在校'");
		sb.append(" and t.nj = ?");
		sb.append("  and t.xydm = ?");
		sb.append("  and t.zydm = ?");
		ZsxxglDaoForPage zfp = new ZsxxglDaoForPage();
		return zfp.getPageListOld(zf, sb.toString(), new String[] {zf.getNj(),zf.getXydm(),zf.getZydm()});
		}

	/**
	 * 
	 * @描述:获取可退宿列表
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-15 下午03:41:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws Exception
	 */
	public int getHjrs(ZsxxglForm zf, User user)
			throws Exception {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");

		StringBuffer sb = new StringBuffer();
		sb.append(" select nvl(sum(dlxrs),0) dlxrs from ( ");
		sb
				.append(" select t.nj || '_' || t.xydm || '_' || t.zydm njxyzy, t.nj,t.xymc,t.xydm,t.zymc,t.zydm,count(*) dlxrs");
		sb.append(" from view_xsbfxx t");
		sb.append(" inner join view_xg_gygl_new_cwxx cwxx");
		sb.append(" on t.xh=cwxx.xh");
		sb.append(" where 1=1");
		sb.append(" and t.sfzx='不在校'");
		sb.append(" group by t.nj,t.xymc,t.xydm,t.zymc,t.zydm");
		sb.append(" ) a where 1=1 ");
		sb.append(searchTjByUser);
		String count = dao.getOneRs(sb.toString(), new String[]{}, "dlxrs");
		return Integer.valueOf(count);
	}

	/**
	 * 
	 * @描述:获取可以退宿的学生
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-15 下午04:38:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getTsxs(User user) {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuffer sb = new StringBuffer();
		sb.append("select t.xh");
		sb.append(" from view_xsxxb t");
		sb.append(" inner join view_xg_gygl_new_cwxx cwxx");
		sb.append(" on t.xh=cwxx.xh");
		sb.append(" where 1=1");
		sb.append(" and t.sfzx='不在校'");
		sb.append(" ");
		sb.append(searchTjByUser);
		return dao.getListNotOut(sb.toString(), new String[] {});
	}

	/**
	 * 查询床位信息
	 * 
	 * @param model
	 * @return
	 */
	public List<String[]> queryCw(ZsxxglForm myForm, HttpServletRequest request)
			throws Exception {
		return queryCw(myForm, request, true);
	}

	public List<String[]> queryCw(ZsxxglForm myForm,
			HttpServletRequest request, boolean isHavePage) throws Exception {
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// 权限控制
		String searchTjQx = "";

		SearchService searchService = new SearchService();
		String searchTjByUser = "";
		
		CommService service = new CommService();
		User user = service.getUser(request);
		/*
		 * 床位分配数据范围： 1、分配到学院 xg_gygl_new_jbszb 中 cwfpdx字段值为xydm
		 * 学院用户：查询分配给用户所属学院的床位 辅导员用户：查询用户管辖班级所属学院的空床位及自己班级学生所住床位 2、分配要班级
		 * xg_gygl_new_jbszb 中 cwfpdx字段值为 bjdm 学院用户：查询分配给用户所属学院的床位
		 * 辅导员用户：查询分配给用户管辖班级的床位 3、分配要班级 xg_gygl_new_jbszb 中 cwfpdx字段值为 zydm
		 * 学院用户：查询分配给用户所属学院的床位 辅导员用户：查询用户管辖班级所属专业的空床位及自己班级学生所住床位
		 */
		if ("xydm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			
			// 辅导员权限
			boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
			// 班主任权限
			boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
			if (fdyqx || bzrqx) {// 如果是辅导员或班主任；生成用户带班所在学院的过滤条件
				searchTjByUser = getFdySearch(user);
			} else {
				searchTjByUser = searchService.getSearchTjByUser(request, "a",
						"xydm", "xsbjdm"); // 学院用户
			}
			searchTj = searchTj.replaceAll("bjdm", "xsbjdm");// 高级查询条件bjdm查询学生所属班级
			searchTj = searchTj.replaceAll("zydm", "xszydm");// 高级查询条件zydm查询学生所属专业
		} else if ("bjdm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			searchTjByUser = searchService.getSearchTjByUser(request, "a",
					"xydm", "bjdm");
		} else if ("zydm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			
			// 辅导员权限
			boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
			// 班主任权限
			boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
			if (fdyqx || bzrqx) {// 如果是辅导员或班主任；生成用户带班所在专业的过滤条件

				searchTjByUser = getFdySearchZyfp(user);

			} else if ("xy".equalsIgnoreCase(user.getUserType())) {
				searchTjByUser = searchService.getSearchTjByUser(request, "a",
						"xydm", "xsbjdm"); // 学院用户
			}
		}
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // 公寓辅导员数据范围过滤条件

		/*
		 * 当用户为公寓管理员时，xg_gygl_new_gyfdyb 中 存在该用户数据 不考虑其作为学院或者辅导员的权限控制，只控制楼栋数据范围
		 */
		if("xy".equalsIgnoreCase(user.getUserType()) && "11799".equals(Base.xxdm)){
			searchTjQx = " and (a.xydm = '" + user.getUserDep() + "'  or a.xh is null )" ;
		} else if (searchTjByGyfdy != null && !"".equalsIgnoreCase(searchTjByGyfdy)) {// 用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		} else {// 用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = searchTjByUser;
		}

		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();

		// sql.append("select rownum r,a.* from (select lddm||qsh||cwh pkValue,a.*,b.xm,"
		// +
		// "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis "+
		// "from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where 1=1 ");
		if (Globals.XXDM_NTZYDX.equals(Base.xxdm)) {// 处理南通职业个性化入学方式
			sql
					.append("select rownum r,a.* from (select lddm||qsh||cwh pkValue,a.*,b.xm,"
							+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis,(case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz, a.bjmc cwbjmc,a.xymc cwxymc,b.bjmc xsbjmc, b.zymc xszymc "
							+ "from view_xg_gygl_new_cwxx a left join "
							+ xsxxb_ntzydx
							+ " b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where 1=1 ");
		}else if(Globals.XXDM_DBSYDX.equals(Base.xxdm)){// 东北石油大学个性化，增加入住，调整，退宿时间
			sql .append("select rownum r,a.* from (select a.*,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from bks_bjdm where bjdm=a.bjdm) cwbjmc,b.tzsj,decode(a.xh,'',c.tssj,'') tssj from (select lddm||qsh||cwh pkValue,a.*,b.xm,b.zymc xszymc,b.zydm xszydm, b.bjmc xsbjmc,b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm,"
							+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis,(case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz "
							+ "from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,to_number(a.cwh)) a left join (select ydhlddm,ydhqsh,ydhcwh,tstzsj tzsj from " 
							+ "(SELECT a.*,row_number()over(partition by ydhlddm,ydhqsh,ydhcwh order by tstzsj desc ) rn FROM XG_GYGL_NEW_SSYD_SSYDJG a) where rn =1 ) b on a.lddm=b.ydhlddm and a.qsh=b.ydhqsh and a.cwh=b.ydhcwh ");
			sql.append("left join (select ydqlddm,ydqqsh,ydqcwh,tstzsj tssj from ( ");
			sql.append("SELECT a.*,row_number()over(partition by ydqlddm,ydqqsh,ydqcwh order by tstzsj desc ) rn FROM XG_GYGL_NEW_SSYD_SSYDJG a) where rn =1 ) c ");
			sql.append("on a.lddm=c.ydqlddm and a.qsh=c.ydqqsh and a.cwh=c.ydqcwh ) a ");
			sql.append("where 1=1 ");
		}else if("13033".equalsIgnoreCase(Base.xxdm)) {// 湖南机电职业技术学院
			sql.append("select rownum r,a.*,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from bks_bjdm where bjdm=a.bjdm) cwbjmc from (select lddm||qsh||cwh pkValue,a.*,b.xm,b.zymc xszymc,b.zydm xszydm, b.bjmc xsbjmc,b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm,"
					+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis,(case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz, "
					+ "(case when c.n is null then 0 else c.n end) n "
					+ "from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh  " 
					+ "left join (select count(1) n, a.xh from xg_wjcf_wjcfb a group by xh) c on a.xh = c.xh "
					+ "order by a.lddm,to_number(a.ch),a.qsh,to_number(a.cwh)) a where 1=1 ");
		}else {
			//辽宁机电职业技术学院 床位号存在中文，个性化修改
			String sb = "";
			if("12898".equals(Base.xxdm)){
				sb = "a.cwh";
			}else{
				sb = "a.cwh";
			}
			if("10868".equals(Base.xxdm)){
				sql.append(" select rownum r,a.*,(select xymc from zxbz_xxbmdm where bmdm = a.xydm) cwxymc,(select bjmc from bks_bjdm where bjdm = a.bjdm) cwbjmc");
				sql.append(" from (select lddm || qsh || cwh pkValue,a.*,b.xm,b.zymc xszymc,b.zydm xszydm,b.bjmc xsbjmc, b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm,(case when a.xydm is not null or a.nj is not null or");
				sql.append(" a.xh is not null then 'disabled'  else '' end) dis,(case when length(a.bz) > 5 then substr(a.bz, 1, 5) || '...' else a.bz end) subbz,");
				sql.append(" c.gxsj,c.sqxj,c.cxsj  from view_xg_gygl_new_cwxx a");
				sql.append("  left join view_xsbfxx b  on a.xh = b.xh");
				sql.append(" left join ( select * from (select row_number() over(partition by t.lddm , t.qsh order by t.gxsj desc) rn,t.gxsj,t.sqxj,t.cxsj,t.lddm || t.qsh qshx from xg_gygl_new_xjqsjgb t) where rn = 1 )c");
				sql.append(" on a.lddm || a.qsh = c.qshx  order by a.lddm, to_number(a.ch), a.qsh, "+sb+") a  where 1 = 1");
			}else{
				sql.append("select rownum r,a.*,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from bks_bjdm where bjdm=a.bjdm) cwbjmc from (select lddm||qsh||cwh pkValue,a.*,a.xymc as xsxymc,b.xm,b.zymc xszymc,b.zydm xszydm, b.bjmc xsbjmc,b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm,"
						+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis,(case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz "
						+ "from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh order by a.lddm,a.ch,a.qsh,"+sb+") a where 1=1 ");
			}
			
		}

		String[] output = null;
		if (Globals.XXDM_zjgmzyjsxy.equalsIgnoreCase(Base.xxdm)) {
			output = new String[] { "pkValue", "dis", "ldmc", "qsh", "cwh",
					"qsxb", "xh", "xm", "xszymc", "xsbjmc", "sfbl", "nj",
					"xydm", "bjdm", "cwxymc", "cwbjmc", "zymc", "sjhm" };
		} else if("13033".equalsIgnoreCase(Base.xxdm)) {// 湖南机电职业技术学院
			output = new String[] { "pkValue", "dis", "ldmc", "qsh", "cwh",
					"qsxb", "xh", "xm", "xszymc", "xsbjmc", "sfbl", "nj",
					"xydm", "bjdm", "cwxymc", "cwbjmc", "zymc", "subbz", "bz", "n" };
		}else if("10868".equals(Base.xxdm)){
			output = new String[] { "pkValue", "dis", "ldmc", "qsh", "cwh",
					"qsxb", "xh", "xm", "xszymc", "xsbjmc", "sfbl", "nj",
					"xydm", "bjdm", "cwxymc", "cwbjmc", "zymc", "subbz", "bz","gxsj","sqxj","cxsj"};
		}
		else if("12216".equals(Base.xxdm)){
			output = new String[] { "pkValue", "dis", "ldmc", "qsh", "cwh",
					"qsxb", "xh", "xm","xszymc", "xsbjmc", "sfbl", "nj",
					"xydm", "bjdm", "cwxymc", "cwbjmc", "zymc", "subbz", "bz", "lddm","xymc" };
		}else {
			output = new String[] { "pkValue", "dis", "ldmc", "qsh", "cwh",
					"qsxb", "xh", "xm", "xszymc", "xsbjmc", "sfbl", "nj",
					"xydm", "bjdm", "cwxymc", "cwbjmc", "zymc", "subbz", "bz", "lddm" };
		}
		try {
			String var = null;
			for(int i=0 ; i<output.length;i++){
				var += output[i]+",";
			}
			System.out.println(var);
			if (isHavePage) {
				list = CommonQueryDAO.commonQuery(sql.toString(), searchTj
						+ searchTjQx, inputV, output, myForm);
			} else {
				list = CommonQueryDAO.commonQueryNotFy(sql.toString(), searchTj
						+ searchTjQx, inputV, output, myForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @描述:(如果是辅导员或是班主任角色，生成过滤条件查询辅导员带班所在的专业的床位)
	 * @作者：cmj[工号：913]
	 * @日期：2013-9-5 上午10:04:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return String 返回类型
	 * @throws
	 */
	private String getFdySearchZyfp(User user) {
		// 辅导员权限
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// 班主任权限
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		// 用户名
		String userName = user.getUserName();
		String search = "";
		if (!fdyqx && bzrqx) {// 只有班主任权限
			search = " and ((( sfrz = '否' ) and exists(select 1 from (select distinct xydm,nj,zydm from view_njxyzybj a where exists(select 1 from (select * from bzrbbb where zgh='"
					+ userName
					+ "' ) where bjdm=a.bjdm )) where a.xydm=xydm and nj=a.nj and zydm=a.zydm))or  (exists (select 1 from bzrbbb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName + "') ) )";
		} else if (fdyqx && !bzrqx) {// 只有辅导员权限
			search = " and ((( sfrz = '否' ) and exists(select 1 from (select distinct xydm,nj,zydm from view_njxyzybj a where exists(select 1 from (select * from fdybjb where zgh='"
					+ userName
					+ "' ) where bjdm=a.bjdm )) where a.xydm=xydm and nj=a.nj and zydm=a.zydm))or  (exists (select 1 from fdybjb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName + "')) )";
		} else if (fdyqx && bzrqx) {// 辅导员班主任权限
			search = " and ((( sfrz = '否' ) and exists(select 1 from (select distinct xydm,nj,zydm from view_njxyzybj a where exists(select 1 from (select * from bzrbbb where zgh='"
					+ userName
					+ "' union select * from fdybjb where zgh='"
					+ userName
					+ "') where bjdm=a.bjdm )) where a.xydm=xydm and nj=a.nj and zydm=a.zydm))or  (exists (select 1 from bzrbbb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName
					+ "')  or exists (select 1 from fdybjb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName + "')) )";
		}
		return search;
	}

	/**
	 * @描述:TODO(如果是辅导员或是班主任角色，生成过滤条件查询辅导员带班所在的学院的床位)
	 * @作者：cmj [工号：913]
	 * @日期：2013-6-18 下午05:50:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return String 返回类型
	 * @throws
	 */
	public String getFdySearch(User user) {
		// TODO 自动生成方法存根
		// 辅导员权限
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// 班主任权限
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		// 用户名
		String userName = user.getUserName();
		String search = "";
		if (!fdyqx && bzrqx) {// 只有班主任权限
			search = " and ((( sfrz = '否' ) and exists(select 1 from (select distinct xydm,nj from view_njxyzybj a where exists(select 1 from (select * from bzrbbb where zgh='"
					+ userName
					+ "' ) where bjdm=a.bjdm )) where a.xydm=xydm and nj=a.nj))or  (exists (select 1 from bzrbbb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName + "') ) )";
		} else if (fdyqx && !bzrqx) {// 只有辅导员权限
			search = " and ((( sfrz = '否' ) and exists(select 1 from (select distinct xydm,nj from view_njxyzybj a where exists(select 1 from (select * from fdybjb where zgh='"
					+ userName
					+ "') where bjdm=a.bjdm )) where a.xydm=xydm and nj=a.nj))or  (exists (select 1 from fdybjb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName + "')) )";
		} else if (fdyqx && bzrqx) {// 辅导员班主任权限
			search = " and ((( sfrz = '否' ) and exists(select 1 from (select distinct xydm,nj from view_njxyzybj a where exists(select 1 from (select * from bzrbbb where zgh='"
					+ userName
					+ "' union select * from fdybjb where zgh='"
					+ userName
					+ "') where bjdm=a.bjdm )) where a.xydm=xydm and nj=a.nj))or  (exists (select 1 from bzrbbb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName
					+ "')  or exists (select 1 from fdybjb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName + "')) )";
		}
		return search;
	}

	/**
	 * 获取当前查询数据集已入住的床位总数
	 * 
	 * @return
	 */
	public String getYrzrs(ZsxxglForm myForm, HttpServletRequest request)
			throws Exception {
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		// 权限控制
		String searchTjQx = "";
		
		CommService service = new CommService();
		User user = service.getUser(request);
		
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "xsbjdm"); // 学院用户
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // 公寓辅导员

		if("xy".equalsIgnoreCase(user.getUserType()) && "11799".equals(Base.xxdm)){
			searchTjQx = " and (a.xydm = '" + user.getUserDep() + "'  or a.xh is null )" ;
		} else if (searchTjByGyfdy != null && !"".equalsIgnoreCase(searchTjByGyfdy)) {// 用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		} else {// 用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = searchTjByUser;
		}
		
		StringBuffer sql = new StringBuffer();
		if (Globals.XXDM_NTZYDX.equals(Base.xxdm)) {
			sql.append("select count(*) num ");
			sql.append("from (select a.*,b.xm,b.rxfs rxfsdm,b.bjdm xsbjdm from view_xg_gygl_new_cwxx a");	
			sql.append(" left join view_xsxxb b on a.xh=b.xh) a where 1=1 "); 
		}else if(Globals.XXDM_DBSYDX.equals(Base.xxdm)){
			sql.append("select count(*) num ");  
			sql.append("from (select a.*, b.xm, b.xz, b.pycc, b.bjdm xsbjdm ");
			sql.append("from view_xg_gygl_new_cwxx a ");
			sql.append("left join view_xsjbxx b ");
			sql.append("on a.xh = b.xh) a ");			
			sql.append("left join (select ydhlddm, ydhqsh, ydhcwh, tstzsj tzsj from (");	
			sql.append("SELECT a.*,row_number() over(partition by ydhlddm, ydhqsh, ydhcwh order by tstzsj desc) rn ");			 
			sql.append("FROM XG_GYGL_NEW_SSYD_SSYDJG a) ");			
			sql.append("where rn = 1) b ");			
			sql.append("on a.lddm = b.ydhlddm ");
			sql.append("and a.qsh = b.ydhqsh ");			
			sql.append("and a.cwh = b.ydhcwh ");
			sql.append("left join (select ydqlddm, ydqqsh, ydqcwh, tstzsj tssj ");
			sql.append("from (SELECT a.*,");
			sql.append("row_number() over(partition by ydqlddm, ydqqsh, ydqcwh order by tstzsj desc) rn ");
			sql.append("FROM XG_GYGL_NEW_SSYD_SSYDJG a) ");
			sql.append("where rn = 1) c ");
			sql.append("on a.lddm = c.ydqlddm ");
			sql.append("and a.qsh = c.ydqqsh ");          
			sql.append("and a.cwh = c.ydqcwh ");        
			sql.append("where 1 = 1");
		}else{
			sql.append("select count(*) num from (");
			sql.append("select a.*,b.xm,b.xz,b.pycc,b.bjdm xsbjdm from ");
			sql.append("view_xg_gygl_new_cwxx a ");
			sql.append("left join view_xsjbxx b on a.xh=b.xh) a where 1=1 ");
		}

		String rs = dao.getOneRs(
				sql.toString() + searchTj + searchTjQx + " and sfrz ='是'", inputV, "num");
		return rs;
	}

	/**
	 * 床位对调
	 * 
	 * @param pk
	 * @return
	 */
	public boolean cwdd(String[] pk, ZsxxglForm model) {
		boolean flag = false;
		TsglForm tsglForm = new TsglForm();
		tsglForm.setTsyy("住宿异动");
		tsglForm.setTssj(model.getRzsj());
		tsglForm.setBz(model.getBz());
		tsglForm.setTsczr(model.getCzr());

		if (pk != null && pk.length == 2) {
			String sql = "select xh,qsxb from view_xg_gygl_new_cwxx where lddm||qsh||cwh=?";
			Map<String, String> cwxx1 = dao.getMap(sql, new String[] { pk[0] },
					new String[] { "xh", "qsxb" });
			Map<String, String> cwxx2 = dao.getMap(sql, new String[] { pk[1] },
					new String[] { "xh", "qsxb" });

			if (Base.isNull(cwxx1.get("qsxb"))
					|| !cwxx1.get("qsxb").equals(cwxx2.get("qsxb"))) {
				return flag;
			}
			String xh1 = cwxx1.get("xh");
			String xh2 = cwxx2.get("xh");

			sql = "select nj,xydm,bjdm,zydm from view_xsjbxx where xh=?";
			List<String> sqlArr = new ArrayList<String>();
			String[] njxy;
			// 处理第一个床位上学生的sql放在第二个床位上
			if (!Base.isNull(xh1)) {
				njxy = dao.getOneRs(sql, new String[] { xh1 }, new String[] {
						"nj", "xydm", "bjdm", "zydm" });
				sqlArr.add("update xg_gygl_new_cwxxb set nj='" + njxy[0]
						+ "',xydm='" + njxy[1] + "',bjdm='" + njxy[2]
						+ "',zydm='" + njxy[3] + "',xh='" + xh1 + "',"
						+ "rzsj='" + model.getRzsj() + "',rzyydm='"
						+ model.getRzyy() + "',rzczr='" + model.getCzr()
						+ "' where lddm||qsh||cwh='" + pk[1] + "'");
				tsglForm.setLddm(pk[0]);
				sqlArr.add(getTsxxSql(tsglForm));
			} else {
				sqlArr
						.add("update xg_gygl_new_cwxxb set xh='',rzsj='',rzczr='' where lddm||qsh||cwh='"
								+ pk[1] + "'");
			}
			// 处理第二个床位上学生的sql放在第一个床位上
			if (!Base.isNull(xh2)) {
				njxy = dao.getOneRs(sql, new String[] { xh2 }, new String[] {
						"nj", "xydm", "bjdm", "zydm" });
				sqlArr.add("update xg_gygl_new_cwxxb set nj='" + njxy[0]
						+ "',xydm='" + njxy[1] + "',bjdm='" + njxy[2]
						+ "',zydm='" + njxy[3] + "',xh='" + xh2 + "',"
						+ "rzsj='" + model.getRzsj() + "',rzyydm='"
						+ model.getRzyy() + "',rzczr='" + model.getCzr()
						+ "' where lddm||qsh||cwh='" + pk[0] + "'");
				tsglForm.setLddm(pk[1]);
				sqlArr.add(getTsxxSql(tsglForm));
			} else {
				sqlArr
						.add("update xg_gygl_new_cwxxb set xh='',rzsj='',rzczr='' where lddm||qsh||cwh='"
								+ pk[0] + "'");
			}

			CommDAO dao = new CommDAO();
			try {
				flag = dao.saveArrDate(sqlArr.toArray(new String[] {}));
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}

		return flag;
	}

	/**
	 * 查询床位信息_(导出床头卡用的结果集)
	 * 
	 * @param model
	 * @return
	 */
	public List<String[]> queryCw_expCtk(ZsxxglForm myForm,
			HttpServletRequest request) throws Exception {

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// 权限控制
		String searchTjQx = "";

		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "bjdm"); // 学院用户
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // 公寓辅导员

		if (searchTjByGyfdy != null && !"".equalsIgnoreCase(searchTjByGyfdy)) {// 用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		} else {// 用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = searchTjByUser;
		}

		// 如果是选择的复选框，那么就按照复选框进行选择
		String[] pks = myForm.getPrimarykey_checkVal();
		if (pks != null && pks.length > 0) {
			if (pks.length > 1000) {
				new ArrayList<String[]>();
			}
			searchTj = " and lddm||qsh||cwh in (";
			searchTjQx = "";
			inputV = pks;

			for (int i = 0; i < pks.length; i++) {
				searchTj += "?,";
			}
			searchTj = searchTj.substring(0, searchTj.length() - 1);
			searchTj += ") ";
		}

		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();

		sql
				.append("select rownum r,a.* from (select lddm||qsh||cwh pkValue,a.*,b.xm,"
						+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis "
						+ ",c.xymc xsxymc,c.zymc xszymc,c.bjmc xsbjmc "
						+ "from view_xg_gygl_new_cwxx a left join "
						+ Base.xsxxb
						+ " b on a.xh=b.xh "
						+ "left join view_njxyzybj_all c on b.bjdm=c.bjdm "
						+ "order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where 1=1 ");

		// sql.append("select * from (" +
		// "select a.lddm,a.ldmc,a.qsh,a.cwh,a.xh,b.xm,c.xymc,c.zymc,c.bjmc,a.bjdm "
		// +
		// "from view_xg_gygl_new_cwxx a " +
		// "left join "+Base.xsxxb+" b on a.xh=b.xh " +
		// "left join view_njxyzybj_all c on b.bjdm=c.bjdm " +
		// "order by a.lddm,to_number(a.ch),a.qsh,a.cwh" +
		// ") a where 1=1 ");

		String[] output = new String[] { "ldmc", "qsh", "cwh", "xh", "xm",
				"xsxymc", "xszymc", "xsbjmc" };

		try {
			list = CommonQueryDAO.commonQueryNotFy(sql.toString(), searchTj
					+ searchTjQx, inputV, output, myForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @描述:TODO(获取床位操作时间范围)
	 * @作者：cmj [工号：913]
	 * @日期：2013-6-20 下午03:17:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return String 返回类型
	 * @throws
	 */
	public String getCwczsjfw() {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		String sql = "select csz from xg_gygl_new_jbszb where csdm='fdyczsjsz'";
		dao.getOneRs(sql, new String[] {}, "csz");
		return dao.getOneRs(sql, new String[] {}, "csz");
	}

	/**
	 * 住宿信息管理自定义导出
	 * 
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> queryExportCw(ZsxxglForm myForm,
			HttpServletRequest request) throws Exception {

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// 权限控制
		String searchTjQx = "";

		SearchService searchService = new SearchService();
		String searchTjByUser = "";
		if ("xydm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			searchTjByUser = searchService.getSearchTjByUser(request, "a",
					"xydm", "xsbjdm"); // 学院用户
			searchTj = searchTj.replaceAll("bjdm", "xsbjdm");
			searchTj = searchTj.replaceAll("zydm", "xszydm");
		}
		if ("bjdm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			searchTjByUser = searchService.getSearchTjByUser(request, "a",
					"xydm", "bjdm");
		}
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // 公寓辅导员

		if (searchTjByGyfdy != null && !"".equalsIgnoreCase(searchTjByGyfdy)) {// 用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		} else {// 用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = searchTjByUser;
		}

		StringBuilder sql = new StringBuilder();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (Globals.XXDM_NTZYDX.equals(Base.xxdm)) {// 处理南通职业个性化入学方式
			sql
					.append("select rownum r,a.* from (select lddm||qsh||cwh pkValue,a.*,b.xm,"
							+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis ,a.bjmc cwbjmc,a.xymc cwxymc,b.bjmc xsbjmc, b.zymc xszymc "
							+ "from view_xg_gygl_new_cwxx a left join "
							+ xsxxb_ntzydx
							+ " b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where 1=1 ");
		} else {
			sql.append("select rownum r,a.*,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from bks_bjdm where bjdm=a.bjdm) cwbjmc ");
			sql.append("from (select lddm||qsh||cwh pkValue,a.*,b.xm,b.zymc xszymc,b.zydm xszydm, b.sjhm lxfs, b.bjmc xsbjmc,b.bjdm xsbjdm,e.bzr,f.fdy, ");
			sql.append("(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis ,b.xz,b.pycc,g.tzjl ");
			if("10868".equals(Base.xxdm)){
				sql.append(" ,c.gxsj,c.sqxj,c.cxsj ");
			}
			sql.append("from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh left join (select bjdm, wm_concat(bzr) bzr from ");
			sql.append("(select a.bjdm, b.xm || b.lxdh bzr from bzrbbb a left join fdyxxb b on a.zgh = b.zgh) group by bjdm) e on b.bjdm = e.bjdm ");
			sql.append(" left join (select wm_concat(fdy) fdy, bjdm from (select b.xm || b.lxdh fdy, a.bjdm from fdybjb a left join ");
			sql.append("fdyxxb b on a.zgh = b.zgh )group by bjdm) f on b.bjdm = f.bjdm ");
			sql.append("left join (select * from (select t.xh, wm_concat(t.ydjl) over(partition by xh order by ydjl) tzjl, row_number() over(partition by xh order by ydjl desc) r from "
					+ "(select t.xh, case when t.ssydlx = '01' then tstzsj || ' ' || ssydlxmc || ' ' || tsqs || ' -> ' || rzqs else tstzsj || ' ' || ssydlxmc || ' ' || tsqs || rzqs end ydjl "
					+ "from (select * from (select t.xh, t.ssydlx, t.tstzsj, t.ydqldmc || '_' || t.ydqqsh || '_' || t.ydqcwh tsqs, case when t.ydhldmc is not null and t.ydhqsh "
					+ "is not null and t.ydhcwh is not null then t.ydhldmc || '_' || t.ydhqsh || '_' || t.ydhcwh else '' end as rzqs, case when t.ssydlx = '00' then '退宿' "
					+ "when t.ssydlx = '01' then '宿舍调整' when t.ssydlx = '03' then '入住' end ssydlxmc from XG_GYGL_NEW_SSYD_SSYDJG t) ) t) t) where r = 1) g on a.xh = g.xh "); //增加学生寝室调整记录字段
			if("10868".equals(Base.xxdm)){
				sql.append(" left join (select * from (select row_number() over(partition by t.lddm , t.qsh order by t.gxsj desc) rn,t.gxsj,t.sqxj,t.cxsj,t.lddm || t.qsh qshx from xg_gygl_new_xjqsjgb t) where rn = 1");
				sql.append("  )c on a.lddm || a.qsh = c.qshx ");
			}
			sql.append( "order by a.lddm,to_number(a.ch),a.qsh,to_number(a.cwh)) a where 1=1 ");
		}

		String[] output = null;
		output = new String[] { "pkValue", "dis", "ldmc", "qsh", "cwh", "qsxb",
				"xh", "xm", "xszymc", "xsbjmc", "sfbl", "nj", "xydm", "bjdm",
				"cwxymc", "cwbjmc", "sfbz","bzr","fdy","lxfs" };

		try {
			list = CommonQueryDAO.commonQueryforExportList(sql.toString(),
					searchTj + searchTjQx, inputV, output, myForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获得入住原因信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRzyyList(ZsxxglForm model) throws Exception {
		String searchTj = "";
		List<String> inputV = new ArrayList<String>();
		String sql = "select * from (select a.*,rownum r from xg_gygl_new_rzyydmb a order by a.rzyydm) ";
		String[] output = new String[] { "rzyydm", "rzyydm", "rzyymc" };
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV
				.toArray(new String[] {}), output, model);
	}

	/**
	 * 
	 * @描述:TODO入住原因代码维护
	 * @作者xucy[工号：754]
	 * @日期：2013-8-29 上午10:29:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	public String saveRzyy(ZsxxglForm model, String type) throws Exception {

		String msg = "参数错误！";
		boolean b = false;
		String sql;
		if ("add".equals(type)) {
			sql = "select count(1) num from xg_gygl_new_rzyydmb where rzyydm=?";
			String num = dao.getOneRs(sql, new String[] { model.getRzyydm() },
					"num");
			if ("0".equals(num)) {
				sql = "insert into xg_gygl_new_rzyydmb(rzyydm,rzyymc) values(?,?)";
				b = dao.runUpdate(sql, new String[] { model.getRzyydm(),
						model.getRzyymc() });
				msg = (b ? "操作成功！" : "操作失败！");
			} else {
				msg = "入住原因代码已存在！";
			}
		} else if ("update".equals(type)) {
			sql = "update xg_gygl_new_rzyydmb  set rzyymc=? where rzyydm=? ";
			b = dao.runUpdate(sql, new String[] { model.getRzyymc(),
					model.getRzyydm() });
			msg = (b ? "操作成功！" : "操作失败！");
		} else if ("delete".equals(type)) {

			StringBuilder check = new StringBuilder();
			check
					.append(" select rzyydm from xg_gygl_new_cwxxb where rzyydm=? ");
			String gyjlcfdm = dao.getOneRs(check.toString(),
					new String[] { model.getRzyydm() }, "rzyydm");

			if (Base.isNull(gyjlcfdm)) {
				sql = "delete from  xg_gygl_new_rzyydmb  where rzyydm=? ";
				b = dao.runUpdate(sql, new String[] { model.getRzyydm() });
				msg = (b ? "操作成功！" : "操作失败！");
			} else {
				msg = "入住原因代码已经使用，不能删除！";
			}
		}
		return msg;
	}
	//获得学院名称
	public String getXymc(String xydm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xymc from VIEW_NJXYZYBJ where xydm = ? ");
		String[] inputValue = { xydm };
		String[] outputValue = { "xymc" };
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String mc = "";
		if (oneRs != null && oneRs.length > 0) {
			mc = oneRs[0];
		}
		return mc;
	}
	//获得专业名称
	public String getZymc(String zydm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select zymc from VIEW_NJXYZYBJ where zydm = ? ");
		String[] inputValue = { zydm };
		String[] outputValue = { "zymc" };
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String mc = "";
		if (oneRs != null && oneRs.length > 0) {
			mc = oneRs[0];
		}
		return mc;
	}
	
	
	/**
	 * 
	 * @描述: 学生违纪详细信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-2-25 上午10:16:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXswjxx(ZsxxglForm model, User user)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,(select xqmc from XQDZB where xqdm = a.xq) xqmc,b.xm,b.xb,b.bjdm,b.bjmc,c.fjmc ssfjmc,c.fj ssfj,c.sqly, ");
	    sql.append(" (case when jcwh is not null then jcsj when zzsj is not null then zzsj when sssj is not null then sssj else cfsj end) fwsj, ");
	    sql.append(" (case when jcwh is not null then jcwh when zzwh is not null then zzwh when sswh is not null then sswh else cfwh end) fwwh, ");
	    sql.append(" (case when jcwh is not null then '解除处分' when zzwh is not null then '终止处分' when a.ssjg is not null then a.ssjg else '处分成立' end) fwjg ");
	    sql.append(" from xg_wjcf_wjcfb a left join view_xsxxb b on a.xh = b.xh left join xg_wjcf_wjcfssb c on a.cfid = c.cfid ");
	    sql.append(" where 1 = 1 ");
	    sql.append(" and a.xh = ? ");
		ZsxxglDaoForPage dao = new ZsxxglDaoForPage();
		return dao.getPageListOld(model, sql.toString(), new String[] {model.getXh()});
	}
	
	/**
	 * 
	 * @描述:获取辅导员【黑龙江农垦导出寝室名单功能专用】
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-6 上午11:58:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qsh
	 * @param searchTj
	 * @param inputV
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getFdy(String qsh,String searchTj,String[] inputV){
		StringBuilder sql = new StringBuilder();
		List<String> paralist = new ArrayList<String>();
		sql.append("  select replace(wm_concat(xm), ';', '、') fdy");
		sql.append(" from fdyxxb");
		sql.append(" where zgh in");
		sql.append(" (select distinct zgh");
		sql.append(" from fdybjb");
		sql.append(" where bjdm in");//and lddm = ?
		sql.append(" (select bjdm from(");
		sql.append(" select rownum r,");
		sql.append("  a.*,");
		sql.append(" (select xymc from zxbz_xxbmdm where bmdm = a.xydm) cwxymc,");
		sql.append(" (select bjmc from bks_bjdm where bjdm = a.bjdm) cwbjmc");
		sql.append(" from (select lddm || qsh || cwh pkValue,");
		sql.append(" a.*,");
		sql.append(" b.xm,");
		sql.append(" b.zymc xszymc,");
		sql.append(" b.zydm xszydm,");
		sql.append(" b.bjmc xsbjmc,");
		sql.append(" b.bjdm xsbjdm,");
		sql.append(" b.xz,");
		sql.append(" b.pycc,");
		sql.append(" b.sjhm,");
		sql.append(" (case");
		sql.append(" when a.xydm is not null or a.nj is not null or");
		sql.append(" a.xh is not null then");
		sql.append(" 'disabled'");
		sql.append("  else");
		sql.append(" ''");
		sql.append(" end) dis,");
		sql.append(" (case");
		sql.append(" when length(a.bz) > 5 then");
		sql.append(" substr(a.bz, 1, 5) || '...'");
		sql.append(" else");
		sql.append(" a.bz");
		sql.append(" end) subbz");
		sql.append(" from view_xg_gygl_new_cwxx a");
		sql.append(" left join view_xsbfxx b");
		sql.append(" on a.xh = b.xh");
		sql.append(" order by a.lddm, to_number(a.ch), a.qsh, to_number(a.cwh)) a");
		sql.append(" where 1 = 1 ");
		sql.append(searchTj);
		sql.append(" ");
		sql.append(" ) where qsh = ? ))");
		for(int i=0;i<inputV.length;i++){
			paralist.add(inputV[i]);
		}
		paralist.add(qsh);
//		sql.append("  (select bjdm from view_xg_gygl_new_cwxx where qsh = ? ))");
		return dao.getOneRs(sql.toString(),paralist.toArray(new String[]{}), "fdy");
	}
	/**
	 * @throws Exception  
	 * @描述:批量保存床位信息表备注的修改
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月29日 下午2:29:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pkValueArr
	 * @param bz
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveBzBatchForUpdate(String[] pkValueArr, String bz) throws Exception {
		StringBuilder sql = new StringBuilder("UPDATE xg_gygl_new_cwxxb SET BZ = ? WHERE (LDDM||QSH||CWH) IN (");
		List<String> paraList = new ArrayList<String>();
		paraList.add(bz);
		for(int i=0;i<pkValueArr.length;i++){
			paraList.add(pkValueArr[i]);
			sql.append("?");
			if(i!=pkValueArr.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		boolean result = dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));
		return result;
	}
	
	/** 
	 * @描述:根据学号清空床位信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-23 下午02:12:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deleteCwxxByXh(String xh) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_gygl_new_cwxxb set xh = null where xh = ?");
		return dao.runUpdate(sb.toString(), new String[]{xh});
	}
	
	/** 
	 * @描述:根据床位具体信息清空床位信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-23 下午02:31:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param qsh
	 * @param cwh
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateCwxxByDetils(String lddm,String qsh,String cwh,String xh) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_gygl_new_cwxxb set xh = ? where lddm = ? and qsh = ? and cwh = ?");
		return dao.runUpdate(sb.toString(), new String[]{xh,lddm,qsh,cwh});
	}
	
	/**
	 * 
	 * @描述: 甘肃交通职业技术学院自定义导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-1 上午11:47:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> queryExportCwForGsjt(ZsxxglForm myForm,
			HttpServletRequest request) throws Exception {
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// 权限控制
		String searchTjQx = "";

		SearchService searchService = new SearchService();
		String searchTjByUser = "";
		if ("xydm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			searchTjByUser = searchService.getSearchTjByUser(request, "a",
					"xydm", "xsbjdm"); // 学院用户
			searchTj = searchTj.replaceAll("bjdm", "xsbjdm");
			searchTj = searchTj.replaceAll("zydm", "xszydm");
		}
		if ("bjdm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			searchTjByUser = searchService.getSearchTjByUser(request, "a",
					"xydm", "bjdm");
		}
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // 公寓辅导员

		if (searchTjByGyfdy != null && !"".equalsIgnoreCase(searchTjByGyfdy)) {// 用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		} else {// 用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = searchTjByUser;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select replace(wm_concat(distinct bjmc),';',',') bjmc,replace(wm_concat(distinct xm),';',',') xm,ldmc,qsh from (");
		sql.append(" select  a.*,(select xymc from zxbz_xxbmdm where bmdm = a.xydm) cwxymc,");
		sql.append(" (select bjmc from bks_bjdm where bjdm = a.bjdm) cwbjmc");
		sql.append(" from (select lddm || qsh || cwh pkValue,");
		sql.append(" a.*,");
		sql.append(" b.xm,");
		sql.append(" b.zymc xszymc,");
		sql.append(" b.zydm xszydm,");
		sql.append(" b.sjhm lxfs,");
		sql.append(" b.bjmc xsbjmc,");
		sql.append(" b.bjdm xsbjdm,");
		sql.append(" e.bzr,");
		sql.append(" f.fdy,");
		sql.append(" (case");
		sql.append(" when a.xydm is not null or a.nj is not null or");
		sql.append(" a.xh is not null then");
		sql.append(" 'disabled'");
		sql.append(" else");
		sql.append(" ''");
		sql.append(" end) dis,");
		sql.append(" b.xz,");
		sql.append(" b.pycc,");
		sql.append(" g.tzjl");
		sql.append(" from view_xg_gygl_new_cwxx a");
		sql.append(" left join view_xsbfxx b");
		sql.append(" on a.xh = b.xh");
		sql.append(" left join (select bjdm, wm_concat(bzr) bzr");
		sql.append(" from (select a.bjdm, b.xm || b.lxdh bzr");
		sql.append(" from bzrbbb a");
		sql.append(" left join fdyxxb b");
		sql.append(" on a.zgh = b.zgh)");
		sql.append(" group by bjdm) e");
		sql.append(" on b.bjdm = e.bjdm");
		sql.append(" left join (select wm_concat(fdy) fdy, bjdm");
		sql.append(" from (select b.xm || b.lxdh fdy, a.bjdm");
		sql.append(" from fdybjb a");
		sql.append(" left join fdyxxb b");
		sql.append(" on a.zgh = b.zgh)");
		sql.append(" group by bjdm) f");
		sql.append(" on b.bjdm = f.bjdm");
		sql.append(" left join (select *");
		sql.append(" from (select t.xh,");
		sql.append(" wm_concat(t.ydjl) over(partition by xh order by ydjl) tzjl,");
		sql.append(" row_number() over(partition by xh order by ydjl desc) r");
		sql.append(" from (select t.xh,");
		sql.append(" case when t.ssydlx = '01' then");
		sql.append(" tstzsj || ' ' || ssydlxmc || ' ' || tsqs ||  ' -> ' || rzqs");
		sql.append(" else tstzsj || ' ' || ssydlxmc || ' ' || tsqs || rzqs  end ydjl");
		sql.append(" from (select * from (select t.xh,t.ssydlx,t.tstzsj,t.ydqldmc || '_' || t.ydqqsh || '_' || t.ydqcwh tsqs,");
		sql.append(" case when t.ydhldmc is not null and  t.ydhqsh is not null and  t.ydhcwh is not null then");
		sql.append(" t.ydhldmc || '_' || t.ydhqsh || '_' ||t.ydhcwh else '' end as rzqs,");
		sql.append(" case  when t.ssydlx = '00' then '退宿'");
		sql.append(" when t.ssydlx = '01' then '宿舍调整'");
		sql.append(" when t.ssydlx = '03' then '入住' end ssydlxmc from XG_GYGL_NEW_SSYD_SSYDJG t)) t) t) where r = 1) g");
		sql.append(" on a.xh = g.xh");
		sql.append(" order by a.lddm, to_number(a.ch), a.qsh, to_number(a.cwh)) a");
		sql.append(" where 1 = 1");
		sql.append(" "+searchTj + searchTjQx);
		sql.append(" ");
		sql.append("  )  group by ldmc,qsh");
		return DAO.getInstance().getListNotOut(sql.toString(), inputV);
	}
}
