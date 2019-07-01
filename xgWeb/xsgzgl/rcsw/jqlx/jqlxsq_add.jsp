<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/jqlx/js/jqlxComm.js"></script>
		<script	type="text/javascript">
		function check(ids){
			var id=ids.split("-");
			for(var i=0;i<id.length;i++){
				var lddm=jQuery.trim(jQuery("#"+id[i]).val());
				if(lddm==null||""==lddm){
					return false;
				}
			}
			return true;
		}
		function saveForm(obj){
			// ==========个性化 begin===========
			var xxdm = '${xxdm}';
			if(xxdm == '10351'){
				clearData('bz', bzMsg);
			}
			// ==========个性化 end===========
			//先验证学号
			var xh = jQuery("#xh").val();
			var sqly = jQuery("#sqly").val();
			if (jQuery.trim(xh) == ""){
				showAlert("请先选择学生！");
				return false;
			}	
			if (jQuery.trim(jQuery("#lxkssj").val()) == ""){
				showAlert("留校开始时间不能为空！");
				return false;
			}
			if (jQuery.trim(jQuery("#lxjzsj").val()) == ""){
				showAlert("留校截止时间不能为空！");
				return false;
			}
			// ==========个性化 begin===========
			var xxdm = '${xxdm}';
			if(xxdm == '12861' || xxdm == '10351'){
				if (jQuery.trim(jQuery("#jzxm").val()) == ""){
					showAlert("家长姓名不能为空！");
					return false;
				}
				if (jQuery.trim(jQuery("#jzlxdh").val()) == ""){
					showAlert("家长联系电话不能为空！");
					return false;
				}
			}
			if(xxdm == '10351') {
				if (jQuery.trim(jQuery("#lxsqlxdm").val()) == ""){
					showAlert("申请类型不能为空！");
					return false;
				}
			}
			if(xxdm == '11488'){
				var ids = "lxyy-dwlxr-dwlxdh";
				if(!check(ids)){
					showAlert("请将带<font color='red'>*</font>的项目填写完整");
					return false;
				}
				var lxyy = jQuery("#lxyy").val();
				if(lxyy == '3'){
					var lxdw = jQuery.trim(jQuery("#lxdw").val());
					if(lxdw == null || lxdw == ''){
						showAlert("请将带<font color='red'>*</font>的项目填写完整");
						return false;
					}
				}
			}

			//北京中医药
            if(xxdm == '10026'){
                var ids = "lsxq-lddm-qsh";
                if(!check(ids)){
                    showAlert("请将带<font color='red'>*</font>的项目填写完整");
                    return false;
                }
            }

			//浙江中医药
            if(xxdm == '10344'){
                //去掉原住宿园区，原宿舍楼号，原寝室号三个选择框
				//var ids = "rzdz-lddm-qsh-dwlxdh-lxdw-bz-lxkssj-lxjzsj-sqly-sfgcj-sqlxtj";
				var ids = "dwlxdh-lxdw-bz-lxkssj-lxjzsj-sqly-sfgcj-sqlxtj";
				if(!check(ids)){
                    showAlert("请将带<font color='red'>*</font>的项目填写完整");
                    return false;
                }
            }

			if(xxdm == '10364'){
				if (jQuery.trim(jQuery("#lxyy").val()) == ""){
					showAlert("留校原因不能为空！");
					return false;
				}
			}
			if(xxdm == '10530'){
				if (jQuery.trim(jQuery("#jzlxdh").val()) == ""){
					showAlert("联系电话不能为空！");
					return false;
				}
			}
			/*if(xxdm == '10511'){
				if (jQuery.trim(jQuery("#rzdz").val()) == ""){
					showAlert("现住寝室不能为空！");
					return false;
				}
			}*/
			// ==========个性化 end===========
			if(xxdm != '11488'&&(xxdm != '10364')){
				if (jQuery.trim(sqly) == ""){
					showAlert("留校原因不能为空！");
					return false;
				}
			}			
			<logic:notEmpty name="jcszModel" property="uploadid">
			if (jQuery("#lxxy_checkbox") && !jQuery("#lxxy_checkbox").attr('checked')){
				showAlert("请先接受《留校协议》 ！");
				return false;
			}
			</logic:notEmpty>
			var url = "";
			if(obj == 'save'){
				 url = "rcsw_jqlx.do?method=addJqlx&type=save";
			}
			if(obj == 'submit'){
				 url = "rcsw_jqlx.do?method=addJqlx&type=submit";
			}
		      ajaxSubFormWithFun("jqlxModel",url,function(data){
		    	 if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }
				});
		  }
		jQuery(document).ready(function(){ 
			//初始化日期控件，根据参数设置对日期做限制
			var lxkssjV = '${jcszModel.lxkssj }';
			var lxjssjV = '${jcszModel.lxjssj }';
			jQuery("#lxkssj").bind("focus", function (){
				var lxjssj = jQuery("#lxjzsj").val();
				if(lxjssj == ''){
					lxjssj = lxjssjV;
				}
				WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:lxjssj,minDate:lxkssjV}); 
			}); 
			jQuery("#lxjzsj").bind("focus", function (){
				var lxkssj = jQuery("#lxkssj").val();
				if(lxkssj == ''){
					lxkssj = lxkssjV;
				}
				WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:lxjssjV,minDate:lxkssj});
			}); 
			jQuery("#lxkssj2").bind("focus", function (){
				var lxjssj2 = jQuery("#lxjzsj2").val();
				if(lxjssj2 == ''){
					lxjssj2 = lxjssjV;
				}
				WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:lxjssj2,minDate:lxkssjV}); 
			}); 
			jQuery("#lxjzsj2").bind("focus", function (){
				var lxkssj2 = jQuery("#lxkssj2").val();
				if(lxkssj2 == ''){
					lxkssj2 = lxkssjV;
				}
				WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:lxjssjV,minDate:lxkssj2});
			}); 
			
			// ==========个性化 begin===========
			var xxdm = '${xxdm}';
			if(xxdm == '10351'){
				initData('bz', bzMsg);
			}
			/*if(xxdm == '10511'){
				jQuery("#rzdz").css("width","320px");
			}*/
			// ==========个性化 end===========
		});
		</script>
		
	</head>
	<body>
		
		<html:form action="/rcsw_jqlx" method="post" styleId="jqlxModel">
		<html:hidden property="yzqs" styleId="yzqs"/>
			<% String xxdm = (String) request.getAttribute("xxdm"); %>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:470px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/selectStudent.jsp" %>
					<logic:equal value="10277" name="xxdm">
						<tr>
							<th>是否贫困生</th>
							<td colspan="3">
									${sfkns }
							</td>
						</tr>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="20%">学年</th>
							<td width="30%">
