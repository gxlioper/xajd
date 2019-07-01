<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dmpz/js/dmpz.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">


    </script>
    <style type="text/css">
    </style>
</head>
<body>
<html:form action="/dzdy_dmpz" method="post" styleId="dzbdmpzForm">

    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>增加项目</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
            <th><font color="red">*</font>代码</th>
            <td>
                <html:text property="dm" styleId="dm" onkeyup="checkInput(this)" maxlength="15" style="width:90%"/>
            </td>
            </tr>
            <tr>
            <th><font color="red">*</font>名称</th>
            <td>
                <html:text property="mc" styleId="mc" style="width:90%"/>
            </td>
            </tr>
            <tr>
            <th><font color="red">*</font>所属书院</th>
            <td>
                <input type="text" name="symc" style="width:100px;" id="symc" readonly="true" maxlength="10"
                       style="float: left;"/>
                <html:hidden property="sydm" styleId="sydm"/>
                <html:hidden property="xydm" styleId="xydm"/>
                    <button class="btn_01" id="selfzr" onclick="getSy()" type="button">选择</button>
            </td>
            </tr>

            </tbody>
        </table>
    </div>
    <div style="height:30px;"></div>
    <%--;height:520px --%>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button type="button" onclick="saveDm();">
                            保 存
                        </button>
                        <button type="button" onclick="iFClose();">
                            关闭
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