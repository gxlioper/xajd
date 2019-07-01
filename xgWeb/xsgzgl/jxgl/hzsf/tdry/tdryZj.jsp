<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script	language="javascript" defer="defer" >
			// 保存
			function Save(){
				if($("xh") && $("xh").value==""){
		     		alertInfo("学号不能为空!",function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		return false;
	     		}
				if($("bzjbdm") && $("bzjbdm").value==""){
		     		alertInfo("编制级别不能为空!",function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		return false;
	     		}
				if($("bzdm") && $("bzdm").value==""){
		     		alertInfo("编制名称不能为空!",function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		return false;
	     		}
				if($("tdrydm") && $("tdrydm").value==""){
		     		alertInfo("团队荣誉不能为空!",function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		return false;
	     		}
				var message = checkSaveInfo();
				if("true"!=message){
					alertInfo(message,function(tag){
						if(tag=="ok"){
							return false;
						}
					});
					return false;
				}
				confirmInfo("是否要保存新数据？",saveTdryInfo);	
				
			}
			function checkSaveInfo(){
				var data = "true";
				jQuery.ajaxSetup({async:false});
				// 得到JSON对象
			    var parameter ={};
			    parameter["xn"]=escape(jQuery("#xn").val());
			    parameter["bzdm"]=escape(jQuery("#bzdm").val());
			    parameter["tdrydm"]=escape(jQuery("#tdrydm").val());
			    parameter["doType"]=escape("add");
				var url = "jxgl_tdry_ajax.do?method=checkSaveInfo";
				jQuery.post(url,parameter,
					function(result){
						data = result;
					}
				);
				jQuery.ajaxSetup({async:true});
				return data;
			}
			
			function saveTdryInfo(tag){
				if(tag=="ok"){
					jQuery.ajaxSetup({async:false});
					// 得到JSON对象
				    var parameter ={};	
				    parameter["xn"]=escape(jQuery("#xn").val());
				    parameter["bzjbdm"]=escape(jQuery("#bzjbdm").val());
				    parameter["bzdm"]=escape(jQuery("#bzdm").val());
				    parameter["tdrydm"]=escape(jQuery("#tdrydm").val());
				    parameter["bz"]=escape(jQuery("#bz").val());
					var url = "jxgl_tdry_ajax.do?method=save";
			        $("divWaiting").style.display="";
					$("divDisable").style.display="";
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result);
						}
					);
					jQuery.ajaxSetup({async:true});
					}
			}
			//菜单联动
			function getBzdmList(){
				if($("bzjbdm") && $("bzjbdm").value==""){
					jQuery("#tr_bzxx").hide();
					return false;
	     		}
	     		if($("bzjbdm").value=="1"){
	         		jQuery("#bzxx").text("团级");
	     		}else if($("bzjbdm").value=="2"){
	         		jQuery("#bzxx").text("营级");
	     		}else if($("bzjbdm").value=="3"){
	         		jQuery("#bzxx").text("连级");
	     		}
				jQuery.ajaxSetup({async:false});
				var parameter ={};
			    parameter["xn"]=escape(jQuery("#xn").val());
			    parameter["bzjbdm"]=escape(jQuery("#bzjbdm").val());
				jQuery.getJSON('jxgl_tdry_ajax.do?method=getBzdmList',parameter,function(data){
					jQuery("#tr_bzxx").show();
					jQuery('#bzdm').empty();
					jQuery('#bzdm').append("<option value=''></option>");
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
							jQuery('#bzdm').append(option);
						}
					}
				});
				jQuery.ajaxSetup({async:true});
			}
		</script>
	</head>
	<body>
		<html:form action="/jxgl_tdry" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>团队荣誉增加</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr style="height:22px">
							<th width="16%">
								<span class="red">*</span>学年
							</th>
							<td width="84%">
								<html:select name="rs" property="xn" style="width:180px" styleId="xn" onchange="getBzdmList()">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								<span class="red">*</span>编制级别
							</th>
							<td width="84%">
								<html:select property="bzjbdm" style="width:180px" styleId="bzjbdm" onchange="getBzdmList()">
									<html:option value=""></html:option>
									<html:options collection="zjList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								<span class="red">*</span>编制名称
							</th>
							<td width="84%">
								<html:select property="bzdm" style="width:180px" styleId="bzdm">
									<html:option value=""></html:option>
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								<font class="red">*</font>团队荣誉
							</th>
							<td>
								<html:select property="tdrydm" style="width:180px" styleId="tdrydm">
									<option value=''>--请选择--</option>
									<html:options collection="tdryList" property="tdrydm" labelProperty="tdrymc"/>
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								备注
							</th>
							<td>
								<html:textarea property='bz' styleId="bz" onblur="chLeng(this,1000);" style="word-break:break-all;width:97%" rows='3' />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="Save();return false;">
										保 存
									</button>
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

