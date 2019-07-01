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
	
	//��ñ�ͷ
	public List<HashMap<String,String>> getTableTop(String lb){
		String[] ens = null;
		String[] cns = null;
		DAO dao = DAO.getInstance();
		if("cqtj".equals(lb)){
			ens = new String[]{"pk","xn","xq","xymc","jcsj","ydrs","sdrs","qjrs","kkrs","cql"};
			cns = new String[]{"pk","ѧ��","ѧ��",Base.YXPZXY_KEY+"����","���ʱ��","Ӧ������","ʵ������","�������","��������","������"};
		}
		if("kkqk".equals(lb)){
			ens = new String[]{"pk","xh","xm","xn","xq","nj","xymc","zymc","bjmc","kkxs","jycfdj"};
			cns = new String[]{"pk","ѧ��","����","ѧ��","ѧ��","�꼶",Base.YXPZXY_KEY+"����","רҵ����","�༶����","����ѧʱ","���鴦�ֵȼ�"};
		}
		return dao.arrayToList(ens, cns);
	}
	//��ϵ,��ϵ���뱣��
	public boolean saveBxjthdsq_ser(RcglForm form,String lb){
		RcglDAO dao = new RcglDAO();
		return dao.saveBxjthdsq_db(form,lb);
	}
	
	//��ѯ����ͳ��
	public List<HashMap<String,String>> queryCqtj_ser(RcglForm form){
		RcglDAO dao = new RcglDAO();
		return dao.queryCqtj_db(form);
	}
	
	//���ӳ���ͳ��
	public boolean addCqtj_ser(RcglForm form){
		RcglDAO dao = new RcglDAO();
		return dao.addCqtj_db(form);
	}
	
	//��ó���ͳ���޸���Ϣ
	public HashMap<String,String> getCqtjUpdateXx_ser(String pk){
		RcglDAO dao = new RcglDAO();
		return dao.getCqtjUpdateXx_db(pk);
	}
	
	//�������ͳ���޸���Ϣ
	public boolean saveCqtjUpdateXx_ser(String pk,RcglForm form){
		RcglDAO dao = new RcglDAO();
		return dao.saveCqtjUpdateXx_db(pk, form);
	}
	
	//ɾ������ͳ����Ϣ
	public boolean deleteCqtjXx_ser(String pks){
		RcglDAO dao = new RcglDAO();
		return dao.deleteCqtjXx_db(pks);
	}
	
	//����ͳ�Ʊ���
	public void cjtjPrint_ser(String jcsj,OutputStream os) throws Exception{
		RcglDAO dao = new RcglDAO();
		HashMap<String,String> map = new HashMap<String,String>();
		int qxydrs = 0;
		int qxsdrs = 0;
		int qxqjrs = 0;
		int qxkkrs = 0;
		String qxcql = "";
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("�ճ���ͳ�Ʊ�", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
		String jcsj_cn = jcsj.substring(0, 4)+"��"+jcsj.substring(4, 6)+"��"+jcsj.substring(6)+"��"; 
		ws.mergeCells(0, 0, 5, 1);
		mb.printToOneCell_multy(ws,jcsj_cn+"ѧ������ͳ�Ʊ�", 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		mb.printToOneCell_multy(ws, "ϵ��", 0, 2, 11, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "Ӧ��ѧ����", 1, 2, 11, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "ʵ��ѧ����", 2, 2, 11, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "�������", 3, 2, 11, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "��������", 4, 2, 11, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "������", 5, 2, 11, true, Alignment.CENTRE,
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
			mb.printToOneCell_multy(ws, "ȫУ�ϼ�", 0, 3+list.size(), 10, true, Alignment.CENTRE,
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
