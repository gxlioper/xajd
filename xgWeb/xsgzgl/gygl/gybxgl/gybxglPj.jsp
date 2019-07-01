<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript">
		</script>
	</head>
	<body >

		<html:form  action="/gyglnew_gybxgl" styleId="gyglnewGybxglForm" method="post">
		<input type="hidden" id="idList" name="idList" value="${idList}" />	
			
				<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>服务评价</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="35%">
									<span class="red">*</span>满意程度
								</th>
								<td>
									<html:select  property="mycd" styleId="mycd" style="width:155px">
										<html:options collection="mydList" labelProperty="mc" property="dm"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									评价<br/><font color="red">(限制字数200)<font color="red">
								</th>
								<td>
									<textarea name="pj" rows="3" style="width: 90%" cols="1" onblur="chLeng(this,200);"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="保存" onclick="saveData('gyglnew_gybxgl.do?method=gybxglStudentpj&doType=pj','mycd');">
											保 存
										</button>
										<button type="button" name="取消" onclick="Close();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
		</html:form>
		<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			showAlert(jQuery('#message').val(),{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
		</script>
		</logic:present>
	</body>
</html>
