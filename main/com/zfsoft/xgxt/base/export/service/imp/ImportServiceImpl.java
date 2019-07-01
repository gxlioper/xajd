package com.zfsoft.xgxt.base.export.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.action.ValueStack;
import com.zfsoft.xgxt.base.export.business.BusinessExtend;
import com.zfsoft.xgxt.base.export.business.IBaseBusiness;
import com.zfsoft.xgxt.base.export.business.imp.dtjs.DtjsRule;
import com.zfsoft.xgxt.base.export.business.imp.pjjxsq.PjjxsqRule;
import com.zfsoft.xgxt.base.export.business.imp.system.XhRule;
import com.zfsoft.xgxt.base.export.business.imp.yhzgl.YhzglRule;
import com.zfsoft.xgxt.base.export.dao.IImportDao;
import com.zfsoft.xgxt.base.export.dao.imp.ImportDao;
import com.zfsoft.xgxt.base.export.defualt.BaseDefualtData;
import com.zfsoft.xgxt.base.export.excel.ExcelUtils;
import com.zfsoft.xgxt.base.export.excel.imp.ImportInitParameter;
import com.zfsoft.xgxt.base.export.excel.imp.ImportJdbcTypeFormat;
import com.zfsoft.xgxt.base.export.excel.template.ATemplateBuilder;
import com.zfsoft.xgxt.base.export.excel.template.ImportTemplateBuilder;
import com.zfsoft.xgxt.base.export.model.ImportModel;
import com.zfsoft.xgxt.base.export.service.IImportService;
import com.zfsoft.xgxt.base.export.validator.ValidatorUtils;
import com.zfsoft.xgxt.base.export.validator.rules.NotEmptyRule;
import com.zfsoft.xgxt.base.util.dbf.DBFReader;
import com.zfsoft.xgxt.base.util.dbf.DBFWriter;
import com.zfsoft.xgxt.base.util.dbf.JDBField;

/**
 * ��������
 * 
 * @author Jiangdong.Yi
 * 
 */
