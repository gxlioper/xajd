<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/station/station.js"></script>
		<script type="text/javascript" src="js/station/city_name.js"></script>
		<script type="text/javascript">
	jQuery(document).ready(function() {
		changeSqkg();
	});

	//更新岗位申请
	function changeSqkg() {
		var sqkg = jQuery("[name=sqkg]:checked").val();
		if ("1" == sqkg) {
			jQuery("table select,input:not(input:radio[name=sqkg])").attr(
					"disabled", false);
		} else if ("0" == sqkg) {
			jQuery("table select,input:not(input:radio[name=sqkg])").attr(
					"disabled", "disabled");
		}
	}

	function saveCssz() {
		var sqkglength = jQuery("[name=sqkg]:checked").length;
		if (sqkglength == 0) {
			showAlertDivLayer("请设置申请开关!");
			return false;
		}
		var splc = jQuery("#splc").val();
		var sqkg = jQuery("[name=sqkg]:checked").val();
		if ("1" == sqkg && (splc == "" || splc == null)) {
			showAlertDivLayer("请选择审核流程!");
			return false;
		}
		var url = "sspycssz.do?method=saveCssz";
		ajaxSubFormWithFun("sspycsszform", url, function(data) {
			showAlertDivLayer(data["message"]);
		});
	}
</script>
	</head>
	<body >
		<html:form action="/sspycssz" method="post" styleId="sspycsszform" >
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>时间设置</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="40%"><span class="red">*</span>申请开关</th>
						<td>
						   
						   <logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<html:radio property="sqkg" onclick="changeSqkg();" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
						</td>
					</tr>
					<tr>
						<th>申请开放时间</th>
						<td>
							<html:text  property="sqkssj" styleId="sqkssj"   size="10"
									onclick="return showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text  property="sqjssj" styleId="sqjssj"   size="10"
									onclick="return showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>审核流程</th>
						<td>
							<html:select property="splc" styleId="splc" disabled="false" >
							<html:options collection="shlcList" property="splc"
								labelProperty="lcxx" />
						</html:select>
						</td>
					</tr>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">	
			          <logic:equal name="writeAble" value="yes">		            
						<button type="button" class="button2" onclick="saveCssz();return false;" id="btn_save">
							保 存
						</button>
						</logic:equal>
						<button type="button" class="button2" onclick="window.close();return false;" style="width:80px;display:none" 
							id="buttonClose">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
				</tbody>
			</table>
			</div>
		</html:form>
	</body>
</html>
