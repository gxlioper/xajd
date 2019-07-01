<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpyxcxywh" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkVal }"/>
		<div class="title">
 			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 审核 - 奖学金审核
			</div>
		</div>
		
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						单个审核
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
					学年：
				</td>
				<td align="left" width="32%">
					${rs.xn }
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
					${rs.xq }
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
					奖学金：
				</td>
				<td align="left">
					${rs.jxjmc }
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
					奖励金额：
				</td>
				<td align="left">
					${rs.jlje }
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
					担任职务：
				</td>
				<td align="left">
					${rs.drzw }
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
					审核：
				</td>
				<td align="left">
					<html:select property="yesNo" styleId="yesNo" >
						<html:option value="未审核">未审核</html:option>
						<html:option value="通过">通过</html:option>
						<html:option value="不通过">不通过</html:option>
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
					操行成绩：
				</td>
				<td align="left">
					${rs.cxcj }
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					平均成绩：
				</td>
				<td align="left">
					${rs.pjcj }
				</td>
				<td align="right">
					平均成绩排名：
				</td>
				<td align="left">
					${rs.pjcjpm }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					总成绩：
				</td>
				<td align="left">
					${rs.zcj }
				</td>
				<td align="right">
					总成绩排名：
				</td>
				<td align="left">
					${rs.pm }
				</td>
			</tr>
			<tr>
				<td align="right">
					申请理由：
				</td>
				<td align="left" colspan="3">
					${rs.sqly }
				</td>
			</tr>
			<tr>
				<td align="right">
					审核意见：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yj" styleId="yj" style="width:95%" rows="6"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<logic:notEqual value="view" name="act">
						<button class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_xcxy_jxjshone.do?act=save','')"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_cjprint" onclick="showTopWin('pjpy_shcbys_cjprint.do?xh='+document.getElementById('xh').value,700,600)">
						学生成绩单
					</button>
				</div>
				<!-- 保存提示信息 -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>