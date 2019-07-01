package xsgzgl.xsxx.xsxxhz;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.xtwh.zpgl.XtwhZpglService;
import jxl.write.Label;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-5-18 上午10:08:08</p>
 */
public class XsxxCommService extends XsxxXxhzbService {

	
	public void printXsxx(WritableWorkbook wwb, String xh) {
		
		XsxxglService stuService = new XsxxglService();
		// 学生基本信息
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		// 评奖评优信息
		List<HashMap<String, String>> pjInfo = dao.getPjInfo(xh, "5");
		// 学习经历信息
		List<HashMap<String, String>> xxjlInfo = dao.getXxjlInfo(xh);
		// 学籍异动信息
		List<HashMap<String, String>> xjydInfo = dao.getXjydInfo(xh, "5");
		// 综测信息
		List<HashMap<String, String>> zcfInfo = dao.getZcfInfo(xh, "4");
		// 勤工助学信息
		List<HashMap<String, String>> qgzxInfo = dao.getQgzxInfo(xh, "4");
		// 违纪处分信息
		List<HashMap<String, String>> wjInfo = dao.getWjInfo(xh, "4");
		// 学生资助信息
		List<HashMap<String, String>> zzInfo = dao.getZzInfo(xh, "4");
		// 住宿信息
		List<HashMap<String, String>> zsInfo = dao.getZsInfo(xh, "4");
		// 党团信息
		HashMap<String, String> dtInfo = dao.getDtInfo(xh);
		// 家庭成员信息
		HashMap<String, String> jtcyMap = dao.getJtcyInfo(xh);
		// 家庭成员信息
		HashMap<String, String> pyccMap = dao.getPyccInfo(stuInfo.get("pycc"));

		try {
			WritableSheet ws = wwb.getSheet(0);
			XtwhZpglService service = new XtwhZpglService();

			// 表头信息
			ws.addCell(new Label(1, 1, Base.xxmc + "学生信息汇总表", ws.getCell(1, 1)
					.getCellFormat()));

			File file = service.getPhotoFile(stuInfo.get("xh"));
			// 插入照片
			if (file == null) {
			} else {
				WritableImage wi = new WritableImage(9, 4, 1, 5, file);
				ws.addImage(wi);
			}

			// --------------------------first line--------------------------------------
			// 学号
			ws.addCell(new Label(2, 4, stuInfo.get("xh"), ws.getCell(2, 4)
					.getCellFormat()));
			// 姓名
			ws.addCell(new Label(4, 4, stuInfo.get("xm"), ws.getCell(4, 4)
					.getCellFormat()));
			// 性别
			ws.addCell(new Label(6, 4, stuInfo.get("xb"), ws.getCell(6, 4)
					.getCellFormat()));
			// 出生年月
			ws.addCell(new Label(8, 4, stuInfo.get("csrq"), ws.getCell(8, 4)
					.getCellFormat()));

			// --------------------------secound line--------------------------------------
			// 学院
			ws.addCell(new Label(2, 5, stuInfo.get("xymc"), ws.getCell(2, 5)
					.getCellFormat()));
			// 专业
			ws.addCell(new Label(4, 5, stuInfo.get("zymc"), ws.getCell(4, 5)
					.getCellFormat()));
			// 班级
			ws.addCell(new Label(6, 5, stuInfo.get("bjmc"), ws.getCell(6, 5)
					.getCellFormat()));
			// 民族名称
			ws.addCell(new Label(8, 5, stuInfo.get("mzmc"), ws.getCell(8, 5)
					.getCellFormat()));

			// --------------------------third line--------------------------------------
			// 学制
			ws.addCell(new Label(2, 6, stuInfo.get("xz"), ws.getCell(2, 6)
					.getCellFormat()));
			// 入学日期
			ws.addCell(new Label(4, 6, stuInfo.get("rxrq"), ws.getCell(4, 6)
					.getCellFormat()));
			// 毕业年月
			ws.addCell(new Label(6, 6, stuInfo.get("byny"), ws.getCell(6, 6)
					.getCellFormat()));
			// 籍贯
			ws.addCell(new Label(8, 6, stuInfo.get("jg"), ws.getCell(8, 6)
					.getCellFormat()));

			// --------------------------fourth line--------------------------------------
			// 政治面貌
			ws.addCell(new Label(2, 7, stuInfo.get("zzmmmc"), ws.getCell(2, 7)
					.getCellFormat()));
			// 培养层次
			ws.addCell(new Label(4, 7, pyccMap.get("pyccmc"), ws.getCell(4, 7)
					.getCellFormat()));
			// 户口所在点
			ws.addCell(new Label(6, 7, stuInfo.get("hkszd"), ws.getCell(6, 7)
					.getCellFormat()));
			// 生源地
			ws.addCell(new Label(8, 7, stuInfo.get("syd"), ws.getCell(8, 7)
					.getCellFormat()));

			// --------------------------fifth line--------------------------------------
			// 学籍状态
			ws.addCell(new Label(2, 8, stuInfo.get("xjztm"), ws.getCell(2, 8)
					.getCellFormat()));
			// 是否走读
			ws.addCell(new Label(4, 8, stuInfo.get("sfzd"), ws.getCell(6, 8)
					.getCellFormat()));	
//			 是否走读
			ws.addCell(new Label(6, 8, stuInfo.get("sfzh"), ws.getCell(6, 8)
					.getCellFormat()));	
			// 手机号码
			ws.addCell(new Label(2, 10, stuInfo.get("sjhm"), ws.getCell(2, 10)
					.getCellFormat()));
			// 家庭电话
			ws.addCell(new Label(4, 10, jtcyMap.get("lxdh1"), ws.getCell(4, 10)
					.getCellFormat()));
			// QQ号码
			ws.addCell(new Label(6, 10, stuInfo.get("qqhm"), ws.getCell(6, 10)
					.getCellFormat()));
			// 电子邮箱
			ws.addCell(new Label(8, 10, stuInfo.get("dzyx"), ws.getCell(8, 10)
					.getCellFormat()));

			// --------------------------家庭成员信息 begin--------------------------------------

			ws.addCell(new Label(2, 11, jtcyMap.get("jtszd"), ws.getCell(2, 11)
					.getCellFormat()));
			ws.addCell(new Label(8, 11, jtcyMap.get("yb"), ws.getCell(8, 11)
					.getCellFormat()));

			ws.addCell(new Label(2, 13, jtcyMap.get("jtcy1_xm"), ws.getCell(2,
					13).getCellFormat()));
			ws.addCell(new Label(3, 13, jtcyMap.get("jtcy1_gx"), ws.getCell(3,
					13).getCellFormat()));
			ws.addCell(new Label(4, 13, jtcyMap.get("jtcy1_nl"), ws.getCell(4,
					13).getCellFormat()));
			ws.addCell(new Label(5, 13, jtcyMap.get("zzmm1"), ws.getCell(5, 13)
					.getCellFormat()));
			ws.addCell(new Label(6, 13, jtcyMap.get("mz1"), ws.getCell(7, 13)
					.getCellFormat()));
			ws.addCell(new Label(7, 13, jtcyMap.get("gzdwdz1"), ws.getCell(7,
					13).getCellFormat()));
			ws.addCell(new Label(9, 13, jtcyMap.get("jtcy1_lxdh1"), ws.getCell(9,
					13).getCellFormat()));

			ws.addCell(new Label(2, 14, jtcyMap.get("jtcy2_xm"), ws.getCell(2,
					14).getCellFormat()));
			ws.addCell(new Label(3, 14, jtcyMap.get("jtcy2_gx"), ws.getCell(3,
					14).getCellFormat()));
			ws.addCell(new Label(4, 14, jtcyMap.get("jtcy2_nl"), ws.getCell(3,
					14).getCellFormat()));
			ws.addCell(new Label(5, 14, jtcyMap.get("zzmm2"), ws.getCell(5, 14)
					.getCellFormat()));
			ws.addCell(new Label(6, 14, jtcyMap.get("mz2"), ws.getCell(5, 14)
					.getCellFormat()));
			ws.addCell(new Label(7, 14, jtcyMap.get("gzdwdz2"), ws.getCell(7,
					14).getCellFormat()));
			ws.addCell(new Label(9, 14, jtcyMap.get("jtcy2_lxdh1"), ws.getCell(9,
					14).getCellFormat()));
			
			ws.addCell(new Label(2, 15, jtcyMap.get("jtcy3_xm"), ws.getCell(2,
					15).getCellFormat()));
			ws.addCell(new Label(3, 15, jtcyMap.get("jtcy3_gx"), ws.getCell(3,
					15).getCellFormat()));
			ws.addCell(new Label(4, 15, jtcyMap.get("jtcy3_nl"), ws.getCell(3,
					15).getCellFormat()));
			ws.addCell(new Label(5, 15, jtcyMap.get("zzmm3"), ws.getCell(5, 15)
					.getCellFormat()));
			ws.addCell(new Label(6, 15, jtcyMap.get("mz3"), ws.getCell(5, 14)
					.getCellFormat()));
			ws.addCell(new Label(7, 15, jtcyMap.get("gzdwdz3"), ws.getCell(7,
					15).getCellFormat()));
			ws.addCell(new Label(9, 15, jtcyMap.get("jtcy3_lxdh1"), ws.getCell(9,
					15).getCellFormat()));
			
			ws.addCell(new Label(2, 16, jtcyMap.get("jtcy4_xm"), ws.getCell(2,
					16).getCellFormat()));
			ws.addCell(new Label(3, 16, jtcyMap.get("jtcy4_gx"), ws.getCell(3,
					16).getCellFormat()));
			ws.addCell(new Label(4, 16, jtcyMap.get("jtcy4_nl"), ws.getCell(3,
					16).getCellFormat()));
			ws.addCell(new Label(5, 16, jtcyMap.get("zzmm4"), ws.getCell(5, 16)
					.getCellFormat()));
			ws.addCell(new Label(6, 16, jtcyMap.get("mz4"), ws.getCell(6, 16)
					.getCellFormat()));
			ws.addCell(new Label(7, 16, jtcyMap.get("gzdwdz4"), ws.getCell(7,
					16).getCellFormat()));
			ws.addCell(new Label(9, 16, jtcyMap.get("jtcy4_lxdh1"), ws.getCell(9,
					16).getCellFormat()));

			ws.addCell(new Label(2, 17, jtcyMap.get("jtcy5_xm"), ws.getCell(2,
					17).getCellFormat()));
			ws.addCell(new Label(3, 17, jtcyMap.get("jtcy5_gx"), ws.getCell(3,
					17).getCellFormat()));
			ws.addCell(new Label(4, 17, jtcyMap.get("jtcy5_nl"), ws.getCell(3,
					16).getCellFormat()));
			ws.addCell(new Label(5, 17, jtcyMap.get("zzmm5"), ws.getCell(5, 17)
					.getCellFormat()));
			ws.addCell(new Label(6, 17, jtcyMap.get("mz5"), ws.getCell(6, 17)
					.getCellFormat()));
			ws.addCell(new Label(7, 17, jtcyMap.get("gzdwdz5"), ws.getCell(7,
					17).getCellFormat()));
			ws.addCell(new Label(9, 17, jtcyMap.get("jtcy5_lxdh1"), ws.getCell(9,
					17).getCellFormat()));
			// --------------------------家庭成员信息 end--------------------------------------
			
			// --------------------------学习经历信息 begin----------------------
			for (int i = 0; i < xxjlInfo.size(); i++) {

				HashMap<String, String> xxjlMap = xxjlInfo.get(i);

				ws.addCell(new Label(1, 18 + i, xxjlMap.get("xxsj"), ws
						.getCell(1, 18 + i).getCellFormat()));

				ws.addCell(new Label(5, 18 + i, xxjlMap.get("dwmc"), ws
						.getCell(5, 18 + i).getCellFormat()));
			}
			// --------------------------学习经历信息 end----------------------

			
			// -----------------------学籍异动信息 begin--------------------------------
			for (int i = 0; i < xjydInfo.size(); i++) {

				HashMap<String, String> xjydMap = xjydInfo.get(i);

				ws.addCell(new Label(1, 26 + i, xjydMap.get("ydlbmc"), ws
						.getCell(1, 26 + i).getCellFormat()));
				ws.addCell(new Label(2, 26 + i, xjydMap.get("ydrq"), ws
						.getCell(2, 26 + i).getCellFormat()));
				ws.addCell(new Label(3, 26 + i, xjydMap.get("clwh"), ws
						.getCell(3, 26 + i).getCellFormat()));
				ws.addCell(new Label(4, 26 + i, xjydMap.get("ydyy"), ws
						.getCell(4, 26 + i).getCellFormat()));
				ws.addCell(new Label(5, 26 + i, xjydMap.get("ydsm"), ws
						.getCell(5, 26 + i).getCellFormat()));
				ws.addCell(new Label(7, 26 + i, xjydMap.get("bz"), ws.getCell(
						6, 26 + i).getCellFormat()));
			}
			// -----------------------学籍异动信息 end--------------------------------

			
			// -----------------------党团信息 begin--------------------------------
			ws.addCell(new Label(2, 32, dtInfo.get("rt"), ws.getCell(2, 32)
					.getCellFormat()));
			ws.addCell(new Label(4, 32, dtInfo.get("rdjjfz"), ws.getCell(4, 32)
					.getCellFormat()));
			ws.addCell(new Label(6, 32, dtInfo.get("ybdy"), ws.getCell(6, 32)
					.getCellFormat()));
			ws.addCell(new Label(8, 32, dtInfo.get("zsdy"), ws.getCell(8, 32)
					.getCellFormat()));
			// -----------------------党团信息 end --------------------------------
			
			
			
			// -------------------------综测分信息 begin -----------------------------
			for (int i = 0; i < zcfInfo.size(); i++) {

				HashMap<String, String> zcfMap = zcfInfo.get(i);

				ws.addCell(new Label(1, 35 + i, zcfMap.get("xn"), ws.getCell(1,
						35 + i).getCellFormat()));
				ws.addCell(new Label(3, 35 + i, zcfMap.get("zcf"), ws.getCell(
						2, 35 + i).getCellFormat()));
				ws.addCell(new Label(5, 35 + i, zcfMap.get("zcfbjpm"), ws
						.getCell(3, 35 + i).getCellFormat()));
				ws.addCell(new Label(7, 35 + i, zcfMap.get("zcfnjzypm"), ws
						.getCell(4, 35 + i).getCellFormat()));
			}
			// -------------------------综测分信息 end -----------------------------	
			
			
			// -------------------------评奖信息 begin -----------------------------
			for (int i = 0; i < pjInfo.size(); i++) {
				HashMap<String, String> pjpyMap = pjInfo.get(i);

				ws.addCell(new Label(1, 41 + i, pjpyMap.get("pjxn"), ws
						.getCell(1, 41 + i).getCellFormat()));
				ws.addCell(new Label(3, 41 + i, pjpyMap.get("xmmc"), ws
						.getCell(3, 41 + i).getCellFormat()));
				ws.addCell(new Label(5, 41 + i, pjpyMap.get("xmlx"), ws
						.getCell(5, 41 + i).getCellFormat()));
				ws.addCell(new Label(7, 41 + i, pjpyMap.get("xmje"), ws
						.getCell(7, 41 + i).getCellFormat()));

			}
			// -------------------------评奖信息 end -----------------------------
			
			
			// -------------------------违纪处分 begin -----------------------------
			for (int i = 0; i < wjInfo.size(); i++) {

				HashMap<String, String> wjMap = wjInfo.get(i);

				ws.addCell(new Label(1, 47 + i, wjMap.get("xn"), ws.getCell(1,
						47 + i).getCellFormat()));
				ws.addCell(new Label(3, 47 + i, wjMap.get("cflbmc"), ws
						.getCell(3, 47 + i).getCellFormat()));
				ws.addCell(new Label(5, 47 + i, wjMap.get("cfyymc"), ws
						.getCell(5, 47 + i).getCellFormat()));
				ws.addCell(new Label(7, 47 + i, wjMap.get("cfsj"), ws.getCell(
						7, 47 + i).getCellFormat()));
				ws.addCell(new Label(9, 47 + i, wjMap.get("cfwh"), ws.getCell(
						9, 47 + i).getCellFormat()));
			}
			// -------------------------违纪处分 end -----------------------------
			
			
			// -------------------------学生资助 begin -----------------------------
			for (int i = 0; i < zzInfo.size(); i++) {

				HashMap<String, String> zzMap = zzInfo.get(i);

				ws.addCell(new Label(1, 53 + i, zzMap.get("xn"), ws.getCell(1,
						53 + i).getCellFormat()));
				ws.addCell(new Label(3, 53 + i, zzMap.get("xmmc"), ws.getCell(
						3, 53 + i).getCellFormat()));
				ws.addCell(new Label(5, 53 + i, zzMap.get("xmzzjb"), ws
						.getCell(5, 53 + i).getCellFormat()));
				ws.addCell(new Label(7, 53 + i, zzMap.get("xmzzje"), ws
						.getCell(7, 53 + i).getCellFormat()));
			}
			// -------------------------学生资助 end -----------------------------
			
			// -------------------------勤工助学 begin -----------------------------		
			for (int i = 0; i < qgzxInfo.size(); i++) {

				HashMap<String, String> qgzxMap = qgzxInfo.get(i);

				ws.addCell(new Label(1, 59 + i, qgzxMap.get("xn"), ws.getCell(
						1, 59 + i).getCellFormat()));
				ws.addCell(new Label(3, 59 + i, qgzxMap.get("gwdm"), ws
						.getCell(3, 59 + i).getCellFormat()));
				ws.addCell(new Label(7, 59 + i, qgzxMap.get("gzsj"), ws
						.getCell(7, 59 + i).getCellFormat()));
			}
			// -------------------------勤工助学 end -----------------------------	
			
			// -------------------------住宿信息 begin -----------------------------			
			for (int i = 0; i < zsInfo.size(); i++) {

				HashMap<String, String> zsMap = zsInfo.get(i);

				ws.addCell(new Label(1, 65 + i, zsMap.get("ldmc"), ws.getCell(
						1, 65 + i).getCellFormat()));
				ws.addCell(new Label(3, 65 + i, zsMap.get("qsh"), ws.getCell(3,
						65 + i).getCellFormat()));
				ws.addCell(new Label(5, 65 + i, zsMap.get("cwh"), ws.getCell(5,
						65 + i).getCellFormat()));
				ws.addCell(new Label(7, 65 + i, zsMap.get("rzsj"), ws.getCell(
						7, 65 + i).getCellFormat()));
			}
			// -------------------------住宿信息 end -----------------------------			
			ws.addCell(new Label(9,70, GetTime.getNowTime2(), ws.getCell(7, 70).getCellFormat()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

}
