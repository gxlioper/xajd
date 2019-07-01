<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
</head>
<body>
<html:form action="/dzdy_dmpz" method="post" styleId="dzbdmpzForm" onsubmit="return false;">
    <table width="100%" border="0" class="formlist">
        <thead>
        <tr>
            <th colspan="4">
				<span>党总支信息
				</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th width="20%">名称</th>
            <td id="dzbmc" colspan="3">${dzzmc}</td>
        </tr>
        <tr>
            <th width="20%">成立时间</th>
            <td id="clsj" colspan="3">${clsj}</td>

        </tr>


        <thead>
        <tr>
            <th colspan="4">
					<span>历届信息
					</span>
            </th>
        </tr>

        <logic:iterate id="i" name="dzzList">
            <th colspan="4">
                <div align="left">换届时间:${i.ljsj}</div>
            </th>

            <tr>
                <th width="20%">${i.zwmc}</th>
                <td id="dzbsj">${i.lxrxm}&nbsp;&nbsp;${i.lxrdh}</td>
            </tr>

        </logic:iterate>

        </thead>


    </table>
    <div style="height:35px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="btn">
                        <button type="button" onclick="Close();return false;">
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