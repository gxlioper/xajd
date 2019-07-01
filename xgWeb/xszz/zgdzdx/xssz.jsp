<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
<!-- 头文件 -->
<script type="text/javascript">
<!--
	function sjyz(){
		var qssj = $('qssj').value;
		var jssj = $('jssj').value;
		
		if (Number(qssj) > Number(jssj)){
			alert('起始时间不能晚于结束时间');
			return false;
		}
		saveUpdate('/xgxt/zgdzdx_xszz.do?method=xssz&doType=save','save_qssj-save_jssj');
	}
//-->
</script>
<body>
	<script type="text/javascript" src="js/xszz/xszzFunction.js"></script>
	<html:form action="/zgdzdx_xszz" method="post">
		<input type="hidden" name="pkValue" value="1"/>
		<input type="hidden" name="save_id" value="1"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>新生设置</span></th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          		<button type="button" name="保存"  id="buttonSave" onclick="sjyz();">保 存</button>
			          </div></td>
			      </tr>
			    </tfoot>
			  
				<tbody>
					<tr>
						<td align="right">
							<font color="red">*</font>起始时间：
						</td>
						<td>
							<html:text property="save_qssj" 
									   readonly="true" 
									   styleId="qssj" 
									   name="rs"
									   onclick="showCalendar(this.id,'y-mm-dd');" 
									   onblur='dateFormatChg(this)'></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>结束时间：
						</td>
						<td>
							<html:text property="save_jssj" 
									   readonly="true" 
									   styleId="jssj" 
									   name="rs"
									   onclick="showCalendar(this.id,'y-mm-dd');" 
									   onblur='dateFormatChg(this)'></html:text>
						</td>
					</tr>
				</tbody>
			</table>
	</div>
	</html:form>
	<logic:present name="result">
		<script>
			alert('${message}');
		</script>
	</logic:present>
</body>
</html>
