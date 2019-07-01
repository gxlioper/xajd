<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src='/xgxt/js/check.js'></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/xlzx/xlwjga/xlwjgy/js/xlwjgy.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript">

    </script>
</head>

<body>
<html:form method="post" styleId="form" action="/xlzx_xlwjgy">
    <div style='width: 100%; height: 450px; overflow-x: hidden; overflow-y: auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生基本信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>危机表现</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th align="right">
                    <font color="red">*</font>报告时间
                </th>
                <td >
                    <html:text property="bgsj" styleId="bgsj" style="width:150px;"
                               onclick="return showCalendar('bgsj','yyyy-MM-dd HH:mm');"/>
                </td>
                <th align="right">
                    <font color="red">*</font>报告人
                </th>
                <td>
                    <html:text property="bgr" styleId="bgr" style="width:120px;"
                               onblur="checkLen(this,8);"/>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>发现途径
                </th>
                <td >
                    <html:select property="fxtj" styleId="fxtj" style="width:120px">
                        <html:option value=""/>
                        <html:option value="fdy">辅导员</html:option>
                        <html:option value="stu">学生</html:option>
                        <html:option value="tel">电话热线</html:option>
                        <html:option value="bbs">BBS</html:option>
                        <html:option value="zx">咨询</html:option>
                        <html:option value="qq">QQ</html:option>
                        <html:option value="qt">其他</html:option>

                    </html:select>
                </td>
                <th align="right">
                    <font color="red">*</font>危机程度
                </th>
                <td >
                    <html:select property="wjcd" styleId="wjcd" style="width:120px">
                        <html:option value=""/>
                        <html:option value="fcjj">非常紧急</html:option>
                        <html:option value="jj">紧急</html:option>
                        <html:option value="yb">一般</html:option>
                        <html:option value="bj">不急</html:option>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>危机发展过程及表现
                    <br/>
                    <font color="red"><B>(限1000字)</B>
                    </font>
                </th>
                <td colspan="3">
                    <html:textarea property='wjfzgc' styleId="wjfzgc" style="width:95%" rows='5'
                                   onblur="checkLen(this,1000)"/>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>危机干预</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th align="right">
                    <font color="red">*</font>危机干预时间
                </th>
                <td>
                    <html:text property="wjgysj" styleId="wjgysj" style="width:150px;"
                               onclick="return showCalendar('wjgysj','yyyy-MM-dd HH:mm');"/>
                </td>
                <th align="right">
                    <font color="red">*</font>干预人员
                </th>
                <td>
                    <html:text property="wjgyry" styleId="wjgyry" style="width:120px;"
                               onblur="checkLen(this,8);"/>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>干预方式
                </th>
                <td >
                    <html:select property="wjgyfs" styleId="wjgyfs" style="width:120px">
                        <html:option value=""/>
                        <html:option value="xcgy">现场干预</html:option>
                        <html:option value="zdxy">指导书院/学院</html:option>
                        <html:option value="yjzx">建议预约咨询</html:option>
                        <html:option value="jyyl">建议医疗</html:option>
                    </html:select>
                </td>
                <th align="right">
                    <font color="red">*</font>协同部门
                </th>
                <td >
                    <html:select property="xtbm" styleId="xtbm" style="width:120px">
                        <html:option value=""/>
                        <html:option value="sy">书院/学院</html:option>
                        <html:option value="gac">公安处</html:option>
                        <html:option value="wlzx">网络中心</html:option>
                        <html:option value="xyy">校医院</html:option>
                        <html:option value="qt">其他</html:option>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>结果
                </th>
                <td align="left" colspan="3">
                    <html:select property="wjgyjg" styleId="wjgyjg" style="width:120px">
                        <html:option value=""/>
                        <html:option value="sljj">顺利解决</html:option>
                        <html:option value="ydgj">有待跟进</html:option>
                        <html:option value="jyyl">建议医疗</html:option>
                        <html:option value="xx">休学</html:option>
                        <html:option value="tx">退学</html:option>
                        <html:option value="qt">其他</html:option>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>危机处理过程及措施
                    <br/>
                    <font color="red"><B>(限1000字)</B>
                    </font>
                </th>
                <td align="left" colspan="3">
                    <html:textarea property='wjclgc' styleId="wjclgc" style="width:95%" rows='5'
                                   onblur="checkLen(this,1000)"/>
                </td>
            </tr>
                <%--<tr>
                    <th>
                        <font color="red">*</font>附件
                    </th>
                    <td colspan="3">
                        <html:hidden property="filepath2" styleId="filepath2"/>
                        <input type="file" id="filepath_f2" name="filepath2" />
                        <script type="text/javascript">
                            //调用附件
                            jQuery(function(){
                                jQuery('#filepath_f2').multiUploader({
                                    maxcount : 1,
                                    //后缀
                                    accept : 'png|gif|jpg|zip|rar|doc|docx',
                                    //最大文件大小 单位M
                                    maxsize: 10,
                                    //存放附件的隐藏域的id
                                    elementid : 'filepath2',
                                    eid : 'filepath_f2'
                                });
                            });
                        </script>
                    </td>
                </tr>--%>
            </tbody>
        </table>
    </div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td>
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button id="buttonSave" type="button" onclick="save('add');return false;">
                            保 存
                        </button>
                        <button type="button" onclick="iFClose();return false;">
                            关 闭
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</html:form>
</body>
</html>