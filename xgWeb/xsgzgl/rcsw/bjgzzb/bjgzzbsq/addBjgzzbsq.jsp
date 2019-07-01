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
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script	type="text/javascript">
			function saveForm(type){
				var bjdm = jQuery("#bjdm").val();
				if (jQuery.trim(bjdm) == ""){
					showAlert("请选择一个班级！");
					return false;
				}
				var sdrs = jQuery("#sdrs").val();
				if (jQuery.trim(sdrs) == ""){
					showAlert("实到人数不能为空！");
					return false;
				}	
				var qjrs = jQuery("#qjrs").val();
				if (jQuery.trim(qjrs) == ""){
					showAlert("请假人数不能为空！");
					return false;
				}	
				var wdrs = jQuery("#wdrs").val();
				if (jQuery.trim(wdrs) == ""){
					showAlert("无故未到学生人数不能为空！");
					return false;
				}	
				var ydrs = jQuery("#ydrs").val();
				if(parseInt(ydrs) != (parseInt(sdrs) + parseInt(qjrs) + parseInt(wdrs))){
					showAlert("应到人数=实到人数+请假人数+无故未到学生人数！");
					return false;
				}
				var zynr = jQuery("#zynr").val();
				if (jQuery.trim(zynr) == ""){
					showAlert("本周对学生进行讲评的主要内容不能为空！");
					return false;
				}	
				if (zynr.length > 500){
					showAlert("本周对学生进行讲评的主要内容最多500字！");
					return false;
				}	
				var zywt = jQuery("#zywt").val();
				if (jQuery.trim(zywt) == ""){
					showAlert("本周学生存在的主要问题不能为空！");
					return false;
				}	
				if (zywt.length > 500){
					showAlert("本周学生存在的主要问题最多500字！");
					return false;
				}	
				var jjdc = jQuery("#jjdc").val();
				if (jQuery.trim(jjdc) == ""){
					showAlert("您认为较合理的解决对策不能为空！");
					return false;
				}	
				if (jjdc.length > 500){
					showAlert("您认为较合理的解决对策最多500字！");
					return false;
				}	
				var url = "rcsw_xsgzzb_xsgzzbsqgl.do?method=addXsgzzbsq&gzzblx=bj&type="+type;
		      	ajaxSubFormWithFun("xsgzzbsqForm",url,function(data){
		    	 if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }});
		  	}
		  	// 根据周次显示周次起止日期
			function changeZcksjsrq(){
				var zcObj = jQuery("#zc").find("option:selected");
				var html = zcObj.attr("ksrq") + " 至 " + zcObj.attr("jsrq");
				jQuery("#zcksjsrq_td").html(html);
				jQuery("#zcksjsrq_hidden").val(html);
			}
			function checkRs(){
				var ydrs = jQuery("#ydrs").val();
				if (jQuery.trim(ydrs) == ""){
					return false;
				}
				var sdrs = jQuery("#sdrs").val();
				if (jQuery.trim(sdrs) == ""){
					return false;
				}	
				var qjrs = jQuery("#qjrs").val();
				if (jQuery.trim(qjrs) == ""){
					return false;
				}	
				if(parseInt(ydrs) < (parseInt(sdrs) + parseInt(qjrs))){
					showAlert("实到人数+请假人数不能大于应到人数！");
					return false;
				}
				var wdrs = jQuery("#wdrs").val();
				if (jQuery.trim(wdrs) == ""){
					jQuery("#wdrs").val(parseInt(ydrs) - parseInt(sdrs) - parseInt(qjrs));
				}else{
					if(parseInt(ydrs) != (parseInt(sdrs) + parseInt(qjrs) + parseInt(wdrs))){
						showAlert("应到人数=实到人数+请假人数+无故未到学生人数！");
						return false;
					}
				}
			}
			jQuery(function(){
				changeZcksjsrq();
			});
		</script>
	</head>
	<body>
		<html:form action="/rcsw_xsgzzb_xsgzzbsqgl" method="post" styleId="xsgzzbsqForm">
			<html:hidden property="xn"  styleId="xn" />
			<html:hidden property="xq"  styleId="xq" />
			<input type="hidden" name="zcksjsrq" id="zcksjsrq_hidden" value="${xsgzzbsqMap.zcksjsrq}"/>
			<% String userStatus = (String)request.getAttribute("userStatus"); %>
			<div style='tab;width:100%;height:418px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>周报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="18%"><span class="red">*</span>班级</th>
							<td width="32%" colspan="3">
								<input type="text" id="bjmc" value="" style="width:200px;" readonly="readonly" title=""/>
								<input type="hidden" name="bjdm" id="bjdm" value=""/>
								<button class="btn_01" type="button" onclick="showDialog('请选择一个班级',800,500,'rcsw_xsgzzb_xsgzzbsqgl.do?method=bjManage');return false;">选择</button>
								<span id="bj_span"></span>
							</td>
					    </tr>

						<logic:equal name="xxdm" value="10704">
							<tr>
								<th>
									带班辅导员
								</th>
								<td id="dbfdy" colspan="3">
								</td>
							</tr>
						</logic:equal>

						<tr>
							<th width="18%">学年</th>
							<td width="32%">
								${xsgzzbsqMap.xn}
							</td>
							<th width="18%">学期</th>
							<td width="32%">
								${xsgzzbsqMap.xqmc}
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>周次</th>
							<td>
								<select name="zc" id="zc" value="${xsgzzbsqMap.zc}" onchange="changeZcksjsrq();">
									<logic:iterate id="zc" name="zcList">
									<option value="${zc.dm}" ksrq="${zc.ksrq}" jsrq="${zc.jsrq}" <logic:equal name="zc" property="dm" value="${xsgzzbsqMap.zc}">selected="selected"</logic:equal>>${zc.mc}</option>
									</logic:iterate>
								</select>
							</td>
							<th>周次起止日期</th>
							<td id="zcksjsrq_td">
								
							</td>
					    </tr>
					    <tr>
							<th>
								应到人数
								<input type="hidden" name="ydrs" id="ydrs" value=""/>
							</th>
							<td id="ydrs_td">
							</td>
							<th>
								<span class="red">*</span>实到人数
							</th>
							<td>
								<html:text property="sdrs" styleId="sdrs" maxlength="5" style="" onkeyup="checkInputData(this);" onblur="checkRs();"></html:text>
							</td>
			      		</tr>
					    <tr>
							<th>
								<span class="red">*</span>请假人数
							</th>
							<td>
								<html:text property="qjrs" styleId="qjrs" maxlength="5" style="" onkeyup="checkInputData(this);" onblur="checkRs();"></html:text>
							</td>
							<th>
								<span class="red">*</span>无故未到学生人数
							</th>
							<td>
								<html:text property="wdrs" styleId="wdrs" maxlength="5" style="" onkeyup="checkInputData(this);" onblur="checkRs();"></html:text>
							</td>
			      		</tr>
					    <tr>
							<th>
								<span class="red">*</span>本周对学生进行<br />讲评的主要内容
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
							    <html:textarea property='zynr' style="width:98%" styleId="zynr" rows='5' />
							</td>
			      		</tr>
					    <tr>
							<th>
								<span class="red">*</span>本周学生存在的<br />主要问题
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
							    <html:textarea property='zywt' style="width:98%" styleId="zywt" rows='5' />
							</td>
			      		</tr>
					    <tr>
							<th>
								<span class="red">*</span>您认为较合理的<br />解决对策
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
							    <html:textarea property='jjdc' style="width:98%" styleId="jjdc" rows='5' />
							</td>
			      		</tr>
					    <tr>
					    	<th align="right">
								附件
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'filepath',
	
											eid : 'filepath_f'
											});
									});
								</script>
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
		</html:form>
	</body>
</html>

