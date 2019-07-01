<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript">
			function saveForm(){	  		
				var length=jQuery("[name=ry]:checked").length;
	
				if(length < 1){
					showAlert("请选择教师类型！");
					return false;
				}
				
				showDialog("请选择编班的教师类型", 800, 500, "szdw_szbb.do?method=jsbb&quantity="+length);
	
				iFClose();	
							
			}
		</script>
	</head>
	<body>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>编班教师类型</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="35%">
								<font color="red">*</font>编班教师类型
							</th>
							<td width="60%">
								<input type="radio" value="bzr" name="ry" />班主任
								<input type="radio" value="fdy" name="ry" />辅导员
							</td>
						</tr>
					</tbody>
				<tfoot>
						<tr>
							<td align="center" colspan="2">							
			        			<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveForm();return false;" id="buttonSave">
										确定
									</button>
									<button type="button" onclick="iFClose();">
										取消
									</button>	
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
	</body>
</html>

