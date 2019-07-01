<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	    <script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsz/js/xmsz.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			jQuery("#xn").change(function(){
				if(jQuery("#xn").val()<'${currxn}'){
					showAlert("学年不得小于当前学年"+'${currxn}'+"！",{},{"clkFun":function(){
						jQuery("#xn").val('${currxn}');
						return false;
				    }});
				}
				if(jQuery("#xn").val()=='${currxn}'){
					jQuery("#xq").val('${currxq}');
				}
			});
			jQuery("#xq").change(function(){
				if(jQuery("#xn").val()=='${currxn}'){
					showAlert("学期不得小于当前学期"+'${currxq}'+"！",{},{"clkFun":function(){
						jQuery("#xq").val('${currxq}');
						return false;
				    }});
				}
			});
		});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/rcsw_txhd_xmszCx" method="post" styleId="TxhdXmszForm" onsubmit="return false;">
			
			<html:hidden property="xmdm" styleId="stid" value="${xmdm}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>社团项目</span>
							</th>
						</tr>
					</thead>
					<tbody>
						
						<tr>
							<th width="40%">
								<font color="red">*</font>项目名称
							</th>
							<td width="60%">
								<html:text property="xmmc" value="复制${xmmc}" styleId="xmmc" maxlength="50" style="width:96%"></html:text>
							
							</td>
						</tr>
						<tr>
							<th width="40%">
							<font color="red">*</font>学年
							</th>
							<td width="60%">
								<html:select  property="xn" styleId="xn" value="${currxn}" style="width:98%">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="40%">
							<font color="red">*</font>学期
							</th>
							<td width="60%">
								<html:select  property="xq" styleId="xq" value="${currxq}" style="width:98%">
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
					</tbody>
				 </table>
				</div>
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveCopyXmjg();">
										保存
									</button>
									<button type="button" onclick="iFClose();">
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

