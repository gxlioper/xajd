package xgxt.pjpy.comm.zhcp.sjdr;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.impexp.CommImpExpService;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszService;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_���ݵ���_Service��
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

public class ZhcpSjdrService extends PjpyCommService {
	

	ZhcpSjdrDAO dao = new ZhcpSjdrDAO();

	/**
	 * ��ó�ʼ����Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getCshXmList(ZhcpSjdrForm model) {
		
		ZhcpJbszForm jbszModel = new ZhcpJbszForm();
		ZhcpJbszService service = new ZhcpJbszService();

		// ������Ŀ�б�
		jbszModel.setXmjb("3");
		
		List<HashMap<String, String>> list = service.getZctreeList(jbszModel);

		return list;
	}

	/**
	 * �����Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXmList(ZhcpSjdrForm model) {
		
		// ������Ŀ
		String czxm = model.getCzxm();
		czxm = Base.isNull(czxm) ? "pdjbf" : czxm;

		List<HashMap<String, String>> xmList = null;
		if ("pdjbf".equalsIgnoreCase(czxm)) {// Ʒ�»�����
			xmList = getPdhpXmList(model);
		}

		return xmList;
	}

	/**
	 * ���Ʒ�»�����Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getPdhpXmList(ZhcpSjdrForm model) {

		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;

		String xn = jbszModel.getPjxn();
		String xq = jbszModel.getPjxq();
		String nd = jbszModel.getPjnd();

		// Ʒ�»�����Ŀ��s
		String tableName = "xg_pjpy_pdbxhpxmb";
		// ��������
		StringBuilder query = new StringBuilder(" where 1 = 1 ");
		query.append(" and xn = ? ");
		query.append(" and xq = ? ");
		query.append(" and nd = ? ");
		query.append(" order by xmdm ");
		// ����ֵ
		String[] inPutList = new String[] { xn, xq, nd };
		// ���ֵ
		String[] colList = new String[] { "xmdm", "xmmc" };

		return getRsList(tableName, query.toString(), inPutList, colList, "");
	}
	
	/**
	 * ���Ʒ�»����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getPdhpInfoList(ZhcpSjdrForm model, User user,
			List<HashMap<String, String>> xmList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		return dao.getPdhpInfoList(model, user, xmList);
	}
	
	/**
	 * ����ۺϷ�ά���б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZhfwhList(ZhcpSjdrForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		return dao.getZhfwhList(model, user);
	}
	
	/**
	 * �������Html
	 * 
	 * @author ΰ�����
	 */
	public String getSpHtml(SearchRsModel rsModel, ZhcpSjdrForm model,
			ArrayList<String[]> rsArrList) {

		// ������Ŀ
		String czxm = model.getCzxm();
		// IE�汾
		String ie = rsModel.getIe();
		// ��Դ��
		String lyb = model.getLyb();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

//				spHtml.append("<td align=\"center\" width=\"5px\">");
//				spHtml.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
//				spHtml.append("value=\"" + rs[0] + "\"/>");
//				spHtml.append("</td>");

				for (int j = 1; j < rs.length-2; j++) {
					spHtml.append("<td align=\"center\" nowrap=\"nowrap\"> ");
					
					spHtml.append(rs[j]);
					spHtml.append("</td>");
				}
				
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" ");
				// ����IE8�����
				if ("5.8".equalsIgnoreCase(ie)) {
					if("noLyb".equalsIgnoreCase(lyb)){
						spHtml.append("height=\"28.5px\"");
					}else{
						spHtml.append("height=\"29.5px\"");
					}
				} else {
					if("noLyb".equalsIgnoreCase(lyb)){
						//spHtml.append("height=\"28.5px\"");
					}else{
						spHtml.append("height=\"30px\"");
					}
				}
				spHtml.append(">");
				
