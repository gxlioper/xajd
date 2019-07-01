<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/kqgl/zjsy/js/zjsykq.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/kqgl/kqsh/js/kqsh.js"></script>
		<script type="text/javascript">
		function qqxszj(html){
			jQuery("#tbody_qqryxx").append(html);	
			}
		
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.id}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.id}");
		});
		
		</script>
	</head>		
	<body>	
		<html:form action="/zjsy_kqsh" method="post" styleId="KqshForm" onsubmit="return false;">
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;' >
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
						<span>考勤信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					    <tr>
							<th width="20%">学年学期</th>
							<td>
								<span>${KqshForm.xn}</span><span>${KqshForm.xqmc}</span>
							</td>
						  <th>月份</th>
							<td>
								<span>${KqshForm.toyf}</span>
							</td>
					    </tr>
					    <tr>
					        <th>周次</th>
							<td>
								<span>${KqshForm.tozc}</span>
							</td>
					        <th>
								班级
							</th>
							<td>
								<span>${KqshForm.bjmc}</span>
							</td>
							
						</tr>
					    <tr>
					   		 <th>应出勤人数</th>
								<td colspan="3">
									<span>${KqshForm.cqrs}</span>
								</td>
						</tr>
			      		  <tr>
							<th>
								备注
							</th>
							<td colspan="3">
								<span>${KqshForm.bz}</span>
							</td>
			      		</tr>
					</tbody>
				</table>
			   <table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td width='25%'>学号</td>
							<td width='20%'>姓名</td>
							<td width='20%'>病假次数</td>
							<td width='20%'>事假次数</td>
							<td width='20%'>旷课节数</td>
						</tr>
					</thead>
					<tbody id="tbody_qqryxx">
					  <logic:notEmpty name="kqinfoList">
						  	<logic:iterate id="i" name="kqinfoList" indexId="index01">
								<tr>
									<td name="xh">${i.xh}</td>
									<td>${i.xm}</td>
									<td>${i.bjcs}</td>
									<td>${i.sjcs}</td>
									<td>${i.kkjs}</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="kqinfoList">
							<tr>
								<td align="center" colspan="5">
									无异常考情学生
								</td>
							</tr>
						</logic:empty>
				    </tbody>
				</table>
			</div>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
				   <thead>
						<tr>
							<th colspan="4">
								<span>审批信息</span>
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
			</div>
			<div style="height: 30px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>