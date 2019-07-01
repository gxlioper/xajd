<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
		function dc(){
			var ids = "lddm-yf";
			if(check(ids) == false){
				showAlert("请将带<font color='red'>*</font>的项目填写完整");
				return false;
			}
			
			var url = "gyglnew_fscx.do?method=gywsfdc";

			var flg = true;
			
			var lddm = jQuery("#lddm").val();
			var yf = jQuery("#yf").val();
			var nd = jQuery("#nd").val();
			
			jQuery.ajaxSetup({async:false});
			jQuery.post("gyglnew_fscx.do?method=sfjc", {lddm:lddm,yf:yf,nd:nd}, function(data) {
				if(!data){
					flg = false;					
				}	      		
			}, 'json');
			jQuery.ajaxSetup({async:true});
			
			if(!flg){
				showAlert("你所选择的楼栋在该月无卫生分检查记录或卫生分检查记录为提交！");
				return false;
			}
			
			document.forms[0].action = url;
			document.forms[0].submit();
		}

		function ldChange(obj){
			var option = jQuery(obj).find("option:checked");
			var mc = jQuery(option).text();
			jQuery("#ldmc").val(mc);
		}

		function check(ids){
			var id=ids.split("-");
			for(var i=0;i<id.length;i++){
				var lddm=jQuery("#"+id[i]).val();
				if(lddm==null||""==lddm){
					return false;
				}
			}
			return true;
		}
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_fscx" styleId="fscxForm" method="post">
		<input type="hidden" id="nd" name="nd" value="${nd}" /> 
		<input type="hidden" id="ldmc" name="ldmc" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>公寓月卫生分统计导出</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>
								楼栋
							</th>
							<td style="width:70%" colspan="3">
								<html:select property="lddm" styleId="lddm" onchange="ldChange(this);return false;">
									<option value="">----选择----</option>
									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>
								月份
							</th>
							<td style="width:70%" colspan="3">
								<html:select property="yf" styleId="yf">
									<option value="">---选择---</option>
									<html:options collection="yfList" property="yf" labelProperty="yf" />
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<font color="red">*</font>"为必填项
								</div>
								<div class="btn">
									<button class="button2" id="btn_bc" type="button" onclick="dc()">
										导出
									</button>
									<button class="button2" type="button" onclick="Close()">
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
