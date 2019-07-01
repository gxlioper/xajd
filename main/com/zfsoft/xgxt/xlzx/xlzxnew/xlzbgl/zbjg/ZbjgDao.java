package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsb.ZbsbForm;

public class ZbjgDao extends SuperDAOImpl<ZbjgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ZbjgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(ZbjgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t.*, t1.zbzc, t2.bjmc,t3.xqmc,t1.ZBKSRQ,t1.ZBJSRQ");
		sql.append(" from XG_XLJK_XLWYGL_new_XLSBJGB t");
		sql.append(" left join XG_XLJK_XLWYGL_new_ZBRCXXB t1");
		sql.append(" on t.sbzbid = t1.zbid");
		sql.append(" left join view_njxyzybj t2");
		sql.append("  on t.bjdm = t2.bjdm");
		sql.append(" left join xqdzb t3");
		sql.append(" on t.xq = t3.xqdm");
		sql.append(" where t.bjdm in(");
		sql.append(" select bjdm from fdybjb where zgh = '"+user.getUserName()+"'");
		sql.append(" )");
		sql.append(" )t where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(),inputV);
	}
	
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-2 ����10:36:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getTeaBjxx(String bjdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select replace(wm_concat(t1.xm), ';', ',') xm, t2.bjmc, t2.bjdm");
		sql.append(" from bzrbbb t");
		sql.append(" left join yhb t1");
		sql.append(" on t.zgh = t1.yhm");
		sql.append(" left join view_njxyzybj t2");
		sql.append(" on t.bjdm = t2.bjdm");
		sql.append(" where t.bjdm = ?");
		sql.append(" group by t2.bjdm, t2.bjmc");
		return dao.getMapNotOut(sql.toString(),new String[]{bjdm});
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
	public List<HashMap<String,String>> getZbWtryInfo(String zbjgid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.zbwtms,b.xm,b.xb from  xg_xljk_xlwygl_wtxxb a");
		sql.append(" left join view_xsbfxx b");
		sql.append(" on a.xh = b.XH");
		sql.append(" where a.zbjgid = ?");
		return dao.getListNotOut(sql.toString(),new String[]{zbjgid});
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
		sql.append(" insert into xg_xljk_xlwygl_wtxxb(lx,zbjgid,xh,zbwtms) values(?,?,?,?)");
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
		sql.append(" delete from xg_xljk_xlwygl_wtxxb where zbjgid = ?");
		return dao.runUpdateNotCommit(sql.toString(),new String[]{zbsqid});
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
		sql.append(" select count(1) cnt from XG_XLJK_XLWYGL_new_XLSBJGB where bjdm =? and sbzbid = ?");
		int rs = Integer.valueOf(dao.getOneRs(sql.toString(),new String[]{bjdm,sbzbid},"cnt"));
		return rs > 0 ? false : true;
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(ZbjgForm.class);
		this.setKey("sbjgid");
		this.setTableName("XG_XLJK_XLWYGL_new_XLSBJGB");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ���ϱ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-2 ����03:07:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delSbjg(String[] sbjgids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xljk_xlwygl_wtxxb where zbjgid in(");
		for (int i = 0; i < sbjgids.length; i++) {
			sql.append("?");
			if(i != sbjgids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(),sbjgids );
	}
	
	/**
	 *
	 * @����: ��ѯ������Ա���д�����Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-3 ����02:47:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjList(String zgh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.bjdm,t1.bjmc,t2.xm from fdybjb t");
		sql.append(" left join view_njxyzybj t1");
		sql.append(" on t.bjdm = t1.bjdm");
		sql.append(" left join (");
		sql.append(" select a.bjdm,replace(wm_concat(b.xm),';',',') xm from  bzrbbb a left join yhb b");
		sql.append(" on a.zgh = b.yhm  group by a.bjdm");
		sql.append(" ) t2 on t1.bjdm = t2.bjdm");
		sql.append("  where t.zgh = ? order by t.bjdm asc");
		return dao.getListNotOut(sql.toString(), new String[]{zgh});
	}
	
	/**
	 * 
	 * @����: �Ƿ񸨵�Ա
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-3 ����04:41:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fdy
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsFdy(String zgh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from fdybjb where zgh = ?");
		String rs = dao.getOneRs(sql.toString(), new String[]{zgh}, "cnt");
		return "0".equals(rs)?false:true;
	}
	
	/**
	 * 
	 * @����: ��ȡ�ܴ�List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����08:47:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param sblx
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcList(String xn, String xq) {
		String nowtime = GetTime.getTimeByFormat("yyyy-mm-dd");
		StringBuffer sql = new StringBuffer();
		sql.append(" select zbid,zbzc from XG_XLJK_XLWYGL_NEW_ZBRCXXB t ");
		sql.append(" where t.xn = ? and t.xq = ? and zbksrq <= ? and zbjsrq >= ?"); 
		
		return dao.getArrayList(sql.toString(), new String[]{xn,xq,nowtime,nowtime}, new String[]{"zbid","zbzc"});
	}
	
	/**
	 * 
	 * @����: ��ȡ�ܱ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����08:50:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param zbid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getQzrq(String zbid) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.zbksrq||' ~ '||t.zbjsrq as zbqzrq from XG_XLJK_XLWYGL_NEW_ZBRCXXB t ");
		sql.append(" where  t.zbid = ? "); 
		
		return dao.getArrayList(sql.toString(), new String[]{zbid}, new String[]{"zbqzrq"});
	}
	
}
