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
			function saveForm(){

				var sydkshlc = jQuery("#sydkshlc").val(); 
				var sydkxn =   jQuery("#sydkxn").val();
				if (sydkshlc==null ||sydkshlc == ""||sydkxn==null
						||sydkxn==""){
					showAlertDivLayer("请将必填项填写完整！");
					return false;
				}
				
				var id = jQuery("#id").val();
				var url = id == "" ? "zxdkCssz.do?method=save" : "zxdkCssz.do?method=update";
				ajaxSubFormWithFun("zxdkCsszForm",url,function(data){
					showAlertDivLayer(data["message"]);
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
						<th width="40%"><span class="red">*</span>生源地贷款开关</th>
						<td>
							<logic:present name="onOff">
								<logic:iterate id="o" name="onOff">
									<html:radio property="sydkg" value="${o.dm}">${o.mc}</html:radio>
								</logic:iterate>
							</logic:present>
						</td>
					</tr>
					<tr>
						<th>
							生源地贷款起始时间
						</th>
						<td>
							<html:text  property="sydkssj" styleId="sydkssj"
										onfocus="showCalendar('sydkssj','y-mm-dd',true,'sydjssj');" 
										readonly="true"></html:text>
								至
							<html:text  property="sydjssj" styleId="sydjssj"
										onfocus="showCalendar('sydjssj','y-mm-dd',false,'sydkssj');" 
								 		readonly="true"></html:text>
									
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>审核流程
						</th>
						<td>
							<html:select property="sydkshlc" styleId="sydkshlc">
								<html:option value=""></html:option>
								<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
							</html:select>
						</td>
					</tr>
									<tr>
						<th>
							<span class="red">*</span>生源地贷款学年
						</th>
						<td>
							<html:select property="sydkxn" styleId="sydkxn">
								<html:option value=""></html:option>
								<html:options collection="xnlist" property="xn" labelProperty="xn"/>
							</html:select>
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
