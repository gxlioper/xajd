package xsgzgl.gygl.zsxxgl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.AbstractExportService;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.xtwh.zpgl.XtwhZpglService;
import xsgzgl.gygl.comm.GyglNewService;
import xsgzgl.gygl.cwgl.CwglDAO;
import xsgzgl.gygl.cwgl.CwglForm;

public class ZsxxglService extends GyglNewService {
	protected static final transient Logger log = Logger
			.getLogger(AbstractExportService.class);
	private ZsxxglDao dao = new ZsxxglDao();

	/**
	 * 
	 * @����:��ȡ�������б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-15 ����03:41:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKtsList(ZsxxglForm zf, User user)
			throws Exception {
		return dao.getKtsList(zf, user);
	}

	/**
	 * 
	 * @����:�ϼ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-16 ����10:43:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return Integer ��������
	 * @throws
	 */
	public int getHjrs(ZsxxglForm zsxxglForm, User user) throws Exception {

		return dao.getHjrs(zsxxglForm, user);
	}

	/**
	 * 
	 * @����:������У
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-15 ����04:41:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zf
	 * @param user
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public String pllx(ZsxxglForm zf, User user) throws Exception {
		CwglDAO cwglDao = new CwglDAO();
		CwglForm myForm = new CwglForm();
		// ����������Ϣ
		myForm.setTssj(zf.getTssj());
		myForm.setTsyy(zf.getTsyy());
		myForm.setBz(zf.getBz());
		myForm.setSfqccwss(zf.getSfqccwss());
		// ����ѧ��ѧ��
		myForm.setXn(zf.getXn());
		myForm.setXq(zf.getXq());

		// ����Ҫ���޵�ѧ��
		List<String> xhList = new ArrayList<String>();
		List<HashMap<String, String>> list = dao.getTsxs(user);
		for (HashMap<String, String> hm : list) {
			xhList.add(hm.get("xh"));
		}
		myForm.setPk_xh(xhList.toArray(new String[] {}));
		return cwglDao.saveTsxx(myForm, user);
	}

	/**
	 * ��ѯ��λ
	 * 
	 * @param model
	 * @return
	 */
	public List<String[]> queryCw(ZsxxglForm myForm, HttpServletRequest request)
			throws Exception {
		return dao.queryCw(myForm, request);
	}

