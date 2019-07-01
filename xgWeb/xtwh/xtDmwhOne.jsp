<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzDao.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script type="text/javascript" src="js/sxjyFunction.js"></script>
		<script type='text/javascript' src='js/xtwh/codeYz.js'></script>
		<script type='text/javascript' src='js/check.js'></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		
		<script type="text/javascript">
			function changeOps(tableName, dm, mc,id,pk,zbpk){
				
				var msg = "----请选择----";
				var pks = new Array;
				//alert(zbpk);
				pks = zbpk.split(',');
				var pkValue = ""; 
				for(var i = 0;i<pks.length;i++){
					pkValue += $(pks[i]).value;
					//alert(pks[i]); 	
				}
				
				if(pkValue == ""){
					pk = "";
				}else{
					pk.replace(',','||');
				}
				document.getElementsByName(id)[1].id=id+'t';
				//alert(tableName+','+dm+','+mc+','+msg+','+pk+','+pkValue);
				getXszzInfo.getXszzList(tableName, dm, mc, msg, pk, pkValue,function(data){
					if(data!=null){
						DWRUtil.removeAllOptions(id+'t');
						DWRUtil.addOptions(id+'t',data,"dm","mc");
					}
				});
					
			}
			
			function ymcsh(){
					var selects = document.getElementsByTagName('select');
					var leng = selects.length;
				    for(var i = 0;i<leng;i++){
					    var selId=selects[i].id;
				    	var value = $(document.getElementById(selects[i].id+'t')).value;
				    	
						var HtmlText = selects[i].outerHTML;
				    	for(var k = 0;k<document.getElementsByName('opTwoAssociateK').length;k++){
				    	 	if(document.getElementsByName('opTwoAssociateK')[k].value.indexOf(selects[i].id)!='-1'){
				    	 		dwrMsg = document.getElementsByName('opTwoAssociateV')[k].value.split('!!');
				    	 		var tableName = dwrMsg[4];
				    	 		var dm = dwrMsg[5];
				    	 		var mc = dwrMsg[6];
				    	 		var id = dwrMsg[0];
				    	 		var zbpk = dwrMsg[2];
				    	 		var pk = dwrMsg[3];
				    	 		document.getElementsByTagName('select')[i].onchange="changeOps(\'"+tableName+"\', \'"+dm+"\',\' "+mc+"\',\'"+id+"\',\'"+pk+"\',\'"+zbpk+"\')";
				    	 	}
				    	}
				    	document.getElementById(selects[i].id+'t').outerHTML=selects[i].outerHTML;
				    	document.getElementsByTagName('select')[i].name='err';
				    	document.getElementsByTagName('select')[i].style.display='none';
				    	document.getElementsByTagName('select')[i].id='';
				    	jQuery("#"+selId).val(value);
				    }
			}
			
			function dmwhSave(){
					for(var i=0;i<document.getElementsByName('ablePk').length;i++){
						if(document.getElementsByName(document.getElementsByName('ablePk')[i].value).length==1){
							if(trim(document.getElementsByName(document.getElementsByName('ablePk')[i].value)[0].value)==''){
								showAlert("带*号的为必填项");
								return;
							}
						} else {
							if(trim(document.getElementsByName(document.getElementsByName('ablePk')[i].value)[1].value)==''){
								showAlert("带*号的为必填项");
								return;
							}
						}
						
					}
					//refreshForm('/xgxt/xtwhOther.do?method=xtDmwhSaveNew')
					 var url = "xtwhOther.do?method=xtDmwhSaveNew";
				      ajaxSubFormWithFun("xtwhOtherForm",url,function(data){
				    	 if(data["message"]=="保存成功！"){
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
						
			jQuery(function(){
				ymcsh();
			})
		</script>
	</head>
	<body>
		<html:form action="/xtwhOther" method="post" styleId="xtwhOtherForm">
			<logic:notEmpty name="rs">
				<input type="hidden" name="tName" id="tName" value="${tName}" />
				<input type="hidden" name="ssmk" id="ssmk" value="${ssmk}" />
				<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}" />
				<logic:iterate id="opxl" name="opTwoAssociate">
					<input type="hidden" name="opTwoAssociateK" id="opTwoAssociateK"
						value="<bean:write name="opxl" property="key" />" />
					<input type="hidden" name="opTwoAssociateV" id="opTwoAssociateV"
						value="<logic:iterate id="val" name="opxl" property="value"><bean:write name="val"/>!!</logic:iterate>" />
				</logic:iterate>
				<logic:iterate id="opT" name="opMap">
					<select class='selectHid' name="<bean:write name="opT" property="key" />" onchange=""
						id="<bean:write name="opT" property="key" />" >
						<logic:iterate id="ops" name="opT" property="value">
							<option value="<bean:write name="ops" property="dm" />">
								<bean:write name="ops" property="mc" />
							</option>
						</logic:iterate>
					</select>
				</logic:iterate>

				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>基础代码维护</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz" id="btx">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" class="button2" onclick="dmwhSave();return false;">
											保 存
										</button>
										<button type="button" class="button2" onclick="Close();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<logic:iterate id="rsT" name="rs">
								<tr>
									<th style="width: 28%">
										<logic:equal name="rsT" property="nullable" value="N">
											<input type="hidden" name="ablePk"
												value="<bean:write name = "rsT" property="en"/>" />
											<font color="red">*</font>
										</logic:equal>
										<bean:write name="rsT" property="cn" />
									</th>
									<td>
										<logic:equal name="rsT" property="zdlx" value="disNum">
											<input type="text" style="width: 62%"
												name="<bean:write name = "rsT" property="en"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') "
												id="<bean:write name = "rsT" property="en"/>t"
												value="<bean:write name = "rsT" property="value" />"
												maxlength="<bean:write name = "rsT" property="length" />*2" />
												<script language="javascript">
													var s = <bean:write name = "rsT" property="length" />*2;
													$("<bean:write name = 'rsT' property='en'/>t").maxLength=s;
												</script>
										</logic:equal>
										<logic:equal name="rsT" property="zdlx" value="disDouble">
											<input type="text" style="width: 62%"
												name="<bean:write name = "rsT" property="en"/>"
												onkeypress="checkInputNum(this)"
												id="<bean:write name = "rsT" property="en"/>t"
												value="<bean:write name = "rsT" property="value" />"
												maxlength="<bean:write name = "rsT" property="length" />" />
												<script language="javascript">
													var s = <bean:write name = "rsT" property="length" />*2;
													$("<bean:write name = 'rsT' property='en'/>t").maxLength=s;
												</script>
										</logic:equal>
										<logic:equal name="rsT" property="zdlx" value="disData">
											<input type="text" style="width: 62%"
												name="<bean:write name = "rsT" property="en"/>"
												onkeypress="chkonlynum()"
												id="<bean:write name = "rsT" property="en"/>t"
												value="<bean:write name = "rsT" property="value" />"
												maxlength="<bean:write name = "rsT" property="length" />"
												onblur="dateFormatChg(this)" style="cursor:hand;"
												onclick="return showCalendar('<bean:write name = "rsT" property="en"/>t','y-mm-dd');" />
										</logic:equal>
										
										<logic:equal name="rsT" property="zdlx" value="disTextarea">
											<textarea rows="5" style="width: 300px" 
												name="<bean:write name = "rsT" property="en"/>"
												id="<bean:write name = "rsT" property="en"/>t"
												value="<bean:write name = "rsT" property="value" />"
												onblur="checkLen(this,<bean:write name = "rsT" property="length" />)"
												><bean:write name = "rsT" property="value" /></textarea>
										</logic:equal>
										
										<logic:equal name="rsT" property="zdlx" value="">
											<input type="text" style="width: 62%"
												name="<bean:write name = "rsT" property="en"/>"
												id="<bean:write name = "rsT" property="en"/>t"
												value="<bean:write name = "rsT" property="value" />"
												maxlength="<bean:write name = "rsT" property="length" />" />
										</logic:equal>

									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>



			</logic:notEmpty>
			<logic:notEmpty name="result">
				<logic:equal value="NO" name="result">
					<script language="javascript">
						alertInfo("操作失败！",function(){Close();});
					</script>
				</logic:equal>
				<logic:equal value="OK" name="result">
					<script language="javascript">
						alertInfo("操作成功！",function(){Close();});
					</script>
				</logic:equal>
				<script language="javascript">	
				if (parent.window){
					refershParent();
				}				
		</script>
			</logic:notEmpty>
		</html:form></body>
</html>
