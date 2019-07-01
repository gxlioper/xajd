<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript">
			function sendXx(){
				if(window.opener == undefined){					 				
				
					var xydm = window.dialogArguments.document.forms[0].xydm.value;
					var zbmc = window.dialogArguments.document.forms[0].zbmc.value;
					
					var url = window.dialogArguments.document.forms[0].url.value;
					url+="&zgh="+curr_row.getElementsByTagName('input')[0].value;
					
					if(xydm != ""){
						url+="&xydm="+xydm;
					}
					if(zbmc != ""){
						url+="&zbmc="+zbmc;
					}
					window.dialogArguments.document.forms[0].action = url;
					window.dialogArguments.document.forms[0].submit();
				}else{				
					var xydm = window.opener.document.forms[0].xydm.value;
					var zbmc = window.opener.document.forms[0].zbmc.value;
					
					var url = window.opener.document.forms[0].url.value;
					url+="&zgh="+curr_row.getElementsByTagName('input')[0].value;
					
					if(xydm != ""){
						url+="&xydm="+xydm;
					}
					if(zbmc != ""){
						url+="&zbmc="+zbmc;
					}
					window.opener.document.forms[0].action = url;
					window.opener.document.forms[0].submit();
				}
				window.close();
			}
			jQuery(function(){
				xyDisabled('xy');
			})
		</script>
	</head>
	<body >
		<html:form action="/commXszz" method="post">
			<!-- ������ -->
			<%@ include file="/xszz/hiddenValue.jsp"%>
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }"/>
			<!-- ������ end-->
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ϣά�� - ��ʦ��Ϣ</a>
				</p>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /></font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="searchtab">
						<table width="100%" class="" border="0">
							<thead>
								<tr>
									<th>���ڲ���</th>
									<td><html:select property="szbm" style="width: 150px" onchange="">
											<html:option value=""></html:option>
											<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
										</html:select>
									</td>
									<th>ְ����</th>
									<td><html:text property="zgh" style="width:85px" maxlength="20"/></td>
									<th>����</th>
									<td><html:text property="xm" style="width:85px" maxlength="20"/></td>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<td colspan="6">
									<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('/xgxt/commXszz.do?method=lsxxManage');">
										��ѯ
									</button>
									 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										����
									 </button>
									</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					
							
					<div class="formbox">
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
									��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������˫��ѡ��ѧ����Ϣ.</font> 
								</span>
							</h3>
							<table width="100%" id="rsTable" class="dateline">
								<thead>	
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" 
										ondblclick="sendXx()">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v" />" />
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<!--��ҳ��ʾ-->
						     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
							<!--��ҳ��ʾ-->
							<script type="text/javascript">
								$('choose').className="hide";
							</script>
					</logic:notEmpty>
				</div>
			</logic:empty>
		</html:form>
	</body>
</html>
