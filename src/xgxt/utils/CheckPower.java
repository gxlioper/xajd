package xgxt.utils;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import xgxt.DAO.*;
public class CheckPower {
	/**
	 * @param userName
	 * @param dyym
	 * @return ���ش��û����û�Ȩ�ޱ�Ķ�Ӧҳ��Ķ�дȨ
	 */
	public static boolean checkUsrPower(String userName, String dyym){
		DAO dao = DAO.getInstance();
		String sql = "select a.dxq from yhqxb a,gnmkdmb b where a.yhm=? and b.dyym=? and a.gnmkdm=b.gnmkdm";
		String dxq = dao.getOneRs(sql, new String[]{userName,dyym}, "dxq");
		return (dxq.equalsIgnoreCase("1")? true : false);
	}

	public static boolean checkUsrPageAccessPower(String userOnLine,String usrName,String dyym) throws SQLException{
		DAO	dao = DAO.getInstance();
		String dxq = "";
		String sql = "";
		if("student".equalsIgnoreCase(userOnLine)){
			sql = "select a.dxq from yhzqxb a,gnmkdmb b where a.gnmkdm=b.gnmkdm and a.zdm='6727' and b.dyym=?";
			dxq = dao.getOneRs(sql, new String[]{dyym}, "dxq");
		} else {
			sql = "select a.dxq from yhqxb a,gnmkdmb b where a.gnmkdm=b.gnmkdm and a.yhm=? and b.dyym=?";
			dxq = dao.getOneRs(sql, new String[]{usrName,dyym}, "dxq");
		}	
		return true;
	}
	
	/**
	 * �ж��Ƿ�������ʱ����
	 * 
	 * @return boolean
	 * @author luo
	 */
	public static boolean checkTime() throws SQLException {
		DAO	dao = DAO.getInstance();
		// �ж��Ƿ������õ���������ʱ�䷶Χ�� tag Ϊempty��������ʱ����
		String sql = "select * from SHGC_XSBXSJ where kssqsj<to_char(sysdate,'yyyymmddhh24miss') and jssqsj>to_char(sysdate,'yyyymmddhh24miss')";

		String tag = dao.returntag(sql, new String[] {});
		if (tag != null && !tag.equalsIgnoreCase("empty")) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 
	 * @param session
	 * @return ������Ӧ���û����ڲ��Ŵ���(������ΪѧУ��ѧ�������򷵻ؿգ����򷵻�ѧԺ����)
	 */
	public static String getDepartmentId(HttpSession session){
		String userType = session.getAttribute("userType").toString();
		if("admin".equalsIgnoreCase(userType) || "xx".equalsIgnoreCase(userType)){
			return "";
		} else {
			return session.getAttribute("userDep").toString();
		}
	}
}
