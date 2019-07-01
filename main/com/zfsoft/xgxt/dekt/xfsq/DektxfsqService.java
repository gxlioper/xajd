package com.zfsoft.xgxt.dekt.xfsq;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.zdydr.service.ZdydrService;
import com.zfsoft.xgxt.dekt.xmwh.DektxmwhForm;
import com.zfsoft.xgxt.dekt.xmwh.DektxmwhService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.*;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Boolean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DektxfsqService extends SuperServiceImpl<DektxfsqForm,DektxfsqDao> {
	private DektxfsqDao dao= new DektxfsqDao();
	private ZdydrService zdydrService = new ZdydrService();
	private DektxmwhService dektxmwhService = new DektxmwhService();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public DektxfsqService(){
		super.setDao(dao);
	 }
	 
	public boolean checkExist(DektxfsqForm form) throws Exception {
		return dao.checkExist(form);
	}
	
	public boolean saveSq(DektxfsqForm form) throws Exception {
		return dao.saveSq(form);
	}
	
	public boolean tjSq(DektxfsqForm form)throws Exception{
		String splc=dao.getSplc(form);
		boolean result=dao.updateShzt(form.getSqid(),Constants.YW_SHZ);
		if(result){
			result = shlc.runSubmit(form.getSqid(), splc, form.getXh(), "dekt_xfsh_list.do", "dekt_xfsq_sqlb.do");
		}
		return result;
	}

	public Map<String, String> getView(DektxfsqForm model) throws Exception {
		return dao.getView(model);
	}

	/**
	 *  个性化导入模版，生成工作簿作为浏览器的响应.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-09 14:38
	 * @param outputStream
	 * @return void
	 * @throw
	 */
	public void createWwb(ServletOutputStream outputStream,String drmkdm) {

		// 打开文件
		WritableWorkbook book = null;
		try {
			//获取导入规则，写入excel导入列批注
			List<HashMap<String,String>> drgzxxList = zdydrService.getDrgzxxList(drmkdm);

			book = Workbook.createWorkbook(outputStream);
			// 生成导入表，参数0表示sheet1，impor为其名称
			WritableSheet sheet1 = book.createSheet("import", 0);

			//填充导入列及批注提示到导入表
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,Colour.WHITE);
			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
			//设置背景颜色;
			cellFormat1.setBackground(Colour.GREEN);
			for(int i=0;i<drgzxxList.size();i++){
				//考虑根据内容自适应宽度，暂未实现...

				Label label = new Label(i,0, drgzxxList.get(i).get("drlmc"),cellFormat1);
//				sheet1.setColumnView(1, drgzxxList.get(i).get("drlmc").length());

				/*
				 * 主键、唯一(sfzj/sfwy)：不能重复
				 * 必填(sfbt)：不可为空
				 * 最大长度(zdcd)：最大长度为?
				 */
				List<String> pznrList = new ArrayList<String>();
				if("1".equals(drgzxxList.get(i).get("sfwy"))){
					pznrList.add("不能重复");
				}
				if("1".equals(drgzxxList.get(i).get("sfbt"))){
					pznrList.add("不可为空");
				}
				if(!StringUtil.isNull(drgzxxList.get(i).get("zdcd"))){
					pznrList.add("最大长度为："+drgzxxList.get(i).get("zdcd"));
				}

				String pznr = "";
				for(int j=0;j<pznrList.size();j++){
					pznr = pznr+(j+1)+"."+pznrList.get(j);
					if(j!=pznrList.size()-1) pznr+="\r\n";
				}

				WritableCellFeatures cellFeatures = new WritableCellFeatures();
				cellFeatures.setComment(pznr);
				label.setCellFeatures(cellFeatures);

				sheet1.addCell(label);
			}

			//获取辅助表信息，生成并填充辅助表
			List<HashMap<String,Object>> drfzxxAndFzdmxxList = zdydrService.getDrfzxxAndFzdmxxList(drmkdm);

			//循环生成辅助表sheet
			for(int k=0;k<drfzxxAndFzdmxxList.size();k++){
				HashMap<String,Object> drfzxxAndFzdmxx = drfzxxAndFzdmxxList.get(k);
				String dm = (String)drfzxxAndFzdmxx.get("fzdmxx_dm");
				String mc = (String)drfzxxAndFzdmxx.get("fzdmxx_mc");
				WritableSheet sheet = book.createSheet((String) drfzxxAndFzdmxx.get("fzmc"),k+1);
				List<HashMap<String,String>> fzdmxxList = (List<HashMap<String,String>>)drfzxxAndFzdmxx.get("fzdmxxList");
				for(int x=0;x<fzdmxxList.size();x++){
					Label label1 = new Label(0, x, fzdmxxList.get(x).get(dm));
					Label label2 = new Label(1, x, fzdmxxList.get(x).get(mc));
					sheet.addCell(label1);
					sheet.addCell(label2);
				}
			}

			//获取认定项目信息
			DektxmwhForm xmwhForm=new DektxmwhForm();
			List<HashMap<String,String>> xmlist=dektxmwhService.getAllList(xmwhForm);

			//个性化辅助表，手动拼写认定项目信息
			WritableSheet sheet_sdfz = book.createSheet("认定项目标准",drfzxxAndFzdmxxList.size()+1);
			Label label_lx = new Label(0, 0, "类型",cellFormat1);
			Label label_rdxm = new Label(1, 0, "认定项目",cellFormat1);
			Label label_rdnrbz = new Label(2,0,"认定内容及标准",cellFormat1);
			Label label_dj = new Label(3,0,"等级",cellFormat1);
			Label label_xf = new Label(4,0,"学分",cellFormat1);
			Label label_yjsm = new Label(5,0,"认定依据及部门",cellFormat1);

			sheet_sdfz.addCell(label_lx);
			sheet_sdfz.addCell(label_rdxm);
			sheet_sdfz.addCell(label_rdnrbz);
			sheet_sdfz.addCell(label_dj);
			sheet_sdfz.addCell(label_xf);
			sheet_sdfz.addCell(label_yjsm);

			for(int x=0;x<xmlist.size();x++){
				Label label1 = new Label(0, x+1, xmlist.get(x).get("lx"));
				Label label2 = new Label(1, x+1, xmlist.get(x).get("rdxm"));
				Label label3 = new Label(2, x+1, xmlist.get(x).get("rdnrbz"));
				Label label4 = new Label(3, x+1, xmlist.get(x).get("dj"));
				Label label5 = new Label(4, x+1, xmlist.get(x).get("xf"));
				Label label6 = new Label(5, x+1, xmlist.get(x).get("yjsm"));

				sheet_sdfz.addCell(label1);
				sheet_sdfz.addCell(label2);
				sheet_sdfz.addCell(label3);
				sheet_sdfz.addCell(label4);
				sheet_sdfz.addCell(label5);
				sheet_sdfz.addCell(label6);
			}

			sheet_sdfz.setColumnView(0, 20);
			sheet_sdfz.setColumnView(1, 20);
			sheet_sdfz.setColumnView(2, 40);
			sheet_sdfz.setColumnView(3, 20);
			sheet_sdfz.setColumnView(4, 10);
			sheet_sdfz.setColumnView(5, 100);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 写入数据并关闭文件
			try {
				book.write();
				book.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *  保存导入信息.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-10 20:24
	 * @param inputStream
	 * @param path
	 * @param drmkdm
	 * @return java.util.HashMap<java.lang.String,java.lang.Object>
	 * @throw
	 */
	public HashMap<String,Object> saveImport(InputStream inputStream, String path, String drmkdm) throws SQLException{

		HashMap<String, Object> resultMap = null;
		//获取导入列规则
		List<HashMap<String,String>> drgzxxList = zdydrService.getDrgzxxList(drmkdm);

		Workbook wb = null;
		Sheet sheet = null;
		try {
			wb = Workbook.getWorkbook(inputStream);
			sheet = wb.getSheet(0);
			//验证模版头部列  error:01
			resultMap = zdydrService.checkImportHeader(sheet, drgzxxList);
			if((Boolean) resultMap.get("result")){
				//模版无误后进行下面的操作
				//读取所有行存入List<HashMap<String,String>>  每行对应一个HashMap
				List<HashMap<String,String>> excelDataList = zdydrService.getExcelDataList(sheet,drgzxxList);

				if(excelDataList.isEmpty()){
					resultMap.put("totalCount", excelDataList.size());
					return resultMap;
				}

				//个性化联合验证组装
				List<HashMap<String,Object>> lhList = new ArrayList<HashMap<String, Object>>();
				HashMap<String,Object> xhxmlhMap = new HashMap<String, Object>();
				xhxmlhMap.put("tableName","xsxxb");
				xhxmlhMap.put("drlArr",new String []{"xh","xm"});
				xhxmlhMap.put("drlmcArr",new String []{"学号","姓名"});

				HashMap<String,Object> rdnrlhMap = new HashMap<String, Object>();
				rdnrlhMap.put("tableName","xg_dekt_xmdmb");
				rdnrlhMap.put("drlArr",new String []{"lx","rdxm","rdnrbz","dj"});
				rdnrlhMap.put("drlmcArr",new String []{"类型","认定项目","认定内容及标准","等级"});

				lhList.add(xhxmlhMap);
				lhList.add(rdnrlhMap);

				//导入操作表，用于查询验证重复性
				String tableName = "(SELECT a.*,b.lx,b.RDXM,b.RDNRBZ,b.DJ FROM XG_DEKT_DEKTXFSQB a " +
						"LEFT JOIN XG_DEKT_XMDMB b ON a.XMID = b.XMID)";

				//保存前的逐行逐列验证 error:02
				resultMap = zdydrService.checkExcelDataList(excelDataList,drgzxxList,tableName,lhList);
				if((Boolean) resultMap.get("result")){
					//验证通过，还需要判断excel数据中本身是否有重复
					resultMap = zdydrService.checkExcelDataRepeat(excelDataList,drgzxxList);
					if((Boolean) resultMap.get("result")){
						//不存在重复则进行最后的插入数据到数据库的操作
						boolean insertResult = this.insertDataIntoDB(excelDataList);
						if(insertResult){
							resultMap.put("totalCount", excelDataList.size());
						}
					}else{
						//存在重复，根据错误数据提示生成excel文件到服务器error:03
						String errorTipsExcelName = zdydrService.createErrorTipsExcel(excelDataList,path,drmkdm,drgzxxList);
						resultMap.put("errorTipsExcelName", errorTipsExcelName);
					}
				}else{
					//验证不通过，根据错误数据提示生成excel文件到服务器
					String errorTipsExcelName = zdydrService.createErrorTipsExcel(excelDataList,path,drmkdm,drgzxxList);
					resultMap.put("errorTipsExcelName", errorTipsExcelName);
				}
			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultMap;
	}

	/**
	 *  将验证通过后的数据插入数据库.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-11 16:56
	 * @param excelDataList
	 * @return boolean
	 * @throw
	 */
	private boolean insertDataIntoDB(List<HashMap<String, String>> excelDataList) throws SQLException {

		List<String []> paraList = new ArrayList<String[]>();
		for(HashMap<String,String> excelData:excelDataList){
			String xh = excelData.get("xh");
			String cyfs = excelData.get("cyfs");
			String hjsj = excelData.get("hjsj");
			String sqsm = excelData.get("sqsm");
			String lx = excelData.get("lx");
			String rdxm = excelData.get("rdxm");
			String rdnrbz = excelData.get("rdnrbz");
			String dj = excelData.get("dj");
			paraList.add(new String [] {xh,cyfs,hjsj,sqsm,lx,rdxm,rdnrbz,dj});
		}
		return dao.batchInsertDataIntoDB(paraList);
	}
}


