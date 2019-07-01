package xgxt.xtwh.common.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

public class XtlcglDAO extends DAO{
	/**
	 * ��ȡ��˹��ܲ˵���Ӧ�ĸ�λid��Ϣ
	 * */
	public String[] getGnshgwxx(String cdid){
		StringBuilder sql = new StringBuilder();
//		sql.append("select  max(ltrim(sys_connect_by_path(xtgwid, ','), ',')) xtgwid from (");
		sql.append("select xtgwid, ");
		sql.append("  row_number() over(partition by 1 order by xtgwid) pno,");
		sql.append("row_number() over(partition by 1 order by xtgwid) - 1 sno from (");
		sql.append("select a.* from xg_xtwh_shlcjbgwxxb a where exists(");
		sql.append("			select 1 from (");
		sql.append("				select guid jbid from xg_xtwh_shlcjbxxb a where exists(");
		sql.append("						 select 1 from xg_xtwh_shlcdyxxb b where a.lcid=b.lcid and b.cdid=?");
		sql.append("				)");
		sql.append("			) b where a.jbid=b.jbid ");
		sql.append("	)");
		sql.append("	)a ");
		sql.append("order by xtgwid");
//		sql.append(")connect by prior sno = pno ");
		
//		String xtgwid = getOneRs(sql.toString(), new String[]{cdid}, "xtgwid");
//		xtgwid = StringUtils.isNull(xtgwid) ? "" : xtgwid;
		
		List<HashMap<String, String>> rs = getList(sql.toString(), new String[]{cdid} , new String[]{"xtgwid"});
		int num = rs == null || rs.size()<0 ? 0 : rs.size();
		String[] gwArr = new String[num];
		
		for(int i=0; i<num ; i++){
			gwArr[i] = rs.get(i).get("xtgwid");
		}
		
		return gwArr;
	}
	
	/**
	 * ��ȡ��˹��ܲ˵���Ӧ�ĸ�λ������Ϣ
	 * */
	public String[] getGnshgwmcxx(String cdid){
		StringBuilder sql = new StringBuilder();
		HashMap<String, String> lcxx = getGnlcxx(cdid);
		//�����ݼ�¼��Ϊ������ʱ���Ĳ�ѯ���һ����¼û�в�ѯ�������Դ˴���Ϊѭ���õ�һ�е�ֵ
//		sql.append("select  max(ltrim(sys_connect_by_path(xtgwmc, ','), ',')) xtgwmc from (");
		sql.append("select xtgwid,(select xtgwmc from xg_xtwh_xtgwxxb b where b.guid=a.xtgwid)||'���' xtgwmc, ");
		sql.append("  row_number() over(partition by 1 order by xtgwid) pno,");
		sql.append("row_number() over(partition by 1 order by xtgwid) - 1 sno ");
		sql.append("from (select a.* from xg_xtwh_shlcjbgwxxb a where exists(");
		sql.append("			select 1 from (");
		sql.append("				select guid jbid from xg_xtwh_shlcjbxxb a where exists(");
		sql.append("						 select 1 from xg_xtwh_shlcdyxxb b where a.lcid=b.lcid and b.cdid=? and b.lcid=?");
		sql.append("				)");
		sql.append("			) b where a.jbid=b.jbid ");
		sql.append("	)");
		sql.append("	)a ");
		sql.append("order by xtgwid");
//		sql.append(")connect by prior sno = pno ");
		
//		String xtgwid = getOneRs(sql.toString(), new String[]{cdid,lcxx.get("lcid")}, "xtgwmc");
//		xtgwid = StringUtils.isNull(xtgwid) ? "" : xtgwid;
		List<HashMap<String, String>> rs = getList(sql.toString(), new String[]{cdid,lcxx.get("lcid")} , new String[]{"xtgwmc"});
		int num = rs == null || rs.size()<0 ? 0 : rs.size();
		String[] gwArr = new String[num];
		
		for(int i=0; i<num ; i++){
			gwArr[i] = rs.get(i).get("xtgwmc");
		}
		
		return gwArr;
	}
	
	/**
	 * ��ѯ�û��ĸ�λ��Ϣ
	 * */
	public HashMap<String, String> getYhlcgwxx(String xtgwid,String cdid){
		StringBuilder sql = new StringBuilder();
		HashMap<String, String> lcMap = getGnlcxx(cdid);//��ȡ�������������Ϣ
		sql.append("select yhm,xtgwid,jbid,(select jbmc from xg_xtwh_shlcjbxxb b where a.jbid=b.guid)jbmc,");
		sql.append("(select jb from xg_xtwh_shlcjbxxb b where a.jbid=b.guid)jb,xtgwmc from(");
		sql.append("select yhm,xtgwid,");
		sql.append("(select xtgwmc from xg_xtwh_xtgwxxb b where a.xtgwid = b.guid) xtgwmc,");
		sql.append("(select jbid from xg_xtwh_shlcjbgwxxb c where a.xtgwid = c.xtgwid ");
		sql.append("and exists(select 1 from xg_xtwh_shlcjbxxb d where c.jbid=d.guid and lcid=?))jbid ");
		sql.append("from xg_xtwh_yhgwxxb a");
		sql.append(") a ");
		sql.append(" where xtgwid=?");
		
		String lcid = lcMap.get("lcid");
		String[] output = new String[]{"yhm","xtgwid","xtgwmc","jbid","jbmc","jb"};
		String[] input = new String[]{lcid,xtgwid};
		
		return getMap(sql.toString(), input, output);
	}

