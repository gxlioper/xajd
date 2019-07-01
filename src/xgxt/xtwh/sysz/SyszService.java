package xgxt.xtwh.sysz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.db.GetSysData;
import xgxt.xtwh.XtwhService;

import com.zfsoft.basic.BasicService;
import com.zfsoft.xgxt.base.util.SearchUtil;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_��ҳ����_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class SyszService extends XtwhService {

	SyszDAO dao = new SyszDAO();

	
	/**
	 * ��ѯ����ר���ļ���Ϣ��ȡǰ10����
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>> getFilesInfoList(SyszForm myForm, User user){
		String sql = new StringBuilder("select newsid,towho,filemc,filepath,rownum r from ")
						.append("(select newsid,towho,filemc,filepath from xg_xtwh_xzzqb")
						.append(" order by scsj desc) where 1=1 ").toString();
		int maxSize = 5; 
		Pages pages = myForm.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> listNew=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> list = CommonQueryDAO.commonPageQueryForMap(pages, sql, new String[]{});
		for(HashMap<String, String> hm:list){
			String newsId=hm.get("newsid");
			String toWho=hm.get("towho");
			if(SearchUtil.getInstance().isHaveQx(user, newsId, toWho)){
				listNew.add(hm);
			}
		}
		int size = maxSize - listNew.size();
		for (int i = 0; i < size; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("newsid", "");
			map.put("filemc", "");
			map.put("filepath", "");
			listNew.add(map);
		}
		return listNew;
	}
	
	
	
	
	
	
	/**
	 * �������ר����ѯ����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXzzqRsList(SyszForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String userType = user.getUserType();
		//�ļ�����
		String filess = model.getFiless();
		
		String kssj = model.getKssj();
		String jssj = model.getJssj();

		// ��
		String tableName = "xg_view_xtwh_xzzq";
		// ��ѯ����
		StringBuilder query = new StringBuilder();
		String[] queryList = new String[] { "filelx" };
		String[] queryLikeList = new String[] { "filemc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		query.append(myQuery.getQueryString());
		query.append(Base.isNull(filess) ? "" : " and filess = '" + filess + "'");
		
		query.append(Base.isNull(kssj) ? "" : " and scsj >= '" + kssj
				+ "000000'");
		if (!Base.isNull(jssj)) {
			query.append(" and (scsj < '" + jssj + "'");
			query.append(" or scsj like '" + jssj + "%')");
		}

		// ����ֵ
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}


	/**
	 * �������ר��
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXzzqMoreList(SyszForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		//�ļ�����
		String filess = model.getFiless();
		
		String kssj = model.getKssj();
		String jssj = model.getJssj();

		// ��
		String tableName = "xg_view_xtwh_xzzq";
		// ��ѯ����
		StringBuilder query = new StringBuilder();
		String[] queryList = new String[] { "filelx" };
		String[] queryLikeList = new String[] { "filemc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		query.append(myQuery.getQueryString());

		query.append(Base.isNull(filess) ? "" : " and filess = '" + filess + "'");
		
		query.append(Base.isNull(kssj) ? "" : " and scsj >= '" + kssj
				+ "000000'");
		if (!Base.isNull(jssj)) {
			query.append(" and (scsj < '" + jssj + "'");
			query.append(" or scsj like '" + jssj + "%')");
		}

		// ����ֵ
		String[] inPutList = myQuery.getInputList();
		ArrayList<String[]> listNew=new ArrayList<String[]>();
		ArrayList<String[]> list = getRsArrList(tableName, query.toString(), inPutList, colList, "", model);
		for(String[] hm:list){
			String newsId=hm[0];
			String toWho=hm[4];
			if("ȫУʦ��".equals(toWho)){
				toWho = "all_teastu";
			}else if("ָ��ʦ��".equals(toWho)){
				toWho = "some_teastu";
			}else if("ȫУ��ʦ".equals(toWho)){
				toWho = "all_tea";
			}else if("ָ����ʦ".equals(toWho)){
				toWho = "some_tea";
			}else if("ȫУѧ��".equals(toWho)){
				toWho = "all_stu";
			}else if("ָ��ѧ��".equals(toWho)){
				toWho = "some_stu";
			}else{
				toWho = "";
			}
			if(SearchUtil.getInstance().isHaveQx(user, newsId, toWho)){
				listNew.add(hm);
			}
		}
		return listNew;
	}
	
	/**
	 * ��������ר��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveXzzq(SyszForm model, User user,
			HttpServletRequest request) throws Exception {
		String newsId = request.getParameter("newsId");
		String toWho = request.getParameter("toWho");
		// �����
		String tableName = "xg_xtwh_xzzqb";
		// ����
		String pk = "newsid";
		if(StringUtils.isNull(newsId)){
			newsId = GetSysData.getGuid();
		}
		model.setNewsid(newsId);
		model.setTowho(toWho);
		// ����ֵ
		String[] pkValue = new String[] { newsId };
		// ��һ�ֶ�
		String[] onezd = new String[] { "newsid", "filepath", "filemc", "filelx",
				"filess", "filesm", "towho", "bz", "scr" };

		model.setScr(user.getUserName());

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		model.setBz(((String)model.getBz()).replaceAll("\\n", "<br/>"));
		model.setFilesm((((String)model.getFilesm()).replaceAll("\\n", "<br/>")));
		
		Boolean b = saveInfoToDb(saveForm, model, request);
		if(b){
			SearchUtil su=SearchUtil.getInstance();
			if(toWho.contains("some")){
				String qxfwid = request.getParameter("qxfwid");
				if(StringUtils.isNotNull(qxfwid)){
					su.updateSearchModel(request, newsId);
				}else{
					su.saveSearchModel(request, newsId);
				}
			}else{
				su.deleteSearch(request, newsId);
			}
		}
		return b;
	}

	/**
	 * ɾ������ר��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean delXzzq(SyszForm model, User user, HttpServletRequest request)
			throws Exception {

		// ɾ��DB
		boolean flag = delXzzqInDB(model, user, request);

		if (flag) {
			// ɾ���ļ�
			delUpLoadFile(model.getPrimarykey_checkVal());
		}

		return flag;

	}

	/**
	 * ɾ������ר��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private Boolean delXzzqInDB(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// �����
		String tableName = "xg_xtwh_xzzqb";
		// ����
		String pk = "newsid";
		// ����ֵ
		String[] pkValue = model.getPrimarykey_checkVal();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return delInfoInDb(saveForm, model, user);

	}

	/**
	 * �������ר���ļ���Ϣ
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getXzzqInfo(SyszForm model) {

		// �����
		String tableName = "xg_xtwh_xzzqb";
		// ����
		String pk = "newsid";
		// ����ֵ
		String pkValue = model.getNewsid();
		// ��һ�ֶ�
		String[] colList = new String[] { "newsid", "filepath", "filemc", "filelx",
				"filesm","filess", "toWho", "bz", "scr" };

		return getRsInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * �������ר�������ص��ļ��б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXzzqFileList(SyszForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String userType = user.getUserType();
		//���ƶ���
		String xzdx = "stu".equalsIgnoreCase(userType) ? "ѧ��" : "��ʦ";
		//�ļ�����
		String filess = model.getFiless();
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputValue = SearchService.getTjInput(model.getSearchModel());
		
		// ��
		String tableName = "xg_view_xtwh_xzzq";
		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(" where 1 = 1 ");
		query.append("and (xzdx = 'ȫ��' or xzdx = '" + xzdx + "')");
		query.append(Base.isNull(filess) ? "" : " and filess = '" + filess + "'");
		query.append(searchTj);
		
		return getRsArrList(tableName, query.toString(), inputValue, colList,
				"", model);
	}

	/**
	 * �����ҳ�����ѯ����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getSydcRsList(SyszForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "sfqy" };
		String[] queryLikeList = new String[] { "dcnr" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String kssj = model.getKssj();
		String jssj = model.getJssj();

		// ��
		String tableName = "xg_view_xtwh_sydc";
		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(Base.isNull(kssj) ? "" : " and dcsj >= '" + kssj
				+ "000000'");
		if (!Base.isNull(jssj)) {
			query.append(" and (dcsj < '" + jssj + "'");
			query.append(" or dcsj like '" + jssj + "%')");
		}

		// ����ֵ
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * �����ҳ������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getSzdcInfo(SyszForm model) {

		// �����
		String tableName = "xg_view_xtwh_sydc";
		// ����
		String pk = "dcid";
		// ����ֵ
		String pkValue = model.getDcid();
		// ��һ�ֶ�
		String[] colList = new String[] { "dcid", "dcnr", "sfqy", "bz" };

		return getRsInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * ������ҳ����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveSydc(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// ����ID
		String dcid = model.getDcid();
		// �Ƿ�����
		String sfqy = model.getSfqy();

		// �������
		boolean flag = saveSydcnr(model, user, request);

		if (flag) {

			if (Base.isNull(dcid)) {
				dcid = dao.getDcid(model, user);
				model.setDcid(dcid);
			}

			// ����ѡ��
			flag = saveSydcxx(model, user, request);

			if (flag && "��".equalsIgnoreCase(sfqy)) {
				// ������������Ϊ�ر�
				flag = dao.setOtherDcClose(model, user);
			}
		}

		return flag;

	}

	/**
	 * �����������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private Boolean saveSydcnr(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// ����ID
		String dcid = model.getDcid();
		// �����
		String tableName = "xg_xtwh_sydcb";
		// ����
		String pk = "dcid";
		// ����ֵ
		String[] pkValue = new String[] { dcid };
		// ��һ�ֶ�
		String[] onezd = Base.isNull(dcid) ? new String[] { "dcnr", "sfqy",
				"bz", "dcr" } : new String[] { "dcid", "dcnr", "sfqy", "bz",
				"dcr" };

		model.setDcr(user.getUserName());

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return saveInfoToDb(saveForm, model, request);
	}

	/**
	 * �������ѡ��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private Boolean saveSydcxx(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// ����ID
		String dcid = model.getDcid();
		// �����
		String tableName = "xg_xtwh_sydcxxb";
		// ����
		String pk = "dcid";
		// ����ֵ
		String[] pkValue = new String[] { dcid };
		// ��һ�ֶ�
		String[] onezd = new String[] { "dcid" };
		// �����ֶ�
		String[] arrzd = new String[] { "xxid", "xxnr" };
		// �ǿ��ֶ�
		String[] notnull = new String[] { "xxnr" };

		model.setDcr(user.getUserName());

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setNotnull(notnull);

		return saveInfoToDb(saveForm, model, user);
	}

	/**
	 * ������ҳ�����Ƿ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveSydcSfqy(SyszForm model, User user) throws Exception {

		return dao.saveSydcSfqy(model, user);

	}

	/**
	 * ɾ����ҳ����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean delSydc(SyszForm model, User user, HttpServletRequest request)
			throws Exception {

		// ɾ����ҳ��������
		boolean flag = delSydcnr(model, user, request);

		if (flag) {

			// ɾ����ҳ����ѡ��
			flag = delSydcxx(model, user, request);

			if (flag) {

				// ɾ����ҳ����ͳ��
				flag = delSydctj(model, user, request);
			}
		}

		return flag;

	}

	/**
	 * ɾ����������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private Boolean delSydcnr(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// �����
		String tableName = "xg_xtwh_sydcb";
		// ����
		String pk = "dcid";
		// ����ֵ
		String[] pkValue = model.getPrimarykey_checkVal();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return delInfoInDb(saveForm, model, user);

	}

	/**
	 * ɾ������ѡ��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private Boolean delSydcxx(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// �����
		String tableName = "xg_xtwh_sydcxxb";
		// ����
		String pk = "dcid";
		// ����ֵ
		String[] pkValue = model.getPrimarykey_checkVal();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return delInfoInDb(saveForm, model, user);

	}

	/**
	 * ɾ������ͳ��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private Boolean delSydctj(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// �����
		String tableName = "xg_xtwh_sydcjgb";
		// ����
		String pk = "dcid";
		// ����ֵ
		String[] pkValue = model.getPrimarykey_checkVal();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return delInfoInDb(saveForm, model, user);

	}

	/**
	 * ��õ���ͳ��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSydcTjList(SyszForm model, User user) {

		List<HashMap<String, String>> list = dao.getSydcTjList(model, user);

		List<HashMap<String, String>> resList = new ArrayList<HashMap<String, String>>();

		if (list != null && list.size() > 0) {

			String all = "0";

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				// ����
				String num = map.get("num");

				all = String.valueOf(Integer.parseInt(all)
						+ Integer.parseInt(num));
			}

			HashMap<String, String> allMap = new HashMap<String, String>();
			allMap.put("all", all);
			resList.add(allMap);

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				// ����
				String num = map.get("num");

				String bl = String.valueOf(Math.round((Float.parseFloat(num)
						/ Float.parseFloat(all) * 100)));

				map.put("bl", bl);

				resList.add(map);
			}
		}

		return resList;
	}

	// =======================����made by ΰ�����=====================

	// =======================����made by qlj=======================
	
	/**
	 * ��ȡ��ʼ���б���Ϣ
	 * author ��������
	 * @throws Exception 
	 */
	public void getInitList(HttpServletRequest request,SyszForm myForm,String lx) throws Exception {
		
		SyszDAO syszDao=new SyszDAO();
		
		if("xsxm".equalsIgnoreCase(lx)){
			// ��ʾ��ʽ
			List<HashMap<String, String>> xsfsList = initList("xsfs");
			request.setAttribute("xsfsList", xsfsList);
			// �Ƿ���ʾ
			List<HashMap<String, String>> sfxsList = initList("sfxs");
			request.setAttribute("sfxsList", sfxsList);
		}else if("dbsx".equalsIgnoreCase(lx)){
			// ģ������
			List<HashMap<String, String>> mklxList = syszDao.getMklb(myForm.getUserName());
			if(mklxList.size()>0 && ("".equalsIgnoreCase(myForm.getMklx())
					|| Base.isNull(myForm.getMklx()))){
				myForm.setMklx((mklxList.get(0)).get("dm"));
			}
			request.setAttribute("mklxList", mklxList);
			// ��Ŀ�б�
			List<HashMap<String, String>> xmList=syszDao.getDbsxXmList(myForm);
			request.setAttribute("xmList", xmList);
		}
	}
	
	/**
	 * ��ȡ��ʼ���б���Ϣ
	 * author ��������
	 * @throws Exception 
	 */
	public void getXssqInit(HttpServletRequest request,SyszForm myForm) throws Exception {
		
		SyszDAO syszDao=new SyszDAO();
	
		// ģ������
		List<HashMap<String, String>> mklxList = syszDao.getXssqMklb();
		request.setAttribute("mklxList", mklxList);
		// ������Ŀ�б�
		request.setAttribute("xmList", getSqxxList(myForm));
	}

	/**
	 * ��ȡ�����б�(����תLIST)
	 * author ��������
	 * @param xslx ��ʾ����
	 * @return List<HashMap<String, String>> 
	 */
	public List<HashMap<String, String>> initList(String xslx) {
		DAO commonDAO = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		// ��ʾ��ʽ
		if ("xsfs".equalsIgnoreCase(xslx)) {
			dm = new String[] { "��ϵ��ʽ" };
			mc = new String[] { "��ϵ��ʽ"};
		}

		// �Ƿ���ʾ
		if ("sfxs".equalsIgnoreCase(xslx)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		}

		// ģ������
		if ("mklx".equalsIgnoreCase(xslx)) {
			dm = new String[] { "ѧ������","�ļ�����" };
			mc = new String[] { "ѧ������","�ļ�����" };
		}

		return commonDAO.arrayToList(dm, mc);
	}

	/**
	 * ��ȡѧ��������Ϣ
	 * author ��������
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 * @throws Exception
	 */
	public List<String[]> getXssqInfo(SyszForm myForm) throws Exception {
		return dao.getXssqInfo(myForm);
	}

	/**
	 * ����������Ϣ
	 * author ��������
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 * @throws Exception
	 */
	public List<String[]> getDbsxInfo(SyszForm myForm) throws Exception {
		return dao.getDbsxInfo(myForm);
	}

	/**
	 * ��ȡѧ��������Ŀ�б�
	 * author ��������
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSqxxList(SyszForm myForm)
			throws Exception {

		return dao.getSqxxList(myForm);
	}

	/**
	 * ��ȡ����������Ŀ�б�
	 * author �������� 
	 * @param myForm
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDbsxXmList(SyszForm myForm)
			throws Exception {
		return dao.getDbsxXmList(myForm);
	}

	/**
	 * ��Ŀ�б�
	 * author ��������
	 * @param mklx
	 * @param userName
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getXmList(String mklx, String userName) {
		return dao.getXmList(mklx, userName);
	}
	
	/**
	 * ��ʾ��Ŀ���Ƿ���ʾ���� �������湦�� 
	 * author ��������
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSfxs(SyszForm myForm) throws Exception {

		return dao.updateSfxs(myForm);
	}
	
	
	public void getTop(HttpServletRequest request, SyszForm myForm) {
		BasicService basicService = new BasicService();

		String[] topTr =  { "��Ŀ����", "����������" };
		String tableName = "";

		if ("ѧ������".equalsIgnoreCase(myForm.getMklx())) {
			tableName = "xszz_shztb";
			topTr = new String[] { "��Ŀ����", "����������" };
		} else if ("�ļ�����".equalsIgnoreCase(myForm.getMklx())) {
			tableName = "wjsc_scwjxxb";
			topTr = new String[] { "�ļ���","�ļ�����", "�ļ�����ʱ��" };
		}
		request.setAttribute("topTr", basicService.getTopTr(tableName, topTr));

	}
	
	public boolean deleteFile(String filePath) throws Exception{
		return dao.deleteFile(filePath);
	}
	
	/**
	 * ��ȡ �������Ŵ�������(ͨ��)
	 * @param user 
	 * @return  List<HashMap<String,String>>
	 * @author qlj
	 * @throws Exception 
	 */
	public List<String[]>getPjpyDbsx(SyszForm myForm) throws Exception{
		SyszDAO dao = new SyszDAO();
		List<String[]>pjdbList=dao.getPjpyDbsx(myForm);
		List<String[]>dbxxList=new ArrayList<String[]>();
		for(int i=0;i<pjdbList.size();i++){
			String[]pjdbArr=pjdbList.get(i);
			String[]dbxxArr=new String[3];
			String[]shxxArr=pjdbArr[4].split(",");
			String[]gwxxArr=pjdbArr[5].split(",");
			String[]jbxxArr=pjdbArr[6].split(",");
			StringBuilder html=new StringBuilder();
			dbxxArr[0]=pjdbArr[0];
			dbxxArr[2]=pjdbArr[2];
			html.append(pjdbArr[2]);
			for(int j=0;j<shxxArr.length;j++){
				
				html.append(" <a href=\"#\"onclick=\"pjpySh('"+pjdbArr[1]+"','"+gwxxArr[j]+"','"+jbxxArr[j]+"')\" style=\"cursor:hand\"");
				html.append(" <U><font color=\"blue\" >��"+shxxArr[j]+"��</font></U></a> ");
				
			}
			dbxxArr[1]=html.toString();
			dbxxList.add(dbxxArr);;
		}
		return  dbxxList;
	}
	
	/**
	 * ������ҳ����ID��ȡ��������
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String>getSydcInfo(SyszForm myForm) throws Exception {
		return dao.getSydcInfo(myForm);
	}
	
	//	 =======================����made by ���͵Ĵ�����=======================
	
}