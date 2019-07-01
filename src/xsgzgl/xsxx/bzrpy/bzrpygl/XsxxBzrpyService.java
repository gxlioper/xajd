package xsgzgl.xsxx.bzrpy.bzrpygl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.SaveForm;
import xsgzgl.xsxx.bzrpy.BasicModel;

public class XsxxBzrpyService extends CommService{

	XsxxBzrpyDAO dao=new XsxxBzrpyDAO();
	
	/**
	 * ͨ�ò�ѯ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getBasicList(BasicModel model) throws Exception{

		return dao.getBasicList(model);
	}
	
	/**
	 * ���ݴ���� ����ֵ��ʽ���ֶ���Ϣ���б���
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean saveInfo(BasicModel model){
		
		return dao.saveInfo(model);
	}
	

	/**
	 * ���ݴ���� ����ֵ��ʽ���ֶ���Ϣ���б���
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean updateInfo(BasicModel model){
		
		return dao.updateInfo(model);
	}
	

	/**
	 * ���ݴ���� ����ֵ��ʽ���ֶ���Ϣ���б���
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 * @throws Exception 
	 */
	public boolean saveBatch(BasicModel model,Object saveModel) throws Exception{
		
		// �������ݲ��� �ı���
		String realTable=model.getTableName();
		
		String[] pkValue =model.getPkValue();
		
		String[]pk=model.getPk();
		
		String[] arrzd = model.getArrayZd();
		
		String[] onezd = model.getOneZd();
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(getString(pk));
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		return saveInfoToDb(saveForm, saveModel, model.getUser());
	
	}
	
	public String getString(String[]array){
		
		StringBuilder str=new StringBuilder();
		
		for(int i=0;i<array.length;i++){
			if(i!=0){
				
				str.append("||'!!@@!!'||");
			}
			str.append(array[i]);
		}
		
		return str.toString();
	}
	
	
	/**
	 * ��ʾ�ֶ�����Div
	 * 
	 * @author qlj
	 * 
	 * @throws IOException
	 */
	public void showDiv(XsxxBzrpyModel model,HttpServletResponse response) throws IOException {

		HashMap<String,String>bzrpyMap=new HashMap<String,String>();
		
		String xh=model.getXh();
		
		String pylx=model.getPylx();
		
		if(!Base.isNull(xh)){
			
			bzrpyMap=dao.getOneBzrpy(model);
			
		}
		
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>����������</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("����<br/>");
		html.append("<font color=\"red\">(������500����)</font>");
		html.append("</th>");
		html.append("<td>");
		
		html.append(" <input type=\"hidden\" name=\"div_xh\" id=\"div_xh\" value=\""+xh+"\" />");
		html.append(" <textArea name=\"pyyj\" id=\"pyyj\" onblur=\"chLeng(this,500);\" ");
		html.append(" rows='4' style='word-break:break-all;width:96%' >");
		html.append(Base.isNull(bzrpyMap.get("pyyj"))?"":bzrpyMap.get("pyyj"));
		html.append("</textArea>");
		

		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");
		
		String writeAble=model.getWriteAble();
		
		if("yes".equalsIgnoreCase(writeAble)){
			html.append("<button type=\"button\"  id=\"btn_bc\" ");
			
			if("plpy".equalsIgnoreCase(pylx)){
				html.append("onclick=\"savePlpy();return false;");
			}else{
				
				html.append("onclick=\"saveDgpy('"+(Base.isNull(bzrpyMap.get("pyr"))?"":bzrpyMap.get("pyr"))+"');return false;");
			}
			
			
			html.append(" \">");
			html.append("ȷ ��");
			html.append("</button>");
		}
		
		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
		html.append("�� ��");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");

		html.append("</table>");
		html.append("</div>");

		response.getWriter().print(html.toString());
	}
	
	/**
	 * ��ȡ��ǰѧ�������ֹʱ��
	 * @return
	 */
	public HashMap<String,String>getBzrpyJzsj(){
		
		return dao.getBzrpyJzsj();
	}
}
