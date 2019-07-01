package xgxt.comm.homepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.xszz.XszzDAO;

public class DzdxHomePageDAO {

	/**
	 * 中国地质大学 获取资助或者评奖项目的信息
	 * 
	 * @param user,mklx(模块类型)
	 * @return List<HashMap<String,String>> author 潇洒的裘
	 */
	public List<HashMap<String, String>> getZzpjDbsx(User user, HomePageModel model) {

		DAO dao = DAO.getInstance();
		
		HomePageDAO hpDAO=new  HomePageDAO();
		// 用户名
		String yhm = user.getUserName();
		// 用户类型
		String type = user.getUserType();
		// 用户部门
		String bmdm = user.getUserDep();
		//模块类型
		String mklx = model.getMklx();
		//显示数
		int maxSize = model.getMaxSize();
		// 辅导员权限
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// 班主任权限
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());

		boolean isXy = false;

		if ("xy".equalsIgnoreCase(type) && !fdyqx && !bzrqx) {
			// 学院用户
			isXy = true;
		}
		
		StringBuilder xmlb=getXmlbQuery(mklx);
		StringBuilder sb = new StringBuilder();
		
		String gnmkmc="";
		if("zz".equalsIgnoreCase(mklx)){
			gnmkmc="学生资助";
		}else if("pj".equalsIgnoreCase(mklx)){
			gnmkmc="评奖评优";
		}

		// 资助学院用户需要审项目
		// 项目代码、学年、学期、年度、申请时间前4位
		boolean blog = false;
		
		
		if (isXy) {
			blog = true;
			// 一级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and xysh='是' "+xmlb.toString()+") ");
			sb.append(" and xysh='未审核'  and c.xydm='" + bmdm + "' ");
			// 二级审核
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='两级审核' and xysh='是' "+xmlb.toString()+") ");
			sb.append(" and xysh='未审核'  and a.xh=c.xh  and (fdysh='通过' or bzrsh='通过') ");
			sb.append(" and c.xydm='" + bmdm + "' ");
			// 二级审核
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,sqsj from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='三级审核' and xysh='是' "+xmlb.toString()+") ");
			sb.append(" and xysh='未审核'  and a.xh=c.xh  and (fdysh='通过' or bzrsh='通过') ");
			sb.append(" and c.xydm='" + bmdm + "' ");

		}

		// 辅导员
		if (fdyqx) {
			if (blog) {
				sb.append("union all");
			}
			// 一级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and fdysh='是' "+xmlb.toString()+") ");
			sb.append(" and fdysh='未审核' and a.xh=c.xh ");
			sb.append(" and exists(select 1 from fdybjb b where c.bjdm=b.bjdm and b.zgh='"
							+ yhm + "')");
			blog = true;
		}

		// 班主任
		if (bzrqx) {
			if (blog) {
				sb.append("union all");
			}
			// 一级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c  where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and bzrsh='是' "+xmlb.toString()+") ");
			sb.append(" and bzrsh='未审核' and a.xh=c.xh ");
			sb.append("  and exists(select 1 from  bzrbbb b where c.bjdm=b.bjdm and b.zgh='"
							+ yhm + "') ");
			blog = true;
		}

		// 管理员、学校
		if ("admin".equalsIgnoreCase(type) || "xx".equalsIgnoreCase(type)) {
			if (blog) {
				sb.append("union all");
			}
			// 一级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb where xmdm in ");
			sb.append(" ( select xmdm from xszz_zzxmb where shjb='一级审核' and xxsh='是'  "+xmlb.toString()+") ");
			sb.append(" and xxsh='未审核' ");
			sb.append(" union all");
			// 二级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='两级审核' and (bzrsh='是' and b.bzrsh='通过' or xysh='是' and b.xysh='通过' or  fdysh='是' and b.fdysh='通过' ) "+xmlb.toString()+" )");
			sb.append("  and xxsh='未审核' ");
			sb.append("union all");
			// 三级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='三级审核' and xysh='是' and b.xysh='通过'  "+xmlb.toString()+" ) ");
			sb.append("  and xxsh='未审核' ");

		}
		
		
		String sql = " select * from(select * from (select rownum r,'"+gnmkmc+"' mklx,'未审核' dblx, a.xmdm, a.xsh,a.xn,a.xq,a.nd,a.sqn,b.xmmc,b.sqzq from( select xmdm,count(1) xsh, xn,xq,nd,sqn from ("
				+ sb.toString()
				+ ") group by xmdm,xn,xq,nd,sqn)a,xszz_zzxmb b where a.xmdm=b.xmdm)a where r<10 ";
		sql += " and( (a.sqzq='学年' and exists(select 1 from xtszb b where a.xn=b.dqxn)) ";
		sql += " or (a.sqzq='学期' and exists(select 1 from xtszb b where a.xn=b.dqxn and a.xq=b.dqxq)) ";
		sql += " or (a.sqzq='年度' and exists(select 1 from xtszb b where a.nd=b.dqnd)) or (a.sqzq='无周期' and exists(select 1 from xtszb b where a.sqn=b.dqnd))";
		sql += " or (a.sqzq='仅一次' and exists(select 1 from xtszb b where a.sqn=b.dqnd)))";

		// 过滤一部分项目
		for (int i = 0; i < hpDAO.getXszzxmList(user).size(); i++) {
			HashMap<String, String> hashMap = hpDAO.getXszzxmList(user).get(i);
			sql += " or xmdm= '" + hashMap.get("xmdm") + "' ";
		}
		sql+=" ) where rownum<= "+maxSize;
		
		return dao.getList(sql, new String[] {}, new String[] { "mklx", "xmdm",
				"xmmc", "xsh", "dblx" });
	}
	
	/**
	 * 根据模块类型获取项目类别列表
	 * @param mklx
	 * @return  List<HashMap<String, String>>
	 * time 2011.3.30
	 * author qlj
	 */
	public List<HashMap<String, String>> getXmlbList(String mklx){
		
		XszzDAO xszzDAO = new XszzDAO();
		
		List<HashMap<String, String>>xmlbList=new ArrayList<HashMap<String, String>>();
		// 获取资助或评奖模块的项目类别
		if ("zz".equalsIgnoreCase(mklx)) {
			// 获取 学生资助项目类别
			xmlbList=xszzDAO.getSelectList("xmlb_zz");
		} else if ("pj".equalsIgnoreCase(mklx)) {
			// 获取 评奖评优项目类别
			xmlbList=xszzDAO.getSelectList("xmlb_pj");
		}
		
		return xmlbList;
	}
	
	public StringBuilder getXmlbQuery(String mklx){
		
		StringBuilder query=new StringBuilder();
		
		List<HashMap<String, String>>xmlbList=getXmlbList(mklx);
		
		//判断是否有项目列表,没有返回空
		if(xmlbList.size()>0){
			query.append(" and ( ");
			for(int i=0;i<xmlbList.size();i++){
				
				HashMap<String,String>xmlbMap=xmlbList.get(i);
				
				if(i==0){
					query.append(" xmlb='"+xmlbMap.get("en")+"' ");
				}else{
					query.append(" or  xmlb='"+xmlbMap.get("en")+"' ");
				}
			}
			query.append(" ) "); 
		}
		
		return query;
	}
}
