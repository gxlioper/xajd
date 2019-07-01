package xsgzgl.pjpy.general.wdpj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.comm.BasicDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_ͨ��_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyWdpjDAO extends BasicDAO {

	// ==================ִ�в�ѯ���� begin==============================
	
	/**
	 * ��ȡ������Ŀ�����б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXmxzList() {
		DAO dao = DAO.getInstance();
		String sql = " select xzdm dm,xzmc mc from xg_pjpy_xmxzb ";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * ѧ��������������Ϣ
	 * @param myForm
	 * @param user
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getWdpjByStu(PjpyGeneralForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		PjpyGeneralForm jbszForm=PjpyGeneralForm.getJbszModel();
		
		StringBuilder sql = new StringBuilder();
		
		String xh=user.getUserName();
		
		String pjxn=jbszForm.getPjxn();
		
		String pjxq=jbszForm.getPjxq();
		
		String pjnd=jbszForm.getPjnd();

		sql.append(" select rownum r,a.* from ( ");
		
		sql.append(" select a.*, ");
		
		// --------------------������Ŀ���� begin--------------------	
		sql.append(" case when b.xmlx='01' then '��ѧ��' else '�����ƺ�' end xmlx, ");
		// --------------------������Ŀ���� end----------------------			
	
		// ----------------------------------������Ŀ���ʡ���Χ�Ӳ�ѯ begin---------		
		sql.append(" (select xzmc from xg_pjpy_xmxzb c where b.xmxz=c.xzdm)xzmc, ");
		sql.append(" (select fwmc from xg_pjpy_xmfwb c where b.xmfw=c.fwdm)fwmc ");
		// --------------------������Ŀ���ʡ���Χ�Ӳ�ѯ end---------			
		sql.append("  from( ");
		sql.append(" select a.xh,a.pjxn,a.pjxq,a.pjnd,a.xmmc,a.xmje, ");
		
		// --------------------�������ڸ�ʽת�� begin----------------------------------
		sql.append(" substr(a.sqsj,1,4)||'��'||substr(a.sqsj,5,2)||'��'||substr(a.sqsj,7,2)||'��' sqsj,");
		// --------------------�������ڸ�ʽת�� end----------------------------------
		
		// --------------------�������Ϣ�� ��ת��Ϊ -> -------------------------
		sql.append(" a.xmdm,replace(max(a.shzt),',','->')shzt  ");
		
		sql.append("  from(select a.xh, a.pjxn, a.pjxq, a.pjnd, b.xmmc, b.xmje, c.sqsj, a.xmdm,  ");
		
		// ---------------------------�����Ϣ��ת�в��� begin -------------------------		
		sql.append(" to_char(WM_CONCAT(e.mc||'('||shxx||')')over(partition by a.pjxn,  ");
		sql.append(" a.pjxq,a.pjnd,a.xh,a.xmdm  order by d.xh))shzt ");
		
		// <<<<<<<<<<<<<<<<<<<����˼�¼��ͳ�� begin>>>>>>>>>>>>>>>>>>>>
		
		// -------------------�����Ϣ��ת�в��� end --------------------				
		sql.append(" from (select a.*,  ");
		// -------------------�����Ϣ��ת�в��� begin ----------------------		
		
		sql.append(" case when shzt='wsh' then 'δ���' ");
		sql.append("  when shzt='tg' then 'ͨ��' ");
		sql.append("  when shzt='btg' then '��ͨ��' ");
		sql.append("  when shzt='th' then '�˻�' ");
		sql.append("  when shzt='xcs' then '������' end shxx ");
		// -------------------�����Ϣ��ת�в��� end  -------------------------	
		sql.append(" from xg_pjpy_pjxmshb a )a, ");
		sql.append(" xg_pjpy_pjxmwhb b,xg_pjpy_pjxmsqb c,xg_xtwh_spbz d,xg_xtwh_spgw e   ");
		sql.append(" where a.xh =? and a.xmdm=b.xmdm and a.xh=c.xh   ");
		sql.append(" and a.xmdm=c.xmdm and a.pjxn=c.pjxn and   ");
		sql.append(" a.pjxq=c.pjxq and a.pjnd=c.pjnd and b.lcid=d.splc and a.xtgwid=e.id and d.spgw=e.id  ");
		sql.append(" and a.pjxn=? and a.pjxq=? and a.pjnd=? ");
		sql.append(" )a  group by a.xh,a.pjxn,a.pjxq,a.pjnd,a.xmmc,a.xmje,a.sqsj,a.xmdm  ");
		// <<<<<<<<<<<<<<<<<<<����˼�¼��ͳ�� end>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		// <<<<<<<<<<<<<<<<<<<������˼�¼ begin >>>>>>>>>>>>>>>>>>>>>>>>
		sql.append(" union select xh, pjxn, pjxq, pjnd, xmmc, xmje, sqsj, xmdm,'�������' shzt  ");
		sql.append(" from (select a.xh,a.xmdm,a.pjxn,a.pjxq,  ");
		sql.append(" a.pjnd,b.xmmc, b.xmje,a.sqsj,b.sfsh  ");
		sql.append(" from xg_pjpy_pjxmsqb a, xg_pjpy_pjxmwhb b  ");
		sql.append(" where  a.pjxn = b.pjxn  and a.pjxq = b.pjxq and a.pjnd = b.pjnd  ");
		sql.append(" and a.xmdm = b.xmdm and a.pjxn=? and a.pjxq=? and a.pjnd=?) a  ");
		sql.append(" where sfsh = 'no'  ");
		sql.append(" and xh= ?)a,xg_pjpy_pjxmwhb b where a.xmdm=b.xmdm ");
		// <<<<<<<<<<<<<<<<<<<������˼�¼ end >>>>>>>>>>>>>>>>>>>>>>>>
		sql.append(" )a  ");
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {xh,pjxn,pjxq,pjnd,pjxn,pjxq,pjnd,xh},
				new String[] {"xmmc","sqsj","shzt","xmdm"}, myForm);

	}
	
	public List<HashMap<String,String>> getWdpjDetailByStu(String xmdm, String xh){
		
		DAO dao=DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.mc,a.xh plxh, b.shzt,b.shsj,b.shyj,b.sftj,b.shr,");
		sql.append(" (select xm from yhb c where b.shr=c.yhm)shrxm");
		sql.append(" from (select spgw, xh,b.mc");
		sql.append(" from xg_xtwh_spbz  a,xg_xtwh_spgw b ");
		sql.append(" where a.spgw=b.id and  splc =");
		sql.append(" (select lcid");
		sql.append(" from xg_pjpy_pjxmwhb ");
		sql.append(" where xmdm = ?)) a");
		sql.append(" left join");
		sql.append(" (select *");
		sql.append(" from xg_pjpy_pjxmshb");
		sql.append(" where xh = ? ");
		sql.append(" and xmdm =?) b on a.spgw = b.xtgwid");
		sql.append(" order by a.xh");

		return dao.getListNotOut(sql.toString(), new String[] {xmdm,xh,xmdm});

}
	// ==================ִ�в�ѯ���� end==============================
	
	public ArrayList<String[]> getWdpjByTea(PjpyGeneralForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		PjpyGeneralForm jbszForm=PjpyGeneralForm.getJbszModel();

		StringBuilder sql = new StringBuilder();

		String userName= user.getUserName();
		
		String pjxn=jbszForm.getPjxn();
		
		String pjxq=jbszForm.getPjxq();
		
		String pjnd=jbszForm.getPjnd();
		
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "f",
				"xydm", "bjdm");
		
		sql.append(" select rownum r,a.* from ( ");
		
		// ----------------------------��Ŀ��Ϣ���� begin------------------------------
		sql.append(" select a.xmdm,a.xmmc,a.xmxz,a.xmfw,");
		sql.append(" case when a.xmlx='01' then '��ѧ��' else '�����ƺ�' end xmlx,");
		sql.append(" max(xshrs)xshrs,max(shtgrs)shtgrs,max(ksbrs)ksbrs from( ");
		sql.append(" select a.xmlx,a.xmdm,a.xmmc,a.xmxz,a.xmfw, ");
		sql.append(" to_char(WM_CONCAT(a.mc||'('||nvl(b.xshrs,0)||')') ");
		sql.append(" over( partition by a.xmdm  order by a.xh))xshrs, ");
		sql.append(" to_char(WM_CONCAT(a.mc||'('||nvl(c.shtgrs,0)||')') ");
		sql.append(" over( partition by a.xmdm  order by a.xh))shtgrs, ");
		sql.append(" d.qdrs ksbrs ");
		// ---------------------------��Ŀ��Ϣ���� end-------------------------------------
		
		sql.append(" from  ");
		sql.append(" (select a.xmlx,a.xmdm,a.xmmc,b.spgw,c.mc,b.xh, ");
		sql.append(" (select xzmc from xg_pjpy_xmxzb d where a.xmxz=d.xzdm)xmxz, ");
		sql.append(" (select fwmc from xg_pjpy_xmfwb d where a.xmfw=d.fwdm)xmfw ");
		sql.append(" from xg_pjpy_pjxmwhb a, xg_xtwh_spbz b, xg_xtwh_spgw c, ");
		sql.append("  xg_xtwh_spgwyh d  ");
		sql.append(" where a.lcid = b.splc and b.spgw = c.id  and c.id = d.spgw ");
		sql.append(" and d.spyh = ? and a.pjxn=? and a.pjxq=? and a.pjnd=? ) a ");
		 
		// ----------------------------δ�����Ϣͳ�� begin------------------------------
		sql.append(" left join ( ");
		sql.append(" select a.xmdm,c.id,b.xh,nvl(count(1),0)xshrs ");
		sql.append(" from xg_pjpy_pjxmwhb a, xg_xtwh_spbz b, xg_xtwh_spgw c,  ");
		sql.append(" xg_xtwh_spgwyh d ,xg_pjpy_pjxmshb e,xg_view_pjpy_pjryk f ");
		sql.append(" where a.lcid = b.splc and b.spgw = c.id and c.id = d.spgw ");
		sql.append(" and d.spyh = ?  and c.id=e.xtgwid and a.pjxn=e.pjxn and e.xh=f.xh ");
		sql.append(searchTjByUser);
		sql.append(" and a.pjxq=e.pjxq and a.pjnd=e.pjnd  and a.xmdm=e.xmdm ");
		sql.append(" and (shzt='wsh' or shzt='xcs') and (exists(  select 1 from(  ");
		sql.append(" select c.id,c.mc,b.xh plxh,e.* ");
		sql.append(" from xg_pjpy_pjxmwhb a, xg_xtwh_spbz b, xg_xtwh_spgw c,  ");
		sql.append(" xg_xtwh_spgwyh d ,xg_pjpy_pjxmshb e ");
		sql.append(" where a.lcid = b.splc and b.spgw = c.id and c.id = d.spgw ");
		sql.append(" and c.id=e.xtgwid and a.pjxn=e.pjxn ");
		sql.append(" and a.pjxq=e.pjxq and a.pjnd=e.pjnd ");
		sql.append(" and a.xmdm=e.xmdm)f where b.xh=f.plxh+1 and f.shzt='tg' ");
		sql.append(" and e.xmdm=f.xmdm and e.pjxn=f.pjxn and   ");
		sql.append(" e.pjxq=f.pjxq and e.pjnd=f.pjnd and e.xh=f.xh ");
		sql.append(" )or b.xh=1)group by a.xmdm,c.id,c.mc,b.xh ");
		sql.append(" )b on a.xmdm=b.xmdm and a.spgw=b.id  ");
		// ----------------------------δ�����Ϣͳ�� end------------------------------	
		
		// ----------------------------�������Ϣͳ�� begin----------------------------
		sql.append(" left join(select a.xmdm,c.id,b.xh,c.mc,nvl(count(1),0)shtgrs ");
		sql.append(" from xg_pjpy_pjxmwhb a, xg_xtwh_spbz b, xg_xtwh_spgw c,  ");
		sql.append(" xg_xtwh_spgwyh d ,xg_pjpy_pjxmshb e,xg_view_pjpy_pjryk f ");
		sql.append(" where a.lcid = b.splc and b.spgw = c.id  and c.id = d.spgw ");
		sql.append(" and d.spyh =? and c.id=e.xtgwid  and a.pjxn=e.pjxn and e.xh=f.xh ");
		sql.append(searchTjByUser);
		sql.append(" and a.pjxq=e.pjxq and a.pjnd=e.pjnd and a.xmdm=e.xmdm ");
		sql.append(" and shzt='tg' ");
		sql.append(" group by c.id,c.mc,a.xmdm,b.xh)c  on a.xmdm=c.xmdm and a.spgw=c.id  ");
		// ----------------------------�������Ϣͳ�� end ----------------------------
		
		searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"bmdm", "bmdm");
		// ----------------------------���걨���� begin ------------------------------
		sql.append(" left join (");
		sql.append(" select xmdm,sum(qdrs)qdrs from( select * from xg_pjpy_rsszb a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" )group by xmdm) d on a.xmdm=d.xmdm ");
		//----------------------------���걨���� end ------------------------------
		sql.append(" )a group by a.xmdm,a.xmmc,a.xmxz,a.xmfw,a.xmlx ");
		
		sql.append(" )a  where 1=1 ");
		
		//sql.append(searchTjByUser);

		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {
				userName, pjxn, pjxq, pjnd, userName, userName }, new String[] {
				"xmmc", "xmlx", "xshrs", "shtgrs","ksbrs", "xmdm" }, myForm);

	}
}
