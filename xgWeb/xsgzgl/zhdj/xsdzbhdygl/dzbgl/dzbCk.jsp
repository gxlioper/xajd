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
<html:form action="/dzdy_dzbgl" method="post" styleId="DzbglForm" onsubmit="return false;">
	<input type="hidden" value="${dzblx}" name="dzblx" id="dzblx"/>	
    <table width="100%" border="0" class="formlist">
        <thead>
        <tr>
            <th colspan="4">
				<span>��֧����Ϣ
				</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th width="20%">��������</th>
            <td id="ssjc" colspan="3">${dzbInfo.mc}</td>
        </tr>
        <tr>
            <th width="20%">����</th>
            <td id="dzbmc" colspan="3">${dzbInfo.dzbmc}</td>
        </tr>
        <tr>
            <th width="20%">����ʱ��</th>
            <td id="clsj" colspan="3">${dzbInfo.clsj}</td>
        </tr>
        <tr>
            <th width="20%">����ʱ��</th>
            <td id="hjsj" colspan="3">${dzbInfo.hjsj}</td>
        </tr>
        </tbody>

    <table width="100%" border="1" style="margin:2px auto;" class="formlist">
           <thead>
	        <tr>
	            <th colspan="4">
						<span>��֧��ְ����Ϣ
						</span>
	            </th>
	        </tr>
           <tr>
               <td width='25%'>ְ������</td>
               <td width='25%'>ѧ�Ż���ְ����</td>
               <td width='25%'>����</td>
               <td width='25%'>��ϵ��ʽ</td>
           </tr>
 	       </thead>
           <tbody id="zwxx">
           <logic:present name = "dzbZwList">
           		<logic:iterate id="zwxx" name = "dzbZwList" indexId="index">
           			<tr>
           				<td>${zwxx.zwmc}</td>
           				<td>${zwxx.lxrid}</td>
           				<td>${zwxx.lxrmc}</td>
           				<td>${zwxx.lxrdh}</td>       				
           			</tr>
           		</logic:iterate>
           </logic:present>
           </tbody>
           <logic:empty name = "dzbZwList">
           		<tr>
           			<td colspan ="4">�������Ϣ</td>
           		</tr>
           </logic:empty>
        </table>


        <table width="100%" border="0" class="datelist" style="margin:2px auto;" >
            <logic:empty name="rs" property="cyHtml">
                <thead>
                <th colspan="10">
						<span>��֧����Ա��Ϣ
						</span>
                </th>
                </thead>
                <tbody>
                <tr>
                    <td>����ؼ�¼��</td>
                </tr>
                </tbody>
            </logic:empty>
            <logic:notEmpty name="rs" property="cyHtml">
                <thead>
		           <th colspan="10">
						<span>��֧����Ա��Ϣ
						</span>
		           </th>
		           <%
				String dzblx = (String)request.getAttribute("dzblx");
		           if(dzblx.equals("ѧ����֧��")){
		           %>

                <tr>
                    <td width='2%'>�к�</td>
                    <td width='10%'>ѧ��</td>
                    <td width='10%'>����</td>
                    <td width='8%'>�Ա�</td>
                    <td width='10%'>רҵ</td>
                    <td width='15%'>��ϵ�绰</td>
                    <td width='15%'>���</td>
                    <td width='14%'>״̬</td>
                    <td width='10%'>�Ƿ�ʧ��</td>
                    <td width='10'>�Ƿ�����</td>
                </tr>
              <%}else { %>
                 <tr>
                    <td >�к�</td>
                    <td >ְ����</td>
                    <td >����</td>
                    <td >��ϵ�绰</td>
                </tr>
                <%} %>
                </thead>
                <tbody id="tbody_zgryxx">
                    ${rs.cyHtml }
                </tbody>
            </logic:notEmpty>
        </table>
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