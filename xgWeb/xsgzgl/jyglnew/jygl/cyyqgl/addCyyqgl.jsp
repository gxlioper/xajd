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
		<script type="text/javascript" src="xsgzgl/jyglnew/jygl/js/jygl.js"></script>
		<script	type="text/javascript">
			function saveForm(type){
				var xn = jQuery("#xn").val();
				if (jQuery.trim(xn) == ""){
					showAlert("请选择学年！");
					return false;
				}
				var xq = jQuery("#xq").val();
				if (jQuery.trim(xq) == ""){
					showAlert("请选择学期！");
					return false;
				}
				var gsmc = jQuery("#gsmc").val();
				if (jQuery.trim(gsmc) == ""){
					showAlert("公司名称不能为空！");
					return false;
				}
				var gslx = jQuery("#gslx").val();
				if (jQuery.trim(gslx) == ""){
					showAlert("请选择公司类型！");
					return false;
				}
				var rzsj = jQuery("#rzsj").val();
				if (jQuery.trim(rzsj) == ""){
					showAlert("请选择入驻时间！");
					return false;
				}
				var sshy = jQuery("#sshy").val();
				if (jQuery.trim(sshy) == ""){
					showAlert("请选择所属行业！");
					return false;
				}
				var sfzc = jQuery("#sfzc").val();
				if(sfzc == '1'){
					var zcgsmc = jQuery("#zcgsmc").val();
					if (jQuery.trim(zcgsmc) == ""){
						showAlert("注册公司名称不能为空！");
						return false;
					}
					var zcsj = jQuery("#zcsj").val();
					if (jQuery.trim(zcsj) == ""){
						showAlert("请选择注册时间！");
						return false;
					}
					var zczb = jQuery("#zczb").val();
					if (jQuery.trim(zczb) == ""){
						showAlert("注册资本不能为空！");
						return false;
					}
					var zcsshy = jQuery("#zcsshy").val();
					if (jQuery.trim(zcsshy) == ""){
						showAlert("请选择所属行业！");
						return false;
					}
				}
				var url = "jyglnew_jygl_cyyqglgl.do?method=addCyyqgl&type="+type;
		      	ajaxSubFormWithFun("cyyqglForm",url,function(data){
		    	 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }});
		  	}
		  	function changeZcgsxx(obj){
				if(obj.value == '1'){
					jQuery(".zcgsxx_class").show();
				}else{
					jQuery(".zcgsxx_class").hide();
				}
		  	}
			jQuery(function(){
			});
		</script>
	</head>
	<body>
		<html:form action="/jyglnew_jygl_cyyqglgl" method="post" styleId="cyyqglForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;height: 465px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>创业园区公司信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%"><span class="red">*</span>学年</th>
							<td width="30%">
								<html:select  property="xn" styleId="xn" style="width:150px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th width="20%"><span class="red">*</span>学期</th>
							<td width="30%">
								<html:select  property="xq" styleId="xq" style="width:150px">
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width=""><font class="red">*</font>公司名称</th>
							<td width="" colspan="3">
								<html:text property="gsmc" styleId="gsmc" maxlength="80" style="width:513px;" />
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>公司类型</th>
							<td width="">
								<html:select property="gslx" styleId="gslx" style="width:150px;">
									<html:options collection="gslxList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
							<th width=""><font class="red">*</font>入驻时间</th>
							<td width="">
								<html:text property="rzsj" styleId="rzsj" onclick="return showCalendar('rzsj','yyyy-MM-dd');" readonly="true" ></html:text>
							</td>
					    </tr>
						<tr>
							<th width="">团队人数</th>
							<td width="">
								<html:text property="tdrs" styleId="tdrs" maxlength="10" style="" onkeyup="checkInputData(this);"/>
							</td>
					    	<th width=""><font class="red">*</font>所属行业</th>
							<td width="" colspan="3">
								<html:select property="sshy" styleId="sshy" style="width:150px;">
									<html:options collection="sshyList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
					    </tr>
					    <tr>
					    	<th width=""><font class="red">*</font>是否注册</th>
							<td width="" colspan="3">
								<html:select property="sfzc" styleId="sfzc" style="width:150px;" onchange="changeZcgsxx(this);">
									<html:options collection="isnotList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
					    </tr>
					    <tr class="zcgsxx_class">
							<th width=""><font class="red">*</font>注册公司名称</th>
							<td width="" colspan="3">
								<html:text property="zcgsmc" styleId="zcgsmc" maxlength="80" style="width:513px;" />
							</td>
					    </tr>
						<tr class="zcgsxx_class">
							<th width=""><font class="red">*</font>注册时间</th>
							<td width="">
								<html:text property="zcsj" styleId="zcsj" onclick="return showCalendar('zcsj','yyyy-MM-dd');" readonly="true" ></html:text>
							</td>
							<th width=""><font class="red">*</font>注册资本</th>
							<td width="">
								<html:text property="zczb" styleId="zczb" maxlength="10" style="" onkeyup="checkInputData(this);"/>
							</td>
					    </tr>
						<tr class="zcgsxx_class">
							<th width="">就业人数</th>
							<td width="">
								<html:text property="jyrs" styleId="jyrs" maxlength="10" style="" onkeyup="checkInputData(this);"/>
							</td>
					    	<th width=""><font class="red">*</font>注册所属行业</th>
							<td width="" colspan="3">
								<html:select property="zcsshy" styleId="zcsshy" style="width:150px;">
									<html:options collection="sshyList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>创业团队成员信息</span>
							</th>
						</tr>
					</thead>
				 </table>
				
				<div style="height:200px;overflow-y:auto;">
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
						<thead>
							<tr>
								<td colspan="8">
								<button type="button" onclick="addXs();return false;" class="btn_01">增加学生</button>
								<button type="button" onclick="delXs();return false;" class="btn_01">删除学生</button>
								</td>
							</tr>
							<tr>
								<td width='5%'><input type="checkbox" name="selectAll" onclick="selectAllXs(this);" /></td>
								<td width='15%'>学号</td>
								<td width='12%'>姓名</td>
<%--								<td width='9%'>性别</td>--%>
<%--								<td width='9%'>年级</td>--%>
								<td width='17%'><bean:message key="lable.xb" /></td>
								<td width='17%'>专业</td>
								<td width='17%'>班级</td>
								<td width='11%'>手机号码</td>
								<td width='11%'>QQ号码</td>
							</tr>
						</thead>
						<tbody id="tbody_xs"></tbody>
					</table>
				</div>
				
				</div>
				<div>
					<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">"<span class="red">*</span>"为必填项</div>
									<div class="btn">
										<button type="button" type="button" onclick="saveForm('save');">
											保存
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

