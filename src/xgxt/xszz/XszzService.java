package xgxt.xszz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.upload.FormFile;

import common.Globals;
import common.XszzXmdm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.xml.XMLReader;
import xgxt.form.SaveForm;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.xszz.comm.XszzCommDAO;
import xgxt.xszz.comm.XszzCommService;

public class XszzService extends CommService{

	XszzDAO dao = new XszzDAO();

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(XszzTyForm myForm, HttpServletRequest request,
			String manu) throws Exception {

		// =====================���Ĭ����Ŀ����Ŀ����=============================
		// ����������
		String knsdm = XszzXmdm.XSZZ_KNS;
		String mklx = myForm.getMklx();
		
		// =====================ͨ��=============================
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// �꼶ѧԺרҵ�༶
		FormModleCommon.setNdXnXqList(request);// ���ѧ��ѧ��
		FormModleCommon.requestSetList(new String[] { "bm" }, request);// �Զ���(Ŀǰ�����Ŵ���)

		List<HashMap<String, String>> xbList = dao.getSelectList("xblx");// �Ա�
		request.setAttribute("xbList", xbList);

		List<HashMap<String, String>> sfList = dao.getSelectList("sflx");// �Ƿ��б�
		request.setAttribute("sfList", sfList);

		// ����б�
		List<HashMap<String, String>> shList = dao.getSelectList("shlx");
		request.setAttribute("shList", shList);
		
		// ��ͥ��Ա��������
		List<HashMap<String, String>> jtcyTitleList = dao
				.getSelectList("jtcyzd");
		request.setAttribute("jtcyTitleList", jtcyTitleList);
		
		// ��Ŀ����б�
		if("pj".equalsIgnoreCase(mklx)){
			List<HashMap<String, String>> xmlbList = dao.getSelectList("xmlb_pj");
			request.setAttribute("xmlbList", xmlbList);
		}else if("zz".equalsIgnoreCase(mklx)){
			List<HashMap<String, String>> xmlbList = dao.getSelectList("xmlb_zz");
			request.setAttribute("xmlbList", xmlbList);
		}else{
			
			String xxdm = Base.xxdm;
			
			//�人��·ְҵ����ѧԺ
			if(Globals.XXDM_WHTLZYJSXY.equalsIgnoreCase(xxdm)){	
				List<HashMap<String, String>> xmlbList =  dao.getWhList(
						"xg_xszz_xmlbb", "xmlb", "xmlb", "", "", "");
				
				ArrayList<HashMap<String, String>> lbList = new ArrayList<HashMap<String, String>>();
				for(int i = 1;i<xmlbList.size();i++){
					HashMap<String,String> map = xmlbList.get(i);
					map.put("en", map.get("dm"));
					map.put("cn", map.get("mc"));
					lbList.add(map);
				}
				
				request.setAttribute("xmlbList", lbList);
			
			}else{
				List<HashMap<String, String>> xmlbList = dao.getSelectList("xmlb");
				request.setAttribute("xmlbList", xmlbList);
			}
		}
		// =====================��ʳ����=============================
		if ("fsbz_fp".equalsIgnoreCase(manu)) {// ����
			DAO tyDao = DAO.getInstance();
			// ѧ���б�
			List<HashMap<String, String>> ndList = tyDao.getXnndList();
			ndList.remove(0);
			request.setAttribute("ndList", ndList);

		} else if ("fsbz_ff".equalsIgnoreCase(manu)) {// ����
			DAO tyDao = DAO.getInstance();
			// ѧ���б�
			List<HashMap<String, String>> ndList = tyDao.getXnndList();
			ndList.remove(0);
			request.setAttribute("ndList", ndList);

			// �·��б�
			List<HashMap<String, String>> yfList = tyDao.getYfList();
			request.setAttribute("yfList", yfList);

			// ���������б�
			List<HashMap<String, String>> bzlxList = dao.getWhList(
					"xszz_fsbzlxb", "dm", "mc", "", "", "");
			bzlxList.remove(0);
			request.setAttribute("bzlxList", bzlxList);
		}
		// =====================����������=============================
		else if ("jjkns".equalsIgnoreCase(manu)) {

			// ����������Ϣ
			StuInfoDAO stuDao = new StuInfoDAO();
			HashMap<String, String> map = new HashMap<String, String>();
			String xh = myForm.getXh();
			String[] colList = new String[] { "hkshen", "hkshi", "hkxian",
					"syd" };
			map = CommonQueryDAO
					.commonQueryOne("view_xsxxb", colList, "xh", xh);

			// ʡ�б�
			List<HashMap<String, String>> ssList = stuDao.getSsList();
			request.setAttribute("ssList", ssList);

			// �����������б�
			List<HashMap<String, String>> hkshiList = stuDao.getShiList(
					map.get("hkshen")).get("shiList");
			request.setAttribute("hkshiList", hkshiList);

			// �����������б�
			List<HashMap<String, String>> hkxianList = stuDao.getShiList(
					map.get("hkshi")).get("xianList");
			request.setAttribute("hkxianList", hkxianList);

			// �����ȼ��б�
			List<HashMap<String, String>> dydjList = dao.getWhList(
					"hndx_xszz_dydjb", "dm", "mc", "", "", "");
			request.setAttribute("dydjList", dydjList);

			// ����б�
			List<HashMap<String, String>> knsjbList = dao.getWhList(
					"hndx_xszz_jjknsjb", "dm", "mc", "", "", "");
			knsjbList.remove(0);
			request.setAttribute("knsjbList", knsjbList);
		}
		// =====================��Ŀά��=============================
		else if ("xmwh".equalsIgnoreCase(manu)) {

			XszzCommService commService = new XszzCommService();
			// ������Ŀ�б�
			List<HashMap<String, String>> xmList = commService
					.getShXmList(myForm);
			request.setAttribute("xmList", xmList);
			
			// ��˼����б�
			List<HashMap<String, String>> shjbList = dao.getSelectList("shjb");
			request.setAttribute("shjbList", shjbList);

			// ���������б�
			List<HashMap<String, String>> rsjbList = dao.getSelectList("rsjb");
			request.setAttribute("rsjbList", rsjbList);

			// ���Ƽ����б�
			List<HashMap<String, String>> kzjbList = dao.getSelectList("kzjb");
			request.setAttribute("kzjbList", kzjbList);

			// ��Ŀ�����Ƿ��б�
			List<HashMap<String, String>> szsfList = dao
					.getSelectList("szsflx");
			request.setAttribute("szsfList", szsfList);

			// ѧ���б�
			List<HashMap<String, String>> xzList = dao.getWhList("view_xsjbxx",
					"xz", "xz", "", "", "");
			xzList.remove(0);
			request.setAttribute("xzList", xzList);

			// ���������б�
			List<HashMap<String, String>> sqzqList = dao.getSelectList("sqzq");
			request.setAttribute("sqzqList", sqzqList);

			// �Ƿ���Ҫ����б�
			List<HashMap<String, String>> sfjeList = dao.getSelectList("sfje");
			request.setAttribute("sfjeList", sfjeList);

			// �Ƿ�ּ��б�
			List<HashMap<String, String>> sffjList = dao.getSelectList("sffj");
			request.setAttribute("sffjList", sffjList);

			// ��������б�
			List<HashMap<String, String>> jelxList = dao.getSelectList("jelx");
			request.setAttribute("jelxList", jelxList);

			// ���������б�
			List<HashMap<String, String>> kgztList = dao.getSelectList("kgzt");
			request.setAttribute("kgztList", kgztList);
			
			// ����ʱ���б�
			List<HashMap<String, String>> pdsjList = dao.getSelectList("pdsj");
			request.setAttribute("pdsjList", pdsjList);
			
			// �������б�
			List<HashMap<String, String>> knsList = dao.getWhList(
					"xszz_xmfjqkb", "fjmc", "fjmc", "", "xmdm", knsdm);
			request.setAttribute("knsList", knsList);
			
			// ��������
			List<HashMap<String, String>> tjlxList = dao.getSelectList("zhftj");
			tjlxList.remove(0);
			request.setAttribute("tjlxList", tjlxList);
		}
		// =====================��������=============================
		else if ("rssz".equalsIgnoreCase(manu)) {

			XszzCommDAO commDao = new XszzCommDAO();

			// ������Ŀ�б�
			String query = getFlowControlSql(myForm);
			List<HashMap<String, String>> zzxmList = commDao.getZzxmList(mklx,myForm.getQueryequals_xmlb(),query);
			request.setAttribute("zzxmList", zzxmList);

			// ���Ƽ����б�
			List<HashMap<String, String>> kzjbList = dao.getSelectList("kzjb");
			request.setAttribute("kzjbList", kzjbList);
 
		}
		// =====================�����ѯ=============================
		else if ("jgcx".equalsIgnoreCase(manu)) {
			XszzCommService commService = new XszzCommService();

			// ������Ŀ�б�
			List<HashMap<String, String>> xmList = commService
					.getShXmList(myForm);
			request.setAttribute("xmList", xmList);
		}

		// =====================��Ŀ�������=============================
		else if ("sqsh".equalsIgnoreCase(manu)) {

			// ������Ŀ�б�
			List<HashMap<String, String>> gxList = dao.getWhList(
					"xszz_jtcygxb", "dm", "mc", "", "", "");
			gxList.remove(0);
			request.setAttribute("gxList", gxList);
		}
		// =====================�ֶ�����=============================
		else if ("zdsz".equalsIgnoreCase(manu)) {

			// ������Ŀ�б�
			List<HashMap<String, String>> xmList = dao.getWhList("xszz_zzxmb",
					"xmdm", "xmmc", "", "mrxm", "��");
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "ws");
			map.put("mc", "����");
			
			xmList.add(map);
			
			request.setAttribute("xmList", xmList);
		}
		// =====================��������=============================
		else if ("tjsz".equalsIgnoreCase(manu)) {

			// ��Ŀ�б�
			List<HashMap<String, String>> xmList = dao.getWhList(
					"xszz_com_wszxjdmb", "xmdm", "xmmc", "", "", "");

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "kns");
			map.put("mc", "�����������϶�");
			xmList.add(map);

