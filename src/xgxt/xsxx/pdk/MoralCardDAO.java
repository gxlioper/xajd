package xgxt.xsxx.pdk;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.comm.XsxxCommForm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class MoralCardDAO extends DAO {
	
	
	/**
	 * ��ѯѧ��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStudents(XsxxCommForm model,String query,String[] input,String[] colList) throws Exception{
		//String zxxs = "and (sfyby = '��' or sfyby is null) and (sfzx = '��У' or sfzx is null)";//��У����������
		Pages pages = model.getPages();
		//����¼��
		pages.setMaxRecord(Integer.parseInt(getOneRs("select count(1) c from view_xsbfxx a where 1=1 "+query, input, "c")));
		
		//��ѯ�����
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,xm,nj,xymc,zymc,bjmc,xz from (select a.xh,a.xm,a.nj,a.xymc,a.zymc,a.bjmc,a.xz,rownum r from view_xsbfxx a where 1=1 ");
		sql.append(query);
		//sql.append(zxxs);
		sql.append(" and rownum <=");
		sql.append((pages.getStart() + pages.getPageSize()));
		sql.append(" ) where r > "); 
		sql.append(pages.getStart());
		
		return rsToVator(sql.toString(), input, colList);
	}
	
	
	
	/**
	 * ����ѧ�Ų�ѯ�����ȵ�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getDyddListByXh(String xh){
		
		String sql = "select * from xg_xsxx_dyddb where xh=? order by to_number(xssx)";
		
		return getListNotOut(sql, new String[]{xh});
	}
	
	
	
	/**
	 * ����ѧ��ɾ�������ȵ�
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean clearDyddByXh(String xh) throws Exception{
		
		String sql = "delete from xg_xsxx_dyddb where xh=?";
		
		return runUpdate(sql, new String[]{xh});
	}
	
	
	/**
	 * �����ȵڱ���
	 * @param xh
	 * @param xqmc
	 * @param pjjg
	 * @return
	 * @throws SQLException
	 */
	public boolean saveDydd(String xh,String[] xqmc,String[] pjjg,String[] xssx) throws SQLException{
		
		if (xqmc.length > 0){
			String[] sqlArr = new String[xqmc.length];
			for (int i = 0 ; i < xqmc.length; i++){
				
				if (StringUtils.isNotNull(xqmc[i])){
				StringBuilder sql = new StringBuilder();
				
				sql.append("insert into xg_xsxx_dyddb values ('")
				   .append(xh)
				   .append("','")
				   .append(xqmc[i])
				   .append("','")
				   .append(pjjg[i])
				   .append("','")
				   .append(xssx[i])
				   .append("')");
				
				sqlArr[i] = sql.toString();
				}
			}
			
			int[] result = runBatch(sqlArr);
			return checkBatch(result);
		} else {
			return true;
		}
	}
	
	
	
	/**
	 * ���ͼ�¼�б�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getJcjlListByXh(String xh){
		
		String sql = "select * from xg_xsxx_jcjlb where xh=?";
		
		return getListNotOut(sql, new String[]{xh});
	}
	
	
	/**
	 * ����ѧ��ɾ�����ͼ�¼
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean clearJcjlByXh(String xh) throws Exception{
		
		String sql = "delete from xg_xsxx_jcjlb where xh=?";
		
		return runUpdate(sql, new String[]{xh});
	}
	
	
	
	/**
	 * ���ͼ�¼����
	 * @param xh
	 * @param rq
	 * @param zy
	 * @param bz
	 * @return
	 * @throws SQLException
	 */
	public boolean saveJcjl(String xh,String[] rq,String[] zy,String[] bz) throws SQLException{

		if (rq.length > 0){
			String[] sqlArr = new String[rq.length];
			
			for (int i = 0 ; i < rq.length ; i++){
				StringBuilder sql = new StringBuilder();
				
				sql.append("insert into xg_xsxx_jcjlb(xh,rq,zy,bz) values ('")
				   .append(xh)
				   .append("','")
				   .append(rq[i])
				   .append("','")
				   .append(zy[i])
				   .append("','")
				   .append(bz[i])
				   .append("')");
				
				sqlArr[i] = sql.toString();
			}
			
			int[] result = runBatch(sqlArr);
			return checkBatch(result);
		} else {
			return true;
		}
	}
	
	
	/**
	 * ����ѧ�Ų�ѯ���ͨ���Ľ�ѧ���¼(�㽭��ҵְҵ�����ʽ)
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getPjpyList(String xh){
		
		//String sql = "select hdsj sqsj,xmmc jxjmc,bz jxjlbmc from xg_pjpy_pjlsxxb where xh=?";//old
		//��������new
		String sql ="select to_char(to_date(a.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') sqsj,a.xmmc,(select xqmc from xqdzb xqb where xqb.xqdm = a.xq) xqmc,a.xn,a.ylzd2 hjwh,b.xmlxmc from xg_pjpy_new_pjjgb a left join xg_pjpy_new_xmlx b on a.lxdm=b.xmlxdm where xh=?";
		return getListNotOut(sql, new String[]{xh});
	}
	
	
	/**
	 * ����ѧ�Ų�ѯ���ͨ����Υ�ͼ�¼
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getWjcfList(String xh){
		//old
		//String sql = "select a.cfsj,b.cflbmc,a.cfwh||' '||c.cfyymc bz from wjcfb a left join cflbdmb b on a.cflb = b.cflbdm left join cfyydmb c on a.cfyy=c.cfyydm where a.xxsh='ͨ��' and xh=?";
		//new
		String sql ="select to_char(to_date(cfsj,'yyyy-mm-dd'),'yyyy-mm-dd') cfsj,cfwh,cflbmc,cfyymc from xg_wjcf_wjcfb where xh=? ";
		return getList(sql, new String[]{xh}, new String[]{"cfsj","cfwh","cflbmc","cfyymc"});
	}
	
	
	/**
	 * ����ѧ�Ų�ѯ�����ƺż�¼
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getRychListByXh(String xh){
		String sql = "select a.rychhdsj,b.rychmc,a.xxyj||' '||b.rychmc bz from xsrychb a left join rychdmb b on b.rychdm=a.rychdm where a.xxsh='ͨ��' and a.xh=?";
		
		return getList(sql, new String[]{xh}, new String[]{"rychhdsj","rychmc","bz"});
	}
	/**
	 * ����ѧ�Ų�ѯ���Υ�ʹ��ּ�¼
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getJcWjcfByXh(String xh){
		//String sql = "select jcsj,jcjg,jcwh||' '||jcjg bz from wjcf_zjlg_lxckb where xxsh='ͨ��' and xh=?";
		String sql ="select to_char(to_date(jcsj,'yyyy-mm-dd'),'yyyy-mm-dd') jcsj,jcwh,cflbmc,jcyj from xg_wjcf_wjcfb where xh=? and jcsj is not null";
		
		return getList(sql, new String[]{xh}, new String[]{"jcsj","jcwh","cflbmc","jcyj"});
	}
	
	/**
	 * ����ѧ�Ų�ѯѧ��������¼
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getXszzByXh(String xh){
		String sql ="select to_char(to_date(a.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') sqsj,a.xmmc,(select lbmc from xg_xszz_new_zzxmlbb c where a.lbdm=c.lbdm) lbmc from xg_xszz_new_zzxmjgb a where xh=?";
		
		return getList(sql, new String[]{xh}, new String[]{"sqsj","xmmc","lbmc"});
	}
	
	
	/**
	 * ѧ��ѧ����Ϣ
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getXjxxList(String[] xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xh,xm,xb,xymc,zymc,bjmc,ksh,sfzh,csrq,zzmmmc,mzmc,sydsmc,zyfx from view_xsxxb where ");
		   
		for (int i = 0 ; i < xh.length ; i++){
			sql.append(" xh=? ");
			
			if (i != xh.length-1){
				sql.append(" or ");
			}
		}
		   
		return getListNotOut(sql.toString(), xh);
	}
	
	
	/**
	 * �����ȵڲ�ѯ
	 */
	public List<String[]> getDyddList(XsxxCommForm model,String query,String[] input){
		//��ѯ�����
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"xh","xm","nj","xymc","zymc","bjmc","xqmc","pjjg"};
		
		sql.append("select a.*,rownum r from(")
		   .append("select t.xh,v.xm,v.nj,v.xydm,v.xymc,v.zydm,")
		   .append("v.zymc,v.bjdm,v.bjmc,t.xqmc,t.pjjg,")
		   .append("t.xssx,c.count from xg_xsxx_dyddb t ")
		   .append("left join view_xsjbxx v on t.xh=v.xh ")
		   .append("left join (select xh,count(1) count from ")
		   .append("xg_xsxx_dyddb group by xh) c on t.xh = c.xh ")
		   .append("order by xh,xssx) a where 1=1 ")
		   .append(query);
		
		return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(), input,colList);
	}
	
}
