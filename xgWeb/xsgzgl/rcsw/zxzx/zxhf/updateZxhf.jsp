<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script	type="text/javascript">
			function saveForm(type){
				var hfnr = jQuery("#hfnr").val();
				if (jQuery.trim(hfnr) == ""){
					showAlert("回复内容不能为空！");
					return false;
				}
				var url = "rcsw_zxzx_zxhfgl.do?method=updateZxhf&type="+type;
		      	ajaxSubFormWithFun("zxhfForm",url,function(data){
		    	 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }});
		  	}
			jQuery(function(){
			});
		</script>
	</head>
	<body>
		<html:form action="/rcsw_zxzx_zxhfgl" method="post" styleId="zxhfForm">
			<html:hidden property="hfid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="18%">咨询人学号</th>
							<td width="32%">
								${rs.zxr }
							</td>
							<th width="18%">咨询人姓名</th>
							<td width="32%">
								${rs.zxrxm }
							</td>
					    </tr>
						<tr>
							<th width="">咨询人所在<bean:message key="lable.xy" /></th>
							<td width="">
								${rs.xymc }
							</td>
							<th width="">咨询时间</th>
							<td width="">
								${rs.zxsj }
							</td>
					    </tr>
						<tr>
							<th width="">咨询版块</th>
							<td width="" colspan="3">
								${rs.bkmc }
							</td>
					    </tr>
						<tr>
							<th width="">咨询主题</th>
							<td width="" colspan="3">
								${rs.zxzt }
							</td>
					    </tr>
						<tr>
							<th width="">咨询内容
							</th>
							<td colspan="3">
								${rs.zxnr }
							</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>回复信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width=""><font class="red">*</font>回复内容<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='hfnr' style="width:98%" styleId="hfnr" rows='5' onblur="checkLen(this,500);"/>
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
										<button type="button" type="button" onclick="saveForm('save');">
											保存
										</button>
										<button type="button" type="button" onclick="iFClose();">
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

