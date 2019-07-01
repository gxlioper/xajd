<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${jjzgForm.sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${jjzgForm.splcid}&shid=${data.shid}");
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
			showConfirmDivLayer("��ȷ��<font color='red'>[" + text + "]</font>��������",{"okFun":function(){
					zx(shzt,text);
			}});
		}
		
		
		function zx(shzt,text){
			var url = "jjgl_jjzg.do?method=submitAudit&tt="+new Date();
			jQuery("#zgshForm").ajaxSubmit( {
				url : url,
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data["message"] == "����ɹ���") {
						showAlert("<font color='red'>["+text+"]</font>�����ɹ���", {}, {
							"clkFun" : function() {
								if (parent.window) {
									refershParent();
								}
							}
						});
					} else {
						showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					}
				}
			});
		}
	</script>
  </head>
  
  <body>
		<html:form action="/jjgl_jjzg" method="post" styleId="zgshForm">
			<html:hidden property="id" value="${jjzgForm.sqid }"/>
			<html:hidden property="xh" value="${userName }"/>
			<html:hidden property="shzt"/>
		
			<div class='tab' style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr style="height: 45px;">
							<th width="15%">ѧ��</th>
							<td width="25%">${jbxx.xh }</td>
							<th width="15%">����</th>
							<td width="25%">${jbxx.xm }</td>
							<td rowspan="3" align="center">
								<img src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh }" width="100" height="120" >
							</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>�Ա�</th>
					    	<td>${jbxx.xb }</td>
					    	<th>�꼶</th>
					    	<td>${jbxx.nj }</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>ѧԺ</th>
					    	<td>${jbxx.xymc }</td>
					    	<th>�༶</th>
					    	<td>${jbxx.bjmc }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>�ҽ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="height: 45px;">
					    	<th>�ó���Ŀ</th>
					    	<td colspan="4">
					    		${jjzgForm.xkmca } ��${jjzgForm.xkmcb } ��${jjzgForm.xkmcc }
					    	</td>
					    </tr>
						<tr style="height: 45px;">
					    	<th>����꼶</th>
					    	<td>
					    		${jjzgForm.jjnjmc }
					    	</td>
					    	<th>��ϵ�绰</th>
					    	<td colspan="2">
					    		${jjzgForm.lxdh }
					    	</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>��ע</th>
					    	<td colspan="4">
					    		${jjzgForm.bz }
					    	</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>��˽��
							</th>
							<td colspan="4" id="shjgSpan">
								
							</td>
						</tr>
						<tr>
						<th width="20%">
								<font color="red">*&nbsp;</font> ������&nbsp;
								<br />
								<font color="red">(��200��)</font>
							</th>
							<td colspan="4">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=jjzg&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);">${rs.shyj}</textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5" id="shlccx">
							
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
									<button type="button" id="btqd" onclick="saveAudit();">
										�� ��
									</button>
									<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
										�� ��
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
