<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript">	     
	     function save(){
	     	if($("xn") && $("xn").value==""){
	     		alertInfo("ѧ�겻��Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("xq") && $("xq").value==""){
	     		alertInfo("ѧ�ڲ���Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	if($("jcyf") && $("jcyf").value==""){
	     		alertInfo("�·ݲ���Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
		     	}
	     	
	     	if($("mc") && $("mc").value.trim()==""){
	     		alertInfo("���Ʋ���Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("jclx") && $("jclx").value.trim()==""){
	     		alertInfo("���Ͳ���Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("kssj") && $("kssj").value=="" || $("jssj") && $("jssj").value==""){
	     		alertInfo("��ֹʱ�䲻��Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	if($("jcyf") && $("jcyf").value!=""){
		     	var flag=true;
		     	var xn=jQuery("#xn").val();
		     	var ksxn=xn.substring(0,4);
		     	var jsxn=xn.substring(5,9);
		     	if(jQuery("#jcyf").val().substring(0,4)<ksxn||jQuery("#jcyf").val().substring(0,4)>jsxn){
		     		alertInfo("����·ݲ���ѧ�귶Χ��!",function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		flag=false;
		     	}
		     	if(!flag){
		     	return flag;
		     	}
		     	}
	     	if(checkmc()&&checkqzsj()){
		     	if(jQuery("#xxdm").val() == '33333' || jQuery("#xxdm").val() == '70002'){
					saveJcrcForZjSyJs();
			    }else{
		     		saveJcrcglInfo("ok");
				}
	     	}
	     }

		 //�������ճ̣��㽭��ҵ��ʦѧԺר�ã�
	     function saveJcrcForZjSyJs(){
		     url = 'gyglnew_jcrcglForZjSyJs.do?method=saveForm';
	    	 ajaxSubFormWithFun("jcForm", url, function(data) {
	    			if (data["message"] == "����ɹ���") {
	    				showAlert(data["message"], {}, {
	    					"clkFun" : function() {
	    						if (parent.window) {
	    							refershParent();
	    						}
	    					}
	    				});
	    			} else {
	    				showAlert(data["message"]);
	    			}
	    		});

		 }
	     
	      function modi(){
	       var mc2 = jQuery("#mc2").val();
	       var mc = jQuery("#mc").val();
	       var kssj2 = jQuery("#kssj2").val();
	       var kssj = jQuery("#kssj").val();
	       var jssj2 = jQuery("#jssj2").val();
	       var jssj = jQuery("#jssj").val();
	       if($("xn") && $("xn").value==""){
	     		alertInfo("ѧ�겻��Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("xq") && $("xq").value==""){
	     		alertInfo("ѧ�ڲ���Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	if($("jcyf") && $("jcyf").value==""){
	     		alertInfo("�·ݲ���Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
		     	}
	     	if($("jcyf") && $("jcyf").value!=""){
		     	var flag=true;
		     	var xn=jQuery("#xn").val();
		     	var ksxn=xn.substring(0,4);
		     	var jsxn=xn.substring(5,9);
		     	if(jQuery("#jcyf").val().substring(0,4)<ksxn||jQuery("#jcyf").val().substring(0,4)>jsxn){
		     		alertInfo("����·ݲ���ѧ�귶Χ��!",function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		flag=false;
		     	}
		     	if(!flag){
			     	return flag;
			     	}
		     	}
		        
	     	
	     	if($("mc") && $("mc").value.trim()==""){
	     		alertInfo("���Ʋ���Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("jclx") && $("jclx").value.trim()==""){
	     		alertInfo("���Ͳ���Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("kssj") && $("kssj").value=="" || $("jssj") && $("jssj").value==""){
	     		alertInfo("��ֹʱ�䲻��Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	       if(mc2!=mc){
	       	if(kssj2!=kssj || jssj2!=jssj){
	       		if(checkmc()&&checkqzsj()){
	     			confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",modiJcrcglInfo);
	     		}
	       	}else{
	       		if(checkmc()){
	     		confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",modiJcrcglInfo);
	     		}
	       	}
	       }else if(mc2==mc){
	       	if(kssj2!=kssj || jssj2!=jssj){
	       		if(checkqzsj()){
	     			confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",modiJcrcglInfo);
	     		}
	       	}else{
	     		confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",modiJcrcglInfo);
	       	}
	       }
	     }
	 
	 
	     
	     function saveJcrcglInfo(tag){
	     		
				if(tag=="ok"){
					
					//����
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
					
					parameter["xn"]=jQuery("#xn").val();
					
					parameter["xq"]=escape(jQuery("#xq").val());

					parameter["jcyf"]=escape(jQuery("#jcyf").val());
					
					parameter["kssj"]=escape(jQuery("#kssj").val());
					
					parameter["jssj"]=escape(jQuery("#jssj").val());
					
					parameter["mc"]=escape(jQuery("#mc").val());
					
					if (jQuery("#jclx").val() != undefined){
						parameter["jclx"]=escape(jQuery("#jclx").val());
					}
					
					parameter["bz"]=escape(jQuery("#bz").val());
					
					var url = "gyglnew_jcrcgl_ajax.do?method=save";
		          	
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							//alertInfo(result);
							 showAlert(result,{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    		}});
							
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			}
			
			function checkmc(){
			jQuery.ajaxSetup({async:false});
				var parameter ={};
				var flag = 0;
			    parameter["mc"]=escape(jQuery("#mc").val());
			    var url = "gyglnew_jcrcgl_ajax.do?method=checkMc";
			    
			    jQuery.post(url,parameter,
						function(result){
						if(result=="���ƿ��ã�"){
							flag=1;
						}else{
							alertInfo(result,function(tag){
	     						if(tag=="ok"){
				     				return false;
	     						}
	     					});
	     					flag=0;
	     					return false;
						}	
						}
					);
					jQuery.ajaxSetup({async:true});
					if(flag==1){
						return true;
					}else{
						return false;
					}
			}
			
			function checkqzsj(){
				jQuery.ajaxSetup({async:false});
				var parameter ={};
				var flag = 0;
		        parameter["pkValue"]=jQuery("#pkValue").val();
				parameter["xn"]=jQuery("#xn").val();	
				parameter["xq"]=escape(jQuery("#xq").val());
			    parameter["kssj"]=escape(jQuery("#kssj").val());
			    parameter["jssj"]=escape(jQuery("#jssj").val());
				if (jQuery("#jclx").val() != undefined){
					parameter["jclx"]=escape(jQuery("#jclx").val());
				}
			    var url = "gyglnew_jcrcgl_ajax.do?method=checkQzsj";
			    
			    jQuery.post(url,parameter,
						function(result){
						if(result=="��ֹʱ����ã�"){
							flag = 1;
						}else{
							alertInfo(result,function(tag){
	     						if(tag=="ok"){
				     				return false;
	     						}
	     					});
	     					flag=0;
	     					return false;
						}
						}
					);
					jQuery.ajaxSetup({async:true});
					if(flag==1){
						return true;
					}else{
						return false;
					}
			}
			
			function modiJcrcglInfo(tag){
	     		
				if(tag=="ok"){
					
					//����
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
			        
			        parameter["pkValue"]=jQuery("#pkValue").val();
					
					parameter["xn"]=jQuery("#xn").val();
					
					parameter["xq"]=escape(jQuery("#xq").val());
					parameter["jcyf"]=escape(jQuery("#jcyf").val());
					
					parameter["kssj"]=escape(jQuery("#kssj").val());
					
					parameter["jssj"]=escape(jQuery("#jssj").val());
					
					parameter["mc"]=escape(jQuery("#mc").val());
					
					if (jQuery("#jclx").val() != undefined){
						parameter["jclx"]=escape(jQuery("#jclx").val());
					}
					
					parameter["bz"]=escape(jQuery("#bz").val());
					
					var url = "gyglnew_jcrcgl_ajax.do?method=modi";
		          	
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							//alertInfo(result);
							 showAlert(result,{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    		}});
							
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			}
</script>
	</head>
	<body onload="">
	
		<html:form action="/gyglnew_jcrcgl" method="post" styleId="jcForm">
			<% String xxdm = (String) request.getAttribute("xxdm"); %>
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
			<logic:equal name="doType" value="add">
				<input type="hidden" name="xn" id="xn" value="${xn }" />
				<input type="hidden" name="xq" id="xq" value="${xq }" />
			</logic:equal>
			<logic:equal name="doType" value="modi">
				<input type="hidden" name="xn" id="xn" value="${rs.xn }" />
				<input type="hidden" name="xq" id="xq" value="${rs.xq }" />
			</logic:equal>

			<div style='width:100%;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr onclick="deploy('tbody_jbxx')">
							<th colspan="4">
								<span>����ճ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							
							<td width="34%" colspan="3" >
								<logic:equal name="doType" value="add">
									${xn }
								</logic:equal>
								<logic:equal name="doType" value="modi">
									${rs.xn }
								</logic:equal>
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							
							<td width="34%" colspan="3">
								<logic:equal name="doType" value="add">
									${xqmc }
								</logic:equal>
								<logic:equal name="doType" value="modi">
									${rs.xqmc }
								</logic:equal>
							</td>
						</tr>
						<% if("18180".equals(xxdm) || "13696".equals(xxdm) || "13108".equals(xxdm) || "13779".equals(xxdm) || "10279".equals(xxdm)||"13011".equals(xxdm)){ %>
						<tr style="height:22px">
							<th width="16%">
								<font color="red">*</font>�·�
							</th>
							
							<td width="34%" colspan="3">
								<html:text   property="jcyf" styleId="jcyf"   style="width:90px" value="${rs.jcyf }"
			 						onclick="return showCalendar('jcyf','yyyy-MM');" 
									onblur="dateFormatChgToMonth(this)" readonly="true" />
							</td>
						</tr>
						<% } %>
						<tr style="height:22px">
							<th align="right">
								<font color="red">*</font>����
							</th>
							<td align="left" colspan="3">
								<input type="hidden" name="mc2" id="mc2" value="${rs.mc }" />
								<html:text property="mc" styleId="mc" value="${rs.mc }" maxlength="25"/>
							</td>
						</tr>
						
						<logic:equal value="true" name="xjqs">
							<tr style="height:22px">
								<th align="right">
									<font color="red">*</font>����
								</th>
								<td align="left" colspan="3">
									<html:select property="jclx" styleId="jclx">
										<html:option value=""></html:option>
										<html:option value="0">����</html:option>
										<html:option value="1">�ȼ�</html:option>
										<html:option value="2">�Ǽ�</html:option>
									</html:select>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual value="true" name="xjqs">
							<input type="hidden" name="jclx" id="jclx" value="${gyglJcrcglForm.jclx}" />
						</logic:notEqual>
						<tr style="height:22px">
							<th align="right">
								<font color="red">*</font>��ֹʱ��
							</th>
							<td align="left" colspan="3">
								<input type="hidden" name="kssj2" id="kssj2" value="${rs.kssj }" />
								<input type="hidden" name="jssj2" id="jssj2" value="${rs.jssj }" />
								<html:text   property="kssj" styleId="kssj"   style="width:90px" value="${rs.kssj }"
									onclick="return showCalendar('kssj','y-mm-dd',true,'jssj');" 
									onblur="dateFormatChg(this)" readonly="true" />��
								<html:text  name="rs" property="jssj" styleId="jssj"  value="${rs.jssj }"  style="width:90px"
										onclick="return showCalendar('jssj','y-mm-dd',false,'kssj');" 
										onblur="dateFormatChg(this)" readonly="true" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								��ע<br /><font color="red" >(��500������)</font>
							</th>
							<td colspan="3">
								<html:textarea  property='bz' styleId="bz" style="word-break:break-all;width:99%" onblur="chLengs(this,500);"
									rows='4' value="${rs.bz }"  />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>

								<div class="btn">
									
									<logic:equal name="doType" value="add">
											<button type="button" id="buttonSave" onclick="save();return false;">
												�� ��
											</button>
									</logic:equal>
									<logic:equal name="doType" value="modi">
											<button type="button" id="buttonSave" onclick="modi();">
												�� ��
											</button>
									</logic:equal>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>