	/**
	 * 
	 * @����: ��ȡ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-26 ����10:35:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 *             LinkedHashMap<String,List<String[]>> ��������
	 * @throws
	 */
	public File getExportData(ZsxxglForm myForm, HttpServletRequest request)
			throws Exception {
		LinkedHashMap<String, List<String[]>> dataMap = new LinkedHashMap<String, List<String[]>>();
		List<String[]> list = dao.queryCw(myForm, request,false);
		int zdcws=0;//���λ��
		String qsh = null;
		int i = 0;
		List<String[]> newList=null;
		if(null==list||list.size()<=0){
			return null;
		}
		String qsmc=list.get(0)[2];
		//�����Һ�����
//		Collections.sort(list, new Comparator<String[]>() {
//			public int compare(String[] arg0, String[] arg1) {
//				try {
//					return Integer.parseInt(arg0[3]) > Integer
//							.parseInt(arg1[3]) ? 1 : -1;
//				} catch (Exception e) {
//					return 0;
//				}
//			}
//		});
		for (String[] sz : list) {
			if (qsh == null) {
				qsh = sz[3];
				newList = new ArrayList<String[]>();
			}
			newList.add(sz);
			if (i + 1 >= list.size() || !qsh.equals(list.get(i + 1)[3])) {// �����ǰ���Һź���һ�����ҺŲ�������¼
				//��¼���λ��
				if(newList.size()>=zdcws){
					zdcws=newList.size();
				}
				dataMap.put(qsh, newList);
				qsh = null;
			}
			i++;
		}
		return exportData(qsmc,zdcws,dataMap, myForm);
	}
	/**
	 * @����: �����ٽ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-26 ����04:29:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qsmc
	 * @param zdcws
	 * @param data
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public File exportData(String qsmc,int zdcws,HashMap<String, List<String[]>> data,ZsxxglForm myForm)
			throws Exception {
		int cws = zdcws;
		int cols = 1;// ��ʼ��
		// �����ļ���� ����ʱĿ¼
		File file = createFile();
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		try {
			FileOutputStream stream = new FileOutputStream(file);
			// ����excel������
			WritableWorkbook wwb = Workbook.createWorkbook(stream);
			WritableSheet ws = wwb.createSheet(IExportService.SHEET_NAME, 0);
			// ���ñ�ͷ����
			WritableCellFormat wcf = new WritableCellFormat();
			WritableFont wf = new WritableFont(WritableFont.ARIAL);
			wf.setBoldStyle(WritableFont.BOLD);
			wf.setPointSize(10);
			wcf.setFont(wf);
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setBackground(Colour.GREEN);
			wcf.setWrap(false);
			// ��ͷд��
			WritableCellFormat wcftitle = new WritableCellFormat();
			WritableFont wftitle = new WritableFont(WritableFont.ARIAL);
			wftitle.setPointSize(10);
			wftitle.setBoldStyle(WritableFont.BOLD);
			wcftitle.setFont(wf);
			wcftitle.setAlignment(Alignment.CENTRE);
			ws.addCell(new Label(0, 0, qsmc+"¥����������",wcftitle));
			if("12727".equals(Base.xxdm)){
				ws.mergeCells(0, 0, cws + 2, 0);
			} else {
				ws.mergeCells(0, 0, cws + 1, 0);
			}
			ws.addCell(new Label(0, 1, "���Һ�", wcf));
			for (int i = 1; i <= cws; i++) {
				ws.addCell(new Label(i, 1, String.valueOf(i), wcf));
			}
			if("12727".equals(Base.xxdm)){
				ws.addCell(new Label(cws + 1, 1, "����Ա", wcf));
				ws.addCell(new Label(cws + 2, 1, "��ע", wcf));
			}else{
				ws.addCell(new Label(cws + 1, 1, "��ע", wcf));
			}
			Iterator<Entry<String, List<String[]>>> it = data.entrySet()
					.iterator();
			int row = 2;
			WritableCellFormat cellWcf = new WritableCellFormat();
			cellWcf.setAlignment(Alignment.CENTRE);
			while (it.hasNext()) {
				Entry<String, List<String[]>> entry = it.next();

				// �ϲ���  ���Һ�
				ws.mergeCells(0, row, 0, row + 1);
				ws.addCell(new Label(0, row, entry.getKey(), cellWcf));
				
				List<String[]> dataList = entry.getValue();
				//	System.out.println("+-���Һ�:"+entry.getKey()+"--");
				for (String[] sz : dataList) {
				//	System.out.println("-��ס��Ա:"+sz[7]+"--");
					ws.addCell(new Label(cols, row, sz[7],cellWcf));
					ws.addCell(new Label(cols, row + 1, sz[9],cellWcf));
					cols++;
				}
				//����Ǻ�����ũ��ѧԺ ע�븨��Ա
				if("12727".equals(Base.xxdm)){
					String fdy = this.getFdy(entry.getKey(),searchTj,inputV);
					// �ϲ���  ����Ա
					ws.mergeCells(cws + 1, row, cws + 1, row + 1);
					// �ϲ���  ��ע
					ws.mergeCells(cws + 2 , row, cws + 2, row + 1);
					ws.addCell(new Label(cws + 1, row, fdy, cellWcf));
				} else {
					// �ϲ���  ��ע
					ws.mergeCells(cws + 1, row, cws + 1, row + 1);
				}
				row += 2;
				cols = 1;
			}
			wwb.write();
			wwb.close();
		} catch (Exception ex) {
			log.error("Export failed ", ex);
			throw new Exception(ex);
		}
		return file;
	}

	// ������ʱ�ļ�
	private File createFile() {
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		// ���������ļ�
		File file = new File(tempDir.getPath() + "/"
				+ String.valueOf(System.currentTimeMillis()) + ".xls");
		file.setWritable(true);
		return file;
	}

	/**
	 * ��ȡ��ǰ��ѯ���ݼ�����ס�Ĵ�λ����
	 * 
	 * @return
	 */
	public String getYrzrs(ZsxxglForm myForm, HttpServletRequest request)
			throws Exception {
		return dao.getYrzrs(myForm, request);
	}

