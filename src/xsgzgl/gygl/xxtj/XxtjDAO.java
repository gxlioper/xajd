package xsgzgl.gygl.xxtj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewInit;

public class XxtjDAO extends GyglNewDAO{
	/**
	 * ��ȡ����¥�������Ҵ�λͳ��
	 * @param model
	 * @return
	 */
	@Deprecated
	public List<HashMap<String, String>> getLdqscwTj(XxtjModel model){
		StringBuilder sql = new StringBuilder();
		
		// ��ͳ�Ƶ�Ϊ¥�����룬¥�����ƣ��������ң�Ů�����ң��ѷ����������ң��ѷ���Ů�����ң�
		// ������λ����Ů����λ�����ѷ���������λ�����ѷ���Ů����λ��
		sql.append("select rownum r,a.lddm,a.ldmc,nvl(b.nsqs,0) nsqs,nvl(c.vsqs,0) vsqs,")
			.append("nvl(d.yfpnsqs,0) yfpnsqs,nvl(e.yfpvsqs,0) yfpvsqs,nvl(f.nscws,0) nscws,")
			.append("nvl(g.vscws,0) vscws,nvl(h.yfpnscws,0) yfpnscws,nvl(i.yfpvscws,0) yfpvscws,")
			.append("nvl(j.yrznscws, 0) yrznscws,nvl(k.yrzvscws, 0) yrzvscws ")
			.append("from xg_gygl_new_ldxxb a left join ")
			.append("(select lddm,count(*) nsqs from xg_gygl_new_qsxxb where qsxb='��' group by lddm) b ")
			.append("on a.lddm=b.lddm left join ")
			.append("(select lddm,count(*) vsqs from xg_gygl_new_qsxxb where qsxb='Ů' group by lddm) c ")
			.append("on a.lddm=c.lddm left join ")
			.append("(select lddm,count(*) yfpnsqs from xg_gygl_new_qsxxb ")
			.append("where xydm is not null and nj is not null and qsxb='��' ")
			.append("group by lddm) d on a.lddm=d.lddm left join ")
			.append("(select lddm,count(*) yfpvsqs from xg_gygl_new_qsxxb ")
			.append("where xydm is not null and nj is not null and qsxb='Ů' ")
			.append("group by lddm) e on a.lddm=e.lddm left join ")
			.append("(select lddm,count(*) nscws from view_xg_gygl_new_cwxx where qsxb='��' ")
			.append("group by lddm) f on a.lddm=f.lddm left join ")
			.append("(select lddm,count(*) vscws from view_xg_gygl_new_cwxx where qsxb='Ů' ")
			.append("group by lddm) g on a.lddm=g.lddm left join ")
			.append("(select lddm,count(*) yfpnscws from view_xg_gygl_new_cwxx where qsxb='��' ")
			.append("and xydm is not null and nj is not null group by lddm) h ")
			.append("on a.lddm=h.lddm left join ")
			.append("(select lddm,count(*) yfpvscws from view_xg_gygl_new_cwxx where qsxb='Ů' ")
			.append("and xydm is not null and nj is not null group by lddm) i ")
			.append("on a.lddm=i.lddm left join ")
			.append("(select lddm,count(*) yrznscws from view_xg_gygl_new_cwxx where qsxb='��' ")
			.append("and xh is not null group by lddm) j on a.lddm=j.lddm left join ")
			.append("(select lddm,count(*) yrzvscws from view_xg_gygl_new_cwxx where qsxb='Ů' ")
			.append("and xh is not null group by lddm) k on a.lddm=k.lddm order by a.lddm");
			
		
		String[] colList = new String[]{"lddm", "ldmc", "nsqs", "vsqs", "yfpnsqs", "yfpvsqs",
										"nscws", "vscws", "yfpnscws", "yfpvscws", "yrznscws", "yrzvscws"};
		
		return DAO.getInstance().getList(sql.toString(), new String[]{}, colList);
	}
	
	
	/**
	 * ��ȡ����¥�������Ҵ�λͳ��
	 */
	public List<HashMap<String, String>> getLdqscwTj_new(XxtjModel model){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String[] colList =null;
		if("1".equals(GyglNewInit.XQBJ)){
			colList = new String[]{"lddm", "ldmc","xqmc", "nsqs", "vsqs", "yfpnsqs", "yfpvsqs",	"nscws", "vscws", "yfpnscws", "yfpvscws", "yrznscws", "yrzvscws"};
			sql.append("select a.lddm,nvl(a.ldmc,d.xqmc||'�ϼ�') ldmc,a.xqdm,d.xqmc, ");
			if("12673".equals(Base.xxdm)){//���ɹŵ��ӿƼ�ְҵ����ѧԺ���Ի�
				sql.append("count(distinct case when b.qsxb='��' and (select count(1) from xg_gygl_new_cwxxb t1 where t1.qsh=b.qsh and t1.lddm=b.lddm  and t1.sfbl='��')>0 then a.lddm||','||b.qsh else null end  ) nsqs, ");
				sql.append("count(distinct case when b.qsxb='Ů' and (select count(1) from xg_gygl_new_cwxxb t1 where t1.qsh=b.qsh and t1.lddm=b.lddm  and t1.sfbl='��')>0 then a.lddm||','||b.qsh else null end  ) vsqs, ");
			}
			else{
				sql.append("count(distinct decode(b.qsxb,'��',a.lddm||','||b.qsh,null)) nsqs, ");
				sql.append("count(distinct decode(b.qsxb,'Ů',a.lddm||','||b.qsh,null)) vsqs, ");
			}
			sql.append("count(distinct case when b.xydm is not null and b.nj is not null and qsxb='��' then a.lddm||','||b.qsh else null end) yfpnsqs, ");
			sql.append("count(distinct case when b.xydm is not null and b.nj is not null and qsxb='Ů' then a.lddm||','||b.qsh else null end) yfpvsqs, ");
			if("12673".equals(Base.xxdm)){
				sql.append("count(case when qsxb='��' and cwh is not null  and c.sfbl='��' then 1 else null end) nscws, ");
				sql.append("count(case when qsxb='Ů' and cwh is not null  and c.sfbl='��' then 1 else null end) vscws, ");
			}
			else{
				sql.append("count(case when qsxb='��' and cwh is not null then 1 else null end) nscws, ");
				sql.append("count(case when qsxb='Ů' and cwh is not null then 1 else null end) vscws, ");
			}

			sql.append("count(case when c.xydm is not null and c.nj is not null and qsxb='��' and cwh is not null then 1 else null end) yfpnscws, ");
			sql.append("count(case when c.xydm is not null and c.nj is not null and qsxb='Ů' and cwh is not null then 1 else null end) yfpvscws, ");
			sql.append("count(case when qsxb='��' and xh is not null then 1 else null end) yrznscws, ");
			sql.append("count(case when qsxb='Ů' and xh is not null then 1 else null end) yrzvscws ");
			sql.append("from xg_gygl_new_ldxxb a left join xg_gygl_new_qsxxb b on a.lddm=b.lddm ");
			sql.append("left join xg_gygl_new_cwxxb c on b.lddm=c.lddm and b.qsh=c.qsh ");
			sql.append("left join DM_ZJU_XQ d on a.xqdm=d.dm ");
			if(StringUtils.isBlank(model.getXqdm())){
				sql.append("group by rollup((a.xqdm,d.xqmc),(a.lddm,a.ldmc)) order by a.xqdm,a.lddm ");
			}else{
				sql.append("where a.xqdm = ? ");
				params.add(model.getXqdm());
				sql.append("group by rollup((a.xqdm, d.xqmc,a.lddm,a.ldmc)) order by a.xqdm,a.lddm ");
			}
		}else{
			colList = new String[]{"lddm", "ldmc", "nsqs", "vsqs", "yfpnsqs", "yfpvsqs",	"nscws", "vscws", "yfpnscws", "yfpvscws", "yrznscws", "yrzvscws"};
			sql.append("select a.lddm,nvl(a.ldmc,'�ϼ�') ldmc, ");
			sql.append("count(distinct case when b.qsxb='��' and (select count(1) from xg_gygl_new_cwxxb t1 where t1.qsh=b.qsh and t1.lddm=b.lddm  and t1.sfbl='��')>0 then a.lddm||','||b.qsh else null end  ) nsqs, ");
			sql.append("count(distinct case when b.qsxb='Ů' and (select count(1) from xg_gygl_new_cwxxb t1 where t1.qsh=b.qsh and t1.lddm=b.lddm  and t1.sfbl='��')>0 then a.lddm||','||b.qsh else null end  ) vsqs, ");
			sql.append("count(distinct case when b.xydm is not null and b.nj is not null and qsxb='��' then a.lddm||','||b.qsh else null end) yfpnsqs, ");
			sql.append("count(distinct case when b.xydm is not null and b.nj is not null and qsxb='Ů' then a.lddm||','||b.qsh else null end) yfpvsqs, ");
			sql.append("count(case when qsxb='��' and cwh is not null then 1 else null end) nscws, ");
			sql.append("count(case when qsxb='Ů' and cwh is not null then 1 else null end) vscws, ");
			sql.append("count(case when c.xydm is not null and c.nj is not null and qsxb='��' and cwh is not null and c.sfbl='��' then 1 else null end) yfpnscws, ");
			sql.append("count(case when c.xydm is not null and c.nj is not null and qsxb='Ů' and cwh is not null and c.sfbl='��' then 1 else null end) yfpvscws, ");
			sql.append("count(case when qsxb='��' and xh is not null then 1 else null end) yrznscws, ");
			sql.append("count(case when qsxb='Ů' and xh is not null then 1 else null end) yrzvscws ");
			sql.append("from xg_gygl_new_ldxxb a left join xg_gygl_new_qsxxb b on a.lddm=b.lddm ");
			sql.append("left join xg_gygl_new_cwxxb c on b.lddm=c.lddm and b.qsh=c.qsh ");
			if(!StringUtils.isBlank(model.getXqdm())){
				sql.append("where a.xqdm = ? ");
				params.add(model.getXqdm());
			}
			sql.append("group by rollup((a.lddm,a.ldmc)) ");
		}

		return DAO.getInstance().getList(sql.toString(), params.toArray(new String[]{}), colList);
	}
	
	
	
	
	
