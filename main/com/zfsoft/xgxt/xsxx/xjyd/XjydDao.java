package com.zfsoft.xgxt.xsxx.xjyd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.exception.SystemException;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ѧ���춯
 * @���ߣ� Qilm[����:964]
 * @ʱ�䣺 2013-11-28 ����09:40:48 
 * @�汾�� V5.12.20
 */
public class XjydDao extends SuperDAOImpl<XjydForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XjydForm t)
			throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XjydForm t, User user)
			throws Exception {
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("dm_xjlb");
		super.setKey("xjlbdm");
		super.setClass(XjydForm.class);
		
	}


	@Override
	public XjydForm getModel(XjydForm model) throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT xjlbdm,  ");
		sql.append("        xjlbmc,  ");
		sql.append("        sfyxj,  ");
		sql.append("        case  ");
		sql.append("          when sfyxj ='1' then '��ѧ��' ");
		sql.append("        else '��ѧ��' ");
		sql.append("        end sfyxjmc, ");
		sql.append("        sfzx, ");
		sql.append("        case  ");
		sql.append("          when sfzx ='1' then '����У' ");
		sql.append("        else '��У' ");
		sql.append("        end sfzxmc ");
		sql.append("   FROM DM_XJLB ");
		sql.append("   WHERE 1=1 ");

		if (!StringUtil.isNull(model.getXjlbmc())) {
			params.add(model.getXjlbmc());
			sql.append(" and xjlbmc = ? ");
		}
		
		if (!StringUtil.isNull(model.getXjlbdm())) {
			params.add(model.getXjlbdm());
			sql.append(" and xjlbdm = ? ");
		}
		return this.getModel(model, sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception  
	 * @����:ѧ���춯����б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����10:10:38
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXjlbPageList(XjydForm model) throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT xjlbdm,  ");
		sql.append("        xjlbmc,  ");
		sql.append("        sfyxj,  ");
		sql.append("        sfnz,   ");
		sql.append("        case  ");
		sql.append("          when sfyxj ='1' then '��ѧ��' ");
		sql.append("        else '��ѧ��' ");
		sql.append("        end sfyxjmc, ");
		sql.append("        sfzx, ");
		sql.append("        case  ");
		sql.append("          when sfzx ='1' then '����У' ");
		sql.append("        else '��У' ");
		sql.append("        end sfzxmc ");
		sql.append("   FROM DM_XJLB ");
		sql.append("   WHERE 1=1 ");

		if (!StringUtil.isNull(model.getXjlbmc())) {
			params.add(model.getXjlbmc());
			sql.append(" and xjlbmc like '%'||?||'%'");
		}
		
		if (!StringUtil.isNull(model.getXjlbdm())) {
			params.add(model.getXjlbdm());
			sql.append(" and xjlbdm = ? ");
		}
		return getPageList(model, sql.toString(),
				params.toArray(new String[] {}));
	}

	/**
	 * @����:����ѧ���춯���
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����02:21:17
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveXjydlb(XjydForm model) {
		
		//����SQL
		StringBuilder sql=new StringBuilder();
		sql.append(" insert into ");
		sql.append(" DM_XJLB(xjlbdm,Xjlbmc,Sfyxj,Sfzx)");
		sql.append(" values ");
		sql.append(" ( ?,?,?,? )");
		
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql.toString(), 
					new String[] {model.getXjlbdm(),
						model.getXjlbmc(),
						model.getSfyxj(),
						model.getSfzx()});
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * @����:ɾ��ѧ���춯���
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����02:21:17
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public int delXjydlb(String values) {
		
		String[] valuess = values.split(",");
		
		//����SQL
		StringBuilder sql=new StringBuilder();
		int num = 0;
		sql.append(" delete from ");
		sql.append(" DM_XJLB ");
		sql.append(" where 1 =1 ");

		if (!StringUtil.isNull(values)) {
			sql.append(" and xjlbdm in ( ");
			for(int i=0; i<valuess.length; i++){
				if(i==0){
					sql.append("'"+valuess[i]+"'");
				}else{
					sql.append(",'"+valuess[i]+"'");
				}
			}
			sql.append(" )");
		}		
		
		try {
			num = dao.runDelete(sql.toString(), new String[] {});
			
		} catch (Exception e) {
			e.printStackTrace();
			num = 0;
		}
		return num;
	}

	/**
	 * @����:ѧ���춯�����������б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����03:48:08
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception  
	 */
	public List<HashMap<String, String>> getXjlbShpzPageList(XjydForm model) throws Exception {

		StringBuilder sql = new StringBuilder();

		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		sql.append(" select * from ( ");
		sql.append("   select t1.*,  ");
		sql.append("        t2.xjlbmc,  ");
		sql.append("        t2.sfyxj,  ");
		sql.append("        t2.sfzx,  ");
		sql.append("        case  ");
		sql.append("          when t2.sfyxj ='1' then '��ѧ��' ");
		sql.append("        else '��ѧ��' ");
		sql.append("        end sfyxjmc, ");
		sql.append("        case  ");
		sql.append("          when t2.sfzx ='1' then '����У' ");
		sql.append("        else '��У' ");
		sql.append("        end sfzxmc, ");
		sql.append("        case  ");
		sql.append("          when t1.sfksq ='0' then '����' ");
		sql.append("        else '�ر�' ");
		sql.append("        end sfksqmc, ");		
		sql.append(" 		case ");
		sql.append("   			when t1.sfksq <> '0' then '' ");
		sql.append("   			when t1.sqjssj is not null then t1.sqkssj || ' �� '|| t1.sqjssj ");
		sql.append("   			when t1.sqkssj is not null then t1.sqkssj||  ' �� ' ");
		sql.append("   			else '' end sqqzsj, ");		
		sql.append("        decode(e.lcxx, '', '�������',e.mc || '��' || replace(e.lcxx, ';', '->')) shlcmc, ");
		sql.append("        case  ");
		sql.append("          when t1.SFTJBJ ='0' then '��Ҫ' ");
		sql.append("        else '����Ҫ' ");
		sql.append("        end sftjbjmc, ");
		sql.append("        case  ");
		sql.append("          when t1.LRQZSJ ='1' then '��Ҫ' ");
		sql.append("        else '����Ҫ' ");
		sql.append("        end lrqzsjmc ");
		sql.append("   from xg_xsxx_xjyd_xjydlbb t1 ");
		sql.append("   left join dm_xjlb t2 ");
		sql.append("        on t1.xjlbdm = t2.xjlbdm ");
		sql.append("   left join (select splc, mc, lcxx ");
		sql.append("        from (select splc, ");
		sql.append("                     a.mc, ");
		sql.append("                     to_char(WM_CONCAT(c.mc) ");
		sql.append("                             over(partition by splc order by xh)) lcxx, ");
		sql.append("                     xh, ");
		sql.append("                     row_number() over(partition by splc order by xh desc) as ddd ");
		sql.append("              from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c ");
		sql.append("              where djlx = 'xsxx' ");
		sql.append("                 and a.id = b.splc ");
		
		sql.append("                and b.spgw = c.id) b ");
		sql.append("         where ddd = 1) e ");
		sql.append("        on t1.shlcid = e.splc ");
		sql.append(" ) a where 1=1 ");
		
		if (!StringUtil.isNull(model.getXjlbdm())) {
			sql.append(" and xjlbdm = '"+ model.getXjlbdm() +"'");
		}
		if (!StringUtil.isNull(model.getXjlbmc())) {
			sql.append(" and xjlbmc like '%"+ model.getXjlbmc() +"%' ");
		}
		sql.append(searchTj);		

		
		return getPageList(model, sql.toString(),inputV);
	}

	/** 
	 * @����:ѧ���춯����б�ȫ��/���趨��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����04:46:37
	 * @param flg : 0:ȫ���춯�б�1����������춯��2�������ӵ��춯�б�
	 * @param ydlbdm : �˻ص��춯������
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXjlbList(String flg, String ydlbdm) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.xjlbdm, t1.xjlbmc, t1.sfyxj, t1.sfzx ");
		sql.append("   from dm_xjlb t1 ");
		if("1".equals(flg)){
			sql.append("  inner join XG_XSXX_XJYD_XJYDLBB t2 ");
			sql.append("     on t1.xjlbdm = t2.xjlbdm ");
			sql.append("    and t2.sfksq = '0' ");
			sql.append("    and ((t2.SQKSSJ is not null and t2.SQKSSJ <= to_char(sysdate,'yyyy-mm-dd')) or t2.SQKSSJ is null) ");
			sql.append("    and ((t2.SQJSSJ is not null and t2.SQJSSJ >= to_char(sysdate,'yyyy-mm-dd')) or t2.SQJSSJ is null) ");
			
			// �˻��춯���������
			if(StringUtils.isNotEmpty(ydlbdm)){
				sql.append(" union ");
				sql.append("  select xjlbdm, xjlbmc, sfyxj, sfzx ");
				sql.append("   from dm_xjlb  ");
				sql.append("     where xjlbdm = '" + ydlbdm + "' ");
			}
		}else if("2".equals(flg)){
			sql.append("    where  not exists (select xjlbdm from XG_XSXX_XJYD_XJYDLBB t2 where t1.xjlbdm = t2.xjlbdm) ");		
		}
		sql.append("   order by xjlbdm ");		
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/** 
	 * @����:����ѧ���춯����������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-29 ����08:40:06
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveXjydlbShpz(XjydForm model) {

		
		//����SQL
		StringBuilder sql=new StringBuilder();
		sql.append(" insert into ");
		sql.append(" XG_XSXX_XJYD_XJYDLBB (xjlbdm , shlcid , sftjbj , sfksq , sqkssj , sqjssj , lrqzsj, xzsfkq, xxsfkq)");
		sql.append(" values ");
		sql.append(" ( ?,?,?,?,?,?,?,?,? )");
		
		boolean flag = false;
		if(!"0".equals(model.getSfksq())){
			model.setSqkssj("");
			model.setSqjssj("");
		}
		try {
			flag = dao.runUpdate(sql.toString(), 
					new String[] {model.getXjlbdm(),
						model.getShlcid(),
						model.getSftjbj(),
						model.getSfksq(),
						model.getSqkssj(),
						model.getSqjssj(),
						model.getLrqzsj(),
						model.getXzsfkq(),
						model.getXxsfkq()});
			
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 
	 * @����:ѧ���춯����������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-29 ����08:47:53
	 * @param myForm
	 * @return
	 * XjydForm �������� 
	 * @throws Exception  
	 */
	public XjydForm getModelShpz(XjydForm model) throws Exception {
		

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT t2.xjlbdm,  ");
		sql.append("   t2.xjlbmc,  ");
		sql.append("   t2.sfyxj,  ");
		sql.append("   case  ");
		sql.append("     when  t2.sfyxj ='1' then '��ѧ��' ");
		sql.append("   else '��ѧ��' ");
		sql.append("   end sfyxjmc, ");
		sql.append("   t2.sfzx, ");
		sql.append("   case  ");
		sql.append("     when t2.sfzx ='1' then '����У' ");
		sql.append("   else '��У' ");
		sql.append("   end sfzxmc, ");
		sql.append("   t1.shlcid, ");
		sql.append("   t1.sftjbj, ");
		sql.append("   t1.sfksq, ");
		sql.append("   t1.sqkssj, ");
		sql.append("   t1.sqjssj, ");
		sql.append("   t1.lrqzsj ");
		if("10511".equals(Base.xxdm)) {
			sql.append(",t1.xzsfkq, t1.xxsfkq ");
		}
		sql.append(" FROM DM_XJLB t2 ");
		sql.append(" left join XG_XSXX_XJYD_XJYDLBB t1 ");
		sql.append(" on t1.xjlbdm = t2.xjlbdm ");
		sql.append(" WHERE 1=1 ");
				
		if (!StringUtil.isNull(model.getXjlbdm())) {
			params.add(model.getXjlbdm());
			sql.append(" and t2.xjlbdm = ? ");
		}
		return this.getModel(model, sql.toString(), params.toArray(new String[]{}));
	}

	/** 
	 * @����:�޸�ѧ���춯����������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-29 ����08:55:36
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updXjydlbShpz(XjydForm model) {
		
		//����SQL
		StringBuilder sql=new StringBuilder();
		sql.append(" update ");
		sql.append(" XG_XSXX_XJYD_XJYDLBB ");
		sql.append(" set ");
		sql.append(" SHLCID = ? , ");
		sql.append(" SFTJBJ = ? , ");
		sql.append(" SFKSQ = ? , ");
		sql.append(" SQKSSJ = ? , ");
		sql.append(" SQJSSJ = ? ,  ");
		sql.append(" lrqzsj = ? , ");
		sql.append(" XZSFKQ = ? , ");
		sql.append(" XXSFKQ = ? ");
		sql.append(" where ");
		sql.append(" XJLBDM = ?  ");

		if(!"0".equals(model.getSfksq())){
			model.setSqkssj("");
			model.setSqjssj("");
		}
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql.toString(), 
					new String[] {model.getShlcid(),
						model.getSftjbj(),
						model.getSfksq(),
						model.getSqkssj(),
						model.getSqjssj(),
						model.getLrqzsj(),
						model.getXzsfkq(),
						model.getXxsfkq(),
						model.getXjlbdm()});
			
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/** 
	 * @����:ɾ��ѧ���춯����������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-29 ����08:59:07
	 * @param values
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int delXjydlbShpz(String values) {

		//����SQL
		StringBuilder sql=new StringBuilder();
		int num = 0;
		sql.append(" delete from ");
		sql.append(" XG_XSXX_XJYD_XJYDLBB ");
		sql.append(" where 1 =1 ");

		if (!StringUtil.isNull(values)) {
			sql.append(" and xjlbdm in ("+ values + ")");
		}		
		
		try {
			num = dao.runDelete(sql.toString(), new String[] {});
			
		} catch (Exception e) {
			e.printStackTrace();
			num = 0;
		}
		return num;
	}
	
	/**
	 * 
	 * @����:����ѧ�������뷵��ѧ�������Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-30 ����03:24:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xjlbdm
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getXjydInfor(String xjlbdm){
		
		if(!StringUtil.isNull(xjlbdm)){
					
			StringBuilder sql = new StringBuilder();
			
			sql.append(" select * from XG_XSXX_XJYD_XJYDLBB where xjlbdm = ? ");
			
			return dao.getMapNotOut(sql.toString(), new String []{xjlbdm});
		}
		
		logger.debug("ѧ��������Ϊ�գ�");
		throw new SystemException("ѧ��������Ϊ�գ�");
		
		
	}
	
	/**
	 * 
	 * @����:�鿴��������ѧ����Ϣ�����Ƿ�ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-29 ����04:46:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lbdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String lbdmExistXsxxb (String lbdm){
		
		String sql = "select count(1) count from xsxxb a where xjlbdm = ? or xjlb = ? ";
		
		return dao.getOneRs(sql, new String []{lbdm,lbdm}, "count");
		
	}
	
	
	/**
	 * 
	 * @����:�鿴���������춯��������Ƿ�ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-29 ����05:05:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lbdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String lbdmExistXjydjgb (String lbdm){
		
		String sql = "select count(1) count from xg_xsxx_xjydjgb a where a.ydqxjlb = ? or a.ydlbdm = ? ";
		
		return dao.getOneRs(sql, new String []{lbdm,lbdm}, "count");
		
	}
	
	
	/**
	 * 
	 * @����:�鿴���������춯���뵱���Ƿ�ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-29 ����05:17:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lbdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String lbdmExistXjydsqb (String lbdm){
		
		String sql = "select count(1) count from xg_xsxx_xjydsqb a where ydlbdm = ? ";
		
		return dao.getOneRs(sql, new String []{lbdm}, "count");
		
	}
	
	
	/**
	 * 
	 * @����:��ѯ��������Ƿ��ѱ�ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-9-9 ����09:34:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lbmc
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String lbmcExistLbmc(String lbmc){
		
		String sql = "select count(1) count from dm_xjlb where xjlbmc = ?";
		
		return dao.getOneRs(sql, new String []{lbmc}, "count");
	}

	/** 
	 * @����:��ѯ�������Ƿ��ѱ�ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-9-10 ����09:25:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lbdm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String lbdmExists(String lbdm) {
		
		String sql = "select count(1) count from dm_xjlb where xjlbdm = ? ";

		return dao.getOneRs(sql, new String[]{lbdm}, "count");
	}
	
	
}
