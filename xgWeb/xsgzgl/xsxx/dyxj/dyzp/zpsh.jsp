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
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${dyxjDyzpModel.id}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${dyxjDyzpModel.splcid}&shid=${dyxjDyzpModel.id}");
			});
			function saveAudit(){
				var shzt=jQuery("#shjg").val();
				jQuery("#shzt").val(shzt);
				/*
				if (jQuery("#shyj").val() == ""){
					showAlertDivLayer("����д��������");
					return false;
				}*/
				if (jQuery("#shyj").val().length>200){
					showAlertDivLayer("���������ܳ���200��");
					return false;
				}
				//����������˱�����ԭ����30%�ĳ�50%
				if(jQuery("#yxsx").val() == "true" && jQuery("#pddjdm").find("option:selected").text() == "��"){
					showAlert("�༶���������ȳ���50%��������ѡ��ȼ���");
					return false;
				}
				var text=jQuery("#shjg").find("option:selected").text();
				//�ύ���
				zx(shzt,text);
			}
			
			function zx(shzt,text){
				var url = "xsxxDyxjDyzp.do?method=saveDyzpsh&tt="+new Date();
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
		<html:form action="/xsxxDyxjDyzp" method="post" styleId="form">
			<html:hidden property="id"/>
			<html:hidden property="shzt"/>
			<html:hidden property="xtgwid" styleId="xtgwid"/>
			<html:hidden property="splcid" styleId="splcid"/>
			<input type="hidden" id="yxsx" value = "${yxsx}"/>
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
							<th>ѧ��</th>
							<td>
								${dyxjDyzpModel.xn}
							</td>
							<th>ѧ��</th>
							<td>
								${dyxjDyzpModel.xqmc}
							</td>
						</tr>
						<tr>
							<th>�����ȼ�</th>
							<td colspan="3">
								${dyxjDyzpModel.djmc}
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3">
								${dyxjDyzpModel.zpnr}
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
							<td id="shjgSpan" colspan="">
								
							</td>
							<th>
								<font color="red">*</font>�����ȼ�
							</th>
							<td>
								<html:select property="pddjdm" styleId="pddjdm">
									<html:options collection="zpdjList" property="djdm" labelProperty="djmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								 ������&nbsp;
								<br />
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xsxx_dyxj&id=shyj" />
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