<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<script>
<!--
	function pt(url) {
		if (curr_row==null || curr_row=='') {
			if (confirm('û��ѡ���κ�����,����һ�м���,ȷ��Ҫ��ӡ��?')) {
				window.open(url);
			}
		} else {
		
			var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pk;
			window.open(url);
		}
	}
	function fdysh() {
		var isFdy = document.getElementById('isFdy').value;
		if (isFdy=='true') {
			var nj = document.getElementById('nj').value;
			var jxjdm = document.getElementById('jxjdm').value;
			var zydm = document.getElementById('zy').value;
			if (nj==''||nj==null||jxjdm==''||jxjdm==null||zydm==''||zydm==null) {
				alert('���ʱ,��ѯ�������꼶,��ѧ��,רҵΪ��ѡ��!');
				return;
			} else {
				shandSubmit('pjpy_shcbys_jxjshres.do','tg');
			}
		} else {
			shandSubmit('pjpy_shcbys_jxjshres.do','tg');
		}
	}
//-->
</script>
<body onload="xyDisabled('xy')">
	<html:form action="/pjpyshcbyswh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		<bean:message bundle="pjpyshcbys" key="pjpy_shcbys_jxjsh" />
       		</div>
    	</div>
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="userType" name="userType" value="${userType }"/>
    	<input type="hidden" id="tableName" name="tableName" value="view_xsjxjb"/>
    	<input type="hidden" id="realTable" name="realTable" value="xsjxjb"/>
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/><!--���ʧ����Ϣ��ʾ -->
    	<input type="hidden" name="userName" id="userName" value="${userName }"/>
    	<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }"/>
    	<fieldset>
				<legend>
					����ѡ��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
						<td align="left">
							�꼶��
							<html:select property="nj" styleId="nj" styleClass="select" 
							onchange="initZyList();initBjList()" style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
							&nbsp;&nbsp;
								ѧ�꣺
								<html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;
								ѧ�ڣ�
								<html:select property="xq" 
								styleClass="select" style="width:90px;padding-left:80px" styleId="xq" >
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;
								ѧ��:
								<html:text property="xh" styleId="xh" styleClass="inputtext"
								 style="width:100px"></html:text>
								 &nbsp;&nbsp;
								��ѧ��:
								 <html:select property="jxjdm" styleId="jxjdm" onchange="refreshForm('pjpy_shcbys_jxjsh.do')" style="width:110px">
								 	<html:option value=""></html:option>
								 	<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
								 </html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="dataQryChk('pjpy_shcbys_jxjshqry.do');">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								ϵ��
								<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:165px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								רҵ��
								<html:select property="zydm" onchange="initBjList();refreshForm('pjpy_shcbys_jxjsh.do');" style="width:165px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								�༶��
									<html:select property="bjdm" style="width:165px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								״̬��
								  <html:select property="bz" name="fm">
								   	<html:option value=""></html:option>
								   	<html:option value="0">δ���</html:option>
								   	<html:option value="1">��ͨ��</html:option>
								   	<html:option value="2">ͨ��</html:option>
								</html:select>
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
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ;������ͷ���Խ�������;</font>
							</legend>
							<logic:equal value="xy" name="userType">
								<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="3" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;background-color: <logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" ondblclick="modiAndDel('pjpy_shcbys_jxjshone.do?pkValue=','modi','700','550');">
										<td align=center><input type="checkbox" id="cbv" name="cbv"
										 value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>"
										 <logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>/>
									
									    </td>
										<logic:iterate id="v" name="s" offset="3">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="2" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;background-color: <logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" ondblclick="modiAndDel('pjpy_shcbys_jxjshone.do?pkValue=','modi','700','550');">
										<td align=center><input type="checkbox" id="cbv" name="cbv"
										 value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"/>
									   
									    </td>
										<logic:iterate id="v" name="s" offset="2">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							</logic:notEqual>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<button type="button" class="button2" id="btn_tg" style="width:80px"
								onclick="fdysh()">
								���ͨ��
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_btg" style="width:80px"
								onclick="shandSubmit('pjpy_shcbys_jxjshres.do','btg');">
								��˲�ͨ��
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_print" onclick="pt('pjpy_shcbys_jxjprintpk.do?pkValue=')" style="width:80px"
						id="buttonClose">
						�����ӡ
					</button>
					&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_prints" onclick="pt('pjpy_shcbys_cjprintpk.do?pkValue=')" style="width:80px"
						id="buttonClose">
						�ɼ���ӡ
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataExport()"
										style="width: 80px">
										��������
									</button>
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
				</div>
				 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <logic:present name="inserted">
	 	<logic:equal value="yes" name="inserted">
	 		<script>
	 			alert('�����ɹ���');
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 	<logic:equal value="no" name="inserted">
	 		<script>
	 			alert('' + document.getElementById('failInfo').value);
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 </logic:present> 
	</div>
	</html:form>
</body>