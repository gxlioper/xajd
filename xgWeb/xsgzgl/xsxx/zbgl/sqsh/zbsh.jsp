<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zbglSqshForm.id}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${zbglSqshForm.splcid}&shid=${zbglSqshForm.id}");
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
				var text=jQuery("#shjg").find("option:selected").text();
				//�ύ���
				zx(shzt,text);
			}
			
			function zx(shzt,text){
				var url = "zbglSqsh.do?method=submitAudit&tt="+new Date();
				ajaxSubFormWithFun("form",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/zbglSqsh" method="post" styleId="form">
			<html:hidden property="id"/>
			<html:hidden property="shzt"/>
			
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
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>������Ů</th>
							<td>
								${zbglSqshForm.dszn }
							</td>
							<th>����״��</th>
							<td>
								${zbglSqshForm.hyzk }
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								${zbglSqshForm.sl }
							</td>
							<th>���</th>
							<td>
								${zbglSqshForm.sg } cm
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								${zbglSqshForm.tz } kg
							</td>
							<th>Ӧ����</th>
							<td>
								${zbglSqshForm.yzd }
							</td>
						</tr>
						<tr>
							<th>Ӧ����Դ</th>
							<td>
								${zbglSqshForm.yzly }
							</td>
							<th>�ξ���Ը</th>
							<td>
								${zbglSqshForm.cjyy }
							</td>
						</tr>
						<logic:equal name="xxdm" value="14073">
							<tr>
								<th>�Ͷ���ֹ����</th>
								<td>
									${zbglSqshForm.ylzd1}
									��
									${zbglSqshForm.ylzd2}
								</td>
								<th>ѧϰ����</th>
								<td>
									${zbglSqshForm.ylzd3}
								</td>
							</tr>
							<tr>
								<th>ѧҵ���</th>
								<td>
									${zbglSqshForm.ylzd4}
								</td>
								<th>ѧУ����</th>
								<td>
									${zbglSqshForm.ylzd5}
								</td>
							</tr>
							<tr>
								<th>ѧУ�������ŵ�ַ</th>
								<td colspan="3">
									${zbglSqshForm.ylzd7}
								</td>
							</tr>
							<tr>
								<th>�ʱ�</th>
								<td>
									${zbglSqshForm.ylzd8}
								</td>
								<th>ԺУ���ڵ�</th>
								<td>
									${zbglSqshForm.ylzd6}
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>��ҵ���</th>
							<td>
								${zbglSqshForm.cylb }
							</td>
							<th>ְҵ�ʸ�֤��</th>
							<td>
								${zbglSqshForm.zgzs }
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3" style="">
								${zbglSqshForm.bz }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${zbglSqshForm.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
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
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xss_zbgl&id=shyj" />
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