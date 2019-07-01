/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-31 ����11:02:56 
 */
package com.zfsoft.xgxt.base.export.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Arrays2;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.export.model.ImportModel;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;
import com.zfsoft.xgxt.xpjpy.xmsz.jdsz.JdszService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ҵ����չ
 * @�๦������: ����ҵ����չ����
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-12-31 ����11:02:56
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public abstract class BusinessExtend implements IBaseBusiness {
	// �ɹ�����
	private List<List<ImportModel>> success;
	// ��֤ͨ��ԭʼ�����б�����BusinessExtend��֤��ͨ��ʱ,���ɴ����ļ���
	private List<String[]> succeedExcelDataList;
	// ��������
	private List<String[]> error;
	// ����Դ
	private List<String[]> dataSource;
	// ��������
	private HashMap<String, Object> excelObject;
	// ��ɾ������
	private List<Integer> deleteIndex = new ArrayList<Integer>();
	// ����ģ�����
	private String drmkdm;
	// ��չ����
	private String kzdm;
	// ��֤ͨ��
	public final String _YZTG = "true";

	public BusinessExtend(String drmkdm, String kzdm) {
		this.drmkdm = drmkdm;
		this.kzdm = kzdm;
	}
	/**
	 * 
	 * @����: �Ƿ�ɹ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-23 ����05:28:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param message
	 * @return
	 * boolean �������� 
	 */
	protected boolean isSuccess(String message){
		if(StringUtils.isNotNull(message)&&_YZTG.equals(message)){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @����: ��ʽ������(��������дӰ��)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-31 ����11:13:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param excelObject
	 *            excel����Դ
	 * @return HashMap<String,Object> ��������
	 */
	public HashMap<String, Object> formartData(List<List<ImportModel>> success, List<String[]> succeedExcelDataList,
			List<String[]> error) {
		return formartDataUtil(success, succeedExcelDataList, error);
	}
	/**
	 * ��ʽ������(����������дӰ��)
	 */
	private HashMap<String, Object> formartDataUtil(List<List<ImportModel>> success, List<String[]> succeedExcelDataList,
			List<String[]> error) {
		// ȥ������ҵ�����ݴ��������
		List<List<ImportModel>> successNew = new ArrayList<List<ImportModel>>();
		for (int i = 0; i < success.size(); i++) {
			if(!deleteIndex.contains(i)){
				successNew.add(success.get(i));
			}
		}
		List<String[]> succeedExcelDataListNew = new ArrayList<String[]>();
		for (int i = 0; i < succeedExcelDataList.size(); i++) {
			if(!deleteIndex.contains(i)){
				succeedExcelDataListNew.add(succeedExcelDataList.get(i));
			}
		}
		this.success = successNew;
		this.succeedExcelDataList = succeedExcelDataListNew;
		this.error = error;
		excelObject.put("succeedList", successNew);
		excelObject.put("succeedExcelDataList", succeedExcelDataListNew);
		excelObject.put("errorList", error);
		this.deleteIndex = new ArrayList<Integer>();
		return excelObject;
	}

	/**
	 * 
	 * @����: ��ȡ��Ӧ�ֶ���֤��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-31 ����11:30:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @param zdm
	 * @param xh
	 * @return String ��������
	 */
	public HashMap<String, String> getParamForKey(String ywkzkey, String drmkdm) {
		try {
			StringBuffer sb = new StringBuffer();
			sb
					.append("select * from ZFXG_DR_DRBMPZB where dryzbh=? and drmkdm=?");
			return DAO.getInstance().getMapNotOut(sb.toString(),
					new String[] { ywkzkey, drmkdm });
		} catch (Exception e) {
			throw new RuntimeException("��չҵ����֤key����Ψһ��");
		}
	}

	/**
	 * 
	 * @����: ��ȡ������ʾ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-2 ����04:08:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywkzkey
	 * @param drmkdm
	 * @return String ��������
	 */
	public String getParamMessage(String ywkzkey, String drmkdm) {
		return getParamForKey(ywkzkey, drmkdm).get("drmkyzsm");
	}

	/**
	 * 
	 * @����: ��ȡʵ��ֵ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-31 ����03:48:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param excelData
	 * @param object
	 * @return String ��������
	 */
	public String getValue(Map<String, String> excelData, String object) {
		return excelData.get(object.toUpperCase());
	}

	/**
	 * 
	 * @����: ���뷵����Ϣ������#{str}Ϊʵ��ֵ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-2 ����02:12:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param excelData
	 *            ������
	 * @param object
	 *            ԭ��Ϣ
	 * @return String ��������
	 */
	public String compileMessage(Map<String, String> excelData, String object) {
		StringBuffer sb = new StringBuffer();
		String[] objects = object.split("#");
		String v;
		int start;
		int end;
		for (String p : objects) {
			start = p.indexOf("{");
			end = p.lastIndexOf("}");
			if (start >= 0 && end >= 0) {
				v = p.substring(start + 1, end);
				sb.append(getValue(excelData, v));
				sb.append(p.substring(end + 1, p.length()));
			} else {
				sb.append(p);
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * @����: ��ȡ���ò���excel�������ж�Ӧ��ֵ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-31 ����11:49:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 *            map(��������,��Ӧexcle��ǰ����ֵ)
	 * @return Map<String,String> ��������
	 */
	public Map<String, String> getParamExcleValue(List<ImportModel> data) {
		Map<String, String> map = new HashMap<String, String>();
		for (ImportModel im : data) {
			map.put(im.getZdm(), im.getValue().toString());
		}
		return map;
	}

	/**
	 * 
	 * @����: ��Ӵ�����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-31 ����04:39:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param i
	 *            ���������±�
	 * @param message
	 *            ������Ϣ void ��������
	 */
	public void addError(int i, String message) {
		List<String[]> er = getError();
		String[] mes;
		if (null != er && er.size() > 0) {
			mes = er.get(i);
			mes[mes.length - 1] = mes[mes.length - 1] + "\n" + message;
			er.set(i, mes);
		} else {
			mes = dataSource.get(i);
			mes = Arrays2.addObject(mes, message);
			er.add(mes);
		}
		// this.success.remove(i);
		// ��¼����ҵ����������� index������ֱ��ɾ����ѭ����δ����
		deleteIndex.add(i);
		this.error = er;
	}
	public void addErrorData(int i, String message, List<String[]> succeedExcelDataList) {
		if(deleteIndex.contains(i)){
			return;
		}
		List<String[]> er = getError();
		String[] mes = succeedExcelDataList.get(i);
		mes = Arrays2.addObject(mes, message);
		er.add(mes);
		// ��¼����ҵ����������� index������ֱ��ɾ����ѭ����δ����
		deleteIndex.add(i);
		this.error = er;
	}

	/**
	 * @return the success
	 */
	public List<List<ImportModel>> getSuccess() {
		return success;
	}

	/**
	 * @return the error
	 */
	public List<String[]> getError() {
		return error;
	}

	/**
	 * @return the succeedExcelDataList
	 */
	public List<String[]> getSucceedExcelDataList() {
		return succeedExcelDataList;
	}
	/**
	 * @return the dataSource
	 */
	public List<String[]> getDataSource() {
		return dataSource;
	}

	/**
	 * 
	 * @����: ��ʼ����֤����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-2 ����11:59:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param excelObject
	 */
	private void init(HashMap<String, Object> excelObject) {
		if (null == excelObject) {
			throw new RuntimeException("excel����Ϊ�գ�����!");
		}
		this.success = (List<List<ImportModel>>) excelObject.get("succeedList");
		this.succeedExcelDataList = (List<String[]>) excelObject.get("succeedExcelDataList");
		this.error = (List<String[]>) excelObject.get("errorList");
		this.dataSource = (List<String[]>) excelObject.get("dataSource");
		this.excelObject = excelObject;
	}

	// ��չ����
	public List<List<ImportModel>> businessExcute(
			HashMap<String, Object> excelObject) {
		init(excelObject);
		List<List<ImportModel>> su = getSuccess();
		List<String[]> succeedExcelDataList = getSucceedExcelDataList();
		// ���е�������һ�������֤
		checkAll(su, succeedExcelDataList);
		List<String[]> er = getError();
		su = getSuccess();
		succeedExcelDataList = getSucceedExcelDataList();
		formartDataUtil(su, succeedExcelDataList, er);
		// ��һ����֤ǰ��ȡ��������
		su = getSuccess();
		succeedExcelDataList = getSucceedExcelDataList();
		
		String message = _YZTG;
		// ����ҵ���ʽ��
		// ��֤����������������ڷ��Ž������������ʾ
		// ��ȡ������õ������
		String param = getParamMessage(this.getKzdm(), this.getDrmkdm());
		int i = 0;
		for (List<ImportModel> data : su) {
			message = check(param, data);
			// ��֤û��ͨ����Ӵ�����Ϣ
			if (!_YZTG.equals(message)) {
				addErrorData(i, message, succeedExcelDataList);
			}
			i++;
		}
		er = getError();
		su = getSuccess();
		succeedExcelDataList = getSucceedExcelDataList();
		formartData(su, succeedExcelDataList, er);
		excelObject = this.excelObject;
		return getSuccess();
	}
	/** 
	 * ѧ�����ݹ��ˣ�������
	 */
	public List<List<ImportModel>> xhCheck(HashMap<String, Object> excelObject, User user) {
		init(excelObject);
		List<List<ImportModel>> su = getSuccess();
		List<String[]> succeedExcelDataList = getSucceedExcelDataList();
		int i = 0;
		for (List<ImportModel> data : su) {
			String message = _YZTG;
			Map<String, String> map = getParamExcleValue(data);
			String xh = map.get("XH");
			if(!StringUtil.isNull(xh)){
				// Ȩ�޹���
				String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
				StringBuffer sb = new StringBuffer();
				sb.append(" select count(1) num from ( ");
				sb.append(" select a.xh,a.xn,c.bmdm xydm,b.bjdm from XG_PJPY_NEW_CPMDB a left join (select zydm,bjdm from bks_bjdm) b on a.bjdm=b.bjdm left join (select zydm,bmdm from bks_zydm) c on b.zydm=c.zydm ");
				sb.append(" ) t where t.xh=? and exists (select 1 from xg_pjpy_new_csszb where rownum =1 and xn=t.xn) ");
				sb.append(searchTjByUser);
				String num = DAO.getInstance().getOneRs(sb.toString(), new String[] { xh }, "num");
				if ("0".equals(num)) {
					message = "��ѧ����������ѧԺ�����ܵ��룡";
				}
			}
			// ��֤û��ͨ����Ӵ�����Ϣ
			if (!_YZTG.equals(message)) {
				addErrorData(i, message, succeedExcelDataList);
			}
			i++;
		}
		List<String[]> er = getError();
		su = getSuccess();
		succeedExcelDataList = getSucceedExcelDataList();
		formartDataUtil(su, succeedExcelDataList, er);
		excelObject = this.excelObject;
		return getSuccess();
	}

	public List<ImportModel> businessInsertData(ImportModel model,
			List<ImportModel> importColumnList) {
				return importColumnList;
		//model.getDrbm();������
		// Ĭ�ϲ����Ĳ��������Ϣ
	}

	/**
	 * 
	 * @����: ��չ��֤����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-2 ����11:49:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param message
	 *            ��ʾ��Ϣ
	 * @param data
	 *            ��֤������
	 * @return ��trueΪͨ��������Ϊ���ص���ʾ��Ϣ�� String ��������
	 */
	protected abstract String check(String message, List<ImportModel> data);
	/**
	 * ��չ��֤���������е�������һ�������֤��
	 * Ĭ�ϲ����κ�ʵ��,���������Ҫ��д
	 */
	protected void checkAll(List<List<ImportModel>> succeedList, List<String[]> succeedExcelDataList){
	}

	/**
	 * @return the drmkdm
	 */
	public String getDrmkdm() {
		return drmkdm;
	}

	/**
	 * @param drmkdmҪ���õ�
	 *            drmkdm
	 */
	public void setDrmkdm(String drmkdm) {
		this.drmkdm = drmkdm;
	}

	/**
	 * @return the kzdm
	 */
	public String getKzdm() {
		return kzdm;
	}

	/**
	 * @param kzdmҪ���õ�
	 *            kzdm
	 */
	public void setKzdm(String kzdm) {
		this.kzdm = kzdm;
	}
}
