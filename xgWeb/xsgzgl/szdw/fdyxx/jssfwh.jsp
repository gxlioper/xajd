<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var zghs = "${zgharr}";
			var jssfs = "${jssfs}";
			if(zghs.split(",").length == 1){
				var jssfarr = jssfs.split(",");
				for(var i = 0; i < jssfarr.length; i++){
					jQuery("input[name='jssf'][value='"+jssfarr[i]+"']").attr("checked",true);
				}
				
			}
		});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/szdw_fdyxx" method="post" styleId="fdyxxModel" onsubmit="return false;">
		   <input type="hidden" name="zghs" value="${zghs}"/>
		   <input type="hidden" id="jssfs" name="jssfs" value="${jssfs}"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th><font color="red">*</font>��ʦ���</th>
							<td colspan="3">
								<label><input type="checkbox" id="jssf" name="jssf" value="רְ����Ա">רְ����Ա</label><br/>
								<label><input type="checkbox" id="jssf" name="jssf" value="��ְ����Ա">��ְ����Ա</label><br/>
								<label><input type="checkbox" id="jssf" name="jssf" value="�������Ա">�������Ա</label><br/>
								<label><input type="checkbox" id="jssf" name="jssf" value="���ڱ���">���ڱ���</label><br/>
								<label><input type="checkbox" id="jssf" name="jssf" value="������">������</label>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="savejssfPlwh();">
										��    ��
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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