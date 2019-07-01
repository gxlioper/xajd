package com.zfsoft.xgxt.xszz.knsrdnew.knsrdzb;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.ListUtils;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����������ģ��
 * @�๦������: ѧ������2013��--�������϶� 
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-22 ����09:03:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdzbDao extends SuperDAOImpl<KnsrdzbForm> {

	

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KnsrdzbForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KnsrdzbForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_knsrd_knzbb");
		super.setKey("zbid");
		
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ������ָ�꼯��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����09:55:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnszbPageList(KnsrdzbForm model)throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select zbid,zbmc,  ");
		sql.append(" ( case when qyzt=0  then 'ͣ��' else '����' end ) qyztmc,qyzt from xg_xszz_knsrd_knzbb a where 1 = 1 ");
		return getPageList(model, sql.toString(),params.toArray(new String[] {}));
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-15 ����01:18:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbdm
	 * @param zbmc
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkZbmc(String zbdm,String zbmc){
		StringBuilder sql = new StringBuilder();
		List<String> param=new ArrayList<String>();
		sql.append("select * from xg_xszz_knsrd_knzbb where zbmc=? ");
		param.add(zbmc);
		if(StringUtils.isNotNull(zbdm)){
			sql.append("and zbid <> ?");
			param.add(zbdm);
		}
		return ListUtils.getListSize(dao.getListNotOut(sql.toString(),param.toArray(new String[]{})))<=0;
	}
	/**
	 * 
	 * @����:TODO(��������ָ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-21 ����01:27:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws
	 */
	public boolean addKnsrdzb(KnsrdzbForm form){
		String sql = "insert into xg_xszz_knsrd_knzbb(zbid,zbmc,qyzt) values(?,?,?)";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getZbid(),form.getZbmc(),form.getQyzt()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:TODO(����ָ�����Ա�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-21 ����01:27:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws
	 */
	public boolean addKnsrdzbsx(KnsrdzbsxForm form){
		boolean flag = false;
		String sql = "insert into xg_xszz_knsrd_zbsxb(sxid,zbid,sxmc,qzbl,xssx) values(?,?,?,?,?)";
		try {
			flag = dao.runUpdate(sql, new String[]{form.getSxid(),form.getZbid(),form.getSxmc(),form.getQzbl(),form.getXssx()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 
	 * @����:TODO(����ָ�����ݱ�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-21 ����01:27:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws
	 */
	public boolean addKnsrdzbnr(KnsrdzbnrForm form){
		String sql = "insert into xg_xszz_knsrd_zbnrb(sxid,nrmc,fzlx,fz,xssx) values(?,?,?,?,?)";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getSxid(),form.getNrmc(),form.getFzlx(),form.getFz(),form.getXssx()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag ;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-21 ����05:09:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbsxList(String zbid)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select sxid,zbid,");
		sql.append(" sxmc,qzbl,xssx from xg_xszz_knsrd_zbsxb where zbid = ? order by xssx ");
		return dao.getListNotOut(sql.toString(), new String[] { zbid});
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ�������϶�ָ�����ݼ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����09:55:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sxid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbnrList(String sxid)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select nrid,sxid,nrmc,fzlx,fz,xssx from xg_xszz_knsrd_zbnrb where sxid = ? order by xssx ");
		//sql.append(" select a.nrid,sxid,nrmc,fzlx,fz,xssx,b.shfz from xg_xszz_knsrd_zbnrb a  ");
		//sql.append(" left join xg_xszz_knsrd_zbsqnrb b on a.nrid = b.nrid where a.sxid = ? order by a.xssx ");
		return dao.getListNotOut(sql.toString(), new String[] {sxid});
	}
	
	
	/**
	 * 
	 * @����:TODO(��������ָ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-21 ����01:27:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws
	 */
	public boolean updateKnsrdzb(KnsrdzbForm form){
		String sql = " update xg_xszz_knsrd_knzbb set zbmc=? where zbid = ? ";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getZbmc(),form.getZbid()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:TODO(����ָ�����Ա�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-21 ����01:27:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws
	 */
	public boolean updateKnsrdzbsx(KnsrdzbsxForm form){
		String sql = " update xg_xszz_knsrd_zbsxb set zbid=?,sxmc=?,qzbl=?,xssx=? where sxid=? ";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getZbid(),form.getSxmc(),form.getQzbl(),form.getXssx(),form.getSxid()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 
	 * @����:TODO(����ָ�����ݱ�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-21 ����01:27:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws
	 */
	public boolean updateKnsrdzbnr(KnsrdzbnrForm form){
		String sql = " update xg_xszz_knsrd_zbnrb set sxid=?,nrmc=?,fzlx=?,fz=?,xssx=? where nrid=? ";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getSxid(),form.getNrmc(),form.getFzlx(),form.getFz(),form.getXssx(),form.getNrid()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 
	 * @����:TODO(ɾ��������ָ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����04:28:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteKnsrdzbsx(String zbid){
		boolean flag = false;
		try {
			flag = dao.runUpdate(" delete from xg_xszz_knsrd_zbsxb where zbid = ? ", new String[]{zbid} );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:TODO(ɾ��������ָ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����04:29:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteKnzb(String zbid){
		boolean flag = false;
		try {
			flag = dao.runUpdate(" delete from xg_xszz_knsrd_knzbb where zbid = ? ", new String[]{zbid} );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	
	/**
	 * 
	 * @����:TODO(ɾ��������ָ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����09:52:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean  deleteKnsrdzbnr(String[] values) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		boolean flag = false;
		sql.append(" delete from xg_xszz_knsrd_zbnrb  where sxid in  ");
		sql.append(" ( ");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("?");
			params.add(values[i]);
			if(i != n-1){
				sql.append(",");
			}
		}
		sql.append(" ) ");
		flag = dao.runUpdate(sql.toString(), params.toArray(new String[]{}) );
		return flag ;
		
	}
	/**
	 * 
	 * @����:TODO(��ȡ�������϶�����id����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����09:39:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbid
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] getKnsrdzbnrsxidList(String zbid) throws Exception {
		StringBuilder sql = new StringBuilder(
				" select sxid,zbid from xg_xszz_knsrd_zbsxb where zbid = ? "  
					);
		String[] sxids = dao.getRs(sql.toString(), new String[]{zbid},"sxid");
		return sxids;
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ�������϶�ָ��id����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����09:39:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbid
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] getKnsrdzbidsList(String zbid) throws Exception {
		StringBuilder sql = new StringBuilder(
				" select zbid from xg_xszz_knsrd_knzbb where zbid <> ? "  
					);
		String[] zbids = dao.getRs(sql.toString(), new String[]{zbid},"zbid");
		return zbids;
	}
	
	/**
	 * 
	 * @����:TODO(�����������϶�ָ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����09:51:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean tyKnsrdzb(String[] values ) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		boolean flag = false;
		sql.append(" update  xg_xszz_knsrd_knzbb set qyzt = 0 where zbid in  ");
		sql.append(" ( ");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("?");
			params.add(values[i]);
			if(i != n-1){
				sql.append(",");
			}
		}
		sql.append(" ) ");
		flag = dao.runUpdate(sql.toString(), params.toArray(new String[]{}) );
		return flag ;
		
	}
	
	/**
	 * 
	 * @����:TODO(�����������϶�ָ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����02:47:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean qyKnsrdzb(String zbid ) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		boolean flag = false;
		sql.append(" update  xg_xszz_knsrd_knzbb set qyzt = 1 where zbid = ? ");
		flag = dao.runUpdate(sql.toString(), new String[]{zbid} );
		return flag ;
		
	}
	
	/**
	 * 
	 * @����:TODO(�õ��������϶�����Form)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����03:04:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * KnsrdzbsxForm �������� 
	 * @throws
	 */
	public KnsrdzbsxForm getKnsrdzbsxModel(String sxid) throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append(" select sxmc,qzbl,xssx from xg_xszz_knsrd_zbsxb where sxid = ? ");
		KnsrdzbsxForm model=new KnsrdzbsxForm();
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{sxid});
		BeanUtils.copyProperties(model, map);
		return model;
	}
	
	
	public KnsrdzbnrForm getKnsrdzbnrModel(String nrid) throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append(" select nrmc,fzlx,fz,xssx from xg_xszz_knsrd_zbnrb where nrid = ? ");
		KnsrdzbnrForm model = new KnsrdzbnrForm();
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{nrid});
		BeanUtils.copyProperties(model, map);
		return model;
	}
	
	
	/**
	 * 
	 * @����:TODO(��ȡָ����������ids)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����03:26:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] getKnsrdzbnridList(String sxid) throws Exception {
		
		/*StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select * from xg_xszz_knsrd_zbnrb  where sxid in  ");
		sql.append(" ( ");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("?");
			params.add(values[i]);
			if(i != n-1){
				sql.append(",");
			}
		}
		sql.append(" ) ");*/
		StringBuilder sql = new StringBuilder(" select nrid from xg_xszz_knsrd_zbnrb  where sxid = ? " );
		String[] nrids = dao.getRs(sql.toString(), new String[]{sxid},"nrid");
		return nrids;
	}
	
	/**
	 * 
	 * @����:TODO(�ж��Ƿ���ɾ������������ָ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����04:11:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ccqjtxid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String zbid){
		StringBuffer sql = new StringBuffer();
		sql.append("  select distinct a.zbmc from xg_xszz_knsrd_knzbb a left join xg_xszz_knsrd_zbsqb b ");
		sql.append(" on a.zbid = b.zbid where a.zbid = ?  and b.zbid is not null ");
		
		Map<String,String> map= dao.getMapNotOut(sql.toString(),new String[]{zbid});
		String zbmc = map.get("zbmc");
		//���δ�ύ�ſ����ύ
		return zbmc==null?true:false;
		//return true;
	}
	
	public HashMap<String, String> getKnsrdzb(String zbid){
		StringBuffer sb=new StringBuffer();
		sb.append(" select zbmc from xg_xszz_knsrd_knzbb where zbid = ?  ");
		//sb.append("  ");
		return dao.getMapNotOut(sb.toString(),new String[]{zbid});
	}
	
	/**
	 * 
	 * @����:TODO(����ָ��Id�ж��Ƿ����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-11 ����10:38:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForUpdate(String zbid) {
		
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_xszz_knsrd_zbsqb where zbid=? ");
		String num = dao.getOneRs(sql.toString(), new String[] {zbid}, "num");
		return num;

	}
	
}
