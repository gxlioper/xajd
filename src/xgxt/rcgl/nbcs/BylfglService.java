package xgxt.rcgl.nbcs;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class BylfglService {
	BylfglDAO dao = new BylfglDAO();
	
	/**
	 * ��ȡ��������
	 * @param String tableName
	 * @param String[] col
	 * @return String[]
	 * */
	public String[] getColCN(String tableName,String[] col){
		return dao.getColumnNameCN(col, tableName);
	}
	
	/**
	 * ��ȡ��ҵ���������Ϣ��ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getBylfsqTopTr(){
		String[] colList = {"����","nd","xymc","sqsj","xxsh","shrxm","shsj"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_bylfglb");
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * ��ȡ��ҵ����黹��Ϣ��ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getBylfghdjTopTr(){
		String[] colList = {"����","nd","xymc","shfz","shmaozi","shlingdai","shlingjie","pcje"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_bylfglb");
		return dao.arrayToList(colList, colListCN);
	}
	
	
	
	/**
	 * ��ѯѧԺ��ҵ���������Ϣ
	 * @param BylfglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryBylfglb(BylfglForm model){
		return dao.selectBylfglb(model,null);
	}
	
	/**
	 * ��ѯѧԺ��ҵ����黹��Ϣ
	 * @param BylfglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryBylfglbGhxx(BylfglForm model){
		String[] col = {"pk","nd","xymc","shfz","shmaozi","shlingdai","shlingjie","pcje"};
		return dao.selectBylfglb(model,col);
	}
	
	/**
	 * ��ѯѧԺ��ҵ�����Ϣ����
	 * @param BylfglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryBylfglbForExp(BylfglForm model){
		return dao.selectBylfglbForExp(model);
	}
	
	
	/**
	 * ����������ѯѧԺ��ҵ�����Ϣ
	 * @param BylfglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryBylfglbOne(BylfglForm model){
		return dao.selectBylfglbOne(model);
	}
	
	/**
	 * ����ѧԺ��ҵ���������Ϣ
	 * @param BylfglForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveBylfglbSq(BylfglForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "bylfglb";
		String primaryKey = "nd||xydm";
		String pkValue = model.getNd()+model.getXydm();
		String[] columns = {"nd","xydm","lqr","bkfxl","bkfl","bkfm","bkfs","zkfxl","zkfl","zkfm","zkfs","xzfxl","xzfl","xzfm","xzfs","dsfxl","dsfl","dsfm","dsfs","maozi","pijian","lingdai","lingjie"};
		String[] values = {model.getNd(),model.getXydm(),model.getLqr(),model.getBkfxl(),model.getBkfl(),model.getBkfm(),model.getBkfs(),model.getZkfxl(),model.getZkfl(),model.getZkfm(),model.getZkfs(),model.getXzfxl(),model.getXzfl(),model.getXzfm(),model.getXzfs(),model.getDsfxl(),model.getDsfl(),model.getDsfm(),model.getDsfs(),model.getMaozi(),model.getPijian(),model.getLingdai(),model.getLingjie()};
		
		if(dao.checkExists(tableName, primaryKey,pkValue )){//��¼�Ѿ�����			
			result = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
		}else{
			result = StandardOperation.insert(tableName, columns, values, request);
		}		
		return result;
	}
	
	/**
	 * ����ѧԺ��ҵ������������Ϣ
	 * @param BylfglForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveBylfglbSh(BylfglForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "bylfglb";
		String primaryKey = "nd||xydm";
		String pkValue = model.getNd()+model.getXydm();
		String[] columns = {"xxsh","shryhm","shsj"};
		String[] values = {model.getXxsh(),model.getShryhm(),model.getShsj()};
		
		result = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);	
		return result;
	}
	
	/**
	 * ��ҵ����黹�Ǽ���Ϣ����
	 * @param BylfglForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveBylfghdj(BylfglForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "bylfglb";
		String primaryKey = "nd||xydm";
		String pkValue = model.getNd()+model.getXydm();
		String[] columns = {"ghbkfxl","ghbkfl","ghbkfm","ghbkfs","ghzkfxl","ghzkfl","ghzkfm","ghzkfs","ghxzfxl","ghxzfl","ghxzfm","ghxzfs","ghdsfxl","ghdsfl","ghdsfm","ghdsfs","ghmaozi","ghpijian","ghlingdai","ghlingjie","shfz","shmaozi","shlingjie","shlingdai","pcje"};
		String[] values = {model.getGhbkfxl(),model.getGhbkfl(),model.getGhbkfm(),model.getGhbkfs(),model.getGhzkfxl(),model.getGhzkfl(),model.getGhzkfm(),model.getGhzkfs(),model.getGhxzfxl(),model.getGhxzfl(),model.getGhxzfm(),model.getGhxzfs(),model.getGhdsfxl(),model.getGhdsfl(),model.getGhdsfm(),model.getGhdsfs(),model.getGhmaozi(),model.getGhpijian(),model.getGhlingdai(),model.getGhlingjie(),model.getShfz(),model.getShmaozi(),model.getShlingjie(),model.getShlingdai(),model.getPcje()};
		
		result = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);	
		return result;
	}
	
	
	/**
	 * ��������ѧԺ������������Ϣ
	 * @param BylfglForm model
	 * @return boolean
	 * */
	public boolean saveBylfglbShBatch(BylfglForm model){
		String[] pkV = model.getPkV();
		String[] sqlArr = new String[pkV.length];
		boolean result = true;
		
		for(int i=0; i<pkV.length; i++){
			if(StringUtils.isNotNull(pkV[i])){
				sqlArr[i] = "update bylfglb set xxsh='" + model.getXxsh()+ "',shryhm='" + model.getShryhm() + "',shsj='" + model.getShsj() + "' where nd||xydm='"  + pkV[i] + "'";
			}
		}
		
		try{
			dao.runBatch(sqlArr);
		}catch(Exception ex){
			ex.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	
	/**
	 * ɾ��ѧ����ҵ���������Ϣ
	 * @param BylfglForm model
	 * @return boolean
	 * */
	public boolean delBylfglb(BylfglForm model){
		String[] pkV = model.getPkV();
		String[] sqlArr = new String[pkV.length];
		boolean result = true;
		
		for(int i=0; i<pkV.length; i++){
			if(StringUtils.isNotNull(pkV[i])){
				sqlArr[i] = "delete from bylfglb where nd||xydm='"  + pkV[i] + "'";
			}
		}
		
		try{
			dao.runBatch(sqlArr);
		}catch(Exception ex){
			ex.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	/**
	 * ��ӡ��ҵ����黹һ����
	 * @param OutputStream os
	 * @param BylfglForm model
	 * @throws Exception 
	 * */
	public void printBylfghllb(OutputStream os,BylfglForm model) throws Exception{
		String nd = model.getNd();	
		model.setNd(StringUtils.isNull(nd) ? Base.currNd : nd) ;
		
		List<HashMap<String, String>> list = dao.selectBylfghForAll(model);
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("ѧλ���黹���һ����", 0);
		try{
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);
			
			ws.mergeCells(0, 0, 19, 0);
			ws.addCell(new Label(0,0,model.getNd()+"���ѧλ���黹���һ����",wcfTytle));
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			ws.mergeCells(0, 1, 0, 2);
			ws.addCell(new Label(0,1,Base.YXPZXY_KEY,wcfTytle));
			ws.mergeCells(1, 1, 4, 1);
			ws.addCell(new Label(1,1,"���Ʒ�",wcfTytle));
			ws.addCell(new Label(1,2,"xl",wcfTytle));
			ws.addCell(new Label(2,2,"l",wcfTytle));
			ws.addCell(new Label(3,2,"m",wcfTytle));
			ws.addCell(new Label(4,2,"s",wcfTytle));
			
			ws.mergeCells(5, 1, 8, 1);			
			ws.addCell(new Label(5,1,"ר�Ʒ�",wcfTytle));
			ws.addCell(new Label(5,2,"xl",wcfTytle));
			ws.addCell(new Label(6,2,"l",wcfTytle));
			ws.addCell(new Label(7,2,"m",wcfTytle));
			ws.addCell(new Label(8,2,"s",wcfTytle));
			
			ws.mergeCells(9, 1, 12, 1);
			ws.addCell(new Label(9,1,"У����",wcfTytle));
			ws.addCell(new Label(9,2,"xl",wcfTytle));
			ws.addCell(new Label(10,2,"l",wcfTytle));
			ws.addCell(new Label(11,2,"m",wcfTytle));
			ws.addCell(new Label(12,2,"s",wcfTytle));
			
			ws.mergeCells(13, 1, 16, 1);
			ws.addCell(new Label(13,1,"��ʦ��",wcfTytle));
			ws.addCell(new Label(13,2,"xl",wcfTytle));
			ws.addCell(new Label(14,2,"l",wcfTytle));
			ws.addCell(new Label(15,2,"m",wcfTytle));
			ws.addCell(new Label(16,2,"s",wcfTytle));
			
			ws.mergeCells(17, 1, 17, 2);
			ws.addCell(new Label(17,1,"ñ��",wcfTytle));
			ws.mergeCells(18, 1, 18, 2);
			ws.addCell(new Label(18,1,"���",wcfTytle));
			ws.mergeCells(19, 1, 19, 2);
			ws.addCell(new Label(19,1,"���",wcfTytle));
			
			if(list.size()>0){
				DecimalFormat df = new DecimalFormat("0.00");
				int shfzs = 0;
				int shmaozis = 0;
				int shlingjies = 0;
				int shlingdais = 0;
				float pcjeZs = 0;
				for(int i=0; i<list.size(); i++){				
					HashMap<String, String> map = list.get(i);
					
					shfzs += Integer.parseInt(StringUtils.isNull(map.get("shfz")) ? "0" : map.get("shfz"));
					shmaozis += Integer.parseInt(StringUtils.isNull(map.get("shmaozi")) ? "0" : map.get("shmaozi"));
					shlingjies += Integer.parseInt(StringUtils.isNull(map.get("shlingjie")) ? "0" : map.get("shlingjie"));
					shlingdais += Integer.parseInt(StringUtils.isNull(map.get("shlingdai")) ? "0" : map.get("shlingdai"));
					pcjeZs += Integer.parseInt(StringUtils.isNull(map.get("pcje")) ? "0" : map.get("pcje"));
					
					ws.addCell(new Label(0,3+i,map.get("xymc"),wcfTytle));
					ws.addCell(new Label(1,3+i,map.get("ghbkfxl"),wcfTytle));
					ws.addCell(new Label(2,3+i,map.get("ghbkfl"),wcfTytle));
					ws.addCell(new Label(3,3+i,map.get("ghbkfm"),wcfTytle));
					ws.addCell(new Label(4,3+i,map.get("ghbkfs"),wcfTytle));
					ws.addCell(new Label(5,3+i,map.get("ghzkfxl"),wcfTytle));
					ws.addCell(new Label(6,3+i,map.get("ghzkfl"),wcfTytle));
					ws.addCell(new Label(7,3+i,map.get("ghzkfm"),wcfTytle));
					ws.addCell(new Label(8,3+i,map.get("ghzkfs"),wcfTytle));
					ws.addCell(new Label(9,3+i,map.get("ghxzfxl"),wcfTytle));
					ws.addCell(new Label(10,3+i,map.get("ghxzfl"),wcfTytle));
					ws.addCell(new Label(11,3+i,map.get("ghxzfm"),wcfTytle));
					ws.addCell(new Label(12,3+i,map.get("ghxzfs"),wcfTytle));
					ws.addCell(new Label(13,3+i,map.get("ghdsfxl"),wcfTytle));
					ws.addCell(new Label(14,3+i,map.get("ghdsfl"),wcfTytle));
					ws.addCell(new Label(15,3+i,map.get("ghdsfm"),wcfTytle));
					ws.addCell(new Label(16,3+i,map.get("ghdsfs"),wcfTytle));
					ws.addCell(new Label(17,3+i,map.get("ghmaozi"),wcfTytle));
					ws.addCell(new Label(18,3+i,map.get("ghlingjie"),wcfTytle));
					ws.addCell(new Label(19,3+i,map.get("ghlingdai"),wcfTytle));
				}
				wcfTytle = new WritableCellFormat();
				alignMent = Alignment.LEFT;
				vag = VerticalAlignment.CENTRE;
				 
				wcfTytle.setVerticalAlignment(vag);
				wcfTytle.setAlignment(alignMent);
				
				wfTytle = new WritableFont(WritableFont.ARIAL);
				wfTytle.setPointSize(12);
				wcfTytle.setFont(wfTytle);
				wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
				
				//д�ϼ�
				ws.mergeCells(0, 3+list.size(), 19, 3+list.size());
				ws.addCell(new Label(0,3+list.size(),"�𻵣���װ" + shfzs + "����ñ��" + shmaozis + "�������" + shlingjies + " ������� " + shlingdais + "����",wcfTytle));
				ws.mergeCells(0, 3+list.size()+1, 19, 3+list.size()+1);
				ws.addCell(new Label(0,3+list.size()+1,"���ϼƣ� " + df.format(pcjeZs) + " Ԫ",wcfTytle));
			}
			//��ͻ������
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
