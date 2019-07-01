/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-19 ����09:40:31 
 */  
package com.zfsoft.xgxt.gygl.qsdsgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������: ���ҵ�ʦά��
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-19 ����09:40:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QsdswhDao extends SuperDAOImpl<QsdswhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QsdswhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QsdswhForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		/*sql.append("select t.*")
		.append("  from (select a.lddm || '@@' ||a.qsh pk, a.*, c.ldmc, b.qsxb xb, b.ch, b.xydm, b.nj, d.bmmc ")
		.append("          from XG_GYGL_QSDS a")
		.append("          left join xg_gygl_new_qsxxb b")
		.append("            on a.lddm = b.lddm and a.qsh = b.qsh ")
		.append("          left join xg_gygl_new_ldxxb c")
		.append("            on a.lddm = c.lddm left join zxbz_xxbmdm d on b.xydm = d.bmdm) t")
		.append(" where 1 = 1 ")
		.append(searchTj);*/
		
		sql.append("select t1.* from view_new_dc_qsdsgl t1 where 1=1 ")
		.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> exportDskh(QsdswhForm t, User user)
	throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "c.cwh";
		}else{
			sb = "to_number(c.cwh)";
		}
		sql.append(" select t1.*,t2.cj,t2.xn,t2.xq,t2.nd,(select xqmc from xqdzb xqb where xqb.xqdm = t2.xq) xqmc,c.cws,c.xm qxxsxm,c.yqmc from ( ");
		sql.append(" select a.* from view_new_dc_qsdsgl a where 1=1 ");
		sql.append(searchTj);
		sql.append(" ) t1 ");
		sql.append(" left join (select zgh,cj,xn,xq,nd from XG_GYGL_QSDSKH where (xn || xq) = (select dqxn || dqxq from XTSZB where rownum = 1)) t2 on (t1.zgh = t2.zgh) ");
		sql.append(" left join (select a.lddm,a.ch,a.qsh,a.cwh,a.cws,a.yqmc,b.xm from view_xg_gygl_new_cwxx a left join ").append(Base.xsxxb+" b on a.xh=b.xh ");
		sql.append(" ) c on (t1.lddm=c.lddm and t1.qsh=c.qsh) ");
		sql.append(" where c.lddm is not null order by c.lddm,to_number(c.ch),c.qsh,"+sb+" ");
		
		Pages pages = t.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		t.setPages(pages);
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * ��ѯ������Ϣ���
	 */
	public List<String[]> getQsxxList(String lddm, String qsh) throws Exception{
			
		String[] outPutString = new String[] { "ldmc", "qsh", "cwh","qsxb", "xh", "xm", "xszymc", "xsbjmc" };
		StringBuilder sql = new StringBuilder();
		//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "a.cwh";
		}else{
			sb = "to_number(a.cwh)";
		}
		sql.append("( ");
		sql.append("select rownum r,a.*,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from bks_bjdm where bjdm=a.bjdm) cwbjmc from (select lddm||qsh||cwh pkValue,a.*,b.xm,b.zymc xszymc,b.zydm xszydm, b.bjmc xsbjmc,b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm, ");
		sql.append("(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis,(case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz ");
		sql.append("from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,"+sb+") a ");
		sql.append("where a.lddm=? and a.qsh=? ");
		sql.append(") ");

		return CommonQueryDAO.commonQueryNotFy(sql.toString()," where 1=1 order by cwh",new String[]{lddm, qsh},outPutString,"");
	}
	
	/** 
	 * ���ݴ�ѧ���Ի���ѯѧ����Ϣ
	 */
	public List<HashMap<String,String>> getxsxx(String lddm, String qsh) throws Exception{
		String sql = "select t1.xh,t1.xm,t1.xymc,t1.zymc,t1.bjmc,t2.cwh from view_xg_gygl_new_cwxx t2 left join view_xsxxb t1 on t2.xh = t1.xh where t2.xh is not null and t2.lddm = ? and t2.qsh = ?";
		return dao.getListNotOut(sql, new String[]{lddm,qsh});
	}
	

	
	/**
	 * 
	 * ��ȡ���ҵ�ʦ��Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-21 ����09:39:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getQsdsxx(QsdswhForm model){
		    StringBuilder sql = new StringBuilder();
					
			sql.append("select * from (select a.xqfdyxm,a.xqfdylxdh,a.lddm,a.qsh,a.zgh,a.bz, b.ch, b.qsxb, b.xydm, b.nj,c.ldmc,c.ldxb , c.ldcs , c.qsch ,c.sfhlc,g.xm dsxm,g.bmmc dw,g.lxdh");
			if(Base.xxdm.equalsIgnoreCase("10351")){//���ݴ�ѧ���Ի��ֶ�
				sql.append(" ,a.rqkssj,a.rqjssj");
			}
			sql.append(" from XG_GYGL_QSDS a ");
			sql.append(" left join Xg_Gygl_New_Qsxxb b on a.lddm = b.lddm and a.qsh = b.qsh ");
			sql.append(" left join Xg_Gygl_New_Ldxxb c on a.lddm = c.lddm");
			sql.append(" left join view_fdyxx g on a.zgh=g.zgh");
			sql.append(" ) where  lddm = ? and qsh = ?");
				
		
			
		String lddm = model.getLddm();
		String qsh = model.getQsh();
		
		return dao.getMapNotOut(sql.toString(), new String[]{lddm, qsh});
	}
	
	/**
	 * 
	 * @����:����ѧ�Ż�ȡ���ҵ�ʦ��Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-24 ����10:56:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getQsdsxxListByXh(String xh){
		StringBuffer sql = new StringBuffer("select t.* from (select a.lddm || '@@' || a.qsh pk, ");
		sql.append("    a.lddm,a.qsh,a.bz,a.zgh,a.nd,a.xqfdyxm,a.xqfdylxdh,a.rqkssj,a.rqjssj,  ")
		.append("       c.ldmc,b.qsxb xb,b.ch,b.xydm,b.nj,d.bmmc,e.xh,f.xm dsxm,f.bmmc dw,f.lxdh  ")
		.append("          from XG_GYGL_QSDS a ")
		.append("          left join xg_gygl_new_qsxxb b ")
		.append("            on a.lddm = b.lddm ")
		.append("           and a.qsh = b.qsh ")
		.append("          left join xg_gygl_new_ldxxb c ")
		.append("            on a.lddm = c.lddm ")
		.append("          left join zxbz_xxbmdm d ")
		.append("            on b.xydm = d.bmdm ")
		.append("          left join view_fdyxx f ")
		.append("            on a.zgh = f.zgh ")
		.append("          left join XG_GYGL_NEW_CWXXB e on a.lddm = e.lddm and a.qsh = e.qsh) t ")
		.append(" where xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
/**	
 * @����: ���������ж��Ƿ����ͬ����¼��
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2015-11-11 ����09:22:49
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param form
 * @return
 * String �������� 
 * @throws
 */
