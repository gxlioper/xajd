<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>	
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript">

		//ͨ��
		function tongguo(){
			confirmInfo("ȷ��ͨ����",function(ta){
				if(ta=="ok"){
					var cfId = $("cfId").value;
					var sfZgj = $("sfZgj").value; 
					var xh = $("xh").value;
					var spgwId = $("spgwId").value;
					var cflbdm = $("cflbdm").value;
					var xcsZt = $("xcsZt").value;
					var shyj = $("shyj").value;
					if(sfZgj=="true"){
						var cfwh = $("cfwh").value;
						var cfsj = $("cfsj").value; 
						if(cfwh==""||cfsj==""){
							showAlertDivLayer("�뽫��*����Ŀ��д������");
							return false;
						}else{
							var url = "general_wjcf_cfsh_ajax.do?method=saveCfsh&doType=tg";
							//����
						 	var parameter = {
						 		"cfId":cfId,
						 		"xh":xh,
						 		"spgwId":spgwId,
						 		"cflbdm":cflbdm,
						 		"xcsZt":xcsZt,
						 		"cfwh":escape(cfwh),
						 		"cfsj":cfsj,
						 		"shyj":escape(shyj)
							};
							jQuery.post(url,
								parameter,
								function(result){
									 showAlert("�����ɹ�",{},{"clkFun":function(){
						    				if (parent.window){
						    					refershParent();
						    				}
						    			}});
							});
							}
					}else{
						var url = "general_wjcf_cfsh_ajax.do?method=saveCfsh&doType=tg";
						//����
					 	var parameter = {
					 		"cfId":cfId,
					 		"xh":xh,
					 		"spgwId":spgwId,
					 		"cflbdm":cflbdm,
					 		"xcsZt":xcsZt,
					 		"shyj":escape(shyj)
						};
						jQuery.post(url,
								parameter,
								function(result){
							 	showAlert("�����ɹ�",{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
						});
						}
					}
			});
		}

		//ͨ�����ύ
		function tongguoBtj(){
			var cfId = $("cfId").value;
			var sfZgj = $("sfZgj").value; 
			var xh = $("xh").value;
			var spgwId = $("spgwId").value;
			var cflbdm = $("cflbdm").value;
			var xcsZt = $("xcsZt").value;
			var shyj = $("shyj").value;
			
				var cfwh = $("cfwh").value;
				var cfsj = $("cfsj").value; 
				if(cfwh==""||cfsj==""){
					showAlertDivLayer("�뽫��*����Ŀ��д������");
					return false;
				}else{
					var url = "general_wjcf_cfsh_ajax.do?method=saveCfsh&doType=tg";
					//����
				 	var parameter = {
				 		"cfId":cfId,
				 		"xh":xh,
				 		"spgwId":spgwId,
				 		"cflbdm":cflbdm,
				 		"xcsZt":xcsZt,
				 		"cfwh":escape(cfwh),
				 		"cfsj":cfsj,
				 		"shyj":escape(shyj)
					};
					jQuery.post(url,
						parameter,
						function(result){
							var url = "general_wjcf_cfsh_ajax.do?method=zjtjSh";
									//����
								var parameter = {
								 	"cfId":cfId
								};
								jQuery.post(url,
										parameter,
										function(result){
									 	showAlert("�����ɹ�",{},{"clkFun":function(){
						    				if (parent.window){
						    					refershParent();
						    				}
						    			}});
								});
					});
					}
		}
		
		//��ͨ��
		function butongguo(){
			confirmInfo("ȷ����ͨ����",function(tag){
				if(tag=="ok"){
					var cfId = $("cfId").value;
					var xh = $("xh").value;
					var spgwId = $("spgwId").value;
					var cflbdm = $("cflbdm").value;
					var xcsZt = $("xcsZt").value;
					var shyj = $("shyj").value;
					var url = "general_wjcf_cfsh_ajax.do?method=saveCfsh&doType=btg";
					//����
				 	var parameter = {
				 		"cfId":cfId,
				 		"xh":xh,
				 		"spgwId":spgwId,
				 		"cflbdm":cflbdm,
				 		"xcsZt":xcsZt,
				 		"shyj":escape(shyj)
					};
					jQuery.post(url,
							parameter,
							function(result){
							showAlert("�����ɹ�",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
						});
				}
			});
		}

		//�˻�
		function tuihui(){
			confirmInfo("ȷ���˻���",function(tag){
				if(tag=="ok"){
					var cfId = $("cfId").value;
					var xh = $("xh").value;
					var spgwId = $("spgwId").value;
					var cflbdm = $("cflbdm").value;
					var xcsZt = $("xcsZt").value;
					var shyj = $("shyj").value;
					var url = "general_wjcf_cfsh_ajax.do?method=saveCfsh&doType=th";
					//����
				 	var parameter = {
				 		"cfId":cfId,
				 		"xh":xh,
				 		"spgwId":spgwId,
				 		"cflbdm":cflbdm,
				 		"xcsZt":xcsZt,
				 		"shyj":escape(shyj)
					};
					jQuery.post(url,
							parameter,
							function(result){
							showAlert("�����ɹ�",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
						});
				}
			});
		}

		//����
		function fjxz(){
			var url="general_wjcf_cfsb_ajax.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		</script>
	</head>
	<body >
		<html:form action="/general_wjcf_cfsb_ajax" method="post">
					<input type="hidden" name="url" id="url" value="general_wjcf_cfsb_ajax.do?method=zjWjcf">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">	
					<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
					<input type="hidden" name="cfId" id="cfId" value="${wjsb.cfid}"/>
					<input type="hidden" name="fjmc" id="fjmc" value="${wjsb.fjmc}"/>
					<input type="hidden" name="message" id="message" value="${message }">	
					<input type="hidden" name="doType" id="doType"  >	
					<input type="hidden" name="sfZgj" id="sfZgj"  value="${sfZgj }">	
					<input type="hidden" name="spgwId" id="spgwId"  value="${spgw}">	
					<input type="hidden" name="cflbdm" id="cflbdm"  value="${cflbdm}">	
					<input type="hidden" name="xcsZt" id="xcsZt"  value="${cfsh.shzt}">	
					<input type="hidden" name="tgCz" id="tgCz"  value="${tgCz}">	
				
				<div  style="width:100%;height:810px;overflow-x:hidden;overflow-y:auto;">
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
						<td align="right" width="18%">
							ѧ�ţ�
						</td>
						<td align="left" width="28%">
							${rs.xh}
						</td>
						<td align="right" width="20%">
							������
						</td>
						<td align="left" width="28%">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
							<td align="left">
							${rs.xb}
						</td>
						<td align="right">
							�꼶��
						</td>
							<td align="left">
							${rs.nj}
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xb" />��
						</td>
							<td align="left">
							${rs.xymc}
						</td>
						<td align="right">
							רҵ��
						</td>
							<td align="left">
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							${rs.bjmc}
						</td>
						<td align="right">
							������ò��
						</td>
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
						<td align="right" width="20%">
							����ѧ�꣺
						</td>
						<td align="left" width="30%">
							${wjsb.xn}
						</td>
						<td align="right" width="20%">
							����ѧ�ڣ�
						</td>
						<td align="left" width="30%">
							${wjsb.xqmc}
						</td>
					</tr>
					<tr>
						<td align="right">
							����ԭ��
						</td>
						<td align="left">
							${wjsb.cfyymc}
							
						</td>
						<td align="right">
							�������
						</td>
						<td align="left">
							${wjsb.cflbmc}
						</td>
					</tr>
					<tr>
						<td align="right">
							Υ��ʱ�䣺
						</td>
						<td align="left">
							${wjsb.wjsj}
						</td>
						<td align="right">
							�������������ϻ򸽼���
						</td>
						<td align="left">
							<logic:notEmpty name="wjsb" property="fjmc">
								<a  href="#" onclick="fjxz();return false;" class="name">���ظ���</a>
							</logic:notEmpty>
						</td>
					</tr>
						<tr>
						<td align="right">
							Υ����ʵ������
						</td>
							<td align="left" colspan="3" style="word-break:break-all;width:100%" >
								${wjsb.wjssjg}
						</td>
					</tr>
						<tr>
						<td align="right">
							��ע��
						</td>
							<td align="left" colspan="3" style="word-break:break-all;width:100%" >
								${wjsb.bz}
						</td>
						</td>
					</tr>
					<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<logic:equal name ="sfZgj" value="true">
							<tr>
								<td align="right">
									<font color="red">*</font>�����ĺţ�
								</td>
								<td align="left"  >
									<html:text property='cfwh' styleId="cfwh" maxlength="30" value="${cfsh.cfwh}"/>
								</td>
								<td align="right">
									<font color="red">*</font>����ʱ�䣺
								</td>
								<td align="left">
								<html:text property="cfsj" styleId="cfsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('cfsj','y-mm-dd');" value="${cfsh.cfsj}"/>
							</td>
							</tr>
						</logic:equal>
						<tr>
							<td align="right" width="15%">
							��������<br/>
							<font color="red"><B>(��1000��)</B></font>
						</td>
						<td align="left" colspan="3" >									
								<textarea  id="shyj" name="shyj"  value="${cfsh.shyj}" rows="5" onblur="checkLen(this,1000)" style="width:600px"></textarea>
								
						</td>
					</tr>
					</tbody>
					
					<table width="100%" border="0" class="formlist">
							<thead>
							<tr>
								<th colspan="4">
									<span>����������</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
						<td colspan="5">
							<table class="formList" width="100%">
								
								<tbody align="center">
							<logic:notEmpty name="cfshList">
							<logic:iterate name="cfshList" id="s">
										<tr >
												<th>
												��λ����
												</th>
												<td align="left">
												${s.gwmc}
												</td>
												<th>
												���״̬
												</th>
												<td align="left">
												
												<logic:equal name="s" property="shzt" value="tg">
												<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="wsh">
													<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="btg">
													<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="th">
													<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="xcs">
													<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
												</logic:equal>
												</td>
												<th>
												���ʱ��
												</th>
											<td align="left">
												${s.shsj}
											</td>
											<th>
												�����
											</th>
											<td align="left">
												${s.shrxm}
											</td>
											</tr>
											<tr>
											<th>
												������
											</th>
											<td colspan="7" style="word-break:break-all;width:100%" align="left" >
												${s.shyj}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
					</tbody>
					</table>
					</td>
					</tr>
					</tbody>
					</table>
					
					<table width="100%" border="0" class="formlist">
					
						<thead>
							<tr>
								<th colspan="4">
									<span>���ܴ������</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<td colspan="4">
							<table class="formList" width="100%">
								<thead align="center">
									<tr align="center">
										<td width="15%"><b>ѧ��</b></td>
										<td width="10%"><b>ѧ��</b></td>
										<td width="10%"><b>�������</b></td>
										<td width="20%"><b>����ԭ��</b></td>
										<td width="20%"><b>����ʱ��</b></td>
										<td width="25%"><b>�����ĺ�</b></td>
									</tr>
								</thead>
								<tbody align="center">
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
									<tr style="height:22px">
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
										</tr>
							</logic:empty>
							</tbody>
							</table>
							</td>
				</tr>
					</tbody>
					</table>
					
				</table>
				</table>
			</div>
			</div>
			<table width="100%" border="0" class="formlist" >
					
				<tfoot>
					<tr>
						<td colspan="4">
							
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
								
							</div>
							<div class="btn">
							<logic:equal property="sftj" name ="cfsh" value="0">
								
									<button type="button" onclick="tongguo();return false;" >
											ͨ��
										</button>
									<logic:equal name ="sfZgj" value="true">
									<button type="button"  onclick="tongguoBtj();return false;">
											ͨ�����ύ
										</button>
									</logic:equal>
									<button type="button"    onclick="butongguo();return false;" >
										��ͨ��
									</button>
									
									<logic:equal name ="sfDyj" value="false">
										
										<button type="button"    onclick="tuihui();return false;" >
											�˻�
										</button>
									</logic:equal>
								
								</logic:equal>
								<button type="button"  onclick="Close();return false;" >
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
				</input>
			
		</html:form>
		
	</body>
</html>
