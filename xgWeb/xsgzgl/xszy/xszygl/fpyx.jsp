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
		function saveFpyx(){ 
			var bjyx = jQuery("#bjyx").val();
			if(""==bjyx){
				showAlert("��ѡ��Ժϵ��");
				return false;
				}
			var api = frameElement.api,W = api.opener;
			W.saveFpyx(bjyx);
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
						<div class="con" >
						 <div>
							<p style="font-size: 14px; " align="left">
								��<font color="blue">${xm }</font>��ʦ���½���Ժϵ���䣬����󽫰��µ�Ժϵ��������ƥ�䡣
							</p>
						 </div>
						 <div>
							<p align="center">
								<font color="#FF9933">����Ժϵ</font>
								<html:select property="bjyx" styleId="bjyx" style="width:300px">
								    <html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</p>
						</div>
						</div>
					</li>
				</ul>
				</div>
				<div style="height:30px">
				</div>
				<div>
					<table width="100%" border="0" class="formlist"
						style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" onclick="saveFpyx()">
											ȷ��
										</button>
										<button type="button" onclick="Close();return false;">
											ȡ��
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

