<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>	
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script language="javascript" src="xsgzgl/wjcf/cfjgglnew/cfjg/js/cfjg.js"></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">

		//���� 
		function save(){
			var xh = jQuery("#xh").val();
			var wjsj = jQuery("#wjsj").val();
			var cfwh = jQuery("#cfwh").val();
			var cfsj = jQuery("#cfsj").val();
			var cfid = jQuery("#cfid").val();
			if(""==xh||cfwh==""||cfsj==""){
				alertError("�뽫��*����Ŀ��д������");
				return false;
			}
			if(wjsj>cfsj){
			alertError("����ʱ�䲻������Υ��ʱ�䣡");
			return false;
			}
			if(jQuery("#xxdm").val()=="12872"){
				if (jQuery(".MultiFile-label").length == 0){		
					return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");;
				}
			}
			if("12686"==jQuery("#xxdm").val()){
				var flag=true;
				jQuery.ajaxSetup({async:false});
				jQuery.post("wjcf_cfsbgl.do?method=checkExistCfwh", {
					cfwh:cfwh,
					cfid:cfid
				}, function(data) {
					if(data ==null || data){
						flag=false;
					}
				},"json");
				jQuery.ajaxSetup({async:true});
				if(!flag){
					showAlert("�ô����ĺ��Ѵ��ڣ����޸ģ�");
					return false;
					}
				}
			// ��֤�����ڽ���⵱���Ƿ���� ����֤������ѧ�š�������𡢴���ʱ��
			jQuery.post("wjcf_cfsbgl.do?method=checkExistCfjg", {
				xh:xh,
				cfid:jQuery("#cfid").val()
			}, function(data) {
				if(data ==null || data){
					showAlert("��ѧ��Υ�����ڴ��ֽ���д��ڣ�");
					return false;
				}else{
				     var url = "wjcf_cfjg.do?method=saveCfjgshlcXg";
				      ajaxSubFormWithFun("cfjgForm",url,function(data){
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
			},"json");
		}
		
		function showCfqxFlag(cflbdm){
			//�����ൺ�Ƶ����ְҵ����ѧԺ���θù���
			if(${xxdm=='13011'}) return false;
			
			if(cflbdm==""){return false;}
			var cfqx = "";
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqx").html(data["message"]);
			},'json');
		}
		function updateInit(){
			var cflbdm = jQuery("#cflbdm").val();
			showCfqxFlag(cflbdm);
			
			jQuery("#cfsj").change(function(){
				defaultCfdqsj();
			});
		}
		</script>
		
	</head>
	<body onload="updateInit();">
		<div class="prompt">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				��ǰΪ�������ݣ��������ֶο��޸ģ�
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/wjcf_cfjg" method="post" enctype='multipart/form-data' styleId="cfjgForm">
					<input type="hidden" name="cfid" id="cfid" value="${rs.cfid }"/>	
					<input type="hidden" name="xh" id="xh" value="${rs.xh }"/>
					<input type="hidden" name="wjsj" id="wjsj" value="${rs.wjsj }"/>
					<input type="hidden" name="cflbdm" id="cflbdm" value="${rs.cflbdm }"/>
					<input type="hidden" name="message" id="message" value="${message }">
					<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }">	
			        <input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
					<html:hidden property="fjmc" styleId="fjmc"/>		
				<div  style="width:100%;height:425px;overflow-x:hidden;overflow-y:auto;">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>ѧ����Ϣ</span>
								</th>
							</tr>
						</thead>
						<%@ include file="/xsgzgl/wjcf/cfsbglnew/cfsb/selectStudent.jsp"%>
						<thead>
							<tr>
								<th colspan="4">
									<span>�����ϱ���Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							����ѧ��
						</th>
						<td align="left" width="30%">
							${rs.xn }
						</td>
						<th align="right" width="20%">
						����ѧ��
						</th>
						<td align="left" width="30%">
							${rs.xq }
						</td>
					</tr>
					<tr>
						<th align="right">
							����ԭ��
						</th>
						<td align="left">
							${rs.cfyymc }
						</td>
						<th align="right">
							�������
						</th>
						<td align="left">
						${rs.cflbmc }
						<span id="showCfqx" style="color: red"></span>
						</td>
					</tr>
					<tr>
						<th align="right">
							Υ��ʱ��
						</th>
						<td align="left">
							${rs.wjsj }
						</td>
						<th align="right">
							�ϱ���
						</th>
						<td align="left" colspan="3">
							${rs.sbbxm }
						</td>
					</tr>
					<logic:equal value="12686" name="xxdm">
					<tr>
						<th align="right">
							���
						</th>
						<td align="left" colspan="3">
						${rs.nd }
						</td>
					</tr>
					</logic:equal>
					<tr>
						<th align="right">
							��������
						</th>
						<td align="left" colspan="3" >
					${rs.cfyj}
						</td>
					</tr>
					<tr>
						<th align="right" >
							<logic:equal name="xxdm" value="12872">
									<font class="red">*</font>
							</logic:equal>�������������ϻ򸽼�
						</th>
						<td colspan="3">
							<html:hidden name="rs" property="filepath" styleId="filepath" />
							<input type="file" id="filepath_f" name="filepath" />
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									jQuery('#filepath_f').multiUploader({
										//����ļ���С ��λM
										maxsize: 10,
										//��Ÿ������������id
										elementid : 'filepath',

										eid : 'filepath_f'
										});
								});
							</script>
						</td>
					</tr>
					<tr>
						<th align="right">
							Υ����ʵ����
						</th>
						<td align="left" colspan="3" >
						${rs.wjssjg}
						</td>
					</tr>
					
					<tr>
						<th align="right">
							<font color="red">*</font>�����ĺ�
						</th>
						<td align="left">
							<html:text property="cfwh" styleId="cfwh" maxlength="30" value="${rs.cfwh }"></html:text>
						</td>
						<th align="right">
							<font color="red">*</font>����ʱ��
						</th>
						<td align="left">
							<html:text property="cfsj" styleId="cfsj"
								style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd',false,'wjsj','','',defaultCfdqsj);" value="${rs.cfsj }"/>
						</td>
					</tr>
					<tr id="cffw_tr3">
						<logic:present name="rs" property="cfdqsj">
							<th align="right">
								<font color="red">*</font>���ֵ���ʱ��
							</th>
							<td align="left"  colspan="3">
								<html:text property="cfdqsj" styleId="cfdqsj"
								style="cursor:hand;"
								onclick="return showCalendar('cfdqsj','y-mm-dd',false,'cfsj');" value="${rs.cfdqsj }"/>
							</td>
						</logic:present>
					</tr>
					<tr>
						<th align="left">
							��ע
						</th>
						<td align="left" colspan="3" >
								${rs.bz}
						</td>
					</tr>
					</tbody>
						<thead>
							<tr>
								<th colspan="4">
									<span>���ܴ������</span>
								</th>
							</tr>
						</thead>
						
						<tr>
						<td colspan="4">
							<table class="formList" width="100%">
								<thead align="left">
									<tr align="left">
										<td ><b>ѧ��</b></td>
										<td ><b>ѧ��</b></td>
										<td><b>�������</b></td>
										<td ><b>����ԭ��</b></td>
										<td ><b>����ʱ��</b></td>
										<td ><b>�����ĺ�</b></td>
									</tr>
								</thead>
								<tbody align="left">
							<logic:notEmpty name="yscfqkList">
							<logic:iterate name="yscfqkList" id="s">
										<tr  style="cursor:hand">
										<td >
												${s.xn}
											</td>
											<td >
												${s.xqmc}
											</td>
											<td >
												${s.cflbmc}
											</td>
											<td >
												${s.cfyymc}
											</td>
											<td>
												${s.cfsj}
											</td>
											<td>
												${s.cfwh}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
							</tbody>
							</table>
							</td>
				</tr>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="save();return false;" id="buttonSave">
									�� ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>
