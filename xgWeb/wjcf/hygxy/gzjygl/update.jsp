<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<base target="_self">
<body >
	<html:form action="/wjcfhygxywh.do" method="post">
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			<input type="hidden" name="cfpk" id="cfpk" value="${cfpk }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="wjcfhygxy" key="wjcf_hygxy_gzjygl" />
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						单个修改
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>学号：
				</td>
				<td align="left">
					<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
				</td>
				<td align="right" style="width:22%">
					处分原因：
				</td>
				<td align="left">
					<bean:write name="rs" property="cfyymc"/>
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
					处分类别：
				</td>
				<td align="left">
					<bean:write name="rs" property="cflbmc"/>
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
					处分时间：
				</td>
				<td align="left">
					<bean:write name="rs" property="cfsj"/>
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
					<font color="red">*</font>学年：
				</td>
				<td align="left">
					<html:select property="xn" styleId="xn" styleClass="select" style="width: 90px">
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
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
					<font color="red">*</font>学期：
				</td>
				<td align="left">
					<html:select property="xq" styleId="xq" styleClass="select" style="width: 90px">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
					</html:select>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					专业：
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				<td align="right">
					年度：
				</td>
				<td align="left">
					<html:select property="nd" styleId="nd" styleClass="select" style="width: 90px">
						<html:options collection="xnList" property="nd" labelProperty="nd"/>
					</html:select>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级：
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td align="right">
					
				</td>
				<td align="left">
					
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					教育人：
				</td>
				<td align="left">
					<html:text property="jyr" styleId="jyr" styleClass="inputtext"></html:text>
				</td>
				<td align="right">
					<font color="red">*</font>教育时间：
				</td>
				<td align="left">
					<html:text property="jysj" styleId="jysj" 
					onblur="dateFormatChg(this)" style="cursor:hand;"
					onclick="return showCalendar('jysj','y-mm-dd');" ></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					教育主题：
				</td>
				<td colspan="3">
					<html:text property="jyzt" styleId="jyzt" style="width:98%"></html:text>
				</td>
			</tr>
			<tr >
				<td align="right">
					被教育人表现：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="jyrbx" styleId="jyrbx" 
					style="width: 98%" styleClass="inputtext" rows="7"></html:textarea>
				</td>
			</tr>
			
		</table>
		<div class="buttontool" align="center">
						<logic:notEqual value="view" name="act">
							<button type="button" class="button2" id="btn_save" 
							onclick="saveinfo('hygxy_gzjymodisave.do','xn-xq-jysj');"
							style="width:80px">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEqual>
						<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
		</div>
		<!-- 保存提示信息 -->
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>