<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript' src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/pjpy/ghxy/grjfsq.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			onShow();
		})
	</script>
	<body >
		<html:form action="/ghxy_ryjf.do">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：${title }
				</div>
			</div>

			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm" />
			<input type="hidden" id="url" name="url"
				value="/ghxy_ryjf.do?method=grjfsq" />
			<input type="hidden" id="doType" value="" />
			<fieldset>
				<legend>
					学生基本信息
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>个人信息</b>
							</td>
						</tr>
					</thead>
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font style="color: red">*</font>学号：
						</td>
						<td align="left" width="35%">
							<logic:equal value="stu" name="userType">
								<html:text styleId="xh" property="xh"
									style="width:100%;heigh:100%" value="${rs.xh}" readonly="true" />
							</logic:equal>

							<logic:notEqual value="stu" name="userType">
								<html:text property="xh" styleId="xh" readonly="readonly"
									onchange="checkXhExists($('getStuInfo').value);"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</logic:notEqual>
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">
							<input type="hidden" name="xn" value="${xn }"/>
							${xn }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							学期：
						</td>
						<td align="left">
							<input type="hidden" name="xq" value="${xqdm }"/>
							${xq }
						</td>
						<td align="right">
							年级：
						</td>
						<td align="left">
							${rs.nj }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							${rs.xm }
						</td>

						<td align="right">
							性别：
						</td>
						<td align="left">
							${rs.xb }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="15%">
							院系：
						</td>
						<td align="left">
							${rs.xymc }
						</td>

						<td align="right">
							专业：
						</td>
						<td align="left">
							${rs.zymc }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							班级：
						</td>
						<td align="left">
							${rs.bjmc }
						</td>
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							${rs.lxdh }
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					相关获奖情况
				</legend>
				<p>
					<input  value="+"
						onclick="trAdd('flag1','add','numAdd1','rzqk');"
						style="width: 20px" />
					<input  value="-" onclick="trDel('flag1','delRow1');"
						style="width: 20px" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
					<input type="text" name="numAdd" id="numAdd1" style="width: 20px"
						onblur="trAdd('flag1','madd','numAdd1','rzqk')">
					&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
					<input type="text" name="numDel" id="numDel1" style="width: 20px"
						onblur="trDelAll('flag1','numDel1')">
					&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<table class="tbstyle" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT"
									class="tbstyle">
									<!-- 打印时第一行不显示- -->
									<thead style="height: 10px">
										<tr>
											<td align="center" nowrap="nowrap" style='width:15%'>
												选定删除行
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												获奖时间
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												获奖名称
											</td>
											<td align="center" nowrap="nowrap" style='width:35%'>
												获奖事由
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												个人申请的获奖分
											</td>
										</tr>
									</thead>
									<tbody width="100%" align="center" class="tbstyle" id="flag1">
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>

			<div class="buttontool" align="center">
				<span class="openbutton">
				<logic:equal value="yes" name="writeAble">
				 <logic:equal value="true" name="isApply">
						<button type="button" class="button2" id="buttonSave"
							onclick="save('/xgxt/ghxy_ryjf.do?method=grjfsq&doType=add');"
							style="width: 80px">
							保 存
						</button>
					</logic:equal> 
				</logic:equal>	
				</span>
			</div>
			<logic:present name="msg">
				<hidden type="hidden" id="msg" value="${msg}" />
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
