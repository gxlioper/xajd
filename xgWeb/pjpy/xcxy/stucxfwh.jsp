<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript">
<!--
	function disp() {
		var ts = document.getElementById('fs').value;
		if (ts == '3') {
			document.getElementById('btn_modi').disabled=true;
			document.getElementById('btn_del').disabled=true;
		}
	}	
//-->
</script>
<body onload="xyDisabled('xy');disp();">
	<html:form action="/pjpyxcxywh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		��ǰλ�ã��������� - ѧ���ɼ�ά�� - ѧ�����з���Ϣά�� 
       		</div>
    	</div>
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="userType" name="userType" value="${userType }"/>
    	<input type="hidden" id="tableName" name="tableName" value="view_pjpy_cxfb"/>
    	<input type="hidden" id="realTable" name="realTable" value="pjpy_cxfb"/>
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/><!-- ����ɾ����Ϣ��ʾ -->
    	<input type="hidden" id="isFdy" name="isFdy" value="${isFdy }"/>
    	<input type="hidden" id="userName" name="userName" value="${userName }"/>
    	<input type="hidden" id="pt" name="pt"/>
    	<fieldset>
				<legend>
					����ѡ��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
						<td align="left">
							�꼶��
							<html:select property="nj" styleId="nj" onchange="initZyList();initBjList()"
							 styleClass="select" style="width:90px">
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
							ѧ�ڣ�
							<html:select property="xq" style="width:90px" styleClass="select"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							&nbsp;&nbsp;
							ѧ�ţ�
							<html:text property="xh" styleId="xh"
							style="width:100px" styleClass="inputtext"></html:text>
							&nbsp;&nbsp;
							��ѯ��ʽ��
							<html:select property="fs" styleId="fs" onchange="refreshForm('pjpy_xcxy_stucxfxxwh.do')">
								<html:option value=""></html:option>
								<html:option value="1">�ӷ�</html:option>
								<html:option value="2">�۷�</html:option>
								<html:option value="3">�ܷ�</html:option>
<%--								<html:option value="4">����</html:option>--%>
							</html:select>
							<%--
							&nbsp;&nbsp;
							����:
							<html:text property="xm" styleId="xm" 
							style="width:100px" styleClass="inputtext"></html:text>
							--%></td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="act" value="qry" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_xcxy_stucxfxxwh.do');document.getElementById('search_go').disabled=true;">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								רҵ��
								<html:select property="zydm" onchange="initBjList()" style="width:180px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								�༶��
								<html:select property="bjdm" style="width:180px" 
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
										style="cursor:hand;" ondblclick="if (document.getElementById('fs').value=='3') return; else modiAndDel('pjpy_xcxy_cxfupdate.do?act=view&pkValue=','modi','550','450');">
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
							<TABLE width="99%" id="rsTable1" class="tbstyle">
											<TR>
												<TD height=3></TD>
											</TR>
											<TR>
												<TD>
													<jsp:include flush="true"
														page="/sjcz/turnpage.jsp?form=pjpyXcxyActionForm"></jsp:include>
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
								onclick="showTopWin('pjpy_xcxy_cxfadd.do',690,570);">
								����
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" id="btn_modi" style="width:80px"
								onclick="modiAndDel('pjpy_xcxy_cxfupdate.do?pkValue=','modi','550','450');">
								�޸�
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" id="btn_del" style="width:80px"
								onclick="deldata('pjpy_xcxy_stucxfxxwh.do?act=del')">
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