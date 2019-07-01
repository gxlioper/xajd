package xgxt.gygl.zsjg;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.GyglCommService;
import xgxt.gygl.cwgl.GyglCwglModel;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����_ס�޽��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class GyglZsjgService extends GyglCommService {

	GyglZsjgDAO dao = new GyglZsjgDAO();
	
	/**
	 * ��ȡѧ��ס����ʷ��Ϣ�б�
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZslsxxList(GyglZsjgForm myForm,
			HttpServletRequest request) throws Exception {
		return dao.getZslsxxList(myForm, request);
	}
	
	/**
	 * ��ȡѧ��ס����Ϣ�б�
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZsxxList(GyglZsjgForm myForm,User user,String[] colList,
			HttpServletRequest request) throws Exception {
		//�������
		String path = myForm.getSearchModel().getPath();
		myForm.getSearchModel().setPath(path);

		SearchService searchService = new SearchService();

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		// Ȩ�޿���
		String searchTjByUser = searchService.getSearchTjByUser("a", user);
		
		searchTj += searchTjByUser;
		return dao.getZsxxList(myForm,user,colList,searchTj,inputV);
	}
	
	/**
	 * ɾ��ѧ����ʷס����Ϣ
	 * @param myForm
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean deleteLsxx(GyglZsjgForm myForm) throws SQLException{
		
		DAO dao=DAO.getInstance();
		String tableName="xszslsxxb";
		String pk="guid";
		String []checkVal=myForm.getCheckVal();
		String[]delSql=new String[checkVal.length];
		for(int i=0;i<checkVal.length;i++){
			delSql[i]=" delete from "+tableName+" where "+pk+" = '"+checkVal[i]+"'";
		}
		int[]result=dao.runBatch(delSql);
		for(int i=0;i<result.length;i++){
			if(result[i]==0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * ѧ������
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean xsts(GyglZsjgForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		String[]pkValue=myForm.getCheckVal();
		int len=pkValue.length;
		String[]delSql=new String[len];
		StringBuilder addSql=new StringBuilder();
		StringBuilder query=new StringBuilder();
		String delTable="xszsxxb";
		String addTable="xszslsxxb";
		query.append(" where 1=1 ");
		for(int i=0;i<len;i++){
			delSql[i]=" delete from "+delTable+" where xh='"+pkValue[i]+"' ";
			if(i==0){
				query.append(" and (");
				query.append(" xh='"+pkValue[i]+"' ");
			}else {
				query.append(" or xh='"+pkValue[i]+"' ");
			}
		}
		query.append(")");
		addSql.append(" insert into xszslsxxb(xh,ssbh,cwh,rzrq,tfrq,xm,xb,nj,xymc,zymc,bjmc,ldmc,qsh,sfbz,sjly) ");
		addSql.append("  select xh,ssbh,cwh,rzrq,jzrq,xm,xb,nj,xymc,zymc,bjmc,ldmc,qsh,zsf,'�ֶ�' from xg_view_gygl_xszsxx ");
		addSql.append(query);
		addSql.append("  ");
		boolean blog=false;
		blog=dao.runUpdate(addSql.toString(), new String[]{});
		if(blog){
			int[] result=dao.runBatch(delSql);
			for(int i=0;i<result.length;i++){
				if(result[i]==0){
					return false;
				}
			}
		}else {
			return false;
		}
		return true;
	}
	
	/**
	 * ѧ������
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean plxsts(GyglZsjgForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		String delSql="";
		StringBuilder addSql=new StringBuilder();
		StringBuilder query=new StringBuilder();
		String delTable="xszsxxb";
		String addTable="xszslsxxb";
		query.append(" where 1=1 ");
		
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		String []inputV=SearchService.getTjInput(myForm.getSearchModel());
		query.append(searchTj);
		
		delSql=" delete from "+delTable+" a where exists(select 1 from view_xsjbxx b where a.xh=b.xh "+searchTj+")";
		
		addSql.append(" insert into xszslsxxb(xh,ssbh,cwh,rzrq,tfrq,xm,xb,nj,xymc,zymc,bjmc,ldmc,qsh,sfbz,sjly) ");
		addSql.append("  select xh,ssbh,cwh,rzrq,jzrq,xm,xb,nj,xymc,zymc,bjmc,ldmc,qsh,zsf,'�ֶ�' from xg_view_gygl_xszsxx ");
		addSql.append(query);
		boolean blog=false;
		blog=dao.runUpdate(addSql.toString(), inputV);
		if(blog){
			blog=dao.runUpdate(delSql,inputV);
		}else {
			return false;
		}
		return blog;
	}
	
	/**
	 * ����Զ����䲿���б�
	 * @throws Exception 
	 */
	public List<String[]> getZdfpBmTjList(GyglZsjgForm model, User user,
			String[] colList, HttpServletRequest request)
			throws Exception {

		// �������
		String fpdx = model.getFpdx();
		String path = model.getSearchModel().getPath() + "&searchType=" + fpdx;
		model.getSearchModel().setPath(path);

		List<String[]> list = null;

		SearchService searchService = new SearchService();

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// Ȩ�޿���
		HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTjByUser = searchService.getSearchTjByUser("a", user);
		searchTj += searchTjByUser;
		
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ

			list = dao.getXyCwxx(model, user, colList, searchTj, inputV);

		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ

			list = dao.getNjXyCwxx(model, user, colList, searchTj, inputV);

		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ

			list = dao.getNjZyCwxx(model, user, colList, searchTj, inputV);

		} else {// �������Ϊ�༶

			list = dao.getNjBjCwxx(model, user, colList, searchTj, inputV);

		}

		return list;
	}
	
	
	public List<String>getYdxnList(GyglZsjgForm model){
		
		List<String>list=new ArrayList<String>();
		int xn=Integer.parseInt(Base.currXn.substring(0,4))+1;
		model.setXnfw("4");
		int xnfw=Integer.parseInt(model.getXnfw());
		for(int i=xnfw;i>0;i--){
			list.add(String.valueOf(xn-i)+"-"+String.valueOf(xn-i+1));
		}
		return list;
	}
	
	public List<String[]>getSsydList(GyglZsjgForm model, User user,
			String[] colList, HttpServletRequest request) throws Exception{
		// �������
		String fpdx = model.getFpdx();
		String path = model.getSearchModel().getPath() + "&searchType=" + fpdx;
		model.getSearchModel().setPath(path);

		SearchService searchService = new SearchService();

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// Ȩ�޿���
		HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTjByUser = searchService.getSearchTjByUser("a", user);

		searchTj += searchTjByUser;
		return dao.getSsydList(model,user,colList,searchTj,inputV);
	} 
	
	public List<String[]>getSsydcxList(GyglZsjgForm model, User user,
			String[] colList, HttpServletRequest request) throws Exception{
		// �������
		String path = model.getSearchModel().getPath();
		model.getSearchModel().setPath(path);

		SearchService searchService = new SearchService();

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// Ȩ�޿���
		HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTjByUser = searchService.getSearchTjByUser("a", user);

		searchTj += searchTjByUser;
		
		return dao.getSsydcxList(model,user,colList,searchTj,inputV);
	} 
	
	
	/**
	 * ���洲λ�춯��Ϣ(ס����Ϣ���춯��Ϣ)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveCwyd(GyglZsjgForm myForm) throws Exception{
		
		if(myForm.getXhArr()!=null && myForm.getXhArr().length>0){
			boolean blog=insertYdxx(myForm);
			if(blog){
			GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
			GyglCwglModel model=new GyglCwglModel();
			// �������ݲ��� �ı���
			String realTable = "xszsxxb";
			
			String[] arrzd = new String[] { "xh","lddm", "qsh","cs","cwh", "rzrq", "sjly","ssbh"};
			
			List<String> pkValue=new ArrayList<String>();
			List<String> lddm=new ArrayList<String>();
			List<String> qsh=new ArrayList<String>();
			List<String> cs=new ArrayList<String>();
			List<String> cwh=new ArrayList<String>();
			List<String> xh=new ArrayList<String>();
			List<String> rzrq=new ArrayList<String>();
			List<String> sjly=new ArrayList<String>();
			List<String> ssbh=new ArrayList<String>();
			for(int i=0;i<myForm.getXhArr().length;i++){
				if(!Base.isNull(myForm.getXhArr()[i])){
					pkValue.add(myForm.getXhArr()[i]);
					lddm.add(myForm.getLddmArr()[i]);
					qsh.add(myForm.getQshArr()[i]);
					cs.add(myForm.getCsArr()[i]);
					cwh.add(myForm.getCwhArr()[i]);
					xh.add(myForm.getXhArr()[i]);
					rzrq.add(GetTime.getNowTime2());
					sjly.add("�ֶ�");
					ssbh.add(myForm.getLddmArr()[i]+"-"+myForm.getQshArr()[i]);
				}
			}
	
			
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(" xh ");
			saveForm.setPkValue(pkValue.toArray(new String[]{}));
			saveForm.setArrzd(arrzd);
			
			model.setSjly(sjly.toArray(new String[]{}));
			model.setLddm(lddm.toArray(new String[]{}));
			model.setQsh(qsh.toArray(new String[]{}));
			model.setCs(cs.toArray(new String[]{}));
			model.setCwh(cwh.toArray(new String[]{}));
			model.setXh(xh.toArray(new String[]{}));
			model.setRzrq(rzrq.toArray(new String[]{}));
			model.setSsbh(ssbh.toArray(new String[]{}));
				
				blog=ghxyNjzrwhService.saveTyxx(saveForm, model);
			}
			return blog;
			
		}else {
			
			return true;
		}
	}
	
	/**
	 * ���������춯���
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertYdxx(GyglZsjgForm myForm) throws Exception{
		return dao.insertYdxx(myForm);
	}
	
	/**
	 * ��ȡ����������Ϣ
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getKxqsList(GyglZsjgForm myForm, User user,
			String[] colList, HttpServletRequest request,String lx) throws Exception {
		return dao.getKxqsList(myForm,colList,request,lx,user);
	}
	
	/**
	 * ��ȡ��������Ϣ
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getKqsxx(GyglZsjgForm myForm, User user,
			String[] colList, HttpServletRequest request) throws Exception {
		myForm.getSearchModel().setPath("gygl_zsjg_kxss.do");
		return dao.getKqsxx(myForm,colList,request);
	}
	
	/**
	 * ��ȡ��������Ϣ
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getXqsxx(GyglZsjgForm myForm, User user,
			String[] colList, HttpServletRequest request) throws Exception {
		return dao.getXqsxx(myForm,colList,request);
	}
	
	/**
	 * ��ȡ¥����ϸ��Ϣ
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getLdMap(GyglZsjgForm myForm){
		
		
		return dao.getLdMap(myForm);
		
	}
	
	public void printKxss(GyglZsjgForm myForm, HttpServletRequest request,
			WritableWorkbook wwb,RequestForm rForm) throws Exception{
		User user=getUser(request);
		
		String[]colList=rForm.getColList();
		List<HashMap<String,String>>topTr=rForm.getTopTr();
		List<String[]>kxssList=new 	ArrayList<String[]>();
		if("kxqs".equalsIgnoreCase(myForm.getDclx())){
			kxssList=getKxqsList(myForm,user,colList,request,"dc");
		}else if("kqs".equalsIgnoreCase(myForm.getDclx())){
			kxssList=getKqsxx(myForm,user,colList,request);
		}else if("xqs".equalsIgnoreCase(myForm.getDclx())){
			kxssList=getXqsxx(myForm,user,colList,request);
		}
		
		try {
			// ����xls��SHEET����
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// ���ö��뷽ʽ
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcfTytle.setFont(wfTytle);
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
//			 ���ñ��߿�
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
	

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> topMap = topTr.get(i);
				ws.addCell(new Label(i, 0, topMap.get("cn"), wcfTytle));

			}

			for (int i = 0; i < kxssList.size(); i++) {
				String[] kxssArr = kxssList.get(i);
				for (int j = 0; j < kxssArr.length; j++) {

					ws.addCell(new Label(j, 1 + i, kxssArr[j], wcfTytle));

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	
	
		// ======================���� Made By ΰ�����===================

	/**
	 * ������Ҵ�λ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getQsrzCwList(String cws) {

		int maxCws = Integer.parseInt(cws);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 1; i <= maxCws; i++) {

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", String.valueOf(i));
			map.put("mc", i + "�Ŵ�");

			list.add(map);
		}

		return list;
	}

	/**
	 * ���������Ϣ
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getQsInfoList(GyglZsjgForm model, User user,
			String[] colList, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getRzqsList(model, user, request);
	}

	/**
	 * ���������ס��Ϣ
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, Object>> getQsrzInfoList(GyglZsjgForm model,
			ArrayList<String[]> qsList) {

		// ���λ��
		String maxCws = getMaxCws();

		// ������ס��Ϣ
		List<HashMap<String, String>> qsrzList = dao.getQsrzInfoList(qsList);

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		if (qsList != null && qsList.size() > 0) {

			for (int i = 0; i < qsList.size(); i++) {

				String lddm = qsList.get(i)[0];// ¥������
				String ldmc = qsList.get(i)[1];// ¥������
				String cs = qsList.get(i)[2];// ����
				String qsh = qsList.get(i)[3];// ���Һ�
				String xbxz = qsList.get(i)[4];// �Ա�����

				// ������ס��Ϣ
				List<HashMap<String, String>> cwsList = getQsrzCwList(maxCws);

				if (qsrzList != null && qsrzList.size() > 0) {

					for (int j = 0; j < qsrzList.size(); j++) {

						HashMap<String, String> rzInfo = qsrzList.get(j);

						String rz_lddm = rzInfo.get("lddm");// ¥������
						String rz_cs = rzInfo.get("cs");// ����
						String rz_qsh = rzInfo.get("qsh");// ���Һ�
						String userd = rzInfo.get("userd");// ��ʹ��

						String xh = rzInfo.get("xh");// ѧ��
						String xm = rzInfo.get("xm");// ����
						String bjmc = rzInfo.get("bjmc");// �༶����
						String cwh = rzInfo.get("cwh");// ��λ��

						if (rz_lddm.equalsIgnoreCase(lddm)
								&& rz_cs.equalsIgnoreCase(cs)
								&& rz_qsh.equalsIgnoreCase(qsh)
								&& !"yes".equalsIgnoreCase(userd)) {

							for (int k = 0; k < cwsList.size(); k++) {

								if (cwh.equalsIgnoreCase(cwsList.get(k).get(
										"dm"))) {
									cwsList.get(k).put("xh", xh);
									cwsList.get(k).put("xm", xm);
									cwsList.get(k).put("bjmc", bjmc);
									cwsList.get(k).put("cwh", cwh);

									qsrzList.get(j).put("userd", "yes");
									break;
								}
							}
						}
					}
				}
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("ldmc", ldmc);
				map.put("qsh", qsh);
				map.put("xbxz", xbxz);
				map.put("cwsList", cwsList);
				
				list.add(map);
			}
		} else {
			int pageSize = model.getPages().getPageSize();

			// ���Ҵ�λ��Ϣ
			List<HashMap<String, String>> cwsList = getQsrzCwList(maxCws);
			
			for (int i = 0; i < pageSize; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				map.put("ldmc", "&nbsp;");
				map.put("qsh", "&nbsp;");
				map.put("xbxz", "&nbsp;");
				map.put("cwsList", cwsList);

				list.add(map);
			}
		}

		return list;
	}

	/**
	 * ����������λ��
	 * 
	 * @author ΰ�����
	 */
	public String getMaxCws() {
		return dao.getMaxCws();
	}

	/**
	 * ����������ס�������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void expQsrzqkToExcel(GyglZsjgForm model, User user,
			HttpServletRequest request, OutputStream os) throws Exception {

		String title = "������ס���";
		// �̶���
		String[] gdName = new String[] { "¥��", "���Һ�", "�Ա�" };
		// ���λ��
		String maxCws = getMaxCws();
		// ���Ҵ�λ��Ϣ
		List<HashMap<String, String>> cwsList = getQsrzCwList(maxCws);

		List<String> topName = new ArrayList<String>();
		for (int i = 0; i < gdName.length; i++) {
			topName.add(gdName[i]);
		}
		if (cwsList != null && cwsList.size() > 0) {
			for (int i = 0; i < cwsList.size(); i++) {
				topName.add(cwsList.get(i).get("mc"));
			}
		}
		
		// ���������ͷ
		List<HashMap<String, String>> topTr = getTopList(topName
				.toArray(new String[] {}));

		// ������������б�
		ArrayList<String[]> qsList = dao.getRzqsListNoFy(model, user, request);
		// ������ס���
		List<HashMap<String, Object>> qsrzInfoList = getQsrzInfoList(model,
				qsList);

		// ���յ�������
		ArrayList<String[]> list = new ArrayList<String[]>();

		if (qsrzInfoList != null && qsrzInfoList.size() > 0) {
			for (int i = 0; i < qsrzInfoList.size(); i++) {
				HashMap<String, Object> map = qsrzInfoList.get(i);

				ArrayList<String> rs = new ArrayList<String>();

				String ldmc = (String) map.get("ldmc");
				String qsh = (String) map.get("qsh");
				String xbxz = (String) map.get("xbxz");

				List<HashMap<String, String>> cwInfoList = (List<HashMap<String, String>>) map
						.get("cwsList");

				rs.add(ldmc);
				rs.add(qsh);
				rs.add(xbxz);

				for (int j = 0; j < cwInfoList.size(); j++) {
					rs.add(cwInfoList.get(j).get("xm"));
				}

				//��һ��
				list.add(rs.toArray(new String[] {}));

				rs = new ArrayList<String>();

				rs.add(ldmc);
				rs.add(qsh);
				rs.add(xbxz);

				for (int j = 0; j < cwInfoList.size(); j++) {
					rs.add(cwInfoList.get(j).get("xh"));
				}

				// �ڶ���
				list.add(rs.toArray(new String[] {}));
				
				rs = new ArrayList<String>();

				rs.add(ldmc);
				rs.add(qsh);
				rs.add(xbxz);

				for (int j = 0; j < cwInfoList.size(); j++) {
					rs.add(cwInfoList.get(j).get("bjmc"));
				}

				// ������
				list.add(rs.toArray(new String[] {}));
			}
		}
		
		String hbzt = "hb";
		int[] hbwz = new int[] { 0, 1, 2 };
		expToExcel(title, topTr, list, os, hbzt, hbwz);
	}
	//	 =================���� Made By ΰ�����==================
}