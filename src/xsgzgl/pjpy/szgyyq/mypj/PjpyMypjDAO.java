package xsgzgl.pjpy.szgyyq.mypj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����_DAO��
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

public class PjpyMypjDAO extends CommDAO {

	/**
	 * ��ö���ͳ���б�(ѧ��)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getStuDshdTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.pf, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyq_dshdjzb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'dshd') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * ������Ա��ͳ���б�(ѧ��)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getStuYybdTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.pf, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyq_yybdjzb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'yybd') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * ���IVT��̳ͳ���б�(ѧ��)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getStuIvtltTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.pf, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyq_ivtltb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'ivtlt') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * �������ͳ���б�(ѧ��)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getStuWthdTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.pf, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyq_xthddjb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'wthd') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * �����֯����ͳ���б�(ѧ��)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getStuZznlTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.hdpf, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyc_zznlfzb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'zznl') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * ������ʵ��ͳ���б�(ѧ��)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getStuShsjTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.shfz, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyc_shsjfzb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'shsj') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * ���5S��ͳ���б�(ѧ��)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getStuFiveSTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.jjf, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.fz, 0) zzf, a.jjf,a.sqf, a.xysh, a.xxsh ");
		sql.append("from szyc_5sb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'wsmk') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"jjf", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * ����ۺ����ʷַ�ͳ���б�(ѧ��)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getStuZhczfTjList(PjpyMypjForm model, User user) {

		StringBuilder sql = new StringBuilder();

		String xh = user.getUserName();
		String xn = Base.currXn;
		String xq = Base.currXq;

		sql.append("select a.sqf, a.zzf, a.bzrsh, a.xysh, a.xxsh,nvl(b.jcf,0) jcf,nvl(b.zgf,0) zgf ");
		sql.append("from (select nvl(a.pf, 0) zzf, a.sqf, a.bzrsh, a.xysh, a.xxsh ");
		sql.append("from szyq_dshdjzb a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xq = ? ");
		sql.append("and a.xh = ?) a ");
		sql.append("left join (select zxmjcf jcf, zxmzgf zgf ");
		sql.append("from szgy_zhszycfsszb ");
		sql.append("where zxmdm = 'dshd') b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "sqf", "zzf",
						"bzrsh", "xysh", "xxsh", "jcf", "zgf" });

		return list;
	}
	
	/**
	 * ��ȡѧУ����ͳ���б�
	 * @param model
	 * @param user
	 * @return
	 * @author gaobb
	 */
	public List<HashMap<String,String>> getXxMyPjTjList(User user){
		String userType=user.getUserType();
		String view_xsjbxx=Base.xsxxb;
		//����
		String tj=" a where xn='"+Base.currXn+"' and xq='"+Base.currXq+"' ";
		String tj_5s=tj;//5sû�а��������
		if("xy".equals(userType)){
			tj+=" and bzrsh='ͨ��' and (xysh='δ���' or xysh='������') and exists (select 1 from view_xsjbxx x where x.xh=a.xh and x.xydm='"+user.getUserDep()+"'))" +
					" group by xn,xq ";
			tj_5s+=" and (xysh='δ���' or xysh='������') and exists (select 1 from "+view_xsjbxx+" x where x.xh=a.xh and x.xydm='"+user.getUserDep()+"')) " +
					" group by xn,xq ";
		}else{
			tj+=" and xysh='ͨ��' and xxsh='δ���' ) group by xn,xq ";
			tj_5s=tj;
		}
		//����Ͷ������
		String sststj = " a where xn='" + Base.currXn + "' and xq='" + Base.currXq + "' ";
		sststj += " and clr is null ";
		if("xy".equals(userType)){
			sststj+=" and exists (select 1 from "+view_xsjbxx+" x where x.xh=a.xh and x.xydm='"+user.getUserDep()+"')) ";
		}else{
			sststj+=" ) ";
		}

		StringBuffer sql=new StringBuffer();
		sql.append("select a.xmlx,a.xmmc,nvl(d.xshrs,'0') xshrs,nvl(b.ssrs,'0') ssrs,nvl(c.tsrs,'0') tsrs from ");
		
		sql.append("("); 
		sql.append("select '����' xmmc,'szyq_dshdjzb' xmlx from dual ");
		sql.append("union all  ");
		sql.append("select '���Ա��' xmmc,'szyq_yybdjzb' xmlx from dual ");
		sql.append("union all  ");
		sql.append("select 'ITV��̳' xmmc,'szyq_ivtltb' xmlx from dual ");
		sql.append("union all  ");
		sql.append("select '����' xmmc,'szyq_xthddjb' xmlx from dual ");
		sql.append("union all  ");
		sql.append("select '��֯����' xmmc,'szyc_zznlfzb' xmlx from dual ");
		sql.append("union all  ");
		sql.append("select '���ʵ��' xmmc,'szyc_shsjfzb' xmlx from dual ");
		sql.append("union all  ");
		sql.append("select '5S' xmmc,'szyc_5sb' xmlx from dual ");
		sql.append(") a "); 
		
		sql.append("left join "); 
		sql.append("("); 
		sql.append("select '����' xmmc,'szyq_dshdjzb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyq_dshdjzb ").append(tj);
		sql.append("union all  ");
		sql.append("select '���Ա��' xmmc,'szyq_yybdjzb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyq_yybdjzb ").append(tj);
		sql.append("union all  ");
		sql.append("select 'ITV��̳' xmmc,'szyq_ivtltb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyq_ivtltb ").append(tj);
		sql.append("union all  ");
		sql.append("select '����' xmmc,'szyq_xthddjb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyq_xthddjb ").append(tj);
		sql.append("union all  ");
		sql.append("select '��֯����' xmmc,'szyc_zznlfzb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyc_zznlfzb ").append(tj);
		sql.append("union all  ");
		sql.append("select '���ʵ��' xmmc,'szyc_shsjfzb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyc_shsjfzb ").append(tj);
		sql.append("union all  ");
		sql.append("select '5S' xmmc,'szyc_5sb' xmlx,count(1) xshrs from (select distinct xn,xq,xh from szyc_5sb ").append(tj_5s);
		sql.append(") d on a.xmlx=d.xmlx "); 
		
		sql.append("left join "); 
		sql.append("(select xmlx,count(1) ssrs from (select distinct xn,xq,xh,xmlx from xg_pjpy_szgyyq_xsssb "+sststj+" group by xmlx) b on a.xmlx=b.xmlx ");
		sql.append("left join ");
		sql.append("(select xmlx,count(1) tsrs from (select distinct xn,xq,xh,xmlx from xg_pjpy_szgyyq_xstsb "+sststj+" group by xmlx) c on a.xmlx=c.xmlx ");
		
		DAO dao = DAO.getInstance();
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xmlx","xmmc","xshrs","ssrs","tsrs"});
	}
	
