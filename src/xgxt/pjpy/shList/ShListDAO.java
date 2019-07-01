
package xgxt.pjpy.shList;

import java.util.ArrayList;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

public class ShListDAO {

	/**
	 * @param tableName Ҫ��ѯ�ı���
	 * @param conditions ��ѯ�����������������а����ˡ�?��ռλ��
	 * @param values ��Ӧcondition�е�ռλ��������ֵ
	 * @param cols  Ҫ���������
	 * @param userType �û�����
	 * @param passType ���״̬
	 * @return
	 */
	public  ArrayList<String[]> searchRS(DAO utilDao,String sqlColumn,String tableName,String conditions,String[] values,String[] cols,String userType,String passType){
		 ArrayList<String[]> rs = new ArrayList<String[]>();
		StringBuffer sql = new StringBuffer("");
//		for(int i=0;i<cols.length;i++){
//			sql.append((i==cols.length-1)?cols[i]:cols[i]+",");
//		}
		sqlColumn = StringUtils.isNull(sqlColumn) ? "" : sqlColumn;
		sql.append(sqlColumn);
		sql.append(" from ");
		sql.append(tableName);
		sql.append(conditions);
		//�����������
		if(("xy".equals(userType))){
			sql.append(("ͨ��".equals(passType))?" and xysh='ͨ��'" : (("��ͨ��".equals(passType))?" and xysh='��ͨ��'":("δ���".equalsIgnoreCase(passType) ? "and xysh='δ���'" : "")));
		} else if(("xx".equals(userType))||("admin".equals(userType))){
			sql.append(" and xysh='ͨ��' ");
			sql.append(("ͨ��".equals(passType))?" and xxsh='ͨ��'" : (("��ͨ��".equals(passType))?" and xxsh='��ͨ��'":("δ���".equalsIgnoreCase(passType) ? "and xxsh='δ���'" : "")));
		}
		rs = utilDao.rsToVator(sql.toString(), values, cols);
		return rs;
	}
}
