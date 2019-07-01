<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("[name='xdkg']").change(function(){
					if(this.value=="1"){
						jQuery("#xdshlctr").show();
					}else{
						jQuery("#xdshlctr").hide();
					}
				})
			})
			function saveForm(){

				var xydshlc = jQuery("#xydshlc").val();
				if (xydshlc == ""){
					showAlertDivLayer("请将必填项填写完整！");
					return false;
				}
				var xdbz = jQuery("[name='xdkg']:checked").val();
				if(xdbz == "1"){
					if(jQuery("#xydxdshlc").val() == "" || jQuery("#xydxdshlc").val() == null ){
						showAlertDivLayer("请将必填项填写完整！");
						return false;
					}
				}				
				var id = jQuery("#id").val();
				var url = id == "" ? "zxdkCssz.do?method=save" : "zxdkCssz.do?method=update";
				ajaxSubFormWithFun("zxdkCsszForm",url,function(data){
					/*bug修改，如果修改成功应该刷新页面*/
					if(data["message"]=="保存成功！"){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
			    			 location.href = "zxdk_gjdk_cssz.do";
							}});
			    	 }else{
			    		 showAlertDivLayer(data["message"]);
			    		}
				});
			}
		</script>
	</head>
  	<body >
	<html:form action="/zxdkCssz" method="post" styleId="zxdkCsszForm">
			<html:hidden property="id" styleId="id"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>参数设置</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="40%"><span class="red">*</span>国家助学贷款申请开关</th>
						<td>
							<logic:present name="onOff">
								<logic:iterate id="o" name="onOff">
									<html:radio property="xydkg" value="${o.dm}">${o.mc}</html:radio>
								</logic:iterate>
							</logic:present>
						</td>
					</tr>
					<tr>
						<th>
							国家助学贷款起始时间
						</th>
						<td>
							<html:text  property="xydkssj" styleId="xydkssj"
										onfocus="showCalendar('xydkssj','y-mm-dd',true,'xydjssj');" 
										readonly="true"></html:text>
								至
								<html:text  property="xydjssj" styleId="xydjssj"
											onfocus="showCalendar('xydjssj','y-mm-dd',false,'xydkssj');" 
									 		readonly="true"></html:text>
									
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>校园地贷款审核流程
						</th>
						<td>
							<html:select property="xydshlc" styleId="xydshlc">
								<html:option value=""></html:option>
								<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th width="40%"><span class="red">*</span>国家助学贷款模式</th>
						<td>
							<html:radio property="xdkg" value="1"></html:radio>一次申请多次续贷
							<html:radio property="xdkg" value="0"></html:radio>每年申请
						</td>
					</tr>
					<logic:equal name="zxdkCsszForm" property="xdkg" value="1">
						<tr id="xdshlctr">
							<th>
								<span class="red">*</span>续贷审核流程
							</th>
							<td>
								<html:select property="xydxdshlc" styleId="xydxdshlc">
									<html:option value=""></html:option>
									<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
								</html:select>
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="zxdkCsszForm" property="xdkg" value="0">
						<tr id="xdshlctr" style="display:none">
							<th>
								<span class="red">*</span>续贷审核流程
							</th>
							<td>
								<html:select property="xydxdshlc" styleId="xydxdshlc">
									<html:option value=""></html:option>
									<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
								</html:select>
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th>
							国家助学贷款续贷起始时间
						</th>
						<td>
							<html:text  property="xdkssj" styleId="xdkssj"
										onfocus="showCalendar('xdkssj','y-mm-dd',true,'xdjssj');" 
										readonly="true"></html:text>
								至
								<html:text  property="xdjssj" styleId="xdjssj"
											onfocus="showCalendar('xdjssj','y-mm-dd',false,'xdkssj');" 
									 		readonly="true"></html:text>
									
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">		
			          	<logic:equal name="writeAble" value="yes">		            
							<button type="button" class="button" onclick="saveForm();" >
								保 存
							</button>
						</logic:equal>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>
