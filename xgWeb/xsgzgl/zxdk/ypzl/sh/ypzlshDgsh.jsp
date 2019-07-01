<%@ page language="java" contentType="text/html; charset=GBK"%>
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
		jQuery.ajaxSetup({async:false});
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="+new Date().getTime());
		getSpje();
		jQuery.ajaxSetup({async:true});
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splc}&shid=${rs.shid}");
	});

	//��ȡ�������
	function getSpje(){
		if(jQuery("#shlccx_table tr").length>2){
			jQuery("#zd3").val(jQuery.trim(jQuery("#shlccx_table tr").eq(-2).find("td").eq(-1).text()));
		}else{
			jQuery("#zd3").val(jQuery("#sqje").val());
		}
	}
	
	function saveSh(){
		var shzt = jQuery("#shjg").val();
		if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
			showAlert("�뽫��������д������");
			return false;
		}
		var xxdm = jQuery("#xxdm").val();
		var zd3 = jQuery("#zd3").val();	
		if(xxdm == "10511"){
			if(shzt == "1"){
				if(zd3 == ""){
					showAlert("����д��׼��");
					return false;
				}
				if(parseInt(zd3) == 0){
					showAlert("��׼����Ϊ0��");
					return false;
				}
				if(parseInt(zd3) > 1000){
					showAlert("��׼�������Ϊ1000��");
					return false;
				}
			}
		}
		
		
		var url = "ypzl_sh.do?method=sbDgsh&type=save";
		ajaxSubFormWithFun("ypzlshForm",url,function(data){
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
</head>
<body>
	<html:form action="/ypzl_sh" method="post" styleId="ypzlshForm">
		<html:hidden name="rs" property="sqid" styleId="sqid"/>
		<html:hidden name="rs" property="splc" styleId="splc"/>
		<input type="hidden"  id="xxdm" value="${xxdm}"/>
		<input type="hidden" name="sqje" id="sqje" value="${rs.sqje}"/>
		<html:hidden property="shid" styleId="shid"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����˾����������ѧ�꣩</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							</td>
							<th>ѧ��</th>
							<td>
								${xqsr}
							</td>
						</tr>
						<tr>
					    	<th>�����Ԫ��</th>
					    	<td>
								${rs.sqje}
							</td>
							<logic:equal value="10511" name="xxdm">
								<th>����ԭ�����</th>
								<td>
									${ytmc}
								</td>
							</logic:equal>
					    </tr>
					    <logic:equal value="10511" name="xxdm">
				    		<tr>
								<th>
									�����ϴ�
								</th>
								<td colspan="3">
										<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
						            <html:hidden property="ytms" styleId="ytms" value="${rs.ytms}" />
						            <script type="text/javascript">
								        //���ø��� 
										jQuery(function(){
											var gid = jQuery('#ytms').val();
											jQuery.MultiUploader_q({
												gid : gid
												});
										});
									</script>
								</td>
							</tr>
					    </logic:equal>
					    <logic:notEqual value="10511" name="xxdm">
							<tr>
								<th align="right" width="10%">
									������Ϣ
								</th>
								<td colspan="3">
									<div id="commonfileupload-list-0" style="padding: 5px;"></div>
									<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											var gid = "${rs.filepath}";
											jQuery.MultiUploader_q({
												gid : gid,
												targetEl : 'commonfileupload-list-0'
											});
										});
									</script>
								</td>
							</tr>	
						</logic:notEqual>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								${rs.sqly}
							</td>
			      		</tr>	
					</tbody>
				 </table>
		
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
						<th >
							��˽��
						</th>
						<td id="shjgSpan">
							
						</td>
						<logic:equal value="10511" name="xxdm">
							<th >
						<font color="red">*</font>��׼���(Ԫ)
					</th>
					<td>
						<html:text property="zd3" styleId="zd3" style="width:90px;" onblur="replaceAboveZero(this)" maxlength="4" onkeyup="checkInput(this)"></html:text>
						<html:hidden property="zd1" styleId="zd1" value="��׼���(Ԫ)"/>
					</td>
						</logic:equal>
					
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> ������
					<br />
					<font color="red">(��200��)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=ypzlsh&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveSh();return false;">
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
