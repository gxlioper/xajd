package xgxt.studentInfo.dao;

import java.util.List;

import xgxt.DAO.DAO;

public class XsxxXxtjglDAO extends DAO{
	
	/**
	 * ��ѯѧԺѧ����Ϣͳ����Ϣ
	 * @param output ������ֶ� 
	 * @param topLen ȡ����������
	 * */
	public List<String[]> selectXyXsxxtj(String[] output, int topLen){
		StringBuilder sql = new StringBuilder("select xymc,xydm, ");
		sql.append("(select count(*) from view_xsjbxx b where a.xydm=b.xydm)zrs,");
        sql.append("(select count(*) from view_xsjbxx b where a.xydm=b.xydm and b.xb='��')boyrs,");
        sql.append("(select count(*) from view_xsjbxx b where a.xydm=b.xydm and b.xb='Ů')girlrs,");
        sql.append("(select count(*) from xsxxb b where a.xydm=b.xydm and b.sfzx='��')zxrs,");
        sql.append("(select count(*) from xsxxb b where a.xydm=b.xydm and b.sfzx='��' and (b.xb='��' or b.xb='1'))zxboyrs,");
        sql.append("(select count(*) from xsxxb b where a.xydm=b.xydm and b.sfzx='��' and (b.xb='Ů' or b.xb='2'))zxgirlrs ");
        sql.append("from view_njxyzybj	a group by xymc,xydm");
		
		return rsToVator(sql.toString(), new String[]{}, output);
	}
	
}
