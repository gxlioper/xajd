<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<link rel="stylesheet" href="xsgzgl/xszy/comm/xszy.css" type="text/css" media="all">
		<script type='text/javascript'>
		function saveZdpp(){ 
			var xszys = jQuery("#xszys").val();
			var qss = jQuery("#qss").val();
			if(0==xszys){
				showAlert("��δ���������֮�ѣ��޷�ƥ�䣡");
				return false;
				}
			if(0==qss){
				showAlert("��δ��������ң��޷�ƥ�䣡");
				return false;
				}
			var api = frameElement.api,W = api.opener;
			W.saveZdpp();
			closeDialog();
		}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/qsppgl" method="post" styleId="XszyQsppForm" onsubmit="return false;">
		<input type="hidden" id="xszys" value="${ppxqMap.xszys}"/>
		<input type="hidden" id="qss" value="${ppxqMap.qss}"/>
		<html:hidden property="nj" styleId="nj" value="${ppxqMap.nj}"/>
		<div class="class_xszy">
		<ul>
			<li class="xszy_li">
			<div class="con">
			  <p class="num_all" align="center">${ppxqMap.ssyxmc}����֮������<font color="#3399CC">${ppxqMap.xszys}</font>��,
			           ѧ��������<font color="#3399CC">${ppxqMap.qss}</font>��
			   </p>
			   <p align="center">
								����֮�ѣ��У�:<font color="#FF9933">${ppxqMap.xszy_man}</font>
								ѧ�����ᣨ�У�:<font color="#FF9933">${ppxqMap.man}</font></p>
			   <p align="center">
								����֮�ѣ�Ů��:<font color="#FF9933">${ppxqMap.xszy_woman}</font>
								ѧ�����ᣨŮ��:<font color="#FF9933">${ppxqMap.woman}</font></p>
			   <p align="center">ϵͳ���Ȱ��Ա�����Զ�ƥ�䣬�Ա������޷����㲻ƥ�䣬�����������ֶ�����

				 </p>
				 </div>
				 </li>
				 </ul>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="saveZdpp()">
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

