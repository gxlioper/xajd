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
			confirmInfo("ȷ��ͨ����",function(tag){
				if(tag=="ok"){
			var cfId = $("cfId").value;
			var sfZgj = $("sfZgj").value; 
			var spgwId = $("spgwId").value;
			var shyj = $("shyj").value;
			if(sfZgj=="true"){
				var jcwh = $("jcwh").value;
				var jcsj = $("jcsj").value; 
				if(jcwh==""||jcsj==""){
					alertError("�뽫��*����Ŀ��д������");
					return false;
				}else{
					var url = "general_wjcf_cfjcsh_ajax.do?method=saveCfjcsh&doType=tg";
					//����
				 	var parameter = {
				 		"cfId":cfId,
				 		"spgwId":spgwId,
				 		"jcwh":escape(jcwh),
				 		"jcsj":jcsj,
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
				var url = "general_wjcf_cfjcsh_ajax.do?method=saveCfjcsh&doType=tg";
				//����
			 	var parameter = {
			 		"cfId":cfId,
			 		"spgwId":spgwId,
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
			confirmInfo("ȷ��ͨ����",function(tag){
				if(tag=="ok"){
			var cfId = $("cfId").value;
			var sfZgj = $("sfZgj").value; 
			var spgwId = $("spgwId").value;
			var shyj = $("shyj").value;
			var jcwh = $("jcwh").value;
			var jcsj = $("jcsj").value; 
				if(jcwh==""||jcsj==""){
					alertError("�뽫��*����Ŀ��д������");
					return false;
				}else{
					var url = "general_wjcf_cfjcsh_ajax.do?method=saveCfjcsh&doType=tg";
					//����
				 	var parameter = {
				 		"cfId":cfId,
				 		"spgwId":spgwId,
				 		"jcwh":escape(jcwh),
				 		"jcsj":jcsj,
				 		"shyj":escape(shyj)
					};
					jQuery.post(url,
						parameter,
						function(result){
						var url = "general_wjcf_cfjcsh_ajax.do?method=zjtjSh";
									//����
								var parameter = {
								 		"cfId":cfId,
								 		"jcwh":escape(jcwh),
								 		"jcsj":jcsj
							};
								jQuery.post(url,
									parameter,
									function(result){
									 showAlert("�����ɹ�",{},{"clkFun":function(){
						    				if (parent.window){
						    					refershParent();
						    				}
						    			}});
									}
									);
					});
					}
			
				}
			});
		}
		
		//��ͨ��
		function butongguo(){

			confirmInfo("ȷ����ͨ����",function(tag){
				if(tag=="ok"){
					var cfId = $("cfId").value;
					var spgwId = $("spgwId").value;
					var shyj = $("shyj").value;
					var url = "general_wjcf_cfjcsh_ajax.do?method=saveCfjcsh&doType=btg";
					//����
				 	var parameter = {
				 		"cfId":cfId,
				 		"spgwId":spgwId,
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
					var spgwId = $("spgwId").value;
					var shyj = $("shyj").value;
					var url = "general_wjcf_cfjcsh_ajax.do?method=saveCfjcsh&doType=th";
					//����
				 	var parameter = {
				 		"cfId":cfId,
				 		"spgwId":spgwId,
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
		function fjxz(type){
			var url="";
			if(type=="sbfj"){
				var sbfj = $("sbfj").value;
				$("fjmc").value=sbfj;
				url="general_wjcf_cfjc_ajax.do?method=fjxz";
				}
			if(type=="ssfj"){
				var ssfj = $("ssfj").value;
				$("fjmc").value=ssfj;
				url="general_wjcf_cfjc_ajax.do?method=ssFjxz";
				}
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		</script>
	</head>
	<body >
		<html:form action="/general_wjcf_cfjcsh_ajax" method="post">
					<input type="hidden" name="url" id="url" value="general_wjcf_cfsb_ajax.do?method=zjWjcf">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">	
					<html:hidden property="xh" styleId="xh" value="${rs.xh}" />
					<html:hidden property="cfId" styleId="cfId" value="${wjsb.cfid}" />
					<input type="hidden" name="message" id="message" value="${message }">	
					<input type="hidden" name="doType" id="doType"  >	
					<input type="hidden" name="sfZgj" id="sfZgj"  value="${sfZgj }">	
					<html:hidden property="spgwId" styleId="spgwId" value="${spgw}" />
					<input type="hidden" name="fjmc"  id="fjmc"  value=""/>
					<input type="hidden" id="sbfj" name="sbfj" value="${wjsb.fjmc}"/>
					<input type="hidden" id="ssfj" name="ssfj" value="${wjsb.ssfjmc}"/>
				<div  style="width:100%;height:800px;overflow-x:hidden;overflow-y:auto;">
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
							ѧԺ��
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
									<span>������Ϣ</span>
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
								<a  href="#" onclick="fjxz('sbfj');return false;" class="name">���ظ���</a>
							</logic:notEmpty>
						</td>
					</tr>
						<tr>
						<td align="right" width="30%">
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
									<span>����������Ϣ</span>
								</th>
							</tr>
						</thead>
					<tr>
						<td align="right">
							������������ѧ��
						</td>
						<td align="left">
							${wjsb.xn}
						</td>
						<td align="right">
							������������ѧ�ڣ�
						</td>
						<td align="left">
							${wjsb.xqmc}
						</td>
					</tr>
					<tr>
					<td align="right">
							������������ʱ��
						</td>
						<td align="left" >
							${cfss.sqsj}
						</td>
						<td align="right">
							����������ϻ򸽼���
						</td>
						<td align="left">
							<logic:notEmpty name="wjsb" property="ssfjmc">
								<a  href="#" onclick="fjxz('ssfj');return false;" class="name">���ظ���</a>
							</logic:notEmpty>
						</td>
					</tr>
						<tr>
					<td align="right">
							����������������
						</td>
						<td align="left" colspan="3" style="word-break:break-all;width:100%">
							${cfss.sqly}
						</td>
					</tr>
					<thead>
							<tr>
								<th colspan="4">
									<span>��������Ϣ</span>
								</th>
							</tr>
						</thead>
					<tr>
						<td align="right">
							���ֽ������ѧ��
						</td>
						<td align="left">
							${wjsb.xn}
						</td>
						<td align="right">
							���ֽ������ѧ�ڣ�
						</td>
						<td align="left">
							${wjsb.xqmc}
						</td>
					</tr>
					<tr>
					<td align="right">
							���ֽ������ʱ��
						</td>
						<td align="left" colspan="3">
							${cfjc.sqsj}
						</td>
					</tr>
						<tr>
					<td align="right">
							���ֽ����������
						</td>
						<td align="left" colspan="3" style="word-break:break-all;width:100%">
							${cfjc.sqly}
						</td>
					</tr>
					<thead>
							<tr>
								<th colspan="4">
									<span>��˽����Ϣ</span>
								</th>
							</tr>
						</thead>
						<logic:equal name ="sfZgj" value="true">
							<tr>
								<td align="right">
									<font color="red">*</font>���ֽ���ĺţ�
								</td>
								<td align="left"  >
									<html:text property='jcwh' styleId="jcwh" maxlength="30" value="${cfsh.jcwh}"/>
								</td>
								<td align="right">
									<font color="red">*</font>���ֽ��ʱ�䣺
								</td>
								<td align="left">
								<html:text property="jcsj" styleId="jcsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('jcsj','y-mm-dd');" value="${cfsh.jcsj}"/>
							</td>
							</tr>
						</logic:equal>
						<tr>
							<td align="right" width="15%">
							��������<br/>
							<font color="red"><B>(��1000��)</B></font>
						</td>
						<td align="left" colspan="3" >
									<html:textarea  property='shyj' styleId='shyj'  style="width:600px"
									rows='5' value="${cfsh.shyj}" onblur="checkLen(this,1000)"/>
						</td>
					</tr>
					</tbody>
					
					<table width="100%" border="0" class="formlist">
							<thead>
							<tr>
								<th colspan="4">
									<span>���ֽ��������</span>
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
											<td colspan="7" style="word-break:break-all;width:100%" align="left">
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
								
									<button type="button"  id="btn_tg" onclick="tongguo();return false;" id="buttonSave">
											ͨ��
										</button>
									<logic:equal name ="sfZgj" value="true">
									<button type="button"  id="btn_tg" onclick="tongguoBtj();return false;" id="buttonSave">
											ͨ�����ύ
										</button>
									</logic:equal>	
									<button type="button"  id="btn_btg"  onclick="butongguo();return false;" id="buttonSave">
										��ͨ��
									</button>
									
									<logic:equal name ="sfDyj" value="false">
										<button type="button"  id="btn_th"  onclick="tuihui();return false;" id="buttonSave">
											�˻�
										</button>
									</logic:equal>
								
								</logic:equal>
								<button type="button"  onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
				</input>
			
		<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
