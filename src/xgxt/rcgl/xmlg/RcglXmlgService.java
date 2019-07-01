package xgxt.rcgl.xmlg;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;

public class RcglXmlgService {
	
	//获得表头
	public List<HashMap<String,String>> getTableTop(String lb){
		String[] ens = null;
		String[] cns = null;
		DAO dao = DAO.getInstance();
		if("cqtj".equals(lb)){
			ens = new String[]{"pk","xn","xq","xymc","jcsj","ydrs","sdrs","qjrs","kkrs","cql"};
			cns = new String[]{"pk","学年","学期",Base.YXPZXY_KEY+"名称","检查时间","应到人数","实到人数","请假人数","旷课人数","出勤率"};
		}
		if("kkqk".equals(lb)){
			ens = new String[]{"pk","xh","xm","xn","xq","nj","xymc","zymc","bjmc","kkxs","jycfdj"};
			cns = new String[]{"pk","学号","姓名","学年","学期","年级",Base.YXPZXY_KEY+"名称","专业名称","班级名称","旷课学时","建议处分等级"};
		}
		return dao.arrayToList(ens, cns);
	}
	//本系,跨系申请保存
	public boolean saveBxjthdsq_ser(RcglForm form,String lb){
		RcglDAO dao = new RcglDAO();
		return dao.saveBxjthdsq_db(form,lb);
	}
	
	//查询出勤统计
	public List<HashMap<String,String>> queryCqtj_ser(RcglForm form){
		RcglDAO dao = new RcglDAO();
		return dao.queryCqtj_db(form);
	}
	
	//增加出勤统计
	public boolean addCqtj_ser(RcglForm form){
		RcglDAO dao = new RcglDAO();
		return dao.addCqtj_db(form);
	}
	
	//获得出勤统计修改信息
	public HashMap<String,String> getCqtjUpdateXx_ser(String pk){
		RcglDAO dao = new RcglDAO();
		return dao.getCqtjUpdateXx_db(pk);
	}
	
	//保存出勤统计修改信息
	public boolean saveCqtjUpdateXx_ser(String pk,RcglForm form){
		RcglDAO dao = new RcglDAO();
		return dao.saveCqtjUpdateXx_db(pk, form);
	}
	
	//删除出勤统计信息
	public boolean deleteCqtjXx_ser(String pks){
		RcglDAO dao = new RcglDAO();
		return dao.deleteCqtjXx_db(pks);
	}
	
	//出勤统计报表
	public void cjtjPrint_ser(String jcsj,OutputStream os) throws Exception{
		RcglDAO dao = new RcglDAO();
		HashMap<String,String> map = new HashMap<String,String>();
		int qxydrs = 0;
		int qxsdrs = 0;
		int qxqjrs = 0;
		int qxkkrs = 0;
		String qxcql = "";
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("日出勤统计表", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
		String jcsj_cn = jcsj.substring(0, 4)+"年"+jcsj.substring(4, 6)+"月"+jcsj.substring(6)+"日"; 
		ws.mergeCells(0, 0, 5, 1);
		mb.printToOneCell_multy(ws,jcsj_cn+"学生出勤统计表", 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		mb.printToOneCell_multy(ws, "系别", 0, 2, 11, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "应到学生数", 1, 2, 11, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "实到学生数", 2, 2, 11, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "请假人数", 3, 2, 11, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "旷课人数", 4, 2, 11, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "出勤率", 5, 2, 11, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		List<HashMap<String,String>> list = dao.cqtjPrint_db(jcsj);
		for(int i=0;i<list.size();i++){
			map = list.get(i);
			ws.addCell(new Label(0, 3+i, map.get("xymc"),wcf));
			ws.addCell(new Label(1, 3+i, map.get("ydrs"),wcf));
			ws.addCell(new Label(2, 3+i, map.get("sdrs"),wcf));
			ws.addCell(new Label(3, 3+i, map.get("qjrs"),wcf));
			ws.addCell(new Label(4, 3+i, map.get("kkrs"),wcf));
			ws.addCell(new Label(5, 3+i, map.get("cql"),wcf));
			qxydrs += Integer.parseInt(map.get("ydrs"));
			qxsdrs += Integer.parseInt(map.get("sdrs"));
			qxqjrs += Base.isNull(map.get("qjrs"))? 0 :Integer.parseInt(map.get("qjrs"));
			qxkkrs += Base.isNull(map.get("kkrs"))? 0 :Integer.parseInt(map.get("kkrs"));
		}
		if(list != null && list.size() > 0){
			qxcql = Math.round((Float.parseFloat(qxsdrs+"")*10000/qxydrs))+"";
			qxcql = Float.parseFloat(qxcql)/100+"%";
			mb.printToOneCell_multy(ws, "全校合计", 0, 3+list.size(), 10, true, Alignment.CENTRE,
					VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, qxydrs+"", 1, 3+list.size(), 10, true, Alignment.CENTRE,
					VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, qxsdrs+"", 2, 3+list.size(), 10, true, Alignment.CENTRE,
					VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, qxqjrs+"", 3, 3+list.size(), 10, true, Alignment.CENTRE,
					VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, qxkkrs+"", 4, 3+list.size(), 10, true, Alignment.CENTRE,
					VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, qxcql, 5, 3+list.size(), 10, true, Alignment.CENTRE,
					VerticalAlignment.CENTRE, false, 300, Border.ALL);
		}
		wwb.write();
		wwb.close();
	}
	
}
