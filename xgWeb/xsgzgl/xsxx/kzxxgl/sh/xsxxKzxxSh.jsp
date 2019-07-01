<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true" ></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/extend/zfsoft-dataExtend.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery('#ExtendDataContent').dataExtend( {
					"mid"      : jQuery('[name="exendDateModuleId"]').val(),
					"dataId"   : jQuery('[name="sqid"]').val(),
					"dataType" : "2",
					"xh"       : jQuery('[name="xh"]').val(),
					"bdpzid"   : "xsxxgl",
					"naviBar"  : true,
					"mode"     : "view",
					"actionBar": false
				});
				
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#sqid").val()+"&tt="+new Date().getTime());
				
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+jQuery("#splc").val()+"&shid="+jQuery("#shid").val());
				
			});
			
		/**
		 * 提交审核
		 * @return
		 */
		function submitSh(){
			if (jQuery("#shyj").val() == ""){
				showAlertDivLayer("请填写审核意见！");
				return false;
			}
			showConfirmDivLayer("您确定审核该申请吗？",{"okFun":function(){
				var url = "xsxx_kzxxshgl.do?method=shAction";
				ajaxSubFormWithFun("xsxxKzxxShForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}});
		}
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/xsxx_kzxxshgl" method="post" styleId="xsxxKzxxShForm">
		<html:hidden property="sqid"  styleId="sqid"/>
		<html:hidden property="exendDateModuleId" />
		<html:hidden property="xh" />
		
		<html:hidden property="sqsj" />
		<html:hidden property="splc" styleId="splc"/>
		<html:hidden property="shzt" />
		<html:hidden property="xtgwid" />
		<html:hidden property="shid" styleId="shid"/>
			<div style="tab;height:510px;overflow-x:hidden;overflow-y:auto;">
				<div id="ExtendDataContent"></div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">* </font>审核结果
							</th>
							<td colspan="3" id="shjgSpan">
								
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								审核意见
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zdzm&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px" rows="5" 
											   onblur="checkLen(this,200);" styleId="shyj"
								></html:textarea>
							</td>
						</tr>
					</tbody>
					</table>
					</div>
					<div>
					<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" id="btqd" onclick="submitSh();">
										确定
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

