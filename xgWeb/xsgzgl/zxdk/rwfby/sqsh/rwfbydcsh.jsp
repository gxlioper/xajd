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
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rwfbysqshForm.id}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rwfbysqshForm.splcid}&shid="+jQuery("#shid").val());
                dclxChange(document.getElementById("dclb"));
			});
			function saveAudit(){
				var shzt=jQuery("#shjg").val();
				jQuery("#shzt").val(shzt);
				if (jQuery("#shyj").val() == ""){
					showAlertDivLayer("����д��������");
					return false;
				}
				if (jQuery("#shyj").val().length>200){
					showAlertDivLayer("���������ܳ���200��");
					return false;
				}
//				if (!checkNull("zd2-zd6")) {
//					 return false;
//				 }
//				var dcje = jQuery("#zd6").val();
//				if(dcje>32000){
//					showAlertDivLayer("�����������Ϊ32000");
//					return false;
//				}
				/**/var dclbmc = jQuery("#zd2").find("option:selected").text();
//				jQuery("#zd3").val(dclbmc);
				var text=jQuery("#shjg").find("option:selected").text();
				//�ύ���
				zx(shzt,text);
			}
			
			function zx(shzt,text){
				var url = "rwfby_sqsh.do?method=submitAudit&tt="+new Date();
				ajaxSubFormWithFun("rwfbysqshForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}
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
		<html:form action="/rwfby_sqsh" method="post" styleId="rwfbysqshForm">
			<html:hidden property="id"/>
			<html:hidden property="shzt"/>
			<input type="hidden" id="shid" value="${shid}"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
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
								${rwfbysqshForm.xn}
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
								${rwfbysqshForm.yhmc }
							</td>
							<th>�����ͬ��</th>
							<td>
								${rwfbysqshForm.dkhth }
							</td>
						</tr>
						<tr>
							<th>����������(Ԫ)</th>
							<td>
							  ${rwfbysqshForm.dcje}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								֤������
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
						<tr>
							<th>
								<font color="red">*</font>��˽��
							</th>
							<td id="shjgSpan" colspan="3">
								
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*&nbsp;</font> ������&nbsp;
								<br />
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zxdk_dkbc&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);">${rs.shyj}</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" id="btqd" onclick="saveAudit();">
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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