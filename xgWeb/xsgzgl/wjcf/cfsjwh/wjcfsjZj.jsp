<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>	
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>		
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript">

		//���� 
		function save(){
			var xh = $("xh").value;
			var cfyymc = $("cfyydm").value;
			var cflbmc = $("cflbdm").value;
			var cflbdm = jQuery("#cflbdm").val();
			var cfwh = $("cfwh").value;
			var cfsj = $("cfsj").value;
			var wjsj = $("wjsj").value;
			var fjmc = $("fj").value;
			if (fjmc != null && fjmc != "") {
				var hz = fjmc.substr(fjmc.lastIndexOf(".")+1,fjmc.length);
				if (hz!='doc'&&hz!='rar'&&hz!='pdf'&&hz!='bmp'&&hz!='jpg'&&hz!='gif'&&hz!='png'){
					alertError("�ϴ��ļ�����ֻ��Ϊ��doc,rar,pdf,bmp,jpg,gif,png");
					return false;
				}
			}
			jQuery("#fjmc").val(fjmc);
			if(""==xh||""==cfyymc||""==cflbmc||""==wjsj||cfwh==""||cfsj==""){
				alertError("�뽫��*����Ŀ��д������");
				return false;
			}
			if(""!=wjsj&&cfsj!=""){
				if(parseInt(wjsj)>(parseInt(cfsj))){
					alertError("Υ��ʱ�䲻�ܴ��ڴ���ʱ�䣡");
					return false;
					}
				}


			// ��֤�����ڽ���⵱���Ƿ���� ����֤������ѧ�š�������𡢴���ʱ��
			jQuery.post("wjcf_cfsbgl.do?method=checkExistCfjg", {
				xh:xh,
				cflbdm:cflbdm,
				wjsj:wjsj
			}, function(data) {
				if(data ==null || data){
					showAlert("��ѧ����"+wjsj+"��Υ�����ڴ��ֽ���д��ڣ�");
					return false;
				}else{
				     var url = "savewjcfsjZj.do";
				      ajaxSubFormWithFun("cfsjwhForm",url,function(data){
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
			if(cflbdm==""){return false;}
			var cfqx = "";
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqx").html(data["message"]);
			},'json');
		}
		</script>
		
	</head>
	<body >
		<html:form action="/wjcfCfshwh_cfsjwh" method="post" enctype='multipart/form-data' styleId="cfsjwhForm">
					<input type="hidden" name="url" id="url" value="wjcfCfshwh_cfsjwh.do?method=wjcfsjZj">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">		
					<input type="hidden" name="message" id="message" value="${message }">	
								<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
					<input type="hidden" name="doType" id="doType"  >
					<html:hidden property="fjmc" styleId="fjmc"/>	
				<div  style="width:100%;height:460px;overflow-x:hidden;overflow-y:auto;">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>ѧ����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" width="30%">
							
							<html:text  property="xh" styleId="xh" value="${rs.xh}"  
							 maxlength="20" readonly="false"/>
						<script type="text/javascript">
			jQuery(function(){
			 var gotoPath="wjcfCfshwh_cfsjwh.do?method=wjcfsjZj";
			jQuery("#xh").keydown(function(event){
				     if(13==event.keyCode){
				      var xh=jQuery("#xh").val();
					  selectStudent(xh,gotoPath);
					
					}
				});
			  jQuery("#xh").blur(function(){
				     var xsxh=jQuery("#xh").val();
					selectStudent(xsxh,gotoPath)
				});
				function selectStudent(xsxh,gotoPath){
			
				if (gotoPath.split("?").length > 1){
					gotoPath = gotoPath+"&xh="+xsxh;
				} else {
					gotoPath = gotoPath+"?xh="+xsxh;
				}
				var api = frameElement.api;
					if (api){
						api.reload(api.get('parentDialog') ,gotoPath);
					} else{
						var W = api.opener;
						W.location=gotoPath;
				} 
				}
			});
		</script>
							<button type="button"   onclick="showDialog('��ѡ��һ��ѧ��',800,550,'xsxx_xsgl.do?method=showStudents&goto=wjcfCfshwh_cfsjwh.do?method=wjcfsjZj');return false;"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
						<th align="right" width="20%">
							����
						</th>
						<td align="left">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<th align="right">
							�Ա�
						</th>
							<td align="left">
							${rs.xb}
						</td>
						<th align="right">
							�꼶
						</th>
							<td align="left">
							${rs.nj}
						</td>
					</tr>
					<tr>
						<th align="right">
							<bean:message key="lable.xb" />
						</th>
							<td align="left">
							${rs.xymc}
						</td>
						<th align="right">
							רҵ
						</th>
							<td align="left">
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							�༶
						</th>
						<td align="left">
							${rs.bjmc}
						</td>
						<th align="right">
							������ò
						</th>
							<td align="left">
							${rs.zzmmmc}
						</td>
						
					</tr>
					
					</tbody>
					<table width="100%" border="0" class="formlist">
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
							<font color="red">*</font>����ѧ��
						</th>
						<td align="left" width="30%">
							<html:select property="xn" styleId="xn" style="width:140px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
							</html:select>
						</td>
						<th align="right" width="20%">
							<font color="red">*</font>����ѧ��
						</th>
						<td align="left" width="30%">
							<html:select property="xq" styleId="xq" style="width:140px">
								<html:option value=""></html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>����ԭ��
						</th>
						<td align="left">
							<html:select property="cfyydm" styleId="cfyydm" style="width:140px">
								<html:option value=""></html:option>
								<html:options collection="cfyyList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<th align="right">
							<font color="red">*</font>�������
						</th>
						<td align="left">
							<html:select property="cflbdm" styleId="cflbdm" style="width:140px" onchange="showCfqxFlag(this.value);">
								<html:option value=""></html:option>
								<html:options collection="cflbList" property="dm" labelProperty="mc"/>
							</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
						</td>
					</tr>
					
					<tr>
						<th align="right">
							<font color="red">*</font>Υ��ʱ��
						</th>
						<td align="left">
							<html:text property="wjsj" styleId="wjsj"
								style="cursor:hand;"
								onclick="return showCalendar('wjsj','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<th align="right">
							�������������ϻ򸽼�
							<br/>(��doc,rar,ͼƬ��ʽ)
						</th>
						<td align="left" colspan="3">
							<html:file property='fj' styleId ="fj" style="width:99%" />
						</td>
					</tr>
					<tr>
						<th align="right">
							Υ����ʵ����
						</th>
						<td align="left" colspan="3" >
								<html:textarea  property='wjssjg' style="width:600px"
								rows='4'  onblur="checkLen(this,1000)"/>
						</td>
					</tr>
					
					<tr>
						<th align="right">
							<font color="red">*</font>�����ĺ�
						</th>
						<td align="left">
							<html:text property="cfwh" styleId="cfwh" maxlength="30" ></html:text>
						</td>
						<th align="right">
							<font color="red">*</font>����ʱ��
						</th>
						<td align="left">
							<html:text property="cfsj" styleId="cfsj"
								style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd');" />
						</td>
					</tr>
					
						<tr>
						<th align="right">
							��ע
						</th>
						<td align="left" colspan="3" >
								<html:textarea  property='bz' style="width:600px"
								rows='4' onblur="checkLen(this,1000)"/>
						</td>
					</tr>
					
					
					
					</tbody>
					
					<table width="100%" border="0" class="formlist">
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
							<logic:empty name="yscfqkList">
									<tr style="height: 22px">
										<td colspan="6" align="center">û��Υ�ͼ�¼</td>
									</tr>
							</logic:empty>
							</tbody>
							</table>
							</td>
				</tr>
						
						<tbody>
					
					</tbody>
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
								<button type="button"    onclick="save();return false;" id="buttonSave">
									�� ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"    onclick="Close();return false;" id="buttonClose">
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
