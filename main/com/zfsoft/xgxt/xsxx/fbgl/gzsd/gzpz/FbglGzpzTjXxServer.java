/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����10:10:46 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����10:10:46
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglGzpzTjXxServer extends
		SuperServiceImpl<FbglGzpzTjXxForm, FbglGzpzTjXxDao> {
	FbglGzpzTjXxDao dao = new FbglGzpzTjXxDao();
	// �ְ���� ָ������
	public static final String _FBGZ_CODE = "FBGZ_PJFP";
	// 2��������;
	public static final String _XXLX_XLK = "2";
	// 3:��ֹλ
	public static final String _XXLX_QZW = "3";
	// ���޸�
	public static final String _SFKXG_KXG = "1";

	public static final String _SFKXG_SPLIC="-";
	
	public FbglGzpzTjXxServer() {
		this.setDao(dao);
	}
	/**
	 * 
	 * @����: ������ϸ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����11:17:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getTJpzxxId(String pzgzid) {
		return dao.getTJpzxxId(pzgzid);
	}
	/**
	 * 
	 * @����: ������������id��ȡ������ϸ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����11:18:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @param tjgzid
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getTJpzxxId(String pzgzid,String tjgzid){
		return dao.getTJpzxxId(pzgzid,tjgzid);
	}
	/**
	 * 
	 * @����: �������ù���id��ȡ���õ�������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-27 ����10:59:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTjgzxx(String pzgzid){
		return dao.getTjgzxx(pzgzid);
	}
	/**
	 * 
	 * @����: �������ö�Ӧ����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-18 ����02:41:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gzpzid
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getGzpzTjxxLx(String gzpzid) {
		return dao.getGzpzTjxxLx(gzpzid);
	}

	/**
	 * 
	 * @����: ���ݹ������ͻ�ȡ����������ϸ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-18 ����02:49:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjgzid
	 * @param gzpzid
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getGzpzTjxxForLx(String tjgzid,
			String gzpzid) {
		return getGzpzTjxxForLx(tjgzid, gzpzid, true);
	}

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-21 ����02:38:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getKxgxx(String pzgzid) {
		return formateGzpz(dao.getXgXx(pzgzid, _SFKXG_KXG));
	}
	/**
	 * 
	 * @����: ��ʽ������������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-21 ����03:15:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> formateGzpz(
			List<HashMap<String, String>> list) {
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		for (HashMap<String, String> hm : list) {
			String ylz = hm.get("ylz");
			hm.put("ylzstr", ylz);
			newList.add(hm);
		}
		return newList;
	}
	/**
	 * 
	 * @����: ��ʽ��Ԥ��ֵ(�����ֶ�����)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-21 ����03:14:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ylz
	 * @return
	 * String �������� 
	 */
	public String formateYlz(String ylz) {
		return formateYlz(ylz,true);
	}
	/**
	 * 
	 * @����: ��ʽ��Ԥ��ֵ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-21 ����03:14:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ylz
	 * @param appendZd �Ƿ�����ֶ�����
	 * @return
	 * String �������� 
	 */
	public String formateYlz(String ylz,boolean appendZd) {
		// Ԥ��ֵ���򣺱���,�ֶΣ������ֶ� xsxx,xymc,xydm
		if (StringUtils.isNotNull(ylz)) {
			String ylzs[] = ylz.split("-");
			if (ylzs.length != 3) {
				throw new RuntimeException("Ԥ��ֵ���ô���,�������������顣");

			}
			StringBuffer sb = new StringBuffer();
			sb.append("select ");
			sb.append(ylzs[1]);
			sb.append(" ");
			sb.append("from ");
			sb.append(ylzs[0]);
			sb.append(" order by ");
			sb.append(ylzs[1]);
			sb.append(" nulls last");
			try {
				ylz = DAO.getInstance().getOneRs(sb.toString(), new String[] {},
						ylzs[1]);
			} catch (Exception e) {
				throw new RuntimeException("Ԥ��ֵ���ô��󣬲����ڶ�Ӧ����ֶΣ����顣");
			}
			//�Ƿ񷵻ذ����ֶ�����
			if(appendZd){
				ylz += _SFKXG_SPLIC + ylzs[2]+_SFKXG_SPLIC+ylzs[1];
			}
		}
		return ylz;
	}
	/**
	 * 
	 * @����: ��ʽ��ѡ��ֵ���ü��ϣ�ת��Ϊmap��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-6-9 ����06:45:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xxzsz
	 * Map<String,String> ��������
	 */
	private Map<String,String> fomartXxz(String xxzsz){
		Map<String,String> map=new HashMap<String, String>();
		if(StringUtils.isNull(xxzsz)){
			map.put(xxzsz, xxzsz);
			return map;
		}
		//ȥ��ǰ���{}
		xxzsz=xxzsz.replace("{", "");
		xxzsz=xxzsz.replace("}", "");
		//{2:2λ,4:4λ}
		String xxzszs[]=xxzsz.split(",");
		String xxzs[];
		for(String xxz:xxzszs){
			xxzs=xxz.split(":");
			if(null!=xxzs&&xxzs.length>0){
				map.put(xxzs[0], xxzs[1]);
			}
		}
		return map;
	}
	/**
	 * 
	 * @����: �������ͻ�ȡ����������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-19 ����09:12:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjgzid
	 *            ����id
	 * @param gzpzid
	 *            ��������id
	 * @param isFormat
	 *            �Ƿ��ʽ������Ϊ�ı�ģʽ
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getGzpzTjxxForLx(String tjgzid,
			String gzpzid, boolean isFormat) {
		List<HashMap<String, String>> oldList = dao.getGzpzTjxxForLx(tjgzid,
				gzpzid);
		if (!isFormat) {
			return oldList;
		}
		Map<String,String> xxzMap=null;
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		int i = 0;
		for (HashMap<String, String> hm : oldList) {
			// String xxzsz=hm.get("xxzsz");
			// ��������ת����ʾ�ı�
			String xxlx = hm.get("xxlx");
			if (StringUtils.isNotNull(xxlx)) {
				// 2��������;3:��ֹλ
				if (xxlx.equals(_XXLX_XLK)) {
					// {2:2λ,4:4λ}
					if (_FBGZ_CODE.equals(hm.get("tjgzid"))) {
						hm.put("xxz", hm.get("xxz").split(":")[1]);
					} else {
						xxzMap=fomartXxz(hm.get("xxzsz"));
						hm.put("xxz", xxzMap.get(hm.get("xxz")));
					}
				} else if (xxlx.equals(_XXLX_QZW)) {
					String[] xxzs = hm.get("xxz").split("~");
					hm.put("xxz", "��ֹλ��" + xxzs[0] + ";λ����" + xxzs[1]);
				}
			}
			// �ϲ���ʾ
			StringBuffer tjxz = new StringBuffer();
			if (i != 0) {
				tjxz.append("&nbsp;&nbsp;+");
			}
			tjxz.append(hm.get("tjszmc"));
			// ѡ������
			if (StringUtils.isNotNull(hm.get("xxz"))) {
				tjxz.append("[" + hm.get("xxz") + "]");
			}
			// �Ƿ���
			if (StringUtils.isNotNull(hm.get("wsblt"))) {
				tjxz.append("[" + hm.get("wsblt") + "]");
			}
			// �Ƿ���޸�
			if (StringUtils.isNotNull(hm.get("sfkxgt"))) {
				tjxz.append("[" + hm.get("sfkxgt") + "]");
			}
			hm.put("tjxz", tjxz.toString());
			i++;
			list.add(hm);
		}
		return list;
	}

	/**
	 * 
	 * @����: ��ȡ��Ӧ��ŵ���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-19 ����03:06:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gzpzid
	 * @param sx
	 * @return List<HashMap<String,String>> ��������
	 */
	public HashMap<String, String> getGzpzTjgz(String pzgzid, String tjgzid,
			String tjszzd, String sx) {
		return dao.getGzpzTjgz(pzgzid, tjgzid, tjszzd, sx);
	}

	public boolean save(FbglGzpzTjXxForm ff) {
		try {
			return runInsert(ff);
		} catch (Exception e) {
			throw new RuntimeException("��������������ϸ��Ϣʧ��!");
		}
	}
}
