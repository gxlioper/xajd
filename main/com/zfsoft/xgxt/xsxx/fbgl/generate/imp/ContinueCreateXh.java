/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-24 ����02:56:13 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate.imp;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: ��������ѧ��
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-24 ����02:56:13
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ContinueCreateXh extends CreateXh {
	@Override
	public String getBaseSql() {
		sb.append("select * from (");
		sb.append("select t.*,t.nj||'_'||t.ksh pk from xg_xsxx_fbgl_xsxx_lsb t) where 1=1");
		String pk=getParam().get(0);
		if (StringUtils.isNotNull(pk)) {
			sb.append(" and pk in (");
			for(String id:pk.split(",")){
				sb.append("'");
				sb.append(id);
				sb.append("',");
			}
			sb.append("'-1')");
		}
		sb.append(" and xh is null");
		getParam().remove(0);
		return sb.toString();
	}
	/*
	      ����: @see com.zfsoft.xgxt.xsxx.fbgl.generate.imp.CreateXh#getValue(java.lang.String, java.lang.String)
	 */
	
	@Override
	protected String getValue(String zd, String pk) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select " + zd + " from XG_XSXX_FBGL_XSXX_LSB where nj||'_'||ksh=?");
			return DAO.getInstance().getOneRs(sb.toString(),
					new String[] { pk }, zd);
		} catch (Exception e) {
			// �����ѯ�޴��ֶ� ����Ĭ��ֵ
			return null;
		}
	}
}
