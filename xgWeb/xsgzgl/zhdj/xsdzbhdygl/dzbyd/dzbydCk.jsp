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
    <script type="text/javascript">

    </script>
</head>
<body>
<html:form action="/dzdy_dzbyd" method="post" styleId="DzbydForm" onsubmit="return false;">
    <table width="100%" border="0" class="formlist">
        <thead>
        <tr>
            <th colspan="4">
				<span>ѧ����Ϣ
				</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th width="20%">����</th>
            <td id="xm">${dzbydInfo.xm}</td>
            <th width="20%">�Ա�</th>
            <td id="xb">${dzbydInfo.xb}</td>
        </tr>
        <tr>
            <th width="20%">����</th>
            <td id="mz">${dzbydInfo.mz}</td>
            <th width="20%">��������</th>
            <td id="csrq">${dzbydInfo.csrq}</td>
        </tr>
        <tr>
            <th width="20%">�꼶</th>
            <td id="nj">${dzbydInfo.nj}</td>
            <th width="20%">��Ժ</th>
            <td id="symc">${dzbydInfo.symc}</td>
        </tr>
        <tr>
            <th width="20%">רҵ</th>
            <td id="zymc">${dzbydInfo.zymc}</td>
            <th width="20%">�����༶</th>
            <td id="bjmc">${dzbydInfo.bjmc}</td>
        </tr>
        <tr>
            <th width="20%">רҵ�༶</th>
            <td id="zybjmc">${dzbydInfo.zybjmc}</td>
            <th width="20%">������ò</th>
            <td id="zzmmmc">${dzbydInfo.zzmmmc}</td>
        </tr>
        </tbody>
        <table width="100%" border="1" style="margin:2px auto;" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
						<span>��֧���춯��Ϣ
						</span>
                </th>
            </tr>
            <tr>
                <th width="20%">ԭ��֧��</th>
                <td id="dzbmc" colspan="3">${dzbydInfo.dzbmc}</td>
            </tr>
            <tr>
                <th width="20%">�춯��֧��</th>
                <td id="yddzbmc" colspan="3">${dzbydInfo.yddzbmc}</td>
            </tr>
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
                            �� ��
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