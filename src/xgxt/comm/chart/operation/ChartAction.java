package xgxt.comm.chart.operation;

import java.awt.Rectangle;
import java.awt.Shape;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.servlet.ServletUtilities;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;

public class ChartAction extends BasicAction {

	/**
	 * ѧ����Ϣ����ͼ��ͳ��
	 * @author Penghui Qu
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward getStuChart(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ChartForm chartForm = (ChartForm) form;
		ChartService service = new ChartService();

		//Ĭ��ͳ���ֶ��Ա�
		if (StringUtils.isNull(chartForm.getTjzd())) {
			chartForm.setTjzd("xb");
		}
		
		//Ĭ��ͳ�����Ϊ��ͼ
		if (StringUtils.isNull(chartForm.getTjlb())) {
			chartForm.setTjlb("pie");
		}
		
		/*��֪��Ϊʲô��������룬����ת����Խ��*/
		/*if (StringUtils.isNotNull(chartForm.getSearchModel().getInput_mhcx())) {
			chartForm.getSearchModel().setInput_mhcx(new String(chartForm.getSearchModel().getInput_mhcx().getBytes("gbk"),"utf-8"));
		}*/
		
		
		JFreeChart chart = service.getChart(chartForm);
		
		setUrl(request,response, chart, chartForm);
		request.setAttribute("path", "chart.do?method=getStuChart");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("searchTj", chartForm.getSearchModel());
		return mapping.findForward("stuChart");
	}

	
	
	/**
	 * ������ͼƬ�������Ϣ����request�ﴫ��jsp��
	 * <br/>
	 * ��������ֵ�� url��ͼƬ·�� fileName���ļ��� imageMap��ͼƬ����map
	 * @param request
	 * @param response
	 * @param chart ���ɵ�jfreeChart
	 * @param model ���ŵ������������
	 * @throws IOException
	 */
	private void setUrl(HttpServletRequest request, HttpServletResponse response,JFreeChart chart,
			ChartForm model) throws IOException {

		int height = 400;
		int width = 780;
		
		  
		response.setContentType("text/html;charset=gbk");   
		PrintWriter writer = response.getWriter();    
		
		Shape shape = new Rectangle(10, 5);   
        ChartEntity entity = new ChartEntity(shape);   
        StandardEntityCollection coll = new StandardEntityCollection(); 
        coll.add(entity);   
        ChartRenderingInfo info = new ChartRenderingInfo(coll);   
		
        //����ΪJPEGͼƬ��PNGͼƬ��gbk������IE6����������ʾmap������ʾ
		String fileName = ServletUtilities.saveChartAsJPEG(chart, width, height,info,
				request.getSession());
		ChartUtilities.writeImageMap(writer, fileName, info, true);   
		
		
		String url = request.getContextPath() + "/DisplayChart?filename="
				+ fileName;

		String imageMap = ChartUtilities.getImageMap(fileName, info);  
		request.setAttribute("url", url);
		request.setAttribute("fileName", fileName);
		request.setAttribute("imageMap", imageMap);
	}
}
