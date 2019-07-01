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
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwgl.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwglnewcomm.js"></script>
		<script language="javascript">
		function checkFzInfo(str){
			var date = "true";
			var parameter={};
			var url="qgzx_gwglnew_ajax.do?method=checkFzInfo&xn=";
			url+=$("xn").value.trim()
			parameter["pkValue"]=str;	
			parameter["xq"]=jQuery("#xq").val();
			var yxssz = jQuery("[name=yxssz]:checked").val();
			if(yxssz=='cq'){
				parameter["gwjssj"]="";
			}else{
				parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
			}
			parameter["yxssz"]=yxssz;
			parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
			jQuery.ajaxSetup({async:false});	
			jQuery.post(url,
				parameter,
				function(result){
					data = result;
				}
			);
			jQuery.ajaxSetup({async:true});
			return data;
		}

		//复制保存
		function gwxxDivSave(){
			if($("xn").value.trim()==""){
				showAlert("请选择您要复制的学年！");
				return false;
			}
			var yxsszLen = jQuery("[name=yxssz]:checked").length;
			if(yxsszLen==0){
				showAlert("请选择有效时设置！");
				return false;
			}
			if($("gwkssj").value==""){
				showAlert("岗位开始时间不能为空！");
		 		return false;
			}
			var yxssz = jQuery("[name=yxssz]:checked").val();
			if(yxssz=='xs' && $("gwjssj").value==""){
				showAlert("岗位结束时间不能为空！");
				return false;
			}
			var str = jQuery("#idList").val();
			var message = checkFzInfo(str);
			if(message!="true"){
				showAlert(message);
				return false;
			}
			var parameter={}
			var url="qgzx_gwglnew_ajax.do?method=gwxxFz&doType=fz&xn=";
			url+=$("xn").value.trim()
			parameter["pkValue"]=str;
			parameter["xq"]=jQuery("#xq").val();
			var yxssz = jQuery("[name=yxssz]:checked").val();
			if(yxssz=='cq'){
				parameter["gwjssj"]="";
			}else{
				parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
			}
			parameter["yxssz"]=yxssz;
			parameter["gwkssj"]=escape(jQuery("#gwkssj").val());							
			jQuery.ajaxSetup({async:false});	
			jQuery.post(url,
				parameter,
				function(result){
					showAlert(result,{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				}
			);
			
		}

		jQuery(function(){
			var num = jQuery("#num").val();
			var len = jQuery("#len").val();
			var str = jQuery("#str").val();

			var strName = jQuery("#qgzq").val() == "xq" ? "学期" : "学年";
			jQuery('#yxgw').html("当前共选中<font class='red'>"+num+"</font>个"+strName+"中的<font class='red'>"+len+"</font>个岗位");	
			changeYxssz();
			
		});
	
		</script>
	</head>
	<body >

		<html:form styleId="qgzxGwglForm" action="/qgzx_gwglnew_ajax"  method="post">
			<input type="hidden" id="num" name="num" value="${num}" />
			<input type="hidden" id="len" name="len" value="${len}" />
			<input type="hidden" id="str" name="str" value="${str}" />
			<input type="hidden" id="idList" name="idList" value="${idList}" />
			<input type="hidden" id="qgzq" name="qgzq" value="${cs.qgzq}" />
			
			
				<!-- 提示信息 -->
				<div class="prompt" id="promptTs">
					<h3>
						<span>提示：</span>
					</h3>
					<p>
						仅能将选中岗位复制到非岗位所属学年中
					</p>
					<a class="close" title="隐藏"
					   onclick="this.parentNode.style.display='none'"></a>
				</div>
				<!-- 提示信息 end-->	
				<div style="tab;overflow-x:hidden;overflow-y:auto;height:315px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>复制岗位信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="40%">
								已选岗位
							</th>
							<td width="60%">
								<font id="yxgw"></font>
							</td>
						</tr>
						<tr>
							<th width="40%">
								<span class="red">*</span>
								<logic:equal name="cs" property="qgzq" value="xn">
									目标学年
								</logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
									目标学年学期
								</logic:equal>
							</th>
							<td width="60%">
								<logic:equal name="cs" property="qgzq" value="xn">
									<html:select property="xn" styleId="xn" style="width:120px" >
										<html:options labelProperty="xn" property="xn" collection="xnList"/>
									</html:select>
									<input type="hidden" id="xq" name="xq" value="" />
								</logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
									<html:select property="xn" styleId="xn" style="width:100px" >
										<html:options labelProperty="xn" property="xn" collection="xnList"/>
									</html:select>
									<html:select property="xq" styleId="xq" style="width:100px" >
										<html:option value=""></html:option>
										<html:options labelProperty="xqmc" property="xqdm" collection="xqList"/>
									</html:select>
								</logic:equal>
							</td>
						</tr>
						
						<tr>
							<th><span class="red">*</span>有效时设置</th>
							<td>
							   <logic:present name="yxsszList">
									<logic:iterate id="yxsszMap" name="yxsszList" >
										<html:radio property="yxssz" onclick="changeYxssz();" value="${yxsszMap.dm}">${yxsszMap.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>岗位开始时间</th>
							<td>
								<html:text  property="gwkssj" styleId="gwkssj" size="10" readonly="true"></html:text>
							</td>
						</tr>
						<tr id="gwjssj_tr">
							<th id="gwjssj_th"></th>
							<td id="gwjssj_td">
								<html:text  property="gwjssj" styleId="gwjssj" size="10" readonly="true"></html:text>
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
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" name="保存" onclick="gwxxDivSave();">
										保 存
									</button>
									<button type="button" name="关闭" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>				
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
