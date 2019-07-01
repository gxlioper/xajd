<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"  src="js/sztzFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script language="javascript"  src="js/gygl/gyglTyFunction.js"></script>
	</head>

	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zjcmGygl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<!-- 卫生检查情况 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>卫生检查情况</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="height: 23px">
						<th align="right" width="20%">
							校区：
						</th>
						<td align="left" width="30%">
							<bean:write name="rs" property="xqmc"/>
						</td>
						<th align="right" width="20%">
							楼栋：
						</th>
						<td align="left">
							<bean:write name="rs" property="ldmc"/>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							所属层数：
						</th>
						<td align="left">
							第<bean:write name="rs" property="cs"/>层
						<th align="right">
							寝室号：
						</th>
						<td align="left">
							<bean:write name="rs" property="qsh"/>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							学年：
						</th>
						<td align="left">
							<html:hidden name='rs' property="xn" styleId="xn"/>
							<html:select name="rs" property="xn" style="" styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</td>
						<th align="right">
							学期：
						</th>
						<td align="left">
							<html:hidden name='rs' property="xq" styleId="xq"/>
							<html:select name="rs" property="xq" style="" styleId="xq" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							检查时间：
						</th>
						<td align="left">
							<bean:write name="rs" property="jcsj"/>
						</td>
						<th align="right">
							<font color="red">*</font>卫生检查分：
						</th>
						<td align="left">
							<html:text name='rs' property="fs" styleId="fs" onblur="chMax(this,100)"
								onkeypress="return onlyNum(this,5)"
								maxlength="3" style="width:100%;ime-mode:disabled"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<logic:notPresent name="rsList">
								本寝室无人居住
							</logic:notPresent>
							<logic:present name="rsList">
							<fieldset>
								<legend>
									本寝室居住人数： <bean:write name="rsNum" />人
								</legend>
								<table width="100%" id="rsTable" class="tbstyle">
									<thead>	
										<tr align="center" style="cursor:hand">
											<logic:iterate id="tit" name="topTr" offset="0">
												<td id="<bean:write name="tit" property="en"/>" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="rsList" id="s" indexId="index">
										<tr>
											<logic:iterate id="v" name="s">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</fieldset>
							</logic:present>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
							<div class="btn">
								<logic:notEqual name="doType" value="view">
									<button id="buttonSave" onclick="saveUpdate('/xgxt/zjcmGygl.do?method=wsjcView&doType=save','fs')"
										style="width: 80px">
										保	存
									</button>
								</logic:notEqual>
								&nbsp;&nbsp;
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									关	闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
