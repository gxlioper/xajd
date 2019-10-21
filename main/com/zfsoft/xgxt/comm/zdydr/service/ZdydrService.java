package com.zfsoft.xgxt.comm.zdydr.service;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.comm.zdydr.dao.ZdydrDao;
import com.zfsoft.xgxt.comm.zdydr.model.ZdydrModel;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import xgxt.utils.String.StringUtils;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.Boolean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 自定义个性化导入service.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2017-10-10 10:08
 */
public class ZdydrService extends SuperServiceImpl<ZdydrModel,ZdydrDao> {

    /**
     *  查询导入模版信息.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-10 13:58
     * @param drmkdm
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getDrmbxx(String drmkdm) {

        return dao.getDrmbxx(drmkdm);
    }

    /**
     *  查询导入规则信息.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-10 13:58
     * @param drmkdm
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw
     */
    public List<HashMap<String,String>> getDrgzxxList(String drmkdm) {

        return dao.getDrgzxxList(drmkdm);
    }

    /**
     *  获取导入辅助表配置信息和辅助表代码信息.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-10 17:00
     * @param drmkdm
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.Object>>
     * @throw
     */
    public List<HashMap<String,Object>> getDrfzxxAndFzdmxxList(String drmkdm) {

        List<HashMap<String,Object>> drfzxxAndFzdmxxList = new ArrayList<HashMap<String,Object>>();
        List<HashMap<String,String>> drfzxxList = dao.getDrfzxxList(drmkdm);
        for(HashMap<String,String> drfzxx:drfzxxList){
            String fzmc = drfzxx.get("fzmc");
            String pz = drfzxx.get("pz");

            String[] pzArr = pz.split("\\|");
            String tableName = pzArr[0].substring(pzArr[0].indexOf(":")+1);
            String [] outputValue = {pzArr[1],pzArr[2]};

            List<HashMap<String,String>> fzdmxxList = dao.getFzdmxxList(tableName,outputValue,outputValue[0]);
            HashMap<String,Object> drfzxxAndFzdmxx = new HashMap<String,Object>();
            drfzxxAndFzdmxx.put("fzmc", fzmc);
            drfzxxAndFzdmxx.put("fzdmxx_dm", pzArr[1]);
            drfzxxAndFzdmxx.put("fzdmxx_mc", pzArr[2]);
            drfzxxAndFzdmxx.put("fzdmxxList", fzdmxxList);

            drfzxxAndFzdmxxList.add(drfzxxAndFzdmxx);

        }
        return drfzxxAndFzdmxxList;
    }

    /**
     *  验证导入模版（头部列）.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 08:56
     * @param sheet
     * @param drgzxxList
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     * @throw
     */
    public HashMap<String,Object> checkImportHeader(Sheet sheet, List<HashMap<String, String>> drgzxxList) {

        HashMap<String,Object> resultMap = new HashMap<String,Object>();
        Integer rows = sheet.getRows();
        Integer cols = sheet.getColumns();

        List<String> excelHeaderList = new ArrayList<String>();
        for(int i=0;i<cols;i++){
            excelHeaderList.add(sheet.getCell(i, 0).getContents());
        }
        if(drgzxxList.size()!=cols){
            resultMap.put("result", false);
            resultMap.put("error", "01");
            resultMap.put("message", "导入文件中导入列不符合模版要求！");
            return resultMap;
        }else{
            for(int j=0;j<drgzxxList.size();j++){
                String drlmc = drgzxxList.get(j).get("drlmc");
                String excelHeaderCol = excelHeaderList.get(j);
                if(!drlmc.equals(excelHeaderCol)){
                    resultMap.put("result", false);
                    resultMap.put("error", "01");
                    resultMap.put("message", "导入文件中导入列不符合模版要求！");
                    return resultMap;
                }
            }
            resultMap.put("result", true);
            return resultMap;
        }
    }

    /**
     *  读取Excel导入文件中的数据到list中.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 08:59
     * @param sheet
     * @param drgzxxList
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw
     */
    public List<HashMap<String,String>> getExcelDataList(Sheet sheet, List<HashMap<String, String>> drgzxxList) {

        List<HashMap<String,String>> excelDataList = new ArrayList<HashMap<String,String>>();
        int rows = sheet.getRows();
        int cols = sheet.getColumns();
        for(int i=1;i<rows;i++){
            HashMap<String,String> excelRow = new HashMap<String,String>();
            for(int j=0;j<cols;j++){
                excelRow.put(drgzxxList.get(j).get("drl"), sheet.getCell(j, i).getContents().trim());
            }
            excelDataList.add(excelRow);
        }
        return excelDataList;
    }

