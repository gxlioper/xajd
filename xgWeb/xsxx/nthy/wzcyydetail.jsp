<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/checkUtils.js"></script>
</head>

<body>
	<html:form action="/xszc" method="post">
		<input type="hidden" id="xhstr" name="xhstr" value="${xhstr }"/>

		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
		    	<tr>
		        	<th colspan="2"><span>未注册原因填写</span></th>
		        </tr>
		    </thead>
			<tbody>
			<tr>
				<th>
					<span class="red">*</span>未注册原因
					<br/>
					<font color="red">(限制字数1000个)</font>
				</th>
				<td>
					<html:textarea property="wzcyy" cols="40" rows="8" onblur="checkLeng(this,1000)"></html:textarea>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="2"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
					<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('xszc.do?method=wzcyydetail&doType=save&zt=未注册','wzcyy-wzcyy');">
						保&nbsp;&nbsp;存
					</button>
					<button type="button" class="button2" id="buttonSave" onclick="Close();return false;">
						关&nbsp;&nbsp;闭
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
	  </div>
		
	</html:form>
	<logic:present name="result">
		<logic:equal value="true" name="result">
			<script language="javascript">
         	alert("操作成功！");
         	if (window.dialogArguments) {
				window.close();
				window.dialogArguments.document.getElementById('search_go').click();
			}
         	</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
       	 	 alert("操作失败！");
       	 	if (window.dialogArguments) {
				window.close();
				window.dialogArguments.document.getElementById('search_go').click();
			}
         	</script>
		</logic:equal>
	</logic:present>
</body>
</html>
