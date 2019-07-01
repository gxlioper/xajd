/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-16 ����10:37:45 
 */
package com.zfsoft.xgxt.base.export.defualt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ģ��
 * @�๦������: ��ȡģ��Ĭ������
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-16 ����10:37:45
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BaseDefualtData implements IDefualtData {
	private String kzbm;
	private String kzlm;

	public List<String[]> getDefualtData(String drmkdm) {
		List<HashMap<String, String>> list = getDefualtDataForDb(drmkdm);
		List<String[]> defualtList = new ArrayList<String[]>();
		for (HashMap<String, String> hm : list) {
			// ���ݿ�����Ĭ��ֵ
			defualtList.add(fomartParam(drmkdm, hm.get("hh")));
		}
		// ҵ����չ���õ�Ĭ��ֵ
		List<String[]> business = getDefualtDataForBusiness(kzbm,
				kzlm, drmkdm);
		if (null != business) {
			defualtList.addAll(business);
		}
		return defualtList;
	}

	/**
	 * 
	 * @����: ��ȡҵ����չ����Ĭ��ֵ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-16 ����11:30:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param kzbm
	 *            ��չ����
	 * @param kzlm
	 *            ��չ����(����ʵ��IDefualtData�ӿ�)
	 * @param drmkdm
	 *            ��չģ�����
	 * @return List<String[]> ��������
	 */
	private List<String[]> getDefualtDataForBusiness(String kzbm,
			String kzlm, String drmkdm) {
		IDefualtData bd = getObject(kzbm, kzlm, null);
		if (null == bd) {
			return null;
		}
		return bd.getDefualtData(drmkdm);
	}

	/**
	 * 
	 * @����: ��ȡ��Ӧ��չ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-2 ����04:20:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param classPath
	 * @param calssName
	 * @param obj
	 * @return BusinessExtend ��������
	 */
	private static IDefualtData getObject(String classPath, String calssName,
			Object... obj) {
		if (StringUtils.isNull(classPath) || StringUtils.isNull(calssName)) {
			return null;
		}
		try {
			if (null == obj) {
				return (IDefualtData) Class
						.forName(classPath + "." + calssName).newInstance();
			}
			// �������
			Class[] classs = new Class[obj.length];
			for (int i = 0; i < obj.length; i++) {
				classs[i] = obj[i].getClass();
			}
			Class<IDefualtData> ib = (Class<IDefualtData>) Class
					.forName(classPath + "." + calssName);
			return ib.getConstructor(classs).newInstance(obj);
		} catch (Exception e) {
			throw new RuntimeException("���ô������� ·�����ú���չ���Ƿ�ʵ��IDefualtData�ӿ�:"
					+ classPath + "." + calssName);
		}
	}

	private List<HashMap<String, String>> getDefualtDataForDb(String drmkdm) {
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select drmkdm, hh from zfxg_dr_mrsj where drmkdm=?");
		sb.append(" group by drmkdm, hh order by hh");
		return DAO.getInstance().getListNotOut(sb.toString(),
				new String[] { drmkdm });
	}

	private String[] fomartParam(String drmkdm, String rowindex) {
		String kzbm;
		String kzlm;
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select * from zfxg_dr_mrsj where drmkdm=? and hh=? order by to_number(xh)");
		List<HashMap<String, String>> list = DAO.getInstance().getListNotOut(
				sb.toString(), new String[] { drmkdm, rowindex });
		if(null==list||list.size()<=0){
			return new String[]{};
		}
		String maxXh=list.get(list.size()-1).get("xh");
		String[] param = new String[Integer.parseInt(maxXh)];
		int i = 0;
		for (HashMap<String, String> hm : list) {
			kzbm = hm.get("kzbm");
			kzlm = hm.get("kzlm");
			i=Integer.parseInt(hm.get("xh"));
			i=i-1;//xh��1��ʼ�����Լ�1�����±�
			//����Ѿ�����չ�࣬���ټ�¼
			if (StringUtils.isNull(this.kzbm)
					&& StringUtils.isNotNull(kzbm)) {
				this.kzbm = kzbm;
			}
			if (StringUtils.isNull(this.kzlm)
					&& StringUtils.isNotNull(kzlm)) {
				this.kzlm = kzlm;
			}
			if(i>=param.length){
				throw new RuntimeException("�������(xh)���󣬳�����Ӧ���±ꡣ");
			}
			param[i] = hm.get("lz").toString();
			//i++;
		}
		return param;
	}
}
