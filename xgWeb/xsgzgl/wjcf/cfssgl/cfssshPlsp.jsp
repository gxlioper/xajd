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

		//���
		function sssh(ssjg){
			confirmInfo("ȷ��Ҫ������",function(tag){
				 if(tag=="ok" ){
			var url="wjcfCfssgl_cfsssh.do?method=bccfssshPlsh&doType=save";
			var sswh=jQuery("#sswh").val();
			var sssj=jQuery("#sssj").val();
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
					//����
				 	var parameter = {
				 		"xtgwid":xtgwid,
				 		"pkValue":pkValue,
				 		"sswh":escape(sswh),
				 		"sssj":sssj,
				 		"shzt":ssjg,
				 		"shyj":escape(shyj)
					};
				}else{
						var parameter = {
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
				 }
			});
			
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
					showAlertDivLayer("û�д��������Ը��ģ�");
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
			var pkValue = $("pkValue").value;
			var xtgwid = $("xtgwid").value;
			var shyj = $("shyj").value;
			var url = "wjcfCfssgl_cfsssh.do?method=bccfssshPlsh&doType=save";
			//����
		 	var parameter = {
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
		
		</script>
	</head>
	<body>
		<html:form action="/wjcfCfssgl_cfsssh.do?method=cfssshSh" method="post">
			<input type="hidden" name="pkValue" id="pkValue"  value="${pkValue}"/>
			<input type="hidden" name="xtgwid" id="xtgwid"  value="${xtgwid}"/>
			<input type="hidden" name="shzt" id="shzt" />
			<input type="hidden" name="cfggw" id="cfggw" />
			<logic:present name="rs">
			<input type="hidden" name="ssfjmc" id="ssfjmc" value="${rs.ssfjmc }"/>
			</logic:present>
			<%--<div class="tab_cur" >
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>${title } - �������</a>
					</p>
			</div>--%>
		
			<div class="tab">
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
								<bean:write name="cf" property="shr"/>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								<bean:write name="cf" property="gwmc"/>������
							</th>
							<td style="width:30%" colspan="3"  class="hh">
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
								<input type="text" name="sswh" id="sswh"  maxlength="30" /> 
							</td>
							<th style="width:20%">
							<font color="red">*</font>
								����ʱ��
							</th>
							<td style="width:30%">
							<html:text property="sssj" styleId="sssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('sssj','y-mm-dd');"/>
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
								<font color="red"><B>(��1000��)</B></font>
							</th>
							<td style="width:80%" colspan="3">
								<textarea name="shyj" id="shyj" onblur="chLeng(this,1000)" style="height: 60px; margin: 1px; width: 100%;"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
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
				
			</div>
			<div id="div_spgw" style="display:none"></div>
		</html:form>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("����ʧ�ܣ�",function(){
							refreshParent2();
						});
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("�����ɹ���",function(){
							refreshParent2();
						});
					</script>
				</logic:equal>
			</logic:notEmpty>
	</body>
</html>