public String checkExistForSave(QsdswhForm form){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String lddm =  form.getLddm();
		String qsh =  form.getQsh();
		if(Base.xxdm.equals("10351")){
			sql.append(" select count(1) num from (select a.*,(case when a.rqjssj is null then sysdate+1 else to_date((a.rqjssj || '00:00:00'),'yyyy-mm-dd hh24:mi:ss') end) rzsj from XG_GYGL_QSDS a");
			sql.append(" where a.lddm = ? and a.qsh = ?) a where a.rzsj > sysdate");
		}else{			
			sql.append(" select count(1) num from XG_GYGL_QSDS ");
			sql.append(" where lddm = ? and qsh = ?  ");
		}
		params.add(lddm);
		params.add(qsh);
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}
	/**
	 * ��������������ҵ�ʦ��Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-21 ����10:00:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveQsdsxx(QsdswhForm model) throws Exception{
		// ��ȡϵͳ����
		String select = "select LDDM , QSH , BZ from XG_GYGL_QSDS where LDDM = ? and QSH = ? ";
		String update = "update XG_GYGL_QSDS set zgh = ? , BZ = ?,xqfdyxm=?,xqfdylxdh=? where LDDM = ? and QSH = ? ";
		String update_wzdx = "update XG_GYGL_QSDS set zgh = ? , BZ = ?,xqfdyxm=?,xqfdylxdh=?,rqkssj=?,rqjssj=? where LDDM = ? and QSH = ?";
		
		/**
		 * ��ѯ
		 */
		List<HashMap<String, String>> ls = dao.getListNotOut(select, new String[]{model.getLddm() , model.getQsh()});

		/**
		 * ����
		 */
		if(ls != null && ls.size() >= 1){
			if(Base.xxdm.equalsIgnoreCase("10351")){//���ݴ�ѧ���Ի�
				return dao.runUpdate(update_wzdx, new String[]{model.getZgh(), model.getBz() ,model.getXqfdyxm(),model.getXqfdylxdh(),model.getRqkssj(),model.getRqjssj(), model.getLddm() , model.getQsh()});
			}else{				
				return dao.runUpdate(update, new String[]{model.getZgh(), model.getBz() ,model.getXqfdyxm(),model.getXqfdylxdh(), model.getLddm() , model.getQsh()});
			}
		/**
		 * ����
		 */
		}else{
			return runInsert(model);
		}
		
	}
	
	/**
	 * 
	 * ɾ����Ϣ����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-21 ����11:55:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ldxx
	 * @param qsh
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int[] deleteDsdsxxPl(List<String[]> pks) throws Exception{
		String sql = "delete from XG_GYGL_QSDS where LDDM = ? and QSH = ?";
		return dao.runBatch(sql, pks);
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setTableName("XG_GYGL_QSDS");
		setClass(QsdswhForm.class);
	}

}
