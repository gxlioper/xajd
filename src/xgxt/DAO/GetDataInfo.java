package xgxt.DAO;

import java.sql.SQLException;
import java.util.*;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.SearchUtils;

import common.Globals;

public class GetDataInfo {
	/**
	 * @��������������
	 *
	 */
	DAO dao = DAO.getInstance();
	
	/**
	 * @return ���˵�λ��ϸ��Ϣ
	 */
	public String[] getYrdwInfo(String setPara){
		String []getPara={"lxr","lxdh"};
		String sql = "select lxr,lxdh from yrdwdmb where yrdwdm=?";
		String []reVal = dao.getOneRs(sql, new String []{setPara}, getPara);
		return reVal;
	}
	
	/**
	 * �ڹ���ѧ���ݸ�λ���Ʋ�ѯ������λ
	 * @param pkValue
	 * @return String[]
	 * */
	public String[] getDwmc(String pkValue){
		String xxdm = StandardOperation.getXxdm();		
		String[] pk = pkValue.split("-");
		pkValue = pk[0]+pk[1];
		HashMap<String, String> map = new HashMap<String, String>();
		String[] dwInfo = new String[6];
		String sql = "select yrdwmc from yrdwdmb where yrdwdm = (select sqdw from gwxxb where gwdm||gwsbsj=?)";
		dwInfo[0] = dao.getOneRs(sql, new String[]{pkValue}, "yrdwmc");
		sql = "select fzr,lxdh,gzsj,gznr,ryyq from gwxxb where gwdm||gwsbsj=?";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			sql = "select (select lxr from yrdwdmb b where b.yrdwdm=a.sqdw)fzr, (select lxdh from yrdwdmb b where a.sqdw=b.yrdwdm)lxdh," +
				  "gzsj,gznr,ryyq from view_gwxx a where gwdm||gwsbsj=?";
		}
		map = dao.getMap(sql, new String[]{pkValue}, new String[]{"fzr","lxdh","gzsj","gznr","ryyq"});
		String fzr = map.get("fzr");
		String lxdh = map.get("lxdh");
		String gzsj = map.get("gzsj");
		String gznr = map.get("gznr");
		String ryyq = map.get("ryyq");
		dwInfo[1] = (fzr == null || "".equalsIgnoreCase(fzr))? "" : fzr ;
		dwInfo[2] = (lxdh == null || "".equalsIgnoreCase(lxdh))? "" : lxdh;
		dwInfo[3] = (gzsj == null || "".equalsIgnoreCase(gzsj))? "" : gzsj;
		dwInfo[4] = (gznr == null || "".equalsIgnoreCase(gznr))? "" : gznr;
		dwInfo[5] = (ryyq == null || "".equalsIgnoreCase(ryyq)) ? "" : ryyq;
		return dwInfo;
	}
	
	/**
	 * @param query����ѯ����
	 * @return ������Ϣ�б�
	 */
	public List getDormInfo(String query){
		String [] setpara = query.split("!!-!!");
		String sql = "";
		if(setpara != null){
			if("ass".equalsIgnoreCase(setpara[0])){
				sql = "select a.ssbh||'/'||a.sycws en,a.ssbh||'/'||a.cws||'/'||a.sycws cn from (select a.ssbh,a.cws,a.cws-nvl(b.rs,0) sycws from ssxxb a left join (select ssbh,count(*) rs from xszsxxb where jzrq is null group by ssbh order by ssbh) b on a.ssbh=b.ssbh where a.fpbj='һ��' and a.ssbh not in (select ssbh from ssfpb) and "
					+ "a.lddm like ? and a.cs like ?) a where a.sycws>0";
			}else{
				sql = "select b.ssbh,b.cwh,b.ssbh||'/'||b.cws||'/'||b.cwh en,b.ssbh||'/'||b.cws||'/'||b.sycws||'/'||b.cwh cn from (select a.*,a.cws-nvl(b.rs,0) sycws from ("
					+ "select a.*,b.cws from (select a.ssbh,a.cwh from cwxxb a where not exists (select * from ssfpb b where a.ssbh=b.ssbh and a.cwh=b.cwh )"
					+ ") a left join ssxxb b on a.ssbh=b.ssbh and b.fpbj='һ��') a left join (select ssbh,count(*) rs from ssfpb group by ssbh order by ssbh) b on a.ssbh=b.ssbh) "
					+ " b,ssxxb a where a.ssbh=b.ssbh and "
					+ "a.lddm like ? and a.cs like ? order by b.ssbh,b.cwh";
			}
		}
		List reVal = dao.getList(sql, new String[]{setpara[1],setpara[2]}, new String[] { "en","cn" });
		return reVal;
	}
	
	/**
	 * @param query ��ѯ����
	 * @return ���������Ƿ���� ����=1 ������=0 �������Ѿ�ͨ�����=2
	 * @throws SQLException
	 */
	public String IsDataExist(String query) throws SQLException{
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String [] setPara = null;
		String flag = "";
		
		sql = "select * from view_xsgwxx where xh=? and gwdm=? and gwsbsj=?";
		setPara = query.split("-");
		flag = dao.returntag(sql, new String []{setPara[0],setPara[1],setPara[2]});
		
		if("empty".equalsIgnoreCase(flag)){
			return "0";
		}else{
			sql = "select * from view_xsgwxx where xh=? and gwdm=? and gwsbsj=? and xxyj='ͨ��'";
			flag = dao.returntag(sql, new String []{setPara[0],setPara[1],setPara[2]});	
			
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){//�й����ʴ�ѧ
				sql = "select * from view_xsgwxx where xh=?";
				flag = dao.returntag(sql, new String []{setPara[0]});
				if("empty".equalsIgnoreCase(flag)){
					return "";
				}else{
					return "3";
				}				
			}
			if("empty".equalsIgnoreCase(flag)){
				return "1";
			}else{
				return "2";
			}
		}
	}
	
	/**
	 * @param query ��ѯ����
	 * @return ���������Ƿ���� 1��ʾ��ѧ���Ѿ������ڹ���ѧ�����Ѿ�ͨ����ˣ�֤��ѧ���ڸ� 0��ʾѧ�����ڸ�
	 * @throws SQLException
	 */
	public String zgdzdx_hg_IsDataExist(String query) throws SQLException{
		
		String sql = "select * from view_xsgwxx where xh=? and gwdm=? and gwsbsj=? and xxyj='ͨ��'";		
		String [] setPara = query.split("-");
		String flag = dao.returntag(sql, new String []{setPara[0],setPara[1],setPara[2]});
		if("empty".equalsIgnoreCase(flag)){//����ڸ�λ��Ϣ��ͼ�в�ѯ����0��ѧ�����������뻻��
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * @param query ��ѯ����
	 * @return ���������Ƿ���� 1��ʾ��ѧ���Ѿ������ڹ���ѧ�����Ѿ�ͨ����ˣ�֤��ѧ���ڸ� 0��ʾѧ�����ڸ�
	 * @throws SQLException
	 */
	public String zgdzdx_sqgw_IsDataExist(String query) throws SQLException{
		
		String sql = "select * from view_xsgwxx where xh=? and gwdm=? and gwsbsj=? and xxyj='ͨ��'";		
		String [] setPara = query.split("-");
		String flag = dao.returntag(sql, new String []{setPara[0],setPara[1],setPara[2]});
		if("empty".equalsIgnoreCase(flag)){//����ڸ�λ��Ϣ��ͼ�в�ѯ����0��ѧ�����������뻻�ڣ�����1��������ø�λ
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * @param obj
	 * @param xymc ѧԺ����
	 * @return ����ѧԺ�б�xymc-xydm
	 */
	public List getXyInfo(String obj,String xymc){
		ArrayList<Object> arrayList = new ArrayList<Object>();
		String sql = "select distinct xymc||'-'||xydm dm from view_njxyzybj where xymc like ? order by dm";
		List rsList = dao.getList(sql, new String[]{xymc}, new String[]{"dm"});
		arrayList.add(0, obj);
		arrayList.add(1,rsList);
		return arrayList;
	}
	
	/**
	 * @param obj
	 * @param xy ѧԺ
	 * @param zymc רҵ
	 * @return ����רҵ�б�zymc-zydm
	 */
	public List getZyInfo(String obj,String xy,String zymc){
		ArrayList<Object> arrayList = new ArrayList<Object>();
		String sql = "select distinct zymc||'-'||zydm dm from view_njxyzybj where xydm like ? and zymc like ? order by dm";
		List rsList = dao.getList(sql, new String[]{xy,zymc}, new String[]{"dm"});
		arrayList.add(0, obj);
		arrayList.add(1,rsList);
		return arrayList;
	}
	
	/**
	 * @param obj
	 * @param nj �꼶
	 * @param xy ѧԺ
	 * @param zy רҵ
	 * @param bjmc �༶����
	 * @return ���ذ༶�б�bjmc-bjdm
	 */
	public List getBjInfo(String obj, String nj, String xy, String zy, String bjmc){
		ArrayList<Object> arrayList = new ArrayList<Object>();
		String sql = "select distinct bjmc||'-'||bjdm dm from view_njxyzybj where nj like ? and xydm like ? and zydm like ? and bjmc like ? order by dm";
		List rsList = dao.getList(sql, new String[]{nj,xy,zy,bjmc}, new String[]{"dm"});
		arrayList.add(0, obj);
		arrayList.add(1,rsList);
		return arrayList;
	}
	
	/**
	 * @param xn ѧ��
	 * @param nd ���
	 * @param xq ѧ��
	 * @param yrdwdm ���˵�λ����
	 * @return ���ظ�λ��ϸ��Ϣ
	 */
	public String [] getGwDetInfo(String xn,String nd,String xq,String yrdwdm){
		String sql = "select a.xydm,a.gws,a.syrs,a.gwxz,a.hbzje,nvl(b.cjje,0) cjje,nvl(a.hbzje,0)-nvl(b.cjje,0) jyje from (select a.yrdwdm,a.xydm,b.gws,b.syrs,b.gwxz,c.hbzje from view_yrdwdmb a,(" +
				" select sqdw,gwxz,count(*) gws,sum(nvl(sqsyrs,0)) syrs from gwxxb a where shjg='ͨ��' and xn=? and xueqi=? and nd=? and sqdw=?" +
				" group by sqdw,gwxz) b,(select yrdwdm,sum(hbje) hbzje from jfhbb where xn=? and xq=? and nd=? and yrdwdm=? group by yrdwdm) c where a.yrdwdm=b.sqdw and a.yrdwdm=c.yrdwdm) a left join " +
				"(select sqdw,sum(cjje) cjje from (" +
				"select a.sqdw,a.xn,a.xueqi,a.nd,b.xh,b.cjje from gwxxb a,(" +
				"select a.xh,a.xn,a.xq,a.nd,a.gwdm,a.gwsbsj,b.cjje from xsgwxxb a,xscjffb b " +
				"where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) b " +
				"where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj) where xn=? and xueqi=? and nd=? and sqdw=? group by sqdw) b on a.yrdwdm=b.sqdw";
		String [] reValue = dao.getOneRs(sql, new String []{xn,xq,nd,yrdwdm,xn,xq,nd,yrdwdm,xn,xq,nd,yrdwdm}, new String[]{"xydm","gws","syrs","gwxz","hbzje","cjje","jyje"});
		return reValue;
	}
    /**
     * 
     * @param xqdmУ������
     * @param xbxd ¥���Ա��޶�
     * @return �������Ữ��¥���б�
     */
	public List<HashMap<String, String>> getSsFpLdList(String xqdm,String xbxd) {		
		String sql = "";
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(xqdm)?" and 1=2 ":"  and xqdm like '%"+xqdm+"%' ");
		querry.append(Base.isNull(xbxd)?" and 1=2  ":" and xbxd like '%" + xbxd +"%' ");
	    sql = "select '' dm,'--��ѡ��--' mc from sslddmb where rownum='1' union all select * from" +
	    		" (select distinct lddm dm,ldmc mc from sslddmb "+querry+" order by lddm)";
		return dao.getList(sql, new String[]{}, new String[] { "dm", "mc" });
	}
	/**
	 * 
	 * @param xqdmУ������
	 * @param xb�Ա�
	 * @param lddm¥������
	 * @param cs ����
	 * @param fpfs ���ַ�ʽ
	 * @return �������Ữ����������Ϣ�б�
	 */
	public List<HashMap<String, String>> getSsFpSsXxList2(String xqdm,String xb,String lddm,String cs) {		
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(lddm)?" and 1=2 ":" and lddm = '"+lddm+"' ");
		querry.append(Base.isNull(cs)?" and 1=1 ":" and cs = '"+cs+"' ");
		querry.append(Base.isNull(xqdm)?" and 1=1 ":" and xqdm = '"+xqdm+"' ");
		querry.append(Base.isNull(xb)?" and 1=1 ":" and xbxd = '"+xb+"' ");
		return ass_SsXsList(querry.toString());     		 
	}
	
	public List<HashMap<String, String>> getSsFpSsXxList(String xqdm,String xb,String lddm,String cs,String fpfs) {
		List<HashMap<String, String>> ssList = new ArrayList<HashMap<String, String>>() ;
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(lddm)?" and 1=1 ":" and lddm = '"+lddm+"' ");
		querry.append(Base.isNull(cs)?" and 1=1 ":" and cs = '"+cs+"' ");
		querry.append(Base.isNull(xqdm)?" and 1=1 ":" and xqdm = '"+xqdm+"' ");
		querry.append(Base.isNull(xb)?" and 1=1 ":" and xbxd = '"+xb+"' ");
		if(!Base.isNull(lddm) && !Base.isNull(cs) 
				&& !Base.isNull(xqdm) && !Base.isNull(xb) ){
			if(!Base.isNull(fpfs)&&fpfs.equalsIgnoreCase("acw")){
				ssList = acw_SsXsList(querry.toString());
			}else {
				ssList = ass_SsXsList(querry.toString());
			}
		}
		return ssList;
	}
	
	public List<HashMap<String, String>>acw_SsXsList(String querry){
		dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();		

		sql.append(" select ssbh || '/' || cws || '/' || cwh dm,ldmc || '/' || cs || '��' || '/' || qsh || '/' || cws || '/' ||sycws || '/' || cwh mc "); 
		sql.append(" from (select a.ssbh, a.cwh,a.cws,a.cs,a.lddm,a.ldmc,a.xbxd,a.xqdm,a.cws - nvl(a.rs, 0) sycws,a.qsh from (select a.ssbh,a.cwh, "); 
		sql.append(" b.cws,b.cs,b.lddm,b.qsh,( case when nvl((select count(xh)  from xszsxxb c where a.ssbh=c.ssbh),0)>=nvl((select sum(cws)  from ssfpb "); 
		sql.append(" d where a.ssbh=d.ssbh),0) then nvl((select count(xh)  from xszsxxb c where a.ssbh=c.ssbh),0) else nvl((select sum(cws)  from ssfpb d "); 
		sql.append(" where a.ssbh=d.ssbh),0) end ) rs,(select xbxd from sslddmb d where d.lddm = b.lddm) xbxd, "); 
		sql.append(" (select xqdm from sslddmb d where d.lddm = b.lddm) xqdm,(select ldmc from sslddmb d where d.lddm = b.lddm) ldmc from ( "); 
		sql.append(" select distinct a.ssbh, a.cwh from cwxxb a where not exists  (  select ssbh ,cwh from ( select distinct ssbh,cwh  "); 
		sql.append(" from xszsxxb union all select distinct ssbh, cwh from ssfpb ) b where a.ssbh=b.ssbh and a.cwh=b.cwh ) ) a "); 
		sql.append(" , ssxxb b where a.ssbh = b.ssbh and b.fpbj = 'һ��'    ) a where 1=1   "+querry+" order by ssbh, cwh) "); 
		
		List<HashMap<String, String>> ssList = dao.getList(sql.toString(), new String[]{}, new String[] { "dm", "mc" });
		return ssList;
	}
	public List<HashMap<String, String>>ass_SsXsList(String querry){
		StringBuffer sql = new StringBuffer();
		String xxdm = dao.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			sql.append(" select a.ssbh||'/'||a.sycws dm,(select d.ldmc  from ssxxb c,sslddmb d where c.ssbh = a.ssbh and c.lddm=d.lddm)||'/'|| cs || '��' || '/' || qsh || '/' ||a.cws||'/'||a.sycws mc from");
			sql.append(" (select a.ssbh,a.qsh,a.cs,a.cws,a.cws-nvl(b.rs,0) sycws from view_ssxx a left join ");
			sql.append(" ( select distinct a.ssbh,count( ssbh)rs from cwxxb a where  exists  (select ssbh ,cwh from (select distinct ssbh,cwh from xszsxxb ");
			sql.append("  union all select distinct ssbh, cwh from ssfpb ) b where a.ssbh=b.ssbh and a.cwh=b.cwh )group by ssbh ");
			sql.append("  ) b on a.ssbh=b.ssbh where a.fpbj='һ��'");
			sql.append(querry + ") a where a.sycws>0  order by dm ");
		}else{
			//(select yqmc from yqdmb b,ssxxb c,sslddmb d where c.ssbh = a.ssbh and c.lddm=d.lddm and d.yqdm=b.yqdm)||
			
			sql.append(" select a.ssbh||'/'||a.sycws dm,(select d.ldmc  from ssxxb c,sslddmb d where c.ssbh = a.ssbh and c.lddm=d.lddm)||'/'|| cs || '��' || '/' || qsh || '/' ||a.cws||'/'||a.sycws mc from");
			sql.append(" (select a.ssbh,a.qsh,a.cs,a.cws,(a.cws-nvl(b.rsb,0)-(select count(*)from wxs_xszsxxb f where f.ssbh=a.ssbh)-(select nvl(sum(cws),0) from ssfpb c where c.ssbh=a.ssbh )) sycws from view_ssxx a left join ");
			sql.append(" (  select ssbh ,sum(rs)rsb from (select ssbh,count(xh)rs from (select distinct xh,ssbh from xszsxxb a ");		
			sql.append("  where not exists(select xh,ssbh from ( select  distinct a.xh,a.ssbh from (select b.ssbh,xh,nvl(nj,(select nj from newstusinfo a where a.ksh=b.xh ))nj," );
			sql.append("  nvl(xydm, (select xydm from newstusinfo a where a.ksh=b.xh ))xydm from (select a.ssbh,a.xh,(select nj from view_xsjbxx b where a.xh=b.xh)nj,(select xydm from view_xsjbxx b where a.xh=b.xh)xydm from xszsxxb a ");	
			sql.append(" where exists(select 1 from view_ssxx c where a.ssbh = c.ssbh "+ querry+")");
			sql.append(" )b) a,ssfpb b where  a.ssbh = b.ssbh  and a.nj = b.nj and a.xydm = b.xydm )b  where a.xh=b.xh and a.ssbh=b.ssbh) ) a group by ssbh ) group by ssbh ");
			sql.append("  ) b on a.ssbh=b.ssbh where a.fpbj='һ��'");
			sql.append(querry + ") a where a.sycws>0  order by dm ");	
		}
		List<HashMap<String, String>> ssList = dao.getList(sql.toString(), new String[]{}, new String[] { "dm", "mc" });
		return ssList;
	}
	
	/**
	 * 
	 * @param lddm ¥������
	 * @param xqdm У������
	 * @param xydm ѧԺ����
	 * @param bjdm �༶����
	 * @return �������Ữ�����ѻ���������Ϣ�б�
	 */
	public List<HashMap<String, String>> getSsFpFpSsXxList(String lddm,String cs,String nj,String xydm,String bjdm) {
		StringBuffer sql = new StringBuffer();
		String xxdm = dao.getXxdm();
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		if(lddm==null){
			querry.append(" and 1=2 ");
		}
		querry.append(Base.isNull(lddm)?" and 1=1 ":" and lddm = '"+lddm+"' ");
		querry.append(Base.isNull(nj)?" and 1=1 ":" and nj = '"+ nj +"' ");		
		querry.append(Base.isNull(xydm)?" and 1=1 ":" and xydm = '"+ xydm +"' ");
		querry.append(Base.isNull(cs)?" and 1=1 ":" and cs = '"+ cs +"' ");	
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			querry.append(Base.isNull(bjdm)||bjdm.equalsIgnoreCase("bj")?" and 1=1 ":" and bjdm = '"+bjdm+"' ");			
			sql.append("select nj||'/'||xydm||'/'||bjdm||'/'||ssbh||'/'||sycws||'/'||cwh dm,bjmc||'/'||ldmc||'/'||cs||'��'||'/'||qsh||'/'||cws||'/'||sycws||'/'||cwh mc from ( ");
			sql.append("select distinct a.*,a.cws-nvl(a.rs,0)sycws from( select a.nj,a.xydm,a.bjdm,a.ssbh,a.cwh,b.cws,b.cs,b.qsh,c.lddm,c.ldmc,c.xqdm,d.zymc,d.bjmc,");
			sql.append(" (select count(distinct xydm||ssbh||cws||bjdm||cwh||nj) rs from ssfpb b where a.ssbh = b.ssbh and b.bjdm in(select bjdm from view_njxyzybj))rs from ssfpb a,ssxxb b,sslddmb  ");
			sql.append(" c,view_njxyzybj d where a.ssbh=b.ssbh and b.lddm=c.lddm and a.xydm=d.xydm and a.bjdm=d.bjdm )a)"+querry+"  order by lddm,cs,qsh,to_number(cwh)");			
		}else{
			sql.append("select  xydm||'/'||ssbh||'/'||fpcws||'/'||nj dm,nj||'/'||bmmc||'/'||ldmc||'/'||cs||'��/'||qsh||'/'||cws||'/'||fpcws mc from ");
			sql.append("(select (case  when a.nj is null then ' ' else a.nj end ) nj,a.xydm,d.bmmc,a.ssbh,a.cws fpcws,b.cs,b.cws,");
			sql.append("b.lddm,b.qsh,c.ldmc,c.xqdm,c.xbxd from ssfpb a,ssxxb b,sslddmb c,zxbz_xxbmdm d where a.ssbh=b.ssbh and ");
		    sql.append("b.lddm=c.lddm and a.xydm=d.bmdm)" + querry + " order by xydm,lddm,qsh ") ;
		}
		return dao.getList(sql.toString(), new String[]{}, new String[] { "dm", "mc" });
	}
	/**
	 * 
	 * @param xqdmУ������
	 * @param xydmѧԺ����
	 * @param bjdm�༶����
	 * @param nj�꼶
	 * @return  �������Ữ������ͳ�ƽ��
	 */
	 //����ѻ��ִ�λ��
	 public  String[] getSsFpYhfcws(String nj,String xydm,String bjdm){
		 String query = "";
		 if(nj != null && !nj.equals("")){
			 query += " and a.nj='"+nj+"' ";
		 }
		 if(!Base.isNull(xydm)&&!xydm.equalsIgnoreCase("null")){
			 query += " and a.xydm='"+xydm+"' ";
		 }
		 if(!Base.isNull(bjdm)&&!bjdm.equalsIgnoreCase("null")){
			 query += " and a.bjdm='"+bjdm+"' ";
		 }
		 DAO dao    = DAO.getInstance();		
		 String querr2 = " b.fpbj ='һ��' ";
		 String sql = "select boycws+girlcws+bgcws total,boycws,girlcws,bgcws from (select (select nvl(sum(a.cws),0) boycws "
		 		      +"from ssfpb a,view_ssxx b where a.ssbh=b.ssbh and "+querr2+" and b.xbxd='��' "+query+") boycws,(select nvl(sum(a.cws),0)" 
		 		      +" girlcws from ssfpb a,view_ssxx b where a.ssbh=b.ssbh and "+querr2+" and b.xbxd='Ů' "+query+") girlcws,(select nvl(sum(a.cws),0)"
		 		      +" bgcws from ssfpb a,view_ssxx b where a.ssbh=b.ssbh and "+querr2+" and b.xbxd='���' "+query+") bgcws from dual)";
		 return dao.getOneRs(sql, new String[]{}, new String[]{"total","boycws","girlcws","bgcws"});
	 }
	/**
	 * 
	 * @param lddm ¥������
	 * @return ��ȡ���Ữ����¥���б�
	 */
	public List<HashMap<String, String>> getSsFpCsList(String lddm) {
		String sql = "";
		sql = "select ''dm , '--��ѡ��--'mc from dual union all select dm,'��'||mc||'��'  from (select distinct cs dm,cs mc from ssxxb where lddm=? order by to_number(cs))";
		List<HashMap<String, String>> csList = dao.getList(sql, new String[] { lddm },
				new String[] { "dm","mc" });
		return csList;
	} 
	/**
	 * 
	 * @param xydmѧԺ���� 
	 * @param cwfp ���ലλ�����־
	 * @return ��ȡ��λ������У���б�
	 */
	public List<HashMap<String, String>> getCwFpXqList(String xydm,String cwfp,String nj,String bjdm){
		StringBuffer querry = new StringBuffer();
		String sql = ""; 
		//String clin = "--��ѡ���꼶��Ժϵ--";
		String clin = "--��ѡ��--";
		querry.append(" where a.ssbh=b.ssbh ");
		querry.append(Base.isNull(xydm)?" and 1=2 ":" and a.xydm='" + xydm + "' ");  //ѧԺ
		querry.append(Base.isNull(nj)?" and 1=1 ":" and a.nj='"+nj+"' ");		     //�꼶
		if(dao.getXxdm().equalsIgnoreCase(Globals.XXDM_HZZY)){//����ְҵ
			querry.append(Base.isNull(bjdm)?" and 1=2 ":" and a.bjdm='" + bjdm + "' ");
			clin = "��ѡ���꼶,Ժϵ,�༶";
		}
		if(!Base.isNull(cwfp)&&cwfp.equalsIgnoreCase("checked")){
			sql = " select '' dm,'"+clin+"' mc from dual union all select * from  (select distinct b.xqdm dm,b.xqmc mc from ssfpb a,view_ssxx b "+querry+" )";
		}else{
			sql = "select '' dm,'"+clin+"' mc from dual union all select * from (select distinct b.xqdm dm,b.xqmc mc from ssfpb a,view_ssxx b "+querry+" )";
		}
		//List aa = dao.getList(sql,new String[]{},new String[]{"dm","mc"});
		return dao.getList(sql,new String[]{},new String[]{"dm","mc"});
	}
	/**
	 * 
	 * @param xqdmУ������
	 * @param xydmѧԺ����
	 * @param xb�Ա�
	 * @param cwfp ���ലλ�����־
	 * @return��ȡ��λ������¥���б�
	 */
	public List<HashMap<String,String>>getCwFpLdList(String xqdm,String xydm,String xb,String cwfp,String nj){
		StringBuffer querry = new StringBuffer();
		querry.append(" where a.ssbh=b.ssbh ");	
		if(!Base.isNull(cwfp)&&cwfp.equalsIgnoreCase("checked")){
			querry.append(Base.isNull(xb)?" and 1=1 ":" and b.xbxd='" + xb + "' ");				
		}else{			
			querry.append(Base.isNull(xb)?" and 1=2 ":" and b.xbxd='" + xb + "' ");
			querry.append(Base.isNull(xydm)?" and 1=2 ":" and a.xydm='" + xydm + "' ");
			querry.append(Base.isNull(xqdm)?" and 1=2 ":"  and b.xqdm='" + xqdm + "' ");
			querry.append(Base.isNull(nj)?" and 1=2 ":"  and a.nj='" + nj + "' ");			
		}
		String sql = "select ''dm,'--��ѡ��--'mc from dual union all select * from (select distinct b.lddm dm,b.ldmc mc from ssfpb a,view_ssxx b "+querry+" )";
		return dao.getList(sql, new String[] {}, new String[] {"dm", "mc" });
	}
	
	/**
	 * 
	 * @param xqdmУ������
	 * @param xb�Ա�
	 * @return��ȡ��λ������¥���б�
	 */
	public List<HashMap<String,String>>getLdList(String xqdm,String xb){
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");	
		querry.append(Base.isNull(xb)?" and 1=2 ":" and xbxd='" + xb + "' ");
		querry.append(Base.isNull(xqdm)?" and 1=2 ":"  and xqdm='" + xqdm + "' ");
		String sql = "select '' dm,'--��ѡ��--' mc from dual union all select * from " +
					"(select distinct lddm dm,ldmc mc from view_ssxx "
					+ querry +" order by to_number(dm))";
		return dao.getList(sql, new String[] {}, new String[] {"dm", "mc" });
	}
	/**
	 * 
	 * @param xqdmѧ�ڴ���
	 * @param xydmѧԺ����
	 * @param lddm¥������
	 * @param xb�Ա�
	 * @param cs����
	 * @param cwfp���ലλ�����־
	 * @return��ȡ��λ���������ᴲλ��Ϣ�б�
	 */
	public synchronized List<HashMap<String,String>>getCwFpSsCwXxList(String xqdm,String xydm,String lddm,String xb,String cs,String cwfp,String nj,String bjdm){
		StringBuffer sql = new StringBuffer();
		String xxdm = dao.getXxdm();
		StringBuffer querry = new StringBuffer();
		if(!Base.isNull(cwfp)&&cwfp.equalsIgnoreCase("checked")){
			querry.append(Base.isNull(lddm)?" and 1=2 ":"  and b.lddm='" + lddm + "'");    //¥������
			querry.append(Base.isNull(xb)?" and 1=1 ":"  and b.xbxd='" + xb + "' ");       //�Ա�
			querry.append(Base.isNull(cs)?" and 1=1 ":"  and b.cs='" + cs + "' ");         // ����
			//querry.append(Base.isNull(nj)?" and 1=1 ":" and a.nj='"+nj+"' ");
			querry.append(" order by dm");
			//querry.append(" order by to_number(Replace(Replace(dm,'-',''),'/',''))");
			sql.append( "select distinct(a.ssbh||'/'||c.cwh) dm,b.ldmc||b.qsh||'/'||b.cws||'/'||c.cwh mc from ssfpb a,view_ssxx b,cwxxb c where a.ssbh=b.ssbh and a.ssbh=c.ssbh and a.ssbh||'-'||c.cwh not in (select ssbh||'-'||cwh from xszsxxb)"+querry);
		}else{
			querry.append(Base.isNull(xydm)?" and 1=2 ":"  and a.xydm='" + xydm + "'");
			querry.append((Base.isNull(xqdm)||xqdm.equalsIgnoreCase("null"))?" and 1=2 ":" and b.xqdm='" + xqdm + "'");
			querry.append((Base.isNull(lddm)||lddm.equalsIgnoreCase("null"))?" and 1=2 ":"  and b.lddm='" + lddm + "'");
			querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("null"))?" and 1=2 ":"  and b.xbxd='" + xb + "' ");
			querry.append((Base.isNull(cs)||cs.equalsIgnoreCase("null"))?" and 1=1 ":"  and b.cs='" + cs + "' ");
			querry.append((Base.isNull(nj))?" and 1=2 ":" and a.nj='"+nj+"' ");
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {//����ְҵ����ѧԺ				
				querry.append((Base.isNull(bjdm)||bjdm.equalsIgnoreCase("null"))?" and 1=1 ":" and a.bjdm='"+bjdm+"' ");			
				querry.append(" order by dm ");	
				sql.append(" select a.ssbh||'/'||a.cwh dm,b.ldmc||b.qsh||'/'||b.cws||'/'||a.cwh mc from ssfpb a,view_ssxx b where ");
				sql.append("a.ssbh=b.ssbh and not exists (select * from xszsxxb b where a.ssbh=b.ssbh and a.cwh=b.cwh) "+querry );
			} else {				
//				querry.append(" order by dm");
//				sql.append("select dm,mc from (select a.ssbh || '/' || c.cwh dm,b.ldmc||b.qsh || '/' || b.cws || '/' || c.cwh mc, ");
//				sql.append("a.ssbh,a.nj,a.xydm from ssfpb a, view_ssxx b, cwxxb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh and a.cws=b.cws ");
//				sql.append("and a.ssbh||c.cwh not in (select distinct ssbh||cwh from xszsxxb) union select a.ssbh || '/' || a.cwh en,  ");
//				sql.append("b.ldmc || b.qsh || '/' || b.cws || '/' || a.cwh cn,a.ssbh ,a.nj,a.xydm from ( select a.ssbh,cwh,xydm,nj from ( ");
//				sql.append("select a.ssbh, rank() over ( partition by a.ssbh,xydm,nj order by to_number(a.cwh) ) px,a.cwh,cws,countcwh,xydm,nj from ( ");
//				sql.append("select ssbh,cws,cwh,countcwh,xydm,nj from (select distinct a.ssbh, c.cwh, a.cws,countcwh,d.xydm,d.nj from ssfpb a,cwxxb c,  ");
//				sql.append("(select ssbh,countcwh,xydm,nj from (select distinct a.ssbh,count(b.cwh) countcwh,a.xydm,a.nj from (select a.ssbh,a.xydm,a.nj ");
//				sql.append("from ssfpb a,view_ssxx b where a.cws <> b.cws and a.ssbh=b.ssbh ) a left join (select distinct a.ssbh,a.cwh,b.nj,b.xydm  ");
//				sql.append("from xszsxxb a,view_xsjbxx b where a.xh=b.xh ) b on a.ssbh=b.ssbh and a.xydm=b.xydm and a.nj=b.nj group by a.ssbh,a.xydm,a.nj ");
//				sql.append(")) d where a.ssbh = c.ssbh and a.ssbh = d.ssbh and a.ssbh || c.cwh not in (select ssbh || cwh from xszsxxb) and d.countcwh<>a.cws ");
//				sql.append("and a.xydm=d.xydm and a.nj=d.nj )) a )a  where px between 1 and a.cws-a.countcwh ) a,view_ssxx b where a.ssbh = b.ssbh )a, ");
//				sql.append("view_ssxx b,ssfpb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh and c.xydm=a.xydm and c.nj=a.nj ");
//				sql.append(querry);
				//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
				String sb = "";
				if("12898".equals(Base.xxdm)){
					sb = "c.cwh";
				}else{
					sb = "to_number(c.cwh)";
				}
				sql.append(" select dm,mc from(select a.ssbh||'/'||c.cwh dm,b.ldmc||b.qsh||'/'||b.cws||'/'||c.cwh mc,");
				sql.append(" to_number(b.cws) - to_number((select count(ssbh) from xszsxxb b where a.ssbh = b.ssbh)) leaveCws,");
				sql.append("  (select count(ssbh)from view_xszsxx b where a.ssbh = b.ssbh and a.nj=b.nj and a.xydm=b.xydm)rzs,");			
   				sql.append(" row_number() over( partition by a.ssbh order by a.ssbh,"+sb+") px,a.cws fps  from ssfpb a,view_ssxx b,");
   				sql.append(" cwxxb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh");
   				sql.append(querry.toString());
   				sql.append(" and not exists (select 1 from (select distinct ssbh,cwh from xszsxxb) m where a.ssbh=m.ssbh and c.cwh=m.cwh) ");
   				sql.append(" and not exists (select 1 from wxs_xszsxxb n where a.ssbh=n.ssbh and c.cwh = n.cwh) ");
   				sql.append(")a where px <=fps-rzs");
   			
			}
		}
	    //System.out.println(sql.toString());
		return dao.getList(sql.toString(), new String[] {}, new String[] {"dm", "mc" });
	}
	
	
	/**
	 * 
	 * @param xqdmѧ�ڴ���
	 * @param lddm¥������
	 * @param xb�Ա�
	 * @param cs����
	 * @return��ȡ��λ���������ᴲλ��Ϣ�б�
	 */
	public synchronized List<HashMap<String,String>>getWfpcwxxList(String xqdm,String lddm,String xb,String cs){
		StringBuffer sql = new StringBuffer();
//		String xxdm = dao.getXxdm();
		StringBuffer querry = new StringBuffer();
		querry.append((Base.isNull(xqdm)||xqdm.equalsIgnoreCase("null"))?" and 1=2 ":" and a.xqdm='" + xqdm + "'");
		querry.append((Base.isNull(lddm)||lddm.equalsIgnoreCase("null"))?" and 1=2 ":" and a.lddm='" + lddm + "'");
		querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("null"))?" and 1=2 ":" and a.xbxd='" + xb + "' ");
		querry.append((Base.isNull(cs)||cs.equalsIgnoreCase("null"))?" and 1=1 ":" and a.cs='" + cs + "' ");
		sql.append("select a.ssbh||'/'||c.cwh dm,a.ldmc||a.qsh||'/'||a.cws||'/'||c.cwh mc ,a.ssbh||c.cwh sx ");
		sql.append("from  view_ssxx a,cwxxb c where  a.ssbh=c.ssbh ");
		sql.append(querry.toString());
		//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "c.cwh";
		}else{
			sb = "to_number(c.cwh)";
		}
		sql.append(" and a.ssbh||c.cwh not in (select distinct ssbh||cwh from gygl_jqlxxxb where ssbh is not null) order by a.ssbh,"+sb+" ");
	    //System.out.println(sql.toString());
		return dao.getList(sql.toString(), new String[] {}, new String[] {"dm", "mc" });
	}
	/**
	 * 
	 * @param xydmѧԺ����
	 * @param nj�꼶
	 * @param bjdm�༶����
	 * @return ��ȡ��λ���������ᴲλ��������ͳ�ƽ��
	 */
	public String[] getCwFpZsData(String xydm,String nj,String bjdm){
		String[] dataTem = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(xydm)?"and 1=1 ":" and xydm='" + xydm + "' ");
		querry.append(Base.isNull(nj)?" and 1=1 ":" and nj='" + nj + "' ");
		querry.append((Base.isNull(bjdm)||bjdm.equalsIgnoreCase("bj"))?" and 1=1 ":" and bjdm='" + bjdm + "' ");
	    sql.append("select boys+girls total,boys,girls from (select (select count(xh) from view_xsjbxx where xb='��' "+querry);
	    sql.append(" and xh not in (select xh from  xszsxxb)) boys ,(select count(xh) from view_xsjbxx where xb='Ů' "+querry);
	    sql.append("and xh not in (select xh from xszsxxb)) girls from dual)");
		dataTem = dao.getOneRs(sql.toString(), new String[] {}, new String[] {"total", "boys", "girls" });
		return dataTem;
	}
	/**
	 * 
	 * @param xydmѧԺ����
	 * @param nj�꼶
	 * @param bjdm�༶����
	 * @return ��ȡ��λ������δ�������ᴲλ��������ͳ�ƽ��
	 */
	public String[] getWfpcwzsData(String xn,String xq,String nj,String xydm){
		String[] dataTem = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer querry = new StringBuffer();
		dataTem = dao.getOneRs("select xqdm,xqmc from xqdzb where xqmc=?",new String[]{xq} , new String[]{"xqdm"});
		querry.append(Base.isNull(xn)?"and 1=1 ":" and a.xn='" + xn + "' ");
		querry.append(Base.isNull(xq)?" and 1=1 ":" and a.xq='" + dataTem[0] + "' ");
		querry.append(Base.isNull(nj)?" and 1=1 ":" and b.nj='" + nj + "' ");
		querry.append(Base.isNull(xydm)?"and 1=1 ":" and b.xydm='" + xydm + "' ");
		sql.append(" select boys+girls total,boys,girls from ");
		sql.append(" (select ");
		sql.append(" (select count(a.xh)  from gygl_jqlxxxb a ,view_xsxxb b where a.xh=b.xh and b.xb='��' and a.ssbh is null " + querry);
		sql.append(" ) boys, (select count(a.xh)  from gygl_jqlxxxb a ,view_xsxxb b where a.xh=b.xh and b.xb='Ů' and a.ssbh is null " + querry);
		sql.append(" ) girls  from dual)");
		dataTem = dao.getOneRs(sql.toString(), new String[] {}, new String[] {"total", "boys", "girls" });
		return dataTem;
	}
	/**
	 * 
	 * @param xydmѧԺ����
	 * @param nj�꼶
	 * @param bjdm�༶����
	 * @return ��ȡ��λ�������ѷ������ᴲλ��������ͳ�ƽ��
	 */
	public String[] getYfpcwzsData(String xn,String xq,String nj,String xydm){
		String[] dataTem = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer querry = new StringBuffer();
		dataTem = dao.getOneRs("select xqdm,xqmc from xqdzb where xqmc=?",new String[]{xq} , new String[]{"xqdm"});
		querry.append(Base.isNull(xn)?"and 1=1 ":" and a.xn='" + xn + "' ");
		querry.append(Base.isNull(xq)?" and 1=1 ":" and a.xq='" + dataTem[0] + "' ");
		querry.append(Base.isNull(nj)?" and 1=1 ":" and b.nj='" + nj + "' ");
		querry.append(Base.isNull(xydm)?"and 1=1 ":" and b.xydm='" + xydm + "' ");
		sql.append(" select boys+girls total,boys,girls from ");
		sql.append(" (select  (select count(a.xh)  from gygl_jqlxxxb a ,view_xsxxb b ");
		sql.append(" where a.xh=b.xh and b.xb='��' and a.ssbh is not null " + querry);
		sql.append(" ) boys, (select count(a.xh)  from gygl_jqlxxxb a ,view_xsxxb b ");
		sql.append(" where a.xh=b.xh and b.xb='Ů' and a.ssbh is  not null " + querry);
		sql.append(" ) girls  from dual)");
		dataTem = dao.getOneRs(sql.toString(), new String[] {}, new String[] {"total", "boys", "girls" });
		return dataTem;
	}
	/**
	 * 
	 * @param xydmѧԺ����
	 * @param nj�꼶
	 * @param bjdm�༶����
	 * @param xb�Ա�
	 * @return��ȡ��λ������ѧ���б�
	 */
	public  List<HashMap<String,String>> getCwFpSsXsList(String xydm,String nj,String bjdm,String xb){
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(xydm)?"  and 1=2 ":" and xydm='" + xydm + "' ");
		querry.append(Base.isNull(nj)?"  and 1=1 ":" and nj='" + nj + "' ");
		querry.append(Base.isNull(bjdm) || "bj".equalsIgnoreCase(bjdm) ? " and 1=1 "
						: " and bjdm='" + bjdm + "' ");
		querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("���"))?" and 1=1 ":" and xb='" + xb + "' ");
 	    String sql = " select xh dm,xh||'/'||xm||'/'||xb mc from view_xsjbxx where xh not in (select xh from xszsxxb)"
 	    	       + querry+" order by xb desc ,xh ";
 	    List<HashMap<String,String>> xsList = dao.getList(sql, new String[] {}, new String[] {
					"dm", "mc" });
		return xsList;
	}
	
	/**
	 * 
	 * @param xydmѧԺ����
	 * @param nj�꼶
	 * @param xnѧ��
	 * @param xqѧ��
	 * @param xb�Ա�
	 * @return��ȡ��λ������ѧ���б�
	 */
	public  List<HashMap<String,String>> getWfpxsxxList(String xn,String xq,String nj,String xydm,String xb){
		StringBuffer querry = new StringBuffer();
		String[] dataTem = null;
		dataTem = dao.getOneRs("select xqdm,xqmc from xqdzb where xqmc=?",new String[]{xq} , new String[]{"xqdm"});
		querry.append(Base.isNull(xn)?"  and 1=2 ":" and a.xn='" + xn + "' ");
		querry.append(Base.isNull(xq)?"  and 1=1 ":" and a.xq='" + dataTem[0] + "' ");
		querry.append(Base.isNull(xydm)?"  and 1=1 ":" and b.xydm='" + xydm + "' ");
		querry.append(Base.isNull(nj)?"  and 1=2 ":" and b.nj='" + nj + "' ");
		querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("���"))?" and 1=1 ":" and b.xb='" + xb + "' ");
 	    String sql = " select a.xh dm ,a.xh||'/'||b.xm||'/'||b.xb mc from gygl_jqlxxxb a ,view_xsxxb b where a.xh=b.xh and a.ssbh is null "
 	    	       + querry+" order by b.xb desc ";
 	    List<HashMap<String,String>> xsList = dao.getList(sql, new String[] {}, new String[] {
					"dm", "mc" });
		return xsList;
	}
	/**
	 * 
	 * @param xydm ѧԺ����
	 * @param nj�꼶
	 * @param bjdm�༶����
	 * @param cwfp ���ലλ�����־
	 * @return ��ȡ��λ�������ѷ��䴲λ��Ϣ�б�
	 */
	public  List<HashMap<String,String>> getCwFpFpCwList(String xydm,String nj,String bjdm,String xb,String cwfp,String lddm){
	   StringBuffer querry = new StringBuffer();
	   String sql = "";
	   querry.append("where 1=1 ");
	   querry.append(Base.isNull(xydm)?" and 1=2 ":" and xydm = '"+xydm+"'");
	   querry.append(Base.isNull(nj)?" and 1=1 ":" and nj = '"+nj+"' ");
	   querry.append(Base.isNull(bjdm) || "bj".equalsIgnoreCase(bjdm) ? " and 1=1 "
						: " and bjdm = '" + bjdm + "' ");
	   querry.append((Base.isNull(xb)||"���".equalsIgnoreCase(xb))?" ":" and xb = '"+xb+"' ");
	   querry.append(Base.isNull(lddm)?"":" and lddm = '"+lddm+"' ");
	   if(!Base.isNull(cwfp)&&cwfp.equalsIgnoreCase("checked")){
		   sql = "select ''dm,''mc from dual";
	   } else {	   
		   sql = "select distinct (xh||'/'||ssbh||'/'||cwh||'/'||nvl(rzrq,' ')) dm , xh||'/'||xm||'/'||xb||'/'||ldmc||qsh||'/'||cws||'/'||cwh||'/'||nvl(rzrq,' ') mc from view_xszsxx "+querry;
	   }
	   List<HashMap<String,String>> fpCwList = dao.getList(sql, new String[] {}, new String[] {	"dm", "mc" });
		return fpCwList;
	}
	
	/**
	 * 
	 * @param xydm ѧԺ����
	 * @param nj�꼶
	 * @param bjdm�༶����
	 * @param cwfp ���ലλ�����־
	 * @return ��ȡ��λ�������ѷ��䴲λ��Ϣ�б�
	 */
	public  List<HashMap<String,String>> getYfpqkxxList(String xn,String xq,String nj,String xydm,String xb){
	   StringBuffer querry = new StringBuffer();
	   String sql = "";
	   String[] dataTem = null;
		dataTem = dao.getOneRs("select xqdm,xqmc from xqdzb where xqmc=?",new String[]{xq} , new String[]{"xqdm"});
		querry.append(Base.isNull(xn)?"  and 1=2 ":" and a.xn='" + xn + "' ");
		querry.append(Base.isNull(xq)?"  and 1=1 ":" and a.xq='" + dataTem[0] + "' ");
		querry.append(Base.isNull(xydm)?"  and 1=1 ":" and b.xydm='" + xydm + "' ");
		querry.append(Base.isNull(nj)?"  and 1=2 ":" and b.nj='" + nj + "' ");
		querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("���"))?" and 1=1 ":" and b.xb='" + xb + "' ");
	   sql = "select dm,mc from ( select distinct (a.xh||'/'||a.ssbh||'/'||a.cwh) dm , a.xh||'/'||" +
	   		"b.xm||'/'||b.xb||'/'||c.ldmc||c.qsh||'/'||c.cws||'/'||cwh mc,b.xb from " +
	   		"gygl_jqlxxxb a,view_xsxxb b,view_ssxx c where a.xh=b.xh and a.ssbh=c.ssbh " +
	   		"and a.ssbh is not null "+querry + ") order by xb desc";
	   List<HashMap<String,String>> fpCwList = dao.getList(sql, new String[] {}, new String[] {	"dm", "mc" });
		return fpCwList;
	}
	/**
	 * 
	 * @param xydm ѧԺ����
	 * @param nj�꼶
	 * @param bjdm�༶����
	 * @param cwfp ���ലλ�����־
	 * @return ��ȡ��λ�������ѷ��䴲λ��Ϣ�б�
	 */
	public String[] getCwYfpZsData(String xydm,String nj,String bjdm){
		String[] dataTem = null;
		StringBuffer querry = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		querry.append(Base.isNull(xydm)?" and 1=1 ":" and xydm='" + xydm + "' ");
		querry.append(Base.isNull(nj)?" and 1=1 ":"and nj='" + nj + "' ");
		querry.append(Base.isNull(bjdm)?" and 1=1 ":" and bjdm='" + bjdm + "' ");	
		sql.append("select boy+girl total,boy,girl from (select (select count(xh) from view_xszsxx where xb='��'"+querry+" )boy,");
		sql.append("(select count(xh) from view_xszsxx where xb='Ů' "+querry+" ) girl from dual)");
		//System.out.println(sql.toString());
		dataTem = dao.getOneRs(sql.toString(), new String[] {}, new String[] {"total", "boy", "girl" });
		return dataTem;
	}
	
	public List<HashMap<String,String>> GegGyWxNrFzBmList(String wxnr){
		String sql = "select b.fzbmdm dm,b.fzbmmc mc from csmz_gywxnrdmb a ,csmz_gywxfzbmdmb b where a.fzbmdm=b.fzbmdm and a.nrdm=?";
		return dao.getList(sql, new String []{wxnr}, new String []{"dm","mc"});		
	}
	/**
	 * 
	 * @param lddm ¥������,nj �꼶
	 * @return ��ȡ��λ������¥���б�
	 */
	public List getcwFpCsList(String lddm,String nj,String xydm,String cwfp){
		String sql = "";
		StringBuffer querry = new StringBuffer();
		if(!Base.isNull(cwfp)&&cwfp.equalsIgnoreCase("checked")){			
			sql = "select distinct cs dm,'��'||cs||'��' mc from ssxxb  where ssbh not in(select ssbh from ssfpb) order by to_number(cs) ";
		}else{
			querry.append(Base.isNull(lddm)?" and 1=2 ":" and lddm='"+lddm+"' ");
			querry.append(Base.isNull(nj)?" and 1=2 ":" and nj='"+nj+"' ");
			querry.append(Base.isNull(xydm)?" and 1=2 ":" and xydm='"+xydm+"' ");
			sql = " select ''dm,'--��ѡ��--'mc  from dual union select distinct cs dm,'��'||cs||'��' mc from ssfpb a,ssxxb b where a.ssbh=b.ssbh "+querry+"  order by dm desc";
		}
		List<HashMap<String, String>> csList = dao.getList(sql, new String[] {},
				new String[] { "dm","mc" });
		return csList;
	}
	
	/**
	 * 
	 * @param lddm ¥������
	 * @return ��ȡ��λ������¥���б�
	 */
	public List getLcList(String lddm){
		String sql = "";
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(lddm)?" and 1=2 ":" and lddm='"+lddm+"' ");
		sql = " select '' dm,'--��ѡ��--' mc from dual union all select dm,mc from " +
				"(select distinct cs dm,'��'||cs||'��' mc from ssxxb a where 1=1 "
				+ querry + "  order by to_number(dm) desc)";
		List<HashMap<String, String>> csList = dao.getList(sql, new String[] {},
				new String[] { "dm","mc" });
		return csList;
	}
	/**
	 * 
	 * @param jzdm
	 * @return ��ȡ��ѵ������ϸ��Ϣ
	 */
	public String[] getJxjzDate(String jzdm, String nj) {
		String[] dataTem = null;
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
			sql = "select NVL(bzdm,'') bzdm,bzmc,bzdj,(case bzdj when '1' then 'Ӫ��' when '2' then '����' when '3' then '�༶1' when '4' then '�༶2' else bzdj end) bzdjmc,NVL(zdy,' ') zdy,NVL(jgmc,' ') jgmc,NVL((select getXbmzJxbzss(?,?,?) sT from dual),' ') ssjz,NVL(bz,' ') bz,xb from XBMZ_JXBZDMB a where bzdm=? and nj=? and xb=?";
			dataTem = dao.getOneRs(sql, new String[] { jzdm, nj, "Ů", jzdm, nj,
					"Ů" }, new String[] { "bzdm", "bzmc", "bzdj", "bzdjmc",
					"zdy", "jgmc", "ssjz", "bz", "xb" });
		} else {
			sql = "select xn,nj,bzdm,bzmc,bzdj,(select b.jzmc from jxjzdj b where a.bzdj = b.jzdm) bzdjmc,"
					+ " NVL((select b.xm from jxgl_jgxxb b where a.jgbh=b.jgbh),'-') jgmc,NVL((select b.xm from jxgl_ddjsxxb b where a.jsdm=b.jsdm),'-') zdy,sjdm,NVL((select getJxglbzss(?,?) sT from dual),'-') ssjz,NVL(bz,'-') bz,"
					+ " case when xb = '1' then '��' when xb = '2' then 'Ů' else '-' end xb from jxbzdmb a where bzdm=? and nj=?";
			dataTem = dao.getOneRs(sql, new String[] { jzdm, nj, jzdm, nj, },
					new String[] { "bzdm", "bzmc", "bzdj", "bzdjmc", "zdy",
							"jgmc", "ssjz", "bz", "xb","xn" });
		}

		return dataTem;
	}
	
	public String[] getXbmzJxjzDate(String jzdm,String nj,String sjdm){
		String[] dataTem = null;
		String sql = "";
		sql = "select bzdj from XBMZ_JXBZDMB where bzdm=?";
		String bzdj = dao.getOneRs(sql, new String[] { jzdm }, "bzdj");
		sql = "select xb from XBMZ_JXBZDMB where bzdm=?";
		String xb = dao.getOneRs(sql, new String[] { sjdm }, "xb");
		sql = "select NVL(bzdm,'') bzdm,bzmc,bzdj,(case bzdj when '1' then 'Ӫ��' when '2' then '����' when '4' then '�༶' else bzdj end) bzdjmc,NVL(zdy,' ') zdy,NVL(jgmc,' ') jgmc,NVL((select getXbmzJxbzss(?,?,?) sT from dual),' ') ssjz,NVL(bz,' ') bz,xb from XBMZ_JXBZDMB a where bzdm=? and nj=? ";
		if ("4".equals(bzdj)) {
			sql += "and xb=?";
			dataTem = dao.getOneRs(sql, new String[] { jzdm, nj, xb, jzdm, nj,
					xb }, new String[] { "bzdm", "bzmc", "bzdj", "bzdjmc",
					"zdy", "jgmc", "ssjz", "bz", "xb" });
		} else {
			dataTem = dao.getOneRs(sql,
					new String[] { jzdm, nj, xb, jzdm, nj, }, new String[] {
							"bzdm", "bzmc", "bzdj", "bzdjmc", "zdy", "jgmc",
							"ssjz", "bz", "xb" });
		}
			
		return dataTem;
	}
	
	/**
	 * 
	 * @return  �й����ʴ�ѧ������ѧ�����ͨ��չ�����޵õ�չ�ں�����������
	 */
	public String getZgdzdxGjzxdkZqqx(String xh,String zqnx){
		String zqhdkqx = "null";
		String num = dao.getOneRs("select count(*) num from zdgzgx_gjzxdk where shjg='ͨ��' and xh=?", new String[]{xh}, "num");
		
		if (!"0".equalsIgnoreCase(num)) {
			zqhdkqx = dao.getOneRs(
					"select nvl((substr(b.dkqx,10,4)+"+zqnx+"),substr(b.dkqx,10,4))||substr(b.dkqx,14,4) zqrq from view_zdgzgx_gjzxdk b where b.xh=?",
					new String[] { xh }, "zqrq");
		}
		return zqhdkqx;
	}
	
	/**
	 * 
	 * @return ����ѧ�𡪡��õ�������˾�������
	 */
	public String getJzxjPlshNum(String pk,String userType){
		String[] pkT = pk.split("!#!");
		int i = 0;
		
		if (pkT != null){
			String sql = "select count(*) num from jzxj_xssqb where xn||xq||xmdm||xh=?";
			if ("xx".equalsIgnoreCase(userType)){
				sql += " and xxsh<>'ͨ��'";
			} else {
				sql += " and xysh<>'ͨ��'";
			}
			for (int j = 0; j < pkT.length; j++){
				String numT = dao.getOneRs(sql, new String[]{pkT[j]}, "num");
				if (!"0".equalsIgnoreCase(numT)){
					i++;
				}
			}
		}
		
		return String.valueOf(i);
	}
	
	/**
	 * 
	 * @return  �������ϴ�ѧ---ͨ����ѧ�����õ���ѧ�������Ϣ
	 */
	public String[] getBjlhdxZxjxx(String zxjdm){
		String[] sT = dao.getOneRs("select zxjmc,zxjje from xszz_bjlh_gjzxjdmb where zxjdm=?", new String[]{zxjdm}, new String[]{"zxjmc", "zxjje"});
		
		return sT;
	}
	
	/**�й����ʴ�ѧ���˵�λ��λ����ѧ����ʱ��
	 * @throws Exception */
	public String insertYrdwghxslsb(String xh,String gwdm,String gwsbsj,String lxdh,String cz,String userName,String table) throws Exception{
		boolean res = false;
		String sql=null;
		try{
			if("table1".equalsIgnoreCase(table)){
				sql = "insert into zgdzdx_yrdwghxslsb (xh,gwdm,gwsbsj,lxdh,bj,yhm)values(?,?,?,?,?,?)";
				res = dao.insert(sql, new String[]{xh,gwdm,gwsbsj,lxdh,cz,userName});
			}else{
				
				sql = "insert into zgdzdx_yrdwghxslsb (xh,gwdm,gwsbsj,lxdh,bj,yhm)values(?,?,?,?,'����',?)";
				res = dao.insert(sql, new String[]{xh,gwdm,gwsbsj,lxdh,userName});
			}
		} catch(Exception e){
			res = false;
		}
		if(res){
			return "true";
		}
		return "ѧ�������ظ�����Ч,����!";
	}
	
	/**�й����ʴ�ѧ���˵�λ��λ����ѧ����ʱ���¼ɾ��
	 * @throws Exception */
	public String deleteYrdwghxslsb(String xh,String gwdm,String gwsbsj) throws Exception{
		boolean res = false;
		String sql = "delete zgdzdx_yrdwghxslsb where xh=? and gwdm=? and gwsbsj=?";
		res = dao.runUpdate(sql, new String[]{xh,gwdm,gwsbsj});
		if(res){
			return "ture";
		}
		return "ѧ�������쳣,����!";
	}
	
	/**
	 * ���ָ���������Ϣ(����)
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public String[] getTableInfo(String tableName, String[] colList, String pk,
			String pkValue, String query) {

		DAO dao = DAO.getInstance();

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk);
		sql.append(" = ? ");
		sql.append(query);

		String[] rs = dao.getOneRs(sql.toString(), new String[] { pkValue },
				colList);

		return rs;
	}
	
	/**
	 * ���ָ���������Ϣ(�б�)
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTableListInfo(String tableName,
			String[] colList, String pk, String pkValue, String query) {

		DAO dao = DAO.getInstance();

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(tableName);
		sql.append(" where 1 = 1 ");
		if (!Base.isNull(pk) && !Base.isNull(pkValue)) {
			sql.append(" and ");
			sql.append(pk);
			sql.append(" = '");
			sql.append(pkValue);
			sql.append("'");
		}
		sql.append(query);

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, colList);

		return list;
	}

	/**
	 * ���ָ���������Ϣ(�����б�)
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public List<String[]> getTableListArrInfo(String tableName,
			String[] colList, String pk, String pkValue, String query) {

		DAO dao = DAO.getInstance();

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(tableName);
		sql.append(" where 1 = 1 ");
		if (!Base.isNull(pk) && !Base.isNull(pkValue)) {
			sql.append(" and ");
			sql.append(pk);
			sql.append(" = '");
			sql.append(pkValue);
			sql.append("'");
		}
		sql.append(query);

		List<String[]> list = dao.rsToVator(sql.toString(), new String[] {},
				colList);

		return list;
	}
	
	/**
	 * ����DWR��ò�ѯ����(�����б�)
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public List<String[]> getRsListForPage(String tableName, String[] colList,
			String pk, String pkValue, String query, String[] pages) {

		DAO dao = DAO.getInstance();

		String pageSize = pages[0];
		String currentPage = pages[1];
		
		String start = String.valueOf((Integer.parseInt(currentPage) - 1)
				* Integer.parseInt(pageSize) + 1);

		String end = String.valueOf(Integer.parseInt(start)
				+ Integer.parseInt(pageSize));
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select * from (");
		sql.append(" select rownum r,t.* from ");
		sql.append(tableName);
		sql.append(" t where 1 = 1 ");

		if (!Base.isNull(pk) && !Base.isNull(pkValue)) {
			sql.append(" and ");
			sql.append(pk);
			sql.append(" = '");
			sql.append(pkValue);
			sql.append("'");
		}

		sql.append(query);
		sql.append(" ) ");
		
		String countSql = "select count(1) num from (" + sql.toString() + ")";
		String count = dao.getOneRs(countSql, new String[] {}, "num");
			
		sql.append(" where r >=" + start);
		sql.append(" and r <" + end);
		
		List<String[]> list = dao.rsToVator(sql.toString(), new String[] {},
				colList);

		List<String[]> rsList = new ArrayList<String[]>();
		rsList.add(new String[] { count });
		rsList.addAll(list);
		
		return rsList;
	}
	
	/**
	 * ���ָ������ֶ�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getTableZd(String tableName) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct zd from (select lower(COLUMN_NAME) zd ");
		sql.append(",length(lower(COLUMN_NAME)) cd ");
		sql.append("from user_tab_cols where table_name ");
		sql.append("in (upper('" + tableName + "'), upper('" + tableName
				+ "'))) order by cd");

		String[] zd = dao.getRs(sql.toString(), new String[] {}, "zd");

		CommService service = new CommService();
		zd=service.getOutValue(zd);
		
		return zd;
	}
	
	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		DAO dao = DAO.getInstance();

		return dao.getOneValue(tableName, dm, pk, pkValue);
	}
	
	/**
	 * ���ָ��ģ��Ľ������ʾ��ͷ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList, String path) {
		
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);

		return topTr;
	}
}
