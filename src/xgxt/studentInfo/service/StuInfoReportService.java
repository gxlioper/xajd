package xgxt.studentInfo.service;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.studentInfo.dao.StuInfoReportDAO;
import xgxt.studentInfo.model.StuInfoReportModel;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class StuInfoReportService {

	StuInfoReportDAO dao = new StuInfoReportDAO();
	
	/**
	 * 设置学生信息里的民族名称是否有空数据<br>
	 * 如果有设置为true,否则设置为false;
	 * @param model
	 */
	private void setMzmcIsNull(StuInfoReportModel model) {
		int count = Integer.valueOf(dao.getCountByMzmcIsNull());
		
		if (count > 0) {
			model.setMzmcNull(true);
		} else {
			model.setMzmcNull(false);
		}
	}
	
	
	
	/**
	 * 设置学生信息里的户口性质是否有空数据<br>
	 * 如果有设置为true,否则设置为false;
	 * @param model
	 */
	private void setHkxzIsNull(StuInfoReportModel model) {
		int count = Integer.valueOf(dao.getCountByHkxzIsNull());
		
		if (count > 0) {
			model.setHkxzNull(true);
		} else {
			model.setHkxzNull(false);
		}
	}
	
	
	/**
	 * 设置学生信息里的性别是否有空数据<br>
	 * 如果有设置为true,否则设置为false;
	 * @param model
	 */
	private void setXbIsNull(StuInfoReportModel model) {
		int count = Integer.valueOf(dao.getCountByXbIsNull());
		
		if (count > 0) {
			model.setXbNull(true);
		} else {
			model.setXbNull(false);
		}
	}
	
	
	public void printStuInfoRepordOfZy(StudentInfoForm myForm,OutputStream os) {

		String[] xszd = myForm.getXszd();//{"xjzt","mzmc","hkxz","xb","zzmm","kns"}
		//String[] xszd = {"xjzt","mzmc","xb","zzmm","kns"};
		//总计数据
		int zjZrs = 0;//总人数
		int zjZxrs = 0;//在校人数
		int zjFzxrs = 0;//非在校人数
		int zjHzrs = 0;//汉族
		int zjSsmzrs = 0;//少数民族
		int zjWqdmzrs = 0;//未确定民族
		int zjCzrs = 0;//城镇户口
		int zjNcrs = 0;//农村户口
		int zjWqdhk = 0;//未确定户口
		int zjNsrs = 0;//男生人数
		int zjVsrs = 0;//女生人数
		int zjWqdxbrs = 0;//未确定性别人数
		int zjDyrs = 0;//党员人数
		int zjTyrs = 0;//团员人数
		int zjQtzzmm = 0;//其它政治面貌人数
		int zjKnsrs = 0;//困难生人数
		
		String title = Base.xxmc + "高校学生情况统计表";
		//存储各类别字段是否有空值的model
		StuInfoReportModel model = getStuInfoReportModel();
		List<HashMap<String,String>> data = dao.getStuInfoReportByNjAndZy();
		
		int cols = 4;
		int temp = 0;
		
		cols = getCols(xszd, model, cols);
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);

		try {
			excel.printTitle(ws, title, cols, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式

			//合并单元格
			ws.mergeCells(0, 1, 0, 2);
			ws.mergeCells(1, 1, 1, 2);
			ws.mergeCells(2, 1, 2, 2);
			ws.mergeCells(3, 1, 3, 2);
			
			ws.addCell(new Label(0, 1,Base.YXPZXY_KEY+"名称", wcfTytle));
			ws.addCell(new Label(1, 1,"年级", wcfTytle));
			ws.addCell(new Label(2, 1,"专业", wcfTytle));
			ws.addCell(new Label(3, 1,"合计", wcfTytle));
			
			int currCol = 4;
			
			//学籍
			if (StringUtils.stringExistArray("xjzt", xszd)) {
				ws.mergeCells(4, 1, 5, 1);//学籍状态合并
				ws.addCell(new Label(currCol, 1,"学籍状态", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"在校生", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"非在校生", wcfTytle));
			}
			//民族
			if (StringUtils.stringExistArray("mzmc", xszd)) {
				if (model.isMzmcNull()) {
					ws.mergeCells(currCol, 1, currCol+2, 1);
					ws.addCell(new Label(currCol+2, 2,"未确定", wcfTytle));
					temp = 1;
				} else {
					ws.mergeCells(currCol, 1, currCol+1, 1);
					temp = 0;
				}
				ws.addCell(new Label(currCol, 1,"民族", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"汉族", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"少数民族", wcfTytle));
				currCol += temp;
			}
			
			//户口性质
			if (StringUtils.stringExistArray("hkxz", xszd)) {
				if (model.isHkxzNull()) {
					ws.mergeCells(currCol, 1, currCol+2, 1);
					ws.addCell(new Label(currCol+2,2,"未确定", wcfTytle));
					temp = 1;
				} else {
					temp = 0;
					ws.mergeCells(currCol, 1, currCol+1, 1);
				}
				ws.addCell(new Label(currCol, 1,"户籍", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"城市", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"农村", wcfTytle));
				currCol += temp;
			}
			
			//性别
			if (StringUtils.stringExistArray("xb", xszd)) {
				if (model.isXbNull()) {
					ws.mergeCells(currCol, 1, currCol+2, 1);
					ws.addCell(new Label(currCol+2, 2,"未确定", wcfTytle));
					temp = 1;
				} else {
					temp = 0;
					ws.mergeCells(currCol, 1, currCol+1, 1);
				}
				ws.addCell(new Label(currCol, 1,"性别", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"男", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"女", wcfTytle));
				currCol += temp;
			}
			
			//政治面貌
			if (StringUtils.stringExistArray("zzmm", xszd)) {
				ws.mergeCells(currCol, 1, currCol+2, 1);
				ws.addCell(new Label(currCol, 1,"政治面貌", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"党", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"团", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"其它", wcfTytle));
			}
			
			//困难生
			if (StringUtils.stringExistArray("kns", xszd)) {
				ws.mergeCells(currCol, 1, currCol, 2);
				ws.addCell(new Label(currCol, 1,"困难生人数", wcfTytle));
			}
			
			int currRow = 3;//当前行
			
			//小计用的数据
			int countZrs = 0;
			int countZxrs = 0;
			int countFzxrs = 0;
			int countHzrs = 0;
			int countSsmzrs = 0;
			int countWqdmzrs = 0;
			int countCzrs = 0;
			int countNcrs = 0;
			int countWqdhk = 0;
			int countNsrs = 0;
			int countVsrs = 0;
			int countWqdxbrs = 0;
			int countDyrs = 0;
			int countTyrs = 0;
			int countQtzzmm = 0;
			int countKnsrs = 0;
			
			for (int i = 0 ; i < data.size() ; i++) {
				int curCol = 0;
				
				String xymc = data.get(i).get("xymc");
				String nj = data.get(i).get("nj");
				String zrs = data.get(i).get("zrs");
				String zxrs = data.get(i).get("zxrs");
				String fzxrs = data.get(i).get("fzxrs");
				String hzrs = data.get(i).get("hzrs");
				String ssmzrs = data.get(i).get("ssmzrs");
				String wqdmzrs = data.get(i).get("wqdmzrs");
				String czrs = data.get(i).get("czrs");
				String ncrs = data.get(i).get("ncrs");
				String wqdhk = data.get(i).get("wqdhk");
				String nsrs = data.get(i).get("nsrs");
				String vsrs = data.get(i).get("vsrs");
				String wqdxbrs = data.get(i).get("wqdxbrs");
				String dyrs = data.get(i).get("dyrs");
				String tyrs = data.get(i).get("tyrs");
				String qtzzmm = data.get(i).get("qtzzmm");
				String knsrs = data.get(i).get("knsrs");
				
				//小计部分数据累加
				countZrs+=Integer.valueOf(zrs);
				countZxrs+=Integer.valueOf(zxrs);
				countFzxrs+=Integer.valueOf(fzxrs);
				countHzrs+=Integer.valueOf(hzrs);
				countSsmzrs+=Integer.valueOf(ssmzrs);
				countWqdmzrs+=Integer.valueOf(wqdmzrs);
				countCzrs+=Integer.valueOf(czrs);
				countNcrs+=Integer.valueOf(ncrs);
				countWqdhk+=Integer.valueOf(wqdhk);
				countNsrs+=Integer.valueOf(nsrs);
				countVsrs+=Integer.valueOf(vsrs);
				countWqdxbrs+=Integer.valueOf(wqdxbrs);
				countDyrs+=Integer.valueOf(dyrs);
				countTyrs+=Integer.valueOf(tyrs);
				countQtzzmm+=Integer.valueOf(qtzzmm);
				countKnsrs+=Integer.valueOf(knsrs);
				
				ws.addCell(new Label(curCol++, currRow,xymc, wcfTytle));//学院名称
				ws.addCell(new Label(curCol++, currRow,nj, wcfTytle));//年级
				ws.addCell(new Label(curCol++, currRow,data.get(i).get("zymc"), wcfTytle));//专业名称
				ws.addCell(new Label(curCol++, currRow,zrs, wcfTytle));//人数
				/*学籍状态*/
				if (StringUtils.stringExistArray("xjzt", xszd)) {
					ws.addCell(new Label(curCol++, currRow,zxrs, wcfTytle));//在校人数
					ws.addCell(new Label(curCol++, currRow,fzxrs, wcfTytle));//非在校人数
				}
				/*民族*/
				if (StringUtils.stringExistArray("mzmc", xszd)) {
					ws.addCell(new Label(curCol++, currRow,hzrs, wcfTytle));//汉族人数
					ws.addCell(new Label(curCol++, currRow,ssmzrs, wcfTytle));//少数民族人数
					//如果民族有空数据输出未确定民族
					if (model.isMzmcNull()) {
						ws.addCell(new Label(curCol++, currRow,wqdmzrs, wcfTytle));
					}
				}
				
				/*户口性质*/
				if (StringUtils.stringExistArray("hkxz", xszd)) {
					ws.addCell(new Label(curCol++, currRow,czrs, wcfTytle));//城镇户口
					ws.addCell(new Label(curCol++, currRow,ncrs, wcfTytle));//农村户口
					//如果户口性质为空输出未确定户口性质
					if (model.isHkxzNull()) {
						ws.addCell(new Label(curCol++, currRow,wqdhk, wcfTytle));
					}
				}
				
				/*性别*/
				if (StringUtils.stringExistArray("xb", xszd)) {
					ws.addCell(new Label(curCol++, currRow,nsrs, wcfTytle));//男生人数
					ws.addCell(new Label(curCol++, currRow,vsrs, wcfTytle));//女生人数
					//如果性别有空数据输出未确定性别
					if (model.isXbNull()) {
						ws.addCell(new Label(curCol++, currRow,wqdxbrs, wcfTytle));
					}
				}
				
				/*政治面貌*/
				if (StringUtils.stringExistArray("zzmm", xszd)) {
					ws.addCell(new Label(curCol++, currRow,dyrs, wcfTytle));//党员
					ws.addCell(new Label(curCol++, currRow,tyrs, wcfTytle));//团员
					ws.addCell(new Label(curCol++, currRow,qtzzmm, wcfTytle));//其它政治面貌
				}
				
				/*困难生*/
				if (StringUtils.stringExistArray("kns", xszd)) {
					ws.addCell(new Label(curCol++, currRow,knsrs, wcfTytle));//困难生
				}
				
				/*年级插入小计部分*/
				if ((i != data.size()-1 && ! nj.equalsIgnoreCase(data.get(i+1).get("nj"))) || i == data.size()-1) {
					currRow ++;
					
					curCol = 0;
					ws.addCell(new Label(curCol++, currRow,xymc, wcfTytle));
					ws.addCell(new Label(curCol++, currRow,nj, wcfTytle));
					ws.addCell(new Label(curCol++, currRow,"小计", wcfTytle));
					ws.addCell(new Label(curCol++, currRow,String.valueOf(countZrs), wcfTytle));
					
					/*学籍状态*/
					if (StringUtils.stringExistArray("xjzt", xszd)) {
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countZxrs), wcfTytle));
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countFzxrs), wcfTytle));
					}
					/*民族*/
					if (StringUtils.stringExistArray("mzmc", xszd)) {
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countHzrs), wcfTytle));
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countSsmzrs), wcfTytle));
						if (model.isMzmcNull()) {
							ws.addCell(new Label(curCol++, currRow,String.valueOf(countWqdmzrs), wcfTytle));
						}
					}
					
					/*户口性质*/
					if (StringUtils.stringExistArray("hkxz", xszd)) {
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countCzrs), wcfTytle));
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countNcrs), wcfTytle));
						if (model.isHkxzNull()) {
							ws.addCell(new Label(curCol++, currRow,String.valueOf(countWqdhk), wcfTytle));
						}
					}
					
					/*性别*/
					if (StringUtils.stringExistArray("xb", xszd)) {
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countNsrs), wcfTytle));
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countVsrs), wcfTytle));
						if (model.isXbNull()) {
							ws.addCell(new Label(curCol++, currRow,String.valueOf(countWqdxbrs), wcfTytle));
						}
					}
					
					/*政治面貌*/
					if (StringUtils.stringExistArray("zzmm", xszd)) {
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countDyrs), wcfTytle));
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countTyrs), wcfTytle));
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countQtzzmm), wcfTytle));
					}
					
					/*困难生*/
					if (StringUtils.stringExistArray("kns", xszd)) {
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countKnsrs), wcfTytle));
					}
					/*总计部分数据累加*/
					zjZrs += countZrs;
					zjZxrs += countZxrs;
					zjFzxrs += countFzxrs;
					zjHzrs += countHzrs;
					zjSsmzrs += countSsmzrs;
					zjWqdmzrs += countWqdmzrs;
					zjCzrs += countCzrs;
					zjNcrs += countNcrs;
					zjWqdhk += countWqdhk;
					zjNsrs += countNsrs;
					zjVsrs += countVsrs;
					zjWqdxbrs += countWqdxbrs;
					zjDyrs += countDyrs;
					zjTyrs += countTyrs;
					zjQtzzmm += countQtzzmm;
					zjKnsrs += countKnsrs;
					
					/*小计部分数据清0*/
					countZrs = 0;
					countZxrs = 0;
					countFzxrs = 0;
					countHzrs = 0;
					countSsmzrs = 0;
					countWqdmzrs = 0;
					countCzrs = 0;
					countNcrs = 0;
					countWqdhk = 0;
					countNsrs = 0;
					countVsrs = 0;
					countWqdxbrs = 0;
					countDyrs = 0;
					countTyrs = 0;
					countQtzzmm = 0;
					countKnsrs = 0;
				}
				
				currRow ++;
			}
			
			/*总计部分输出*/
			ws.mergeCells(0, currRow, 2, currRow);
			ws.addCell(new Label(0, currRow,"人数情况总和：", wcfTytle));
			int curCol = 3;
			ws.addCell(new Label(curCol++, currRow,String.valueOf(zjZrs), wcfTytle));
			
			/*学籍状态*/
			if (StringUtils.stringExistArray("xjzt", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjZxrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjFzxrs), wcfTytle));
			}
			
			/*民族*/
			if (StringUtils.stringExistArray("mzmc", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjHzrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjSsmzrs), wcfTytle));
				if (model.isMzmcNull()) {
					ws.addCell(new Label(curCol++, currRow,String.valueOf(zjWqdmzrs), wcfTytle));
				}
			}
			
			/*户口性质*/
			if (StringUtils.stringExistArray("hkxz", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjCzrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjNcrs), wcfTytle));
				if (model.isHkxzNull()) {
					ws.addCell(new Label(curCol++, currRow,String.valueOf(zjWqdhk), wcfTytle));
				}
			}
			
			/*性别*/
			if (StringUtils.stringExistArray("xb", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjNsrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjVsrs), wcfTytle));
				if (model.isXbNull()) {
					ws.addCell(new Label(curCol++, currRow,String.valueOf(zjWqdxbrs), wcfTytle));
				}
			}
			
			/*政治面貌*/
			if (StringUtils.stringExistArray("zzmm", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjDyrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjTyrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjQtzzmm), wcfTytle));
			}
			
			/*困难生人数*/
			if (StringUtils.stringExistArray("kns", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjKnsrs), wcfTytle));
			}
			
			ExcelMB.mergeCells(ws, currRow, 0, 3);
			ExcelMB.mergeCells(ws, currRow, 1, 3);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}



	/**
	 * @param xszd
	 * @param model
	 * @param cols
	 * @return
	 */
	private int getCols(String[] xszd, StuInfoReportModel model, int cols) {
		
		//cols ++;
		
		if (StringUtils.stringExistArray("xjzt", xszd)) {
			cols +=2;
		}
		
		if (StringUtils.stringExistArray("mzmc", xszd)) {
			cols +=2;
			
			if (model.isMzmcNull()) {
				cols +=1;
			}
		}
		
		if (StringUtils.stringExistArray("hkxz", xszd)) {
			cols +=2;
			
			if (model.isHkxzNull()) {
				cols +=1;
			}
		}
		
		if (StringUtils.stringExistArray("xb", xszd)) {
			cols +=2;
			
			if (model.isXbNull()) {
				cols +=1;
			}
		}
		
		if (StringUtils.stringExistArray("zzmm", xszd)) {
			cols +=3;
		}
		
		if (StringUtils.stringExistArray("kns", xszd)) {
			cols +=1;
		}
		
		return cols;
	}

	
	public void printStuInfoRepordOfBj(StudentInfoForm myForm,OutputStream os) {

		String[] xszd = myForm.getXszd();
		//{"xjzt","mzmc","hkxz","xb","zzmm","kns"}
		//String[] xszd = {"xjzt","mzmc","xb","zzmm","kns"};
		
		String xymc = dao.getOneRs("select xymc from view_njxyzybj where xydm = ?", new String[] {myForm.getXydm()}, "xymc");
		String zymc = dao.getOneRs("select zymc from view_njxyzybj where zydm = ?", new String[] {myForm.getZydm()}, "zymc");
		//总计数据
		int zjZrs = 0;//总人数
		int zjZxrs = 0;//在校人数
		int zjFzxrs = 0;//非在校人数
		int zjHzrs = 0;//汉族
		int zjSsmzrs = 0;//少数民族
		int zjWqdmzrs = 0;//未确定民族
		int zjCzrs = 0;//城镇户口
		int zjNcrs = 0;//农村户口
		int zjWqdhk = 0;//未确定户口
		int zjNsrs = 0;//男生人数
		int zjVsrs = 0;//女生人数
		int zjWqdxbrs = 0;//未确定性别人数
		int zjDyrs = 0;//党员人数
		int zjTyrs = 0;//团员人数
		int zjQtzzmm = 0;//其它政治面貌人数
		int zjKnsrs = 0;//困难生人数
		
		String title = Base.xxmc + "高校学生情况统计表";
		//存储各类别字段是否有空值的model
		StuInfoReportModel model = getStuInfoReportModel();
		List<HashMap<String,String>> data = dao.getStuInfoReportByBj(myForm);
		
		int cols = 3;
		int temp = 0;
		
		cols = getCols(xszd, model, cols);
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);

		try {
//			if (cols < 6) {
//				cols = 6;
//			}
			excel.printTitle(ws, title, cols, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式

			/*二级标题*/
			ws.mergeCells(0, 1, 1, 1);
			
			if (cols > 3) {
				ws.mergeCells(3, 1, cols-1, 1);
				ws.addCell(new Label(3,1,"", wcfTytle));
			}
			
			ws.addCell(new Label(0, 1,Base.YXPZXY_KEY+"："+xymc, wcfTytle));
			ws.addCell(new Label(2, 1,"专业："+zymc, wcfTytle));
//			ws.mergeCells(6, 1, cols-1, 1);
			
			//合并单元格
			ws.mergeCells(0, 2, 0, 3);
			ws.mergeCells(1, 2, 1, 3);
			ws.mergeCells(2, 2, 2, 3);
			
			ws.addCell(new Label(0, 2,"序号", wcfTytle));
			ws.addCell(new Label(1, 2,"班级", wcfTytle));
			ws.addCell(new Label(2, 2,"合计", wcfTytle));
			
			int currCol = 3;
			
			//学籍
			if (StringUtils.stringExistArray("xjzt", xszd)) {
				ws.mergeCells(3, 2, 4, 2);//学籍状态合并
				ws.addCell(new Label(currCol, 2,"学籍状态", wcfTytle));
				ws.addCell(new Label(currCol++, 3,"在校生", wcfTytle));
				ws.addCell(new Label(currCol++, 3,"非在校生", wcfTytle));
			}
			//民族
			if (StringUtils.stringExistArray("mzmc", xszd)) {
				if (model.isMzmcNull()) {
					ws.mergeCells(currCol, 2, currCol+2, 2);
					ws.addCell(new Label(currCol+2, 3,"未确定", wcfTytle));
					temp = 1;
				} else {
					ws.mergeCells(currCol, 2, currCol+1, 2);
					temp = 0;
				}
				ws.addCell(new Label(currCol, 2,"民族", wcfTytle));
				ws.addCell(new Label(currCol++, 3,"汉族", wcfTytle));
				ws.addCell(new Label(currCol++, 3,"少数民族", wcfTytle));
				currCol += temp;
			}
			
			//户口性质
			if (StringUtils.stringExistArray("hkxz", xszd)) {
				if (model.isHkxzNull()) {
					ws.mergeCells(currCol, 2, currCol+2, 2);
					ws.addCell(new Label(currCol+2,3,"未确定", wcfTytle));
					temp = 1;
				} else {
					temp = 0;
					ws.mergeCells(currCol, 2, currCol+1, 2);
				}
				ws.addCell(new Label(currCol, 2,"户籍", wcfTytle));
				ws.addCell(new Label(currCol++, 3,"城市", wcfTytle));
				ws.addCell(new Label(currCol++, 3,"农村", wcfTytle));
				currCol += temp;
			}
			
			//性别
			if (StringUtils.stringExistArray("xb", xszd)) {
				if (model.isXbNull()) {
					ws.mergeCells(currCol, 2, currCol+2, 2);
					ws.addCell(new Label(currCol+2, 3,"未确定", wcfTytle));
					temp = 1;
				} else {
					temp = 0;
					ws.mergeCells(currCol, 2, currCol+1, 2);
				}
				ws.addCell(new Label(currCol, 2,"性别", wcfTytle));
				ws.addCell(new Label(currCol++, 3,"男", wcfTytle));
				ws.addCell(new Label(currCol++, 3,"女", wcfTytle));
				currCol += temp;
			}
			
			//政治面貌
			if (StringUtils.stringExistArray("zzmm", xszd)) {
				ws.mergeCells(currCol, 2, currCol+2, 2);
				ws.addCell(new Label(currCol, 2,"政治面貌", wcfTytle));
				ws.addCell(new Label(currCol++, 3,"党", wcfTytle));
				ws.addCell(new Label(currCol++, 3,"团", wcfTytle));
				ws.addCell(new Label(currCol++, 3,"其它", wcfTytle));
			}
			
			//困难生
			if (StringUtils.stringExistArray("kns", xszd)) {
				ws.mergeCells(currCol, 2, currCol, 3);
				ws.addCell(new Label(currCol, 2,"困难生人数", wcfTytle));
			}
			
			int currRow = 4;//当前行
			
			
			for (int i = 0 ; i < data.size() ; i++) {
				int curCol = 0;
				
				String bjmc = data.get(i).get("bjmc");
				String zrs = data.get(i).get("zrs");
				String zxrs = data.get(i).get("zxrs");
				String fzxrs = data.get(i).get("fzxrs");
				String hzrs = data.get(i).get("hzrs");
				String ssmzrs = data.get(i).get("ssmzrs");
				String wqdmzrs = data.get(i).get("wqdmzrs");
				String czrs = data.get(i).get("czrs");
				String ncrs = data.get(i).get("ncrs");
				String wqdhk = data.get(i).get("wqdhk");
				String nsrs = data.get(i).get("nsrs");
				String vsrs = data.get(i).get("vsrs");
				String wqdxbrs = data.get(i).get("wqdxbrs");
				String dyrs = data.get(i).get("dyrs");
				String tyrs = data.get(i).get("tyrs");
				String qtzzmm = data.get(i).get("qtzzmm");
				String knsrs = data.get(i).get("knsrs");
				
				//小计部分数据累加
				zjZrs+=Integer.valueOf(zrs);
				zjZxrs+=Integer.valueOf(zxrs);
				zjFzxrs+=Integer.valueOf(fzxrs);
				zjHzrs+=Integer.valueOf(hzrs);
				zjSsmzrs+=Integer.valueOf(ssmzrs);
				zjWqdmzrs+=Integer.valueOf(wqdmzrs);
				zjCzrs+=Integer.valueOf(czrs);
				zjNcrs+=Integer.valueOf(ncrs);
				zjWqdhk+=Integer.valueOf(wqdhk);
				zjNsrs+=Integer.valueOf(nsrs);
				zjVsrs+=Integer.valueOf(vsrs);
				zjWqdxbrs+=Integer.valueOf(wqdxbrs);
				zjDyrs+=Integer.valueOf(dyrs);
				zjTyrs+=Integer.valueOf(tyrs);
				zjQtzzmm+=Integer.valueOf(qtzzmm);
				zjKnsrs+=Integer.valueOf(knsrs);
				
				ws.addCell(new Label(curCol++, currRow,String.valueOf(i+1), wcfTytle));//序号
				ws.addCell(new Label(curCol++, currRow,bjmc, wcfTytle));//班级名称
				ws.addCell(new Label(curCol++, currRow,zrs, wcfTytle));//人数
				/*学籍状态*/
				if (StringUtils.stringExistArray("xjzt", xszd)) {
					ws.addCell(new Label(curCol++, currRow,zxrs, wcfTytle));//在校人数
					ws.addCell(new Label(curCol++, currRow,fzxrs, wcfTytle));//非在校人数
				}
				/*民族*/
				if (StringUtils.stringExistArray("mzmc", xszd)) {
					ws.addCell(new Label(curCol++, currRow,hzrs, wcfTytle));//汉族人数
					ws.addCell(new Label(curCol++, currRow,ssmzrs, wcfTytle));//少数民族人数
					//如果民族有空数据输出未确定民族
					if (model.isMzmcNull()) {
						ws.addCell(new Label(curCol++, currRow,wqdmzrs, wcfTytle));
					}
				}
				
				/*户口性质*/
				if (StringUtils.stringExistArray("hkxz", xszd)) {
					ws.addCell(new Label(curCol++, currRow,czrs, wcfTytle));//城镇户口
					ws.addCell(new Label(curCol++, currRow,ncrs, wcfTytle));//农村户口
					//如果户口性质为空输出未确定户口性质
					if (model.isHkxzNull()) {
						ws.addCell(new Label(curCol++, currRow,wqdhk, wcfTytle));
					}
				}
				
				/*性别*/
				if (StringUtils.stringExistArray("xb", xszd)) {
					ws.addCell(new Label(curCol++, currRow,nsrs, wcfTytle));//男生人数
					ws.addCell(new Label(curCol++, currRow,vsrs, wcfTytle));//女生人数
					//如果性别有空数据输出未确定性别
					if (model.isXbNull()) {
						ws.addCell(new Label(curCol++, currRow,wqdxbrs, wcfTytle));
					}
				}
				
				/*政治面貌*/
				if (StringUtils.stringExistArray("zzmm", xszd)) {
					ws.addCell(new Label(curCol++, currRow,dyrs, wcfTytle));//党员
					ws.addCell(new Label(curCol++, currRow,tyrs, wcfTytle));//团员
					ws.addCell(new Label(curCol++, currRow,qtzzmm, wcfTytle));//其它政治面貌
				}
				
				/*困难生*/
				if (StringUtils.stringExistArray("kns", xszd)) {
					ws.addCell(new Label(curCol++, currRow,knsrs, wcfTytle));//困难生
				}
				
				currRow ++;
			}
			
			/*总计部分输出*/
			ws.mergeCells(0, currRow, 1, currRow);
			ws.addCell(new Label(0, currRow,"人数情况总和：", wcfTytle));
			int curCol = 2;
			ws.addCell(new Label(curCol++, currRow,String.valueOf(zjZrs), wcfTytle));
			
			/*学籍状态*/
			if (StringUtils.stringExistArray("xjzt", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjZxrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjFzxrs), wcfTytle));
			}
			
			/*民族*/
			if (StringUtils.stringExistArray("mzmc", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjHzrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjSsmzrs), wcfTytle));
				if (model.isMzmcNull()) {
					ws.addCell(new Label(curCol++, currRow,String.valueOf(zjWqdmzrs), wcfTytle));
				}
			}
			
			/*户口性质*/
			if (StringUtils.stringExistArray("hkxz", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjCzrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjNcrs), wcfTytle));
				if (model.isHkxzNull()) {
					ws.addCell(new Label(curCol++, currRow,String.valueOf(zjWqdhk), wcfTytle));
				}
			}
			
			/*性别*/
			if (StringUtils.stringExistArray("xb", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjNsrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjVsrs), wcfTytle));
				if (model.isXbNull()) {
					ws.addCell(new Label(curCol++, currRow,String.valueOf(zjWqdxbrs), wcfTytle));
				}
			}
			
			/*政治面貌*/
			if (StringUtils.stringExistArray("zzmm", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjDyrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjTyrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjQtzzmm), wcfTytle));
			}
			
			/*困难生人数*/
			if (StringUtils.stringExistArray("kns", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjKnsrs), wcfTytle));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}



	
	
	
	
	public void printStuInfoRepordOfXy(StudentInfoForm myForm,OutputStream os) {

		String[] xszd = myForm.getXszd();//{"xjzt","mzmc","hkxz","xb","zzmm","kns"}
		//String[] xszd = {"xjzt","mzmc","xb","zzmm","kns"};
		//总计数据
		int zjZrs = 0;//总人数
		int zjZxrs = 0;//在校人数
		int zjFzxrs = 0;//非在校人数
		int zjHzrs = 0;//汉族
		int zjSsmzrs = 0;//少数民族
		int zjWqdmzrs = 0;//未确定民族
		int zjCzrs = 0;//城镇户口
		int zjNcrs = 0;//农村户口
		int zjWqdhk = 0;//未确定户口
		int zjNsrs = 0;//男生人数
		int zjVsrs = 0;//女生人数
		int zjWqdxbrs = 0;//未确定性别人数
		int zjDyrs = 0;//党员人数
		int zjTyrs = 0;//团员人数
		int zjQtzzmm = 0;//其它政治面貌人数
		int zjKnsrs = 0;//困难生人数
		
		String title = Base.xxmc + "高校学生情况统计表";
		//存储各类别字段是否有空值的model
		StuInfoReportModel model = getStuInfoReportModel();
		List<HashMap<String,String>> data = dao.getStuInfoReportByXyAndNj();
		
		int cols = 3;
		int temp = 0;
		
		cols = getCols(xszd, model, cols);
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);

		try {
			excel.printTitle(ws, title, cols, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式

			//合并单元格
			ws.mergeCells(0, 1, 0, 2);
			ws.mergeCells(1, 1, 1, 2);
			ws.mergeCells(2, 1, 2, 2);
			
			ws.addCell(new Label(0, 1,Base.YXPZXY_KEY+"名称", wcfTytle));
			ws.addCell(new Label(1, 1,"年级", wcfTytle));
			ws.addCell(new Label(2, 1,"合计", wcfTytle));
			
			int currCol = 3;
			
			//学籍
			if (StringUtils.stringExistArray("xjzt", xszd)) {
				ws.mergeCells(3, 1, 4, 1);//学籍状态合并
				ws.addCell(new Label(currCol, 1,"学籍状态", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"在校生", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"非在校生", wcfTytle));
			}
			//民族
			if (StringUtils.stringExistArray("mzmc", xszd)) {
				if (model.isMzmcNull()) {
					ws.mergeCells(currCol, 1, currCol+2, 1);
					ws.addCell(new Label(currCol+2, 2,"未确定", wcfTytle));
					temp = 1;
				} else {
					ws.mergeCells(currCol, 1, currCol+1, 1);
					temp = 0;
				}
				ws.addCell(new Label(currCol, 1,"民族", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"汉族", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"少数民族", wcfTytle));
				currCol += temp;
			}
			
			//户口性质
			if (StringUtils.stringExistArray("hkxz", xszd)) {
				if (model.isHkxzNull()) {
					ws.mergeCells(currCol, 1, currCol+2, 1);
					ws.addCell(new Label(currCol+2,2,"未确定", wcfTytle));
					temp = 1;
				} else {
					temp = 0;
					ws.mergeCells(currCol, 1, currCol+1, 1);
				}
				ws.addCell(new Label(currCol, 1,"户籍", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"城市", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"农村", wcfTytle));
				currCol += temp;
			}
			
			//性别
			if (StringUtils.stringExistArray("xb", xszd)) {
				if (model.isXbNull()) {
					ws.mergeCells(currCol, 1, currCol+2, 1);
					ws.addCell(new Label(currCol+2, 2,"未确定", wcfTytle));
					temp = 1;
				} else {
					temp = 0;
					ws.mergeCells(currCol, 1, currCol+1, 1);
				}
				ws.addCell(new Label(currCol, 1,"性别", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"男", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"女", wcfTytle));
				currCol += temp;
			}
			
			//政治面貌
			if (StringUtils.stringExistArray("zzmm", xszd)) {
				ws.mergeCells(currCol, 1, currCol+2, 1);
				ws.addCell(new Label(currCol, 1,"政治面貌", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"党", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"团", wcfTytle));
				ws.addCell(new Label(currCol++, 2,"其它", wcfTytle));
			}
			
			//困难生
			if (StringUtils.stringExistArray("kns", xszd)) {
				ws.mergeCells(currCol, 1, currCol, 2);
				ws.addCell(new Label(currCol, 1,"困难生人数", wcfTytle));
			}
			
			int currRow = 3;//当前行
			
			//小计用的数据
			int countZrs = 0;
			int countZxrs = 0;
			int countFzxrs = 0;
			int countHzrs = 0;
			int countSsmzrs = 0;
			int countWqdmzrs = 0;
			int countCzrs = 0;
			int countNcrs = 0;
			int countWqdhk = 0;
			int countNsrs = 0;
			int countVsrs = 0;
			int countWqdxbrs = 0;
			int countDyrs = 0;
			int countTyrs = 0;
			int countQtzzmm = 0;
			int countKnsrs = 0;
			
			for (int i = 0 ; i < data.size() ; i++) {
				int curCol = 0;
				
				String xymc = data.get(i).get("xymc");
				String nj = data.get(i).get("nj");
				String zrs = data.get(i).get("zrs");
				String zxrs = data.get(i).get("zxrs");
				String fzxrs = data.get(i).get("fzxrs");
				String hzrs = data.get(i).get("hzrs");
				String ssmzrs = data.get(i).get("ssmzrs");
				String wqdmzrs = data.get(i).get("wqdmzrs");
				String czrs = data.get(i).get("czrs");
				String ncrs = data.get(i).get("ncrs");
				String wqdhk = data.get(i).get("wqdhk");
				String nsrs = data.get(i).get("nsrs");
				String vsrs = data.get(i).get("vsrs");
				String wqdxbrs = data.get(i).get("wqdxbrs");
				String dyrs = data.get(i).get("dyrs");
				String tyrs = data.get(i).get("tyrs");
				String qtzzmm = data.get(i).get("qtzzmm");
				String knsrs = data.get(i).get("knsrs");
				
				//小计部分数据累加
				countZrs+=Integer.valueOf(zrs);
				countZxrs+=Integer.valueOf(zxrs);
				countFzxrs+=Integer.valueOf(fzxrs);
				countHzrs+=Integer.valueOf(hzrs);
				countSsmzrs+=Integer.valueOf(ssmzrs);
				countWqdmzrs+=Integer.valueOf(wqdmzrs);
				countCzrs+=Integer.valueOf(czrs);
				countNcrs+=Integer.valueOf(ncrs);
				countWqdhk+=Integer.valueOf(wqdhk);
				countNsrs+=Integer.valueOf(nsrs);
				countVsrs+=Integer.valueOf(vsrs);
				countWqdxbrs+=Integer.valueOf(wqdxbrs);
				countDyrs+=Integer.valueOf(dyrs);
				countTyrs+=Integer.valueOf(tyrs);
				countQtzzmm+=Integer.valueOf(qtzzmm);
				countKnsrs+=Integer.valueOf(knsrs);
				
				ws.addCell(new Label(curCol++, currRow,xymc, wcfTytle));//学院名称
				ws.addCell(new Label(curCol++, currRow,nj, wcfTytle));//年级
				ws.addCell(new Label(curCol++, currRow,zrs, wcfTytle));//人数
				/*学籍状态*/
				if (StringUtils.stringExistArray("xjzt", xszd)) {
					ws.addCell(new Label(curCol++, currRow,zxrs, wcfTytle));//在校人数
					ws.addCell(new Label(curCol++, currRow,fzxrs, wcfTytle));//非在校人数
				}
				/*民族*/
				if (StringUtils.stringExistArray("mzmc", xszd)) {
					ws.addCell(new Label(curCol++, currRow,hzrs, wcfTytle));//汉族人数
					ws.addCell(new Label(curCol++, currRow,ssmzrs, wcfTytle));//少数民族人数
					//如果民族有空数据输出未确定民族
					if (model.isMzmcNull()) {
						ws.addCell(new Label(curCol++, currRow,wqdmzrs, wcfTytle));
					}
				}
				
				/*户口性质*/
				if (StringUtils.stringExistArray("hkxz", xszd)) {
					ws.addCell(new Label(curCol++, currRow,czrs, wcfTytle));//城镇户口
					ws.addCell(new Label(curCol++, currRow,ncrs, wcfTytle));//农村户口
					//如果户口性质为空输出未确定户口性质
					if (model.isHkxzNull()) {
						ws.addCell(new Label(curCol++, currRow,wqdhk, wcfTytle));
					}
				}
				
				/*性别*/
				if (StringUtils.stringExistArray("xb", xszd)) {
					ws.addCell(new Label(curCol++, currRow,nsrs, wcfTytle));//男生人数
					ws.addCell(new Label(curCol++, currRow,vsrs, wcfTytle));//女生人数
					//如果性别有空数据输出未确定性别
					if (model.isXbNull()) {
						ws.addCell(new Label(curCol++, currRow,wqdxbrs, wcfTytle));
					}
				}
				
				/*政治面貌*/
				if (StringUtils.stringExistArray("zzmm", xszd)) {
					ws.addCell(new Label(curCol++, currRow,dyrs, wcfTytle));//党员
					ws.addCell(new Label(curCol++, currRow,tyrs, wcfTytle));//团员
					ws.addCell(new Label(curCol++, currRow,qtzzmm, wcfTytle));//其它政治面貌
				}
				
				/*困难生*/
				if (StringUtils.stringExistArray("kns", xszd)) {
					ws.addCell(new Label(curCol++, currRow,knsrs, wcfTytle));//困难生
				}
				
				/*年级插入小计部分*/
				if ((i != data.size()-1 && ! xymc.equalsIgnoreCase(data.get(i+1).get("xymc"))) || i == data.size()-1) {
					currRow ++;
					
					curCol = 0;
					ws.addCell(new Label(curCol++, currRow,xymc, wcfTytle));
					ws.addCell(new Label(curCol++, currRow,"小计", wcfTytle));
					ws.addCell(new Label(curCol++, currRow,String.valueOf(countZrs), wcfTytle));
					
					/*学籍状态*/
					if (StringUtils.stringExistArray("xjzt", xszd)) {
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countZxrs), wcfTytle));
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countFzxrs), wcfTytle));
					}
					/*民族*/
					if (StringUtils.stringExistArray("mzmc", xszd)) {
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countHzrs), wcfTytle));
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countSsmzrs), wcfTytle));
						if (model.isMzmcNull()) {
							ws.addCell(new Label(curCol++, currRow,String.valueOf(countWqdmzrs), wcfTytle));
						}
					}
					
					/*户口性质*/
					if (StringUtils.stringExistArray("hkxz", xszd)) {
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countCzrs), wcfTytle));
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countNcrs), wcfTytle));
						if (model.isHkxzNull()) {
							ws.addCell(new Label(curCol++, currRow,String.valueOf(countWqdhk), wcfTytle));
						}
					}
					
					/*性别*/
					if (StringUtils.stringExistArray("xb", xszd)) {
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countNsrs), wcfTytle));
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countVsrs), wcfTytle));
						if (model.isXbNull()) {
							ws.addCell(new Label(curCol++, currRow,String.valueOf(countWqdxbrs), wcfTytle));
						}
					}
					
					/*政治面貌*/
					if (StringUtils.stringExistArray("zzmm", xszd)) {
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countDyrs), wcfTytle));
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countTyrs), wcfTytle));
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countQtzzmm), wcfTytle));
					}
					
					/*困难生*/
					if (StringUtils.stringExistArray("kns", xszd)) {
						ws.addCell(new Label(curCol++, currRow,String.valueOf(countKnsrs), wcfTytle));
					}
					/*总计部分数据累加*/
					zjZrs += countZrs;
					zjZxrs += countZxrs;
					zjFzxrs += countFzxrs;
					zjHzrs += countHzrs;
					zjSsmzrs += countSsmzrs;
					zjWqdmzrs += countWqdmzrs;
					zjCzrs += countCzrs;
					zjNcrs += countNcrs;
					zjWqdhk += countWqdhk;
					zjNsrs += countNsrs;
					zjVsrs += countVsrs;
					zjWqdxbrs += countWqdxbrs;
					zjDyrs += countDyrs;
					zjTyrs += countTyrs;
					zjQtzzmm += countQtzzmm;
					zjKnsrs += countKnsrs;
					
					/*小计部分数据清0*/
					countZrs = 0;
					countZxrs = 0;
					countFzxrs = 0;
					countHzrs = 0;
					countSsmzrs = 0;
					countWqdmzrs = 0;
					countCzrs = 0;
					countNcrs = 0;
					countWqdhk = 0;
					countNsrs = 0;
					countVsrs = 0;
					countWqdxbrs = 0;
					countDyrs = 0;
					countTyrs = 0;
					countQtzzmm = 0;
					countKnsrs = 0;
				}
				
				currRow ++;
			}
			
			/*总计部分输出*/
			ws.mergeCells(0, currRow, 1, currRow);
			ws.addCell(new Label(0, currRow,"全校人数情况总和：", wcfTytle));
			int curCol = 2;
			ws.addCell(new Label(curCol++, currRow,String.valueOf(zjZrs), wcfTytle));
			
			/*学籍状态*/
			if (StringUtils.stringExistArray("xjzt", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjZxrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjFzxrs), wcfTytle));
			}
			
			/*民族*/
			if (StringUtils.stringExistArray("mzmc", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjHzrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjSsmzrs), wcfTytle));
				if (model.isMzmcNull()) {
					ws.addCell(new Label(curCol++, currRow,String.valueOf(zjWqdmzrs), wcfTytle));
				}
			}
			
			/*户口性质*/
			if (StringUtils.stringExistArray("hkxz", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjCzrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjNcrs), wcfTytle));
				if (model.isHkxzNull()) {
					ws.addCell(new Label(curCol++, currRow,String.valueOf(zjWqdhk), wcfTytle));
				}
			}
			
			/*性别*/
			if (StringUtils.stringExistArray("xb", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjNsrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjVsrs), wcfTytle));
				if (model.isXbNull()) {
					ws.addCell(new Label(curCol++, currRow,String.valueOf(zjWqdxbrs), wcfTytle));
				}
			}
			
			/*政治面貌*/
			if (StringUtils.stringExistArray("zzmm", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjDyrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjTyrs), wcfTytle));
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjQtzzmm), wcfTytle));
			}
			
			/*困难生人数*/
			if (StringUtils.stringExistArray("kns", xszd)) {
				ws.addCell(new Label(curCol++, currRow,String.valueOf(zjKnsrs), wcfTytle));
			}
			
			ExcelMB.mergeCells(ws, currRow, 0, 3);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	

	/**
	 * 学生信息统计相关字段是否有空值
	 * 
	 * @return StuInfoReportModel
	 */
	private StuInfoReportModel getStuInfoReportModel() {
		StuInfoReportModel model = new StuInfoReportModel();
		setHkxzIsNull(model);
		setMzmcIsNull(model);
		setXbIsNull(model);
		return model;
	}
	
}
