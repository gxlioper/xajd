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
			  <p class="num_all" align="center"><font color="#3399CC">${nj}���ѧ԰���ҷ���ͳ��
			  </font>
			   </p>
				<table width="100%">
									<thead>
										<tr>
											<td>ѧ԰</td>
											<td>ѧ԰������</td>
											<td>�ѻ���������</td>
											<td>�ѷ�������֮����</td>
										</tr>
									</thead>
									<tbody id="fpxq">
									<logic:present name="fpTjList">
										<logic:iterate id="fptj" name="fpTjList" indexId="i">
											<tr>
												<td>
												${fptj.xymc }
												</td>
												<td>
												${fptj.qss }
												</td>
												<td>
												${fptj.yhfqs }
												</td>
												<td>
												${fptj.yfpxszy }
												</td>
												
											</tr>
										</logic:iterate>
									</logic:present>
									<logic:empty name="fpTjList">
											<tr>
												<td colspan="6" align="center">��ѧ԰��Ϣ��</td>
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

