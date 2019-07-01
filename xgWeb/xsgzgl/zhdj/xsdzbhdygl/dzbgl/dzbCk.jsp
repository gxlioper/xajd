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
				<span>党支部信息
				</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th width="20%">所属基层</th>
            <td id="ssjc" colspan="3">${dzbInfo.mc}</td>
        </tr>
        <tr>
            <th width="20%">名称</th>
            <td id="dzbmc" colspan="3">${dzbInfo.dzbmc}</td>
        </tr>
        <tr>
            <th width="20%">成立时间</th>
            <td id="clsj" colspan="3">${dzbInfo.clsj}</td>
        </tr>
        <tr>
            <th width="20%">换届时间</th>
            <td id="hjsj" colspan="3">${dzbInfo.hjsj}</td>
        </tr>
        </tbody>

    <table width="100%" border="1" style="margin:2px auto;" class="formlist">
           <thead>
	        <tr>
	            <th colspan="4">
						<span>党支部职务信息
						</span>
	            </th>
	        </tr>
           <tr>
               <td width='25%'>职务名称</td>
               <td width='25%'>学号或者职工号</td>
               <td width='25%'>名称</td>
               <td width='25%'>联系方式</td>
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
           			<td colspan ="4">无相关信息</td>
           		</tr>
           </logic:empty>
        </table>


        <table width="100%" border="0" class="datelist" style="margin:2px auto;" >
            <logic:empty name="rs" property="cyHtml">
                <thead>
                <th colspan="10">
						<span>党支部成员信息
						</span>
                </th>
                </thead>
                <tbody>
                <tr>
                    <td>无相关记录！</td>
                </tr>
                </tbody>
            </logic:empty>
            <logic:notEmpty name="rs" property="cyHtml">
                <thead>
		           <th colspan="10">
						<span>党支部成员信息
						</span>
		           </th>
		           <%
				String dzblx = (String)request.getAttribute("dzblx");
		           if(dzblx.equals("学生党支部")){
		           %>

                <tr>
                    <td width='2%'>行号</td>
                    <td width='10%'>学号</td>
                    <td width='10%'>姓名</td>
                    <td width='8%'>性别</td>
                    <td width='10%'>专业</td>
                    <td width='15%'>联系电话</td>
                    <td width='15%'>类别</td>
                    <td width='14%'>状态</td>
                    <td width='10%'>是否失联</td>
                    <td width='10'>是否流动</td>
                </tr>
              <%}else { %>
                 <tr>
                    <td >行号</td>
                    <td >职工号</td>
                    <td >姓名</td>
                    <td >联系电话</td>
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