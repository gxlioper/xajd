package com.zfsoft.xgxt.jskp.xmsb;



import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jskp.sbjg.JskpXmsbjgForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ʵ����
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2017-7-5 ����04:44:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JskpXmsbDao extends SuperDAOImpl<JskpXmsbForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JskpXmsbForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JskpXmsbForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		if(t.getSbzt().equals("1")){//���걨
			sql.append(" select a.*,d.xm fzrxm,e.bmmc zdbmmc,decode(b.xmdl,'gdx','�̶���','������') xmdlmc,b.xmmc,c.xmlbmc from xg_jskp_xmsbb a left join xg_jskp_xmjgb b on a.xmid=b.xmid left join xg_jskp_xmlbb c on b.xmlb=c.xmlbdm ");
			sql.append(" left join yhb d");
			sql.append(" on b.fzr = d.yhm");
			sql.append(" left join zxbz_xxbmdm e on b.zdbm=e.bmdm");
			sql.append(" where  a.xh='"+user.getUserName()+"' and a.shzt != '0' ");
		}else{
			sql.append(" select * from (select a.*, d.xm fzrxm,e.bmmc zdbmmc,decode(a.xmdl,'gdx','�̶���','������') xmdlmc, c.xmlbmc,");
			sql.append(" case when a.kgzt = 1 and sysdate between to_date(nvl(a.sbkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
			sql.append(" and to_date(nvl(a.sbjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end sbkg ");
			sql.append(" from xg_jskp_xmjgb a  left join xg_jskp_xmlbb c on a.xmlb=c.xmlbdm " );
			sql.append(" left join yhb d");
			sql.append(" on a.fzr = d.yhm");
			sql.append(" left join zxbz_xxbmdm e on a.zdbm=e.bmdm");
			sql.append(" where a.xmdl='gdx') where sbkg = 'true' ");
		}
		sql.append(")t where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:�ж��Ƿ������д��¼
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����04:29:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param czlx
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public String xmsbCheck(JskpXmsbForm model,User user) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select case when jgsbzq='xn' and years>0 then '0' when jgsbzq='month' and mons>0 then '0' ");
		sql.append(" when jgsbzq='day' and days>0 then '0' else '1' end jgzq   from (select a.*,b.jgsbzq, ");
		sql.append(" months_between(to_date(substr(a.sbsj,1,10),'yyyy-mm-dd'),to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'))/12 years,"); 
		sql.append(" months_between(to_date(substr(a.sbsj,1,10),'yyyy-mm-dd'),to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')) mons,");
		sql.append(" to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')-to_date(substr(a.sbsj,1,10),'yyyy-mm-dd') days,");
        sql.append(" rownum rn from xg_jskp_xmsbb a left join xg_jskp_xmjgb b ");
        sql.append(" on a.xmid=b.xmid where a.xh =? and a.xmid=? order by sbsj desc) where rn=1");
        return dao.getOneRs(sql.toString(), new String[]{user.getUserName(),model.getXmid()}, "jgzq");
	}

	public JskpXmsbForm getModel(JskpXmsbForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.xmmc  from xg_jskp_xmsbb t1 left join xg_jskp_xmjgb t2 on t1.xmid = t2.xmid");
		sql.append(" where t1.sqid=? ");
		return super.getModel(sql.toString(),new String[]{model.getSqid()});
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(JskpXmsbForm.class);
		super.setKey("sqid");
		super.setTableName("xg_jskp_xmsbb");
		
	}
	
	/**
	 * @����: ����������Ϊ0ʱ������б�ѧ�Ų鿴��xhLink��
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-11-22 ����03:03:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXxck (String id) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select xmmc,lxsj,lxly,filepath fjid,sqid from xg_jskp_xmsqb where sqid = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{id});
	}
	
}
