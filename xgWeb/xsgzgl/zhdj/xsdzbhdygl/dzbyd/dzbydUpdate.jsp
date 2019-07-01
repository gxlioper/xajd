<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" %>
<%@page import="freemarker.log.Logger"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dzbyd/js/dzbyd.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
<html:form action="/dzdy_dzbyd" method="post" styleId="DzbydForm" onsubmit="return false;">
    <table width="100%" border="0" class="formlist">
        <input type="hidden" id="xh" value="${dzbydInfo.xh}" name = "xh"/>
        <input type="hidden" id="dzbid" value="${dzbydInfo.dzbid}" name = "dzbid"/>
        <thead>
        <tr>
            <th colspan="4">
				<span>学生信息
				</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th width="20%">姓名</th>
            <td id="xm">${dzbydInfo.xm}</td>
            <th width="20%">性别</th>
            <td id="xb">${dzbydInfo.xb}</td>
        </tr>
        <tr>
            <th width="20%">民族</th>
            <td id="mz">${dzbydInfo.mz}</td>
            <th width="20%">出生日期</th>
            <td id="csrq">${dzbydInfo.csrq}</td>
        </tr>
        <tr>
            <th width="20%">年级</th>
            <td id="nj">${dzbydInfo.nj}</td>
            <th width="20%">书院</th>
            <td id="symc">${dzbydInfo.symc}</td>
        </tr>
        <tr>
            <th width="20%">专业</th>
            <td id="zymc">${dzbydInfo.zymc}</td>
            <th width="20%">行政班级</th>
            <td id="bjmc">${dzbydInfo.bjmc}</td>
        </tr>
        <tr>
            <th width="20%">专业班级</th>
            <td id="zybjmc">${dzbydInfo.zybjmc}</td>
            <th width="20%">政治面貌</th>
            <td id="zzmmmc">${dzbydInfo.zzmmmc}</td>
        </tr>
        </tbody>
        <table width="100%" border="1" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
						<span>党支部异动信息
						</span>
                </th>
            </tr>
            <tr>
                <th width="20%">原党支部</th>
                <td id="dzbmc" colspan="3">${dzbydInfo.dzbmc}</td>
            </tr>
            <tr>
                <th width="20%">异动后党支部</th>
                <td id ="yddzbmc" colspan="3">
                    <html:select property="yddzbid" style="width:105px" styleId="yddzbid" value="${dzbydInfo.yddzbmc}">
                        <html:option value="">无</html:option>
                        <html:options collection="dzbList" property="dzbid" labelProperty="dzbmc" />
                    </html:select>
                </td>
            </tr>
            </thead>
        </table>
    <div style="height:35px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button type="button" onclick="bc();">
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