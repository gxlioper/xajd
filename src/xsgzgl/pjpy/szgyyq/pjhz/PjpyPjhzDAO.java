package xsgzgl.pjpy.szgyyq.pjhz;

import java.sql.SQLException;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_��������_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyPjhzDAO extends DAO { 
	
	/**
	 * ��ȡ����ɼ���Ŀ
	 * @param form
	 * @return
	 */
	public String[] getJwcjkm(PjpyPjhzForm form){
		String sql="select distinct a.kcmc from cjb a,view_xsxxb b where a.xh=b.xh and a.xn=? and a.xq=? and b.bjdm=? and a.kcxz in ("+form.getKcxz()+")";
		try {
			return this.getArray(sql, new String[]{form.getXn(),form.getXq(),form.getBjdm()}, "kcmc");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new String[]{};
	}
	
	/**
	 * ��ȡ���������б�
	 * @param sql
	 * @param inputValue
	 * @param outputValue
	 * @return
	 */
	public List<String[]> getPjhzList(String sql,String[] inputValue,String[] outputValue){
		return this.rsToVator(sql, inputValue, outputValue);
	}
	
	/**
	 * ��ȡ�༶����
	 * @param bjdm
	 * @return
	 */
	public String getBJmc(String bjdm){
		String sql="select bjmc from view_njxyzybj_all where bjdm=?";
		return this.getOneRs(sql, new String[]{bjdm}, "bjmc");
	}
 
}
