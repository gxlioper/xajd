/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����04:40:57 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ���Ϊ����ģ��
 * @�๦������: �ճ���Ϊ��� 
 * @���ߣ�dlq [���ţ�995]
 * @ʱ�䣺 2013-8-7 ����04:40:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RcxwjgDao extends SuperDAOImpl<RcxwjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("xg_rcsw_rcxwjg");
		super.setKey("id");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwjgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		/*sql.append("select t1.* from (select a.id,a.xh,a.xn,a.rcxwlbdldm,a.rcxwlbdm,a.rcxwjlsj, ");
		sql.append("a.bz,c.xm,c.xb,c.bjmc,c.bjdm,c.xydm,d.rcxwlbdlmc,e.rcxwlbfz,e.rcxwlbmc,f.xqmc xqmc,f.xqdm,a.xq, ");
		//sql.append("decode(a.xq,'01','��','02','��',a.xq ) xq, ");
		sql.append(" c.lxdh,c.zydm,c.nj,");
		sql.append("decode(b.shzt,'0','δ���','1','ͨ��','2','��ͨ��','','�������',b.shzt) shztmc ");
		sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwxxwh b on a.id = b.id ");
		sql.append(" left join view_xsxxb c on a.xh = c.xh ");
		sql.append(" left join  xg_rcsw_rcxwdbdlb d on a.rcxwlbdldm = d.rcxwlbdldm  ");
		sql.append(" left join xg_rcsw_rcxwlbdmb e on a.rcxwlbdm = e.rcxwlbdm ");
		sql.append(" left join xqdzb f on a.xq = f.xqdm ) t1 where 1=1 ");*/
		 // 
		
		sql.append("select * from VIEW_NEW_DC_RCSW_RCXWJG t1 where 1=1 ");
		
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		if(Base.xxdm.equalsIgnoreCase("12867")){
			StringBuilder sql1 = new StringBuilder();
			sql1.append(" update xg_rcsw_rcxwjg set rcxwlbdm = rcxwlbdldm where rcxwlbdm is null");
			dao.runUpdate(sql1.toString(), new String[]{});
		}
		return getPageList(t, sql.toString(), inputV);
	
	}
	
	public List<HashMap<String, String>> getXwdlfList(RcxwjgForm t, User user,List<HashMap<String,String>> xmdlList)
	throws Exception {
		// TODO �Զ����ɷ������
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from(select t.*, i.yqdm, c.xm, c.xb, c.bjmc, c.nj,c.zydm,c.zymc,c.bjdm, c.xydm, c.xymc from(select ");
		for (int i = 0 , j = xmdlList.size() ; i < j ; i++){
			sql.append("case when nvl(sum(fz").append(i).append(")+"+xmdlList.get(i).get("rcxwdljcf")+",0)>"+xmdlList.get(i).get("rcxwdlfssx")+" then "+xmdlList.get(i).get("rcxwdlfssx")+" else  ");
			sql.append("nvl(sum(fz").append(i).append(")+"+xmdlList.get(i).get("rcxwdljcf")+",0) end fz").append(i).append(",");
			
		}
		sql.append(" xh,xn from ( select");
		for (int i = 0 , j = xmdlList.size() ; i < j ; i++){
			sql.append(" case when t.rcxwlbdldm='").append(xmdlList.get(i).get("rcxwlbdldm"))
			   .append("' then t.fz else '' end fz")
			   .append(i).append(",");
		}
		sql.append(" xh,xn from(select a.xh,a.xn, b.rcxwlbdldm,b.rcxwlbdlmc, c.rcxwlbmc, ");
		sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then a.fz else 'δ֪����' end) fz");
		sql.append("  from xg_rcsw_rcxwjg a ");
		sql.append("  left join xg_rcsw_rcxwdbdlb b ");
		sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append("  left join xg_rcsw_rcxwlbdmb c ");
		sql.append("    on a.rcxwlbdm = c.rcxwlbdm)t) ");
		sql.append("  group by xh,xn)t left join view_xsbfxx c on t.xh = c.xh left join xg_gygl_new_cwxxb g on t.xh=g.xh");
        sql.append("   left join xg_gygl_new_ldxxb h on h.lddm=g.lddm left join zxbz_ssyqdm i on i.yqdm=h.yqdm)t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);

}
	
	public List<HashMap<String, String>> getAllList(RcxwjgForm t, User user) throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		
		
		if("10355".equals(Base.xxdm)){
			sql.append("select t.*, (case  when pdszdf != 0 then '���'  else 'δ��д' end) zt1, ");
			sql.append("  (case  when zhnldf != 0 then  '���' else  'δ��д' end) zt2 ");
			sql.append("  from (select a.xh, a.xb, a.xm, a.xymc, a.zymc, a.bjmc, a.nj, b.pdszdf, b.zhnldf, b.xyspdf, b.stszdf ");
			sql.append("   from (  select * from (select sum(case when t.rcxwlbdlmc = 'Ʒ������' then  to_number(t.fz) else 0 end) pdszdf, ");
			sql.append("   sum(case  when t.rcxwlbdlmc = '�ۺ�����' then to_number(t.fz)  else  0 end) zhnldf, ");
			sql.append("   sum(case  when t.rcxwlbdlmc = 'ѧҵˮƽ' then to_number(t.fz)  else  0 end) xyspdf, ");
			sql.append("   sum(case  when t.rcxwlbdlmc = '��������' then to_number(t.fz)  else  0 end) stszdf, ");
			sql.append("   t.xh,t.xn,t.xq,t.xb,t.xydm,t.xymc,t.bjdm,t.bjmc ");
			sql.append("   from view_new_dc_rcsw_rcxwjg t group by xh,xn,xq,xb,xydm,xymc,bjdm,bjmc ) t");
			sql.append("   where 1 = 1 ");
			sql.append(searchTj);
			sql.append("                      ) b ");
			sql.append("   left join view_xsjbxx a  on a.xh = b.xh) t where 1 = 1 ");
			sql.append(searchTjByUser);


		}else{
			sql.append("select * from (");
			sql.append("select a.*,b.fz zfz from VIEW_NEW_DC_RCSW_RCXWJG a left join");
			sql.append("(select xn,xh,xq,case when to_char(sum(fz)) like '.%'then '0' ");
			sql.append("|| to_char(sum(fz)) else to_char(sum(fz)) end fz from (select *from(select ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else 'δ֪����' end) fz,xh,xq,xn from ");
			sql.append("xg_rcsw_rcxwjg a left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm ))group by xn, xq,xh)b on");
			sql.append(" a.xh=b.xh and a.xn=b.xn and a.xq=b.xq ");
			sql.append(")t where 1=1 ");
			sql.append(searchTjByUser);
			sql.append(searchTj);
		}
		return dao.getListNotOut(sql.toString(),inputV);

	}
	
	/**
	 * 
	 * ��ȡ��Ϊ���༯��
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-9 ����03:57:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXwdlList(HttpServletRequest request){	
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select b.rcxwlbdldm,b.rcxwlbdlmc, ");
		sql.append(" case when b.sqkg=1 and sysdate between to_date(nvl(b.sqkssj,'1990-01-01'),'yyyy-mm-dd') and to_date(nvl(b.sqjssj,'2200-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen ");
		sql.append(" from xg_rcsw_rcxwdbdlb b  ");
		sql.append(" ) where isopen='true' order by rcxwlbdldm ");
		
		return dao.getList(sql.toString(), new String[]{},new String[]{"rcxwlbdldm","rcxwlbdlmc"});
	}
	
	/**
	 * 
	 * ��ȡ��Ϊ��𼯺�
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-9 ����03:57:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xwdldm
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXwlbList(String xwdldm, HttpServletRequest request){
		String sql = "select rcxwlbdm,rcxwlbmc from xg_rcsw_rcxwlbdmb where rcxwlbdldm=? and sfqy='1' order by rcxwlbdm ";		
		return dao.getList(sql, new String[]{xwdldm},new String[]{"rcxwlbdm","rcxwlbmc"});
	}
	
	/**
	 * 
	 * �鿴��Ϊ���
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-9 ����10:34:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneXwjgList(String  xwjgId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.id,a.xn, g.xqmc,rcxwlbmc,rcxwlbdlmc,a.fjlj,a.fjmc, ");
		sql.append(" a.rcxwjlsj,a.gfly,a.bz,decode(f.shzt,'0','δ���','1','ͨ��','2','��ͨ��','','�������',f.shzt) shztmc,d.shyj,(case when c.rcxwfzlx='01' then '+'||a.fz when c.rcxwfzlx='02' then '-'||a.fz else 'δ֪����' end) fz,c.rcxwlbbz,a.fssj,(select xm from yhb y where y.yhm=a.jlr) jlrxm ");
		sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm ");
		sql.append(" left join  xg_xtwh_shztb d on a.id = d.ywid ");
		sql.append(" left join xg_xtwh_spgw  e on d.gwid = e.id  left join xg_rcsw_rcxwxxwh f  on a.id = f.id ");
		sql.append(" left join  xqdzb g on a.xq = g.xqdm ");
		sql.append(" where a.id = ? ");
		
		return dao.getMapNotOut(sql.toString(),new String[]{xwjgId});
	}
	
	/**
	 * 
	 * �޸���Ϊά����Ϣʱ�ж��޸ĵ���Ϊά���ǲ����Ѿ�������Ϊ�����
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-12 ����04:23:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xwjgId
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkXwwhForxwjg(String  xwjgId) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_rcsw_rcxwjg a ");
		sql.append(" where a.id = ? ");
		String num = dao.getOneRs(sql.toString(),new String[]{xwjgId},"num");
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return  flag;
	}
	
	
	/**
	 * 
	 * �鿴��ʷ��Ϊ��¼��Ϣ
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-9 ����10:34:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public ArrayList<String[]>  getMoreXwjgList(RcxwjgForm model,HashMap<String,String> cspzMap) throws Exception {
		StringBuilder sql = new StringBuilder();
		String[] outputValue = null;
		String[] inputValue = new String[]{model.getXh(),model.getXh(),model.getXh()};
		if("1".equals(cspzMap.get("zq"))){
			outputValue = new String[] { "xn","rcxwlbdlmc","rcxwlbmc","fssj","fz","jlrxm" };
			sql.append(" select xn,rcxwlbdlmc,rcxwlbmc,fssj, ");
			sql.append(" case when fz like '.%' then  '0' || fz ");
			sql.append(" when fz like '-.%' then substr(fz, 0, 1) || '0' || substr(fz, 2, length(fz)) ");
			sql.append(" else fz end fz,jlrxm,xssx from ( ");
			sql.append("select xn, rcxwlbdlmc, rcxwlbmc, fssj, fz,jlrxm,xssx from (");
			sql.append(" select a.xn,b.rcxwlbdlmc, c.rcxwlbmc,a.rcxwjlsj, ");
			sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else 'δ֪����' end) fz ,a.fssj,(select xm from yhb y where y.yhm=a.jlr) jlrxm, '0' xssx ");
			sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join  xqdzb e on a.xq = e.xqdm ");
			sql.append(" where a.xh = ?");
			sql.append(" order by xn desc) ");
			sql.append(" union all ");
			sql.append(" select xn,rcxwlbdlmc,rcxwlbmc,fssj,to_char((fz+"+cspzMap.get("rcxwjcf")+"))fz,jlrxm,xssx from(");
			sql.append(" select  xn,'�ܷ�:' rcxwlbdlmc,'' rcxwlbmc,'0' fssj,");
			sql.append(" case when to_char(sum(fz)+"+cspzMap.get("rcxwjcf")+") like '.%' then '0'||to_char(sum(fz))");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz, '' jlrxm, '1' xssx from ( ");
			sql.append("select a.xn,a.rcxwlbdldm,a.rcxwlbdlmc, case when a.fz+b.rcxwdljcf> b.rcxwdlfssx then b.rcxwdlfssx else to_char(a.fz+b.rcxwdljcf) end fz from (");
			sql.append(" select xn,rcxwlbdldm,rcxwlbdlmc,sum(fz) fz from(");
			sql.append(" select a.xh,a.xn,b.rcxwdljcf,b.rcxwdlfssx, b.rcxwlbdldm,b.rcxwlbdlmc, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then a.fz else 'δ֪����' end) fz");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn desc) group by xn, rcxwlbdldm, rcxwlbdlmc, rcxwdljcf)a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm=b.rcxwlbdldm)group by xn) ");
			sql.append(" union all ");
			sql.append("select xn,rcxwlbdlmc,rcxwlbmc,fssj,case when fz+rcxwdljcf> rcxwdlfssx then rcxwdlfssx else to_char(fz+rcxwdljcf) end fz,jlrxm,xssx from(");
			sql.append(" select xn,rcxwlbdlmc, rcxwdlfssx,rcxwdljcf,'' rcxwlbmc,'9999999999' fssj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz))");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz, '' jlrxm, '0' xssx from ( ");
			sql.append(" select a.xh,a.xn, b.rcxwlbdlmc,b.rcxwdljcf,b.rcxwdlfssx, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then a.fz else 'δ֪����' end) fz");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn desc) group by xn,rcxwlbdlmc,rcxwdlfssx, rcxwdljcf) ");
			sql.append(" ) order by xn desc,xssx desc,rcxwlbdlmc,fssj desc ");
		}else{
			outputValue = new String[] { "xn","xq","rcxwlbdlmc","rcxwlbmc","fssj","fz","jlrxm" };
			sql.append(" select xn,xq, rcxwlbdlmc, rcxwlbmc, fssj, ");
			sql.append(" case when fz like '.%' then  '0' || fz ");
			sql.append(" when fz like '-.%' then substr(fz, 0, 1) || '0' || substr(fz, 2, length(fz)) ");
			sql.append(" else fz end fz,jlrxm,xssx from ( ");
			sql.append("select xn,xq, rcxwlbdlmc, rcxwlbmc, fssj, fz,jlrxm,xssx from (");
			sql.append(" select a.xn,e.xqmc xq,b.rcxwlbdlmc, c.rcxwlbmc,a.rcxwjlsj, ");
			sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else 'δ֪����' end) fz ,a.fssj,(select xm from yhb y where y.yhm=a.jlr) jlrxm, '0' xssx ");
			sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join  xqdzb e on a.xq = e.xqdm ");
			sql.append(" where a.xh = ?");
			sql.append(" order by xn desc) ");
			sql.append(" union all ");
			sql.append(" select xn,xq,rcxwlbdlmc,rcxwlbmc,fssj,to_char((fz+"+cspzMap.get("rcxwjcf")+"))fz,jlrxm,xssx from(");
			sql.append(" select  xn, xqmc xq,'�ܷ�:' rcxwlbdlmc,'' rcxwlbmc,'0' fssj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz))");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz, '' jlrxm, '1' xssx from ( ");
			sql.append("select a.xn,xq,xqmc,a.rcxwlbdldm,a.rcxwlbdlmc, case when a.fz+a.rcxwdljcf> b.rcxwdlfssx then b.rcxwdlfssx else to_char(a.fz+a.rcxwdljcf) end fz from (");
			sql.append(" select xn,xq,xqmc,rcxwdljcf,rcxwlbdldm,rcxwlbdlmc,sum(fz) fz from(");
			sql.append(" select a.xh,a.xq,a.xn,");
			if("10355".equals(Base.xxdm)){
				  sql.append("'0' rcxwdljcf");	
				}
				else{
				  sql.append("b.rcxwdljcf");
				}
			sql.append(",b.rcxwdlfssx, b.rcxwlbdldm,e.xqmc, b.rcxwlbdlmc, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then a.fz else 'δ֪����' end) fz");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join xqdzb e ");
			sql.append("on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn desc) group by xn ,xq, xqmc, rcxwlbdldm, rcxwlbdlmc,rcxwdljcf)a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm=b.rcxwlbdldm)group by xn, xqmc) ");
			sql.append(" union all ");
			sql.append("select xn,xq,rcxwlbdlmc,rcxwlbmc,fssj,case when fz+rcxwdljcf> rcxwdlfssx then rcxwdlfssx else to_char(fz+rcxwdljcf) end fz,jlrxm,xssx from(");
			sql.append(" select  xn, xqmc xq,rcxwlbdlmc,rcxwdlfssx,"); 
			if("10355".equals(Base.xxdm)){
			  sql.append("'0' rcxwdljcf");	
			}
			else{
			  sql.append("rcxwdljcf");
			}
			
			sql.append(" ,'' rcxwlbmc,'9999999999' fssj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz))");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz, '' jlrxm, '0' xssx from ( ");
			sql.append(" select a.xh,a.xq,a.xn, e.xqmc, b.rcxwlbdlmc,b.rcxwdljcf,b.rcxwdlfssx, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then a.fz else 'δ֪����' end) fz");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join xqdzb e ");
			sql.append("on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn desc) group by xn,xqmc,rcxwlbdlmc,rcxwdlfssx, rcxwdljcf) ");
			sql.append(" ) order by xn, xq desc,xssx desc,rcxwlbdlmc,fssj desc ");
		}
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputValue, outputValue, model);
	}
	/**
	 * 
	 * @����:��ȡ����Ϊ�����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-9 ����11:32:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getCspz(){ 
		String cszSql = "select * from xg_rcsw_cspzb where rownum=1";
		return dao.getMapNotOut(cszSql, new String[]{});
	}
	
	/**
	 * 
	 * ѧ���ܹ��鿴���Լ��Ĳ��зַ���������ؼ�¼
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-10-16 ����10:31:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<String[]> getStuRcswList(String xh) {
		//DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.xn,e.xqmc xq ,b.rcxwlbdlmc, c.rcxwlbmc,a.rcxwjlsj,a.fz rcxwlbfz ");
		sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm ");
		sql.append(" left join  xqdzb e on a.xq = e.xqdm ");
		sql.append(" where a.xh = ? ");
		sql.append(" order by xn ");
		
		
		return dao.rsToVator(sql.toString(),new String[]{xh}, new String[] { "xn","xq","rcxwlbdlmc","rcxwlbmc","rcxwjlsj","rcxwlbfz" });
	}
	
	
	/**
	 * 
	 * @����:����ѧ�Ų�ѯ�ճ�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getRcswList(String xh,HashMap<String,String> cspzMap) {
		StringBuilder sql = new StringBuilder();
		if("1".equals(cspzMap.get("zq"))){
			sql.append(" select xn,rcxwlbdlmc,rcxwlbmc,rcxwjlsj, ");
			sql.append(" case when fz like '.%' then  '0' || fz ");
			sql.append(" when fz like '-.%' then substr(fz, 0, 1) || '0' || substr(fz, 2, length(fz)) ");
			sql.append(" else fz end fz,xssx from ( ");
			sql.append("select xn,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,fz,xssx from ( ");
			sql.append("select a.xn,b.rcxwlbdlmc, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else 'δ֪����' end) fz ,a.rcxwjlsj, '0' xssx");
			sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("     on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn) ");
			sql.append(" union all ");
			sql.append("select xn,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,to_char((fz+"+cspzMap.get("rcxwjcf")+"))fz,xssx from(");
			sql.append(" select  xn,'�ܷ�:' rcxwlbdlmc,'' rcxwlbmc,'0' rcxwjlsj, ");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) ");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz,'1' xssx from ( ");
			sql.append("select a.xn,a.rcxwlbdldm,a.rcxwlbdlmc, case when a.fz+b.rcxwdljcf> b.rcxwdlfssx then b.rcxwdlfssx else to_char(a.fz+b.rcxwdljcf) end fz from (");
			sql.append(" select xn,rcxwlbdldm,rcxwlbdlmc,sum(fz) fz from(");
			sql.append(" select a.xn, b.rcxwlbdlmc, c.rcxwlbmc,b.rcxwdljcf,  b.rcxwdlfssx, b.rcxwlbdldm,");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else 'δ֪����' end) fz ,a.rcxwjlsj, '1' xssx");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn)  group by xn, rcxwlbdldm, rcxwlbdlmc) a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm) group by xn) ");
			sql.append(" union all ");
			sql.append("select xn,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,case when fz+rcxwdljcf> rcxwdlfssx then rcxwdlfssx else to_char(fz+rcxwdljcf) end fz,xssx from(");
			sql.append(" select  xn,rcxwlbdlmc,rcxwdlfssx, rcxwdljcf,'' rcxwlbmc,'9999999999' rcxwjlsj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) " );
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz,'0' xssx ");
			sql.append(" from (select a.xh,a.xn,b.rcxwlbdlmc,b.rcxwdljcf, b.rcxwdlfssx, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else 'δ֪����' end) fz, '0' xssx");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn)  group by xn, rcxwlbdlmc, rcxwdlfssx, rcxwdljcf) ");
			sql.append(" ) order by xn,xssx desc,rcxwlbdlmc,rcxwjlsj desc ");
			
		}else{
			sql.append(" select xn,xqmc,rcxwlbdlmc,rcxwlbmc,rcxwjlsj, ");
			sql.append(" case when fz like '.%' then  '0' || fz ");
			sql.append(" when fz like '-.%' then substr(fz, 0, 1) || '0' || substr(fz, 2, length(fz)) ");
			sql.append(" else fz end fz,xssx from ( ");
			sql.append("select xn,xqmc,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,fz,xssx from ( ");
			sql.append("select a.xn, e.xqmc, b.rcxwlbdlmc, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else 'δ֪����' end) fz ,a.rcxwjlsj, '0' xssx");
			sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("     on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append("  left join xqdzb e ");
			sql.append("    on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn) ");
			sql.append(" union all ");
			sql.append(" select xn,xqmc,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,to_char((fz+"+cspzMap.get("rcxwjcf")+"))fz,xssx from(");
			sql.append(" select  xn, xqmc,'�ܷ�:' rcxwlbdlmc,'' rcxwlbmc,'0' rcxwjlsj, ");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) ");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz,'1' xssx from ( ");
			sql.append("select a.xn,a.xqmc,a.rcxwlbdldm,a.rcxwlbdlmc, case when a.fz+b.rcxwdljcf> b.rcxwdlfssx then b.rcxwdlfssx else to_char(a.fz+b.rcxwdljcf) end fz from (");
			sql.append(" select xn,xqmc,rcxwlbdldm,rcxwlbdlmc,sum(fz) fz from(");
			sql.append(" select a.xn, e.xqmc,");
			if("10355".equals(Base.xxdm)){
				  sql.append("'0' rcxwdljcf");	
				}
				else{
				  sql.append("b.rcxwdljcf");
				}
            sql.append(",b.rcxwdlfssx,b.rcxwlbdldm, b.rcxwlbdlmc, c.rcxwlbmc,");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else 'δ֪����' end) fz ,a.rcxwjlsj, '1' xssx");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join xqdzb e ");
			sql.append("on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn) group by xn , xqmc, rcxwlbdldm, rcxwlbdlmc) a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm) group by xn,xqmc)  ");
			sql.append(" union all ");
			sql.append("select xn,xqmc,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,case when fz+rcxwdljcf> rcxwdlfssx then rcxwdlfssx else to_char(fz+rcxwdljcf) end fz,xssx from(");
			sql.append(" select  xn,xqmc,rcxwlbdlmc,rcxwdlfssx,");
			if("10355".equals(Base.xxdm)){
				  sql.append("'0' rcxwdljcf");	
				}
				else{
				  sql.append("rcxwdljcf");
				}
            sql.append(",'' rcxwlbmc,'9999999999' rcxwjlsj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) " );
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz,'0' xssx ");
			sql.append(" from (select a.xh,a.xq,a.xn, e.xqmc, b.rcxwdlfssx, b.rcxwdljcf,b.rcxwlbdlmc, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else 'δ֪����' end) fz, '0' xssx");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join xqdzb e ");
			sql.append("on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn) group by xn, xqmc, rcxwlbdlmc, rcxwdlfssx, rcxwdljcf) ");
			sql.append(" ) order by xn, xqmc, xssx desc, rcxwlbdlmc, rcxwjlsj desc ");
		}
		return dao.getListNotOut(sql.toString(), new String[] { xh,xh,xh });
	}
	
	
	
	@Override
	public RcxwjgForm getModel(RcxwjgForm t) throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append(" select a.*,b.rcxwlbzgfz,b.rcxwlbzdfz from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwlbdmb b on a.rcxwlbdm=b.rcxwlbdm where a.id=?");
		RcxwjgForm model=new RcxwjgForm();
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{t.getId()});
		BeanUtils.copyProperties(model, map);
		return model;
	}
	
	/** 
	 * @����: �ж���Ϣ�Ƿ��ظ�(ѧ�š�ѧ�ڡ�ѧ�ꡢ��Ϊ�б�����ʱ��)
	 * @���ߣ�HongLin[���ţ�707]
	 * @���ڣ�2014-2-24 ����05:47:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getRcxwxxSfcf(HttpServletRequest request,String xh,String xn,String xq,String[] xwlbStr,String[] fssjStr){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.rcxwlbmc from xg_rcsw_rcxwjg a left join XG_RCSW_RCXWLBDMB b on a.rcxwlbdm=b.rcxwlbdm where 1=1 ");
		if(xwlbStr!=null && xwlbStr.length>0){
			sql.append(" and ");
			for (int i=0;i<xwlbStr.length;i++){
				if(i==(xwlbStr.length-1)){
					sql.append("(a.xh='"+xh+"' and a.xn='"+xn+"' and a.xq='"+xq+"' and a.rcxwlbdm='"+xwlbStr[i]+"' and a.fssj='"+fssjStr[i]+"')");
				}else{
					sql.append("(a.xh='"+xh+"' and a.xn='"+xn+"' and a.xq='"+xq+"' and a.rcxwlbdm='"+xwlbStr[i]+"' and a.fssj='"+fssjStr[i]+"') or ");
				}
			}
		}
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xh","xn","xq","rcxwlbmc","fssj"});
	}
	/**
	 * 
	 * @����:��ȡ¥��
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-12-1 ����03:22:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yqdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	
	public List<HashMap<String,String>> getLdxx(String yqdm){
		String sql = "select lddm,ldmc from xg_gygl_new_ldxxb where yqdm = ? ";
		return DAO.getInstance().getList(sql, new String[]{yqdm}, new String[]{"lddm","ldmc"});
	}
	/**
	 * ��ȡ����list
	 * @param lddm
	 * @return
	 */
//	public List<HashMap<String,String>> getQsList(String yqdm){
//		String sql= "select ldmc,qsh,qsxb from view_xg_gygl_new_qsxx where lddm = ? order by ch,qsh";
//		return DAO.getInstance().getList(sql, new String[]{lddm}, new String[]{"ldmc","qsh","qsxb"});
//	}
	/**
	 * ��ȡ¥����������
	 * @param lddm
	 * @return
	 */
	public String getQsCount(String lddm){
		String sql= "select count(*) qss from view_xg_gygl_new_qsxx where lddm=?";
		return DAO.getInstance().getOneRs(sql, new String[]{lddm}, "qss");
	}
	/**
	 * ��ȡ¥������ס����Ϣlist
	 * @param lddm
	 * @return
	 */
	public List<HashMap<String,String>> getZsxxList(String yqdm){
		String sql= "select a.lddm,a.qsh,a.xh,b.xm,b.bjmc from (select ldmc,qsh,cwh,xh from view_xg_gygl_new_cwxx " +
				"where xh is not null and yqdm = ? order by qsh,cwh ) a left join view_xsjbxx b on a.xh = b.xh";
		return DAO.getInstance().getList(sql, new String[]{yqdm}, new String[]{"qsh","xh","xm","bjmc"});
	}
	/**
	 * 
	 * @����:��ȡ԰��¥����סѧ��ѧ��ѧ������Ʒ�з�
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-12-2 ����09:04:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yqdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getpxfList(String yqdm,String xn,String xq){
		StringBuffer sql  = new StringBuffer();
		sql.append("select a.*,b.plusxm,b.cutxm,(zfs+to_number(nvl(b.fz,0))) sjfz from(select * from (select a.*,80+to_number(nvl(b.zfs,0)) zfs from (select a.*,b.xm,b.zzmmmc from (select* from view_xg_gygl_new_cwxx where yqdm=?) a left join view_xsbfxx b on a.xh=b.xh) a  left join");
		sql.append(" (  select sum(fs) zfs,xh from XG_GYGL_NEW_WSJC_XSFSB where xn=? and xq=? group by xh ");
		sql.append(" ) b on a.xh=b.xh )where xh is not null)a left join (select xh,xn,xq,sum(decode(rcxwfzlx, '01', fz)) plusxm,");
		sql.append(" sum(decode(rcxwfzlx, '02', fz)) cutxm, case when to_char(sum(fz)) like '.%' then '0' || to_char(sum(fz))");
		sql.append(" else to_char(sum(fz)) end fz from (select a.xh,a.xn,a.xq,c.rcxwfzlx,b.rcxwlbdlmc,c.rcxwlbmc,(case when c.rcxwfzlx = '02' then  '-' || a.fz");
		sql.append(" when c.rcxwfzlx = '01' then a.fz else  'δ֪����' end) fz  from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm");
		sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm where xn=? and xq=? order by xn)group by xh,xn,xq)b on a.xh=b.xh order by lddm,qsh,cwh");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{yqdm,xn,xq,xn,xq});
	}
	
	public String getXqmc(String xqdm){
		String sql ="select xqmc from xqdzb where xqdm =?";
		return DAO.getInstance().getOneRs(sql, new String[]{xqdm}, "xqmc");
		
	}
	public String getYqmc(String yqdm){
		String sql ="select yqmc from zxbz_ssyqdm where yqdm =?";
		return DAO.getInstance().getOneRs(sql, new String[]{yqdm}, "yqmc");
		
	}
	
	/**
	 * 
	 * @����: �ճ���Ϊ���ݵ�����2�����������ʲ�����˼��������ʲ���-�㽭ҽѧ���Ի���
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2015-1-20 ����02:57:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getrcxwfList(String xn){
		StringBuffer sql  = new StringBuffer();
		sql.append(" select xh, xm, xymc, zymc, bjmc, nlf, sxddf, (nlf + sxddf) zf, (case when nlf >= 70 and sxddf >= 70 then '2' when nlf < 70 and sxddf < 70 then '0' else '1' end) xf ");
		sql.append(" from (select a.xh, c.xm, c.xymc, c.zymc, c.bjmc, (60 + to_number(nvl(b.fz, 0))) nlf, z.sxddf ");
		sql.append(" from (select xh from xg_rcsw_rcxwjg a left join XG_RCSW_RCXWDBDLB b on a.rcxwlbdldm = b.rcxwlbdldm ");
        sql.append(" where rcxwlbdlmc like '%�������ʲ���%' or rcxwlbdlmc like '%˼��������ʲ���%' group by xh) a ");       
        sql.append(" left join view_xsbfxx c on a.xh = c.XH left join (select xh, xn, sum(decode(rcxwfzlx, '01', fz)) plusxm, sum(decode(rcxwfzlx, '02', fz)) cutxm, ");
        sql.append(" case when to_char(sum(fz)) like '.%' then '0' || to_char(sum(fz)) else to_char(sum(fz)) end fz ");                   
        sql.append(" from (select a.xh, a.xn, a.xq, c.rcxwfzlx, c.rcxwlbmc, (case when c.rcxwfzlx = '02' then '-' || a.fz when c.rcxwfzlx = '01' then a.fz else 'δ֪����' end) fz ");                     
        sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm left join xg_rcsw_rcxwlbdmb c ");
        sql.append(" on a.rcxwlbdm = c.rcxwlbdm left join view_xsbfxx d on a.xh = d.xh where xn = ? and rcxwlbdlmc like '%�������ʲ���%' ");
        sql.append(" order by xn) group by xh, xn) b on a.XH = b.xh ");
        sql.append(" left join (select (60 + to_number(nvl(b.fz, 0))) sxddf, xh ");
        sql.append(" from (select xh, xn, rcxwlbdlmc, sum(decode(rcxwfzlx, '01', fz)) plusxm, sum(decode(rcxwfzlx, '02', fz)) cutxm, ");
        sql.append(" case when to_char(sum(fz)) like '.%' then '0' || to_char(sum(fz)) else to_char(sum(fz)) end fz ");
        sql.append(" from (select a.xh, a.xn, a.xq, c.rcxwfzlx, b.rcxwlbdlmc, c.rcxwlbmc, (case when c.rcxwfzlx = '02' then '-' || a.fz when c.rcxwfzlx = '01' then a.fz else 'δ֪����' end) fz ");
        sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
        sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm left join view_xsbfxx d ");
        sql.append(" on a.xh = d.xh where xn = ? and rcxwlbdlmc like '%˼��������ʲ���%' order by xn) ");
        sql.append(" group by xh, xn, rcxwlbdlmc) b) z on z.xh = a.xh) ");
        
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{xn,xn});
	}
	/**
	 * 
	 * @����:�ճ���Ϊ�ܷ֣����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-13 ����10:57:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getrcxwzfList(HashMap<String,String> cspzMap,String xn){
		StringBuffer sql  = new StringBuffer();
		sql.append("select b.xm, b.xymc, b.zymc, b.bjmc,b.nj,a.xn,a.xh,to_char((a.fz+"+cspzMap.get("rcxwjcf")+"))fz from(");
		sql.append(" select  xh,xn,");
		sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) ");
		sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
		sql.append("else to_char(sum(fz)) end fz from ( ");
		sql.append(" select a.xn,a.xh, b.rcxwlbdlmc, c.rcxwlbmc,");
		sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else 'δ֪����' end) fz");
		sql.append("  from xg_rcsw_rcxwjg a ");
		sql.append("  left join xg_rcsw_rcxwdbdlb b ");
		sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append("  left join xg_rcsw_rcxwlbdmb c ");
		sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
	
		sql.append(" order by xh,xn) group by xh,xn) a  ");
		sql.append(" left join view_xsbfxx b on a.xh=b.xh  ");
		sql.append(" where a.xn=?");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{xn});
	}
	/**
	 * 
	 * @����:��ѯѧ���Ƿ���Ҫ��������ʾ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-8-10 ����01:47:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean getCffWarnStudent(String xh){
		StringBuffer sql  = new StringBuffer();
		sql.append("select count(1) num from (select xh, sum(fz) cff, fssj from (select a.xh,a.fz,to_char(to_date(a.fssj, 'yyyy-mm-dd'), 'yyyy-mm') fssj,");
		sql.append(" b.rcxwfzlx from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwlbdmb b on a.rcxwlbdm = b.rcxwlbdm)");
		sql.append(" where rcxwfzlx = '02' and fssj=to_char(sysdate,'yyyy-mm') group by xh, fssj) where cff>=10 and xh=?");
		return Integer.parseInt(dao.getOneRs(sql.toString(), new String[]{xh}, "num"))>0;
		
		
	}

	/** 
	 * @����:�ൺ�������Ի�����ѯ˼��Ʒ�¿γɼ����ܱ�����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��31�� ����9:53:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSxpdcjhzList(RcxwjgForm model, User user) throws Exception{
		
		SearchModel searchModel = model.getSearchModel();
		//�����������
		searchModel.setSearch_tj_rcxwdl(null);//��Ϊ����
		searchModel.setSearch_tj_rcxwlb(null);//��Ϊ���
		searchModel.setSearch_tj_jssj(null);//ʱ���ѯ������ʱ��
		searchModel.setSearch_tj_kssj(null);//ʱ���ѯ����ʼʱ��
		
		//��ñ�Ҫ��ѧ��ѧ�ڣ�ֻ����һ����
		String xn = searchModel.getSearch_tj_xn()[0];
		String xq = searchModel.getSearch_tj_xq()[0];
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM (");
		sql.append("SELECT dense_rank() over ");
		sql.append("(partition by t2.bjdm order by (round(t2.llf*0.4+(t2.jcf+t2.qtf+t2.ygpf)*0.6,2)) desc) rank,");
		sql.append("(round(t2.llf*0.4+(t2.jcf+t2.qtf+t2.ygpf)*0.6,2)) zf,(round(t2.llf*0.4,2)) llf_p4,(t2.qtpf+t2.ygpf) pf,");
		sql.append("(round((t2.jcf+t2.qtf+t2.ygpf)*0.6,2)) tf_p6,t2.qtmf mf,t2.jcf+t2.qtf+t2.ygpf tf,t2.* FROM (");
		sql.append("SELECT vx.bjdm,vx.bjmc,vx.xh,vx.xm,vx.xb,vx.nj,vx.xydm,vx.zydm,");
		sql.append(" '");
		sql.append(xn);
		sql.append("' xn,");
		sql.append(" '");
		sql.append(xq);
		sql.append("' xq,");
		sql.append("q.xqmc,'75' jcf,nvl(t1.qtpf,0) qtpf,nvl(t1.qtmf,0) qtmf,nvl(l.llf,0) llf,");
		sql.append("nvl(CASE WHEN ");
		sql.append("(t1.qtpf-t1.qtmf) > 20 THEN 20 ELSE (t1.qtpf-t1.qtmf) END,0) qtf,");
		sql.append("nvl(CASE WHEN ");
		sql.append("t1.ygpf > 5 THEN 5 ELSE t1.ygpf END,0) ygpf ");
		sql.append("FROM view_xsbfxx vx LEFT JOIN (SELECT r1.xh,r1.xn,r1.xq,");
		sql.append("sum(to_number(decode(r2.rcxwfzlx,'01',(CASE r2.rcxwlbmc WHEN '�幤�' THEN ");
		sql.append("nvl(r1.fz,0) ELSE '0' END ),'02','0'))) ygpf,");
		sql.append("sum(to_number(decode(r2.rcxwfzlx,'01',(CASE r2.rcxwlbmc WHEN '�幤�' THEN ");
		sql.append("'0' ELSE nvl(r1.fz,0) END ),'02','0'))) qtpf,");
		sql.append("sum(to_number(decode(r2.rcxwfzlx,'01','0','02',nvl(r1.fz,0)))) qtmf ");
		sql.append("FROM XG_RCSW_RCXWJG r1 ");
		sql.append("LEFT JOIN xg_rcsw_rcxwlbdmb r2 ON r1.rcxwlbdm = r2.rcxwlbdm ");
		sql.append("WHERE xn = '");
		sql.append(xn);
		sql.append("' AND xq = '");
		sql.append(xq);
		sql.append("' ");
		sql.append("GROUP BY r1.xh,r1.xn,r1.xq) t1 ");
		sql.append(" ON t1.xh = vx.xh ");
		sql.append("LEFT JOIN xg_rcsw_rcxwjg_llfjgb l ON t1.xh = l.xh AND t1.xn = l.xn AND t1.xq = l.xq ");
		sql.append("LEFT JOIN xqdzb q ON q.xqdm = ");
		sql.append(" '");
		sql.append(xq);
		sql.append("') t2 ");
		sql.append(") t where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	/**
	 * @����: ����id��ȡ��Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-14 ����11:03:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getKptzsForId(String id){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from VIEW_NEW_DC_RCSW_RCXWJG where id = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{id});
	}
}
