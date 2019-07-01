package xgxt.comm.chart.imp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

/**
 *图表统计数据处理和效果设置
 * <P>
 * 提供饼图、单一键柱状图、双键柱状图数据处理
 * 和 饼图、单一键柱状图的样式设置，双键柱状图
 * 的图片效果还未添加（与单一键柱状图有所区别需要单独处理）。
 * 若现有的数据处理方法不能满足您的需要，建议覆盖
 * </p>
 * <P>
 * 为了保证一个系统内所设置的图片效果一致
 * 不建议覆盖setCategoryPlot 和 setPiePlot
 * </P>
 * <p>Author: 屈朋辉</p>
 * <p>Version: 1.0</p>
 */
public class AbstractDrawChart {

	/**
	 * 获取饼状图的数据源
	 * 
	 * @param data
	 *            HashMap<String,String> 键 (key:String) ,
	 *            值 (value:Number 只要能转换为double类型的数据即可)
	 * @return DefaultPieDataset
	 */
	protected static DefaultPieDataset getPieDataset(List<HashMap<String, String>> data) {
	
		DefaultPieDataset pieDataset = new DefaultPieDataset();
	
		for (HashMap<String, String> map : data) {
	
			if (StringUtils.isNotNull(map.get("key"))) {
				pieDataset.setValue(map.get("key"), Double.valueOf(map
						.get("value")));
			}
		}
	
		return pieDataset;
	}

	/**
	 * 柱状图数据源
	 * 
	 * @param values
	 *            数字，需要可转换为double
	 * @param rowKeys
	 *            行名
	 * @param colKeys
	 *            列名
	 * @return DefaultCategoryDataset
	 * @throws Exception
	 */
	protected static DefaultCategoryDataset getCategoryDataset(String[] values, String[] rowKeys, String[] colKeys) throws Exception {
	
		if (null == values || null == rowKeys || null == colKeys
				|| values.length != rowKeys.length
				|| values.length != colKeys.length) {
			throw new Exception("柱状图数据源传入参数有误！");
		}
	
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
		for (int i = 0; i < values.length; i++) {
			dataset.addValue(Double.valueOf(values[i]), rowKeys[i], colKeys[i]);
		}
	
		return dataset;
	}

	/**
	 * 柱状图数据源
	 * @param data
	 * 		HashMap<String,String> 键 (key:String) ,值 (value:Number，需要可转换为double)
	 * @return DefaultCategoryDataset
	 * @throws Exception
	 */
	protected static DefaultCategoryDataset getCategoryDataset(List<HashMap<String, String>> data) throws Exception {
	
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
		for (HashMap<String, String> map : data) {
	
			if (StringUtils.isNotNull(map.get("key"))) {
				dataset.setValue(Double.valueOf(map.get("value")), map
						.get("key"), "");
			}
	
		}
	
		return dataset;
	}


	/**
	 * 
	 * 柱状图的图片效果
	 * <strong>不建议覆盖<strong>
	 * @param chart
	 */
	protected void setCategoryPlot(JFreeChart chart) {
		CategoryPlot plot = chart.getCategoryPlot();
	
		//柱状图效果 
		plot.setBackgroundPaint(Color.WHITE);//背景颜色
		plot.setRangeGridlinePaint(Color.gray);//虚线的颜色
		plot.setForegroundAlpha(0.8f);//透明度
		plot.setOrientation(PlotOrientation.VERTICAL);//数据区的方向
		
		
		TextTitle t = chart.getTitle();
		t.setFont(new Font("黑体", Font.PLAIN, 20));// 标题文字
		
		CategoryAxis domainAxis = plot.getDomainAxis();// x轴
		ValueAxis numberAxis = plot.getRangeAxis(); // y轴
	
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);// X轴上文字角度
		domainAxis.setLabelFont(new Font("黑体", Font.PLAIN, 20));// x轴标题文字
		domainAxis.setTickLabelFont(new Font("黑体", Font.PLAIN, 12));// x轴坐标上文字
		numberAxis.setLabelFont(new Font("黑体", Font.PLAIN, 20));// y轴标题文字
		numberAxis.setTickLabelFont(new Font("黑体", Font.PLAIN, 12));// y轴坐标上文字
		chart.getLegend().setItemFont(new Font("黑体", Font.PLAIN, 12));// 图例文字
		
		BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
		renderer.setDrawBarOutline(false);// 设置柱子边框可见
		renderer.setMaximumBarWidth(0.04000000000000001D);// 设置柱的最大宽度
		renderer.setItemMargin(0.10000000000000001D);
		renderer.setWallPaint(Color.WHITE);//外围的颜色
		
