<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script type="text/javascript" src="pjpy/nbzy/nbzyJs.js"></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<script type="text/javascript">

	function selectAlls(){
	var checkBoxArr = document.getElementsByName("cbv");
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = true;
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}

</script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpynbzywh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		��ǰ����λ��: �������� - ��Ϣά�� - �ۺ����ʲ���
       		</div>
    	</div>
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="userType" name="userType" value="${userType }"/>
    	<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
    	<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
    	<input type="hidden" id="tmp" name="tmp" value="${tmp}"/>
    	<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
    	<!-- ����ɾ����Ϣ��ʾ -->
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/>
    	<fieldset>
				<legend>
					����ѡ��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="2" align="left">
								ά����Ŀ��
								<html:radio property="szlx" value="0" style="cursor:hand"
								onclick="refreshForm('pjpy_nbzy_zhszcp.do?act=szlx')"/>ְҵ������
								<html:radio property="szlx" value="1" style="cursor:hand"
								onclick="refreshForm('pjpy_nbzy_zhszcp.do?act=szlx')"/>ְҵ����������
								<html:radio property="szlx" value="2" style="cursor:hand"
								onclick="refreshForm('pjpy_nbzy_zhszcp.do?act=szlx')"/>�ɳ�����չ���ʷ�
								<html:radio property="szlx" value="3" style="cursor:hand"
								onclick="refreshForm('pjpy_nbzy_zhszcp.do?act=szlx')"/>�ۺϲ����ܷ�
							</td>
						</tr>
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
								<html:text property="xm" styleId="xm" styleClass="inputtext"
								 style="width:100px"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="if (document.getElementById('xn').value=='') {alert('ѧ��Ϊ��ѡ!');return;} else dataQryChk('pjpy_nbzy_szqry.do');">
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
								<font color="blue">��ʾ��������ͷ���Խ�������;</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAlls()" />
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
										style="cursor:hand;" ondblclick="modiTab()">
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
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<logic:notEqual value="3" name="tmp">
								<button type="button" class="button2" id="btn_add" style="width:80px"
								onclick="addTab()">
								����
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_modi" style="width:80px"
								onclick="modiTab()">
								�޸�
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_del" style="width:80px"
								onclick="delTab()">
								ɾ��
							</button>
							&nbsp;&nbsp;&nbsp;
							</logic:notEqual>
							<logic:equal value="3" name="tmp">
								<button type="button" class="button2" id="btn_count" style="width:80px"
								onclick="nbzy_autocount();">
								�Զ�����
							</button>
							&nbsp;&nbsp;&nbsp;
							</logic:equal>
							<button type="button" class="button2"
								onclick="impAndChkData();"
								style="width:80px">
										��������
									</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
									��������
								</button>
								&nbsp;&nbsp;&nbsp;
								<a href="xlsDown/${excelb}"
										target="_blank">ģ������</a>
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
				</div>
			</div>
	</html:form>
	 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <!-- ������ʾ -->
	 <jsp:include flush="true" page="/syscommon/deleteprompt.jsp"></jsp:include>
</body>