				if("noLyb".equalsIgnoreCase(lyb)){
					spHtml.append("<input type=\"text\" onblur=\"setEditValue();chMax(this,100);checkInputNum(this);\"");
					spHtml.append("style=\"width:60px\" maxlength=\"5\" ");
					spHtml.append(" name=\"" + ("czxm_" + czxm) + "\"");
					spHtml.append(" id=\"" + ("czxm_" + czxm + "_" + rs[0]) + "\"");
					spHtml.append(" value=\"" + rs[rs.length-2] + "\"");
					spHtml.append("/>");
				}else{
					spHtml.append(rs[rs.length-2]);
				}
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}
		
		return spHtml.toString();
	}
	
	/**
	 * �����ۺ�������ط���
	 * 
	 * @author ΰ�����
	 * @throws Exception 
	 */
	public Boolean saveZhcpfXgInfo(ZhcpSjdrForm model, User user)
			throws Exception {
		return dao.saveZhcpfXgInfo(model, user);
	}
	
	/**
	 * ͬ���ۺ�������ط���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean tbZhcpfXgInfo(ZhcpSjdrForm model, User user) {
		return dao.tbZhcpfXgInfo(model, user);
	}

	/**
	 * ����赼��������Ϣ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getExpBmInfo(ZhcpSjdrForm model) {
		return dao.getExpBmInfo(model);
	}
	
	/**
	 * �����۲��ģ��
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean expZcfmbToExcel(ZhcpSjdrForm model, User user) throws Exception {
		
		CommImpExpService ioService = new CommImpExpService();
		
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		
		String sheetName = "";
		
		// �����б�
		List<HashMap<String, String>> bmList = model.getBmList();
		// �۲�����
		String zczq = model.getZczq();
		// ������ʽ
		String dcxs = model.getDcxs();
		// ������Ŀ
		String czxm = model.getCzxm();
		// ѧ��
		String xn = jbszModel.getPjxn();
		// ѧ��
		String xq = jbszModel.getPjxq();
		// ���
		String nd = jbszModel.getPjnd();
		
		String xmmc = getOneValue("xg_pjpy_zcxmb", "xmmc", "xn||xq||nd||xmdm", xn+xq+nd+czxm);
		// =======================��ͷ============================
		List<String> topName = new ArrayList<String>();
		if("xn".equalsIgnoreCase(zczq)){//ѧ��
			topName.add("ѧ��");
		}else if("xq".equalsIgnoreCase(zczq)){//ѧ��
			topName.add("ѧ��");
			topName.add("ѧ��");
		}else{//���
			topName.add("���");
		}
		
		// �̶���
		String[] gdName = new String[] { "ѧ��", "����", "�Ա�", "�꼶",Base.YXPZXY_KEY, "רҵ",
				"�༶", xmmc + "_(�뵼�����֣�������ܵ���ϵͳ�쳣)" };

		for (int i = 0; i < gdName.length; i++) {
			topName.add(gdName[i]);
		}
		// =======================��ͷ end============================
		
		// ���������ͷ
		List<HashMap<String, String>> topTr = getTopList(topName
				.toArray(new String[] {}));
		List<String[]> fileList = new ArrayList<String[]>();
		
		for (int i = 0; i < bmList.size(); i++) {
			
			HashMap<String, String> map = bmList.get(i);
			
			String bmdm = map.get("bmdm");
			String fileName = map.get("bmmc") + "_" + map.get("bmdm");
			
			if ("xy".equalsIgnoreCase(dcxs)){
				model.setXydm(bmdm);
			}else{
				model.setBjdm(bmdm);
			}
			// ���յ�������
			List<String[]> list = dao.getZhfxsList(model, user);
		
			ioService.createXLS(sheetName, model.getFilePath(), fileName, topTr, list);
			fileList.add(new String[]{fileName,bmdm,dcxs,user.getUserName()});
		}
		
		return dao.saveStencilLog(fileList);
	}
	
	
	/**
	 * ��������
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public String impZcfInfo(ZhcpSjdrForm model, String filePath,User user) throws Exception {

		File file = new File(filePath);
		
		String message = "";
		
		boolean flag = false;
		
		// ��������
		if (!Base.isNull(filePath)) {

			Workbook book = Workbook.getWorkbook(file);
			// ��õ�һ�����������
			Sheet sheet = book.getSheet(0);
			// ����list
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			// ��
			int rows = sheet.getRows();
			// ��
			int column = sheet.getColumns() + 1;
			// ��ͷ
			String[] bt = new String[column];

			//��Ŀ����
			String xmmc = "";
			
			// ѭ����
			for (int i = 0; i < rows; i++) {
				// ��һ�б��⣬����
				if (i != 0) {
					HashMap<String, String> map = new HashMap<String, String>();
					// ѭ����
					for (int j = 0; j < column; j++) {

						if (j == column - 1) {

							Cell cell = sheet.getCell(j - 1, i);
							String nr = cell.getContents();

							map.put("����", nr);
						} else {
							Cell cell = sheet.getCell(j, i);
							String nr = cell.getContents();

							map.put(bt[j], nr);
						}
					}
					list.add(map);
				} else {
					for (int j = 0; j < column; j++) {

						if (j == column - 1) {
							Cell cell = sheet.getCell(j - 1, i);
							String nr = cell.getContents();
							bt[j] = nr;
							xmmc = nr.split("_")[0];
						} else {
							Cell cell = sheet.getCell(j, i);
							String nr = cell.getContents();
							bt[j] = nr;
						}
					}
				}
			}
			
			ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
			
			String sheetName = "";
			// �۲�����
			String zczq = model.getZczq();
			
			// ѧ��
			String xn = jbszModel.getPjxn();
			// ѧ��
			String xq = jbszModel.getPjxq();
			// ���
			String nd = jbszModel.getPjnd();
			// ����
			String pkValue = xn + xq + nd + xmmc;
			// ������Ŀ
			String czxm = getOneValue("xg_pjpy_zcxmb", "xmdm",
					"xn||xq||nd||xmmc", pkValue);
			
			ArrayList<String> xnList = new ArrayList<String>();
			ArrayList<String> xqList = new ArrayList<String>();
			ArrayList<String> ndList = new ArrayList<String>();
			ArrayList<String> xhList = new ArrayList<String>();
			ArrayList<String> fsList = new ArrayList<String>();
			
			// ѭ������
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				xnList.add(Base.isNull(map.get("ѧ��")) ? "��" : map.get("ѧ��"));
				xqList.add(Base.isNull(map.get("ѧ��")) ? "��" : map.get("ѧ��"));
				ndList.add(Base.isNull(map.get("���")) ? "��" : map.get("���"));
				xhList.add(map.get("ѧ��"));
				fsList.add(map.get("����"));
			}
			
			StringBuilder sql = new StringBuilder();
			sql.append("update xg_pjpy_zhcpb ");
			sql.append("set " + czxm + "=? ");
			sql.append("where 1 = 1 ");
			sql.append("and xn=? ");
			sql.append("and xq=? ");
			sql.append("and nd=? ");
			sql.append("and xh=? ");
			
			List<String[]> params = new ArrayList<String[]>();
			
			if (xnList != null && xnList.size() > 0) {
				for (int i = 0; i < xnList.size(); i++) {
					String[] value = new String[] { fsList.get(i),
							xnList.get(i), xqList.get(i), ndList.get(i),
							xhList.get(i) };
					
					params.add(value);
				}
			}
			
			flag = saveArrDate(sql.toString(), params, "xg_pjpy_zhcpb", user);
			
			file.delete();
		}

		return message;
	}
	
	
	
	/**
	 * ����ģ��ļ�¼
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStencilLog(User user,ZhcpSjdrForm model) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String[] queryArr = new String[]{"bmlx"};
		String[] queryLikeArr = new String[]{"wjmc"};
		String[] colList = new String[]{"wjmc","cjsj"};
		Map<String,Object>  map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		
		sql.append("select a.wjmc,a.cjsj,rownum r from pjpy_zhcp_mmccb a where 1=1 ");
		
		if (Boolean.parseBoolean(user.getIsFdy())){
			sql.append(" and exists (select 1 from fdybjb b where a.bmdm=b.bjdm)");
		} else if ("xy".equalsIgnoreCase(user.getUserType())){
			sql.append(" and a.bmdm='")
			   .append(user.getUserDep())
			   .append("'");
		}
		sql.append(map.get("sql"));
		return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(), (String[])map.get("input"), colList);
	}
	
	
}