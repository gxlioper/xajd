/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:07:04 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ϣ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:07:04
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ShlcpzDao extends SuperDAOImpl<ShlcpzForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ShlcpzForm t, User user)
			throws Exception {
		return null;
	}

	@Override
	protected void setTableInfo() {
		this.setKey("jddm");
		this.setTableName("XG_DTJS_JBSZB");
		this.setClass(ShlcpzForm.class);
	}

	@Override
	public List<HashMap<String, String>> getPageList(ShlcpzForm t)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select d.jddm,d.jdmc,");
		// ��ѯ�������
		sql
				.append(" decode(e.lcxx, '', '�������', mc || '��' || replace(lcxx, ';', '->')) lcxx,");
		sql.append(" decode(d.ksqkg,'1','����','�ر�') ksqkgxx,");
		sql.append(" case ");
		sql.append("   when d.ksqkg <> '1' then '' ");
		sql.append("   when d.ksqjssj is not null then d.ksqkssj || ' �� '|| d.ksqjssj ");
		sql.append("   when d.ksqkssj is not null then d.ksqkssj||  ' �� ' ");
		sql.append("   else '' end qzsj ");
		sql.append(" from XG_DTJS_JBSZB d left join (select splc, mc, lcxx ");
		sql
				.append(" from (select splc,a.mc,to_char(WM_CONCAT(c.mc) over (partition by splc order by xh )) lcxx, xh, ");
		sql.append(" row_number() over(partition by splc order by xh desc) as ddd");
		sql
				.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = ? and a.id = b.splc ");
		sql
				.append(" and b.spgw = c.id) b where ddd = 1 ) e on d.splc = e.splc where 1 = 1 ");
		sql.append(" and d.splc is not null");
		if (!StringUtils.isNull(t.getJdmc())) {
			sql.append(" and jdmc like '%" + t.getJdmc() + "%'");
		}
		if (StringUtils.equals("13431", Base.xxdm)) {
			sql.append(" order by jdsx*1");
		}
		return this.getPageList(t, sql.toString(), new String[] {ShlcpzAction._DTXX_SPLC_NAME});
	}

	/**
	 * 
	 * @����:��ÿ������ӽ׶δ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-22 ����05:12:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getJdList() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select d.jddm,d.jdmc");
		sql
				.append(" from XG_DTJS_JBSZB d where d.SFKPZSHL=? and d.splc is null order by to_number(d.jdsx) asc");
		return dao.getListNotOut(sql.toString(),
				new String[] { Constants.SHLC_SFKPZSHLC_KEYPZ });
	}

	/**
	 * 
	 * @����:��ȡ���˽׶������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-28 ����10:15:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getGrJdxx(String xh) throws Exception {
		StringBuffer sql = new StringBuffer();
		List<String> param = new ArrayList<String>();
		sql.append(" select b.xh,a.jddm,a.jdmc,a.splc,b.grxj,b.zd1,b.zd2,b.zd3,b.zd5,b.zd8,b.zd9,b.zd10,b.kssj,b.xh||b.jddm dtxxjgid,a.ksqkg,a.ksqkssj,a.ksqjssj,b.sjly");
		sql
				.append(" from XG_DTJS_JBSZB a left join (select * from XG_DTJS_XSDTXXJLB where 1=1");
		if (StringUtils.isNotNull(xh)) {
			sql.append("and xh=?");
			param.add(xh);
		}else{
			xh="";
			sql.append("and xh=?");
			param.add(xh);
		}
		sql.append(") b on a.jddm=b.jddm order by to_number(a.jdsx)");
		return dao.getListNotOut(sql.toString(), param.toArray(new String[]{}));
	}
}
