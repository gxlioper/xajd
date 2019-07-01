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
		<script type="text/javascript" src="xsgzgl/szdw/fdyrz/js/fdyrz_sq.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//为button注册事件
				//jQuery("#xzbj").load("szdw_fdyrz_sq.do?method=fdyrzsqbj");
				//jQuery("#bjlist").load("xsxx_bjgl.do?method=bjList");
				jQuery("#bjlist").hide();
				jQuery("#but_save").click(save);
				jQuery("#but_close").click(go_back);
				
			});
			//申请保存
			function updateFdyrzsq(obj){
				
				jQuery.ajaxSetup( {
					//禁止触发全局 AJAX 事件
					async : false
				});
				
				var sqid = jQuery("#sqid").val();
				var zjz = jQuery("#zjz").val();
				var sqly = jQuery("#sqly").val();
				var splc = jQuery("#splc").val();
				var sqdbgs = jQuery("#sqdbgs").val();
				var shzt = jQuery("#shzt").val();
				if(zjz==null || zjz ==""){
					alertInfo("请选择专兼职");
				}else if(sqly==null || sqly ==""){
					alertInfo("请填写申请理由！");
				}else if(sqdbgs==null || sqdbgs ==""){
					alertInfo("请输入申请班级数量");
				}else if(sqly.length>500){
					alertInfo("申请理由不能多于500字");
				}else if(zjz=="兼职" && sqdbgs>4){
					alertInfo("兼职最多可申请四个班级");
				}else{
					var url = "";
					if(obj == "submit"){
					  url="szdw_fdyrz_sq.do?method=fdyrzsqxg&type=submit";
					}else{
					  url="szdw_fdyrz_sq.do?method=fdyrzsqxg&type=update";
					}
					jQuery.ajaxSetup({
						 contentType:"application/x-www-form-urlencoded;charset=utf-8"
					});
					jQuery("#but_save").attr("disabled","true");
					
					// 非退回记录需要进行时间开关验证
					if(shzt!='3' && obj == "submit"){
						var tj = true;
						
						jQuery.post("szdw_fdyrz_sq.do?method=timeSwitch",{},function(data){
							var message = data["message"];
							if(message!="true"){
								alertInfo(message);
								tj = false;
							}
						},'json');

						if(!tj){
							return false;
						}
					};

					
					jQuery.ajax({
						   type: "POST",
						   url: url,
						   data: { zjz:zjz,sqly:sqly,splc:splc,sqdbgs:sqdbgs,sqid:sqid,shzt:shzt},
						   dataType: "json",
						   success: function(data){
						     if(data.success =="false"){
						    	 jQuery("#but_save").attr("disabled",false);
						     }else{
						    	 showAlertDivLayer(data["message"],{},{"clkFun":function(){
										if (parent.window){
											refershParent();
										}
									}});
						     }
						   }
						});
				}

				jQuery.ajaxSetup( {
					//重新开启触发全局 AJAX 事件
					async : true
				});
			}

			
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl.do?method=zjXxgl" method="post" styleId="demoForm">
		<input type="hidden" name="shzt" id="shzt" value="${model.shzt }" />
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
								<html:hidden property="sqid" name="model" styleId="sqid"/>
								<html:hidden property="splc" name="model" styleId="splc"/>
								<html:hidden property="shzt" name="model" styleId="shzt"/>
								<input type="hidden" id="xzbj_text">
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								职工号
							</th>
							<td width="34%">
							${rs.zgh }
							</td>
							<th width="16%">
								<font color="red">*</font>专兼职
							</th>
							<td width="34%" >
								<select name="zjz" id="zjz">
									<option value="专职" <logic:equal value="专职" property="zjz" name="rs">selected="selected"</logic:equal>>专职</option>
									<option value="兼职" <logic:equal value="兼职" property="zjz" name="rs">selected="selected"</logic:equal>>兼职</option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
							${rs.xm}
							</td>
							<th width="16%">
								性别
							</th>
							<td width="34%" >
								${rs.xbs }
							</td>
						</tr>
						<tr>
							<th width="16%">
								民族
							</th>
							<td width="34%">
							${rs.mzmc}
							</td>
							<th width="16%">
								政治面貌
							</th>
							<td width="34%" >
								${rs.zzmmmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								联系电话
							</th>
							<td width="34%">
							${rs.yddh}
							</td>
							<th width="16%">
								电子邮箱
							</th>
							<td width="34%" >
								${rs.dzyx}
							</td>
						</tr>
						<tr>
							<th>
								家庭地址
							</th>
							<td colspan="3">
							${rs.jtzz}
							</td>
						</tr>
						<tr>
							<th width="16%">
								思政工作时间
							</th>
							<td width="34%">
							${rs.szgzsj}
							</td>
							<th width="16%">
								到校工作时间
							</th>
							<td width="34%" >
								${rs.lxgzsj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								学历
							</th>
							<td width="34%">
							${rs.xl}
							</td>
							<th width="16%">
								专业
							</th>
							<td width="34%" >
								${rs.sxzy}
							</td>
						</tr>
						<tr>
							<th>
								毕业院校
							</th>
							<td colspan="3">
							${rs.byyx}
							</td>
						</tr>
						
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请带班信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_sqly">
						<tr>
							<th width="16%">
								<font color="red">*</font>申请带班个数
							</th>
							<td width="34%" colspan="3">
								<html:text property="sqdbgs" name="model" styleId="sqdbgs" onkeyup="checkInputNum(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>申请理由
								</br>
								<font color="red">限制500字</font>
							</th>
							<td width="34%" colspan="3">
								<textarea rows="5" style="width: 90%" id="sqly" name="sqly">${model.sqly}</textarea>
							</td>
						</tr>
						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button"  onclick="updateFdyrzsq('update')">
										保存草稿
									</button>
									&nbsp;&nbsp;
									<button type="button" type="button"  onclick="updateFdyrzsq('submit')">
										提交申请
									</button>
									&nbsp;&nbsp;
									<button type="button" type="button" id= "but_close" onclick="iFClose();">
										关闭
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

