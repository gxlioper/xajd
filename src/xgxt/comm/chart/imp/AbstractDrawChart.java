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
 *ͼ��ͳ�����ݴ����Ч������
 * <P>
 * �ṩ��ͼ����һ����״ͼ��˫����״ͼ���ݴ���
 * �� ��ͼ����һ����״ͼ����ʽ���ã�˫����״ͼ
 * ��ͼƬЧ����δ��ӣ��뵥һ����״ͼ����������Ҫ����������
 * �����е����ݴ�������������������Ҫ�����鸲��
 * </p>
 * <P>
 * Ϊ�˱�֤һ��ϵͳ�������õ�ͼƬЧ��һ��
 * �����鸲��setCategoryPlot �� setPiePlot
 * </P>
 * <p>Author: �����</p>
 * <p>Version: 1.0</p>
 */
public class AbstractDrawChart {

	/**
	 * ��ȡ��״ͼ������Դ
	 * 
	 * @param data
	 *            HashMap<String,String> �� (key:String) ,
	 *            ֵ (value:Number ֻҪ��ת��Ϊdouble���͵����ݼ���)
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
	 * ��״ͼ����Դ
	 * 
	 * @param values
	 *            ���֣���Ҫ��ת��Ϊdouble
	 * @param rowKeys
	 *            ����
	 * @param colKeys
	 *            ����
	 * @return DefaultCategoryDataset
	 * @throws Exception
	 */
	protected static DefaultCategoryDataset getCategoryDataset(String[] values, String[] rowKeys, String[] colKeys) throws Exception {
	
		if (null == values || null == rowKeys || null == colKeys
				|| values.length != rowKeys.length
				|| values.length != colKeys.length) {
			throw new Exception("��״ͼ����Դ�����������");
		}
	
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
		for (int i = 0; i < values.length; i++) {
			dataset.addValue(Double.valueOf(values[i]), rowKeys[i], colKeys[i]);
		}
	
		return dataset;
	}

	/**
	 * ��״ͼ����Դ
	 * @param data
	 * 		HashMap<String,String> �� (key:String) ,ֵ (value:Number����Ҫ��ת��Ϊdouble)
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
	 * ��״ͼ��ͼƬЧ��
	 * <strong>�����鸲��<strong>
	 * @param chart
	 */
	protected void setCategoryPlot(JFreeChart chart) {
		CategoryPlot plot = chart.getCategoryPlot();
	
		//��״ͼЧ�� 
		plot.setBackgroundPaint(Color.WHITE);//������ɫ
		plot.setRangeGridlinePaint(Color.gray);//���ߵ���ɫ
		plot.setForegroundAlpha(0.8f);//͸����
		plot.setOrientation(PlotOrientation.VERTICAL);//�������ķ���
		
		
		TextTitle t = chart.getTitle();
		t.setFont(new Font("����", Font.PLAIN, 20));// ��������
		
		CategoryAxis domainAxis = plot.getDomainAxis();// x��
		ValueAxis numberAxis = plot.getRangeAxis(); // y��
	
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);// X�������ֽǶ�
		domainAxis.setLabelFont(new Font("����", Font.PLAIN, 20));// x���������
		domainAxis.setTickLabelFont(new Font("����", Font.PLAIN, 12));// x������������
		numberAxis.setLabelFont(new Font("����", Font.PLAIN, 20));// y���������
		numberAxis.setTickLabelFont(new Font("����", Font.PLAIN, 12));// y������������
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));// ͼ������
		
		BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
		renderer.setDrawBarOutline(false);// �������ӱ߿�ɼ�
		renderer.setMaximumBarWidth(0.04000000000000001D);// �������������
		renderer.setItemMargin(0.10000000000000001D);
		renderer.setWallPaint(Color.WHITE);//��Χ����ɫ
		
		//ͼƬ��Map ������ʾ{0} ����1 {1}����2 {2}���� {3}��������������2��Ϊ�� 
		renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator(
				"{0}:({2} �� {3})", NumberFormat.getIntegerInstance(),new DecimalFormat("0.00%")));
	
		renderer.setBaseItemLabelsVisible(true);
		plot.setNoDataMessage("û�п���ʾ����!");
		//setBackgroundImage(plot); ����ͼƬ
		if ("10466".equals(Base.xxdm)) {
			for (int i = 0 ; i < plot.getDataset().getRowCount() ; i++) {
				renderer.setSeriesItemLabelGenerator(i,
						new StandardCategoryItemLabelGenerator("{2}",
								NumberFormat.getIntegerInstance(), new DecimalFormat("0.00%")));
			}
			//��ֵ��ʾ��������,���һ����������ֵ�ĽǶ� 0D��180�㷽��
			ItemLabelPosition itemLabelPositionFallback=new ItemLabelPosition(
				          ItemLabelAnchor.OUTSIDE12,TextAnchor.BASELINE_LEFT,
				          TextAnchor.HALF_ASCENT_LEFT,0D);
			renderer.setBasePositiveItemLabelPosition(itemLabelPositionFallback);
			CategoryAxis categoryaxis = plot.getDomainAxis();
			categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		}
		/*
		 * ������ֱ����ͼƬ����ʾ������ʾ����ش���,���ﲻʹ��(����ɾ��������)
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
	 * ��״ͼЧ��
	 * <strong>�����鸲��<strong>
	 * @param chart
	 */
	protected void setPiePlot(JFreeChart chart) {
		/* ͼƬЧ��* */
		TextTitle textTitle = chart.getTitle(); // ����
		textTitle.setFont(new Font("����", Font.PLAIN, 20)); // ������������

		PiePlot plot = (PiePlot) chart.getPlot();
		// ������ʾ{0}:���� {1}:���� {2}:����
		
		//ͼƬ��ʾʱ��ʾ��������ʾ
		 plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
		 "{0}({1}={2})", NumberFormat.getNumberInstance(),
			new DecimalFormat("0.00%")));
		// ����ƶ�������ʱ��ʾ��������ʾ
//		plot.setToolTipGenerator(new StandardPieToolTipGenerator(
//				"{0}:({1}={2})", NumberFormat.getNumberInstance(),
//				new DecimalFormat("0.00%")));
		//plot.setLabelGenerator(null);// ͼ�з�Map��ʾ��Ϊ��
//		
		//��������ɫ
		plot.setBaseSectionOutlinePaint(Color.BLACK);  
	    plot.setBaseSectionPaint(Color.BLACK); 
		 
		
		// ͼƬ͸����
		plot.setForegroundAlpha(0.8f);
		// ���ϵ���������
		plot.setLabelFont(new Font("����", Font.PLAIN, 14));
		// ����������͸����
		plot.setBackgroundAlpha(0.7f);
		// ������������ɫ
		plot.setBackgroundPaint(Color.WHITE);
		// ���ݿ�ָ���
//		plot.setAutoPopulateSectionOutlinePaint(false);
		// ������ֵ�ķ���
		plot.setIgnoreNullValues(true);
		//����ͼƬ�߿���ɫ
		plot.setOutlinePaint(Color.white);
		
		plot.setNoDataMessage("û�п���ʾ����!");
		
		// plot.setURLGenerator(new StandardPieURLGenerator("#"));//�趨����
		// plot.setLabelGap(0.02D);
		// setBackgroundImage(plot);// ����ͼƬ
		// ͼ����������
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));
	}
	
	
	/**
	 * Ϊͼ�����ñ���ͼƬ������������ʹ�ñ���ͼƬ��
	 * �˷�����ʱ��ʹ��
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