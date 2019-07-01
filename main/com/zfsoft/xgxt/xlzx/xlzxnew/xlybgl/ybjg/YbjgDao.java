package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybjg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class YbjgDao extends SuperDAOImpl<YbjgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(YbjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(YbjgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select * from (");
		sql.append(" select t.*,to_number(t.yf) || '��' yfmc,t1.xm,t2.bmmc xymc from xg_xljk_xlwygl_ybsbjgb t");
		sql.append(" left join yhb t1");
		sql.append(" on t.txr =t1.yhm");
		sql.append(" left join ZXBZ_XXBMDM t2");
		sql.append(" on t.xydm = t2.bmdm");
		sql.append(")t where 1=1 ");
		sql.append(searchTj);
		sql.append(" order by xn,yf,txrq desc");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(YbjgForm.class);
		this.setKey("jgid");
		this.setTableName("xg_xljk_xlwygl_ybsbjgb");
	}
	
	/**
	 * 
	 * @����: ��ȡѧԺ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-5 ����11:59:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXymc(String xydm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct xymc from view_njxyzybj where xydm = ?");
		return dao.getOneRs(sql.toString(),new String[]{xydm}, "xymc");
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
	public List<HashMap<String,String>> getYbWtryInfo(String ybjgid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.ybwtms,a.ybgycs,a.ybgyhjg,a.wtfsrq,b.xm,b.xb,b.bjmc,b.sjhm from  xg_xljk_xlwygl_wtxxb a");
		sql.append(" left join view_xsbfxx b");
		sql.append(" on a.xh = b.XH");
		sql.append(" where a.ybjgid = ?");
		return dao.getListNotOut(sql.toString(),new String[]{ybjgid});
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
	public List<HashMap<String,String>> getStuCx(YbjgForm t, User user,String xhs) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select xh,xm,xb,xydm,bjdm,xymc,bjmc,nj,zymc,zydm,sjhm from view_xsbfxx where xydm = ?");
		List<String> paraList = new ArrayList<String>();
		paraList.add(t.getXydm());
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
		sql.append(" "+searchTj);
		sql.append(" order by bjdm asc,xydm asc,nj asc");
		paraList.addAll(Arrays.asList(inputV));
		return getPageList(t, sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-5 ����02:55:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXyList(String xymc) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct xydm, xymc from view_njxyzybj where 1=1");
		List<String> paraList = new ArrayList<String>();
		if(StringUtils.isNotNull(xymc)){
			sql.append(" and xymc like ?");
			paraList.add("%"+xymc+"%");
		}
		sql.append(" order by xymc,xydm");
		return getPageList(new YbjgForm(), sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-5 ����04:22:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbsqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delWtb(String ybjgid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xljk_xlwygl_wtxxb where ybjgid = ?");
		return dao.runUpdate(sql.toString(),new String[]{ybjgid});
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
	public boolean checkIsNotExist(String xydm,String xn,String yf){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_xljk_xlwygl_ybsbjgb where xydm =? and xn = ? and yf=?");
		int rs = Integer.valueOf(dao.getOneRs(sql.toString(),new String[]{xydm,xn,yf},"cnt"));
		return rs > 0 ? false : true;
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
		sql.append(" insert into xg_xljk_xlwygl_wtxxb(lx,ybjgid,xh,ybwtms,ybgycs,ybgyhjg,wtfsrq) values(?,?,?,?,?,?,?)");
		return dao.runBatchBoolean(sql.toString(), paraList);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ���±����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-2 ����03:07:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delSbjg(String[] sbjgids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xljk_xlwygl_wtxxb where ybjgid in(");
		for (int i = 0; i < sbjgids.length; i++) {
			sql.append("?");
			if(i != sbjgids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(),sbjgids );
	}

	public boolean update(YbjgForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xljk_xlwygl_ybsbjgb set ztqk='',sfywt=? where jgid = ?");
		return dao.runUpdate(sql.toString(),new String[]{t.getSfywt(),t.getJgid()});
	}

	public List<HashMap<String, String>> getYbhzList(YbjgForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t3.xm txrxm, to_number(t1.yf) || '��' yfmc,t2.txr,t2.xq,t2.txrq,t2.shzt,t2.ztqk,t2.filepath,t2.sfywt,t2.sjly,t2.ybid,case when t2.shzt = '0' or t2.shzt is null then 'δ�ϱ�' when t2.sfywt = '��' and shzt != '0' then  '������' when t2.sfywt = '��' and t2.shzt !='0' and t2.shzt is not null  then '���ϱ�' end ybqk from (");
		sql.append(" select a.*,b.*,c.* from (select distinct xydm,xymc from view_xsbfxx) a,(select distinct xn from ( select xn from XG_XLJK_XLWYGL_YBSBB union all select xn from xg_xljk_xlwygl_ybsbjgb))b,(select yf from yfb) c) t1");
		sql.append(" left join (select '1' sjly,sbid ybid,xn, yf, xydm, txr, xq, txrq, shzt, ztqk, filepath, sfywt from XG_XLJK_XLWYGL_YBSBB union all select sjly,jgid ybid,xn, yf, xydm, txr,xq,txrq, '1' shzt,ztqk,filepath,sfywt from xg_xljk_xlwygl_ybsbjgb");
		sql.append(" where sjly = '0') t2 on t1.xydm=t2.xydm and t1.xn=t2.xn and t1.yf=t2.yf ");
		sql.append(" left join yhb t3");
		sql.append(" on t2.txr =t3.yhm");
		sql.append(")t where 1=1 ");
		sql.append(searchTj);
		sql.append(" order by xn,yf,xymc");
		return getPageList(t, sql.toString(), inputV);
	}

	public List<HashMap<String, String>> getAllHzList(YbjgForm t, User user) throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return this.getYbhzList(t, user);
	}

}
