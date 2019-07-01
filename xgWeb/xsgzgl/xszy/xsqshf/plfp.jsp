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
		<div class="class_xszy">
		<ul>
			<li class="xszy_li">
			<div class="con">
			  <p class="num_all" align="center">选择寝室数<font color="#3399CC">${qssMap.qss}</font>个,
			           其中男生寝室<font color="#3399CC">${qssMap.mqss}</font>
			      女生寝室<font color="#3399CC">${qssMap.wqss}</font>     
			   </p>
			   
				<table width="100%" border="0" class="formlist">
					<tbody>
						
						<tr>
						    <th>
						    <div align="left">
						    <font color="#FF9933">把您选择的记录分配至</font>
									<html:select property="ssyxdm" styleId="ssyxdm">
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
							</html:select>
						    </div>
							</th>
						</br>
						</tr>
					</tbody>
				
				 </table>
				 <p align="center">其中<font color="#3399CC">${qssMap.hhqss}</font>个混合宿舍无法自动分配，请手动设置
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
								<button type="button" onclick="savePlfp()">
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

