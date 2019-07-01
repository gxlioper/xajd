<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
		<script language="javascript" src="js/xsgyglFunction.js"></script>			
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 申请 - 精神文明活动 </a>
			</p>
		</div>
		<!-- 标题 end-->
			<html:form action="/zgdzdx_Gygl" method="post">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">			 					 
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>活动情况</span>
						</th>
					</tr>
				</thead>
				<tbody>	
				<tr>
					<th height="22" width="15%" align="right">
						活动名称：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="hdmc"/>
					</td>
					<th height="22" align="right" width="15%">
						主办单位：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="zbdw"/>
					</td>
				</tr>
				<tr>
					<th height="22" align="right" width="20%">
						负责人姓名：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="fzrxm"/>
					</td>
					<th height="22" align="right" width="20%">
						负责人联系方式：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="fzrlxfs"/>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						经手人姓名：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="jsrxm"/>
					</td>
					<th height="22" align="right">
						经手人联系方式：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="jsrlxfs"/>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						活动地点：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="hddd"/>
					</td>
					<th height="22" align="right">
						参加人数：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="cjrs"/>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						活动开始日期：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="hdksrq"/>					
					</td>
					<th height="22" align="right">
						活动结束日期：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="hdjsrq"/>
					</td>					
				</tr>
				<tr>
					<th height="22" align="right">
						活动申请人：
					</th>
					<td height="22" align="left">
					    <bean:write name="rs" property="sqrxm"/>
					</td>
					<th height="22" align="right">
						申请时间：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="sqsj"/>				
					</td>					
				</tr>
				<tr>
					<th height="22" align="right">
						审核状态：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="shzt"/>				
					</td>
					<th height="22" align="right">
						
					</th>
					<td height="22" align="left">
					   
					</td>					
				</tr>
				<tr>
					<th height="22" align="right">
						活动内容：
					</th>
					<td height="22" align="left" colspan="3">
						<bean:write name="rs" property="hdnr"/>	
					</td>					
				</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									关	闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>				
		</logic:notEmpty>				
		</html:form>		
	</body>

</html>