public class ImportServiceImpl extends BaseServiceImpl<ImportModel, IImportDao>
		implements IImportService {
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public ImportServiceImpl() {
		this.setDao(new ImportDao());
	}

	/**
	 * ��֤����Դ <li>���ݽṹ hashMap String[]</li>
	 */
	private Map<String, Object> vDataSource = null;
	/**
	 * ��֤����
	 */
	private Map<String, Object> validatorParame = null;
	/**
	 * �ϲ���֤�б�
	 */
	private List<ImportModel> uniteValidatorList = null;
	/**
	 * �ϲ���ֵ֤
	 */
	private Map<String, Object> uniteValidatorValue = null;

	public Object getValidatorParame(String vRuleName) {
		if (StringUtils.isNull(vRuleName)) {
			return null;
		}
		return validatorParame.get(vRuleName);
	}

	public void setValidatorParame(String vRuleName, Object objParame) {
		this.validatorParame.put(vRuleName, objParame);
	}

	public Map<String, Object> getvDataSource() {
		return vDataSource;
	}

	public void setvDataSource(Map<String, Object> vDataSource) {
		this.vDataSource = vDataSource;
	}

	public Map<String, Object> getValidatorParame() {
		return validatorParame;
	}

	public void setValidatorParame(Map<String, Object> validatorParame) {
		this.validatorParame = validatorParame;
	}

	/**
	 * ������֤
	 */
	private void createValidator() {
		// �����µĲ�������
		this.validatorParame = new HashMap<String, Object>();
		this.vDataSource = new HashMap<String, Object>();
		this.uniteValidatorList = new ArrayList<ImportModel>();
		this.uniteValidatorValue = new HashMap<String, Object>();
	}

	/**
	 * ���
	 */
	private void cleanValidator() {
		// ע���µĲ�������
		// this.validatorParame = null;
		// this.vDataSource = null;
		// this.uniteValidatorList = null;
		// this.uniteValidatorValue = null;
	}

	/**
	 * ��ʼ����֤����Դ <li>��ֻ����Ψһ��֤</li>
	 */
	private void initVDataSource(List<String[]> dataSource,
			List<ImportModel> importModelList) {
		if (dataSource == null || importModelList == null) {
			return;
		}
		if (this.vDataSource == null) {
			this.vDataSource = new HashMap<String, Object>();
		}
		String[] columnValue = null;
		// �����������Դ�б� ת�� �����б���
		for (int i = 0; i < importModelList.size(); i++) {
			ImportModel im = getUniteValidatorImportModel(importModelList
					.get(i));
			// ���˲���Ψһ��֤
			if (im == null) {
				continue;
			}

			// ����Ǻϲ���֤�� �������ͬ
			if (this.vDataSource.get(im.getYzcs()) != null) {
				columnValue = (String[]) this.vDataSource.get(im.getYzcs());
			} else {
				columnValue = new String[dataSource.size()];
			}

			// ��ȡ������
			for (int j = 0; j < dataSource.size(); j++) {
				if (columnValue[j] == null || "".equals(columnValue[j])) {
					columnValue[j] = dataSource.get(j)[i];
				} else {
					columnValue[j] = columnValue[j] + dataSource.get(j)[i];
				}
			}
			// ʹ����֤������key ֻ����Ψһ��֤
			this.vDataSource.put(im.getYzcs(), columnValue);
		}
	}

	/**
	 * ��ȡ��֤����
	 * 
	 * @param importModelList
	 * @return
	 */
	private ImportModel getUniteValidatorImportModel(ImportModel importModel) {
		if (importModel == null) {
			return null;
		}

		ImportModel im = null;
		// ����Ǻϲ���֤
		im = getUniteItem(importModel);
		if (im != null) {
			return im;
		}

		if (!StringUtils.isNull(importModel.getYzlmc())
				&& importModel.getYzlmc().indexOf(RULE_IMPORTUNIQUERULE) > 0) {
			// TODO �˴������Ż�����Ϊÿһ�е���֤����һ���ģ����Կ����ڴ���

			String[] yzlmcs = importModel.getYzlmc().split(",");
			String[] yzlbms = importModel.getYzlbm().split(",");
			String[] yzcss = importModel.getYzcs().split(",");
			String[] yzmcs = importModel.getYzmc().split(",");
			String[] yzsms = importModel.getYzsm().split(",");

			// ������ǰ��֤����
			im = new ImportModel();
			copyImportModelProperties(im, importModel);
			// �滻����
			for (int i = 0; i < yzlmcs.length; i++) {
				if (RULE_IMPORTUNIQUERULE.equals(yzlmcs[i])) {
					im.setYzlmc(yzlmcs[i]);
					im.setYzlbm(yzlbms[i]);
					im.setYzcs(yzcss[i]);
					im.setYzmc(yzmcs[i]);
					im.setYzsm(yzsms[i]);
					break;
				}
			}
			return im;
		}

		return im;
	}

	/**
	 * ��ȡ����� �б���ݵ���ģ�����
	 * 
	 * @param drmkdm
	 * @return
	 */
	public List<ImportModel> getImportTableListByDrmkdm(String drmkdm) {
		if (StringUtils.isNull(drmkdm)) {
			return null;
		}
		return dao.getImportTableListByDrmkdm(drmkdm);
	}

	/**
	 * ��ȡ��֤�ֶ��б� ���ݵ���ģ�����͵������
	 * 
	 * @param drmkdm
	 * @param drbm
	 * @return
	 */
	public List<ImportModel> getValidateColumnList(String drmkdm, String drbm) {
		if (StringUtils.isNull(drmkdm) || StringUtils.isNull(drbm)) {
			return null;
		}
		ImportModel importModel = new ImportModel();
		importModel.setDrmkdm(drmkdm);
		importModel.setDrbm(drbm);
		return dao.getValidateColumnListByDrmkdmAndDrbm(importModel);
	}

	/**
	 * ��ȡ�����ֶ��б� ���ݵ���ģ�����͵������
	 * 
	 * @param drmkdm
	 * @param drbm
	 * @return
	 */
	public List<ImportModel> getImportColumnList(String drmkdm, String drbm) {
		if (StringUtils.isNull(drmkdm) || StringUtils.isNull(drbm)) {
			return null;
		}
		ImportModel importModel = new ImportModel();
		importModel.setDrmkdm(drmkdm);
		importModel.setDrbm(drbm);
		return dao.getImportColumnListByDrmkdmAndDrbm(importModel);
	}

	/**
	 * ��ȡ����ģ��
	 * 
	 * @param drmkdm
	 * @param drbm
	 * @return
	 */
	public File getImportTemplate(ImportModel importModel ) throws Exception {
		String drmkdm=null;
		String drbm = null;
		String drwjgs = null;
		if(importModel != null){
			drmkdm = importModel.getDrmkdm();
			drbm = importModel.getDrbm();
			drwjgs = importModel.getDrwjgs();
		}
		if (StringUtils.isNull(drmkdm) || StringUtils.isNull(drbm)) {
			return null;
		}
		// �����µ���֤��������
		createValidator();

		// ��ȡ��֤�ֶ��б�
		List<ImportModel> list = getValidateColumnList(drmkdm, drbm);
		List<String[]> strList = importModelListToStringArrayList(list);
		List<String[]> postilList = getValidatePostil(drmkdm, drbm);

		// ��ȡģ��·��
		StringBuffer path = new StringBuffer(ValueStack
				.getBasePath(IMPORT_TEMP_DIRPATH));
		path.append("\\");
		path.append(IMPORT_TEMPLATE);
		path.append("_");
		path.append(drmkdm);
		path.append(".");
		if(drwjgs != null){
			path.append(drwjgs);
		}else{
			path.append(IMPORT_SUFFLX);
		}
		// ��������ģ��
		ATemplateBuilder templateBuilder = new ImportTemplateBuilder(postilList);
		//982 ����ģ�� Ĭ����������
		BaseDefualtData bdd=new BaseDefualtData();
		strList.addAll(bdd.getDefualtData(drmkdm));

		File file = null;
		if(drwjgs != null && drwjgs.equals("dbf")){//dbf�ļ�����
			file = createDbf(list, path.toString());
		}else{
			file = ExcelUtils.createExcel(strList, path.toString(),
					templateBuilder);
		}

		return file;
	}
	
	/*
	 * �ж��ļ���ʽ
	 */
	private String getWjgs(String fileName){
		String drwjgs = null;//�����ļ���ʽ
		if(fileName != null){
			String[] fileNames = fileName.split("[.]");
			if(fileNames != null && fileNames.length > 0){
				drwjgs = fileNames[fileNames.length - 1];
			}
		}
		if(drwjgs != null){
			drwjgs = drwjgs.toLowerCase();
		}
		return drwjgs;
	}
	/*
	 * ����dbf�ļ�
	 */
	private File createDbf(List<ImportModel> list, String path)
			throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			makeDir(file.getParentFile());
		}
		FileOutputStream os = new FileOutputStream(file);

		JDBField[] ajdbfield = new JDBField[list.size()];
		
		ImportModel importModel = null;

		List<String> tableNameList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			importModel = list.get(i);
			if(!tableNameList.contains(importModel.getDrbm())){
				tableNameList.add(importModel.getDrbm());
			}
		}
		List<HashMap<String, String>> tableColumnsList = new ArrayList<HashMap<String,String>>();
		for (String tableNameTemp : tableNameList) {
			tableColumnsList.addAll(dao.getTableColumns(tableNameTemp));
		}
		for (int i = 0; i < list.size(); i++) {
			importModel = list.get(i);
			String dataLength = null;
			for (HashMap<String, String> hashMap : tableColumnsList) {
				String columnName = hashMap.get("column_name");
				if(importModel.getZdm() != null && importModel.getZdm().trim().equalsIgnoreCase(columnName)){
					dataLength = hashMap.get("data_length");
					break;
				}
			}
			int iDataLength = 20;
			if(dataLength != null){
				try {
					iDataLength = Integer.parseInt(dataLength);
				} catch (Exception e) {
				}
			}
			ajdbfield[i] = new JDBField(importModel.getDrmbzdmc(), 'C', iDataLength, 0);
		}
		DBFWriter dbfwriter = new DBFWriter(os, ajdbfield);

		dbfwriter.close();
		return file;
	}
	
	private void makeDir(File file) {
		if (!file.getParentFile().exists()) {
			makeDir(file.getParentFile());
		}
		file.mkdir();
	}

	/**
	 * ��������
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> importData(ImportModel model, User user)
			throws Exception {
		if (model == null || model.getImportFile() == null
				|| StringUtils.isNull(model.getDrmkdm())
				|| StringUtils.isNull(model.getDrbm())) {
			return null;
		}
		// �����µ���֤��������
		createValidator();
		InputStream is = null;
		String fileName = null;
		if(model.getDrms() != null && model.getDrms().equals("1")){
			File file = model.getUpdateFile();
			is = new FileInputStream(file);
		}else{
			FormFile ff = model.getImportFile();
			is = ff.getInputStream();
			fileName = ff.getFileName();
		}

		// ��ȡ��������
		List<String[]> dataList = null;
		String drwjgs = getWjgs(fileName);
		if(drwjgs != null && drwjgs.equals("dbf")){//dbf�ļ�����
			DBFReader dbfreader = new DBFReader(is);
			dataList = dbfreader.getDataList();
		}else{
			dataList = ExcelUtils.getDataList(is);
		}
		if (dataList == null || dataList.size() <= 1) {
			return null;
		}

		// ��ȡ��֤�ֶ��б�
		List<ImportModel> validatorColumnList = getValidateColumnList(model
				.getDrmkdm(), model.getDrbm());

		// ��ȡ�ֶΰ󶨵���֤
		List<ImportModel> validatorList = getColumnListUniteValidate(model
				.getDrmkdm(), model.getDrbm());

		// ��ȡʵ�ʵ����ľ�����
		List<ImportModel> importColumnList = getImportColumnList(model
				.getDrmkdm(), model.getDrbm());

		// ��ʽ����������Դ ���Ż�
		dataList = formatImportData(dataList, validatorColumnList);

		// ��֤���ǰ������֤������ȫ��
		initVDataSource(dataList, validatorList);

		// ��֤���
		HashMap<String, Object> vReslut = validateData(model,dataList, validatorList,
				importColumnList);

		// ͳ����֤���
		ImportModel importModel = new ImportModel();
		importModel.setDrzs(String.valueOf(dataList.size()));

		// ������ȷ������
		List<List<ImportModel>> succeedList = (List<List<ImportModel>>) vReslut
				.get("succeedList");
		//ҵ����չ
		IBaseBusiness bb = getBaseBusiness(model);
		if(bb != null && (bb instanceof PjjxsqRule)){ // ��ʱֻ���������뿪��
			// ============ ѧ�����ݹ��� begin=========
			BusinessExtend xhCheck = new XhRule(model.getDrmkdm());
			succeedList = xhCheck.xhCheck(vReslut, user);
			// ============ ѧ�����ݹ��� end=========
		}
		if(bb != null){
			succeedList = bb.businessExcute(vReslut);
			importColumnList = bb.businessInsertData(importModel, importColumnList);
		}
		if (succeedList != null && succeedList.size() > 0) {
			// �ɹ���
			importModel.setCgs(String.valueOf(succeedList.size()));
			if(model.getDrms() != null && model.getDrms().equals("1")){
				if(bb != null && (bb instanceof DtjsRule)){ // ���Ž��趨�ơ����¡�
					new ImportDao().updateImportData_dtjs(succeedList, model.getDrbm(), validatorList);
					// �ɹ��� = ���� - ʧ����
					importModel.setCgs(String.valueOf(dataList.size() - ((List<String[]>) vReslut.get("errorList")).size()));
				}else{
					updateImportData(succeedList, model.getDrbm(), validatorList);
				}
			}else{
				if(bb != null && (bb instanceof DtjsRule)){ // ���Ž��趨�ơ����ӡ�
					// �ɹ��� = ���� - ʧ����
					importModel.setCgs(String.valueOf(dataList.size() - ((List<String[]>) vReslut.get("errorList")).size()));
				}
				if(bb != null && (bb instanceof YhzglRule)){ // ���û����롾���Ի���
					insertImportData_yhzgl(succeedList, model.getDrbm(), importColumnList);
				}else{ // ͨ��
					insertImportData(succeedList, model.getDrbm(), importColumnList);
				}
			}
		} else {
			// �ɹ���
			importModel.setCgs("0");
		}

		List<String[]> errorList = (List<String[]>) vReslut.get("errorList");
		if (errorList != null && errorList.size() > 0) {
			// ������
			importModel.setCws(String.valueOf(errorList.size()));
		} else {
			// ������
			importModel.setCws("0");
		}

		// ���뵼����
		vReslut.put("importModel", importModel);
		return vReslut;
	}

	/**
	 * ��ȡ�����������Excel
	 * 
	 * @param drmkdm
	 * @param drbm
	 * @param errorList
	 * @return
	 * @throws Exception
	 */
	public File getImportErrortData(String drmkdm, String drbm,
			List<String[]> errorList, User user) throws Exception {
		if (StringUtils.isNull(drmkdm) || StringUtils.isNull(drbm)
				|| errorList == null || errorList.size() == 0) {
			return null;
		}
		// �����µ���֤��������
		createValidator();

		// ��ȡ����ģ��
		String[] importTempletStr = getErrorTemplet(drmkdm, drbm);
		// ���ñ�ע
		List<String[]> postilList = getValidatePostil(drmkdm, drbm);
		// ��������ģ��
		ATemplateBuilder templateBuilder = new ImportTemplateBuilder(postilList);
		// ��ʽ���������ݣ� ����ģ���ͷ
		if (errorList != null && errorList.size() > 0) {
			errorList.add(0, importTempletStr);
		}

		// ���������ļ�·��
		StringBuffer filePath = new StringBuffer(ValueStack
				.getBasePath(IImportService.IMPORT_TEMP_DIRPATH));
		filePath.append("\\");
		filePath.append(IImportService.IMPORT_ERROR);
		filePath.append("_");
		filePath.append(drmkdm);
		// ������������ļ����Ƽ����û���
		if (!StringUtils.isNull(user.getUserName())) {
			filePath.append("_");
			filePath.append(user.getUserName());
		}
		filePath.append(".");
		filePath.append(IMPORT_SUFFLX);

		ExcelUtils.createExcel(errorList, filePath.toString(), templateBuilder);

		return null;
	}

	/**
	 * ����ģ��listת�� �ַ��������б�, ֻת���������Ƶ�String[]
	 * 
	 * @param modelList
	 * @return
	 */
	private List<String[]> importModelListToStringArrayList(
			List<ImportModel> modelList) {
		if (modelList == null || modelList.size() == 0) {
			return null;
		}
		List<String[]> strList = new ArrayList<String[]>();
		String[] str = new String[modelList.size()];
		for (int i = 0; i < modelList.size(); i++) {
			str[i] = modelList.get(i).getDrmbzdmc();
		}
		strList.add(str);
		return strList;
	}

	/**
	 * ��ȡ�����ģ��ͷ
	 * 
	 * @param drmkdm
	 * @param drbm
	 * @return
	 */
	private String[] getErrorTemplet(String drmkdm, String drbm) {
		if (StringUtils.isNull(drmkdm) || StringUtils.isNull(drbm)) {
			return null;
		}
		// ��ȡ��֤�ֶ��б�
		List<ImportModel> importTemplet = getValidateColumnList(drmkdm, drbm);
		List<String[]> importTempletStr = importModelListToStringArrayList(importTemplet);
		if (importTempletStr != null && importTempletStr.size() > 0) {
			String[] templet = new String[importTempletStr.get(0).length + 1];
			for (int i = 0; i < importTempletStr.get(0).length; i++) {
				templet[i] = importTempletStr.get(0)[i];
			}
			templet[importTempletStr.get(0).length] = "����ע";
			return templet;
		} else {
			return null;
		}
	}

	/**
	 * ��֤����
	 * 
	 * @param dataSource
	 *            ����Դ
	 * @param validateRule
	 *            ��֤�к���֤����
	 * @return
	 */
	private HashMap<String, Object> validateData(ImportModel model,List<String[]> dataSource,
			List<ImportModel> validateRule, List<ImportModel> importColumnList)
			throws Exception {
		if (dataSource == null || validateRule == null
				|| importColumnList == null) {
			return null;
		}
		// ��֤���
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		// ��֤���
		String vResult = "";
		// ��֤ͨ�������б�
		List<List<ImportModel>> succeedList = new ArrayList<List<ImportModel>>();
		// ��֤ͨ��ԭʼ�����б�����BusinessExtend��֤��ͨ��ʱ,���ɴ����ļ���
		List<String[]> succeedExcelDataList = new ArrayList<String[]>();
		// ��֤��ͨ�������б�
		List<String[]> errorList = new ArrayList<String[]>();
		// ��ʱ��������
		String[] tempErrorData = null;
		// ��ʱԭʼ�����б�����BusinessExtend��֤��ͨ��ʱ,���ɴ����ļ���
		String[] tempSucceedExcelData = null;
		// ��ʱ��ȷ���ݼ���
		List<ImportModel> tempSucceedData = null;
		// ��ʱ��ȷ����
		ImportModel importModel = null;
		// ��֤�����б�
		String[] vRule = null;
		// ��֤����
		String[] vPackage = null;
		// ��֤�б�� true��ͨ����false����ͨ��
		boolean rowSign = true;
		// ��֤�������Ϣ
		StringBuffer errorMessage = null;
		// �ϲ�����
		int uniteNum = 0;
		// �ɹ����ݼ�����
		int succeedTally = -1;
		for (int i = 0; i < dataSource.size(); i++) {
			// �����õ���
			tempErrorData = new String[dataSource.get(i).length + 1];
			tempSucceedExcelData = new String[dataSource.get(i).length];
			tempSucceedData = new ArrayList<ImportModel>();
			// ���ñ��
			rowSign = true;
			// �����֤��Ϣ
			errorMessage = new StringBuffer();
			// ����ϲ�����
			uniteNum = 0;
			// ����ɹ����ݼ�����
			succeedTally = 0;
			// ��֤value
			StringBuffer valueB = null;
			// ��ʼ����һ�еĺϲ���֤����
			this.uniteValidatorValue = new HashMap<String, Object>();
			// �����õ���Ԫ��
			for (int j = 0; j < dataSource.get(i).length; j++) {
				// ��ȡ��֤����
				vRule = validateRule.get(j).getYzlmc() != null ? validateRule
						.get(j).getYzlmc().split(",") : new String[0];
				vPackage = validateRule.get(j).getYzlbm() != null ? validateRule
						.get(j).getYzlbm().split(",")
						: new String[0];

				// ��������װ��������
				if(dataSource.get(i)[j] != null){
					valueB = new StringBuffer(dataSource.get(i)[j]);
				}else{
					valueB = new StringBuffer();
				}

				// ����֤�������ݴ�������
				tempErrorData[j] = valueB.toString();
				tempSucceedExcelData[j] = valueB.toString();
				
				// ��֤�����Ƿ���Ҫ�ϲ���֤
				if (getUniteItem(validateRule.get(j)) != null) {
					setUniteValidatorValue(validateRule.get(j), valueB
							.toString());
				}

				ImportModel validateImportModel = new ImportModel();
				validateImportModel = validateRule.get(j);
				validateImportModel.setDrms(model.getDrms());
				// ����֤
				vResult = validateValue(validateImportModel, valueB, vPackage,
						vRule);
				if (!StringUtils.isNull(vResult)) {
					// ��֤��ͨ��
					errorMessage.append(validateRule.get(j).getDrmbzdmc());
					errorMessage.append(":");
					errorMessage.append(vResult);
					errorMessage.append("\n");
					rowSign = false;
				} else {
					// ��֤ͨ��
				}

				// ������֤�ɹ������ݼ�����
				if (uniteNum == 0) {
					uniteNum = Integer.valueOf(
							importColumnList.get(succeedTally).getHbdrs())
							.intValue();
				}
				// ������������ɼ��ٺϲ���
				uniteNum--;


				// ������ȷ��ֵtempSucceedData
				addSucceedData(tempSucceedData, validateRule.get(j), valueB
						.toString());

				// ������֤�ɹ������ݼ����� ������������
				if (uniteNum == 0) {
					succeedTally++;
				}
			}
			// �ϲ�������֤
			String vUniteMessage = validateUniteValue();
			// ��֤�����Ϊ�� ������ ����¼����״̬
			if (!StringUtils.isNull(vUniteMessage)) {
				errorMessage.append(vUniteMessage);
				rowSign = false;
			}

			// ����������֤
			if (rowSign) {
				// ͨ��
				succeedList.add(tempSucceedData);
				succeedExcelDataList.add(tempSucceedExcelData);
			} else {
				// ��ͨ��
				tempErrorData[dataSource.get(i).length] = errorMessage
						.toString();
				errorList.add(tempErrorData);
			}
		}
		hashMap.put("dataSource", dataSource);
		hashMap.put("succeedList", succeedList);
		hashMap.put("succeedExcelDataList", succeedExcelDataList);
		hashMap.put("errorList", errorList);
		return hashMap;
	}

	/**
	 * ������ȷ����
	 * 
	 * @param tempSucceedData
	 * @param importModel
	 */
	private void addSucceedData(List<ImportModel> tempSucceedData,
			ImportModel importModel, Object value) throws Exception {
		if (tempSucceedData == null || importModel == null) {
			return;
		}
		StringBuffer val = new StringBuffer("");

		for (int i = 0; i < tempSucceedData.size(); i++) {
			if (tempSucceedData.get(i).getZdm().equals(importModel.getZdm())) {
				// ƾ����ֵ
				val.append(tempSucceedData.get(i).getValue().toString());
				val.append(value);
				// ������ֵ
				tempSucceedData.get(i).setValue(
						ImportJdbcTypeFormat.parameterFormat(val.toString(),
								importModel.getZdlx()));
				return;
			}
		}

		// ����������У�������
		ImportModel im = new ImportModel();
		im.setZdlx(importModel.getZdlx());
		im.setZdm(importModel.getZdm());
		im.setValue(ImportJdbcTypeFormat.parameterFormat(value.toString(),
				importModel.getZdlx()));
		tempSucceedData.add(im);
	}

	/**
	 * ��ֵ֤
	 * 
	 * @param importModel
	 *            �������
	 * @param value
	 *            ��֤����
	 * @param vRuleObj
	 *            ��֤����
	 * @return
	 * 
	 *         private String validateValue(ImportModel importModel, String
	 *         value, String[] vRule)throws Exception { if(importModel == null
	 *         || vRule == null || vRule.length == 0){ return null; } // ��֤�������
	 *         StringBuffer vPackage = null; // ��֤����object���� Object vRuleObj =
	 *         null; // ��֤���� String[] yzcs=importModel.getYzcs().split(",");
	 * 
	 *         //��֤�����Ϣ StringBuffer resultMessage=new StringBuffer();
	 * 
	 *         //���������ʼ�� ImportInitParameter iip=new ImportInitParameter(); for
	 *         (int i = 0; i < vRule.length; i++) { // ��֤������� vPackage = new
	 *         StringBuffer("com.zfsoft.common.validator.rules.");
	 *         vPackage.append(vRule[i]); vRuleObj =
	 *         Class.forName(vPackage.toString()); //ִ����֤���� ��ǰ�Ǵ������Ͳ������� if
	 *         (!StringUtils.isNull(yzcs[i]) &&
	 *         !IMPORT_YZCS_DEFAULT.equals(yzcs[i])) { Object[]
	 *         parameters=iip.getInitParameterArray(vRule[i],yzcs[i]);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 *         
	 *         resultMessage.append(ValidatorUtils.validateValueByParameter(value
	 *         , parameters, vRuleObj)); }else{
	 *         resultMessage.append(ValidatorUtils.validateValue(value,
	 *         vRuleObj)); } } return resultMessage.toString(); }
	 */

	/**
	 * ��ֵ֤
	 * 
	 * @param importModel
	 *            �������
	 * @param value
	 *            ��֤����
	 * @param vPackage
	 *            ��֤����
	 * @param vRuleObj
	 *            ��֤����
	 * @return
	 */
	private String validateValue(ImportModel importModel, Object value,
			String[] vPackage, String[] vRule) throws Exception {
		if (importModel == null || vRule == null || vRule.length == 0) {
			return null;
		}
		// ��֤�������
		StringBuffer vPackages = null;
		// ��֤����object����
		Object vRuleObj = null;
		// ��֤����
		String[] yzcs = importModel.getYzcs().split(",");

		// ��֤�����Ϣ
		StringBuffer resultMessage = new StringBuffer();

		// ���������ʼ��
		ImportInitParameter iip = new ImportInitParameter(vDataSource);

		// ��֤����key
		String vParameKey = "";

		// ��֤�ò�����֤�Ƿ����
		Object[] parameters = null;
		for (int i = 0; i < vRule.length; i++) {
			// ��֤�������
			vPackages = new StringBuffer(vPackage[i]);
			//982 �Կչ����޶�Ӧ���� ������֤
			if(StringUtils.isNull(vPackage[i])||"null".equals(vPackages.toString())){
				continue;
			}
			vPackages.append(".");
			vPackages.append(vRule[i]);
			vRuleObj = Class.forName(vPackages.toString());

			// ������Ƿǿ���֤��������֤��ֵ�ǿյ�ʱ��ֱ��ͨ��������֤
			if (!NotEmptyRule.class.equals(vRuleObj)
					&& (value == null || StringUtils.isNull(value.toString()))) {
				continue;
			}
			// ִ����֤���� ��ǰ�Ǵ������Ͳ�������
			boolean yzFlag = false;
			if(importModel.getDrms() != null && importModel.getDrms().equals("1")){//�����²���
				if (!StringUtils.isNull(yzcs[i])
						&& !IMPORT_YZCS_DEFAULT.equals(yzcs[i])&& !RULE_IMPORTUNIQUERULE.equals(vRule[i])) {
					yzFlag = true;
				}
			}else{
				if (!StringUtils.isNull(yzcs[i])
						&& !IMPORT_YZCS_DEFAULT.equals(yzcs[i]) ) {
					yzFlag = true;
				}
			}
			if (yzFlag) {
				// ��֤�ò�����֤�Ƿ���� �˴�ʹ�������� key
				vParameKey = importModel.getDrmbzdmc() + "_" + vRule[i];
				parameters = (Object[]) getValidatorParame(vParameKey);
				if (parameters == null) {
					parameters = iip.getInitParameterArray(vRule[i], yzcs[i]);
					// ���ò�����ȫ�ֱ�����
					setValidatorParame(vParameKey, parameters);
				}

				resultMessage.append(ValidatorUtils.validateValueByParameter(
						value, parameters, vRuleObj));
			} else {
				resultMessage.append(ValidatorUtils.validateValue(value,
						vRuleObj));
			}
		}
		return resultMessage.toString();
	}

	/**
	 * * ���뵼������
	 * 
	 * @param dataList
	 *            �����б�
	 * @param tableName
	 *            ������
	 * @param columnList
	 *            �������б�
	 * @return
	 */
	private boolean insertImportData(List<List<ImportModel>> dataList,
			String tableName, List<ImportModel> columnList) {
		if (dataList == null || dataList.size() == 0
				|| StringUtils.isNull(tableName) || columnList == null
				|| columnList.size() == 0) {
			return false;
		}
		HashMap<String, Object> insertData = new HashMap<String, Object>();
		// ����
		insertData.put("tableName", tableName);

		// �ֶ��б�
		insertData.put("columnList", columnList);

		// ֵ�б�
		insertData.put("dataList", dataList);

		return dao.batchInsertData(insertData) > 0;
	}
	/**
	 * ���뵼������ ���û����롾���Ի���
	 */
	private boolean insertImportData_yhzgl(List<List<ImportModel>> dataList,
			String tableName, List<ImportModel> columnList) {
		if (dataList == null || dataList.size() == 0
				|| StringUtils.isNull(tableName) || columnList == null
				|| columnList.size() == 0) {
			return false;
		}
		HashMap<String, Object> insertData = new HashMap<String, Object>();
		// ����
		insertData.put("tableName", tableName);

		// �ֶ��б�
		insertData.put("columnList", columnList);

		// ֵ�б�
		insertData.put("dataList", dataList);

		return dao.batchInsertData_yhzgl(insertData);
	}
	
	/*
	 * ���µ��������
	 */
	private boolean updateImportData(List<List<ImportModel>> dataList,
			String tableName, List<ImportModel> columnList) {
		if (dataList == null || dataList.size() == 0
				|| StringUtils.isNull(tableName) || columnList == null
				|| columnList.size() == 0) {
			return false;
		}
		HashMap<String, Object> insertData = new HashMap<String, Object>();
		// ����
		insertData.put("tableName", tableName);

		// �ֶ��б�
		insertData.put("columnList", columnList);

		// ֵ�б�
		insertData.put("dataList", dataList);
		
		
		String yzlmc = null;
		String zdm = null;
		List<String> uniqueZdmList = new ArrayList<String>();
		for (ImportModel importModel : columnList) {
			zdm = importModel.getZdm();
			yzlmc = importModel.getYzlmc();
			if(yzlmc != null && yzlmc.contains(IImportService.RULE_IMPORTUNIQUERULE)){
				uniqueZdmList.add(zdm);
			}
		}
		boolean result = false;
		if(uniqueZdmList.size() > 0){
			insertData.put("uniqueZdmList", uniqueZdmList);
			result = dao.batchUpdate(insertData) > 0;
		}
		return result;
	}


	/**
	 * ��ʽ���������ݣ�ԭ�����û���������ģ�壬ʹ����ģ����ֶ����У�
	 * 
	 * @param dataList
	 * @param columnList
	 * @return
	 */
	private List<String[]> formatImportData(List<String[]> dataList,
			List<ImportModel> columnList) {
		if (dataList == null || dataList.size() == 0 || columnList == null
				|| columnList.size() == 0) {
			return null;
		}
		// ɾ����һ�б���
		if (dataList.size() > 0) {
			dataList.remove(0);
		}
		// ȥ�������пհ�
		List<String[]> impDataList = new ArrayList<String[]>();
		String[] data = null;
		String[] dtjlStrs = null;
		for (int i = 0; i < dataList.size(); i++) {
			dtjlStrs = dataList.get(i);
			data = new String[columnList.size()];
			for (int j = 0; j < columnList.size(); j++) {
				if(dtjlStrs.length > j){
					data[j] = dtjlStrs[j];
				}
			}
			impDataList.add(data);
		}
		return impDataList;
	}

	/**
	 * ��ȡ�ֶ��б� �ϲ���֤����(��','�ָ�)
	 * 
	 * @param model
	 * @return
	 */
	private List<ImportModel> getColumnListUniteValidate(String drmkdm,
			String drbm) throws Exception {
		if (StringUtils.isNull(drmkdm) || StringUtils.isNull(drbm)) {
			return null;
		}
		ImportModel importModel = new ImportModel();
		importModel.setDrmkdm(drmkdm);
		importModel.setDrbm(drbm);
		return uniteValidateInfo(dao
				.getValidateColumnListByDrmkdmAndDrbm(importModel), dao
				.getColumnValidateListByDrmkdmAndDrbm(importModel));
	}

	/**
	 * �ϲ���֤����
	 * 
	 * @param columnList
	 * @param vRuleList
	 * @return
	 */
	private List<ImportModel> uniteValidateRule(List<ImportModel> columnList,
			List<ImportModel> vRuleList) {
		if (columnList == null || vRuleList == null) {
			return columnList;
		}
		// �ϲ���
		String yzlmc = "";
		String yzcs = "";
		String yzmc = "";
		String yzsm = "";
		String yzlbm = "";

		for (int i = 0; i < columnList.size(); i++) {
			yzlmc = "";
			yzcs = "";
			yzmc = "";
			yzsm = "";
			yzlbm = "";

			for (int j = 0; j < vRuleList.size(); j++) {
				if (columnList.get(i).getDrmbzdmc().equals(
						vRuleList.get(j).getDrmbzdmc())) {
					if (StringUtils.isNull(yzlmc)) {
						yzlmc = vRuleList.get(j).getYzlmc();
						yzcs = vRuleList.get(j).getYzcs();
						yzmc = vRuleList.get(j).getYzmc();
						// ��֤˵�������滻
						if (StringUtils.isNull(vRuleList.get(j).getDrmkyzsm())) {
							yzsm = vRuleList.get(j).getYzsm();
						} else {
							yzsm = vRuleList.get(j).getDrmkyzsm();
						}
						yzlbm = vRuleList.get(j).getYzlbm();
					} else {
						yzlmc = yzlmc + "," + vRuleList.get(j).getYzlmc();
						yzcs = yzcs + "," + vRuleList.get(j).getYzcs();
						yzmc = yzmc + "," + vRuleList.get(j).getYzmc();

						// ��֤˵�������滻
						if (StringUtils.isNull(vRuleList.get(j).getDrmkyzsm())) {
							yzsm = yzsm + "," + vRuleList.get(j).getYzsm();
						} else {
							yzsm = yzsm + "," + vRuleList.get(j).getDrmkyzsm();
						}
						yzlbm = yzlbm + "," + vRuleList.get(j).getYzlbm();
					}
				}
			}

			columnList.get(i).setYzlmc(yzlmc);
			columnList.get(i).setYzcs(yzcs);
			columnList.get(i).setYzmc(yzmc);
			columnList.get(i).setYzsm(yzsm);
			columnList.get(i).setYzlbm(yzlbm);
		}
		return columnList;
	}

	/**
	 * �ϲ���֤��Ϣ
	 * 
	 * @param columnList
	 * @param vRuleList
	 * @return
	 */
	private List<ImportModel> uniteValidateInfo(List<ImportModel> columnList,
			List<ImportModel> vRuleList) throws Exception {
		if (columnList == null || vRuleList == null) {
			return columnList;
		}
		// �ϲ���
		String yzlmc = "";
		String yzcs = "";
		String yzmc = "";
		String yzsm = "";
		String yzlbm = "";

		for (int i = 0; i < columnList.size(); i++) {
			yzlmc = "";
			yzcs = "";
			yzmc = "";
			yzsm = "";
			yzlbm = "";

			for (int j = 0; j < vRuleList.size(); j++) {
				// �Ƿ��ǵ�ǰ��
				if (columnList.get(i).getDrmbzdmc().equals(
						vRuleList.get(j).getDrmbzdmc())) {
					// �ϲ�����֤ ��Ϊ�ϲ���֤���򲻼�����ͨ��֤����
					if (SFHBYZ_1.equals(vRuleList.get(j).getSfhbyz())) {
						setUniteValidatorList(vRuleList.get(j));
						continue;
					}

					// δ����֤��
					if (StringUtils.isNull(yzlmc)) {
						yzlmc = vRuleList.get(j).getYzlmc();
						yzcs = vRuleList.get(j).getYzcs();
						yzmc = vRuleList.get(j).getYzmc();
						// ��֤˵�������滻
						if (StringUtils.isNull(vRuleList.get(j).getDrmkyzsm())) {
							yzsm = vRuleList.get(j).getYzsm();
						} else {
							yzsm = vRuleList.get(j).getDrmkyzsm();
						}
						yzlbm = vRuleList.get(j).getYzlbm();
					} else {
						yzlmc = yzlmc + "," + vRuleList.get(j).getYzlmc();
						yzcs = yzcs + "," + vRuleList.get(j).getYzcs();
						yzmc = yzmc + "," + vRuleList.get(j).getYzmc();

						// ��֤˵�������滻
						if (StringUtils.isNull(vRuleList.get(j).getDrmkyzsm())) {
							yzsm = yzsm + "," + vRuleList.get(j).getYzsm();
						} else {
							yzsm = yzsm + "," + vRuleList.get(j).getDrmkyzsm();
						}
						yzlbm = yzlbm + "," + vRuleList.get(j).getYzlbm();
					}
				}
			}

			columnList.get(i).setYzlmc(yzlmc);
			columnList.get(i).setYzcs(yzcs);
			columnList.get(i).setYzmc(yzmc);
			columnList.get(i).setYzsm(yzsm);
			columnList.get(i).setYzlbm(yzlbm);
		}
		return columnList;
	}

	/**
	 * ��ȡ��֤��ע
	 * 
	 * @param drmkdm
	 * @param drbm
	 * @return
	 */
	private List<String[]> getValidatePostil(String drmkdm, String drbm)
			throws Exception {
		if (StringUtils.isNull(drmkdm) || StringUtils.isNull(drbm)) {
			return null;
		}
		List<ImportModel> impList = getColumnListUniteValidate(drmkdm, drbm);
		if (impList != null && impList.size() == 0) {
			return null;
		}
		List<String[]> list = new ArrayList<String[]>();
		String[] postil = null;
		StringBuffer pos = null;
		// ��֤˵��
		String[] yzsms = null;
		postil = new String[impList.size()];
		for (int i = 0; i < impList.size(); i++) {

			pos = new StringBuffer();
			if (!StringUtils.isNull(impList.get(i).getYzmc())) {
				yzsms = impList.get(i).getYzsm().split(",");
				for (int j = 0; j < yzsms.length; j++) {
					pos.append(impList.get(i).getDrmbzdmc());
					pos.append(":");
					pos.append(yzsms[j]);
					pos.append(";");
					pos.append("\n");
				}
			}
			postil[i] = pos.toString();
		}
		list.add(postil);
		return list;
	}

	/**
	 * ��ȡ��֤�����б�
	 * 
	 * @param parame
	 *            {tableName:"",columnName:"XXX,XXX"}
	 * @return
	 */
	public List<HashMap<String, String>> getValidateValueList(
			HashMap<String, String> parame) {
		if (parame == null) {
			return null;
		}
		return dao.getValidateValueList(parame);
	}

	/**
	 * ��ȡ��֤���ݵ�������
	 * 
	 * @param parame
	 *            {tableName:"",columnName:"XXX"}
	 * @return HashMap
	 */
	public HashMap<String, String> getValidateValueMap(
			HashMap<String, String> parame) {
		if (parame == null) {
			return null;
		}
		List<HashMap<String, String>> vData = dao.getValidateValueMap(parame);
		return listHashMapvalueToHashMapKey(vData, parame.get("columnName"));
	}

	/**
	 * ��list��hashMap��Value ת�г� hashMap��key
	 * 
	 * @return
	 */
	private HashMap<String, String> listHashMapvalueToHashMapKey(
			List<HashMap<String, String>> dataList, String parame) {
		if (dataList == null) {
			return null;
		}
		HashMap<String, String> hashMap = new HashMap<String, String>();
		for (int i = 0; i < dataList.size(); i++) {
			hashMap.put(getColumnValue(dataList.get(i), "COLUMNNAME"), getColumnValue(dataList.get(i), "COLUMNNAME"));
		}
		return hashMap;
	}
	/**
	 * 
	 * @����: ��ȡ����ֵ������ѧ��ͨ��jdbc����<�����ִ�Сд>��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-2 ����11:15:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data ����Դ(Map<String,String>)
	 * @param name ����
	 * @return
	 * String �������� 
	 */
	private String getColumnValue(HashMap<String, String> data,String name){
		String columnV=data.get(name);
		return StringUtils.isNull(columnV)? data.get(name.toLowerCase()):columnV;
	}
	/**
	 * ��ȡ�ϲ���֤��
	 * 
	 * @param importModel
	 *            ��֤model
	 * @return ������ڣ�model�������ڣ�null
	 */
	private ImportModel getUniteItem(ImportModel importModel) {
		if (importModel == null || uniteValidatorList.size() == 0) {
			return null;
		}
		ImportModel uniteModel = null;
		for (int i = 0; i < uniteValidatorList.size(); i++) {
			if (uniteValidatorList.get(i).getDrmbzdmc().indexOf(
					UNITESIGN + importModel.getDrmbzdmc() + UNITESIGN) > -1) {
				uniteModel = uniteValidatorList.get(i);
			}
		}
		return uniteModel;
	}

	/**
	 * ����Ϊ�ϲ���֤��
	 * 
	 * @param importModel
	 */
	private void setUniteValidatorList(ImportModel importModel)
			throws Exception {
		if (importModel == null) {
			return;
		}
		// �Ƿ�����Ӻϲ���
		if (uniteValidatorList.size() != 0) {
			for (int i = 0; i < uniteValidatorList.size(); i++) {
				// �����Ƿ���ͬһ����֤����
				if (uniteValidatorList.get(i).getYzcs().equals(
						importModel.getYzcs())) {
					uniteValidatorList.get(i).setDrmbzdmc(
							uniteValidatorList.get(i).getDrmbzdmc()
									+ importModel.getDrmbzdmc() + UNITESIGN);
					return;
				}
			}
		}

		ImportModel uniteItem = new ImportModel();
		// �򵥸�������
		copyImportModelProperties(uniteItem, importModel);

		// ��Ǻϲ���
		uniteItem.setDrmbzdmc(UNITESIGN + uniteItem.getDrmbzdmc() + UNITESIGN);
		uniteValidatorList.add(uniteItem);
	}

	/**
	 * ���úϲ���֤��ֵ
	 * 
	 * @param importModel
	 * @throws Exception
	 */
	private void setUniteValidatorValue(ImportModel importModel, String value)
			throws Exception {
		if (importModel == null) {
			return;
		}
		// ף����֤
		String uniteDrmbzdmc = UNITESIGN + importModel.getDrmbzdmc()
				+ UNITESIGN;

		// �ϲ���ֵ֤
		if (uniteValidatorList.size() != 0) {
			for (int i = 0; i < uniteValidatorList.size(); i++) {
				// �жϵ�ǰ�ֶ��Ƿ��Ǻϲ��� ��������"�ֶ�����"���ͺϲ�����ģ���ֶ������� ���ڵ�
				if ( uniteValidatorList.get(i).getDrmbzdmc().indexOf(
								uniteDrmbzdmc) > -1) {
					// �ж��Ƿ��Ѵ���uniteValidatorValue �����������ϲ�����
					if (uniteValidatorValue.containsKey(uniteValidatorList.get(
							i).getDrmbzdmc())) {
						uniteValidatorValue.put(uniteValidatorList.get(i)
								.getDrmbzdmc(), uniteValidatorValue
								.get(uniteValidatorList.get(i).getDrmbzdmc())
								+ value);
						return;
					} else {
						uniteValidatorValue.put(uniteValidatorList.get(i)
								.getDrmbzdmc(), value);
						return;
					}
				}
			}
		}
	}

	/**
	 * ��֤�ϲ�ֵ
	 * 
	 * @return
	 */
	private String validateUniteValue() throws Exception {
		if (uniteValidatorList == null || uniteValidatorList.size() == 0) {
			return "";
		}

		// ��֤����
		String[] vRule = null;
		// ��֤����
		String[] vPackage = null;
		// ��֤���
		String vResult = "";
		// ������Ϣ
		StringBuffer vMessage = new StringBuffer("");
		// ����ģ���ֶ�����
		String drmbzdmc = "";

		for (int i = 0; i < uniteValidatorList.size(); i++) {
			if (uniteValidatorValue.containsKey(uniteValidatorList.get(i)
					.getDrmbzdmc())) {
				// ��ȡ��֤����
				vRule = uniteValidatorList.get(i).getYzlmc() != null ? uniteValidatorList
						.get(i).getYzlmc().split(",")
						: new String[0];
				vPackage = uniteValidatorList.get(i).getYzlbm() != null ? uniteValidatorList
						.get(i).getYzlbm().split(",")
						: new String[0];
			}
			// ����֤
			vResult = validateValue(uniteValidatorList.get(i),
					this.uniteValidatorValue.get(uniteValidatorList.get(i)
							.getDrmbzdmc()), vPackage, vRule);
			if (!StringUtils.isNull(vResult)) {
				drmbzdmc = uniteValidatorList.get(i).getDrmbzdmc();

				// ��֤��ͨ��
				// ��ȡ UNITESIGN �ָ��
				vMessage.append(drmbzdmc.substring(
						drmbzdmc.indexOf(UNITESIGN) + UNITESIGN.length(),
						drmbzdmc.lastIndexOf(UNITESIGN)).replaceAll(UNITESIGN,
						"+"));
				vMessage.append(":");
				vMessage.append(vResult);
				vMessage.append("\n");
			}
		}

		return vMessage.toString();
	}

	/**
	 * ���Ƶ����������
	 * 
	 * @param dest
	 *            Ŀ�����
	 * @param orig
	 *            Դ����
	 */
	private void copyImportModelProperties(ImportModel dest, ImportModel orig) {
		if (dest == null || orig == null) {
			return;
		}
		// ���importModel �������ݿ����ڴ˴����� ��
		dest.setDryzbh(orig.getDryzbh());
		dest.setDrmkdm(orig.getDrmkdm());
		dest.setDrmkmc(orig.getDrmkmc());
		dest.setDrmkyzsm(orig.getDrmkyzsm());
		dest.setDrbm(orig.getDrbm());
		dest.setDrbzwmc(orig.getDrbzwmc());
		dest.setZdm(orig.getZdm());
		dest.setZdmc(orig.getZdmc());
		dest.setZdlx(orig.getZdlx());
		dest.setXssx(orig.getXssx());
		dest.setYzcs(orig.getYzcs());
		dest.setDrmbzdmc(orig.getDrmbzdmc());
		dest.setSfhbyz(orig.getSfhbyz());
		dest.setYzmc(orig.getYzmc());
		dest.setYzlmc(orig.getYzlmc());
		dest.setYzsm(orig.getYzsm());
		dest.setCshcssy(orig.getCshcssy());
		dest.setYzlbm(orig.getYzlbm());
	}
}
