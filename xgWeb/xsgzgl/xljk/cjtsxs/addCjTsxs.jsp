<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xljk/cjtsxs/js/cjtsxs.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			
		})
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xljk_cjtsxs" method="post" styleId="cjtsxsForm" onsubmit="return false;">
		<html:hidden property="lrr" />
		<html:hidden property="lrsj" />
		<html:hidden property="xn" />
		<html:hidden property="xq" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>春季特殊问题学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${cjtsxsForm.xn}
							</td>							
							<th>学期</th>
							<td>
								${cjtsxsForm.xqmc}
							</td>
						</tr>
						<tr>
							<th><span><font color="red">*</font></span>
								问题描述
								<br /><font color="red">&lt;限1000字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='wtms' style="width:98%" styleId="wtms" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
									<button type="button" onclick="save('save');">
										保存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