	/**
	 * ��ȡ��˹���������Ϣ
	 * */
	public HashMap<String, String> getGnlcxx(String cdid){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.lcid,b.lcmc,b.yxj,b.tjbcyfs from xg_xtwh_shlcdyxxb a  left join xg_xtwh_shlckxxb b");
		sql.append(" on a.lcid=b.guid where cdid=?");
		
		return getMap(sql.toString(), new String[]{cdid}, new String[]{"lcid", "lcmc", "yxj", "tjbcyfs"});
	}
	
	/**
	 * ��ѯĳһ���ܵ�ǰ�û����ڵĸ�λ��Ϣ
	 * */
	public List<HashMap<String, String>> selectYhgwList(String cdid, String yhm){
		StringBuilder sql = new StringBuilder(); 
		sql.append("select a.*,(select xtgwmc from xg_xtwh_xtgwxxb b where a.xtgwid=b.guid)xtgwmc  from (");
		sql.append("select a.*, b.xtgwid from xg_xtwh_shlcjbxxb a left join xg_xtwh_shlcjbgwxxb b on a.guid = b.jbid");
		sql.append(") a where lcid = (select lcid from xg_xtwh_shlcdyxxb where cdid = ?) and exists(");
		sql.append("select 1 from xg_xtwh_yhgwxxb b where a.xtgwid = b.xtgwid and b.yhm = ?) order by jb");
		
		return getList(sql.toString(), new String[]{cdid, yhm}, new String[]{"lcid", "xtgwid", "xtgwmc"});
	}
	
	/**
	 * ��ѯ��λ�����������Ϣ
	 * */
	public HashMap<String, String> selectGwylcxgxx(String lcid, String gwid){
		String sql = "select a.* from (select a.*, b.xtgwid from xg_xtwh_shlcjbxxb a left join xg_xtwh_shlcjbgwxxb b " 
					+ "on a.guid = b.jbid) a where lcid = ? and xtgwid=? ";
		return getMap(sql, new String[]{lcid,gwid}, new String[]{"lcid","jbmc","jb","xtgwid"});
	}
		
	
	/**
	 * �ж��Ƿ����ռ���
	 * */
	public boolean lastLevel(String lcid, String xtgwid){
		String sql = "select max(jb)lczgjb from xg_xtwh_shlcjbxxb where lcid=?";
		//������߼���
		String lczgjb = getOneRs(sql, new String[]{lcid}, "lczgjb");
		sql = "select jb from xg_xtwh_shlcjbxxb a left join xg_xtwh_shlcjbgwxxb b on a.guid=b.jbid where a.lcid=? and b.xtgwid=?";
		//��λ����
		String gwjb = getOneRs(sql, new String[]{lcid,xtgwid}, "jb");
		
		return Integer.parseInt(StringUtils.isNull(lczgjb) ? "0" : lczgjb) == Integer.parseInt(StringUtils.isNull(gwjb) ? "0" : gwjb);
	}
	
	/**
	 * �ж��Ƿ��������
	 * */
	public boolean firstLevel(String lcid, String xtgwid){
		String sql = "select min(jb)lczgjb from xg_xtwh_shlcjbxxb where lcid=?";
		//������߼���
		String lczdjb = getOneRs(sql, new String[]{lcid}, "lczgjb");
		sql = "select jb from xg_xtwh_shlcjbxxb a left join xg_xtwh_shlcjbgwxxb b on a.guid=b.jbid where a.lcid=? and b.xtgwid=?";
		//��λ����
		String gwjb = getOneRs(sql, new String[]{lcid,xtgwid}, "jb");
		
		return Integer.parseInt(StringUtils.isNull(lczdjb) ? "0" : lczdjb) == Integer.parseInt(StringUtils.isNull(gwjb) ? "0" : gwjb);
	}
	
	/**
	 * �ж��Ƿ����ո�λ���
	 * */
	public boolean lastStation(String lcid, String xtgwid, String shb){
		boolean result = false;
		//���һ�������
		boolean lastLevel = lastLevel(lcid, xtgwid);
		if(lastLevel){
			//ͬ���������û��������
			String sql = "select max(jb)lczgjb from xg_xtwh_shlcjbxxb where lcid=?";
			//������߼���
			String lczgjb = getOneRs(sql, new String[]{lcid}, "lczgjb");
			sql = "select count(*)num from " + shb + " a where (a.shzt='ͨ��' or a.shzt='��ͨ��') and exists(select 1 from " 
				  + "(select * from xg_xtwh_shlcjbxxb a left join xg_xtwh_shlcjbgwxxb b on a.guid=b.jbid where a.lcid=? " 
				  + "and b.xtgwid<>? and a.jb=?) b where a.xtgwid=b.xtgwid)";
			String num = getOneRs(sql, new String[]{lcid, xtgwid, lczgjb}, "num");
			result = Integer.parseInt(StringUtils.isNull(num) ? "0" : num) == 0;
		}
		return result;
	}
	
	/**
	 * ��ȡ��������еĸ�λ
	 * */
	public List<HashMap<String, String>> getShlcgw(String shlcid){
		String sql = "select a.*,b.xtgwid,(select xtgwmc from xg_xtwh_xtgwxxb c where b.xtgwid=c.guid)xtgwmc " 
					+ "from xg_xtwh_shlcjbxxb a left join xg_xtwh_shlcjbgwxxb b on a.guid=b.jbid where a.lcid=? ";
		return getList(sql, new String[]{shlcid}, new String[]{"xtgwid", "xtgwmc"});
	}
}

