package xgxt.wjcf.fjgc;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.form.ShgcForm;
import xgxt.utils.ExcelMethods;
import xgxt.wjcf.WjcfUtil;

public class WjcfFjgcService {
	
	public void wjcfHztj(ShgcForm shgcForm,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		DecimalFormat df=new DecimalFormat("##0.0000");
		WjcfUtil wjcfUtil =new WjcfUtil();
		//����KSSJ,JSSJ�Զ����� ѧ���б�
		List<HashMap<String,String>>xnlist=wjcfUtil.wjcfXnList(shgcForm);
		//����ԭ���б�
		shgcForm.setKsxn(xnlist.get(0).get("xn"));
		shgcForm.setJsxn(xnlist.get(xnlist.size()-1).get("xn"));
		List<HashMap<String,String>>cfyyList=wjcfUtil.wjcfXxList();
		//ѧԺ�б�
		List<HashMap<String,String>>xyList=wjcfUtil.wjcfXyList();
		//���ֻ�������
		List<HashMap<String,String>>wjzrsList=wjcfUtil.wjzrsList();
		//����������Ŀ����
		List<HashMap<String,String>>wjrsList=wjcfUtil.wjrsList();
		//
		List<HashMap<String,String>>xywjrsList=wjcfUtil.xywjrsList(shgcForm);
		//ѧԺ����
		HashMap<String,String>xyrsMap=wjcfUtil.xyrsList();
		//ѧԺ ����ԭ������
		List<HashMap<String,String>>cfrsList=wjcfUtil.cfrsList(shgcForm);
		
		List<HashMap<String,String>>xwjxsList=wjcfUtil.xwjxsList(shgcForm);
		
		List<HashMap<String,String>>wjyyrsList=wjcfUtil.wjyyrsList(shgcForm);
		
		HashMap<String,String>xxZrs=wjcfUtil.getZrs();
		//����п��
		int colsLen=(xnlist.size()*2+1)*(cfyyList.size()*2+1)+2;
		try {
			// ����xls��SHEET����
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// ���ö��뷽ʽ
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcfTytle.setFont(wfTytle);
			//�ϲ���Ԫ��(��ͷ)
			ws.mergeCells(0, 0,colsLen-1, 0);
			ws.addCell(new Label(0,0,"��������ѧԺ"+shgcForm.getKsnf()+"-"+shgcForm.getJsnf()+"ѧ��ѧ��Υ�ʹ������ͳ�Ʊ�",wcfTytle));
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
//			 ���ñ��߿�
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
	

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			ws.mergeCells(0, 1,0, 2);
			ws.addCell(new Label(0,1,"���",wcfTytle));
			
			ws.mergeCells(1, 1,1, 2);
			ws.addCell(new Label(1,1,"ϵ��",wcfTytle));
			
			int cfyyinit=2;//����Ŀ���Ƴ�ʼλ��
			ws.mergeCells(cfyyinit, 1,cfyyinit+xnlist.size()*2, 1);
			ws.addCell(new Label(cfyyinit,1,"Υ��ѧ������",wcfTytle));
			cfyyinit=cfyyinit+xnlist.size()*2+1;
			//����Ŀ���Ƶ�Ԫ��ϲ�
			for(int i=0;i<cfyyList.size();i++){
				//cfyyinit+xnlist.size()*2+1 �ӳ�ʼλ������ ѡ���ѧ����е�ѧ����(xnlist.size()*2),1Ϊ����
				HashMap<String,String>cfyyMap=cfyyList.get(i);
				ws.mergeCells(cfyyinit, 1,cfyyinit+xnlist.size()*2, 1);
				//����Ŀ������ʾ
				ws.addCell(new Label(cfyyinit,1,cfyyMap.get("mc"),wcfTytle));
				cfyyinit=cfyyinit+xnlist.size()*2+1;
			}

			ws.mergeCells(cfyyinit, 1,cfyyinit+xnlist.size()*2-1, 1);
			ws.addCell(new Label(cfyyinit,1,"��Υ����",wcfTytle));
			
			cfyyinit=cfyyinit+xnlist.size()*2;
			for(int i=0;i<cfyyList.size();i++){
				//cfyyinit+xnlist.size()*2+1 �ӳ�ʼλ������ ѡ���ѧ����е�ѧ����(xnlist.size()*2),1Ϊ����
				HashMap<String,String>cfyyMap=cfyyList.get(i);
				ws.mergeCells(cfyyinit, 1,cfyyinit+xnlist.size()*2-1, 1);
				//����Ŀ������ʾ
				ws.addCell(new Label(cfyyinit,1,cfyyMap.get("mc")+"��",wcfTytle));
				cfyyinit=cfyyinit+xnlist.size()*2;
			}
			
			int xninit=2;//ѧ��λ�ó�ʼ��
			for(int i=0;i<cfyyList.size()+1;i++){
				for(int j=0;j<xnlist.size();j++){
					HashMap<String,String>xnMap=xnlist.get(j);
					ws.addCell(new Label(xninit,2,xnMap.get("xn")+"ѧ�괺ѧ��",wcfTytle));
					ws.addCell(new Label(xninit+1,2,xnMap.get("xn")+"ѧ����ѧ��",wcfTytle));
					xninit+=2;
				}
				ws.addCell(new Label(xninit,2,"�ܼ�",wcfTytle));
				xninit+=1;
			}
			
			for(int i=0;i<cfyyList.size()+1;i++){
				for(int j=0;j<xnlist.size();j++){
					HashMap<String,String>xnMap=xnlist.get(j);
					ws.addCell(new Label(xninit,2,xnMap.get("xn")+"ѧ�괺ѧ��",wcfTytle));
					ws.addCell(new Label(xninit+1,2,xnMap.get("xn")+"ѧ����ѧ��",wcfTytle));
					xninit+=2;
				}
			}
			
			for(int i=0;i<=xyList.size();i++){
				for(int j=2;j<=colsLen;j++){
					if(j<((xnlist.size()*2+1)*(cfyyList.size()+1)+2)){
					ws.addCell(new Label(j,i+3,"0",wcfTytle));
					}else{
						ws.addCell(new Label(j,i+3,"0.00%",wcfTytle));
					}
				}
			}
			
			int cols=2;
			//������
			for(int i = 0; i<xyList.size();i++){
				//��ѧԺ�б�Ϊ��,����ǰ��3��
				HashMap<String,String>xyMap=xyList.get(i);
				//���
				ws.addCell(new Label(0,i+3,""+(i+1),wcfTytle));
				//ϵ��
				ws.addCell(new Label(1,i+3,""+(xyMap.get("mc")),wcfTytle));
				
				for(int j = 0; j<wjzrsList.size();j++){
					cols=2;
						for(int n=0;n<xnlist.size();n++){
							HashMap<String,String>xnMap=xnlist.get(n);
							HashMap<String,String>wjzrsMap=wjzrsList.get(j);
							if(wjzrsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
									&& xnMap.get("xqone").equalsIgnoreCase(wjzrsMap.get("xq"))
									&& xyMap.get("dm").equalsIgnoreCase(wjzrsMap.get("xydm"))){
								ws.addCell(new Label(cols,i+3,wjzrsMap.get("rs"),wcfTytle));
							}else if(wjzrsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
									&& xnMap.get("xqtwo").equalsIgnoreCase(wjzrsMap.get("xq"))
									&& xyMap.get("dm").equalsIgnoreCase(wjzrsMap.get("xydm"))){
								ws.addCell(new Label(cols+1,i+3,wjzrsMap.get("rs"),wcfTytle));
							}
							cols+=2;
						}
					
				}
				
				//ѧԺ����������
				for(int j=0;j<xywjrsList.size();j++){
					HashMap<String,String>xywjrsMap=xywjrsList.get(j);
					cols=2+xnlist.size()*2;
					if( xyMap.get("dm").equalsIgnoreCase(xywjrsMap.get("xydm"))){
						ws.addCell(new Label(cols,i+3,xywjrsMap.get("rs"),wcfTytle));
					}
				}
				
				//����ͳ��
				for(int z=0;z<cfyyList.size();z++){
					
					HashMap<String,String>cfyyMap=cfyyList.get(z);
					for(int j = 0; j<wjrsList.size();j++){
						HashMap<String,String>wjrsMap=wjrsList.get(j);
						cols=3+xnlist.size()*2+z*(xnlist.size()*2+1);
						for(int n=0;n<xnlist.size();n++){
							HashMap<String,String>xnMap=xnlist.get(n);
							if(wjrsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
									&& cfyyMap.get("dm").equalsIgnoreCase(wjrsMap.get("cfyy"))
									&& xnMap.get("xqone").equalsIgnoreCase(wjrsMap.get("xq"))
									&& xyMap.get("dm").equalsIgnoreCase(wjrsMap.get("xydm"))){
								ws.addCell(new Label(cols,i+3,wjrsMap.get("rs"),wcfTytle));
							}else if(wjrsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
									&& cfyyMap.get("dm").equalsIgnoreCase(wjrsMap.get("cfyy"))
									&& xnMap.get("xqtwo").equalsIgnoreCase(wjrsMap.get("xq"))
									&& xyMap.get("dm").equalsIgnoreCase(wjrsMap.get("xydm"))){
								ws.addCell(new Label(cols+1,i+3,wjrsMap.get("rs"),wcfTytle));
							}
							cols+=2;
						}
					}
				}
				
				for(int j=0;j<cfyyList.size();j++){
					HashMap<String,String>cfyyMap=cfyyList.get(j);
					for(int z = 0; z<cfrsList.size();z++){
						HashMap<String,String>cfrsMap=cfrsList.get(z);
						cols=2+2*xnlist.size()+(j+1)*(2*xnlist.size()+1);
						if(xyMap.get("dm").equalsIgnoreCase(cfrsMap.get("xydm"))
								&& cfyyMap.get("dm").equalsIgnoreCase(cfrsMap.get("cfyy"))){
							ws.addCell(new Label(cols,i+3,cfrsMap.get("rs"),wcfTytle));
						}
					}
				}
				
				//��Υ�ʹ�����
				for(int j = 0; j<wjzrsList.size();j++){
					cols=(xnlist.size()*2+1)*(cfyyList.size()+1)+2;
						for(int n=0;n<xnlist.size();n++){
							HashMap<String,String>xnMap=xnlist.get(n);
							HashMap<String,String>wjzrsMap=wjzrsList.get(j);
							if(wjzrsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
									&& xnMap.get("xqone").equalsIgnoreCase(wjzrsMap.get("xq"))
									&& xyMap.get("dm").equalsIgnoreCase(wjzrsMap.get("xydm"))){
								float xyrs=Float.parseFloat(xyrsMap.get(xyMap.get("dm")));
								float rs=Float.parseFloat(wjzrsMap.get("rs"));
								ws.addCell(new Label(cols,i+3,""+df.format((rs/xyrs*100))+"%",wcfTytle));
							}else if(wjzrsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
									&& xnMap.get("xqtwo").equalsIgnoreCase(wjzrsMap.get("xq"))
									&& xyMap.get("dm").equalsIgnoreCase(wjzrsMap.get("xydm"))){
								float xyrs=Float.parseFloat(xyrsMap.get(xyMap.get("dm")));
								float rs=Float.parseFloat(wjzrsMap.get("rs"));
								ws.addCell(new Label(cols+1,i+3,""+df.format((rs/xyrs*100))+"%",wcfTytle));
							}
							cols+=2;
						}
					
				}
				
				
				//Υ�ʹ�����
				for(int z=0;z<cfyyList.size();z++){
					
					HashMap<String,String>cfyyMap=cfyyList.get(z);
					for(int j = 0; j<wjrsList.size();j++){
						HashMap<String,String>wjrsMap=wjrsList.get(j);
						cols=2+xnlist.size()*2+z*(xnlist.size()*2)+(xnlist.size()*2+1)*(cfyyList.size()+1);
						for(int n=0;n<xnlist.size();n++){
							HashMap<String,String>xnMap=xnlist.get(n);
							if(wjrsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
									&& cfyyMap.get("dm").equalsIgnoreCase(wjrsMap.get("cfyy"))
									&& xnMap.get("xqone").equalsIgnoreCase(wjrsMap.get("xq"))
									&& xyMap.get("dm").equalsIgnoreCase(wjrsMap.get("xydm"))){
								float xyrs=Float.parseFloat(xyrsMap.get(xyMap.get("dm")));
								float rs=Float.parseFloat(wjrsMap.get("rs"));
								ws.addCell(new Label(cols,i+3,""+df.format((rs/xyrs*100))+"%",wcfTytle));
							}else if(wjrsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
									&& cfyyMap.get("dm").equalsIgnoreCase(wjrsMap.get("cfyy"))
									&& xnMap.get("xqtwo").equalsIgnoreCase(wjrsMap.get("xq"))
									&& xyMap.get("dm").equalsIgnoreCase(wjrsMap.get("xydm"))){
								float xyrs=Float.parseFloat(xyrsMap.get(xyMap.get("dm")));
								float rs=Float.parseFloat(wjrsMap.get("rs"));
								ws.addCell(new Label(cols+1,i+3,""+df.format((rs/xyrs*100))+"%",wcfTytle));
							}
							cols+=2;
						}
					}
				}
			}
			
			ws.addCell(new Label(1,xyList.size()+3,"ȫУ�ϼ�",wcfTytle));
			
			//�������ĺϼ�
			cols=2;
			int sumRs=0;
			for(int j=0;j<xnlist.size();j++){
				HashMap<String,String>xnMap=xnlist.get(j);
					for(int z=0;z<xwjxsList.size();z++){
						HashMap<String,String>xwjxsMap=xwjxsList.get(z);
						if(xwjxsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
								&& xwjxsMap.get("xq").equalsIgnoreCase(xnMap.get("xqone"))){
							sumRs+=Integer.parseInt(xwjxsMap.get("rs"));
							ws.addCell(new Label(cols,xyList.size()+3,""+xwjxsMap.get("rs"),wcfTytle));
						}else if(xwjxsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
								&& xwjxsMap.get("xq").equalsIgnoreCase(xnMap.get("xqtwo"))){
							ws.addCell(new Label(cols+1,xyList.size()+3,""+xwjxsMap.get("rs"),wcfTytle));
							sumRs+=Integer.parseInt(xwjxsMap.get("rs"));
						}
						
					}
					cols+=2;
			}
			ws.addCell(new Label(2+xnlist.size()*2,xyList.size()+3,""+sumRs,wcfTytle));
			
			//
			int cfyyrs=0;
			for(int z=0;z<cfyyList.size();z++){
				
				HashMap<String,String>cfyyMap=cfyyList.get(z);
				for(int j = 0; j<wjyyrsList.size();j++){
					HashMap<String,String>wjyyrsMap=wjyyrsList.get(j);
					cols=3+xnlist.size()*2+z*(xnlist.size()*2+1);
					for(int n=0;n<xnlist.size();n++){
						HashMap<String,String>xnMap=xnlist.get(n);
						if(wjyyrsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
								&& cfyyMap.get("dm").equalsIgnoreCase(wjyyrsMap.get("cfyy"))
								&& xnMap.get("xqone").equalsIgnoreCase(wjyyrsMap.get("xq"))){
							cfyyrs+=Integer.parseInt(wjyyrsMap.get("rs"));
							ws.addCell(new Label(cols,xyList.size()+3,""+wjyyrsMap.get("rs"),wcfTytle));
						}else if(wjyyrsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
								&& cfyyMap.get("dm").equalsIgnoreCase(wjyyrsMap.get("cfyy"))
								&& xnMap.get("xqtwo").equalsIgnoreCase(wjyyrsMap.get("xq"))){
							cfyyrs+=Integer.parseInt(wjyyrsMap.get("rs"));
							ws.addCell(new Label(cols+1,xyList.size()+3,""+wjyyrsMap.get("rs"),wcfTytle));
						}
						cols+=2;
					}
					ws.addCell(new Label(cols,xyList.size()+3,""+cfyyrs,wcfTytle));
				}
				cfyyrs=0;
			}
			
			//��������
			cols=2;
			cfyyrs=0;
			float cfrs=0.0f;
			int zrs=Integer.parseInt(xxZrs.get("rs"));
			cols=(xnlist.size()*2+1)*(cfyyList.size()+1)+2;
			for(int j=0;j<xnlist.size();j++){
				HashMap<String,String>xnMap=xnlist.get(j);
					for(int z=0;z<xwjxsList.size();z++){
						HashMap<String,String>xwjxsMap=xwjxsList.get(z);
						if(xwjxsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
								&& xwjxsMap.get("xq").equalsIgnoreCase(xnMap.get("xqone"))){
							sumRs+=Integer.parseInt(xwjxsMap.get("rs"));
							cfrs=Float.parseFloat(xwjxsMap.get("rs"))/zrs;
							ws.addCell(new Label(cols,xyList.size()+3,""+df.format(cfrs*100)+"%",wcfTytle));
						}else if(xwjxsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
								&& xwjxsMap.get("xq").equalsIgnoreCase(xnMap.get("xqtwo"))){
							sumRs+=Integer.parseInt(xwjxsMap.get("rs"));
							cfrs=Float.parseFloat(xwjxsMap.get("rs"))/zrs;
							ws.addCell(new Label(cols+1,xyList.size()+3,""+df.format(cfrs*100)+"%",wcfTytle));
						}
						
					}
					cols+=2;
			}
			
			
			for(int z=0;z<cfyyList.size();z++){
				
				HashMap<String,String>cfyyMap=cfyyList.get(z);
				for(int j = 0; j<wjyyrsList.size();j++){
					HashMap<String,String>wjyyrsMap=wjyyrsList.get(j);
					cols=2+xnlist.size()*2+z*(xnlist.size()*2)+(xnlist.size()*2+1)*(cfyyList.size()+1);
					for(int n=0;n<xnlist.size();n++){
						HashMap<String,String>xnMap=xnlist.get(n);
						if(wjyyrsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
								&& cfyyMap.get("dm").equalsIgnoreCase(wjyyrsMap.get("cfyy"))
								&& xnMap.get("xqone").equalsIgnoreCase(wjyyrsMap.get("xq"))){
							cfrs=Float.parseFloat(wjyyrsMap.get("rs"))/zrs;
							ws.addCell(new Label(cols,xyList.size()+3,""+df.format(cfrs)+"%",wcfTytle));
						}else if(wjyyrsMap.get("xn").equalsIgnoreCase(xnMap.get("xn"))
								&& cfyyMap.get("dm").equalsIgnoreCase(wjyyrsMap.get("cfyy"))
								&& xnMap.get("xqtwo").equalsIgnoreCase(wjyyrsMap.get("xq"))){
							cfrs=Float.parseFloat(wjyyrsMap.get("rs"))/zrs;
							ws.addCell(new Label(cols+1,xyList.size()+3,""+df.format(cfrs*100)+"%",wcfTytle));
						}
						cols+=2;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
