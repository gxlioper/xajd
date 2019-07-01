/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-24 ����04:56:39 
 */  
package com.zfsoft.xgxt.xsxx.cxpy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;


import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(�����������) 
 * @���ߣ� CMJ [���ţ�913]
 * @ʱ�䣺 2013-7-24 ����04:56:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxpyDao extends SuperDAOImpl<CxpyForm> {



	@Override
	public List<HashMap<String, String>> getPageList(CxpyForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(CxpyForm model, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from (select a.sqsj,a.xh,sjly,pk,cxdj cxdjdm,(select cxdjmc from xsxx_cxdjdmb e where a.cxdj=e.cxdjdm) cxdjmc,bzr,cxpy,");
		sql.append("xm,xb,nj,xymc,zymc,bjmc,xydm,zydm,bjdm,a.xn,a.xq,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc from xsxx_cxpyb a left join  view_xsjbxx b on a.xh=b.xh) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}


	public List<HashMap<String, String>> getCxdjList(){
		String sql="select cxdjdm dm,cxdjmc mc from  xsxx_cxdjdmb order by cxdjdm ";
		return dao.getListNotOut(sql, new String[]{});
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xsxx_cxpyb");
		super.setKey("pk");
		super.setClass(CxpyForm.class);

	}


	/** 
	 * @����:TODO(��ȡѧ��)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-7-25 ����05:03:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsPageList(CxpyForm model, User user) throws Exception{
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, rownum r  ");
		sql.append(" from (select a.* from view_xsjbxx a where not exists (select 1 from xsxx_cxpyb c where a.xh = c.xh and c.xn='"+model.getXn()+"' and c.xq='"+model.getXq()+"' ) ");
		sql.append("order by a.nj, a.xydm, a.zydm, a.bjdm, a.xh) a where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}


	/** 
	 * @����:TODO(��ȡѡ���ѧ��list)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-7-25 ����07:13:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xhs
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXzxsList(String xhs) {
		// TODO �Զ����ɷ������
		String[] xh=xhs.split(",");
		StringBuilder sql=new StringBuilder();
		sql.append("select * from view_xsjbxx where 1=1 ");
		StringBuilder whereSql=new StringBuilder(" and(");
		for (int i=0;i<xh.length;i++){
			if(i!=xh.length-1){
				whereSql.append(" xh=? or ");
			}else{
				whereSql.append(" xh=? )");
			}
		}
		sql.append(whereSql);
		
		return dao.getList(sql.toString(), xh, new String[]{"xh","xm","xymc","zymc","bjmc"});
	}


	/**
	 * @throws SQLException  
	 * @����:TODO(�������������Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-7-26 ����10:48:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public boolean save(CxpyForm model) throws SQLException {
		// TODO �Զ����ɷ������
		StringBuilder sql=new StringBuilder();
		sql.append("insert into xsxx_cxpyb(xn,xq,xh,cxdj,cxpy,bzr,sqsj) values(?,?,?,?,?,?,?)");
		List<String[]> params=new ArrayList<String[]>();
		String xhs=model.getXhs();
		String[] xh=xhs.split(",");
		HashSet<String> set=new HashSet<String>();
		for(int i=0;i<xh.length;i++){
			set.add(xh[i]);
		}
		for (String string : set) {
			String[] param=new String[7];
			param[0]=model.getXn();
			param[1]=model.getXq();
			param[2]=string;
			param[3]=model.getCxdjdm();
			param[4]=model.getCxpy();
			param[5]=model.getBzr();
			param[6]=model.getSqsj();
			params.add(param);
		}
		int[] count=dao.runBatch(sql.toString(), params);
		return count.length==set.size();
	}
	
	@Override
	public CxpyForm getModel(CxpyForm model) throws Exception{
		StringBuilder sql=new StringBuilder();
		CxpyForm myForm=new CxpyForm();
		sql.append("select * from (select a.sqsj,a.xh,pk,cxdj,(select cxdjmc from xsxx_cxdjdmb e where a.cxdj=e.cxdjdm) cxdjmc,bzr,cxpy,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc, ");
		sql.append("xm,xb,nj,xymc,zymc,bjmc,xydm,zydm,bjdm,a.xn,xq from xsxx_cxpyb a left join  view_xsjbxx b on a.xh=b.xh) where 1=1 and pk=?");
		HashMap<String, String> map=dao.getMap(sql.toString(), new String[]{model.getPk()}, new String[]{"sqsj","xh","xm","nj","pk","xymc","cxdjmc","xqmc","bzr","zymc","bjmc","xn","xq","cxdj","cxpy"});
		BeanUtils.copyProperties(myForm,map );
		return myForm;
	}

	
	public String getCount(CxpyForm t){
		String[] params = null;
		StringBuilder sql = new StringBuilder("select count(1) c from xsxx_cxpyb where xh = ? and xn=? and xq=?");
		
		if (!StringUtil.isNull(t.getPk())){
			sql.append(" and pk <> ?");
			params = new String[]{t.getXh(),t.getXn(),t.getXq(),t.getPk()};
		} else {
			params = new String[]{t.getXh(),t.getXn(),t.getXq()};
		}
		return dao.getOneRs(sql.toString(), params , "c");
	}
	
	/** 
	 * 
	 * @����:�޸�ѧ����������
	 * @���ߣ��ո־� [1075]
	 * @���ڣ�2015-4-3 ����02:15:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updCxpy(CxpyForm model) {
		
		//����SQL
		StringBuilder sql=new StringBuilder();
		sql.append(" update ");
		sql.append(" xsxx_cxpyb ");
		sql.append(" set ");
		sql.append(" cxdj = ? , ");
		sql.append(" cxpy = ? , ");
		sql.append(" bzr = ? , ");
		sql.append(" sjly = ? ");
		sql.append(" where ");
		sql.append(" xh = ?  ");
		sql.append(" and xn=?  ");
		sql.append(" and xq=?  ");

		boolean flag = false;
		try {
			flag = dao.runUpdate(sql.toString(), 
					new String[] {model.getCxdj(),
						model.getCxpy(),
						model.getBzr(),
						model.getSjly(),
						model.getXh(),
						model.getXn(),
						model.getXq()});
			
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public HashMap<String,String> getCxpyByXhXnXq(String xh,String xn,String xq){
		StringBuilder sql=new StringBuilder();
		sql.append("select * from (select a.xh,pk,cxdj,(select cxdjmc from xsxx_cxdjdmb e where " );
		sql.append("a.cxdj=e.cxdjdm) cxdjmc,bzr,cxpy,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc, ");
		sql.append("xm,xb,nj,xymc,zymc,bjmc,xydm,zydm,bjdm,a.xn,xq from xsxx_cxpyb a left join ");
		sql.append("view_xsjbxx b on a.xh=b.xh) where xh=? and xn=? and xq=?");
		return dao.getMapNotOut(sql.toString(),new String[]{xh,xn,xq});
	}
	
	public HashMap<String,String> getCxpyByXhXn(String xh,String xn){
		StringBuilder sql=new StringBuilder();
		sql.append("select * from (select a.xh,pk,cxdj,(select cxdjmc from xsxx_cxdjdmb e where " );
		sql.append("a.cxdj=e.cxdjdm) cxdjmc,substr(sqsj,0,4) y,substr(sqsj,6,2) m, substr(sqsj,9,2) d,bzr,cxpy,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc, ");
		sql.append("xm,xb,nj,xymc,zymc,bjmc,xydm,zydm,bjdm,a.xn,xq from xsxx_cxpyb a left join ");
		sql.append("view_xsjbxx b on a.xh=b.xh) where xh=? and xn=?");
		return dao.getMapNotOut(sql.toString(),new String[]{xh,xn});
	}
	//����ҽҩ���Ի�����
	public HashMap<String,String> getCxpyForXzyy(String xh,String xn,String xq){
		StringBuilder sql=new StringBuilder();
		sql.append("select * from (select a.xh,pk,cxdj,(select cxdjmc from xsxx_cxdjdmb e where " );
		sql.append("a.cxdj=e.cxdjdm) cxdjmc,substr(sqsj,0,4) y,substr(sqsj,6,2) m, substr(sqsj,9,2) d,bzr,cxpy,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc, ");
		sql.append("xm,xb,nj,xymc,zymc,bjmc,xydm,zydm,bjdm,a.xn,xq from xsxx_cxpyb a left join ");
		sql.append("view_xsjbxx b on a.xh=b.xh) where xh=? and xn=? and xq = ? order by xn ");
		return dao.getMapNotOut(sql.toString(),new String[]{xh,xn,xq});
	}
}
