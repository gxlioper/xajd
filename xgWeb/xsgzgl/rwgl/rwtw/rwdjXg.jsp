<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
						<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script>
			//保存
			function rwdjBc(){
				if(jQuery.trim(jQuery("#xh").val())==""){
			 		alertInfo("学号不能为空!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				
				if(jQuery.trim(jQuery("#rwxn").val())==""){
			 		alertInfo("入伍学年不能为空!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				
				if(jQuery.trim(jQuery("#rwsj").val())==""){
			 		alertInfo("入伍时间不能为空!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				if(jQuery.trim(jQuery("#sg").val())==""){
			 		alertInfo("身高不能为空!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				if(jQuery.trim(jQuery("#tz").val())==""){
			 		alertInfo("体重不能为空!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				if(jQuery.trim(jQuery("#zysl").val())==""){
			 		alertInfo("左眼视力不能为空!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				if(jQuery.trim(jQuery("#yysl").val())==""){
			 		alertInfo("右眼视力不能为空!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				
				//if(jQuery.trim(jQuery("#rwd").val())==""){
			 	//	alertInfo("入伍地不能为空!",function(tag){
			 	//		if(tag=="ok"){
			 	//			return false;
			 	//		}
			 	//	});
			 	//	return false;
				//}
				
				jQuery.ajaxSetup({async:false});	
				// 得到JSON对象
			    var parameter ={};	
			    parameter["xh"]=escape(jQuery("#xh").val());
				parameter["rwsj"]=escape(jQuery("#rwsj").val());
				parameter["rwdwd"]=escape(jQuery("#rwdwd").val());
				parameter["rwxn"]=escape(jQuery("#rwxn").val());
				parameter["rwfs"]=escape(jQuery("#rwfs").val());
				parameter["sg"] = escape(jQuery("#sg").val());
				parameter["tz"] = escape(jQuery("#tz").val());
				parameter["zysl"] = escape(jQuery("#zysl").val());
				parameter["yysl"] = escape(jQuery("#yysl").val());
				parameter["rwd"] = escape(jQuery("#rwd").val());
				url = "rwgl_rwtwgl_ajax.do?method=rwdjBc&doType=update";
			    $("divWaiting").style.display="";
				$("divDisable").style.display="";
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						if("保存成功"==result){
							 showAlert(result,{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});

						}else{
							alertInfo(result,function(tag){
				     			if(tag=="ok"){
				     				return false;
				     			}
				     		});
				     		return false;
						}
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		</script>
	</head>
	<body>
		<html:form action="/rwgl_rwtwgl" method="post">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:405px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
								<html:hidden name="rs" property="xh" styleId="xh"></html:hidden>
								${rs.xh }
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%" >
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th width="16%">
								性别
							</th>
							<td width="34%">
								${rs.xb }
							</td>
							<th width="16%">
								年级
							</th>
							<td width="34%">
								${rs.nj }
							</td>
						</tr>
						<tr>
							<th width="16%">
								<bean:message key="lable.xb" />
							</th>
							<td width="34%">
								${rs.xymc }
							</td>
							<th width="16%">
								专业
							</th>
							<td width="34%">
								${rs.zymc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								班级
							</th>
							<td width="34%">
								${rs.bjmc }
							</td>
							<th width="16%">
								政治面貌
							</th>
							<td width="34%">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								民族
							</th>
							<td width="34%">
								${rs.mzmc }
							</td>
							<th width="16%">
								出生日期
							</th>
							<td width="34%">
								${rs.csrq }
							</td>
						</tr>
						<tr>
							<th width="16%">
								身份证号
							</th>
							<td width="34%">
								${rs.sfzh }
							</td>
							<th width="16%">
								手机号码
							</th>
							<td width="34%">
								${rs.sjhm }
							</td>
						</tr>
						<tr>
							<th width="16%">
								家庭地址
							</th>
							<td width="34%">
								${rs.jtdz }
							</td>
							<th width="16%">
								家庭电话
							</th>
							<td width="34%">
								${rs.jtdh }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>入伍信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>入伍学年
							</th>
							<td width="34%">
								<html:select  property="rwxn" styleId="rwxn" style="width:155px" value="${rs.rwxn}">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>入伍时间
							</th>
							<td width="34%">
								<html:text name="rs" property="rwsj" styleId="rwsj" maxlength="10" onclick="return showCalendar('rwsj','y-mm-dd');" onblur="dateFormatChg(this)" readonly="true" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red"></font>入伍地
							</th>
							<td width="34%">
								<!-- 温州大学 -->
								<logic:equal name="xxdm" value="10351">	
									<html:select  name="rs" property="rwdwd" styleId="rwdwd" style="width:155px">
										<html:options collection="rwtjList" labelProperty="mc" property="dm"/>
									</html:select>
								</logic:equal>
								<logic:notEqual name="xxdm" value="10351">	
									<html:text name="rs" property="rwd" styleId="rwd" maxlength="120" styleClass="text_nor" ></html:text>
								</logic:notEqual>
							</td>
							<th width="16%">
								<font color="red">*</font>入伍方式
							</th>
							<td width="34%">
								<html:select  property="rwfs" styleId="rwfs" style="width:155px" name="rs">
									<html:options collection="rwfsList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%"><span class="red">*</span>身高</th>
							<td width="34%">
								<html:text name="rs" property="sg" styleId="sg" maxlength="5" onkeyup="checkInputNum(this)"></html:text>
							</td>
							<th width="16%"><span class="red">*</span>体重</th>
							<td width="34%">
								<html:text name="rs" property="tz" styleId="tz" maxlength="10" onkeyup="checkInputNum(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%"><span class="red">*</span>左眼视力</th>
							<td width="34%">
								<html:text name="rs" property="zysl" styleId="zysl" maxlength="5" onkeyup="checkInputNum(this)"></html:text>
							</td>
							<th width="16%"><span class="red">*</span>右眼视力</th>
							<td width="34%">
								<html:text name="rs" property="yysl" styleId="yysl" maxlength="5" onkeyup="checkInputNum(this)"></html:text>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="rwdjBc()">
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

