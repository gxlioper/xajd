/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:07:04 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.ctc.wstx.util.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjsq.QjsqForm;
import com.zfsoft.xgxt.sztz.zyszpj.ZyszpjForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:07:04
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjshDao extends SuperDAOImpl<QjshForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QjshForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		//�㽭����ְҵ���Ի����ж���ٿ�ʼʱ���Ƿ�ʱ��
		if("12866".equals(Base.xxdm)){
			sql.append(" select a.*, case when a.shzt = '0' and sysdate > to_date(a.cssj,'yyyy/mm/dd hh24:mi') then '1' when a.shzt = '5' and sysdate > to_date(a.cssj,'yyyy/mm/dd HH24:mi') then '1' else '0' end sfcs");
			sql.append(" from (select t1.*,(select xqmc from xqdzb where xqdm=t1.xq) xqmc,qjjg.xjzt,b.qjlxmc,t2.xm,t2.xb,t2.bjmc, t2.lxdh,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.nj,t2.zybj,t2.zybjmc,d.gwid,d.shr,d.shyj, f.mc || '[' || decode(d.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����') || ']' shztmc");
		}else{			
			sql.append(" select * from (select t1.*,(select xqmc from xqdzb where xqdm=t1.xq) xqmc,qjjg.xjzt,b.qjlxmc,t2.xm,t2.xb,t2.bjmc, t2.lxdh,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.nj,t2.zybj,t2.zybjmc,d.gwid,d.shr,d.shyj, f.mc || '[' || decode(d.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����') || ']' shztmc");
		}
		sql.append(" , d.guid shid , f.gwz ");
		sql.append(" ,row_number() over(partition by t1.qjsqid order by d.shsj desc) rn ");
		//�㽭����ְҵ���Ի����ж���ٿ�ʼʱ���Ƿ�ʱ��
		if("12866".equals(Base.xxdm)){
			sql.append(", case when instr(t1.kssj,':') = '0' then  to_char((to_date(t1.kssj,'yyyy/mm/dd HH24:mi') + 1),'yyyy/mm/dd HH24:mi') else  to_char((to_date(t1.kssj,'yyyy/mm/dd HH24:mi')+1 / 24 / 60),'yyyy/mm/dd HH24:mi') end cssj");			
		}
		//�������⾭��ѧԺ���Ի�������¥�������Һţ�
		if("12303".equals(Base.xxdm)){
			sql.append(" , cwxx.lddm,cwxx.ldmc,cwxx.qsh");
		}
		sql.append(" from xg_rcsw_qjgl_qjsq t1");
		//���״̬
		//sql.append(" join xg_xtwh_shztb sh on t1.qjsqid = sh.ywid");

		//ѧ����Ϣ
		sql.append(" left join view_xsbfxx t2");
		sql.append(" on t1.xh = t2.xh");
		//����������Ի�����¥�������Һ�
		if("12303".equals(Base.xxdm)){
			sql.append(" left join view_xg_gygl_new_cwxx cwxx on t1.xh = cwxx.xh ");
		}
		//���״̬��Ϣ
		sql.append(" left join xg_xtwh_shztb d");
		sql.append(" on t1.qjsqid = d.ywid");
		//��ѯ���״̬
//		String shlx = t.getShzt();
//		if(!shlx.equals("dsh")){
//			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
//		}else{
//			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
//		}	
		//������Ϣ
		sql.append(" left join xg_rcsw_qjgl_qjjg qjjg on qjjg.qjsqid=t1.qjsqid");
		//���������Ϣ
		sql.append(" left join xg_rcsw_qjgl_qjlx b on t1.qjlxid=b.qjlxid ");
		//��˸�λ����Ϣ
		sql.append(" left join xg_xtwh_spgwyh e");
		sql.append(" on d.gwid = e.spgw");
		sql.append(" left join xg_xtwh_spgw f");
		sql.append(" on d.gwid = f.id");
		sql.append(" where e.spyh = '"+user.getUserName()+"' and t1.shzt<>9 and d.shzt<>9 ");

		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
		}else{
			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
		}	
		sql.append(" ) a where rn = 1 )a ");
		sql.append(" where 1 = 1 ");
