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
		<!-- 多功能操作区 -->
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li>
						<a id="btn_fh" class="btn_fh"> 返 回 </a>
					</li>
				</ul>
			</div>
		</div>
		<div class="class_xszy">
		<ul>
			<li class="xszy_li">
			<div class="con">
			  <p class="num_all" align="center"><font color="#3399CC">${nj}年度学园寝室分配统计
			  </font>
			   </p>
				<table width="100%">
									<thead>
										<tr>
											<td>学园</td>
											<td>学园寝室数</td>
											<td>已划分寝室数</td>
											<td>已分配新生之友数</td>
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
												<td colspan="6" align="center">无学园信息！</td>
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

