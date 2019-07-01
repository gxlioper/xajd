/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-30 ����11:39:31 
 */
package  xsgzgl.qgzx.kycxgl.kyxmgl;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ŀ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-11-30 ����11:39:31
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KyxmglDao extends SuperDAOImpl<KyxmglForm> {
	
	@Override
	public List<HashMap<String, String>> getPageList(KyxmglForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	//�����б�
	@Override
	public List<HashMap<String, String>> getPageList(KyxmglForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjOfKycxByUser(user, "t", "xh");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.mc xmsxmc,t3.xm,t4.bmmc ssdwmc,t5.mc yxztmc,");
		sql.append("decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc ");
		sql.append(" from XG_QGZX_KYXMXX t1 left join XG_QGZX_XMSX t2 on t1.xmsxdm = t2.dm");
		sql.append(" left join view_xsjbxx t3 on t1.XH= t3.xh left join zxbz_xxbmdm t4 on t1.xmssdw=t4.bmdm");
		sql.append(" left join XG_QGZX_XMYXZT t5 on t1.yxzt=t5.dm");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getPageListOfSh(KyxmglForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.mc xmsxmc,t3.xm,t4.bmmc ssdwmc,t5.mc yxztmc,");
		sql.append("t6.guid shid,t6.shzt shztx,t6.gwid,t6.shr,t6.shyj,t9.mc || '[' ||");
		sql.append("decode(t6.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t9.gwz, ");
		sql.append(" row_number() over(partition by t1.xmid order by t6.shsj desc) rn ");
		sql.append(" from XG_QGZX_KYXMXX t1 left join XG_QGZX_XMSX t2 on t1.xmsxdm = t2.dm");
		sql.append(" left join view_xsjbxx t3 on t1.XH= t3.xh left join zxbz_xxbmdm t4 on t1.xmssdw=t4.bmdm");
		sql.append(" left join XG_QGZX_XMYXZT t5 on t1.yxzt=t5.dm");
		sql.append(" left join xg_xtwh_shztb t6 on t1.xmid = t6.ywid");
		sql.append(" left join xg_xtwh_spgwyh t7 on  t6.gwid = t7.spgw  left join xg_xtwh_spgw t9 on t6.gwid = t9.id where t7.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t6.shzt<>0 and  t6.shzt<>4)");
		} else {
			sql.append(" and (t6.shzt=0  or t6.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getKycxList(String xh) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select t1.*,t2.xmmc,t3.mc xmsxmc from XG_QGZX_SJXMCYXXB t1 left join XG_QGZX_SJXMXX t2 on t1.xmid=t2.xmid left join XG_QGZX_XMSX_SJ t3 on t2.xmsxdm = t3.dm");
		sql.append(" union all select t1.*,t2.xmmc,t3.mc xmsxmc from XG_QGZX_KYXMCYXXB t1 left join XG_QGZX_KYXMXX t2 ");
		sql.append(" on t1.xmid=t2.xmid left join XG_QGZX_XMSX t3 on t2.xmsxdm = t3.dm)");
		sql.append(" where xh=?");
	    return dao.getListNotOut(sql.toString(), new String[]{xh});
	}


	
	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ������Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-30����04:38:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getKyxmgl(KyxmglForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.mc xmsxmc,t3.xm,t3.sjhm lxfs,t4.bmmc ssdwmc,case when t1.lxdj='1' then '�ص���Ŀ' else 'һ����Ŀ' end lxdjmc");
		sql.append(" from XG_QGZX_KYXMXX t1 left join XG_QGZX_XMSX t2 on t1.xmsxdm = t2.dm");
		sql.append(" left join view_xsjbxx t3 on t1.XH= t3.xh left join zxbz_xxbmdm t4 on t1.xmssdw=t4.bmdm");
		sql.append(" where t1.xmid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getXmid() });
	}


	/**
	 *�ж���Ŀ�Ƿ��Ѵ���
	 */
	public boolean isHaveKyxm(KyxmglForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_QGZX_KYXMXX where xmmc=?");
		if(null!=model.getXmid()){
			sql.append(" and xmid<>'"+model.getXmid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXmmc()}, "num");
		return Integer.parseInt(num)>0;
	}


	/**
	 * 
	 * ɾ�����н��
	 */
	public boolean delKyxmgl(String xmid) throws Exception {
		String sql = "delete from XG_QGZX_KYXMXX where xmid=?";
		return dao.runUpdate(sql, new String[] { xmid });
	}
	

	public List<HashMap<String,String>> getCyList(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bjmc,t2.xm,t2.xb,t2.xymc,t2.sjhm lxdh,case when t1.sfsfs='0' then '��' else '��'  end sfsfsmc");
		sql.append(" from XG_QGZX_KYXMCYXXB t1 left join view_xsbfxx t2 on t1.xh=t2.xh ");
		sql.append("  where t1.xmid=?");
		
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}
	
	public List<HashMap<String,String>> getTeaList(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.lxdh,t2.xm,t3.bmmc xymc");
		sql.append(" from XG_QGZX_KYXMZDLSB t1 left join fdyxxb t2 on t1.zgh=t2.zgh left join zxbz_xxbmdm t3 on t2.bmdm=t3.bmdm ");
		sql.append("  where t1.xmid=?");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}
	public List<HashMap<String,String>> getYsxxList(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,(select nvl(sum(ysje),0) from XG_QGZX_KYXMJFYSB where xmid=?)sbjfhj from XG_QGZX_KYXMJFYSB t1  where t1.xmid=?");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm,xmdm });
	}
	
	public List<HashMap<String,String>> getBgztList(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.mc from XG_QGZX_YXZTBGB t1 left join XG_QGZX_XMYXZT t2 on t1.yxzt=t2.dm  where t1.xmid=? order by t1.bgsj");
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
	@Override
	public KyxmglForm getModel(KyxmglForm myForm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xm,t2.nj,xymc from XG_QGZX_KYXMXX t1 left join view_xsjbxx t2 on t1.xh=t2.xh  where t1.xmid=?");
		return getModel(sql.toString(), new String[]{myForm.getXmid()});
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(KyxmglForm.class);
		super.setKey("xmid");
		super.setTableName("XG_QGZX_KYXMXX");

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
	public List<HashMap<String, String>> getXsxxList(KyxmglForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhs = model.getXhs();
		StringBuilder sql = new StringBuilder("select * from(select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc, nvl(a.sjhm,'��')lxdh,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where 1=1 ");
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
		sql.append(")a where 1=1 ");
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	public List<HashMap<String, String>> showStudents(KyxmglForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,nj,sjhm,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsjbxx a where 1=1 ");
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getTeaList(KyxmglForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String[] zghs = t.getZghs();
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
		sql.append(" )a where 1=1 ");
		sql.append(searchTj);
		sql.append("order by bmdm desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getBmList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select bmdm xydm,rownum rn,F_PINYIN(substr(bmmc,0,1)) xypy,F_PINYIN(substr(bmmc,0,1)) ||'-'||bmmc xymc ");
		sql.append(" from zxbz_xxbmdm ");
		sql.append(")");
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
		String sql ="select * from XG_QGZX_XMSX";
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
		String sql ="delete from XG_QGZX_KYXMCYXXB where xmid=?";
		return dao.runUpdate(sql, new String[]{xmid});
		
	}
	//ɾ��ָ����ʦ
	public boolean delJs(String xmid) throws Exception{
		String sql ="delete from XG_QGZX_KYXMZDLSB where xmid=?";
		return dao.runUpdate(sql, new String[]{xmid});
	}
	
	//ɾ����Ŀ�����б�
	public boolean delXmfy(String xmid) throws Exception{
		String sql ="delete from XG_QGZX_KYXMFYB where xmid=?";
		return dao.runUpdate(sql, new String[]{xmid});
	}
	/**
	 * 
	 * @����:����Ԥ��ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-2 ����04:28:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delYs(String xmid) throws Exception{
		String sql ="delete from XG_QGZX_KYXMJFYSB where xmid=?";
		return dao.runUpdate(sql, new String[]{xmid});
	}
	public boolean ysPlbc(List<String[]> ysParams) throws SQLException {
		String sql = "insert into XG_QGZX_KYXMJFYSB(xmid,zclb,ysje,zyyt) values(?,?,?,?)";
		int[] result = dao.runBatch(sql, ysParams);
		return dao.checkBatchResult(result);
	}
	public boolean xsPlbc(List<String[]> xsParams) throws SQLException {
		String sql = "insert into XG_QGZX_KYXMCYXXB(xmid,xh,xmfg,sfsfs) values(?,?,?,?)";
		int[] result = dao.runBatch(sql, xsParams);
		return dao.checkBatchResult(result);
	}
	public boolean insertZtBg(KyxmglForm model) throws Exception {
		String sql = "insert into XG_QGZX_YXZTBGB(xmid,yxzt) values(?,?)";
		return  dao.runUpdate(sql, new String[]{model.getXmid(),model.getYxzt()});
	}
	public boolean jsPlbc(List<String[]> jsParams) throws SQLException {
		String sql = "insert into XG_QGZX_KYXMZDLSB(xmid,zgh,zc,yjfx) values(?,?,?,?)";
		int[] result = dao.runBatch(sql, jsParams);
		return dao.checkBatchResult(result);
	}
	
	public boolean fywhPlbc(List<String[]> fyParams) throws SQLException {
		String sql = "insert into XG_QGZX_KYXMFYB(xmid,fylb,fymc,fyje,bxsj,bz) values(?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, fyParams);
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
