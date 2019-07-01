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
<html:form method="post" styleId="form" action="/xlzx_zdgzxs">
    <div style='width: 100%; height: 450px; overflow-x: hidden; overflow-y: auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生基本信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>咨询情况</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th align="right">
                    咨询师
                </th>
                <td >
                        ${map.zxsxm}

                </td>
                <th align="right">
                    联系电话
                </th>
                <td>
                        ${map.zxslxdh}
                </td>
            </tr>
            <tr>
                <th align="right">
                    目前咨询次数
                </th>
                <td >
                        ${map.zxcs}
                </td>
                <th align="right">
                    是否征得学生同意
                </th>
                <td >
                        ${map.sfxsty == "1" ? "是":"否"}
                </td>
            </tr>
            <tr>
                <th align="right">
                    问题类别
                </th>
                <td colspan="3">
                        ${map.wtlbmc}
                </td>
            </tr>
            <tr>
                <th align="right">
                    问题描述
                </th>
                <td align="left" colspan="3">
                        ${map.wtms}
                </td>
            </tr>
            <tr>
                <th align="right">
                    处理建议
                </th>
                <td align="left" colspan="3">
                        ${map.cljy}
                </td>
            </tr>
                <%--<tr>
                    <th>
                        附件
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
                    <div class="btn">
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