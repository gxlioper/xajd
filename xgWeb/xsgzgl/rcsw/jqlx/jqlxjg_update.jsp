<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/jqlx/js/jqlxComm.js"></script>
		<script	type="text/javascript">
		function saveForm(obj){
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
			// ==========个性化 begin===========
			var xxdm = '${xxdm}';
			if(xxdm == '10351'){
				clearData('bz', bzMsg);
			}
			// ==========个性化 end===========
			var sqly = jQuery("#sqly").val();
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
					showAlert("留宿住寝室不能为空！");
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

			//浙江中医药
            if(xxdm == '10344'){
            	//去掉原住宿园区，原宿舍楼号，原寝室号三个选择框
				//var ids = "rzdz-lddm-dwlxdh-qsh-lxdw-bz-lxkssj-lxjzsj-sqly-lxxq-lxld-lxqs-sfgcj-sqlxtj";
				var ids = "dwlxdh-lxdw-bz-lxkssj-lxjzsj-sqly-lxxq-lxld-lxqs-sfgcj-sqlxtj";
				if(!check(ids)){
                    showAlert("请将带<font color='red'>*</font>的项目填写完整");
                    return false;
                }
            }

			var url = "rcsw_jqlx.do?method=updateJqlxJg&type=update";
			
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
			
			// ======== 加载床位 begin===============
			var cwxx = jQuery("#cwxx").val();
			if(cwxx!=""){
				if('${xxdm}' != "10344"){
					showCwxx(cwxx, '${rs.sqid}');
				}
			}
			// ======== 加载床位 end===============
			// =========== 审批流数据只允许修改留校寝室 begin ==========
			if('${rs.sjlx}' == '1'){
				jQuery("select,textarea,input:not(input:hidden)").attr("disabled","disabled");
			}
			// =========== 审批流数据只允许修改留校寝室 begin ==========
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
			<input type="hidden" id="xh" value="${rs.xh }"/>
			<input type="hidden" name="sjlx" value="${rs.sjlx }"/>
			<html:hidden property="sqid" styleId="sqid"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<% String xxdm = (String) request.getAttribute("xxdm"); %>
			<logic:equal value="1" name="rs" property="sjlx">
			<logic:notEqual value="10344" name="xxdm">
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					审核流程数据只允许修改<font color="red">留校寝室</font>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			</logic:notEqual>
			</logic:equal>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:470px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
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
							<th width="20%"><span class="red">*</span>学年</th>
							<td width="30%">
								<html:select  property="xn" styleId="xn" style="width:130px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th width="20%"><span class="red">*</span>学期</th>
							<td width="30%">
								<html:select  property="xq" styleId="xq" style="width:130px">
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
						<!-- 浙江中医药大学个性化start -->
						<logic:equal value="10344" name="xxdm">
							<%--<tr>
								<th width="20%">
									<span class="red">*</span>原住宿点
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
							--%>
							<tr>
								<%--<th width="20%">
									<span class="red">*</span>原寝室号
								</th>
								<td>
									<html:text property="qsh" styleId="qsh" maxlength="10"></html:text>
								</td>
								--%>
								<th width="20%">
									原住寝室
								</th>
								<td>
									${rs.yzqs}
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
							<tr>
								<th width="20%">
									<span class="red">*</span>留宿园区
								</th>
								<td>
									<html:select property="lxxq" styleId="lxxq" style="width:155px">
										<html:options collection="yqList" property="yqdm" labelProperty="yqmc"/>
									</html:select>
								</td>
								<th width="20%">
									<span class="red">*</span>留宿宿舍楼号
								</th>
								<td>
									<html:text property="lxld" styleId="lxld" style="width:155px" maxlength="20">
										
									</html:text>
								</td>
							</tr>
							<tr>
								<th width="20%">
									<span class="red">*</span>留宿寝室号
								</th>
								<td>
									<html:text property="lxqs" styleId="lxqs" style="width:155px" maxlength="20">
										
									</html:text>
								</td>
							</tr>		
						</logic:equal>
					    <tr>
							<th><span class="red">*</span>留校开始时间</th>
							<td>
								<html:text property="lxkssj" styleId="lxkssj"  />
							</td>
							<th><span class="red">*</span>留校截止时间</th>
							<td>
								<html:text property="lxjzsj" styleId="lxjzsj" />
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
							<th id="qzxylxr"><span class="red">*</span>
							<logic:equal value="3" name="rs" property="lxyy">
								兼职单位
							</logic:equal>
							<logic:equal value="7" name="rs" property="lxyy">
								家庭
							</logic:equal>联系人
							</th>
							<td>
								<html:text property="dwlxr" styleId="dwlxr"></html:text>
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
						<logic:equal value="3" name="rs" property="lxyy">
							<tr id="qzxyjzdw">
								<th>
									<span class="red">*</span>兼职单位
								</th>
								<td colspan="3">
									<html:text property="lxdw" styleId="lxdw" style='width:500px;' maxlength='50'></html:text>
								</td>
							</tr>
						</logic:equal>
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
							<logic:equal value="寒假" name="jqlxV">
								<tr>
									<th>
										<span class="red">*</span>是否吃年夜饭
									</th>
									<td colspan="3">
										<html:select property="sfcnyf" styleId="sfcnyf"
											style="width:150px">
											<html:option value="否">否</html:option>
											<html:option value="是">是</html:option>
										</html:select>
									</td>
								</tr>
							</logic:equal>
						</logic:equal>
						<logic:equal value="10277" name="xxdm">
							<logic:equal value="寒假" name="jqlxV">
								<tr>
									<th>
										<span class="red">*</span>是否吃年夜饭
									</th>
									<td colspan="3">
										<html:select property="sfcnyf" styleId="sfcnyf"
											style="width:150px">
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
									<html:text property="jzxm" styleId="jzxm" maxlength="10"></html:text>
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
								<html:select  property="lsxq" styleId="lsxq" style="width:155px">
									<html:options collection="lsxqList" labelProperty="xqmc" property="dm"/>
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
						<logic:notEqual value="11488" name="xxdm">
						<logic:notEqual value="10026" name="xxdm">
						<logic:notEqual value="10344" name="xxdm">
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
<%--								<% if("10511".equals(xxdm)){ %><span class="red">*</span><% } %>留宿住寝室--%>
<%--							</th>--%>
<%--							<td colspan="3">--%>
<%--								<html:text property="rzdz" styleId="rzdz" size="70"></html:text><% if("10511".equals(xxdm)){ %>（填写格式*区*栋*室，例如东区6栋101室）<% } %>--%>
<%--							</td>--%>
<%--			      		</tr>--%>

					<logic:notEqual value="10026" name="xxdm">
					<logic:notEqual value="10344" name="xxdm">
						<tr>
							<th>留校寝室</th>
							<td align="left" width="10%" colspan="3">
								<button class="btn_01" type="button"  onclick="selectCw('${path}','${rs.sqid }');return false;" >选择床位</button>
							</td>
						</tr>
						<tr id="yxzcwxx">
							<th>
								已选择床位信息
							</th>
							<td colspan="3">
								<table id="cwxxTable"></table>
								<input type="hidden" name="cwxx" id="cwxx" value="${rs.cwxx }"/>
							</td>
						</tr>
					</logic:notEqual>
					</logic:notEqual>
						
			      			
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
							</td>
			      		</tr>
			      		</logic:notEqual>
			      		</logic:notEqual>
			      		<logic:equal value="0" name="rs" property="sjlx">
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
							</logic:equal>
							<logic:equal value="1" name="rs" property="sjlx">
							<tr>
								<th align="right" style="width: 10%">
									附件信息
								</th>
								<td colspan="3">
										<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
										<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												var gid = "${rs.fjxx}";
												jQuery.MultiUploader_q({
													gid : gid
													});
											});
										</script>
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
									<button type="button" type="button" onclick="saveForm('save');">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

