/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-4 ����03:07:14 
 */
package com.zfsoft.xgxt.base.export.business;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.utils.Arrays2;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.model.ImportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ȡҵ����չʵ�幤��
 * @�๦������:��ȡ��Ӧҵ����չ����򵥹���
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-12-4 ����03:07:14
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BusinessFactory {
	public static IBaseBusiness getInstance(ImportModel im) {
		String drmkdm = im.getDrmkdm();
		String yzcs = im.getYzcs();
		HashMap<String, String> map = getRuleParam(drmkdm);
		String classPath = map.get("yzlbm");
		String calssName = map.get("yzlmc");
		String[] param = StringUtils.isNull(yzcs) ? new String[] {} : yzcs
				.split("-");
		param = Arrays2.addObject(param, drmkdm);
		return getObject(classPath, calssName, param);
	}

	/**
	 * 
	 * @����: ��ȡ���ò���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-2 ����04:20:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dryzbh
	 * @return HashMap<String,String> ��������
	 */
	private static HashMap<String, String> getRuleParam(String drmkdm) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ZFXG_DR_DRYZB where dryzbh=?");
		return DAO.getInstance().getMapNotOut(sb.toString(),
				new String[] { drmkdm });
	}

	/**
	 * 
	 * @����: ���ݲ�����ȡ��Ӧ��չ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-2 ����04:20:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param classPath
	 * @param calssName
	 * @param obj
	 * @return BusinessExtend ��������
	 */
	@SuppressWarnings("unchecked")
	private static BusinessExtend getObject(String classPath, String calssName,
			Object... obj) {
		if (StringUtils.isNull(classPath) || StringUtils.isNull(calssName)) {
			return null;
		}
		try {
			// �������
			Class[] classs = new Class[obj.length];
			for (int i = 0; i < obj.length; i++) {
				classs[i] = obj[i].getClass();
			}
			Class<BusinessExtend> ib = (Class<BusinessExtend>) Class
					.forName(classPath + "." + calssName); 
			return ib.getConstructor(classs).newInstance(obj);
		} catch (Exception e) {
			throw new RuntimeException("������֤��·����������:"+classPath + "." + calssName);
		}
	}
}
