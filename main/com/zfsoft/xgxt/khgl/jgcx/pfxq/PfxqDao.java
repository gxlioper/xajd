/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-18 ����09:12:20 
 */  
package com.zfsoft.xgxt.khgl.jgcx.pfxq;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.khgl.khpf.KhpfService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�cq [����:785]
 * @ʱ�䣺 2015-8-18 ����09:12:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PfxqDao extends SuperDAOImpl<PfxqForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PfxqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ������������ѯ
	 */
	public List<HashMap<String, String>> getPageList(PfxqForm t, User user)
			throws Exception {
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (");
		sql.append("select t4.xmmc,t3.khbmc,t1.khbid,t1.xmid,t1.pfr,t1.khdxr,t1.tjsj,t5.xm||t6.xm khdxmc, ");
		if(Base.xxdm.equals("12309") || Base.xxdm.equals("33333")){//���������Ի�
			sql.append(" t9.yjjy, ");
		}
		sql.append("t5.xb || decode(t6.xb, '1', '��', '2', 'Ů', t6.xb) khdxxb,t5.xymc || t7.bmmc khdxbm,t5.xh||t6.zgh dm,sum(t2.fs*decode(t8.pflx,'2','-1','1')) zf,? pfrxm ");
		sql.append("from xg_khgl_khpf t1 left join xg_khgl_khpf_pfmx t2 on t1.pfid=t2.pfid ");
		sql.append("left join xg_khgl_khb t3 on t1.khbid=t3.khbid left join xg_khgl_khxm t4 on t1.xmid=t4.xmid ");
		sql.append("left join view_xsbfxx t5 on t1.khdxr=t5.xh  left join fdyxxb t6 on t1.khdxr=t6.zgh ");
		sql.append("left join zxbz_xxbmdm t7 on t6.bmdm=t7.bmdm ");
		sql.append("left join xg_khgl_tk_zbx t8 on t2.khbid =t8.khbid and t2.zbmxid = t8.zbmxid ");
		if(Base.xxdm.equals("12309") || Base.xxdm.equals("33333")){//���������Ի�
			sql.append(" left join xg_khgl_khpf_yjjyb t9 on t1.pfid =t9.pfid ");
		}
		sql.append("where t1.pfr= ? and t1.sftj= '"+KhpfService.SFTJ_Y+"' and t5.xh||t6.zgh is not null ");
		sql.append("group by t4.xmmc,t3.khbmc,t1.khbid,t1.xmid,t1.pfr,t1.khdxr,t1.tjsj,t5.xm||t6.xm,t5.xh||t6.zgh, ");
		sql.append("t5.xb || decode(t6.xb, '1', '��', '2', 'Ů', t6.xb), t5.xymc || t7.bmmc");
		if(Base.xxdm.equals("12309") || Base.xxdm.equals("33333")){//���������Ի�
			sql.append(",t9.yjjy");
		}
		sql.append(") where 1=1 ");
		sql.append(searchTj);
		
		//���Լ��Ĳ����͸߼���ѯ�����ϲ�
		String[] both = (String[]) ArrayUtils.addAll(new String []{user.getRealName(),user.getUserName()}, inputV);
		
		return getPageList(t, sql.toString(), both);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������

	}

}
