<%@ page language="java" contentType="text/html; charset=GBK"%>
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
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splcid}&shid=${model.shid}",function(){
				});

				var zcfs = "${rs.zcfsmc}";
				if("�ʼ�" == zcfs){
					/*�����ֶ�*/
					jQuery("#mailedOne").show();
					jQuery("#mailedTwo").show();
					jQuery("#mailedThree").show();
					jQuery("#byoOne").hide();
				}else{
					/*�����ֶ�*/
					jQuery("#mailedOne").hide();
					jQuery("#mailedTwo").hide();
					jQuery("#mailedThree").hide();
					jQuery("#byoOne").show();
				}
			});
			
			/**
			 * ������˱���
			 * @return
			 */
			function dazcshSingleSave(){
				var shjg = jQuery("#shjg").val();
				if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
					showAlert("�뽫��<font color='red'>*</font>����д������");
					return false;
				}
				var url = "dagl_dazcsh.do?method=dazcshSingleSave";
				ajaxSubFormWithFun("dazcshForm",url,function(data){
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
	<html:form action="/dagl_dazcsh" method="post" styleId="dazcshForm">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splcid" styleId="splcid" value="${rs.splcid}"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
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
							<span>��Ŀ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<th>ת����ʽ</th>
							<td colspan="3">${rs.zcfsmc}</td>
						</tr>
						
						<tr id="mailedOne">
							<th>�ʼĵ�ַ</th>
							<td>${rs.yjdz}</td>
							<th>��������</th>
							<td>${rs.yzbm}</td>
						</tr>
						
						<tr id="mailedTwo">
							<th>�ռ���</th>
							<td>${rs.sjr}</td>
							<th>�ռ��˵绰</th>
							<td>${rs.sjrdh}</td>
						</tr>
						
						<tr id="mailedThree">
							<th>��λ����</th>
							<td>${rs.dwmc}</td>
							<th>��λ��ַ</th>
							<td>${rs.dwdz}</td>
						</tr>
						
						<tr id="byoOne">
							<th>�Դ�������ŵ</th>
							<td>
								�����Ĳ�����
								<a href="javascript:void(0);" onclick="window.open('common_upload.do?method=asyncDownload&fid=${dazccsszForm.uploadid}');return false;" class="name" style="margin-left: 0px;">
									������ת��Э�顷
								</a>
							</td>
							<th>Ԥ���ᵵ����</th>
							<td>${rs.yqtdrq}</td>
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
					<th>
						<font color="red">*</font>��˽��
					</th>
					<td id="shjgSpan" colspan="3">&lt;
						
					</td>
				</tr>
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> ������
						<br />
						<font color="red">(��200��)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=sbsh&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 50px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" name="����" onclick="dazcshSingleSave();return false;">
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