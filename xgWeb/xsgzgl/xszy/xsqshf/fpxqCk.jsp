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
		jQuery(function(){
			jQuery("#btn_fh").bind("click",function(){
				document.location.href="xszyXsqshf.do?method=getQshfxxList";
			});
		});
		function savePlfp(){ 
			var ssyxdm = jQuery("#ssyxdm").val();
			var api = frameElement.api,W = api.opener;
			W.savePlfp(ssyxdm);
			closeDialog();
		}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xszyXsqshf" method="post" styleId="XszyQshfForm" onsubmit="return false;">
		<!-- �๦�ܲ����� -->
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li>
						<a id="btn_fh" class="btn_fh"> �� �� </a>
					</li>
				</ul>
			</div>
		</div>
		<div class="class_xszy">
		<ul>
			<li class="xszy_li">
			<div class="con">
			  <p class="num_all" align="center"><font color="#3399CC">${nj}���ѧ԰������${qsxxAllMap.qss}����
			  ������${qsxxAllMap.man}��Ů����${qsxxAllMap.woman}
			  </font>
			   </p>
				<table width="100%">
									<thead>
										<tr>
											<td>Ժϵ</td>
											<td>�ѷ�������������</td>
											<td>�ѷ���Ů��������</td>
										</tr>
									</thead>
									<tbody id="fpxq">
									<logic:present name="fpXqList">
										<logic:iterate id="fpsq" name="fpXqList" indexId="i">
											<tr>
												<td>
												${fpsq.ssyxmc }
												</td>
												<td>
												${fpsq.man }
												</td>
												<td>
												${fpsq.woman }
												</td>
											
												</td>
											</tr>
										</logic:iterate>
									</logic:present>
									<logic:empty name="fpXqList">
											<tr>
												<td colspan="6" align="center">�޷�����Ϣ��</td>
											</tr>
										</logic:empty>
									</tbody>
								</table>
				 
				 </div>
				 </li>
				 </ul>
			    <div>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

