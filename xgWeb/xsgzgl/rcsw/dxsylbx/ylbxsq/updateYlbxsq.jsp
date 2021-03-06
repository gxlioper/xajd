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
		
		<script	type="text/javascript">

		function saveForm(obj){

			var czqebzIds = "";
			var cbzkIds = "";
			var checkCzqebz = "";
			var checkCbzk = "";
			var isopen = jQuery("#isopen").val();
			var shzt = jQuery("shzt").val();

			if(3!=shzt&&"submit"==obj&&"true"!=isopen){
				showAlert("当前未开放申请，请联系管理员！");
				return false;
			}
			
			jQuery("input[name=czqebz]:checked").each(function(){
				czqebzIds += jQuery(this).val()+ "," ;
			});						
			jQuery("input[name=cbzk]:checked").each(function(){
				cbzkIds += jQuery(this).val() + ",";
			});
			if(cbzkIds.length>0){
				cbzkIds = cbzkIds.substring(0, cbzkIds.length-1);
				checkCbzk ="1";
			}
			if(czqebzIds.length>0){
				czqebzIds = czqebzIds.substring(0, czqebzIds.length-1);
				checkCzqebz = "1";
			}

			var qtcbzkval = jQuery("#qtcbzkval").val();
			if(jQuery("input[name=qtcbzk]").prop("checked") == false){
				qtcbzkval = "";
			}
			
			//先验证学号
			//var xh = jQuery("#xh").val();
			var sqly = jQuery("#sqly").val();
			var zjsyrxm = jQuery("#zjsyrxm").val();
			var zjh = jQuery("#zjh").val();
			//var cbsj = jQuery("#cbsj").val();
			
			/*if (jQuery.trim(xh) == ""){
				showAlert("请先选择学生！");
				return false;
			}*/	
			if (jQuery.trim(checkCzqebz) == ""){
				showAlert("请先选择补助人员身份！");
				return false;
			}
			/*
			if (jQuery.trim(zjsyrxm) == ""){
				showAlert("请先输入证件所有人姓名！");
				return false;
			}

			if (jQuery.trim(zjh) == ""){
				showAlert("请先输入证件号！");
				return false;
			}*/

			if (jQuery.trim(checkCbzk) == ""&&undefined==jQuery("input[name=qtcbzk]:checked").val()){
				showAlert("请先选择参保状况！");
				return false;
			}

			/*if (jQuery.trim(cbsj) == ""){
				showAlert("请先选择参保时间！");
				return false;
			}*/
			if("0"==jQuery("input[name=qtcbzk]:checked").val()&&jQuery.trim(qtcbzkval)==""){
				showAlert("请录入参保状况当中的其它信息");
				return false;
			}
			
			if (jQuery.trim(sqly) == ""){
				showAlert("申请理由不能为空！");
				return false;
			} 

			
			var url = "";
			if(obj == 'update'){
				 url = "rcsw_dxsylbx_ylbxsqgl.do?method=updateYlbxsq&type=update&czqebzdm="+czqebzIds+"&cbzkdm="+cbzkIds+"&qtcbzkval="+qtcbzkval;
			}
			if(obj == 'submit'){
				 url = "rcsw_dxsylbx_ylbxsqgl.do?method=updateYlbxsq&type=submit&czqebzdm="+czqebzIds+"&cbzkdm="+cbzkIds+"&qtcbzkval="+qtcbzkval;
			}

			var isopen = jQuery("#isopen").val();
			var shzt = jQuery("#shzt").val();

			
			if(isopen==null||isopen==''){
				showAlertDivLayer('基础设置未初始化，请联系管理员！');
				return false;
			}
			if ("false" == isopen && obj=="submit"&&'3' != shzt){
				showAlertDivLayer("当前未开放申请，请联系管理员！");
				return false;
			}

	        ajaxSubFormWithFun("ylbxsqForm",url,function(data){
	    		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
	    	 });
	    	 
		  }

		  

		jQuery(function(){
			
			var cbzkdms = jQuery("#cbzkdm").val();
			var czqebzdms = jQuery("#czqebzdm").val();
			var qtcbzkvalVal = jQuery("#qtcbzkval").val();
			var cbzkdmss;
			if(cbzkdms.length>0){
				cbzkdmss = cbzkdms.split(",");
				for ( var i = 0; i < cbzkdmss.length; i++) {
					jQuery("input[name=cbzk]").each(function(){
						if(jQuery(this).val() == cbzkdmss[i]){
							jQuery(this).attr("checked","checked");
						}
					});
				}
			}

			var czqebzdmss;
			if(czqebzdms.length>0){
				czqebzdmss = czqebzdms.split(",");
				for ( var i = 0; i < czqebzdmss.length; i++) {
					jQuery("input[name=czqebz]").each(function(){
						if(jQuery(this).val() == czqebzdmss[i]){
							jQuery(this).attr("checked","checked");
						}
					});
				}
			}

			if(qtcbzkvalVal != ""){
				jQuery("#qtcbzk").attr("checked","checked");
				jQuery("#qtcbzkvaled").show();
			}
			
		});

		function showInput(obj){
			if(obj.checked){
				jQuery("#qtcbzkval").show();
			}else{
				jQuery("#qtcbzkval").hide();
			}
		}
		</script>
		
	</head>
	<body>
		
		<html:form action="/rcsw_dxsylbx_ylbxsqgl" method="post" styleId="ylbxsqForm">
			<html:hidden property="ylsqid"  styleId="ylsqid" />
			<html:hidden property="czqebzdm"  styleId="czqebzdm"/>
			<html:hidden property="cbzkdm"  styleId="cbzkdm" />
			<html:hidden property="shzt" styleId="shzt" />
			<html:hidden property="splc" />
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
			<div style='tab;width:100%;height:480px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/dxsylbx/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>医疗保险申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><span class="red">*</span>学年</th>
							<td>
								<html:hidden property="xn" />
								<bean:write name="ylbxsqForm" property="xn"/>
							</td>
							<th><span class="red">*</span>学期</th>
							<td>
								<html:hidden property="xqmc"/>
								<bean:write name="ylbxsqForm" property="xqmc"/>
							</td>
					    </tr>
					
					    <tr>
							<th ><span class="red">*</span>补助类型</th>
							<td colspan="3" >
							<logic:notEmpty name="czqebzryList">
							<logic:iterate name="czqebzryList" id="s"  indexId="i" >
								<label><input type="checkbox" name="czqebz" value="${s.czqebzdm}"/>${s.czqebzmc}</label>
								<br/>
							</logic:iterate>
							</logic:notEmpty>
							</td>
					    </tr>

					    <tr>
							<th>证件所有人姓名</th>
							<td>
								<html:text property="zjsyrxm"  styleId="zjsyrxm" maxlength="10"></html:text>
							</td>
							
							<th>证件号</th>
							<td>
								<html:text property="zjh" styleId="zjh" maxlength="25"></html:text>
							</td>
					    </tr>
					    
					     <tr>
							<th><span class="red">*</span>参保状况</th>
							<td colspan="3" >
								<logic:notEmpty name="cbzkList">
								<logic:iterate name="cbzkList" id="b"  indexId="i" >
									<label><input type="checkbox" name="cbzk" value="${b.cbzkdm}"/>${b.cbzkmc}</label>
									<br/>
								</logic:iterate>
								</logic:notEmpty>
								<label><input type="checkbox" name="qtcbzk" value="0" onclick="showInput(this);" id="qtcbzk"/>其它</label>
								<logic:notEmpty name="qtcbzkval" >
								   <html:text property="qtcbzkval" style="width:100px"  styleId="qtcbzkval" ></html:text>
								</logic:notEmpty>
								
								<logic:empty name="qtcbzkval" >
									<input type="text" name ="qtcbzkval" style="width:100px;display:none" id="qtcbzkval" />
								</logic:empty>
								
							</td>
					    </tr>
					    <%--
					    <tr>
							<th><span class="red">*</span>参保时间</th>
							<td colspan="3">
								<html:text style="cursor:hand;" styleId="cbsj"
									property="cbsj"
									onclick="return showCalendar('cbsj','y-mm-dd');"
									readonly="readonly" />
							</td>
					    </tr>
					    --%>
					    <tr>
							<th>
								<span class="red">*</span>申请理由
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
							    <html:textarea property='sqly' style="width:98%" styleId="sqly" rows='5' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
			      		
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('update');">
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
		</html:form>
		
	</body>
</html>

