package xgxt.xszz.tjgy;

import java.io.File;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import common.XszzXmdm;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommTjbbService;

/**
 * 天津工业大学资助统计表
 * @author Penghui.Qu
 */
public class XszzPrintService implements XszzCommTjbbService {

	
	/**
	 * 打印入口
	 */
	public void printZztjbb(XszzTyForm model, OutputStream os) {
		
		String tjbdm = model.getTjbbdm();
		String xmdm = XszzXmdm.XSZZ_GJZXJ;
		String[] fjqkArr = getFjqkArr(xmdm);

		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		
		if (XszzXmdm.XSZZ_TJBB_TJGY_GJZXJHZB.equalsIgnoreCase(tjbdm)) {
			//国家助学金汇总表(专科)
			List<String[]> data = getZxjHzData(model,fjqkArr,"zks");
			String[] hjData = getZxjHzhj(model,fjqkArr,"zks");
			printZxjHzb(wwb,model, fjqkArr, data,"专科",hjData);
			
			//国家助学金汇总表(本科)
			data = getZxjHzData(model,fjqkArr,"bks");
			hjData = getZxjHzhj(model,fjqkArr,"bks");
			printZxjHzb(wwb,model, fjqkArr, data,"本科",hjData);
			
			//国家助学金汇总表(汇总)
			data = getZxjHzData(model,fjqkArr,"hz");
			hjData = getZxjHzhj(model,fjqkArr,"hz");
			printZxjHzb(wwb,model, fjqkArr, data,"总表",hjData);
		} else if (XszzXmdm.XSZZ_TJBB_TJGY_GJZXJTJB.equalsIgnoreCase(tjbdm)) {
			//国家助学金统计表（专科）
			String[] data = getZxjTjData(model,fjqkArr,"zks");
			String zxsrs = getZxsrs("zks");
			printZxjTjb(wwb,model, fjqkArr, data,"专科",zxsrs);
			
			//国家助学金统计表（本科）
			data = getZxjTjData(model,fjqkArr,"bks");
			zxsrs = getZxsrs("bks");
			printZxjTjb(wwb,model, fjqkArr, data,"本科",zxsrs);
			
			//国家助学金统计表（汇总）
			data = getZxjTjData(model,fjqkArr,"hz");
			zxsrs = getZxsrs("hz");
			printZxjTjb(wwb,model, fjqkArr, data,"汇总",zxsrs);
		}  else if (XszzXmdm.XSZZ_TJBB_TJGY_LZJXJTJB.equalsIgnoreCase(tjbdm)){
			//国家励志奖学金统计表(专科)
			String[] data = getLzjxTjData(model,"zks");
			String zxsrs = getZxsrs("zks");
			printLzjxjTjb(wwb,model,data,zxsrs,"专科");
			//国家励志奖学金统计表(本科)
			data = getLzjxTjData(model,"bks");
			zxsrs = getZxsrs("bks");
			printLzjxjTjb(wwb,model,data,zxsrs,"本科");
			//国家励志奖学金统计表(汇总)
			data = getLzjxTjData(model,"hz");
			zxsrs = getZxsrs("hz");
			printLzjxjTjb(wwb,model,data,zxsrs,"汇总");
		} else if (XszzXmdm.XSZZ_TJBB_TJGY_LZJXJHZB.equalsIgnoreCase(tjbdm)){
			//国家励志奖学金初审名单表(专科)
			List<String[]> data = getLzjxjHzData(model, "zks");
			printLzjxjHzb(wwb,model,data,"专科");
			//国家励志奖学金初审名单表(本科)
			data = getLzjxjHzData(model, "bks");
			printLzjxjHzb(wwb,model,data,"本科");
			//国家励志奖学金初审名单表(总科)
			data = getLzjxjHzData(model, "hz");
			printLzjxjHzb(wwb,model,data,"总表");
		}
		//输出excel
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	
	/**
	 * 国家助学金汇总表
	 */
	private void printZxjHzb(WritableWorkbook wwb,XszzTyForm model,
				String[] fjqkArr,List<String[]> data,String pycc,String[] hjData){
		
		int i = fjqkArr.length;
		StringBuilder title = new StringBuilder();
		title.append(model.getXn())
		     .append("学年度普通本科高校、高等职业学校国家助学金汇总表（")
		     .append("总表".equals(pycc) ? "" : "普通")
		     .append(pycc)
		     .append("）");
		
		ExcelMB excel = new ExcelMB();
		WritableSheet ws = wwb.createSheet(pycc, 0);

		try {
			excel.printTitle(ws, title.toString(), 18, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式

			//二级标题
			ws.mergeCells(1, 1, 6, 1);
			ws.addCell(new Label(1, 1,"学校名称：天津工业大学", wcfTytle));
			ws.mergeCells(8+i, 1, 10+i, 1);
			ws.addCell(new Label(8+i, 1,"填表日期：", wcfTytle));

			//
			ws.mergeCells(7, 2, 7+i, 2);
			ws.addCell(new Label(7, 2,"资助金额", wcfTytle));
			
			//
			String[] cols = new String[]{"序号","姓名","性别","民族","出生年月","户籍性质","公民身份号码"};
			for (int j = 0 ; j < cols.length ; j++){
				ws.mergeCells(j, 2, j, 3);
				ws.addCell(new Label(j, 2,cols[j], wcfTytle));
			}
			//合计
			ws.addCell(new Label(cols.length, 3,"合计", wcfTytle));
			
			for (int c = 0 ; c < fjqkArr.length ; c++){
				ws.addCell(new Label(cols.length+c+1, 3,fjqkArr[c], wcfTytle));
			}
			
			ws.mergeCells(cols.length+i+1, 2, cols.length+i+1, 3);
			ws.mergeCells(cols.length+i+2, 2, cols.length+i+2, 3);
			ws.addCell(new Label(cols.length+i+1, 2,"银行卡号码", wcfTytle));
			ws.addCell(new Label(cols.length+i+2, 2,"年级", wcfTytle));
			
			ws.mergeCells(cols.length+i+3, 2, cols.length+i+6, 2);
			ws.addCell(new Label(cols.length+i+3, 2,"学生生源地", wcfTytle));
			ws.addCell(new Label(cols.length+i+3, 3,"东部", wcfTytle));
			ws.addCell(new Label(cols.length+i+4, 3,"其中天津", wcfTytle));
			ws.addCell(new Label(cols.length+i+5, 3,"中部", wcfTytle));
			ws.addCell(new Label(cols.length+i+6, 3,"西部", wcfTytle));
			
			ws.mergeCells(cols.length+i+7, 2, cols.length+i+7, 3);
			ws.addCell(new Label(cols.length+i+7, 2,"备注", wcfTytle));
			
			//合计行
			for (int m = 0 ; m < hjData.length ; m++){
				ws.addCell(new Label(7+m, 4,hjData[m], wcfTytle));
			}
			
			//数据输出
			for (int x = 0 ; x < data.size() ; x++){
				for (int y = 0 ; y < data.get(x).length ; y++){
					ws.addCell(new Label(y, x+5,data.get(x)[y], wcfTytle));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 国家助学金统计表
	 * @param wwb
	 * @param model
	 * @param fjqkArr
	 * @param data
	 * @param pycc
	 */
	private void printZxjTjb(WritableWorkbook wwb,XszzTyForm model,
			String[] fjqkArr,String[] data,String pycc,String zxsrs){
		
		int i = fjqkArr.length;
		String title = "天津市普通本科高校、高等职业学校国家助学金统计表";
		
		ExcelMB excel = new ExcelMB();
		WritableSheet ws = wwb.createSheet(pycc, 0);
		
		try {
			excel.printTitle(ws, title, 10+i, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式

			//二级标题
			ws.mergeCells(0, 1, 2, 1);
			ws.addCell(new Label(0, 1,"学校名称：天津工业大学", wcfTytle));
			ws.mergeCells(5+i, 1, 8+i, 1);
			ws.addCell(new Label(5+i, 1,"填表日期：", wcfTytle));

			//表头
			ws.mergeCells(0, 2, 0, 3);
			ws.mergeCells(1, 2, 1, 3);
			ws.addCell(new Label(0, 2,"学校", wcfTytle));
			ws.addCell(new Label(1, 2,"在校生人数", wcfTytle));
			
			ws.mergeCells(2, 2, 3, 2);
			ws.addCell(new Label(2, 2,"受奖励学生数（人）", wcfTytle));
			ws.addCell(new Label(2, 3,"合计", wcfTytle));
			ws.addCell(new Label(3, 3,"其中：农村", wcfTytle));
			
			ws.mergeCells(4, 2, 4+i, 2);
			ws.addCell(new Label(4, 2,"资助金额", wcfTytle));
			ws.addCell(new Label(4, 3,"合计", wcfTytle));
			for (int j = 0; j < i ; j++){
				ws.addCell(new Label(5+j, 3,fjqkArr[j], wcfTytle));
			}
			
			ws.mergeCells(5+i, 2, 8+i, 2);
			ws.addCell(new Label(5+i, 2,"生源地人数（人）", wcfTytle));
			ws.addCell(new Label(5+i, 3,"东部", wcfTytle));
			ws.addCell(new Label(6+i, 3,"其中：天津", wcfTytle));
			ws.addCell(new Label(7+i, 3,"中部", wcfTytle));
			ws.addCell(new Label(8+i, 3,"西部", wcfTytle));
			
			ws.mergeCells(9+i, 2, 9+i, 3);
			ws.addCell(new Label(9+i, 2,"备注", wcfTytle));
			
			ws.addCell(new Label(0, 4,"天津工业大学", wcfTytle));
			ws.addCell(new Label(1, 4,zxsrs, wcfTytle));
			
			for(int x = 0; x < data.length ; x++){
				ws.addCell(new Label(2+x, 4,data[x], wcfTytle));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 资助分级情况
	 * @param xmdm
	 * @return
	 */
	private String[] getFjqkArr(String xmdm){
		DAO dao = DAO.getInstance();
		String sql = "select fjmc from xszz_xmfjqkb where xmdm = ? order by id";
		
		try {
			return dao.getArray(sql, new String[]{xmdm}, "fjmc");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * 国家助学金汇总数据查询
	 * @param model
	 * @param fjqkArr
	 * @param pycc 培养层次
	 * @return
	 * 
	 * 附：原生SQL
	 * 
	 * select a.xh,b.xm,c.kzzd1 xb,c.kzzd3 mz,
       c.kzzd2 csny,c.jthk,c.kzzd6 sfzh,
       ---这里做循环
       case when a.xmzzjb='一级' then a.xmzzje end xmzzjb1,
       case when a.xmzzjb='二级' then a.xmzzje end xmzzjb2,
       case when a.xmzzjb='三级' then a.xmzzje end xmzzjb3,
       c.kzzd11 yhkh,substr(kzzd5,0,4) nj,
       case when d.dqmc='东部' or d.dqmc='天津' then 1 end dbdq,
       case when d.dqmc='天津' then 1 end tjdq,
       case when d.dqmc='中部' then 1 end zbdq,
       case when d.dqmc='西部' then 1 end xbdq,
       '' bz,b.syd
		from gjzxj a 
		left join view_xsxxb b on a.xh = b.xh
		left join (select t.* from jtqkdcb t 
		where exists (select 1 from (select xh , max(sqsj) sqsj from 
		jtqkdcb group by xh) j where t.xh=j.xh and t.sqsj=j.sqsj)) c on a.xh = c.xh
		left join xg_xszz_dqfbb d on b.syd=d.dqdm
		where a.xn = '2010-2011'
	 * 
	 */
	private List<String[]> getZxjHzData(XszzTyForm model,String[] fjqkArr,String pycc){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String[] outputArr = new String[]{"r","xm","xb","mz","csny","jthk","sfzh","hj"};
		outputArr = StringUtils.joinStrArr(outputArr,fjqkArr,new String[]{"yhkh","nj","dbdq","tjdq","zbdq","xbdq","bz"});
		
		sql.append("select rownum r,a.xh,b.xm,c.kzzd1 xb,c.kzzd3 mz,")
		   .append("c.kzzd2 csny,c.jthk,c.kzzd6 sfzh,'' hj,");
		
		if (null != fjqkArr){
			for (int i = 0 ; i < fjqkArr.length ; i++){
				sql.append("case when a.xmzzjb='")
				   .append(fjqkArr[i])
				   .append("' then a.xmzzje end ")
				   .append(fjqkArr[i]).append(",");
			}
		}
		
		sql.append("c.kzzd11 yhkh,substr(kzzd5,0,4) nj,")
		   .append("case when d.dqmc='东部' or d.dqmc='天津' then 1 end dbdq,")
		   .append("case when d.dqmc='天津' then 1 end tjdq,")
		   .append("case when d.dqmc='中部' then 1 end zbdq,")
		   .append("case when d.dqmc='西部' then 1 end xbdq,")
		   .append("'' bz from gjzxj a ")
		   .append("left join xsxxb b on a.xh = b.xh ")
		   .append("left join (select t.* from jtqkdcb t ")
		   .append("where exists (select 1 from (select xh , max(sqsj) sqsj from ")
		   .append("jtqkdcb group by xh) j where t.xh=j.xh and t.sqsj=j.sqsj)) c on a.xh = c.xh ")
		   .append("left join xg_xszz_dqfbb d on b.syd=d.dqdm ")
		   .append("where a.xn=? ");
		
		if ("bks".equalsIgnoreCase(pycc)){
			//本科生
			sql.append(" and exists (select 1 from view_xsjbxx x where a.xh=x.xh and 4<=to_number(nvl(x.xz,0)))");
		} else if ("zks".equalsIgnoreCase(pycc)){
			//专科生
			sql.append(" and exists (select 1 from view_xsjbxx x where a.xh=x.xh and to_number(nvl(x.xz,0))<4)");
		}
		return dao.rsToVator(sql.toString(), new String[]{model.getXn()}, outputArr);
	}
	
	
	
	
	/**
	 * 国家助学金统计数据查询
	 * @param model
	 * @param fjqkArr 项目分级情况
	 * @param pycc 培养层次
	 * @return
	 * 
	 * 附：原生SQL
	 * select 
       sum(count) jlrs,sum(nchk) ncrs,sum(xmzzjb1+xmzzjb2+xmzzjb3) hj,
       --循环
       sum(xmzzjb1) xmzzjb1,sum(xmzzjb2) xmzzjb2,sum(xmzzjb3) xmzzjb3,
       sum(dbdq) dbdq,sum(tjdq) tjdq,sum(zbdq) zbdq,sum(xbdq) xbdq,'' bz
		from (
		select 1 count,
		       case when c.jthk='农村' then 1 else 0 end nchk,
		       ---这里做循环
		       case when a.xmzzjb='一级' then a.xmzzje else '0' end xmzzjb1,
		       case when a.xmzzjb='二级' then a.xmzzje else '0' end xmzzjb2,
		       case when a.xmzzjb='三级' then a.xmzzje else '0' end xmzzjb3,
		       case when d.dqmc='东部' or d.dqmc='天津' then 1 else 0 end dbdq,
		       case when d.dqmc='天津' then 1 else 0 end tjdq,
		       case when d.dqmc='中部' then 1 else 0 end zbdq,
		       case when d.dqmc='西部' then 1 else 0 end xbdq
		from gjzxj a 
		left join view_xsxxb b on a.xh = b.xh
		left join (select t.* from jtqkdcb t 
		where exists (select 1 from (select xh , max(sqsj) sqsj from 
		jtqkdcb group by xh) j where t.xh=j.xh and t.sqsj=j.sqsj)) c on a.xh = c.xh
		left join xg_xszz_dqfbb d on b.syd=d.dqdm
		--where a.xn = '2010-2011'
		) 
	 * 
	 */
	private String[] getZxjTjData(XszzTyForm model,String[] fjqkArr,String pycc){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String[] outputArr = new String[]{"jlrs","ncrs","hj"};
		outputArr = StringUtils.joinStrArr(outputArr,fjqkArr,new String[]{"dbdq","tjdq","zbdq","xbdq","bz"});
		
		sql.append("select sum(count) jlrs,sum(nchk) ncrs,sum(");
		
		for (int i = 0 ; i < fjqkArr.length ; i++){
			sql.append(fjqkArr[i]);
			
			if (i != fjqkArr.length - 1){
				sql.append("+");
			}
		}
		sql.append(") hj,");
		
		for (int i = 0 ; i < fjqkArr.length ; i++){
			sql.append("sum(")
			   .append(fjqkArr[i])
			   .append(") ")
			   .append(fjqkArr[i])
			   .append(",");
		}
		sql.append("sum(dbdq) dbdq,sum(tjdq) tjdq,")
		   .append("sum(zbdq) zbdq,sum(xbdq) xbdq,'' bz ")
		   .append("from (select 1 count,")
		   .append("case when c.jthk='农村' then 1 else 0 end nchk,");
		
		for (int i = 0 ; i < fjqkArr.length ; i++){
			sql.append("case when a.xmzzjb='")
			   .append(fjqkArr[i])
			   .append("' then a.xmzzje else '0' end ")
			   .append(fjqkArr[i])
			   .append(",");
		}
		
		sql.append("case when d.dqmc='东部' or d.dqmc='天津' then 1 else 0 end dbdq,")
		   .append("case when d.dqmc='天津' then 1 else 0 end tjdq,")
		   .append("case when d.dqmc='中部' then 1 else 0 end zbdq,")
		   .append("case when d.dqmc='西部' then 1 else 0 end xbdq ")
		   .append("from gjzxj a ")
		   .append("left join xsxxb b on a.xh = b.xh ")
		   .append("left join (select t.* from jtqkdcb t ")
		   .append("where exists (select 1 from (select xh , max(sqsj) sqsj from  ")
		   .append("jtqkdcb group by xh) j where t.xh=j.xh and t.sqsj=j.sqsj)) c on a.xh = c.xh ")
		   .append("left join xg_xszz_dqfbb d on b.syd=d.dqdm ");
		
		sql.append("where a.xn=? ");
		if ("bks".equalsIgnoreCase(pycc)){
			//本科生
			sql.append(" and exists (select 1 from view_xsjbxx x where a.xh=x.xh and 4<=to_number(nvl(x.xz,0)))");
		} else if ("zks".equalsIgnoreCase(pycc)){
			//专科生
			sql.append(" and exists (select 1 from view_xsjbxx x where a.xh=x.xh and to_number(nvl(x.xz,0))<4)");
		}
		sql.append(")");
		
		return dao.getOneRs(sql.toString(), new String[]{model.getXn()}, outputArr);
	}
	
	
	
	/**
	 * 查询在校生人数
	 * @param pycc
	 * @return
	 */
	private String getZxsrs(String pycc){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from view_xsjbxx where 1=1 ");
		if ("bks".equalsIgnoreCase(pycc)){
			//本科生
			sql.append(" and 4<=to_number(nvl(xz,0)) ");
		} else if ("zks".equalsIgnoreCase(pycc)){
			//专科生
			sql.append(" and to_number(nvl(xz,0))<4 ");
		}
		return dao.getOneRs(sql.toString(), new String[]{}, "count");
	}
	
	
	
	/**
	 * 助学金汇总统计，合计栏
	 * @param model
	 * @param fjqkArr
	 * @param pycc
	 * @return
	 */
	private String[] getZxjHzhj(XszzTyForm model,String[] fjqkArr,String pycc){
		
		DAO dao = DAO.getInstance();
		String[] outputArr = StringUtils.joinStrArr(new String[]{"zzhj"},
					fjqkArr,new String[]{"yhkh","nj","dbdq","tjdq","zbdq","xbdq"});
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sum(");
		for (int i = 0 ; i < fjqkArr.length ; i++){
			sql.append(fjqkArr[i]);
			
			if (i != fjqkArr.length-1){
				sql.append("+");
			}
		}
		sql.append(") zzhj,");
		
		for (int i = 0 ; i < fjqkArr.length ; i++){
			sql.append("sum(").append(fjqkArr[i]).append(") ").append(fjqkArr[i]).append(",");
		}
		
		sql.append("'' yhkh,'' nj,")
		   .append("sum(dbdq) dbdq,")
		   .append("sum(tjdq) tjdq,")
		   .append("sum(zbdq) zbdq,")
		   .append("sum(xbdq) xbdq ")
		   .append("from (select a.xh,b.xm,");
		
		for (int i = 0 ; i < fjqkArr.length ; i++){
			sql.append("case when a.xmzzjb='")
			   .append(fjqkArr[i])
			   .append("' then a.xmzzje else '0' end ")
			   .append(fjqkArr[i])
			   .append(",");
		}
		sql.append("case when d.dqmc='东部' or d.dqmc='天津' then 1 else 0 end dbdq,")
		   .append("case when d.dqmc='天津' then 1 else 0 end tjdq,")
		   .append("case when d.dqmc='中部' then 1 else 0 end zbdq,")
		   .append("case when d.dqmc='西部' then 1 else 0 end xbdq ")
		   .append("from gjzxj a ")
		   .append("left join xsxxb b on a.xh = b.xh ")
		   .append("left join xg_xszz_dqfbb d on b.syd=d.dqdm ")
		   .append("where a.xn = ? ");
		
		if ("bks".equalsIgnoreCase(pycc)){
			//本科生
			sql.append(" and exists (select 1 from view_xsjbxx x where a.xh=x.xh and 4<=to_number(nvl(x.xz,0)))");
		} else if ("zks".equalsIgnoreCase(pycc)){
			//专科生
			sql.append(" and exists (select 1 from view_xsjbxx x where a.xh=x.xh and to_number(nvl(x.xz,0))<4)");
		}
		sql.append(")");
		return dao.getOneRs(sql.toString(), new String[]{model.getXn()}, outputArr);
	}
	
	
	
	
	
	/**
	 * 导入数据
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public boolean impZcfInfo(String filePath,User user) throws Exception {

		File file = new File(filePath);
		boolean flg = false;
		
		// 导入数据
		if (!Base.isNull(filePath)) {
			Workbook book = Workbook.getWorkbook(file);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 导入list
			List<String[]> params = new ArrayList<String[]>();
			// 行
			int rows = sheet.getRows();
			// 循环行
			for (int i = 0; i < rows; i++) {
				
				Cell cell = sheet.getCell(0, i);
				String content = cell.getContents();
				
				if (content.indexOf("/") > -1) {
					String[] param = new String[2];
					param[1] = content;
					param[0] = sheet.getCell(2, i).getContents();
					
					params.add(param);
				} else {
					continue;
				}
			}
			
			DAO dao = DAO.getInstance();
			String sql = "update xg_xszz_dqfbb set dqmc = ? where dqdm = ?";
			int[] result = dao.runBatch(sql, params);
			flg = dao.checkBatchResult(result);
			file.delete();
		}

		return flg;
	}
	
	
	
	/**
	 * 国家励志奖学金统计表
	 * @param wwb
	 * @param model
	 * @param data
	 * @param zxsrs
	 * @param pycc
	 */
	private void printLzjxjTjb(WritableWorkbook wwb,XszzTyForm model,String[] data,String zxsrs,String pycc){
	
		String title = model.getXn()+"年度天津市普通本科高校、高等职业学校国家励志奖学金统计表";
		ExcelMB excel = new ExcelMB();
		WritableSheet ws = wwb.createSheet(pycc, 0);
	
		try {
			excel.printTitle(ws, title.toString(), 10, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
	
			//二级标题
			ws.mergeCells(0, 1, 1, 1);
			ws.addCell(new Label(0, 1,"学校名称：天津工业大学", wcfTytle));
			ws.mergeCells(5, 1, 7, 1);
			ws.addCell(new Label(5, 1,"填表日期：", wcfTytle));
	
			//第三行
			ws.mergeCells(0, 2, 0, 3);
			ws.mergeCells(1, 2, 1, 3);
			ws.addCell(new Label(0, 2,"学校", wcfTytle));
			ws.addCell(new Label(1, 2,"在校生人数", wcfTytle));
			ws.mergeCells(2, 2, 3, 2);
			ws.addCell(new Label(2, 2,"受奖励学生数（人）", wcfTytle));
			ws.addCell(new Label(2, 3,"合计", wcfTytle));
			ws.addCell(new Label(3, 3,"其中：农村", wcfTytle));
			
			ws.mergeCells(4, 2, 4, 3);
			ws.addCell(new Label(4, 2,"奖励金额（万元）", wcfTytle));
			
			ws.mergeCells(5, 2, 8, 2);
			ws.addCell(new Label(5, 2,"生源地人数（人）", wcfTytle));
			ws.addCell(new Label(5, 3,"东部", wcfTytle));
			ws.addCell(new Label(6, 3,"其中：天津", wcfTytle));
			ws.addCell(new Label(7, 3,"中部", wcfTytle));
			ws.addCell(new Label(8, 3,"西部", wcfTytle));
			
			ws.mergeCells(9, 2, 9, 3);
			ws.addCell(new Label(9, 2,"备注", wcfTytle));
			
			//数据输出
			ws.addCell(new Label(0, 4,"天津工业大学", wcfTytle));
			ws.addCell(new Label(1, 4,zxsrs, wcfTytle));
			for (int i = 0 ; i < data.length ; i++){
				ws.addCell(new Label(2+i, 4,data[i], wcfTytle));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 国家励志奖学金统计数据,附原生SQL
	 * 
	 * select sum(count) jlrs,
       sum(nchk) ncrs,
       sum(jlje) jlje,
       sum(dbdq) dbdq,
       sum(tjdq) tjdq,
       sum(zbdq) zbdq,
       sum(xbdq) xbdq,
       '' bz 
		from (
		     select 1 count,
		           case when c.jthk='农村' then 1 else 0 end nchk,
		           case when a.xmzzje <> '不涉及金额' then to_number(nvl(a.xmzzje,0)) else 0 end jlje,
		           case when d.dqmc='东部' or d.dqmc='天津' then 1 else 0 end dbdq,
		           case when d.dqmc='天津' then 1 else 0 end tjdq,
		           case when d.dqmc='中部' then 1 else 0 end zbdq,
		           case when d.dqmc='西部' then 1 else 0 end xbdq 
		     from gjlzjxj a 
		     left join xsxxb b on a.xh = b.xh 
		     left join (
		          select t.jthk,t.xh from jtqkdcb t where exists 
		          (select 1 from (select xh , max(sqsj) sqsj from  jtqkdcb group by xh) j where t.xh=j.xh and t.sqsj=j.sqsj)
		          ) c on a.xh = c.xh 
		     left join xg_xszz_dqfbb d on b.syd=d.dqdm
		)
	 */
	private String[] getLzjxTjData(XszzTyForm model,String pycc){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String[] outputArr = new String[]{"jlrs","ncrs","jlje","dbdq","tjdq","zbdq","xbdq","bz"};
		
		sql.append("select sum(count) jlrs,sum(nchk) ncrs,")
		   .append("sum(jlje) jlje,sum(dbdq) dbdq,")
		   .append("sum(tjdq) tjdq,sum(zbdq) zbdq,")
		   .append("sum(xbdq) xbdq,'' bz  ")
		   .append("from (select 1 count,")
		   .append("case when c.jthk='农村' then 1 else 0 end nchk,")
		   .append("case when a.xmzzje <> '不涉及金额' then to_number(nvl(a.xmzzje,0)) else 0 end jlje,")
		   .append("case when d.dqmc='东部' or d.dqmc='天津' then 1 else 0 end dbdq,")
		   .append("case when d.dqmc='天津' then 1 else 0 end tjdq,")
		   .append("case when d.dqmc='中部' then 1 else 0 end zbdq,")
		   .append("case when d.dqmc='西部' then 1 else 0 end xbdq ")
		   .append("from gjlzjxj a left join xsxxb b on a.xh = b.xh ")
		   .append("left join ( select t.jthk,t.xh from jtqkdcb t where exists ")
		   .append("(select 1 from (select xh , max(sqsj) sqsj from ")
		   .append(" jtqkdcb group by xh) j where t.xh=j.xh and t.sqsj=j.sqsj)")
		   .append(") c on a.xh = c.xh ")
		   .append("left join xg_xszz_dqfbb d on b.syd=d.dqdm ")
		   .append("where a.xn = ? ");
	
		if ("bks".equalsIgnoreCase(pycc)){
			//本科生
			sql.append(" and exists (select 1 from view_xsjbxx x where a.xh=x.xh and 4<=to_number(nvl(x.xz,0)))");
		} else if ("zks".equalsIgnoreCase(pycc)){
			//专科生
			sql.append(" and exists (select 1 from view_xsjbxx x where a.xh=x.xh and to_number(nvl(x.xz,0))<4)");
		}
		sql.append(")");
		
		return dao.getOneRs(sql.toString(), new String[]{model.getXn()}, outputArr);
	}
	
	
	
	/**
	 * 国家励志奖学金初审名单表
	 * @param wwb
	 * @param model
	 * @param data
	 * @param pycc
	 */
	private void printLzjxjHzb(WritableWorkbook wwb,XszzTyForm model,List<String[]> data,String pycc){
		
		String title = model.getXn()+"学年度普通本科高校、高等职业学校国家励志奖学金获奖学生初审名单表（"+pycc+"）";
		ExcelMB excel = new ExcelMB();
		WritableSheet ws = wwb.createSheet(pycc, 0);
	
		try {
			excel.printTitle(ws, title.toString(), 9, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
	
			//二级标题
			ws.mergeCells(0, 1, 2, 1);
			ws.addCell(new Label(0, 1,"学校名称：天津工业大学", wcfTytle));
			ws.mergeCells(5, 1, 8, 1);
			ws.addCell(new Label(5, 1,"填表日期：", wcfTytle));
			//第三行
			ws.addCell(new Label(0, 2,"序号", wcfTytle));
			ws.addCell(new Label(1, 2,"姓名", wcfTytle));
			ws.addCell(new Label(2, 2,"身份证号", wcfTytle));
			ws.addCell(new Label(3, 2,Base.YXPZXY_KEY, wcfTytle));
			ws.addCell(new Label(4, 2,"专业", wcfTytle));
			ws.addCell(new Label(5, 2,"学号", wcfTytle));
			ws.addCell(new Label(6, 2,"性别", wcfTytle));
			ws.addCell(new Label(7, 2,"民族", wcfTytle));
			ws.addCell(new Label(8, 2,"入学日期", wcfTytle));
			//数据输出
			for (int i = 0 ; i < data.size() ; i++){
				ws.addCell(new Label(0, 3+i,String.valueOf(i+1), wcfTytle));
				for (int j = 0 ; j < data.get(i).length ; j++){
					ws.addCell(new Label(1+j, 3+i,data.get(i)[j], wcfTytle));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 国家励志奖学金初审名单表数据
	 * @param model
	 * @param pycc
	 * @return
	 */
	private List<String[]> getLzjxjHzData(XszzTyForm model,String pycc){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String[] outputArr = new String[]{"xm","sfzh","xymc","zymc","xh","xb","mz","rxrq"};
		
		sql.append("select b.xm,b.sfzh,b.xymc,b.zymc,a.xh,b.xb,b.mz,c.rxrq from gjlzjxj a ")
		   .append("left join view_xsjbxx b on a.xh = b.xh ")
		   .append("left join xsxxb c on a.xh = c.xh ")
		   .append("where a.xn = ? ");
		
		if ("bks".equalsIgnoreCase(pycc)){
			//本科生
			sql.append(" and exists (select 1 from xsxxb x where a.xh=x.xh and 4<=to_number(nvl(x.xz,0)))");
		} else if ("zks".equalsIgnoreCase(pycc)){
			//专科生
			sql.append(" and exists (select 1 from xsxxb x where a.xh=x.xh and to_number(nvl(x.xz,0))<4)");
		}
		
		return dao.rsToVator(sql.toString(), new String[]{model.getXn()}, outputArr);
	}
}