	public String getSearchTjstr(ZsxxglForm myForm, HttpServletRequest request)
			throws Exception {

		SearchService searchService = new SearchService();

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		String searchTjstr = "";
		if (searchTj != null && !"".equalsIgnoreCase(searchTj)) {
			String[] tj = searchTj.replace("?", "split").split("split");
			for (int i = 0; i < inputV.length; i++) {
				searchTjstr += tj[i] + "'" + inputV[i] + "'";
			}
			searchTjstr += tj[inputV.length];
		}

		String searchTjByUser = "";
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // ��Ԣ����Ա

		if (searchTjByGyfdy != null && !"".equalsIgnoreCase(searchTjByGyfdy)) {// �û�Ϊ��Ԣ����Ա
			searchTjByUser = searchTjByGyfdy;
		} else {
			searchTjByUser = searchService.getSearchTjByUser(request, "a",
					"xydm", "xsbjdm"); // ѧԺ�û�
		}

		return searchTjstr + searchTjByUser;
	}

	/**
	 * ��λ�Ե�
	 * 
	 * @param pk
	 * @return
	 */
	public boolean cwdd(String[] pk, ZsxxglForm myForm) {
		return dao.cwdd(pk, myForm);
	}

	/**
	 * ������ͷ��
	 * 
	 * @throws Exception
	 */
	public String expCtk(ZsxxglForm myForm, HttpServletRequest request,
			HttpServletResponse response, String path) throws Exception {
		List<String[]> list = dao.queryCw_expCtk(myForm, request);
		// ����ֻ����Excel�������Ķ���
		Workbook rw = Workbook.getWorkbook(new File(path));
		// ������д���Excel����������
		// WritableWorkbook wwb = Workbook.createWorkbook(new
		// File("c:\\aa.xls"),
		// rw);
		WritableWorkbook wwb = Workbook.createWorkbook(response
				.getOutputStream(), rw);
		// ��ȡ��һ�Ź�����
		try {
			jxl.write.WritableSheet ws = wwb.getSheet(0);
			// ��õ�һ����Ԫ�����
			jxl.write.WritableCell wc = ws.getWritableCell(2, 1);
			// ������ͷ�������е�Ԫ��
			jxl.write.WritableCell[] ctks = new jxl.write.WritableCell[20];
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 4; j++) {
					ctks[i * 4 + j] = ws.getWritableCell(j, i + 1);
				}
			}

