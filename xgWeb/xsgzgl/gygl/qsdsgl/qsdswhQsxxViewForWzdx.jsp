<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script language="javascript">
		
		/*function searchRs(){
			allNotEmpThenGo('gyglnew_ldgl_ldgl.do');
		}*/

		function zxsxxView(xh) {
			showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh);
		}
				
		</script>
	</head>
	<body>

		<html:form action="/gyglnew_ldgl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="text" id="nouse" style="display: none"/>
			<!-- 隐藏域 -->
			<!-- 模块类型 -->
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:289px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>${qsh}寝室住宿明细</span>
							</th>
						</tr>
					</thead>
				</table>
				<logic:notEmpty name="rsList">
				<div class="con_overlfow" >
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>学号</td>
								<td>姓名</td>
								<td>学院</td>
								<td>专业</td>
								<td>班级</td>
								<td>床位号</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rsList" id="s" indexId="index">	
								<tr>									
										<td nowrap>
											<a href='javascript:void(0);' class='name' onclick='zxsxxView("<bean:write name="s" property="xh"/>");return false;'><bean:write name="s" property="xh"/></a>
										</td>
										<td nowrap>
											<bean:write name="s" property="xm"/>
										</td>
										<td nowrap>
											<bean:write name="s" property="xymc"/>
										</td>
										<td nowrap>
											<bean:write name="s" property="zymc"/>
										</td>
										<td nowrap>
											<bean:write name="s" property="bjmc"/>
										</td>
										<td nowrap>
											<bean:write name="s" property="cwh"/>
										</td>									
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
				</logic:notEmpty>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
							<div class="btn">
								<button type="button" type="button" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>