	/**
	 * ��ȡ����������ͳ���б�
	 * @param model
	 * @param user
	 * @return
	 * @author gaobb
	 */
	public List<HashMap<String,String>> getBzrMyPjTjList(User user){
		//����
		String tj=" a where xn='"+Base.currXn+"' and xq='"+Base.currXq+"' "+
		    " and exists (select 1 from "+Base.xsxxb+" x,bzrbbb y where x.bjdm=y.bjdm and x.xh=a.xh and y.zgh='"+user.getUserName()+"')) group by xn,xq ";
		//�ֶ�
		String field=" count(case when bzrsh='δ���' then 1 when bzrsh='������' then 1 end) xshrs,count(case when xysh='ͨ��' then 1 end) xyshtgrs, " +//���������,ѧԺ���ͨ��������ѧУ���ͨ������
				"count(case when xxsh='ͨ��' then 1 end) xxshtgrs";
 
		StringBuffer sql=new StringBuffer();
		sql.append("select a.* from ( ");
		sql.append("select '����' xmmc,'szyq_dshdjzb' xmlx,"+field+" from ( select distinct xn,xq,xh,bzrsh,xysh,xxsh from szyq_dshdjzb ").append(tj);
		sql.append("union all  ");
		sql.append("select '���Ա��' xmmc,'szyq_yybdjzb' xmlx,"+field+" from ( select distinct xn,xq,xh,bzrsh,xysh,xxsh from szyq_yybdjzb ").append(tj);
		sql.append("union all  ");
		sql.append("select 'ITV��̳' xmmc,'szyq_ivtltb' xmlx,"+field+" from ( select distinct xn,xq,xh,bzrsh,xysh,xxsh from szyq_ivtltb ").append(tj);
		sql.append("union all  ");
		sql.append("select '����' xmmc,'szyq_xthddjb' xmlx,"+field+" from ( select distinct xn,xq,xh,bzrsh,xysh,xxsh from szyq_xthddjb ").append(tj);
		sql.append("union all  ");
		sql.append("select '��֯����' xmmc,'szyc_zznlfzb' xmlx,"+field+" from ( select distinct xn,xq,xh,bzrsh,xysh,xxsh from szyc_zznlfzb ").append(tj);
		sql.append("union all  ");
		sql.append("select '���ʵ��' xmmc,'szyc_shsjfzb' xmlx,"+field+" from ( select distinct xn,xq,xh,bzrsh,xysh,xxsh from szyc_shsjfzb ").append(tj);
		sql.append(") a "); 
		
		DAO dao = DAO.getInstance();
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xmlx", "xmmc", "xshrs", "xyshtgrs", "xxshtgrs" });
	}
	
	/**
	 * ����ͳ��Ϊ�յ����
	 * @param list
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>> xxbzrMyPjCheckEmpty(List<HashMap<String,String>>  list,String type){
		String[] xmmcs;
		if("bzr".equals(type)){
			xmmcs=new String[]{"����","���Ա��","ITV��̳","����","��֯����","���ʵ��"};
		}else{
			xmmcs=new String[]{"����","���Ա��","ITV��̳","����","��֯����","���ʵ��","5S"};
		}
		boolean mark=false;
		for(int i=0;i<xmmcs.length;i++){
			mark=false;
			for(int j=0;j<list.size();j++){
				if(xmmcs[i].equals(list.get(j).get("xmmc"))){
					mark=true;
					break;
				}
			}
			if(!mark){
				HashMap<String,String> map=new HashMap<String, String>();
				map.put("xmmc", xmmcs[i]);
				map.put("xshrs", "0");
				map.put("ssrs", "0");
				map.put("tsrs", "0");
				list.add(map);
			}
		}
		return list;
	}
	
}