	/**
	 * ��ȡ¥������ϸ��Ϣ����¥�����������Һʹ�λ
	 * @param lddm
	 * @param output
	 * @return
	 */
	public List<HashMap<String, String>> getLdxxOne(String lddm, String[] output,User user){
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (select a.*,(case  when a.yzr = 0 then '��' when a.yzr = a.cws then  '��' else '��'end) zszt, ");
		sql.append(" (case  when a.sfkck = 0 then '��' when a.sfkck = -1 then '��' else '��' end) ckqs,");
		sql.append("case when a.ssxys >1 then '��' else '��' end sfhz,case when a.blcws >0 then '��' else '��' end sfbl,");
		sql.append("b.ldmc,b.qsxb,b.ch,b.qsnj nj,b.qsxydm xydm,b.qsxymc xymc from ");
				//��ȡ���ҵĴ�λ��������������λ��������ס������������λ��
		sql.append("(select lddm,qsh,");
		if(!"xx".equals(user.getUserStatus())&&!"xy".equals(user.getUserStatus())&&!"12673".equals(Base.xxdm)){
		   sql.append("sum(case when  b.bjdm in(select bjdm from bzrbbb where zgh='"+user.getUserName()+"' union select bjdm from fdybjb where zgh= '"+user.getUserName()+"') then 1 else 0 end) sfkck,");
		}else{
		sql.append("-1 sfkck,");
		}
		sql.append("count(case when sfbl ='��' then 1 else 0 end) cws,sum(case when sfbl ='��' then 1 else 0 end) blcws," );
		sql.append("sum(case when a.xh is null then 0 else 1 end) yzr,count(distinct(a.xydm)) ssxys ");
		sql.append("from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh group by lddm,qsh) a ");
		sql.append("left join (select distinct(lddm||qsh)ldqs,lddm,qsh,ldmc,qsxb,ch,qsnj,qsxydm,qsxymc from view_xg_gygl_new_cwxx) b ");
		sql.append("on a.lddm=b.lddm and a.qsh=b.qsh order by b.ch desc,a.qsh) where  lddm=?");
		
/*		sql.append("select a.lddm,a.ldmc,a.ldxb,a.ch,a.qsh,a.xydm,b.bmmc xymc,a.nj,a.cws,a.yzr,")
			.append("(case when a.yzr=0 then '��' when a.yzr=a.cws then '��' else '��' end) zszt,sfhz from ")
			.append("(select a.lddm,a.ldmc,a.ldxb,a.qsh,a.ch,a.xydm,a.nj,nvl(b.cws,0) cws,nvl(c.yzr,0) yzr,decode(d.sfhz,'yes','��','��') sfhz from ")
			.append("(select a.lddm,b.ldmc,b.ldxb,a.ch,a.qsh,a.nj,a.xydm from xg_gygl_new_qsxxb a,xg_gygl_new_ldxxb b where a.lddm=? and a.lddm=b.lddm order by a.qsh)a left join ")
			.append("(select lddm,qsh,count(*) cws from xg_gygl_new_cwxxb where lddm=? group by lddm,qsh) b ")
			.append("on a.lddm=b.lddm and a.qsh=b.qsh left join ")
			.append("(select lddm,qsh,count(*) yzr from xg_gygl_new_cwxxb where xh is not null and lddm=? group by lddm,qsh) c ")
			.append("on a.lddm=c.lddm and a.qsh=c.qsh left join")
			.append("(select lddm,qsh,'yes' sfhz from xg_gygl_new_qsxxb a where exists (select 1 from xg_gygl_new_cwxxb b ")
			.append("where b.lddm=a.lddm and b.qsh = a.qsh and (b.xydm <> a.xydm or b.nj <> a.nj)))d ")
			.append("on a.lddm=d.lddm and a.qsh=d.qsh) a left join zxbz_xxbmdm b on a.xydm=b.bmdm");*/
		
		String[] input = new String[]{lddm}; 
		return DAO.getInstance().getList(sql.toString(), input, output);
	}
	
	/**
	 * ������Ϣͳ��
	 * @param lddm
	 * @param qsh
	 * @return
	 */
	public List<HashMap<String, String>> xxtjForQs(String lddm, String qsh){
		//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "a.cwh";
		}else{
			sb = "to_number(a.cwh)";
		}
		String sql = "select a.*,b.xm,b.mzmc,b.sjhm,b.bjdm from (select a.* from view_xg_gygl_new_cwxx a where lddm=? and qsh=?) a left join view_xsbfxx b on a.xh=b.xh order by "+sb+" ";
		
		return DAO.getInstance().getListNotOut(sql, new String[]{lddm, qsh});
	}
	public List<HashMap<String, String>> getAllLdxx(){
		String sql = "select * from xg_gygl_new_ldxxb";
		
		return DAO.getInstance().getListNotOut(sql, new String[]{});
	}
	/**
	 * 
	 * @����:¥����Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-20 ����04:02:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLcxx(String lddm){
		String sql = "select distinct ch from xg_gygl_new_qsxxb where lddm=? order by to_number(ch) desc";
		
		return DAO.getInstance().getListNotOut(sql, new String[]{lddm});
	}
	
	/**
	 * ��ȡ¥�����ҵ����λ��
	 * @param lddm
	 * @return
	 */
	public String getMaxCws(String lddm){
		String sql = "select nvl(max(count(*)),0) num from view_xg_gygl_new_cwxx where lddm = ? group by qsh";
		return DAO.getInstance().getOneRs(sql, new String[]{lddm}, "num");
	}
	/**
	 * ��ȡ¥����������� �㽭����ְҵ����ѧԺ
	 */
	public String getMaxQss_zjjszyjsxy(String lddm){
		String sql = "select max(count(*)) num from view_xg_gygl_new_qsxx where lddm = ? group by ch";
		return DAO.getInstance().getOneRs(sql, new String[]{lddm}, "num");
	}
	/**
	 * ��ȡ�������λ�� �㽭����ְҵ����ѧԺ
	 */
	public String getMaxQsCws_zjjszyjsxy(String lddm,String ch,String qsh){
		String sql = "select max(count(*)) num from view_xg_gygl_new_cwxx where lddm = ? and ch = ? and qsh = ? group by qsh";
		return DAO.getInstance().getOneRs(sql, new String[]{lddm, ch, qsh}, "num");
	}
	
