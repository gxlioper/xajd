/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-6 ����03:34:28 
 */
package com.zfsoft.xgxt.gygl.gypy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: (��Ԣ��Ʒ����)
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-8-6 ����03:34:28
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class GypyService extends SuperServiceImpl<GypyForm, GypyDAO> {
	private GypyDAO dao = new GypyDAO();
	/**
	 * ��������
	 */
	public static final String _WMSS = "1";
	/**
	 * ���㸨��Ա
	 */
	public static final String _YXFDY = "2";
	/**
	 * ����ѧ��
	 */
	public static final String _YXXS = "3";

	/**
	 * 
	 * @ϵͳ����: ��������
	 * @ģ������: XXXX����ģ��
	 * @�๦������: TODO(������һ�仰��������������)
	 * @���ߣ� �Ų�·[����:982]
	 * @ʱ�䣺 2013-8-21 ����07:53:19
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
	 */
	enum czdm {
		list, add, update, del, detail, export
	}

	public GypyService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:��ȡ�߼���ѯ��Ӧ����·��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-21 ����10:58:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String ��������
	 * @throws
	 */
	public String getGjcxPath(GypyForm gf) {
		String path = "gypywmss.do";
		if (GypyService._YXFDY.equals(gf.getPylx())) {
			path = "gypyyxfdy.do";
		}
		if (GypyService._YXXS.equals(gf.getPylx())) {
			path = "gypyyxxs.do";
		}
		return path;
	}

	/**
	 * 
	 * @����:���ݲ���������ȡҪ��ת�Ķ�Ӧ·��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-21 ����07:50:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pylx
	 * @param czdm
	 * @return String ��������
	 * @throws
	 */
	public String getForwordPath(GypyForm gf, int czdm) {
		// ����Ѿ��й������루����Ϊ�����㸨��Ա������
		if (StringUtils.isNotNull(gf.getGldm())) {
			return "yxfdy" + getCzdm(czdm);
		}
		String pylx = gf.getPylx();
		String path = "gypy" + getCzdm(czdm);
		if (GypyService._YXFDY.equals(pylx)) {
			path = "yxfdy" + getCzdm(czdm);

		}
		if (GypyService._YXXS.equals(pylx)) {
			path = "yxxs" + getCzdm(czdm);
		}
		return path;
	}

	private String getCzdm(int czdm) {
		String cz = "lb";
		switch (czdm) {
		case 1:
			cz = "lb";
			break;
		case 2:
			cz = "zj";
			break;
		case 3:
			cz = "xg";
			break;
		case 4:
			cz = "sc";
			break;
		case 5:
			cz = "ck";
			break;
		default:
			break;
		}
		return cz;
	}

	/**
	 * @throws Exception 
	 * 
	 * @����:�����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-20 ����04:12:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gf
	 *            void ��������
	 * @throws
	 */
	public boolean add(GypyForm gf) throws Exception {
			return dao.runInsert(gf);
	}

	/**
	 * @throws Exception 
	 * 
	 * @����:ɾ����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-20 ����04:14:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gf
	 *            void ��������
	 * @throws
	 */
	public int delete(String[] ids) throws Exception {
			return dao.runDelete(ids);
	}

	/**
	 * 
	 * @����:��ѯ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-20 ����04:14:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gf
	 *            void ��������
	 * @throws
	 */
	public HashMap<String, String> query(GypyForm gf) {
		HashMap<String, String> data = null;
		data = dao.getModelMapForId(gf.getGypyid());
		// ��ȡ��������
		String pylx = data.get("pylx");
		// ���ô����Ӧ����
		dao.setXyXqLdForDm(data);
		// ����Ǹ���Ա�������ظ���Ա��Ϣ
		if (GypyService._YXFDY.equals(pylx)) {
			dao.setFdyxx(data);
		}
		if (GypyService._YXXS.equals(pylx)) {
			data.putAll(dao.getXsxx(data.get("gldm")));
		} else {
			// ������ȡ����������Ϣ
			data.putAll(dao.getQsxx(data.get("lddm"), data.get("ch"), data
					.get("qsh")));
		}
		gf.setPylx(pylx);
		return data;
	}

	/**
	 * @throws Exception
	 * 
	 * @����: �޸���������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-20 ����04:14:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gf
	 *            void ��������
	 * @throws
	 */
	public boolean update(GypyForm gf) throws Exception {
		return dao.runUpdate(gf);
	}

	public List<HashMap<String, String>> exportData(GypyForm gf)
			throws Exception {
		return dao.getData(gf, false);
	}

	public Map<String, String> getMaxQsh(String lddm, String ch) {
		return dao.getMaxQsh(lddm, ch);
	}

	/**
	 * 
	 * @����:��ȡ����������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-21 ����02:56:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @param ch
	 * @param qsh
	 * @return Map<String,String> ��������
	 * @throws
	 */
	public Map<String, String> getQsxx(String lddm, String ch, String qsh) {
		return dao.getQsxx(lddm, ch, qsh);
	}

	public Map<String, String> getXsxx(String xh) {
		return dao.getXsxx(xh);
	}

	/**
	 * @throws Exception
	 * 
	 * @����:�������Ψһ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-23 ����09:06:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gf
	 * @return boolean ��������
	 * @throws
	 */
	public boolean checkSoleData(GypyForm gf) throws Exception {
		Map<String, String> map = null;
		List<HashMap<String, String>> data = null;
		// ���������������֤
		if (StringUtils.isNull(gf.getGypyid())) {
			// �������ɲ�����֤
			gf.setPyly(null);
			data = dao.getDataForBeFindEntity(gf);
		}
		if (null != data && data.size() > 0) {
			map = data.get(0);
			// �������������ͬһ��Ⱥ�ͬһѧ�ڵ����� ���������
			if (gf.getXn().equals(map.get("xn"))
					&& gf.getXqdm().equals(map.get("xqdm"))) {
				return false;
			}
		}
		return true;
	}
}
