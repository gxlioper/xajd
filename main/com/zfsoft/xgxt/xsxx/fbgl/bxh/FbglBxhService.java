/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-10 ����05:21:59 
 */
package com.zfsoft.xgxt.xsxx.fbgl.bxh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.extend.IDelete;
import com.zfsoft.xgxt.xsxx.fbgl.Config;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglForm;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglService;
import com.zfsoft.xgxt.xsxx.fbgl.comm.BarSorce;
import com.zfsoft.xgxt.xsxx.fbgl.comm.ProgressBar;
import com.zfsoft.xgxt.xsxx.fbgl.generate.BaseCreate;
import com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate;
import com.zfsoft.xgxt.xsxx.fbgl.generate.imp.ContinueCreateXh;
import com.zfsoft.xgxt.xsxx.fbgl.generate.imp.CreateXh;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjServer;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxServer;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxDao;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-10 ����05:21:59
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglBxhService extends FbglBbglService {
	/**
	 * �ѱ�ѧ��
	 */
	public static final String _BXH_YBXH = "ybxh";
	/**
	 * δ��ѧ��
	 */
	public static final String _BXH_WBXH = "wbxh";

	/**
	 * 
	 * @����: ��ȡ��ѧ���б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-8 ����04:20:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fbf
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPageListForBxh(FbglBbglForm fbf,
			User user) throws Exception {
		// �ѱ�ѧ���б�
		if (_BXH_YBXH.equals(fbf.getXhzt())) {
			return dao.getPageListForYbxh(fbf, user);
		}
		return dao.getPageListForBxh(fbf, user);
	}

	/**
	 * 
	 * @����: ��ȡ����������ϸ�б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-8 ����04:20:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getTjnrList() {
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		return fgts.getTjnrList();
	}

	/**
	 * 
	 * @����: ��ȡ����ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-14 ����04:04:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return
	 */
	public int getXszs(String pk) {
		FbglXsxxService fxs = new FbglXsxxService();
		return fxs.getXsxx(pk, FbglXsxxService._XSXX_LXCX_ALL).size();
	}

	/**
	 * 
	 * @����: �ѱ��ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-14 ����04:27:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return int ��������
	 */
	public int getYbxhXs(String pk) {
		FbglXsxxService fxs = new FbglXsxxService();
		return fxs.getXsxx(pk, FbglXsxxService._XSXX_LXCX_YBXH).size();
	}

	public Map<String, Object> deleteAllXh() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		FbglXsxxDao fxd = new FbglXsxxDao();
		map.put(IDelete._CGTS, String.valueOf(fxd.deleteAllXh()));
		map.put(IDelete._ERROE_OBJ, IDelete._UNHAVE_ERROE);
		return map;
	}

	/**
	 * 
	 * @����: ��ȡ�ѱ�ѧ��ѧ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-8 ����04:19:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pks
	 * @return int ��������
	 */
	public int getYbxhForxs(String[] pks) {
		try {
			int i = 0;
			for (String pk : pks) {
				FbglXsxxService fxs = new FbglXsxxService();
				// ���ѧ�Ų�Ϊ��
				if (StringUtils.isNotNull(fxs.getModel(pk).getXh())) {
					i++;
				}
			}
			return i;
		} catch (Exception e) {
			throw new RuntimeException("��ȡѧ����Ϣʧ�ܣ�");
		}
	}

	/**
	 * 
	 * @����: δ���ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-14 ����04:27:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return int ��������
	 * @throws
	 */
	public int getWbxhXs(String pk) {
		FbglXsxxService fxs = new FbglXsxxService();
		return fxs.getXsxx(pk, FbglXsxxService._XSXX_LXCX_WBXH).size();
	}

	/**
	 * 
	 * @����: ����ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-8 ����04:18:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @param pzgzid
	 * @param barKey
	 * @param bxhzt
	 * @return boolean ��������
	 */
	public String scxh(String pk, String pzgzid, String barKey, String bxhzt) {
		// �ѱ�ѧ������ѧ��
		if (_BXH_YBXH.equals(bxhzt)) {
			return scxh(pk, pzgzid, barKey, true);
		}
		return scxh(pk, pzgzid, barKey, false);
	}

	/**
	 * 
	 * @����: ����ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-24 ����10:53:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @param pzgzid
	 * @param barKey
	 *            ������Ψһkey
	 * @param isContinue
	 *            �Ƿ�Ϊ�����ְࣨѡ�о���ѧ�����ְࣩ
	 * @return boolean ��������
	 */
	public String scxh(String pk, String pzgzid, String barKey,
			boolean isContinue) {
		boolean isok = true;
		// ��ǰѡ��Ĺ�����Ϣ
		FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
		// ����������
		BaseCreate bc = new CreateXh();
		if (isContinue) {
			bc = new ContinueCreateXh();
		}
		bc.addParam(pk);
		//�������ù���id
		bc.putMapParam("pzgzid",pzgzid);
		// ������ѧ����Ϣ
		List<HashMap<String, String>> tjPzxx = bc.generate(pzgzid, null);
		// ������
		ProgressBar pb = BarSorce.initProgressBar(barKey, tjPzxx.size());
		if (null == tjPzxx || tjPzxx.size() <= 0) {
			BarSorce.cleanBar(barKey);
			return "true";
		}
		FbglXsxxForm fxf = new FbglXsxxForm();
		String tjgzid = Config._TJGZID_XH;
		String code = null;
		FbglXsxxService fxs = new FbglXsxxService();
		// ��ȡ���������Ϣ
		List<HashMap<String, String>> list = fgtxs.getGzpzTjxxForLx(
				tjgzid, pzgzid, false);
		FbglGzpzTjXxForm fgtxf = new FbglGzpzTjXxForm();
		FbglTjgzForm ftf = new FbglTjgzForm();
		// ���ɹ���
		IGenerate gg = (IGenerate) bc;
		String name ;
		try {
			// ���ɶ�Ӧ����
			for (HashMap<String, String> xsxx : tjPzxx) {
				if(null==xsxx.get("bjdm")||"".equals(xsxx.get("bjdm"))){
					pb.change();
					continue;
				}
				code="";
				BeanUtils.copyProperties(fxf, xsxx);
				// ���ö�Ӧ����������Ϣ�͹���������ϸ��Ϣ
				for (HashMap<String, String> hm : list) {
					BeanUtils.copyProperties(fgtxf, hm);
					BeanUtils.copyProperties(ftf, hm);
					// ���ɶ�Ӧ����� code
					code += gg.getCode(ftf, fgtxf, fxf);
				}
				// ��ȡҪ�趨������
				name = tjgzid.split(_TJGZSD_FGF)[1];
				fxf = (FbglXsxxForm) setCodeValue(fxf, name, code);
				// }
				fxf.setBxhgz(pzgzid);
				// ��������޸ĸ������
				if(null!=gg.getErrorMessage()){
					return gg.getErrorMessage();
					
				}
				isok = isok && fxs.runUpdate(fxf);
				pb.change();
			}
		} catch (Exception e) {
			throw new RuntimeException("��ȡ��Ӧ������Ϣʧ��!" + e.getMessage());
		}
		return String.valueOf(isok);
	}

	/**
	 * 
	 * @����: ɾ��ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-24 ����02:00:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param type
	 * @return
	 * @throws Exception
	 *             Map<String,Object> ��������
	 */
	public Map<String, Object> deleteXh(String[] ids, String type)
			throws Exception {
		// �ѱ�ѧ��
		if (_BXH_YBXH.equals(type)) {
			return deleteYbXh(ids);
		}
		return batchExecute(ids, new IDelete() {
			FbglXsxxService fxs = new FbglXsxxService();
			Map<String, String> message = new HashMap<String, String>();
			public boolean isCanDelete(String pk) {
				return true;
			}
			public Map<String, String> showMessage(String pk) {
				return message;
			}
			public int execute(String[] ids) {
				try {
					return fxs.deleteXhForPk(ids);
				} catch (Exception e) {
					throw new RuntimeException("ɾ��ʧ��!");
				}
			}
		});
	}

	/**
	 * 
	 * @����: ɾ���ѷְ�ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-24 ����02:00:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return Map<String,Object> ��������
	 */
	public Map<String, Object> deleteYbXh(String[] ids) {
		return batchExecute(ids, new IDelete() {
			FbglXsxxService fxs = new FbglXsxxService();
			FbglXsxxForm fxf = new FbglXsxxForm();

			public Map<String, String> showMessage(String pk) throws Exception {
				Map<String, String> message = new HashMap<String, String>();
				if (null == fxf) {
					fxf = fxs.getModel(pk);
				}
				// ҳ����ʾ�������
				message.put("nj", fxf.getNj());
				message.put("xymc", fxf.getXy());
				message.put("bjmc", fxf.getBjmc());
				message.put("xm", fxf.getXm());
				return message;
			}

			public boolean isCanDelete(String pk) throws Exception {
				fxf = fxs.getModel(pk);
				// �ѱ�ѧ�Ųſ���ɾ��
				if (StringUtils.isNotNull(fxf.getXh())) {
					return true;
				}
				return false;
			}

			public int execute(String[] ids) throws Exception {
				return fxs.updateXh(ids);
			}
		});
	}
}
