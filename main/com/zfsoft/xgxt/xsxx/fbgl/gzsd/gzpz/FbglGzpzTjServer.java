/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����10:09:43 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.utils.GetTime;
import com.zfsoft.xgxt.base.extend.IDelete;
import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xsxx.fbgl.Config;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglService;
import com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate;
import com.zfsoft.xgxt.xsxx.fbgl.generate.imp.PreviewImp;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglGzdmService;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����10:09:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglGzpzTjServer extends
		SuperServiceImplExtend<FbglGzpzTjForm, FbglGzpzTjDao> {
	FbglGzpzTjDao dao = new FbglGzpzTjDao();

	public FbglGzpzTjServer() {
		this.setDao(dao);
	}

	public boolean save(FbglGzpzTjForm ff) {
		try {
			ff.setGxsj(GetTime.getNowTime4());
			return runInsert(ff);
		} catch (Exception e) {
			throw new RuntimeException("������������ʧ��!");
		}
	}

	/**
	 * 
	 * @����: ��ȡ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-18 ����01:51:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @return Map<String,Object> ��������
	 */
	public Map<String, Object> getGzpz(String pzgzid, boolean isFormat) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			FbglGzpzTjForm fgtf = getModel(pzgzid);
			// ��������
			FbglGzdmService fgs = new FbglGzdmService();
			// ���ù�������
			fgtf.setGzmc(fgs.getGzmc(fgtf.getGzdm()));
			data.put("gzpztj", fgtf);
			List<HashMap<String, Object>> hmList = new ArrayList<HashMap<String, Object>>();
			FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
			// FbglTjgzService fts=new FbglTjgzService();
			for (HashMap<String, String> map : fgtxs.getGzpzTjxxLx(pzgzid)) {
				HashMap<String, Object> hm = new HashMap<String, Object>();
				// ������������
				hm.putAll(map);
				// ����������ϸ��Ϣ
				hm.put("tjxx", fgtxs.getGzpzTjxxForLx(map.get("tjgzid"),
						pzgzid, isFormat));
				hmList.add(hm);
			}
			data.put("gzpztjxx", hmList);
		} catch (Exception e) {
			throw new RuntimeException("��ȡ���ù���ʧ�ܣ�" + e.getMessage());
		}
		return data;
	}

	/**
	 * 
	 * @����: ��ȡ�����������ڲ鿴
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-19 ����09:15:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @return Map<String,Object> ��������
	 * @throws
	 */
	public Map<String, Object> getGzpz(String pzgzid) {
		return getGzpz(pzgzid, true);
	}

	/**
	 * 
	 * @����: ��ȡ�������������޸�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-19 ����09:15:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @return Map<String,Object> ��������
	 * @throws
	 */
	public Map<String, Object> getGzpzForUpdate(String pzgzid) {
		return getGzpz(pzgzid, false);
	}

	/**
	 * 
	 * @����: ���ƹ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-17 ����08:58:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @return boolean ��������
	 */
	public boolean copy(String pzgzid) {
		boolean isok = false;
		FbglGzpzTjForm fgt;
		try {
			fgt = getModel(pzgzid);
			// ������������
			String zbid = UniqID.getInstance().getUniqIDHash();
			fgt.setPzgzid(zbid);
			fgt.setPzgzmc("���� " + fgt.getPzgzmc());
			// fgt.setQyzt(_QYZT_BQY);
			isok = save(fgt);
			FbglGzpzTjXxServer fgs = new FbglGzpzTjXxServer();
			List<HashMap<String, String>> list = fgs.getTJpzxxId(pzgzid);
			for (HashMap<String, String> hm : list) {
				FbglGzpzTjXxForm fgf = new FbglGzpzTjXxForm();
				BeanUtils.copyProperties(fgf, hm);
				fgf.setPzgzid(zbid);
				isok = fgs.save(fgf);
			}
			return isok;
		} catch (Exception e) {
			throw new RuntimeException("���ƴ���!");
		}
	}

	/**
	 * 
	 * @����: ����ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-28 ����04:11:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 *             Map<String,Object> ��������
	 */
	public Map<String, Object> delete(String[] ids){
		return batchExecute(ids, new IDelete() {
			FbglGzpzTjForm fgtf;

			public boolean isCanDelete(String pk) throws Exception {
				fgtf = getModel(pk);
				// ���û� �Ѿ�ʹ��
				if (Config._QYZT_QY.equals(fgtf.getQyzt()) || isUse(pk)) {
					return false;
				}else if(Config._SFNZ_YES.equals(fgtf.getSfnz())){
					return false;
				}
				return true;
			}

			public Map<String, String> showMessage(String pk) throws Exception {
				//FbglGzdmService fgs = new FbglGzdmService();
				Map<String, String> message = new HashMap<String, String>();
				message.put("pzgzmc",fgtf.getPzgzmc());
				message.put("qyzt",fgtf.getQyzt());
				message.put("sfnz",fgtf.getSfnz());
				return message;
			}

			// ִ��ɾ��
			public int execute(String[] ids) {
				try {
					return runDelete(ids);
				} catch (Exception e) {
					throw new RuntimeException("ɾ��ʧ��!");
				}
			}
		});
	}

	/**
	 * 
	 * @����: �Ƿ��Ѿ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-1 ����10:04:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @return boolean ��������
	 */
	public boolean isUse(String pzgzid) {
		try {
			FbglBbglService fgtxs = new FbglBbglService();
			List<HashMap<String, String>> syxx = fgtxs.getBbxxForPzgz(pzgzid);
			if (null != syxx && syxx.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new RuntimeException("��ȡ��Ӧ��ϸ������Ϣʧ�ܣ�" + e.getMessage());
		}
	}

	/**
	 * 
	 * @����: ��ȡ�ύ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-17 ����11:54:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @param tjgzid
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getTjnrpz(String pzgzid, String tjgzid) {
		return dao.getGzpznr(pzgzid, tjgzid);
	}

	/**
	 * 
	 * @����: ��ȡ���������б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-17 ����11:57:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getTjnrList() {
		return dao.getTjnrList();
	}
	/**
	 * 
	 * @����: ���ݹ�������ȡ�����ù���list
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-2 ����09:24:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gzdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getYqyTjnrList(String gzdm) {
		return dao.getTjnrList(gzdm,Config._QYZT_QY);
	}
	/**
	 * 
	 * @����: ��ȡԤ��ֵ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-26 ����10:46:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gzpzid
	 * @param tjgzid
	 * @param lsh
	 * @return String ��������
	 */
	public List<String> getGzStr(String gzpzid, String tjgzid) {
		List<String> str = new ArrayList<String>();
		try {
			FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
			List<HashMap<String, String>> list = fgtxs.getGzpzTjxxForLx(tjgzid,
					gzpzid, false);
			FbglGzpzTjXxForm fgtxf = new FbglGzpzTjXxForm();
			FbglTjgzForm ftf = new FbglTjgzForm();
			for (HashMap<String, String> hm : list) {
				BeanUtils.copyProperties(fgtxf, hm);
				BeanUtils.copyProperties(ftf, hm);
				IGenerate gg = new PreviewImp();
				String code = gg.getCode(ftf, fgtxf);
				if (!"".equals(code)) {
					str.add(code);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("��ȡ���ù���ʧ��!" + e.getMessage());
		}
		return str;
	}

	public boolean sfQy(String gzdm) {
		// ��ʱ����������֤
		return false;
		 //return dao.sfQy(gzdm);
	}
}
