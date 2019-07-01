<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${dkbcSqshForm.id}&tt="+new Date().getTime());
                dclxChange(document.getElementById("dclb"));
			});
            function dclxChange(target){
                if(target.value == "01"){
                    jQuery("#yhxx").hide();
                } else {
                    jQuery("#yhxx").show();
                }
            }
		</script>
	</head>
	<body>
		<html:form action="/dkbc_sqsh" method="post" styleId="form">
			<html:hidden property="id"/>
			<html:hidden property="shzt"/>
			<div style='tab;width:100%;height:440px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>����ѧ��</th>
							<td>
								${dkbcSqshForm.xn}
							</td>
							<th><span class="red">*</span>��������</th>
							<td>
								<html:select property="dclb" styleId="dclb" onchange="dclxChange(this);" disabled="true">
									<html:options collection="dclblist" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td>
								${dkbcSqshForm.yhmc }
							</td>
							<th>�����ͬ��</th>
							<td>
									${dkbcSqshForm.dkhth }
							</td>
						</tr>
						<tr>
							<th>����������(Ԫ)</th>
							<td>
								${dkbcSqshForm.dcje}
							</td>
						</tr>
						<tr>
							<th>��ҵ��λ����</th>
							<td>
								${dkbcSqshForm.dwmc }
							</td>
							<th>������Դ���绰</th>
							<td>
								${dkbcSqshForm.dwdh }
							</td>
						</tr>
						<tr>
							<th>��ҵ��λ��ַ</th>
							<td>
								${dkbcSqshForm.dwdz}
							</td>
							<th>��������</th>
							<td>
									${dkbcSqshForm.fwnx}
							</td>

						</tr>
						<tr>
							<th>��ҵ����(����)</th>
							<td>
									${dkbcSqshForm.hyxz}
							</td>
							<th>ְ������</th>
							<td>
									${dkbcSqshForm.zwmc}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="filepath" styleId="filepath"/>
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
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
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
			<div style="height: 5px;"></div>
			<table  width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="iFClose();">
									�ر�
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
	
</html>