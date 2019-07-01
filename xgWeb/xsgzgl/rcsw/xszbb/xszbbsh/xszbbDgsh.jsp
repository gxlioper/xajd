<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	<script type="text/javascript">
	

	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.bbsqid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
		displayBbyhk();
		displayHcDdAndSj();
	});

	function saveShzt(){
		var shzt = jQuery("#shjg").val();
		if(jQuery("#shjg").val() == "0"){
			showAlertDivLayer("��ѡ�����״̬��");
			return false;
		}
		var shyj = jQuery("#shyj").val();
		if (jQuery.trim(shyj) == ""){
			showAlertDivLayer("����д��������");
			return false;
		}
		var message;
		if(jQuery("#shjg").val() == "1"){
			message = "ͨ��";
		}
		if(jQuery("#shjg").val() == "2"){
			message = "��ͨ��";
		}
		if(jQuery("#shjg").val() == "3"){
			message = "�˻�";
		}
		showConfirmDivLayer("��ȷ��" + message + "��������",{"okFun":function(){
			var url = "rcsw_xszbb_bbshgl.do?method=xszbbDgsh&type=save";
			ajaxSubFormWithFun("xszbbshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}});
	}

	function displayBbyhk(){
		var bbyhk = jQuery("#xszbblxdm").val();
		//�����Żݿ�����
		if (bbyhk == "001"){
			jQuery('.bbyhk').css("display","");
		} else {
			//�ر�
			jQuery('.bbyhk').css("display","none");
		}
	}
	function displayHcDdAndSj(){
		var sfbbhcyhk = jQuery("#sfbbhcyhk").val();
		//�����Żݿ�����
		if (sfbbhcyhk == "y"){
			jQuery('.bbyhk_sjdd').css("display","");
		} else {
			//�ر�
			jQuery('.bbyhk_sjdd').css("display","none");
		}
	}
	
	</script>
</head>
<body>
	<html:form action="/rcsw_xszbb_bbshgl" method="post" styleId="xszbbshForm">
			
		<html:hidden name="model" property="bbsqid" styleId="bbsqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="xszbblxdm" styleId="xszbblxdm"/>
		<html:hidden name="model" property="sqsj" styleId="sqsj"/>
		<html:hidden name="model" property="sqly" styleId="sqly"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
		<html:hidden name="model" property="sfbbhcyhk" styleId="sfbbhcyhk"/>
		<html:hidden name="model" property="sj" styleId="sj"/>
		<html:hidden name="model" property="dd" styleId="dd"/>
		<logic:equal value="13011" name="xxdm">
			<html:hidden name="model" property="ccqdz" styleId="ccqdz"/>
			<html:hidden name="model" property="cczdz" styleId="cczdz"/>
		</logic:equal>
		<logic:equal value="10695" name="xxdm">
			<html:hidden name="model" property="ccqdz" styleId="ccqdz"/>
			<html:hidden name="model" property="cczdz" styleId="cczdz"/>
		</logic:equal>
		<html:hidden name="model" property="filepath"/>
		<input type="hidden" name="xszbblxdm" id="xszbblxdm" value="${rs.xszbblxdm}" />
		<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
			
		<table class="formlist" width="95%">
			<thead>
				<tr>
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
			</table>
			<table width="100%" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					���벹��֤��
				</th>
				<td align="left" width="30%">
					${rs.xszbblxmc}
				</td>
				<logic:equal value="11400" name="xxdm">
					<th align="right" width="20%">
				    </th>
				    <td align="left" width="30%">
				    </td>
				</logic:equal>
				<logic:notEqual value="11400" name="xxdm">
					<th align="right" width="20%">
					 <font class="bbyhk" style="display: none">�Ƿ񲹰���Żݿ�</font>
				    </th>
				    <td align="left" width="30%">
					  &nbsp;&nbsp;<font class="bbyhk" style="display: none">${rs.sfbbhcyhk}</font>
				    </td>
				</logic:notEqual>
			</tr>
			<logic:equal value="12684" name="xxdm">
			     <tr class="bbyhk bbyhk_sjdd" style="display:none">
			     	<th>ʱ��</th>
					<td>
						${rs.sj}
					</td>
					<th>�ص�</th>
					<td>
					${rs.dd}	
					</td>
			     </tr>
			     </logic:equal>
			 <logic:equal value="13011" name="xxdm">
			     <logic:equal name="rs" property="sfbbhcyhkmc" value="y">					     
				    <tr id="qj">						     	
				     	<th>
				     		�˳�����
				     	</th>					     	
				     	<td colspan="3">
				     		${rs.ccqdz}��${rs.cczdz}
				     	</td>						     							     	
				    </tr>
			     </logic:equal>					     
			</logic:equal>
			<logic:equal value="10695" name="xxdm">
			     <logic:equal name="rs" property="sfbbhcyhkmc" value="y">					     
				    <tr id="qj">						     	
				     	<th>
				     		�˳�����
				     	</th>					     	
				     	<td colspan="3">
				     		${rs.ccqdz}��${rs.cczdz}
				     	</td>						     							     	
				    </tr>
			     </logic:equal>					     
			</logic:equal>
			<tr>
				<th >
					����ʱ��				
				</th>
				<td colspan="3">
					${rs.sqsj}
				</td>
				
			</tr>	
			<tr>	
					<th>
						��������
					</th>
					<td colspan="3" style="word-break:break-all;" width="650px">
						${rs.sqly }
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
								var gid = "${model.filepath}";
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
							<span>�����ʷ</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
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
					<tr>
						<th>
							<font color="red">*</font>��˽��
						</th>
						<td colspan="3" id="shjgSpan">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> ������
					<br />
					<font color="red">(��200��)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xszbb&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
			
			
		</div>
		</td>
		</tr>
		</table>
		</div>
		<table width="100%" border="0" class="formlist">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveShzt();return false;">
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
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
