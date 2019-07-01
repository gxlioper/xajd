<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//点击是否审核
		function clickSfsh(){
		
			var sfsh = $("hid_sfsh").value;
			
			if("是" == sfsh){
				$("tr_splc").style.display = "";
				$("tr_lssh").style.display = "";
			}else{
				$("tr_splc").style.display = "none";
				$("tr_lssh").style.display = "none";
				
				$("rad_lcid_no").checked="true";
				$("hid_lcid").value="无";
				
				$("shkssj").value = "";//审核开始时间
				$("shjssj").value = "";//审核结束时间
			}
		}
		
		//下一步
		function nextStep(tag){
			if(tag == "ok"){
				var sfsh = $("hid_sfsh").value;//是否审核
				var lcid = $("hid_lcid").value;//流程ID
				var sqkssj = $("sqkssj").value;//申请开始时间
				var sqjssj = $("sqjssj").value;//申请结束时间
				var shkssj = $("shkssj").value;//审核开始时间
				var shjssj = $("shjssj").value;//审核结束时间
				
				if(sqkssj == ""){
					alertError("修改开始时间为空，请确认");
					return false;
				}
				if(sqjssj == ""){
					alertError("修改结束时间为空，请确认");
					return false;
				}
				if(parseInt(sqkssj)>parseInt(sqjssj)){
					alertError("修改开始时间不能晚于结束时间");
					return false;
				}
				
				if(sfsh == "是"){

					if(lcid == "无"){
						alertError("如果需要审核的话，请选择审核流程");
						return false;
					}			
					if(shkssj == ""){
						alertError("审核开始时间为空，请确认");
						return false;
					}
					if(shjssj == ""){
						alertError("审核结束时间为空，请确认");
						return false;
					}
					if(parseInt(shkssj)>parseInt(shjssj)){
						alertError("审核开始时间不能晚于结束时间");
						return false;
					}
					if(parseInt(sqjssj)>parseInt(shkssj)){
						alertError("审核开始时间不能早于修改结束时间");
						return false;
					}
				}
				
							
				var url="xsxx_cssz_grxx_method.do?method=saveCssz";
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
				//参数
			 	var parameter = {
					"sfsh":escape(sfsh),
					"lcid":lcid,
					"sqkssj":sqkssj,
					"sqjssj":sqjssj,
					"shkssj":shkssj,
					"shjssj":shjssj
				};
				
				jQuery.post(url,parameter,function(result){
					refreshForm("xsxx_cssz_grxx_method.do?method=xszdManage");
				});
			}
		}
		</script>
	</head>
	<body onload="clickSfsh()" >
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。</br>
				<span id="div_help" style="display: none">
				1.如果学生信息修改不需要审核的话，则学生提交申请后,<font color="blue">直接更新</font>学生信息库。</br>
				2.如果需要审核的，将展现所属模块为<font color="blue">学生信息</font>的审核流。</br>
				3.学生只可以在<font color="blue">修改时间范围内</font>进行修改，但是如果申请被<font color="blue">退回</font>的话，即使不在时间范围内也可以修改。</br>
				4.老师只可以在<font color="blue">审核时间范围内</font>进行审核。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/xsxx_cssz" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			
			<!-- 维护信息 -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="2">
							<span>个人信息修改参数设置</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							是否需要审核
						</th>
						<td width="">
							<html:radio name="rs" property="sfsh" value="是" onclick="$('hid_sfsh').value=this.value;clickSfsh()"/>是
							<html:radio name="rs" property="sfsh" value="否" onclick="$('hid_sfsh').value=this.value;clickSfsh()"/>否
							<html:hidden name="rs" property="sfsh" styleId="hid_sfsh"/>
						</td>
					</tr>
					<tr style="display:none" id="tr_splc">
						<th>
							审核流程选择
						</th>
						<td>
							<html:hidden name="rs" property="lcid" styleId="hid_lcid"/>
							<div style="width:100%;height:130px;overflow-x:hidden;overflow-y:auto;">
								<input type="radio" name="rad_lcid" id="rad_lcid_no"
										onclick="$('lcid').value=this.value"
										value="无"
								/>无
								
								<br/>
								<logic:iterate name="splcList" id="splcRs">
									<input type="radio" name="rad_lcid" 
										onclick="$('lcid').value=this.value"
										value="${splcRs.dm }"
										<logic:equal name="rs" property="lcid" value="${splcRs.dm }">checked="checked"</logic:equal>
									/>${splcRs.mc }
										
									<br/>
								</logic:iterate>
							</div>
						</td>
					</tr>
					<tr>
						<th width="">
							学生修改时间
						</th>
						<td width="">
							<html:text name="rs" property="sqkssj" styleId="sqkssj" style="" 
								onclick="return showCalendar('sqkssj','ymmdd');"/>
							——
							<html:text name="rs" property="sqjssj" styleId="sqjssj" style="" 
								onclick="return showCalendar('sqjssj','ymmdd');"/>
						</td>
					</tr>
					<tr style="display:none" id="tr_lssh">
						<th width="">
							老师审核时间
						</th>
						<td width="">
							<html:text name="rs" property="shkssj" styleId="shkssj" style="" 
								onclick="return showCalendar('shkssj','ymmdd');"/>
							——
							<html:text name="rs" property="shjssj" styleId="shjssj" style="" 
								onclick="return showCalendar('shjssj','ymmdd');"/>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='2'>
							<div class="btn">
								<!-- 保存 -->
								<button type="button" onclick="confirmInfo('请确认您的设置',nextStep);">
									下一步
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 维护信息 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>

		</html:form>
	</body>
</html>