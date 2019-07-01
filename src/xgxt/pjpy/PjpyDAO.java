package xgxt.pjpy;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;

import common.Globals;

/**
 * @author Administrator
 * @category ��Ҫ���ڴ洢sql���ִ�к���
 */
public class PjpyDAO {



	/**
	 * @param xh
	 * @param xn
	 * @return ������Ӧѧ����ָ��ѧ���ƽ��ѧ�ּ��㣬���ڼ����ж�
	 */
	public static String getStuJd(String xh,String xn){
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select pjjd from pjjdb where xh=? and xn=?", new String[]{xh,xn}, "pjjd");
	}

	/**
	 * @param jxjdm
	 * @return  ������Ӧ�Ľ�ѧ�𼨵����Ʊ�׼
	 */
	public static String getJxjjd(String jxjdm){
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select SZJDBZ from jxjdmb where jxjdm=?", new String[]{jxjdm}, "SZJDBZ");
	}

	/**
	 * @return ���ع���ʦ��˼��������ʱ������
	 */
	public static String[] getSjxz(){//��Ҫ���б���ѧ��ʦ��˼������ʱ������
		DAO dao = DAO.getInstance();
		String temsql = "select jxjsqxn from xtszb";
		String jxjsqxn = dao.getOneRs(temsql, new String[]{}, "jxjsqxn");
		String sql = "select xn,xspdkssj,xspdjssj,jspdkssj,jspdjssj from pdsjxzb where xn=?";
		String[] outArr = {"xn","xspdkssj","xspdjssj","jspdkssj","jspdjssj"};
		return dao.getOneRs(sql, new String[]{jxjsqxn}, outArr);
	}

	/**
	 * @param sjxzArr ������ǻ�������ʱ������
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

	//���ѧ������Ӧ��ͬ��ͬѧ�б�
	public List<HashMap<String, String>> getClassMate(String userName){
		DAO dao = DAO.getInstance();
		String sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,b.cj xspdcj from view_xsjbxx a left join xsspcjb b on a.xh=b.xh where a.bjdm=(select bjdm from view_xsjbxx where xh=?) order by xh";
		return dao.getList(sql, new String[]{userName}, new String[]{"xh","xm","xymc","zymc","bjmc","xspdcj"});		
	}
	//��øø���Ա����Ӧ�İ༶�б�
	public List<HashMap<String, String>> getClasses(String userName){
		DAO dao = DAO.getInstance();
		String sql = "select bjdm,bjmc from view_njxyzybj a where exists(select 1 from fdybjb b where b.zgh=? and a.bjdm=b.bjdm) order by bjdm";
		return dao.getList(sql, new String[]{ userName }, new String[]{"bjdm","bjmc"});
	}

	//��ø���Ա�����������Ӧ�İ༶ѧ��
	public List<HashMap<String, String>> getClassByTeacher(String bjdm){
		DAO dao = DAO.getInstance();
		String sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,b.cj jspdcj from view_xsjbxx a left join jsspcjb b  on a.xh=b.xh where bjdm=? order by a.xh";
		return dao.getList(sql, new String[]{bjdm}, new String[]{"xh","xm","xymc","zymc","bjmc","jspdcj"});
	}

	//��ȡ�������ֶβ�ѯ���
	public String getJxjColumns(String tableName){
		String xxdm = StandardOperation.getXxdm();
		String sql = "select * from " + tableName;

		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)&& tableName.equalsIgnoreCase("view_xsjxjb")){
			//�人����ѧ��������
			sql = "select xn,nd,xh,xm,xb,nj,xymc,zymc,bjmc,jxjmc,jlje,jxjlb,lwmc,qkmc,fbsj,sfdyzz,gkfs,gzashjqk,sqly,fdysh, xysh,xxsh from " + tableName; 
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX) && tableName.equalsIgnoreCase("view_xsrychb")){
			//�人����ѧ�����ƺ�
			sql = "select xn,nd,xh,xm,xb,nj,xymc,zymc,bjmc,rychmc,sfdlsq,dlsqly,sqly,fdysh,xysh,xxsh from " + tableName;
		}
		return sql;
	}
	
}
