<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>		
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" defer="defer">
		  var api = frameElement.api, W = api.opener;
		//��ʼ��
		jQuery(document).ready(function(){ 
			getCjnj();
			var pkValue = jQuery("#pk").val();
			jQuery.ajaxSetup({async:false});
			jQuery.ajaxSetup({cache:false});
			var url="jxgl_jxxxwh_ajax.do?method=jxxxXg";		
			url+="&pkValue="+pkValue;
			jQuery.getJSON(url,{},function(data){
				if(data != null){
					jQuery("#jxid").val(data.jxid);
					jQuery("#jxmc").val(data.jxmc);
					jQuery("#kssj").val(data.kssj);
					jQuery("#jssj").val(data.jssj);
					jQuery("#cjnj").val(data.cjnj);
					jQuery("input[name=jxzt][type=radio][value="+data.jxzt+"]").click();
				}
			});
			jQuery.ajaxSetup({async:true});
		});
		//��ѵ��Ϣ����(ǰ̨��֤)
		function jxxxDivSave(){
			if(jQuery("#jxmc").val()==null || jQuery("#jxmc").val()==""){
		 		alertInfo("��ѵ���Ʋ���Ϊ��!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			
			var flag = checkJxxx();
			if(!flag){
				alertInfo("��ѵ���Ʋ����ظ�!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			if(jQuery("#kssj").val()==null || jQuery("#kssj").val()==""){
		 		alertInfo("��ʼʱ�䲻��Ϊ��!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			if(jQuery("#jssj").val()==null || jQuery("#jssj").val()==""){
		 		alertInfo("����ʱ�䲻��Ϊ��!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			
			if(jQuery("#kssj").val()!="" && jQuery("#jssj").val()!="" && jQuery("#kssj").val()>jQuery("#jssj").val()){
		 		alertInfo("��ʼʱ�䲻�ܴ��ڽ���ʱ��!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			if(jQuery("#cjnj").val()==null || jQuery("#cjnj").val()==""){
		 		alertInfo("�μ��꼶����Ϊ��!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}

			jxxxXgSave();
		}


		//��ѵ��Ϣ��̨��֤
		function checkJxxx(){
			var flag = false;
			jQuery.ajaxSetup({async:false});	
			// �õ�JSON����
		    var parameter ={};	
		    parameter["jxid"]=escape(jQuery("#jxid").val());
		    parameter["jxmc"]=escape(jQuery("#jxmc").val());
		    var url = "jxgl_jxxxwh_ajax.do?method=checkJxxx";
			jQuery.post(url,parameter,
				function(result){
					if("true"==result){
						flag = true;
					}
				});
			jQuery.ajaxSetup({async:true});
			return flag;
		}
		//��ѵ��Ϣ����
		function jxxxXgSave(){
			jQuery.ajaxSetup({async:false});	
			// �õ�JSON����
		    var parameter ={};	
		    parameter["jxid"]=escape(jQuery("#jxid").val());
		    parameter["jxmc"]=escape(jQuery("#jxmc").val());
			parameter["kssj"]=escape(jQuery("#kssj").val());
			parameter["jssj"]=escape(jQuery("#jssj").val());
			parameter["cjnj"]=escape(jQuery("#cjnj").val());
			parameter["xqrs"]=escape(jQuery("#xqrs").val());
			parameter["jxzt"]=escape(jQuery("input[name=jxzt]:checked").val());
			var url = "jxgl_jxxxwh_ajax.do?method=jxxxSave";
		   // jQuery("#divWaiting").style.display="";
		    //jQuery("#divDisable").style.display="";
			jQuery.post(url,parameter,
				function(result){
					//$("divWaiting").style.display="none";
					//$("divDisable").style.display="none";
					if("����ɹ�"==result && jQuery("#jxid") && jQuery("#jxid").value=="" && "start"==jQuery("input[name=jxzt]:checked").val()){
						 showAlert(result,{},{"clkFun":function(){
			        			if (parent.window){
			        				W.searchRs();
			        				closeDialog();
			        			}
			      		  }});
						confirmInfo(jQuery("#jxmc").val()+"�ѳɹ��������Ƿ�������ɾ�ѵ������",function(tag){
							if(tag=="ok"){
								var array = jQuery("[name=div_pkValue]");
								var pkValue = jQuery(array[0]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
								refreshForm('jxgl_jxxxwh.do?method=jxmdCx&pkValue='+pkValue+'&cxqk=cx');
							}
						});
					}else{
						 showAlert(result,{},{"clkFun":function(){
			        			if (parent.window){
			        				W.searchRs();
			        				closeDialog();
			        			}
			      		  }});
					}
				}
			);
			jQuery.ajaxSetup({async:true});
		}
		//��òμ��꼶
		function getCjnj(){
			var parameter={};
			jQuery.ajaxSetup({async:false});
			url = "jxgl_jxxxwh_ajax.do?method=getCjnj";
			jQuery.getJSON(url,parameter,function(data){
				jQuery('#cjnj').empty();
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].cjnj + "\">" + data[i].cjnj + "</option>";
						jQuery('#cjnj').append(option);
					}
				}
			});
			jQuery.ajaxSetup({async:true});
		}
		</script>
	</head>
	<body>
	   <html:form action="/jxgl_jxxxwh" method="post">
	        <input type="hidden" id="pk" value="${pkValue}"/>
			<div id="tempDiv">
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>��ѵ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>��ѵ����
							</th>
							<td>
								<input type="hidden" name="jxid" id="jxid" value=""/>
								<input type="text" name="jxmc" id="jxmc" value="" maxlength="50" style="width: 150px;"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��ʼʱ��
							</th>
							<td>
								<input type="text" id="kssj" name="kssj" style="width: 150px;"
									onclick="return showCalendar('kssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>����ʱ��
							</th>
							<td>
								<input type="text" id="jssj" name="jssj" style="width: 150px;"
									onclick="return showCalendar('jssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�μ��꼶
							</th>
							<td>
								<select id="cjnj" name="cjnj" style="width: 150px;"></select>
							</td>
						</tr>
						<tr>
							<th>
								��ѵ״̬
							</th>
							<td>
								<input type="radio" name="jxzt" value="start" checked="checked"/>����
								<input type="radio" name="jxzt" value="stop"/>ֹͣ
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" name="����" onclick="jxxxDivSave();return false;">
										����
									</button>
									<button type="button" name="�ر�" onclick="iFClose();return false;">
										�ر�
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