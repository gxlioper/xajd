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
<input type="hidden" name="dwlx" value="��λ"/>
<div class="index_mainbody">
 <div class="index_topframe">
	<!-- logo �� �˵� -->
    <jsp:include flush="true" page="/jygl/jyweb/title.jsp"></jsp:include>    
  </div>
    <div class="index_mainframe">
		<div class="type_mid">
        	<h3><span>�û�ע��</span></h3>
            <div class="module">
            	<h5>��ҵ��λ�����������</h5>
				<table width="100%" border="0" class="tab_02">
                  <tr>
                    <th width="70px"><font class="red">*</font>�û���</th>
                    <td>
                     <div class="pos" style="z-index:3">
                        <html:text property="yhm" styleId="yhm" maxlength="15" 
                        onfocus="$('msg_div').className = 'msg_prompt'; $('div_yhm').className = 'hide'" 
                        onblur="$('msg_div').className = 'hide';checkYhm();"></html:text>
                     	 <div id="div_yhm" class="hide" >
		           			 <p id="yhm_error_info"></p>
		       			</div>
		       			<div id="msg_div" class="hide">
		                	<p>6-15���ַ�����ĸ�����֡��»��ߣ�</p>
		            	</div>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <th><font class="red">*</font>����</th>
                    <td>
                    <div class="pos" style="z-index:2">
                    	<html:password property="mm" styleId="mm" maxlength="15" 
                    	onfocus="$('msg_div1').className = 'msg_prompt'; $('div_mm').className = 'hide'" 
                    	onblur="$('msg_div1').className = 'hide';checkMm();"></html:password>
                   		<div id="div_mm" class="hide" >
		           			 <p>�����ʽ����ȷ��</p>
		       			</div>
		       			<div id="msg_div1" class="hide">
		                	<p>6-15���ַ�����ĸ�����֡��»��ߣ�</p>
		            	</div>
		      			
		       		</div>
                   	</td>
                   	
                  </tr>
                  <tr>
                    <th><font class="red">*</font>ȷ������</th>
                    <td>
                    <div class="pos" style="z-index:1">
                    	<html:password property="mm2" styleId="mm2"  maxlength="15" onblur="checkMm2();"/>
                    	<div id="div_mm2" class="hide" >
		           			 <p>�������벻һ�£�</p>
		       			</div>
                    </div>
                    </td>
                  </tr>
                </table>
            </div>
            <div class="module">
       	    <h5>��ҵ��λ�������</h5>
				<table width="100%" border="0" class="tab_02">
                  <tr>
                    <th width="70px"><font class="red">*</font>��λ����</th>
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
                    <th>��ҵ����</th>
                    <td><html:text property="qyfr"></html:text></td>
                  </tr>
                  <tr>
                    <th>��ϵ�绰</th>
                    <td><html:text property="lxdh" onblur="checkInputData(this);"></html:text></td>
                  </tr>
                </table>
            </div>
            
          <div class="btn">
          	<button id="savebutton" onclick="saveDataShowTips('/xgxt/jyweb.do?method=register&doType=save','yhm-mm-mm2-dwmc','���ڴ����������Ժ�!')">
          		�� ��
          	</button>
          	<button onclick="refreshForm('/xgxt/index.do?userType=dw')">
          		�� ¼
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
