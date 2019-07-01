/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-26 ����09:32:22 
 */
package com.zfsoft.xgxt.xsxx.fbgl.xsxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����ѧ����Ϣ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-26 ����09:32:22
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglXsxxService extends
		SuperServiceImplExtend<FbglXsxxForm, FbglXsxxDao> {
	public static final String _XSXX_LXCX_ALL = "all";// ����ѧ��
	public static final String _XSXX_LXCX_YBXH = "ybxh";// �ѱ�ѧ��
	public static final String _XSXX_LXCX_WBXH = "wbxh";// δ��ѧ��
	FbglXsxxDao dao = new FbglXsxxDao();

	public FbglXsxxService() {
		this.setDao(dao);
	}

	@Override
	public FbglXsxxForm getModel(FbglXsxxForm t) throws Exception {
		return dao.getModel(t);
	}

	public boolean runUpdate(FbglXsxxForm t) throws Exception {
		//������������
		t.setPk(t.getNj() + "-" + t.getKsh());
		return dao.runUpdate(t);
	}
	public String getAllPk(){
		return getAllCols("pk",dao.getXsList());
	}
	/**
	 * 
	 * @����: ��������������ȡ��Ӧѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-14 ����04:02:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 *            ��b.nj||'-'||b.xydm||'-'||b.zydm||'-'||b.PYCC||'-'||b.xz��
	 * @param xsfw
	 *            ѧ����Χ
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getXsxx(String pk, String xsfw) {
		return dao.getXsxx(pk, xsfw);
	}

	public List<HashMap<String, String>> getXsxxForNjKsh(String pk, String xsfw) {
		return dao.getXsxxForNjKsh(pk, xsfw);
	}

	@Override
	public int runDelete(String value, User user) throws Exception {
		if (null != value && value.length() > 0) {
			return dao.rundelForFbglXsxx(value.split(","));
		}
		return super.runDelete(value, user);
	}

	/**
	 * 
	 * @����: ����pk����ɾ��ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-24 ����01:59:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pks
	 *            �������� ��ο���ͼ
	 * @return int ��������
	 */
	public int deleteXhForPk(String[] pks) {
		return dao.deleteXhForPk(pks);
	}
	
	

	/**
	 * 
	 * @����: ��������ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-24 ����01:59:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param njKsh
	 *            ���� �꼶+������
	 * @return int ��������
	 */
	public int updateXh(String[] njKsh) {
		return dao.updateXh(njKsh);
	}
	public int updateTjzt(String pks,String tjzt) throws Exception {
		return dao.updateTjzt(pks,tjzt);
	}
	/**
	 * 
	 * @����: ��ȡ�༶��ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-10 ����04:40:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return List<String> ��������
	 */
	public List<String> getXsForPk(String pk) {
		List<String> pks = new ArrayList<String>();
		List<HashMap<String, String>> list = dao.getXsForPk(pk);
		for (HashMap<String, String> hm : list) {
			pks.add(hm.get("nj") + FbglXsxxDao._NJ_KSH_FGF + hm.get("ksh"));
		}
		return pks;
	}
	/**
	 * 
	 * @����: ���ݰ༶�����ȡѧ����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-2 ����09:36:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm �༶����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getXsxxForBjdm(String bjdm){
		return dao.getXsxxForBjdm(bjdm);
	}
	/**
	 * 
	 * @����: �޸ķְ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-7 ����02:37:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return boolean ��������
	 */
	public boolean updateFb(List<HashMap<String, String>> list, String pzgzid) {
		boolean isok = true;
		try {
			FbglXsxxForm fxf = new FbglXsxxForm();
			for (HashMap<String, String> hm : list) {
				BeanUtils.copyProperties(fxf, hm);
				fxf.setFbgz(pzgzid);
				isok = isok && dao.runUpdateForFb(fxf) > 0;
			}
		} catch (Exception e) {
			throw new RuntimeException("����༶ʧ�ܣ�");
		}
		return isok;
	}

	/**
	 * \
	 * 
	 * @����: ���·ְ���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-10 ����03:42:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return boolean ��������
	 */
	public boolean runUpdateForFb(FbglXsxxForm t) {
		try {
			return dao.runUpdateForFb(t) > 0;
		} catch (Exception e) {
			throw new RuntimeException("���·ְ���Ϣʧ��!" + e.getMessage());
		}
	}

	/**
	 * 
	 * @����: ȡ���ְ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-10 ����01:59:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fxf
	 * @return int ��������
	 */
	public boolean qxFb(FbglXsxxForm fxf) {
		return dao.qxFb(fxf) > 0;
	}
}
