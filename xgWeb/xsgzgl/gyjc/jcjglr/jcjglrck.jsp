<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/gyjc_jcjglr" method="post" styleId="JcjglrForm" onsubmit="return false;">
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
				<span>寝室信息
				</span>
				</th>
			</tr>
			</thead>
			<tbody>
			    <tr>
					<th>楼栋</th>
					<td id="ldmc" width="30%">${qsxx.ldmc}</td>
					<th width="20%">层号</th>
					<td id="ch">${qsxx.ch}</td>
				</tr>
				<tr>
					<th width="20%">寝室</th>
					<td id="qshTd" width="30%">${qsxx.qsh}</td>
					<th width="20%">学院</th>
					<td id="xymc">${qsxx.xymc}</td>
				</tr>
			</tbody>
			<logic:equal value="1" name="jcmxMap" property="wsjc">
			<thead>
				<tr>
					<th colspan="4">
					<span>卫生检查&nbsp;&nbsp;
					</span>
					<logic:iterate id="i" name="djList">
						<logic:equal value="1" name="i" property="jjlx">
							&nbsp;&nbsp;<font color='blue'>文明寝室等级：${i.dj}</font>
							<logic:notEmpty name="i" property="xh">
								&nbsp;&nbsp;<font color='blue'>不达标说明：${i.xh}</font>
							</logic:notEmpty>
							&nbsp;&nbsp;<font color='blue'>检查人：${i.xm}</font>
							&nbsp;&nbsp;<font color='blue'>检查时间：${i.jcrq}</font>
						</logic:equal>
					</logic:iterate>
					</th>
				</tr>
			</thead>
			  <tbody id="wsjcTbody">
			  <tr>
			  <th colspan="2"><div align="center">卫生区块</div></th>
			  <th colspan="2"><div align="center">要求</div></th>
			  </tr>
			  <logic:iterate id="i" name="wsjcPfList">
			  		<tr class='jcxmTr'>
			  			<td colspan="2">
			  					${i.fjmc}
			  			</td>
			  			<td colspan="2">
			  					${i.wsqkyq}
			  			</td>
			  		</tr>
			  </logic:iterate>
			  </tbody>
			  </logic:equal>
			  <logic:equal value="2" name="jcmxMap" property="aqjc">
			  <thead>
				<tr>
					<th colspan="4">
					<span>安全检查&nbsp;&nbsp;
					</span>
							<logic:iterate id="i" name="djList">
						<logic:equal value="2" name="i" property="jjlx">
							&nbsp;&nbsp;<font color='blue'>文明寝室等级：${i.dj}</font>
							<logic:notEmpty name="i" property="xh">
								&nbsp;&nbsp;<font color='blue'>不达标说明：${i.xh}</font>
							</logic:notEmpty>
							&nbsp;&nbsp;<font color='blue'>检查人：${i.xm}</font>
							&nbsp;&nbsp;<font color='blue'>检查时间：${i.jcrq}</font>
						</logic:equal>
					</logic:iterate>
					</th>
				</tr>
			</thead>
			  <tbody id="aqjcTbody">
			  <tr>
			  <th colspan="2"><div align="center">项目</div></th>
			  <th colspan="2"><div align="center">要求</div></th>
			  </tr>
			    <logic:iterate id="i" name="aqjcPfList">
			  		<tr class='jcxmTr'>
			  			<td colspan="2">
			  					${i.fjmc}
			  			</td>
			  			<td colspan="2">
			  					${i.wsqkyq}
			  			</td>
			  		</tr>
			  </logic:iterate>
			  </tbody>
			  </logic:equal>
			  <logic:equal value="3" name="jcmxMap" property="jljc">
			 <thead>
				<tr>
					<th colspan="4">
					<span>纪律检查&nbsp;&nbsp;
					</span>
					<logic:iterate id="i" name="djList">
						<logic:equal value="3" name="i" property="jjlx">
							&nbsp;&nbsp;<font color='blue'>文明寝室等级：${i.dj}</font>
							<logic:notEmpty name="i" property="xh">
								&nbsp;&nbsp;<font color='blue'>不达标说明：${i.xh}</font>
							</logic:notEmpty>
							&nbsp;&nbsp;<font color='blue'>检查人：${i.xm}</font>
							&nbsp;&nbsp;<font color='blue'>检查时间：${i.jcrq}</font>
						</logic:equal>
					</logic:iterate>
					</th>
					
				</tr>
			</thead>
			  <tbody id="jljcTbody">
			  <tr>
			  <th colspan="2"><div align="center">项目</div></th>
			  <th colspan="2"><div align="center">要求</div></th>
			  </tr>
			   <logic:iterate id="i" name="jljcPfList">
			  		<tr class='jcxmTr'>
			  			<td colspan="2">
			  					${i.fjmc}
			  			</td>
			  			<td colspan="2">
			  					${i.wsqkyq}
			  			</td>
			  		</tr>
			  </logic:iterate>
			  </tbody>
			  </logic:equal>
			  <thead>
				<tr>
					<th colspan="4">
					<span>其他信息
					</span>
					</th>
				</tr>
			</thead>
			  <tbody>
			  <tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="fjid" styleId="fjid" value="${jcmxMap.fjid}" />
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = jQuery('#fjid').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>
							</td>
						</tr>
			  </tbody>
			  </table>
				<div style="height:35px"></div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>