		//图片的Map 数据提示{0} 类型1 {1}类型2 {2}数字 {3}比例，这里类型2设为空 
		renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator(
				"{0}:({2} → {3})", NumberFormat.getIntegerInstance(),new DecimalFormat("0.00%")));
	
		renderer.setBaseItemLabelsVisible(true);
		plot.setNoDataMessage("没有可显示数据!");
		//setBackgroundImage(plot); 背景图片
		if ("10466".equals(Base.xxdm)) {
			for (int i = 0 ; i < plot.getDataset().getRowCount() ; i++) {
				renderer.setSeriesItemLabelGenerator(i,
						new StandardCategoryItemLabelGenerator("{2}",
								NumberFormat.getIntegerInstance(), new DecimalFormat("0.00%")));
			}
			//数值显示在柱子外,最后一个参数是数值的角度 0D是180°方向
			ItemLabelPosition itemLabelPositionFallback=new ItemLabelPosition(
				          ItemLabelAnchor.OUTSIDE12,TextAnchor.BASELINE_LEFT,
				          TextAnchor.HALF_ASCENT_LEFT,0D);
			renderer.setBasePositiveItemLabelPosition(itemLabelPositionFallback);
			CategoryAxis categoryaxis = plot.getDomainAxis();
			categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		}
		/*
		 * 以下是直接在图片上显示数据提示的相关代码,这里不使用(请勿删除！！！)
		 * 
		 * for (int i = 0 ; i < plot.getDataset().getRowCount() ; i++) {
			renderer.setSeriesItemLabelGenerator(i,
					new StandardCategoryItemLabelGenerator("{3}",
							NumberFormat.getIntegerInstance(), new DecimalFormat("0.00%")));
		}
		ItemLabelPosition itemlabelposition = new ItemLabelPosition(
				ItemLabelAnchor.INSIDE12, TextAnchor.CENTER_RIGHT,
				TextAnchor.CENTER_RIGHT, -1.5707963267948966D);
		renderer.setBasePositiveItemLabelPosition(itemlabelposition);
		CategoryAxis categoryaxis = plot.getDomainAxis();
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		*/
		
	}

	

	
	/**
	 * 饼状图效果
	 * <strong>不建议覆盖<strong>
	 * @param chart
	 */
	protected void setPiePlot(JFreeChart chart) {
		/* 图片效果* */
		TextTitle textTitle = chart.getTitle(); // 标题
		textTitle.setFont(new Font("黑体", Font.PLAIN, 20)); // 标题文字乱码

		PiePlot plot = (PiePlot) chart.getPlot();
		// 数据提示{0}:类型 {1}:数字 {2}:比例
		
		//图片显示时显示的数据提示
		 plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
		 "{0}({1}={2})", NumberFormat.getNumberInstance(),
			new DecimalFormat("0.00%")));
		// 鼠标移动到区域时显示的区域提示
//		plot.setToolTipGenerator(new StandardPieToolTipGenerator(
//				"{0}:({1}={2})", NumberFormat.getNumberInstance(),
//				new DecimalFormat("0.00%")));
		//plot.setLabelGenerator(null);// 图中非Map提示设为空
//		
		//轮廓的颜色
		plot.setBaseSectionOutlinePaint(Color.BLACK);  
	    plot.setBaseSectionPaint(Color.BLACK); 
		 
		
		// 图片透明度
		plot.setForegroundAlpha(0.8f);
		// 饼上的文字乱码
		plot.setLabelFont(new Font("黑体", Font.PLAIN, 14));
		// 数据区背景透明度
		plot.setBackgroundAlpha(0.7f);
		// 数据区背景颜色
		plot.setBackgroundPaint(Color.WHITE);
		// 数据块分隔线
//		plot.setAutoPopulateSectionOutlinePaint(false);
		// 忽略无值的分类
		plot.setIgnoreNullValues(true);
		//设置图片边框颜色
		plot.setOutlinePaint(Color.white);
		
		plot.setNoDataMessage("没有可显示数据!");
		
		// plot.setURLGenerator(new StandardPieURLGenerator("#"));//设定链接
		// plot.setLabelGap(0.02D);
		// setBackgroundImage(plot);// 背景图片
		// 图例文字乱码
		chart.getLegend().setItemFont(new Font("黑体", Font.PLAIN, 12));
	}
	
	
	/**
	 * 为图表设置背景图片，美工不建议使用背景图片。
	 * 此方法暂时不使用
	 * @param chart
	 * @param path
	 */
	public void setBackgroundImage(JFreeChart chart, String path) {
		
		Image image = null;
		File file = new File(path);
		Plot plot = chart.getPlot();
		
		if (file.exists()) {
			try {
				image = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		if (null != image) {
			plot.setBackgroundImage(image);
		}
	}
}