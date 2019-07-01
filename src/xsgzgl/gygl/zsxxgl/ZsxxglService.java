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
	 * @描述:获取可退宿列表
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-15 下午03:41:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKtsList(ZsxxglForm zf, User user)
			throws Exception {
		return dao.getKtsList(zf, user);
	}

	/**
	 * 
	 * @描述:合计总人数
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-16 上午10:43:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return Integer 返回类型
	 * @throws
	 */
	public int getHjrs(ZsxxglForm zsxxglForm, User user) throws Exception {

		return dao.getHjrs(zsxxglForm, user);
	}

	/**
	 * 
	 * @描述:批量离校
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-15 下午04:41:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zf
	 * @param user
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	public String pllx(ZsxxglForm zf, User user) throws Exception {
		CwglDAO cwglDao = new CwglDAO();
		CwglForm myForm = new CwglForm();
		// 设置退宿信息
		myForm.setTssj(zf.getTssj());
		myForm.setTsyy(zf.getTsyy());
		myForm.setBz(zf.getBz());
		myForm.setSfqccwss(zf.getSfqccwss());
		// 设置学年学期
		myForm.setXn(zf.getXn());
		myForm.setXq(zf.getXq());

		// 设置要退宿的学生
		List<String> xhList = new ArrayList<String>();
		List<HashMap<String, String>> list = dao.getTsxs(user);
		for (HashMap<String, String> hm : list) {
			xhList.add(hm.get("xh"));
		}
		myForm.setPk_xh(xhList.toArray(new String[] {}));
		return cwglDao.saveTsxx(myForm, user);
	}

	/**
	 * 查询床位
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
	 * @描述: 获取导出数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-26 上午10:35:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 *             LinkedHashMap<String,List<String[]>> 返回类型
	 * @throws
	 */
	public File getExportData(ZsxxglForm myForm, HttpServletRequest request)
			throws Exception {
		LinkedHashMap<String, List<String[]>> dataMap = new LinkedHashMap<String, List<String[]>>();
		List<String[]> list = dao.queryCw(myForm, request,false);
		int zdcws=0;//最大床位数
		String qsh = null;
		int i = 0;
		List<String[]> newList=null;
		if(null==list||list.size()<=0){
			return null;
		}
		String qsmc=list.get(0)[2];
		//按寝室号排序
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
			if (i + 1 >= list.size() || !qsh.equals(list.get(i + 1)[3])) {// 如果当前寝室号和下一个寝室号不相等则记录
				//记录最大床位数
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
	 * @描述: 导出促进
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-26 下午04:29:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qsmc
	 * @param zdcws
	 * @param data
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public File exportData(String qsmc,int zdcws,HashMap<String, List<String[]>> data,ZsxxglForm myForm)
			throws Exception {
		int cws = zdcws;
		int cols = 1;// 起始列
		// 导出文件存放 的临时目录
		File file = createFile();
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		try {
			FileOutputStream stream = new FileOutputStream(file);
			// 创建excel工作表
			WritableWorkbook wwb = Workbook.createWorkbook(stream);
			WritableSheet ws = wwb.createSheet(IExportService.SHEET_NAME, 0);
			// 设置表头字体
			WritableCellFormat wcf = new WritableCellFormat();
			WritableFont wf = new WritableFont(WritableFont.ARIAL);
			wf.setBoldStyle(WritableFont.BOLD);
			wf.setPointSize(10);
			wcf.setFont(wf);
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setBackground(Colour.GREEN);
			wcf.setWrap(false);
			// 表头写入
			WritableCellFormat wcftitle = new WritableCellFormat();
			WritableFont wftitle = new WritableFont(WritableFont.ARIAL);
			wftitle.setPointSize(10);
			wftitle.setBoldStyle(WritableFont.BOLD);
			wcftitle.setFont(wf);
			wcftitle.setAlignment(Alignment.CENTRE);
			ws.addCell(new Label(0, 0, qsmc+"楼栋寝室名单",wcftitle));
			if("12727".equals(Base.xxdm)){
				ws.mergeCells(0, 0, cws + 2, 0);
			} else {
				ws.mergeCells(0, 0, cws + 1, 0);
			}
			ws.addCell(new Label(0, 1, "寝室号", wcf));
			for (int i = 1; i <= cws; i++) {
				ws.addCell(new Label(i, 1, String.valueOf(i), wcf));
			}
			if("12727".equals(Base.xxdm)){
				ws.addCell(new Label(cws + 1, 1, "辅导员", wcf));
				ws.addCell(new Label(cws + 2, 1, "备注", wcf));
			}else{
				ws.addCell(new Label(cws + 1, 1, "备注", wcf));
			}
			Iterator<Entry<String, List<String[]>>> it = data.entrySet()
					.iterator();
			int row = 2;
			WritableCellFormat cellWcf = new WritableCellFormat();
			cellWcf.setAlignment(Alignment.CENTRE);
			while (it.hasNext()) {
				Entry<String, List<String[]>> entry = it.next();

				// 合并列  寝室号
				ws.mergeCells(0, row, 0, row + 1);
				ws.addCell(new Label(0, row, entry.getKey(), cellWcf));
				
				List<String[]> dataList = entry.getValue();
				//	System.out.println("+-寝室号:"+entry.getKey()+"--");
				for (String[] sz : dataList) {
				//	System.out.println("-入住成员:"+sz[7]+"--");
					ws.addCell(new Label(cols, row, sz[7],cellWcf));
					ws.addCell(new Label(cols, row + 1, sz[9],cellWcf));
					cols++;
				}
				//如果是黑龙江农垦学院 注入辅导员
				if("12727".equals(Base.xxdm)){
					String fdy = this.getFdy(entry.getKey(),searchTj,inputV);
					// 合并列  辅导员
					ws.mergeCells(cws + 1, row, cws + 1, row + 1);
					// 合并列  备注
					ws.mergeCells(cws + 2 , row, cws + 2, row + 1);
					ws.addCell(new Label(cws + 1, row, fdy, cellWcf));
				} else {
					// 合并列  备注
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

	// 创建临时文件
	private File createFile() {
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		// 创建导出文件
		File file = new File(tempDir.getPath() + "/"
				+ String.valueOf(System.currentTimeMillis()) + ".xls");
		file.setWritable(true);
		return file;
	}

	/**
	 * 获取当前查询数据集已入住的床位总数
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

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
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
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // 公寓辅导员

		if (searchTjByGyfdy != null && !"".equalsIgnoreCase(searchTjByGyfdy)) {// 用户为公寓辅导员
			searchTjByUser = searchTjByGyfdy;
		} else {
			searchTjByUser = searchService.getSearchTjByUser(request, "a",
					"xydm", "xsbjdm"); // 学院用户
		}

		return searchTjstr + searchTjByUser;
	}

	/**
	 * 床位对调
	 * 
	 * @param pk
	 * @return
	 */
	public boolean cwdd(String[] pk, ZsxxglForm myForm) {
		return dao.cwdd(pk, myForm);
	}

	/**
	 * 导出床头卡
	 * 
	 * @throws Exception
	 */
	public String expCtk(ZsxxglForm myForm, HttpServletRequest request,
			HttpServletResponse response, String path) throws Exception {
		List<String[]> list = dao.queryCw_expCtk(myForm, request);
		// 创建只读的Excel工作薄的对象
		Workbook rw = Workbook.getWorkbook(new File(path));
		// 创建可写入的Excel工作薄对象
		// WritableWorkbook wwb = Workbook.createWorkbook(new
		// File("c:\\aa.xls"),
		// rw);
		WritableWorkbook wwb = Workbook.createWorkbook(response
				.getOutputStream(), rw);
		// 读取第一张工作表
		try {
			jxl.write.WritableSheet ws = wwb.getSheet(0);
			// 获得第一个单元格对象
			jxl.write.WritableCell wc = ws.getWritableCell(2, 1);
			// 整个床头卡的所有单元格
			jxl.write.WritableCell[] ctks = new jxl.write.WritableCell[20];
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 4; j++) {
					ctks[i * 4 + j] = ws.getWritableCell(j, i + 1);
				}
			}

			// 1.复制计算需要的床头卡数
			int rowHight = 560;// 行高
			int ctkRowCount = Math.round(list.size() / 2f);// 床头卡行数
			String[] cwxx_left;// 床位信息——左边
			// jxl.write.WritableCell copy= wc.copyTo(0, 41);
			jxl.write.WritableCell[] ctks_left_temp = new jxl.write.WritableCell[20];
			jxl.write.WritableCell[] ctks_right_temp = new jxl.write.WritableCell[20];
			XtwhZpglService xtwhzpglService = new XtwhZpglService();
			for (int i = 0; i < ctkRowCount; i++) {
				// 复制床头卡信息
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

				// 合并照片单元格
				ws.mergeCells(0, i * 6 + 1, 1, i * 6 + 4);
				ws.mergeCells(5, i * 6 + 1, 6, i * 6 + 4);

				// 设置行高
				ws.setRowView(i * 6 + 1, rowHight);
				ws.setRowView(i * 6 + 2, rowHight);
				ws.setRowView(i * 6 + 3, rowHight);
				ws.setRowView(i * 6 + 4, rowHight);
				ws.setRowView(i * 6 + 5, rowHight);

				// 填充数据
				// {"ldmc","qsh","cwh","xh","xm","xymc","zymc","bjmc"}
				cwxx_left = list.get(i * 2);
				((Label) ctks_left_temp[3]).setString(cwxx_left[4]);
				((Label) ctks_left_temp[7]).setString(cwxx_left[5]);
				((Label) ctks_left_temp[11]).setString(cwxx_left[7]);
				((Label) ctks_left_temp[15]).setString(cwxx_left[3]);
				((Label) ctks_left_temp[17])
						.setString((cwxx_left[2] == null ? "" : cwxx_left[2])
								+ "床");
				((Label) ctks_left_temp[19]).setString(cwxx_left[0]+"-"+cwxx_left[1]);
				if (cwxx_left[3] != null && !"".equals(cwxx_left[3])) {
					// 插入照片
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
									+ "床");
					((Label) ctks_right_temp[19]).setString(cwxx_left[0]+"-"+cwxx_left[1]);
					if (cwxx_left[3] != null && !"".equals(cwxx_left[3])) {
						// 插入照片
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
			// 判断单元格的类型, 做出相应的转化
			// if (wc.getType() == CellType.LABEL) {
			// Label l = (Label) wc;
			// l.setString("The value has been modified.");
			// }
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			// 写入Excel对象
			wwb.write();
			// 关闭可写入的Excel对象
			wwb.close();
			// 关闭只读的Excel对象
			rw.close();
		}
		return null;
	}

	/**
	 * @throws Exception
	 * @描述:TODO(辅导员判断是否可操作床位)
	 * @作者：cmj [工号：913]
	 * @日期：2013-6-20 下午03:16:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean getCzkz() throws Exception {
		// TODO 自动生成方法存根
		String sj = dao.getCwczsjfw();
		if (sj == null || "".equalsIgnoreCase(sj)) {// 没有设置时间
			return true;
		} else {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			String[] ksjssj = sj.split(",");
			if (ksjssj.length == 1) {// 只设置了开始时间
				Date kssj = sf.parse(ksjssj[0] + " 00:00:00");
				return date.after(kssj);
			} else if (ksjssj.length == 2 && ksjssj[0].equalsIgnoreCase("")) {// 只设置了结束时间
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
	 * 住宿信息管理自定义导出
	 * 
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> queryExportCw(ZsxxglForm myForm,
			HttpServletRequest request) throws Exception {
		return dao.queryExportCw(myForm, request);
	}

	/**
	 * 获得入住原因信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRzyyList(ZsxxglForm model) throws Exception {
		return dao.getRzyyList(model);
	}

	/**
	 * 保存入住原因信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String saveRzyy(ZsxxglForm model, String type) throws Exception {
		return dao.saveRzyy(model, type);
	}
	/**
	 * @描述: 获得离校未退宿学生信息列表
	 * @作者：孟威[工号：1186]
	 * @日期：2015-12-29 上午09:33:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zf
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLxxsList(ZsxxglForm zf, User user)
		throws Exception {
		return dao.getLxxsList(zf, user);
	}
	/**
	 * @描述: 未退宿学生以学院代码获得学院名称
	 * @作者：孟威[工号：1186]
	 * @日期：2015-12-29 上午09:30:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getXymc(String xydm) throws Exception{
		return dao.getXymc(xydm);
	}
	/**
	 * @描述: 未退宿学生以专业代码获得专业名称
	 * @作者： 孟威[工号：1186]
	 * @日期：2015-12-29 上午09:32:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zydm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getZymc(String zydm) throws Exception{
		return dao.getZymc(zydm);
	}
	
	/**
	 * 
	 * @描述: 学生违纪详细信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-2-25 上午10:16:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXswjxx(ZsxxglForm model, User user)
			throws Exception {
		return dao.getXswjxx(model, user);
	}
	
	/**
	 * 
	 * @描述:获取辅导员【黑龙江农垦导出寝室名单功能专用】
	 * @作者：张昌路[工号：982]
	 * @日期：2016-6-12 下午03:37:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qsh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getFdy(String qsh,String searchTj,String[] inputV){
		return dao.getFdy(qsh,searchTj,inputV);
	}

	/**
	 * @throws Exception  
	 * @描述:批量保存备注的修改，pkValues为多个楼栋号，寝室号，床位号拼接的主键字符串
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月29日 下午2:25:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pkValues
	 * @param bz
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveBzBatchForUpdate(String pkValues, String bz) throws Exception {
		String[] pkValueArr = pkValues.split(",");
		return dao.saveBzBatchForUpdate(pkValueArr,bz);
	}
	
	/** 
	 * @描述:寝室换人(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-23 下午02:28:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param qsh
	 * @param cwh
	 * @param oldXh
	 * @param newXh
	 * @return
	 * boolean 返回类型 
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
	 * @描述: 甘肃交通职业技术学院自定义导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-1 上午11:47:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> queryExportCwForGsjt(ZsxxglForm myForm,
			HttpServletRequest request) throws Exception {
		return dao.queryExportCwForGsjt(myForm, request);
	}
	
}
