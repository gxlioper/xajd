<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript">
			function sendInfo() {
				var text = curr_row.getElementsByTagName('input')[0].value;
	
				window.dialogArguments.document.forms[0].action = 'jywebUseCheckSession.do?method=workAdd&save_gsmc='+text;
				window.dialogArguments.document.forms[0].submit();
				window.close();
				
			}
		</script>	
	</head>
<body>
<html:form action="/jyweb" method="post">

<!-- �๦�ܲ�����һ -->
<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><span>��Ƹ��Ϣ����-��ҵ��ѯ</span>
			</p>
		</div>
<div class="toolbox">
  <!-- �������� -->
  <div class="searchtab">
    <table width="100%" border="0">
      <tfoot>
        <tr>
          <td colspan="6">
            <div class="btn">
              <button class="btn_cx" id="search_go" 
              	onclick="allNotEmpThenGo('/xgxt/jyweb.do?method=moreCompany&doType=query')">
              	�� ѯ
              </button>
              <button class="btn_cz" id="btn_cz" 
              	onclick="searchReset();return false;">
              	�� ��
              </button>
            </div>
          </td>
        </tr>
      </tfoot>
      <tbody>
      	<tr>
      		<th>��λ����</th>
      		<td><html:text property="querylike_dwmc" maxlength="25"></html:text></td>
      		<th>��λ����</th>
      		<td>
      			<html:select property="queryequals_dwxz">
      				<html:options collection="dwxzList" property="dm" labelProperty="mc"/>
      			</html:select>
      		</td>
      		
        </tr>
        <tr>
        	<th>ע��ʱ��</th>
        	<td>
        		 <html:text 
        		 property="querygreaterequal_zcsj" 
        		 onclick="showCalendar(this.id,'y-mm-dd')"
        		 styleId="querygreaterequal_zcsj"
        		 onblur="dateFormatChg(this);" 
        		 ></html:text> - <html:text 
        		 onclick="showCalendar(this.id,'y-mm-dd')"
        		 styleId="querylessequal_zcsj"
        		 property="querylessequal_zcsj"
        		 onblur="dateFormatChg(this);" 
        		 ></html:text>
        	</td>
        	<th>��ҵ����</th>
      		<td>
      			<html:select property="queryequals_hyfl">
      				<html:options collection="hyflList" property="dm" labelProperty="mc"/>
      			</html:select>
      		</td>
        </tr>
      </tbody>
    </table>
  </div>
</div>
<!-- From���� start-->
<!---------------------���--�и�ѡ������ݱ�------------------->
<div class="formbox">
    <h3 class="datetitle_01">
    <span>
    	��ѯ���&nbsp;&nbsp;
 		 <logic:notEmpty name="rs">
 		 	<font color="blue">��ʾ��˫��һ��ѡ��λ��������ͷ��������</font> 
 		 </logic:notEmpty>
    </span>
    </h3>
   
 
  <table summary="" class="dateline" align="" width="100%">
    <thead>
      <tr>
		<logic:iterate id="tit" name="topTr" offset="1"scope="request">
			<td id="${tit.en}">
				${tit.cn}
			</td>
		</logic:iterate>
      </tr>
    </thead>
    <tbody>
     <logic:notEmpty name="rs">
	      <logic:iterate name="rs" id="s">
		      <tr onclick="rowOnClick(this)" 
		      	  ondblclick="sendInfo();">
		        <logic:iterate id="v" name="s" offset="1" length="1">
					<td>
						${v }
						<input type="hidden" value="${v }"/>
					</td>
				</logic:iterate>
				 <logic:iterate id="v" name="s" offset="2" >
					<td>
						${v }
					</td>
				</logic:iterate>
		      </tr>
	      </logic:iterate>
	      	<%
				for (int i = 0; i < (Integer) request.getAttribute("maxNum")- ((List) request.getAttribute("rs")).size(); i++) {
			%>
			<tr>
				<logic:iterate id="t" name="topTr" offset="1" scope="request">
					<td>
						&nbsp;
					</td>
				</logic:iterate>
			</tr>
			<%
			}
			%>
      </logic:notEmpty>
      <logic:empty name="rs">
			<%
				for (int i = 0; i < (Integer) request.getAttribute("maxNum"); i++) {
			%>
			<tr>
				<logic:iterate id="t" name="topTr" offset="1" scope="request">
					<td>
						&nbsp;
					</td>
				</logic:iterate>
			</tr>
			<%
			}
			%>
		</logic:empty>
    </tbody>
  </table>
  <!--��ҳ��ʾ-->
      <jsp:include flush="true" page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
  <!--��ҳ��ʾ-->
  
</div>
</html:form>
</body>
</html>
