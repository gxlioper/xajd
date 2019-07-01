/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-6 ����05:06:24 
 */
package com.zfsoft.xgxt.xsxx.fbgl.fbgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.extend.IDelete;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglService;
import com.zfsoft.xgxt.xsxx.fbgl.comm.BarSorce;
import com.zfsoft.xgxt.xsxx.fbgl.comm.ProgressBar;
import com.zfsoft.xgxt.xsxx.fbgl.generate.BaseCreate;
import com.zfsoft.xgxt.xsxx.fbgl.generate.imp.CreateFb;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxDao;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-6 ����05:06:24
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglService extends FbglBbglService {
	private String barKey = "fb";

	/**
	 * 
	 * @����: ��ȡѡ���רҵ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-6 ����05:24:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return String ��������
	 */
	public int getSelectZy(String pk) {
		String[] pks = pk.split(",");
		return dao.getSelectZy(pks).size();
	}

	/**
	 * 
	 * @����: ��ȡѡ���רҵids
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-7 ����02:53:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return String ��������
	 */
	public String getSelectZyIds(String pk) {
		StringBuffer zy = new StringBuffer();
		String[] pks = pk.split(",");
		List<HashMap<String, String>> list = dao.getSelectZy(pks);
		for (HashMap<String, String> hm : list) {
			zy.append(hm.get("zydm"));
			zy.append(",");
		}
		return zy.toString();
	}

	/**
	 * 
	 * @����: ��ȡ�ѷְ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-6 ����05:44:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return String ��������
	 */
	public String getYfbTs(String pk) {
		String[] pks = pk.split(",");
		return dao.getFbxx(true, pks);
	}

	/**
	 * 
	 * @����: ��ȡ���зְ��б�����pk
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-3 ����03:30:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String ��������
	 */
	public String getAllPks() {
		return getAllCols("pk", dao.getFbList());
	}

	/**
	 * 
	 * @����: ��ȡδ�ְ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-6 ����05:44:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return String ��������
	 */
	public String getWfbTs(String pk) {
		String[] pks = pk.split(",");
		return dao.getFbxx(false, pks);
	}

	/**
	 * 
	 * @����: ����ְ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-28 ����09:16:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @param zydms
	 * @return boolean ��������
	 */
	public boolean saveFb(String pzgzid, String pks[]) {
		boolean isok = true;
		// ��ʼ��������
		ProgressBar pb=BarSorce.initProgressBar(barKey + pzgzid,Integer.parseInt(dao.getFbxx(false,pks)));
		List<HashMap<String, String>> njList = dao.getNj();
		if(null==njList||njList.size()<=0){
			//��ò�Ҫʹ�ý�������ʾ��ҵ����ò�Ҫ���롣
			//����ϵͳ��Ӧ������Ϣ��ʾ�����ã�
			pb.setMessage("�ְ�֮ǰ�����Ƚ��б�࣡");
			throw new SystemException("�ְ�֮ǰ�����Ƚ��б�࣡");
		}
		String nj = "";
		List<HashMap<String, String>> xsxx=null;
		for (HashMap<String, String> njmap : njList) {
			nj = njmap.get("nj");
			for (String pk : pks) {
				// ÿ��רҵ
				BaseCreate fg = new CreateFb();
				fg.addParam(pk);
				fg.addParam(nj);
				// �ְ�
				xsxx=fg.generate(pzgzid, pk);
			}
		}
		if(null==xsxx||xsxx.isEmpty()){
			pb.setMessage("�����ڶ�Ӧ�꼶�İ༶�����ְܷ࣡");
			throw new SystemException("�����ڶ�Ӧ�꼶�İ༶�����ְܷ࣡");
		}
		return isok;
	}

	/**
	 * 
	 * @����: ��ȡרҵ�İ༶
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����11:35:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zydm
	 * @param nj
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getZyBj(String pk, String nj) {
		if (StringUtils.isNotNull(nj)) {
			return dao.getPkBj(pk, nj);
		}
		return dao.getPkBj(pk);
	}

	/**
	 * 
	 * @����: ��ȡ�Էְ�ѧ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����01:59:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return int ��������
	 */
	public int getYyxsx(String bjdm) {
		FbglXsxxService fxs = new FbglXsxxService();
		List<HashMap<String, String>> list = fxs.getXsxxForBjdm(bjdm);
		return null == list || list.size() <= 0 ? 0 : list.size();
	}

	/**
	 * 
	 * @����: ��ȡ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-10 ����03:00:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pks
	 * @return Map<String,String> ��������
	 */
	public Map<String, String> getTbParam(String[] pks) {
		Map<String, String> data = new HashMap<String, String>();
		try {
			FbglXsxxService fxs = new FbglXsxxService();
			FbglXsxxForm fxf = fxs.getModel(pks[0]);
			data.put("xh", fxf.getXh());
			data.put("yxxs", String.valueOf(pks.length));
			data.put("nj", fxf.getNj());
			data.put("xymc", fxf.getXy());
			data.put("zymc", fxf.getZymc());
		} catch (Exception e) {
			throw new RuntimeException("��ȡѧ����Ϣʧ��!" + e.getMessage());
		}
		return data;
	}

	/**
	 * 
	 * @����: �����༶
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-10 ����03:43:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pks
	 *            �������ѧ���������� nj+ksh
	 * @param bjdm
	 *            �������İ༶����
	 * @param bjmc
	 *            �������İ༶����
	 * @return boolean ��������
	 */
	public boolean tzbj(String[] pks, String bjdm, String bjmc) {
		boolean isok = true;
		try {
			FbglXsxxService fxs = new FbglXsxxService();
			FbglXsxxForm fxf = new FbglXsxxForm();
			String njksh[];
			for (String pk : pks) {
				njksh = pk.split(FbglXsxxDao._NJ_KSH_FGF);
				fxf.setNj(njksh[0]);
				fxf.setKsh(njksh[1]);
				fxf.setBjdm(bjdm);
				fxf.setBjmc(bjmc);
				fxf.setPzgzid("");
				isok = isok && fxs.runUpdateForFb(fxf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isok;
	}

	/**
	 * 
	 * @����: ɾ���ְ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-2 ����01:55:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param fbzt
	 * @return
	 * @throws Exception
	 *             Map<String,Object> ��������
	 */
	public Map<String, Object> deleteFb(String[] ids, String fbzt)
			throws Exception {
		// δѡ��ȫ��ȡ��
		if (null == ids || ids.length <= 0) {
			Map<String, Object> message = new HashMap<String, Object>();
			int ts = dao.delFbxx();
			message.put(IDelete._CGTS, ts);
			return message;
		}
		if (FbglBbglService._TBZT_YFB.equals(fbzt)) {
			return deleteFb(ids);
		}
		return batchExecute(ids, new IDelete() {
			FbglXsxxService fxs = new FbglXsxxService();
			public boolean isCanDelete(String pk) {
				
				return true;
			}

			public Map<String, String> showMessage(String pk) throws Exception {
				Map<String, String> message = new HashMap<String, String>();
				FbglXsxxForm ff = fxs.getModel(pk);
				message.put("xh", ff.getXh());
				message.put("xm", ff.getXm());
				return message;
			}

			public int execute(String[] ids) throws Exception {
				int i = 0;
				FbglXsxxForm ff = new FbglXsxxForm();
				// ѭ���༶
				for (String bjpks : ids) {
					// ��ȡ�༶�µ��ѱ��ѧ��
					List<String> pks = fxs.getXsForPk(bjpks);
					// ѭ��ѧ����ȡ���ְ�
					for (String id : pks) {
						ff.setPk(id);
						if (fxs.qxFb(ff)) {
							i++;
						} else {
							break;
						}
					}
				}
				return i;
			}
		});
	}

	/**
	 * 
	 * @����: ȡ���ְ���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-10 ����02:02:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 *             Map<String,Object> ��������
	 */
	public Map<String, Object> deleteFb(String[] ids) throws Exception {
		return batchExecute(ids, new IDelete() {
			FbglXsxxService fxs = new FbglXsxxService();

			public boolean isCanDelete(String pk) {
				return true;
			}

			public Map<String, String> showMessage(String pk) throws Exception {
				Map<String, String> message = new HashMap<String, String>();
				FbglXsxxForm ff = fxs.getModel(pk);
				message.put("xh", ff.getXh());
				message.put("xm", ff.getXm());
				return message;
			}

			public int execute(String[] ids) throws Exception {
				int i = 0;
				FbglXsxxForm ff = new FbglXsxxForm();
				// ѭ��ѧ����ȡ���ְ�
				for (String id : ids) {
					ff.setPk(id);
					if (fxs.qxFb(ff)) {
						i++;
					} else {
						break;
					}
				}
				return i;
			}
		});
	}
}
