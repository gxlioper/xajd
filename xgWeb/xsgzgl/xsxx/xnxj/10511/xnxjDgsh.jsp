<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xnxj/10511/js/xnxj.js"></script>
		<script type="text/javascript">

			jQuery(function(){
				
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#id").val()+"&tt="+new Date().getTime());
				
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+jQuery("#splid").val()+"&shid="+jQuery("#shid").val());
				
			});
		</script>
	</head>
	<body>
		<html:form action="/xsxx_xnxj_xjtxgl" method="post" styleId="xnxjForm">
			<html:hidden property="id" styleId="id"/>
			<html:hidden property="xtgwid" styleId="xtgwid"/>
			<html:hidden property="splid" styleId="splid"/>
			<html:hidden property="shid" styleId="shid"/>
			<div style="overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xsxx/xnxj/10511/comm/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>学年小结信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td colspan="3"><bean:write name="hsdxnxjsqForm" property="xn"/></td>
						</tr>
						<tr>
							<th>
								自我小结
							</th>
							<td colspan="3" style="word-break:break-all;">
								<bean:write name="hsdxnxjsqForm" property="xjnr"/>
							</td>
						</tr>
					</tbody>
					
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
						<tr>
							<th>
								<font color="red">*</font>审核结果
							</th>
							<td colspan="3" id="shjgSpan">
								
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								审核意见
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xnxj&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px;" rows="5" 
											   onblur="checkLen(this,200);" styleId="shyj"
								></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
							<table width="100%" border="0" class="formlist">
			
								<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" id="btqd" onclick="saveXnxjSh();">
										确定
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

