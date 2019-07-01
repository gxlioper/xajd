/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-17 ����09:44:06 
 */
package com.zfsoft.xgxt.xsxx.fbgl.bbgl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.extend.IDelete;
import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsxx.fbgl.comm.BarSorce;
import com.zfsoft.xgxt.xsxx.fbgl.comm.ProgressBar;
import com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate;
import com.zfsoft.xgxt.xsxx.fbgl.generate.imp.CreateBjdm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxServer;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-2-17 ����09:44:06
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglBbglService extends
		SuperServiceImplExtend<FbglBbglForm, FbglBbglDao> {
	// ��������key
	private String barkey = "fbglbb";
	// ����������
	private ProgressBar pb;
	// ��������ǰִ�н�����
	private int now = 1;
	private String checkMess=null;
	/**
	 * δ�ύ
	 */
	public static final String _SFYTJ_WTJ = "0";
	/**
	 * �Ѿ��ύ
	 */
	public static final String _SFYTJ_YTJ = "1";
	public FbglBbglDao dao = new FbglBbglDao();
	/**
	 * ���޸���Ϣ�ָ���
	 */
	public static final String _SPLIT_KXG = ",";
	public static final String _SPLIT_MC = ":";
	// ���������趨��Ӧ�� ���������ָ�ֵ������ bbgz_bjdm��ӦΪbjdm
	public static final String _TJGZSD_FGF = "_";
	public static final String _BBZT_YBB = "ybb";
	public static final String _TBZT_YFB = "yfb";

	public FbglBbglService() {
		this.setDao(dao);
	}

	@Override
	public List<HashMap<String, String>> getPageList(FbglBbglForm t, User user)
			throws Exception {
		if (_BBZT_YBB.equals(t.getBbzt())) {
			return dao.getPageListForYbb(t, user);
		}
		return super.getPageList(t, user);
	}

	/**
	 * 
	 * @����: ��ȡ�ְ��б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-3 ����03:26:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPageListForFb(FbglBbglForm t,
			User user) throws Exception {
		if (_TBZT_YFB.equals(t.getFbzt())) {
			return dao.getPageListForYfb(t, user);
		}
		return dao.getPageListForFb(t);
	}

	/**
	 * 
	 * @����: ��ȡ���޸���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-21 ����03:20:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @return List<HashMap<String,String>> ��������
	 */
	public String getKxgxx(String pzgzid) {
		if (StringUtils.isNull(pzgzid)) {
			return null;
		}
		StringBuffer str = new StringBuffer();
		FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
		for (HashMap<String, String> hm : fgtxs.getKxgxx(pzgzid)) {
			String ylzs = hm.get("ylzstr");
			str.append(hm.get("tjszmc"));
			str.append(_SPLIT_MC);
			str.append(ylzs);
			str.append(_SPLIT_KXG);
		}
		return str.toString();
	}

	/**
	 * 
	 * @����: ���������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-5 ����05:30:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param array
	 * @param pzgzid
	 * @return boolean ��������
	 */
	public boolean saveTbxx(JSONArray array, String pzgzid, String pk) {
/*		boolean isok = true;
		dao.delAllFbxx(pk);
		try {
			FbglBbglForm fbf = new FbglBbglForm();
			fbf.setPzgzid(pzgzid);
			for (int i = 0; i < array.length(); i++) {
				JSONObject jb = array.getJSONObject(i);
				fbf.setBjbh(String.valueOf(i + 1));
				fbf.setPk(jb.get("pk").toString());
				fbf.setBjdm(jb.get("bjdm").toString());
				fbf.setBjmc(jb.get("bjmc").toString());
				fbf.setBjrssx(jb.get("bjrssx").toString());
				isok = dao.runInsert(fbf);
			}
		} catch (Exception e) {
			throw new RuntimeException("�������!" + e.getMessage());
		}
		return true;*/
		boolean isok = true;
		dao.delAllFbxx(pk);
		try {
			List<String[]> params=new ArrayList<String[]>();
			FbglBbglForm fbf = new FbglBbglForm();
			//fbf.setPzgzid(pzgzid);
			for (int i = 0; i < array.length(); i++) {
				List<String> p=new ArrayList<String>();
				JSONObject jb = array.getJSONObject(i);
				fbf.setPk(jb.get("pk").toString());
				fbf.setBjrssx(jb.get("bjrssx").toString());
				p.add(pzgzid);
				p.add(String.valueOf(i + 1));
				//p.add(jb.get("pk").toString());
				p.add(jb.get("bjdm").toString());
				p.add(jb.get("bjmc").toString());
				p.add(fbf.getBjrssx());
				p.add(fbf.getNj());
				p.add(fbf.getBmdm());
				p.add(fbf.getPycc());
				p.add(fbf.getXz());
				p.add(fbf.getZydm());
				params.add(p.toArray(new String[]{}));
				//isok = dao.runInsert(fbf);
			}
			isok=dao.batchInsert(params);
		} catch (Exception e) {
			throw new RuntimeException("�������!" + e.getMessage());
		}
		return isok;
	}

	/**
	 * 
	 * @����: ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-28 ����03:18:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param array
	 *            json����
	 * @param pzgzid
	 *            ���ù���
	 * @param all
	 *            �����������ݲ�����Ϊ��Ч�ʴ�ҳ�洫�ݣ�
	 * @return
	 * @throws Exception boolean ��������
	 */
	public synchronized String save(JSONArray array, String pzgzid, String all)
			throws Exception {
		checkMess = MessageKey.FBGL_CHECK_OK;
		try {
			FbglBbglForm fbf = new FbglBbglForm();
			fbf.setPzgzid(pzgzid);
			pb = BarSorce.initProgressBar(barkey + pzgzid, StringUtils
					.paseStringToInteger(all));
			// ��������ǰ����
			//now += array.length();
			for (int i = 0; i < array.length(); i++) {
				fbf.clean();// �����ظ�����FbglBbglForm����ÿ�����
				// ���÷ְ��������
				fbf.setIsNewLsh("1");
				JSONObject jb = array.getJSONObject(i);
				String pk = jb.get("pk").toString();
				BeanUtils.copyProperties(fbf, getBbxx(pk));
				JSONArray trArray = jb.getJSONArray("tr");
				// ͨ���趨��ֵ���ɰ༶���루�Լ���Ӧ�Ĺ���
				Map<String, String> xgzd = new HashMap<String, String>();
				for (int j = 0; j < trArray.length(); j++) {
					JSONObject obj = trArray.getJSONObject(j);
					xgzd.put(obj.get("name").toString(), obj.getString("value")
							.toString());
				}
				// �������� �����RowData
				fbf.setRowData(xgzd);
				// ��ʽ������ ������
				checkMess = formartCodeAndSave(fbf, false);
				if(!MessageKey.FBGL_CHECK_OK.equals(checkMess)){
					pb.finish();
					pb.stop();
					return checkMess;
				}
			}
		} catch (Exception e) {
			pb.autoFinish();
			pb.stop();
			throw new RuntimeException("����������ݴ���" + e.getMessage());
		}
		return checkMess;
	}

	/**
	 * 
	 * @����: ���ݹ�����趨ֵ �����Ӧ�����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-27 ����11:34:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fbf
	 * @param isRecursion
	 *            �Ƿ��ǵݹ��������ˮ�����ɰ༶ʱΪ�ݹ������
	 * @throws Exception void ��������
	 */
	public synchronized String formartCodeAndSave(FbglBbglForm fbf,
			boolean isRecursion) throws Exception {
		// ��ȡ�༶Code
		fbf = getFbglBbglCode(fbf);
		if(null==fbf){
			return MessageKey.FBGL_CHECK_LSHBZ;
		}
		checkMess = dao.checkBb(fbf);
		if(!MessageKey.FBGL_CHECK_OK.equals(checkMess)){
			return checkMess;
		}
		return saveFormartCode(fbf, isRecursion);
	}

	/**
	 * 
	 * @����: ��ȡ��һ��������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-4 ����03:19:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @param pzgzid
	 * @return FbglBbglForm ��������
	 * @throws
	 */
	public FbglBbglForm fbglBbglNextCodexx(String pk, String pzgzid,
			String index) {
		FbglBbglForm fbf = new FbglBbglForm();
		try {
			BeanUtils.copyProperties(fbf, getBbxx(pk));
			fbf.setPk(pk);
			fbf.setPzgzid(pzgzid);
			// ԭ�������Լ���ȡ���ݿ����ֵ ҳ��ɾ���༶�����ݿⲢû��ɾ��������skewing��¼����ҳ��ɾ����������
			// �������Ե�ǰ���ɵ�code����΢����
			fbf.setSkewing(index);
		} catch (Exception e) {
			throw new RuntimeException("��ȡ��Ӧ�����Ϣʧ��!" + e.getMessage());
		}
		return getFbglBbglCode(fbf);
	}

	/**
	 * 
	 * @����: ��ȡ�༶�������code
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-4 ����02:52:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fbf
	 * @return
	 * @throws Exception
	 *             FbglBbglForm ��������
	 * @throws
	 */
	public synchronized FbglBbglForm getFbglBbglCode(FbglBbglForm fbf) {
		try {
			if (StringUtils.isNull(fbf.getPk())
					|| StringUtils.isNull(fbf.getPzgzid())) {
				return null;
			}
			// ��ǰѡ��Ĺ�����Ϣ
			FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
			List<HashMap<String, String>> tjList = fgtxs.getTjgzxx(fbf
					.getPzgzid());
			IGenerate gg = new CreateBjdm();
			// ��ȡ��Ӧ�����code
			for (HashMap<String, String> tjmap : tjList) {
				String code = "";
				String tjgzid = tjmap.get("tjgzid");
				List<HashMap<String, String>> list = fgtxs.getGzpzTjxxForLx(
						tjgzid, fbf.getPzgzid(), false);
				// ����
				FbglGzpzTjXxForm fgtxf = new FbglGzpzTjXxForm();
				FbglTjgzForm ftf = new FbglTjgzForm();
				// ѭ���������û�ȡ����code
				for (HashMap<String, String> hm : list) {
					BeanUtils.copyProperties(fgtxf, hm);
					BeanUtils.copyProperties(ftf, hm);
					code += gg.getCode(ftf, fgtxf, fbf);
				}
				// ��ȡҪ�趨������
				String name = tjgzid.split(_TJGZSD_FGF)[1];
				//���ĵ���ʾ����
				checkMess=gg.getErrorMessage();
				if(StringUtils.isNotNull(checkMess)){
					return null;
				}
				fbf = (FbglBbglForm) setCodeValue(fbf, name, code);
			}
			return fbf;
		} catch (Exception e) {
			throw new RuntimeException("��ȡ��Ӧ������Ϣʧ��!",e);
		}
	}

	/**
	 * 
	 * @����: ��ʽ�����ɰ༶��Ų�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-28 ����02:14:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fbf
	 * @param isRecursion
	 * @return boolean ��������
	 */
	private synchronized String saveFormartCode(FbglBbglForm fbf,
			boolean isRecursion) {
		try {
			this.runInsert(fbf);
			pb.change();
			Integer bjbh = StringUtils.paseStringToInteger(fbf.getBjbh());
			Map<String, String> map = fbf.getRowData();
			// �������
			String bjgs = map.get("bjgs");
			for (int i = 1; i < Integer.parseInt(bjgs) && !isRecursion; i++) {
				// ����
				bjbh++;
				// �����±��
				fbf.setBjbh(bjbh.toString());
				// �ݼ����ɰ༶����
				Integer newBjgs = (Integer.parseInt(bjgs) - 1);
				map.put("bjgs", newBjgs.toString());
				fbf.setRowData(map);
				fbf.setIsNewLsh("0");//��һ���༶���ȡǰ̨����ֵ�������Ϊ������ˮ��
				String message = formartCodeAndSave(fbf, true);
				if(!MessageKey.FBGL_CHECK_OK.equals(message)){
					pb.autoFinish();
					return message;
				}
				// ���������ȸ���
				//pb.change();
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException("��������Ϣ����!" + e.getMessage());
		}
		return MessageKey.FBGL_CHECK_OK;
	}

	/**
	 * 
	 * @����: �趨��Ӧ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-27 ����11:32:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fbf
	 * @param name
	 * @param value
	 * @return FbglBbglForm ��������
	 */
	protected Object setCodeValue(Object fbf, String name, String value) {
		try {
			Class<?> cla = fbf.getClass();
			String methodName = "set" + name.substring(0, 1).toUpperCase()
					+ name.substring(1, name.length()).toLowerCase();
			Method method = cla.getMethod(methodName, String.class);
			method.invoke(fbf, value);
		} catch (Exception e) {
			throw new RuntimeException("���ö�Ӧֵ[" + name + "]ʧ��" + e.getMessage());
		}
		return fbf;
	}

	/**
	 * 
	 * @����: �@ȡ���������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-28 ����02:17:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return HashMap<String,String> ��������
	 */
	public HashMap<String, String> getBbxx(String pk) {
		return dao.getBbxx(pk);
	}

	/**
	 * 
	 * @����: ��ȡ��̬�����ɣ������ɸ��ĵ�ǰ̨ʵ�֣���ʱû���ҵ���ط�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-24 ����09:38:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param kxg
	 * @return String ��������
	 */
	public String getAutoGrid(String kxg) {
		if (StringUtils.isNull(kxg)) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		StringBuffer formatter = new StringBuffer();
		String[] kxgs = kxg.split(_SPLIT_KXG);
		String ylzs[];
		String mc, ylzcs[];
		for (String ylz : kxgs) {
			ylzs = ylz.split(_SPLIT_MC);
			mc = ylzs[0];
			ylzcs = ylzs[1].split(FbglGzpzTjXxServer._SFKXG_SPLIC);
			// ���Ϸ�����
			if (ylzcs.length < 2) {
				continue;
			}
			// ƴ������html
			formatter.append(" var v=rowObject['" + ylzcs[2].toLowerCase()
					+ "'];");
			formatter.append(" var html=\"<input name='" + ylzcs[1]
					+ "' type='text' size='15px;' value='\"+v+\"'/>\";");
			formatter.append(" return html;");
			sb.append("{label:'" + mc + "',name:'" + ylzcs[1] + "', index: '"
					+ ylzcs[1] + "',formatter:function(cellValue,rowObject){"
					+ formatter + "}},");
			formatter.delete(0, formatter.length());
		}
		return sb.toString();
	}

	/**
	 * 
	 * @����: ��ȡ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-4 ����10:23:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getTbxx(String pk) {
		return dao.getTbxx(pk);
	}

	/**
	 * 
	 * @����: ��ȡ����������༶
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-4 ����10:29:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getTbJtBj(String pk) {
		return dao.getTbJtBj(pk);
	}

	/**
	 * 
	 * @����: ��ȡ�����༶�����ù���id
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-4 ����10:47:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return String ��������
	 * @throws
	 */
	public String getTbPzgzid(String pk) {
		List<HashMap<String, String>> list = dao.getTbJtBj(pk);
		if (null != list && list.size() > 0) {
			return list.get(0).get("pzgzid");
		}
		return null;
	}

	/**
	 * 
	 * @����: ��ȡ�����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-4 ����10:26:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getBbxx(FbglBbglForm myForm) {
		return dao.getBbxx(myForm);
	}

	public List<HashMap<String, String>> getBbxxForPzgz(String pzgzid) {
		return dao.getBbxxForPzgz(pzgzid);
	}

	/**
	 * 
	 * @����: ��ȡ��Ӧѧ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-8 ����03:27:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return Integer ��������
	 */
	public Integer getXss(String bjdm) {
		FbglXsxxService fxs = new FbglXsxxService();
		List<HashMap<String, String>> list = fxs.getXsxxForBjdm(bjdm);
		return null == list ? null : list.size();
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
			// �Ƿ����ɾ��
			public boolean isCanDelete(String pk) {
				try {
					FbglBbglForm ff = getModel(pk);
					Integer xss = getXss(ff.getBjdm());
					// ����ɾ��,����ѧ��
					if (null != xss && xss > 0) {
						return false;
					}
					return true;
				} catch (Exception e) {
					throw new RuntimeException("��ȡ��ӦҪɾ�����ʧ��!");
				}
			}

			// ��ʾ����ɾ����ԭ��
			public Map<String, String> showMessage(String pk) {
				Map<String, String> message = new HashMap<String, String>();
				try {
					FbglBbglForm ff = getModel(pk);
					message.put("bjmc", ff.getBjmc());
				} catch (Exception e) {
					throw new RuntimeException("��ȡ��ӦҪɾ�����ʧ��!");
				}
				return message;
			}

			// ִ��ɾ��
			public int execute(String[] ids) {
				try {
					// ִ��ɾ��
					return runDelete(ids);
				} catch (Exception e) {
					throw new RuntimeException("ɾ��ʧ��!");
				}
			}
		});
	}

	/**
	 * 
	 * @����: ��ȡ��ǰ�༶�б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-10 ����03:09:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getDqBjList(FbglBbglForm myForm) {
		return dao.getDqBjList(myForm);
	}
}
