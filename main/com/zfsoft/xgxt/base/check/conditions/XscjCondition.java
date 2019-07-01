/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-2 ����10:37:41 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��֤�ɼ����ҵ��
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-5-2 ����10:37:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XscjCondition {

	
	/**
	 * 
	 * @����: ѧ���������Ŀ��
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-2 ����11:57:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjgs(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		if(Base.xxdm.equals("12684")){
			sql.append("select count(1) bjgs from cjb where zpcj1 < 60 and xh = ? ");
		}
		//���ϳ���ѧԺ���Ի�
		else if(Base.xxdm.equals("11527")){
			sql.append("select count(1) bjgs from (select xh,xn,kcmc,cxbj,xkkh,max(cj) cj from view_zhhcjb group by xh,xn,kcmc,cxbj,xkkh) where cj < 60 and xh = ? ");
		}else{			
			sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and xh = ? ");
		}
		if("10344".equals(Base.xxdm)){
			sql.append(" and kcxz not in ('�ڶ�����','����ѡ�޿�') ");
		}
		params.add(xh);
		
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				//���ϳ���ѧԺ
				if(Base.xxdm.equals("11527")){
					sql.append("and (cxbj is null or cxbj = '0') and substr(xkkh,2,9) in ( ");
				}else{					
					sql.append("and xn||';'||xq in ( ");
				}
			}else{
				//���ϳ���ѧԺ
				if(Base.xxdm.equals("11527")){
					sql.append("and (cxbj is null or cxbj = '0') and substr(xkkh,2,9) in ( ");
				}else{					
					sql.append("and xn in ( ");
				}
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				//���ϳ���ѧԺ���Ի���ֻ�ж�ѧ�꣩
				if(Base.xxdm.equals("11527")){
					params.add(zqArray[i].substring(0, 9));
				}else{					
					params.add(zqArray[i]);
				}
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	/**
	 * 
	 * @����: ѧ���������Ŀ����������(����������ѡ�޿�)
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-2 ����11:57:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjgsNbkNgx(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

			
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and xh = ? ");
		//����ְҵ����ѧԺ
		if ("12874".equals(Base.xxdm)) {
			sql.append(" and kcxz in ('ͨʶ��ѡ��','רҵ��ѡ��','רҵ���޿�','���޿�','������޿�','ͨʶ���޿�','������ѡ��','רҵ���Ŀ�','רҵ���޿α��޿�') ");
		}
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	/**
	 * 
	 * @����: ѧ���������Ŀ��������(����������ѡ�޿�)
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-2 ����11:57:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjgsHbkNgx(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and (bkcj is null or bkcj < 60) and xh = ? ");
		params.add(xh);
		//����ְҵ����ѧԺ
		if ("12874".equals(Base.xxdm)) {
			sql.append(" and kcxz in ('ͨʶ��ѡ��','רҵ��ѡ��','רҵ���޿�','���޿�','������޿�','ͨʶ���޿�','������ѡ��','רҵ���Ŀ�','רҵ���޿α��޿�') ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	/**
	 * 
	 * @����:ѧ���������Ŀ��(�����ַ��ж�)
	 * @���ߣ�taogj [���ţ�1075]
	 * @���ڣ�2014-10-22 ����03:36:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjgs2(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
		
	
	/**
	 * 
	 * @����:ѧ���������Ŀ��(�㽭����)
	 * @���ߣ��ո־� [1075]
	 * @���ڣ�2015-3-25 ����01:52:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjgs3(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where zpcj1 < 60 and xh = ? ");
		sql.append(" and kcmc not in (select kcmc from tyk_zjb) and kcxz not like '%����ѡ�޿�%'");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
		
	/**
	 * 
	 * @����: ѧ���������Ŀ��(���������ɼ�)
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-2 ����11:57:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjgs4(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and (bkcj is null or bkcj < 60) and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	/**
	 * 
	 * @����:ѧ�����޿β������Ŀ��(����ʦ��)
	 * @���ߣ��ո־� [1075]
	 * @���ڣ�2015-9-10 ����11:40:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjgs5(String xh, HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and kcxz like '%����%' and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	/**
	 * 
	 * @����:ѧ���������Ŀ��(�������ϴ�ѧ)
	 * @���ߣ��ո־� [1075]
	 * @���ڣ�2015-9-10 ����11:40:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjgs6(String xh, HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and xh = ? and (cxbj is null or cxbj='0' or cxbj='3') and kcxz not like '����ѡ��%' and kcxz not like 'Уѡ%' ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	/**
	 * 
	 * @����:ƽ���ɼ� (������������)
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-6 ����07:02:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPjcj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select (sum(cj)/count(1)) pjcj from view_zhhcjb where xh=? and kcmc not like '%����%' ");
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
		
		sql.append("group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjcj");
	}
	
	/**
	 * 
	 * @����:ƽ���ɼ� (����)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-4 ����03:54:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBxPjcj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select (sum(cj)/count(1)) pjcj from view_zhhcjb where xh=? and kcxz like '%����%' ");
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
		
		sql.append("group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjcj");
	}
	
	
	/**
	 * 
	 * @����:ƽ���ɼ� (���пγ�)/ָ�������ڸ��Ƴɼ�ƽ����
	 * @���ߣ�tgj [���ţ�1075]
	 * @���ڣ�2014-10-15 ����10:25:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPjcj3(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select (sum(cj)/count(1)) pjcj from view_zhhcjb where xh=? ");
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
		
		sql.append("group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjcj");
	}
	
	/**
	 * @����:ƽ���ɼ� (���пγ�)������/ָ�����ڸ��γ̳ɼ��꼶רҵ����
	 *��ȡ��ѧ����ָ�����������гɼ����ƽ�������Լ������꼶��רҵ������)
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��19�� ����2:28:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPjcj3Rank(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT rank FROM (");
		sql.append("SELECT c.xh, dense_rank() over (partition by v.nj,v.zydm order by (avg(c.cj)) desc) rank ");
		sql.append("FROM cjb c LEFT JOIN VIEW_XSBFXX v ON c.xh = v.xh ");
		sql.append("WHERE 1=1 ");
		
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
		
		sql.append(" GROUP BY c.xh,v.nj,v.zydm)");
		sql.append(" where xh = ? ");
		
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "rank");
	}
		
	/**
	 * 
	 * @����:ƽ���ɼ� (�������ϴ�ѧ���Ի�)
	 * @���ߣ�tgj [���ţ�1075]
	 * @���ڣ�2014-10-10 ����05:10:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPjcj2(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select (nvl(round ((sum(cj * xf) / sum(xf)),0),0)) pjcj from view_zhhcjb where xh=? and (cxbj is null or cxbj='0' or cxbj='3') and kcxz not like '����ѡ��%' and kcxz not like 'Уѡ%' ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjcj");
	}



	/**
	 * 
	 * @����: �ȼ����Գɼ�����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-7 ����09:34:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDjksjg(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xsdjksb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);

		//���ݸ�ʽ {djksmc:'CET4',tjlx='>=',tjz='425'},{djksmc:'CET6',tjlx='>=',tjz='425'}
		String tjz = condition.get("tjz");
		
		if (!StringUtils.isNull(tjz)){
			
			String[] tjzInfo = tjz.split(",");
			sql.append(" and (");
			
			for (int i = 0 ; i < tjzInfo.length ; i++){
				
				String[] info = tjzInfo[i].split("#");
				
				sql.append("(djksmc=? ");
				params.add(info[0]);
				sql.append(" and cj");
				sql.append(info[1]);
				sql.append("?");
				params.add(info[2]);
				sql.append(")");
				
				if (i != tjzInfo.length -1){
					sql.append(" or ");
				}
			}
			
			sql.append(" )");
			
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
	 * @����: �ɼ�����
	 * @���ߣ�������
	 * @���ڣ�2013-12-16 ����09:34:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCjjd(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select min(jd) jd from view_zhhcjb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);

		//���ݸ�ʽ {djksmc:'CET4',tjlx='>=',tjz='425'},{djksmc:'CET6',tjlx='>=',tjz='425'}
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "jd");
	}
	


	/**
	 * 
	 * @����:���Ƶȼ����Գɼ�
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-7 ����10:24:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDkdjcj(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select max(to_number(cj)) cj from xsdjksb where xh = ? and ( ");
		
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		String[] tjdms = condition.get("tjdm").split(","); //�����������Ϊ�ȼ���������,�༶������,�ŷָ����:CET4,CET6
		
		for(int i=0; i<tjdms.length; i++){
			if(i==0){
				sql.append(" djksmc=? ");
			}else{
				sql.append(" or djksmc=? ");
			}
			params.add(tjdms[i]);
		}
	 
		sql.append(" ) ");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}

	/**
	 * 
	 * @����:��ý�����ɼ���������(�ٷֱ�)
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-10-24 ����02:23:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZycjJbbl(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select max(to_number(jbbl)) jbbl from xg_view_pjpy_jbbl where xh = ? ");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "jbbl");
	}
	
	
	/**
	 * 
	 * @����:ƽ���ɼ� 
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-6 ����07:02:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getKScj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(min(to_number(cj)), 999) zxcj  from view_zhhcjb where xh=? ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxcj");
	}
	
	/**
	 * 
	 * @����:ָ�����ڱ��޿ε��Ƴɼ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-4 ����04:25:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBxKccj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select nvl(min(to_number(cj)),0) zxcj  from view_zhhcjb where xh=? and kcxz like '%����%' ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxcj");
	}
	
	
	/**
	 * 
	 * @����:����ѡ������Ƴɼ������ݹ�ҵ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-22 ����11:02:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getKScj2(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select min(to_number(cj)) zxcj  from view_zhhcjb where xh=? and kcxz not like '%����ѡ��%' ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxcj");
	}
	
	/**
	 * 
	 * @����:���޿γɼ�(����ʦ��)
	 * @���ߣ��ո־� [1075]
	 * @���ڣ�2015-9-10 ����02:04:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getKScj3(String xh, HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select min(to_number(cj)) zxcj  from view_zhhcjb where xh=? and kcxz like '%����%' ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxcj");
	}
	
	/**
	 * 
	 * @����: ������߷֣����ϳ��У�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-8-19 ����10:18:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDkzgf(String xh, HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select max(to_number(cj)) zxcj  from view_zhhcjb where xh=? ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxcj");
	}
	
	/**
	 * @����:�����γɼ� �㽭����ְҵѧԺ
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-12-12 ����04:31:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getTykcj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select min(to_number(cj)) tykcj  from view_zhhcjb where xh=? and kcmc like '%����%' ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "tykcj");
	}
	
	
	
	/**
	 * 
	 * @����:�����γɼ� (�㽭����)
	 * @���ߣ��ո־� [1075]
	 * @���ڣ�2015-3-25 ����01:54:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getTykcj2(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		if(("13867").equals(Base.xxdm) || ("12688").equals(Base.xxdm)){
			//��������
			sql.append("select nvl(min(to_number(cj)),999) tykcj  from view_zhhcjb where xh=? and kcmc like '%����%' ");
		}else{
			sql.append("select nvl(min(to_number(cj)),999) tykcj  from view_zhhcjb where xh=? and kcmc like '%�����뽡��%' ");
		}
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "tykcj");
	}
	
	/**
	 * 
	 * @����:�۲������ɼ� (�㽭����)
	 * @���ߣ����� [856]
	 * @���ڣ�2015-3-25 ����01:54:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getTykcj3(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(min(to_number(fs)),0) tykcj  from view_zjjd_tycj where xh=? ");
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "tykcj");
	}
	/**
	 * 
	 * @����:������ó�����ȼ�
	 * @���ߣ����� [856]
	 * @���ڣ�2015-7-7 ����09:11:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getTykcj4(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when cj >= 98 then '��' else '��' end dj from (select min(cj) cj  from cjb where xh=? and kcmc like '%����%' ");
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
			sql.append("))");
		}
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "dj");
	}
	/**
	 * 
	 * @����: �ɼ�����
	 * @���ߣ���ǿ
	 * @���ڣ�
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPjxfjd(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select round(sum(jd)/count(1),2) pjxfjd from view_zhhcjb where xh = ? ");
		
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
		System.out.println("ִ�е�SQL====="+sql.toString());
		System.out.println("����======"+params);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjxfjd");
	}
	
	
	/**
	 * 
	 * @����:ָ�������޲�����¼
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-19 ����02:30:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBkcj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from view_zhhcjb where bkcj is not null and ((kclx is not null and kclx not in ('���޿�','����ѡ�޿�','���޿�')) or kclx is null) and xh = ? ");
		
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	} 
	
	
	/**
	 * 
	 * @����: ��У�ڼ��õ��������
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-11-4 ����04:10:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZzjesx(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(sum(je),0) as jesx from xg_xszz_new_zzxmjgb where xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "jesx");
	}
	
	
	/**
	 * 
	 * @����: ÿѧ��ѡ��+�����ܿγ���Ŀ
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-12-2 ����04:29:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getKmxzAll(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) n from view_zhhcjb where (kcxz like '%ѡ��%' or kcxz like '%����%') and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	
	/**
	 * 
	 * @����: ÿѧ������ܿγ���Ŀ
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-12-3 ����10:33:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getKmxzBx(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) n from view_zhhcjb where kcxz like '%����%' and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	
	/**
	 * 
	 * @����: �ɼ����㣨���޿γ̣�
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-12-3 ����01:36:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPjxfjdBx(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select round(sum(jd)/count(1),2) pjxfjd from view_zhhcjb where kcxz like '%����%' and xh = ? ");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjxfjd");
	}
	
	
	/**
	 * 
	 * @����: ��ѧ�֣�ȫ���γ̣�
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-12-3 ����02:50:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZxfAll(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select sum(xf) zxf from view_zhhcjb where xh = ? ");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxf");
	}
	
	
	/**
	 * 
	 * @����: ��ѧ�֣����ޣ�
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-12-3 ����02:58:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZxfBx(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select sum(xf) zxf from view_zhhcjb where kcxz like '%����%' and xh = ? ");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxf");
	}
	
	/**
	 * 
	 * @����:CET3
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-3-23 ����11:43:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCET3(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,max(to_number(case when (djksmc='CET4' or djksmc='CET6') and cj < '425'  then '0' else cj end)) cj from xsdjksb ");
		sql.append(" where xh =? and djksmc like 'CET%' ");
		
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
		
		sql.append("group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	
	/**
	 * 
	 * @����:��ҵ���(����)�����ɼ�Ϊ�������ϣ������ã�
	 * @���ߣ�tgj [1075]
	 * @���ڣ�2015-5-14 ����06:13:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBydbcj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select case when count(1)>0 then 1 else 0 end num from cjb where kcmc like '%��ҵ%���%' and cj in ('����','����') and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	
	/**
	 * 
	 * @����:�Ϻ�����ȡ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-10-24 ����02:23:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getJdc(String xh,HashMap<String,String> condition){

		StringBuilder sql = new StringBuilder();
		
		sql.append("select fs from (select xn,xq,xh,jd-lag(jd,1,0) over(partition by xh order by xh,xn,xq) fs ");
		sql.append("from chg_zz.xg_pjpy_jwjd) where xn||xq = (select xn||xq from xg_pjpy_new_csszb) and xh = ? ");
		
		return DAO.getInstance().getOneRs(sql.toString(), new String[]{xh}, "fs");
	}
	
	/**
	 * 
	 * @����:����ҽѧԺ���ڻ�ȡ���Ǽ����һ�ô���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-9-23 ����07:26:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
    public String getXjqscs(String xh,HashMap<String,String> condition){
    	String flagxn = condition.get("xn");
		String tjdm = condition.get("tjdm");
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) num");
		sql.append(" from view_xg_gygl_new_wsjc_qsfsb_yf t ");
		sql.append(" where t.qsh = (select qsh from xg_gygl_new_cwxxb where xh = ?)");
		params.add(xh);
		if(flagxn != null && !flagxn.trim().equals("")){
			String[] xn = flagxn.split(",");
			sql.append(" and t.xn || t.xq  in (");
			for(int i=0;i<xn.length;i++){
				sql.append("?");
				params.add(xn[i]);
				if(i != xn.length-1 ){
					sql.append(",");
				}
			}
			sql.append(")");
		}
	
		sql.append(" and t.dj = ?");
		if(tjdm.equals("pjtj_gygl_13779_3xcs")){
			params.add("����");
		}else if(tjdm.equals("pjtj_gygl_13779_4xcs")){
			params.add("����");
		}else if(tjdm.equals("pjtj_gygl_13779_5xcs")){
			params.add("����");
		}
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
    
    /**
     * 
     * @����:����ҽѧԺ�Ƿ��ȡ�������Ǽ������������ҳƺ�
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2015-9-24 ����08:39:56
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getXjqs(String xh,HashMap<String,String> condition){
    	String flagxn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	
    	sql.append(" select case when count(1)>0 then '1'  ");
    	sql.append(" else '0' end flag ");
    	sql.append(" from view_xg_gygl_new_wsjc_qsfsb_yf t ");
    	sql.append(" where t.qsh = (select qsh from xg_gygl_new_cwxxb where xh = ?)");
    	params.add(xh);
    	if(flagxn != null && !flagxn.trim().equals("")){
    		String[] xn = flagxn.split(",");
    		sql.append(" and t.xn || t.xq  in (");
    		for(int i=0;i<xn.length;i++){
    			sql.append("?");
    			params.add(xn[i]);
    			if(i != xn.length-1 ){
    				sql.append(",");
    			}
    		}
    		sql.append(")");
    	}
    	sql.append(" and t.dj in (?,?,?)");
    	params.add("����");
    	params.add("����");
    	params.add("����");
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "flag");
    }

   /**
    *  
    * @����:����ְҵ����ѧԺ ���Ǽ���������
    * @���ߣ�caopei[���ţ�1352]
    * @���ڣ�2016-9-13 ����02:39:20
    * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
    * @param xh
    * @param condition
    * @return
    * String �������� 
    * @throws
    */
    public String getXjss(String xh,HashMap<String,String> condition) throws Exception{
			String flagxn = condition.get("xn");
			List<String> params = new ArrayList<String>();
			StringBuilder sql = new StringBuilder();
			sql.append(" select case when count(1)>0 then '1'  else '0' end flag ");
			sql.append(" from xg_gygl_new_wsjc_qsfsb a left join xg_gygl_new_wsjc_jcrcb b " +
					" on a.guid = b.guid where b.jclx='2' and a.dj is not null ");
			sql.append(" and a.qsh = (select qsh from xg_gygl_new_cwxxb where xh = ?) ");
			params.add(xh);
			if(flagxn != null && !flagxn.trim().equals("")){
				String[] xn = flagxn.split(",");
				sql.append(" and b.xn||';'||b.xq  in (");
				for(int i=0;i<xn.length;i++){
					sql.append("?");
					params.add(xn[i]);
					if(i != xn.length-1 ){
						sql.append(",");
					}
				}
				sql.append(")");
			}
			String tjz = condition.get("tjz");
			if (!StringUtil.isNull(tjz)){
				sql.append(" and a.dj in (");
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
			return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "flag");
    	
	}
	
    /**
     * 
     * @����:����ҽѧԺѧ��ƽ��ѧ�ѣ�ѧ�ڣ�
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2015-9-24 ����08:39:56
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getPjxf(String xh,HashMap<String,String> condition){
    	String xnflag = condition.get("xn");
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		//����ǵڶ�ѧ��
		if(xnflag != null && !xnflag.trim().equals("")){
			String[] xnxq = xnflag.split(";");
			String xn = xnxq[0];
			String xq = xnxq[1];
			String[] nds = xn.split("-");
			boolean flag = this.xqpd(xq);
			if(flag){
				sql.append(" select sum(xfje)/4 pjxf from XG_GYGL_YXFB t where  xh = ?  and  to_char(to_date(t.rq,'yyyymmdd'),'yyyymmdd') between  ?  and  ? ");
				String nd = nds[1];
				params.add(xh);
				params.add(nd+"0201");
				params.add(nd+"0731");
			}else{
				//�����һѧ��
				sql.append(" select sum(xfje)/5 pjxf from XG_GYGL_YXFB t where  xh = ?  and  to_char(to_date(t.rq,'yyyymmdd'),'yyyymmdd') between  ?  and  ? ");
				params.add(xh);
				String nd = nds[0];
				params.add(nd+"0801");
				params.add(nds[1]+"0131");
			}
		}else{
			return condition.get("tjz");
		}
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjxf");
	}
    
    /**
     * 
     * @����:�Ƿ�ڶ�ѧ���ж�
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2015-9-24 ����09:18:42
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xqdm
     * @return
     * boolean �������� 
     * @throws
     */
    public boolean xqpd(String xqdm){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select count(1) flag from xqdzb where xqjb <");
    	sql.append(" (select xqjb from xqdzb where xqdm = ?)");
    	String flag = DAO.getInstance().getOneRs(sql.toString(), new String[]{xqdm}, "flag");
    	return Integer.parseInt(flag)>0 ? true : false;
    }
    
    /**
     * 
     * @����:�Ĵ���Ϣְҵ����ѧԺ���������м��롾����ѧ�ִ��ڵ���3�ֵ�,ȡ�ճ���Ϊѧ�ڻ����ܷ֣��ճ���Ϊά���ܷ֣���
     * ����������
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2015-11-2 ����04:39:16
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getZhzf_13815(String xh,HashMap<String,String> condition){
    	String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select  sum(decode(t1.rcxwfzlx,'01',t.fz,'02',-t.fz,t.fz)) zhzf from XG_RCSW_RCXWJG t" +
    			" left join  xg_rcsw_rcxwlbdmb t1  " +
    			" on t.rcxwlbdm = t1.rcxwlbdm" +
    			" where xh = ? ");
    	params.add(xh);
    	if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append(" and xn||';'||xq in ( ");
			}else{
				sql.append(" and xn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"zhzf");
    }
    
    /**
     * 
     * @����:�Ĵ���Ϣְҵ����ѧԺ���������м��롾�ۺ������������δ��ڵ�������ֵ��
     * ����������
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2015-11-2 ����04:39:16
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getZhjbmc_13815(String xh,HashMap<String,String> condition){
    	String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select  t.jbmc from xg_view_pjpy_jbbl t where t.xh = ?");
    	params.add(xh);
    	if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append(" and xn||';'||xq in ( ");
			}else{
				sql.append(" and xn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"jbmc");
    }
    
    /**
     * 
     * @����: У�ڴ�ѧӢ��һ���ɼ����Ϻ�Ϸ�磩
     * @���ߣ�������[���ţ�1123]
     * @���ڣ�2015-12-22 ����09:32:37
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getDxyyYj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select max(to_number(cj)) cj from cjb where xh=? and kcmc like '%��ѧӢ��ȼ�һ��%'");
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
    
    
    /**
     * 
     * @����: У�ڴ�ѧӢ������ɼ����Ϻ�Ϸ�磩
     * @���ߣ�������[���ţ�1123]
     * @���ڣ�2015-12-22 ����09:32:37
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getDxyyEj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select max(to_number(cj)) cj from cjb where xh=? and kcmc like '%��ѧӢ��ȼ�����%'");
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
    
    
      
    /**
     * 
     * @����: У�ڴ�ѧӢ�������ɼ����Ϻ�Ϸ�磩
     * @���ߣ�������[���ţ�1123]
     * @���ڣ�2015-12-22 ����09:32:37
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getDxyySj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select max(to_number(cj)) cj from cjb where xh=? and kcmc like '%��ѧӢ��ȼ�����%'");
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
    
    
    /**
     * 
     * @����: У�ڴ�ѧӢ���ļ��ɼ����Ϻ�Ϸ�磩
     * @���ߣ�������[���ţ�1123]
     * @���ڣ�2015-12-22 ����09:32:37
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getDxyySij(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select max(to_number(cj)) cj from cjb where xh=? and kcmc like '%��ѧӢ��ȼ��ļ�%'");
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
    
    
    
    /**
     * 
     * @����: �㽭�ʵ�ְҵ����ѧԺ��������--��Ŀ����--���������м�һ������ֵ
     * 		    �������Ǽ�������ѡ������
     * @���ߣ�����[���ţ�1186]
     * @���ڣ�2015-12-23 ����11:50:00
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getYwxpx(String xh,HashMap<String,String> condition){
    	String xn = condition.get("xn");
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select nvl(sum(b.cnt),0) cnt ");
		sql.append(" from xg_gygl_new_cwxxb a ");
		sql.append(" left join (select LDDM, QSH, xn, xq, count(1) cnt ");
		sql.append(" from (select LDDM, QSH, DJ, xn, xq, jcyf, tjzt ");
		sql.append(" from VIEW_XG_GYGL_NEW_WSJC_QSFSB_YF where 1 = 1 ");			
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
		sql.append(" and dj = '5��') ");
		sql.append(" group by LDDM, QSH, xn, xq) b ");
		sql.append(" on a.lddm = b.lddm ");
		sql.append(" and a.qsh = b.qsh ");
		sql.append(" where a.xh = ? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cnt");
	}
    
    
    /**
     * 
     * @����:���ܳɼ�
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2016-2-23 ����09:02:40
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getCpcj(String xh,HashMap<String,String> condition){
    	String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select t.fs from xg_pjpy_new_cpfs t where t.xh = ?");
    	params.add(xh);
    	if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append(" and xn||';'||xq in ( ");
			}else{
				sql.append(" and xn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"fs");
    }
    
    /**
     * 
     * @����:���޿�ÿ�Ƴɼ�������ֵ��
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2016-2-24 ����03:11:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getBxkcj(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select min(cj) cj from view_zhhcjb where kcxz like '%����%' and xh = ? ");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
    
    /**
     * 
     * @����:��չ��ÿ�Ƴɼ�������ֵ��
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2016-2-24 ����03:19:39
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getTzkcj(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select min(cj) cj from view_zhhcjb where kcxz like '%��չ%' and xh = ? ");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
    
    /**
     * 
     * @����:�۲�������
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2016-2-24 ����03:19:39
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getZcpmb(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select zcpmb from xg_view_pjpy_zcpmb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zcpmb");
	}
    
    /**
     * 
     * @����: ��ҵ��ƻ��������û���80������(��ͨ�Ƽ��I�WԺ)
     * @���ߣ�������[���ţ�1123]
     * @���ڣ�2016-4-1 ����11:15:47
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getBysjlwcj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select case when count(1)>0 then 1 else 0 end num from view_zhhcjb where kcmc in ('��ҵ���','����') and (cj >= 80) and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
    }
    
    /**
     * 
     * @����: ѧҵ�ɼ�(��ͨ�Ƽ��I�WԺ)
     * @���ߣ�������[���ţ�1123]
     * @���ڣ�2016-4-1 ����02:29:38
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getXycj(String xh,HashMap<String,String> condition){
    	String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select max(t.cj) fs from xg_pjpy_new_xycj t where t.xh = ?");
    	params.add(xh);
    	if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append(" and xn||';'||xq in ( ");
			}else{
				sql.append(" and xn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"fs");
    }
    
    /**
     * 
     * @����:��������������Υ�ͼ�¼��
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-4-1 ����02:29:38
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getBjywwj(String bjdm,HashMap<String,String> condition){
    	String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select count(1) num from xg_view_wjcf_wjcfb where bjdm = ?");
    	params.add(bjdm);
    	if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append(" and xn||';'||xq in ( ");
			}else{
				sql.append(" and xn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"num");
    }
    
    /**
     * 
     * @����:�����������������꼶��
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-4-1 ����02:29:38
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getKsqnj(String bjdm,HashMap<String,String> condition){
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select count(1) num from  view_njxyzybj where bjdm = ?  ");
    	params.add(bjdm);
    	String tjz = condition.get("tjz");
    	if (!StringUtil.isNull(tjz)){
    		sql.append(" and nj in (");
			String[] zqArray = tjz.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"num");
    }
    
    /**
     * 
     * @����:��������ɼ���70(��)��80(����)֮��
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-9-9 ����10:39:16
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param bjdm
     * @param condition
     * @return
     * String �������� 
     * @throws
     */
    public String getCjBetween70And80(String xh,HashMap<String,String> condition){
    	String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select count(1) count from view_zhhcjb where cj >= 70 and cj < 80 and xh = ? ");
    	params.add(xh);
    	if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append(" and xn||';'||xq in ( ");
			}else{
				sql.append(" and xn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"count");
    }
    
    /**
	 * 
	 * @����: �㽭ͬ�ÿƼ�ְҵѧԺ���Ƴɼ�������ڶ���
	 * @���ߣ�yxy[1206]
	 * @���ڣ�2016-12-16 ����09:34:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCjjdZjtj(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(min(jd),0) jd from (select * from view_zhhcjb where kcxz not like '%����ѡ��%') where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);

		//���ݸ�ʽ {djksmc:'CET4',tjlx='>=',tjz='425'},{djksmc:'CET6',tjlx='>=',tjz='425'}
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "jd");
	}
	
	/**
	 * 
	 * @����: �㽭ͬ��ְҵѧԺ˼��Ʒ����Ϊʵ������>=?
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-28 ����11:12:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
  public String getKScjDybxk(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(max(to_number(cj)), 0) zxcj  from view_zhhcjb where xh=? and   kcmc like '%˼��Ʒ����Ϊʵ��%'");
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
		
	//	sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxcj");
	}
  
 /**
  * 
  * @����: �Ϻ�Ϸ��ѧԺרҵ��
  * @���ߣ�yxy[���ţ�1206]
  * @���ڣ�2016-11-2 ����04:59:54
  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
  * @param xh
  * @param condition
  * @return
  * String �������� 
  * @throws
  */
  public String getZyj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select sum(fz) fz from view_qtjxsz where xh = ? and jxdm = '1' ");
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
				params.add((zqArray[i].split(";"))[0]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
	//	sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "fz");
	}
  
  /**
   * 
   * @����:�Ϻ�Ϸ��ѧԺ������
   * @���ߣ�yxy[���ţ�1206]
   * @���ڣ�2016-11-2 ����05:01:28
   * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
   * @param xh
   * @param condition
   * @return
   * String �������� 
   * @throws
   */
  public String getXsj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select sum(fz) fz from view_qtjxsz where xh = ? and jxdm = '2' ");
		params.add(xh);
		
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			//if(xn.contains(";")){
			//	sql.append("and xn||';'||xq in ( ");
			//}else{
				sql.append("and xn in ( ");
			//}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add((zqArray[i].split(";"))[0]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
	//	sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "fz");
	}
  
  /** 
	 * @����:��ȡ��ѧӢ��ɼ�(һ��)(�Ϻ�Ϸ��ѧԺר��)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-21 ����10:23:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getCet_One(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select max(to_number(cj)) cj from cjb where xh = ? and kcmc = ?");
		
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		params.add("��ѧӢ��һ��");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/** 
	 * @����:��ȡ��ѧӢ��ɼ�(����)(�Ϻ�Ϸ��ѧԺר��)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-21 ����10:23:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getCet_Two(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select max(to_number(cj)) cj from cjb where xh = ? and kcmc = ?");
		
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		params.add("��ѧӢ�����");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/** 
	 * @����:��ȡ��ѧӢ��ɼ�(����)(�Ϻ�Ϸ��ѧԺר��)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-21 ����10:23:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getCet_Three(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select max(to_number(cj)) cj from cjb where xh = ? and kcmc = ?");
		
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		params.add("��ѧӢ������");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/** 
	 * @����:��ȡ��ѧӢ��ɼ�(�ļ�)(�Ϻ�Ϸ��ѧԺר��)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-21 ����10:23:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getCet_Four(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select max(to_number(cj)) cj from cjb where xh = ? and kcmc = ?");
		
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		params.add("��ѧӢ���ļ�");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	
	
	/** 
	 * @����:��ȡѧ����Ӧרҵ���ɿγɼ�����(�Ϻ�Ϸ��ѧԺר��)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-21 ����04:17:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getZyzgkcjRank(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select pjcj,rn from view_shxj_xszyzgkcj where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and pjxn||';'||xq in ( ");
			}else{
				sql.append("and pjxn in ( ");
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
		Map<String,String> map = DAO.getInstance().getMap(sql.toString(), params.toArray(new String[]{}), new String[]{"rn","pjcj"});
		if(null == map || map.size() == 0){
			return null;
		}
		if("0".equals(map.get("pjcj"))){//��ȡƽ���ɼ�
			return null;
		}else{
			return map.get("rn");
		}
	}
	
	/**
	 * 
	 * @����: �ൺ�����Ǽ����Ҵ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-8 ����05:56:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	 public String getXjqsCsQdbh(String xh,HashMap<String,String> condition){
			
			List<String> params = new ArrayList<String>();
			StringBuilder sql = new StringBuilder();
			
			sql.append(" select count(1) num  ");
			sql.append(" from  ");
			sql.append("  (select a.LDDM,");
			sql.append("   a.QSH,");
			sql.append("   a.FS,");
			sql.append("   a.DJ,");
			sql.append("  a.guid,");
			sql.append("   a.lrsj,");
			sql.append("  b.xn,");
			sql.append("  b.xq,");
			sql.append("  b.jcyf,");
			sql.append("  a.jcrq,");
			sql.append("  a.jcbm,");
			sql.append("  a.jcry,");
			sql.append("   b.tjzt");
			sql.append("  from XG_GYGL_NEW_WSJC_QSFSB a");
			sql.append("   left join xg_gygl_new_wsjc_jcrcb b");
			sql.append("   on a.guid = b.guid where tjzt = '1')");
			sql.append(" where lddm || qsh =");
			sql.append(" (select lddm || qsh from xg_gygl_new_cwxxb where xh = ?)");
			params.add(xh);
			sql.append("  and dj in (select dj from XG_GYGL_NEW_WSJC_DJFSB where lx = '1')");
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
			
		//	sql.append(" group by xh ");
			
			return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		}
	
	/**
	 * @��������ѧ����û���Ǽ����Ҵ���
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��8�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getXjqsCount(String xh,HashMap<String,String> condition){
		String xn = condition.get("xn");
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_gygl_new_cwxxb a left join XG_GYGL_NEW_WSJC_QSFSB b on a.lddm = b.lddm and a.qsh = b.qsh ");
		sql.append(" left join xg_gygl_new_wsjc_jcrcb c on b.guid = c.guid where c.tjzt = '1' and b.dj like '%��' and a.xh = ? ");
		params.add(xh);
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and c.xn||';'||c.xq in ( ");
			}else{
				sql.append("and c.xn in ( ");
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
	 * @���������޿β�����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��12�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getBxbkNum(String xh,HashMap<String,String> condition){
		String xn = condition.get("xn");
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from CJB where kcxz like '%����%' and bkcj is not null and xh = ? ");
		params.add(xh);
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
	 * @������Ӣ�������� ���ϳ���ѧԺ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��21�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String yygjsh(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select (case when count(1)>0 then 1 else count(1) end) num from XSDJKSB where xh=? ");
		params.add(xh);
		String xymc=new XsxxService().getXsjbxx(xh).get("xymc");
		if("����ѧԺ".equals(xymc)||"�������������ѧԺ".equals(xymc)||"����ѧԺ".equals(xymc)){
			sql.append(" and djksmc in ('CET4','CET6','CET-4','CET-6') and cj>425 ");
		}else if("�����ѧԺ".equals(xymc)){
			sql.append(" and djksmc in ('TEM8','TEM-8') and cj>=60");
		}else{
			sql.append(" and djksmc in ('CET6','CET-6') and cj>425");
		}
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @������������ƽ����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��26�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getDykpjf(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select avg(cj) pjf from cjb t ");
		sql.append(" where kcmc in ('�й����ִ�ʷ��Ҫ', '˼����������뷨�ɻ���', '���˼�������ԭ��', 'ë��˼����й���ɫ�������������ϵ����') and xh = ? ");
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
		sql.append(" group by xh ");
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjf");
	}
	
	
	/**
	 * @���������ġ���Ȼ�������๫ѡ������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��17�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String gxkms_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) ms from (select t1.*,t2.khfs khfs,t2.kclb kclb from CJB t1 left join JXFWBVIEW t2 on t1.xkkh=t2.xkkh and t1.xn||t1.xq = t2.xn||t2.xq) ");
		sql.append(" where  kclb like '%����%' or kclb like '%��Ȼ%' or kclb like '%����%' and xh=? ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "ms");
	}
	
	/**
	 * @���������ġ���Ȼ�������๫ѡ�γɼ�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��17�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String gxkcj_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select MIN(cj) cj from (select t1.*,t2.khfs khfs,t2.kclb kclb from CJB t1 left join JXFWBVIEW t2 on t1.xkkh=t2.xkkh and t1.xn||t1.xq = t2.xn||t2.xq) ");
		sql.append(" where  kclb like '%����%' or kclb like '%��Ȼ%' or kclb like '%����%' and xh=? ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/**
	 * @������TEM-4�ɼ������ϳ��и��Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��17�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String TEM4cj_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select MAX(cj) cj from XSDJKSB t where t.djksmc in ('TEM-4','TEM4') and xh=? ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/**
	 * @������TEM-8�ɼ������ϳ��и��Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��17�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String TEM8cj_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select MAX(cj) cj from XSDJKSB t where t.djksmc in ('TEM-8','TEM8') and xh=? ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/**
	 * @���������Կ�Ŀ�ɼ� �����ϳ��и��Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��17�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String kskmcj_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select MIN(cj) cj from (select t1.*,t2.khfs khfs2,t2.kclb kclb from CJB t1 left join JXFWBVIEW t2 on t1.xkkh=t2.xkkh and t1.xn||t1.xq = t2.xn||t2.xq) ");
		sql.append(" where  khfs2='����' and xh=? ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/**
	 * @�����������Ŀ�ɼ� �����ϳ��и��Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��17�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String kckmcj_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select MIN(cj) cj from (select t1.*,t2.khfs khfs2,t2.kclb kclb from CJB t1 left join JXFWBVIEW t2 on t1.xkkh=t2.xkkh and t1.xn||t1.xq = t2.xn||t2.xq) ");
		sql.append(" where  khfs2='����' and xh=? ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/**
	 * @�������ɼ�С��XX�ֵĿ�Ŀ������1�ţ����ϳ���ѧԺ���Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��20�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String kscjbcg_11527_1(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select (case when count(1)<=1 then 1 else 0 end) n  from cjb where xh=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		if (!StringUtil.isNull(tjz)){
			sql.append(" and cj<? ");
			params.add(tjz);
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	/**
	 * @�������ɼ�С��XX�ֵĿ�Ŀ������2�ţ����ϳ���ѧԺ���Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��20�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String kscjbcg_11527_2(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select (case when count(1)<=2 then 1 else 0 end) n  from cjb where xh=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		if (!StringUtil.isNull(tjz)){
			sql.append(" and cj<? ");
			params.add(tjz);
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	/**
	 * @�������ɼ�С��XX�ֵĿ�Ŀ������3�ţ����ϳ���ѧԺ���Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��20�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String kscjbcg_11527_3(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select (case when count(1)<=3 then 1 else 0 end) n  from cjb where xh=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		if (!StringUtil.isNull(tjz)){
			sql.append(" and cj<? ");
			params.add(tjz);
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	/**
	 * @�������ɼ�С��XX�ֵĿ�Ŀ������4�ţ����ϳ���ѧԺ���Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��20�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String kscjbcg_11527_4(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select (case when count(1)<=4 then 1 else 0 end) n  from cjb where xh=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		if (!StringUtil.isNull(tjz)){
			sql.append(" and cj<? ");
			params.add(tjz);
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	/**
	 * @�������ɼ�С��XX�ֵĿ�Ŀ������5�ţ����ϳ���ѧԺ���Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��20�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String kscjbcg_11527_5(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select (case when count(1)<=5 then 1 else 0 end) n  from cjb where xh=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		if (!StringUtil.isNull(tjz)){
			sql.append(" and cj<? ");
			params.add(tjz);
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	/**
	 * @���������޿γɼ��༶�����ٷֱ�_��ÿѧ���ּܷ����������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��14�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String bxkcjbjpmbfb(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select xh,max(nvl(trunc(pm/bjrs,4)*100,100)) pmbfb from ");
		sql.append("(select xn,xq,xh,rank() over(partition by xn,xq,bjdm order by zf desc) pm,bjrs from ");
		sql.append("(select a.*,b.bjdm,b.bjrs  from ");
		sql.append("(select xn,xq,xh,sum(cj) zf from cjb where kcxz like '%����%'  group by xn,xq,xh)a "); 
		sql.append("left join view_xsxx_njxyzybjrs_dqnj b on a.xh=b.xh) where 1=1 ");
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
		sql.append(")group by xh ) where xh=? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pmbfb");
	}
	
	/**
	 * @���������޿γɼ�רҵ�����ٷֱ�_��ÿѧ���ּܷ����������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��14�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String bxkcjzypmbfb(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select xh,max(nvl(trunc(pm/zyrs,4)*100,100)) pmbfb from ");
		sql.append("(select xn,xq,xh,rank() over(partition by xn,xq,zydm order by zf desc) pm,zyrs from ");
		sql.append("(select a.*,b.zydm,b.zyrs  from ");
		sql.append("(select xn,xq,xh,sum(cj) zf from cjb where kcxz like '%����%'  group by xn,xq,xh)a "); 
		sql.append("left join view_xsxx_njxyzybjrs_dqnj b on a.xh=b.xh) where 1=1 ");
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
		sql.append(")group by xh ) where xh=? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pmbfb");
	}
	
	/**
	 * @���������޿γɼ��༶�����ٷֱ�_����ѡѧ���ּܷ�������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��20�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String bxkcjbjpmbfb2(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select xh,max(nvl(trunc(pm/bjrs,4)*100,100)) pmbfb from ");
		sql.append("(select xh,rank() over(partition by bjdm order by zf desc) pm,bjrs from ");
		sql.append("(select a.*,b.bjdm,b.bjrs  from ");
		sql.append("(select xh,sum(cj) zf from cjb where kcxz like '%����%' ");
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
		sql.append(" group by xh)a "); 
		sql.append("left join view_xsxx_njxyzybjrs_dqnj b on a.xh=b.xh) where 1=1 ");
		sql.append(")group by xh ) where xh=? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pmbfb");
	}
	
	/**
	 * @���������޿γɼ�רҵ�����ٷֱ�_����ѡѧ���ּܷ�������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��20�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String bxkcjzypmbfb2(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select xh,max(nvl(trunc(pm/zyrs,4)*100,100)) pmbfb from ");
		sql.append("(select xh,rank() over(partition by zydm order by zf desc) pm,zyrs from ");
		sql.append("(select a.*,b.zydm,b.zyrs  from ");
		sql.append("(select xh,sum(cj) zf from cjb where kcxz like '%����%' ");
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
		sql.append(" group by xh)a "); 
		sql.append("left join view_xsxx_njxyzybjrs_dqnj b on a.xh=b.xh) where 1=1 ");
		
		sql.append(")group by xh ) where xh=? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pmbfb");
	}
	
	/**
	 * @�����������������֮һ����
		�� 2������ѧ����
		�� 2������ѧ���ɲ���
		�� 1������ѧ������3�Σ�һ/��/�����Ƚ�ѧ��
		�� 1��ѧ���ɲ�����3�Σ�һ/��/�����Ƚ�ѧ��
		�� 1����ѧ����1������ѧ���ɲ���
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��14�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String hjqk_12930(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) n from ( ");
		sql.append("select xh,(mc+lx)fs from ( ");
		sql.append("select xh,sum(mc) mc,sum(lx) lx from ( ");
		sql.append("select xh,decode(xmmc,'����ѧ��','3','����ѧ���ɲ�','3','0') mc ,decode(xmlxmc,'һ�Ƚ�ѧ��','1','���Ƚ�ѧ��','1','���Ƚ�ѧ��','1','0')lx  ");
		sql.append("from xg_pjpy_new_pjjgb t1 left join XG_PJPY_NEW_XMLX t2 on t1.lxdm=t2.xmlxdm ");
		sql.append(") group by xh  ");
		sql.append(") where mc>=3 and (mc+lx)>=6 and xh=? )");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	/**
	 * @���������з�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��17�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String cxf_12930(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select nvl(max(cj),'0')cj from cjb where xn||xq in (select xn||xq from xg_pjpy_new_csszb) and kcmc='���з�' and xh=? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	public String wsf(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select min(fs) zxwsf from XG_GYGL_NEW_WSJC_QSFSB a ");
		sql.append("left join XG_GYGL_NEW_CWXXB b ");
		sql.append("on a.lddm = b.lddm and a.qsh = b.qsh ");
		sql.append("left join  XG_GYGL_NEW_WSJC_JCRCB c ");
		sql.append("on a.guid = c.guid ");
		sql.append("where c.tjzt='1'and b.xh= ?");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and c.xn||';'||c.xq in ( ");
			}else{
				sql.append("and c.xn in ( ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxwsf");
	}
	
	
	public String pjwsf(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select avg(fs) pjwsf from XG_GYGL_NEW_WSJC_QSFSB a ");
		sql.append("left join XG_GYGL_NEW_CWXXB b ");
		sql.append("on a.lddm = b.lddm and a.qsh = b.qsh ");
		sql.append("left join  XG_GYGL_NEW_WSJC_JCRCB c ");
		sql.append("on a.guid = c.guid ");
		sql.append("where c.tjzt='1'and b.xh= ?");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and c.xn||';'||c.xq in ( ");
			}else{
				sql.append("and c.xn in ( ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjwsf");
	}
	
	/**
	 * @������˼��Ʒ����Ϊʵ���ɼ�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��6��5�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * String ��������
	 */
	public String sxpdxwsjcj_12647(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select nvl(min(cj),'0') cj from view_zhhcjb  where kcmc like '˼��Ʒ����Ϊʵ��%' and xh=? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/** 
	 * @����:��ȡ�Ǽ����Ҵ���(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-18 ����01:53:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getXjqsForQdbh(String xh,HashMap<String,String> condition) throws Exception{
		CsszService csService = new CsszService();
		CsszModel cm = csService.getModel();
		String xn = cm.getXn();
		String dqxn = (xn.substring(0, 4))+"-08-01";//��ȡ��ǰѧ��	
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select case when count(1)>0 then 1 else 0 end num ");
		sql.append(" from xg_gygl_new_xjqsjgb t ");
		sql.append(" where exists (select 1 from (select lddm,qsh from view_xg_gygl_new_cwxx where xh = ?) a where t.lddm = a.lddm and t.qsh = a.qsh)");
		sql.append(" and (");
		sql.append(" (to_date('"+dqxn+"','yyyy-mm-dd')");
		sql.append(" <=");
		sql.append(" to_date(to_char(t.gxsj),'yyyy-mm-dd')");
		sql.append(" and to_date(to_char(t.gxsj),'yyyy-mm-dd')");
		sql.append(" <=");
		sql.append(" add_months(to_date('"+dqxn+"','yyyy-mm-dd'),12) and t.cxzt = '0')");
		sql.append(" or ");
		sql.append(" (to_date('"+dqxn+"','yyyy-mm-dd')");
		sql.append(" <=");
		sql.append(" to_date(to_char(t.gxsj),'yyyy-mm-dd')");
		sql.append(" and to_date(to_char(t.gxsj),'yyyy-mm-dd')");
		sql.append(" <=");
		sql.append(" add_months(to_date('"+dqxn+"','yyyy-mm-dd'),12) ");
		sql.append(" and to_date(to_char(cxsj),'yyyy-mm-dd') > ");
		sql.append(" add_months(to_date('"+dqxn+"','yyyy-mm-dd'),12)");
		sql.append(" and t.cxzt = '1')");
		sql.append(" )");
	
		return DAO.getInstance().getOneRs(sql.toString(), new String[]{xh}, "num");
	}
	
	/** 
	 * @����:��ȡ���ܲ��Գɼ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-19 ����02:47:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getTncscj(String xh,HashMap<String,String> condition) {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select fs from xg_zhcp_zcfsb where xmdm = (select xmdm from xg_zhcp_zcxmb where xmmc = '���ܲ��Գɼ�')");
		sql.append("and xh= ?");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "fs");
	}
	
	/**
	 * @description	�� �񽱼���
	 * @author 		�� ������1282��
	 * @date 		��2017-11-29 ����05:05:27
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getHjjb(String xh,HashMap<String,String> condition) {
		StringBuilder sql = new StringBuilder();
		sql.append("select max(hjjb) dj from xg_xsxx_kzxx_hjqkb where xh = ?");
		return DAO.getInstance().getOneRs(sql.toString(), new String[]{xh}, "dj");
	}
	
	/**
	 * @description	�� ѧ�ּ������������칤�̴�ѧ ��
	 * @author 		�� ������1282��
	 * @date 		��2017-12-12 ����02:38:35
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getXfjdPm(String xh,HashMap<String,String> condition ) {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.xh,a.xn,b.bjdm,a.pjjd,");
		sql.append(" rank() over(partition by a.xn,b.bjdm order by a.pjjd desc) pm from");
		sql.append(" (select xn,xh,sum(jdxf) zjd,sum(xf) zxf,round(sum(jdxf)/sum(xf)) pjjd from view_xszz_xsjd_11799 group by xn,xh) a left join view_xsxxb b on a.xh = b.xh");
		sql.append(" where  ");
		
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
//			if(xn.contains(";")){
//				sql.append("and a.xn||';'||a.xq in ( ");
//			}else{
//				sql.append("and a.xn in ( ");
//			}
			sql.append(" a.xn in ( ");
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append("))where xh = ?");
			params.add(xh);
		}
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
	}
	
	/**
	 * @description	�� ѧ�ּ�������ǰ�ٷֱȣ����칤�̴�ѧ ��
	 * @author 		�� ������1282��
	 * @date 		��2017-12-12 ����03:05:22
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getXfjdPmBfb(String xh,HashMap<String,String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.xh,a.xn,b.bjdm,a.pjjd,c.rs,");
		sql.append(" rank() over(partition by a.xn,b.bjdm order by a.pjjd desc) pm from");
		sql.append(" (select xn,xh,sum(jdxf) zjd,sum(xf) zxf,round(sum(jdxf)/sum(xf)) pjjd from view_xszz_xsjd_11799 group by xn,xh) a left join view_xsxxb b on a.xh = b.xh");
		sql.append(" left join (select a.xn,b.bjdm,count(1) rs from cjb a left join view_xsxxb b on a.xh = b.xh group by a.xn,b.bjdm) c on a.xn = c.xn and b.bjdm = c.bjdm");
		sql.append(" where 1=1 ");
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
//			if(xn.contains(";")){
//				sql.append("and a.xn||';'||a.xq in ( ");
//			}else{
//				sql.append("and a.xn in ( ");
//			}
			sql.append(" and a.xn in ( ");
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(") ");
		}
		sql.append(" ) where xh = ? ");
		params.add(xh);
		
		String bjrs = dao.getOneRs("select count(1) rs from view_xsbfxx where bjdm in (select bjdm from view_xsbfxx where xh = ?) group by bjdm", new String[]{xh}, "rs");
		String bjpm = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
		//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		
		//�������������ڻ����ѧ��ʵ������ʱ�����ؽ����Ϊ0��������֤�߼�ȥ����
		if (Math.round(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	/**
	 * @description	�� ��ȡ���������ٷֱ�(���༶)
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-17 ����11:14:49
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getjdPmBfbForBj(String xh,HashMap<String,String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append( "select xh,pm from (");
		sql.append(" select a.xh,rank() over(partition by b.bjdm order by avg(a.jd) desc) pm");
		sql.append(" from cjb a");
		sql.append(" left join view_xsxxb b on a.xh = b.xh");
		sql.append(" where 1 = 1");
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.xn||';'||a.xq in ( ");
			}else{
				sql.append("and a.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(" group by a.xh,b.bjdm)");
		sql.append(" where xh = ?");
		params.add(xh);
		String bjrs = dao.getOneRs("select count(1) rs from view_xsbfxx where bjdm in (select bjdm from view_xsbfxx where xh = ?) group by bjdm", new String[]{xh}, "rs");
		String bjpm = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
		//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		
		//�������������ڻ����ѧ��ʵ������ʱ�����ؽ����Ϊ0��������֤�߼�ȥ����
		if (Math.round(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	/**
	 * @description	��  ��ȡƽ��ѧ�ּ��������ٷֱ�(���༶)
	 * @author 		�� lj��1282��
	 * @date 		��2018-5-17 ����08:35:40
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getxfjdPmBfbForBj(String xh,HashMap<String,String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append( "select xh,pm from (");
		sql.append(" select a.xh,rank() over(partition by a.bjdm order by a.xfjd desc) pm");
		sql.append(" from (select a.xh,b.bjdm,(sum((nvl(a.xf,0)*nvl(a.jd,0)))/(sum(a.xf))) xfjd from cjb a left join view_xsxxb b on a.xh = b.xh");
		//sql.append();
		sql.append(" where 1 = 1");
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.xn||';'||a.xq in ( ");
			}else{
				sql.append("and a.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(" group by a.xh,b.bjdm) a group by a.xh,a.bjdm,a.xfjd)");
		sql.append(" where xh = ?");
		params.add(xh);
		String bjrs = dao.getOneRs("select count(1) rs from view_xsbfxx where bjdm in (select bjdm from view_xsbfxx where xh = ?) group by bjdm", new String[]{xh}, "rs");
		String bjpm = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
		//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		
		//�������������ڻ����ѧ��ʵ������ʱ�����ؽ����Ϊ0��������֤�߼�ȥ����
		if (Math.round(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	/**
	 * @description	�� ���������ٷֱȣ���רҵ�꼶��
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-18 ����10:12:57
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getjdPmBfbForNjZy(String xh,HashMap<String,String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append( "select xh,pm from (");
		sql.append(" select a.xh,rank() over(partition by b.nj,b.zydm order by avg(a.jd) desc) pm");
		sql.append(" from cjb a");
		sql.append(" left join view_xsxxb b on a.xh = b.xh");
		sql.append(" where 1 = 1");
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.xn||';'||a.xq in ( ");
			}else{
				sql.append("and a.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(" group by a.xh,b.nj,b.zydm)");
		sql.append(" where xh = ?");
		params.add(xh);
		String bjrs = dao.getOneRs("select count(1) rs from view_xsbfxx where nj in (select nj from view_xsbfxx where xh = ?) and zydm in (select zydm from view_xsbfxx where xh = ?) group by nj,zydm", new String[]{xh,xh}, "rs");
		String bjpm = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
		//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		//�������������ڻ����ѧ��ʵ������ʱ�����ؽ����Ϊ0��������֤�߼�ȥ����
		if (Math.round(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	/**
	 * @description	�� ��ȡƽ��ѧ�ּ��������ٷֱ�(���꼶רҵ)
	 * @author 		�� lj��1282��
	 * @date 		��2018-5-17 ����08:54:41
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getxfjdPmBfbForNjZy(String xh,HashMap<String,String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append( "select xh,pm from (");
		sql.append(" select a.xh,rank() over(partition by a.nj,a.zydm order by a.xfjd desc) pm");
		sql.append(" from (select a.xh,b.nj,b.zydm,(sum((nvl(a.xf,0)*nvl(a.jd,0)))/(sum(a.xf))) xfjd from cjb a left join view_xsxxb b on a.xh = b.xh");
		sql.append(" where 1 = 1");
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.xn||';'||a.xq in ( ");
			}else{
				sql.append("and a.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(" group by a.xh,b.nj,b.zydm) a group by a.xh,a.nj,a.zydm,a.xfjd)");
		sql.append(" where xh = ?");
		params.add(xh);
		String bjrs = dao.getOneRs("select count(1) rs from view_xsbfxx where nj in (select nj from view_xsbfxx where xh = ?) and zydm in (select zydm from view_xsbfxx where xh = ?) group by nj,zydm", new String[]{xh,xh}, "rs");
		String bjpm = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
		//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		//�������������ڻ����ѧ��ʵ������ʱ�����ؽ����Ϊ0��������֤�߼�ȥ����
		if (Math.round(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	/**
	 * 
	 * @����: ��ȡУ�⽱ѧ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-4-8 ����09:36:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXwJxjNum(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb t");
		sql.append(" left join xg_pjpy_new_pjxmb t1 on t.xmdm = t1.xmdm");
		sql.append(" left join xg_pjpy_new_xmxz t2 on t.xzdm = t2.xmxzdm");
		sql.append(" where t.xh = ?  and t2.xmxzmc = 'У�⽱ѧ��' ");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and t.xn||';'||t.xq in ( ");
			}else{
				sql.append("and t.xn in ( ");
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "cnt");
	}
	
	/**
	 * 
	 * @����:�����ƺţ�У�⽱ѧ��У�ڽ�ѧ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-4-8 ����09:57:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getRychAndXwXnJxjNum(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb t");
		sql.append(" left join xg_pjpy_new_pjxmb t1 on t.xmdm = t1.xmdm");
		sql.append(" left join xg_pjpy_new_xmxz t2 on t.xzdm = t2.xmxzdm");
		sql.append(" where t.xh = ?  and t2.xmxzmc in('У�⽱ѧ��','У�ڽ�ѧ��','�����ƺ�') ");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and t.xn||';'||t.xq in ( ");
			}else{
				sql.append("and t.xn in ( ");
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "cnt");
	}
	
	/**
	 * 
	 * @����: У��У�ڽ�ѧ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-4-8 ����10:00:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXwXnJxjNum(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb t");
		sql.append(" left join xg_pjpy_new_pjxmb t1 on t.xmdm = t1.xmdm");
		sql.append(" left join xg_pjpy_new_xmxz t2 on t.xzdm = t2.xmxzdm");
		sql.append(" where t.xh = ?  and t2.xmxzmc in('У�⽱ѧ��','У�ڽ�ѧ��') ");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and t.xn||';'||t.xq in ( ");
			}else{
				sql.append("and t.xn in ( ");
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "cnt");
	}

	/**
	 * @description	�� ������ͳɼ��������Ƽ���ѧ��
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-28 ����11:12:07
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getLowestPerformance(String xh,HashMap<String,String> condition){

		StringBuilder sql = new StringBuilder();

		sql.append(" select min(cj) cj from cjb where xh = ? ");

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

		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}



	/**
	 *  ����ũҵ��ѧ.
	 *  ƽ������༶����
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-05-02 17:28
	 * @param
	 * @return java.lang.String
	 * @throw
	 */
	public String getPjjdBjPm(String xh,HashMap<String,String> condition){

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append( "select xh,pm from (");
		sql.append(" select a.xh,rank() over(partition by b.bjdm order by avg(a.jd) desc) pm");
		sql.append(" from cjb a");
		sql.append(" left join view_xsxxb b on a.xh = b.xh");
		sql.append(" where 1 = 1");
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.xn||';'||a.xq in ( ");
			}else{
				sql.append("and a.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(" group by a.xh,b.bjdm)");
		sql.append(" where xh = ?");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
	}

	/**
	 *  ����ũҵ��ѧ.
	 *  ƽ�������꼶��רҵ������
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-05-02 17:28
	 * @param
	 * @return java.lang.String
	 * @throw
	 */
	public String getPjjdNjPm(String xh,HashMap<String,String> condition){

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append( "select xh,pm from (");
		sql.append(" select a.xh,rank() over(partition by b.nj,b.zydm order by avg(a.jd) desc) pm");
		sql.append(" from cjb a");
		sql.append(" left join view_xsxxb b on a.xh = b.xh");
		sql.append(" where 1 = 1");
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.xn||';'||a.xq in ( ");
			}else{
				sql.append("and a.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(" group by a.xh,b.nj,b.zydm)");
		sql.append(" where xh = ?");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
	}


	/**
	 *  ����ũҵ��ѧ.
	 *  ƽ������༶���꼶����
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-05-02 17:28
	 * @param
	 * @return java.lang.String
	 * @throw
	 */
	public String getPjjdBjOrNjPm(String xh,HashMap<String,String> condition){

		String pjjdBjPm = getPjjdBjPm(xh,condition);
		String pjjdNjPm = getPjjdNjPm(xh,condition);

		if(StringUtils.isNull(pjjdBjPm)||StringUtils.isNull(pjjdNjPm)){
			return null;
		}
		return pjjdBjPm+"||"+pjjdNjPm;
	}

	/**
	 *  ����ũҵ��ѧ.
	 *  ƽ������༶���꼶�����ٷֱ�
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-05-02 17:28
	 * @param
	 * @return java.lang.String
	 * @throw
	 */
	public String getPjjdBjOrNjPmBfb(String xh,HashMap<String,String> condition){

		String pjjdBjPmBfb =  getjdPmBfbForBj(xh,condition);
		String pjjdNjPmBfb =  getjdPmBfbForNjZy(xh,condition);
		return pjjdBjPmBfb+"||"+pjjdNjPmBfb;
	}


	/**
	 * @����:�����Ŀ����һ��Ϊ����������Ϊ��
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/8/17 10:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh, condition]
	 * @return: java.lang.String
	 */
	public String getKccjymwl(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder temp = new StringBuilder();
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				temp.append("and xn||';'||xq in ( ");
			}else{
				temp.append("and xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				temp.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					temp.append(",");
				}
			}
			temp.append(")");
		}
		//�ɼ�Ϊ���Ŀ�Ŀ
		StringBuilder sql_1 = new StringBuilder();
		sql_1.append("select count(1) wyx from CJB where cj>=(select dycj from cjdzb where dydj like '%��%') ");
		sql_1.append(" and  cj<(select dycj from cjdzb where dydj like '%��%')");
		sql_1.append(" and kcxz='����'  and xh = ? ");
		sql_1.append(temp);
		String liang = dao.getOneRs(sql_1.toString(), params.toArray(new String[]{}), "wyx");

		//�ɼ�Ϊ�����µĿ�Ŀ
		StringBuilder sql_2 = new StringBuilder();
		sql_2.append("select count(1) wyx from CJB where cj<(select dycj from cjdzb where dydj like '%��%') ");
		sql_2.append(" and kcxz='����'  and xh = ? ");
		sql_2.append(temp);
		String cha = dao.getOneRs(sql_2.toString(), params.toArray(new String[]{}), "wyx");

		int count=0;
		int cha_int = Integer.valueOf(cha);
		int liang_int = Integer.valueOf(liang);
		if(cha_int == 0 && liang_int <= 1){
			count=1;
		}else{
			count=0;
		}

		return String.valueOf(count) ;
	}

	/**
	 * @����:�����Ŀ��Ϊ��
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/8/17 10:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh, condition]
	 * @return: java.lang.String
	 */
	public String getGkkccj(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) wyx from CJB where cj<(select dycj from cjdzb where dydj like '%��%')");
		sql.append(" and kcxz='����'  and xh = ? ");

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

		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "wyx");
	}
}