			// 1.���Ƽ�����Ҫ�Ĵ�ͷ����
			int rowHight = 560;// �и�
			int ctkRowCount = Math.round(list.size() / 2f);// ��ͷ������
			String[] cwxx_left;// ��λ��Ϣ�������
			// jxl.write.WritableCell copy= wc.copyTo(0, 41);
			jxl.write.WritableCell[] ctks_left_temp = new jxl.write.WritableCell[20];
			jxl.write.WritableCell[] ctks_right_temp = new jxl.write.WritableCell[20];
			XtwhZpglService xtwhzpglService = new XtwhZpglService();
			for (int i = 0; i < ctkRowCount; i++) {
				// ���ƴ�ͷ����Ϣ
				for (int i_n = 0; i_n < 5; i_n++) {
					for (int j = 0; j < 4; j++) {
						jxl.write.WritableCell copy_left = ctks[i_n * 4 + j]
								.copyTo(j, i_n + i * 6 + 1);
						jxl.write.WritableCell copy_right = ctks[i_n * 4 + j]
								.copyTo(j + 5, i_n + i * 6 + 1);
						ws.addCell(copy_left);
						ws.addCell(copy_right);
						ctks_left_temp[i_n * 4 + j] = copy_left;
						ctks_right_temp[i_n * 4 + j] = copy_right;
					}
				}

				// �ϲ���Ƭ��Ԫ��
				ws.mergeCells(0, i * 6 + 1, 1, i * 6 + 4);
				ws.mergeCells(5, i * 6 + 1, 6, i * 6 + 4);

				// �����и�
				ws.setRowView(i * 6 + 1, rowHight);
				ws.setRowView(i * 6 + 2, rowHight);
				ws.setRowView(i * 6 + 3, rowHight);
				ws.setRowView(i * 6 + 4, rowHight);
				ws.setRowView(i * 6 + 5, rowHight);

				// �������
				// {"ldmc","qsh","cwh","xh","xm","xymc","zymc","bjmc"}
				cwxx_left = list.get(i * 2);
				((Label) ctks_left_temp[3]).setString(cwxx_left[4]);
				((Label) ctks_left_temp[7]).setString(cwxx_left[5]);
				((Label) ctks_left_temp[11]).setString(cwxx_left[7]);
				((Label) ctks_left_temp[15]).setString(cwxx_left[3]);
				((Label) ctks_left_temp[17])
						.setString((cwxx_left[2] == null ? "" : cwxx_left[2])
								+ "��");
				((Label) ctks_left_temp[19]).setString(cwxx_left[0]+"-"+cwxx_left[1]);
				if (cwxx_left[3] != null && !"".equals(cwxx_left[3])) {
					// ������Ƭ
					File file = xtwhzpglService.getPhotoFile(cwxx_left[3]);
					if (file != null) {
						WritableImage wi = new WritableImage(0, i * 6 + 1, 2,
								4, file);
						ws.addImage(wi);
					}
				}
				if (i * 2 + 1 < list.size()) {
					cwxx_left = list.get(i * 2 + 1);
					((Label) ctks_right_temp[3]).setString(cwxx_left[4]);
					((Label) ctks_right_temp[7]).setString(cwxx_left[5]);
					((Label) ctks_right_temp[11]).setString(cwxx_left[7]);
					((Label) ctks_right_temp[15]).setString(cwxx_left[3]);
					((Label) ctks_right_temp[17])
							.setString((cwxx_left[2] == null ? ""
									: cwxx_left[2])
									+ "��");
					((Label) ctks_right_temp[19]).setString(cwxx_left[0]+"-"+cwxx_left[1]);
					if (cwxx_left[3] != null && !"".equals(cwxx_left[3])) {
						// ������Ƭ
						File file = xtwhzpglService.getPhotoFile(cwxx_left[3]);
						if (file != null) {
							WritableImage wi = new WritableImage(5, i * 6 + 1,
									2, 4, file);
							ws.addImage(wi);
						}
					}
				}

			}

			// jxl.write.WritableCell copy= wc.copyTo(0, 41);
			// ws.mergeCells(15, 1, 16, 4);
			// ws.addCell(copy);

