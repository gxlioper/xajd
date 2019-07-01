package xgxt.comm.homepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.xtwh.yhsjfw.YhsjfwService;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.comm.xml.XMLReader;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;

import common.Globals;

public class HomePageDAO {
	
	
	/**
	 * 获取 学生资助待办事项(通用)
	 * @param user 
	 * @return  List<HashMap<String,String>>
	 * author 潇洒的裘
	 */
	public List<HashMap<String,String>>getXszzDbsx(User user,HomePageModel model){
		
		DAO dao = DAO.getInstance();
		//用户名
		String yhm = user.getUserName();
		//用户类型
		String type = user.getUserType();
		//用户部门
		String bmdm = user.getUserDep();
		//显示记录数
		int maxSize=model.getMaxSize();
		
		//辅导员权限
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		//班主任权限
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		
		boolean isXy = false;
		
		if ("xy".equalsIgnoreCase(type) && !fdyqx && !bzrqx) {
			// 学院用户
			isXy = true;
		}
		
		StringBuilder sb=new StringBuilder();
		
		
		//资助学院用户需要审项目
		//项目代码、学年、学期、年度、申请时间前4位
		boolean blog=false;
		if(isXy){
			blog=true;
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and xysh='是' ) ");
			sb.append(" and xysh='未审核'  and c.xydm='"+bmdm+"' ");
			//二级审核
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='两级审核' and xysh='是' ) ");
			sb.append(" and xysh='未审核'  and a.xh=c.xh  and (fdysh='通过' or bzrsh='通过' or xysh='未审核') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			//二级审核
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,sqsj from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='三级审核' and xysh='是' ) ");
			sb.append(" and xysh='未审核'  and a.xh=c.xh  and (fdysh='通过' or bzrsh='通过') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			
		}
		
		//辅导员
		if(fdyqx ){
			if(blog){
				sb.append("union all");
			}
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and fdysh='是' ) ");
			sb.append(" and fdysh='未审核' and a.xh=c.xh ");
			sb.append(" and exists(select 1 from fdybjb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "')");
			blog=true;
		}
		
		//班主任
		if(bzrqx){
			if(blog){
				sb.append("union all");
			}
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c  where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and bzrsh='是' ) ");
			sb.append(" and bzrsh='未审核' and a.xh=c.xh ");
			sb.append("  and exists(select 1 from  bzrbbb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "') ");
			blog=true;
		}
		
		//管理员、学校
		if("admin".equalsIgnoreCase(type) || "xx".equalsIgnoreCase(type)){
			if(blog){
				sb.append("union all");
			}
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb where xmdm in ");	
			sb.append(" ( select xmdm from xszz_zzxmb where shjb='一级审核' and xxsh='是'  ) ");
			sb.append(" and xxsh='未审核' ");
			sb.append(" union all");
			//二级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='两级审核' and (bzrsh='是' and b.bzrsh='通过' or xysh='是' and b.xysh='通过' or  fdysh='是' and b.fdysh='通过' ) )");
			sb.append("  and xxsh='未审核' ");
			sb.append("union all");
			//三级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='三级审核' and xysh='是' and b.xysh='通过'  ) ");
			sb.append("  and xxsh='未审核' ");
			
		}
		
		String sql="select * from (";
		sql+="select * from (select rownum r,'学生资助' mklx,'未审核' dblx, a.xmdm, a.xsh,a.xn,a.xq,a.nd,a.sqn,b.xmmc,b.sqzq from( select xmdm,count(1) xsh, xn,xq,nd,sqn from ("+sb.toString()+") group by xmdm,xn,xq,nd,sqn)a,xszz_zzxmb b where a.xmdm=b.xmdm)a where r<10 ";
		sql+=" and( (a.sqzq='学年' and exists(select 1 from xtszb b where a.xn=b.dqxn)) ";
		sql+=" or (a.sqzq='学期' and exists(select 1 from xtszb b where a.xn=b.dqxn and a.xq=b.dqxq)) ";
		sql+=" or (a.sqzq='年度' and exists(select 1 from xtszb b where a.nd=b.dqnd)) or (a.sqzq='无周期' and exists(select 1 from xtszb b where a.sqn=b.dqnd))" ;
		sql+=" or (a.sqzq='仅一次' and exists(select 1 from xtszb b where a.sqn=b.dqnd)))";
		
		//过滤一部分项目
		for(int i=0;i<getXszzxmList(user).size();i++){
			HashMap<String,String>hashMap=getXszzxmList(user).get(i);
			sql+=" or xmdm= '"+hashMap.get("xmdm")+"' ";
		}
		sql+=")where rownum <= "+maxSize;
	
