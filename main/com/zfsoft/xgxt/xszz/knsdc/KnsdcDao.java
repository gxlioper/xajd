package com.zfsoft.xgxt.xszz.knsdc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import xgxt.form.User;


import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;




/**
 * ����ά��DAO
 * @author maxh
 * 2013-4-17
 */
public class KnsdcDao extends SuperDAOImpl<KnsdcForm> {

	/**
	 * ��ͨ��ѯ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPageList(KnsdcForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select dcdm,dcmc,xmsm,sfqy from xg_xszz_new_kndcdmb where 1=1");
		
		if (!StringUtil.isNull(model.getDcdm())){
			params.add(model.getDcdm());
			sql.append(" and dcdm = ? ");
		}
		
		if (!StringUtil.isNull(model.getDcmc())){
			params.add(model.getDcmc());
			sql.append(" and dcmc like '%'||?||'%'");
		}
		//sql.append(" order by dcdm");
		
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}

	public List<HashMap<String, String>> knyyList(KnsdcForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select * from xg_xszz_new_knyydmb where 1=1");
		if (!StringUtil.isNull(model.getYymc())) {
			params.add(model.getYymc());
			sql.append(" and yymc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}
	/**
	 * ��ȡ ��󵵴δ��룬��������
	 * @return
	 * @throws SQLException
	 */
	public int getMaxDcdm() throws SQLException{
		
		String sql = "select nvl(max(dcdm),1) dcdm from xg_xszz_new_kndcdmb";
		
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @����:���Ӳ���Ψһ����֤
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-25 ����05:21:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� wanghj����2014-1-16����String->int
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public int checkExistForSave(KnsdcForm model) {
		StringBuilder sql = new StringBuilder("select count(1) num from xg_xszz_new_kndcdmb where dcmc=?");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getDcmc()}, "num");
		return Integer.parseInt(num);
		
	}
	/**
	 * 
	 * @����:�޸Ĳ���Ψһ����֤
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-25 ����05:21:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����wanghj����2014-1-16����String->int��ֻ��֤���Ƹ�Ϊ��������ƶ���Ҫ��֤�Ƿ���ڡ�
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public int checkExistForUpdate(KnsdcForm model) {
		String dcdmSql="select count(1) num from xg_xszz_new_kndcdmb where dcdm=?";
		String dcdmNum=dao.getOneRs(dcdmSql.toString(), new String[]{model.getDcdm()}, "num");
		
		String dcmcSql="select count(1) num from xg_xszz_new_kndcdmb where dcmc=? and dcdm <> ?";
		String dcmcNum=dao.getOneRs(dcmcSql.toString(), new String[]{model.getDcmc(),model.getDcdm()}, "num");
		
		int num = Integer.parseInt(dcdmNum)+Integer.parseInt(dcmcNum);
		return num;
		
	}
	public boolean checkKnyy(KnsdcForm model) {
		StringBuffer  sql= new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select count(1) num from xg_xszz_new_knyydmb where 1=1 and yymc=?");
		params.add(model.getYymc());
		if(null!=model.getYydm()){
			sql.append(" and yydm<>?");
			params.add(model.getYydm());
		}
	String num= dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	return Integer.parseInt(num)>0;
	}
	public boolean checkKnyyUsed(String[] values) throws Exception {
		boolean used=false;
		for (int i = 0; i < values.length; i++) {
			StringBuilder sql = new StringBuilder();
			sql.append("select count(1) num from (select xh,ylzd5 from XG_XSZZ_NEW_KNSJGB union all select xh,ylzd5 from XG_XSZZ_NEW_KNSSQB) where ylzd5 like '%'||?||'%'");
			String num=dao.getOneRs(sql.toString(),new String[] {values[i]} , "num");
			if(Integer.parseInt(num)>0){
				used=true;
				break;
			}
		}
		return used;
	}
	public int deleteKnyyInfo(String[] values) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xszz_new_knyydmb where  yydm in(");
		for (int i = 0; i < values.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(values[i]);
		}
		sql.append(" ) ");
		return dao.runDelete(sql.toString(), params.toArray(new String[]{}));
		
	}
	public boolean addKnyy(KnsdcForm model) throws Exception {
		String sql = "insert into xg_xszz_new_knyydmb(yymc,xh) values(?,?)";
		return dao.runUpdate(sql, new String[] { model.getYymc(),model.getXh()});
	}
	public boolean updateKnyy(KnsdcForm model) throws Exception {
		String sql = "update xg_xszz_new_knyydmb set yymc=?,xh=? where yydm=?";
		return dao.runUpdate(sql, new String[] { model.getYymc(),model.getXh(),model.getYydm()});
	}
	
	public KnsdcForm getKnyy(KnsdcForm model) throws Exception {
		String sql = "select * from  xg_xszz_new_knyydmb where yydm=?";
		return getModel(model,sql, new String[]{model.getYydm()});
	}
	
	public List<HashMap<String, String>> getKnyyList() throws Exception {
		String sql = "select * from  xg_xszz_new_knyydmb order by to_number(xh)";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	

	@Override
	public List<HashMap<String, String>> getPageList(KnsdcForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_kndcdmb");
		super.setKey("dcdm");

	}
	/**
	 * 
	 * @����:����������list
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-22 ����02:11:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsdcList() {
		String sql = "select dcdm ,dcmc from xg_xszz_new_kndcdmb where sfqy ='��' or sfqy is null order by dcdm";
		return dao.getList(sql, new String[]{},new String[]{"dcdm","dcmc"});
	}
	/**
	 * 
	 * @����:�޳��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-18 ����09:45:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getWczzList() {
		String sql = "select distinct wczzje zzjedm, wczzje zzjemc from xg_xszz_wczzjeb order by wczzje";
		return dao.getList(sql, new String[]{},new String[]{"zzjedm","zzjemc"});
	}
	
	
	
	/**
	 * 
	 * @����:����������������Ƿ�������
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-24 ����08:48:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public String[]  checkDcForKnsjg( String value) throws Exception{
			StringBuilder sql = new StringBuilder(" select distinct  b.dcmc from xg_xszz_new_knsjgb a,xg_xszz_new_kndcdmb b where  a.rddc= to_char(b.dcdm) and a.rddc in(" +value +")");
			String[] dcmc=dao.getRs(sql.toString(), new String[]{}, "dcmc");
		return dcmc;
	}
	/**
	 * 
	 * @����:����������������Ƿ�������
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-24 ����10:09:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[]  checkDcForKnssh( String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct  b.dcmc from xg_xszz_new_knsshb  a,xg_xszz_new_kndcdmb b where  a.rddc= to_char(b.dcdm) and a.shzt<>'0' and a.rddc in("+value+")");
		String[] dcmc=dao.getRs(sql.toString(), new String[]{}, "dcmc");
	    return dcmc;
   }
	
	/**
	 * 
	 * @����:�����������-����ѧ��ѧ�ڣ���ѯ�϶�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsjgRddc(String xn,String xq) throws Exception {
		String[] inputValue = null;

		StringBuilder sb = new StringBuilder();
		sb.append("select distinct rddc dm,(select dcmc from xg_xszz_new_kndcdmb c where a.rddc=c.dcdm) mc  ");
		sb.append(" from xg_xszz_new_knsjgb a  ");
		sb.append("  where xn=? ");
		
		if(xq != null && !xq.trim().equals("")){
			sb.append("  and xq=? ");
			inputValue = new String[2];
			inputValue[0] = xn;
			inputValue[1] = xq;
		}else{
			inputValue = new String[1];
			inputValue[0] = xn;
		}
		
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	
	public boolean updateKnsdcInfo(String dcdm,KnsdcForm form) throws Exception{
		
		String[] input = {form.getDcdm(),form.getDcmc(),form.getXmsm(),dcdm};
		String sql = "update xg_xszz_new_kndcdmb set dcdm=?,dcmc=?,xmsm=? where dcdm=?";
		
		return dao.runUpdate(sql, input);
	}

}
