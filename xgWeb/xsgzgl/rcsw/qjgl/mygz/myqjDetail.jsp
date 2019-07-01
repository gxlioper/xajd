<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/icon.css" />
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/rcsw/qjgl/myqjDetail.js"></script>
				<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
			<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
				<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" defer="defer">
		var j=0;
		function uploadPic(){
			var qjclPath=$("qjclPath").value;
			var path=qjclPath.split(".")[1];
			if(path!="jpg"){
				alertError("仅支持.jpg格式的图片文件,请确认！");
				return false;
			}
			jQuery.ajaxFileUpload({
			  url:'rcsw_qjgl.do?method=uploadQjcl',//服务器端程序
			  secureuri:false,
			  fileElementId:'qjclPath',//input框的ID
			  async: false,
			  dataType: 'json',//返回数据类型
			  success: function (data){//上传成功
			  	jQuery('#qjclImg').empty();
			  	$("qjcl").value=data;
			  	var html = '<img src="<%=request.getContextPath()%>/qjclPic.jsp?id='+data+'&flg=';
			  		html+= (++j);
			  		html+= '" border="0" align="absmiddle" style="width:800px" />'
			  	
			  	jQuery("#qjclImg").html(html).show();
			  }
			});
		}
		
		//保存请假申请
		function saveQjsq(tag){
			var xh=jQuery("#xh").val();
			var qjlx=jQuery("input:checked").val();
			if(xh==""){
				showAlert("请选择学生");
				return false;
			}
			if(tag == "ok"){
				//开始时间
				var kssj = $("kssj").value;
				if(kssj == ""){
					alertError("请假开始时间不能为空，请确认");
					return false;
				}
				
				//结束时间
				var jssj = $("jssj").value;
				if(jssj == ""){
					alertError("请假结束时间不能为空，请确认");
					return false;
				}
				
				if(parseInt(kssj) > parseInt(jssj)){
					alertError("【开始时间】不能晚于【结束时间】，请确认");
					return false;
				}
				//申请天数
				var sqts = $("sqts").value;
				if(null==sqts||sqts == ""){
					alertError("申请天数不能为空，请确认");
					return false;
				}
				
				//
				if(null==qjlx||qjlx == ""){
					alertError("请假类型不能为空，请确认");
					return false;
				}
				
				//学号
				var xh = $("xh").value;
				//请假ID
				var qjid = $("qjid").value;
				//联系电话
				var lxdh = $("lxdh").value;
				//家庭电话
				var jtdh = $("jtdh").value;
				//家庭地址
				var jtdz = $("jtszd").value;
				//申请理由
				var sqly = $("sqly").value;
				//备注
				var bz = $("bz").value;
				//备注
				var qjcl = $("qjcl").value;

				var qjlx = jQuery('input[name=qjlx]:checked').val(); 
				
				var url="rcsw_qjgl.do?method=saveQjsq";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
					
				//参数
			 	var parameter = {
					"qjid":qjid,
					"xh":xh,
					"sqts":sqts,
					"kssj":kssj,
					"jssj":jssj,	
					"lxdh":lxdh,
					"jtdh":escape(jtdh),
					"jtdz":escape(jtdz),
					"sqly":escape(sqly),
					"bz":escape(bz),
					"qjcl":escape(qjcl),
					"qjlx":escape(qjlx)
				};
				
				jQuery.post(url,parameter,function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					if(result=="保存成功"){
						showAlert(result,{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					}else{
						alertInfo(result);
					}
				});
			}
		}
		
		//设置类型名称
		function setLxmc(){
			
			var sqts=$("sqts").value;
			var qjlx=jQuery("[name=qjlx]:checked").eq(0).val();
			
			var url="rcsw_qjgl.do?method=getQjxm";
			
			if(sqts!="" && qjlx!=""){
				//参数
			 	var parameter = {
					"sqts":sqts,
					"qjlx":qjlx
				};
				
				jQuery.post(url,parameter,function(data){
					var select=jQuery("<select id='qjid' name='qjid'></select>");
					if(data != ""){
						//var qjxm = result.split("!!@@!!")[0];
						//var id = result.split("!!@@!!")[1];
						//$("qjid").value=id;
						jQuery.each(data,function(i,e){
						var option = jQuery('<option></option>');
						    option.append(e["lxmc"]);
						    option.attr("value",e["id"]);
						    select.append(option);
						});
						jQuery("#btn_bc").attr("disabled",false);
						jQuery("#p_qjxm").html(select);
						
					}else{
						//$("qjid").value="";
						$("btn_bc").disabled="true";
						$("p_qjxm").innerHTML="<font color=\"red\">您所申请的请假天数，尚未指定审核流程，无法申请，请确认</font>";
					}
				},'json');
			}
		}
		
		
		</script>
	</head>
	<body onload="">

		<!-- 标题 -->
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>--%>
		<!-- 标题 end-->

		<!-- 提示信息 end-->
		<div class="prompt" id="div_help">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
					1.联系电话，家庭电话，家庭地址取自学生信息，如果您觉得有误的话，可以修改。</br>
					2.请填写<font color="blue">申请天数</font>，会根据您录入的天数，自动绑定请假类型。</br>
					3.如果没有任一请假类型与您录入的申请天数匹配，则<font color="blue">无法申请</font>，请联系相关老师确认。
			</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/rcsw_qjgl" method="post"  enctype="multipart/form-data">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden name="rs" property="qjcl" styleId="qjcl" />
			<html:hidden name="rs" property="id" styleId="id" />
			<input type="hidden" id="url" name="url"
				value="rcsw_qjgl.do?method=myqjDetail" />
		
			<!-- 隐藏域 end-->

			<!-- 学生基本信息 -->
			<div style="width:100%;height:400px;overflow-x:hidden;overflow-y:auto;">
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>学生基本信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">
							学号
						</th>
						<td width="30%">
							
							<html:hidden name="rs" property="id" styleId="id"/>
							<!-- 
							<html:hidden name="rs" property="qjid" styleId="qjid"/>
							-->
							<logic:equal name="userType" value="stu">
								<html:hidden name="rs" property="xh" styleId="xh"/>
								${rs.xh }
							</logic:equal> 
							<logic:notEqual name="userType" value="stu">
								<html:text name="rs" property="xh" styleId="xh" readonly="readonly"/>
								<button type="button" onclick="showDialog('请选择一个学生',680,550,'xsxx_xsgl.do?method=showStudents&goto=rcsw_qjgl.do?method=myqjDetail');return false;" class="btn_01" >
									选择
								</button>
							</logic:notEqual> 
						</td>
						<th width="20%">
							姓名
						</th>
						<td width="30%">
							${rs.xm }
						</td>
					</tr>
					<tr>
						<th>
							性别
						</th>
						<td>
							${rs.xb }
						</td>
						<th>
							班级
						</th>
						<td>
							${rs.bjmc }
						</td>
					</tr>
					<tr>
						<th>
							联系电话
						</th>
						<td>
							<html:text name="rs" property="lxdh" styleId="lxdh"
								onkeyup="checkInputNum(this)" onblur="checkInputNum(this)"
								maxlength="20" style="ime-mode:disabled;" />
						</td>
						<th>
							家庭电话
						</th>
						<td>
							<html:text name="rs" property="jtdh" styleId="jtdh"
								onkeyup="checkInputNum(this)" onblur="checkInputNum(this)"
								maxlength="20" style="ime-mode:disabled;" />
						</td>
					</tr>
					<tr>
						<th>
							家庭地址
						</th>
						<td colspan="3">
							<html:text name="rs" property="jtszd" styleId="jtszd"
								style="width: 545px" maxlength="50" />
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 学生基本信息 end-->

			<!-- 请假基本信息 -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>请假申请信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">
							<font color="red">*</font>请假开始时间
						</th>
						<td width="30%">
							<html:text name="rs" property="kssj" styleId="kssj"
								onclick="return showCalendar('kssj','ymmdd');" readonly="true" />
						</td>
						<th width="20%">
							<font color="red">*</font>请假结束时间
						</th>
						<td width="30%">
							<html:text name="rs" property="jssj" styleId="jssj"
								onclick="return showCalendar('jssj','ymmdd');" readonly="true" />
						</td>
					</tr>
					<tr>
						<th width="20%">
							<font color="red">*</font>申请天数
						</th>
						<td width="30%">
							<html:text name="rs" property="sqts" styleId="sqts"
								onkeyup="checkInputNum(this)" onblur="checkInputNum(this);setLxmc()"
								maxlength="5" style="width:50px;ime-mode:disabled;" />天
						</td>
						<th width="20%">
							<font color="red">*</font>请假类型
						</th>
						<td width="30%">
							<logic:iterate name="qjlxList" id="qjlx" indexId="index">
								<html:radio property="qjlx"  styleId="qjlx_${index }" value="${qjlx.dm}" />${qjlx.mc}
							</logic:iterate>
						</td>
					</tr>
					<tr>
						<th width="20%">
							请假流程
						</th>
						<td width="30%" colspan="3">
							<p id="p_qjxm">
								<font color="blue">请录入你希望申请的天数</font>
							</p>
						</td>
					</tr>
					<tr>
						<th>
							申请理由
							<br />
							<font color="red">(限制录入500字)</font>						
						</th>
						<td colspan="3">
							<html:textarea name='rs' property="sqly"  
								styleId="sqly" rows="5" 
								style="word-break:break-all;width:545px" 
								onblur="chLeng(this,'500')">
							</html:textarea>
						</td>
					</tr>
					<tr>
						<th>
							备注
							<br />
							<font color="red">(限制录入500字)</font>	
						</th>
						<td colspan="3">
							<html:textarea name='rs' property="bz"  
								styleId="bz" rows="5" 
								style="word-break:break-all;width:545px" 
								onblur="chLeng(this,'500')">
							</html:textarea>
						</td>
					</tr>
				</tbody>
				
				<tbody>
					
					<tr>
						<th>
						       附件上传
						</th>
						<td colspan="3"> 
							<input type="file" id="qjclPath" name="qjclPath" style="width:60%"  onchange='uploadPic();'/>
							&nbsp;&nbsp;
							<font color="red">(文件大小小于&lt;10M&gt;)</font>
							<div>
								<font color="blue">目前尚未有证明材料，请点击"浏览"选择文件上传<br/>
								注:仅支持.jpg格式的图片文件</font>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="4"> 
						<div id="qjclImg" style="display:none">
							<img style="width:800px"
								src="<%=request.getContextPath()%>/qjclPic.jsp?id=${rs.qjcl }"
								border="0"/>
						</div>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 请假基本信息 end-->

			

			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			</div>
			<table class="formlist" width="">
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<!-- 保存 -->
								<button type="button"  onclick="saveQjsq('ok');" id="btn_bc">
									<bean:message key="lable.btn_bc_space" />
								</button>
								<!-- 关闭-->
								<button type="button"  onclick="Close();return false;">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>
