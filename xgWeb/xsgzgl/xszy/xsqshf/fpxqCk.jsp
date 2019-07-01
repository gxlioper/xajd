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
			  <p class="num_all" align="center"><font color="#3399CC">${nj}年度学园寝室数${qsxxAllMap.qss}个，
			  男生：${qsxxAllMap.man}，女生：${qsxxAllMap.woman}
			  </font>
			   </p>
				<table width="100%">
									<thead>
										<tr>
											<td>院系</td>
											<td>已分配男生寝室数</td>
											<td>已分配女生寝室数</td>
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
												<td colspan="6" align="center">无分配信息！</td>
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

