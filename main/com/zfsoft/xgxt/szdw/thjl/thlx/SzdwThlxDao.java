/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ� 2014-10-8 ����11:40:22
 */  
package com.zfsoft.xgxt.szdw.thjl.thlx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼������-̸����¼ά��-̸������
 * @�๦������: 
 * @���ߣ� ��ˮ��[����:1150]
 * @ʱ�䣺 2014-10-8 ����11:40:22
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SzdwThlxDao extends SuperDAOImpl<SzdwThlxForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_SZDW_THJL_THLX");
		setKey("lxdm");
		setClass(SzdwThlxForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	/**
	 * @����:̸�������б�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 9:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [t]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	@Override
	public List<HashMap<String, String>> getPageList(SzdwThlxForm t)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.lxdm,t.lxmc ")
		   .append("from XG_SZDW_THJL_THLX t ")
		   .append("where 1=1 order by t.lxdm");
		return getPageList(t, sql.toString(), new String[]{});
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SzdwThlxForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	/** 
	 * @����: ̸�����ʹ����Ƿ����
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-8 ����11:40:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 */
	public boolean thlxIsExist(SzdwThlxForm model) {
		String sql="select count(t.lxdm) num from XG_SZDW_THJL_THLX t where t.lxdm=?";
		String num = dao.getOneRs(sql, new String[]{model.getLxdm()}, "num");
		return !num.equals("0");
	}

	public boolean thlxIsExist_10351(SzdwThlxForm model) throws Exception {
		int rs = 0;
		/*if(old)
		String sql="select count(t.lxdm) num from XG_SZDW_THJL_THLX t where (t.lxdm='";
		rs = dao.getOneRsint(sql);*/
		return rs == 0;
	}


	/**
	 * @����:����������б�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 9:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [t]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
    public List<HashMap<String,String>> getKhwtPageList(SzdwThlxForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*,t1.lxmc ssthlxmc ");
		sql.append("from XG_SZDW_THJL_KHWTLX t ");
		sql.append("left join XG_SZDW_THJL_THLX t1 on t1.lxdm=t.ssthlx ");
		sql.append("where 1=1 order by t.ssthlx,t.lxdm");
		return getPageList(t, sql.toString(), new String[]{});
    }

    /**
     * @����:���������б����ݴ�ѧ��
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/7/11 9:56
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [t]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getWtmsPageList(SzdwThlxForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*,t2.lxmc ssthlxmc,t1.lxmc sskhwtmc ");
		sql.append(" from XG_SZDW_THJL_KHWT t ");
		sql.append(" left join XG_SZDW_THJL_KHWTLX t1 on t.sskhwt=t1.lxdm ");
		sql.append(" left join XG_SZDW_THJL_THLX t2 on t2.lxdm=t1.ssthlx ");
		sql.append(" where 1=1 order by t2.lxdm,t.sskhwt,t.lxdm");
		return getPageList(t, sql.toString(), new String[]{});
    }

    /**
     * @����:�ṩ�����б�
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/7/11 16:54
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [t]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
	public List<HashMap<String,String>> getTgbzPageList(SzdwThlxForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.lxdm,t.lxmc ");
		sql.append("from XG_SZDW_THJL_TGBZ t ");
		sql.append("where 1=1 order by t.lxdm");
		return getPageList(t, sql.toString(), new String[]{});
	}

	/**
	 * @����:�������������
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 16:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: boolean
	 */
	public boolean addKhwt(SzdwThlxForm model) throws Exception {
    	String sql = "insert into XG_SZDW_THJL_KHWTLX (lxdm,lxmc,ssthlx) values(?,?,?)";
    	return dao.runUpdate(sql,new String[]{model.getLxdm(),model.getLxmc(),model.getSsthlx()});

	}

	/**
	 * @����:������������
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 16:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: boolean
	 */
	public boolean addWtms(SzdwThlxForm model) throws Exception {
		String sql = "insert into XG_SZDW_THJL_KHWT (lxdm,lxmc,sskhwt) values(?,?,?)";
		return dao.runUpdate(sql,new String[]{model.getLxdm(),model.getLxmc(),model.getSskhwt()});
	}

	/**
	 * @����:�����ṩ����
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 16:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: boolean
	 */
	public boolean addTgbz(SzdwThlxForm model) throws Exception {
		String sql = "insert into XG_SZDW_THJL_TGBZ (lxdm,lxmc) values(?,?)";
		return dao.runUpdate(sql,new String[]{model.getLxdm(),model.getLxmc()});
	}

	/**
	 * @����:����̸�����ͻ�ȡ����������б�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 16:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [ssthlx]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public HashMap<String,String> getKhwtListByThlx(String ssthlx) {
    	String sql = "select * from XG_SZDW_THJL_THLX  where lxdm=?";
    	return dao.getMapNotOut(sql,new String[]{ssthlx});
	}

	/**
	 * @����:��ȡ�����������Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 17:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getKhwtInfo(SzdwThlxForm model) {
		String sql = "select b.lxmc ssthlxmc,a.* from XG_SZDW_THJL_KHWTLX a " +
				" left join XG_SZDW_THJL_THLX b on b.lxdm=a.ssthlx where a.lxdm=?";
		return dao.getMapNotOut(sql,new String[]{model.getLxdm()});
	}
	/**
	 * @����:��ȡ����������Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 17:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getWtmsInfo(SzdwThlxForm model) {
		String sql = "select a.*,b.lxmc sskhwtmc,c.lxmc ssthlxmc from XG_SZDW_THJL_KHWT a" +
				" left join XG_SZDW_THJL_KHWTLX b on b.lxdm=a.sskhwt " +
				" left join XG_SZDW_THJL_THLX c on c.lxdm=b.ssthlx  where a.lxdm=?";
		return dao.getMapNotOut(sql,new String[]{model.getLxdm()});
	}
	/**
	 * @����:��ȡ�ṩ������Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 17:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getTgbzInfo(SzdwThlxForm model) {
		String sql = "select * from XG_SZDW_THJL_TGBZ where lxdm=?";
		return dao.getMapNotOut(sql,new String[]{model.getLxdm()});
	}

	public boolean updateKhwt(SzdwThlxForm model) throws Exception {
		String sql = "update XG_SZDW_THJL_KHWTLX set lxmc=? where lxdm=?";
		return dao.runUpdate(sql,new String[]{model.getLxmc(),model.getLxdm()});
	}
	public boolean updateWtms(SzdwThlxForm model) throws Exception {
		String sql = "update XG_SZDW_THJL_KHWT set lxmc=? where lxdm=?";
		return dao.runUpdate(sql,new String[]{model.getLxmc(),model.getLxdm()});
	}
	public boolean updateTgbz(SzdwThlxForm model) throws Exception {
		String sql = "update XG_SZDW_THJL_TGBZ set lxmc=? where lxdm=?";
		return dao.runUpdate(sql,new String[]{model.getLxmc(),model.getLxdm()});
	}

	public int getCount(SzdwThlxForm model, String tableName) throws SQLException {
		String sql = "select count(*) s from "+tableName+" where lxmc='"
				+model.getLxmc()+"' and lxdm<>'"+model.getLxdm()+"'";
		return dao.getOneRsint(sql);
	}

	public int delete(String[] lxdms, String tableName) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from "+tableName+" where lxdm in( ");
		for(int i=0;i<lxdms.length-1;i++){
			sql.append("?,");
		}
		sql.append("?)");

		return  dao.runDelete(sql.toString(),lxdms);
	}

	public List<HashMap<String,String>> getWtmsListByKhwt(String sskhwt) {
		String sql = "select * from XG_SZDW_THJL_KHWT  where sskhwt=?";
		return dao.getListNotOut(sql,new String[]{sskhwt});
	}

	public List<HashMap<String,String>> getAllTgbz() {
		String sql = "select * from XG_SZDW_THJL_TGBZ order by lxdm";
		return dao.getListNotOut(sql,new String[]{});
	}

	public List<HashMap<String,String>> getAllBzjg() {
		String sql = "select * from XG_SZDW_THJL_BZJG order by dm";
		return dao.getListNotOut(sql,new String[]{});
	}

	public String[] getBzjgmcByDms(String[] dms) throws SQLException {
		StringBuffer sql = new StringBuffer();
		if(dms.length>0){
			sql.append("select mc from XG_SZDW_THJL_BZJG where dm in (");
			for(int i = 0;i < dms.length-1;i++){
				sql.append("?,");
			}
			sql.append("?)");
		}
		return dao.getArray(sql.toString(),dms,"mc");
	}
}
