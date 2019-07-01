/*
 * @Title: 学生工作管理信息系统
 * 
 * @ClassName: YlxfbService.java
 * 
 * @time: 2010-06-04 
 * 
 * @copyright: hz-zfsoft 
 */
package xgxt.pjpy.zjlg.ylxfb;

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
import xgxt.utils.ExcelMethods;
import xgxt.utils.MathCommon;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

/**
 * @desc 浙江理工 - 评奖评优 - 优秀学风班级数据维护SERVICE，
 *                          包括增，删，改，查，审核等操作
 * @innerClass  YlxfbDAO.java                          
 * @author lt
 * @version 1.0 2010-06-04
 */
public class YlxfbService {

	YlxfbDAO dao = new YlxfbDAO();
	
	/**
	 * 禁用复选框按钮条件
	 * @param userType
	 * @param fdyFlag
	 * @return
	 */
	public String getClientColumn(String userType, String fdyFlag) {
		return UserTypePd.isXx(userType) ? " '' dis,"
				: (UserTypePd.isFdy(fdyFlag) ? " (case when (xxsh='通过' or xysh='通过') then 'disabled' else '' end) dis,"
						: " (case when xxsh='通过' then 'disabled' else '' end) dis,");
	}
	
	/**
	 * 查询班级考试,考查课程分数设置信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> queryBjkskckxx(YlxfbModel model) {
		return dao.queryBjkskckxx(model);
	}
	
	/**
	 * 查询班级排名信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryPmxx(YlxfbModel model) throws Exception{
		int nj = StringUtils.isNotNull(model.getNj()) ? Integer.parseInt(model.getNj()) : 0;
		String ynj = (nj) + "-" + (nj + 1);
		
		String enj = (nj + 1) + "-" + (nj + 2);
		
		HashMap<String, String> rs = dao.queryPmxx(model, ynj, enj);
		if (!rs.isEmpty()) {
			double kcyxs = StringUtils.isNotNull(rs.get("kcyxs")) ? Double.parseDouble(rs.get("kcyxs")) : 0;
			double kcjgs = StringUtils.isNotNull(rs.get("kcjgs")) ? Double.parseDouble(rs.get("kcjgs")) : 0;
			double kckcx = StringUtils.isNotNull(rs.get("kckcx")) ? Double.parseDouble(rs.get("kckcx")) : 0;
			
			double ksyxs = StringUtils.isNotNull(rs.get("ksyxs")) ? Double.parseDouble(rs.get("ksyxs")) : 0;
			double ksjgs = StringUtils.isNotNull(rs.get("ksjgs")) ? Double.parseDouble(rs.get("ksjgs")) : 0;
			double kskcx = StringUtils.isNotNull(rs.get("kskcx")) ? Double.parseDouble(rs.get("kskcx")) : 0;
			
			double sszs = StringUtils.isNotNull(rs.get("sszs")) ? Double.parseDouble(rs.get("sszs")) : 0;
			double ajss = StringUtils.isNotNull(rs.get("ajss")) ? Double.parseDouble(rs.get("ajss")) : 0;
			double tsss = StringUtils.isNotNull(rs.get("tsss")) ? Double.parseDouble(rs.get("tsss")) : 0;
			double wmss = StringUtils.isNotNull(rs.get("wmss")) ? Double.parseDouble(rs.get("wmss")) : 0;
			
			if (kckcx != 0) {
				rs.put("kckyxl", String.valueOf(MathCommon.getDouble(kcyxs/kckcx*100, 2)));
				rs.put("kckjgl", String.valueOf(MathCommon.getDouble(kcjgs/kckcx*100, 2)));
			}
			if (kskcx != 0) {
				rs.put("kskyxl", String.valueOf(MathCommon.getDouble(ksyxs/kskcx*100, 2)));
				rs.put("kskjgl", String.valueOf(MathCommon.getDouble(ksjgs/kskcx*100, 2)));
			}
			if (sszs != 0) {
				rs.put("ajl", String.valueOf(MathCommon.getDouble(ajss/sszs*100, 2)));
				rs.put("tsl", String.valueOf(MathCommon.getDouble(tsss/sszs*100, 2)));
				rs.put("wml", String.valueOf(MathCommon.getDouble(wmss/sszs*100, 2)));
			}
		}
		return rs;
	}
	
	public String getFdyxm(String bjdm) {
		return dao.getFdyxm(bjdm);
	}
	
	public void exportYlxfbhzData(WritableWorkbook wwb, YlxfbModel model) throws Exception{
		WritableSheet ws = wwb.getSheet(0);
		List<String[]> rs = dao.queryYlxfbHzData(model);
		WritableCellFormat format = ExcelMB.getWritableCellFormat(10,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE,
				Border.NONE);
		
		ws.addCell(new Label(2,2,model.getXymc(),format));
		ws.addCell(new Label(20,2,DateUtils.getLyr(),format));
		
		if (!rs.isEmpty()) {
			ExcelMB.writeDataToCellByIterator(ws, rs, rs.get(0).length - 1,0, 5, format);
			
			String[] bjdmArr = dao.queryBjdmByxydm(model);
			String bjdmInSql = getBjdmInSql(bjdmArr);
			
			List<HashMap<String, String>> kcfsszMap = dao.queryBjkskckxx(model);
			String ksfs = "0";
			String kcfs = "0";
			if (!kcfsszMap.isEmpty()) {
				for (HashMap<String, String> map : kcfsszMap) {
					if (!map.isEmpty() && "kckyxlfs".equalsIgnoreCase(map.get("tjzd"))) {
						kcfs = map.get("tjz");
					} else if (!map.isEmpty() && "kskyxlfs".equalsIgnoreCase(map.get("tjzd"))) {
						ksfs = map.get("tjz");
					}
				}
			}
			
			List<HashMap<String, String>> bjpmList = dao.queryBjpmList(model, bjdmInSql, ksfs, kcfs);
			if (!bjpmList.isEmpty()) {
				for (int i = 0; i < rs.size(); i++) {
					String[] oneData = rs.get(i);
					if (oneData != null && oneData.length > 0) {
						for (int j = 0; j < bjpmList.size(); j++) {
							HashMap<String, String> map = bjpmList.get(j);
							/* 计算每个班级优秀率,及格率,宿舍文明率 */
							if (!map.isEmpty()
									&& map.get("bjdm").equalsIgnoreCase(
											oneData[oneData.length - 1])) {
								double kcyxs = StringUtils.isNotNull(map.get("kcyxs")) ? Double.parseDouble(map.get("kcyxs")) : 0;
								double kcjgs = StringUtils.isNotNull(map.get("kcjgs")) ? Double.parseDouble(map.get("kcjgs")) : 0;
								double kckcx = StringUtils.isNotNull(map.get("kckcx")) ? Double.parseDouble(map.get("kckcx")) : 0;
								
								double ksyxs = StringUtils.isNotNull(map.get("ksyxs")) ? Double.parseDouble(map.get("ksyxs")) : 0;
								double ksjgs = StringUtils.isNotNull(map.get("ksjgs")) ? Double.parseDouble(map.get("ksjgs")) : 0;
								double kskcx = StringUtils.isNotNull(map.get("kskcx")) ? Double.parseDouble(map.get("kskcx")) : 0;
								
								double sszs = StringUtils.isNotNull(map.get("sszs")) ? Double.parseDouble(map.get("sszs")) : 0;
								double ajss = StringUtils.isNotNull(map.get("ajqs")) ? Double.parseDouble(map.get("ajqs")) : 0;
								double tsss = StringUtils.isNotNull(map.get("tsqs")) ? Double.parseDouble(map.get("tsqs")) : 0;
								double wmss = StringUtils.isNotNull(map.get("wmqs")) ? Double.parseDouble(map.get("wmqs")) : 0;
								
								if (kckcx != 0) {
									map.put("kckyxl", String.valueOf(0==MathCommon.getDouble(kcyxs/kckcx*100, 2) ? "" : MathCommon.getDouble(kcyxs/kckcx*100, 2)+"%"));
									map.put("kckjgl", String.valueOf(0==MathCommon.getDouble(kcjgs/kckcx*100, 2) ? "" : MathCommon.getDouble(kcjgs/kckcx*100, 2)+"%"));
								}
								if (kskcx != 0) {
									map.put("kskyxl", String.valueOf(0==MathCommon.getDouble(ksyxs/kskcx*100, 2) ? "" : MathCommon.getDouble(ksyxs/kskcx*100, 2)+"%"));
									map.put("kskjgl", String.valueOf(0==MathCommon.getDouble(ksjgs/kskcx*100, 2) ? "" : MathCommon.getDouble(ksjgs/kskcx*100, 2)+"%"));
								}
								if (sszs != 0) {
									map.put("ajl", String.valueOf(0==MathCommon.getDouble(ajss/sszs*100, 2) ? "" : MathCommon.getDouble(ajss/sszs*100, 2) + "%"));
									map.put("tsl", String.valueOf(0==MathCommon.getDouble(tsss/sszs*100, 2) ? "" : MathCommon.getDouble(tsss/sszs*100, 2) + "%"));
									map.put("wml", String.valueOf(0==MathCommon.getDouble(wmss/sszs*100, 2) ? "" : MathCommon.getDouble(wmss/sszs*100, 2) + "%"));
								}
							}
						}
					}
				}
				
				for (int i=0;i<bjpmList.size();i++) {
					HashMap<String, String> map = bjpmList.get(i);
					ws.addCell(new Label(6,i+5,map.get("kskyxl"),format));
					ws.addCell(new Label(7,i+5,map.get("kskjgl"),format));
					ws.addCell(new Label(8,i+5,map.get("kckyxl"),format));
					ws.addCell(new Label(9,i+5,map.get("kckjgl"),format));
					ws.addCell(new Label(10,i+5,(StringUtils.isNotNull(map.get("ynj")) ? map.get("ynj") : "") + "/" + (StringUtils.isNotNull(map.get("enj")) ? map.get("enj") : ""),format));
					ws.addCell(new Label(15,i+5,map.get("ajl"),format));
					ws.addCell(new Label(16,i+5,map.get("wml"),format));
					ws.addCell(new Label(17,i+5,map.get("tsl"),format));
					ws.addCell(new Label(18,i+5,map.get("wjcs"),format));
				}
			}
			
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	private String getBjdmInSql(String[] bjdmArr) {
		String bjdmInSql = "";
		if (bjdmArr != null && bjdmArr.length > 0) {
			bjdmInSql = " and b.bjdm in ('";
			for (int i=0;i<bjdmArr.length;i++) {
				bjdmInSql += bjdmArr[i];
				bjdmInSql += "','";
			}
			bjdmInSql = bjdmInSql.substring(0, bjdmInSql.length() - 2);
			bjdmInSql +=")";
		}
		return bjdmInSql;
	}
	
	
}