    public void createWwb(ServletOutputStream outputStream, String drmkdm) {

        // 打开文件
        WritableWorkbook book = null;
        try {
            //获取导入规则，写入excel导入列批注
            List<HashMap<String,String>> drgzxxList = this.getDrgzxxList(drmkdm);

            book = Workbook.createWorkbook(outputStream);
            // 生成导入表，参数0表示sheet1，impor为其名称
            WritableSheet sheet1 = book.createSheet("import", 0);

            //填充导入列及批注提示到导入表
            WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,Colour.WHITE);
            WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
            //设置背景颜色;
            cellFormat1.setBackground(Colour.GREEN);
            for(int i=0;i<drgzxxList.size();i++){
                //考虑根据内容自适应宽度，暂未实现...

                Label label = new Label(i,0, drgzxxList.get(i).get("drlmc"),cellFormat1);
//				sheet1.setColumnView(1, drgzxxList.get(i).get("drlmc").length());

				/*
				 * 主键、唯一(sfzj/sfwy)：不能重复
				 * 必填(sfbt)：不可为空
				 * 最大长度(zdcd)：最大长度为?
				 */
                List<String> pznrList = new ArrayList<String>();
                if("1".equals(drgzxxList.get(i).get("sfwy"))){
                    pznrList.add("不能重复");
                }
                if("1".equals(drgzxxList.get(i).get("sfbt"))){
                    pznrList.add("不可为空");
                }
                if(!StringUtil.isNull(drgzxxList.get(i).get("zdcd"))){
                    pznrList.add("最大长度为："+drgzxxList.get(i).get("zdcd"));
                }

                String pznr = "";
                for(int j=0;j<pznrList.size();j++){
                    pznr = pznr+(j+1)+"."+pznrList.get(j);
                    if(j!=pznrList.size()-1) pznr+="\r\n";
                }

                WritableCellFeatures cellFeatures = new WritableCellFeatures();
                cellFeatures.setComment(pznr);
                label.setCellFeatures(cellFeatures);

                sheet1.addCell(label);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 写入数据并关闭文件
            try {
                book.write();
                book.close();
            } catch (WriteException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     *  逐行验证数据.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 19:06
     * @param excelDataList
     * @param drgzxxList
     * @param tableName
     * @param lhList
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     * @throw
     */
    public HashMap<String,Object> checkExcelDataList(List<HashMap<String, String>> excelDataList,
                                                     List<HashMap<String, String>> drgzxxList,String tableName,
                                                     List<HashMap<String,Object>> lhList) {

        //excelDataList中所有数据，对于验证错误的行，增加了改行所有列错误提示最为最后一列
        HashMap<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result", true);
        Integer errorCount = 0;

        for(HashMap<String,String>excelData:excelDataList){
            boolean rowResult = true;
            StringBuilder rowError = new StringBuilder();

            for(HashMap<String,String> drgzxx : drgzxxList){
//                String sfzj = drgzxx.get("sfzj");
                String sfbt = drgzxx.get("sfbt");
                String sfwy = drgzxx.get("sfwy");
                Integer zdcd = Integer.parseInt(drgzxx.get("zdcd"));
                String lsjgsh = drgzxx.get("lsjgsh");
                String gshxx = drgzxx.get("gshxx");
                String drlmc = drgzxx.get("drlmc");
                String drl = drgzxx.get("drl");
                String cellContents = excelData.get(drl);

                if("1".equals(sfbt)){
                    //验证不可为空
                    if(StringUtil.isNull(cellContents)){
                        resultMap.put("result", false);
                        rowResult = false;
                        rowError.append("["+drlmc+"]不可为空； ");
                        continue;
                    }
                }

                if(!StringUtil.isNull(cellContents)){
                    //单元格数据不为空时做下面的验证
                    //验证最大长度
                    if(!checkLength(cellContents,zdcd)){
                        resultMap.put("result", false);
                        rowResult = false;
                        rowError.append("["+drlmc+"]最大长度为:"+zdcd+"； ");
                        continue;
                    }
                    //验证数据格式(来源于导入规则的格式配置)
                    if(lsjgsh.startsWith("field")){
                        if(lsjgsh.contains("Datetime")){
                            String fmt = lsjgsh.substring(lsjgsh.indexOf("(")+1,lsjgsh.indexOf(")"));
                            SimpleDateFormat sdf = new SimpleDateFormat(fmt);
                            try {
                                this.checkDateFormat(sdf,cellContents);
                            } catch (ParseException e) {
                                resultMap.put("result", false);
                                rowResult = false;
                                rowError.append("["+drlmc+"]格式为:"+fmt+"； ");
                                continue;
                            }
                        }
                    }
                    if(lsjgsh.startsWith("json")){
                        //这里将数据根据json替换
                        HashMap<String,String> jsonMap = new HashMap<String,String>();
                        String jsonString = lsjgsh.substring(lsjgsh.indexOf("{")+1,lsjgsh.indexOf("}"));
                        String[] jsonArr = jsonString.split(",");
                        for(String json:jsonArr){
                            String [] keyValue = json.split(":");
                            String key = keyValue[0].replaceAll("\"", "");
                            String value = keyValue[1].replaceAll("\"", "");
                            jsonMap.put(key, value);
                        }
                        if(jsonMap.keySet().contains(cellContents)){
                            excelData.put(drl, jsonMap.get(cellContents));
                        }else{
                            resultMap.put("result", false);
                            rowResult = false;
                            rowError.append("["+drlmc+"]"+gshxx+"； ");
                            continue;
                        }

                    }
                    if(lsjgsh.startsWith("sql")){
                        String sql = lsjgsh.substring(lsjgsh.indexOf(":")+1);
                        String data = dao.changeCellData(sql,cellContents,drl);
                        if(StringUtil.isNull(data)){
                            resultMap.put("result", false);
                            rowResult = false;
                            rowError.append("["+drlmc+"]"+gshxx+"； ");
                            continue;
                        }else{
                            excelData.put(drl, data);
                        }
                    }
                    if(lsjgsh.startsWith("select")){
                        //将数据根据sql校验，用于使用中间表关联的数据
                        String sql = lsjgsh.substring(lsjgsh.indexOf(":")+1);
                        String[] sel = cellContents.split(",");
                        for(int i=0;i<sel.length;i++){
                            String data = dao.changeCellData(sql,sel[i],drl);
                            if(StringUtil.isNull(data)){
                                resultMap.put("result", false);
                                rowResult = false;
                                rowError.append("["+drlmc+"]"+gshxx+"； ");
                                continue;
                            }else{
                                excelData.put(drl, cellContents);
                            }
                        }
                    }

                    //验证单个字段是否唯一
                   /* if("1".equals(sfwy)){
                        cellContents = excelData.get(drl);
                        //验证不可重复(对导入操作的表)
                        if(dao.isRepeatForDr(drl,cellContents,tableName)){
                            resultMap.put("result", false);
                            rowResult = false;
                            rowError.append("["+drlmc+"]不可重复； ");
                            continue;
                        }
                    }*/
                }

            }

            //主键、唯一字段信息
            Map<String,List<String>> zjwyZdxxMap = getMapByDrgzxxList(drgzxxList);

            //XG_QGZX_JCFFSQB表主键验证
            if(!checkJCFFSQBZj(zjwyZdxxMap.get("zjDrl"),excelData)){
                resultMap.put("result", false);
                rowResult = false;
                rowError.append(zjwyZdxxMap.get("zjDrlmc").toString()+"主键字段违反唯一约束； ");
            }
            //XG_QGZX_GWFFZTSQB表主键验证
            if(!checkGWFFZTSQBZj(zjwyZdxxMap.get("wyDrl"),excelData)){
                resultMap.put("result", false);
                rowResult = false;
                rowError.append(zjwyZdxxMap.get("wyDrlmc").toString()+"主键字段违反唯一约束； ");
            }

            //联合验证
            /*if(lhList != null){
                for (HashMap<String,Object> lhMap:lhList){
                    if(!checkLh(lhMap,excelData)){
                        resultMap.put("result", false);
                        rowResult = false;
                        rowError.append(Arrays.toString((String [])lhMap.get("drlmcArr"))+"联合字段不存在或不匹配； ");
                    }
                }
            }*/

            excelData.put("rowError", rowError.toString());
            if(!rowResult){
                errorCount++;
            }
        }

        if(!(Boolean)resultMap.get("result")){
            resultMap.put("error", "02");
            //resultMap.put("excelDataList", excelDataList);
            resultMap.put("errorCount", errorCount);
            resultMap.put("totalCount", excelDataList.size());
        }
        return resultMap;
    }

    /**
     *  单元格字段日期格式验证.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 15:12
     * @param sdf
     * @param cellContents
     * @return void
     * @throw
     */
    public void checkDateFormat(SimpleDateFormat sdf, String cellContents) throws ParseException {

        if(cellContents.contains("|")){
            String [] cellContentsArr = cellContents.split("\\|");
            for(String str:cellContentsArr){
                sdf.parse(str);
            }
        }else{
            sdf.parse(cellContents);
        }
    }

    /**
     *  单元格字段长度验证.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 15:02
     * @param cellContents
     * @param zdcd
     * @return boolean
     * @throw
     */
    public boolean checkLength(String cellContents, Integer zdcd) {
        boolean result = true;
        if(cellContents.contains("|")){
            String [] cellContentsArr = cellContents.split("\\|");
            for(String str:cellContentsArr){
                if(str.length()>zdcd){
                    result = false;
                    break;
                }
            }
        }else{
            if(cellContents.length()>zdcd){
                result = false;
            }
        }
        return result;
    }

    /**
     *  联合字段约束验证.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 14:35
     * @param lhMap
     * @param excelData
     * @return boolean
     * @throw
     */
    public boolean checkLh(HashMap<String, Object> lhMap, HashMap<String, String> excelData) {

        String [] lhDrlArr = (String [])lhMap.get("drlArr");
        String tableName = (String)lhMap.get("tableName");
        return dao.checkLh(lhDrlArr,excelData,tableName);
    }

    /**
     *  主键约束验证.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 11:36
     * @param zjDrlList
     * @param excelData
     * @return boolean
     * @throw
     */
    public boolean checkZj(List<String> zjDrlList, HashMap<String, String> excelData,String tableName) {
        boolean result = true;
        if(!zjDrlList.isEmpty()){
            result = dao.checkZj(zjDrlList,excelData,tableName);
        }
        return result;
    }

    public boolean checkJCFFSQBZj(List<String> zjDrlList, HashMap<String, String> excelData){
        boolean result = true;
        if(!zjDrlList.isEmpty()){
            result = dao.checkJCFFSQBZj(excelData);
        }
        return result;
    }

    public boolean checkGWFFZTSQBZj(List<String> zjDrlList, HashMap<String, String> excelData){
        boolean result = true;
        if(!zjDrlList.isEmpty()){
            result = dao.checkGWFFZTSQBZj(excelData);
        }
        return result;
    }
    /**
     *  根据导入规则信息列表获取相关信息.
     *  <p>主键列表，唯一字段列表</p>
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 11:22
     * @param drgzxxList
     * @return java.util.HashMap<java.lang.String,java.util.List<java.lang.String>>
     * @throw
     */
    public HashMap<String,List<String>> getMapByDrgzxxList(List<HashMap<String, String>> drgzxxList){

        HashMap<String,List<String>> map = new HashMap<String,List<String>>();
        //XG_QGZX_JCFFSQB表的唯一键List
        List<String> zjDrlList = new ArrayList<String>();
        List<String> zjDrlmcList = new ArrayList<String>();
        //XG_QGZX_GWFFZTSQB表的唯一键list
        List<String> wyDrlList = new ArrayList<String>();
        List<String> wyDrlmcList = new ArrayList<String>();

        for(HashMap<String,String> m:drgzxxList){
            String drl = m.get("drl");
            if("xh".equals(drl) || "gwdm".equals(drl) || "ffny".equals(drl)){//
                zjDrlList.add(m.get("drl"));
                zjDrlmcList.add(m.get("drlmc"));
            }
            if("yrdwdm".equals(drl) ){//|| "ffny".equals(drl)
                wyDrlList.add(m.get("drl"));
                wyDrlmcList.add(m.get("drlmc"));
            }
        }

        map.put("zjDrl",zjDrlList);
        map.put("zjDrlmc",zjDrlmcList);
        map.put("wyDrl",wyDrlList);
        map.put("wyDrlmc",wyDrlmcList);
        return map;
    }

    /**
     *  验证Excel中数据本身的重复性.
     *  <p>主键约束，唯一约束</p>
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 15:34
     * @param excelDataList
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     * @throw
     */
    public HashMap<String,Object> checkExcelDataRepeat(List<HashMap<String, String>> excelDataList,
                                                       List<HashMap<String, String>> drgzxxList) {

        //主键、唯一字段信息
        Map<String,List<String>> zjwyZdxxMap = getMapByDrgzxxList(drgzxxList);
        List<String> zjDrlList = zjwyZdxxMap.get("zjDrl");
        List<String> wyDrlList = zjwyZdxxMap.get("wyDrl");
        List<String> zjDrlmcList = zjwyZdxxMap.get("zjDrlmc");
        List<String> wyDrlmcList = zjwyZdxxMap.get("wyDrlmc");

        HashMap<String,Object> resultMap = new HashMap<String,Object>();
        boolean result = true;
        int errorCount = 0;

        for(int i=0;i<excelDataList.size()-1;i++){
            for(int j=i+1;j<excelDataList.size();j++){
                StringBuilder rowError = new StringBuilder();
                HashMap<String,String> excelDataI = excelDataList.get(i);
                HashMap<String,String> excelDataJ = excelDataList.get(j);

                String zjValueOfExcelDataI = "";
                String zjValueOfExcelDataJ = "";

                if(zjDrlList != null){
                    for(String s:zjDrlList){
                        zjValueOfExcelDataI += excelDataI.get(s);
                        zjValueOfExcelDataJ += excelDataJ.get(s);
                    }
                }

                if(StringUtils.isNotNull(zjValueOfExcelDataI)&&StringUtils.isNotNull(zjValueOfExcelDataJ)
                        &&zjValueOfExcelDataI.equals(zjValueOfExcelDataJ)){
                    rowError.append(zjDrlmcList.toString()+"excel自身数据中主键字段违反唯一约束； ");
                    result = false;
                }

                if(wyDrlList != null){
                    for (int k=0;k<wyDrlList.size();k++){
                        String wyValueOfExcelDataI = excelDataI.get(wyDrlList.get(k));
                        String wyValueOfExcelDataJ = excelDataJ.get(wyDrlList.get(k));
                        if(wyValueOfExcelDataI != null && wyValueOfExcelDataJ != null
                                && wyValueOfExcelDataI.equals(wyValueOfExcelDataJ)){
                            rowError.append("excel自身数据中"+wyDrlmcList.get(k)+"字段重复； ");
                            result = false;
                        }
                    }
                }

                if(!result){
                    excelDataI.put("rowError", rowError.toString());
                    excelDataJ.put("rowError", rowError.toString());
                    errorCount++;
                }
            }
        }

        if(!result){
            resultMap.put("error", "03");
            resultMap.put("errorCount", errorCount+1);
            resultMap.put("totalCount", excelDataList.size());
        }
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     *  生成Excel错误提示文件到服务器.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 17:46
     * @param excelDataList
     * @param path
     * @param drmkdm
     * @param drgzxxList
     * @return java.lang.String
     * @throw
     */
    public String createErrorTipsExcel(List<HashMap<String, String>> excelDataList, String path, String drmkdm, List<HashMap<String, String>> drgzxxList) {

        File file = new File(path,drmkdm+"_error.xls");

        // 打开文件
        WritableWorkbook book = null;
        try {
            book = Workbook.createWorkbook(file);
            WritableSheet sheet1 = book.createSheet("error", 0);

            //填充头部
            WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
            WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
            cellFormat1.setBackground(Colour.GREEN);

            WritableFont font2 = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableCellFormat cellFormat2 = new WritableCellFormat(font2);
            cellFormat2.setBackground(Colour.YELLOW);

            WritableCellFormat cellFormat3 = new WritableCellFormat(font1);
            cellFormat3.setBackground(Colour.RED);

            for(int i=0;i<=drgzxxList.size();i++){
                Label label = null;
                if(drgzxxList.size()==i){
                    label = new Label(i,0,"错误信息" ,cellFormat3);
                }else{
                    label = new Label(i,0,drgzxxList.get(i).get("drlmc"),cellFormat1);
                }
                sheet1.addCell(label);
            }

            //填充内容
            for(int i=0;i<excelDataList.size();i++){
                for(int j=0;j<=drgzxxList.size();j++){
                    Label label = null;
                    if(drgzxxList.size()==j){
                        String errorTip = excelDataList.get(i).get("rowError");
                        if(StringUtil.isNull(errorTip)){
                            //错误提示为空，无背景色
                            label = new Label(j,i+1,errorTip);
                        }else{
                            //错误提示不为空，黄色背景色
                            label = new Label(j,i+1,errorTip,cellFormat2);
                        }
                    }else{
                        label = new Label(j,i+1,excelDataList.get(i).get(drgzxxList.get(j).get("drl")));
                    }
                    sheet1.addCell(label);
                }
            }

        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 写入数据并关闭文件
            try {
                book.write();
                book.close();
            } catch (WriteException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file.getName();
    }
}
