package xsgzgl.pjpy.general.tjfx;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;

public class HjxsmdService extends BasicService {
	private HjxsmdDao hjxsmdDao = new HjxsmdDao();

	public List<HashMap<String, String>> getXyList() {
		// TODO 自动生成方法存根
		DAO dao =DAO.getInstance();
		String sql="select distinct xydm,xymc from xg_view_pjpy_xyhjtj";
		return dao.getList(sql, new String[]{}, new String[]{"xydm","xymc"});
	}

	public List<XyHjxsmdModel> getXyHjxsmdList(
			List<HashMap<String, String>> xyList, HjxsmdForm myform) throws Exception {
		// TODO 自动生成方法存根
		String xydm;
		List<XyHjxsmdModel> rsList=new ArrayList<XyHjxsmdModel>();
		for (HashMap<String, String> hashMap : xyList) {
			xydm=hashMap.get("xydm");
			XyHjxsmdModel xyHjxsmdModel=getXyHjxsmdModel(xydm,myform);
			xyHjxsmdModel.setXymc(hashMap.get("xymc"));
			rsList.add(xyHjxsmdModel);
		}
		return rsList;
	}

	private XyHjxsmdModel getXyHjxsmdModel(String xydm, HjxsmdForm myform) throws Exception {
		// TODO 自动生成方法存根
		String[] pjxms=hjxsmdDao.getxmList();
		XyHjxsmdModel xyHjxsmdModel = new XyHjxsmdModel();
		HashMap<String, String[]> map= new HashMap<String, String[]>();
		for (String string : pjxms) {
			String[] xms=hjxsmdDao.getXmlist(xydm,string,myform);
			map.put(string, xms);
		}
		xyHjxsmdModel.setXsList(map);
		return xyHjxsmdModel;
	}

	public void exportJxjfwmdNewData(List<XyHjxsmdModel> xyHjxsmdList,
			OutputStream ops, HjxsmdForm myform,HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		WritableWorkbook wwb =ExcelMethods.getWritableWorkbook(response.getOutputStream());
		try{
			WritableSheet ws=wwb.createSheet("11", 0);
			WritableCellFormat wcFormate=new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;
			//格式
			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			//字体
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcFormate.setFont(wfTytle);
			ws.mergeCells(0, 0,7, 3);
			ws.addCell(new Label(0,0,Base.xxmc+myform.getXn()+"学年获奖学生名单",wcFormate));
			wcFormate = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;
			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			wcFormate.setBorder(Border.ALL, BorderLineStyle.THIN);
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcFormate.setFont(wfTytle);
			wcFormate = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			wcFormate.setWrap(true);
//			 设置表格边框
			//wcFormate.setBorder(Border.ALL, BorderLineStyle.THIN);
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcFormate.setFont(wfTytle);
			int j=0;
			for (XyHjxsmdModel xyHjxsmdModel : xyHjxsmdList) {
				 String xymc=xyHjxsmdModel.getXymc();
				 ws.mergeCells(0, 4+j, 1, 4+j);
				 ws.addCell(new Label(0,4+j,"学院："+xymc,wcFormate));
				 HashMap<String, String[]> map=xyHjxsmdModel.getXsList();
				 Set<String> key=map.keySet();
				 for (String string : key) {
					String[] xms=map.get(string);
					ws.mergeCells(1, 5+j, 2, 5+j);
					ws.addCell(new Label(1,5+j,string,wcFormate));
					int i=0;
					for (String xm : xms) {
						if(3+i>7){
							j++;
							i=0;
						}
						ws.addCell(new Label(1+i,6+j,xm,wcFormate));
						i++;
					}
					j+=2;
				}
				 j+=2;
				 
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			wwb.write();
			wwb.close();
		}
	}
}
