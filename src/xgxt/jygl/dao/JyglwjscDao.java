package xgxt.jygl.dao;

import java.util.List;

import xgxt.DAO.DAO;

public class JyglwjscDao {
	String sql = null;
	DAO dao = DAO.getInstance();
	
	//�ļ��ϴ���Ĳ��� �����ǣ��ϴ��� �ϴ��ļ��� �ϴ�·�� �ļ���С
	public boolean getMFile(String str1, String str2, String str3, String str4) throws Exception  {
    	sql = "insert into scwjb(wjscperson,wjname,wjpath,wjdx) values(?,?,?,?)";
    	return dao.runUpdate(sql, new String[] {str1, str2, str3, str4});
        }
	
	public List getMyFile() {
		sql = "select * from scwjb where 1=1";
		return dao.getList(sql, new String[]{},
				
			new String[] { "wjscperson", "wjname", "wjpath", "wjdx" });
	    }
	
	public boolean getMFile2(String str1, String str2, String str3, String str4) throws Exception  {
    	sql = "insert into scwjb2(wjscperson,wjname,wjpath,wjdx) values(?,?,?,?)";
    	return dao.runUpdate(sql, new String[] {str1, str2, str3, str4});
        }
	
	public List getMyFile2() {
		sql = "select * from scwjb2 where 1=1";
		return dao.getList(sql, new String[]{},
			new String[] { "wjscperson", "wjname", "wjpath", "wjdx" });
	    }
	
	
	
}
