<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type="text/javascript" src="js/calendar/calendar.js"></script>		
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<style>
		.hh{
			word-wrap:break-word;
		    word-break:break-all;
		    -moz-binding: url('./wordwrap.xml#wordwrap');
		    overflow: hidden;
     	}
		</style>
		<script type="text/javascript">
		//����������Ϣ
		function save(){
			
		}

		//���
		function sssh(ssjg){

		confirmInfo("ȷ��Ҫ������",function(tag){
		 if(tag=="ok" ){
			var url="wjcfCfssgl_cfsssh.do?method=bccfssshSh&doType=save";
			var sswh=jQuery("#sswh").val();
			var sssj=jQuery("#sssj").val();
			var cfid = $("cfid").value;
			var pkValue = $("pkValue").value;
			var xtgwid = $("xtgwid").value;
			var shyj = $("shyj").value;
			var jQshzt=jQuery("#shzt");
			if(ssjg != "th"){
				if(sswh!=null && sswh==""){
					//alertInfo("����д�����ĺ�!");
					showAlertDivLayer("����д�����ĺ�!");
					return false;
				}
				if(sssj!=null && sssj==""){
					//alertInfo("��ѡ������ʱ��!");
					showAlertDivLayer("��ѡ������ʱ��!");
					return false;
				}
			}
			jQshzt.val(ssjg);	
			if("ggcf"==ssjg){
				showCflb();
			}else{
				if(null!=sswh&&""!=sswh&&null!=sssj&&""!=sssj){
					var parameter = {
					 		"cfid":cfid,
					 		"xtgwid":xtgwid,
					 		"pkValue":pkValue,
					 		"sswh":escape(sswh),
					 		"sssj":sssj,
					 		"shzt":ssjg,
					 		"shyj":escape(shyj)
						};
					}
				else{
				 	var parameter = {
				 		"cfid":cfid,
				 		"xtgwid":xtgwid,
				 		"pkValue":pkValue,
				 		"shzt":ssjg,
				 		"shyj":escape(shyj)
					};
				}
				jQuery.post(url,
						parameter,
						function(result){
					if("true"==result&&ssjg != "th"){
						confirmInfo("�����ɹ����Ƿ�Ҫ�������ύ����ʽ�⣿",function(tag){
							if(tag=="ok" ){
								var url = "wjcfCfssgl_cfsssh.do?method=cfssshZjtj";
								//����
							 	var parameter = {
							 		"cfid":cfid,
							 		"pkValue":pkValue,
							 		"sswh":escape(sswh),
							 		"sssj":sssj
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
								}else{
									refershParent();
								}
							}
						);
					}else{
						 showAlert("�����ɹ�",{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
					}
					});
			}
		 }
		}
	);
		}

		//�������
		function showCflb(){
			var url="wjcfCfssgl_cfsssh.do?method=showCflbDiv";

			//��������
		 	var parameter = {
			};
		  	
			jQuery("#div_spgw").load(url,parameter,function(){
			
				var len=jQuery("[name=cflbmcs]").length;
		
				if(len==0){
					alertInfo("û�д��������Ը��ģ�");
					return false;
				}else{
					tipsWindown("ϵͳ��ʾ","id:div_spgw","300","150","true","","true","id");
				}
			});
		}

		//���ȷ�����ύ
		function checkCflb(){
			//var url="wjcfCfssgl_cfsssh.do?method=cfssshSh&doType=save";
			//$("cfggw").value=jQuery("#cflbmcs").val();
			//document.forms[0].action = url;
			//document.forms[0].submit();
			//closeWindown();
			var sswh=jQuery("#sswh").val();
			var sssj=jQuery("#sssj").val();
			var cfid = $("cfid").value;
			var pkValue = $("pkValue").value;
			var xtgwid = $("xtgwid").value;
			var shyj = $("shyj").value;
			var url = "wjcfCfssgl_cfsssh.do?method=bccfssshSh&doType=save";
			//����
		 	var parameter = {
		 		"cfid":cfid,
		 		"xtgwid":xtgwid,
		 		"cfggw":escape(jQuery("#cflbmcs").val()),
		 		"pkValue":pkValue,
		 		"sswh":escape(sswh),
		 		"sssj":sssj,
		 		"shzt":"ggcf",
		 		"shyj":escape(shyj)
			};
			jQuery.post(url,
					parameter,
					function(result){
						if("true"==result){
							confirmInfo("�����ɹ����Ƿ�Ҫ�������ύ����ʽ�⣿",function(tag){
								if(tag=="ok" ){
									var url = "wjcfCfssgl_cfsssh.do?method=cfssshZjtj";
									//����
								 	var parameter = {
								 		"cfid":cfid,
								 		"cfggw":escape(jQuery("#cflbmcs").val()),
								 		"pkValue":pkValue,
								 		"sswh":escape(sswh),
								 		"sssj":sssj
									};
								 	jQuery.post(url,
											parameter,
											 showAlert("�����ɹ�",{},{"clkFun":function(){
								    				if (parent.window){
								    					refershParent();
								    				}
								    			}})
										);
									}else{
										refreshParent();
									}
								}
							);
						}else{
							 showAlert("�����ɹ�",{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
						}
				});
		}
		
		//����
		function xzSsfj(downType){
			var url="";
			if(downType=="ssfj"){
				url="wjcfCfssgl_cfsswh.do?method=cfssfjXz";
			}else if(downType=="cffj"){
				url="wjcfCfssgl_cfsswh.do?method=cffjXz";
			}else{
				return false;
			}
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
				
			
		}
		</script>
	</head>
	<body>
		<html:form action="/wjcfCfssgl_cfsssh.do?method=cfssshSh" method="post">
			<input type="hidden" name="cfid" id="cfid" value="${pkValue}"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
			<input type="hidden" name="xtgwid" id="xtgwid" value="${xtgwid}"/>
			<input type="hidden" name="shzt" id="shzt" />
			<input type="hidden" name="cfggw" id="cfggw" />
			
			<logic:present name="rs">
			<input type="hidden" name="ssfjmc" id="ssfjmc" value="${rs.ssfjmc }"/>
			</logic:present>
			<%--<div class="tab_cur" >
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>${title } - ���</a>
					</p>
			</div>--%>
	
			<div class="tab">
			<div  style="width:100%;height:410px;overflow-x:hidden;overflow-y:auto;">
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
							<th style="width:20%">
								ѧ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xh"/>
								</logic:present>
							</td>
							<th style="width:20%">
								����
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xm"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								�Ա�
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xb"/>
								</logic:present>
							</td>
							<th style="width:20%">
								�꼶
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="nj"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xymc"/>
								</logic:present>
							</td>
							<th style="width:20%">
								רҵ
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="zymc"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								�༶
							</th>
							<td style="width:80%" colspan="3" class="hh">
								<logic:present name="rs">
									<bean:write name="rs" property="bjmc"/>
								</logic:present>
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
							<th style="width:20%">
								����ѧ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xn"/>
								</logic:present>
							</td>
							<th style="width:20%">
								����ѧ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xq"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								�������
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cflbmc"/>
								</logic:present>
							</td>
							<th style="width:20%">
								����ԭ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cfyymc"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								�����ĺ�
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cfwh"/>
								</logic:present>
							</td>
							<th style="width:20%">
								����ʱ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cfsj"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								Υ��ʱ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="wjsj"/>
								</logic:present>
							</td>
							<th style="width:20%">
								�������������ϻ򸽼�
							</th>
							<td style="width:30%">
								<logic:notEmpty name="rs" property="cffjmc">
									<logic:notEqual name="rs" property="cffjmc" value="">
									<a href="#" onclick="xzSsfj('cffj');return false;" class="name">���ظ���</a>
									</logic:notEqual>
									</logic:notEmpty>
									<logic:empty name="rs" property="cffjmc">
									<logic:equal name="rs" property="cffjmc" value="">
									���ϴ����ָ���
									</logic:equal>
									</logic:empty>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								Υ����ʵ����
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								<logic:present name="rs">
									<bean:write name="rs" property="wjssjg"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								��ע
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								<logic:present name="rs">
									<bean:write name="rs" property="bz"/>
								</logic:present>
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
							<th style="width:20%">
								����ʱ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="sqsj"/>
								</logic:present>
							</td>
							<th style="width:20%">
								֤�����ϻ򸽼�
							</th>
							<td style="width:30%">
								<logic:present name="rs">
								${ssfjmc }
									<logic:notEmpty name="rs" property="ssfjmc">
									<logic:notEqual name="rs" property="ssfjmc" value="">
									<a href="#" onclick="xzSsfj('ssfj');return false;" class="name">���ظ���</a>
									</logic:notEqual>
									</logic:notEmpty>
									<logic:empty name="rs" property="ssfjmc">
									<logic:equal name="rs" property="ssfjmc" value="">
									���ϴ����߸���
									</logic:equal>
									</logic:empty>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								��������
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								<logic:present name="rs">
									<bean:write name="rs" property="sqly"/>
								</logic:present>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="ssshList">
						<logic:iterate id="cf" name="ssshList" indexId="ind">
						<logic:notEqual name="cf" property="shzt" value="wsh">
						<logic:notEqual name="cf" property="spgw" value="${xtgwid}">
						<tr>
							<th style="width:20%">
								<bean:write name="cf" property="gwmc"/>��˽��
							</th>
							<td style="width:30%">
								<bean:write name="cf" property="shztzw"/>
							</td>
							<th style="width:20%">
								<bean:write name="cf" property="gwmc"/>�����
							</th>
							<td style="width:30%">
								<bean:write name="cf" property="shrmc"/>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								<bean:write name="cf" property="gwmc"/>������
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								<bean:write name="cf" property="shyj"/>
							</td>
						</tr>
						</logic:notEqual>
						</logic:notEqual>
						</logic:iterate>
						</logic:present>
						<logic:present name="splcDjRs">
						<logic:present name="rsSh">
						<logic:present name="splcDjRs" property="spgw">
						<logic:present name="rsSh" property="xtgwid">
						<logic:equal name="splcDjRs" property="spgw" value="${rsSh.xtgwid}">
						<tr>
							<th style="width:20%">
							<font color="red">*</font>
								�����ĺ�
							</th>
							<td style="width:30%">
								<input type="text" name="sswh" id="sswh"  maxlength="30" value="${cf.sswh}" /> 
							</td>
							<th style="width:20%">
							<font color="red">*</font>
								����ʱ��
							</th>
							<td style="width:30%">
								<html:text property="sssj" styleId="sssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('sssj','y-mm-dd');" value="${cf.sssj}"/>
							</td>
						</tr>
						</logic:equal>
						</logic:present>
						</logic:present>
						</logic:present>
						</logic:present>
						<tr>
							<th style="width:20%">
								�����Ϣ<br />
								<font color="red">(��1000��)</font>
							</th>
							<td  align="left" colspan="3" >
								<logic:present name="rsSh">
								<textarea name="shyj" id="shyj" onblur="checkLen(this,1000)" style="width:600px" rows='5' ><bean:write name="rsSh" property="shyj"/></textarea>
								</logic:present>
								<logic:notPresent name="rsSh">
								<textarea name="shyj" id="shyj" onblur="checkLen(this,1000)" style="height: 60px; margin: 1px; width: 100%;"></textarea>
								</logic:notPresent>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<div style="height: 15px"></div>
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
								<!-- �û���λ������ -->
									<logic:present name="spgwqx">
									<logic:present name="splcDjRs">
									<logic:present name="rsSh">
									<logic:present name="splcDjRs" property="spgw">
									<logic:present name="rsSh" property="xtgwid">
										<logic:equal name="splcDjRs" property="spgw" value="${rsSh.xtgwid}">
										<button type="button"  class="button2"  onclick="sssh('wcycf')">
											ά��ԭ����
										</button>
										<button type="button"  class="button2"  onclick="sssh('cxcf')">
											��������
										</button>
										<button type="button"  class="button2"  onclick="sssh('ggcf')">
											���Ĵ���
										</button>
										</logic:equal>
										<logic:notEqual name="splcDjRs" property="spgw" value="${rsSh.xtgwid}">
											<button type="button"  class="button2"  <bean:write name="spgwqx" property="tg"/> onclick="sssh('tg')">
												ͨ��
											</button>
											<button type="button"  class="button2"  <bean:write name="spgwqx" property="tg"/> onclick="sssh('btg')">
												��ͨ��
											</button>
										</logic:notEqual>
									</logic:present>
									</logic:present>
									</logic:present>
									</logic:present>
									<logic:present name="splcYjRs">
									<logic:present name="rsSh">
									<logic:present name="splcYjRs" property="spgw">
									<logic:present name="rsSh" property="xtgwid">
										<logic:notEqual name="splcYjRs" property="spgw" value="${rsSh.xtgwid}">
										<button type="button"  class="button2"  <bean:write name="spgwqx" property="th"/> onclick="sssh('th')">
											�˻�
										</button>
										</logic:notEqual>
									</logic:present>
									</logic:present>
									</logic:present>
									</logic:present>
									</logic:present>
									<!-- <button type="button"  class="button2"  onclick="Close();return false;">
										�ύ
									</button> -->
									<button type="button"  class="button2"  onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<div id="div_spgw" style="display:none"></div>
			
		</html:form>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					
					showAlert("����ʧ�ܣ�",{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">				
					
					showAlert("�����ɹ���",{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
					</script>
				</logic:equal>
			</logic:notEmpty>
	</body>
</html>
