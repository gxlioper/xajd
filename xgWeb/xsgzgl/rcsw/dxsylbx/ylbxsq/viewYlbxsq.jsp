<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.ylsqid}&tt="+ new Date().getTime());
			});
	   </script>

	</head>
	<body>

		<html:form action="/rcsw_dxsylbx_ylbxsqgl" method="post"
			styleId="ylbxsqForm">
		
		<html:hidden property="ylsqid" styleId="ylsqid" />	
			
			<div style='tab;width:100%;height:430px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>医疗保险申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>学年</th>
							<td>
								${rs.xn}
							</td>
							<th>学期</th>
							<td>
								${rs.xqmc}
							</td>
					    </tr>
					    
					    <tr>
							<th>补助类型</th>
							<td colspan="3" >
							<logic:notEmpty name="czqebzrymcsList">
							<logic:iterate name="czqebzrymcsList" id="s"  indexId="i" >
								${s.czqebzmc}
								<br/>
							</logic:iterate>
							</logic:notEmpty>
							</td>
					    </tr>
					    
					    
					     <tr>
							<th>证件所有人姓名</th>
							<td>
								${rs.zjsyrxm}
							</td>
							<th>证件号</th>
							<td>
								${rs.zjh}
							</td>
					    </tr>
					    
					    
					    <tr>
							<th>参保状况</th>
							<td colspan="3" >
								<logic:notEmpty name="cbzkmcsList">
									<logic:iterate name="cbzkmcsList" id="s"  indexId="i" >
										${s.cbzkmc}
										<br/>
									</logic:iterate>
								</logic:notEmpty>
								
								<logic:notEmpty name="qtcbzkval" >
									其它( ${qtcbzkval} )			   
								</logic:notEmpty>
							</td>
					    </tr>
					    <%--
					    <tr>
							<th>参保时间</th>
							<td colspan="3">
								${rs.cbsj}
							</td>
					    </tr>
					    --%>
					    <tr>
							<th>
								申请理由
								
							</th>
							<td colspan="3">
								${rs.sqly}
							</td>
			      		</tr>
			      		
					</tbody>
				</table>
				
				<logic:notEqual value="无需审核" name="shztmc">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" id="shlccx">

								</td>
							</tr>

						</tbody>

					</table>
				</logic:notEqual>
				
			</div>
				<table width="100%" border="0" class="formlist">
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
		</table>
		</html:form>
	</body>
</html>

