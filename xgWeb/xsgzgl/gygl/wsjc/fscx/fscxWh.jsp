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
		<script language="javascript">	     
			function Save(){
				if($("fz") && $("fz").value==""){
	     			alertInfo("分值不能为空!",function(tag){
	     				if(tag=="ok"){
	     						return false;
	     				}
	     			});
	     			return false;
	     		}
				if($("jcrq") && $("jcrq").value==""){
	     			alertInfo("检查日期不能为空!",function(tag){
	     				if(tag=="ok"){
	     						return false;
	     				}
	     			});
	     			return false;
	     		}
	     		if($("jcbm") && $("jcbm").value==""){
	     			alertInfo("检查部门不能为空!",function(tag){
	     				if(tag=="ok"){
	     						return false;
	     				}
	     			});
	     			return false;
	     		}
	     		if($("jcry") && $("jcry").value==""){
	     			alertInfo("检查人员不能为空!",function(tag){
	     				if(tag=="ok"){
	     						return false;
	     				}
	     			});
	     			return false;
	     		}
	     		if(checkjcrq()){
	     			saveFscxInfo("ok");	
	     		}
			}
			
			function saveFscxInfo(tag){
				if(tag=="ok"){
					var sfsdj = jQuery("#sfsdj").val();
					//主键
					var pkValue=new Array();
					jQuery.ajaxSetup({async:false});	
					// 得到JSON对象
			        var parameter ={};
					
					parameter["pkValue"]=escape(jQuery("#pkValue").val());
					parameter["lddm"]=escape(jQuery("#lddm").val());
					parameter["qsh"]=escape(jQuery("#qsh").val());
					if(sfsdj=="0"){
						parameter["fz"]=escape(jQuery("#fz").val());
					}else{
						parameter["dj"]=escape(jQuery("#dj").val());
					}
					parameter["jcrq"]=escape(jQuery("#jcrq").val());
					parameter["jcbm"]=escape(jQuery("#jcbm").val());
					parameter["jcry"]=escape(jQuery("#jcry").val());
					parameter["bz"]=escape(jQuery("#bz").val());
					
					var url = "gyglnew_fscx_ajax.do?method=save";
		          	
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
			
			function checkIs(obj){
			var val=jQuery(obj).val();
			var reg=/^(?:0|[1-9][0-9]?|[1-9][0-9]+\.\d{0,2}|[0-9]+\.\d{0,2}|100)$/;
			if(val!=null && val!=""){
				if(reg.test(val)){
					jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", true);
				}else{
					jQuery(obj).parent().parent().find("td").eq(8).find("input[type='text']").val(null);
					alertInfo("只能为0到100的数字",function(){obj.focus();});
				}	
			}
			if(val==""){
				jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", false);
			}
		}
		
		function checkjcrq(){
				jQuery.ajaxSetup({async:false});
				var parameter ={};
				var flag = 0;
				parameter["pkValue"]=escape(jQuery("#pkValue").val());
				parameter["jcrq"]=jQuery("#jcrq").val();	
			    var url = "gyglnew_fslr_ajax.do?method=checkJcrq";
			    
			    jQuery.post(url,parameter,
						function(result){
						if(result=="检查日期可用！"){
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
		</script>
	</head>
	<body onload="">
	
		<html:form action="/gyglnew_jcrcgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="lddm" id="lddm" value="${lddm }" />
			<input type="hidden" name="qsh" id="qsh" value="${qsh }" />
			<input type="hidden" name="sfsdj" id="sfsdj" value="${sfsdj }" />

			<div style='width:100%;height:350px;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr onclick="deploy('tbody_jbxx')">
							<th colspan="4">
								<span>卫生分信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="16%">
								检查日程
							</th>
							
							<td width="34%" colspan="3" >
								${jcrc}
							</td>
						</tr>
						<tr style="height:22px">
						<logic:equal value="0" name="jclx">
							<th width="16%">
								<font color="red">*</font>分值
							</th>
							<td align="left" colspan="3">
								<html:text property="fz" styleId="fz" value="${rs.fs }" maxlength="25" onblur="checkIs(this);"/>
							</td>
						</logic:equal>
						<logic:equal value="1" name="jclx">
							<th width="16%">
								<font color="red">*</font>等级
							</th>
							<td align="left" colspan="3">
								<html:select property="dj" styleId="dj" name="rs" onchange=" ">
									<html:options collection="djlist" labelProperty="dj" property="dj"/>
								</html:select>
							</td>
						</logic:equal>
						<logic:equal value="2" name="jclx">
							<th width="16%">
								<font color="red">*</font>星级
							</th>
							<td align="left" colspan="3">
								<html:select property="dj" styleId="dj" name="rs" onchange=" ">
									<html:options collection="xjlist" labelProperty="dj" property="dj"/>
								</html:select>
							</td>
						</logic:equal>
						
						
						</tr>
						<tr style="height:22px">
							<th width="16%">
								<font color="red">*</font>检查日期
							</th>
							
							<td width="34%" colspan="3">
								<html:text property="jcrq" styleId="jcrq"   style="width:90px" value="${rs.jcrq }"
									onclick="return showCalendar('jcrq','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								<font color="red">*</font>检查部门
							</th>
							<td align="left" colspan="3">
								<html:text property="jcbm" styleId="jcbm" value="${rs.jcbm }" maxlength="50"/>
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								<font color="red">*</font>检查人员
							</th>
							<td align="left" colspan="3">
								<html:text property="jcry" styleId="jcry" value="${rs.jcry }" maxlength="50"/>
							</td>
						</tr>
						<tr style="height:90px">
							<th align="right" >
								检查备注
							</th>
							<td colspan="3" ><br /><html:textarea  property='bz' styleId="bz" style="word-break:break-all;width:95%" onblur="chLeng(this,500);"
									rows='4' value="${rs.bz }"  />
							<br /></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
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

			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>

