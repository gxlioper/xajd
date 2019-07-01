<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script>
		function save(){
			if($('kssj').value > $('jssj').value){
				alert('结束时间不能早于开始时间!');
				return false;
			}
			var doType = document.getElementById('doType').value;
			if(doType == "modi"){
				doType = "update";
			}else {
				doType = "save";
			}
			saveUpdate('/xgxt/qgzxZgdzdx.do?method=xxtsUpdate&doType='+doType,'save_kssj-save_jssj-save_tsnr');
		}
	</script>
</head>
<body>	
	<html:form action="/qgzxZgdzdx" method="post">
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<input type="hidden" name="doType" id="doType" value="${doType}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
			<table class="formlist" width="100%">
				<thead>
			    	<tr>
			        	<th colspan="4"><span>消息提示设置</span></th>
			        </tr>
			    </thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>开始时间</th>
					<td>
						<html:text property="save_kssj" styleId="kssj" 
						onclick="return showCalendar('kssj','y-mm-dd');"
						onblur="dateFormatChg(this)" readonly="true" value="${rs.kssj }"/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>结束时间</th>
					<td>
						<html:text property="save_jssj" styleId="jssj" 
						onclick="return showCalendar('jssj','y-mm-dd');"
						onblur="dateFormatChg(this)" readonly="true" value="${rs.jssj }"/>
					</td>
				</tr>
				<tr>
					<th><font color="red">*<限50字></font>提示信息</th>
					<td><html:textarea property="save_tsnr" rows="5" cols="40" onblur="checkLen(this,'50')" value="${rs.tsnr}"></html:textarea>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button type="button" class="button2" id="buttonSave" onclick="save();return false;">
							保&nbsp;&nbsp;存
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
	</html:form>
	<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
</html>