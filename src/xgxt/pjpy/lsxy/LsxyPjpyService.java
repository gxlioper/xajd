package xgxt.pjpy.lsxy;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.guizhdx.GuizhdxDAO;
import xgxt.pjpy.ntzydx.PjpyNtzydxDAO;
import xgxt.pjpy.tginterface.PjpyCommonInterface;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.pjpy.zjjd.PjpyZjjdDAO;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyDAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class LsxyPjpyService {
	
	/**
	 * ��ӡ�ۺ����ʲ������ܱ�
	 * @param os
	 * @param model
	 * */
	public void getZhszcphzb(OutputStream os,PjpyLsxyForm model){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		WritableWorkbook wwb = null;
		WritableSheet ws = null;
		String bnjzyrs = dao.getBnjzyrs(model.getBjdm());
		
		try {			
			//��ѯѧ���ۺ����ʲ���������Ϣ
			List<HashMap<String, String>> list = dao.selectXszhszcphzxx(model);			
			
			wwb = Workbook.createWorkbook(os);
			ws = wwb.createSheet("���ݵ���", 0);
			Label titleCell = new Label(0, 0, StandardOperation.getXxmc()+ "ѧ���ۺ����ʲ����ɼ����ܱ�");
			WritableCellFormat wcFormat = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.ARIAL, 18);
			font.setBoldStyle(WritableFont.BOLD);
//			font.setUnderlineStyle(UnderlineStyle.SINGLE);
			wcFormat.setFont(font);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setBackground(Colour.WHITE);
			
			//��ͷ
			ws.mergeCells(0, 0, 12, 0);
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
			//����ͷ
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL, 12);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcFormat.setBackground(Colour.WHITE);
			
			ws.addCell(new Label(0, 1, "",wcFormat));
			ws.addCell(new Label(1, 1, "",wcFormat));
			ws.addCell(new Label(2, 1, "",wcFormat));
			ws.addCell(new Label(3, 1, model.getXn() + "ѧ��",wcFormat));
			ws.addCell(new Label(4, 1, "��",wcFormat));
			ws.addCell(new Label(5, 1, model.getXqmc(),wcFormat));
			ws.addCell(new Label(6, 1, "ѧ��",wcFormat));
			ws.addCell(new Label(7, 1, "",wcFormat));
			ws.addCell(new Label(8, 1, "",wcFormat));
			ws.addCell(new Label(9, 1, "",wcFormat));
			ws.addCell(new Label(10, 1, "",wcFormat));
			ws.addCell(new Label(11, 1, "",wcFormat));
			ws.addCell(new Label(12, 1, "",wcFormat));
			
			//����
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL, 12);
			wcFormat.setFont(font);
			wcFormat.setBorder(Border.NONE, BorderLineStyle.NONE,Colour.WHITE);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcFormat.setBackground(Colour.WHITE);
			
			ws.mergeCells(0, 2, 2, 2);
			ws.addCell(new Label(0, 2, "����ѧԺ�����£�",wcFormat));
			ws.addCell(new Label(3, 2, "",wcFormat));
			ws.addCell(new Label(4, 2, "",wcFormat));
			ws.addCell(new Label(5, 2, "�༶��",wcFormat));
			ws.mergeCells(6, 2, 7, 2);
			ws.addCell(new Label(6, 2, model.getBjmc(),wcFormat));
			ws.addCell(new Label(7, 2, "",wcFormat));
			ws.addCell(new Label(8, 2, "",wcFormat));
			ws.addCell(new Label(9, 2,"ͬ�꼶ͬרҵ������",wcFormat));
			ws.addCell(new Label(10, 2, "",wcFormat));
			ws.addCell(new Label(11, 2, "",wcFormat));
			ws.addCell(new Label(12, 2,bnjzyrs,wcFormat));
			//���
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL, 10);
			wcFormat.setFont(font);
			wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			ws.mergeCells(0, 3, 0, 4);
			ws.addCell(new Label(0, 3,"ѧ��",wcFormat));
			ws.mergeCells(1, 3, 1, 4);
			ws.addCell(new Label(1, 3,"����",wcFormat));
			ws.mergeCells(2, 3, 5, 3);
			ws.addCell(new Label(2, 3,"�������ݼ���ֵ",wcFormat));
			ws.addCell(new Label(2, 4,"�� 20%",wcFormat));
			ws.addCell(new Label(3, 4,"�� 65%",wcFormat));
			ws.addCell(new Label(4, 4,"�� 5%",wcFormat));
			ws.addCell(new Label(5, 4,"�� 10%",wcFormat));
			ws.mergeCells(6, 3, 6, 4);
			ws.addCell(new Label(6, 3,"�ر�����",wcFormat));
			ws.mergeCells(7, 3, 9, 3);
			ws.addCell(new Label(7, 3,"�ۺ���������",wcFormat));
			ws.addCell(new Label(7, 4,"�ܳɼ�",wcFormat));
			ws.addCell(new Label(8, 4,"�༶",wcFormat));
			ws.addCell(new Label(9, 4,"ͬ�꼶ͬרҵ",wcFormat));
			ws.mergeCells(10, 3, 12, 3);
			ws.addCell(new Label(10, 3,"ѧϰ�ɼ�����",wcFormat));
			ws.addCell(new Label(10, 4,"�ܳɼ�",wcFormat));
			ws.addCell(new Label(11, 4,"�༶",wcFormat));
			ws.addCell(new Label(12, 4,"ͬ�꼶ͬרҵ",wcFormat));
			
			int row = 5;
			for(int i=0 ; i<list.size(); i++){
				HashMap<String, String> map = list.get(i);
				ws.addCell(new Label(0, row,map.get("xh"),wcFormat));
				ws.addCell(new Label(1, row,map.get("xm"),wcFormat));
				ws.addCell(new Label(2, row,map.get("dcj"),wcFormat));
				ws.addCell(new Label(3, row,map.get("zcj"),wcFormat));
				ws.addCell(new Label(4, row,map.get("tcj"),wcFormat));
				ws.addCell(new Label(5, row,map.get("nlf"),wcFormat));
				ws.addCell(new Label(6, row,map.get("tbjf"),wcFormat));
				ws.addCell(new Label(7, row,map.get("zf"),wcFormat));
				ws.addCell(new Label(8, row,map.get("zfpm"),wcFormat));
				ws.addCell(new Label(9, row,map.get("zyzfpm"),wcFormat));
				ws.addCell(new Label(10, row,map.get("cj"),wcFormat));
				ws.addCell(new Label(11, row,map.get("cjpm"),wcFormat));
				ws.addCell(new Label(12, row,map.get("zycjpm"),wcFormat));
				row++;
			}
			//ҳ��
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL, 12);
			wcFormat.setFont(font);
			wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcFormat.setBorder(Border.NONE, BorderLineStyle.NONE,Colour.WHITE);
			wcFormat.setBackground(Colour.WHITE);
			
			ws.mergeCells(0, row, 2, row);
			ws.addCell(new Label(0, row,"�����Σ�",wcFormat));
			ws.addCell(new Label(3, row,"����һʽ����, (�����Ρ�Ժϵ��ѧ������һ�ݣ�",wcFormat));
			ws.mergeCells(3, row, 8, row);
			ws.mergeCells(9, row, 12, row);			
			ws.addCell(new Label(9, row,"ѧ��������������",wcFormat));			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);// ������ͻ���
	}
	
	/**
	 * ��ȡ���˽�ѧ��������Ϣ
	 * @param jxjpdModel
	 * @return HashMap<String, String>
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjpdxx(JxjpdxxModel jxjpdModel){
		XsxxglService xsxx = new XsxxglService();
		HashMap<String, String> map = new HashMap<String, String>();
		//ѧ��������Ϣ
		map.putAll(xsxx.selectStuinfo(jxjpdModel.getXh()));
		
		if(Globals.XXDM_LSXY.equalsIgnoreCase(Base.xxdm)){			
			//��ˮѧԺ
			LsxyPjpyDAO dao = new LsxyPjpyDAO();
			map = dao.getJxjpdxx(jxjpdModel,map);
		}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			//�㽭�Ƽ�ѧԺ
			PjpyZjkjxyDAO zjkjDao = new PjpyZjkjxyDAO();
			map = zjkjDao.getJxjpdxx(jxjpdModel,map);
		}else if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			//��ְͨҵ��ѧ
			PjpyNtzydxDAO dao = new PjpyNtzydxDAO(); 			
			map = dao.getJxjpdxx(jxjpdModel,map);
		}		
		
		map.put("save_xh", map.get("xh"));
		
		return map;
	}
	
	/**
	 * ��ѧ�����������ж�
	 * @param jxjpdModel
	 * @param xmlx
	 * @return String
	 * */
	public HashMap<String, String> pdStuTjFlag(JxjpdxxModel jxjpdModel , String xmlx){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		List<HashMap<String, String>> tjList = dao.getTjList(jxjpdModel,xmlx);
		HashMap<String, String> zcMap = getJxjpdxx(jxjpdModel);
		HashMap<String, String> argMap = new HashMap<String, String>();
		String data = "0"; //�����ֶζ�Ӧ�۲���������
		String msg = "";//������Ϣ
		String value = "";
		boolean result = true;
		
		//�����������ѭ���ж���ȡ��Ӧ����		
		for (HashMap<String, String> temp : tjList) {		
			String tjzd = temp.get("tjzd");
			if ("wjcs".equals(tjzd)) {
				PjpyCommonInterface pjInterface = new PjpyCommonInterface();
				msg = "Υ�ʹ�������������!";
				argMap.put("xh", jxjpdModel.getXh());
				argMap.put("xn", jxjpdModel.getXn());
				data = pjInterface.queryStuCfxx(argMap).size()+"";
				result = isFhtj(temp.get("tjlx"), temp.get("tjz"), data);//�Ƚ�����
			} else if ("dkkccj".equals(tjzd)) {
				msg = "���Ƴɼ�����������!";
				argMap.put("xh", jxjpdModel.getXh());
				argMap.put("xn", jxjpdModel.getXn());				
				result = dao.getDkcj(argMap,temp,"");				
			} else if ("zhcpcj".equals(tjzd)) {
				msg = "�ۺϲ����ɼ�����������!";
				data = zcMap.get("zf");
				result = isFhtj(temp.get("tjlx"), temp.get("tjz"), data);//�Ƚ�����
			} else if ("zhcppm".equals(tjzd)) {
				msg = "�ۺ����ʲ����༶��������������!";
				jxjpdModel.setZfpmbl(temp.get("tjz"));
				zcMap = getJxjpdxx(jxjpdModel);
				data = zcMap.get("zfpm");
				value = zcMap.get("zfblmc");
				result = isFhtj(temp.get("tjlx"), value, data);//�Ƚ�����
			} else if ("zhcpzypm".equals(tjzd)) {
				msg = "�ۺ����ʲ���רҵ��������������!!";
				jxjpdModel.setZyzfpmbl(temp.get("tjz"));
				zcMap = getJxjpdxx(jxjpdModel);
				data = zcMap.get("zyzfpm");
				value = zcMap.get("zyzfblmc");
				result = isFhtj(temp.get("tjlx"), value, data);//�Ƚ�����
			} else if ("cj".equals(tjzd)) {
				msg = "ѧϰ�ɼ�����������!";
				data = zcMap.get("cj");
				result = isFhtj(temp.get("tjlx"), temp.get("tjz"), data);//�Ƚ�����
			} else if ("cjpm".equals(tjzd)) {
				msg = "ѧϰ�ɼ��༶��������������!";
				jxjpdModel.setCjpmbl(temp.get("tjz"));
				zcMap = getJxjpdxx(jxjpdModel);
				data = zcMap.get("cjpm");
				value = zcMap.get("cjblmc");
				result = isFhtj(temp.get("tjlx"), value, data);//�Ƚ�����
			} else if ("zycjpm".equals(tjzd)) {
				msg = "ѧϰ�ɼ�רҵ��������������!";
				jxjpdModel.setZycjpmbl(temp.get("tjz"));
				zcMap = getJxjpdxx(jxjpdModel);
				data = zcMap.get("zycjpm");
				value = zcMap.get("zycjblmc");
				result = isFhtj(temp.get("tjlx"), value, data);//�Ƚ�����
			} else if("kckcj".equalsIgnoreCase(tjzd)){
				msg = "����γɼ�����������!";
				result = dao.getDkcj(argMap,temp,"�����");	
			} else if("pjxfjd".equalsIgnoreCase(tjzd)){
				msg = "ƽ��ѧ�ּ��㲻��������!";
				data = zcMap.get("pjxfjd");
				result = isFhtj(temp.get("tjlx"), temp.get("tjz"), data);//�Ƚ�����
			}
			
			if (!result) {
				break;
			}
		}
		
		HashMap<String, String> tjgl = new HashMap<String, String>();
		
		tjgl.put("message", msg);
		tjgl.put("result", String.valueOf(result));
		
		return tjgl;
	}
	
	/**
	 * �ж����������Ƿ���������
	 * @param tjlx
	 * @param tjz
	 * @param data
	 * @return
	 */
	public boolean isFhtj(String tjlx,String tjz,String data) {
		
		if (Base.isNull(data)) {
			return false;
		}
		
		double dtjz = Double.valueOf(tjz);
		double ddata = Double.valueOf(data);
		
		if (">".equals(tjlx)) {
			return ddata > dtjz;
		} else if(">=".equals(tjlx)) {
			return ddata >= dtjz;
		} else if("=".equals(tjlx)) {
			return ddata == dtjz;
		} else if("<".equals(tjlx)) {
			return ddata < dtjz;
		} else if("<=".equals(tjlx)) {
			return ddata <= dtjz;
		}
		
		return false;
	}
	
	/**
	 * ��ѧ����������
	 * */
	public boolean checkJxjRsxz(HashMap<String, String> map){
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		jxjpdModel.setXh(map.get("xh"));
		jxjpdModel.setXn(map.get("xn"));
		jxjpdModel.setXydm(map.get("xydm"));
		jxjpdModel.setZydm(map.get("zydm"));
		jxjpdModel.setBjdm(map.get("bjdm"));
		jxjpdModel.setNj(map.get("nj"));
		jxjpdModel.setJxjdm(map.get("jxjdm"));
		
		//��ȡ��ǰѧԺ���������䷽ʽ
		String fpfs = dao.getJxjrsfpfs(jxjpdModel);
		int fprs = 0;
		int tgrs = 0;
		//��ѯ��������
		fprs = dao.getJxjFprs(jxjpdModel, fpfs);
		//��ѯͨ������
		tgrs = dao.getJxjShtgrs(jxjpdModel,fpfs);
		return fprs>tgrs || fprs == -1;
	}
	
	/**
	 * �����ƺ���������
	 * */
	public boolean checkRychRsxz(HashMap<String, String> map){
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		jxjpdModel.setXh(map.get("xh"));
		jxjpdModel.setXn(map.get("xn"));
		jxjpdModel.setXydm(map.get("xydm"));
		jxjpdModel.setZydm(map.get("zydm"));
		jxjpdModel.setBjdm(map.get("bjdm"));
		jxjpdModel.setNj(map.get("nj"));
		jxjpdModel.setJxjdm(map.get("jxjdm"));
		
		//��ȡ��ǰѧԺ���������䷽ʽ
		String fpfs = dao.getJxjrsfpfs(jxjpdModel);
		int fprs = 0;
		int tgrs = 0;
		//��ѯ��������
		fprs = dao.getJxjFprs(jxjpdModel, fpfs);
		//��ѯͨ������
		tgrs = dao.getRychShtgrs(jxjpdModel,fpfs);
		return fprs>tgrs || fprs == -1;
	}
	
	
	/**
	 * ��ȡѧ����ѧ����Ϣ��
	 * @param jxjpdModel
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsjxjb(JxjpdxxModel jxjpdModel){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		return dao.selectXsjxjb(jxjpdModel);
	}
	
	/**
	 * ����������ȡѧ�������ƺ���Ϣ��
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsrychbByPk(String pkValue){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		return dao.selectXsrychbByPk(pkValue);
	}
	
	
	/**
	 * ��ȡѧ���ɼ���Ϣ��
	 * @param jxjpdModel
	 * @param kclx
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXscjb(JxjpdxxModel jxjpdModel, String kclx){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		return dao.selectXscjb(jxjpdModel, kclx);
	}
	
	/**
	 * ��ȡѧ�������ƺ���Ϣ��
	 * @param jxjpdModel
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsrychb(JxjpdxxModel jxjpdModel){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		return dao.selectXsrychb(jxjpdModel);
	}
	
	/**
	 * �ж��Ƿ��ȡ�˽�ѧ��
	 * @param jxjpdModel
	 * @return boolean
	 * */
	public boolean getSfhdjxj(JxjpdxxModel jxjpdModel){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		int count = dao.selectXsjxjbCount(jxjpdModel);
		return count > 0;
	}
	
	/**
	 * �ɼ���ϸ��Ϣ��ѯ
	 * @param viewName
	 * @param outputColumn
	 * @param pkValue
	 * */
	public List<String[]> queryXscjOne(String viewName, 
										String[] outputColumn,
										String pkValue){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		return dao.selectXscjbOne(viewName,outputColumn,pkValue);
	}
}
