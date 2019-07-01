<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/jtpjsq/js/jtpjsq.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/jtpjsh/js/jtpjsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/js/jtpjyz.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>	
		<script type="text/javascript">
			jQuery(function() {
				loadJxxx();
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${data.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${data.splcid}&shid=${data.shid}");
				initFormYz();
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form"
			action="/jtpjsh">
			<html:hidden property="splcid" styleId="splcid" />
			<html:hidden property="sqid" styleId="sqid" value="${data.sqid}" />
			<html:hidden property="pjjtid" styleId="mrpjjtiid"
				value="${data.pjjtid}" />
			<html:hidden property="jxid" styleId="jxid" value="${data.jxid}" />
			<html:hidden property="pjjtid" styleId="pjjtid"
				value="${data.pjjtid}" />
			<html:hidden property="jtpjdw" styleId="jtpjdw" value="${data.jtpjdw}"/>
			<html:hidden property="type" value="view" styleId="type" />
			<html:hidden property="gwid" value="${gwid}" styleId="gwid" />
			<input type="hidden" id="first" value="1"/>
			<input type="hidden" id="xxdm" value="${xxdm}" />
			<div>
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>集体奖项申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								申请学年
							</th>
							<td width="30%" id="sqxn">
								${data.sqxn}
							</td>
							<th width="20%">
								申请学期
							</th>
							<td width="30%" id="sqxq">
								${xqmc}
							</td>
						</tr>
						<tr>
							<th>
								申请奖项
							</th>
							<td>
								${data.jxmc}
							</td>
							<th>
								评定周期
							</th>
							<td id="pdzq">
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
				<div id="divyzts">
					
				</div>
				<table width="" border="0" class="formlist">
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
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4" style="border-top: 0px;">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">

							</td>
						</tr>

					</tbody>
					<tbody>
						<tr>
							<th style="border-top: 0px;">
								<span class="red">*</span>审核结果
							</th>
							<td colspan="3" id="shjgSpan">
								
							</td>
						</tr>
						<!-- 西安科技大学个性化 -->
						<logic:equal value="10704" name="xxdm">
							<logic:equal value="true" name="bjpj">
								<tr>
									<th>
										<span>评定分数</span>
									</th>
									<td colspan="3">
										<html:text property="rdfs" styleId="rdfs" onblur="checkZs(this)"></html:text> 
									</td>
								</tr>
							</logic:equal>
						</logic:equal>
						<tr>
							<th width="20%">
								<span class="red">*</span>审核意见&nbsp;
								<br />
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=jtpj&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj"
									style="word-break: break-all; width: 100%;margin-top:5px"
									onblur="checkLen(this,200);">${rs.shyj}</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 25px;">
			</div>
			<div>
				<table width="" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="btqd" onclick="save_sh();">
										保 存
									</button>
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
