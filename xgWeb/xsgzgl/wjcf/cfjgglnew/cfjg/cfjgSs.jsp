<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	
		<%@ include file="/syscommon/head.ini"%> 
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/uicomm.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script language="javascript" defer="defer">

		function ssjgsave() {
			var sswh =jQuery("#sswh").val();//申诉问号
			var sssj = jQuery("#sssj").val();//申诉时间
			var ssjg = jQuery("#ssjg").val();//申诉结果
			var cflbmc = jQuery("#cflbdm").find("option:selected").text();//处分类别代码
			var ssyj = jQuery("#ssyj").val();//申诉结果
			if("block"==document.getElementById('cfggw').style.display){
				if(null==sssj||""==sssj||null==sswh||""==sswh||null==ssjg||""==ssjg||null==cflbmc||""==cflbmc){
					alertError("请将带*的项目填写完整！");
					return false;
				}
			}else{
				if(null==sssj||""==sssj||null==sswh||""==sswh||null==ssjg||""==ssjg){
					alertError("请将带*的项目填写完整！");
					return false;
				}
			}
			var api = frameElement.api,W = api.opener;
			var pkValue=W.jQuery("#dataTable").getSeletIds();
			var url = "wjcf_cfjg.do?method=saveWjcfss";
			  jQuery.post(url,{cfid:pkValue,sswh:sswh,sssj:sssj,ssjg:ssjg,cflbmc:cflbmc,ssyj:ssyj},function(data){
				 if(data["message"]=="操作成功！"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		 
			    	 }
			},'json');
		}

		
		jQuery(function(){
			var ssjg =jQuery("#ssjg").val();
			if (ssjg == '更改处分') {
				jQuery("#cfggw").show();
			}else{
				jQuery("#cfggw").hide();
			}
			jQuery("#cflbdm").find("option:selected").text("${wjcfss.cflbmc}");//处分类别代码
			jQuery("#showCfqx").html("${cfqx}");
		});

		function discfgg() {
			var ssjg = jQuery("#ssjg").val();
			if (ssjg =='更改处分') {
				jQuery("#cfggw").show();
			} else {
				jQuery("#cfggw").hide();
			}
		}
		
		function showCfqxFlag(cflbdm){
			if(cflbdm==""){return false;}
			var cfqx = "";
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqx").html(data["message"]);
			},'json');
		}
		
		</script>
	</head>
	<body >
		<html:form action="/wjcf_cfjg" method="post" styleId="cfjgForm" onsubmit="return false;" >
			<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
			<div class="open_win01">
				
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="">
								<th>
									<span class="red">*</span>申诉文号
								</th>
								<td>
									<html:text property="sswh" styleId="sswh" maxlength="30" value="${wjcfss.sswh}"></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									<span class="red">*</span>申诉时间
								</th>
								<td>
									<html:text property="sssj" styleId="sssj"  value="${wjcfss.sssj}" style="cursor:hand;"
								onclick="return showCalendar('sssj','y-mm-dd','','','100','50');" ></html:text>
								</td>
							</tr>
							<tr id="" style="">
								<th>
									<span class="red">*</span>申诉结果
								</th>
								<td>
									<html:select property="ssjg" styleId="ssjg" value="${wjcfss.ssjg}" style="width:140px" onchange="discfgg()">
										<html:option value=""></html:option>
										<html:option value="维持原处分">维持原处分</html:option>
										<html:option value="撤销处分">撤销处分</html:option>
										<html:option value="更改处分">更改处分</html:option>
									</html:select>
								</td> 
							</tr>
							<tr id="cfggw">
								<th>
									<span class="red">*</span>处分更改为
								</th>
								<td>
									<html:select property="cflbdm" styleId="cflbdm" value="${wjcfss.cflbmc}" style="width:100px"  onchange="showCfqxFlag(this.value);">
										<html:options collection="cflbsList" property="dm" labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
								</td> 
								
							</tr>
							<tr id="">
								<th>
									申诉意见<br/>
									<font color="red">(限制200字)</font>
								</th>
								<td>
									<html:textarea property="ssyj" styleId="ssyj" rows="5" value="${wjcfss.ssyj}" style="width:290px" onblur="checkLen(this,200)"></html:textarea>
								</td> 
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button"  name="保存" onclick="ssjgsave();">
											保 存
										</button>
										<button type="button"  name="取消" onclick="Close();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
			</div>
			
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>
