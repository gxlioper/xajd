<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript">
<!--
	function autocount() {
		if (document.getElementById('xn').value=='' ||document.getElementById('xy').value=='') {
			alert('�ò�������ѧ�꣬<bean:message key="lable.xsgzyxpzxy" />�Զ����������ɼ�,�����ɼ�,ƽ��ѧ��,������,�ͷ���!\nѧ���<bean:message key="lable.xsgzyxpzxy" />Ϊ��ѡ��.');
			return;
		} else {
			if (confirm('ȷ��Ҫ��ѧ��,<bean:message key="lable.xsgzyxpzxy" />Ϊ��λ�Զ����������ɼ�,�����ɼ�,ƽ��ѧ��,������,�ͷ�����?')) {
			
			refreshForm('pjpy_whlghxxy_zhszcpwh.do?act=auto');
			BatAlert.showTips('���ڲ�������ȴ�...');
			} 
			return;
		}
	}	
//-->

</script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyhxxywh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		��ǰ����λ�ã��������� - ��Ϣά�� - �ۺ����ʲ���
       		</div>
    	</div>
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="userType" name="userType" value="${userType }"/>
    	<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
    	<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/><!-- ����ɾ����Ϣ��ʾ -->
    	<input type="hidden" id="pt" name="pt" value="pt"/>
    	<fieldset>
				<legend>
					����ѡ��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
							�꼶��
							<html:select property="nj" styleId="nj" style="width:90px"
							onchange="initZyList();initBjList()" styleClass="select">
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
								ѧ��:
								<html:text property="xh" styleId="xh" styleClass="inputtext"
								 style="width:100px"></html:text>
								&nbsp;&nbsp;
								����:
								<html:text property="xm" styleId="xm" styleClass="inputtext" style="width:100px"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="act" value="qry" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="dataQryChk('pjpy_whlghxxy_zhszcpwh.do');">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:150px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								רҵ��
								<html:select property="zydm" onchange="initBjList()" style="width:170px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								�༶��
								<html:select property="bjdm" style="width:170px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
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
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
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
										style="cursor:hand;" ondblclick="modiAndDel('pjpy_hxxy_zhszcpmodi.do?act=view&pkValue=','modi','620','480')">
										<td align=center><input type="checkbox" id="cbv" name="cbv"
										 value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									    </td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="100%" id="Table" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=pjpyHxxyActionForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<button class="button2" id="btn_add" style="width:80px"
								onclick="showTopWin('pjpy_zjcm_zhszcpblsz.do',700,230)">
								���ñ���
								</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" id="btn_add" style="width:80px"
								onclick="autocount()">
								�Զ�����
							</button>
							&nbsp;&nbsp;&nbsp;
<%--							<button class="button2" id="btn_add" style="width:80px"--%>
<%--								onclick="showTopWin('hhgxy_dycxfAdd.do','620','480')">--%>
<%--								����--%>
<%--							</button>--%>
<%--							&nbsp;&nbsp;&nbsp;--%>
							<button class="button2" id="btn_modi" style="width:80px"
								onclick="modiAndDel('pjpy_hxxy_zhszcpmodi.do?pkValue=','modi','620','480')">
								�޸�
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" id="btn_del" style="width:80px"
								onclick="deldata('pjpy_whlghxxy_zhszcpwh.do?act=del')">
								ɾ��
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2"
								onclick="impAndChkData();"
								style="width:80px">
										��������
									</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="dataExport()" style="width:80px">
									��������
								</button>
<%--								&nbsp;&nbsp;&nbsp;--%>
<%--								<a href="xlsDown/hxxy_zhszcp.xls"--%>
<%--										target="_blank">ģ������</a>--%>
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
				</div>
			</div>
	</html:form>
	 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <logic:present name="deleted">
	 	<logic:equal value="yes" name="deleted">
	 		<script>
	 			alert('�����ɹ���');
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 	<logic:equal value="no" name="deleted">
	 		<script>
	 			alert('' + document.getElementById('failInfo').value());
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 </logic:present> 
</body>