			// System.out.println("##:"+wc.getContents());
			// System.out.println("##1:"+ws.getWritableCell(2,
			// 5).getContents());
			// �жϵ�Ԫ�������, ������Ӧ��ת��
			// if (wc.getType() == CellType.LABEL) {
			// Label l = (Label) wc;
			// l.setString("The value has been modified.");
			// }
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} finally {
			// д��Excel����
			wwb.write();
			// �رտ�д���Excel����
			wwb.close();
			// �ر�ֻ����Excel����
			rw.close();
		}
		return null;
	}

	/**
	 * @throws Exception
	 * @����:TODO(����Ա�ж��Ƿ�ɲ�����λ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-6-20 ����03:16:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return boolean ��������
	 * @throws
	 */
	public boolean getCzkz() throws Exception {
		// TODO �Զ����ɷ������
		String sj = dao.getCwczsjfw();
		if (sj == null || "".equalsIgnoreCase(sj)) {// û������ʱ��
			return true;
		} else {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			String[] ksjssj = sj.split(",");
			if (ksjssj.length == 1) {// ֻ�����˿�ʼʱ��
				Date kssj = sf.parse(ksjssj[0] + " 00:00:00");
				return date.after(kssj);
			} else if (ksjssj.length == 2 && ksjssj[0].equalsIgnoreCase("")) {// ֻ�����˽���ʱ��
				Date jssj = sf.parse(ksjssj[1] + " 23:59:59");
				int i = date.compareTo(jssj);
				return date.before(jssj);
			} else if (ksjssj.length == 2) {
				Date kssj = sf.parse(ksjssj[0] + " 00:00:00");
				Date jssj = sf.parse(ksjssj[1] + " 23:59:59");
				return date.after(kssj) && date.before(jssj);
			}

		}
		return true;
	}

	/**
	 * ס����Ϣ�����Զ��嵼��
	 * 
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> queryExportCw(ZsxxglForm myForm,
			HttpServletRequest request) throws Exception {
		return dao.queryExportCw(myForm, request);
	}

	/**
	 * �����סԭ����Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRzyyList(ZsxxglForm model) throws Exception {
		return dao.getRzyyList(model);
	}

	/**
	 * ������סԭ����Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String saveRzyy(ZsxxglForm model, String type) throws Exception {
		return dao.saveRzyy(model, type);
	}
	/**
	 * @����: �����Уδ����ѧ����Ϣ�б�
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-12-29 ����09:33:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zf
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLxxsList(ZsxxglForm zf, User user)
		throws Exception {
		return dao.getLxxsList(zf, user);
	}
	/**
	 * @����: δ����ѧ����ѧԺ������ѧԺ����
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-12-29 ����09:30:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xydm
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getXymc(String xydm) throws Exception{
		return dao.getXymc(xydm);
	}
	/**
	 * @����: δ����ѧ����רҵ������רҵ����
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2015-12-29 ����09:32:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zydm
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getZymc(String zydm) throws Exception{
		return dao.getZymc(zydm);
	}
	
	/**
	 * 
	 * @����: ѧ��Υ����ϸ��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-2-25 ����10:16:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXswjxx(ZsxxglForm model, User user)
			throws Exception {
		return dao.getXswjxx(model, user);
	}
	
	/**
	 * 
	 * @����:��ȡ����Ա��������ũ�ѵ���������������ר�á�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2016-6-12 ����03:37:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qsh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFdy(String qsh,String searchTj,String[] inputV){
		return dao.getFdy(qsh,searchTj,inputV);
	}

	/**
	 * @throws Exception  
	 * @����:�������汸ע���޸ģ�pkValuesΪ���¥���ţ����Һţ���λ��ƴ�ӵ������ַ���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��29�� ����2:25:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkValues
	 * @param bz
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveBzBatchForUpdate(String pkValues, String bz) throws Exception {
		String[] pkValueArr = pkValues.split(",");
		return dao.saveBzBatchForUpdate(pkValueArr,bz);
	}
	
	/** 
	 * @����:���һ���(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-23 ����02:28:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @param qsh
	 * @param cwh
	 * @param oldXh
	 * @param newXh
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean qshr(String lddm,String qsh,String cwh,String xh) throws Exception{
		boolean result = false;
		result = dao.deleteCwxxByXh(xh);
		if(result){
			result = dao.updateCwxxByDetils(lddm, qsh, cwh, xh);
		}
		return result;
	}
	
	/**
	 * 
	 * @����: ���ְཻͨҵ����ѧԺ�Զ��嵼��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-1 ����11:47:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> queryExportCwForGsjt(ZsxxglForm myForm,
			HttpServletRequest request) throws Exception {
		return dao.queryExportCwForGsjt(myForm, request);
	}
	
}
