package xgxt.wjcf;


import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import common.Globals;

import xgxt.DAO.DAO;

public class WjcfDataAccessAction {
	//����DAO
	//public static DAO dao = DAO.getInstance();

	//��ȡ���ݼ� -- ���ٽ���
	public static ArrayList<String[]> getRsList(String pk, String [] setpara, String [] colList, String realName){
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		String sql = "";
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			sql = "select " + pk + " ����,a.* from " + realName +  " a" + 
			//" where nj like ? and cfsj > ? and xydm like ? and zydm like ? and bjdm like ? and xh like ? and nd like ? ";
			" where nj like ? and xydm like ? and zydm like ? and bjdm like ? and xh like ? ";
		} else {
			sql = "select " + pk + " ����,a.* from " + realName +  " a" + 
			//" where nj like ? and cfsj > ? and xydm like ? and zydm like ? and bjdm like ? and xh like ? and nd like ? ";
			" where nj like ? and xydm like ? and zydm like ? and bjdm like ? and xh like ? and nd like ? ";
		}
		 
		return dao.rsToVator(sql, setpara, colList);
	}

	//��ȡtable��ͷ -- ���ٽ���
	public static List getTheadList(String [] colList, String realName){
		DAO dao = DAO.getInstance();
		String [] colListCN = dao.getColumnNameCN(colList,realName);
		return dao.arrayToList(colList, colListCN);
	}

	//��ȡ����ѧ��������Ϣ
	public static HashMap<String, String> getStuMap(String pk, String [] setpara, String []colList, String realName){
		DAO dao = DAO.getInstance();
		String sql = "select xh,nd,xm,xn,xb,xq,nj,cflbmc cflb,xymc,cfyymc cfyy,zymc,cfsj,bjmc,cfwh,xsbx1,xsbx2,xsbx3,xsbx4,xyyj from " + realName + " where " + pk + "=?";
		return dao.getMap(sql, setpara, colList);
	}

	//��ȡ���ݼ� -- ������Ϣ
	public static ArrayList<String[]> getKqRs(String pk, String [] colList, String tableName, StringBuffer querry){
		DAO dao = DAO.getInstance();
		String sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName + " a where 1=1 " + querry.toString();
		return dao.rsToVator(sql, new String[]{}, colList);
	}

}
