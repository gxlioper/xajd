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
		//保存审批信息
		function save(){
			
		}

		//审核
		function sssh(ssjg){

		confirmInfo("确定要操作吗？",function(tag){
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
					//alertInfo("请填写申诉文号!");
					showAlertDivLayer("请填写申诉文号!");
					return false;
				}
				if(sssj!=null && sssj==""){
					//alertInfo("请选择申诉时间!");
					showAlertDivLayer("请选择申诉时间!");
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
						confirmInfo("操作成功，是否要将数据提交至正式库？",function(tag){
							if(tag=="ok" ){
								var url = "wjcfCfssgl_cfsssh.do?method=cfssshZjtj";
								//参数
							 	var parameter = {
							 		"cfid":cfid,
							 		"pkValue":pkValue,
							 		"sswh":escape(sswh),
							 		"sssj":sssj
								};
							 	jQuery.post(url,
										parameter,
										function(result){
							 				
						 				 showAlert("操作成功",{},{"clkFun":function(){
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
						 showAlert("操作成功",{},{"clkFun":function(){
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

		//处分类别
		function showCflb(){
			var url="wjcfCfssgl_cfsssh.do?method=showCflbDiv";

			//其他数据
		 	var parameter = {
			};
		  	
			jQuery("#div_spgw").load(url,parameter,function(){
			
				var len=jQuery("[name=cflbmcs]").length;
		
				if(len==0){
					alertInfo("没有处分类别可以更改！");
					return false;
				}else{
					tipsWindown("系统提示","id:div_spgw","300","150","true","","true","id");
				}
			});
		}

		//点击确定后提交
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
			//参数
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
							confirmInfo("操作成功，是否要将数据提交至正式库？",function(tag){
								if(tag=="ok" ){
									var url = "wjcfCfssgl_cfsssh.do?method=cfssshZjtj";
									//参数
								 	var parameter = {
								 		"cfid":cfid,
								 		"cfggw":escape(jQuery("#cflbmcs").val()),
								 		"pkValue":pkValue,
								 		"sswh":escape(sswh),
								 		"sssj":sssj
									};
								 	jQuery.post(url,
											parameter,
											 showAlert("操作成功",{},{"clkFun":function(){
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
							 showAlert("操作成功",{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
						}
				});
		}
		
		//下载
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
						<em>您的当前位置:</em><a>${title } - 审核</a>
					</p>
			</div>--%>
	
			<div class="tab">
			<div  style="width:100%;height:410px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%">
								学号
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xh"/>
								</logic:present>
							</td>
							<th style="width:20%">
								姓名
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xm"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								性别
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xb"/>
								</logic:present>
							</td>
							<th style="width:20%">
								年级
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
								专业
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="zymc"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								班级
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
								<span>处分信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%">
								处分学年
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xn"/>
								</logic:present>
							</td>
							<th style="width:20%">
								处分学期
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xq"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								处分类别
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cflbmc"/>
								</logic:present>
							</td>
							<th style="width:20%">
								处分原因
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cfyymc"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								处分文号
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cfwh"/>
								</logic:present>
							</td>
							<th style="width:20%">
								处分时间
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cfsj"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								违纪时间
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="wjsj"/>
								</logic:present>
							</td>
							<th style="width:20%">
								处理决定书面材料或附件
							</th>
							<td style="width:30%">
								<logic:notEmpty name="rs" property="cffjmc">
									<logic:notEqual name="rs" property="cffjmc" value="">
									<a href="#" onclick="xzSsfj('cffj');return false;" class="name">下载附件</a>
									</logic:notEqual>
									</logic:notEmpty>
									<logic:empty name="rs" property="cffjmc">
									<logic:equal name="rs" property="cffjmc" value="">
									无上传处分附件
									</logic:equal>
									</logic:empty>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								违纪事实经过
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								<logic:present name="rs">
									<bean:write name="rs" property="wjssjg"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								备注
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
								<span>申诉信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%">
								申诉时间
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="sqsj"/>
								</logic:present>
							</td>
							<th style="width:20%">
								证明材料或附件
							</th>
							<td style="width:30%">
								<logic:present name="rs">
								${ssfjmc }
									<logic:notEmpty name="rs" property="ssfjmc">
									<logic:notEqual name="rs" property="ssfjmc" value="">
									<a href="#" onclick="xzSsfj('ssfj');return false;" class="name">下载附件</a>
									</logic:notEqual>
									</logic:notEmpty>
									<logic:empty name="rs" property="ssfjmc">
									<logic:equal name="rs" property="ssfjmc" value="">
									无上传申诉附件
									</logic:equal>
									</logic:empty>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								申诉理由
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
								<span>申诉审核信息</span>
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
								<bean:write name="cf" property="gwmc"/>审核结果
							</th>
							<td style="width:30%">
								<bean:write name="cf" property="shztzw"/>
							</td>
							<th style="width:20%">
								<bean:write name="cf" property="gwmc"/>审核人
							</th>
							<td style="width:30%">
								<bean:write name="cf" property="shrmc"/>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								<bean:write name="cf" property="gwmc"/>审核意见
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
								申诉文号
							</th>
							<td style="width:30%">
								<input type="text" name="sswh" id="sswh"  maxlength="30" value="${cf.sswh}" /> 
							</td>
							<th style="width:20%">
							<font color="red">*</font>
								申诉时间
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
								审核信息<br />
								<font color="red">(限1000字)</font>
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
								<!-- 用户岗位类别控制 -->
									<logic:present name="spgwqx">
									<logic:present name="splcDjRs">
									<logic:present name="rsSh">
									<logic:present name="splcDjRs" property="spgw">
									<logic:present name="rsSh" property="xtgwid">
										<logic:equal name="splcDjRs" property="spgw" value="${rsSh.xtgwid}">
										<button type="button"  class="button2"  onclick="sssh('wcycf')">
											维持原处分
										</button>
										<button type="button"  class="button2"  onclick="sssh('cxcf')">
											撤销处分
										</button>
										<button type="button"  class="button2"  onclick="sssh('ggcf')">
											更改处分
										</button>
										</logic:equal>
										<logic:notEqual name="splcDjRs" property="spgw" value="${rsSh.xtgwid}">
											<button type="button"  class="button2"  <bean:write name="spgwqx" property="tg"/> onclick="sssh('tg')">
												通过
											</button>
											<button type="button"  class="button2"  <bean:write name="spgwqx" property="tg"/> onclick="sssh('btg')">
												不通过
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
											退回
										</button>
										</logic:notEqual>
									</logic:present>
									</logic:present>
									</logic:present>
									</logic:present>
									</logic:present>
									<!-- <button type="button"  class="button2"  onclick="Close();return false;">
										提交
									</button> -->
									<button type="button"  class="button2"  onclick="Close();return false;">
										关 闭
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
					
					showAlert("操作失败！",{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">				
					
					showAlert("操作成功！",{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
					</script>
				</logic:equal>
			</logic:notEmpty>
	</body>
</html>
