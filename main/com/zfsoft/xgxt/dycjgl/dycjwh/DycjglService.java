package com.zfsoft.xgxt.dycjgl.dycjwh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.views.utils.ArrayUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import jxl.CellFeatures;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.apache.struts.upload.FormFile;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DycjglService extends SuperServiceImpl<DycjglForm,DycjglDao> {
    public List<HashMap<String,String>> getStudentList(DycjglForm model) throws Exception {
        List<HashMap<String,String>>xmList =getXmList();
        return dao.getStudentList(model,xmList);
    }

    public List<HashMap<String,String>> getXmList() {
        return dao.getXmList();
    }

    public List<HashMap<String,String>> getBkList(DycjglForm model) throws Exception {
        List<HashMap<String,String>>xmList =getXmList();
        return dao.getBkList(model,xmList);
    }

    public List<HashMap<String,String>> getSfhg() {
        List<HashMap<String,String>> sfheList = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> map2 = new HashMap<String, String>();
        map2.put("xxmc","合格");
        HashMap<String,String> map3 = new HashMap<String, String>();
        map3.put("xxmc","不合格");
        sfheList.add(map2);
        sfheList.add(map3);
        return sfheList;
    }

    public boolean savedycj(DycjglForm t, User user) throws Exception {
        //判断该学年、学期，该学生是否已经录入了此项目的分数
        HashMap<String,String> fsMap  = dao.getFsid(t);
        t.setZgh(user.getUserName());
        t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));

        //无：插入 有：更新
        if (StringUtil.isNull(fsMap.get("guid"))){
            return dao.runInsert(t);
        } else {
            t.setGuid(fsMap.get("guid"));
            return dao.runUpdate(t);
        }
    }

    public HashMap<String,String> getXnXqInfo() {
        return dao.getXnXqInfo();
    }

    public List<HashMap<String,String>> getStudentByBjdm(DycjglForm model) {
        List<HashMap<String,String>>xmList =getXmList();
        return dao.getrStudentBuBjdm(model,xmList);
    }

    public List<HashMap<String,String>> getBkBhg(DycjglForm model) {
        List<HashMap<String,String>>xmList =getXmList();
        return dao.getBkBhg(model,xmList);
    }




    public File createImportTemplate(DycjglForm model, User user) {
        WritableWorkbook wwb = null;

        //导出文件存放 的临时目录
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        if (!tempDir.exists()){
            tempDir.mkdir();
        }

        //创建导出文件
        File file = new File( tempDir.getPath() + "/" +"德育成绩导入模版.xls");
        file.setWritable(true);

        try{
            FileOutputStream stream = new FileOutputStream(file);
            //创建excel工作表
            wwb = Workbook.createWorkbook(stream);

            WritableSheet ws = wwb.createSheet("德育成绩导入模版", 0);


            //获得综测分项目
            List<HashMap<String, String>> xmList = dao.getXmList();


            //固定表头学号、姓名
            ws.addCell(new Label(0, 0, "学号"));
            ws.addCell(new Label(1, 0, "姓名"));

            for (int i = 0 , j = xmList.size() ; i < j ; i++){
                Label label = new Label(2+i, 0, xmList.get(i).get("xmmc"));
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //将项目代码作为注释，用于识别导入
                wcfeatures.setComment(xmList.get(i).get("xmdm"));
                label.setCellFeatures(wcfeatures);
                ws.addCell(label);
            }

            //不分页
            model.getPages().setPageSize(Integer.MAX_VALUE);
            //查询学生及已经录入的分数
            List<HashMap<String,String>> zcfList = dao.getrStudentBuBjdm(model,xmList);
            for (int i = 0 , j = zcfList.size() ; i < j ; i++){
                ws.addCell(new Label(0, i+1, zcfList.get(i).get("xh")));
                ws.addCell(new Label(1, i+1, zcfList.get(i).get("xm")));
                ws.addCell(new Label(2, i+1, zcfList.get(i).get("fs0")));
                ws.addCell(new Label(3, i+1, zcfList.get(i).get("fs1")));
                ws.addCell(new Label(4, i+1, zcfList.get(i).get("fs2")));
            }
            wwb.write();
            wwb.close();
        }catch (Exception e) {
            throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
        }

        file.setWritable(true);
        return file;
    }

    public File importDycj(DycjglForm model, User user) throws Exception {
        FormFile importFile = model.getImportFile();
        //将FormFile 转换为File 对象
        File file = FileUtil.conversionFormFile(importFile);

        //获取Excel工作表
        Workbook book = Workbook.getWorkbook(file);

        List<HashMap<String, String>> xmList = dao.getXmList();

        WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
        WritableSheet ws = wwb.getSheet(0);
        //设置错误消息提示·列宽
        ws.setColumnView(xmList.size()+2, 30);



        //导入模版与综测结构比较验证
        for (int i = 0 , j = xmList.size() ; i < j ; i++){

            String xmdm = xmList.get(i).get("xmdm");
            String xmmc = xmList.get(i).get("xmmc");

            CellFeatures cellFeatures = ws.getCell(2+i, 0).getCellFeatures();

            if (cellFeatures == null){
                throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
            }

            String cellComment = cellFeatures.getComment();
            String cellContent = ws.getCell(2+i, 0).getContents();

//            //验证下表头是否与导出模版一致
//            if (!xmdm.equals(cellComment.trim()) || !xmmc.equals(cellContent.trim())){
//                throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
//            }
        }

		/*
		 * 检测导入数据
		 */
        int rows = ws.getRows();
        List<String[]> params = new ArrayList<String[]>();

        boolean checkResult = true;

        for (int i = 1 ; i < rows ; i++){
            StringBuilder errorMessage = new StringBuilder();

            String xh = ws.getCell(0, i).getContents();
            String xm = ws.getCell(1, i).getContents();

            if (StringUtil.isNull(xh) || StringUtil.isNull(xm)){
                //学号和姓名怎么可以为空呢？
                errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_XHXM_NULL));
            }else{
                HashMap<String,String>xqxn =  getXnXqInfo();
                for (int m = 0 ; m < xmList.size() ; m++){
                    String xmfs = ws.getCell(m+2, i).getContents().trim();
                    //收集参数
                    String[] param = null;
                    try {//排除表格当中空白得地方
                        int a = Integer.valueOf(xmfs);
                        if(a>=0){
                            param = new String[]{xh,xqxn.get("xn"),xqxn.get("xqdm"),xmList.get(m).get("xmdm"),xmfs,user.getUserName(),xh,xmList.get(m).get("xmdm")};
                        }
                    }catch (Exception e)
                    {
                        continue;
                    }


                    params.add(param);
                }
            }


            //错误消息追加
            if (errorMessage.length() > 0){
                WritableCellFormat wcf = new WritableCellFormat();
                WritableFont wf = new WritableFont(WritableFont.ARIAL);
                wf.setColour(Colour.RED);
                wcf.setFont(wf);
                wcf.setAlignment(Alignment.CENTRE);
                ws.addCell(new Label(xmList.size()+2, i, errorMessage.toString(),wcf));
                checkResult = false;
            }
        }


        //验证OK的导入，失败的导出
        if(!params.isEmpty()){
            System.out.println("action========"+System.currentTimeMillis());
            dao.batchInsert(params);//批量插入
            System.out.println("end=========="+System.currentTimeMillis());
        }


        if (!checkResult){
            WritableSheet ws1 = wwb.createSheet("错误数据", 1);
            ws1.addCell(new Label(0,0,ws.getCell(0,0).getContents()));
            ws1.addCell(new Label(1,0,ws.getCell(1,0).getContents()));
            ws1.addCell(new Label(2,0,ws.getCell(2,0).getContents()));
            ws1.addCell(new Label(3,0,ws.getCell(3,0).getContents()));
            ws1.addCell(new Label(4,0,ws.getCell(4,0).getContents()));
            ws1.addCell(new Label(5,0,ws.getCell(5,0).getContents()));
            for (int i = 0 , j = xmList.size() ; i < j ; i++){
                Label label = new Label(2+i, 0, xmList.get(i).get("xmmc"));
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //将项目代码作为注释，用于识别导入
                wcfeatures.setComment(xmList.get(i).get("xmdm"));
                label.setCellFeatures(wcfeatures);
                ws1.addCell(label);
            }
            wwb.removeSheet(0);
            wwb.write();
            wwb.close();
            return file;
        }
        return null;

    }

    public File createImportTemplateBhg(DycjglForm model, User user) {

        WritableWorkbook wwb = null;

        //导出文件存放 的临时目录
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        if (!tempDir.exists()){
            tempDir.mkdir();
        }

        //创建导出文件
        File file = new File( tempDir.getPath() + "/" +"德育成绩补考情况导入模版.xls");
        file.setWritable(true);
        try{
            FileOutputStream stream = new FileOutputStream(file);
            //创建excel工作表
            wwb = Workbook.createWorkbook(stream);

            WritableSheet ws = wwb.createSheet("德育成绩补考情况导入模版", 0);
            //获得综测分项目
            List<HashMap<String, String>> xmList = dao.getXmList();
            //固定表头学号、姓名
            ws.addCell(new Label(0, 0, "学号"));
            ws.addCell(new Label(1, 0, "姓名"));
            ws.addCell(new Label(2, 0, "不合格学生补考情况"));
            ws.addCell(new Label(3, 0, "是否合格"));
            //不分页
            model.getPages().setPageSize(Integer.MAX_VALUE);
            //查询学生及已经录入的分数
            List<HashMap<String,String>> bkBhgList = dao.getBkBhg(model,xmList);
            for (int i = 0 , j = bkBhgList.size() ; i < j ; i++){
                ws.addCell(new Label(0, i+1, bkBhgList.get(i).get("xh")));
                ws.addCell(new Label(1, i+1, bkBhgList.get(i).get("xm")));
                ws.addCell(new Label(2, i+1, bkBhgList.get(i).get("bkqk")));
                ws.addCell(new Label(3, i+1, bkBhgList.get(i).get("bksfhg")));
            }
            wwb.write();
            wwb.close();
        }catch (Exception e) {
            throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
        }
        file.setWritable(true);
        return file;
    }



    public File importDycjBhg(DycjglForm model, User user) throws Exception {
        FormFile importFile = model.getImportFile();
        //将FormFile 转换为File 对象
        File file = FileUtil.conversionFormFile(importFile);

        //获取Excel工作表
        Workbook book = Workbook.getWorkbook(file);
        List<HashMap<String, String>> xmList = dao.getXmList();
        WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
        WritableSheet ws = wwb.getSheet(0);
        //设置错误消息提示·列宽
        ws.setColumnView(2+2, 30);
		/*
		 * 检测导入数据
		 */
        int rows = ws.getRows();
        List<String[]> params = new ArrayList<String[]>();

        boolean checkResult = true;

        for (int i = 1 ; i < rows ; i++){
            StringBuilder errorMessage = new StringBuilder();

            String xh = ws.getCell(0, i).getContents();
            String xm = ws.getCell(1, i).getContents();

            if (StringUtil.isNull(xh) || StringUtil.isNull(xm)){
                //学号和姓名怎么可以为空呢？
                errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_XHXM_NULL));
            }else{
                HashMap<String,String>xqxn =  getXnXqInfo();

                    String bkqk = ws.getCell(2, i).getContents().trim();
                    String bksfhg = ws.getCell(3, i).getContents().trim();
                    //收集参数
                    String[] param = new String[]{xh,xqxn.get("xn"),xqxn.get("xqdm"),bkqk,bksfhg,user.getUserName(),xh,xqxn.get("xqdm"),xqxn.get("xn"),};
                    params.add(param);
            }
            //错误消息追加
            if (errorMessage.length() > 0){
                WritableCellFormat wcf = new WritableCellFormat();
                WritableFont wf = new WritableFont(WritableFont.ARIAL);
                wf.setColour(Colour.RED);
                wcf.setFont(wf);
                wcf.setAlignment(Alignment.CENTRE);
                ws.addCell(new Label(2+2, i, errorMessage.toString(),wcf));
                checkResult = false;
            }
        }


        //验证OK的导入，失败的导出
        if(!params.isEmpty()){
            System.out.println("action========"+System.currentTimeMillis());
            dao.batchInsertBhg(params);//批量插入
            System.out.println("end=========="+System.currentTimeMillis());
        }


        if (!checkResult){
            WritableSheet ws1 = wwb.createSheet("错误数据", 1);
            ws1.addCell(new Label(0,0,ws.getCell(0,0).getContents()));
            ws1.addCell(new Label(1,0,ws.getCell(1,0).getContents()));
            ws1.addCell(new Label(2,0,ws.getCell(2,0).getContents()));
            ws1.addCell(new Label(3,0,ws.getCell(3,0).getContents()));
            wwb.removeSheet(0);
            wwb.write();
            wwb.close();
            return file;
        }
        return null;

    }

    public List<HashMap<String,String>> getbjQuery(DycjglForm model, User user) throws Exception {
        List<HashMap<String, String>> xmList = dao.getXmList();
        return dao.getbjQuery(model,xmList);
    }
}
