<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		function displayQzsj(){
			var pjkg = jQuery(":radio:checked").val();
			//开启
			if (pjkg == 1){
				jQuery("#qzsjTr").show();
			} else {
				//关闭
				jQuery("#qzsjTr").hide();
			}
		}
		
		//保存参数设置
		function saveCssz(zdKey,zdValue){
			if (zdValue != null){
				jQuery.post("xpj_cssz.do?method=saveCssz",{"zdKey":zdKey,"zdValue":zdValue},function(data){
					if(data["message"] != null){
						alert(data["message"]);
					}
				});
			}
		}
		
		jQuery(function(){
			displayQzsj();
			
			jQuery(":radio").bind("click",function(){
				saveCssz("pjkg",jQuery(this).val());
			});
			
			jQuery("#pjzq").bind("change",function(){
				jQuery.ajaxSetup({async:false});
				
				if (jQuery(this).val() == ""){
					return ;
				}
				
				saveCssz("pjzq",jQuery(this).val());
				var frameWindow = new ForceWindow("zcxmFrame");
				frameWindow.open("xpj_zcxm.do?method=viewZcxm");
			});
		});
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		<!-- 提示信息 -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					1、当前页面用户操作数据会即时保存生效;<br/>
					2、综测项目的最大分和最小分用于综测分维护输入限制。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/xpj_cssz" method="post" styleId="csszForm">
			
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					
					<thead>
						<tr>
							<th colspan="4">
								<span>基本设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="16%">评奖开关</th>
							<td width="34%">
								<logic:iterate id="k" name="pjkgList">
									<lable><html:radio property="pjkg" value="${k.dm }" onclick="displayQzsj();"></html:radio>${k.mc }</lable>
								</logic:iterate>
							</td>
							<th width="16%">评奖周期</th>
							<td width="34%">
								<html:select property="pjzq" styleId="pjzq">
									<html:option value=""></html:option>
									<html:options collection="pjzqList" property="zqdm" labelProperty="zqmc"/>
								</html:select>
							</td>
					    </tr>
					    <tr id="qzsjTr">
							<th>起止时间</th>
							<td colspan="3">
								<html:text  property="kssj" styleId="kssj"
											onfocus="showCalendar('kssj','yyyy-MM-dd HH:mm',true,'jssj');" 
											onchange="saveCssz('kssj',this.value)"
											readonly="true"
											></html:text>
								至
								<html:text  property="jssj" styleId="jssj"
											onfocus="showCalendar('jssj','yyyy-MM-dd HH:mm',false,'kssj');" 
									 		onchange="saveCssz('jssj',this.value)"
									 		readonly="true"
									 		></html:text>
							</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>综测项目</span>
								<div style="margin-left: 500px;">
								<logic:equal value="1" name="szyf">
								<button class="btn_common"type="button"
												onclick="showDialog('设置月份',550,180,'xpj_zcxm.do?method=showYf');"
										>
											设置月份
										</button>
								</logic:equal>
								<logic:equal value="1" name="zcxxb">
									<logic:equal value="1" property="zcxmjb" name="xpjCsszModel">
										<button class="btn_common"  type="button"
												onclick="showDialog('各年级详细比例',750,380,'xpj_zcxm.do?method=showXxbl');"
										>
											各年级详细比例
										</button>
									</logic:equal>
									<logic:equal value="2" property="zcxmjb" name="xpjCsszModel">
										<button class="btn_common"  type="button"
												onclick="showDialog('各院系详细比例',750,480,'xpj_zcxm.do?method=showXxbl');"
										>
											各院系详细比例
										</button>
									</logic:equal>
								</logic:equal>
								</div>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<iframe src="xpj_zcxm.do?method=viewZcxm"
										width="100%" id="zcxmFrame" name="zcxmFrame"
										height="550px"
										frameborder="0" marginwidth="0" marginheight="0"
										scrolling="auto">
								></iframe>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

