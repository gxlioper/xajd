package xgxt.pjpy.comm.pjpy;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.form.RequestForm;
import xgxt.pjpy.comm.pjpy.model.PjpyStuModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.pjlc.jgcx.PjpyJgcxDAO;
import xgxt.pjpy.comm.pjpy.pjlc.jgcx.PjpyJgcxForm;
import xgxt.pjpy.comm.pjpy.xmsz.PjpyXmszDAO;
import xgxt.pjpy.comm.zhcp.zczf.ZhcpZczfService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͨ��-service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyCommService extends CommService {

	// �б����
	public List<HashMap<String, String>> getSelectList(String type) {
		DAO dao = DAO.getInstance();
		String[] dm = null;
		String[] mc = null;
		// �Ƿ�����
		if ("sfqy".equalsIgnoreCase(type)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		}
		//
		if ("xmlx".equalsIgnoreCase(type)) {
			dm = new String[] { "01", "02" };
			mc = new String[] { "��ѧ��", "�����ƺ�" };
		}

		if ("shzt".equalsIgnoreCase(type)) {
			dm = new String[] { "δ���", "ͨ��", "��ͨ��", "�˻�", "������" };
			mc = dm;
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void setPjpyOptionList(PjpyCommForm pjpyCommForm, RequestForm rForm,
			HttpServletRequest request) throws Exception {
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, pjpyCommForm);
		setList(model, rForm, request);
	}

	/**
	 * ��ȡ��Ŀ�����б�
	 * 
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmxzList() {
		PjpyXmszDAO dao = new PjpyXmszDAO();
		return dao.getXmxzList();
	}

	/**
	 * ��ȡ��Ŀ��Χ�б�
	 * 
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmfwList() {
		PjpyXmszDAO dao = new PjpyXmszDAO();
		return dao.getXmfwList();
	}

	/**
	 * ͨ����Ŀ��������Ŀ����
	 * 
	 * @param pjxn||pjxq||pjnd||xmdm
	 * @return PjpyXmszModel(��Ŀ����model)
	 * @author sjf
	 */
	public PjpyXmszModel getXmszForXmdm(String pkValue) {
		DAO dao = DAO.getInstance();
		String sql = "select * from xg_pjpy_pjxmwhb where pjxn||pjxq||pjnd||xmdm=?";

		// ������Ŀ�����ȡ��Ŀ����
		Map<String, String> xmszMap = dao.getMapNotOut(sql,
				new String[] { pkValue });

		return copyBean(xmszMap);
	}

	/**
	 * ��õ�ǰָ��������������������Ŀ
	 * 
	 * @param PjxtszModel
	 * @return List<HashMap<String, String>> key:xmdm key:xmmc
	 */
	public List<HashMap<String, String>> getPjxmList(PjxtszModel model) {

		String sql = "select a.xmdm,a.xmmc,a.xmlx from xg_pjpy_pjxmwhb a where sfqy='��'";
		String[] input = new String[] {};

		if (null != model) {
			HashMap<String, Object> map = getQueryPjxmSQL(model);
			sql += (String) map.get("sql");
			input = (String[]) map.get("input");
		}

		return DAO.getInstance().getListNotOut(sql, input);
	}

	/**
	 * ���ݻ�ȡ���ڻ�ȡ��Ŀ�б�SQL
	 * 
	 * @param model
	 * @return
	 */
	private HashMap<String, Object> getQueryPjxmSQL(PjxtszModel model) {
		StringBuilder query = new StringBuilder();
		List<String> input = new ArrayList<String>();

		if (StringUtils.isNotNull(model.getPjxn())) {
			query.append(" and pjxn=?");
			input.add(model.getPjxn());
		}

		if (StringUtils.isNotNull(model.getPjnd())) {
			query.append(" and pjnd=?");
			input.add(model.getPjnd());
		}

		if (StringUtils.isNotNull(model.getPjxq())) {
			query.append(" and pjxq=?");
			input.add(model.getPjxq());
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("sql", query.toString());
		result.put("input", input.toArray(new String[] {}));
		return result;
	}

	/**
	 * ��ѯ��Ϊǰ����������Ŀ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getQztjXmList(PjxtszModel model) {
		String sql = "select xmdm,xmmc from xg_pjpy_pjxmwhb where qztj='��' and sfqy='��'";
		String[] input = new String[] {};

		if (null != model) {
			HashMap<String, Object> map = getQueryPjxmSQL(model);
			sql += (String) map.get("sql");
			input = (String[]) map.get("input");
		}

		return DAO.getInstance().getList(sql, input,
				new String[] { "xmdm", "xmmc" });
	}

	/**
	 * ��ȡָ�����õ�����������Ŀ
	 * 
	 * @param model
	 *            ָ����Ŀ������
	 * @param colList
	 *            ��ȡȥ��Ŀ����Ϣ
	 * @author sjf
	 */
	public List<HashMap<String, String>> getPjxm(Map<String, String> map,
			String[] colList) {
		StringBuilder query = new StringBuilder(" where 1=1 ");
		String tableName = "xg_pjpy_pjxmwhb";

		List<String> inputList = new ArrayList<String>();

		// ƴдwhere����
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (StringUtils.isNotNull(entry.getValue())) {
				query.append(" and ").append(entry.getKey()).append("=?");
				inputList.add(entry.getValue());
			}
		}

		return CommonQueryDAO.commonQueryforList(tableName, query.toString(),
				(String[]) inputList.toArray(new String[] {}), colList, "");
	}

	/**
	 * ��ȡָ�����õ�����������Ŀ
	 * 
	 * @param map
	 *            ָ����Ŀ������
	 * @author sjf
	 */
	public List<PjpyXmszModel> getPjxm(Map<String, String> map) {
		String[] output = DAO.getInstance().getColumnName(
				"select * from xg_pjpy_pjxmwhb where 1=2");
		List<PjpyXmszModel> list = new ArrayList<PjpyXmszModel>();

		List<HashMap<String, String>> rs = getPjxm(map, output);

		for (Map<String, String> xmszMap : rs) {
			list.add(copyBean(xmszMap));
		}

		return list;
	}

	private PjpyXmszModel copyBean(Map<String, String> xmszMap) {
		Class<PjpyXmszModel> clazz = PjpyXmszModel.class;
		PjpyXmszModel model = new PjpyXmszModel();
		DAO dao = DAO.getInstance();

		String lcsql = "select a.id gwid,a.mc shlc from xg_xtwh_spgw a,xg_xtwh_spbz b where a.id = b.spgw and "
				+ "splc=? order by b.xh";

		// ������Ŀ�����ȡ��Ŀ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(xmszMap);

		// ��ȡ�������
		map.put("shlc", dao.getList(lcsql,
				new String[] { xmszMap.get("lcid") }, new String[] { "gwid",
						"shlc" }));

		// ͨ���������Ŀ����model��ֵ
		Method[] methods = clazz.getDeclaredMethods();

		for (int i = 0; i < methods.length; i++) {
			String methodName = methods[i].getName();
			String field = String.valueOf(methodName.charAt(3)).toLowerCase()
					+ methodName.substring(4);

			if (methodName.substring(0, 3).equalsIgnoreCase("set")) {
				try {
					methods[i].invoke(model, map.get(field));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return model;
	}

	/**
	 * ��ȡ������Χ
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getTjfw(PjxtszModel model) {

		String sql = "select xydm,xymc from xg_pjpy_xyb where 1=1 ";
		String[] input = new String[] {};

		if (null != model) {
			HashMap<String, Object> map = getQueryPjxmSQL(model);
			sql += (String) map.get("sql");
			input = (String[]) map.get("input");
		}

		return DAO.getInstance().getList(sql, input,
				new String[] { "xydm", "xymc" });
	}

	/**
	 * ������Ŀ�����ѯ������˸����������� DWR����
	 * 
	 * @param xmdm
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getLcmcByXmdm(String xmdm,
			String[] lcmcValue) throws SQLException {
		PjpyJgcxForm model = new PjpyJgcxForm();
		PjpyJgcxDAO dao = new PjpyJgcxDAO();

		model.setXmdm(xmdm);
		String[] lcmc = dao.getLcmcByXmdm(model);
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		if (null != lcmc && lcmc.length > 0) {
			for (int i = 0; i < lcmc.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("key", lcmc[i]);
				map.put("value", i < lcmcValue.length ? lcmcValue[i].trim()
						: "");
				result.add(map);
			}
		}

		return result;
	}

	/**
	 * ������������Ķ���
	 * 
	 * @param xh
	 * @return
	 */
	public PjpyStuModel getStuForPj(String xh, PjpyXmszModel xmszModel) {
		PjpyStuModel model = new PjpyStuModel();
		String pjzq = xmszModel.getSqzq();

		model.setJbxx(getPjXsxx(xh));
		System.out.println("ѧ����Ϣ��ѯ:......"+model.getJbxx().isEmpty());

		// �ɼ���Ϣ
		model.setCjxx(getXscjxx(xh, pjzq));
		// �۲���Ϣ
		model.setZcxx(new ZhcpZczfService().getZcfInfoList(xh));
		// �ȼ�����
		model.setDjks(CommonQueryDAO.commonQueryforList("xsdjksb",
				" where xh=?", new String[] { xh }, new String[] { "djksmc",
						"cj" }, ""));

		return model;
	}

	/**
	 * ����۲���Ϣ
	 * 
	 * @param xh
	 * @return
	 */
	public Map<String, String> getZcxx(String xh) {
		StringBuilder sql = new StringBuilder(
				"select * from xg_pjpy_zhcpcjb where xh=? and xn=? and xq=? and nd=?");
		String[] input = new String[] { xh, PjxtszModel.pjxtszModel.getPjxn(),
				PjxtszModel.pjxtszModel.getPjxq(),
				PjxtszModel.pjxtszModel.getPjnd() };

		DAO dao = DAO.getInstance();
		return dao.getMapNotOut(sql.toString(), input);
	}

	/**
	 * ���ѧ���ɼ���Ϣ
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getXscjxx(String xh, String pjzq) {
		StringBuilder sql = new StringBuilder(
				"select a.*,(select xqmc from xqdzb where xqdm=a.xq) xqmc from cjb a where 1=1 and xh=?");
		List<String> inputList = new ArrayList<String>();
		String[] output = null;
		inputList.add(xh);

		if ("xn".equalsIgnoreCase(pjzq)) {
			sql.append(" and xn=?");
			output = new String[] { "xn", "xqmc", "kcmc", "cj", "kcxz", "xf",
					"jd" };
			inputList.add(PjxtszModel.pjxtszModel.getPjxn());
		} else if ("xq".equalsIgnoreCase(pjzq)) {
			sql.append(" and xn=? and xq=?");

			output = new String[] { "xn", "xqmc", "kcmc", "cj", "kcxz", "xf",
					"jd" };
			inputList.add(PjxtszModel.pjxtszModel.getPjxn());
			inputList.add(PjxtszModel.pjxtszModel.getPjxq());
		} else {
			sql.append(" and nd=?");

			output = new String[] { "nd", "kcmc", "cj", "kcxz", "xf", "jd" };
			inputList.add(PjxtszModel.pjxtszModel.getPjnd());
		}

		sql.append(" order by xn,xq,nd");

		DAO dao = DAO.getInstance();
		// �ɼ���Ϣ
		return dao.getList(sql.toString(), (String[]) inputList
				.toArray(new String[] {}), output);
	}

	/**
	 * ��ȡ������Աѧ����Ϣ
	 * 
	 * @param xh
	 * @return
	 */
	public Map<String, String> getPjXsxx(String xh) {
		System.out.println("��Ҫ��ѯ��ѧ����....."+xh);
		String sql = "select * from view_xg_pjpy_ryqd where xh=?";
		return DAO.getInstance().getMapNotOut(sql, new String[] { xh });
	}

	/**
	 * ��ò����б�
	 */
	public List<HashMap<String, String>> getBmList(String[] bmInfo,
			String userStatus, String userName, String userDep) {

		String dm = "";
		String mc = "";
		String sjdm = "";

		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();

		String bmlx = bmInfo[0];
		String nj = bmInfo[1];
		String bmdm = bmInfo[2];

		if ("nj".equalsIgnoreCase(bmlx)) {
			dm = bmlx;
			mc = bmlx;
			sjdm = "nj";
		} else if ("xy".equalsIgnoreCase(bmlx)) {
			dm = bmlx + "dm";
			mc = bmlx + "mc";
			sjdm = "nj";
		} else if ("zy".equalsIgnoreCase(bmlx)) {
			dm = "nj||" + bmlx + "dm";
			mc = bmlx + "mc";
			sjdm = "xydm";
		} else if ("bj".equalsIgnoreCase(bmlx)) {
			dm = bmlx + "dm";
			mc = bmlx + "mc";
			sjdm = "zydm";
		}

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct ");
		sql.append(dm);
		sql.append(" dm, ");
		sql.append("case when length(" + mc + ") > 6 then substr(" + mc
				+ ", 0, 6) || '...' else  to_char(" + mc + ") end ");
		sql.append(" mc, ");
		sql.append(mc);
		sql.append(" allmc, ");
		sql.append(Base.isNull(bmdm) ? "''" : sjdm);
		sql.append(" sjdm, ");
		sql.append("'" + bmlx + "' ");
		sql.append(" bmlx,");
		sql.append(Base.isNull(bmdm) ? "'' nj " : "nj ");
		sql.append(" from xg_view_pjpy_njxyzybj ");
		sql.append(" where 1 = 1 ");
		sql.append(" and pjxn = '");
		sql.append(pjxn);
		sql.append("' and pjxq = '");
		sql.append(pjxq);
		sql.append("' and pjnd = '");
		sql.append(pjnd);
		sql.append("'");
		sql.append(Base.isNull(nj) ? "" : " and nj = '" + nj + "'");
		sql.append(Base.isNull(bmdm) ? "" : " and " + sjdm + " = '" + bmdm
				+ "'");
		sql.append(" order by dm,mc ");

		// ���ֵ
		String[] colList = new String[] { "dm", "mc", "allmc", "sjdm", "bmlx",
				"nj" };

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, colList);

		return list;
	}

	/**
	 * �����Ŀ������Ϣ
	 */
	public HashMap<String, String> getXmjbInfo(PjpyJgcxForm model) {

		PjpyCommDAO dao = new PjpyCommDAO();

		HashMap<String, String> map = dao.getXmjbInfo(model);

		return map;
	}

	/**
	 * �����Ŀ�����Ϣ
	 */
	public HashMap<String, String> getXmshInfo(PjpyJgcxForm model) {

		PjpyCommDAO dao = new PjpyCommDAO();

		List<HashMap<String, String>> list = dao.getXmjbInfoList(model);

		HashMap<String, String> map = new HashMap<String, String>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> info = list.get(i);

				String shyj = info.get("shyj");
				shyj = Base.isNull(shyj) ? "" : shyj;
				map.put("shyj" + (i + 1), info.get("shyj"));
			}
		}

		return map;
	}

	/**
	 * ����������ϸҳ��-�����ֶ���Ϣ
	 * 
	 * @param xmmc
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getOtherInfo(String xmmc, String xh)
			throws Exception {
		// ���õ������ֶ�
		List<HashMap<String, String>> zdxxList = getZdxxList(xmmc);

		for (HashMap<String, String> map : zdxxList) {
			String lylx = map.get("zdlylx");
			String zdz = "";

			if ("method".equalsIgnoreCase(lylx)) {
				Class c = PjpyOtherInfo.class;
				// ����������õķ���
				zdz = (String) c.getMethod(map.get("zdly"),
						new Class[] { String.class }).invoke(
						new PjpyOtherInfo(), new String[] { xh });
			} else if ("view".equalsIgnoreCase(lylx)) {
				// ������ͼ
				zdz = getPjpyQtxx(map.get("zdly"), map.get("zdm"), xmmc, xh);
			}
			map.put("zdz", zdz);
		}

		return zdxxList;
	}

	/**
	 * ��ѯ���������ֶ�
	 * 
	 * @param xmmc
	 * @return
	 */
	private List<HashMap<String, String>> getZdxxList(String xmmc) {
		DAO dao = DAO.getInstance();
		String sql = "select zdmc,zdm,zdly,zdlylx,xxlx,xssx from xg_pjpy_qtxxb where xxdm=? and xmmc=? order by xssx";

		return dao.getListNotOut(sql, new String[] { Base.xxdm, xmmc });
	}

	/**
	 * �������õ���ͼ�ֶβ�ѯ
	 * 
	 * @param viewName
	 * @param zdm
	 * @param xh
	 * @return
	 */
	private String getPjpyQtxx(String viewName, String zdm, String xmmc,
			String xh) {
		DAO dao = DAO.getInstance();
		String[] kzzd = new String[] { "kzzd1", "kzzd2", "kzzd3", "kzzd4",
				"kzzd5","kzzd6","kzzd7" };
		String zdz = "";
		StringBuilder sql = new StringBuilder();

		if (StringUtils.getStrIndexInArray(zdm, kzzd) > -1) {
			// ������չ�ֶ�
			sql
					.append("select ")
					.append(zdm)
					.append(" from ")
					.append(viewName)
					.append(" a where xh=? and exists ")
					.append(
							"(select 1 from xg_pjpy_pjxmwhb b where b.xmmc=? and a.xmdm=b.xmdm)");
			zdz = dao.getOneRs(sql.toString(), new String[] { xh, xmmc }, zdm);

			if (StringUtils.isNull(zdz)) {
				// ����Ŀ��չ�ֶ�ֵΪ�գ�����������Ŀ�������
				sql = new StringBuilder();
				sql.append("select ").append(zdm).append(" from ").append(
						viewName).append(" where xh=? and ").append(zdm)
						.append(" is not null and rownum=1");
				zdz = dao.getOneRs(sql.toString(), new String[] { xh }, zdm);
			}
		} else {
			// ��ʱ��û�������ó�
			sql.append("select ").append(zdm).append(" from ").append(viewName)
					.append(" where xh=? ");

			zdz = dao.getOneRs(sql.toString(), new String[] { xh }, zdm);
		}

		return zdz;
	}

	/**
	 * ��ȡѧ���ɼ���Ϣ(����ѧ��ѧ��,ѧ��)
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public HashMap<String, Object> getXscjList(PjpyJgcxForm model,
			HashMap<String, String> map) {

		PjpyCommDAO dao = new PjpyCommDAO();
		// ��ȡѧ������ѧ��ɼ���Ϣ
		List<HashMap<String, String>> xscjList = dao.getXscjList(model, map);
		// ��һѧ�ڳɼ�
		List<HashMap<String, String>> dyxqcjList = new ArrayList<HashMap<String, String>>();
		// �ڶ�ѧ�ڳɼ�
		List<HashMap<String, String>> dexqcjList = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < xscjList.size(); i++) {
			// ��һѧ�ڳɼ�
			HashMap<String, String> xscjMap = xscjList.get(i);
			if ("1".equalsIgnoreCase(xscjMap.get("xqjb"))) {
				dyxqcjList.add(xscjMap);
				continue;
			}

			// �ڶ�ѧ�ڳɼ�
			if ("2".equalsIgnoreCase(xscjMap.get("xqjb"))) {
				dexqcjList.add(xscjMap);
				continue;
			}
		}

		// �����л�ȥ��������
		return setXsXncj(dyxqcjList, dexqcjList);
	}

	/**
	 * ��ȡѧ��ѧ��ɼ���Ϣ
	 * 
	 * @param dyxqcjList
	 * @param dexqcjList
	 * @return
	 */
	public HashMap<String, Object> setXsXncj(
			List<HashMap<String, String>> dyxqcjList,
			List<HashMap<String, String>> dexqcjList) {

		HashMap<String, Object> xncjMap = new HashMap<String, Object>();
		if (dyxqcjList.size() > 15) {
			// ȥ������12�к�Ķ�����
			for (int i = 15; i < dyxqcjList.size(); i++) {
				dyxqcjList.remove(i);
			}
		} else if (dyxqcjList.size() < 15) {
			// ��ȫ12��
			for (int i = dyxqcjList.size(); i < 15; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("kcmc", "");
				map.put("cj", "");
				dyxqcjList.add(map);
			}
		}

		if (dexqcjList.size() > 15) {
			// ȥ������12�к�Ķ�����
			for (int i = 15; i < dexqcjList.size(); i++) {
				dexqcjList.remove(i);
			}
		} else if (dexqcjList.size() < 15) {
			// ��ȫ12��
			for (int i = dexqcjList.size(); i < 15; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("kcmc", "");
				map.put("cj", "");
				dexqcjList.add(map);
			}
		}

		xncjMap.put("dyxqcj", dyxqcjList);
		xncjMap.put("dexqcj", dexqcjList);
		return xncjMap;
	}

	/**
	 * ��ȡ�༶����
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public String getBjrs(PjpyJgcxForm model, HashMap<String, String> map) {
		return PjpyCommDAO.getBjrs(map.get("xh"));
	}

	/**
	 * ������Ŀ��
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public String getBkks(PjpyJgcxForm model, HashMap<String, String> map) {

		PjpyCommDAO dao = new PjpyCommDAO();
		String bkks = dao.getBkks(model, map);
		if (Base.isNull(bkks)) {
			bkks = "��";
		}
		return bkks;

	}

	
	
	
	/**
	 * δȡ��ѧ�ֵĿ�Ŀ��
	 * @param xh
	 * @return
	 */
	public String getWdxfkms(String xh){
		PjpyCommDAO dao = new PjpyCommDAO();
		String kms = dao.getWdxfkms(xh);
		if (StringUtils.isNull(kms)){
			kms = "0";
		}
		return kms;
	}
	
	
	/**
	 * ��������ѧ��ƽ���ּ�ƽ��������
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public HashMap<String, String> getPjfInfo(PjpyJgcxForm model,
			HashMap<String, String> map) {

		PjpyCommDAO dao = new PjpyCommDAO();
		return dao.getPjfInfo(model,map);
	}
	
	/**
	 * ��ȡ�Ƿ�Υ����Ϣ
	 * @param model
	 * @param map
	 * @return
	 */
	public String getSfwj(PjpyJgcxForm model,
			HashMap<String, String> map){
		PjpyCommDAO dao=new PjpyCommDAO();
		return dao.getSfwj(model, map);
	}
	
	/**
	 * ��ȡ�۲��ܷ�,�༶����
	 * @param model
	 * @param map
	 * @return
	 */
	public HashMap<String,String>getZczfBjpm(PjpyJgcxForm model, HashMap<String, String> map){

		PjpyCommDAO dao=new PjpyCommDAO();
		return dao.getZczfBjpm(model, map);
	}
	
	/**
	 * ��ȡ�༶�ɲ���Ϣ
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public String getBjgbInfo(PjpyJgcxForm model, HashMap<String, String> map) {

		PjpyCommDAO dao = new PjpyCommDAO();
		return dao.getBjgbInfo(model, map);
	}
	
	/**
	 * ��ȡ�༶����(������Ա)
	 */
	public String getBjrsByPjry(PjpyJgcxForm model, HashMap<String, String> map){
	
		PjpyCommDAO dao = new PjpyCommDAO();
		return dao.getBjrsByPjry(model, map);
	}
	
	/**
	 * ��ȡ����������������Ϣ
	 * @param model
	 * @param map
	 * @return
	 */
	public HashMap<String,String>getPjpyShyj(PjpyJgcxForm model, HashMap<String, String> map){
		PjpyCommDAO dao=new PjpyCommDAO();
		HashMap<String,String>shyjMap=new HashMap<String,String>();
		List<HashMap<String,String>>pjpyShyjList=dao.getPjpyShyj(model, map);
		for(int i=0;i<pjpyShyjList.size();i++){
			HashMap<String,String>pjpyShyjMap=pjpyShyjList.get(i);
			if(i==0){
				shyjMap.put("xxyj", pjpyShyjMap.get("shyj"));
				shyjMap.put("xxshr", pjpyShyjMap.get("xm"));
				shyjMap.put("xxnian", pjpyShyjMap.get("nian"));
				shyjMap.put("xxyue", pjpyShyjMap.get("yue"));
				shyjMap.put("xxri", pjpyShyjMap.get("ri"));
			}else if(i==1){
				shyjMap.put("xyyj", pjpyShyjMap.get("shyj"));
				shyjMap.put("xyshr", pjpyShyjMap.get("xm"));
				shyjMap.put("xynian", pjpyShyjMap.get("nian"));
				shyjMap.put("xyyue", pjpyShyjMap.get("yue"));
				shyjMap.put("xyri", pjpyShyjMap.get("ri"));
			}else {
				break;
			}
		}
		
		return shyjMap;
	}
	
	/**
	 * ���ݴ�ѧƽ���ɼ�����Ȩƽ����/��Ȩƽ�����㣩
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public HashMap<String,String> getPjcj(PjpyJgcxForm model, HashMap<String, String> map) {
		PjpyCommDAO dao=new PjpyCommDAO();
		return dao.getPjcj(model, map);
	}
	
	/**
	 * ��ȡ������Ϣ
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public List<HashMap<String,String>> getPjInfo(PjpyJgcxForm model, HashMap<String, String> map) {
		PjpyCommDAO dao=new PjpyCommDAO();
		return dao.getPjInfo(model, map);
	}
	
	/**
	 * ��ȡѧУ�����������ݴ�ѧһ������������̣�
	 * @param model
	 * @param map
	 * @return
	 */
	public HashMap<String, String> getShxx(PjpyJgcxForm model,
			HashMap<String, String> map) {
		PjpyCommDAO dao=new PjpyCommDAO();
		return dao.getShxx(model, map);
	}
	/**
	 * �����������
	 * @param model
	 * @param map
	 * @return
	 */
	public String getSqly(PjpyJgcxForm model, HashMap<String, String> map) {
		PjpyCommDAO dao=new PjpyCommDAO();
		String sqly ="";
		
		try{
		
			sqly=dao.getPjpyInfo(model, map).get("sqly");
		
		}catch(Exception ex){
			
			return null;
		}
		
		return sqly;
	}

	public String getHzsfSqly(PjxtszModel jbszModel, String xmmc, String xh) {
		PjpyCommDAO dao=new PjpyCommDAO();
		String sqly ="";
		sqly=dao.getHzsfSqly(jbszModel,xmmc,xh).get("sqly");
		return sqly;
	}
}
