<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/gygl/wsjc.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		</script>
	</head>

	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>卫生检查 - 卫生分情况</a>
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
							校区
						</th>
						<td align="left" width="30%">
							${rs.xqmc }
						</td>
						<th align="right" width="20%">
							楼栋
						</th>
						<td align="left">
							${rs.ldmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							所属层数
						</th>
						<td align="left">
							第${rs.cs }层
						<th align="right">
							寝室号
						</th>
						<td align="left">
							${rs.qsh }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							所属<bean:message key="lable.xb" />
						</th>
						<td align="left">
							${rs.xymc }
						</td>
						<th align="right">
							检查部门
						</th>
						<td align="left">
							${rs.jcbm }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							<logic:equal name="jczq" value="周">
								检查周次
							</logic:equal>
							<logic:equal name="jczq" value="日">
								检查时间
							</logic:equal>
						</th>
						<td align="left">
							<logic:equal name="jczq" value="周">
								第${rs.jczc }周
							</logic:equal>
							<logic:equal name="jczq" value="日">
								${rs.jcsj }
							</logic:equal>
						</td>
						<th align="right">
							<logic:equal name="lrxs" value="分数">
								卫生分
							</logic:equal>
							<logic:equal name="lrxs" value="等级">
								卫生等级
							</logic:equal>
						</th>
						<td align="left">
							<logic:equal name="lrxs" value="分数">
								${rs.wsffs }
							</logic:equal>
							<logic:equal name="lrxs" value="等级">
								${rs.wsfdj }
							</logic:equal>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							备注
						</th>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="bz" styleId="bz" rows="5"
								style="width: 500px" onblur="chLeng(this,250)"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<logic:notPresent name="rsArrList">
								本寝室无人居住
							</logic:notPresent>
							<logic:present name="rsArrList">
								<fieldset>
									<table width="100%" id="rsTable" class="tbstyle">
										<thead>	
											<tr align="center" style="cursor:hand">
												<td>学号</td>
												<td>姓名</td>
												<td>性别</td>
												<td>院系</td>
												<td>专业</td>
												<td>班级</td>
												<td>床位号</td>
											</tr>
										</thead>
										<logic:iterate name="rsArrList" id="s" indexId="index">
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
						<td colspan="4">
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
		</html:form>
	</body>
</html>
