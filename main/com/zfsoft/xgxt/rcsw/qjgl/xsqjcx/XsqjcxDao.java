/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-3 ����03:47:32 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.xsqjcx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2016-3-3 ����03:47:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsqjcxDao extends SuperDAOImpl<XsqjcxForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsqjcxForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsqjcxForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		SearchModel searchmodel = t.getSearchModel();
		String[] searchTj_qjksjc = searchmodel.getSearch_tj_qjksjc();
		String[] searchTj_qjjsjc = searchmodel.getSearch_tj_qjjsjc();
		String[] searchTj_kcmc = searchmodel.getSearch_tj_kcmc();
		String[] searchTj_qjkssj= searchmodel.getSearch_tj_kssj();
		String[] searchTj_qjjssj = searchmodel.getSearch_tj_jssj();
		searchmodel.setSearch_tj_qjksjc(null);
		searchmodel.setSearch_tj_qjjsjc(null);
		searchmodel.setSearch_tj_kcmc(null);
		searchmodel.setSearch_tj_kssj(null);
		searchmodel.setSearch_tj_jssj(null);
		
		String searchTj = SearchService.getSearchTj(searchmodel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(searchmodel);
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ");
		sql.append(" sys_zjly_xsqjcx_view_all t");
		if(StringUtils.isNull(t.getFlag())){
			sql.append(" where t.zgh like '");
			sql.append("%"+user.getUserName()+"%");
			sql.append("'");
			sql.append(qjjcSqlString(searchTj_qjksjc,searchTj_qjjsjc,searchTj_kcmc,searchTj_qjkssj,searchTj_qjjssj));
			sql.append(" and 1=1");
		}else{
			sql.append(" where 1=1 ");
			sql.append(qjjcSqlString(searchTj_qjksjc,searchTj_qjjsjc,null,searchTj_qjkssj,searchTj_qjjssj));
			sql.append(searchTjByUser);
		}
	
		sql.append(" ");
		sql.append(searchTj);
	
		
//		if (sql.toString().split("qjkssj")!= null && sql.toString().split("qjkssj").length >=3) {
//			sql.replace(sql.toString().lastIndexOf("qjkssj"), sql.toString().lastIndexOf("qjkssj")+6, "qjjssj");
//		} else if(sql.toString().split("qjkssj")!= null && sql.toString().split("qjkssj").length >1) {
//			if(sql.toString().indexOf(">") != -1){
//				sql.replace(sql.toString().lastIndexOf(">"), sql.toString().lastIndexOf(">")+1, "");
//			}else{
//				sql.replace(sql.toString().lastIndexOf("qjkssj"), sql.toString().indexOf("qjkssj")+6, "qjjssj");
//				sql.replace(sql.toString().lastIndexOf("<"), sql.toString().lastIndexOf("<")+1, "");
//			}
//			
//		};
		
		sql.append(" order by xymc,zymc,bjmc,xn,xq desc");
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}
	
	public HashMap<String, String> Qjsqck(XsqjcxForm para){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from sys_zjly_xsqjcx_view_all ");
		sql.append(" where id = ? and xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{para.getId(),para.getXh()});
	}
	
	public String qjjcSqlString(String[]qjksjc,String[]qjjsjc,String[]kcmc,String[] qjkssj,String[] qjjssj){
		StringBuilder sql = new StringBuilder();
		if(qjksjc != null && qjksjc.length >0){
			sql.append(" and (");
			for (int i = 0; i < qjksjc.length; i++) {
				sql.append(" qjksjc like '%"+qjksjc[i]+"%' ");
				if(i != qjksjc.length-1){
					sql.append(" or ");
				}
				
			}
			sql.append(")");
			
		}
		if(qjjsjc != null && qjjsjc.length >0){
			sql.append(" and (");
            for (int i = 0; i < qjjsjc.length; i++) {
            	sql.append(" qjjsjc like '%"+qjjsjc[i]+"%' ");
				if(i != qjjsjc.length-1){
					sql.append(" or ");
				}
			}
            sql.append(")");
		}
		if(kcmc != null && kcmc.length >0){
			sql.append(" and (");
            for (int i = 0; i < kcmc.length; i++) {
            	sql.append(" kcmc like '%"+kcmc[i]+"%' ");
				if(i != kcmc.length-1){
					sql.append(" or ");
				}
			}
            sql.append(")");
		}
		if(qjkssj == null || qjkssj.length == 0 || StringUtils.isNull(qjkssj[0]) ){
			qjkssj = null;
		}
		if(qjjssj == null || qjjssj.length == 0 || StringUtils.isNull(qjjssj[0])){
			qjjssj = null;
		}
		/**
		 * ����Ĳ�ѯ������Ϊ��ʵ���������ǣ�����ϸ��⣬���Ժ����������뿴����
		 * ����ѡ��2016-09-10��2016-09-12
		 * ��ô���ϵ������(1)2016-09-08�� 2016-09-13��һͷһβȫ�������⣬���غ�
		 *  (2)2016-09-10 ��2016-09-12�ţ���������
		 *  (3)2016-09-11��2016-09-13�ţ�β�������⣬���غ�
		 *  (4)2016-09-09��2016-09-11��ͷ�������⣬���غ�
		 *  ��֮��ѡ���������غϵ����ݲ��ܱ�������
		 */
		if((qjkssj != null ) || (qjjssj != null )){
			if((qjkssj != null && qjjssj == null) || (qjkssj == null && qjjssj != null)){
				String para = qjkssj != null ? qjkssj[0] : qjjssj[0];
				sql.append(" and  ((to_number("+SearchService.replaceTime("qjkssj")+") <= "+para+" )");
				sql.append(" and (to_number("+SearchService.replaceTime("qjjssj")+") >= "+para+" )");
				sql.append(")");
			}else{
				String kssj = qjkssj[0];
				String jssj = qjjssj[0];
				sql.append(" and (");
				sql.append(" (to_number("+SearchService.replaceTime("qjkssj")+") <= "+jssj+" )");
				sql.append(" and ");
				sql.append(" (to_number("+SearchService.replaceTime("qjjssj")+") >= "+kssj+" )");
				sql.append(" )");
				sql.append(" ");
				sql.append(" ");
			}
		}
		return sql.toString();
		 
	}

	/** 
	 * @����:ȡ������ʷ�б�
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-4-11 ����10:02:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSplsList(XsqjcxForm model) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select taskname,to_char(starttime,'yyyy-mm-dd hh24:mi:ss') starttime,to_char(endtime,'yyyy-mm-dd hh24:mi:ss') endtime,exefullname,to_char(opinion) opinion,");
		sql.append(" case when checkstatus='-1' then '��������'");
		sql.append(" when  checkstatus='1'  then 'ͬ��'");
		sql.append(" when  checkstatus='2'  then '����'");
		sql.append(" when  checkstatus='3'  then '����'");
		sql.append(" when  checkstatus='4'  then '����'");
		sql.append(" when  checkstatus='14' then '��ֹ'");
		sql.append(" when  checkstatus='15' then '��ͨ���'");
		sql.append(" when  checkstatus='21' then 'ת��'");
		sql.append(" when  checkstatus='23' then '����ִ����'");
		sql.append(" when  checkstatus='24' then '���ص�������'");
		sql.append(" when  checkstatus='28' then '�����'");
		sql.append(" when  checkstatus='33' then '�ύ'");
		sql.append(" when  checkstatus='35' then '��Ԥ'");
		sql.append(" when  checkstatus='38' then '��ת'");
		sql.append(" when  checkstatus='40' then '���ύ' end checkstatus");
		sql.append(" from zfsoft_bpmx.bpm_task_opinion where actinstid =? order by starttime,endtime");
		return dao.getListNotOut(sql.toString(),new String[]{model.getActinstid()});
	}
}
