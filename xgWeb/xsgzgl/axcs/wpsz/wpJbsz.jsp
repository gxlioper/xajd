<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/axcs/wpsz/js/wpsz.js"></script>
		<script language="javascript">
		function saveForm(){	  
			  var splc=jQuery("#splc").val();
			  if(jQuery.trim(splc)==""){
				  showAlert("请将必填项填写完整！");
					return false;
			  }
		     var url = "axcsWpsz.do?method=wpJbsz&type=save";
		      ajaxSubFormWithFun("WpszForm",url,function(data){
		    	 if(data["message"]=="保存成功！"){
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
		</script>
	</head>
	<body>
		<html:form action="/axcsWpsz" method="post" styleId="WpszForm">
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					当前设置项目为：
					<font color="red">${xmmc} &nbsp; <span id="spztTip"
						style="display: none;"> 当前项目已有学生申请，部分项不允许修改 </span> </font>
					只有在申请开关开启状态，并且申请时间有效范围内进行设置后，才为“已设置”状态，其余均为“未设置”状态
				</p>
				<a class="close" title="隐藏"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
			<html:hidden property="xmdm" styleId="xmdm" />
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>时间设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">

						<tr>
							<th width="120px">
								<font color="red">*</font>申请开关
							</th>
							<td colspan="3">
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList">
										<label>
											<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>
								开放申请时间
							</th>
							<td>
								<html:text property="sqkssj" styleId="sqkssj"
									onfocus="showCalendar('sqkssj','ymmdd');" />
								&nbsp;至
								<html:text property="sqjssj" styleId="sqjssj"
									onfocus="showCalendar('sqjssj','ymmdd');" />
							</td>
						</tr>
						<tr>
							<th width="120px">
								<font color="red">*</font>审核开关
							</th>
							<td colspan="3">
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList">
										<label>
											<html:radio property="shkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>
								开放审核时间
							</th>
							<td>
								<html:text property="shkssj" styleId="shkssj"
									onfocus="showCalendar('shkssj','ymmdd');" />
								&nbsp;至
								<html:text property="shjssj" styleId="shjssj"
									onfocus="showCalendar('shjssj','ymmdd');" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>审核流程
							</th>
							<td>
								<html:select property="splc" styleId="splc">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>

	<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
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

