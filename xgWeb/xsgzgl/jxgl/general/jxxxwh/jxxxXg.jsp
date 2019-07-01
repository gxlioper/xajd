<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>		
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" defer="defer">
		  var api = frameElement.api, W = api.opener;
		//初始化
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
		//军训信息保存(前台验证)
		function jxxxDivSave(){
			if(jQuery("#jxmc").val()==null || jQuery("#jxmc").val()==""){
		 		alertInfo("军训名称不能为空!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			
			var flag = checkJxxx();
			if(!flag){
				alertInfo("军训名称不能重复!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			if(jQuery("#kssj").val()==null || jQuery("#kssj").val()==""){
		 		alertInfo("开始时间不能为空!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			if(jQuery("#jssj").val()==null || jQuery("#jssj").val()==""){
		 		alertInfo("结束时间不能为空!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			
			if(jQuery("#kssj").val()!="" && jQuery("#jssj").val()!="" && jQuery("#kssj").val()>jQuery("#jssj").val()){
		 		alertInfo("开始时间不能大于结束时间!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			if(jQuery("#cjnj").val()==null || jQuery("#cjnj").val()==""){
		 		alertInfo("参加年级不能为空!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}

			jxxxXgSave();
		}


		//军训信息后台验证
		function checkJxxx(){
			var flag = false;
			jQuery.ajaxSetup({async:false});	
			// 得到JSON对象
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
		//军训信息保存
		function jxxxXgSave(){
			jQuery.ajaxSetup({async:false});	
			// 得到JSON对象
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
					if("保存成功"==result && jQuery("#jxid") && jQuery("#jxid").value=="" && "start"==jQuery("input[name=jxzt]:checked").val()){
						 showAlert(result,{},{"clkFun":function(){
			        			if (parent.window){
			        				W.searchRs();
			        				closeDialog();
			        			}
			      		  }});
						confirmInfo(jQuery("#jxmc").val()+"已成功创建，是否继续生成军训名单？",function(tag){
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
		//获得参加年级
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
								<span>军训信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>军训名称
							</th>
							<td>
								<input type="hidden" name="jxid" id="jxid" value=""/>
								<input type="text" name="jxmc" id="jxmc" value="" maxlength="50" style="width: 150px;"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>开始时间
							</th>
							<td>
								<input type="text" id="kssj" name="kssj" style="width: 150px;"
									onclick="return showCalendar('kssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>结束时间
							</th>
							<td>
								<input type="text" id="jssj" name="jssj" style="width: 150px;"
									onclick="return showCalendar('jssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>参加年级
							</th>
							<td>
								<select id="cjnj" name="cjnj" style="width: 150px;"></select>
							</td>
						</tr>
						<tr>
							<th>
								军训状态
							</th>
							<td>
								<input type="radio" name="jxzt" value="start" checked="checked"/>运行
								<input type="radio" name="jxzt" value="stop"/>停止
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" name="保存" onclick="jxxxDivSave();return false;">
										保存
									</button>
									<button type="button" name="关闭" onclick="iFClose();return false;">
										关闭
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