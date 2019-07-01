<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/wjcfFuction.js"></script>
<body>
	<html:form action="/wjcfzgkdwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置: 当前位置：违纪处分 - 跟踪教育 - 跟踪教育记录
			</div>
		</div>
		<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("您输入的学号无效!");
    				</script>
			</logic:equal>
		<table class="tbstyle" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						单个修改
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="15%">
					<font color="red">*</font>学号：
				</td>
				<td align="left" width="35%">
						<html:text name='rs' property="xh" styleId="xh"
						 readonly="true"/>
				</td>
				<td align="right" width="15%">
					学年：
				</td>
				<td align="left" width="35%">
					<bean:write name="rs" property="xn" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					姓名：
				</td>
				<td align="left">
					<bean:write name="rs" property="xm" />
				</td>
				<td align="right">
					年度：
				</td>
				<td align="left">
					${rs.nd }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					性别：
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					处分类别：
				</td>
				<td align="left">
					${rs.cflbmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					年级：
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				<td align="right">
					处分原因：
				</td>
				<td align="left">
					${rs.cfyymc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					处分时间：
				</td>
				<td align="left">
					${rs.cfsj }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					专业：
				</td>
				<td align="left">
					${rs.zymc }
				</td>
				<td align="right">
					处分文号：
				</td>
				<td align="left">
					${rs.cfwh }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级：
				</td>
				<td align="left" colspan="3">
					${rs.bjmc }
				</td>
<%--				<td align="right">--%>
<%--					--%>
<%--				</td>--%>
<%--				<td align="left">--%>
<%--				--%>
<%--				</td>--%>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>时间：
				</td>
				<td align="left">
					<html:text property="sj" styleId="sj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('sj','y-mm-dd');" readonly="true"></html:text>
				</td> 
				<td align="right">
					地点：
				</td>
				<td align="left">
					<html:text property="dd" styleId="dd" style="width: 180px"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					谈话经过：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="thjg" rows="5" styleId="thjg" style="width:98%"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					谈话小结：
				</td>
				<td align="left" colspan="3"><html:textarea property="thxj" styleId="thxj" style="width:98%" rows="4"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
		<logic:notEqual value="view" name="act">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('wjcf_zgkd_gzjyview.do?act=save','xh-sj');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
						关 闭
					</button>
				</div>
				<!-- 保存后提示页面 -->	
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