//		sql.append(" and qjzt=1 ");  modify by zhuon
		sql.append(searchTjByUser);
		sql.append(shgwzByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ö�Ӧ������״̬
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-12 ����10:06:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqId
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getShid(String qjsqId,String gwid){
		StringBuffer sql=new StringBuffer();
		sql.append("select guid from xg_xtwh_shztb sh where sh.ywid=? and sh.gwid=?");
		return dao.getOneRs(sql.toString(), new String[]{qjsqId,gwid}, "guid");
	}
	/**
	 * 
	 * @����:��ȡ��Ӧ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-11 ����11:58:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getSplcInfo(QjshForm t){
		StringBuilder sql = new StringBuilder();
		sql
				.append("select * from (select t1.*,b.qjlxmc,t2.xm,t2.xb,t2.bjmc, t2.lxdh,t2.xydm,t2.zydm,t2.bjdm,t2.nj,d.gwid,d.shr,d.shyj, f.mc || '[' || decode(d.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����') || ']' shztmc");
		sql.append(" from xg_rcsw_qjgl_qjsq t1");
		//ѧ����Ϣ
		sql.append(" left join view_xsxxb t2");
		sql.append(" on t1.xh = t2.xh");
		//���״̬��Ϣ
		sql.append(" left join xg_xtwh_shztb d");
		sql.append(" on t1.qjsqid = d.ywid");
		//��ѯ���״̬
		String shlx = t.getShzt();
		if(null!=shlx&&shlx.equals("dsh")){
			sql.append(" and d.shzt=0  ");
		}else{
			sql.append(" and (d.shzt=1 or d.shzt=2) ");
		}
		//���������Ϣ
		sql.append(" left join xg_rcsw_qjgl_qjlx b on 1=1 ");
		sql.append(" and  t1.qjlxid=b.qjlxid");
		//��˸�λ����Ϣ
		sql.append(" left join xg_xtwh_spgwyh e");
		sql.append(" on d.gwid = e.spgw");
		sql.append(" left join xg_xtwh_spgw f");
		sql.append(" on d.gwid = f.id");
		sql.append(" where e.spyh = 'zf01') t5");
		sql.append(" where 1 = 1");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getQjsqid()});
	}
	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QjshForm t)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @��������ȡ��һ���������id
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����06:54:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String ��������
	 * @throws
	 */
	public String getNextId() {
		String qjlxid = dao.getOneRs(
				"select max(qjlxid)qjlxid from xg_rcsw_qjgl_qjlx ",
				new String[] {}, "qjlxid");
		String newId = String.valueOf(Integer.parseInt(qjlxid) + 1);
		if (newId.length() == 1) {
			newId = "0" + newId;
		}
		return newId;
	}

	/**
	 * 
	 * @����:��֤������ͣ�����Ѿ���ӹ������ٽ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����07:18:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return boolean ��������
	 * @throws
	 */
	public boolean checkQjlx(QjshForm qf) {
		// String
		// qjlxid=dao.getOneRs("select qjlxid from xg_rcsw_qjgl_qjgz where kssj<? and ?<=jssj",new
		// String[]{qf.getQjlxmc()},"qjlxid");
		// �����ȡ����Ӧ��id�����������
		return true;
	}

	public List<HashMap<String, String>> getQjlx() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select qjlxid,qjlxmc from xg_rcsw_qjgl_qjlx order by qjlxid asc");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	/**
	 * 
	 * @����:ɾ����Ӧ��ٽ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-24 ����05:56:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqId
	 * @return
	 * @throws Exception
	 * int �������� 
	 */
	public int deleteQjjgForQjsqId(String qjsqId) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete xg_rcsw_qjgl_qjjg qjjg where qjjg.qjsqid=?");
		return dao.runDelete(sql.toString(),new String[]{qjsqId});
	}
	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		this.setKey("qjsqid");
		this.setTableName("xg_rcsw_qjgl_qjsq");
		this.setClass(QjshForm.class);
	}
	
	/* (non-Javadoc)
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object)
	 */
	@Override
	public QjshForm getModel(QjshForm t) throws Exception {
		String sql = "select t1.*,t2.qjlxmc,decode(t1.sflx,'1','��','0','��','') sflxmc from xg_rcsw_qjgl_qjsq t1 left join xg_rcsw_qjgl_qjlx t2 on t1.qjlxid=t2.qjlxid where t1.qjsqid=?";
		return super.getModel(sql, new String[]{t.getQjsqid()});
	}

}
