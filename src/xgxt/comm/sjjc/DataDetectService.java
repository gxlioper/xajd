package xgxt.comm.sjjc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.xml.XMLReader;

public class DataDetectService {

	DataDetectDAO dao = new DataDetectDAO();

	/**
	 * ����ѧУ���롢ģ������ ��ȡ�쳣���ݼ�������Ϣ
	 * 
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, Object>> getSjjcByXxMk(DataDetectForm myForm) {
		// ���ģ����Ϣ(����jcmkdm,jcmkmc �������)
		List<HashMap<String, String>> jcmkList = dao.getJcmkList(myForm);
		// ���ݼ����ϸ��Ϣ
		List<HashMap<String, String>> sjjcList = dao.getSjjcByXxMk(myForm);
		// �����Ϣ�б�
		List<HashMap<String, Object>> jcxxList = new ArrayList<HashMap<String, Object>>();

		// �ϲ��м���
		int n = 0;
		// ======================����ҳ����ʾ��Ϣ=================================
		for (int i = 0; i < jcmkList.size(); i++) {
			HashMap<String, String> jcmkMap = jcmkList.get(i);
			HashMap<String, Object> jcxxMap = new HashMap<String, Object>();
			List<HashMap<String, String>> jclxList = new ArrayList<HashMap<String, String>>();
			for (int j = 0; j < sjjcList.size(); j++) {
				HashMap<String, String> sjjcMap = sjjcList.get(j);

				if (jcmkMap.get("jcmkdm").equalsIgnoreCase(
						sjjcMap.get("jcmkdm"))
						&& !"yes".equalsIgnoreCase(sjjcMap.get("sfpd"))) {

					jclxList.add(sjjcMap);
					n++;
					sjjcMap.put("sfpd", "yes");
				}
			}
			jcxxMap.put("length", n);
			jcxxMap.putAll(jcmkMap);
			jcxxMap.put("jclx", jclxList);
			jcxxList.add(jcxxMap);
			n = 0;
		}
		// ======================����ҳ����ʾ��Ϣend=================================

		return jcxxList;
	}

	/**
	 * ���ݼ��(����쳣��������)
	 * 
	 * @param inputV(dwr��ȡ)
	 * @return int
	 * @throws Exception
	 */
	public int dataDetect(String[] inputV) throws Exception {

		DataDetectForm myForm = new DataDetectForm();
		// ģ������
		String mklx = inputV[0];
		// ���ģ��
		String jcmk = inputV[1];
		// �쳣����
		String yclx = inputV[2];
		// ���ü�ⷽ��(����ǿ��򲻲��ü�ⷽ�����)
		String methodValue = inputV[3];
		// ��ر���
		String tableName = inputV[4];
		// �ֶ�
		String zd = inputV[5];
		// ����
		String query = inputV[6];

		// ===============����ж�����2Ϊ�գ���ôֻ����ж�����1==================
		// �ж�����1
		String pdlxone = inputV[7];
		// �ж�����2
		String pdlxtwo = inputV[8];
		// �ж�ֵ1
		String pdzone = inputV[9];
		// �ж�ֵ2
		String pdztwo = inputV[10];
		// ===============����ж�����2Ϊ�գ���ôֻ����ж�����1 end==================
		myForm.setTableName(tableName);
		myForm.setZd(zd);
		myForm.setQuery(query);
		myForm.setPdlxone(pdlxone);
		myForm.setPdlxtwo(pdlxtwo);
		myForm.setPdzone(pdzone);
		myForm.setPdztwo(pdztwo);

		int count = 0;

		// �ж��Ƿ���÷��������ж�
		if (Base.isNull(methodValue)) {
			// ��XML�����ļ��л�ȡ���SQL
			String sql = XMLReader.getDataDetect(mklx, jcmk, yclx);
			myForm.setSql(sql);
			// ͳ���쳣���ݵ�����
			count = dao.getCounts(myForm);
		} else {
			try {
				// ͨ�������ȡ���ݿ������õļ�ⷽ�����м��
				DataDetectMethod.class.getMethod(methodValue,
						new Class[] { DataDetectForm.class }).invoke(null,
						new Object[] { (Object) myForm });
				// ͳ���쳣���ݵ�����
				count = Integer.parseInt(myForm.getCount());
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		return count;

	}

	/**
	 * ��ȡ�쳣�������ʹ����ȡ �쳣����
	 * 
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJcsjList(DataDetectForm myForm) throws Exception {

		// �����쳣���ʹ����ȡ����쳣���͵��������
		HashMap<String, String> jcMap = dao.getJcxx(myForm);
		// �����쳣��Ϣ�����
		List<String[]> rs = new ArrayList<String[]>();

		// =================��ȡ�쳣���ͼ������========================

		// ��ⷽ��
		String methodValue = jcMap.get("method");
		// ģ������
		String mklx = jcMap.get("gnmklx");
		// ������ģ��
		String jcmk = jcMap.get("jcmkdm");
		// �쳣���ʹ���
		String yclx = jcMap.get("yclxdm");
		// �쳣��������
		myForm.setYclxmc(jcMap.get("yclxmc"));
		// ����
		myForm.setTableName(jcMap.get("tablename"));
		// ����ֶ�
		myForm.setZd(jcMap.get("zd"));
		// �������
		myForm.setQuery(jcMap.get("query"));
		// �ж�����1
		myForm.setPdlxone(jcMap.get("pdlxone"));
		// �ж�����2
		myForm.setPdlxtwo(jcMap.get("pdlxtwo"));
		// �ж�ֵ1
		myForm.setPdzone(jcMap.get("pdzone"));
		// �ж�ֵ2
		myForm.setPdztwo(jcMap.get("pdztwo"));
		// ����ʾ�쳣��Ϣ�ֶ�
		myForm.setXszd(jcMap.get("xszd"));
		// �쳣���ݼ�������
		myForm.setPk(jcMap.get("pk"));

		// ===============�жϼ�ⷽ���Ƿ�Ϊ��==========================
		if (Base.isNull(methodValue)) {
			String sql = XMLReader.getDataDetect(mklx, jcmk, yclx);
			myForm.setSql(sql);
			dao.getYcsjList(myForm);
			rs = myForm.getRs();
		} else {
			try {
				DataDetectMethod.class.getMethod(methodValue,
						new Class[] { DataDetectForm.class }).invoke(null,
						new Object[] { (Object) myForm });
				dao.getYcsjxxList(myForm);
				rs = myForm.getRs();

			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}

		return rs;

	}

	/**
	 * ɾ���쳣����(���ݴ�������������ֵɾ��)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delData(DataDetectForm myForm) throws Exception {
		return dao.delData(myForm);
	}
}
