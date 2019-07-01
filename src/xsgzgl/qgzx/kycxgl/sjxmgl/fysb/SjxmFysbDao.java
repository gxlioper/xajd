/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-30 ����11:39:31 
 */
package  xsgzgl.qgzx.kycxgl.sjxmgl.fysb;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ʵ������ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-11-30 ����11:39:31
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class SjxmFysbDao extends SuperDAOImpl<SjxmFysbForm> {
	
	@Override
	public List<HashMap<String, String>> getPageList(SjxmFysbForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	//�����б�
	@Override
	public List<HashMap<String, String>> getPageList(SjxmFysbForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjOfKycxByUser(user, "t", "xh");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.xmssdw,t2.xh,t2.xmmc,t6.mc xmsxmc,t3.xm,t4.bmmc ssdwmc,t2.kssj,t2.jssj,");
		sql.append("decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc ");
		sql.append(" from XG_QGZX_SJXMFYSBB t1 left join XG_QGZX_SJXMXX t2 on t1.xmid = t2.xmid");
		sql.append(" left join view_xsbfxx t3 on t2.XH= t3.xh left join zxbz_xxbmdm t4 on t2.xmssdw=t4.bmdm");
		sql.append(" left join XG_QGZX_XMSX_SJ t6 on t2.xmsxdm = t6.dm");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
		
	}
	
	public List<HashMap<String, String>> ffcx(SjxmFysbForm t, User user) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xb,t2.xymc,t2.zymc,t2.bjmc,t2.nj,t2.sjhm,t2.xydm,t2.zydm,t2.bjdm,t3.shzt,t4.xmfg from XG_QGZX_SJXMCYFFXXB t1");
		sql.append(" left join view_xsbfxx t2 on t1.XH= t2.xh left join XG_QGZX_SJXMFYSBB t3 on t1.sbid=t3.sbid left join XG_QGZX_SJXMCYXXB t4 on t1.xmid=t4.xmid where t1.xmid=? ");
		sql.append(") t where 1=1 and shzt='1' ");
		return getPageList(t, sql.toString(), new String[]{t.getXmid()});
		
	}
	
	public List<HashMap<String, String>> getPageListOfFyff(SjxmFysbForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjOfFycxByUser(user, "t", "xh");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xb,t2.xymc,t2.zymc,t2.bjmc,t2.nj,t2.sjhm,t2.xydm,t2.zydm,t2.bjdm,t5.shzt,t6.xmfg ");
		sql.append(",t3.xmmc,t3.xmsxdm,t3.xmssdw,t4.mc xmsxmc from XG_QGZX_SJXMCYFFXXB t1");
		sql.append(" left join view_xsbfxx t2 on t1.XH= t2.xh left join XG_QGZX_SJXMXX t3 on t1.xmid=t3.xmid");
		sql.append("  left join XG_QGZX_XMSX_SJ t4 on t3.xmsxdm = t4.dm left join XG_QGZX_SJXMFYSBB t5 on t1.sbid=t5.sbid");
		sql.append(" left join XG_QGZX_SJXMCYXXB t6 on t1.xmid=t6.xmid and t2.xh=t6.xh) t where 1=1 and shzt='1' ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	
	/**
	 * @throws Exception
	 * 
	 * @����:��ȡʵ����Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-30����04:38:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getSjxmFysb(SjxmFysbForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.mc xmsxmc,t3.xm,t3.sjhm lxfs,t4.bmmc ssdwmc");
		sql.append(" from XG_QGZX_SJXMFYSBB t1 left join XG_QGZX_XMSX_SJ t2 on t1.xmsxdm = t2.dm");
		sql.append(" left join view_xsjbxx t3 on t1.XH= t3.xh left join zxbz_xxbmdm t4 on t1.xmssdw=t4.bmdm");
		sql.append(" where t1.xmid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getXmid() });
	}


	/**
	 *�ж��Ƿ��Ѵ���
	 */
	public boolean isHaveSbjg(SjxmFysbForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_QGZX_SJXMFYSBB where xmmc=?");
		if(null!=model.getXmid()){
			sql.append(" and xmid<>'"+model.getXmid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getGwid()}, "num");
		return Integer.parseInt(num)>0;
	}

	public boolean delSjxmFysb(String xmid) throws Exception {
		String sql = "delete from XG_QGZX_SJXMFYSBB where xmid=?";
		return dao.runUpdate(sql, new String[] { xmid });
	}
	/**
	 * �ж��Ƿ��������¼
	 */
	public boolean isHaveSqJl(String values) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from( select xmdm from xg_sztz_xsxmsq where xmdm in("+values+")");
		sql.append(" union all select xmdm from xg_sztz_xs_sqjg where xmdm in("+values+"))");
		int result = dao.getOneRsint(sql.toString());
		return result > 0;
	}


	public List<HashMap<String,String>> getCyList(String xmdm,String ffny) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xmid,t1.xh,t1.xmfg,t1.xmnzt,t2.bjmc,t2.xm,t2.xb,t2.xymc,nvl(t2.lxdh,'��')lxdh,t3.gs,t3.cjje");
		sql.append(" from XG_QGZX_SJXMCYXXB t1 left join view_xsbfxx t2 on t1.xh=t2.xh ");
		sql.append(" left join (select * from XG_QGZX_SJXMCYFFXXB where ffyf = ?) t3 on t1.xmid=t3.xmid and t1.xh=t3.xh");
		sql.append("  where t1.xmid=? ");
		return dao.getListNotOut(sql.toString(), new String[] { ffny,xmdm });
	}
	
	public List<HashMap<String,String>> getTeaList(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xm,t3.bmmc xymc,nvl(t2.lxdh,'��')lxdh");
		sql.append(" from XG_QGZX_SJXMZDLSB t1 left join fdyxxb t2 on t1.zgh=t2.zgh left join zxbz_xxbmdm t3 on t2.bmdm=t3.bmdm ");
		sql.append("  where t1.xmid=?");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}
	public List<HashMap<String,String>> getSbxmList(User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.* from XG_QGZX_SJXMXX t1 where t1.xh=? and shzt='1' and sysdate between to_date(t1.kssj,'yyyy-mm-dd hh24:mi:ss') and  to_date(t1.jssj,'yyyy-mm-dd hh24:mi:ss')");
		return dao.getListNotOut(sql.toString(), new String[] { user.getUserName() });
	}
	
	public List<HashMap<String,String>> getBgztList(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.mc from XG_QGZX_YXZTBGB t1 left join XG_QGZX_XMYXZT t2 on t1.yxzt=t2.dm  order by t1.bgsj where t1.xmid=?");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm});
	}
	
	public List<HashMap<String,String>> getYxztList() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.* from XG_QGZX_XMYXZT t1");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	
	public List<HashMap<String,String>> getXmfyList(String xmid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.* from XG_QGZX_KYXMFYB t1  where t1.xmid=?");
		return dao.getListNotOut(sql.toString(), new String[] { xmid });
	}
	/**
	 * 
	 * @����:��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-10 ����02:24:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFfyfList(String xmid) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select ffyf nydm,ffyf nymc from (select (select dqnd from xtszb)||lpad(level,2,0)ffyf from dual connect by level<13 )where ffyf>(select nvl(max(ffyf),0) from XG_QGZX_SJXMCYFFXXB where xmid=?)");
		return dao.getListNotOut(sql.toString(), new String[]{xmid});
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(SjxmFysbForm.class);
		super.setKey("sbid");
		super.setTableName("XG_QGZX_SJXMFYSBB");

	}
	/**
	 * 
	 * @����:��ȡѧ����Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-11-30 ����10:27:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(SjxmFysbForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhs = new String[]{};
		StringBuilder sql = new StringBuilder("select * from(select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,a.sjhm,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsbfxx a where 1=1 ");
		if(xhs.length>0){
			sql.append("  and a.xh not in(");
			for (int i = 0; i < xhs.length; i++) {
				if(i!=0){
					sql.append(", ");
				}
				sql.append("'"+xhs[i]+"' ");
			}
			sql.append(")");
			}
		sql.append(" and a.xh not in(select xh from XG_QGZX_SJXMCYXXB where xmid='"+model.getXmid()+"') ");
		sql.append(")a where 1=1 ");
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getTeaList(SjxmFysbForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String[] zghs = new String[]{};
		sql.append("select rownum r,a.* from (select b.yhm zgh,b.xm,b.zdm,b.szbm bmdm,c.bmmc xymc,d.zmc,c.bmlb,nvl(e.lxdh,'��')lxdh from yhb b ");
		sql.append(" left join zxbz_xxbmdm c on b.szbm = c.bmdm left join (select distinct yhm,zmc from view_yhz_yhxxb) d on b.yhm = d.yhm left join fdyxxb e on b.yhm=e.zgh where c.bmlb = '1' ");
		if(zghs.length>0){
			sql.append("  and b.yhm not in(");
			for (int i = 0; i < zghs.length; i++) {
				if(i!=0){
					sql.append(", ");
				}
				sql.append("'"+zghs[i]+"' ");
			}
			sql.append(")");
			}
		sql.append(" and b.yhm not in (select zgh from XG_QGZX_SJXMZDLSB a where a.xmid='"+t.getXmid()+"') order by b.szbm desc)a where 1=1 ");
		sql.append(searchTj);
		sql.append("order by bmdm desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getBmList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select bmdm xydm,F_PINYIN(substr(bmmc,0,1)) xypy,F_PINYIN(substr(bmmc,0,1)) ||'-'||bmmc xymc ");
		sql.append("from zxbz_xxbmdm ");
		sql.append(") order by xypy ");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xydm", "xymc" });
	}
	/**
	 * 
	 * @����:��Ŀ�����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-30 ����06:14:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmsxList()throws Exception{
		String sql ="select * from XG_QGZX_XMSX_SJ";
		return dao.getListNotOut(sql, new String[]{});
	}
	/**
	 * 
	 * @����:��λ�����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-7 ����10:55:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getGwlbList()throws Exception{
		String sql ="select * from XG_QGZX_XMGWLX";
		return dao.getListNotOut(sql, new String[]{});
	}
	/**
	 * 
	 * @����:ɾ��ѧ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-2 ����03:43:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delXs(String xmid) throws Exception{
		String sql ="delete from XG_QGZX_SJXMCYXXB where xmid=?";
		return dao.runUpdate(sql, new String[]{xmid});
		
	}
	//ɾ��ָ����ʦ
	public boolean delJs(String xmid) throws Exception{
		String sql ="delete from XG_QGZX_SJXMZDLSB where xmid=?";
		return dao.runUpdate(sql, new String[]{xmid});
	}
	
	//ɾ����Ŀ�����б�
	public boolean delXmfy(String xmid) throws Exception{
		String sql ="delete from XG_QGZX_KYXMFYB where xmid=?";
		return dao.runUpdate(sql, new String[]{xmid});
	}
	/**
	 * 
	 * @����:�ϱ���Ϣ������Ϣɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-2 ����04:28:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delFyff(SjxmFysbForm model) throws Exception{
		String sql ="delete from XG_QGZX_SJXMCYFFXXB where xmid=? and ffyf=?";
		return dao.runUpdate(sql, new String[]{model.getXmid(),model.getSbyf()});
	}
	public boolean delFyff(String[] values) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_QGZX_SJXMCYFFXXB where sbid in(");
		for (int i = 0; i < values.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), values);
	}
	
	public boolean ffPlbc(List<String[]> gwParams) throws SQLException {
		String sql = "insert into XG_QGZX_SJXMCYFFXXB(sbid,xmid,xh,ffyf,gs,cjje) values(?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, gwParams);
		return dao.checkBatchResult(result);
	}
	public boolean jsPlbc(List<String[]> jsParams) throws SQLException {
		String sql = "insert into XG_QGZX_SJXMZDLSB(xmid,zgh,zc,yjfx) values(?,?,?,?)";
		int[] result = dao.runBatch(sql, jsParams);
		return dao.checkBatchResult(result);
	}
	
	public boolean xsPlbc(List<String[]> xsParams) throws SQLException {
		String sql = "insert into XG_QGZX_SJXMCYXXB(xmid,xh,xmfg,xmnzt) values(?,?,?,?)";
		int[] result = dao.runBatch(sql, xsParams);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @����:��ȡ�����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-3 ����08:45:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getShlcID(String kylb) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_QGZX_QZXM_CSSZ where xmlb=?");
		return dao.getOneRs(sql.toString(), new String[] {kylb}, "splc");
	}

}
