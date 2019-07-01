package xgxt.pjpy;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;

import common.Globals;

/**
 * @author Administrator
 * @category 主要用于存储sql语句执行函数
 */
public class PjpyDAO {



	/**
	 * @param xh
	 * @param xn
	 * @return 返回相应学生的指定学年的平均学分绩点，用于绩点判断
	 */
	public static String getStuJd(String xh,String xn){
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select pjjd from pjjdb where xh=? and xn=?", new String[]{xh,xn}, "pjjd");
	}

	/**
	 * @param jxjdm
	 * @return  返回相应的奖学金绩点限制标准
	 */
	public static String getJxjjd(String jxjdm){
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select SZJDBZ from jxjdmb where jxjdm=?", new String[]{jxjdm}, "SZJDBZ");
	}

	/**
	 * @return 返回关于师生思评评定的时间限制
	 */
	public static String[] getSjxz(){//主要是中北大学的师生思评评定时间限制
		DAO dao = DAO.getInstance();
		String temsql = "select jxjsqxn from xtszb";
		String jxjsqxn = dao.getOneRs(temsql, new String[]{}, "jxjsqxn");
		String sql = "select xn,xspdkssj,xspdjssj,jspdkssj,jspdjssj from pdsjxzb where xn=?";
		String[] outArr = {"xn","xspdkssj","xspdjssj","jspdkssj","jspdjssj"};
		return dao.getOneRs(sql, new String[]{jxjsqxn}, outArr);
	}

	/**
	 * @param sjxzArr 传入的是获得重设的时间限制
	 * @return
	 */
	public static boolean setSjxz(String[] sjxzArr){
		DAO dao = DAO.getInstance();
		String sql = "update pdsjxzb set xn=?,xspdkssj=?,xspdjssj=?,jspdkssj=?,jspdjssj=?";
		try{
			return dao.runUpdate(sql, sjxzArr);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}

	//获得学生所对应的同班同学列表
	public List<HashMap<String, String>> getClassMate(String userName){
		DAO dao = DAO.getInstance();
		String sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,b.cj xspdcj from view_xsjbxx a left join xsspcjb b on a.xh=b.xh where a.bjdm=(select bjdm from view_xsjbxx where xh=?) order by xh";
		return dao.getList(sql, new String[]{userName}, new String[]{"xh","xm","xymc","zymc","bjmc","xspdcj"});		
	}
	//获得该辅导员所对应的班级列表
	public List<HashMap<String, String>> getClasses(String userName){
		DAO dao = DAO.getInstance();
		String sql = "select bjdm,bjmc from view_njxyzybj a where exists(select 1 from fdybjb b where b.zgh=? and a.bjdm=b.bjdm) order by bjdm";
		return dao.getList(sql, new String[]{ userName }, new String[]{"bjdm","bjmc"});
	}

	//获得辅导员或班主任所对应的班级学生
	public List<HashMap<String, String>> getClassByTeacher(String bjdm){
		DAO dao = DAO.getInstance();
		String sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,b.cj jspdcj from view_xsjbxx a left join jsspcjb b  on a.xh=b.xh where bjdm=? order by a.xh";
		return dao.getList(sql, new String[]{bjdm}, new String[]{"xh","xm","xymc","zymc","bjmc","jspdcj"});
	}

	//获取导出的字段查询语句
	public String getJxjColumns(String tableName){
		String xxdm = StandardOperation.getXxdm();
		String sql = "select * from " + tableName;

		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)&& tableName.equalsIgnoreCase("view_xsjxjb")){
			//武汉理工大学评奖评优
			sql = "select xn,nd,xh,xm,xb,nj,xymc,zymc,bjmc,jxjmc,jlje,jxjlb,lwmc,qkmc,fbsj,sfdyzz,gkfs,gzashjqk,sqly,fdysh, xysh,xxsh from " + tableName; 
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX) && tableName.equalsIgnoreCase("view_xsrychb")){
			//武汉理工大学荣誉称号
			sql = "select xn,nd,xh,xm,xb,nj,xymc,zymc,bjmc,rychmc,sfdlsq,dlsqly,sqly,fdysh,xysh,xxsh from " + tableName;
		}
		return sql;
	}
	
}