<%--								<html:select  property="xn" styleId="xn" style="width:130px">--%>
<%--									<html:options collection="xnList" labelProperty="xn" property="xn"/>--%>
<%--								</html:select>--%>
									${rs.xn }
									<html:hidden property="xn"></html:hidden>
							</td>
							<th width="20%">学期</th>
							<td width="30%">
<%--								<html:select  property="xq" styleId="xq" style="width:130px">--%>
<%--									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>--%>
<%--								</html:select>--%>
									${rs.xqmc }
									<html:hidden property="xq"></html:hidden>
							</td>
						</tr>
						<!-- 浙江中医药大学个性化start -->
						<logic:equal value="10344" name="xxdm">
							<tr>
								<%--<th width="20%">
									<span class="red">*</span>原住宿园区
								</th>
								<td>
									<html:select  property="rzdz" styleId="rzdz" style="width:155px">
										<html:options collection="yqList" property="yqdm" labelProperty="yqmc"/>
									</html:select>
								</td>
								<th width="20%">
									<span class="red">*</span>原宿舍楼号
								</th>
								<td>
									<html:text property="lddm" styleId="lddm" style="width:155px" maxlength="10">
										
									</html:text>
								</td>
							</tr>
							<tr>
								<th width="20%">
									<span class="red">*</span>原寝室号
								</th>
								<td>
									<html:text property="qsh" styleId="qsh" maxlength="10"></html:text>
								</td>
								--%>
								<th width="20%">原住寝室</th>
								<td width="30%">
									${rs.yzqs }
									<html:hidden property="yzqs"></html:hidden>
								</td>
								<th>
									<span class="red">*</span>手机号码
								</th>
								<td>
									<html:text property="dwlxdh" styleId="dwlxdh" maxlength="15"></html:text>
								</td>
							</tr>
							<tr>
								<th width="20%">
									<span class="red">*</span>家庭地址
								</th>
								<td>
									<html:text property="lxdw" styleId="lxdw" maxlength="50"></html:text>
								</td>
								<th>
									<span class="red">*</span>家庭联系人和手机
								</th>
								<td>
									<html:text property="bz" styleId="bz" maxlength="40"></html:text>
								</td>
							</tr>		
						</logic:equal>
						<!-- 浙江中医药大学 end -->
					    <tr>
							<th><span class="red">*</span>留校开始时间</th>
							<td>
								<html:text property="lxkssj" styleId="lxkssj" />
							</td>
							<th><span class="red">*</span>留校截止时间</th>
							<td>
								<html:text property="lxjzsj" styleId="lxjzsj"  />
							</td>
					    </tr>
					    <logic:notEqual value="12309" name="xxdm">
					    <tr>
							<th>留校开始时间二</th>
							<td>
								<html:text property="lxkssj2" styleId="lxkssj2" />
							</td>
							<th>留校截止时间二</th>
							<td>
								<html:text property="lxjzsj2" styleId="lxjzsj2" />
							</td>
						</tr>
						<logic:equal value="10344" name="xxdm">
							<tr>
								<th width="20%">
									<span class="red">*</span>是否在校过春节
								</th>
								<td>
									<html:select property="sfgcj" styleId="sfgcj">
										<html:option value="">---请选择---</html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
								<th width="20%">
									<span class="red">*</span>申请留校条件
								</th>
								<td>
									<html:select property="sqlxtj" styleId="sqlxtj" style="width:99%">
										<html:option value="">--请选择--</html:option>
										<html:options collection="lxtjList" property="lxtjmc" labelProperty="lxtjmc" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
						</logic:notEqual>
						<!-- 安徽农业大学留校原因个性化：可选择，与衢州学院公用一张原因代码表 -->
						<logic:equal value="10364" name="xxdm">
							<tr>
								<th><span class="red">*</span>留校原因</th>
								<td colspan="3">
									<html:select property="lxyy" styleId="lxyy">
											<html:option value="">--请选择--</html:option>
											<html:options collection="lxyyList" property="lxyydm" labelProperty="lxyymc" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
						<!-- 衢州学院留校原因个性化 -->
						<logic:equal value="11488" name="xxdm">
					    <tbody id="qzxy">
						<tr>
							<th><span class="red">*</span>留校原因</th>
							<td>
								<html:select property="lxyy" styleId="lxyy" onchange="lxyyChange(this)">
										<html:option value="">--请选择--</html:option>
										<html:options collection="lxyyList" property="lxyydm" labelProperty="lxyymc" />
								</html:select>
							</td>
							<th id="qzxylxr"><span class="red">*</span>联系人</th>
							<td>
								<html:text property="dwlxr" styleId="dwlxr" maxlength="8"></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>联系电话</th>
							<td>
								<html:text property="dwlxdh" styleId="dwlxdh" onblur="checkPhone(this)"></html:text>
							</td>
							<th>
								原住寝室
							</th>
							<td>
								${rs.yzqs }
							</td>
						</tr>
						</tbody>						
						</logic:equal>
						<logic:equal value="10530" name="xxdm">
							<tr>
								<th width="20%">
									<span class="red">*</span>联系电话
								</th>
								<td>
									<html:text property="jzlxdh" styleId="jzlxdh" onkeyup="checkInput(this)" maxlength="15" style=" ime-mode: disabled;"></html:text>
								</td>
								<logic:equal value="寒假" name = "jqlxV">
									<th width="20%">
										<span class="red">*</span>是否留校过春节
									</th>
									<td>
										<html:select property="sfgcj" styleId="sfgcj">
											<html:option value="否">否</html:option>
											<html:option value="是">是</html:option>
										</html:select>
									</td>
								</logic:equal>
							</tr>
						</logic:equal>
					      <logic:equal value="11458" name="xxdm">
					    	<logic:equal value="寒假" name = "jqlxV">
							<tr>
								<th>
									<span class="red">*</span>是否吃年夜饭
								</th>
								<td colspan="3">
									<html:select  property="sfcnyf" styleId="sfcnyf" style="width:150px">
										<html:option value="否">否</html:option>
										<html:option value="是">是</html:option>
									</html:select>
								</td>
				      		</tr>
							</logic:equal>
						</logic:equal>
						  <logic:equal value="10277" name="xxdm">
					    	<logic:equal value="寒假" name = "jqlxV">
							<tr>
								<th>
									<span class="red">*</span>是否吃年夜饭
								</th>
								<td colspan="3">
									<html:select  property="sfcnyf" styleId="sfcnyf" style="width:150px">
										<html:option value="否">否</html:option>
										<html:option value="是">是</html:option>
									</html:select>
								</td>
				      		</tr>
							</logic:equal>
							</logic:equal>
					      <logic:equal value="10351" name="xxdm">
					    	<logic:equal value="寒假" name = "jqlxV">
							<tr>
								<th>
									<span class="red">*</span>是否留校过年
								</th>
								<td colspan="3">
									<html:select  property="sflxgn" styleId="sflxgn" style="width:150px">
										<html:option value="否">否</html:option>
										<html:option value="是">是</html:option>
									</html:select>
								</td>
				      		</tr>
							<tr>
								<th>
									备注
								</th>
								<td colspan="3">
									<html:text property="bz" styleId="bz" size="70" maxlength="100"></html:text>
								</td>
				      		</tr>
							</logic:equal>
						</logic:equal>
						<% if("12861".equals(xxdm) || "10351".equals(xxdm)){ %>
							<tr>
								<th>
									<span class="red">*</span>家长姓名
								</th>
								<td>
									<html:text property="jzxm" styleId="jzxm" maxlength="8"></html:text>
								</td>
								<th>
									<span class="red">*</span>家长联系电话
								</th>
								<td>
									<html:text property="jzlxdh" styleId="jzlxdh" onkeyup="checkInput(this)" maxlength="15" style=" ime-mode: disabled;"></html:text>
								</td>
				      		</tr>
						<% } %>
						<logic:equal value="10718" name="xxdm">
						<tr>
								<th>
									<span class="red">*</span>是否食用清真食物
								</th>
								<td colspan="3">
									<html:select  property="sfsyqzsw" styleId="sfsyqzsw" style="width:150px">
										<html:option value="否">否</html:option>
										<html:option value="是">是</html:option>
									</html:select>
								</td>
				      		</tr>
						</logic:equal>
						<logic:equal value="10704" name="xxdm">
						<tr>
								<th>
									<span class="red">*</span>是否食用清真食物
								</th>
								<td colspan="3">
									<html:select  property="sfsyqzsw" styleId="sfsyqzsw" style="width:150px">
										<html:option value="否">否</html:option>
										<html:option value="是">是</html:option>
									</html:select>
								</td>
				      		</tr>
						</logic:equal>
						<!-- 浙江传媒学院个性化增加留宿校区字段begin -->
						<logic:equal value="11647" name = "xxdm">
							<th width="20%">
								留宿校区
							</th>
							<td colspan="3">
								<html:select property="lsxq" styleId="lsxq" style="width:155px" value="${defaluevalue}">
									<logic:iterate id="i" name="lsxqList">
										<html:option value="${i.dm}">${i.xqmc}</html:option>
									</logic:iterate>
								</html:select>
							</td>
						</logic:equal>
						<!-- 浙江传媒学院个性化增加留宿校区字段end -->
						<!-- 北京中医药个性化begin -->
						<logic:equal value="10026" name = "xxdm">
							<tr>
								<th width="20%">
									<span class="red">*</span>留校校区
								</th>
								<td>
									<html:select  property="lsxq" styleId="lsxq" style="width:155px">
										<html:options collection="lsxqList" labelProperty="xqmc" property="dm"/>
									</html:select>
								</td>
								<th width="20%">
									<span class="red">*</span>楼栋
								</th>
								<td>
									<html:select  property="lddm" styleId="lddm" style="width:155px">
										<html:options collection="ldList" labelProperty="ldmc" property="lddm"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th width="20%">
									<span class="red">*</span>寝室号
								</th>
								<td>
									<html:text property="qsh" styleId="qsh" maxlength="10"></html:text>
								</td>
							</tr>
						</logic:equal>
						<!-- 北京中医药个性化end -->
												
						<logic:notEqual name="xxdm" value="11488">
							<logic:notEqual name="xxdm" value="10026">
								<logic:notEqual name="xxdm" value="10344">
									<tr>
										<th>
											原住寝室
										</th>
										<td colspan="3">
											${rs.yzqs }
										</td>
									</tr>
								</logic:notEqual>
			      			</logic:notEqual>
			      		</logic:notEqual>
