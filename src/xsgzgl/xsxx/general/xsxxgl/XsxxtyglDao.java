package xsgzgl.xsxx.general.xsxxgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglModel;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgForm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Encrypt;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cxtj.QgzxCxtjForm;
import xsgzgl.szdw.general.SzdwGeneralForm;

/**
 * ѧ����Ϣ - ͨ�ù���DAO
 * lt
 * 2013-1-7
 */
public class XsxxtyglDao extends CommDAO {
	
	// ==================ִ�в�ѯ����begin =============================
	/**
	 * ��ѯ��У����Ϣ�б�
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZxsxxList(XsxxtyglForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ====================��������===================================

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select xh pk,a.*,rownum r from (");
		tableSql.append("select * from view_xsxxb_zxs a ");
		tableSql.append("where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(" ");
		tableSql.append(searchTj);
		tableSql.append(" order by nj desc nulls last,xydm,zydm,bjdm,xh) a where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = getCxjgzdpz();

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(myForm.getPages(), tableSql.toString(),
						inputV, colList);

		return list;
	}
	
	/**
	 * ��ȡѧ����Ϣ��ѯ�������ֶ�-Ӣ��
	 * @return
	 */
	public String[] getCxjgzdpz() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "en");
		map.put("gnlj", "xsxx_tygl_cxzxs.do");
		String[] cnArray = getCxjgzdpz(map);
		if (cnArray == null) {
			cnArray = new String[]{"pk", "xh", "xm", "xb", "nj","xymc", "zymc", "bjmc"};
		}
		
		return cnArray;
	}
	
	/**
	 * �������ͷ����ֶλ����ֶ�����
	 * @param type  en/cn
	 * @return
	 */
	public String[] getCxjgzdpz(HashMap<String, String> map) {
		List<HashMap<String, String>> rs = getCxjgzdpzList(map);
		String[] cnArray = null;
		String[] enArray = null;
		if (rs != null && !rs.isEmpty()) {
			enArray = new String[rs.size()+1];
			cnArray = new String[rs.size()+1];
			enArray[0] = "pk";
			for (int i=0;i<rs.size();i++) {
				enArray[i+1] = rs.get(i) != null ? rs.get(i).get("zd") : "δ֪��";
				cnArray[i+1] = rs.get(i) != null ? rs.get(i).get("zdmc") : "δ֪��";
			}
			return "en".equalsIgnoreCase(map.get("type")) ? enArray : cnArray;
		}
		return cnArray;
	}
	
	/**
	 * ��ȡѧ����Ϣ��ѯ��������ֶ��б�
	 * @return
	 */
	public List<HashMap<String, String>> getCxjgzdpzList(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		return dao.getList("select zd,nvl(xgzdmc,zdmc) zdmc,xssx from xg_xsxx_cxjgpzb " +
				"where sfjgxs='��' and gnlj=? order by to_number(xssx)", 
				new String[]{map.get("gnlj")}, new String[]{"zd", "zdmc", "xssx"});
	}
	
	/**
	 * ��ѯ��ʾ���б�
	 * 
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getXsqlbList(XsxxtyglForm myForm) {
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(
						"select * from xg_xsxx_xsqszb where sfqy=? order by sfzd desc,to_number(xssx) asc ",
						new String[] { myForm.getSfqy() });
	}
	
	/**
	 * ��ȡ�ֶ������б�
	 * @return
	 */
	public List<HashMap<String, String>> getZdpzList() {
		DAO dao = DAO.getInstance();
		return dao.getListNotOut("select * from view_xg_xsxx_zdszb", new String[]{});
	}

	/**
	 * ������У����Ϣ
	 * @param myForm
	 * @return
	 */
	public boolean saveZxsxx(XsxxtyglForm myForm) {
		
		return false;
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯ��У����Ϣ
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getZxsxxByXh(XsxxtyglForm myForm) {
		DAO dao = DAO.getInstance();
		String sql = "select a.xgyx,a.jtdzxx,a.yhdm,a.yhkh,a.xh,a.xm,a.xb,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.sfzh,a.lxdh,a.xjlb," +
				"(select zjmc from zjxxb b where a.zjdm=b.zjdm ) zjxx,"+
				"(select xqmc from dm_zju_xq c where a.yxdm = c.dm) zjuxqmc,"+ // У��
				"(select zwmc from xg_szdw_xsgb_zwb c where a.xxfx = c.zwid) zjuzwmc,"+
				"a.xjztm,a.xz,a.dzyx,a.lxdzxx,a.sjhm,a.mm,a.ssbh,a.qsdh,a.ssch,a.zsrq,a.zsjzrq,a.yhkh,a.ykth,a.xmpy,a.syd,a.csrq,a.mz," +
				"a.mzmc,a.zzmm,a.zzmmmc,a.cym,a.sg,a.tz,a.tc,a.kslb,a.rxfs,a.pyfs,a.pycc,a.zd6,a.bz,a.xszp,a.whcd,a.rxqdw,a.jtdh,a.jrgqtsj," +
				"a.jrgcdsj,a.jtcygc,a.jg,a.jlcfjl,a.jtdz,a.jtyb,a.xx,a.jkzk,a.ah,a.sfdk,a.jtjjqk,a.shgxxm1,a.shgxgx1,a.shgxgzdw1," +
				"a.shgxzw1,a.shgxdwdh1,a.shgxsjhm1,a.shgxxm2,a.shgxgx2,a.shgxgzdw2,a.shgxzw2,a.shgxdwdh2,a.shgxsjhm2,a.jtqkjj," +
				"a.byxx,a.kh,a.rxrq,a.fdyxm,a.gkcj,a.qqhm,a.hkxz,a.hkszd,a.zyjb,a.ssyq,a.ssld,a.jtdzs,a.jtdzx,a.sfzsb,a.sfzfx," +
				"a.zjdm,a.zjmc,a.sfyby,a.byny,a.zw,a.sfzx,a.xxfx,a.thbs,a.dybj,a.shbj,a.xzxm,a.xxszd,a.xw,a.xwzsbh," +
				"a.xwzsxlh,a.bjyjl,a.zsbh,a.zsxlh,a.zyfx,a.zylb,a.fxzy,a.fxzyfx,a.bxxs,a.bxlx,a.xxxs,a.csd,a.pyfx," +
				"a.dqszj,a.zsjj,a.ksh,a.zslb,a.gj,a.sfjh,a.ccqj,a.byzffztdm,a.byzffztmc,a.xwzsxxdz,a.rxnj,a.nfby,a.sfzc," +
				"a.dasfyl,a.daylyy,a.yxdm,a.sfzz,a.sfsf,a.sfdl,a.dxhwp,a.bysj,a.zxwyyzdm,a.wydj,a.jsjdj,a.yzbm,a.shzw,a.csd," +
				"a.jypx,a.xmsj,a.zgzs,a.lxdz,a.jljn,a.sybz1,a.sybz2,a.sybz3,a.xldm,a.zkzh,a.sfcj,a.grjl,a.xslb,a.xslbmc," +
				"a.xslx,a.xslxmc,a.sfbys,a.yhdm,a.yhmc,a.dah,a.ylbxh,a.rxqdwdh,a.rxqdwdz,a.rxqdwyb,a.gzbx,a.sfgat,a.sfssmz," +
				"a.sfzd,a.zcsxhm,a.rxqwhcd,(select xlmc from xldmb where a.rxqwhcd = xldm) rxqwhcdmc ,a.byzh,a.syds,a.xjh,a.jrzzmmrq,a.sfhq,a.sydmc,a.yxmc,a.xwmc,a.zxwyyzmc,a.xlmc," +
				"a.zslbmc,a.pyfsmc,a.sfzblx,a.sydsmc,a.ydlbm,a.ydlbmc,a.sydqmc,a.jgmc,a.hkszdmc,a.csdmc,a.zw,a.grjl,a.zd1,a.zd2,a.zd3,a.zd4,a.zd5," +
				"substr(a.xh ,1 ,1 ) xh1 ,substr(a.xh ,2 ,1 ) xh2 ,substr(a.xh ,3 ,1 ) xh3 ,substr(a.xh ,4 ,1 ) xh4 ,substr(a.xh ,5 ,1 ) xh5 ,substr(a.xh ,6 ,1 ) xh6 ,substr(a.xh ,7 ,1 ) xh7, "+
				"substr(a.xh ,8 ,1 ) xh8 ,substr(a.xh ,9 ,1 ) xh9 ,substr(a.xh ,10 ,1 ) xh10 ,substr(a.xh ,11 ,1 ) xh11 ,substr(a.xh ,12 ,1 ) xh12 ,substr(a.xh ,13 ,1 ) xh13 ,substr(a.xh ,14 ,1 ) xh14, "+
				"case when a.zzmmmc like '%��Ա%' then '��Ա' when a.zzmmmc like '%��Ա%' then '��Ա' else '' end zzmmmcjc, m.bzrxm, "+
				"(case when jg is not null then (substr(jg,0,2))||'0000' else '' end) jgshen," +
				"(case when substr(jg,3,2)<>'00' then (substr(jg,0,4))||'00' else '' end ) jgshi,(case when substr(jg,5,2)<>'00' then jg else '' end) jgxian," +
				"'' lxdh1,'' lxdh2,b.jtcy1_xm,b.jtcy1_gx,b.jtcy1_gzdw," +
				"b.jtcy1_gzdz,'' jtcy1_zw,b.jtcy1_lxdh1,b.jtcy1_lxdh2,b.jtcy2_xm,b.jtcy2_gx,b.jtcy2_gzdw,b.jtcy2_gzdz,'' jtcy2_zw," +
				"b.jtcy2_lxdh1,b.jtcy2_lxdh2,b.jtcy3_xm,b.jtcy3_gx,b.jtcy3_gzdw,b.jtcy3_gzdz,'' jtcy3_zw,b.jtcy3_lxdh1,b.jtcy3_lxdh2," +
				"'' email,'' jtszd,(select pyccmc from xg_xsxx_pyccdmb c where a.pycc=c.pyccdm) pyccmc," +
				"(select rxfsmc from xg_xsxx_rxfsdmb c where a.rxfs=c.rxfsdm) rxfsmc," +
				"(select kslbmc from xg_xsxx_kslbdmb c where a.kslb=c.kslbdm) kslbmc," +
				"(case when substr(syd,3,2)<>'00' then substr(syd,0,4)||'00' else '' end) sydshi," +
				"(case when substr(syd,5,2)<>'00' then syd else '' end) sydx,(case when syd is not null then substr(syd,0,2)||'00' else '' end) syds," +
				"(case when a.hkszd is not null then (substr(a.hkszd,0,2))||'0000' else '' end) hkshen," +
				"(case when substr(a.hkszd,3,2)<>'00' then (substr(a.hkszd,0,4))||'00' else '' end) hkshi," +
				"(case when substr(a.hkszd,5,2)<>'00' then a.hkszd else '' end) hkxian," +
				"(select c.qxmc from dmk_qx c where c.qxdm = hkshen) hkshenmc," +
			    "(select c.qxmc from dmk_qx c where c.qxdm = hkshi) hkshimc," +
			    "(select c.qxmc from dmk_qx c where c.qxdm = hkxian) hkxianmc," +
			    "(select c.qxmc from dmk_qx c where c.qxdm = substr(a.JTDZX, 0, 2) || '0000') jtdzshenmc," +
			    "(select c.qxmc from dmk_qx c where c.qxdm = substr(a.JTDZX, 0, 4) || '00' and a.JTDZX <> substr(a.JTDZX, 0, 2) || '0000') jtdzshimc," +
			    "(select c.qxmc from dmk_qx c where c.qxdm = a.JTDZX and a.JTDZX <> substr(a.JTDZX, 0, 2) || '0000' and a.JTDZX <> substr(a.JTDZX, 0, 4) || '00') jtdzxianmc,(select hyzkmc from dmk_hyzk d where a.SFJH = d.hyzkdm)hyzkmc " +
				" from view_xsxxb a left join (" +
				"select a.*,b.jtcy2_xm,b.jtcy2_gx,b.jtcy2_gzdw,b.jtcy2_gzdz,b.jtcy2_lxdh1,b.jtcy2_lxdh2,c.jtcy3_xm,c.jtcy3_gx,c.jtcy3_gzdw,c.jtcy3_gzdz,c.jtcy3_lxdh1,c.jtcy3_lxdh2 from ( " +
				"select * from ( " +
				"select a.xh,a.cyxm jtcy1_xm,b.mc jtcy1_gx,a.cyxxdw jtcy1_gzdw,a.ylzd1 jtcy1_gzdz,a.cylxdh jtcy1_lxdh1,a.ylzd4 jtcy1_lxdh2, " +
				"row_number() over(partition by a.xh order by a.rowid) rn1 from xg_xszz_new_jtcyb a left join xszz_jtcygxb b on a.cygx=b.dm ) where rn1=1) a left join " +
				"(select * from ( " +
				"select a.xh,a.cyxm jtcy2_xm,b.mc jtcy2_gx,a.cyxxdw jtcy2_gzdw,a.ylzd1 jtcy2_gzdz,a.cylxdh jtcy2_lxdh1,a.ylzd4 jtcy2_lxdh2, " +
				"row_number() over(partition by a.xh order by a.rowid) rn2 from xg_xszz_new_jtcyb a left join xszz_jtcygxb b on a.cygx=b.dm ) where rn2=2) b on a.xh=b.xh left join " +
				"(select * from ( " +
				"select a.xh,a.cyxm jtcy3_xm,b.mc jtcy3_gx,a.cyxxdw jtcy3_gzdw,a.ylzd1 jtcy3_gzdz,a.cylxdh jtcy3_lxdh1,a.ylzd4 jtcy3_lxdh2, " +
				"row_number() over(partition by a.xh order by a.rowid) rn3 from xg_xszz_new_jtcyb a left join xszz_jtcygxb b on a.cygx=b.dm ) where rn3=3) c on a.xh=c.xh) b " +
				"on a.xh=b.xh "+
				"left join (select a.bjdm, WM_CONCAT(b.xm) bzrxm from bzrbbb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) m on a.bjdm = m.bjdm "+
				" where a.xh=?" ;
		return dao.getMapNotOut(sql, new String[]{myForm.getXh()});
	}
	
	/**
	 * ��ȡѧ��������е��޸��ֶ���Ϣ
	 * @param xhR
	 * @return
	 */
	public HashMap<String, String> getZxsxxByStu(String sql, String xh) {
		DAO dao = DAO.getInstance();
		return dao.getMapNotOut(sql, new String[]{xh});
	}
	
	/**
	 * ����ѧ��������Ϣ
	 * @param valueMap
	 * @return
	 */
	public boolean saveInfo(HashMap<String, String> valueMap){
		CommService service=new CommService();
		DAO dao=DAO.getInstance();
		//����SQL
		StringBuilder sql=new StringBuilder();
		//�ֶ���
		StringBuilder comments=new StringBuilder();
		//�ֶ�ֵ
		StringBuilder commentsValue=new StringBuilder();
		//ֵ
		List<String> inputV=new ArrayList<String>();
		sql.append(" insert into xsxxb");
		sql.append(" ( ");
		
		commentsValue.append(" values( ");
		int n=0;
		for(Map.Entry<String, String>entry:valueMap.entrySet()){
			String paramName = entry.getKey();
			String paramValue= entry.getValue();
			if(n!=0){
				comments.append(",");
				commentsValue.append(",");
			}
			comments.append(paramName);
			commentsValue.append("?");
			inputV.add(service.unicode2Gbk(paramValue));
			n++;
		}
		commentsValue.append(" ) ");
		//������ֶ�
		sql.append(comments);
		sql.append(") ");
		//����ֵ
		sql.append(commentsValue);
		
		boolean flag = false;
		
		try {
			flag = dao.runUpdate(sql.toString(), inputV
					.toArray(new String[] {}));
		} catch (Exception e) {
			
			flag = false;
			System.out.println("<------- ���ӱ���ʧ�� ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	private boolean isHavexx(String xh, String tableName) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from " + tableName + " where xh=?");
		HashMap<String, String> map = DAO.getInstance().getMapNotOut(
				sb.toString(), new String[] { xh });
		return null == map || map.size() <= 0 ? false : true;
	}

	/**
	 * ͨ���޸ķ�������д��־��
	 * 
	 * @param BasicModel
	 *            model model ������� valueMap��user����Ϣ user ��������д��־ tableName
	 *            ָ���޸ĵı��� pk �����ֶ� valueMap ����ֵ���޸��ֶ���Ϣ
	 * @return
	 * @throws Exception
	 */
	public boolean updateInfo(XsxxtyglForm myForm, String tableName) {
		CommService service = new CommService();

		DAO dao = DAO.getInstance();

		// �û����� д��־���
		User user = myForm.getUser();
		// �޸�ֵ
		HashMap<String, String> valueMap = myForm.getValueMap();
		// ����

		// ����SQL
		StringBuilder sql = new StringBuilder();
		// �ֶ���
		StringBuilder comments = new StringBuilder();
		// �޸�����
		StringBuilder query = new StringBuilder();
		// �޸��ֶ���ֵ
		List<String> inputV = new ArrayList<String>();
		// �޸���������ֵ
		List<String> queryV = new ArrayList<String>();

		// �����ֶ�
		String pk = myForm.getPk();

		sql.append(" update ");
		sql.append(tableName);
		sql.append(" set ");

		int n = 0;
		for (Map.Entry<String, String> entry : valueMap.entrySet()) {

			// ��(�ֶ���)
			String paramName = entry.getKey();
			// ֵ(�ֶ�ֵ)
			String paramValue = entry.getValue();

			boolean flag = true;// �жϸ��ֶ��Ƿ�Ϊ�����ֶ�

			// ------------------����ƴ�� begin --------------------
			if (pk.equalsIgnoreCase(paramName)) {
				flag = false;
				// ------------�������� begin------------
				query.append(" and ");
				query.append(pk);
				query.append("=?");
				// ------------�������� end------------
				queryV.add(paramValue);
				// -------------er~ ����ֵ begin--------

				// -------------er~ ����ֵ end----------
			}
			// ------------------����ƴ�� end --------------------

			// ---------....�����ֶ��ǲ���Ҫ�޸ĵ� begin----------
			if (flag) {

				if (n != 0) {

					comments.append(",");
				}
				// -------�޸���Ϣ begin----------
				comments.append(paramName);
				comments.append("=?");
				// -------�޸���Ϣ end ----------
				n++;

				// --------------�޸�ֵ------------------
				inputV.add(service.unicode2Gbk(paramValue));
			}
			// ---------....�����ֶ��ǲ���Ҫ�޸ĵ� end----------
		}

		// ������ֶ�
		sql.append(comments);
		sql.append(" where 1=1  ");
		sql.append(query);

		boolean flag = false;

		inputV.addAll(queryV);
		// ������ʱд�� 0�±�Ϊѧ��
		// ��������ڸ�����Ϣ
		if (!isHavexx(queryV.get(0), tableName)) {
			return saveInfo(myForm, "xsfzxxb");
		}
		try {
			// ---------------ִ���޸� SQL���----------------------
			flag = dao.runUpdate(sql.toString(), inputV
					.toArray(new String[] {}), tableName, user);
		} catch (Exception e) {

			flag = false;
			System.out.println(tableName + "<------- �޸�ʧ�� ------>");
			e.printStackTrace();
		}

		return flag;
	}

	public boolean saveInfo(XsxxtyglForm myForm, String tableName) {
		CommService service = new CommService();

		DAO dao = DAO.getInstance();

		// �û����� д��־���
		User user = myForm.getUser();
		// �޸�ֵ
		HashMap<String, String> valueMap = myForm.getValueMap();
		// ����

		// ����SQL
		StringBuilder sql = new StringBuilder();
		StringBuilder values = new StringBuilder();
		// �ֶ���
		StringBuilder comments = new StringBuilder();
		// �޸�����
		StringBuilder query = new StringBuilder();
		// �޸��ֶ���ֵ
		List<String> inputV = new ArrayList<String>();
		// �޸���������ֵ
		List<String> queryV = new ArrayList<String>();

		// �����ֶ�
		String pk = myForm.getPk();

		sql.append(" insert into ");
		sql.append(tableName);
		sql.append("(");
		values.append("values(");
		List<String> comment = new ArrayList<String>();
		int n = 0;
		for (Map.Entry<String, String> entry : valueMap.entrySet()) {

			// ��(�ֶ���)
			String paramName = entry.getKey();
			// ֵ(�ֶ�ֵ)
			String paramValue = entry.getValue();

			boolean flag = true;// �жϸ��ֶ��Ƿ�Ϊ�����ֶ�
			if (n != 0) {

				comments.append(",");
				values.append(",");
			}
			comments.append(paramName);
			values.append("?");
			n++;
			// --------------�޸�ֵ------------------
			inputV.add(service.unicode2Gbk(paramValue));
		}

		// ������ֶ�
		sql.append(comments+")");
		sql.append(values + ")");
		boolean flag = false;
		inputV.addAll(queryV);
		try {
			// ---------------ִ���޸� SQL���----------------------
			flag = DAO.getInstance().insert(sql.toString(),
					inputV.toArray(new String[] {}));
		} catch (Exception e) {

			flag = false;
			System.out.println(tableName + "<------- �޸�ʧ�� ------>");
			e.printStackTrace();
		}

		return flag;
	}
	/**
	 * ɾ��ѧ������
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws Exception
	 */
	public Boolean deleteZxsxx(String[] pkValue, User user) throws Exception {

		List<String> inputV = new ArrayList<String>();

		// xsxxbɾ������
		String tableName = "xsxxb";
		StringBuilder sql = new StringBuilder();
		sql.append(" delete xsxxb ");
		sql.append(" where  1=1 and(  ");

		for (int i = 0; i < pkValue.length; i++) {
			if (i != 0) {
				sql.append(" or ");
			}
			sql.append(" xh = ? ");
			inputV.add(pkValue[i]);
		}
		sql.append(")");
		
		List<String[]> params = new ArrayList<String[]>();
		params.add(inputV.toArray(new String[] {}));
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);
		return flag;
	}
	
	/**
	 * ɾ��ѧ����Ƭ
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws Exception
	 */
	public Boolean deleteXszpxx(String[] pkValue, User user) throws Exception {

		List<String> inputV = new ArrayList<String>();

		String tableName = "xszpb";
		StringBuilder sql = new StringBuilder();
		

		// xszpbɾ������
		sql.append(" delete xszpb ");
		sql.append(" where  1=1 and(  ");

		for (int i = 0; i < pkValue.length; i++) {
			if (i != 0) {
				sql.append(" or ");
			}
			sql.append(" xh = ? ");
			inputV.add(pkValue[i]);
		}
		sql.append(")");
		
		List<String[]> params = new ArrayList<String[]>();
		params.add(inputV.toArray(new String[] {}));
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);
		return flag;
	}
	
	/**
	 * ɾ��ѧ������
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws Exception
	 */
	public Boolean deleteXsmmxx(String[] pkValue, User user) throws Exception {

		List<String> inputV = new ArrayList<String>();

		String tableName = "xsmmb";
		StringBuilder sql = new StringBuilder();
		
		// xsmmbɾ������
		sql.append(" delete xsmmb ");
		sql.append(" where  1=1 and(  ");

		for (int i = 0; i < pkValue.length; i++) {
			if (i != 0) {
				sql.append(" or ");
			}
			sql.append(" xh = ? ");
			inputV.add(pkValue[i]);
		}
		sql.append(")");
		
		List<String[]> params = new ArrayList<String[]>();
		params.add(inputV.toArray(new String[] {}));
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);
		return flag;
	}
	
	/**
	 * ɾ��ѧ��������Ϣ
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws Exception
	 */
	public Boolean deleteXsfzxx(String[] pkValue, User user) throws Exception {

		List<String> inputV = new ArrayList<String>();

		// xsxxbɾ������
		String tableName = "xsfzxxb";
		StringBuilder sql = new StringBuilder();
		
		
		// xsfzxxbɾ������
		sql.append(" delete xsfzxxb ");
		sql.append(" where  1=1 and(  ");

		for (int i = 0; i < pkValue.length; i++) {
			if (i != 0) {
				sql.append(" or ");
			}
			sql.append(" xh = ? ");
			inputV.add(pkValue[i]);
		}
		sql.append(")");
		
		List<String[]> params = new ArrayList<String[]>();
		params.add(inputV.toArray(new String[] {}));
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);
		return flag;
	}
	
	/**
	 * ����ѧ�ŵ�ѧ�����룬��Ƭ��������Ϣ
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsqtxx(XsxxtyglForm myForm, User user) throws Exception{
		String[] sql = {
				"insert into xszpb (xh) values ('" + myForm.getXh() + "')",
				"insert into xsfzxxb(xh) values ('" + myForm.getXh() + "')" };
		DAO dao = DAO.getInstance();
		boolean flag = dao.checkBatch(dao.runBatch(sql));
		return flag;
	}
	
	/**
	 * ��ȡ�鿴ҳ���й���ģ���б���Ϣ
	 * @return
	 */
	public List<HashMap<String, String>> getCkxsgnmkxx() {
		DAO dao = DAO.getInstance();
		return dao.getList("select * from xg_xsxx_ckyqpzb where sfqy='��' " +
				"order by to_number(xssx)", new String[]{}, new String[]{"gnmk", "xssx", "zxys", "sfqy", "zmk"});
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯ�Ƿ����
	 * @param xh
	 * @return
	 */
	public String chkStuIsExists(String xh) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select count(xh) cnt from xsxxb where xh=?", new String[]{xh}, "cnt");
	}
	
	/**
	 * ��ȡ�ֶ�ͬ�����ñ�
	 * @return
	 */
	public List<HashMap<String, String>> getXsxxTbzdpzb() {
		DAO dao = DAO.getInstance();
		return dao.getListNotOut("select * from xg_xsxx_xxxgzdszb", new String[]{});
	}
	
	/**
	 * ��ѯ����У����Ϣ�б�
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getFzxsxxList(XsxxtyglForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ====================��������===================================

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select xh pk,a.*,rownum r from (");
		tableSql.append("select * from view_xsxxb_fzxs a ");
		tableSql.append("where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(" ");
		tableSql.append(searchTj);
		tableSql.append(" order by nj desc nulls last,xydm,zydm,bjdm,xh) a where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = getCxjgzdpz();

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(myForm.getPages(), tableSql.toString(),
						inputV, colList);

		return list;
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ���ɼ��б�
	 * @param xh
	 * @return
	 */
	public List<String[]> getStuCjList(String xh) {
		DAO dao = DAO.getInstance();
		return dao.rsToVator("select xn,b.xqmc,kcmc,kcxz,cj,bkcj,jd,xf from cjb a "
								+ "left join xqdzb b on a.xq=b.xqdm where a.xh=? order by xn desc,xq",new String[] { xh },
						new String[] { "xn", "xqmc", "kcmc", "kcxz", "cj","bkcj",
								"jd", "xf" });
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ���ɼ��б�(���������Ի�)
	 * @param xh
	 * @return
	 */
	public List<String[]> getStugllgCjList(String xh) {
		DAO dao = DAO.getInstance();
		return dao.rsToVator("select xn,b.xqmc,kcmc,kcxz,cj,bkcj,cxcj,jd,xf from cjb a "
								+ "left join xqdzb b on a.xq=b.xqdm where a.xh=? order by xn desc,xq",new String[] { xh },
						new String[] { "xn", "xqmc", "kcmc", "kcxz", "cj","bkcj","cxcj",
								"jd", "xf" });
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ���ɼ��б�(����ѧԺ���Ի�)
	 * @param xh
	 * @return
	 */
	public List<String[]> getStuczxyCjList(String xh) {
		DAO dao = DAO.getInstance();
		return dao.rsToVator("select xn,b.xqmc,kcmc,kcxz,cj,zpcj1,jd,xf from cjb a "
								+ "left join xqdzb b on a.xq=b.xqdm where a.xh=? order by xn desc,xq",new String[] { xh },
						new String[] { "xn", "xqmc", "kcmc", "kcxz", "cj","zpcj1",
								"jd", "xf" });
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ���ȼ��ɼ��б�
	 * @param xh
	 * @return
	 */
	public List<String[]> getStuDjcjList(String xh) {
		DAO dao = DAO.getInstance();
		return dao.rsToVator("select xn,b.xqmc,djksmc,cj from xsdjksb a  "
								+ "left join xqdzb b on a.xq=b.xqdm where a.xh=? order by xn desc,xq",new String[] { xh },
						new String[] { "xn", "xqmc", "djksmc", "cj"});
	}
	
	/**
	 * ��ȡѧ��������Ϣ�Ƿ����
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getStuSqztMap(String xh) {
		DAO dao = DAO.getInstance();
		return dao.getMapNotOut("select * from xg_xsxx_xxxgsqb where xh=? and shjg<>'tg'", new String[]{xh});
	}
	
	/**
	 * ��ȡ�ֶ������б�
	 * @return
	 */
	public List<HashMap<String, String>> getZdszList() {
		DAO dao = DAO.getInstance();
		return dao.getListNotOut("select a.* from xg_xsxx_xxxgzdszb a,xg_xsxx_tbzdpzb b" +
				" where b.dyzd=a.zd and b.sfyxgwz='��' and a.gnmk = ?", new String[]{"all"});
	}
	
	/**
	 * ��ȡѧ������еļ�¼��
	 * @param xh
	 * @return
	 */
	public String getStuSqzt(String xh) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select count(*) cnt from xg_xsxx_xxxgsqb where xh=? and shjg='shz'", new String[]{xh}, "cnt");
	}
	
	/**
	 * ��ȡ���ݿ��е�GUID
	 * @return
	 */
	public String getGuid() {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select sys_guid() guid from dual", new String[]{}, "guid");
	}
	
	public boolean getZdsfkxg(String zdm, List<HashMap<String, String>> kxgzdMap) {
		
			for(int i = 0;i<kxgzdMap.size();i++){
				String paramName = kxgzdMap.get(i).get("zd");
				if ("xh".equalsIgnoreCase(zdm) || "sqid".equalsIgnoreCase(zdm)) {return true;}
				if (zdm.equalsIgnoreCase(paramName))
					return true;
			}
			
			return false;
			
	}
	
	/**
	 * ����ѧ��������Ϣ
	 * @param valueMap
	 * @return
	 */
	public boolean saveInfo(HashMap<String, String> valueMap, HashMap<String, String> xsxxMap, String userType){
		CommService service=new CommService();
		DAO dao=DAO.getInstance();
		//����SQL
		StringBuilder sql=new StringBuilder();
		//�ֶ���
		StringBuilder comments=new StringBuilder();
		//�ֶ�ֵ
		StringBuilder commentsValue=new StringBuilder();
		//ֵ
		List<String> inputV=new ArrayList<String>();
		sql.append(" insert into xg_xsxx_xxxgzdxgb");
		sql.append(" ( ");
		List<HashMap<String, String>> kxgzdMaplist =  dao.getListNotOut("select zd from xg_xsxx_xxxgzdszb where lb=? and sfzd is null ",new String[]{userType});
		//HashMap<String, String> kxgzdMap = dao.getMapNotOut("select zd from xg_xsxx_xxxgzdszb where lb=? and sfzd is null ",new String[]{userType});
		//kxgzdMap.put("jtyb", "y");
		
		commentsValue.append(" values( ");
		int n=0;
		for(Map.Entry<String, String>entry:valueMap.entrySet()){
			String paramName = entry.getKey();
			String paramValue= Base.isNull(entry.getValue()) ? "" : entry.getValue();
			//���Ϊ�գ�ѧ�ţ��޸�ǰ������һ�����򲻱����κ�����
			/*
			 * if (StringUtils.isNull(paramValue)
					|| (!"xh".equalsIgnoreCase(paramName) && !Base.isNull(xsxxMap.get(paramName)) && xsxxMap.get(paramName).equalsIgnoreCase(
							service.unicode2Gbk(paramValue)))) {
				continue;
			}
			 */
			if (StringUtils.isNull(paramValue) && Base.isNull(xsxxMap.get(paramName))) {continue;}
			if (!"xh".equalsIgnoreCase(paramName)
					&& null!=xsxxMap.get(paramName)
					&& xsxxMap.get(paramName).equalsIgnoreCase(service.unicode2Gbk(paramValue))) {continue;}
			
			//��ȡ�û����ͣ������޸��ӶΣ����ڿ��޸��ֶη�Χ�ڵ�Ҫ  continue��
			if (null!=kxgzdMaplist&&kxgzdMaplist.size()>0&&!getZdsfkxg(paramName, kxgzdMaplist)) {continue;} 
			
			if(n!=0){
				comments.append(",");
				commentsValue.append(",");
			}
			comments.append(paramName);
			commentsValue.append("?");
			if ("sqid".equalsIgnoreCase(paramName) || "xh".equalsIgnoreCase(paramName)) {
				inputV.add(service.unicode2Gbk(paramValue));
			} else {
				inputV.add(xsxxMap.get(paramName)+ "LiTT" + service.unicode2Gbk(paramValue));
			}
			n++;
		}
		commentsValue.append(" ) ");
		//������ֶ�
		sql.append(comments);
		sql.append(") ");
		//����ֵ
		sql.append(commentsValue);
		
		boolean flag = true;
		if(null!=inputV&&inputV.size()>0){
			try {
				flag = dao.runUpdate(sql.toString(), inputV
						.toArray(new String[] {}));
			} catch (Exception e) {
				
				flag = false;
				System.out.println("<------- ���ӱ���ʧ�� ------>");
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
	/**
	 * ͨ���޸ķ�������д��־��
	 * @param BasicModel model
	 * model ������� valueMap��user����Ϣ
	 * user ��������д��־
	 * tableName ָ���޸ĵı���
	 * pk �����ֶ�
	 * valueMap ����ֵ���޸��ֶ���Ϣ
	 * @return
	 * @throws Exception 
	 */
	public boolean updateInfo(HashMap<String, String> valueMap, HashMap<String, String> xsxxMap){

		CommService service=new CommService();
		
		DAO dao=DAO.getInstance();
		
		// �û����� д��־���
		User user= new User();
		user.setUserName("dqyh");
		// ����
		
		//����SQL
		StringBuilder sql=new StringBuilder();
		//�ֶ���
		StringBuilder comments=new StringBuilder();
		// �޸�����
		StringBuilder query=new StringBuilder();
		// �޸��ֶ���ֵ
		List<String> inputV=new ArrayList<String>();
		// �޸���������ֵ
		List<String> queryV=new ArrayList<String>();
		
		HashMap<String, String> zdxgMap = new HashMap<String, String>();
		zdxgMap = dao.getMapNotOut("select * from xg_xsxx_xxxgzdxgb where sqid=?", new String[]{valueMap.get("sqid")});
		
		sql.append(" update xg_xsxx_xxxgzdxgb ");
		sql.append(" set ");
		
		int n=0;
		for(Map.Entry<String, String>entry:valueMap.entrySet()){
			
			// ��(�ֶ���)
			String paramName = entry.getKey();
			// ֵ(�ֶ�ֵ)
			String paramValue= Base.isNull(entry.getValue()) ? "" : entry.getValue();
			
			
			if ("sqid".equalsIgnoreCase(paramName) || "xh".equalsIgnoreCase(paramName)
					) {continue;}//�������ܸ���
			String xsxxValue = Base.isNull(xsxxMap.get(paramName)) ? "" : xsxxMap.get(paramName);
 			if (xsxxValue.equalsIgnoreCase(service.unicode2Gbk(paramValue))) {
				if (StringUtils.isNull(zdxgMap.get(paramName))) {continue;}//�������ܸ���
			}
			boolean flag=true;// �жϸ��ֶ��Ƿ�Ϊ�����ֶ�
			
			// ---------....�����ֶ��ǲ���Ҫ�޸ĵ� begin----------
			if(flag){
				
				if(n!=0){
					
					comments.append(",");
				}
				// -------�޸���Ϣ begin----------
				comments.append(paramName);
				comments.append("=?");
				// -------�޸���Ϣ end ----------
				n++;
				
				// --------------�޸�ֵ------------------
				inputV.add(xsxxMap.get(paramName) +"LiTT" +service.unicode2Gbk(paramValue));
			}	
			// ---------....�����ֶ��ǲ���Ҫ�޸ĵ� end----------
		}
		
		//������ֶ�
		sql.append(comments);
		sql.append(" where sqid ='"+valueMap.get("sqid")+"'");
		
		boolean flag = true;
		
		inputV.addAll(queryV);
		
		if(null!=inputV&&inputV.size()>0){
			try {
				// ---------------ִ���޸� SQL���----------------------
				flag = dao.runUpdate(sql.toString(), inputV
						.toArray(new String[] {}),"xg_xsxx_xxxgzdxgb ",user);
			} catch (Exception e) {
				
				flag = false;
				System.out.println("xg_xsxx_xxxgzdxgb " + "<------- �޸�ʧ�� ------>");
				e.printStackTrace();
			}
		}
		
		return flag;
	} 
	
	public boolean cshYhmm(XsxxtyglForm myForm) throws Exception{
		Encrypt ept = new Encrypt();
		DAO dao = DAO.getInstance();
		boolean flag = true;
		boolean flag2=false;
		String kl = myForm.getKl();
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> upParams = new ArrayList<String[]>();
		List<String[]> inParams = new ArrayList<String[]>();
		String sql="select xh from view_xsjbxx a where not exists(select 1 from xsmmb b where a.xh=b.xh)";
		String[] xh=dao.getRs(sql, new String[]{}, "xh");
		for(int i=0;i<primarykey_checkVal.length;i++){
			for(int j=0;j<xh.length;j++){
				if(primarykey_checkVal[i].equalsIgnoreCase(xh[j])){
					flag2=true;
					break;
				}
			}
			if(flag2){
				inParams.add(new String[]{primarykey_checkVal[i]});
				flag2=false;
			}else{
				upParams.add(new String[]{primarykey_checkVal[i]});
			}
		}
		
		try {
			if(inParams.size()>0){
				for(int i=0;i<inParams.size();i++){
					String str=inParams.get(i)[0];
					flag=dao.runInsert("xsmmb", new String[]{"mm","xh"}, new String[]{ept.encrypt(kl),str});
				}
			}
			if(flag&&upParams.size()>0){
				flag = saveArrDate("update xsmmb set mm= '"+ept.encrypt(kl)+"' where xh = ?", upParams, "");
			}
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- �û������ʼ��ʧ�� ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * ��ȡ��ͼ���������λ
	 * 
	 * @author xucy
	 */
	public HashMap<String, String> getMinSpgw()
			throws Exception {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.spgw from xg_xtwh_spbz a, ");
		sql.append(" (select splc,min(xh) xh from xg_xtwh_spbz  ");
		sql.append(" where splc = (select shlid from xg_xsxx_xxxgcsszb) ");
		sql.append(" group by splc) b  ");
		sql.append(" where a.xh = b.xh and a.splc = b.splc ");

		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * ��ѯ�����Ϣ
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXgshxx(String sqid) {
		DAO dao = DAO.getInstance();
		String sql = "select t.sqid,t.gwid,t.shzt,t.shsj,t.shyj,t.shr," +
				"(select m.xm from yhb m where t.shr = m.yhm) shrxm,(select a.mc from xg_xtwh_spgw a where a.id = t.gwid) gwmc " +
				"from xg_xsxx_xxxgshb t where t.sqid=? and t.shzt<> 'wsh'";
		return dao.getListNotOut(sql.toString(), new String[] { sqid });
	}
	
	public HashMap<String,String> getWdgz(String id){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append("select * from xg_xtwh_wdgz where userType='teacher' and ywid = ?");

		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}

	/** 
	 * @����:(��ȡѧ���ɲ���Ϣlist)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-6-17 ����02:36:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGbxxList(String xh) {
		DAO dao = DAO.getInstance();
		String sql="select * from view_bjgbxx where xh=?";
		
		return dao.getArrayList(sql, new String[]{xh}, new String[]{"bjgbmc","lxfs","gbjbmc"});
	}
	
	/**
	 * ��У����Ϣ�Զ��嵼��
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZxsxxExportDataList(XsxxtyglForm t, User user)
	throws Exception {

		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		// query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append(" select xh pk,a.*,rownum r from (select *  from view_xsxxb_zxs a where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(" ");
		tableSql.append(searchTj);
		tableSql.append(" order by nj desc nulls last,xydm,zydm,bjdm,xh ) a where 1=1 ");	
		
		return CommonQueryDAO.commonPageQueryForExportMap(t.getPages(), tableSql.toString(),inputV);	
	}
	
	/**
	 * ����У����Ϣ�Զ��嵼��
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getFzxsxxExportDataList(XsxxtyglForm t, User user)
	throws Exception {
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder tableSql = new StringBuilder();
		tableSql.append(" select xh pk,a.*,rownum r from (select *  from view_xsxxb_fzxs a where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(" ");
		tableSql.append(searchTj);
		tableSql.append(" order by nj desc nulls last,xydm,zydm,bjdm,xh ) a where 1=1");		
		
		return CommonQueryDAO.commonPageQueryForExportMap(t.getPages(), tableSql.toString(),inputV);
	}
	//��Ա
	public List<HashMap<String, String>> getTyExportDataList(XsxxtyglForm t, User user)
	throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select xh pk,a.*,rownum r from (");
		sb.append("select t.* from (select nvl2(a.sjhm, '��', '��') as sfytx,nvl2(b.sfpkx, b.sfpkx, '0') as sfpkx ,nvl2(d.xh, '1', '0') as sfbgb ,a.*,e.*,(select mc from XG_XSXX_JKZTB g where a.jkzk=g.dm)jkzkmc,(select mc from sfdqdmb f where a.xwzsbh=f.dm)sfdqmc,(select pyccmc from xg_xsxx_pyccdmb c where a.pycc=c.pyccdm) pyccmc,");
		sb.append("  h.jtdh , ");
		sb.append("(select rxfsmc from xg_xsxx_rxfsdmb c where a.rxfs=c.rxfsdm) rxfsmc,"); 
		sb.append("(select kslbmc from xg_xsxx_kslbdmb c where a.kslb=c.kslbdm) kslbmc from view_xsxxcxjg a left join dmk_qx b on a.syd = b.qxdm left join (select distinct xh from VIEW_NEW_DC_SZDW_XSDWWH where zzzt = '1') d on a.xh=d.xh ");
		
		sb.append("left join ")
		.append("(select nvl(t1.bjdm,t2.bjdm) as bjdm2 , t1.fdyxms , t2.bzrxms from (select a.bjdm ,  WM_CONCAT(b.xm) fdyxms from fdybjb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t1 ")
		.append(" full join ")
		.append("(select a.bjdm ,  WM_CONCAT(b.xm) bzrxms from bzrbbb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t2 on t1.bjdm = t2.bjdm) e ")
		.append(" on a.bjdm = e.bjdm2 ");
		sb.append(" left join xsxxb h on a.xh = h.xh");
		sb.append(") t ");
		sb.append("where 1=1  and zzmmmc = '�й�����������������Ա'");
		if (t.getSfzx() != null && t.getSfzx().equals("0")) {// ����У
			sb.append("  and sfzx='����У'  ");
		} else {// ��У
			sb.append(" and (sfzx='��У' or sfzx is null) ");
		}
		sb.append(searchTjByUser);
		sb.append(" ");
		sb.append(searchTj);
		sb.append(" ) a where 1=1 ");
		
		return CommonQueryDAO.commonPageQueryForExportMap(t.getPages(), sb.toString(),inputV);
	}
	public List<HashMap<String, String>> getTgbExportDataList(XsxxtyglForm t, User user)
	throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sb = new StringBuilder();
		
		sb.append("select xh pk,a.*,rownum r from (");
		sb.append("select t.* from (select nvl2(a.sjhm, '��', '��') as sfytx,nvl2(b.sfpkx, b.sfpkx, '0') as sfpkx ,nvl2(d.xh, '1', '0') as sfbgb ,a.*,e.*,(select mc from XG_XSXX_JKZTB g where a.jkzk=g.dm)jkzkmc,(select mc from sfdqdmb f where a.xwzsbh=f.dm)sfdqmc,(select pyccmc from xg_xsxx_pyccdmb c where a.pycc=c.pyccdm) pyccmc,");
		sb.append(" g.lxmc, g.zwmc, h.jtdh ,");
		sb.append("(select rxfsmc from xg_xsxx_rxfsdmb c where a.rxfs=c.rxfsdm) rxfsmc,"); 
		sb.append("(select kslbmc from xg_xsxx_kslbdmb c where a.kslb=c.kslbdm) kslbmc from view_xsxxcxjg a left join dmk_qx b on a.syd = b.qxdm left join (select distinct xh from VIEW_NEW_DC_SZDW_XSDWWH where zzzt = '1') d on a.xh=d.xh ");
		
		sb.append("left join ")
		.append("(select nvl(t1.bjdm,t2.bjdm) as bjdm2 , t1.fdyxms , t2.bzrxms from (select a.bjdm ,  WM_CONCAT(b.xm) fdyxms from fdybjb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t1 ")
		.append(" full join ")
		.append("(select a.bjdm ,  WM_CONCAT(b.xm) bzrxms from bzrbbb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t2 on t1.bjdm = t2.bjdm) e ")
		.append(" on a.bjdm = e.bjdm2 ");
		sb.append(" left join VIEW_NEW_DC_SZDW_XSDWWH g on a.xh = g.xh ");
		sb.append(" left join xsxxb h on a.xh = h.xh ");
		sb.append(") t ");
		sb.append("where 1=1 and t.lxmc = '�Ÿɲ�' ");
		if (t.getSfzx() != null && t.getSfzx().equals("0")) {// ����У
			sb.append("  and sfzx='����У'  ");
		} else {// ��У
			sb.append(" and (sfzx='��У' or sfzx is null) ");
		}
		sb.append(searchTjByUser);
		sb.append(" ");
		sb.append(searchTj);
		sb.append(" ) a where 1=1 ");
		
		return CommonQueryDAO.commonPageQueryForExportMap(t.getPages(), sb.toString(),inputV);
	}
	/** 
	 * @����:(��ȡ����������Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-7-29 ����02:28:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCxpyxxByXh(XsxxtyglForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select xn,max(xqmc01)xqmc01,max(xqmc02)xqmc02,max(cxdjmc01)cxdjmc01,max(cxdjmc02)cxdjmc02,max(cxpy01)cxpy01,max(cxpy02)cxpy02,max(bzr01) bzr01,max(bzr02) bzr02 from ");
		sql.append("(select xn,(case when xq='01'then");
		sql.append("(select xqmc from xqdzb e where a.xq = e.xqdm) end)xqmc01,");
		sql.append("(case when xq='01'then (select cxdjmc from xsxx_cxdjdmb e where a.cxdj = e.cxdjdm) end) cxdjmc01,");
		sql.append(" (case when xq='01'then cxpy end)cxpy01,");
		sql.append(" (case when xq='01'then bzr end)bzr01,");
		sql.append("(case when xq='02' then (select xqmc from xqdzb e where a.xq = e.xqdm) end) xqmc02 ,");
		sql.append("(case when xq='02'then (select cxdjmc from xsxx_cxdjdmb e where a.cxdj = e.cxdjdm) end) cxdjmc02,");
		sql.append(" (case when xq='02'then cxpy end)cxpy02,");
		sql.append(" (case when xq='02'then bzr end)bzr02");
		sql.append(" from xsxx_cxpyb a where xh = ? order by xn, xq) group by xn order by xn");
		DAO dao = DAO.getInstance();
		
		return dao.getList(sql.toString(), new String[]{model.getXh()}, new String[]{"xn","xqmc01","xqmc02","cxdjmc01","cxdjmc02","cxpy01","cxpy02","bzr01","bzr02"});
	}
	
	public String checkxszpSfcz(String xh){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append("select * from xszpb where xh= ?");

		return dao.getOneRs("select count(*) cnt from xszpb where xh=?", new String[]{xh}, "cnt");
	}

	/** 
	 * @����:(ѧ���γ�list)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-3 ����10:34:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * List<String> �������� 
	 * @throws 
	 */
	public List<String> getKcList(XsxxtyglForm myForm) throws Exception{
		DAO dao = DAO.getInstance();
		String sql="select distinct kcmc from cjb where xh=? order by kcmc";
		return dao.getList(sql, new String[]{myForm.getXh()}, "kcmc");
	}
	
	/** 
	 * @����:(ѧ��ѧ��list)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-3 ����10:34:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * List<String> �������� 
	 * @throws 
	 */
	public List<String> getXnList(XsxxtyglForm myForm) throws Exception{
		DAO dao = DAO.getInstance();
		String sql="select distinct xn from cjb where xh=? order by xn";
		return dao.getList(sql, new String[]{myForm.getXh()}, "xn");
	}

	/** 
	 * @����:(��ȡ����ÿѧ��ĳɼ�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-3 ����10:54:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param xnList
	 * @param kcmc
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getCjMap(XsxxtyglForm myForm,
			List<String> xnList, String kcmc) {
		DAO dao = DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		StringBuilder tempSql=new StringBuilder();
		sql.append("select * from (");
		sql.append("select kcmc");
		for(int i=0;i<xnList.size();i++){
			sql.append(",max(cj"+i+"01)cj"+i+"01");
			sql.append(",max(cj"+i+"02)cj"+i+"02");
			tempSql.append(",decode(xn,'"+xnList.get(i)+"',cj01)cj"+i+"01");
			tempSql.append(",decode(xn,'"+xnList.get(i)+"',cj02)cj"+i+"02");
		}
		sql.append(" from(select kcmc");
		sql.append(tempSql);
		sql.append(" from ( select xn, kcmc, max(cj01) cj01, max(cj02) cj02 ");
		sql.append("from (select xn, kcmc, decode(xq, '01', cj) cj01, decode(xq, '02', cj) cj02");
		sql.append(" from cjb where xh = ? and kcmc=?) group by xn, kcmc)) group by kcmc)");
		return dao.getMapNotOut(sql.toString(), new String[]{myForm.getXh(),kcmc});
	}

	/** 
	 * @����:(��ȡѧ����Ƭ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-3 ����02:51:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * HashMap<String,Blob> �������� 
	 * @throws 
	 */
	public Blob getZpMap(XsxxtyglForm myForm,String column) {
		String sql="select * from xszpb where xh = ?";
		DAO dao = DAO.getInstance();
		
		return dao.getOneBlob(sql, new String[]{myForm.getXh()}, column);
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ���ɼ��б�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getCjList(String xh) {
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(
				"select * from cjb where xh=? ",
				new String[] { xh });
	}
	
	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ��ͥ��Ա��Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-19 ����05:00:01
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getJtcyxxXsList(String xh)
			throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*,b.mc jtcygxmc , c.zzmmmc ");
		sb.append(" from xg_xszz_new_jtcyb a");
		sb.append(" left join xszz_jtcygxb b on a.cygx=b.dm");
		sb.append(" left join zzmmdmb c on a.CYZZMM = c.ZZMMDM ");
		sb.append(" where xh=? ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ���ɼ��б�
	 * @param xh
	 * @return
	 */
	public String getQfqk(String xh) {
		DAO dao = DAO.getInstance();
		
		return dao.getOneRs("select xh,sum(zd1) qfje from rcsw_cwsjb where xh = ? group by xh", new String[]{xh}, "qfje");
		
	}
	
	/**
	 * 
	 * @����:��ѧ��ѧ�ڻ�ȡѧ���ĳɼ���Ϣ
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-7-6 ����11:00:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCjListByXhXnXq(String xh,String xn,String xq){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select kcmc,cj from cjb where xh=? and xq=? and xn=?");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xq,xn});
	}
	/**
	 * @����: �㽭����ְҵѧԺ�����ȡ����Ա��Ϣ
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-12-8 ����10:13:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getFdyxx(String xh){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT A.XH, B.ZGH, B.XM, B.LXDH, A.BJDM FROM XSXXB A LEFT JOIN (SELECT A.ZGH, A.XM, A.LXDH, B.BJDM");
		sql.append("  FROM FDYXXB A LEFT JOIN FDYBJB B ON A.ZGH = B.ZGH) B ON A.BJDM = B.BJDM");
		sql.append(" WHERE XH = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});	
	}
	/** �㽭����ְҵѧԺ�����ȡ��������Ϣ
	 * @����:(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-12-8 ����10:15:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getBzrxx(String xh){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT A.XH, B.ZGH, B.XM, B.LXDH FROM XSXXB A LEFT JOIN (SELECT A.ZGH, B.XM, A.BJDM, B.LXDH FROM BZRBBB A");
		sql.append(" LEFT JOIN FDYXXB B ON A.ZGH = B.ZGH) B ON A.BJDM = B.BJDM");
		sql.append(" WHERE XH = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});	
	}
	/**
	 * @����: �㽭����ְҵѧԺ�����ͥ���������϶�
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-12-8 ����10:16:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getKnrd(String xh){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh, a.xn, a.xq, b.dcmc from XG_XSZZ_NEW_KNSJGB a ");
		sql.append(" left join Xg_Xszz_New_Kndcdmb b on a.rddc = b.dcdm ");
		sql.append(" where xh = ? and xn =(select min(xn) from XG_XSZZ_NEW_KNSJGB where xh =a.xh)");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});	
	}
	
	/**
	 * 
	 * @����: ȡ����Ա����by�༶
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-3 ����11:47:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getFdyByBj(String bjdm){		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();	 
		sql.append(" select a.bjdm , WM_CONCAT(b.xm) xm from fdybjb a left join fdyxxb b on a.zgh = b.zgh where a.bjdm = ? group by a.bjdm ");		
		return dao.getMapNotOut(sql.toString(), new String[]{bjdm});
	}
	
	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException  
	 * @����:��ȡ�༶��������ϸ��Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-15 ����04:31:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getbjHmcXxxx(String bjdm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select bjdm,xh,xm,xb,mzmc,jtdz,jtdh,sfzh,jtshen,jtshi,jtxian,");
		if("12303".equals(Base.xxdm)){
			sql.append(" zzmmmc,sjhm,dzyx,zwmc, ");
		}
		/*��ΪѧԺ�Ǳ��ļ��ͱ��ز�һ�����Ȱ���ѧУ�Ǳ��ļ���ȡֵ�޸ģ��Ժ���������2017-11-10 MengWei====begin====*/
		sql.append(" (select c.qxmc from dmk_qx c where c.qxdm = jtshen) hkshenmc,");
		sql.append(" (select c.qxmc from dmk_qx c where c.qxdm = jtshi) hkshimc,");
		sql.append(" (select c.qxmc from dmk_qx c where c.qxdm = jtxian) hkxianmc, ");
		/*====end====*/
		sql.append(" jzxm from (");
	    sql.append(" select a.xm,a.xh,a.xb,a.mzmc,a.jtdz,a.jtdh,a.sfzh,a.bjdm,");
	    if("12303".equals(Base.xxdm)){
	    	sql.append(" a.zzmmmc,a.sjhm,a.dzyx,b.zwmc,");
	    }
	    sql.append(" (case when a.hkszd is not null then (substr(a.hkszd,0,2))||'0000' else '' end) jtshen,");
	    sql.append(" (case when substr(a.hkszd,3,2)<>'00' then (substr(a.hkszd,0,4))||'00' else '' end) jtshi,");
		sql.append(" (case when substr(a.hkszd,5,2)<>'00' then a.hkszd else '' end) jtxian, ");
		sql.append(" (select c.cyxm from (select * from (select xh, cyxm,row_number() over(partition by xh order by cygx) rn ");
		sql.append(" from xg_xszz_new_jtcyb) where rn = 1) c where a.xh = c.xh) jzxm ");
		sql.append(" from view_xsxxb a  ");
		if("12303".equals(Base.xxdm)){
			sql.append(" left join VIEW_NEW_DC_SZDW_XSDWWH b on a.xh = b.xh");
		}
		sql.append(" where a.sfzx = '��У' or a.sfzx is null)  ");
		
		sql.append(" where bjdm = ? order by xh ");
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql.toString(), new String[]{bjdm});
	}
	
	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException  
	 * @����:��ȡ������༶��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-15 ����04:40:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getHmcBjs(XsxxtyglForm myForm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select a.*,(nvl(d.zs,'0')) zs,(nvl(b.nan,'0')) nan,(nvl(c.nv,'0')) nv from view_njxyzybj a ");
		sql.append(" left join (select count(1) nan, bjdm from view_xsbfxx where xb = '��' and (sfzx = '��У' or sfzx is null) group by bjdm) b on a.bjdm = b.bjdm ");
		sql.append(" left join (select count(1) nv, bjdm from view_xsbfxx where xb = 'Ů' and (sfzx = '��У' or sfzx is null) group by bjdm) c on a.bjdm = c.bjdm");
		sql.append(" left join (select count(1) zs, bjdm from view_xsbfxx where sfzx = '��У' or sfzx is null group by bjdm) d on a.bjdm = d.bjdm");
		sql.append(" ) where 1=1 ");
		sql.append(searchTj);
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	public boolean checkDownLoadAuth(XsxxtyglForm myForm,User user){
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		sql.append("select count(1)num from view_xsjbxx t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append( "and xh=?");
		DAO dao = DAO.getInstance();
		return  Integer.parseInt(dao.getOneRs(sql.toString(), new String[]{myForm.getXh()}, "num"))>0;
		
	}
	
	
	public List<HashMap<String,String>> getXnXscj(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,decode(xf1,'0','',xf1)xfa, decode(xf2,'0','',xf2)xfb, decode(xf3,'0','',xf3)xfc, decode(xf4,'0','',xf4)xfd, decode(xf5,'0','',xf5)xfe from ( ");
		sql.append("select m.kcmc,a.cj cj101, b.cj cj102, c.cj cj201, d.cj cj202, e.cj cj301, f.cj cj302, g.cj cj401, h.cj cj402, i.cj cj501, j.cj cj502, ");
		sql.append("nvl(a.xf,'0')+nvl(b.xf,'0')xf1, nvl(c.xf,'0')+nvl(d.xf,'0')xf2, nvl(e.xf,'0')+nvl(f.xf,'0')xf3, nvl(g.xf,'0')+nvl(h.xf,'0')xf4, nvl(i.xf,'0')+nvl(j.xf,'0')xf5 from ");
		sql.append("(select kcmc,xh from (select xh,kcmc,rank() over(partition by xh,kcmc order by xn,xq) ct ");
		sql.append("from cjb a where xh = ? order by xn,xq) where ct = '1' ) m ");
		sql.append("left join (select '1' xn,xq,kcmc,xf,cj,xh from cjb b where exists (select 1 from view_xsbfxx a where a.nj=substr(b.xn,1,4) and a.xh=b.xh)) a on m.kcmc=a.kcmc and m.xh=a.xh and a.xq='01' ");
		sql.append("left join (select '1' xn,xq,kcmc,xf,cj,xh from cjb b where exists (select 1 from view_xsbfxx a where a.nj=substr(b.xn,1,4) and a.xh=b.xh)) b on m.kcmc=b.kcmc and m.xh=b.xh and b.xq='02' ");
		sql.append("left join (select '2' xn,xq,kcmc,xf,cj,xh from cjb b where exists (select 1 from view_xsbfxx a where a.nj+1=substr(b.xn,1,4) and a.xh=b.xh)) c on m.kcmc=c.kcmc and m.xh=c.xh and c.xq='01' ");
		sql.append("left join (select '2' xn,xq,kcmc,xf,cj,xh from cjb b where exists (select 1 from view_xsbfxx a where a.nj+1=substr(b.xn,1,4) and a.xh=b.xh)) d on m.kcmc=d.kcmc and m.xh=d.xh and d.xq='02' ");
		sql.append("left join (select '3' xn,xq,kcmc,xf,cj,xh from cjb b where exists (select 1 from view_xsbfxx a where a.nj+2=substr(b.xn,1,4) and a.xh=b.xh)) e on m.kcmc=e.kcmc and m.xh=e.xh and e.xq='01' ");
		sql.append("left join (select '3' xn,xq,kcmc,xf,cj,xh from cjb b where exists (select 1 from view_xsbfxx a where a.nj+2=substr(b.xn,1,4) and a.xh=b.xh)) f on m.kcmc=f.kcmc and m.xh=f.xh and f.xq='02' ");
		sql.append("left join (select '4' xn,xq,kcmc,xf,cj,xh from cjb b where exists (select 1 from view_xsbfxx a where a.nj+3=substr(b.xn,1,4) and a.xh=b.xh)) g on m.kcmc=g.kcmc and m.xh=g.xh and g.xq='01' ");
		sql.append("left join (select '4' xn,xq,kcmc,xf,cj,xh from cjb b where exists (select 1 from view_xsbfxx a where a.nj+3=substr(b.xn,1,4) and a.xh=b.xh)) h on m.kcmc=h.kcmc and m.xh=h.xh and h.xq='02' ");
		sql.append("left join (select '5' xn,xq,kcmc,xf,cj,xh from cjb b where exists (select 1 from view_xsbfxx a where a.nj+4=substr(b.xn,1,4) and a.xh=b.xh)) i on m.kcmc=i.kcmc and m.xh=i.xh and i.xq='01' ");
		sql.append("left join (select '5' xn,xq,kcmc,xf,cj,xh from cjb b where exists (select 1 from view_xsbfxx a where a.nj+4=substr(b.xn,1,4) and a.xh=b.xh)) j on m.kcmc=j.kcmc and m.xh=j.xh and j.xq='02' ");
		sql.append(" )t where rownum<=22 ");
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	
	public HashMap<String,String> getCxpd(String xh,String xn,String xq){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select max(py) py,nvl(max(cxdjmc),'    ') cxdjmc,max(tjsj) tjsj,nvl(max(substr(tjsj,1,4)||'��'||substr(tjsj,6,2)||'��'||substr(tjsj,9,2)||'��'),'     ��   ��   ��') tjrq "); 
		sql.append(" from XG_XSXX_CXPY_PYSB_JG b left join XSXX_CXDJDMB c on b.pj=c.cxdjdm ");
		sql.append("where exists (select 1 from view_xsbfxx a where a.nj+?-1=substr(b.xn,1,4) and a.xh=b.xh) ");
		sql.append("and xh=? and xq=? and rownum='1' ");
		return dao.getMapNotOut(sql.toString(), new String[]{xn,xh,xq});
	}
	//��ȡѧ�ƣ�����ҽҩ���Ի��ж�����ְ���Ǹ�ְ
	public String getXsxz(String xh){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select xz from xsxxb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "xz");
	}
	//��ȡ���͵�һ������ʱ������
	public List<HashMap<String, String>> getJcrqjyy(String xh,String n){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( select mc,xmrq from ( select t1.xh, t1.xmmc as mc, to_char(to_date(t1.sqsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyy-MM-dd') xmrq from xg_pjpy_new_pjjgb t1" +
				" left join xqdzb t2 on t1.xq=t2.xqdm union " +
				"select a.xh,(case when a.cfggw is not null then a.cfggw else a.cflbmc end) mc,a.cfsj as xmrq " +
				"from xg_wjcf_wjcfb a where nvl(ssjg,0) <> '��������' and nvl(ssjg,0) <> '��������'  ) where xh = ? order by xmrq desc)where rownum<= ?");
		return dao.getListNotOut(sql.toString(), new String[]{xh,n});
	}
/*	public List<HashMap<String, String>> tyView(XsxxtyglForm t, User user)
	throws Exception {
		
	// ���ɸ߼���ѯ�������������ֵ
	
	String searchTj = " and xh=?";
	String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
			"xydm", "bjdm");
	DAO dao = DAO.getInstance();
	String[] inputV = {t.getXh()};
	
	StringBuilder sql = new StringBuilder();
	
	//������ͼ
	sql.append("select * from xsxxb a where 1=1 ");
	
	sql.append(searchTj);
	sql.append(searchTjByUser);
	return dao.getListNotOut(sql.toString(), inputV);
	}*/
	public List<HashMap<String, String>> tyView(XsxxtyglForm model) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(model.getXh());
		sql.append("select a.*,b.mzmc ,c.zzmmmc from xsxxb a where xh = ? in (123,55,552)");
		sql.append(" left join MZDMB b on a.mz = b.mzdm ");
		sql.append(" left join ZZMMDMB c on a.zzmm = c.zzmmdm");
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	public String getYxyj(String xh){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select shgxgzdw1 from xsxxb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "shgxgzdw1");
	}
	public boolean setYxyj(XsxxglModel model) throws Exception{
		boolean flag = false;
		String sql;
		DAO dao = DAO.getInstance();
		sql = "update xsxxb set shgxgzdw1 = ? where xh = ?  ";
		flag = dao.runUpdate(sql, new String[] { model.getShgxgzdw1(),model.getXh() });
		

		return flag;
	}
	public List<String[]> getHjqkByXh(){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select zwmc from xg_szdw_xsgb_zwb ");
		return dao.rsToListNotOut(sql.toString(), new String[]{});
	}
}
