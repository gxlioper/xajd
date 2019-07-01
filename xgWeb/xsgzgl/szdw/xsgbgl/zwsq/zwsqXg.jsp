<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/zwsq.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//为button注册事件
				//jQuery("#but_save").click(function(){save("zwSq")});
				//jQuery("#tbody_zwxx").load("szdw_xsgb_zwwh.do?method=zwView");
				jQuery("#tbody_zwxx").load("szdw_xsgb_zwwh.do?method=zwView&zwid=${model.zwid}");
				var xh = jQuery("#xh").val();
				if(xh=="" || xh == undefined){
					jQuery("#xh").val('${xh}');
				}
				
			});

			function saveZwsq(obj){
				var oldzwid = jQuery("#oldzwid").val();
				var zwid = jQuery("#zwid").val();
				if(yanzhengXg()){
					if(oldzwid == oldzwid){
						//验证成功后才能进行保存
						var url = "";
						if(obj == "submit"){
							url = "szdw_zwsq.do?method=zwsqXg&type=submit";
						}else{
							url = "szdw_zwsq.do?method=zwsqXg&type=update";
						}
						
						ajaxSubFormWithFun("zwsqForm",url,function(data){
							 showAlertDivLayer(data["message"],{},{"clkFun":function(){
									if (parent.window){
										refershParent();
									}
									//iFClose();
								}});
						});
					}else{
						jQuery.post("szdw_zwsq.do?method=yzZwsq",{zwid:jQuery("#zwid").val(),xh:jQuery("#xh").val()},function(data){
							if(data.message!="true"){
								alertInfo(data.message);
							}else{
								var url = "";
								if(obj == "submit"){
									url = "szdw_zwsq.do?method=zwsqXg&type=submit";
								}else{
									url = "szdw_zwsq.do?method=zwsqXg&type=update";
								}
								//验证成功后才能进行保存
								ajaxSubFormWithFun("zwsqForm",url,function(data){
									 showAlertDivLayer(data["message"],{},{"clkFun":function(){
											if (parent.window){
												refershParent();
											}
											//iFClose();
										}});
								});
							}
						},'json');
					}
				}
			}

			function yanzhengXg(){
				//var xh = jQuery("#xh").val();
				var zwid = jQuery("#zwid").val();
				var sqly = jQuery("#sqly").val();
				if(zwid=="" || zwid == undefined){
					alertInfo("请选择申请的职务");
				}else if(sqly.length>=200){
					alertInfo("申请理由不能超过200字");
				}else{
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/szdw_zwsq" method="post" styleId="zwsqForm">
		<html:hidden property="sqid"  styleId="sqid"/>
		<html:hidden property="xh"  styleId="xh"/>
		<html:hidden property="splc"  styleId="splc"/>
		<html:hidden property="shzt"/>
		<input type="hidden" id="oldzwid" value="${oldzwid}"/>
			<div style='tab;width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>学生干部职务申请</span> 
							</th>
						</tr>
					</thead>
					<tbody id="tbody_zwxx">
						
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								申请理由
								<br /><font color="red">(限200字)</font>
							</th>
							<td  colspan="4">
								<%--
								<textarea id="sqly" name ="sqly" style="width: 500px;height: 100px;" ></textarea>
								--%>
								<html:textarea  property="sqly" style="width:98%" styleId="sqly" rows='5' onblur="checkLen(this,200);" > </html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveZwsq('save');return false;" >
										保存草稿
									</button>
									<button type="button" type="button" onclick="saveZwsq('submit');return false;" >
										提交申请
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

