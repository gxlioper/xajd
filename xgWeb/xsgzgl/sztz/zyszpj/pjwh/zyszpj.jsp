<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
			<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/comm/ymPrompt.js" ></script>
	<script type='text/javascript' src="js/comm/message.js"></script>
	<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script>
				function save(){
					var hpxx=jQuery("textarea[name='hpxx']").val();
					var spxx=jQuery("textarea[name='spxx']").val();
					if(hpxx==""||spxx==""){
						return alertError("请将带<font color='red'>*</font>的项目填写完整！");
					}
					//检测长度
					function checkLength(obj,len){
						var str=obj.value;
					     	if(str.replace(/[^\u0000-\u00ff]/g, "**").length > len){	         
					     		alertError("此项不能大于"+len+"个字符！");
					      		 return false;
					   		 }
					}
				 	jQuery("#form").ajaxSubmit({
						url:"zyszpjwh.do?method=zyszpj&type=save",
						type:"post",
						dataType:"json",
						success:function(data){
					 		 if(data["message"]=="保存成功！"){
					    		 showAlert(data["message"],{},{"clkFun":function(){
					    				if (parent.window){
					    					refershParent();
					    				}
					    			}});
					    	 }else{
					    		 showAlert(data["message"]);
					    		 
					    	 }
						},
						contentType:"application/x-www-form-urlencoded;charset=utf-8"
					});	
				}
			</script>
	</head>
	<html:form action="/zyszpjwh" method="post"
		 styleId="form"> 
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" width="15%">
								<font color="red">*</font>学号：
							</td>
							<td align="left" width="40%">
								<html:hidden property="zyszid" value="${data.zyszid}"/>
								<input type="hidden" name="pjlx" value="${pjlx}"/>
								${stuInfo.xh}
							</td>
							<td align="right" width="15%">
								姓名：
							</td>
							<td align="left">
								${stuInfo.xm}
							</td>
						</tr>
						<tr>
							<td align="right">
								年级：
							</td>
							<td align="left">
								${stuInfo.nj}
							</td>
							<td align="right">
								<bean:message key="lable.xb" />：
							</td>
							<td align="left">
								${stuInfo.xymc}
							</td>
						</tr>
						<tr>
							<td align="right">
								专业：
							</td>
							<td align="left">
								${stuInfo.zymc}
							</td>
							<td align="right">
								班级：
							</td>
							<td align="left">
								${stuInfo.bjmc}
							</td>
						</tr>
						<tr>
							<td align="right" width="10%">
								学年：
							</td>
							<td align="left">
								${data.xn}
							</td>
							<td align="right">
								学期：
							</td>
							<td align="left">
								${data.xq}
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>职业素质活动过程 </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								序号
							</td>
							<td>
								子项目
							</td>
							<td>
								时间
							</td>
							<td>
								地点
							</td>
							<td>
								活动内容
							</td>
							<td>
								参与及获奖情况
							</td>
						</tr>
							<tbody id="tbody_add">
							<logic:notEmpty name="zxm">
							<logic:iterate name="zxm" id="s"  indexId="i">
								<tr>
									<td>
										${i+1}
									</td>
									<td width="120px">
										${s.xmlbmc}
									</td>
									<td width="120px">
										${s.sj}
									</td>
									<td width="120px">
										${s.dd}
									</td>
									<td width="120px">
									${s.hdnr}
									</td>
									<td width="120px">
									${s.cyjhjqk}
									</td>
								</tr>
							</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>自评信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="15%" height="120px">内容</td>
							<td width="85%">${data.zpxx}</td>
						</tr>
					</tbody>
				</table>
				<!-- 当前是否可以进行修改  1代表可以增加、修改-->
				<logic:equal name="sfkypj" value="1">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>互评信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="15%" height="120px"><font color='red'>*</font>内容<br /><font color="red">(限200字)</font></td>
								<td width="85%"><html:textarea property="hpxx" onblur="checkLen(this,200)" rows="8" style="width:100%"/></td>
							</tr>
						</tbody>
					</table>
				</logic:equal>
				<logic:notEqual name="sfkypj" value="1">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>互评信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="15%" height="120px">内容</td>
								<td width="85%">${data.hpxx}</td>
							</tr>
							<tr>
								<td width="15%" height="20px">评价人</td>
								<td width="85%">${data.hpr}</td>
							</tr>
						</tbody>
					</table>
				</logic:notEqual>
				<logic:equal name="pjlx" value="ls">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>师评信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="15%" height="120px"><font color='red'>*</font>内容<br /><font color="red">(限200字)</font></td>
								<td width="85%"><html:textarea property="spxx" rows="8" onblur="checkLen(this,200)" style="width:100%"/></td>
							</tr>
								<tr>
								<td width="15%" height="120px">评价等级</td>
								<td width="85%">
								<html:select property="pjdj" styleId="pjdj"><html:options collection="pjdjlist" property="pjdj" labelProperty="pjdjmc" />
								</html:select>
							</td>
							</tr>
						</tbody>
					</table>
				</logic:equal>
			<logic:notEqual name="sfkypj" value="1">
						<table width="100%" border="0" class="formlist">
							<tfoot>
							<tr>
								<td colspan="4">
								<logic:notEqual name="pjlx" value="ls">
									<div class="bz">
									<logic:equal name="sfkypj" value="-3">
										<span class="red">已被互评不能再进行互评</span>
									</logic:equal>
									<logic:equal name="sfkypj" value="0">
										<span class="red">不能对自己互评</span>
									</logic:equal>
									<logic:equal name="sfkypj" value="-1">
										<span class="red">已经被师评不能进行互评操作</span>
									</logic:equal>
									</div>
									</logic:notEqual>
									<div class="btn">
										<logic:equal name="pjlx" value="ls">
										<button type="button"  onclick="save();return false;" id="buttonSave">
											保 存
										</button>
										</logic:equal>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button"  onclick="iFClose();" id="buttonClose">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
			</logic:notEqual>
			<logic:equal name="sfkypj" value="1">
				<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"  onclick="save();return false;" id="buttonSave">
									保 存
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  onclick="window.parent.ymPrompt.close();return false;" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</logic:equal>
	</html:form>
</html>