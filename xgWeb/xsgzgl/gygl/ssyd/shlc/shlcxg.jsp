<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/shlc/js/shlc.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" defer="defer">
			function saveshlc(){
				
				flg = true;
				var selects = jQuery("select");
				
				jQuery(selects).each(function(){
					if(!jQuery(this).val()){
						flg = false;
						return;
					}
				});
				  
				if(!flg){
					showAlertDivLayer("请将必填项填写完整！");
					return false;
				}
				  
				
			    var url = "shlc.do?method=save";
		        ajaxSubFormWithFun("shlcForm",url,function(data){
		    	    showAlertDivLayer(data["message"]);
				});
			}
			function change(){
				var ssydlx = jQuery("#ssydlx").val();
				var url = "shlc.do?method=getSplcid";
				jQuery.ajax({
					type:'post',
					url:url,
					//dataType:'json',
					contentType:"application/x-www-form-urlencoded; charset=UTF-8",
					data:{ssydlx:ssydlx},
					success:function(data){
						jQuery("#splcid").val(data);
					}
					
				});
			}
		</script>		
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/shlc.do?method=list&type=query" styleId="shlcForm">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<html:hidden property="id"/>
		<html:hidden property="zsfxs" name="zsfxs" styleId="zsfxs" value="${zsfxs}"/>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>宿舍异动审核流程配置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="30%">
								<span class="red">*</span>退宿审核流程
							</th>
							<td> 
								<html:select property="tssplcid" styleId="tssplcid" disabled="false">
									<html:option value="">---请选择---</html:option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="30%">
								<span class="red">*</span>宿舍调整审核流程
							</th>
							<td> 
								<html:select property="sstzsplcid" styleId="sstzsplcid" disabled="false">
									<html:option value="">---请选择---</html:option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<logic:equal name="xxdm" value="12686">
						<tr>
							<th width="30%">
								<span class="red">*</span>实习留宿审核流程
							</th>
							<td> 
								<html:select property="sxlssplcid" styleId="sxlssplcid" disabled="false">
									<html:option value="">---请选择---</html:option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th width="30%">
								<span class="red">*</span>入住审核流程
							</th>
							<td> 
								<html:select property="rzsplcid" styleId="rzsplcid" disabled="false">
									<html:option value="">---请选择---</html:option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
					</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							
								<div class="btn">
									<button type="button"  onclick="saveshlc();return false;" id="buttonSave" <logic:notEqual value="yes" name="writeAble">disabled</logic:notEqual>>
										保 存
									</button>
								</div>
							
						</td>
					</tr>
				</tfoot>
				</table>
		</html:form>
	</body>
</html>