			map = new HashMap<String, String>();
			map.put("dm", "xfhj");
			map.put("mc", "ѧ�ѻ���");
			xmList.add(map);
			request.setAttribute("xmList", xmList);

			// �����б�
			List<HashMap<String, String>> tjList = dao.getWhList("xszz_tjb",
					"tjdm", "tjmc", "", "", "");
			request.setAttribute("tjList", tjList);

			// ���������б�
			List<HashMap<String, String>> tjlxList = dao.getSelectList("tjlx");
			request.setAttribute("tjlxList", tjlxList);

		}
		
		//�������Ƿ����ģ��
		if ("yes".equals(myForm.getIskns())){
			request.setAttribute("knsdl", XMLReader.getFlowControl("xszz", "knsdl"));
			//�������϶��Ƿ������"��ͥ�������"
			request.setAttribute("jtqkdc", XMLReader.getFlowControl("xszz", "jtqkdc"));
		}
		
	}

	/**
	 * ���ѧ����Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * ���ѧ����Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDetStuInfo(String xh) {
		return CommonQueryDAO.getDetStuInfo(xh);
	}

	/**
	 * ���ѧ��������Ϣ���б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszzList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getXszzListInfo(tableName, model, colList, other_query);
	}

	/**
	 * ���ѧ�����������Ϣ����ϸ��
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 */
	public HashMap<String, String> getXszzInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getXszzInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * ɾ��ѧ��������Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @throws Exception
	 */
	public boolean delXszz(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();

		return dao.delDate(form, model);
	}

	/**
	 * ����ѧ�����������Ϣ��������
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param arrzd(�����ֶ�)
	 * @param onezd(��һ�ֶ�)
	 * @param notnull(�ǿ��ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean saveXszz(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * ���������������Ϣ��������
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param onezd(��һ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean saveXszz(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * ����ѧ�����������Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param onezd(��һ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean updateXszz(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}

	/**
	 * @describe ɾ�����ϴ��ļ�
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param dzzd(��ַ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String dzzd, String pk,
			String pkValue) throws Exception {
		return dao.fileDel(tableName, dzzd, pk, pkValue);
	}

	/**
	 * ���ϵͳ��ǰʱ��
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		return dao.getNowTime(lx);
	}

	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		return dao.getOneValue(tableName, dm, pk, pkValue);
	}

	/**
	 * �����ļ��ϴ�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String uploadFile(XszzTyForm model, HttpServletRequest request)
			throws FileNotFoundException, IOException {
		// �����ļ��ϴ�
		FormFile file = model.getUploadFile();
		String filePath = "";
		String fName = "";

		if (file != null && !Base.isNull(file.getFileName())) {
			String dir = "/upload/xszz";
			File f = new File(dir);
			if (!f.exists()) {
				f.mkdirs();
			}
			Timestamp date = new Timestamp(System.currentTimeMillis());
			String dateStr = date.toString().substring(0, 19);
			dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
					.replaceAll(":", "");
			int size = file.getFileSize();
			if (size < 10485760 && size != 0) {
				fName = dateStr + file.getFileName();
				InputStream in = file.getInputStream();
				filePath = dir + "/" + fName;
				OutputStream out = new FileOutputStream(filePath);
				int bytesRead = 0;
				byte[] buffer = new byte[size];
				while ((bytesRead = in.read(buffer, 0, size)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				out.close();
				in.close();
			} else {
				request.setAttribute("fileUplod", "false");
			}
		}
		return filePath;
	}
	
	/**
	 * ���ѧ��������ҳ
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getResultList(ArrayList<String[]> list,
			XszzTyForm model) {

		// ��ҳ
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		if (list != null && list.size() > 0) {

			Pages pages = model.getPages();
			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}

		return rsList;
	}
		
	/**
	 * ���ѧ��������ҳ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getResultList(
			List<HashMap<String, String>> list, XszzTyForm model) {

		// ��ҳ
		List<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();
		Pages pages = model.getPages();
		if (list != null && list.size() > 0) {
			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}else{
			pages.setMaxRecord(0);
		}

		return rsList;
	}
	
	/**
	 * ѧ����������
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expXszzData(String title, List<HashMap<String, String>> topTr,
			ArrayList<String[]> list, OutputStream os) throws Exception {

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ����ͷ
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 0, map.get("cn"), wcf2));
			}
		}

		//�������
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				String[] info = list.get(i);

				if (info != null && info.length > 0) {

					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 1, info[j], wcf2));
					}
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ���ָ������ֶ�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getTableZd(String tableName) throws Exception {
		return dao.getTableZd(tableName);
	}
	
	/**
	 * ��ð༶����
	 * 
	 * @author luojw
	 */
	public String getBjrs(HashMap<String, String> map) {
		return dao.getBjrs(map);
	}
	
	/**
	 * ���������Ŀ�����Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getXmxgInfo(XszzTyForm model) {

		String tableName = "view_xszz_comm_xmwh";
		// ����
		String pk = "pk";
		// ����ֵ��
		String pkValue = model.getPkValue();
		// ����ֶ�
		String[] colList = new String[] { "bzrkz", "bzrsh", "fdykz", "fdysh",
				"kgzt", "kzjb", "mrxm", "rskz", "rssx", "sffj", "jelx", "sfje",
				"shjb", "sqzq", "xmb", "xmdm", "xmmc", "xmsm", "xxdm", "xxkz",
				"xxsh", "xykz", "xysh", "xmlb", "pdsj", "xssx" };

		// ��Ŀ�����Ϣ
		HashMap<String, String> map = (Base.isNull(pkValue)) ? new HashMap<String, String>()
				: getXszzInfo(tableName, pk, pkValue, colList);

		return map;
	}
	
	/**
	 * �ж��Ƿ���������N32��
	 * 
	 * @author luojw
	 */
	public Boolean isKns(HashMap<String, String> map) {

		// ѧ��
		String xn = Base.currXn;
		// ѧ��
		String xq = Base.currXq;
		// ���
		String nd = Base.currNd;
		// ����ѧ��
		String pjxn = Base.getJxjsqxn();
		// ����ѧ��
		String pjxq = Base.getJxjsqxq();
		// �������
		String pjnd = Base.getJxjsqnd();
		// ��������Ŀ����
		String xmdm = XszzXmdm.XSZZ_KNS;
		// ��Ŀ�����Ϣ
		XszzTyForm model = new XszzTyForm();
		model.setPkValue(xmdm);
		HashMap<String, String> xmInfo = getXmxgInfo(model);
		// ��������������
		String sqzq = xmInfo.get("sqzq");
		// ��������˼���
		String shjb = xmInfo.get("shjb");

		map.put("xn", xn);
		map.put("xq", xq);
		map.put("nd", nd);
		map.put("pjxn", pjxn);
		map.put("pjxq", pjxq);
		map.put("pjnd", pjnd);
		map.put("sqzq", sqzq);
		map.put("shjb", shjb);

		return dao.isKns(map);
	}
	
	/**
	 * ��������ȫ����ʼ��
	 * @return
	 * @throws Exception 
	 */
	public boolean dknxCsh() throws Exception {
		return dao.dknxCsh();
	}
	
	/**
	 * �������޲��ֳ�ʼ��
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public boolean dknxCsh(String[] bjdm) throws Exception {
		return dao.dknxCsh(bjdm);
	}
	
	/**
	 * ��ȡ����ͳ�Ʊ����б�
	 * 
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getTjbbList() {
		// ------------2010.9.25 edit by luojw-----------------------
		List<HashMap<String, String>> list = dao.getWhList("xszz_zztjbb",
				"tjbbdm", "tjbbmc", "", "", "");
		String xxdm = Base.xxdm;
		// �й��ش�
		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
			list = dao.getWhList("xszz_zzxmb", "xmdm", "xmmc", "", "", "");
		}
		return list;
	}
	
	
	/**
	 * ��ʼ����Ŀ
	 * @param model
	 * @param manu
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public XszzTyForm initXmdm(XszzTyForm model, String manu)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		XszzCommService commonService = new XszzCommService();
		String xxdm=Base.xxdm;
		
		//�������ó�ʼ��
		if ("rssz".equalsIgnoreCase(manu)) {

			// ��Ŀ�����б�
			List<HashMap<String, String>> xmdmList = dao.getXmxxByRssz("%",getFlowControlSql(model));

			// ��һ����Ŀ����
			if (xmdmList.size() > 0) {
				model.setXmdm(xmdmList.get(0).get("dm"));
			}
			// ��������ʱ ���Ƽ���ѧԺ���༶�ȣ�
			HashMap<String, String> kzjbMap = dao.getXszzInfo("xszz_zzxmb",
					"xmdm", model.getXmdm(), new String[] { "kzjb" });

			model.setKzjb(kzjbMap.get("kzjb"));
			
		}else if("jgcx".equalsIgnoreCase(manu) 
			//�ش����� �����ѯ��ʼ��
				&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){ 
			List<HashMap<String, String>> xmdmList =  commonService.getShXmList(model);

			// ��һ����Ŀ����
			if (xmdmList.size() > 0) {
				model.setXmdm(xmdmList.get(0).get("xmdm"));
			}

			// ��Ŀ��Ϣ
			HashMap<String, String> xmMap = dao.getXszzInfoByXmdm(model
					.getXmdm());
			model.setXmb(xmMap.get("xmb"));
		
		}else if ("jgcx".equalsIgnoreCase(manu)) {
			//�����ѯ��ʼ��
			List<HashMap<String, String>> xmdmList = dao.getXszzxmList(
					"xszz_zzxmb", "xmdm", "xmmc", "", "kgzt", "��������");

			// ��һ����Ŀ����,���������������ģ����Ϊ�������϶�
			if ("yes".equals(model.getIskns())){
				model.setXmdm(XszzXmdm.XSZZ_KNS);
			} else if (xmdmList.size() > 0) {
				model.setXmdm(xmdmList.get(0).get("dm"));
			}

			// ��Ŀ��Ϣ
			HashMap<String, String> xmMap = dao.getXszzInfoByXmdm(model
					.getXmdm());
			model.setXmb(xmMap.get("xmb"));
		
		//��Ŀ��˳�ʼ��
		} else if ("xmsh_xs".equalsIgnoreCase(manu)) {

			List<HashMap<String, String>> xmdmList = commonService
					.getXmshList(model);

			// ��һ����Ŀ����
			if (xmdmList.size() > 0) {
				model.setXmdm(xmdmList.get(0).get("xmdm"));
			}
		}
		return model;
	}
	
	/**
	 * ��ʼ����Ŀ
	 * @param model
	 * @param manu
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public XszzTyForm initXmdm(XszzTyForm model, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		XszzCommService commonService = new XszzCommService();
	
		List<HashMap<String, String>> xmdmList = commonService
				.getXmshList(model);

		// ��һ����Ŀ����
		if (xmdmList.size() > 0) {
			model.setXmdm(xmdmList.get(0).get("xmdm"));
		}
		
		request.setAttribute("xmshList", xmdmList);
		
		return model;
	}
	
	
	
	/**
	 * ���Ʋ�ѯ��Χ
	 * @param request
	 * @param myForm
	 */
	public String getFlowControlSql(XszzTyForm myForm) {
		//�������Ƿ���Ϊ����ģ��
		boolean knsdl = Boolean.valueOf(XMLReader.getFlowControl("xszz", "knsdl"));
		//������"�������϶�"��Ŀ�Ƿ������"��ͥ�������"
		boolean jtqkdc = Boolean.valueOf(XMLReader.getFlowControl("xszz", "jtqkdc"));
		
		if (knsdl){
			String sql = "";
			
			if ("yes".equals(myForm.getIskns())){
				if (jtqkdc){
					sql = " and xmdm='"+XszzXmdm.XSZZ_KNS+"' ";
				} else {
					sql = " and (xmdm='"+XszzXmdm.XSZZ_KNS+"' or xmdm='"+XszzXmdm.XSZZ_JTQKDC+"') ";
				}
				
			} else {
				sql = " and xmdm<>'"+XszzXmdm.XSZZ_KNS+"' and xmdm<>'"+XszzXmdm.XSZZ_JTQKDC+"' ";
			}
			return sql;
		}
		return "";
	}
}
