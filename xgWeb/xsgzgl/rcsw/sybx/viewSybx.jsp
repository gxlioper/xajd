<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/sybx/js/updateSybx.js"></script>
		
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/xgservice.js"></script>
		<script type='text/javascript' src="js/moveDiv.js"></script>
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/messageFunction.js"></script>
		
	</head>
	<body style="width:699px">
		
		<html:form action="/rcsw_sybx" method="post" styleId="sybxForm">
			<html:hidden property="guid"/>
			
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>商业保险信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>学年</th>
							<td>
								${rs.xn }
							</td>
							<th>城镇居民医疗<br/>保险金额</th>
							<td>
								${rs.czjmylbxje }
							</td>
					    </tr>
					    <tr>
					    	<th>城镇居民医疗保险参保起始日期</th>
							<td>
								${rs.czjmylbxcbqsrq }
							</td>
							<th>城镇居民医疗保险参保结束日期</th>
							<td>
								${rs.czjmylbxcbjsrq }
							</td>
					    </tr>
					    <tr>
					    	<th>商业保险金额</th>
							<td>
								${rs.sybxje }
							</td>
							<th>商业保险参保<br/>起始日期</th>
							<td>
								${rs.sybxcbqsrq }
							</td>
					    </tr>
					    <tr>
							<th>商业保险参保<br/>结束日期</th>
							<td>
								${rs.sybxcbjsrq }
							</td>
							<th>保险总金额</th>
							<td>
								${rs.bxje }
							</td>
					    </tr>
					    <tr>
					    	<th>增加原因</th>
							<td>
								${rs.zjyymc }
							</td>
							<th>参保人员类别</th>
							<td>
								${rs.cbrylbmc }
							</td>
					    </tr>
					    <tr>
							<th>缴费人员类别</th>
							<td>
								${rs.jfrylbmc }
							</td>
							<th>身份证签发机关</th>
							<td>
								${rs.sfzqfjg }
							</td>
					    </tr>
					    <tr>
							<th>身份证有效期限<br/>起始日期</th>
							<td>
								${rs.sfzyxqxqsrq }
							</td>
							<th>身份证有效期限<br/>截止日期</th>
							<td>
								${rs.sfzyxqxjzrq }
							</td>
					    </tr>
					    <tr>
							<th>监护人姓名</th>
							<td>
								${rs.jhrxm }
							</td>
							<th>监护人身份证号</th>
							<td>
								${rs.jhrsfzh }
							</td>
					    </tr>
					    <tr>
							<th>
								通讯地址
							</th>
							<td colspan="4">
								${rs.txdz }
							</td>
			     		 </tr>
					    <tr>
							<th>
								备注
							</th>
							<td colspan="4" style="word-wrap: break-word!important; word-break: break-all!important">
								${rs.bz }
							</td>
			     		 </tr>
					</tbody>
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
			</div>
		</html:form>
	</body>
</html>