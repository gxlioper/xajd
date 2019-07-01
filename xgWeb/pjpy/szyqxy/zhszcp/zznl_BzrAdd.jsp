<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
		<script type="text/javascript">
		function bzrSave(){
			var xn=$("xn").value;
			var xq=$("xq").value;
			if(xn == "" || xq == ""){
				alert("ѧ�����ѧ�ڲ���Ϊ�գ���ȷ�ϣ���");
				return false;
			}
			if (confirm("ȷ����¼������ݣ�")) {
				showTips('���������У���ȴ�......');
				refreshForm("/xgxt/pjpyszyqwh.do?method=szyc_zznlBzrAdd&doType=save");
				$("buttonSave").disabled=true;
				$("buttonClose").disabled=true;
			}
		}
		</script>
	</head>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyszyqwh" method="post">
    	<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ۺ�����-ѧ���ۺ��������ɿ�-��֯������ѯ</a>
			</p>
		</div>
    	
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="userType" name="userType" value="${userType}"/>
    	<input type="hidden" id="userName" name="userName" value="${userName}"/>
    	<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
    	<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
    	<input type="hidden" id="tmp" name="tmp" value="${tmp }"/>
    	<input type="hidden" name="pkstring" value="" />
    	<!-- ����ɾ����Ϣ��ʾ -->
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/>
    	<input type="hidden" id="showSelect" name="showSelect" value="yes"/>
    	
    	<div class="comp_title">
	      <ul>
	        <li><a href="javascript:refreshForm('szyc_zznlManage.do');"><span>��ѯ</span></a></li>
	        <li class="ha"><a href="javascript:refreshForm('pjpyszyqwh.do?method=szyc_zznlBzrAdd');"><span>����</span></a></li>
	      </ul>
	    </div>
		    
	   <div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<logic:notEqual name="doType" value="view">
						<li><a href="#" class="btn_zj" onclick="bzrSave();">����</a></li>
					</logic:notEqual>
<!--					<li><a href="#" class="btn_xg"  onclick="window.close();window.dialogArguments.document.getElementById('search_go').click();">�ر�</a></li>-->
				</ul>
			</div>
		</div>
    	
   		<div class="searchtab">
			<table width="100%">
				<thead>
					<tr>
						<th>
							�꼶
						</th>
						<td>
							<html:select property="nj" styleId="nj" style="width:90px"
							onchange="initZyList();initBjList()" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xn" style="width:90px" styleClass="select"
								styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>
							ѧ��
						</th>
						<td>
							<html:select property="xq" style="width:90px" styleClass="select"
								styleId="xn" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>ѧ��</th>
						<td>
							<logic:equal name = "userType" value="stu" scope="session">
							<input type="text" name = "xh" value="<bean:write name="userName" scope="session"/>" readonly="readonly" style="width:100px" class="inputtext"/>
							</logic:equal>
							<logic:notEqual name = "userType" value="stu" scope="session">
							<html:text property="xh" styleId="xh" styleClass="inputtext"
							 style="width:100px"></html:text>
							 </logic:notEqual>
						</td>
						<th>����</th>
						<td>
							<html:text property="xm" styleId="xm" styleClass="inputtext"
							 style="width:100px"></html:text>
						</td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
							<html:select property="xydm" onchange="initZyList();initBjList()"
							styleClass="select" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>רҵ</th>
						<td><html:select property="zydm" onchange="initBjList()" 
							styleClass="select" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
						<th>�༶</th>
						<td>
							<html:select property="bjdm"  
							styleClass="select" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</thead>
				
				<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
						<input type="hidden" name="go" value="a" />
						<button type="button" id="search_go" onclick="refreshForm('pjpyszyqwh.do?method=szyc_zznlBzrAdd&go=go');">
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
				<table class="dateline" align="center" width="100%" id="tTb">
					<thead>
						<tr align="center" style="cursor:hand">
							<logic:iterate id="tit" name="topTr">
								<th id="<bean:write name="tit" property="en"/>">
									<bean:write name="tit" property="cn" />
								</th>
							</logic:iterate>
							<th>
								�����
							</th>
							<th>
								����
							</th>
							<th>
								�ȼ�
							</th>
							<th>
								�Ӽ���
							</th>
							<th>
								����
							</th>
						</tr>
					</thead>
					<logic:iterate name="rsList" id="s" indexId="index">
						<tr>
							<td align="center">
								<bean:write name="s" property="xh" />
								<input type="hidden" name="hdxh" value="<bean:write name="s" property="xh" />"/>
							</td>
							<td align="center">
								<bean:write name="s" property="xm" />
							</td>
							<td align="center">
								<bean:write name="s" property="xb" />
							</td>
							<td align="center">
								<bean:write name="s" property="bjmc" />
							</td>
							<td><textarea  name="hdzt" rows="3" onblur="chLeng(this,150)"></textarea></td>
							<td><input type='text' name='hdrq' id="hdrq${index }" maxlength='20' readonly="readonly" onblur='dateFormatChg(this)' style='cursor:hand;width: 80px' onclick="return showCalendar('hdrq${index }','y-mm-dd')"/></td>		
							<td><input type="text" name="hddj" maxlength="5" style="width: 70px"/></td>
							<td align="center"><select name='jjf' style=""><option value='�ӷ�'>�ӷ�</option><option value='����'>����</option></select></td>
							<td><input type="text" name="shfz" style="width: 50px" onkeypress="return sztzNumInputValue(this,5,event)" maxlength="5" style="ime-mode:disabled"/></td>
						</tr>
					</logic:iterate>
				</table>

			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
	 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <!-- ������ʾ -->
	 <jsp:include flush="true" page="/syscommon/deleteprompt.jsp"></jsp:include>
</body>