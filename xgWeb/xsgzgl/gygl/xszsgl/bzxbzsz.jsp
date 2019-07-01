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
	
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	
	<script	type="text/javascript">
	function plxgbl(){
		saveData('gyglnew_xszsgl.do?method=bzxbzsz&doType=save','');
	}
	
	</script>
</head>
<body>	
	<html:form action="/gyglnew_xszsgl" method="post">
		<input type="hidden" id="xhs" name="xhs" value="${xhs }"/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>床位信息修改</a>
			</p>
		</div>		
		--%>
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr>
					<%--<td colspan="2" align="center"><b>走读备注设置</b></td>
					--%><th colspan="2">
							<span>走读备注设置</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th width="25%" height="30px">走读备注<br/><font color="red">(限制500字)</font></th>
				<td>
					<html:textarea styleId="bzxbz" property="bzxbz" style="width:100%;height:100px" value="${bz}" onblur="chLeng(this,500);"></html:textarea>
				</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="2" height="30px"><div class="bz"></div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave" onclick="plxgbl();">保存</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			
			
			showAlert("保存成功",{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
			
		</script>
	</logic:present>
</body>
</html>
