 <%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
			
		<script type="text/javascript">
			function sendJsm(){
				if(window.dialogArguments.document.getElementById('jsmc')){
					window.dialogArguments.document.getElementById('jsmc').value = curr_row.getElementsByTagName('input')[0].value;
					Close();
					window.dialogArguments.document.getElementById('disbutton').click();
				}
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<center>
			<script language="javascript" src="js/function.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="js/AjaxFunction.js"></script>
			
			<html:form action="/jswhManage" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ɫ��ѯ</a>
				</p>
			</div>
			<div class="searchtab">
			    <table width="100%" border="0">
			    	    <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" class="btn_cx" 
								id="search_go"
								onclick="document.forms[0].go.value='go';refreshForm('/xgxt/jswhManage.do?method=getJsInfo');this.disabled=true;">
							��ѯ
						</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			      <tbody>
			        <tr>
			         <tr>
						<th>��ɫ������Χ</th>
						<td><html:select property="jscmdm" style="width:180px" >
									<html:option value=""></html:option>
									<html:options collection="jsczfwList" property="jscmdm"
										labelProperty="jscmmc" />
								</html:select>
						   </td>
							<th>��ɫ����</th>
							<td><html:select property="jslxdm" style="width:180px" >
									<html:option value=""></html:option>
									<html:options collection="jslxList" property="jslxdm"
										labelProperty="jslxmc" />
								</html:select>
						   </td>
						</tr>
					<tr>
						
						<th>��ɫ��</th>
							<td><html:text property="jsmc" styleId="jsmc"></html:text></td>
							
						<th></th>
							<td></td>
					</tr>
			    
			      </tbody>
			    </table>
			  </div>
			  <!--���й������� ���ݿ�start-->
			<logic:empty name="rs">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
						<font color="red">δ�ҵ��κμ�¼��</font> 
			    </span>
			    </h3>
			 </logic:empty>
			<logic:notEmpty name="rs">
				<h3 class="datetitle_01">
					<span>
						��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������</font>
					</span>
				</h3>
				<table width="99%" id="rsTable" class="dateline" >
					<thead>
							<tr style="cursor:hand" >
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en" />" 
										onclick="tableSort(this)" nowrap />
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);" ondblclick="sendJsm()"
								style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">								
										<input type="hidden" name="pkValue" id="pkV" value="${v }" />
									${v }
									</logic:iterate>
									
								</td>
							
								<logic:iterate id="v" name="s" offset="2">
									<td>${v }</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
				</table>
				<!--��ҳ��ʾ-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qxwhForm"></jsp:include>
				<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>