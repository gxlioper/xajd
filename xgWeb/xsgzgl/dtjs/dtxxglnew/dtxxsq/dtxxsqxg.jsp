<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/dtxxsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				if(jQuery("#shzt").val()=='3'){
					jQuery("#jddm").attr("disabled","disabled");
				}
				<logic:equal value="13871" name="xxdm">
				showRdzys();
				</logic:equal>
			});

			function update(url,type,checkId,keyid){
				if(!check(checkId)){
					showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
					return false;
				}
				var xh=jQuery("#xh").val();
				var dtxxsqid=jQuery("#"+keyid).val();
				lock();
				jQuery.post("dtxxsq.do?method=isCanAdd", {
					xh:xh,dtxxsqid:dtxxsqid,jddm:jQuery("#jddm").val()
				}, function(data) {
					if(data["success"]=="true"){
							var shzt = jQuery("#shzt").val();
							if(shzt!="3" && type=='submit'){
								// ��֤�Ƿ���ύ
								jQuery.post("dtxxsq.do?method=checkSfktj", {
									jddm:jQuery("#jddm").val()
								}, function(data) {
									if(data ==null || data=="false"){
										showAlertDivLayer("������Ľ׶��ѹر����룬�޷��ύ����������ϵ����Ա��");
										return false;
									}else{
										url+= "&type="+ type;
									 	jQuery("#form").ajaxSubmit({
											url:url,
											type:"post",
											dataType:"json",
											success:function(data){
										 		 if(data["message"]=="����ɹ���" ||data["message"]=="�ύ�ɹ���" ){
										    		 showAlert(data["message"],{},{"clkFun":function(){
										    				if (parent.window){
										    					refershParent();
										    				}
										    			}});
										    	 }else{
										    		 showAlert(data["message"]);
										    	 }
											},
											contentType:"application/x-www-form-urlencoded;charset=utf-8"
										});

									}
								});
								
							}else{

								url+= "&type="+ type;
							 	jQuery("#form").ajaxSubmit({
									url:url,
									type:"post",
									dataType:"json",
									success:function(data){
								 		 if(data["message"]=="����ɹ���" ||data["message"]=="�ύ�ɹ���" ){
								    		 showAlert(data["message"],{},{"clkFun":function(){
								    				if (parent.window){
								    					refershParent();
								    				}
								    			}});
								    	 }else{
								    		 showAlert(data["message"]);
								    	 }
									},
									contentType:"application/x-www-form-urlencoded;charset=utf-8"
								});
							}	 	
					}else{
                        showAlert(data["message"]);
					}
				}, 'json');

				unlock();
			}
		</script>
	</head>
	<body>
	<html:form method="post" styleId="form" action="/dtxxsq">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<html:hidden property="dtxxsqid" styleId="dtxxsqid"/>
	<html:hidden property="xh" styleId="xh"/>
	<html:hidden property="shzt" styleId="shzt"/>
		<div style='tab;width:100%;height:370px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ����</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>����׶�����
						</th>
						<td align="left" colspan="3">
								<html:select property="jddm" styleId="jddm" disabled="false"  onchange="showRdzys();">
									<html:options collection="sqJdlist" property="jddm"
										labelProperty="jdmc" />
								</html:select>		
						</td>
						<th hidden align="right" width="10%" name="rdzys">
							�뵳־Ը����
						</th>
						<td hidden align="left"  name="rdzys">
							<html:text property="zd3" maxlength="30" styleId="zd3" style="hidden:true"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>����С��&nbsp;
							<br />
							<font color="red">(��2000��)</font>
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="grxj" styleId="grxj" style="width:97%" onblur="checkLen(this,2000);"></html:textarea>
						</td>
					</tr>
						<tr>
							<th align="right">
								������Ϣ
							</th>
							<td colspan="3">
								<html:hidden property="zd5" styleId="zd5" />
								<input type="file" id="filepath_f" name="zd5" />
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//��׺
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'zd5',

											eid : 'filepath_f'
											});
									});
								</script>
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
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="update('dtxxsq.do?method=update','save','xh-jddm','dtxxsqid');return false;" id="buttonSave">
									����ݸ�
								</button>
								<button type="button"  onclick="update('dtxxsq.do?method=update','submit','xh-jddm','dtxxsqid');return false;" id="buttonSave">
									�ύ����
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
