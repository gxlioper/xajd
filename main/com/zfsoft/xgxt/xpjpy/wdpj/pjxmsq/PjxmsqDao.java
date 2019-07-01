/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-9 ����11:23:29 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.pjxmsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-9 ����11:23:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxmsqDao extends SuperDAOImpl<PjxmsqModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PjxmsqModel t)
			throws Exception {

		SearchModel searchModel = t.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputValue = SearchService.getTjInput(searchModel);

		StringBuilder sql = new StringBuilder();
		
		List<HashMap<String , String>> retData = null;
		
		String currXn = CsszService.getPjzq().get("xn");// ��ǰѧ��
		String currXq = CsszService.getPjzq().get("xq");// ����ѧ��

		//���ֽ�ѧ��ͱ��ý���
		if(!"2".equals(t.getXzdm())){
			t.setXzdm("1");
		}
		
		if(StringUtils.isEqual("wsq", t.getQueryType())){
			
			sql.append("select t.* ,''CONDITIONCHECKRESULT, t.sqkssj || nvl2(t.sqkssj || t.sqjssj , '~' ,'') || t.sqjssj as sqsjd , a.xmlxmc as xmlx , ").
			append("t3.shzt,t3.sqid,t3.splc,decode(t3.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����','δ����') shztmc ").
			append("   from xg_pjpy_new_pjxmb t  left join  ");
			
			if("2".equals(t.getXzdm()))
			{//���ý���
				sql.append("xg_pjpy_new_xmlx a ");
			}
			else{//��ѧ��
				sql.append("xg_pjpy_new_jxjxmlx a ");
			}
			
			sql.append("  on t.lxdm = a.xmlxdm").
			append(" left join (select * from xg_pjpy_new_xmsq b where b.xh = '"+t.getXh()+"') t3 on t.xmdm = t3.xmdm ").
			append("  where not exists").
			append("  (select 1").
			append("           from xg_pjpy_new_xmsq t2").
			append("          where t.xmdm = t2.xmdm").
			append("            and t2.xh = '"+t.getXh()+"'  and t2.shzt not in ('0' , '3'))").
			append("    and 1=1").
			append(" and t.xn = '"+currXn+"'").
			append(" and t.xq = '"+currXq+"'");
			//���ϳ��д�ѧ���Ի�
			if(Base.xxdm.equals("11527")){
				sql.append(" and t.sqkg = '1'");
				sql.append(" and sysdate between to_date(nvl(substr(t.sqkssj,1,10), '1990-01-01'),'yyyy-mm-dd')");
				sql.append(" and to_date(nvl(substr(t.sqjssj,1,10), '2020-01-01'),'yyyy-mm-dd')+1");
			}
			sql.append(" and t.xzdm = '" + t.getXzdm() + "' ");

			sql.append(searchTj);

			sql.append(" order by sqkg desc,to_number(t.xsxh) ");

			retData = getPageList(t, sql.toString(), inputValue);
			
		}else if(StringUtils.isEqual("ysq", t.getQueryType())){
			sql.append("select t.*,xn||xqmc pjzq ");
			
			if("2".equals(t.getXzdm()))
			{//���ý���
				sql.append("from VIEW_NEW_DC_PJPY_JXSQ t  ");
			}
			else{//��ѧ��
				sql.append("from VIEW_NEW_DC_PJPY_JXJSQ t  ");
			}
			
			sql.append("where t.xh='"+t.getXh()+"' ");

			//���ֽ�ѧ��ͱ��ý���
//			if(!"2".equals(t.getXzdm())){
//				t.setXzdm("1");
//			}
			sql.append(" and t.xzdm = '" + t.getXzdm() + "' ");
			sql.append(searchTj);
			sql.append(" and t.shzt not in ('0' , '3') order by sqsj desc");
			/*
			sql.append("select t.*,case when t.shzt in ('0', '3') or nvl(a.shzt, 0) in ('0') then 'yes' else 'no' end as ff,xn||xqmc pjzq ");
			sql.append("from VIEW_NEW_DC_PJPY_JXSQ t left join xg_xtwh_shztb a on t.sqid=a.ywid  left join XG_XTWH_SPBZ b ");
			sql.append("on a.lcid = b.splc and a.gwid = b.spgw where b.xh='1' and t.xh=?  order by to_number(t.xsxh) ");
			*/
			retData = getPageList(t, sql.toString(), inputValue);
		}
		
		return retData;
		
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PjxmsqModel t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(PjxmsqModel.class);
		super.setTableName("xg_pjpy_new_xmsq");
		super.setKey("sqid");
	}

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-10 ����05:19:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param jdid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean insertDbjd(String id,String jdid) throws Exception{
		
		String sql = "insert into xg_pjpy_new_xmsh(sqid,shid) values (?,?)";
		
		return dao.runUpdate(sql, new String[]{id,jdid});
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object, java.lang.String, java.lang.String[])
	 */
	
	@Override
	protected PjxmsqModel getModel(PjxmsqModel t, String sql, String[] input)
			throws Exception {
		// TODO �Զ����ɷ������
		return super.getModel(t, sql, input);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object)
	 */
	
	@Override
	public PjxmsqModel getModel(PjxmsqModel t) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.*,t2.xmmc,t2.lxdm,t2.xzdm,t2.xmje,t3.xqmc from xg_pjpy_new_xmsq t1 ");
		sql.append(" left join xg_pjpy_new_pjxmb t2 on t1.xmdm=t2.xmdm");
		sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm");
		sql.append(" where t1.sqid=?");
		return super.getModel(t, sql.toString(), new String[]{t.getSqid()});
	}

	
	/**
	 * 
	 * @����: ��ѯ�����¼����Ӧ��˼�¼�б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-10 ����05:19:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getSpjlList(String sqid){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.id guid,t1.shid,t1.shzt,t1.shsj,t1.shr,t1.shyj,t2.xmdm,t2.xmmc,t3.xm,t4.mc gwmc,");
		sql.append(" decode(t1.shzt,'0','δ���','1','ͨ��','2','��ͨ��','3','�˻�','9','ȡ�����') shztmc ");
		sql.append(" from xg_pjpy_new_xmsh t1 left join xg_pjpy_new_pjxmb t2 on t1.pdjx=t2.xmdm ");
		sql.append(" left join yhb t3 on t1.shr=t3.yhm left join xg_xtwh_spgw t4 on t1.shid=t4.id ");
		sql.append(" where t1.sqid=?  order by shsj asc");
		
		return dao.getListNotOut(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * 
	 * @����: ��ѯ�����¼����Ӧ�Ĵ���˼�¼��Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-10 ����05:19:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> ��������
	 */
	public HashMap<String,String> getDshGwid(String sqid){
		
		String sql = "select * from xg_pjpy_new_xmsh where sqid=? and shzt=0";
		
		return dao.getMapNotOut(sql, new String[]{sqid});
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-10 ����07:16:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDsgw(String sqid){
		
		String sql = "select shid from xg_pjpy_new_xmsh where sqid=? and shzt='0'";
		
		return dao.getOneRs(sql, new String[]{sqid}, "shid");
	}

	/** 
	 * @����:�ж�ѧ���Ƿ���ڲ�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-16 ����05:22:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param csszModel
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String isExistCpz(CsszModel csszModel, String xh) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) count from xg_pjpy_new_cpmdb where xn = ? and xq = ? and xh = ? and bjdm is not null ");
		
		return dao.getOneRs(sql.toString(), new String[]{csszModel.getXn(),csszModel.getXq(),xh}, "count");
	}
	
	
	/**
	 * 
	 * @����:�õ�ĳѧ����������Ŀ����ϸ��Ϣ (copy from mobile)
	 * @���ߣ�ligl
	 * @���ڣ�2014-3-26 ����04:28:45
	 * @�޸ļ�¼: 
	 * @param xh
	 * @param xmdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getYsqxmDetail(String xh,String xmdm)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,case when t.shzt in ('0', '3') or nvl(a.shzt, 0) in ('0') then 'yes' else 'no' end as ff ");
		sql.append("from VIEW_NEW_DC_PJPY_JXSQ t left join xg_xtwh_shztb a on t.sqid=a.ywid  left join XG_XTWH_SPBZ b ");
		sql.append("on a.lcid = b.splc and a.gwid = b.spgw where b.xh='1' and t.xh=? and xmdm=?  order by to_number(t.xsxh) ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xmdm});
		
	}	
	
	
	
	/**
	 * 
	 * @����:��ѯ�������������Ŀ(�����)
	 * @���ߣ�ligl
	 * @���ڣ�2014-3-28 ����02:12:56
	 * @�޸ļ�¼: 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYsqxmAllYsh(SqshModel model,User user) throws Exception {
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "e", "xydm", "bjdm");		
		StringBuilder sb = new StringBuilder();
	
		sb.append("select xn,xq,dqxmdm,xmmc,count(1) sqrs from (");
		sb.append(" select a.xn, a.xq, a.dqxmdm, a.xh, b.shzt, c.xmmc,b.gwid,");
		sb.append(" row_number() over(partition by b.ywid order by b.shsj desc) as lvl");
		sb.append(" from xg_pjpy_new_xmsq a");
		sb.append(" left join (select * from (select t1.*,row_number() over(partition by t1.ywid order by t1.shsj desc) rn from xg_xtwh_shztb t1) where rn=1) b");
		sb.append(" on a.sqid = b.ywid");
		sb.append(" left join xg_pjpy_new_pjxmb c");
		sb.append(" on a.dqxmdm = c.xmdm");
		sb.append(" left join xg_pjpy_new_cpmdb d");
		sb.append(" on a.xh = d.xh");
		sb.append(" and a.xn = d.xn");
		sb.append(" and a.xq = d.xq");
		sb.append(" left join view_njxyzybj_all e");
		sb.append(" on d.bjdm = e.bjdm");
		sb.append(" left join xg_xtwh_spgwyh f");
		sb.append(" on b.gwid=f.spgw");
		sb.append(" where b.shzt not in ('0', '4')");
		sb.append(" and d.xh is not null");
		sb.append(" and spyh = '"+user.getUserName()+"'");
		sb.append(searchTjByUser);
		sb.append(" ) where lvl = '1'  group by xn,xq,dqxmdm,xmmc");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}
	
	
	/**
	 * 
	 * @����:��ѯ�������������Ŀ
	 * @���ߣ�ligl
	 * @���ڣ�2014-3-26 ����03:33:46
	 * @�޸ļ�¼: 
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */	
	public List<HashMap<String, String>> getYsqxmAll(SqshModel model,User user) throws Exception {
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "e", "xydm", "bjdm");		
		StringBuilder sb = new StringBuilder();
	
		sb.append("select dqxmdm,xmmc,sum(dsh) dsh,count(1) sqrs from (");
		sb.append(" select a.xn, a.xq, a.dqxmdm, a.xh,c.xmmc,b.gwid,");
		sb.append(" case when b.shzt in ('0', '4') then 1 else 0 end dsh");
		sb.append(" from xg_pjpy_new_xmsq a");
		sb.append(" left join (select * from (select t1.*,row_number() over(partition by t1.ywid order by t1.shsj desc) rn from xg_xtwh_shztb t1) where rn=1) b on a.sqid = b.ywid");
		sb.append(" left join xg_pjpy_new_pjxmb c on a.dqxmdm = c.xmdm");
		sb.append(" left join xg_pjpy_new_cpmdb d on a.xh = d.xh and a.xn = d.xn and a.xq = d.xq");
		sb.append(" left join view_njxyzybj_all e on d.bjdm = e.bjdm");
		sb.append(" left join xg_xtwh_spgwyh f on b.gwid=f.spgw");
		sb.append(" where d.xh is not null");
		sb.append(" and spyh = '"+user.getUserName()+"'");
		sb.append(searchTjByUser);
		sb.append("  )   where xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sb.append(" group by dqxmdm,xmmc");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}
	
	
	/**
	 * 
	 * @����: ������˼�������������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-16 ����08:36:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllShyjList(String xh, String xn, String xq, String xmdm) {
		
		StringBuffer sql = new StringBuffer();	
		sql.append(" select t1.shyj from xg_xtwh_shztb t1 left join xg_pjpy_new_xmsq t2 on t1.ywid = t2.sqid ");
		sql.append(" where xh = ? and xn = ? and xq = ? and xmdm = ? order by shsj asc ");
		String[] inputValue = { xh,xn,xq,xmdm };
		return dao.getListNotOut(sql.toString(), inputValue);
		
	}
	
	/**
	 * @��������ȡ ѧ�ꡢ��1ѧ�ڡ���2ѧ�� ����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> ��������
	 */
	public HashMap<String,String> getJd_10277(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select m.*, n.jd xnjd from (select a.xh xh, substr(a.xq, 1, 9) xn, a.jd xqjd1, b.jd xqjd2 from ");
		sql.append(" (select * from XG_V_XQJD@dblink_jwtoxg where substr(xq, 10, 10) = '1') a left join (select * from XG_V_XQJD@dblink_jwtoxg where substr(xq, 10, 10) = '2')");
		sql.append(" b on a.xh = b.xh and substr(a.xq, 1, 9) = substr(b.xq, 1, 9)) m left join XG_V_XNJD@dblink_jwtoxg n on m.xh = n.xh and m.xn = n.xn where");
		sql.append(" m.xh = ? and m.xn = ? ) where rownum = '1'");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xn});
	}
	
	/**
	 * 
	 * @��������ȡ��ߺ�����Ļ��γɼ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-29 ����05:03:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public  HashMap<String,String> getMaxOrMinWfkCj(String xh,String xn ,String xq){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select max(cj) whkzgf,min(cj) whkzdf from cjb where xn = ? and xh = ?");
		paraList.add(xn);
		paraList.add(xh);
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ?"); 
			paraList.add(xq);
		}
		return dao.getMapNotOut(sql.toString(),paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: ���غ�ʱ��ҵ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-4-28 ����02:32:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getHsbyjs(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select (nj+xz) js  from xsxxb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "js");
	}
	
}
