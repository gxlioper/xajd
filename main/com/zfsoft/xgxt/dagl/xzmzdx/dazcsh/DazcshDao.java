/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����03:08:39 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsh;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ����ģ��
 * @�๦������: ����ת��-���
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-5-14 ����03:28:27 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class DazcshDao extends SuperDAOImpl<DazcshForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazcshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazcshForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select t.sqid,t.splcid,");
		sql.append("t.xh,t1.xm,t1.xb,t1.nj,t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.bjdm,t1.bjmc, ");
		sql.append("decode(t.zcfs, '1', '�ʼ�', '2', '�Դ�', t.zcfs) zcfsmc,t.zcfs, ");
		sql.append("t.yjdz,t.yzbm,t.sjr,t.sjrdh,t.dwmc,t.dwdz,t.zddacn,t.yqtdrq,t.sjlrr,t.sjlrsj, ");
		sql.append("t2.guid shid,t2.gwid,t2.shr,t2.shyj,t2.shzt,t4.mc || '[' ||");
		sql.append("decode(t2.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t4.gwz,");
		sql.append("row_number() over(partition by t.sqid order by t2.shsj desc) rn ");
		sql.append("from xg_xsxx_dagl_dazcsqb t ");
		sql.append("left join view_xsbfxx t1 on t.xh = t1.xh ");
		sql.append("left join xg_xtwh_shztb t2 on t.sqid = t2.ywid ");
		sql.append("left join xg_xtwh_spgwyh t3 on t2.gwid = t3.spgw ");
		sql.append("left join xg_xtwh_spgw t4 on t2.gwid = t4.id ");
		sql.append("where t3.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t2.shzt <> '0' and t2.shzt <> '4')");
		}else{
			sql.append(" and (t2.shzt = '0' or t2.shzt = '4' )");
		}
		sql.append(" )a where 1 = 1 and rn = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(DazcshForm.class);
		super.setKey("sqid");
		super.setTableName("xg_xsxx_dagl_dazcsqb");
	}
	
	/**
	 * @����: ��˸�����Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-14 ����05:49:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getDazcshInfo(DazcshForm t) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select t.sqid,t.xh,t1.xm,t1.xb,t1.nj,t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.bjdm,t1.bjmc, ");
		sql.append("decode(t.zcfs,'1','�ʼ�','2','�Դ�',t.zcfs)zcfsmc,t.zcfs, ");
		sql.append("t.yjdz,t.yzbm,t.sjr,t.sjrdh,t.dwmc,t.dwdz,t.zddacn,t.yqtdrq, ");
		sql.append("t.sjlrr,t.sjlrsj,t.splcid, ");
		sql.append("t.shzt,decode(t.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����',t.shzt) shztmc ");
		sql.append("from xg_xsxx_dagl_dazcsqb t ");
		sql.append("left join view_xsbfxx t1 on t.xh = t1.xh ");
		sql.append(")a where 1 = 1 and sqid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}
}
