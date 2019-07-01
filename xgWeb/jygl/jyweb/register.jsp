<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
</head>
<body>
<html:form action="/jyweb">
<input type="hidden" name="dwlx" value="单位"/>
<div class="index_mainbody">
 <div class="index_topframe">
	<!-- logo 和 菜单 -->
    <jsp:include flush="true" page="/jygl/jyweb/title.jsp"></jsp:include>    
  </div>
    <div class="index_mainframe">
		<div class="type_mid">
        	<h3><span>用户注册</span></h3>
            <div class="module">
            	<h5>就业单位基本情况调查</h5>
				<table width="100%" border="0" class="tab_02">
                  <tr>
                    <th width="70px"><font class="red">*</font>用户名</th>
                    <td>
                     <div class="pos" style="z-index:3">
                        <html:text property="yhm" styleId="yhm" maxlength="15" 
                        onfocus="$('msg_div').className = 'msg_prompt'; $('div_yhm').className = 'hide'" 
                        onblur="$('msg_div').className = 'hide';checkYhm();"></html:text>
                     	 <div id="div_yhm" class="hide" >
		           			 <p id="yhm_error_info"></p>
		       			</div>
		       			<div id="msg_div" class="hide">
		                	<p>6-15个字符（字母、数字、下划线）</p>
		            	</div>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <th><font class="red">*</font>密码</th>
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
            </div>
            <div class="module">
       	    <h5>就业单位基本情况</h5>
				<table width="100%" border="0" class="tab_02">
                  <tr>
                    <th width="70px"><font class="red">*</font>单位名称</th>
                    <td>
                    	<div class="pos" style="z-index:1">
                    	<html:text property="dwmc" styleId="dwmc" onblur="isDwmcExists();"></html:text>
                    	<div id="div_dwmc" class="hide" >
		           			 <P id="dwmc_error_info"></p>
		       			</div>
                    	</div>
                    </td>
                  </tr>
                  <tr>
                    <th>企业法人</th>
                    <td><html:text property="qyfr"></html:text></td>
                  </tr>
                  <tr>
                    <th>联系电话</th>
                    <td><html:text property="lxdh" onblur="checkInputData(this);"></html:text></td>
                  </tr>
                </table>
            </div>
            
          <div class="btn">
          	<button id="savebutton" onclick="saveDataShowTips('/xgxt/jyweb.do?method=register&doType=save','yhm-mm-mm2-dwmc','正在处理数据请稍候!')">
          		提 交
          	</button>
          	<button onclick="refreshForm('/xgxt/index.do?userType=dw')">
          		登 录
          	</button>
         </div>
		</div>
    </div>
    </div>
    <div class="index_botframe">
      	<jsp:include flush="true" page="/jygl/jyweb/foot.jsp"></jsp:include>
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
