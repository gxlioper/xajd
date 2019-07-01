/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-15 ����10:53:13 
 */  
package com.zfsoft.xgxt.xszz.hjxf.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-3-15 ����10:53:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjxfSqDao extends SuperDAOImpl<HjxfSqForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	private static final String  RDZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	@Override
	public List<HashMap<String, String>> getPageList(HjxfSqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjxfSqForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.nj,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,");
		sql.append(" decode(t.shzt,");
		sql.append(" '0',");
		sql.append(" 'δ�ύ',");
		sql.append(" '1',");
		sql.append(" 'ͨ��',");
		sql.append(" '2',");
		sql.append(" '��ͨ��',");
		sql.append(" '3',");
		sql.append(" '���˻�',");
		sql.append(" '5',");
		sql.append(" '�����',");
		sql.append(" t.shzt) shztmc,");
		sql.append(" t.xn || t2.xqmc xnxq");
		sql.append(" from XG_XSZZ_NEW_HJXFSQB t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh " +
				" left join xqdzb t2 on t.xq = t2.xqdm " +
				") t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(HjxfSqForm.class);
		super.setKey("hjxfid");
		super.setTableName("XG_XSZZ_NEW_HJXFSQB");
	}
	
	
	//��ȡ������
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_XSZZ_NEW_HJXFJBSZ ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	//��ȡspid
	public String getSqbh(HjxfSqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select hjxfid from XG_XSZZ_NEW_HJXFSQB where xh = ? and  xn = ? and xq = ?");
		return dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXq()}, "hjxfid");
	}
	
	/**
	 * 
	 * @����:��ȡ������ƶ���ȼ�(���Ϊ����ʾ��ͥ���ò�����)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-3-16 ����02:22:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getKnsdj(HjxfSqForm model){
		StringBuffer sql = new  StringBuffer();
		sql.append(" select t1.dcmc djmc ");
		sql.append(" from XG_XSZZ_NEW_HJXFSQB z");
		sql.append("   left join xg_xszz_new_knsjgb t ");
		sql.append(" on z.xh = t.xh and z.xn = t.xn");
		sql.append(" left join ");
		sql.append(" xg_xszz_new_kndcdmb t1");
		sql.append(" on t.rddc = t1.dcdm where z.hjxfid = ? and (t.xq = 'on' or t.xq = ?)");
	    String result = dao.getOneRs(sql.toString(), new String[]{model.getHjxfid(),model.getXq()}, "djmc");
		if(StringUtils.isNotNull(result)){
			return result;
		}else{
			return "��ͥ���ò�����";
		}
		
	}
	
}
