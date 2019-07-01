<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="xsgzgl.gygl.comm.GyglNewInit"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function getQsh(){
			jQuery.setAjax({async:false});

			jQuery.getJSON('',{lddm:jQuery('#lddm').val()},function(){
				
			});

			jQuery.setAjax({async:true});
		}
	</script>
</head>
<body>
	
	<html:form action="/gyglnew_cwgl" method="post">
		<input type="hidden" id="refreshParent" value="true"/>
		<input type="hidden" name="pkValue" value="${rs.pkValue }"/>	
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>床位信息修改</a>
			</p>
		</div>		
		--%><div class="prompt" id="span_qsh" style="display: none">
	       <h3><span>提示：</span></h3>
	       <p>该寝室号在本楼栋下已存在！<br/></p>
	   	</div>
		
		<div class="tab" style="overflow-y:auto; overflow-x:hidden">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="16%">姓名</th>
					<td width="34%">
						${rs.xm }
					</td>
					<th width="16%">学号</th>
					<td width="34%">${rs.xh }</td>
				</tr>
				<tr>
					<th>年级</th>
					<td>
						${rs.nj }
					</td>
					<th><bean:message key="lable.xb" /></th>
					<td>${rs.xymc }</td>
				</tr>
				<tr>
					<th>专业</th>
					<td>
						${rs.zymc }
					</td>
					<th>班级</th>
					<td>${rs.bjmc }</td>
				</tr>
				<tr>
					<th>是否住校</th>
					<td>${rs.sfzs }</td>
					<logic:equal name="xxdm" value="13798">
						<th>辅导员</th>
						<td>${rs.fdyxm}</td>
					</logic:equal>
					<logic:notEqual name="xxdm" value="13798">
						<th>联系方式</th>
						<td>${rs.sjhm}</td>			
					</logic:notEqual>
				</tr>
			</tbody>
			<logic:equal value="是" name="rs" property="sfzs">
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>床位信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th align="right" width="16%">
						楼栋名称
					</th>
					<td align="left" width="34%" nowrap="nowrap">
						${rs.ldmc }
					</td>
					
					<th  width="16%">
						寝室号				
					</th>
					<td  width="34%">
						${rs.qsh }
					</td>
				</tr>
	
				<tr>
					<th>床位号</th>
					<td>
						${rs.cwh }
					</td>
					<th>所属年级</th>
					<td>${rs.cwnj }</td>
				</tr>
				<tr>
					<th>所属<bean:message key="lable.xsgzyxpzxy" /></th>
					<td>${rs.cwxymc }</td>
					
					<th><%if("bjdm".equals(GyglNewInit.CWFPDX)){ %>所属班级<%} %></th>
					<td><%if("bjdm".equals(GyglNewInit.CWFPDX)){ %>${rs.cwbjmc }<%} %></td>
				</tr>
	
				<tr>
					<th>
						备注
					</th>
					<td colspan="3" style="word-break:break-all;width:100%">
						<html:textarea property='bz' style="width:95%" styleId="bz" rows='7' value="${rs.bz}" readonly="true"/>
					</td>
				</tr>
				</tbody>
			</logic:equal>
			<logic:equal value="否" name="rs" property="sfzs">
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>走读备注</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							走读备注
						</th>
						<td colspan="3" style="word-break:break-all;width:95%;height:40px">
							<%--<html:textarea property='bzxbz' style="width:95%" styleId="bzxbz" rows='3' value="${rs.bzxbz}" readonly="true"/>
						--%>
						${rs.bzxbz}
						</td>
					</tr>
				</tbody>
			</logic:equal>
			</table>
			</div>
		<table width="97%">
			<tfoot>
					<tr align="right">
						<td colspan="5">
							<div class="btn">
								<button type="button" name="关闭" onclick="Close();return false;">
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
