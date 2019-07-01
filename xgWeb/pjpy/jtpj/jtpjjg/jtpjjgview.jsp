<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/jtpjjg/js/jtpjjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				loadJxxx();
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/jtpjsq.do?method=add&type=save">
			<html:hidden property="splcid" styleId="splcid"/>
			<html:hidden property="jgid" styleId="jgid" value="${data.jgid}"/>
			<html:hidden property="pjjtid" styleId="mrpjjtiid" value="${data.pjjtid}"/>
			<html:hidden property="jxid" styleId="jxid" value="${data.jxid}"/>
			<html:hidden property="pjjtid" styleId="pjjtid" value="${data.pjjtid}"/>
			<html:hidden property="type" value="view" styleId="type"/>
			<input type="hidden" id="first" value="1"/>
			<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>集体奖项结果</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								申请周期
							</th>
							<td width="30%" colspan="3">
								${data.sqxn} ${sqxqmc}
							</td>
						</tr>
						<tr>
							<th >
								申请奖项
							</th>
							<td>
								${data.jxmc}
							</td>
							<th width="20%">
								评定周期
							</th>
							<td id="pdzq" width="30%">
							</td>
						</tr>
						<tr>
							<th>
								奖项说明
							</th>
							<td colspan="3" id="jxsm">
							</td>
						</tr>
					</tbody>
				</table>
				<div id="jtpjxx">
				
				</div>

				<table width="" border="0" class="formlist">
					<logic:equal value="10704" name="xxdm">
						<logic:equal value="true" name="bjpj">						
							<tr>				
								<th width="20%" style="border-top: 0px;">
									评定分数
								</th>
								<td colspan="3" style="border-top: 0px;">
									${data.rdfs}
								</td>
							</tr>
						</logic:equal>
					</logic:equal>
					<tr>
						<th>
							附件
						</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="filepath" value="${data.filepath}" styleId="fjid"/>
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
					<tr>
						<th width="20%" style="border-top: 0px;">
							申请理由
						</th>
						<td colspan="3" style="border-top: 0px;">
							${data.sqly}
						</td>
					</tr>
				</table>
			</div>
			<div style="hight: 25px"></div>
			<div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="iFClose();" id="buttonClose">
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
