/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-10-8 ����09:27:50 
 */  
package com.zfsoft.xgxt.xtwh.ksdh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-10-8 ����09:27:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
//�޸ı�ṹ֮�󣬹�����Ӧ�ĸı䣬XG_XTWH_KSDHB�� xg_xtwh_dhtp֮������cdid����
//�޸ı�ṹ֮�󣬹�����Ӧ�ĸı䣬xg_xtwh_dhtp�� gnmkdmb֮������dyym����
public class KsdhDao extends SuperDAOImpl<KsdhForm> {

	//�����6727Ϊѧ���飬�̶�����
	private static final String XS_ZDM = "6727";
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KsdhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KsdhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(KsdhForm.class);
		super.setKey("id");
		super.setTableName("XG_XTWH_KSDHB");
	}
	
	//�������ҵ�Ӧ����ڲ˵���ѯ yxy
	public List<HashMap<String, String>> getGncdSzList(User user){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append("select distinct * from (");
		sql.append(" select  t.id,");
		sql.append(" t.yhm,");
		sql.append(" t.cdid gnmkdm,");
		sql.append(" t.czsj,");
		sql.append(" t.yhlx,");
		sql.append(" t.xssx,");
		sql.append(" t1.gnmktblj gnmklj,");
		sql.append(" t1.mkfldm,");
		sql.append(" t1.cshdhmk,");
		sql.append(" t2.dyym,");
		sql.append(" case when length(t2.gnmkmc) <= 4 then t2.gnmkmc else substr(t2.gnmkmc,0,4) || '...' end gnmkmc,");
		sql.append(" t2.gnmkmc title");
		sql.append(" from XG_XTWH_KSDHB t");
		sql.append(" join xg_xtwh_dhtp t1");
		sql.append(" on t.cdid = t1.cdid");
		sql.append(" join gnmkdmb t2");
		sql.append(" on t1.dyym = t2.dyym");
		sql.append(" where t.yhm = ?");
		paralist.add(user.getUserName());
		if("stu".equalsIgnoreCase(user.getUserType())){
			sql.append(" and t2.gnmkdm  in(select distinct gnmkdm from");
			sql.append(" yhzqxb where zdm = ? ");
			paralist.add(XS_ZDM);
		}else{
			sql.append(" and t2.gnmkdm  in(select distinct gnmkdm from ");
			sql.append(" yhqxb where  yhm = ? ");
			paralist.add(user.getUserName());
		}
		sql.append(")");
		sql.append(" and (t1.xxqx = 'public' or t1.xxqx = ?) ");
		paralist.add(Base.xxdm);
		sql.append(") order by to_number(xssx) asc");
		return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}));
	}
	
	//δ�����ҵ�Ӧ����ڲ˵�Ĭ��ֵ yxy
	public List<HashMap<String, String>> getGncdSzList_mr(User user){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String> ();
		sql.append("select distinct * from (");
		sql.append(" select distinct t.cdid gnmkdm, " +
				" case when length(t1.gnmkmc) <= 4 then t1.gnmkmc else substr(t1.gnmkmc,0,4) || '...' end gnmkmc, " +
				" t1.gnmkmc title,t.dyym, t.gnmktblj gnmklj, t.mkfldm");
		sql.append(" from xg_xtwh_dhtp t");
		sql.append(" join gnmkdmb t1");
		sql.append(" on t.dyym = t1.dyym");
		//��Ҫ�����µ�Ĭ��Ӧ�ÿ��ڴ˴����룬�������������insertInto_mrz()������һ�£��ұ������getFlcx_head()�÷�����
		sql.append(" where t.cshdhmk = '1' ");
		if("stu".equalsIgnoreCase(user.getUserType())){
			sql.append("  and t1.mklx <> 'tea'");
		}else{
			sql.append(" and t1.mklx <> 'stu'");
		}
		if("stu".equalsIgnoreCase(user.getUserType())){
			sql.append(" and t1.gnmkdm  in(select distinct gnmkdm from");
			sql.append(" yhzqxb where  zdm  = ? ");
			paralist.add(XS_ZDM);//�����6727Ϊѧ���飬�̶�����
		}else{
			sql.append(" and t1.gnmkdm  in(select distinct gnmkdm from ");
			sql.append(" yhqxb where  yhm = ? ");
			paralist.add(user.getUserName());
		}
		sql.append(")");
		sql.append(" and  (t.xxqx = 'public' or t.xxqx = ?) ");
		paralist.add(Base.xxdm);
		sql.append(")");
		sql.append(" order by gnmkdm asc");
		return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}));
	}
	
	//���û��򿪱༭Ӧ�ò˵�ʱ����ȥ��֤�Ƿ�������ֵ
	public String checkExistSz(User user){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select count(1) flag");
		sql.append(" from XG_XTWH_KSDHB t");
		sql.append(" join XG_XTWH_DHTP t1");
		sql.append(" on t.cdid = t1.cdid");
		sql.append(" join gnmkdmb t2");
		sql.append(" on t1.dyym = t2.dyym");
		sql.append(" where t.yhm = ?");
		paralist.add(user.getUserName());
		if("stu".equalsIgnoreCase(user.getUserType())){
			sql.append(" and t2.gnmkdm  in(select distinct gnmkdm from");
			sql.append(" yhzqxb where  zdm  = ? ");
			paralist.add(XS_ZDM);//�����6727Ϊѧ���飬�̶�����
		}else{
			sql.append(" and t2.gnmkdm  in(select distinct gnmkdm from ");
			sql.append(" yhqxb where  yhm = ? ");
			paralist.add(user.getUserName());
		}
		sql.append(")");
		return dao.getOneRs(sql.toString(), paralist.toArray(new String[]{}), "flag");
	}
	
	//�״α༭�ҵ�Ӧ��ʱ��������
	public boolean insertInto_mrz(User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" insert into XG_XTWH_KSDHB");
		sql.append(" select distinct * from (");
		sql.append(" select  sys_guid() id,  ? yhm,  t.cdid cdid, sysdate czsj, ? yhlx,replace(t.cdid,'0','')  xssx");
		paralist.add(user.getUserName());
		paralist.add(user.getUserType());
		sql.append(" from xg_xtwh_dhtp t");
		sql.append(" join  gnmkdmb t1");
		sql.append(" on t.dyym = t1.dyym");
		//��Ҫ�����µ�Ĭ��Ӧ�ÿ��ڴ˴����룬�������������getGncdSzList_mr()������һ��,�ұ������getFlcx_head()�÷�����
		sql.append(" where t.cshdhmk = '1'");
		//sql.append(" and nvl(t.dxq, 0) <> 0");
		if("stu".equalsIgnoreCase(user.getUserType())){ 
			sql.append("  and t1.mklx <> 'tea'");
		}else{
			sql.append(" and t1.mklx <> 'stu'");
		}
		if("stu".equalsIgnoreCase(user.getUserType())){
			sql.append(" and t1.gnmkdm  in(select distinct gnmkdm from");
			sql.append(" yhzqxb where  zdm  = ? ");
			paralist.add(XS_ZDM);//�����6727Ϊѧ���飬�̶�����
		}else{
			sql.append(" and t1.gnmkdm  in(select distinct gnmkdm from ");
			sql.append(" yhqxb where  yhm = ? ");
			paralist.add(user.getUserName());
		}
		sql.append(")");
		sql.append(" and  (t.xxqx = 'public' or t.xxqx = ?) ");
		sql.append(")");
		paralist.add(Base.xxdm);
		return dao.runUpdate(sql.toString(), paralist.toArray(new String[]{}));
	}
	
	//�����ѯͷ
	public List<HashMap<String, String>> getFlcx_head(User user){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append("  select t.mkfldm,t.mkflmc from XG_DHFLDMB t where (t.xxqx = 'public' or t.xxqx = ?)");
		paralist.add(Base.xxdm);
	    return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}) );
	}
	
	//Ӧ�ò�ѯ @gnmkmc����ģ������ @gnmkdm ���ܷ������
	public List<HashMap<String, String>> getFlcx(String usertype,String username){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append("select distinct * from (");
		sql.append(" select  t.cdid gnmkdm," +
				" case when length(t1.gnmkmc) <= 4 then t1.gnmkmc else substr(t1.gnmkmc,0,4) || '...' end gnmkmc," +
				" t1.gnmkmc title,t1.dyym, t.gnmktblj gnmklj, t.mkfldm");
		sql.append(" from xg_xtwh_dhtp  t");
		sql.append(" join gnmkdmb t1");
		sql.append(" on t.dyym = t1.dyym");
		if("stu".equalsIgnoreCase(usertype)){
			sql.append("  and t1.mklx <> 'tea'");
			sql.append(" and t1.gnmkdm  in(select distinct gnmkdm from");
			sql.append(" yhzqxb where  zdm  = ?");
			paralist.add(XS_ZDM);//�����6727Ϊѧ���飬�̶�����
			sql.append(")");
		}else{
			sql.append(" and t1.mklx <> 'stu'");
			sql.append(" and t1.gnmkdm in(select distinct gnmkdm from ");
			sql.append(" yhqxb where  yhm = ?");
			paralist.add(username);
			sql.append(")");
		}
		sql.append(" and t.cdid not in(select cdid from XG_XTWH_KSDHB where yhm = ? )");
		paralist.add(username);
		sql.append(" and  (t.xxqx = 'public' or t.xxqx = ?) ");
		paralist.add(Base.xxdm);
		sql.append(" order by t1.xssx asc");
		sql.append(")");
		sql.append(" order by gnmkdm asc");
		return dao.getListNotOut(sql.toString(),paralist.toArray(new String[]{}));
	}
	
	
    /**
     * 
     * @����:��ѯ
     * @���ߣ�yxy·[���ţ�1206]
     * @���ڣ�2015-10-19 ����11:22:17
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param usertype
     * @param gnmkmc 
     * @param gnmkdm �����ò�������Ϊ��ʱ�����࣬�ò������ڶ���ֵnull
     * @param username
     * @param htmlgnmkdm
     * @return
     * @throws Exception
     * List<HashMap<String,String>> �������� 
     * @throws
     */
    public List<HashMap<String, String>> QueryData(String usertype,String gnmkmc,String mkfldm,String username,String[] htmlgnmkdm) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> paralist = new ArrayList<String>();
    	sql.append("select distinct * from (");
    	sql.append(" select distinct t.cdid gnmkdm, " +
    			" case when length(t1.gnmkmc) <= 4 then t1.gnmkmc else substr(t1.gnmkmc,0,4) || '...'   end gnmkmc, " +
    			"  t1.gnmkmc title,t1.dyym,  t.gnmktblj gnmklj, t.mkfldm");
		sql.append(" from xg_xtwh_dhtp t");
		sql.append(" join gnmkdmb t1");
		sql.append(" on t.dyym = t1.dyym");
		if("stu".equalsIgnoreCase(usertype)){
			sql.append("  and t1.mklx <> 'tea'");
			sql.append(" and t1.gnmkdm  in(select distinct gnmkdm from");
			sql.append(" yhzqxb where  zdm  = ?");
			paralist.add(XS_ZDM);
			sql.append(")");
		}else{
			sql.append(" and t1.mklx <> 'stu'");
			sql.append(" and t1.gnmkdm in(select distinct gnmkdm from ");
			sql.append(" yhqxb where  yhm = ?");
			paralist.add(username);
			sql.append(")");
		}
		if(htmlgnmkdm != null && htmlgnmkdm.length != 0){
			sql.append(" and t.cdid not in(");
			int htmllen =  htmlgnmkdm.length;
			for (int i = 0; i < htmllen; i++) {
				sql.append("?");
				paralist.add(htmlgnmkdm[i]);
				if(i !=  htmllen-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		if(mkfldm != null && !mkfldm.trim().equals("")){
			sql.append(" and t.mkfldm = ?");
			paralist.add(mkfldm);
		}
		if(gnmkmc != null && !gnmkmc.trim().equals("")){
			sql.append(" and t1.gnmkmc like '%'|| ? || '%'");
			paralist.add(gnmkmc);
		}
		sql.append(" and  (t.xxqx = 'public' or t.xxqx = ?) ");
		paralist.add(Base.xxdm);
		sql.append(")");
		sql.append(" order by gnmkdm asc");
    	return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}));
    }
    
    public boolean del_originalData(User user) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	sql.append(" delete from XG_XTWH_KSDHB where yhm = ?");
    	return dao.runUpdate(sql.toString(), new String[]{user.getUserName()});
    }
    
    /**
     * 
     * @����:�����û�������Ȩ����֮��ɾ�����ٵ������е�����
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2015-10-23 ����02:59:54
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param yhm
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
    public boolean del_Rubbish_data(String yhm){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" delete from XG_XTWH_KSDHB where yhm = ?");
		sql.append(" and cdid not in(");
		sql.append(" select cdid from xg_xtwh_dhtp where dyym in(");
		sql.append(" select distinct dyym from gnmkdmb where gnmkdm in(");
		sql.append(" select distinct gnmkdm from yhqxb where yhm = ?");
		sql.append(")");
		sql.append(" )");
		sql.append(")");
		boolean result = true;
		try {
		    result =  dao.runUpdate(sql.toString(), new String[]{yhm,yhm});
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally{
			System.out.print(sql.toString());
			System.out.println("[yhm:"+yhm+"]");
		}
    	return result;
    }
    
    /**
     * 
     * @����:�û�����Ȩ����֮��ɾ�����ٵ������е�����
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2015-10-23 ����04:13:12
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param yhm
     * @return
     * boolean �������� 
     * @throws
     */
    public boolean del_Rubbish_data_yhz(String zdm){
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> paralist = new ArrayList<String>();
		if(!XS_ZDM.equals(zdm)){
			sql.append(" delete from XG_XTWH_KSDHB where yhm in (");
			sql.append(" select yhm from view_yhz_yhxxb where zdm =  ?)");
			sql.append("  and  cdid not in (select distinct cdid from xg_xtwh_dhtp where dyym in  (select distinct dyym from gnmkdmb where gnmkdm  in(select gnmkdm from yhzqxb where zdm =?");
			sql.append(")))");
			paralist.add(zdm);
			paralist.add(zdm);
		}else{
			sql.append(" delete from XG_XTWH_KSDHB where yhlx = 'stu' ");
			sql.append("  and  cdid not in (select distinct cdid from xg_xtwh_dhtp where dyym in  (select distinct dyym from gnmkdmb where gnmkdm  in(select gnmkdm from yhzqxb where zdm = ?");
			sql.append(")))");
			paralist.add(zdm);
		}
		boolean result = true;
		try {
		    result =  dao.runUpdate(sql.toString(), paralist.toArray(new String[]{}));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally{
			logger.info(sql.toString());
			logger.info("[yhm:"+zdm+"]");
		}
    	return result;
    }
    
    
}
