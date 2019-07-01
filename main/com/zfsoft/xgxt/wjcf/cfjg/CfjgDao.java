/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-5 ����11:40:55 
 */
package com.zfsoft.xgxt.wjcf.cfjg;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-8-5 ����11:40:55
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class CfjgDao extends SuperDAOImpl<CfjgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(CfjgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ���ֲ�ѯ
	 */
	@Override
	public List<HashMap<String, String>> getPageList(CfjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select rownum r , a.* from xg_view_wjcf_wjcfb a ");
		sql.append("where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	

	/**
	 * ��������
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public boolean saveCfsj(CfjgForm form) throws Exception {
		boolean flag = false;
		String sql = "insert into xg_wjcf_wjcfb(filepath,filepath2,filepath3,filepath4,xh,xn,xq,cflbmc,cfyymc,cflbdm,cfyydm,wjsj,sbb,sbsj," +
				"wjssjg,bz,cfwh,cfsj,cfdqsj,fjmc,cfyj,cflsh,nd) " +
				"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String[] input = new String[] { form.getFilepath(), form.getFilepath2(),form.getFilepath3(),form.getFilepath4(),form.getXh(), form.getXn(), form.getXq(), form.getCflbmc(),
				form.getCfyymc(), form.getCflbdm(), form.getCfyydm(), form.getWjsj(), form.getSbb(),
				form.getSbsj(), form.getWjssjg(), form.getBz(), form.getCfwh(), form.getCfsj(),form.getCfdqsj(),
				form.getFjmc(),form.getCfyj(),form.getCflsh(),form.getNd()};
		if("12389".equals(Base.xxdm)){
			sql = "insert into xg_wjcf_wjcfb(filepath,xh,xn,xq,cflbmc,cfyymc,cflbdm,cfyydm,wjsj,sbb,sbsj," +
					"wjssjg,bz,cfwh,cfsj,cfdqsj,fjmc,cfyj,cflsh,nd,sdlx) " +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			input = new String[] { form.getFilepath(), form.getXh(), form.getXn(), form.getXq(), form.getCflbmc(),
					form.getCfyymc(), form.getCflbdm(), form.getCfyydm(), form.getWjsj(), form.getSbb(),
					form.getSbsj(), form.getWjssjg(), form.getBz(), form.getCfwh(), form.getCfsj(),form.getCfdqsj(),
					form.getFjmc(),form.getCfyj(),form.getCflsh(),form.getNd(),form.getSdlx() };
		}
		DAO dao = DAO.getInstance();
		flag = dao.runUpdate(sql,input);

		return flag;
	}
	/**
	 * ��ȡ��ˮ��
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsh(CfjgForm model){
		StringBuffer sql= new StringBuffer();
		sql.append("select cflsh from xg_wjcf_wjcfb")
		.append(" where xn=? and xq=? and cflsh is not null order by cflsh desc");
		return dao.getListNotOut(sql.toString(), new String[]{model.getXn(),model.getXq()});
	}
	
	public String getLsh2(CfjgForm model){
		StringBuffer sql= new StringBuffer();
		sql.append(" select (case when length(cflsh)<2 then '0'|| cflsh else cflsh end)  cflsh from (");
		sql.append("select to_char((to_number(count(1))+1)) cflsh from xg_wjcf_wjcfb")
		.append(" where xn=? and xq=?  ");
		if(null!=model.getCfid()){
			sql.append(" and cfid!='"+model.getCfid()+"'");
		}
		sql.append(")");
		return dao.getOneRs(sql.toString(), new String[]{model.getXn(),model.getXq()},"cflsh");
	}

	/**
	 * 
	 * @����: ��ȡ�����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-22 ����05:02:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cflbdm
	 * @return
	 * @throws Exception
	 *             String ��������
	 */
	public String cflbmc(String cflbdm) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = " select cflbmc from xg_wjcf_cflbdmb where cflbdm=?";
		return dao.getOneRs(sql, new String[] { cflbdm }, "cflbmc");
	}

	/**
	 * 
	 * @����: ��ȡ��Ӧ����ԭ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-22 ����05:02:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfyydm
	 * @return
	 * @throws Exception
	 *             String ��������
	 */
	public String cfyymc(String cfyydm) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = " select cfyymc from xg_wjcf_cfyydmb where cfyydm=?";
		return dao.getOneRs(sql, new String[] { cfyydm }, "cfyymc");
	}

	/**
	 * �鿴������Ϣ
	 * 
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfsjwhCk(String cfid) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select a.filepath,a.filepath2,a.filepath3,a.filepath4,a.ssfilepath,a.CFID,a.nd,A.XH,a.XN,a.XQ,a.cfyj,a.cflsh,");
		if("12389".equals(Base.xxdm)){
			sql.append("a.sdlx,");
		}
		sql.append("(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,cflbdm,cflbmc,");
		sql.append(" a.ocflbmc, a.CFYYMC,a.CFSJ,a.cfdqsj,a.cfwh,a.wjsj,a.sbb,a.SBSJ,a.WJSSJG,a.bz,a.sfsc,a.sssj,a.sswh,");
		sql.append(" (case when a.ssjg='wcycf' then 'ά��ԭ����' when a.ssjg= 'ggcf' then '���Ĵ���' ");
		sql.append(" when a.ssjg='cxcf' then '��������' else a.ssjg end) ssjg,");
		sql.append(" a.cfggw,a.ssyj,a.jcwh,a.jcsj,a.jcyj,a.fjmc,a.fj,a.zzwh,a.zzsj,a.zzyj,a.xm,a.xb,a.nj,a.xydm,a.zydm,");
		sql.append(" a.bjdm,a.xymc,a.zymc,a.bjmc,a.zzmmmc,a.mzmc,a.sfzh,a.ssfjmc,a.ssfj,a.sqly,a.jgmc,v.csrq,");
		sql.append(" v.ssbh, (select a.sqly from xg_wjcf_wjcfjcsqb a where a.cfid = ?) jcly,");
		sql.append(" (select c.qxmc from dmk_qx c  where c.qxdm = substr(v.jg, 0, 2) || '0000') ||");
		sql.append(" (select d.qxmc from dmk_qx d where d.qxdm = substr(v.jg, 0, 4) || '00' and v.jg <> substr(v.jg, 0, 2) || '0000') ||");
		sql.append(" (select e.qxmc from dmk_qx e where e.qxdm = v.jg and v.jg <> substr(v.jg, 0, 2) || '0000' and v.jg <> substr(v.jg, 0, 4) || '00') jgmc,");
		sql.append(" (select count(*) sqjg from xg_wjcf_wjcfjcsqb where sqjg in ('shz') and cfid=?) jclc,");
		sql.append(" (select count(*) ssjg from xg_wjcf_wjcfssb where ssjg in ('shz') and cfid=?) sslc, ");
		sql.append(" (select xm from fdyxxb t where t.zgh=a.sbb) sbbxm ");
		sql.append("from xg_view_wjcf_wjcfb a left join view_xsbfxx v on a.xh = v.XH where cfid = ?");
		HashMap<String, String> result = dao.getMapNotOut(sql.toString(), new String[] { cfid, cfid, cfid, cfid });
		return result;
	}

	/**
	 * ɾ��������Ϣ
	 * 
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsjwhSc(List<String[]> cfid) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.checkBatchResult(dao.runBatch("delete from xg_wjcf_wjcfb where cfid = ?", cfid));
	}

	/**
	 * ɾ��������Ϣʱ����ɾ�������ϱ��������ϱ���ˣ�������������ˣ�����������˱�����
	 * 
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfSbSsJcSc(String[] cfid) throws Exception {
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String[] str = new String[8];
		if (null != cfid && cfid.length > 0) {
			str[0] = "delete from xg_wjcf_wjcfsbb where cfid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[0] += ",";
				}
				str[0] += "'" + cfid[i] + "'";
			}
			str[0] += " ) ";
			str[1] = "delete from xg_wjcf_wjcfjcshb where cfid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[1] += ",";
				}
				str[1] += "'" + cfid[i] + "'";
			}
			str[1] += " ) ";
			str[2] = "delete from xg_wjcf_wjcfjcsqb where cfid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[2] += ",";
				}
				str[2] += "'" + cfid[i] + "'";
			}
			str[2] += " ) ";
			str[3] = "delete from xg_wjcf_wjcfjcshb where cfid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[3] += ",";
				}
				str[3] += "'" + cfid[i] + "'";
			}
			str[3] += " ) ";
			str[4] = "delete from xg_wjcf_wjcfssb where cfid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[4] += ",";
				}
				str[4] += "'" + cfid[i] + "'";

			}
			str[4] += " ) ";
			str[5] = "delete from xg_wjcf_wjcfssshb where cfid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[5] += ",";
				}
				str[5] += "'" + cfid[i] + "'";
			}
			str[5] += " ) ";

			str[6] = "delete from xg_xtwh_wdgz where gnmklx='��������' and ywid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[6] += ",";
				}
				str[6] += "'" + cfid[i] + "'";
			}
			str[6] += " ) ";

			str[7] = "delete from xg_xtwh_wdgz where gnmklx='���ֽ��' and ywid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[7] += ",";
				}
				str[7] += "'" + cfid[i] + "'";
			}
			str[7] += " ) ";

		}

		flag = dao.saveArrDate(str);
		return flag;
	}

	/**
	 * �޸Ĵ�����Ϣ
	 * 
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsjwhXg(CfjgForm form) throws Exception {
		DAO dao = DAO.getInstance();

		String fjmc = StringUtils.isNotNull(form.getFjmc()) ? form.getFjmc().substring(form.getFjmc().lastIndexOf("\\") + 1, form.getFjmc().length()) : "";
		if("12389".equals(Base.xxdm)){
			if (StringUtils.isNotNull(fjmc)) {
				return dao.updateBlob("update xg_wjcf_wjcfb set cfdqsj = ?, fj = ?,xn = ?,xq = ?,cflbmc = ?,cfyymc = ?,wjsj = ?," + "sbb = ?,sbsj = ?,wjssjg = ?,bz = ?,cfwh = ?,cfsj = ?,fjmc = ?,cfyj =?,nd=?,sdlx=? where cfid=?",
						new String[] {form.getCfdqsj(), form.getXn(), form.getXq(), form.getCflbdm(), form.getCfyydm(), form.getWjsj(), form.getSbb(), form.getSbsj(), form.getWjssjg(), form.getBz(), form.getCfwh(),
								form.getCfsj(), fjmc,form.getCfyj(),form.getNd(),form.getSdlx(), form.getCfid() }, form.getFj().getInputStream().available(), form.getFj().getInputStream());
			} else {
				return dao.runUpdate("update xg_wjcf_wjcfb set  cfdqsj = ?, filepath=?,xn = ?,xq = ?,cflbdm=?,cflbmc = ?,cfyymc = ?,wjsj = ?," + "sbb = ?,sbsj = ?,wjssjg = ?,bz = ?,cfwh = ?,cfsj = ?,cfyj = ?,nd=?,sdlx=? where cfid=?", new String[] {
						form.getCfdqsj(), form.getFilepath(), form.getXn(), form.getXq(), form.getCflbdm(),form.getCflbmc(), form.getCfyydm(), form.getWjsj(), form.getSbb(), form.getSbsj(), form.getWjssjg(), form.getBz(), form.getCfwh(), form.getCfsj(),
						form.getCfyj(),form.getNd(),form.getSdlx(),form.getCfid() });
			}
		}else{
			if (StringUtils.isNotNull(fjmc)) {
				return dao.updateBlob("update xg_wjcf_wjcfb set cfdqsj = ?, fj = ?,xn = ?,xq = ?,cflbmc = ?,cfyymc = ?,wjsj = ?," + "sbb = ?,sbsj = ?,wjssjg = ?,bz = ?,cfwh = ?,cfsj = ?,fjmc = ?,cfyj =?,nd=? where cfid=?",
						new String[] {form.getCfdqsj(), form.getXn(), form.getXq(), form.getCflbdm(), form.getCfyydm(), form.getWjsj(), form.getSbb(), form.getSbsj(), form.getWjssjg(), form.getBz(), form.getCfwh(),
								form.getCfsj(), fjmc,form.getCfyj(),form.getNd(), form.getCfid() }, form.getFj().getInputStream().available(), form.getFj().getInputStream());
			} else {
				return dao.runUpdate("update xg_wjcf_wjcfb set  cfdqsj = ?, filepath=?, filepath2=?, filepath3=?, filepath4=?,xn = ?,xq = ?,cflbdm=?,cflbmc = ?,cfyymc = ?,wjsj = ?," + "sbb = ?,sbsj = ?,wjssjg = ?,bz = ?,cfwh = ?,cfsj = ?,cfyj = ?,nd=? where cfid=?", new String[] {
						form.getCfdqsj(), form.getFilepath(), form.getFilepath2(), form.getFilepath3(), form.getFilepath4(), form.getXn(), form.getXq(), form.getCflbdm(),form.getCflbmc(), form.getCfyydm(), form.getWjsj(), form.getSbb(), form.getSbsj(), form.getWjssjg(), form.getBz(), form.getCfwh(), form.getCfsj(),
						form.getCfyj(),form.getNd(),form.getCfid() });
			}
		}

	}

	/**
	 * ���洦��������Ϣ
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfssjgBc(CfjgForm form) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runUpdate("update xg_wjcf_wjcfb set sssj=?,sswh=?,ssjg=?,cfggw=?,ssyj=? where cfid = ?", new String[] { form.getSssj(), form.getSswh(), form.getSsjg(), form.getCflbmc(),
				form.getSsyj(), form.getCfid() });
	}

	/**
	 * ���洦�ֽ����Ϣ
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfjcjgBc(CfjgForm form) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runUpdate("update xg_wjcf_wjcfb set jcsj=?,jcwh=?,jcyj=? where cfid = ?", new String[] { form.getJcsj(), form.getJcwh(), form.getJcyj(), form.getCfid() });
	}

	/**
	 * ���洦����ֹ��Ϣ
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfzzjgBc(CfjgForm form) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runUpdate("update xg_wjcf_wjcfb set zzsj=?,zzwh=?,zzyj=? where cfid = ?", new String[] { form.getZzsj(), form.getZzwh(), form.getZzyj(), form.getCfid() });
	}

	/**
	 * ��ѯ������Ϣ
	 * 
	 * @param form
	 * @return
	 */
	public Blob fjCx(String sql, String[] inputList, String column) {
		DAO dao = DAO.getInstance();
		return dao.getOneLong(sql, inputList, column);
	}

	/**
	 * 
	 * @����:��ѯ�Ƿ���Ա��ְ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-1 ����11:10:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfid
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public HashMap<String, String> getZwAndZzmm(String xh) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select c.ldmc,c.qsh,b.zwmc,case when a.zzmm='01'or a.zzmm='02'or a.zzmm='03'then '��'else '��' end as sfdty from view_xsxxb a ");
		sql.append("left join VIEW_NEW_DC_SZDW_XSDWWH b on a.xh=b.xh ");
		sql.append("left join xg_view_gygl_new_xszsgl c on a.xh = c.xh ");
		sql.append("where a.xh=?");
		return dao.getMap(sql.toString(), new String[] { xh }, new String[] { "zwmc", "sfdty", "ldmc", "qsh" });
	}

	/**
	 * ͨ��ѧ�Ų�ѯΥ�ʹ����б�
	 * 
	 * @param xh
	 * @return
	 */
	public List<String[]> getStuWjcfList(String xh) {
		DAO dao = DAO.getInstance();
		return dao.rsToVator("select xn,(select xqmc from xqdzb b where " + "a.xq=b.xqdm) xq,(case when cfggw is not null then cfggw else cflbmc end) cflbmc,"
				+ "cfyymc,cfsj,cfwh,sssj,sswh,ssjg,jcsj,jcwh," + "decode(zzwh,null,decode(jcwh, null,decode(ssjg, 'wsh', 'δ���', 'shz', '�����', 'cxcf', '�����ѽ��', '���ֳ���'), '�����ѽ��') ,'��������ֹ')as jg "
				+ "from xg_wjcf_wjcfb a where xh=? order by xn desc,xq", new String[] { xh }, new String[] { "xn", "xq", "cflbmc", "cfyymc", "cfsj", "cfwh", "jg" });
	}

	/**
	 * ����ά���Զ��嵼��
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getCfjgExportList(CfjgForm model, User user) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		// �û�����
		// User user = model.getUser();
		String[] colList = new String[] { "cfid", "xn", "xq", "xh", "xm", "cflbmc", "cfyymc", "fwsj", "fwwh", "fwjg", "zzwh", "zzsj", "zzyj" };
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);

		String qxSql = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");

		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		// ====================�������� end================================
		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r , a.* ");
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" ,bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm ");
		}
		sql.append(" from xg_view_wjcf_wjcfb a");
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" left join view_xg_bzrxx bzr on a.bjdm = bzr.bjdm");
		}
		sql.append(query + qxSql);
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) CommonQueryDAO.commonQueryforExportList(sql.toString(), "", inputV, colList, model);
		return list;
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������

	}
	
	/**
	 * ͨ��ѧ�Ų�ѯ��ǰѧ��Υ�����
	 * 
	 * @param xh
	 * @return
	 */
	public String getWjxxByXhXnXq(String xh,String xn,String xq) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,wm_concat(cflbmc) cfmc from xg_view_wjcf_wjcfb where ");
		sql.append("xh=? and xn=? and xq=? group by xh");
		return dao.getOneRs(sql.toString(), new String[]{xh,xn,xq}, "cfmc");
		
	}

	/**
	 * @����:��������̵Ĵ��ֽ���޸�
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-2-16 ����11:25:31
	 * @param myForm
	 */
	public boolean cfsjshlcXg(CfjgForm form) throws  Exception {
		DAO dao = DAO.getInstance();
		String fjmc = StringUtils.isNotNull(form.getFjmc()) ? form.getFjmc().substring(form.getFjmc().lastIndexOf("\\") + 1, form.getFjmc().length()) : "";
		if (StringUtils.isNotNull(fjmc)) {
			return dao.updateBlob("update xg_wjcf_wjcfb set cfdqsj = ?, fj = ?,cfwh = ?,cfsj = ?,fjmc = ? where cfid=?",
					new String[] {form.getCfdqsj(), form.getCfwh(),form.getCfsj(),form.getCfid() }, form.getFj().getInputStream().available(), form.getFj().getInputStream());
		} else {
			return dao.runUpdate("update xg_wjcf_wjcfb set cfdqsj = ?, filepath=?,cfwh = ?,cfsj = ? where cfid=?", new String[] {
					form.getCfdqsj(),form.getFilepath(), form.getCfwh(), form.getCfsj(),
					form.getCfid() });
		}
	}

	/** 
	 * @����:�ൺ�Ƶ����ְҵ����ѧԺ����Ĭ�ϴ����ĺţ����+4λ˳��ţ���ȡ��ǰ�������˳���+1���ֵ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��27�� ����2:48:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param regexp
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getDefaultCfwhFor13011(String regexp) {
		String sql = "SELECT nvl(max(to_number(substr(cfwh,-4))),-1)+1 cfwhseed FROM ((SELECT cfwh from xg_wjcf_wjcfb) "+
					 "UNION (SELECT kzzd5 cfwh FROM xg_wjcf_wjcfsbb)) WHERE  regexp_like(cfwh,?)";
		return dao.getOneRs(sql, new String[]{regexp}, "cfwhseed");
	}

	/** 
	 * @����:���ݴ���id���飬��ѯ���ֽ����Ϣ���б�
	 * ���ں�ְҵ����ѧԺ�����־��������أ�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��15�� ����10:55:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfids
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCfjgList(String[] cfids) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT x.xh,x.xm,x.xb,x.bjmc,w.cfid,w.cflbdm,w.cflbmc,w.cfwh,w.wjssjg,w.cfyymc,");
		sql.append("substr(w.cfyymc,instr(w.cfyymc,'��'),instr(w.cfyymc,'��')) djt FROM xg_wjcf_wjcfb w ");
		sql.append("LEFT JOIN view_xsbfxx x ON w.xh = x.xh ");
		sql.append("WHERE w.cfid in (");
		
		for(int i=0;i<cfids.length;i++){
			sql.append("?");
			if(i!=cfids.length-1){
				sql.append(",");
			}
		}
		
		sql.append(")");
		return dao.getListNotOut(sql.toString(), cfids);
	}

	/** 
	 * @����:���ݴ���id���飬��ѯ���ֽ����Ϣ�����������Ρ�������ò����Ϣ���б�
	 * ���ں�ְҵ����ѧԺ���������������أ�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��16�� ����2:38:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfids
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCfjgMoreList(String[] cfids) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT x.xh,x.xm,x.xb,x.bjmc,x.bjdm,x.mzmc,x.zzmmmc,x.xymc,w.cflbmc,w.wjsj,w.cfid,w.bz,w.cfyj,");
		sql.append("(SELECT replace(wm_concat(xm),';','��') bzr FROM fdyxxb WHERE zgh in (SELECT zgh FROM bzrbbb WHERE bjdm = x.bjdm)) bzr ");
		sql.append("FROM xg_wjcf_wjcfb w LEFT JOIN view_xsbfxx x ON w.xh = x.xh ");
		sql.append("WHERE w.cfid in (");
		
		for(int i=0;i<cfids.length;i++){
			sql.append("?");
			if(i!=cfids.length-1){
				sql.append(",");
			}
		}
		
		sql.append(")");
		return dao.getListNotOut(sql.toString(), cfids);
	}
	
	/**
	 * @����: ����cfidȡ��¼
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-8-2 ����11:53:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCfxxByCfid(String cfid){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_wjcf_wjcfb where cfid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{cfid});
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ��ظ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-26 ����07:42:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(CfjgForm form){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_wjcf_wjcfb where xn = ? and xq = ? and xh = ? and wjsj = ? and cflbdm = ?");
		paraList.add(form.getXn());
		paraList.add(form.getXq());
		paraList.add(form.getXh());
		paraList.add(form.getWjsj());
		paraList.add(form.getCflbdm());
		if(StringUtils.isNotNull(form.getCfid())){
			sql.append(" and cfid != ?");
			paraList.add(form.getCfid());
		}
		String rs = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(rs) ? true :false;
	}
}
