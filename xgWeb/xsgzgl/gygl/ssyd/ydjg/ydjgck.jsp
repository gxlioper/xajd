<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/js/ydjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/ydjg">
		<html:hidden property="ssydlx" value="${data.ssydlx}" styleId="ssydlx"/>
		<div style='tab;width:100%;height:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>宿舍异动信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								宿舍异动类型
							</th>
							<td align="left" colspan="3">
								<span style="color:red;">${data.ssydlxmc}</span>
							</td>
						</tr>
						<tr>
							<th align="right">
								学年/学期
							</th>
							<td align="left" >
								${data.xn} &nbsp; ${data.xqmc}
							</td>
							<th align="right">
								记录时间
							</th>
							<td align="left">
								${data.czsj}
							</td>
						</tr>
						<% 
							String ssydlx=((HashMap<String,String>)request.getAttribute("data")).get("ssydlx");//宿舍异动类型
						%>
						<tr>
							<th align="right" width="10%" id="yy">
								<% if("00".equals(ssydlx)){ %>退宿原因<% } %>
								<% if("01".equals(ssydlx)){ %>宿舍调整原因<% } %>
								<% if("03".equals(ssydlx)){ %>入住原因<% } %>
							</th>
							<td align="left">
								${data.tsyymc}
							</td>
							<th align="right" id="sj">
								<% if("00".equals(ssydlx)){ %>退宿时间<% } %>
								<% if("01".equals(ssydlx)){ %>宿舍调整时间<% } %>
								<% if("03".equals(ssydlx)){ %>入住时间<% } %>
							</th>
							<td align="left" >
								${data.tstzsj}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<% if("00".equals(ssydlx)){ %>退宿寝室<% } %>
								<% if("01".equals(ssydlx)){ %>宿舍调整<% } %>
								<% if("03".equals(ssydlx)){ %>入住寝室<% } %>
							</th>
							<td colspan="3">
								<% if("00".equals(ssydlx)){ %>
									<span style="color:blue;">${data.tsqs}</span>
								<% } %>
								<% if("01".equals(ssydlx)){ %>
									<span style="color:blue;">${data.tsqs} </span>
									<img style="vertical-align: text-bottom;" src='images/ssyd/to.gif' ></img>
									<span style="color:blue;">${data.rzqs}</span>
								<% } %>
								<% if("03".equals(ssydlx)){ %>
									<span style="color:blue;">${data.rzqs}</span>
								<% } %>
							</td>
						</tr>
						<logic:equal value="1" name ="zsfxs">
							<tr>
								<th align="right" style="width: 10%">
									住宿费
								</th>
								<td colspan="3">
									<logic:notEmpty name="data" property="jflx">
										${data.jflx} ${data.zsfje} 元
									</logic:notEmpty>
								</td>
							</tr>
						</logic:equal>
							<tr>
								<th align="right" style="width: 10%">
									附件信息
								</th>
								<td colspan="3">
										<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
										<html:hidden name="data" property="fjxx" styleId="fjxx"/>
										<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												var gid = jQuery('#fjxx').val();
												jQuery.MultiUploader_q({
													gid : gid
													});
											});
										</script>
									</td>
							</tr>
						<tr>
							<logic:notEqual value="12303" name = "xxdm">
								<th align="right" width="10%">
									备注
								</th>
							</logic:notEqual>
							<logic:equal value="12303" name = "xxdm">
								<th align="right" width="10%">
									申请理由
								</th>
							</logic:equal>
							<td colspan="3">
								${data.bz}
							</td>
						</tr>
					</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
