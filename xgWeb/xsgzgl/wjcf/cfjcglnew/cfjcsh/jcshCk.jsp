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

		//����
		function fjxz(){
			var url="wjcf_cfsh.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		//����������ʾ
		function showCfqxFlag(cflbdm){
			//�����ൺ�Ƶ����ְҵ����ѧԺ���θù���
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
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/wjcf/cfsbglnew/cfsb/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����ϱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="20%">
								ѧ��ѧ��
							</th>
							<td align="left" width="30%">
								${map.xn }${map.xqmc }
							</td>
							<th align="right" width="20%">
								Υ��ʱ��
							</th>
							<td align="left" width="30%">
								${map.wjsj }
							</td>
						</tr>
						<tr>
							<th align="right">
								����ԭ��
							</th>
							<td align="left">
								${map.cfyymc }
							</td>
							<th align="right">
								�������
							</th>
							<td align="left">
								${map.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
							</td>
						</tr>
						<tr>
							<th align="right">
								��������
							</th>
							<td align="left" colspan="3">
								${map.cfyj }
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								�������������ϻ򸽼�
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
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
								Υ����ʵ����
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.wjssjg }
							</td>
						</tr>
						<tr>
							<th align="right">
								�����ĺ�
							</th>
							<td align="left">
								${map.cfwh }
							</td>
							<th align="right">
								����ʱ��
							</th>
							<td align="left">
								${map.cfsj }
							</td>
						</tr>
						<logic:present name="map" property="cfdqsj">
							<tr>
								<th align="right">
									���ֵ���ʱ��
								</th>
								<td align="left"  colspan="3">
									${map.cfdqsj }
								</td>
							</tr>
						</logic:present>
						<tr>
							<th align="right">
								��ע
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="wjcf.text" />��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								����ʱ��
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
									<bean:message key="wjcf.text" />����
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
									������Ϣ
								</th>
								<td align="left" colspan="3" style="word-break:break-all;width:100%">
									<bean:write name="jcxx" property="jdxx"/>
								</td>
							</tr>
							<tr>
								<th align="right">
									���ּ�������
								</th>
								<td  align="left" colspan="3" style="word-break:break-all;width:100%">
									<%--<bean:write name="jcxx" property="sqly"/>--%>
									${jcxx.sqly}
								</td>
							</tr>
						</logic:equal>
							<tr>
								<th align="right" width="15%">
									����
								</th>
								<td colspan="3">
									<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
									<html:hidden name="jcxx" property="filepath" styleId="filepath" />
										<script type="text/javascript">
											//���ø��� 
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
									<bean:message key="wjcf.text" />�ĺ�
								</th>
								<td align="left"  >
									${map.jcwh }
								</td>
								<th align="right">
									<bean:message key="wjcf.text" />ʱ��
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
								<span><bean:message key="wjcf.text" />������</span>
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
										<button type="button" name="�� ��" onclick="iFClose();">
											�� ��
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