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
		
		<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
		<div class="tab" style="overflow-y:auto; overflow-x:hidden">
		<table class="formlist" width="95%">
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
				<th>寝室电话</th>
				<td>${rs.qsdh }</td>
			</tr>
			<tr>
				<th>收费标准</th>
				<td>${rs.sfbz }</td>
				<th>所属年级</th>
				<td>${rs.nj }</td>
			</tr>
			<tr>
				<th>所属<bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
				
				<th><%if(GyglNewInit.CWFPDX!=null&&"bjdm".equals(GyglNewInit.CWFPDX)){ %>所属班级<%}else if(GyglNewInit.CWFPDX!=null&&"zydm".equals(GyglNewInit.CWFPDX)){%>所属专业<%} %></th>
				<td><%if(GyglNewInit.CWFPDX!=null&&"bjdm".equals(GyglNewInit.CWFPDX)){ %>${rs.bjmc }<%}else if(GyglNewInit.CWFPDX!=null&&"zydm".equals(GyglNewInit.CWFPDX)){%>${rs.zymc }<%} %></td>
			</tr>

			<tr>
				<th>
					备注
				</th>
				<td colspan="3" style="word-break:break-all;width:100%">
					<html:textarea property='bz' style="width:95%" styleId="bz" rows='3' value="${rs.bz}" readonly="true"/>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>床位保留</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>是否保留</th>
					<td>
						${rs.sfbl }
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>
						保留备注
					</th>
					<td colspan="3" style="word-break:break-all;width:100%">
						<html:textarea property='bz' style="width:95%" styleId="bz" rows='2' value="${rs.blbz}" readonly="true"/>
					</td>
				</tr>
			</tbody>
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>住宿学生信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>姓名</th>
					<td>
						${rs.xm }
					</td>
					<th>学号</th>
					<td>${rs.xh }</td>
				</tr>
				<tr>
					<th>年级</th>
					<td>
						${rs.xsnj }
					</td>
					<th><bean:message key="lable.xb" /></th>
					<td>${rs.xsxymc }</td>
				</tr>
				<tr>
					<th>专业</th>
					<td>
						${rs.xszymc }
					</td>
					<th>班级</th>
					<td>${rs.xsbjmc }</td>
				</tr>
				<tr>
					<th>入住时间</th>
					<td>${rs.rzsj }</td>
					<th>入住原因</th>
					<td>${rs.rzyymc }</td>
				</tr>
				<tr>
					<th>联系方式</th>
					<td colspan="3">${rs.lxfs }</td>
				</tr>
			</tbody>
			</table>
			</div>
			<div class="border_01 formbox">
			<!--带有滚动条表单 标题start-->
			<h3 class="datetitle_01">
				<span>辅导员班主任信息</span>
			</h3>
				<div style="height: 150px;overflow-y:auto; overflow-x:hidden">
					<table width="100%" class="datelist tablenowrap" border="1">
						<thead>
							<tr>
								<th>
									职工号
								</th>
								<th>
									姓名
								</th>
								<th>
									联系电话
								</th>
								<th>
									职位
								</th>
								
							</tr>
						</thead>
						<tbody>
							<logic:iterate id="fdy" name="fdyList">
								<tr class="alt">
									<td>${fdy.zgh}</td>
									<td>${fdy.xm}</td>
									<td>${fdy.lxdh}</td>
									<td>${fdy.zw}</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			</div>
		<div>
		<table width="97%" class="formlist">
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
		</div>
	</html:form>
</body>
</html>