	/**
	 * ��ȡ¥����Ϣ
	 * @param lddm
	 * @return
	 */
	public HashMap<String,String> getLdxx(String lddm){
		String sql = "select * from xg_gygl_new_ldxxb where lddm = ? ";
		return DAO.getInstance().getMap(sql, new String[]{lddm}, new String[]{"lddm","ldmc","ldxb"});
	}
	
	/**
	 * ��ȡ¥������list
	 * @param lddm
	 * @return
	 */
	public List<HashMap<String,String>> getQsList(String lddm){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.ldmc, a.qsh, a.qsxb, a.ch,b.bjmc,b.ct from view_xg_gygl_new_qsxx a  ");
		sql.append(" left join (select * from (select lddm,ldmc,qsh, wm_concat(bjmc) bjmc,count(1) ct from (select lddm,ldmc,qsh,bjdm,bjmc from (select a.*, b.bjdm, b.bjmc from (select lddm,ldmc, qsh, cwh, xh, nj, ch ");
		sql.append(" from view_xg_gygl_new_cwxx where xh is not null and lddm = ?  order by qsh, cwh) a  left join view_xsjbxx b on a.xh = b.xh) group by lddm,ldmc,qsh,");
		sql.append(" bjdm,bjmc) group by lddm,ldmc,qsh)where ct=1) b on a.qsh=b.qsh where a.lddm=?  order by a.ch,a.qsh desc");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{lddm,lddm});
	}
	/**
	 * ��ȡ¥������ס����Ϣlist
	 * @param lddm
	 * @return
	 */
	public List<HashMap<String,String>> getZsxxList(String lddm){
		String sql= "select a.ch,a.qsh,a.cwh,a.xh,b.xm,b.bjmc,c.bzrzgh,c.bzrxm,a.nj from (select ldmc,qsh,ch,cwh,xh,nj from view_xg_gygl_new_cwxx " +
				"where xh is not null and lddm = ? order by qsh,cwh ) a left join view_xsjbxx b on a.xh = b.xh"+
				 " left join (select bjdm,to_char(WM_CONCAT(zgh)) bzrzgh ,to_char(WM_CONCAT(xm)) bzrxm from (  select"+ 
				 " c.bjdm,c.zgh,d.xm from bzrbbb c left join yhb d on c.zgh=d.yhm) group by bjdm) c"+
				   " on b.bjdm=c.bjdm";
		return DAO.getInstance().getList(sql, new String[]{lddm}, new String[]{"qsh","cwh","xh","xm","bjmc","bzrzgh","bzrxm","nj"});
	}
	/**
	 * ��ȡ¥������ס����Ϣlist �㽭����ְҵ����ѧԺ
	 * @param lddm
	 * @return
	 */
	public List<HashMap<String,String>> getZsxxList_zjjszyjsxy(String lddm){
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.qsh,a.cwh,a.xh,b.xm,b.bjmc,c.yllb,d.lxmc yllbmc,  ");
		sql.append("  case when b.xm is null then (case when d.lxmc is null then '' else d.lxmc end) else b.xm end cellxm ");
		sql.append("  from ( ");
		sql.append("   select lddm,ldmc,qsh,cwh,xh from view_xg_gygl_new_cwxx where lddm = ? order by qsh desc,cwh  ");
		sql.append(" ) a left join view_xsjbxx b on a.xh = b.xh  ");
		sql.append(" left join xg_gygl_new_cwxxb c on a.lddm = c.lddm and a.qsh = c.qsh  and a.cwh = c.cwh ");
		sql.append(" left join gygl_yllb d on d.lxdm = c.yllb ");
		sql.append(" order by a.qsh desc,a.cwh asc ");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{lddm});
	}
	
	/**
	 * ��ȡ¥������list_�㶫����ְҵ����ѧԺ
	 * @param lddm
	 * @return
	 */
	public List<HashMap<String,String>> getQsList_gdjs(String lddm){
//		String sql= "select ldmc,qsh,qsxb from view_xg_gygl_new_qsxx where lddm = ? order by ch,qsh";
		String sql="select a.ldmc,a.qsh,a.qsxb,b.qsbj from view_xg_gygl_new_qsxx a "+
					"left join "+
					"( "+
					 "select qsh,to_char(wm_concat(distinct y.bjmc)) qsbj from xg_gygl_new_cwxxb x " +
					 "left join view_xsjbxx y on x.xh=y.xh where lddm=? and x.xh is not null group by qsh "+
					") b on a.qsh=b.qsh "+
					 "where lddm = ? order by a.ch,a.qsh";
		return DAO.getInstance().getList(sql, new String[]{lddm,lddm}, new String[]{"ldmc","qsh","qsxb","qsbj"});
	}
	/**
	 * ��ȡ¥������list_�㽭����ְҵ����ѧԺ
	 * @param lddm
	 * @return
	 */
	public List<HashMap<String,String>> getQsList_zjjszyjsxy(String lddm){
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.*,b.maxcws,f.lxmc yllbmc, ");
		sql.append("  case when e.bjnum is null then 0 else e.bjnum end bjnum, ");
		sql.append("  case when f.ylnum is null then 0 else f.ylnum end ylnum from ( ");
		sql.append("    select ch,lddm,ldmc,qsh,qsxb from view_xg_gygl_new_qsxx where lddm = ?  ");
		sql.append(" ) a left join ( ");
		sql.append("   select max(num) maxcws,ch from(  ");
		sql.append("      select count(*) num,ch,qsh from view_xg_gygl_new_cwxx where lddm = ? group by ch,qsh ");
		sql.append("   ) group by ch ");
		sql.append(" ) b on a.ch=b.ch ");
		sql.append(" left join ( ");
		sql.append("   select a.lddm,a.qsh,count(1) bjnum from ( ");
		sql.append("     select distinct a.lddm,a.qsh,b.bjmc from ( ");
		sql.append("       select lddm,ldmc,qsh,cwh,xh from view_xg_gygl_new_cwxx  ");
		sql.append("       where lddm = ? order by qsh desc,cwh  ");
		sql.append("     ) a left join view_xsjbxx b on a.xh = b.xh  ");
		sql.append("     where b.bjmc is not null ");
		sql.append("   ) a group by a.lddm,a.qsh ");
		sql.append(" ) e on a.lddm = e.lddm and a.qsh = e.qsh ");
		sql.append(" left join ( ");
		sql.append("   select a.lddm,a.qsh,c.lxmc,count(1) ylnum from view_xg_gygl_new_cwxx a ");
		sql.append("   left join xg_gygl_new_cwxxb b on a.lddm = b.lddm and a.qsh = b.qsh  and a.cwh = b.cwh ");
		sql.append("   left join gygl_yllb c on c.lxdm = b.yllb ");
		sql.append("   where a.lddm = ? and b.yllb is not null ");
		sql.append("   group by a.lddm,a.qsh,b.yllb,c.lxmc ");
		sql.append(" ) f on a.lddm = f.lddm and a.qsh = f.qsh ");
		sql.append(" order by a.ch desc,a.qsh desc ");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{lddm,lddm,lddm,lddm});
	}
	/**
	 * @throws SQLException 
	 * 
	 * @����:��ȡ����Ա������δ�����Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-8 ����11:02:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<String> getDbxx(String zgh) throws SQLException{
		String sql = "select bjdm from bzrbbb where zgh=? union select bjdm from fdybjb where zgh= ?";
		return DAO.getInstance().getList(sql, new String[]{zgh,zgh},"bjdm");

	}
	
	/**
	 * 
	 * @����: ��ȡ�༶�б�(��������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-16 ����10:02:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjListforDgList(String[] lddm){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select bjdm, bjmc, count(1) qsgs,wm_concat(qsh) qsh,wm_concat(qshflag) qshflag");
		sql.append(" from (select distinct bjdm, bjmc, lddm || qsh qsh,qsh qshflag");
		sql.append(" from VIEW_XG_GYGL_NEW_CWXX");
		sql.append(" where sfrz = '��' and lddm||qsh not in(select qsh from ");
		sql.append(" (select qsh,count(1) bjs from (");
		sql.append(" select distinct  lddm || qsh qsh,t.bjdm realbj from xg_gygl_new_cwxxb t left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH) group by qsh ) where bjs > 1) and lddm in (");
		for (int i = 0; i < lddm.length; i++) {
			sql.append("?");
			paraList.add(lddm[i]);
			if(i != lddm.length-1){
				sql.append(",");
			}
		}
		sql.append(" ))");
		sql.append("  group by bjdm, bjmc");
		sql.append(" order by bjdm, bjmc");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		return DAO.getInstance().getListNotOut(sql.toString(),paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: ��ȡ�༶�б�(�������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-16 ����10:02:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjListforHhList(String[] lddm){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select  qsh,wm_concat(bjdm) bjdm, wm_concat(bjmc) bjmc,qshflag");
		sql.append(" from (select distinct bjdm, bjmc, lddm || qsh qsh,qsh qshflag");
		sql.append(" from VIEW_XG_GYGL_NEW_CWXX");
		sql.append(" where sfrz = '��' and lddm||qsh  in(select qsh from");
		sql.append(" (select qsh,count(1) bjs from (");
		sql.append(" select distinct  lddm || qsh qsh,t.bjdm realbj from xg_gygl_new_cwxxb t left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH) group by qsh ) where bjs > 1) and lddm in (");
		for (int i = 0; i < lddm.length; i++) {
			sql.append("?");
			paraList.add(lddm[i]);
			if(i != lddm.length-1){
				sql.append(",");
			}
		}
		sql.append(" ))");
		sql.append("  group by qsh,qshflag");
		sql.append(" order by bjdm, bjmc");
		return DAO.getInstance().getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����:��ȡ��λ��Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-16 ����11:21:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qsh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getqsxx(String[] qsh){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select t1.xm,t.cwh,t.lddm || t.qsh qsh ");
		sql.append(" from VIEW_XG_GYGL_NEW_CWXX t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" ");
		sql.append(" where  t.lddm || t.qsh in(");
		sql.append(" ");
//		sql.append(" ");
		for (int i = 0; i < qsh.length; i++) {
			sql.append("?");
			paraList.add(qsh[i]);
			if(i != qsh.length-1){
				sql.append(",");
			}
		}
		sql.append(" ) and  sfrz = '��' order by (lddm || qsh),cwh asc");
		return DAO.getInstance().getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����:��ȡ¥����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-16 ����11:37:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLdmcXx(String[]lddm){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select lddm,ldmc from xg_gygl_new_ldxxb where lddm in(");
		for (int i = 0; i < lddm.length; i++) {
			sql.append("?");
			paraList.add(lddm[i]);
			if(i != lddm.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		sql.append(" order by lddm,ldmc asc");
		return DAO.getInstance().getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: ��ȡ��������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-19 ����11:10:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBzrxx(String[] bjdm){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select t.*,t2.bjmc");
		sql.append(" from fdyxxb t");
		sql.append(" left join bzrbbb t1");
		sql.append(" on t.zgh = t1.zgh");
		sql.append(" left join view_njxyzybj t2");
		sql.append(" on t1.bjdm = t2.bjdm");
		sql.append(" where t.zgh in (select zgh from bzrbbb where bjdm in(");
		for (int i = 0; i < bjdm.length; i++) {
			sql.append("?");
			paraList.add(bjdm[i]);
			if(i != bjdm.length-1){
				sql.append(",");
			}
		}
		
		sql.append(" ))");
		return DAO.getInstance().getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * @��������ȡ��λ��Ϣ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��7�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getCwxx(String lddm){
		StringBuffer sql =new StringBuffer();
		sql.append("select t.* ,mod((rank() over(partition by ch order by qsh,cwh)),4) position  from ( ");
		sql.append("select a.ch, a.qsh, a.cwh, a.xh, b.xm , b.bjdm, b.bjmc, b.zydm, b.zymc, b.xydm, b.xymc, c.fdyxm ");
		sql.append("from view_xg_gygl_new_cwxx a ");
		sql.append("left join view_xsjbxx b on a.xh = b.xh ");
		sql.append("left join (select a.bjdm, WM_CONCAT(b.xm) fdyxm ");
		sql.append("from fdybjb a left join fdyxxb b on a.zgh = b.zgh ");
		sql.append("group by a.bjdm) c on b.bjdm = c.bjdm ");
		sql.append("where a.xh is not null and b.bjdm is not null and lddm=? ");
		sql.append("order by ch,qsh,cwh )t ");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{lddm});
	}
	
	
	/**
	 * ��ȡУ���б�
	 * @return
	 */
	public List<HashMap<String,String>>getXqxx(){

		StringBuilder sql=new StringBuilder();
		sql.append(" select dm xqdm,xqmc from dm_zju_xq ");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{});
	}
	
}
