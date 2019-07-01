<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpyhxxywh" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
 			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 信息维护 - 综合素质测评
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
				<td align="right" width="15%">
					<font color="red">*</font>学号：
				</td>
				<td align="left" width="35%">
					<html:text name='rs' property="xh" styleId="xh" readonly="true" />
				</td>
				<td align="right" width="18%">
					<font color="red">*</font>学年：
				</td>
				<td align="left" width="32%">
					<html:select property="xn" styleId="xn" style="width:95px">
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
					智育成绩：
				</td>
				<td align="left">
					<html:text property="zcj"  styleId="zcj" onkeyup="ckinpdata(this)" readonly="true" style="width:90px" maxlength="6"></html:text>
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
					体育成绩：
				</td>
				<td align="left">
					<html:text property="tcj" styleId="tcj" onkeyup="ckinpdata(this)" style="width:90px" readonly="true" maxlength="6"></html:text>
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
					德育成绩：
				</td>
				<td align="left">
					<html:text property="dcj"  styleId="dcj" onkeyup="ckinpdata(this)" style="width:90px"  maxlength="6"></html:text>
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
					美育成绩：
				</td>
				<td align="left">
					<html:text property="mcj"  styleId="mcj" onkeyup="ckinpdata(this)" style="width:90px"  maxlength="6"></html:text>
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
					学分绩：
				</td>
				<td align="left">
					<html:text property="xf"  styleId="xf" onkeyup="ckinpdata(this)" style="width:90px" readonly="true" maxlength="6"></html:text>
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
					奖励分：
				</td>
				<td align="left">
					<html:text property="jlf"  styleId="jlf" onkeyup="ckinpdata(this)" style="width:90px" readonly="true" maxlength="6"></html:text>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					&nbsp;
				</td>
				<td align="left">
					&nbsp;
				</td>
				<td align="right">
					惩罚分：
				</td>
				<td align="left">
					<html:text property="cff"  styleId="cff" onkeyup="ckinpdata(this)" style="width:90px" readonly="true" maxlength="6"></html:text>
				</td>
				
			</tr>
		</table>
		<div class="buttontool" align="center">
					<logic:notEqual value="view" name="flag">
						<button class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_hxxy_zhszcpmodi.do?act=save','xn')"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
				<!-- 保存提示信息 -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>