<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/nbzy/nbzyJs.js">
</script>
<body>
	<html:form action="/pjpynbzywh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="failinfo" id="failinfo" value="${failinfo }"/>
	<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
	<input type="hidden" name="act" id="act" value="${act}"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置: 评奖评优 - 信息维护 - 综合素质测评
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						${title }
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>学号：
				</td>
				<td align="left">
					<html:text name='rs' property="xh" styleId="xh"/>
				</td>
				<td align="right">
					<font color="red">*</font>学年：
				</td>
				<td align="left">
					<html:select property="xn" styleId="xn" styleClass="select">
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
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
					<font color="red">*</font>学期：
				</td>
				<td align="left">
					<html:select property="xq" styleId="xq" style="width:90px">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
					</html:select>
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
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
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
					班级：
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					分数：
				</td>
				<td align="left">
					<html:text property="tfs" styleId="tfs"></html:text>
				</td>
				<td align="right">
					记分类型：
				</td>
				<td align="left">
					<html:select property="tzt" styleId="tzt">
						<html:option value="0">加分</html:option>
						<html:option value="1">扣分</html:option>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					项目名称：
				</td>
				<td align="left">
					<html:text property="tlr" styleId="tlr"></html:text>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_nbzy_zysyfmodisave.do','xh-xn-xq');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" 
						style="width:80px" id="buttonClose">
						关 闭
					</button>
				</div>
				<!-- 保存提示信息 -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
