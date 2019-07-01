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
				<em>您的当前位置：</em><a>卫生检查 - 基本情况</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/gzdxWsjc">
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
							学号
						</th>
						<td align="left" width="30%">
							${rs.xh }
						</td>
						<th align="right" width="20%">
							姓名
						</th>
						<td align="left">
							${rs.xm }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							年级
						</th>
						<td align="left">
							${rs.nj }
						</td>
						<th align="right">
							校区
						</th>
						<td align="left">
							${rs.xqmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td align="left">
							${rs.xymc }
						</td>
						<th align="right">
							楼栋
						</th>
						<td align="left">
							${rs.ldmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							专业
						</th>
						<td align="left">
							${rs.zymc }
						</td>
						<th align="right">
							层数
						</th>
						<td align="left">
							${rs.cs }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							班级
						</th>
						<td align="left">
							${rs.bjmc }
						</td>
						<th align="right">
							寝室号
						</th>
						<td align="left">
							${rs.qsh }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							政治面貌
						</th>
						<td align="left">
							${rs.zzmmmc }
						</td>
						<th align="right">
							检查部门
						</th>
						<td align="left">
							${rs.bmmc }
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
								<logic:notEqual name="doType" value="view">
									<font color="red">*</font>
								</logic:notEqual>
								卫生分
							</logic:equal>
							<logic:equal name="lrxs" value="等级">
								卫生等级
							</logic:equal>
						</th>
						<td align="left">
							<logic:equal name="doType" value="view">
								<logic:equal name="lrxs" value="分数">
									${rs.wsffs }
								</logic:equal>
								<logic:equal name="lrxs" value="等级">
									${rs.wsfdj }
								</logic:equal>
							</logic:equal>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							备注
						</th>
						<td align="left" colspan="3">						
							<html:textarea name="rs" property="jcbz" styleId="bz" rows="5"
								style="width: 500px" onblur="chLeng(this,250)" readonly="true"/>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<button type="button" id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									关	闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
