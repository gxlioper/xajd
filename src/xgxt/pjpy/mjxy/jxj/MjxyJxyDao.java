package xgxt.pjpy.mjxy.jxj;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class MjxyJxyDao {
	//��ѡ���������б�
	public List<HashMap<String,String>>jxjList(){
		DAO dao=DAO.getInstance();
		return dao.getList("select jxjdm,jxjmc from jxjdmb", new String[]{}, new String[]{"jxjdm", "jxjmc"});
	}
	
	/**
	 * �������Ŀ��
	 */
	public List<HashMap<String,String>>getBjgms(String xn,String xh){
		DAO dao=DAO.getInstance();
		String sql="select xh,count(*)bjgms from cjb where xn=? and xh=? and bkcj is not null group by xh";
		return dao.getList(sql, new String[]{xn,xh}, new String[]{"bjgms"});
	}
	
	/**
	 * �ۿ�����
	 */
	public List<HashMap<String,String>>getZkms(String xn,String xh){
		DAO dao=DAO.getInstance();
		String sql="select xh,count(*)zkms from cjb where xn=? and xh=? group by xh";
		return dao.getList(sql, new String[]{xn,xh}, new String[]{"zkms"});
	}
	
	/**
	 * ��ѧ������רҵ������
	 */
	public List<HashMap<String,String>>getZyrs(String xh){
		DAO dao=DAO.getInstance();
		String sql="select count(b.xh)zyrs from xsxxb a  inner join  xsxxb b on a.zydm=b.zydm where a.xh=? ";
		return dao.getList(sql, new String[]{xh}, new String[]{"zyrs"});
	}
}
