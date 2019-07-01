<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpyxcxywh" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
 			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 学生成绩维护 - 学生操行分信息维护
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
					学期：
				</td>
				<td align="left">
					<html:select property="xq" styleId="xq" style="width:95px">
						<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
					</html:select>
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
					操行项目：
				</td>
				<td align="left">
					<html:text property="cxxm" styleId="cxxm" style="width:180px"></html:text>
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
					操行分数：
				</td>
				<td align="left">
					<html:text property="cxfs" styleId="cxfs" style="width:50px" maxlength="6" onkeyup="ckinpdata(this)"></html:text>
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
					操行类别：
				</td>
				<td align="left">
					<html:select property="cxlb" styleId="cxlb">
						<html:option value="1">加分</html:option>
						<html:option value="0">扣分</html:option>
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
					
				</td>
				<td align="left">
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
					&nbsp;
				</td>
				<td align="left">
					&nbsp;
				</td>
				<td align="right">
					
				</td>
				<td align="left">
				</td>
				
			</tr>
		</table>
		<div class="buttontool" align="center">
					<logic:notEqual value="view" name="act">
						<button class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_xcxy_cxfupdate.do?act=save','')"
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