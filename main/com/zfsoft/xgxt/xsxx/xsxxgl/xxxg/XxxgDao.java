/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:11:11
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxxg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglModel;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ѧ����Ϣ�޸�
 * @���ߣ� ligl
 * @ʱ�䣺 2013-11-23 ����11:11:11
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XxxgDao extends SuperDAOImpl<XsxxglModel> {

	/**
	 * ��ͨ��ѯ����
	 */
	public List<HashMap<String, String>> getPageList(XsxxglModel model)
			throws Exception {
		return null;
	}

	/**
	 * �߼���ѯ����
	 */
	public List<HashMap<String, String>> getPageList(XsxxglModel model,
			User user) throws Exception {
		return null;
	}

	/**
	 * �߼���ѯ����,��˼�¼
	 */
	public List<HashMap<String, String>> getWclPageList(XsxxglModel model,
			User user) throws Exception {
		// ====================��������===================================

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String shgwzByUser = SearchService.getShgwzByUser(user, "a",
				"xydm", "bjdm");

		// query += searchTjByUser;
		// ====================�������� end================================
		// ====================SQLƴװ================================
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb.append("select b.*,c.*,a.xgsj,e.mc,e.gwz,row_number() over (partition by b.ywid order by b.shsj desc) as rn,  ");
		sb.append(" (e.mc || '[' || decode(b.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����',b.shzt) || ']') shztmc ");
		if("12309".equals(Base.xxdm)){
			sb.append(" ,f.fdy");
		}
		sb.append(" from xg_xsxx_xxxgsqb a,xg_xtwh_shztb b,view_xsjbxx c,xg_xtwh_spgwyh d,xg_xtwh_spgw e ");
		if("12309".equals(Base.xxdm)){
			sb.append(",(select wm_concat(t1.xm) fdy,t.bjdm from fdybjb t left join yhb t1");
			sb.append(" on t.zgh = t1.yhm group by t.bjdm)f");
		}
		sb.append(" where a.xh=c.xh and a.sqid=b.ywid and b.gwid=d.spgw   and e.id=b.gwid ");
		if("12309".equals(Base.xxdm)){
			sb.append(" and c.bjdm = f.bjdm");
		}
		String shzt = model.getShzt();

		if (shzt != null && shzt.equals("tg")) {
			sb.append(" and b.shzt='1' ");
		} else if (shzt != null && !shzt.equals("dsh")) {
			sb.append(" and (b.shzt!='0' and b.shzt!='4')");
		} else {
			sb.append(" and (b.shzt='0' or b.shzt='4')");
		}

		sb.append(" and d.spyh='");
		sb.append(user.getUserName());
		sb.append("' ) a where rn=1 ");
		sb.append(searchTjByUser);
		sb.append(searchTj);
		sb.append(shgwzByUser);
		return getPageList(model, sb.toString(), inputV);
	}

	/**
	 * �߼���ѯ����,��˽��
	 */
	public List<HashMap<String, String>> getXgjgPageList(XsxxglModel model,
			User user) throws Exception {
		// ====================��������===================================

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		// query += searchTjByUser;
		// ====================�������� end================================
		// ====================SQLƴװ================================
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb.append("select  c.*,a.sqid,a.xgsj,a.shjg,");
		sb.append("decode(a.shjg,'0','δ���','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����')  shjgmc,");
		sb.append("b.guid spclid,b.lcid,b.ywid,row_number() over(partition by b.ywid order by b.shsj desc) as rn ");
		sb.append(" from xg_xsxx_xxxgsqb a,xg_xtwh_shztb b,view_xsjbxx  c ");
		sb.append(" where a.xh=c.xh  and a.sqid=b.ywid");
		sb.append(") a where  rn=1 ");
		sb.append(searchTjByUser);
		sb.append(searchTj);
		return getPageList(model, sb.toString(), inputV);
	}

	/**
	 * 
	 * @����:�����޸������¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-6 ����10:32:13
	 * @�޸ļ�¼:
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean insertXgsq(XgsqModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xsxx_xxxgsqb(sqid,xh,xgsj,shjg) ");
		sql.append(" values(?,?,?,?) ");
		String[] inputValue = { model.getSqid(), model.getXh(),
				model.getXgsj(), model.getShjg() };
		return dao.runUpdate(sql.toString(), inputValue);
	}

	/**
	 * 
	 * @����:ɾ��ԭ��δ���������������¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-17 ����09:57:32
	 * @�޸ļ�¼:
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean deleteXgsq(XgsqModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xsxx_xxxgsqb ");
		sql.append(" where xh=? and shjg='");
		sql.append(Constants.SH_DSH);
		sql.append("' ");
		String[] inputValue = { model.getXh() };
		dao.runDelete(sql.toString(), inputValue);
		return true;
	}
	
	public boolean deleteShlc(XgsqModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xtwh_shztb ");
		sql.append(" where ywid in(select sqid from xg_xsxx_xxxgsqb where xh=? and shjg='");
		sql.append(Constants.SH_DSH);
		sql.append("') ");
		String[] inputValue = { model.getXh() };
		dao.runDelete(sql.toString(), inputValue);
		return true;
	}


	/**
	 * 
	 * @����:�޸�����״̬
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-9 ����09:42:04
	 * @�޸ļ�¼:
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateXgsqZt(XgsqModel model) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_xsxx_xxxgsqb ");
		sb.append(" set shjg=? ");
		sb.append(" where sqid=? ");
		String[] inputValue = { model.getShjg(), model.getSqid() };
		return dao.runUpdate(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @����:�޸�����״̬
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-9 ����09:42:04
	 * @�޸ļ�¼:
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateXgsqZtNotCommit(XgsqModel model) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_xsxx_xxxgsqb ");
		sb.append(" set shjg=? ");
		sb.append(" where sqid=? ");
		String[] inputValue = { model.getShjg(), model.getSqid() };
		return dao.runUpdateNotCommit(sb.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:ͨ������id���õ��޸��ֶμ��޸ĺ�ֵ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-9 ����01:45:09
	 * @�޸ļ�¼:
	 * @param sqid
	 * @return
	 * @throws
	 */
	public List<HashMap<String, String>> getXgzdList(String sqid)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select sqid,zd,zdz,xgqz from xg_xsxx_xgzdb ");
		sb.append(" where sqid=? ");
		return dao.getListNotOut(sb.toString(), new String[] { sqid });
	}

	/**
	 * 
	 * @����:����ѧ�ţ���ȡ���µ�һ������еļ�¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-16 ����03:57:35
	 * @�޸ļ�¼:
	 * @param xh
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getDataByXh(String xh, String shjg)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb.append("select * from xg_xsxx_xxxgsqb  ");
		sb.append("  where  xh=? and shjg=? ");
		sb.append("  order by xgsj desc  ");
		sb.append(")  where rownum=1 ");
		String[] input = { xh, shjg };
		return dao.getMapNotOut(sb.toString(), input);
	}
	/**
	 * 
	 * @����:��ȡ�����¼
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-11-17 ����11:39:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param shjg
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getShxxByXh(String xh)
		throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (select a.*,b.shsj,b.shyj from (select * from xg_xsxx_xxxgsqb");
		sb.append(" where xh=? ) a");
		sb.append(" join xg_xtwh_shztb b on a.sqid=b.ywid and b.shsj is not null order by xgsj desc,shsj desc)  ");
		sb.append(" where rownum =1");
		return dao.getMapNotOut(sb.toString(), new String[]{xh});
}
	public HashMap<String, String> getShztByXh(String xh)
	throws Exception {
	StringBuilder sb = new StringBuilder();
	sb.append("select * from (select a.*,b.shsj,b.shyj,b.shzt from (select * from xg_xsxx_xxxgsqb");
	sb.append(" where xh=? and shjg = '5') a");
	sb.append(" join xg_xtwh_shztb b on a.sqid=b.ywid and b.shsj is not null order by xgsj desc,shsj desc)  ");
	sb.append(" where rownum =1");
	return dao.getMapNotOut(sb.toString(), new String[]{xh});
}
	public HashMap<String, String> getDshByXh(String xh) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb.append("select * from xg_xsxx_xxxgsqb  ");
		sb.append("  where  xh=? and (shjg='0' or shjg='3') ");
		sb.append("  order by xgsj desc  ");
		sb.append(")  where rownum=1 ");
		String[] input = { xh };
		return dao.getMapNotOut(sb.toString(), input);
	}

	/**
	 * 
	 * @����:�����޸��ֶμ�ֵ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-9 ����02:03:35
	 * @�޸ļ�¼:
	 * @param list
	 * @param sqid
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean insertXgzd(List<XgzdModel> list, String sqid)
			throws Exception {
		boolean result = false;
		List<String[]> paramList = new ArrayList<String[]>();
		StringBuffer sb = new StringBuffer();
		sb.append("insert into xg_xsxx_xgzdb(sqid,zd,zdz,xgqz) ");
		sb.append(" values(?,?,?,?)");
		for (XgzdModel xgzdModel : list) {
			String[] param = { sqid, xgzdModel.getZd(), xgzdModel.getZdz(),
					xgzdModel.getXgqz() };
			paramList.add(param);
		}
		result = dao.runBatchBoolean(sb.toString(), paramList);
		return result;
	}
	
	/**
	 * 
	 * @����:ͨ������id ��ȡѧ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-24 ����09:59:20
	 * @�޸ļ�¼: 
	 * @param sqid
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getXhBySqid(String sqid) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xh ");
		sb.append(" from  xg_xsxx_xxxgsqb ");
		sb.append(" where sqid=?");
		String[] inputValue = {sqid};
		String[] outputValue = {"xh"};
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String xh = null;
		if(oneRs != null && oneRs.length > 0){
			xh = oneRs[0];
		}
		return xh;
	}

	protected void setTableInfo() {
		super.setTableName("");
		super.setKey("");
	}
	
	//�����״̬Ϊ�˻�ʱ������ݸ�����ύʱ��Ҫɾ��xgzdb�е�����
	public boolean delXgzdb(String sqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xsxx_xgzdb where sqid = ?");
		return dao.runUpdate(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 * @����:������ҽҩ���˻񽱸����ϴ���ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-26 ����02:55:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrfjscCx(XsxxglModel model,
			User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(" select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.nj,");
		sql.append(" t1.zymc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.bjdm,");
		sql.append(" t.bjdw bjdwmc");
		sql.append(" from xg_xsxx_new_grhjqk t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.xh");
		//sql.append(" left join zxbz_xxbmdm t2");
		//sql.append(" on t.bjdw = t2.bmdm ");
		sql.append(" order by t.hjsj desc,t1.bjmc,t1.xh ");
		sql.append(" ) t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����: ��ȡѧ������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-26 ����04:57:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXshjXx(String hjid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,t.bjdw bjdwmc from xg_xsxx_new_grhjqk t ");
		//sql.append(" left join zxbz_xxbmdm t2");
		//sql.append(" on t.bjdw = t2.bmdm ");
	    sql.append(" where t.hjid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{hjid});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @������������ҽҩ�ϴ�����������gid
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-26 ����05:58:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param hjid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateGrhjxxGid(String hjid,String gid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xsxx_new_grhjqk set gid = ? where hjid = ?");
		return dao.runUpdate(sql.toString(),  new String[]{gid,hjid});
	}
}
