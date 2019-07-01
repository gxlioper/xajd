/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-26 ����11:41:50 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

import com.zfsoft.utils.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ģ��
 * @�๦������: ѧ�����ڲ�����֤ 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-6-26 ����11:41:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsbmCondition {
	
	
	
	/**
	 * 
	 * @����: ��֤ѧ���Ƿ�����꼶���� 
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-26 ����11:45:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getXsnj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from view_xsjbxx where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and nj in (");
			
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append("?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	
	/**
	 * 
	 * @����: ��֤ѧ��ѧ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-12 ����08:37:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getXsxz(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and xz in (");
			
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append("?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	
	/**
	 * 
	 * @����: ��֤ѧ��ѧ�����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-12 ����08:37:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getXsxl(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and xldm in (");
			
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append("?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	/**
	 * 
	 * @����: ��֤ѧ���������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-12 ����08:37:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getPycc(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and pycc in (");
			
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append("?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * 
	 * @����: ��֤ѧ������
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-7-12 ����08:37:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getMz(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and mz in (");
			
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append("?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * 
	 * @����: ��֤ѧ����Դ��ʡ��
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-7-12 ����08:37:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getSf(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append(" substr(syd,1,2)||'0000'=?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(" or ");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * 
	 * @����:��֤ѧ������ѧԺ
	 * @���ߣ���ǿ [���ţ�785]
	 * @���ڣ�2014-1-26 ����03:05:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXylb(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num  from view_xsjbxx where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append(" xydm = ? ");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(" or ");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @����: �۲�����ǰ50%������У�ڼ��޲����ɼ��������Ƽ�ʦ����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-13 ����11:07:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param condition
	 * @return
	 * String �������� 
	 */
	public String getZztj_11318(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		boolean result = true;
		boolean flag = false; // ����ѭ������
		double tjz = 0.5; // �۲�����ǰ50
		double jspm = 0; // ���ݰ༶������������
		String xn = condition.get("xn");
		// ѧ��ѧ������sql
		String xnSql = "";
		if(xn.contains(";")){
			xnSql = "and xn||';'||xq = ? ";
		}else{
			xnSql = "and xn = ? ";
		}
		// ѧ��ѧ���µ��۲�id
		String zcxmSql = " select xmdm from xg_zhcp_zcxmb where fjdm='N' and xq = 'on' " + xnSql;
		// �۲�����sql
		StringBuilder zcpmBfbSql = new StringBuilder();
		zcpmBfbSql.append(" select count(1) c from xg_pjpy_new_cpmdb t2 ");
		zcpmBfbSql.append(" where t2.bjdm=(select bjdm from xg_pjpy_new_cpmdb where xh =? ");
		zcpmBfbSql.append(" and xn||xq=(select xn || xq from xg_zhcp_zcxmb where xmdm = ? ))");
		zcpmBfbSql.append(" and t2.xn||t2.xq=(select xn||xq  from xg_zhcp_zcxmb");
		zcpmBfbSql.append(" where xmdm =?)");
		// �༶����sql
		String bjpmSql = "select bjpm from xg_zhcp_zcfsb where xmdm=? and xh = ?";
		// ��У�ڼ��޲����ɼ�sql
		String bjgsSql = "select count(1) bjgs from view_zhhcjb where cj < 60 and xh = ? " + xnSql;
		
		String[] zqArray = xn.split(",");
		for (int i = 0 , n = zqArray.length; i < n; i++){
			flag = false;
			
			String zcxm = dao.getOneRs(zcxmSql, new String[]{ zqArray[i] }, "xmdm");
			// ================== �۲�����ǰ50 begin =================
			String bjrs = dao.getOneRs(zcpmBfbSql.toString(), new String[]{xh,zcxm,zcxm}, "c");
			String bjpm = dao.getOneRs(bjpmSql, new String[]{zcxm,xh}, "bjpm");
			
			//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
			jspm = Double.valueOf(bjrs) * tjz;
			
			//�������������ڻ����ѧ��ʵ������ʱ����������
			if (!StringUtil.isNull(bjpm) && Math.floor(jspm) >= Integer.valueOf(bjpm)){
				flag = true;
			} 
			// ================== �۲�����ǰ50 begin =================
			if(!flag){
				// ================== ��У�ڼ��޲����ɼ� begin =================
				String bjgs = dao.getOneRs(bjgsSql, new String[]{xh,zqArray[i]}, "bjgs");
				//����������Ϊ0ʱ����������
				if("0".equals(bjgs)){
					flag = true;
				}
				// ================== ��У�ڼ��޲����ɼ� end =================
			}
			// ����һ����ѡ��ѧ��ѧ�ڽ���Ƚ�
			result = result && flag;
		}
		if(result){
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * @����:�ж�ʦ����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-13 ����09:39:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 */
	public String getSfl(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xsxxb where pyfx = 'ʦ����' and xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);

		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and xn||';'||xq in ( ");
			}else{
				sql.append("and xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
	
	/**
	 * @����:�жϷ�ʦ����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-13 ����09:39:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 */
	public String getFsfl(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xsxxb where pyfx = '��ʦ����' and xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);

		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and xn||';'||xq in ( ");
			}else{
				sql.append("and xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
	
	/**
	 * 
	 * @����:�ж��Ƿ�ʦ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-9-18 ����04:17:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSfsfs(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xsxxb where sfsfs = '��' and xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);

		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and xn||';'||xq in ( ");
			}else{
				sql.append("and xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
	
	
	/**
	 * 
	 * @����:�ж��Ƿ�ʦ����
	 * @���ߣ�tgj [���ţ�1075]
	 * @���ڣ�2015-1-23 ����03:30:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSfsfs2(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append(" sfsfs = ? ");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(" or ");
				}
			}
			sql.append(")");
		}
		
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and xn||';'||xq in ( ");
			}else{
				sql.append("and xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
		
	/**
	 * 
	 * @����: ��֤ѧ������
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-12-3 ����04:08:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXslx(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and xslx in (");
			
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append("?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * 
	 * @����:��֤ѧ���Ա�
	 * @���ߣ��ո־� [1075]
	 * @���ڣ�2015-9-10 ����02:52:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXsxb(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from view_xsjbxx where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and xb in (");
			
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append("?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
		
	/**
	 * 
	 * @����: ��֤������ò
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-11-12 ����04:10:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXszzmm(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and zzmm in (");
			
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append("?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * 
	 * @����: ��ְ֤������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-11-12 ����04:10:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXszw(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_szdw_xsgb_dwb where zzzt = '1' and xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and zwid in (");
			
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append("?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	/**
	 * @����:TODO(�㽭��ѧ���Ի�_ѧ�Ѳ�����Ŀ)
	 * @���ߣ�MengWei[���ţ�1186]
	 * @���ڣ�2016-7-26 ����03:03:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXfbz(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1)num from (select * from  view_knsjgb_fqxrd where xh = ? ) t1 left join xsxxb t2 on t1.xh =t2.xh ");
		sql.append(" left join (select xh,sfdq,decode(sfgc,'1',' �м�','') sfgc,decode(lszn,'1',' ��ʿ��Ů','') lszn,decode(ylzd1,'1',' �Ÿ�������Ů','') ylzd1 from xg_xszz_new_jtqkdcb where sfdq in ('�¶�') or sfgc = '1' or lszn = '1'  or ylzd1 = '1' ) t3 on t1.xh=t3.xh ");
		sql.append(" where (t2.jg='520328' or t3.xh is not null or (t1.rddc='2' and t2.mz not like (select mzdm from mzdmb where mzmc like '����') and  t2.sfzx = '��У')) and (to_number(t2.nj)+to_number(t2.xz))>(select substr(dqxn,6.4) from xtszb) ");
		params.add(xh);
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @������ѧ������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��13�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getXsjg(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				sql.append(" substr(jg,0,2)||'0000'=?");
				params.add(values[i]);
				if(i != n-1){
					sql.append(" or ");
				}
			}
			sql.append(")");
		}
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
}
