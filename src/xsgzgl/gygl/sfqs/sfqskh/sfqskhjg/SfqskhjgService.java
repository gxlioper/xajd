package xsgzgl.gygl.sfqs.sfqskh.sfqskhjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import xgxt.action.Base;
import xsgzgl.gygl.sfqs.sfqscj.sfqscjjg.SfqscjjgDao;
import xsgzgl.gygl.sfqs.sfqscj.sfqscjjg.SfqscjjgForm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class SfqskhjgService extends SuperServiceImpl<SfqskhjgForm,SfqskhjgDao>{

    private int start = 0;
    private int data_start = start+3;
    public List<HashMap<String,String>> export(SfqskhjgForm t)throws Exception {
        return dao.export(t);
    }
    public File exportDate(List<HashMap<String,String>> dataList) throws Exception {
        //数据处理，按楼栋寝室学年为key，分组
        HashMap<String,List<HashMap<String,String>>> data = groupBy(dataList);

        File file = new File(System.getProperty("java.io.tmpdir"),"西安交通大学示范宿舍汇总表.xls");
        if(!file.exists()){
            file.createNewFile();
        }
        //固定写法直接扣
        WritableWorkbook book = Workbook.createWorkbook(file);
        //设置相关格式
        WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);//设置标题字体
        WritableFont headFont = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置表头字体
        WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置正文字体

        WritableCellFormat title_cf = new WritableCellFormat(tileFont);//设置标题单元格字体
        title_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置标题单元格对齐
        title_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置标题单元格边框

        WritableCellFormat head_cf = new WritableCellFormat(headFont);//设置正文表头字体
        head_cf.setAlignment(jxl.format.Alignment.CENTRE);
        head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置表头水平对齐
        head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);

        WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
        body_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置正文单元格对齐
        body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置水平对齐
        body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置正文单元格边框

        //固定写法直接扣

        //创建工作表
        WritableSheet sheet = book.createSheet("sheet1", 0);

        //设置列的宽度
        sheet.setColumnView(0,10);
        sheet.setColumnView(1,20);
        sheet.setColumnView(2,20);
        sheet.setColumnView(3,20);
        sheet.setColumnView(4,10);
        sheet.setColumnView(5,20);
        sheet.setColumnView(6,10);
        sheet.setColumnView(7,20);
        sheet.setColumnView(8,10);
        sheet.setColumnView(9,20);
        sheet.setColumnView(10,10);
        sheet.setColumnView(11,20);
        sheet.setColumnView(12,10);
        sheet.setColumnView(13,20);
        sheet.setRowView(0,700,false);

        //合并单元格
        sheet.mergeCells(0,0,13,0);
        sheet.mergeCells(0,1,0,2);
        sheet.mergeCells(1,1,1,2);
        sheet.mergeCells(2,1,2,2);
        sheet.mergeCells(3,1,3,2);
        sheet.mergeCells(4,1,4,2);
        sheet.mergeCells(5,1,5,2);

        sheet.mergeCells(6,1,7,1);
        sheet.mergeCells(8,1,9,1);
        sheet.mergeCells(10,1,11,1);
        sheet.mergeCells(12,1,13,1);

        sheet.addCell(new Label(0,0,"西安交通大学“示范宿舍”汇总表",title_cf));
        sheet.addCell(new Label(0,1,"序号",head_cf));
        sheet.addCell(new Label(1,1,"书院",head_cf));
        sheet.addCell(new Label(2,1,"宿舍创建类型",head_cf));
        sheet.addCell(new Label(3,1,"楼宇",head_cf));
        sheet.addCell(new Label(4,1,"宿舍号",head_cf));
        sheet.addCell(new Label(5,1,"舍长联系方式",head_cf));
        sheet.addCell(new Label(6,1,"成员1（舍长）",head_cf));
        sheet.addCell(new Label(6,2,"姓名",head_cf));
        sheet.addCell(new Label(7,2,"学号",head_cf));
        sheet.addCell(new Label(8,1,"成员2",head_cf));
        sheet.addCell(new Label(8,2,"姓名",head_cf));
        sheet.addCell(new Label(9,2,"学号",head_cf));
        sheet.addCell(new Label(10,1,"成员3",head_cf));
        sheet.addCell(new Label(10,2,"姓名",head_cf));
        sheet.addCell(new Label(11,2,"学号",head_cf));
        sheet.addCell(new Label(12,1,"成员4",head_cf));
        sheet.addCell(new Label(12,2,"姓名",head_cf));
        sheet.addCell(new Label(13,2,"学号",head_cf));

        int i=0;
        for(String key : data.keySet()){
            //数据从第4行开始，数字3
            sheet.addCell(new Label(0,data_start+i,String.valueOf(i+1),body_cf));
            sheet.addCell(new Label(1,data_start+i,data.get(key).get(0).get("symc"),body_cf));
            sheet.addCell(new Label(2,data_start+i,data.get(key).get(0).get("sblx"),body_cf));
            sheet.addCell(new Label(3,data_start+i,data.get(key).get(0).get("ldmc"),body_cf));
            sheet.addCell(new Label(4,data_start+i,data.get(key).get(0).get("qsh"),body_cf));
            sheet.addCell(new Label(5,data_start+i,data.get(key).get(0).get("lxfs"),body_cf));

            int x=0;
            for(int j=0;j<4;j++){
                if(j<data.get(key).size()){
                    if("是".equals(data.get(key).get(j).get("sfqsz"))){
                        sheet.addCell(new Label(6,data_start+i,data.get(key).get(j).get("xm"),body_cf));
                        sheet.addCell(new Label(7,data_start+i,data.get(key).get(j).get("cyxh"),body_cf));
                    } else {
                        sheet.addCell(new Label(8+x,data_start+i,data.get(key).get(j).get("xm"),body_cf));
                        sheet.addCell(new Label(9+x,data_start+i,data.get(key).get(j).get("cyxh"),body_cf));
                        x= x+2;
                    }
                } else {
                    sheet.addCell(new Label(8+x,data_start+i,"",body_cf));
                    sheet.addCell(new Label(9+x,data_start+i,"",body_cf));
                    x= x+2;
                }

            }

            i++;
        }
        book.write();
        book.close();
        return file;
    }

    private HashMap<String,List<HashMap<String,String>>> groupBy(List<HashMap<String,String>> dataList){
        HashMap<String,List<HashMap<String,String>>> map = new HashMap<String, List<HashMap<String, String>>>();
        List<HashMap<String,String>> list;
        for (HashMap<String,String> item : dataList){
            String key = item.get("lddm")+item.get("qsh")+item.get("xn");
            if(map.containsKey(key)){
                list = map.get(key);
            } else {
                list = new ArrayList<HashMap<String, String>>();
            }
            list.add(item);
            map.put(key,list);
        }
        return map;
    }
}
