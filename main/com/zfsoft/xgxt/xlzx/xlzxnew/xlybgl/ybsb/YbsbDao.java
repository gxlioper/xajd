package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsb;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class YbsbDao extends SuperDAOImpl<YbsbForm> {

	@Override
	public List<HashMap<String, String>> getPageList(YbsbForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(YbsbForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select * from (");
		sql.append(" select t.*,to_number(t.yf) || '��' yfmc,t1.xm,t2.bmmc xymc,decode(t.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����',t.shzt) shztmc from xg_xljk_xlwygl_ybsbb t");
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
		this.setClass(YbsbForm.class);
		this.setKey("sbid");
		this.setTableName("xg_xljk_xlwygl_ybsbb");
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
		sql.append(" where a.ybsqid = ?");
		return dao.getListNotOut(sql.toString(),new String[]{ybjgid});
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
	public boolean delWtb(String ybsqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xljk_xlwygl_wtxxb where ybsqid = ?");
		return dao.runUpdate(sql.toString(),new String[]{ybsqid});
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
		sql.append(" select count(1) cnt from xg_xljk_xlwygl_ybsbb where xydm =? and xn = ? and yf=?");
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
		sql.append(" insert into xg_xljk_xlwygl_wtxxb(lx,ybsqid,xh,ybwtms,ybgycs,ybgyhjg,wtfsrq) values(?,?,?,?,?,?,?)");
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
		sql.append(" delete from xg_xljk_xlwygl_wtxxb where ybsqid in(");
		for (int i = 0; i < sbjgids.length; i++) {
			sql.append("?");
			if(i != sbjgids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(),sbjgids );
	}

	public boolean update(YbsbForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_XLJK_XLWYGL_YBSBB set ztqk='',sfywt=?,shzt=? where sbid = ?");
		return dao.runUpdate(sql.toString(),new String[]{t.getSfywt(),t.getShzt(),t.getSbid()});
	}

}
