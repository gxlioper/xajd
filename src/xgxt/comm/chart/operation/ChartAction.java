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
	 * 学生信息基本图表统计
	 * @author Penghui Qu
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward getStuChart(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ChartForm chartForm = (ChartForm) form;
		ChartService service = new ChartService();

		//默认统计字段性别
		if (StringUtils.isNull(chartForm.getTjzd())) {
			chartForm.setTjzd("xb");
		}
		
		//默认统计类别为饼图
		if (StringUtils.isNull(chartForm.getTjlb())) {
			chartForm.setTjlb("pie");
		}
		
		/*不知道为什么会出现乱码，以下转码可以解决*/
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
	 * 把生成图片的相关信息放在request里传到jsp。
	 * <br/>
	 * 具体设入值有 url：图片路径 fileName：文件名 imageMap：图片焦点map
	 * @param request
	 * @param response
	 * @param chart 生成的jfreeChart
	 * @param model 留着当参数设置入口
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
		
        //保存为JPEG图片，PNG图片在gbk编码下IE6不能正常显示map数据提示
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
