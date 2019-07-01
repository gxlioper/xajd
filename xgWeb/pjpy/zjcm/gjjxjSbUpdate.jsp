<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript">	
	function toPrintOut(){//输出相应的打印页面
		showTips('处理数据中，请等待......');
		document.forms[0].action = "/xgxt/zjcmPjpy.do?method=gjjxjPrint";
		document.forms[0].submit();
	}
	</script>
	
	<body onload="">
		<html:form action="/zjcmPjpy">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="4" align="center">
							国家奖学金申报
						</td>
					</tr>
				</thead>
					<tr>
						<td align="right" width="20%">
							学号：
						</td>
						<td align="left" width="30%">
							<html:hidden name="stuInfo" property="xh"/>
							${stuInfo.xh }
						</td>
						<td align="right" width="20%">
							评奖学年：
						</td>
						<td align="left" width="30%">
							<html:hidden name="stuInfo" property="xn"/>
							<html:select name="stuInfo" property="xn" style="" onchange="" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>			
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td align="left">
							${stuInfo.xm }
						</td>
						<td align="right">
							评奖学期：
						</td>
						<td align="left">
							<html:hidden name="stuInfo" property="xq"/>
							<html:select name="stuInfo" property="xq" style="" onchange="" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
						<td align="left">
							${stuInfo.xb }
						</td>
						<td align="right">
							身份证号：
						</td>
						<td align="left">
							${stuInfo.sfzh }
						</td>
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							${stuInfo.nj }
						</td>
						<td align="right">
							民族：
						</td>
						<td align="left">
							${stuInfo.mzmc }
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							${stuInfo.xymc }
						</td>
						<td align="right">
							政治面貌：
						</td>
						<td align="left">
							${stuInfo.zzmmmc }
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							${stuInfo.zymc }
						</td>
						<td align="right">
							出生日期：
						</td>
						<td align="left">
							${stuInfo.csrq }
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							${stuInfo.bjmc }
						</td>
						<td align="right">
							入学日期：
						</td>
						<td align="left">
							${stuInfo.rxrq }
						</td>
					</tr>
					<tr>
						<td align="right">
							获奖情况：
						</td>
						<td align="left">
							${otherInfo.rymc }
						</td>
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							${stuInfo.lxdh }
						</td>
					</tr>
					<tr>
						<td align="right">
							上学期成绩排名：
						</td>
						<td align="left">
							${otherInfo.sxqcj }
						</td>
						<td align="right">
							本学期成绩排名：
						</td>
						<td align="left">
							${otherInfo.bxqcj }
						</td>
					</tr>
					<tr>
						<td align="right">
							上学期综合排名：
						</td>
						<td align="left">
							${otherInfo.sxqzhfpm }
						</td>
						<td align="right">
							本学期综合排名：
						</td>
						<td align="left">
							${otherInfo.bxqzhfpm }
						</td>
					</tr>
					<tr>
						<td align="right">
							上学期综合考评分：
						</td>
						<td align="left">
							${otherInfo.sxqzhf }
						</td>
						<td align="right">
							本学期综合考评分：
						</td>
						<td align="left">
							${otherInfo.bxqzhf }
						</td>
					</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<logic:notEqual name="doType" value="view">
					<button class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/zjcmPjpy.do?method=gjjxjSbUpdate&doType=save','')"
						style="width: 80px">
						保	存
					</button>
					&nbsp;&nbsp;
					</logic:notEqual>
					<button class="button2" style="width: 80px"
						onClick="toPrintOut();">
						打	印
					</button>
					&nbsp;&nbsp;
					<button class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						关	闭
					</button>
					</td>
				</tr>
			</table>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
