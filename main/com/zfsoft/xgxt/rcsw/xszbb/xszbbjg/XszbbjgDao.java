/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����04:40:57 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbbjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤�������ģ��
 * @�๦������: TODO(ѧ��֤������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-18 ����04:24:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XszbbjgDao extends SuperDAOImpl<XszbbjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("xg_rcsw_xszbb_xszbbjgb");
		super.setKey("bbjgid");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszbbjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszbbjgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		if("11400".equals(Base.xxdm)){
			sql.append(" select a.* from (select t1.bbjgid,t1.xh,t1.xszbblxdm,t1.sjly,t1.sqsj,decode(t1.sflq, '1', '��', '0','��',sflq) sflq,t1.lqsj,t1.ffyh, ");
			sql.append(" t2.xm, t2.xb,t2.xymc,t2.zymc,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm, t2.bjdm, t2.nj,t4.fdyxm,t4.ylbxh, ");
			sql.append(" decode(t1.sfbbhcyhk, 'y','��', 'n', '��', t1.sfbbhcyhk) sfbbhcyhk,t3.xszbblxmc ");
			sql.append(" from xg_rcsw_xszbb_xszbbjgb  t1 left join view_xsbfxx t2  on t1.xh = t2.xh ");
			sql.append(" left join xg_rcsw_xszbb_bblxwhb t3 on t1.xszbblxdm = t3.xszbblxdm " +
					" left join xsxxb t4 on t1.xh = t4.xh " +
					" where t1.xszbblxdm is not null ");
			sql.append(" ) a where 1=1   ");
		}else{
			sql.append(" select a.* from (select t1.dd,t1.sj,t1.bbjgid,t1.xh,t1.xszbblxdm,t1.sjly,t1.sqsj,decode(t1.sflq, '1', '��', '0','��',sflq) sflq,t1.lqsj,t1.ffyh, ");
			sql.append(" t2.xm, t2.xb,t2.xymc,t2.zymc,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm,t2.zybj,t2.zybjmc, t2.bjdm, t2.nj,t2.xz,t2.rxrq,t2.sjhm,t2.jtdzxx,t2.sfzh, ");
			if(Base.xxdm.equals("13011")){//�ൺ�Ƶ���Ի�
				sql.append(" t1.shwcsj,t2.jgmc,");
			}
			if("13871".equals(Base.xxdm)){
				sql.append(" bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm,");
			}
			sql.append(" decode(t1.sfbbhcyhk, 'y','��', 'n', '��', t1.sfbbhcyhk) sfbbhcyhk,t3.xszbblxmc,t1.ccqdz,t1.cczdz,(t1.ccqdz || '-' ||t1.cczdz) hcccqjmc ");
			sql.append(" from xg_rcsw_xszbb_xszbbjgb  t1 ");
			if(Base.xxdm.equals("13011")){//�ൺ�Ƶ���Ի�
				sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh");
			} else {
				sql.append(" left join view_xsbfxx t2  on t1.xh = t2.xh ");
			}
			sql.append(" left join xg_rcsw_xszbb_bblxwhb t3 on t1.xszbblxdm = t3.xszbblxdm ");	
			//��������ְҵ����ѧԺ
			if("13871".equals(Base.xxdm)){
				sql.append(" left join view_xg_bzrxx bzr on t2.bjdm = bzr.bjdm");
			}
			sql.append("  where t1.xszbblxdm is not null ");
			sql.append(" ) a where 1=1   ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	
	}
	
	/**
	 * 
	 * @����:TODO(�ж�ѧ��֤�����������Ƿ��Ѿ����ڸ�ѧ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����08:39:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(XszbbjgForm model) {
		StringBuilder sql = new StringBuilder(
				"select count(1) num from xg_rcsw_xszbb_xszbbjgb where xh=?  ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh()}, "num");
		return num;

	}
	
	
	
	public boolean isCanDel(String bbjgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_rcsw_xszbb_xszbbjgb where bbjgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{bbjgid});
		String sjly=map.get("sjly");
		//���δ�ύ�ſ����ύ
		return sjly.equals("0")?true:false;
	}
	
	public HashMap<String, String> getBbjg(String bbjgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_xszbb_xszbbjgb a");
		sb.append(",view_xsxxb xsxx where a.xh=xsxx.xh and a.bbjgid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{bbjgid});
	}
	
	/**
	 * 
	 * @����:TODO(�鿴ѧ��֤������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����10:49:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xwjgId
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> viewOneXszbbjgList(String  xszbbjgId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.dd,t1.sj,t1.filepath,t1.bbjgid,t1.xh,t1.xszbblxdm,t1.sqly,t1.sqsj,t1.bz, ");
		if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//�ൺ�Ƶ���Ի�
			sql.append("t1.ccqdz,t1.cczdz,t1.sfbbhcyhk sfbbhcyhkmc,");
		}
		sql.append(" decode(t1.sfbbhcyhk, 'y','��', 'n', '��', t1.sfbbhcyhk) sfbbhcyhk, ");
		sql.append(" decode(t1.sflq,'0','��','1','��',t1.sflq) sflq,t1.lqsj,t2.xszbblxmc ");
		sql.append(" from xg_rcsw_xszbb_xszbbjgb  t1 left join xg_rcsw_xszbb_bblxwhb t2  on t1.xszbblxdm = t2.xszbblxdm ");
		sql.append(" where t1.bbjgid = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{xszbbjgId});
	}
	
	/** 
	 * @����:ɾ���𳵳˳�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-5-23 ����06:51:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @throws Exception
	 * void �������� 
	 * @throws 
	 */
	public void delHcccqj(String xh,String xn,String xq) throws Exception{
		String sql = "delete from xg_rcsw_hcyhk_hcccqjjgb where xh = ? and xn = ? and xq = ?";
		dao.runDelete(sql, new String[]{xh,xn,xq});
	}
	
	
}