		return  dao.getList(sql,new String[]{},new String[]{"mklx","xmdm","xmmc","xsh","dblx"});
	}
	
	/**
	 * 获取学生资助项目列表
	 * @param user
	 * @return 
	 */
	public  List<HashMap<String, String>> getXszzxmList(User user){
		
		DAO dao=DAO.getInstance();
		
		String type = user.getUserType();
		
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		
		boolean isXy = false;
		
		if ("xy".equalsIgnoreCase(type) && !fdyqx && !bzrqx) {
			// 学院用户
			isXy = true;
		}
		
		StringBuilder sql=new StringBuilder();
		boolean blog=false;
		if(isXy){
			blog=true;
			sql.append(" select xmdm,xmmc from xszz_zzxmb where (shjb='一级审核' and xysh='是') or (shjb='两级审核' and xysh='是') or (shjb='三级审核' and xysh='是') ");
		}
		
		if(fdyqx){
			if(blog){
				sql.append(" union ");
			}
			sql.append(" select xmdm,xmmc from xszz_zzxmb where (shjb='一级审核' and fdysh='是') or (shjb='两级审核' and fdysh='是') or (shjb='三级审核' and fdysh='是') ");
			blog=true;
		}
		
		if(bzrqx){
			if(blog){
				sql.append(" union ");
			}
			sql.append(" select xmdm,xmmc from xszz_zzxmb where (shjb='一级审核' and bzrsh='是') or (shjb='两级审核' and bzrsh='是') or (shjb='三级审核' and bzrsh='是') ");
			blog=true;
		}
		
		if("admin".equalsIgnoreCase(type) || "xx".equalsIgnoreCase(type)){
			if(blog){
				sql.append(" union ");
			}
			sql.append(" select xmdm,xmmc from xszz_zzxmb where (shjb='一级审核' and xxsh='是') or (shjb='两级审核' and xxsh='是') or (shjb='三级审核' and xxsh='是') ");
			blog=true;
		}
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xmdm","xmmc"});
	}

	/**
	 * 文件管理 获取接收人(只获取 未接收的)
	 * @return
	 */
	public List<HashMap<String, String>> getWjglSjr(User user){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select wjh,wjm,jsr,wjsclj,wdrxm from wjsc_scwjxxb where  (jsr like '%,"+user.getUserName()+"' ");
		sb.append(" or jsr like '"+user.getUserName()+",%' or jsr like '%,"+user.getUserName()+",%') ");
		sb.append(" and( wdrxm like '%,"+user.getUserName()+"'  ");
		sb.append(" or wdrxm like '"+user.getUserName()+",%' or wdrxm like '%,"+user.getUserName()+",%') ");
		sb.append(" order by wjffsj desc ");
		return dao.getList(sb.toString(), new String[]{}, new String[]{"wjh","wjm","jsr","wjsclj","wdrxm"});
	}
	
	/**
	 * 首页待办事项 文件信息
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>>getWjmcList(User user,HomePageModel model){
		//获取接收人
		List<HashMap<String, String>> wjglList=getWjglSjr(user);
		List<HashMap<String,String>>wjList=new ArrayList<HashMap<String,String>>();
		int maxSize=model.getMaxSize();
		for(int i=0;i<wjglList.size();i++){
			// -------------修改时间：2012.2.22      --------------
			// -------------修改人：裘力俊           --------------
			// -------------修改内容：显示记录数的控制--------------
			if(maxSize>0){
				HashMap<String,String>wjjsrMap=wjglList.get(i);
				
				//接收人
				String[]jsr=wjjsrMap.get("jsr").split(",");
				//未读文件人
				String[]wdrxm=wjjsrMap.get("wdrxm").split(",");
				boolean blog=false;
				
				for(int j=0;j<jsr.length;j++){
					if(user.getUserName().equalsIgnoreCase(jsr[j])){
						blog=true;
						break;
					}
				}
				
				if(blog){
					// -------------修改时间：2012.2.22--------------
					// -------------修改人：裘力俊--------------
					// -------------修改内容：wdrxm.length长度之前做了限制，需要删除--------------
					// -------------逻辑混乱了。。。。。。。。。。。。。。。。。。。。--------------
					for(int j=0;j<wdrxm.length;j++){
						if(user.getUserName().equalsIgnoreCase(wdrxm[j])){
							HashMap<String,String>wjMap=new HashMap<String,String>();
							wjMap.put("wjh", wjjsrMap.get("wjh"));
							String shortSjmc = wjjsrMap.get("wjm").length() > 24 ? wjjsrMap
									.get("wjm").substring(0, 24)+"..."
									: wjjsrMap.get("wjm");
							wjMap.put("shortWjm", shortSjmc);
							wjMap.put("xmmc", wjjsrMap.get("wjm"));
							wjMap.put("wjsclj", wjjsrMap.get("wjsclj"));
							wjMap.put("dblx", "未接收");
							wjMap.put("mklx", "文件管理");
							wjList.add(wjMap);
							// -------------修改时间：2012.2.22      --------------
							// -------------修改人：裘力俊           --------------
							// -------------修改内容：记录数自减，记录数控制---------
							maxSize--;
							break;
						}
					}
				}
			}else{
				break;
			}
			
		}

		return wjList;
	}
	
	
	/**
	 * 获取 学生资助 学生申请信息
	 * @param user
	 * @return  List<HashMap<String,String>>
	 * author 潇洒的裘
	 */
	public List<HashMap<String,String>>getXszzXssq(User user){
		DAO  xssqjg_dao = DAO.getInstance();
		
		String userName = user.getUserName();
		
		StringBuilder  xssqjg_sb=new StringBuilder();
		
		xssqjg_sb.append(" select * from ( select rownum r, b.xmdm,b.xmmc, ");
		
		//拼接显示的 申请时间
		xssqjg_sb.append("(case when xn is null and xq is null and nd is null then sqsj");
		xssqjg_sb.append(" when xn is null and xq is null and nd is not null then nd||'年度'");
		xssqjg_sb.append(" when nd is null and xq is null and xn is not null then xn||'学年'");
		xssqjg_sb.append(" when nd is null and xq is not null and xn is not null then xn||'学年,");
		xssqjg_sb.append(" '||(select xqmc from xqdzb where xqdm=xq)||'学期' end )sqzqxx,");
		
		//需要三级审核的项目
		xssqjg_sb.append(" (case when shjb='三级审核' and  shzt3='通过' then '三级审核通过' ");
		xssqjg_sb.append("  when shjb='三级审核' and shzt3='不通过' then '三级审核不通过' ");
		xssqjg_sb.append(" 	when shjb='三级审核' and shzt2='通过' then '二级审核通过' ");
		xssqjg_sb.append("  when shjb='三级审核' and shzt2='不通过' then '二级审核通过' ");
		xssqjg_sb.append("  when shjb='三级审核' and shzt1='通过' then '一级审核通过' ");
		xssqjg_sb.append("  when shjb='三级审核' and shzt1='不通过' then '一级审核不通过' ");
		xssqjg_sb.append("  when shjb='三级审核' and shzt1='未审核' then '未审核'     ");
		
		//需要两级审核的项目
		xssqjg_sb.append("  when shjb = '两级审核' and shzt2 = '通过' then '二级审核通过' ");
        xssqjg_sb.append("  when shjb = '两级审核' and shzt2 = '不通过' then '二级审核通过' ");
		xssqjg_sb.append("  when shjb = '两级审核' and shzt1 = '通过' then '一级审核通过' ");
        xssqjg_sb.append("  when shjb = '两级审核' and shzt1 = '不通过' then  '一级审核不通过' ");
		xssqjg_sb.append("  when shjb = '两级审核' and shzt1 = '未审核' then '未审核' ");
		
		//需要一级审核的项目
 		xssqjg_sb.append("  when shjb = '一级审核' and shzt1 = '通过' then  '一级审核通过' ");
		xssqjg_sb.append("  when shjb = '一级审核' and shzt1 = '不通过' then  '一级审核不通过' ");
		xssqjg_sb.append("  when shjb = '一级审核' and shzt1 = '未审核' then  '未审核' ");
		xssqjg_sb.append("  when shjb = '无需审核' then '已申请' end)shjg");
		xssqjg_sb.append(" from xszz_shztb a,xszz_zzxmb b where a.xmdm=b.xmdm and  xh='"+userName+"'  ");
		
		//判断申请周期
		//周期为学年的项目 学年是否为当前学年
		xssqjg_sb.append(" and( (b.sqzq='学年' and exists(select 1 from xtszb d where a.xn=d.dqxn)) ");
		//周期为学期的项目 学年是否为当前学期
		xssqjg_sb.append(" or (b.sqzq='学期' and exists(select 1 from xtszb d where a.xn=d.dqxn and a.xq=d.dqxq)) ");
		//周期为年度的项目 学年是否为当前年度
		xssqjg_sb.append(" or (b.sqzq='年度' and exists(select 1 from xtszb d where a.nd=d.dqnd))");
		//周期为无周期的项目 	申请时间的前4位是否与当前年度相等
		xssqjg_sb.append(" or (b.sqzq='无周期' and exists(select 1 from xtszb b where substr(a.sqsj,0,4)=b.dqnd))" );
		//周期为仅一次的项目 	申请时间的前4位是否与当前年度相等
		xssqjg_sb.append(" or (b.sqzq='仅一次' and exists(select 1 from xtszb b where substr(a.sqsj,0,4)=b.dqnd)))");
		xssqjg_sb.append("order by xmdm) where r<8");
		return xssqjg_dao.getList(xssqjg_sb.toString(),new String[]{},new String[]{"xmmc","shjg","sqzqxx"});
	}
	
	/**
	 * 获取友情链接、联系方式(首页显示项目)
	 * @return List<HashMap<String,String>>
	 * author 潇洒的裘
	 */
	public List<HashMap<String,String>>getSyxsxmList(){
		
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select xmmc,xmnr,xsfs from xg_sysz_xsxmb where  sfxs='是' order by xssx asc ");
		String[] outPut={"xmmc","xmnr","xsfs"};
		return dao.getList(sb.toString(),new String[]{},outPut);
	}
	
	
	public List<HashMap<String,String>>getSwblXssq(User user){
		DAO dao = DAO.getInstance();
		//获取对应模块的配置信息
		StringBuilder sql=new StringBuilder();
		sql.append(getMyProceeding("rcsw","yfpmd"));
		return dao.getList(sql.toString(), new String[]{user.getUserName()}, new String[]{"sqzqxx","xmmc","shjg"});
	}
	
	public HashMap<String, String> getPzxxMap(String mkmc) {
		DAO dao = DAO.getInstance();
		String sql = "select * from xg_xtwh_xssqszb where mkmc =?  ";
		return dao.getMap(sql, new String[] { mkmc }, new String[] { "mkmc",
				"tablename", "tj", "gx", "tjz", "ljfs", "ljb", "ljtj" });
	}
	
	public List<HashMap<String, String>> getTjjsbList(String mkmc) {
		DAO dao = DAO.getInstance();
		String sql = "select * from xg_xtwh_sqtjjsb where mkmc =?  ";
		return dao.getList(sql, new String[] { mkmc }, new String[] { "mkmc",
				"jszd","jszdpz" });
	}
	
    public  String getMyProceeding(String mkmc,String gnmc) {
		
		String sql= XMLReader.getMyProceeding(mkmc, gnmc);

		return sql;
	}
	
	//=================以下made by 伟大的骆================================
	/**
	 * 获取登陆用户所负责学生人数
	 * 
	 * @param user
	 * @return
	 */
	public String[] getXsrs(User user) {

		DAO dao_tj = DAO.getInstance();
		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// 用户所在部门
		String userDep = user.getUserDep();

		// 返回结果
		String[] rs = new String[20];

		// 辅导员权限
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// 班主任权限
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());

		boolean isXy = false;

		if ("xy".equalsIgnoreCase(userType) && !fdyqx && !bzrqx) {
			// 学院用户
			isXy = true;
		}

		String man = "0";
		String woman = "0";
		int all = 0;
		int wzxb = 0;
		String sql = "";
		String fzxsSqlnew="";

		String[] input = new String[] {};
		String xxdm = Base.xxdm;
		String zxsQuery = " and (sfzx = '在校' or sfzx is null)";
		if (Globals.XXDM_ZGDZDX.equals(xxdm)){//中国地大不需要在校生
			zxsQuery = "";
		}else if(Globals.XXDM_XBMZDX.equals(xxdm)){//西北民大只关注有学籍
			zxsQuery = " and (xjztm='有学籍' or xjztm is null)";
		}else if(Globals.XXDM_SXSFDX.equals(xxdm)){//陕西师范大学 不包括” 民族教育学院”，部门代码18
			zxsQuery = " and (xydm <> '18') and (sfzx = '在校' or sfzx is null) and xjztm='有学籍' ";
		}else if("11080".equals(xxdm)){//西安文理学院只关注有学籍
			zxsQuery =  " and xjztm='有学籍' ";
		}
		YhsjfwService yhsjfwService = new YhsjfwService();
		String yhsjfwSql = null;
		
		if (isXy) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "xy","a", "xydm", "bjdm");
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql = "select xb,count(1) num from (select a.xh,a.xb from view_xsbfxx a where "+yhsjfwSql+" "
					+ zxsQuery
					+ ") group by xb order by xb ";
			}else{
				sql = "select xb,count(1) num from view_xsbfxx where xydm = ? "
					+ zxsQuery
					+ "group by xb order by xb ";
				input = new String[] { userDep };
			}

			fzxsSqlnew = "select xb,count(*) num from view_xsxxb where sfzx='不在校' and (sfyby = '否' or sfyby is null) and xydm = '"+userDep+"' group  by xb";
		} else if("sy".equalsIgnoreCase(userType) && !fdyqx && !bzrqx){
			sql = "select xb,count(1) num from (select distinct a.* from view_xsbfxx a left join XG_XTWH_SYBJGLB b on a.bjdm = b.bjdm left join FDYXXB c on b.sydm = c.sydm where c.zgh = ? "+zxsQuery+" ) "
					 + "group by xb order by xb ";
			input = new String[] { userName };

			fzxsSqlnew = "select xb,count(*) num from (select distinct a.* from view_xsbfxx a left join XG_XTWH_SYBJGLB b on a.bjdm = b.bjdm left join FDYXXB c on b.sydm = c.sydm where c.zgh = '"+userName+"') where sfzx='不在校' and (sfyby = '否' or sfyby is null) group  by xb";
		} else if (fdyqx && bzrqx) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "jd","a", "xydm", "bjdm");
			sql = "select xb,count(1) num from (select * from view_xsbfxx a where ";
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql += yhsjfwSql + " or ";
			}
			sql += " exists (select 1 "
					+ "from bzrbbb b where a.zybj = b.bjdm and b.zgh = ?) "
					+ "or exists (select 1 from fdybjb b where a.bjdm = b.bjdm "
					+ "and b.zgh = ?)) where 1=1 "
					+ zxsQuery
					+ "group by xb order by xb";

			input = new String[] { userName, userName };

			fzxsSqlnew = "select xb,count(*) num from (select distinct a.* from view_xsbfxx a where sfzx='不在校' and (sfyby = '否' or sfyby is null) ";
			fzxsSqlnew += " and exists (select 1 "
					+ "from bzrbbb b where a.zybj = b.bjdm and b.zgh = '"+userName+"') "
					+ "or exists (select 1 from fdybjb b where a.bjdm = b.bjdm "
					+ "and b.zgh = '"+userName+"')) where 1=1 ";
			fzxsSqlnew += " group by xb order by xb ";
		} else if (fdyqx) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "fdy","a", "xydm", "bjdm");
			sql = "select xb, count(1) num from view_xsbfxx a where ";
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql += yhsjfwSql + " or ";
			}
			sql += " exists (select 1 from fdybjb b "
					+ " where a.bjdm = b.bjdm and b.zgh = ?) "
					+ zxsQuery
					+ "group by xb order by xb";

			input = new String[] { userName };
			fzxsSqlnew = "select xb,count(*) num from (select distinct a.* from view_xsbfxx a where sfzx='不在校' and (sfyby = '否' or sfyby is null) ";
			fzxsSqlnew += " and exists (select 1 from fdybjb b where a.bjdm = b.bjdm "
					+ "and b.zgh = '"+userName+"')) where 1=1 ";
			fzxsSqlnew += " group by xb order by xb ";
		} else if (bzrqx) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "bzr","a", "xydm", "bjdm");
			sql = "select xb, count(1) num from view_xsbfxx a where ";
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql += yhsjfwSql + " or ";
			}
			sql += " exists (select 1 from bzrbbb b "
					+ "where a.bjdm = b.bjdm and b.zgh = ?) "
					+ zxsQuery
					+ "group by xb order by xb";

			input = new String[] { userName };
			fzxsSqlnew = "select xb,count(*) num from (select distinct a.* from view_xsbfxx a where sfzx='不在校' and (sfyby = '否' or sfyby is null) ";
			fzxsSqlnew += "and exists (select 1 "
					+ "from bzrbbb b where a.zybj = b.bjdm and b.zgh = '"+userName+"')) where 1=1 ";
			fzxsSqlnew += " group by xb order by xb ";
		} else {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "xx","a", "xydm", "bjdm");
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql = "select xb,count(1) num from (select a.xh,a.xb from view_xsbfxx a where "+yhsjfwSql+" "
				+ zxsQuery + ") group by xb order by xb ";
			}else{
				sql = "select xb,count(1) num from view_xsbfxx " + "where 1 = 1 "
				+ zxsQuery + "group by xb order by xb ";
			}
			fzxsSqlnew = "select xb,count(*) num from view_xsxxb where sfzx='不在校' and (sfyby = '否' or sfyby is null) group  by xb";
		}

		List<HashMap<String, String>> list = dao_tj.getList(sql, input,
				new String[] { "xb", "num" });

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if ("男".equalsIgnoreCase(list.get(i).get("xb"))) {
					man = list.get(i).get("num");
				} else if ("女".equalsIgnoreCase(list.get(i).get("xb"))) {
					woman = list.get(i).get("num");
				} else {
					wzxb += Integer.parseInt(list.get(i).get("num"));
				}
				
				all += Integer.parseInt(list.get(i).get("num"));
			}
		}
		// 陕西师范大学
		String where_10718 = "";
		if("10718".equals(xxdm)){
			where_10718 = SearchService.getSearchTjByUser(user, "t","xydm", "bjdm");
		}

		//查询在校生总数
		String zxs = dao_tj.getOneRs("select count(*) cnt from view_xsjbxx t where 1=1 " + where_10718, new String[]{}, "cnt");
		// 非在校生
		String fzxsSql = "select count(*) cnt from view_xsxxb where sfzx='不在校' and (sfyby = '否' or sfyby is null)";
		if("10143".equals(xxdm)){ // “有学籍”的非在校生
			fzxsSql = "select count(*) cnt from view_xsbfxx where sfzx='不在校' and xjztm='有学籍' ";
		}else if("10718".equals(xxdm)){ // 不包括” 民族教育学院”，部门代码18
			fzxsSql = "select count(*) cnt from view_xsbfxx t where t.xjztm='有学籍' and t.sfzx='不在校' and t.xydm <> '18' " + where_10718;
		}else if("11080".equals(xxdm)){
			fzxsSql = " select count(*) cnt from view_xsbfxx where sfzx='不在校' and xjztm='有学籍' ";//【西安文理学院】非在校生
		}
		String fzxs = dao_tj.getOneRs(fzxsSql, new String[]{}, "cnt");
		//休学学生数
		String xsxss = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where t.xjlbdm ='02' " + where_10718, new String[]{}, "cnt");
		//退学学生数
		String txxss = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where t.xjlbdm ='03' " + where_10718, new String[]{}, "cnt");
		//有学籍学生数
		String yxjxssSql = "select count(*) cnt from view_xsxxb where xjlb in (select xjlbdm from dm_xjlb where sfyxj='0')";
		if("10143".equals(xxdm)){ // “有学籍”的学生
			yxjxssSql = "select count(*) cnt from view_xsbfxx where xjztm='有学籍' ";
		}else if("10718".equals(xxdm)){ // 不包括” 民族教育学院”，部门代码18
			yxjxssSql = "select count(*) cnt from view_xsbfxx t where t.xjztm='有学籍' and (t.sfzx = '在校' or t.sfzx is null) and t.xydm <> '18' " + where_10718;
		}else if("11080".equals(xxdm)){
			yxjxssSql = " select count(*) cnt from view_xsbfxx where sfzx='在校' and xjztm='有学籍' ";
		}
		String yxjxss = dao_tj.getOneRs(yxjxssSql, new String[]{}, "cnt");
		
		// 师范生数
		String sfs = "";
		// 非师范生数
		String fsfs = "";
		// 党员
		String dy = "";
		// 少数民族
		String ssmz = "";
		if("10718".equals(xxdm)){ // 不包括” 民族教育学院”，部门代码18
			sfs = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where t.sfsfs='师范生' and t.xjztm='有学籍' and (t.sfzx = '在校' or t.sfzx is null) and t.xydm <> '18' " + where_10718, new String[]{}, "cnt");
			fsfs = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where t.sfsfs='非师范生' and t.xjztm='有学籍' and (t.sfzx = '在校' or t.sfzx is null) and t.xydm <> '18' " + where_10718, new String[]{}, "cnt");
			dy = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where t.zzmm in ('01','02') and t.xjztm='有学籍' and (t.sfzx = '在校' or t.sfzx is null) and t.xydm <> '18' " + where_10718, new String[]{}, "cnt");
			ssmz = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where t.mz not in ('-8','98','97','01') and t.xjztm='有学籍' and (t.sfzx = '在校' or t.sfzx is null) and t.xydm <> '18' " + where_10718, new String[]{}, "cnt");
		}
		
		//无学籍学生数
		String wxjxss = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where (t.xjlb not in (select xjlbdm from dm_xjlb where sfyxj='0') or t.xjlb is null) " + where_10718, new String[]{}, "cnt");

		List<HashMap<String, String>> fzxlist = dao_tj.getList(fzxsSqlnew, new String[]{},
				new String[] { "xb", "num" });
		String fzxMan="0";
		String fzxWoman="0";
		if (fzxlist != null && fzxlist.size() > 0) {
			for (int i = 0; i < fzxlist.size(); i++) {
				if ("男".equalsIgnoreCase(fzxlist.get(i).get("xb"))) {
					fzxMan = fzxlist.get(i).get("num");
				} else if ("女".equalsIgnoreCase(fzxlist.get(i).get("xb"))) {
					fzxWoman = fzxlist.get(i).get("num");
				}
			}
		}
		rs[0] = man;
		rs[1] = woman;
		rs[2] = String.valueOf(all);
		rs[3] = String.valueOf(wzxb);
		rs[4] = zxs;
		rs[5] = fzxs;
		rs[6] = xsxss;
		rs[7] = txxss;
		rs[8] = yxjxss;
		rs[9] = wxjxss;
		rs[10] = sfs;
		rs[11] = fsfs;
		rs[12] = dy;
		rs[13] = ssmz;
		rs[14] = fzxMan;
		rs[15] = fzxWoman;
		return rs;
	}
	
	/**
	 * 获取登陆用户所负责学生人数（校区统计）
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getXsrsXqtj(User user) {

		DAO dao_tj = DAO.getInstance();
		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// 用户所在部门
		String userDep = user.getUserDep();

		// 辅导员权限
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// 班主任权限
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());

		boolean isXy = false;

		if ("xy".equalsIgnoreCase(userType) && !fdyqx && !bzrqx) {
			// 学院用户
			isXy = true;
		}

		String sql = "";

		String[] input = new String[] {};
		String xxdm = Base.xxdm;
		
		// 中国地大不需要在校生
		String zxsQuery = Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm) ? ""
				: " and (sfzx = '在校' or sfzx is null)";
		
		// 西北民大只关注有学籍
		zxsQuery = Globals.XXDM_XBMZDX.equalsIgnoreCase(xxdm) ? " and (xjztm='有学籍' or xjztm is null)" : zxsQuery;
		
		// 增加校区统计
		String xqdm = " yxdm, ";
		
		YhsjfwService yhsjfwService = new YhsjfwService();
		String yhsjfwSql = null;
		
		if (isXy) {			
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "xy","a", "xydm", "bjdm");
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql = "select "+xqdm+" xb,count(1) num from (select "+xqdm+" a.xh,a.xb from view_xsbfxx a where "+yhsjfwSql+" "
					+ zxsQuery
					+ ") group by "+xqdm+" xb order by "+xqdm+" xb ";
			}else{
				sql = "select "+xqdm+" xb,count(1) num from view_xsbfxx where xydm = ? "
					+ zxsQuery
					+ "group by "+xqdm+" xb order by "+xqdm+" xb ";
				input = new String[] { userDep };
			}			
		} else if (fdyqx && bzrqx) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "jd","a", "xydm", "bjdm");
			sql = "select "+xqdm+" xb,count(1) num from (select * from view_xsbfxx a where ";
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql += yhsjfwSql + " or ";
			}
			sql += " exists (select 1 "
					+ "from bzrbbb b where a.bjdm = b.bjdm and b.zgh = ?) "
					+ "or exists (select 1 from fdybjb b where a.bjdm = b.bjdm "
					+ "and b.zgh = ?)) where 1=1 "
					+ zxsQuery
					+ "group by "+xqdm+" xb order by "+xqdm+" xb";

			input = new String[] { userName, userName };

		} else if (fdyqx) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "fdy","a", "xydm", "bjdm");
			sql = "select "+xqdm+" xb, count(1) num from view_xsbfxx a where ";
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql += yhsjfwSql + " or ";
			}
			sql += " exists (select 1 from fdybjb b "
					+ " where a.bjdm = b.bjdm and b.zgh = ?) "
					+ zxsQuery
					+ "group by "+xqdm+" xb order by "+xqdm+" xb";

			input = new String[] { userName };

		} else if (bzrqx) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "bzr","a", "xydm", "bjdm");
			sql = "select "+xqdm+" xb, count(1) num from view_xsbfxx a where ";
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql += yhsjfwSql + " or ";
			}
			sql += " exists (select 1 from bzrbbb b "
					+ "where a.bjdm = b.bjdm and b.zgh = ?) "
					+ zxsQuery
					+ "group by "+xqdm+" xb order by "+xqdm+" xb";

			input = new String[] { userName };
		} else {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "xx","a", "xydm", "bjdm");
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql = "select "+xqdm+" xb,count(1) num from (select "+xqdm+" a.xh,a.xb from view_xsbfxx a where "+yhsjfwSql+" "
				+ zxsQuery + ") group by "+xqdm+" xb order by "+xqdm+" xb ";
			}else{
				sql = "select "+xqdm+" xb,count(1) num from view_xsbfxx " + "where 1 = 1 "
				+ zxsQuery + "group by "+xqdm+" xb order by "+xqdm+" xb ";
			}	
		}

		StringBuilder rsSql = new StringBuilder();
		rsSql.append(" select a.*,(a.mannum + a.womannum + a.wzxbnum) totalnum from ( ");
		rsSql.append(" select (select xqmc from dm_zju_xq c where yxdm = c.dm) zjuxqmc, ");
		rsSql.append(" sum(mannum) mannum, ");
		rsSql.append(" sum(womannum) womannum, ");
		rsSql.append(" sum(wzxbnum) wzxbnum ");
		rsSql.append(" from ( ");
		rsSql.append(" select a.yxdm, ");
		rsSql.append(" case when a.xb='男' then a.num else 0 end mannum, ");
		rsSql.append(" case when a.xb='女' then a.num else 0 end womannum, ");
		rsSql.append(" case when a.xb is null then a.num else 0 end wzxbnum ");
		rsSql.append(" from ( ");
		rsSql.append(sql);
		rsSql.append(" ) a group by a.yxdm,a.xb,a.num ");
		rsSql.append(" ) a group by a.yxdm order by a.yxdm ");
		rsSql.append(" ) a ");
		
		return dao_tj.getListNotOut(rsSql.toString(), input);
	}
	
	/**
	 * @描述：中高职人数
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年2月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getZgzrsList(User user) {

		DAO dao_tj = DAO.getInstance();
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		
		String sql="select * from  VIEW_XSBFXX t where (sfzx = '在校' or sfzx is null) "+searchTjByUser;
		
		StringBuilder rsSql = new StringBuilder();
		rsSql.append(" select a.*,(a.man+a.wom) sum from (select * from(select sum(man) man,sum(wom) wom,pyccmc ");
		rsSql.append(" from(select pyccmc,case when a.xb='男' then a.rs else 0 end man,case when a.xb='女' then a.rs else 0 end wom ");
		rsSql.append(" from( select sum(1) rs,xb,pyccmc from ");
		rsSql.append(" (select * from ("+sql+") t1 left join XG_XSXX_PYCCDMB t2 on t1.pycc=t2.pyccdm) ");
		rsSql.append(" group by xb,pyccmc ");
		rsSql.append(" )a group by a.xb,a.pyccmc,a.rs ");
		rsSql.append(" )a group by pyccmc)a where (a.pyccmc like '%高职%' or a.pyccmc like '%中职%' or a.pyccmc like '%五年一贯制%' or a.pyccmc like '%成人大专%'))a ");
		
		return dao_tj.getListNotOut(rsSql.toString(), new String[] {});
	}

	public List<HashMap<String,String>> getNjXsList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select value,decode(name,'','其他',name)name from(select count(*) value ,nj name from (select * from view_xsxxb where nvl(sfzx,'在校')='在校' ) group by nj order by nj)");
		return DAO.getInstance().getList(sql.toString(), new String[]{},new String[]{"value","name"});
	}

	public List<HashMap<String,String>> getXsZzmmList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select case when length(name) > 8 then substr(name, 0, 8)||'...' else name  end name, value from (select nvl(zzmmmc,'未确定') name ,zzmm,count(*) value from ");
		sql.append("(select * from view_xsxxb where nvl(sfzx,'在校')='在校') group by zzmmmc,zzmm order by to_number(zzmm) asc) ");
		return DAO.getInstance().getList(sql.toString(), new String[]{},new String[]{"value","name"});
	}

	/**
	 * 获取首页调查内容
	 * 
	 * @param user
	 * @return
	 */
	public String[] getSydcnr(User user) {

		DAO dao = DAO.getInstance();
		
		String sql = "select dcid,substr(dcnr,0,19)dcnr,dcnr dcnrxx from xg_xtwh_sydcb where sfqy='是'";
		
		String[] rs = dao.getOneRs(sql, new String[] { }, new String[] { "dcid","dcnr","dcnrxx" });
		
		return rs;
		
	}
	
	/**
	 * 获取首页调查内容选项
	 * 
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getSydcList(User user, String dcid) {

		DAO dao = DAO.getInstance();

		String sql = "select xxid,substr(xxnr,0,18)xxnr,xxnr xxnrxx from xg_xtwh_sydcxxb where dcid=? order by xxid";

		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { dcid }, new String[] { "xxid", "xxnr","xxnrxx" });

		return list;

	}
	
	/**
	 * 获取 评奖评优待办事项(通用)
	 * @param user 
	 * @return  List<HashMap<String,String>>
	 * @author qlj
	 */
	public List<HashMap<String,String>>getPjpyDbsx(User user){
		
		DAO dao = DAO.getInstance();
		//用户名
		String yhm = user.getUserName();

		StringBuilder sql=new StringBuilder();
		
//		 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// 用户名
		String userName = user.getUserName();

		//sql.append("( ");
//		sql.append(" select '评奖评优' mklx, '未审核' dblx,xmdm,xmmc,");
//		sql.append(" to_char(WM_CONCAT(gwmc||'('||xsh||')')) shxx,to_char(WM_CONCAT(xtgwid)) gwxx,to_char(WM_CONCAT(gwjb)) jbxx from( ");
//		sql.append(" select xmdm,xmmc,pjxn,pjxq,pjnd,xsh,xtgwid,gwmc,xh gwjb from ");
//		sql.append(" ( select b.xmdm,b.xmmc, pjxn, pjxq, pjnd,count(1)xsh,xtgwid,gwmc,lcid ");
//		sql.append(" from (select * from xg_xtwh_spgwyh where spyh = '"+userName+"') a, ");
//		sql.append(" (select a.*, b.mc gwmc,c.xmmc,c.lcid ");
//		sql.append(" from xg_pjpy_pjxmshb a ");
//		sql.append(" left join xg_xtwh_spgw b on a.xtgwid = b.id ");
//		sql.append(" left join xg_pjpy_pjxmwhb c on a.xmdm=c.xmdm ");
//		sql.append(" where shzt = '未审核') b ");
//		sql.append(" where a.spgw = b.xtgwid  ");
//		sql.append(" group by xmdm,xmmc, pjxn, pjxq, pjnd,xtgwid,gwmc,lcid)a ,");
//		sql.append(" xg_xtwh_spbz b where a.xtgwid= b.spgw and a.lcid=b.splc) ");
//		sql.append(" group by xmdm,xmmc, pjxn, pjxq, pjnd ");
		
		
		sql.append(" select '评奖评优' mklx, '未审核' dblx,lcid,pjxn, pjxq, pjnd, xmdm, xmmc, '尚有['||count(1)||']人需审核' shxx");
		sql.append(" from (select a.*,b.spgw, b.xmmc");
		sql.append(" from (select xmdm, pjxn, pjxq, pjnd, xh, min(shjb) shjb,lcid");
		sql.append(" from xg_view_pjpy_xmsh a");
		sql.append(" where (shzt = '未审核' or shzt = '需重审')");
		sql.append(" and (exists (select 1");
		sql.append(" from xg_view_pjpy_xmsh b");
		sql.append(" where shzt = '通过'");
		sql.append(" and to_number(b.shjb) = a.shjb - 1");
		sql.append(" and b.xmdm = a.xmdm");
		sql.append(" and b.pjxn = a.pjxn");
		sql.append(" and b.pjxq = a.pjxq");
		sql.append(" and b.pjnd = a.pjnd");
		sql.append(" and b.xh = a.xh) or a.shjb = 1)");
		sql.append(" group by xmdm, pjxn, pjxq, pjnd, xh,lcid) a");
		sql.append(" left join xg_view_pjpy_xmsh b on a.xmdm = b.xmdm");
		sql.append(" and a.pjxn = b.pjxn");
		sql.append(" and a.pjxq = b.pjxq");
		sql.append(" and a.pjnd = b.pjnd");
		sql.append(" and a.xh = b.xh");
		sql.append(" and a.shjb = b.shjb");
		sql.append(" where exists (select 1");
		sql.append(" from xg_xtwh_spgwyh c");
		sql.append(" where b.spgw = c.spgw");
		sql.append(" and c.spyh = '"+userName+"'))");
		sql.append(" group by pjxn, pjxq, pjnd, xmdm, xmmc,lcid");
		
		System.out.println(sql);
		return  dao.getList(sql.toString(),new String[]{},new String[]{"mklx","xmdm","xmmc","dblx","shxx"});
	}
	
	/**
	 * 获取 评奖评优学生申请事项(通用)
	 * @param user 
	 * @return  List<HashMap<String,String>>
	 * @author qlj
	 */
	public List<HashMap<String,String>>getPjpyXssq(User user){
		DAO dao=DAO.getInstance();
		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// 用户名
		String userName = user.getUserName();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,shzt shjg from ( ");
		sql.append(" select sqzqxx,a.xmdm,a.xmmc,a.lcid,max(shjb) shjb, ");
		sql.append(" to_char(WM_CONCAT(a.gwmc || '(' || a.shzt || ')')) shzt  ");
		sql.append(" from (select a.xnxx||a.xqxx||a.ndxx sqzqxx,a.* from( ");
		sql.append(" select ");
		sql.append(" case when a.pjxn<>'无' then a.pjxn||'学年' else '' end xnxx, ");
		sql.append(" case when a.pjxq<>'无' then a.pjxq||'学期' else '' end xqxx, ");
		sql.append(" case when a.pjnd<>'无' then a.pjnd||'年度' else '' end ndxx, ");
		sql.append(" a.xmdm, a.xmmc, a.shjb, a.mc gwmc, a.shzt,a.lcid from xg_view_pjpy_xmsh a  ");
		sql.append(" where xh = '"+userName+"' and (a.pjxn = '"+pjxn+"' or  ");
		sql.append("(a.pjxn = '"+pjxn+"' and a.pjxq = '"+pjxq+"') or a.pjnd = '"+pjnd+"') ");
		sql.append("order by xmdm, to_number(shjb)) a ");
		sql.append(") a group by a.sqzqxx,a.xmdm,a.xmmc,a.lcid) a ");


		return  dao.getList(sql.toString(),new String[]{},new String[]{"xmmc","shjg","sqzqxx"});
	}
	
	public List<HashMap<String,String>>getShlcJb(User user,String xmdm){
		ArrayList<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();
		PjpyCommDAO commDAO=new PjpyCommDAO();
		PjpyCommService service = new PjpyCommService();

		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		String pkValue = pjxn + pjxq + pjnd + xmdm;

		PjpyXmszModel xmszModel = service.getXmszForXmdm(pkValue);

		// 流程ID
		String lcid = xmszModel.getLcid();
		String userName=user.getUserName();
		if (!Base.isNull(lcid)) {
			// 流程信息
			List<HashMap<String, String>> lcInfoList = commDAO.getLcInfo(lcid);
			// 岗位信息
			List<HashMap<String, String>> gwInfoList = commDAO.getGwInfo(userName,lcid);

			if (lcInfoList != null && lcInfoList.size() > 0) {
				for (int j = 0; j < lcInfoList.size(); j++) {
					String spgw = lcInfoList.get(j).get("spgw");
					if (gwInfoList != null && gwInfoList.size() > 0) {
						for (int k = 0; k < gwInfoList.size(); k++) {
							String yhgw = gwInfoList.get(k).get("spgw");
							if (spgw.equalsIgnoreCase(yhgw)) {
								rsList.add(gwInfoList.get(k));
							}
						}
					}

				}
			}
		}

		return rsList;
	}
	
	/**
	 * 获取学生评奖申请信息
	 * author 潇洒的裘
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getXswjxxInfo(User user) throws Exception {
		DAO dao=DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String xh=user.getUserName();

		// ===========================评奖 申请结果====================================
		sql.append(" select '违纪处分' mklx,  ");
		sql.append(" '违纪信息' xmmc, ");
		sql.append(" xh||xn||nd||sbsj pkValue, ");
		sql.append(" to_char(to_date(wjsj, 'yyyymmdd'), 'yyyy') || '年' ||  ");
		sql.append(" to_char(to_date(wjsj, 'yyyymmdd'), 'mm') ||'月' ||  ");
		sql.append(" to_char(to_date(wjsj, 'yyyymmdd'), 'dd') || '日,' sqzqxx,  ");
		sql.append(" '由于'||cfyymc||'受到'||cflbmc||'处分' shjg ");
		sql.append("  from view_wjcf a   where xh = '"+xh+"'  ");
		//sql.append(" and xn = (select dqxn from xtszb where rownum = 1) ");
		sql.append(" and sfqs = '否' ");
		// ===========================评奖 申请结果end====================================
		
		return dao.getList(sql.toString(), new String[] {},
				new String[] {  "mklx","xmmc", "sqzqxx", "shjg","pkValue" });
	}
}
