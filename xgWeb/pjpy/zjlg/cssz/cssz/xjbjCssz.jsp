<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getCpzfp.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjlg.js"></script>
	</head>
	
	<body onload="xyDisabled('xy');">
		<html:form action="/zjlgPjpy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title" scope="request" /></a>
				</p>
			</div>
			<input type="hidden" id="xxk" name = "xxk" value="xjbj"/>
			<input type="hidden" id="isFdy" value=""/>
			<input type="hidden" id="zyV" name="zyV"/>
			<input type="hidden" id="bjV" name="bjV"/>
			<input type="hidden" id="xq" name="xq" value="${xq }"/>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" id="method" name="method" value="cssz" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="comp_title">
				<ul>
				<logic:notEqual value="10657" name="xxdm">
					<li id="zxszy_sjd"><a href="#" onclick="$('go').value='';refreshForm('zjlgPjpy.do?xxk=jxj')">
							<span>��ѧ��</span>
						</a>
					</li>
					<li id="zxszy_dd"><a href="#" onclick="$('go').value='';refreshForm('zjlgPjpy.do?xxk=rych')">
							<span>�����ƺ�</span>
						</a>
					</li>
				</logic:notEqual>
				<logic:equal value="10657" name="xxdm">
					<li id="zxszy_sjd"><a href="#" onclick="$('go').value='';refreshForm('guizdxPjpy.do?method=rsszXxManage&mklx=jxj')"">
							<span>��ѧ��</span>
						</a>
					</li>
					<li id="zxszy_dd"><a href="#" onclick="$('go').value='';refreshForm('guizdxPjpy.do?method=rsszXxManage&mklx=rych')">
							<span>�����ƺ�</span>
						</a>
					</li>
				</logic:equal>
					<li id="xlxhhd_hdxs" class="ha"><a href="#" onclick="$('go').value='';refreshForm('zjlgPjpy.do?xxk=xjbj')">
							<span>�Ƚ��༶</span>
						</a>
					</li>
				</ul>
			</div>
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
					<logic:notEqual name="userType" value="xy" scope="session">
						<li><a href="#" class="btn_csh" onclick="viewTempDiv('�Ƚ��༶��������','tmpdiv',350,100)">��ʼ��������</a></li>
						<logic:equal value="10338" name="xxdm">
						<li><a href="#" class="btn_sq" onclick="chgPriseXn()">����ѧ��</a></li>
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm">
						<li><a href="#" class="btn_sz" onclick="showTopWin('pjpy_tyb_pjsjsz.do',450,330)">������������</a></li>
						</logic:notEqual>
					</logic:notEqual>
					</ul>
				</div>
				</div>
			</logic:equal>
			
			
			<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td><html:select property="xn" style="width:120px" disabled="true"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select></td>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" style="width:160px" styleId="xy">
									<html:option value="">ȫ�� </html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" onclick="refreshForm('/xgxt/zjlgPjpy.do')">
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
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						</span>
					</h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:notEqual name="userType" value="xy" scope="session">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" align="center" style="cursor:hand"
								ondblclick="if(curr_row.cells[4].innerText==''){alert('��δ���������������ɽ������������ܵ���������');return false;}xjbjrstz();">

								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</logic:notEqual>
						<logic:equal name="userType" value="xy" scope="session">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" align="center" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</logic:equal>
					</table>
					<!--��ҳ��ʾ-->
			     	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlgPjpyForm"></jsp:include>
					<!--��ҳ��ʾ-->
					<script type="text/javascript">
					$('choose').className="hide";
					</script>
			</logic:notEmpty>
			</div>
			
			<div id="tmpdiv1"></div>
			<div id="tmpdiv" style="display: none">
			<table class="formlist">
				<tbody>
					<tr>
						<th>�趨����</th>
						<td><input type="text" id="szbl" name="szbl" style="width:60px" onkeypress="return numInputValue(this,5,event)" maxlength="5"/>%</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						<button type="button" name="�ύ" onclick="plszSave('zjlgPjpy.do?method=xjbjPlszSave')">�ύ</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<logic:present name="updateOK">
			<logic:equal name="updateOK" value = "ok">
				<script language="javascript">
					alert("�޸ĳɹ�");
				</script>
			</logic:equal>
			<logic:equal name="updateOK" value = "no">
				<script language="javascript">
					alert("�޸�ʧ��");
				</script>
			</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
