/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:07:04 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.qjgz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:07:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QjgzDao extends SuperDAOImpl<QjgzForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QjgzForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select d.qjgzid,d.kssj,d.jssj,d.splcid,");
		sql.append(" d.ssxydm,");
		sql.append(" decode(d.ssxydm,'qx','ȫУ',z.bmmc) ssxymc,");
		//��ѯ�������
		sql.append("(d.kssj||'~'||d.jssj||'��')qjqj,");
		//��ѯ�������
		sql.append(" decode(e.lcxx, '', '�������', mc || '��' || replace(lcxx, ';', '->')) lcxx,x.qjlxmc,d.qjlxid, ");
		sql.append(" decode(d.open,'1','����','�ر�') openzt");
		sql.append(" from xg_rcsw_qjgl_qjgz d left join (select splc, mc, lcxx ");
		sql.append(" from (select splc,a.mc,to_char(WM_CONCAT(c.mc) over (partition by splc order by xh )) lcxx, xh,");
		sql.append(" row_number() over (partition by splc order by xh desc ) as ddd");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = 'rcsw'and a.id = b.splc ");
		sql.append(" and b.spgw = c.id) b where ddd = 1 ) e on d.splcid = e.splc ");
		sql.append("  left join xg_rcsw_qjgl_qjlx x");
		sql.append("  on d.qjlxid = x.qjlxid");
		sql.append(" left join ZXBZ_XXBMDM z");
		sql.append(" on z.bmdm = d.ssxydm");

		sql.append(" where 1 = 1 ");
		if(!"xx".equals(user.getUserStatus())){
			sql.append(" and (ssxydm = 'qx' or ssxydm='"+user.getUserDep()+"' )");
		}
		sql.append(" ");
		sql.append(searchTj);
		//���ԭ�����У�action�Ǳ�ԭ���ߵ��ǲ���user�ģ������֪�������õ�
		/*sql.append(" select d.qjgzid,");
		sql.append(" d.qjlxid,");
		sql.append(" d.kssj,");
		sql.append(" d.jssj,");
		sql.append(" d.splcid,");
		sql.append(" d.ssxydm,");
		sql.append(" decode(d.ssxydm,'qx','ȫУ',z.bmmc) ssxymc,");
		sql.append(" (d.kssj || '~' || d.jssj || '��') qjqj,");
		sql.append(" decode(e.lcxx,");
		sql.append("  '',");
		sql.append("  '�������',");
		sql.append(" mc || '��' || replace(lcxx, ';', '->')) lcxx,");
		sql.append(" x.qjlxmc");
		sql.append(" from xg_rcsw_qjgl_qjgz d");
		sql.append(" left join (select splc, mc, lcxx");
		sql.append(" from (select splc,");
		sql.append(" a.mc,");
		sql.append(" to_char(WM_CONCAT(c.mc)");
		sql.append(" over(partition by splc");
		sql.append(" order by xh)) lcxx,");
		sql.append(" xh,");
		sql.append(" row_number() over(partition by splc order by xh desc) as ddd");
		sql.append(" from xg_xtwh_splc a,");
		sql.append(" xg_xtwh_spbz b,");
		sql.append(" xg_xtwh_spgw c");
		sql.append(" where djlx = 'rcsw'");
		sql.append(" and a.id = b.splc");
		sql.append(" and b.spgw = c.id) b");
		sql.append(" where ddd = 1) e");
		sql.append(" on d.splcid = e.splc");
		sql.append(" left join xg_rcsw_qjgl_qjlx x");
		sql.append(" on d.qjlxid = x.qjlxid");
		sql.append(" left join ZXBZ_XXBMDM z");
		sql.append(" on z.bmdm = d.ssxydm");
		sql.append(" where 1 = 1");
		sql.append(" ");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);*/
		return this.getPageList(t, sql.toString(), inputV);
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		this.setKey("qjgzid");
		this.setTableName("xg_rcsw_qjgl_qjgz");
		this.setClass(QjgzForm.class);
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QjgzForm t)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select d.qjgzid,d.kssj,d.jssj,d.splcid,");
		sql.append(" d.ssxydm,");
		sql.append(" decode(d.ssxydm,'qx','ȫУ',z.bmmc) ssxymc,");
		//��ѯ�������
		sql.append("(d.kssj||'~'||d.jssj||'��')qjqj,");
		//��ѯ�������
		sql.append(" decode(e.lcxx, '', '�������', mc || '��' || replace(lcxx, ';', '->')) lcxx,x.qjlxmc,d.qjlxid, ");
		sql.append(" decode(d.open,'1','����','�ر�') openzt");
		sql.append(" from xg_rcsw_qjgl_qjgz d left join (select splc, mc, lcxx ");
		sql.append(" from (select splc,a.mc,to_char(WM_CONCAT(c.mc) over (partition by splc order by xh )) lcxx, xh,");
		sql.append(" row_number() over (partition by splc order by xh desc ) as ddd");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = 'rcsw'and a.id = b.splc ");
		sql.append(" and b.spgw = c.id) b where ddd = 1 ) e on d.splcid = e.splc ");
		sql.append("  left join xg_rcsw_qjgl_qjlx x");
		sql.append("  on d.qjlxid = x.qjlxid");
		sql.append(" left join ZXBZ_XXBMDM z");
		sql.append(" on z.bmdm = d.ssxydm");
		sql.append(" where 1 = 1 ");
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:�Ƿ�ʹ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-13 ����02:12:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isUse(String qjgzid){
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from xg_rcsw_qjgl_qjsq t,xg_rcsw_qjgl_qjgz m where m.kssj<t.qjts and t.qjts<=m.jssj and m.qjgzid=?");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{qjgzid});
		return null==map||map.size()<=0?false:true;
	}
	
	/**
	 * 
	 * @����: ��ȡ�������List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-29 ����11:19:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjlxList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_rcsw_qjgl_qjlx");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	public HashMap<String, String> getInfo(QjgzForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,decode(a.ssxydm,'qx','ȫУ',z.bmmc) ssxymc from XG_RCSW_QJGL_QJGZ a ");
		sql.append(" left join ZXBZ_XXBMDM z");
		sql.append(" on z.bmdm = a.ssxydm");
		sql.append(" where a.qjgzid=?");
		return dao.getMapNotOut(sql.toString(),new String[]{model.getQjgzid()});
	}
}
