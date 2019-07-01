<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xszz/jtcy.js"></script>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/xszz/xszzInit.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	</head>

	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commXszz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<!-- 学生基本情况 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>学生基本情况</span>
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
						<th align="right"  width="20%">
							姓名
						</th>
						<td>
							${rs.xm }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							性别
						</th>
						<td align="left">
							${rs.xb }
						</td>
						<th align="right">
							联系电话
						</th>
						<td>
							${rs.lxdh }
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
							院系
						</th>
						<td>
							${rs.xymc }
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
							班级
						</th>
						<td>
							${rs.bjmc }
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 学生基本情况 end-->
			<!-- 获得资助情况 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="5">
							<span>所获得资助情况（${kssj }——${jssj }）</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="height: 23px">
						<td align="center">
							项目名称
						</td>
						<td align="center">
							项目类别
						</td>
						<td align="center">
							级别
						</td>
						<td align="center">
							金额
						</td>
						<td align="center">
							申请时间
						</td>
					</tr>
					<logic:empty name="rsList">
						<tr>
							<td align="center" colspan="5">
								未获得任何资助
							</td>
						</tr>
					</logic:empty>
					<logic:notEmpty name="rsList">
						<logic:iterate name="rsList" id="xm" indexId="num">
							<logic:notEqual name="num" value="${rsNum - 1 }">
								<tr>
									<td align="center">
										${xm.xmmc }
									</td>
									<td align="center">
										${xm.xmlb }
									</td>
									<td align="center">
										${xm.xmzzjb }
									</td>
									<td align="center">
										${xm.xmzzje }
									</td>
									<td align="center">
										${xm.sqsj }
									</td>
								</tr>
							</logic:notEqual>
							<logic:equal name="num" value="${rsNum - 1}">
								<tr>
									<td align="center" colspan="3">
										总计
									</td>
									<td align="center" colspan="2">
										${xm.zje }
									</td>
								</tr>
							</logic:equal>
						</logic:iterate>
					</logic:notEmpty>
				</tbody>
			</table>
			<!-- 获得资助情况 end-->
			<table border="0" class="formlist" align="center" style="width: 100%">
				<tfoot>
					<tr>
						<td align="center">
							<div class="btn">
								<button type="button"  id="buttonSave" onclick="window.close();return false;" style="width: 80px">
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