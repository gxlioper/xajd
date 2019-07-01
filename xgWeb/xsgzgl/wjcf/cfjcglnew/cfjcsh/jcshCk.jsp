<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfjcglnew/cfjcsh/js/cfjcsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
		var text=jQuery("#xbmc").val();
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${cfjcshForm.ywid}&tt="+new Date().getTime());
			
			var cflbdm ='${map.cflbdm }';
			showCfqxFlag(cflbdm);
		});

		//下载
		function fjxz(){
			var url="wjcf_cfsh.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		//处分期限显示
		function showCfqxFlag(cflbdm){
			//对于青岛酒店管理职业技术学院屏蔽该功能
			if(${xxdm=='13011'}) return false;
			
			if(cflbdm==""){return false;}
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqx").html(data["message"]);
			},'json');
		}

		</script>
	</head>
	
	<body>
		<html:form method="post" styleId="cfjcshForm" action="/wjcf_cfjcsh" >
		<html:hidden property="ywid" name="cfjcshForm" styleId="ywid"/>
		<html:hidden property="shid" name="cfjcshForm" styleId="shid"/>
		<html:hidden property="gwid" name="cfjcshForm" styleId="gwid"/>
		<html:hidden property="splcid" name="cfjcshForm" styleId="splcid"/>
		<html:hidden property="cfid" name="cfjcshForm" styleId="cfid"/>
		<input name="isZhgw" type="hidden" id="isZhgw" value="${isZhgw }"/>
		<input type="hidden" name="fjmc" id="fjmc" value="${map.fjmc }"/>
		<input type="hidden" name="shzt" id="shzt"/>
		<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
			<div
				style='width: 100%; height: 460px; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/wjcf/cfsbglnew/cfsb/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>处分上报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="20%">
								学年学期
							</th>
							<td align="left" width="30%">
								${map.xn }${map.xqmc }
							</td>
							<th align="right" width="20%">
								违纪时间
							</th>
							<td align="left" width="30%">
								${map.wjsj }
							</td>
						</tr>
						<tr>
							<th align="right">
								处分原因
							</th>
							<td align="left">
								${map.cfyymc }
							</td>
							<th align="right">
								处分类别
							</th>
							<td align="left">
								${map.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
							</td>
						</tr>
						<tr>
							<th align="right">
								处分依据
							</th>
							<td align="left" colspan="3">
								${map.cfyj }
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								处理决定书面材料或附件
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${map.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th align="right">
								违纪事实经过
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.wjssjg }
							</td>
						</tr>
						<tr>
							<th align="right">
								处分文号
							</th>
							<td align="left">
								${map.cfwh }
							</td>
							<th align="right">
								处分时间
							</th>
							<td align="left">
								${map.cfsj }
							</td>
						</tr>
						<logic:present name="map" property="cfdqsj">
							<tr>
								<th align="right">
									处分到期时间
								</th>
								<td align="left"  colspan="3">
									${map.cfdqsj }
								</td>
							</tr>
						</logic:present>
						<tr>
							<th align="right">
								备注
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="wjcf.text" />信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								申请时间
							</th>
							<td align="left">
								<bean:write name="jcxx" property="sqsj"/>
							</td>
							<th align="right">
							</th>
							<td align="left">
							</td>
						</tr>
						<logic:notEqual name="xxdm" value="12865">
							<tr>
								<th align="right">
									<bean:message key="wjcf.text" />理由
								</th>
								<td  align="left" colspan="3" style="word-break:break-all;width:100%">
									<%--<bean:write name="jcxx" property="sqly"/>--%>
									${jcxx.sqly}
								</td>
							</tr>
						</logic:notEqual>
						<logic:equal name="xxdm" value="12865">
							<tr>
								<th align="right">
									鉴定信息
								</th>
								<td align="left" colspan="3" style="word-break:break-all;width:100%">
									<bean:write name="jcxx" property="jdxx"/>
								</td>
							</tr>
							<tr>
								<th align="right">
									处分鉴定申请
								</th>
								<td  align="left" colspan="3" style="word-break:break-all;width:100%">
									<%--<bean:write name="jcxx" property="sqly"/>--%>
									${jcxx.sqly}
								</td>
							</tr>
						</logic:equal>
							<tr>
								<th align="right" width="15%">
									附件
								</th>
								<td colspan="3">
									<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
									<html:hidden name="jcxx" property="filepath" styleId="filepath" />
										<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												var gid = jQuery('#filepath').val();
												jQuery.MultiUploader_q({
													gid : gid
													});
											});
										</script> 
								</td>
							</tr>
						<logic:present name="map" property="jcwh">
							<tr>
								<th align="right">
									<bean:message key="wjcf.text" />文号
								</th>
								<td align="left"  >
									${map.jcwh }
								</td>
								<th align="right">
									<bean:message key="wjcf.text" />时间
								</th>
								<td align="left">
									${map.jcsj }
								</td>
							</tr>
						</logic:present>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="wjcf.text" />审核情况</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<div class="btn">
										<button type="button" name="关 闭" onclick="iFClose();">
											关 闭
										</button>
									</div>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			</html:form>
	</body>
</html>