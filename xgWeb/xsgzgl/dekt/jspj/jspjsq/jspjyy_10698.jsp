<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" defer="defer">
		function save(){
			var zgh = jQuery("#zgh").val();
			var zzshen = jQuery("#zzshen").val();
			var xsyyxx = jQuery("#xsyyxx").val();
			var url = "dekt_jspjglsq.do?method=jspjSave_10698";			
			var ids=jQuery("#zgh").val();
			jQuery.post(url,{"zgh":zgh, "zzshen":zzshen, "xsyyxx":xsyyxx},function(result){
			 	showAlert(result,{},{"clkFun":function(){
    				if (parent.window){
    					refershParent();
    				}
    			}});
			});					
		}
		</script>
		</head>
		<body>
		<html:form action="/dekt_jspjglsq" method="post">
		<input type="hidden" id="zgh" value="${zgh}"/>
		<input type="hidden" id="zzshen" value="${zzshen}"/>
	<div class="open_win01" id="yhfz">
	<table width="80%" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>学生预约信息</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th align="right" >
					预约信息
					<br />
					<font color="red"><B>(限200字)</B>
					</font>
				</th>
				<td align="left" colspan="3">
				<html:textarea property='xsyyxx' styleId="xsyyxx" style="width:500px" rows='5' value="${jshf.xsyyxx}"
					onblur="checkLen(this,200)" />
				</td>
			</tr>	
		<tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					"<span class="red">*</span>"为必填项
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="btn">
						<button type="button"  onclick="save()">
							保 存
						</button>
						<button type="button"  onclick="iFClose();return false;">
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