<%--					    <tr>--%>
<%--							<th>--%>
<%--								<% if("10511".equals(xxdm)){ %><span class="red">*</span><% } %>现住寝室--%>
<%--							</th>--%>
<%--							<td colspan="3">--%>
<%--								<html:text property="rzdz" styleId="rzdz" size="70" maxlength="80"></html:text><% if("10511".equals(xxdm)){ %>（填写格式*区*栋*室，例如东区6栋101室）<% } %>--%>
<%--							</td>--%>
<%--			      		</tr>--%>
						
						<logic:equal value="10351" name = "xxdm">
							<tr>
								<th>
									<font color="red">* </font>申请类型
								</th>
								<td colspan="3">
									<html:select property="lxsqlxdm" styleId="lxsqlxdm">
										<html:option value=""></html:option>
										<html:options collection="lxsqlxList" property="lxsqlxdm" labelProperty="lxsqlxmc" />
									</html:select>
								</td>
							</tr>			
						</logic:equal>
						
						<logic:notEqual name="xxdm" value="11488">
						<logic:notEqual name="xxdm" value="10364">
					    <tr>
							<th>
								<span class="red">*</span>留校原因
								<br /><font color="red">&lt;限320字&gt;</font>
							</th>
						
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='5' onblur="checkLen(this,320);"/>
							<logic:equal value="10352" name="xxdm">
							<div style="line-height:20px; min-height:20px; _height:20px; padding:5px 5px 5px 5px; border:#f1e0b5 1px solid; margin-top:6px; position:relative;">
								本人申请留校住宿，并保证做到学生宿舍服务中心制订的下列各项要求：
								<br/>1.学生自愿提出申请，经学院同意后报学生宿舍服务中心审批。
								<br/>2.爱护公物，损坏照价赔偿。
								<br/>3.申请假期住宿的学生，发生一切事故由学生本人负责。
								<br/>4.学生住宿必须自觉遵守《丽水学院学生守则》及学校的各项规章制度，做到遵守纪律，
								<br/>5.后勤公司根据实际统一安排住宿，若被同意住原寝室，要求对本寝室的财物、卫生、安全防范等全面负责。
								<br/>6.留校住宿学生如需延长或缩短住宿时间，或中途需离校两天及以上的，应提前向审批人提出申请，并经后勤公司学生宿舍服务中心同意后方可变更。
							</div>
							</logic:equal>
							</td>
			      		</tr>
			      		</logic:notEqual>
			      		</logic:notEqual>								
			      		
						<tr>
							<th align="right" style="width: 10%">
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="fjxx" styleId="fjxx"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : 3,
											//后缀
											accept : 'doc|docx|xls|xlsx|jpg|rar|zip|pdf|ppt',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'fjxx'
											});
									});
								</script>  
							</td>
						</tr>
						
			      		<!-- 浙江中医药个性化 -->
			      		<logic:equal value="10344" name="xxdm">
			      		<tr>
			      			<th>
			      				假期留宿学生安全承诺
			      			</th>
			      			<td colspan="3">
			      				<div style="line-height:20px; min-height:20px; _height:20px; padding:5px 5px 5px 5px; border:#f1e0b5 1px solid; margin-top:6px; position:relative;">
								本人申请假期留宿，并自愿保证做到以下各项要求：
								<br/>1.假期留宿期间，服从学校，学院和公寓的统一安排管理。
								<br/>2.自觉遵守公寓管理各项规章制度，损坏公物照价赔偿。
								<br/>3.自觉做到每天晚上18:00 —— 22:00到所在楼值班室登记签名，累计三次不登记，取消该生入住资格。
								<br/>4.若有特殊情况需中途离校，自觉到值班室宿管员处进行登记并报告学院。
							</div>
								
			      			</td>
			      		</tr>
			      		</logic:equal>
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
									<logic:notEmpty name="jcszModel" property="uploadid">
										<input type="checkbox" id="lxxy_checkbox"/>
										<logic:equal value="10344" name="xxdm">
										我已接受并下载
										</logic:equal>
										<logic:notEqual value="10344" name="xxdm">										
										我已阅读并接受
										</logic:notEqual>
										<a href="javascript:void(0);" onclick="window.open('common_upload.do?method=asyncDownload&fid=${jcszModel.uploadid}');return false;" class="name" style="margin-left: 0px;">
											<logic:equal value="10344" name="xxdm">
												《假期留宿学生安全承诺书》
											</logic:equal>
											<logic:notEqual value="10344" name="xxdm">
												《留校协议》
											</logic:notEqual>
										</a>
										&nbsp;&nbsp;&nbsp;
									</logic:notEmpty>
									<button type="button" type="button" onclick="saveForm('save');">
										保存草稿
									</button>
									<button type="button" type="button" onclick="saveForm('submit');">
										提交申请
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
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

