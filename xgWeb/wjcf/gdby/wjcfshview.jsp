<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
	<body onload="checkWinType();">
		<html:form action="/wjcfgdbywh" method="post">
			<div class="title">
					<div class="title_img" id="title_m">
						<bean:message key="wjcf_gdby_wjcfshone" bundle="wjcfgdby"/>
					</div>
			</div>
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }"/>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width:20%">
						学号：
					</td>
					<td align="left" style="width:25%">
						<bean:write name="rs" property="xh"/>
						<input type="hidden" name="xh" id="xh" value="<bean:write name="rs" property="xh"/>" />
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<bean:write name="rs" property="nd"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name="rs" property="xm"/>
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						<bean:write name="rs" property="xn"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name="rs" property="xb"/>
					</td>
					<td align="right">
						处分类别：
					</td>
					<td align="left">
					<html:select property="cflb" styleId="cflb" style="width:150" styleClass="select">
						<html:options collection="cflbList" property="cflbdm" labelProperty="cflbmc"/>
					</html:select>
						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="nj"/>
					</td>
					<td align="right">
						处分原因：
					</td>
					<td align="left">
						<html:select property="cfyy" styleId="cfyy" style="width:150" styleClass="select">
							<html:options collection="cfyyList" property="cfyydm" labelProperty="cfyymc"/>
						</html:select>
						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
					<td align="right">
						审核：
					</td>
					<td align="left">
						<html:select property="sh">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td align="right">
					<font color="red">*</font>处分时间：
					</td>
					<td align="left">
						<html:text property="cfsj" styleId="cfsj"
							onblur="dateFormatChg(this)" style="cursor:hand;" readonly="true"
							onclick="return showCalendar('cfsj','y-mm-dd');" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<td align="right">
						<font color="red">*</font>处分文号：
					</td>
					<td align="left">
						<html:text property="cfwh" styleId="cfwh"  maxlength="30"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						审核意见：
					</td>
					<td colspan="4" align="left">
						<html:textarea rows="5" style="width:98%" property="clyj" styleId="a" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
						<button type="button" class="button2"
					onclick="if (chknotnull1()) refreshForm('wjcfsh.do');"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
		</html:form>
		<script language="javascript">
	function getRqVal(name)
			{
			var rq=document.getElementById(name).value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
			}
	function chknotnull1(){
		if (document.getElementById('cfwh').value==null || document.getElementById('cfwh').value==''
		||document.getElementById('cfsj').value==null|| document.getElementById('cfsj').value=='' ) {
			alert('带*号内容为必填项！');
			return false;
		}
		return true;
	}
</script>
	</body>
