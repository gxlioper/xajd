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
		<script type="text/javascript" src="xsgzgl/wjcf/cfssglnew/cfsssh/js/cfsssh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${cfssshForm.ywid}&tt="+new Date().getTime());
			
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

		//申诉附件下载
		function ssfjxz(){
			var url="wjcf_cfsssh.do?method=fjxz";
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
		<html:form method="post" styleId="cfssshForm" action="/wjcf_cfsssh" >
		<html:hidden property="ywid" name="cfssshForm" styleId="ywid"/>
		<html:hidden property="shid" name="cfssshForm" styleId="shid"/>
		<html:hidden property="gwid" name="cfssshForm" styleId="gwid"/>
		<html:hidden property="splcid" name="cfssshForm" styleId="splcid"/>
		<html:hidden property="cfid" name="cfssshForm" styleId="cfid"/>
		<input name="isZhgw" type="hidden" id="isZhgw" value="${isZhgw }"/>
		<input type="hidden" name="shzt" id="shzt"/>
		<input type="hidden" name="fjmc" id="fjmc" value="${map.fjmc }"/>
		<input type="hidden" name="ssfjmc" id="ssfjmc" value="${ssxx.fjmc }"/>
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
								${map.xn }:${map.xqmc }
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
								<span>申诉信息</span>
							</th>
							
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								申诉时间
							</th>
							<td align="left">
								<bean:write name="ssxx" property="sqsj"/>
							</td>
							<th align="right">
								证明材料或附件
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-1" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${map.ssfilepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-1'
											});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th align="right">
								申诉理由
							</th>
							<td  align="left" colspan="3" style="word-break:break-all;width:100%">
								<%--<bean:write name="ssxx" property="sqly"/>--%>
								${ssxx.sqly }
							</td>
						</tr>
						<logic:present name="map" property="sswh">
							<tr>
								<th align="right">
									申诉文号
								</th>
								<td align="left"  >
									${map.sswh }
								</td>
								<th align="right">
									申诉时间
								</th>
								<td align="left">
									${map.sssj }
								</td>
							</tr>
							<tr>
								<th align="right">
									申诉结果
								</th>
								<td align="left" <logic:notPresent name="map" property="cfggw">colspan="3"</logic:notPresent> >
									${map.ssjg }
								</td>
								<logic:present name="map" property="cfggw">
									<th align="right" class="yes" >
										处分更改为
									</th>
									<td align="left" class="yes" >
										${map.cfggw }
									</td>
								</logic:present>
							</tr>
						</logic:present>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>申诉审核情况</span>
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