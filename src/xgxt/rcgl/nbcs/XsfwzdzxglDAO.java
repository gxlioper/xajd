package xgxt.rcgl.nbcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class XsfwzdzxglDAO extends DAO{
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * ��ȡ��ѯ����
	 * */
	public StringBuffer getWhereSql(XsfwzdzxglForm model){
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xh = model.getXh();
		String xm = model.getXm();
		String slryhm = model.getSlryhm();
		String slrxm = model.getSlrxm();
		String slbmdm = model.getSlbmdm();
		String nd = model.getNd();
		String yf = model.getYf();
		String bjsj = model.getBjsj();
		String fwzl = model.getFwzl();
		
		if(!StringUtils.isNull(nj)){
			sb.append( " and nj=?");
			value.add(nj);
		}
		if(!StringUtils.isNull(xydm)){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(!StringUtils.isNull(zydm)){
			sb.append( " and zydm=?");
			value.add(zydm);
		}
		if(!StringUtils.isNull(bjdm)){
			sb.append( " and bjdm=?");
			value.add(bjdm);
		}
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		if(!StringUtils.isNull(slryhm)){
			sb.append( " and slryhm like '%'||?||'%'");
			value.add(slryhm);
		}
		if(!StringUtils.isNull(slrxm)){
			sb.append( " and slrxm like '%'||?||'%'");
			value.add(slrxm);
		}
		if(!StringUtils.isNull(slbmdm)){
			sb.append( " and slbmdm=?");
			value.add(slbmdm);
		}
		if(!StringUtils.isNull(nd)){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(!StringUtils.isNull(yf)){
			sb.append( " and yf=?");
			value.add(yf);
		}
		if(!StringUtils.isNull(bjsj)){
			sb.append( " and bjsj=?");
			value.add(bjsj);
		}
		if(!StringUtils.isNull(fwzl)){
			sb.append( " and fwzl=?");
			value.add(fwzl);
		}
		return sb;
	}
	
	/**
	 * ����¼�Ƿ����
	 * @param String tableName
	 * @param String pk
	 * @param String pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*)num from " + tableName + " where " + pk + " =?";
		String result = getOneRs(sql, new String[]{pkValue},"num");
		return Integer.parseInt(StringUtils.isNull(result) ? "0" : result) >0 ? true : false;
	}
	
	/**
	 * ��ѯѧ��������������Ϣ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectXslfslbmdmb(){
		String sql = "select distinct slbmdm,slbmmc from xslfslbmdmb order by slbmdm";
		String[] col = {"slbmdm","slbmmc"};
		return getList(sql, new String[]{},col);
	}
	
	/**
	 * ��ѯѧ�����ò�����������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXslfbmslrb(XsfwzdzxglForm model){
		String[] outputValue = {"pk","slbmmc","slryhm","slrxm","slrlxdh"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select  rownum r, a.* from (select slbmdm||slryhm pk,slbmdm,slbmmc,slryhm,slrxm,slrlxdh from view_xslfbmslrb a" + whereSql + ")a";
		Pages paganitionModel = model.getPages();
		paganitionModel.setMaxRecord(Integer.parseInt(getOneRs("select count(*) num from view_xslfbmslrb" + whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num")));
		sql = "select * from (" + sql + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart()+model.getPages().getPageSize()); 
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * ��ѯѧ�����ò�����������Ϣ����
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXslfbmslrbForExp(XsfwzdzxglForm model){
		String[] outputValue = {"slbmdm","slbmmc","slryhm","slrxm","slrlxdh"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select rownum r,slbmdm||slryhm pk,slbmdm,slbmmc,slryhm,slrxm,slrlxdh from view_xslfbmslrb";
		
		return rsToVator(sql+whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	
	/**
	 * ����������ѯѧ�����ò�����������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXslfbmslrbOne(XsfwzdzxglForm model){
		String[] outputValue = {"pk","slbmdm","slbmmc","slryhm","slrxm","slrlxdh"};
		String sql = "select slbmdm||slryhm pk,slbmdm,slbmmc,slryhm,slrxm,slrlxdh from view_xslfbmslrb where slbmdm||slryhm=?";
		
		return getMap(sql, new String[]{model.getPkValue()}, outputValue);
	}
	
	/**
	 * ���ѧ�����ò�����������Ϣ�Ƿ��Ѿ�����
	 * @param HashMap<String, String> map
	 * @return boolean
	 * */
	public boolean checkXslfbmlxr(HashMap<String, String> map){		
		return checkExists("xslfbmslrb", "slbmdm||slryhm", map.get("slbmdm")+map.get("slryhm"));
	}
	
	/***
	 * �����û�����ѯ�û�����
	 * @param String yhm
	 * @return String
	 * */
	public String checkYhm(String yhm){
		String sql = "select xm from view_yhxx where yhm=?";
		String xm = getOneRs(sql, new String[]{yhm}, "xm");
		if(StringUtils.isNull(xm)){
			sql = "select xm from view_xsjbxx where xh=?";
			xm = getOneRs(sql, new String[]{yhm}, "xm");
		}
		return xm;
	}
	
	/**
	 * ��ѯѧ��������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXslfglb(XsfwzdzxglForm model){
		String[] outputValue = {"pk","xh","nd","yf","slrxm","slsj","lfsy","hfr","hfsj","bjsj","fwzl"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select  rownum r, a.* from (select xh||lfrq pk,xh,nd,yf,slr,slrxm,slsj,lfsy,hfr,hfsj,bjsj,fwzl from view_xslfglb a" + whereSql + ")a";
		
		Pages paganitionModel = model.getPages();		
		paganitionModel.setMaxRecord(Integer.parseInt(getOneRs("select count(*) num from view_xslfglb" + whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num")));
		sql = "select * from (" + sql + ") where r>" + paganitionModel.getStart() + " and r<=" + (paganitionModel.getStart()+paganitionModel.getPageSize()); 
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * ��ѯѧ��������Ϣ����
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXslfglbForExp(XsfwzdzxglForm model){
		String[] outputValue = {"nd","yf","xh","xm","xb","xz","xydm","xymc","zydm","zymc","bjdm","bjmc","nj","lffs","lfrq","lfsy","slbmdm","slbmmc","slr","slrxm","slsj","hfr","hfsj","bjsj","fwzl","lxdh","bz"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select nd,yf,xh,xm,xb,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,lffs,lfrq,lfsy,slbmdm,slbmmc,slr,slrxm,slsj,hfr,hfsj,bjsj,fwzl,lxdh,bz from view_xslfglb";
		
		return rsToVator(sql+whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * ��ѯѧ������δ������������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectXslfglbForWbl(XsfwzdzxglForm model){
		String[] outputValue = {"nd","yf","xh","xm","xb","xz","xydm","xymc","zydm","zymc","bjdm","bjmc","nj","lffs","lfrq","lfsy","slbmdm","slbmmc","slr","slrxm","slsj","hfr","hfsj","bjsj","fwzl","lxdh","bz"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select nd,yf,xh,xm,xb,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,lffs,lfrq,lfsy,slbmdm,slbmmc,slr,slrxm,slsj,hfr,hfsj,bjsj,fwzl,lxdh,bz from view_xslfglb";
		
		return getList(sql+whereSql+" and (bjsj='δ��' or bjsj='����ʱ' or fwzl='������')", value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * ����������ѯѧ��������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXslfglbOne(XsfwzdzxglForm model){
		String[] outputValue = {"nd","yf","xh","xm","xb","xz","xydm","xymc","zydm","zymc","bjdm","bjmc","nj","lffs","lfrq","lfsy","slbmdm","slbmmc","slr","slrxm","slsj","hfr","hfsj","bjsj","fwzl","lxdh","bz"};
		String sql = "select nd,yf,xh,xm,xb,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,lffs,lfrq,lfsy,slbmdm,slbmmc,slr,slrxm,slsj,hfr,hfsj,bjsj,fwzl,lxdh,bz from view_xslfglb where xh||lfrq=?";
		
		return getMap(sql, new String[]{model.getPkValue()}, outputValue);
	}
	
	/**
	 * ��ѯ�ط���Ϣ��ӡͳ�Ʊ�
	 * @param XsfwzdzxglForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectHfxx(XsfwzdzxglForm model){
		String nd = model.getNd();
		String yf = model.getYf();
		String[] outputValue = {"slbmmc","slzs","hfzs","js","bjs","wbl","my","jbmy","bmy","ld","lr"};
		String sql = "select slbmdm, slbmmc,(select count(*) from xslfglb b where b.slbmdm = a.slbmdm and nd = ?��and yf = ?) slzs," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and yf = ?��and hfr is not null) hfzs," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and yf = ?��and bjsj = '��ʱ') js," +
				"(select count(*)  from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and yf = ?��and bjsj = '����ʱ') bjs," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and yf = ?��and bjsj = 'δ��') wbl," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and yf = ?��and fwzl = '����') my," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and yf = ?��and fwzl = '��������') jbmy," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and yf = ?��and fwzl = '������') bmy," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and yf = ?��and lffs = '����') ld," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and yf = ?��and lffs = '����') lr" +
				" from xslfslbmdmb a";
		
		return getList(sql, new String[]{nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf}, outputValue);
	}
	
	/**
	 * ��ѯ��Ȼط���Ϣ��ӡͳ�Ʊ�
	 * @param XsfwzdzxglForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectNdHfxx(XsfwzdzxglForm model){
		String nd = model.getNd();
		String[] outputValue = {"slbmmc","slzs","hfzs","js","bjs","wbl","my","jbmy","bmy","ld","lr"};
		String sql = "select slbmdm, slbmmc,(select count(*) from xslfglb b where b.slbmdm = a.slbmdm and nd = ?) slzs," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and hfr is not null) hfzs," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and bjsj = '��ʱ') js," +
				"(select count(*)  from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and bjsj = '����ʱ') bjs," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and bjsj = 'δ��') wbl," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and fwzl = '����') my," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and fwzl = '��������') jbmy," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and fwzl = '������') bmy," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and lffs = '����') ld," +
				"(select count(*)��from xslfglb b��where b.slbmdm = a.slbmdm��and nd = ?��and lffs = '����') lr" +
				" from xslfslbmdmb a";
		
		return getList(sql, new String[]{nd,nd,nd,nd,nd,nd,nd,nd,nd,nd}, outputValue);
	}
	
	
	
	/**
	 * ��ѯ�طúϼ���Ϣ
	 * @param XsfwzdzxglForm model
	 * @return List<HashMap<String, String>>
	 * */
	public HashMap<String, String> selectHfhjxx(XsfwzdzxglForm model){
		String nd = model.getNd();
		String yf = model.getYf();
		String[] outputValue = {"slzs","hfzs","js","bjs","wbl","my","jbmy","bmy","ld","lr","ykfts"};
		String sql = "select (select count(*) from xslfglb b where nd = ?��and yf = ?) slzs," +
				"(select count(*)��from xslfglb b��where nd = ?��and yf = ?��and hfr is not null) hfzs," +
				"(select count(*)��from xslfglb b��where nd = ?��and yf = ?��and bjsj = '��ʱ') js," +
				"(select count(*)  from xslfglb b��where nd = ?��and yf = ?��and bjsj = '����ʱ') bjs," +
				"(select count(*)��from xslfglb b��where nd = ?��and yf = ?��and bjsj = 'δ��') wbl," +
				"(select count(*)��from xslfglb b��where nd = ?��and yf = ?��and fwzl = '����') my," +
				"(select count(*)��from xslfglb b��where nd = ?��and yf = ?��and fwzl = '��������') jbmy," +
				"(select count(*)��from xslfglb b��where nd = ?��and yf = ?��and fwzl = '������') bmy," +
				"(select count(*)��from xslfglb b��where nd = ?��and yf = ?��and lffs = '����') ld," +
				"(select count(*)��from xslfglb b��where nd = ?��and yf = ?��and lffs = '����') lr," +
				"(select max(ykfts) from xszdfwzxcqb where nd=? and yf=?)ykfts "+
				" from dual a";
		
		return getMap(sql, new String[]{nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf}, outputValue);
	}
	
	/**
	 * ��ѯ��Ȼطúϼ���Ϣ
	 * @param XsfwzdzxglForm model
	 * @return List<HashMap<String, String>>
	 * */
	public HashMap<String, String> selectNdHfhjxx(XsfwzdzxglForm model){
		String nd = model.getNd();
		String[] outputValue = {"slzs","hfzs","js","bjs","wbl","my","jbmy","bmy","ld","lr","ykfts"};
		String sql = "select (select count(*) from xslfglb b where nd = ?) slzs," +
				"(select count(*)��from xslfglb b��where nd = ?��and hfr is not null) hfzs," +
				"(select count(*)��from xslfglb b��where nd = ?��and bjsj = '��ʱ') js," +
				"(select count(*)  from xslfglb b��where nd = ?��and bjsj = '����ʱ') bjs," +
				"(select count(*)��from xslfglb b��where nd = ?��and bjsj = 'δ��') wbl," +
				"(select count(*)��from xslfglb b��where nd = ?��and fwzl = '����') my," +
				"(select count(*)��from xslfglb b��where nd = ?��and fwzl = '��������') jbmy," +
				"(select count(*)��from xslfglb b��where nd = ?��and fwzl = '������') bmy," +
				"(select count(*)��from xslfglb b��where nd = ?��and lffs = '����') ld," +
				"(select count(*)��from xslfglb b��where nd = ?��and lffs = '����') lr," +
				"(select max(ykfts) from xszdfwzxcqb where nd=? )ykfts "+
				" from dual a";
		
		return getMap(sql, new String[]{nd,nd,nd,nd,nd,nd,nd,nd,nd,nd,nd}, outputValue);
	}
	
	/**
	 * ��ȡ������������Ϣ
	 * @param String slbmdm
	 * @return HashMap<String, String>
	 * */
	public List<HashMap<String, String>> getBmslr(String slbmdm){
		String sql = "select distinct slryhm,slrxm from xslfbmslrb where slbmdm=?";
		return getList(sql, new String[]{slbmdm}, new String[]{"slryhm","slrxm"});
	}
	
	/**
	 * ���ѧ��������Ϣ�Ƿ����
	 * @param HashMap<String, String> map
	 * @return boolean
	 * */
	public boolean checkXslfxx(HashMap<String, String> map){
		return checkExists("xslfglb", "xh||lfrq", map.get("xh")+map.get("lfrq"));
	}	
	
	/**
	 * ��ѯ������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXszdfwzxcqb(XsfwzdzxglForm model){
		String[] outputValue = {"pk","nd","yf","slbmmc","ykfts","jscqts","xscqts","bz"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select  rownum r, a.* from (select nd||yf||slbmdm pk,nd,yf,slbmdm,slbmmc,ykfts,jscqts,xscqts,bz from view_xszdfwzxcqb a" + whereSql + ")a";
		
		Pages paganitionModel = model.getPages();		
		paganitionModel.setMaxRecord(Integer.parseInt(getOneRs("select count(*) num from view_xszdfwzxcqb" + whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num")));
		sql = "select * from (" + sql + ") where r>" + paganitionModel.getStart() + " and r<=" + (paganitionModel.getStart()+paganitionModel.getPageSize()); 
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * ��ѯ������Ϣ����
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXszdfwzxcqbForExp(XsfwzdzxglForm model){
		String[] outputValue = {"nd","yf","slbmdm","slbmmc","ykfts","jscqts","xscqts","bz"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select nd,yf,slbmdm,slbmmc,ykfts,jscqts,xscqts,bz from view_xszdfwzxcqb";
		
		return rsToVator(sql+whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * ��ѯ������Ϣ����
	 * @param XsfwzdzxglForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectXszdfwzxcqbForPrint(XsfwzdzxglForm model){
		String[] outputValue = {"slbmmc","ykfts","jscqts","xscqts","bz"};
		String sql = "select a.slbmmc,b.ykfts,b.jscqts,b.xscqts,b.bz from xslfslbmdmb a left join (select * from xszdfwzxcqb where nd=? and yf=?) b on a.slbmdm=b.slbmdm order by a.slbmdm ";
		
		return getList(sql, new String[]{model.getNd(),model.getYf()}, outputValue);
	}
	
	/**
	 * ����������ѯ������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXszdfwzxcqbOne(XsfwzdzxglForm model){
		String[] outputValue = {"nd","yf","slbmdm","slbmmc","ykfts","jscqts","xscqts","bz"};
		String sql = "select nd,yf,slbmdm,slbmmc,ykfts,jscqts,xscqts,bz from view_xszdfwzxcqb where nd||yf||slbmdm=?";
		
		return getMap(sql, new String[]{model.getPkValue()}, outputValue);
	}
	
	/**
	 * ��������Ϣ�Ƿ����
	 * @param HashMap<String, String> map
	 * @return boolean
	 * */
	public boolean checkCqxx(HashMap<String, String> map){
		return checkExists("xszdfwzxcqb", "nd||yf||slbmdm", map.get("nd")+map.get("yf")+map.get("slbmdm"));
	}
}
