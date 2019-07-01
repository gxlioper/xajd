<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<link rel="stylesheet" href="xsgzgl/xszy/comm/xszy.css"
			type="text/css" media="all">
			<script type='text/javascript'>
		function saveKyxbj(){ 
			var dlyq = jQuery("#dlyq").val();
			var api = frameElement.api,W = api.opener;
			W.saveKyxbj(dlyq);
			closeDialog();
		}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xszygl" method="post" styleId="XszyglForm"
			onsubmit="return false;">
			<div class="class_xszy">
				<ul>
					<li class="kyxbj_li">
						<div class="con">
							<p style="font-size: 14px;" align="left">
								此操作会将选择的新生之友标记为其他院系匹配，本部门将无法再进行匹配寝室。求是学院管理员会根据大类要求重新分配到其他院系。
							</p>
							<p align="center">
								<font color="#FF9933">大类要求</font>
								<html:text property="dlyq" styleId="dlyq" maxlength="64"
									style="width:200px"></html:text>
							</p>
						</div>
					</li>
				</ul>
				<div>
					<table width="100%" border="0" class="formlist"
						style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" onclick="saveKyxbj()">
											确定
										</button>
										<button type="button" onclick="Close();return false;">
											取消
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

