<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->

<html>
		<script language="javascript">
		function SelectrwOption(a,b){
   			document.forms[0].tname.value=a;
   			document.forms[0].action=b;
   			document.forms[0].submit();
		}
		
		function thoughtLoad(defaultid){
		   if(document.forms[0].tname.value!= ""){
			  document.getElementById(document.forms[0].tname.value+"l").className = "xxk_on_l";
			  document.getElementById(document.forms[0].tname.value).className = "xxk_on_m";
			  document.getElementById(document.forms[0].tname.value+"r").className = "xxk_on_r";
		   }
		}
	</script>
	<body onload="thoughtLoad('')">
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<html:form action="/bxxx" method="post">
			<input type="hidden" id="tname" name="tname" value="${tname }" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�${title }
				</div>
			</div>
			<div class="xxk">
				<ul>
					<li id="lpxml" class="xxk_off_l"></li>
					<li id="lpxm"
						onclick="SelectrwOption('lpxm','/xgxt/bxxx.do?method=lpdmwh')"
						class="xxk_off_m">
						&nbsp;��&nbsp;��&nbsp;��&nbsp;Ŀ&nbsp;
					</li>
					<li id="lpxmr" class="xxk_off_r"></li>
				</ul>
				<ul>
					<li id="clxml" class="xxk_off_l"></li>
					<li id="clxm"
						onclick="SelectrwOption('clxm','/xgxt/bxxx.do?method=cldmwh')"
						class="xxk_off_m">
						&nbsp;��&nbsp;��&nbsp;��&nbsp;Ŀ&nbsp;
					</li>
					<li id="clxmr" class="xxk_off_r"></li>
				</ul>
			</div>
			<fieldset>
				<legend>
					��ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
								<td align="left" nowrap>
									���룺
									<html:text property="querylike_dm" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
									&nbsp;&nbsp;
									���ƣ�
									<html:text property="querylike_mc" maxlength="20" ></html:text>
								</td>
								<td width="10"align="center" valign="middle">
								<button class="button2"  id="search_go"
									onclick="allNotEmpThenGo('/xgxt/bxxx.do?method=lpdmwh&doType=query')">
									��ѯ
								</button>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								��¼����
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px">
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
											ondblclick="showInfo('/xgxt/bxxx.do?method=lpxmUpdate','view','360','230')"
										style="cursor:hand;">
										<td align=center>
											<input type="checkbox" id="cbv" name="primarykey_cbv" 	value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									   		<input type="hidden" value="<bean:write name="v" />">
									    </td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="99%" id="Table" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
				<logic:equal value="yes" name="writeAble">
				<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
					<button class="button2" onclick="showTopWin('/xgxt/bxxx.do?method=lpxmUpdate','360','230');"
							style="width:80px">
							����
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="showInfo('/xgxt/bxxx.do?method=lpxmUpdate','update','360','230')"
							style="width:80px"> 
							�޸�
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="deletedata('/xgxt/bxxx.do?method=lpdmwh&doType=del');"
							style="width:80px">
							ɾ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"onclick="impAndChkData();"style="width:80px">
							��������
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="expData('/xgxt/bxxx.do?method=lpdmwh&doType=expData')" style="width:80px">
							��������
					</button>
				</div>
				</logic:equal>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:equal value="true" name="result">
			<script>
			alert('�����ɹ�');
			document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
