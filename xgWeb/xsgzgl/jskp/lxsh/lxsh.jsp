<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${LxshxForm.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${LxshxForm.splcid}&shid=${LxshxForm.shid}",function(){
					jQuery("#shjg").change(function(){
						var tempvalue = this.value;
						if(tempvalue == "2" || tempvalue == "3"){
							jQuery("#pfjqtr").hide();
						}else{
							jQuery("#pfjqtr").show();
						}
					})
					});
			});
			function saveSh(){
				var shzt = jQuery("#shjg").val();
				if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
					showAlert("�뽫��������д������");
					return false;
				}
				if(shzt == "1" ){
					if(jQuery("#zxf").val()== "" || jQuery("#zdf").val() == ""){
						return showAlert("��˽��Ϊͨ��ʱ������������");
					}
					if(parseInt(jQuery("#zxf").val()) > parseInt(jQuery("#zdf").val())){
						return showAlert("�����������ֲ���С����ͷ֣�");
					}
					
				}
				var url = "jskp_lxsh.do?method=saveLxsh";
				ajaxSubFormWithFun("LxshxForm",url,function(data){
					 if(data["message"]=="����ɹ���"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		}
					});
			}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/jskp_lxsh" method="post" styleId="LxshxForm">
		<html:hidden  property="sqid" styleId="sqid"/>
		<html:hidden  property="fzr" styleId="fzr"/>		
		<html:hidden  property="splcid" styleId="splcid"/>
			
		<html:hidden  property="shid" styleId="shid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">��Ŀ����</th>
							<td style="width:35%">
								${LxshxForm.xmmc}
							</td>
							<th style="width:15%">ָ������</th>
							<td style="width:35%">
								${bmmc}
							</td>
						</tr>	
						<tr>
							<th>��Ŀ���</th>
							<td>
								${xmlbmc}
							</td>
							<th>����ʱ��</th>
							<td>
								${LxshxForm.lxsj}
							</td>
						</tr>
						<tr>
							<th>������</th>
							<td>
								<span>${fzrxm}</span>
							</td>
							<th>��������ϵ��ʽ</th>
							<td>
								${LxshxForm.fzrlxfs}
							</td>
						</tr>
						<tr>
							<th>ָ����ʦ</th>
							<td>
								${LxshxForm.zdls}
							</td>
							<th>ָ����ʦ��ϵ��ʽ</th>
							<td>
								${LxshxForm.zdlslxfs}
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3">
								${LxshxForm.zxf}-${LxshxForm.zdf}
							</td>
						</tr>
						<tr>
							<th>������
							</th>
							<td colspan="3">
									<logic:iterate id="i" name="xhList">
										${i.xh}(${i.xm})&nbsp;
									</logic:iterate>
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td colspan="3">
							<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
								jQuery(function(){
									var gid = "${LxshxForm.filepath}";
									jQuery.MultiUploader_q({
										gid : gid,
										targetEl : 'commonfileupload-list-0'
									});
								});
								</script>
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								${LxshxForm.lxly}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
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
							<th >
								��˽��
							</th>
							<td id="shjgSpan">
								
							</td>
						</tr>
						<tr id = "pfjqtr">
							<th><font class="red">*</font>��������</th>
							<td colspan="3">
								<input name="zxf" style="width:80px" type="text" maxlength="8" id="zxf" value="${shzdxx.zxf}" onkeyup="checkInput(this)" onblur="replaceAboveZero(this)" /> - <input name="zdf" style="width:80px" maxlength="8" value="${shzdxx.zdf}"   type="text" id="zdf" onkeyup="checkInput(this)" onblur="replaceAboveZero(this)" />
							</td>
						</tr>
						
						<tr>
							<th width="20%">
								<font color="red">*&nbsp;</font> ������
								<br />
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=lxsh&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
							</td>
						</tr>
					</tbody>	
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="����"  onclick="saveSh();return false;">
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