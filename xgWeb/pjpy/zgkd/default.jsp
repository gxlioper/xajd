<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript"
	src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zgkd/zgkd.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
<!--  <script type="text/javascript">
	var v = 0;//�����ж��û��Ƿ��ǵ�һ�ε������,��һ��Ϊ����,�ڶ���Ϊ����
	var col = -1;//��������,�����ж��û��Ƿ��ǵ�һ�ε������
	//id:��ID, flag:�Ƿ����ͷ����, cellposition: �к�λ��, obj:this
	function keepRowNumber(id,flag,cellposition,obj){
		if(obj.cellIndex != col){//��һ������
			col = obj.cellIndex;
			v = 1;
		}else{//�ǵ�һ������
			col = -1;
			v = 0;
		}
		if(cellposition != obj.cellIndex){
			var trobj = $(id).getElementsByTagName('tr');//��ȡ���е��ж���
			var num;
			if(flag){//����ͷ����
				num = trobj.length-1;
				for(var i=1;i<trobj.length;i++){
					if(v == 1){	//��һ������Ϊ����,��ʾ�к�Ϊ����				
						trobj[i].cells[cellposition].innerText = num;
						num--;	
					}else{					
						trobj[i].cells[cellposition].innerText = i;
					}
									
				}
			}else{//������ͷ����
				num = trobj.length;
				for(var i=0;i<trobj.length;i++){
					trobj[i].cells[cellposition].innerText = i+1;
				}
			}
		}	
	}
</script>   -->
<base target="_self">
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyzgkdwh" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� - ��Ϣά�� - ���ʲ���
			</div>
		</div>
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="userType" name="userType"
			value="${userType }" />
		<input type="hidden" id="tableName" name="tableName"
			value="${tableName}" />
		<input type="hidden" id="realTable" name="realTable"
			value="${realTable}" />
		<input type="hidden" id="failInfo" name="failInfo"
			value="${failinfo }" />
		<input type="hidden" id="isFdy" name="isFdy" value="${isFdy}"/>
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<!-- ����ɾ����Ϣ��ʾ -->
		<input type="hidden" id="pt" />
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
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp; ѧ�꣺
							<html:select property="xn" style="width:90px" styleClass="select"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp; ѧ��:
							<html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:110px"></html:text>
							&nbsp;&nbsp; ����:
							<html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:110px"></html:text>
							&nbsp;&nbsp; ���ʵȼ�:
							<html:select property="szdj" styleId="szdj" styleClass="select"
								style="width:80px">
								<html:option value=""></html:option>
								<html:options collection="szdjList" property="szdjdm"
									labelProperty="szdjmc" />
							</html:select>
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
							<input type="hidden" name="act" value="go" />
							<button class="button2" style="height:40px" id="search_go"
								onclick="dataQryChk('pjpy_zgkd_zhszcpwh.do');">
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
							<html:select property="zydm" onchange="initBjList()"
								style="width:170px" styleClass="select" styleId="zy">
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
							��¼���� ${rsNum} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ;������ͷ���Խ�������;${pm }</font>
						</legend>
						<table width="99%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this);keepRowNumber('rsTable',true,1,this);" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;"
									ondblclick="modiAndDel('pjpy_zgkd_zhszcpview.do?act=view&pkValue=','modi','630','350')">
									<td align=center nowrap>
										<input type="checkbox" id="cbv" name="cbv"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align=center nowrap>
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
												page="/sjcz/turnpage.jsp?form=pjpyZgkdZhszcpActionForm"></jsp:include>
										</TD>
									</TR>
									<TR>
										<TD height=3></TD>
									</TR>
								</TABLE>
					</fieldset>
				</logic:notEmpty>
				<logic:equal value="yes" name="writeAble" scope="request">
					<div class="buttontool" align="center" id="btn"
						style="position:absolute;width:95%;top:100px">
						<button class="button2" style="" id="search_go"
								onclick="showTopWin('pjpy_zgkd_pmfssz.do',440,220)">
								������ʽ����
							</button>
						&nbsp;&nbsp;&nbsp;	
						<button class="button2" id="btn_add" style="width:80px"
							onclick="showTopWin('pjpy_zgkd_szcpadd.do','630','350')">
							����
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" id="btn_modi" style="width:80px"
							onclick="modiAndDel('pjpy_zgkd_zhszcpview.do?pkValue=','modi','630','350')">
							�޸�
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" id="btn_del" style="width:80px"
							onclick="deldata('pjpy_zgkd_zhszcpwh.do?act=del')">
							ɾ��
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="impAndChkData();"
							style="width:80px">
							��������
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="dataExport()" style="width:80px">
							��������
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="szcptj()" style="width:80px">
							����ͳ��
						</button>
						&nbsp;&nbsp;&nbsp;
						<a href="xlsDown/zgkd_zhszcpb.xls" target="_blank">ģ������</a>
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
