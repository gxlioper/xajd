/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-2 ����10:38:52 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import com.zfsoft.utils.StringUtil;
import common.Globals;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��֤�ۺϲ������ҵ�� 
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-5-2 ����10:38:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhcpCondition {

	
	
	/**
	 * 
	 * @����: ��ѯ�۲�ɼ�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-21 ����03:26:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZccj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			sql.append("select b.fs from xg_pjpy_new_cpmdb a left join xg_zhcp_zcfsb b on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq ");
			sql.append("where b.xh = ? and b.xmdm= ? and a.tjzt='1' ");
		}else {
			sql.append("select c.fs from xg_pjpy_new_cpmdb a left join xg_zhcp_fstjjlb b on a.xn=b.xn and a.xq=b.xq and a.bjdm=b.bjdm ");
			sql.append("left join xg_zhcp_zcfsb c on a.xh=c.xh and a.xn=c.xn and a.xq=c.xq ");
			sql.append("where b.tjzt = '1' and c.xh= ? and c.xmdm= ?  ");
		}
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		return dao.getOneRs(sql.toString(),new String[]{xh,zcxm}, "fs");
	}
	
	
	/**
	 * 
	 * @����: �༶����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-21 ����03:25:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjpm(String xh,HashMap<String,String> condition){

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select bjpm from xg_zhcp_zcfsb where xh = ? and xmdm=? ");
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		return dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "bjpm");
	}
	
	/** 
	 * @����:��ȡ�۲��ְܷ༶����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�982]
	 * @���ڣ�2017-10-12 ����10:51:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getBjpmForZhcpZf(String xh,HashMap<String,String> condition){

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select pm from view_xg_khgl_khpftj where xh = ? and xn = ? ");
		
		String xn = condition.get("ylzq");
		if (StringUtil.isNull(xn)){
			return null;
		} 		
		return dao.getOneRs(sql.toString(), new String[]{xh,xn}, "pm");
	}
	
	
	/**
	 * 
	 * @����: ����������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-21 ����04:26:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCpzpm(String xh,HashMap<String,String> condition){

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select cpzpm from xg_zhcp_zcfsb where xh = ? and xmdm=? ");
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		return dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "cpzpm");
	}
	
	
	/**
	 * 
	 * @����: �꼶רҵ����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-21 ����04:52:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getNjzypm(String xh,HashMap<String,String> condition){

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select njzypm from xg_zhcp_zcfsb where xh = ? and xmdm=? ");
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		return dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "njzypm");
	}
	
	/**
	 * 
	 * @����: �༶�������ٷֱȣ��������������룩
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-21 ����04:16:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjpmBfb(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		}
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from xg_pjpy_new_cpmdb t2 ");
		sql.append(" where t2.bjdm=(select bjdm from xg_pjpy_new_cpmdb where xh =? ");
		sql.append(" and xn||xq=(select xn || xq from xg_zhcp_zcxmb where xmdm = ? ))");
		sql.append(" and t2.xn||t2.xq=(select xn||xq  from xg_zhcp_zcxmb");
		sql.append(" where xmdm =?)");
		
		String bjrs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm,zcxm}, "c");
		String bjpm = dao.getOneRs("select bjpm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "bjpm");
		
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
	 * @����:�۲��ְܷ༶����ǰ�ٷֱ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-12 ����11:19:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getBjpmBfbForZhcpZf(String xh,HashMap<String,String> condition){
		
		String xn = condition.get("ylzq");
		if (StringUtil.isNull(xn)){
			return null;
		}
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(xh) num from view_xg_khgl_khpftj");
		sql.append(" where bjdm = (select distinct bjdm from view_xg_khgl_khpftj where xh = ?) and xn = ?");
		
		String bjrs = dao.getOneRs(sql.toString(), new String[]{xh,xn}, "num");
		String bjpm = dao.getOneRs("select pm from view_xg_khgl_khpftj where xn = ? and xh = ?", new String[]{xn,xh}, "pm");
		
		
		if (StringUtil.isNull(bjpm) || StringUtil.isNull(bjrs)){
			return null;
		}
		
		//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		//�������������ڻ����ѧ��ʵ������ʱ�����ؽ����Ϊ0��������֤�߼�ȥ����
		if (Math.round(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
		
	}
	
	
	/**
	 * 
	 * @����: �������������ٷֱȣ��������������룩
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-21 ����04:50:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCpzpmBfb(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) cpzrs from ( ");
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.bjdm,t2.cpzdm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join xg_zhcp_fstjjlb t2 on t1.xn=t2.xn and t1.xq=t2.xq and t1.bjdm=t2.bjdm");
		sql.append(" ) t1 where t1.xn||t1.xq||t1.cpzdm=(");
		sql.append(" select t1.xn||t1.xq||t2.cpzdm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join xg_zhcp_fstjjlb t2 on t1.xn=t2.xn and t1.xq=t2.xq and t1.bjdm=t2.bjdm");
		sql.append(" where t1.xh=? and t1.xn||t1.xq= (select xn || xq from xg_zhcp_zcxmb");
		sql.append(" where xmdm = ?))");
		
		String cpzrs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "cpzrs");
		String cpzpm = dao.getOneRs("select cpzpm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "cpzpm");
		//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(cpzrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(cpzpm)){
			return null;
		}
		
		//�������������ڻ����ѧ��ʵ������ʱ�����ؽ����Ϊ0��������֤�߼�ȥ����
		if (Math.round(jspm) >= Integer.valueOf(cpzpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	
	/**
	 * 
	 * @����: �꼶רҵ�����ٷֱȣ������������룩
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-21 ����05:02:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getNjzypmBfb(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from (");
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.bjdm,t2.nj,t2.zydm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm=t2.bjdm");
		sql.append(" ) t1 where t1.xn||t1.xq||t1.nj||t1.zydm=(");
		sql.append(" select t1.xn||t1.xq||t2.nj||t2.zydm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm=t2.bjdm");
		sql.append(" where t1.xh=? and t1.xn||t1.xq= (select xn || xq");
		sql.append(" from xg_zhcp_zcxmb where xmdm = ?))");
		
		String cprs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "c");
		String pm = dao.getOneRs("select njzypm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "njzypm");
		//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(cprs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(pm)){
			return null;
		}
		
		//�������������ڻ����ѧ��ʵ������ʱ�����ؽ����Ϊ0��������֤�߼�ȥ����
		if (Math.round(jspm) >= Integer.valueOf(pm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	/**
	 * @����:�۲�༶�����ٷֱȺ��۲��꼶רҵ�����ٷֱ�
	 * ʹ�ð���������ũҵ��ѧ
	 * ѧ�������й�������־���������У��������ֿ����۲�༶�������꼶רҵ�����ϲ���һ�𣬱���۲�༶���������꼶רҵ������������OR�Ĺ�ϵ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��23�� ����4:44:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjpmOrNjzypmBfb(String xh,HashMap<String,String> condition){
		String bjpmBfb = getBjpmBfb(xh,condition);
		String njzypmBfb = getNjzypmBfb(xh,condition);
		
		if(StringUtils.isNull(bjpmBfb)||StringUtils.isNull(njzypmBfb)){
			return null;
		}
		return bjpmBfb+"||"+njzypmBfb;
	}
	
	/**
	 * 
	 * @����: �༶�������ٷֱȣ���������ȥС����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-21 ����04:16:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjpmBfb2(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		}
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from xg_pjpy_new_cpmdb t2 ");
		sql.append(" where t2.bjdm=(select bjdm from xg_pjpy_new_cpmdb where xh =? ");
		sql.append(" and xn||xq=(select xn || xq from xg_zhcp_zcxmb where xmdm = ? ))");
		sql.append(" and t2.xn||t2.xq=(select xn||xq  from xg_zhcp_zcxmb");
		sql.append(" where xmdm =?)");
		
		String bjrs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm,zcxm}, "c");
		String bjpm = dao.getOneRs("select bjpm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "bjpm");
		
		//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		
		//�������������ڻ����ѧ��ʵ������ʱ�����ؽ����Ϊ0��������֤�߼�ȥ����
		if (Math.floor(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
		
	}
	
	/**
	 * 
	 * @����: �༶�������ٷֱȣ���������ȥС��,��ý���Ի����༶������һ��������㣩
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-21 ����04:16:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjpmBfb3(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		}
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from xg_pjpy_new_cpmdb t2 ");
		sql.append(" where t2.bjdm=(select bjdm from xg_pjpy_new_cpmdb where xh =? ");
		sql.append(" and xn||xq=(select xn || xq from xg_zhcp_zcxmb where xmdm = ? ))");
		sql.append(" and t2.xn||t2.xq=(select xn||xq  from xg_zhcp_zcxmb");
		sql.append(" where xmdm =?)");
		
		String bjrs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm,zcxm}, "c");
		String bjpm = dao.getOneRs("select bjpm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "bjpm");
		
		//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		
		//�������������ڻ����ѧ��ʵ������ʱ�����ؽ����Ϊ0��������֤�߼�ȥ����
		if (Math.floor(jspm) >= Integer.valueOf(bjpm)||"1".equals(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
		
	}
	
	
	/**
	 * 
	 * @����: �������������ٷֱȣ���������ȥС����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-21 ����04:50:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCpzpmBfb2(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) cpzrs from ( ");
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.bjdm,t2.cpzdm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join xg_zhcp_fstjjlb t2 on t1.xn=t2.xn and t1.xq=t2.xq and t1.bjdm=t2.bjdm");
		sql.append(" ) t1 where t1.xn||t1.xq||t1.cpzdm=(");
		sql.append(" select t1.xn||t1.xq||t2.cpzdm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join xg_zhcp_fstjjlb t2 on t1.xn=t2.xn and t1.xq=t2.xq and t1.bjdm=t2.bjdm");
		sql.append(" where t1.xh=? and t1.xn||t1.xq= (select xn || xq from xg_zhcp_zcxmb");
		sql.append(" where xmdm = ?))");
		
		String cpzrs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "cpzrs");
		String cpzpm = dao.getOneRs("select cpzpm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "cpzpm");
		//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(cpzrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(cpzpm)){
			return null;
		}
		
		//�������������ڻ����ѧ��ʵ������ʱ�����ؽ����Ϊ0��������֤�߼�ȥ����
		if (Math.floor(jspm) >= Integer.valueOf(cpzpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	
	/**
	 * 
	 * @����: �꼶רҵ�����ٷֱȣ�������ȥС����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-21 ����05:02:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getNjzypmBfb2(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from (");
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.bjdm,t2.nj,t2.zydm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm=t2.bjdm");
		sql.append(" ) t1 where t1.xn||t1.xq||t1.nj||t1.zydm=(");
		sql.append(" select t1.xn||t1.xq||t2.nj||t2.zydm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm=t2.bjdm");
		sql.append(" where t1.xh=? and t1.xn||t1.xq= (select xn || xq");
		sql.append(" from xg_zhcp_zcxmb where xmdm = ?))");
		
		String cprs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "c");
		String pm = dao.getOneRs("select njzypm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "njzypm");
		//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(cprs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(pm)){
			return null;
		}
		
		//�������������ڻ����ѧ��ʵ������ʱ�����ؽ����Ϊ0��������֤�߼�ȥ����
		if (Math.floor(jspm) >= Integer.valueOf(pm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	
	/**
	 * 
	 * @����:����ѧ��ƽ���۲�༶ǰ50%�������Ƽ�ʦ����
	 * @���ߣ��ո־� [1075]
	 * @���ڣ�2015-3-19 ����07:36:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjpmSlxqBfb(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from xg_pjpy_new_cpmdb t2 ");
		sql.append(" where t2.bjdm=(select bjdm from xg_pjpy_new_cpmdb where xh =? ");
		sql.append(" and xn||xq='2014-201501')");
		sql.append(" and t2.xn||t2.xq='2014-201501'");
		
		String bjrs = dao.getOneRs(sql.toString(), new String[]{xh}, "c");
		String bjpm = dao.getOneRs("select bjpm from xg_zhcp_tszcfsb where xn='2013-2014022014-201501' and xh = ?", new String[]{xh}, "bjpm");
		
		//���㷽ʽ��Ϊ��������*ǰ�ٷֱȣ��������������õļ��㷽ʽһ��
		double jspm = Double.valueOf(bjrs) * 0.5;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		
		//�������������ڻ����ѧ��ʵ������ʱ�����ؽ����Ϊ0��������֤�߼�ȥ����
		if (Math.floor(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return "1";
		}
		
	}
	
	
	/**
	 * 
	 * @����:TODO���ݹ�ҵ���Ի� �����۲�ȼ�
	 * @���ߣ�xucy[���ţ�754]
	 * @���ڣ�2013-9-25 ����04:25:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZcdj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_zhcp_zcfsb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and zcdj in (");
			
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
	 * @����:���Ʊ�����"У�ڽ�ѧ��"��õĴ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-6 ����08:59:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXljxj(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,case when b.count is null then 0 else b.count end count from xsxxb a left join ( ");
		sql.append("select xh, count(1) count from xg_pjpy_new_pjjgb a ");
		if(Base.xxdm.equals("10335")){
			sql.append(" left join xg_pjpy_new_xmlx b on a.lxdm = b.xmlxdm where trim(b.xmlxmc) like 'У�ڽ�ѧ��' ");
		}else{
			sql.append(" left join xg_pjpy_new_xmxz b on a.xzdm = b.xmxzdm where trim(b.xmxzmc) like 'У�ڽ�ѧ��' ");
		}
		
		List<String> params = new ArrayList<String>();
		
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
		
		sql.append(" group by xh ) b on a.xh = b.xh where a.xh = ? ");
		
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	}
	
	/**
	 * 
	 * @����:ͳ�ƻ񽱴���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-5 ����05:34:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getRmjxjcs(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,case when b.count is null then 0 else b.count end count from xsxxb a left join ( ");
		sql.append("select xh, count(1) count from xg_pjpy_new_pjjgb a ");
		sql.append("  where (trim(a.xmmc) like ");
		String[] tjdms = condition.get("tjdm").split(",");
		for (int i = 0; i < tjdms.length; i++) {
			if(i!=0){
				sql.append(" or trim(a.xmmc) like ");
			}
			sql.append("'"+tjdms[i]+"'");
		}
		sql.append(")");
		List<String> params = new ArrayList<String>();
		
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
		
		sql.append(" group by xh ) b on a.xh = b.xh where a.xh = ? ");
		
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	}
	
	public String getHdjx(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.xmmc from xg_pjpy_new_pjjgb a ");
		sql.append("  where (trim(a.xmmc) like ");
		String[] tjdms = condition.get("tjz").split(",");
		for (int i = 0; i < tjdms.length; i++) {
			if(i!=0){
				sql.append(" or trim(a.xmmc) like ");
			}
			sql.append("'"+tjdms[i]+"'");
		}
		sql.append(")");
		List<String> params = new ArrayList<String>();
		
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
		
		sql.append(" and a.xh = ? ");
		
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "xmmc");
	}

	/**
	 * 
	 * @����:���Ʊ�����"�����ƺ�"��õĴ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-6 ����09:00:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getRych(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,case when b.count is null then 0 else b.count end count from xsxxb a left join ( ");
		sql.append("select xh, count(1) count from xg_pjpy_new_pjjgb a ");
		sql.append(" left join xg_pjpy_new_xmxz b on a.xzdm = b.xmxzdm where trim(b.xmxzmc) like '�����ƺ�' ");
		
		List<String> params = new ArrayList<String>();
		
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
		
		sql.append(" group by xh ) b on a.xh = b.xh where a.xh = ? ");
		
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	}
	
	
	/**
	 * 
	 * @����: ��ý�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-13 ����01:58:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getHdjxmc(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when count(1)>0 then 1 else 0 end num from xg_pjpy_new_pjjgb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);

		String tjdm = condition.get("tjdm");
		
		if (!StringUtils.isNull(tjdm)){
			
			String[] tjzInfo = tjdm.split(",");
			sql.append(" and xmmc like '%' || ? || '%' ");
			String[] info = tjzInfo[0].split("#");
			params.add(info[0]);
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
	 * @����: ��ã�����ѧ���ɲ� or ����ѧ����������д���ģ������Ƽ�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-13 ����01:58:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getHdjxmcs(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when count(1)>0 then 1 else 0 end num from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('����ѧ��','����ѧ���ɲ�') ");
		
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
	 * @����: ��ã�����ѧ���ɲ�������ѧ����� or ����ѧ����������д���ģ������Ƽ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-9-15 ����06:14:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getHdjxmcs1(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when count(1)>0 then 1 else 0 end num from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('����ѧ��','����ѧ���ɲ�','����ѧ�����') ");
		
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
	 * @����:ָ�������ڻ�ý�ѧ��2��,����������һ�λ�ö��Ƚ�ѧ������ ��һ�ȡ����ȡ����Ƚ�ѧ��������д���ģ������Ƽ�
	 * @���ߣ��ո־� [���ţ�1075]
	 * @���ڣ�2014-9-24 ����04:49:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getHdjxmcs2(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select case when count(1)>0 then 1 else 0 end num from xg_pjpy_new_pjjgb a ");
		sql.append(" left join (select xh, count(1) cnt123 from xg_pjpy_new_pjjgb where  xmmc in ('һ�Ƚ�ѧ��','���Ƚ�ѧ��','���Ƚ�ѧ��') ");
		
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) b on a.xh = b.xh ");
		
		sql.append(" left join (select xh, count(1) cnt12 from xg_pjpy_new_pjjgb where  xmmc in ('һ�Ƚ�ѧ��','���Ƚ�ѧ��') ");
		
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
			sql.append(") ");
		}
		
		sql.append(" group by xh  ) c on a.xh = c.xh  ");
		sql.append(" where a.xh = ? and b.cnt123 > 1 and  c.cnt12 > 0");
		
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
	
	/**
	 * @����:����������У�ڼ��ѧ�������4����У�ڼ������ѧ��������ѧ���������2������ѧ���ɲ������㹲����Ա���Ÿɣ���2�������Ƽ�ʦ����ѧ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-12-11 ����08:43:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBks(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" select case when count(1)>0 then 1 else 0 end num from ( ( ");
		// ================== ��ѧ�������4 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('һ�Ƚ�ѧ��','���Ƚ�ѧ��','���Ƚ�ѧ��') ");
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 4 ");
		// ================== ��ѧ�������4 end =======================
		sql.append(" ) union all ( ");
		// ================== ����ѧ��������ѧ���������2 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('����ѧ��','����ѧ�����') ");
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 2 ");
		// ================== ����ѧ��������ѧ���������2 end =======================
		sql.append(" ) union all ( ");
		// ================== ����ѧ���ɲ������㹲����Ա���Ÿɣ���2 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('����ѧ���ɲ�','���㹲����Ա','���㹲���Ÿ�') ");
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 2 ");
		// ================== ����ѧ���ɲ������㹲����Ա���Ÿɣ���2 end =======================
		sql.append(" ) )a ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @����:ר��������У�ڼ��ѧ�������3����У�ڼ������ѧ��������ѧ���������1������ѧ���ɲ������㹲����Ա���Ÿɣ���1�������Ƽ�ʦ����ѧ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-12-11 ����08:43:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZks(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" select case when count(1)>0 then 1 else 0 end num from ( ( ");
		// ================== ��ѧ�������3 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('һ�Ƚ�ѧ��','���Ƚ�ѧ��','���Ƚ�ѧ��') ");
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 3 ");
		// ================== ��ѧ�������3 end =======================
		sql.append(" ) union all ( ");
		// ================== ����ѧ��������ѧ���������1 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('����ѧ��','����ѧ�����') ");
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 1 ");
		// ================== ����ѧ��������ѧ���������1 end =======================
		sql.append(" ) union all ( ");
		// ================== ����ѧ���ɲ������㹲����Ա���Ÿɣ���1 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('����ѧ���ɲ�','���㹲����Ա','���㹲���Ÿ�') ");
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 1 ");
		// ================== ����ѧ���ɲ������㹲����Ա���Ÿɣ���1 end =======================
		sql.append(" ) )a ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @����:��У�ڼ����ٻ�ù�һ�ζ������ϣ������ȣ���ѧ�����־��ѧ��
	 *       ���ù��������Ƚ�ѧ�𣬻��ù�һ��У�����ϡ�������Ա�����������Ÿɲ�����������(���ݹ�ҵ�����ҵ������)
	 * @���ߣ�tgj[���ţ�1075]
	 * @���ڣ�2015-5-14 ����17:43:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getYjbusHdjx(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" select case when count(1)>0 then 1 else 0 end num from ( ( ");
		// ================== ��ѧ�������4 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('һ�Ƚ�ѧ��','���Ƚ�ѧ��','��־��ѧ��') ");
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 1 ");
		// ================== ��ѧ�������4 end =======================
		sql.append(" ) union all ( ");
		// ================== ����ѧ��������ѧ���������2 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('���Ƚ�ѧ��') ");
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 2 ");
		// ================== ����ѧ��������ѧ���������2 end =======================
		sql.append(" ) union all ( ");
		// ================== ����ѧ���ɲ������㹲����Ա���Ÿɣ���2 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('������Ա','�����Ÿɲ�') ");
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 1 ");
		// ================== ����ѧ���ɲ������㹲����Ա���Ÿɣ���2 end =======================
		sql.append(" ) )a ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	/**
	 * 
	 * @����: ��ý�ѧ���������������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-11-5 ����02:42:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getHdjxjqk(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xg_pjpy_new_pjjgb where xh = ? and ( ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		String[] tjdms = condition.get("tjdm").split(","); //�����������Ϊ����Ŀ����,�༶������,�ŷָ����:һ�Ƚ�ѧ��,�صȽ�ѧ��
		
		for(int i=0; i<tjdms.length; i++){
			if(i==0){
				sql.append(" xmmc like '%' || ? || '%' ");
			}else{
				sql.append(" or xmmc like '%' || ? || '%' ");
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
	
	
	/**
	 * 
	 * @����: ѧ��רҵ���ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-30 ����03:47:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZyj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_pjpy_grzyjxx where rddjdm is not null and xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and rddjdm in (");
			
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
	 * @����: �������ѧ���ɲ�������ѧ�������㹲���Ÿɲ���������Ա ������д���� ��������Ͽҽ��ר��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-6 ����02:49:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getHjqkByCqsx(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when count(1)>0 then 1 else 0 end num from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('����ѧ��','����ѧ���ɲ�','���㹲���Ÿɲ�','������Ա') ");
		
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
	 * @����: ��ͨ�Ƽ����������ϣ�ֱ��д����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-10 ����03:04:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSdjxjys(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when count(1)>0 then 1 else 0 end num from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('���Ƚ�ѧ��','���Ƚ�ѧ��','һ�Ƚ�ѧ��') ");
		
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
	 * @����: ��������������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-14 ����03:14:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getHjqkForZD(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when count(1)>1 then 1 else 0 end num from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('����ѧ��','����ѧ���ɲ�','У��������Ա','У�������Ÿɲ�','У�����㹲����Ա') ");
		
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
	 * @�����������(�����ҵ��)
	 *  1�ι��ҽ�ѧ�� ��1�ι�����־��ѧ�� ��1��ʡ������ѧ�� ��1��ʡ������־��ѧ�� 
	 *  ��1��У�ڽ�ѧ��һ�Ƚ� ��2�� У�ڽ�ѧ����Ƚ�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��6��2�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * String ��������
	 */
	public String hjqk_yxbys_13011(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from (select xh,sum(fs) fs from ( ");
		sql.append("select xh,xmmc,decode(t.xmmc,'���ҽ�ѧ��','2','������־��ѧ��','2','ʡ������ѧ��','2','ʡ������־��ѧ��','2', ");
		sql.append("'У�ڽ�ѧ��һ�Ƚ�','2','У�ڽ�ѧ����Ƚ�','1','0')fs from XG_PJPY_NEW_PJJGB t ");
		sql.append(") group by xh) where xh=? and fs>=2 ");
		List<String> params = new ArrayList<String>();
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @�������۲��ܷ������ٷֱ�-ÿѧ��/ѧ��
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��6��5�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * String ��������
	 */
	public String zczfpmbfb(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select round(nvl(max(decode(bjpm,'1','0',bjpm)/rs*100),'100'),2) bfb from (select a.*,d.bjdm,d.rs from xg_zhcp_zcfsb a ");
		sql.append("left join xg_zhcp_zcxmb b on a.xmdm = b.xmdm ");
		sql.append("left join XG_PJPY_NEW_CPMDB c on a.xh=c.xh and a.xn=c.xn and a.xq=c.xq ");
		sql.append("left join (select xn,xq,bjdm,count(xh) rs from XG_PJPY_NEW_CPMDB group by xn,xq,bjdm) d on c.bjdm=d.bjdm and c.xn=d.xn and c.xq=d.xq ");
		sql.append("where b.fjdm='N' and a.bjpm is not null and a.xh=? )t ");
		return DAO.getInstance().getOneRs(sql.toString(), new String[]{xh}, "bfb");
	}

	/**
	 *  ����ũҵ��ѧ.
	 *  ʡ��˫��������֮һ������������ϲ�Ϊһ������
	 *  ��ù���������У��������ѧ�����ƺţ�����һ��У��������ѧ�����ƺ����ڲ�ͬ����ڻ�ù�У������ѧ���ɲ�������������Ա�����ƺţ�
	 *  ����һ�Ρ�����ѧ���ɲ����һ�����ΰ���ũҵ��ѧ����ѧ����ѧ��
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-05-07 08:46
	 * @param xh
	 * @param condition
	 * @return java.lang.String
	 * @throw
	 */
	public String getSjsys(String xh,HashMap<String,String> condition){

		String sql_xjshxsList = "select xh,xn,xq,xmdm,xmmc from xg_pjpy_new_pjjgb where xh = ? and xmmc = 'У������ѧ��'";
		List<HashMap<String,String>> xjshxsList = DAO.getInstance().getListNotOut(sql_xjshxsList,new String[]{xh});

		int xjshxsListSize = xjshxsList.size();
		if(xjshxsListSize >= 2){//���2������У������ѧ��
			return "1";
		}else if(xjshxsListSize == 1){//���1��У������ѧ��
			String sql_ysxsgbhtyCount = "select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('����ѧ���ɲ�','������Ա') and xn != ?";
			String ysxsgbhtyCount = DAO.getInstance().getOneRs(sql_ysxsgbhtyCount,new String[]{xh,xjshxsList.get(0).get("xn")},"cnt");
			if(Integer.valueOf(ysxsgbhtyCount)>0){//�ڲ�ͬѧ���ù�����ѧ���ɲ���������Ա
				return "1";
			}else {
				return "0";
			}
		}else{
			String sql_ysxsgbCount = "select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc = '����ѧ���ɲ�' ";
			String ysxsgbCount = DAO.getInstance().getOneRs(sql_ysxsgbCount,new String[]{xh},"cnt");
			if(Integer.valueOf(ysxsgbCount)>0){//��ù�����ѧ���ɲ�
				String sql_bksjxjCount = "select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc = '����ũҵ��ѧ����ѧ����ѧ��' ";
				String bksjxjCount = DAO.getInstance().getOneRs(sql_bksjxjCount,new String[]{xh},"cnt");
				if(Integer.valueOf(bksjxjCount)>=3){//���>=3�ΰ���ũҵ��ѧ����ѧ����ѧ��
					return "1";
				}else {
					return "0";
				}
			}else {
				return "0";
			}
		}
	}

	/**
	 *  ����ũҵ��ѧ.
	 *  У��˫��������֮һ������������ϲ�Ϊһ������
	 *  ��ù�У��������ѧ�����ƺţ��������ΰ���ũҵ��ѧ����ѧ����ѧ��
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-05-07 08:46
	 * @param xh
	 * @param condition
	 * @return java.lang.String
	 * @throw
	 */
	public String getXjsys(String xh,HashMap<String,String> condition){

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from (select xh,sum(fs) fs from ( ");
		sql.append("select xh,xmmc,decode(t.xmmc,'У������ѧ��','2', ");
		sql.append("'����ũҵ��ѧ����ѧ����ѧ��','1','0')fs from XG_PJPY_NEW_PJJGB t ");
		sql.append(") group by xh) where xh=? and fs>=2 ");

		return DAO.getInstance().getOneRs(sql.toString(),new String[]{xh}, "num");
	}
	
}
