<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/js/ydsq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/ydsq">
		<html:hidden property="ssydlx" value="${data.ssydlx}" styleId="ssydlx"/>
        <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
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
				<logic:notEqual value="10466" name="xxdm">				
					<thead>
							<tr>
								<th colspan="4">
									<span>原床位信息</span>
								</th>
							</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								楼栋名称
							</th>
							<td align="left">
								${cwxxData.ldmc}
							</td>
							<th align="right">
								寝室号
							</th>
							<td align="left">
								${cwxxData.qsh}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								床位号
							</th>
							<td align="left">
								${cwxxData.cwh}
							</td>
							<th align="right">
								寝室电话
							</th>
							<td align="left">
								${cwxxData.qsdh}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								收费标准
							</th>
							<td align="left">
								${cwxxData.sfbz}
							</td>
							<th align="right">
								所属年级
							</th>
							<td align="left">
								${cwxxData.nj}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								所属<bean:message key="lable.xb" />
							</th>
							<td align="left">
								${cwxxData.xymc}
							</td>
							<th align="right">
								所属班级
							</th>
							<td align="left">
								${cwxxData.bjmc}
							</td>
						</tr>
					</tbody>
				</logic:notEqual>
				<logic:equal value="10466" name="xxdm">
					<logic:notEqual value="stu" name="userType">
						<thead>
							<tr>
								<th colspan="4">
									<span>原床位信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th align="right" width="10%">
									楼栋名称
								</th>
								<td align="left">
									${cwxxData.ldmc}
								</td>
								<th align="right">
									寝室号
								</th>
								<td align="left">
									${cwxxData.qsh}
								</td>
							</tr>
							<tr>
								<th align="right" width="10%">
									床位号
								</th>
								<td align="left">
									${cwxxData.cwh}
								</td>
								<th align="right">
									寝室电话
								</th>
								<td align="left">
									${cwxxData.qsdh}
								</td>
							</tr>
							<tr>
								<th align="right" width="10%">
									收费标准
								</th>
								<td align="left">
									${cwxxData.sfbz}
								</td>
								<th align="right">
									所属年级
								</th>
								<td align="left">
									${cwxxData.nj}
								</td>
							</tr>
							<tr>
								<th align="right" width="10%">
									所属<bean:message key="lable.xb" />
								</th>
								<td align="left">
									${cwxxData.xymc}
								</td>
								<th align="right">
									所属班级
								</th>
								<td align="left">
									${cwxxData.bjmc}
								</td>
							</tr>
						</tbody>
					</logic:notEqual>
				</logic:equal>
				<thead>
						<tr>
							<th colspan="4">
								<span>宿舍异动申请</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							宿舍异动类型
						</th>
						<td align="left">
							<span style="color:red;">${data.ssydlxmc}</span>
						</td>
						<th align="right">
							学年/学期
						</th>
						<td align="left">
							${data.xn }
							${data.xqmc}
						</td>
					</tr>
					<% 
						String ssydlx= (String)request.getAttribute("ssydlx");//宿舍异动类型
					%>
					<tr>
						<th align="right" width="10%" id="yy">
							<% if("00".equals(ssydlx)){ %>退宿原因<% } %>
							<% if("01".equals(ssydlx)){ %>宿舍调整原因<% } %>
							<% if("03".equals(ssydlx)){ %>入住原因<% } %>
						</th>
						<td align="left">
							${data.tstzyymc}
						</td>
						<th align="right" id="sj">
							<% if("00".equals(ssydlx)){ %>退宿时间<% } %>
							<logic:equal value="11647" name="xxdm">
							<% if("01".equals(ssydlx)){ %>申请时间<% } %>
							</logic:equal>
							<logic:notEqual value="11647" name="xxdm">
							<% if("01".equals(ssydlx)){ %>宿舍调整时间<% } %>
							</logic:notEqual>
							<% if("03".equals(ssydlx)){ %>入住时间<% } %>
						</th>
						<td align="left" >
							${data.tstzsj}
						</td>
					</tr>
						<tr>
							<th align="right" width="10%">
								<% if("00".equals(ssydlx)){ %>退宿<% } %>
								<% if("01".equals(ssydlx)){ %>宿舍调整<% } %>
								<% if("03".equals(ssydlx)){ %>入住寝室<% } %>
							</th>
							<td colspan="3">
								<% if("00".equals(ssydlx)){ %>
									<span style="color:blue;">${data.tzqldmc}_${data.tzqqsh}_${data.tzqcwh}</span>
								<% } %>
								<% if("01".equals(ssydlx)){ %>
									<span style="color:blue;">${data.tzqldmc}_${data.tzqqsh}_${data.tzqcwh}</span>
									<img style="vertical-align: text-bottom;" src='images/ssyd/to.gif' ></img>
									<logic:notEqual name="data" property="tzhldmc" value="">
										<span style="color:blue;">${data.tzhldmc}_${data.tzhqsh}_${data.tzhcwh}</span>
									</logic:notEqual>
									<logic:equal name="data" property="tzhldmc" value="">
										<span style="color:blue;"></span>
									</logic:equal>
								<% } %>
								<% if("03".equals(ssydlx)){ %>
									<logic:notEqual name="data" property="tzqldmc" value="">
										<span style="color:blue;">${data.tzqldmc}_${data.tzqqsh}_${data.tzqcwh}</span>
									</logic:notEqual>
									<logic:equal name="data" property="tzqldmc" value="">
										<span style="color:blue;"></span>
									</logic:equal>
								<% } %>
							</td>
						</tr>
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
