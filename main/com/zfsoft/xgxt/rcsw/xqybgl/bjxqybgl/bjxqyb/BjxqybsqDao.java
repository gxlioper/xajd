/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-24 ����08:43:41 
 */
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqyb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ҽҩ_ѧ���±�����ģ��
 * @�๦������: TODO(������ҽҩ_ѧ���±�_�༶�±�����)
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-24 ����08:43:41
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BjxqybsqDao extends SuperDAOImpl<BjxqybsqForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(BjxqybsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(BjxqybsqForm t, User user)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();

		sql.append(" select a.*,b.splc splcnew from ( select t1.sqid,t1.xn,t1.xq,t1.yf ny,substr(t1.yf,1, 4)nd, ");
		sql.append(" substr(t1.yf,6,2)yf,t1.bygzkzqk,t1.xsgzrd,t1.xssxdt,t1.xstsjgzjy,t1.txsj, ");
		sql.append(" decode(t1.shzt, '0','δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '','�������', t1.shzt) shztmc, ");
		sql.append(" t4.xqmc,t1.txr,t1.shzt, ");
		sql.append(" t1.splc,t1.bjdm,t2.nj,t2.xydm,t2.zydm,t2.xymc,t2.zymc,t2.bjmc,t3.xm lrrxm from xg_bjzyy_xqyb_bjyb_sq t1  ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append(" left join fdyxxb t3 on t1.txr = t3.zgh ");
		sql.append(" left join xqdzb t4 on t1.xq = t4.xqdm ) a,xg_bjzyy_xqyb_bjyb_cssz b where 1 = 1  ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);

	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {

		// TODO �Զ����ɷ������
		super.setKey("sqid");
		super.setTableName("xg_bjzyy_xqyb_bjyb_sq");
		super.setClass(BjxqybsqForm.class);

	}

	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-���°༶�±�����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����11:29:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateBjxqybsq(BjxqybsqForm model) throws Exception {
		String[] inputV = new String[3];
		StringBuffer sql = new StringBuffer();

		sql.append(" UPDATE xg_bjzyy_xqyb_bjyb_sq ");
		sql.append(" set ");
		sql.append(" shzt = ? ,splc = ? ");
		sql.append(" where sqid = ? ");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();

		return dao.update(sql.toString(), inputV) > 0 ? true : false;

	}

	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-������ID��ȡ������ϸ��Ϣ)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����11:43:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getBjxqybsqInfo(BjxqybsqForm model) {

		StringBuffer sql = new StringBuffer();

		sql.append(" select t1.sqid,t1.xn,t1.xq,t1.yf,t1.bygzkzqk,t1.xsgzrd,t1.xssxdt,t1.xstsjgzjy,t1.txsj, ");
		sql.append("  decode(t1.shzt, '0','δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3','�˻�', '4', '������', '5', '�����', '','�������', t1.shzt) shztmc , ");
		sql.append(" t4.xqmc ,");
		sql.append(" t1.txr,t1.shzt,t1.splc,t1.bjdm,t2.xymc,t2.bjmc,t3.xm lrrxm from xg_bjzyy_xqyb_bjyb_sq t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm left join fdyxxb t3 on t1.txr = t3.zgh  ");
		sql.append(" left join xqdzb t4 on t1.xq = t4.xqdm where  ");
		sql.append("  t1.sqid = ? ");

		return dao.getMapNotOut(sql.toString(),new String[] { model.getSqid() });
	}

	/**
	 * 
	 * @����:TODO(���հ༶����,ѧ��,�·��жϰ༶ѧ���±���������Ƿ��Ѿ����ڸð�)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-7 ����04:34:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return String ��������
	 * @throws
	 */
	public String checkExistForBjxqybsqSave(BjxqybsqForm model) {
		StringBuffer sql = new StringBuffer(
				" select count(1) num from xg_bjzyy_xqyb_bjyb_sq where  xn = ?  and xq = ? and yf = ? and bjdm = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXn(),
				model.getXq(), model.getYf(), model.getBjdm() }, "num");
		return num;
	}

	/**
	 * 
	 * @����:TODO(���հ༶����, ѧ��,�·�,����ID�жϰ༶ѧ���±���������Ƿ��Ѿ����ڸð�)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-7 ����04:50:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return String ��������
	 * @throws
	 */
	public String checkExistForBjxqybsqUpdate(BjxqybsqForm model) {
		StringBuffer sql = new StringBuffer(
				" select count(1) num from xg_bjzyy_xqyb_bjyb_sq where bjdm = ? and xn = ?  and xq = ? and yf = ? and sqid <> ? ");
		String num = dao.getOneRs(sql.toString(), new String[] {
				model.getBjdm(), model.getXn(), model.getXq(), model.getYf(),
				model.getSqid() }, "num");
		return num;
	}

	/**
	 * 
	 * @����:TODO(ѧ���±�_�༶�±�����-�Ӱ༶�±�_���������л�ȡ��������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����11:45:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String ��������
	 * @throws
	 */
	public String getShlcID() {

		String sql = " select splc from xg_bjzyy_xqyb_bjyb_cssz ";
		return dao.getOneRs(sql, new String[] {}, "splc");

	}

	/**
	 * 
	 * @����:TODO(ѧ���±�_�༶�±�����-����ѧ���±��������״̬)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-8 ����09:51:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean cancelBjxqybsq(BjxqybsqForm model) throws Exception {
		String[] inputV = new String[3];
		StringBuffer sql = new StringBuffer();

		sql.append(" UPDATE xg_bjzyy_xqyb_bjyb_sq ");
		sql.append(" set ");
		sql.append(" shzt = ? ,");
		sql.append(" splc = ? ");
		sql.append(" where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();

		return dao.update(sql.toString(), inputV) > 0 ? true : false;
	}

	/**
	 * 
	 * @����:TODO(ѧ���±�_�༶�±�����-ֻ��δ�ύ�ſ��Գ���)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����11:49:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return boolean ��������
	 * @throws
	 */
	public boolean isCanDel(String sqid) {

		StringBuffer sb = new StringBuffer();

		sb.append("select * from xg_bjzyy_xqyb_bjyb_sq where sqid=? ");
		Map<String, String> map = dao.getMapNotOut(sb.toString(),
				new String[] { sqid });
		String bbsqzt = map.get("shzt");
		// ���δ�ύ�ſ����ύ
		return null == bbsqzt || bbsqzt.equals("0") ? true : false;

	}

	/**
	 * 
	 * @����:TODO(ѧ���±�_�༶�±�����-������ID��ȡ�༶��Ϣ)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����11:50:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getBjxqybsq(String sqid) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.bjdm bjdm,b.bjmc bjmc from xg_bjzyy_xqyb_bjyb_sq a,view_njxyzybj_all b  ");
		sb.append(" where a.bjdm = b.bjdm and a.sqid=? ");
		return dao.getMapNotOut(sb.toString(), new String[] { sqid });
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-��ѯ�༶)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-13 ����03:15:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjList(BjxqybsqForm t, User user)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1",
				"xydm", "bjdm");

		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.bjrs,b.* from ( ");
		sql.append(" select bjdm,count(1) bjrs  from view_xsjbxx group by bjdm ");
		sql.append(" ) a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);

		return getPageList(t, sql.toString(), inputValue);
	}

}
