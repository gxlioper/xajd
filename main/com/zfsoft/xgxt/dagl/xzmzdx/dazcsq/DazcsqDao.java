/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����03:13:22 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ����ģ��
 * @�๦������: ����ת��-����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-27 ����03:13:09 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DazcsqDao extends SuperDAOImpl<DazcsqForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazcsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazcsqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t.sqid,t.xh,t1.xm,t1.xb,t1.nj,t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.bjdm,t1.bjmc, ");
		sql.append("decode(t.zcfs,'1','�ʼ�','2','�Դ�',t.zcfs)zcfsmc,t.zcfs, ");
		sql.append("t.yjdz,t.yzbm,t.sjr,t.sjrdh,t.dwmc,t.dwdz,t.zddacn,t.yqtdrq, ");
		sql.append("t.sjlrr,t.sjlrsj,t.splcid, ");
		sql.append("t.shzt,decode(t.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����',t.shzt) shztmc ");
		sql.append("from xg_xsxx_dagl_dazcsqb t ");
		sql.append("left join view_xsbfxx t1 on t.xh = t1.xh ");
		sql.append(")a where 1 = 1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(DazcsqForm.class);
		this.setKey("sqid");
		this.setTableName("xg_xsxx_dagl_dazcsqb");
	}
	
	/**
	 * @����: �ж�Ψһ����ѧ��(xh)
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-9 ����02:06:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(DazcsqForm model){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*)cnt from xg_xsxx_dagl_dazcsqb where xh = ? ");
		paraList.add(model.getXh());
		if(StringUtils.isNotNull(model.getSqid())){
			sql.append(" and sqid <> ? ");
			paraList.add(model.getSqid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		/*��������ʱ����false��������ʱ����true*/
		return "0".equals(cnt) ? true : false;
	}
	
	/**
	 * @����: ɾ��ʱ�������״̬���е���������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-05-09 ����10:39:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delShztbData(String[] sqids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xtwh_shztb where ywid in ( ");
		for (int i = 0; i < sqids.length; i++) {
			sql.append("?");
			if(i != sqids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), sqids);
	}
	
	/**
	 * @����: ��������id��ȡ�����Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-9 ����02:40:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqidArr
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getInfoBySqid(String[] sqidArr) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xsxx_dagl_dazcsqb where");
		for(int i = 0; i < sqidArr.length; i++){
			sql.append(" sqid = ? ");
			if(i < sqidArr.length - 1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), sqidArr);
	}
	
	/**
	 * @����: ��������ID���ѧ��������Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-9 ����05:47:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getInfoBySqid(String sqid)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t.sqid,t.xh,t1.xm,t1.xb,t1.nj,t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.bjdm,t1.bjmc, ");
		sql.append("decode(t.zcfs,'1','�ʼ�','2','�Դ�',t.zcfs)zcfs, ");
		sql.append("t.yjdz,t.yzbm,t.sjr,t.sjrdh,t.dwmc,t.dwdz,t.zddacn,t.yqtdrq, ");
		sql.append("t.sjlrr,t.sjlrsj,t.splcid, ");
		sql.append("t.shzt,decode(t.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����',t.shzt) shztmc ");
		sql.append("from xg_xsxx_dagl_dazcsqb t ");
		sql.append("left join view_xsbfxx t1 on t.xh = t1.xh ");
		sql.append(")t where t.sqid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}

}
