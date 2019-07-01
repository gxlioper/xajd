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
			function twdjBc(){
				if(jQuery.trim(jQuery("#xh").val())==""){
			 		alertInfo("学号不能为空!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				if(jQuery.trim(jQuery("#rwzh").val())==""){
			 		alertInfo("入伍证号不能为空!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				
				if(jQuery.trim(jQuery("#twsj").val())==""){
			 		alertInfo("退伍时间不能为空!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				var rwsj = jQuery("#rwsj").text();
				var twsj = jQuery("#twsj").val();
				if(rwsj>twsj){
					alertInfo("退伍时间不应小于或等于入伍时间!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				jQuery.ajaxSetup({async:false});	
				// 得到JSON对象
			    var parameter ={};	
			    parameter["xh"]=escape(jQuery("#xh").val());
			    parameter["rwzh"]=escape(jQuery("#rwzh").val());
				parameter["twsj"]=escape(jQuery("#twsj").val());
				parameter["yzy"]=escape(jQuery("#yzy").val());
				parameter["ybj"]=escape(jQuery("#ybj").val());
				parameter["hjgx"]=escape(jQuery("#hjgx").val());
				parameter["hkszd"]=escape(jQuery("#hkszd").val());
				parameter["bz"]=escape(jQuery("#bz").val());
				url = "rwgl_rwtwgl_ajax.do?method=twdjBc&doType=update";
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
			<div style="height:485px;overflow-x:hidden;overflow-y:auto;">
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
								<font color="red"></font>入伍时间
							</th>
							<td width="34%" id="rwsj">
								${rs.rwsj }
							</td>
							<th width="16%" >
								<font color="red"></font>入伍地
							</th>
							<td width="34%" style="word-break:break-all;width:99%">
								<!-- 温州大学 -->
								<logic:equal name="xxdm" value="10351">	
									${rs.rwdwdmc }
								</logic:equal>
								<logic:notEqual name="xxdm" value="10351">	
									${rs.rwd }
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th width="16%">
								入伍方式
							</th>
							<td width="34%" colspan="3">
								<font id="rwfs">${rs.rwfsmc}</font>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>退伍信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>入伍证号
							</th>
							<td width="34%">
								<html:text name="rs" property="rwzh" styleId="rwzh" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>退伍时间
							</th>
							<td width="34%">
								<html:text name="rs" property="twsj" styleId="twsj" maxlength="10" onclick="return showCalendar('twsj','y-mm-dd');" onblur="dateFormatChg(this)" readonly="true" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								原专业
							</th>
							<td width="34%">
								<html:text name="rs" property="yzy" styleId="yzy" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								原班级
							</th>
							<td width="34%">
								<html:text name="rs" property="ybj" styleId="ybj" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								户籍关系
							</th>
							<td width="34%">
								<html:text name="rs" property="hjgx" styleId="hjgx" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								户口所在地
							</th>
							<td width="34%">
								<html:text name="rs" property="hkszd" styleId="hkszd" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								备注<br/><font class="red">(限1000字)</font>
							</th>
							<td width="84%" colspan="3">
								<html:textarea name="rs" property='bz' styleId="bz" style="word-break:break-all;width:99%"
										rows='3' onblur="chLeng(this,1000)"/>
							</td>
						</tr>
					</tbody>
					<%--<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="twdjBc()">
										保 存
									</button>
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				--%></table>
			</div>
			<table class="formlist" width="100%">
				<tfoot>
					<tr>
						<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="twdjBc()">
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

