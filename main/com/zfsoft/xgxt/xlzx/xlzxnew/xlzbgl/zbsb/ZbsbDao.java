package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsb;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsh.ZbshForm;

public class ZbsbDao extends SuperDAOImpl<ZbsbForm> {
	public static final String YSH = "Y";
	
	public static final String DSH = "D";
	@Override
	public List<HashMap<String, String>> getPageList(ZbsbForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	
	@Override
	public List<HashMap<String, String>> getPageList(ZbsbForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		sql.append(" select *");
		sql.append(" from (select '0' sblxx,");
		sql.append(" t1.*,");
		sql.append(" a.xqmc,");
		sql.append(" t2.sbsqid,");
		sql.append(" t2.xh,");
		sql.append(" t2.sbsj,");
		sql.append(" t2.sbzbid,");
		sql.append(" t2.ztqk,");
		sql.append(" t2.xlxsxxqk,");
		sql.append(" t2.bz,");
		sql.append(" t2.splcid,");
		sql.append(" t2.shzt,");
		sql.append(" decode(t2.shzt,'0','δ�ύ', '1','ͨ��','2','��ͨ��','3',");
		sql.append(" '���˻�','5','�����','δ�ϱ�') shztmc");
		sql.append(" from (select b.* from XG_XLJK_XLWYGL_new_ZBRCXXB b");
		sql.append(" where b.xn = ? and b.xq = ?)t1");
		sql.append(" left join (select a.* from XG_XLJK_XLWYGL_new_XSSBSQB a");
		sql.append(" where bjdm = (select bjdm from xsxxb where xh = ?)) t2 on t1.zbid = t2.sbzbid");
		sql.append(" left join xqdzb a on t1.xq = a.xqdm) order by sbsj desc");
		sql.append(" ");
		return getPageList(t, sql.toString(),new String[]{t.getXn(),t.getXq(),user.getUserName()});
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(ZbsbForm.class);
		this.setKey("sbsqid");
		this.setTableName("XG_XLJK_XLWYGL_new_XSSBSQB");
	}
	
	public HashMap<String,String> getXnXqmc(String zbid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.xqmc from XG_XLJK_XLWYGL_ZBRCXXB a left join xqdzb b on a.xq = b.xqdm where zbid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{zbid});
	}
	
	/**
	 * 
	 * @����: ѧ����Ȩ��֤
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-28 ����01:45:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String xssqCheck(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select decode(count(1), '1', 'Y', 'N') bjxlwy");
		sql.append(" from XG_XLJK_XLWYGL_NEW_XSSQXXB a");
		sql.append("  where a.xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "bjxlwy");
	}
	
	/**
	 * 
	 * @����: ��ȡѧ����Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����03:32:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getBjxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.bjmc, a.BJDM, replace(wm_concat(c.xm), ';', ',') xm");
		sql.append(" from view_xsbfxx a");
		sql.append(" left join bzrbbb b");
		sql.append(" on a.BJDM = b.bjdm");
		sql.append(" left join yhb c");
		sql.append(" on b.zgh = c.yhm");
		sql.append(" where a.XH = ?");
		sql.append(" group by a.BJMC,a.BJDM");
		return dao.getMapNotOut(sql.toString(),new String[]{xh});
	}
	
	/**
	 * 
	 * @����: ��ȡ�ܱ�������Ա��Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����04:26:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbsqid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZbWtryInfo(String zbsqid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.zbwtms,b.xm,b.xb from  xg_xljk_xlwygl_wtxxb a");
		sql.append(" left join view_xsbfxx b");
		sql.append(" on a.xh = b.XH");
		sql.append(" where a.zbsqid = ?");
		return dao.getListNotOut(sql.toString(),new String[]{zbsqid});
	}
	
	
	/**
	 * @throws SQLException 
	 * 
	 * @����: ���������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����05:34:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbsb
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveDataIntoWtb(List<String[]> paraList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xljk_xlwygl_wtxxb(lx,zbsqid,xh,zbwtms) values(?,?,?,?)");
		return dao.runBatchBoolean(sql.toString(), paraList);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����05:39:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbsqid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delWtb(String zbsqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xljk_xlwygl_wtxxb where zbsqid = ?");
		return dao.runUpdateNotCommit(sql.toString(),new String[]{zbsqid});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ȡѧ����ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����01:45:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getStuCx(ZbsbForm t, User user,String xhs) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,xm,xb,nj,xymc,bjmc,zymc from view_xsbfxx where bjdm = ?");
		List<String> paraList = new ArrayList<String>();
		paraList.add(t.getBjdm());
		if(StringUtils.isNotNull(xhs)){
			String[] xhArray = xhs.split(",");
			sql.append(" and xh not in(");
			for (int i = 0; i < xhArray.length; i++) {
				sql.append("?");
				paraList.add(xhArray[i]);
				if(i != xhArray.length-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		if(StringUtils.isNotNull(t.getXm())){
			sql.append(" and (xh like ? or xm like ?) ");
			paraList.add("%"+t.getXm()+"%");
			paraList.add("%"+t.getXm()+"%");
		}
		return getPageList(t, sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: �����ְ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-1 ����10:32:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkRzrq(String xh){
		String nowDate = GetTime.getTimeByFormat("yyyy-mm-dd");
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt  from XG_XLJK_XLWYGL_new_XSSQXXB t where t.rzksrq <= ? and (t.rzjsrq >= ? or t.rzjsrq is null) and xh = ?");
		int rs = Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{nowDate,nowDate,xh}, "cnt"));
		return rs > 0 ? true :false;
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ�����д�ܱ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-1 ����11:33:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @param sbzbid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExist(String bjdm,String sbzbid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from XG_XLJK_XLWYGL_new_XSSBSQB where bjdm =? and sbzbid = ?");
		int rs = Integer.valueOf(dao.getOneRs(sql.toString(),new String[]{bjdm,sbzbid},"cnt"));
		return rs > 0 ? false : true;
	}
}
