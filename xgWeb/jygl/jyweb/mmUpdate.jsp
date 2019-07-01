<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type='text/javascript' src="js/jygl/register.js"></script>
</head>
<body>
<html:form action="/jyweb.do?method=register">
<input type="hidden" name="yhm" value="${jyweb_userName }"/>
    <div class="index_mainframe">
		<div class="type_mid">
        	<h3><span>用户密码修改</span></h3>
            <div class="module">
            	<h5>修改密码</h5>
				<table width="100%" border="0" class="tab_02">
                 <tr>
                    <th><font class="red">*</font>旧密码</th>
                    <td>
                    <div class="pos" style="z-index:2">
                    	<html:password property="oldmm" styleId="oldmm" maxlength="15"></html:password>
		       		</div>
                   	</td>
                  </tr>
                  <tr>
                    <th><font class="red">*</font>新密码</th>
                    <td>
                    <div class="pos" style="z-index:2">
                    	<html:password property="mm" styleId="mm" maxlength="15"
                    	 onfocus="$('msg_div1').className = 'msg_prompt'; $('div_mm').className = 'hide'" 
                    	 onblur="$('msg_div1').className = 'hide';checkMm();"></html:password>
                   		<div id="div_mm" class="hide" >
		           			 <p>密码格式不正确！</p>
		       			</div>
		       			<div id="msg_div1" class="hide">
		                	<p>6-15个字符（字母、数字、下划线）</p>
		            	</div>
		       		</div>
                   	</td>
                  </tr>
                  <tr>
                    <th><font class="red">*</font>确认密码</th>
                    <td>
                    <div class="pos" style="z-index:1">
                    	<html:password property="mm2" styleId="mm2"  maxlength="15" onblur="checkMm2();"/>
                    	<div id="div_mm2" class="hide" >
		           			 <p>两次密码不一致！</p>
		       			</div>
                    </div>
                    </td>
                  </tr>
                  </table>
          <div class="btn"><button id="savebutton" onclick="saveDataShowTips('jywebUseCheckSession.do?method=mmUpdate&doType=save','mm-mm2','正在处理数据请稍候!')">提 交</button>
         </div>
		</div>
    </div>
    </div>
	<logic:present name="message">
            	<input type="hidden" value="${message }" id="msg"/>
            	<script language="javascript">
            		alert($('msg').value);
            	</script>
    </logic:present>
</html:form>
</body>
</html>
