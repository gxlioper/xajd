package xgxt.pjpy.comm.pjpy.tjsz;

import java.sql.SQLException;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;

public class PjpyTjszMethod extends CommService{
	
	/**
	 * ǰ�ý�ѧ����Ի��޸�
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String attainBurseByXn(HashMap<String,Object>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//��������
		String sqlx=(String)map.get("sqlx");
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String []xhArr=(String[])map.get("xh");
		
		// -------------------���Ի�ҵ�� sql begin-------------------------
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from xg_view_pjpy_xmsh a ");
		sql.append(" where a.shjb = (select max(b.xh) maxLv from xg_xtwh_spbz b ");
		sql.append(" where b.splc = a.lcid) and a.shzt = 'ͨ��' ");
		
		sql.append(" and xh in( ");
		for(int i=0;i<xhArr.length;i++){
			if(i!=0){
				
				sql.append(",");
			}
			
			sql.append(xhArr[i]);
			
		}
		sql.append(" ) ");
		
		sql.append(" and (xmmc ='����ѧ�����' or xmmc='����ѧ��' or xmmc='������Ա' ");
		sql.append(" or xmmc ='����ѧ���ɲ�' or xmmc='���㹲���Ÿɲ�' or xmmc='���㵳Ա')  ");
		// -------------------���Ի�ҵ�� sql end-------------------------
		
		String[]pjzgXh=dao.getArray(sql.toString(), new String[]{}, "xh");
		
		String[]nozgXh=getNoRepeatArrValue(pjzgXh,xhArr);
		
		//����˵��
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? false :true );
		
		if (!flag) {
			if("sq".equalsIgnoreCase(sqlx)){
				//�����������Ļ�
				message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

			}else{
				
				message=nozgXh[0];
			}
		}
		return message;
	}
	
	/**
	 * �ж�����ѧ���Ƿ���Υ�ʹ���
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkWjcfByPjXn(HashMap<String,Object>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//��������
		String sqlx=(String)map.get("sqlx");
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String []xhArr=(String[])map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from wjcfb ");
		sql.append(" where xn = (select pjxn from xg_pjpy_xtszb where rownum = 1) ");
		
		sql.append(" and xh in( ");
		for(int i=0;i<xhArr.length;i++){
			if(i!=0){
				
				sql.append(",");
			}
			
			sql.append("'"+xhArr[i]+"'");
			
		}
		sql.append(" ) ");
		
		sql.append(" and xxsh = 'ͨ��'  ");
		sql.append(" group by xh  ");
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{}, "xh");
		
		//����˵��
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (!flag) {
			if("sq".equalsIgnoreCase(sqlx)){
				//�����������Ļ�
				message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

			}else{
				
				message=nozgXh[0];
			}
		}
		return message;
	}
	
	/**
	 * �ж��Ƿ���Υ�ʹ���
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkWjcf(HashMap<String,Object>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//��������
		String sqlx=(String)map.get("sqlx");
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String []xhArr=(String[])map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from wjcfb ");
		sql.append(" where xh in( ");
		for(int i=0;i<xhArr.length;i++){
			if(i!=0){
				
				sql.append(",");
			}
			
			sql.append("'"+xhArr[i]+"'");
			
		}
		sql.append(" ) ");
		
		sql.append(" and xxsh = 'ͨ��'  ");
		sql.append(" group by xh  ");
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{}, "xh");
		
		//����˵��
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (!flag) {
			if("sq".equalsIgnoreCase(sqlx)){
				//�����������Ļ�
				message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

			}else{
				
				message=nozgXh[0];
			}
		}
		return message;
	}
	
	/**
	 * �Ƿ�������
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkKns(HashMap<String,Object>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//��������
		String sqlx=(String)map.get("sqlx");
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String []xhArr=(String[])map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from xszz_knsb ");
		sql.append(" where xh not in( ");
		for(int i=0;i<xhArr.length;i++){
			if(i!=0){
				
				sql.append(",");
			}
			
			sql.append("'"+xhArr[i]+"'");
			
		}
		sql.append(" ) ");
		
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{}, "xh");
		
		//����˵��
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (!flag) {
			if("sq".equalsIgnoreCase(sqlx)){
				//�����������Ļ�
				message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

			}else{
				
				message=nozgXh[0];
			}
		}
		return message;
	}
}
