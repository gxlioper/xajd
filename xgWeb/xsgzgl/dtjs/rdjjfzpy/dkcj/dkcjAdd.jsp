<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/dtjs/rdjjfzpy/dkcj/js/dkcj.js"></script>
    <script type="text/javascript">
        jQuery(function(){

        });
    </script>
</head>
<body style="width: 100%">
<html:form action="/dtjs_dkcj" method="post" styleId="form" onsubmit="return false;">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;height: 350px' >
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
                    <span>党课成绩信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="15%">
                    <font color="red">*</font>党课成绩
                </th>
                <td width="35%">
                    <html:text property="dkcj" styleId="dkcj" maxlength="3"/>
                </td>
                <th>
                    <font color="red">*</font>考试时间
                </th>
                <td>
                    <input id="now" name="now" type="hidden" value="${nowDate}">
                    <html:text property="kssj" styleId="kssj" readonly="true" onfocus="showCalendar('kssj','y-mm-dd',true,'now');"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:25px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save('add');">
                            保存
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
    <%@ include file="/comm/other/tsxx.jsp"%>
</html:form>
</body>
</html>

