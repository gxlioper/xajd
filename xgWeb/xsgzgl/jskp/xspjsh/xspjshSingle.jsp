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
					jQuery("#shjg").change(function(){
						var tempvalue = this.value;
						if(tempvalue == "2" || tempvalue == "3"){
							jQuery("#fs").val("");
							jQuery("#pftr").hide();
						}else{
							jQuery("#pftr").show();
						}
					})
				});
			});
			
			/**
			 * ������˱���
			 * @return
			 */
			function xspjshSingleSave(){
				
				var shjg = jQuery("#shjg").val();
				
				if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""|| (shjg == "1" && jQuery("#fs").val() == "")){
					showAlert("�뽫��<font color='red'>*</font>����д������");
					return false;
				}
				/*�жϷ����Ƿ����0С��10*/
				if ("1" == shjg){
					var fs = jQuery("#fs").val();
					if(parseFloat(fs) > 9999 || parseFloat(fs) < 0){
						showAlert("�����������������ڣ�");
						return false;
					}
				}

				var url = "xspj_xspjsh.do?method=xspjshSingleSave";
				ajaxSubFormWithFun("xspjshForm",url,function(data){
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
	<html:form action="/xspj_xspjsh" method="post" styleId="xspjshForm">
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
							<th>��Ŀ����</th>
							<td>${rs.xmmc}</td>
							<th>ָ������</th>
							<td>${rs.zdbmmc}</td>
						</tr>
							<th>��Ŀ���</th>
							<td>${rs.xmlbmc}</td>
							<th>����ʱ��</th>
							<td>${rs.cysj}</td>
						<tr>
							<th>������(ѧ��)</th>
							<td>${rs.xh}</td>
							<th>ѧ��</th>
							<td>${rs.xn}</td>
						</tr>
						<tr>
							<th>��ѧ��</th>
							<td>${rs.dxqmc}</td>
							<th>��ֵ</th>
							<td>${rs.fz}</td>
						</tr>
						<tr>
							<th>������</th>
							<td>${rs.fzrxm}</td>
							<th>������<br/>��ϵ��ʽ</th>
							<td>${rs.fzrlxfs}</td>
						</tr>
						<tr>
							<th>ָ����ʦ</th>
							<td>${rs.zdlsxm}</td>
							<th>ָ����ʦ<br/>��ϵ��ʽ</th>
							<td>${rs.zdlslxfs}</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden name="rs" property="fjid" styleId="fjid"/>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
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
						<font color="red">*</font>��˽��
					</th>
					<td id="shjgSpan" colspan="3">&lt;
						
					</td>
					
				</tr>
				<tr id="pftr">
					<th >
						<font color="red">*</font>����
					</th>
					<td colspan="3">
						<html:text style="width:60px" property="fs" styleId="fs"  value="${shxxLevel.zd3}" maxlength="4" onkeyup="checkInputNum(this)"></html:text>
					</td>
				</tr>
			<!-- ��ѧУҪ��  ���ʱ����Ҫ���ӵ����˵Ĺ��ţ�֪����������������-->
				<tr>
					<th>������Դ��ʽ</th>
					<td colspan="3">
						<font color="blue">${rs.sjlyfs}</font>
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
								<button type="button" name="����"  onclick="xspjshSingleSave();return false;">
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