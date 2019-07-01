<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
		<script type="text/javascript">
	<!--
		function cfxxsh(){
			var checkBoxArr = document.getElementsByName("cbv");
			var flag = false;
			var len=0;
			var str = "";
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
					len++;
					str += checkBoxArr[i].value + "!@";
				}
			}
			
			if (!flag) {
				alert("��ѡ��Ҫ��˵ļ�¼,��ѡ,��ѡ����.");
				return false;
			} else {
				showTopWin('wjcf_zjlg_cfxxplsh.do?pkString=' + str,'500','400');
			}
		}	
		function printCfgzs() {
			var pk = '';
			if (curr_row != null) {
				pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
			}
			window.open("wjcf_zjlg_cfbprint.do?pkValue=" + pk);
		}
	//-->
</script>
</head>
<body onload="xyDisabled('xy');">
	<html:form action="/wjcfzjlgwh.do" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<input type="hidden" name="operType" value="query"/>
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="failInfo" id="failInfo" value="${failinfo }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - ������� - �������</a>
			</p>
		</div>
		<logic:equal value="yes" name="writeAble">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_sh" id="btn_delete" onclick="cfxxsh()">���</a></li>
					<li><a href="#" class="btn_ck" id="btn_lxck" onclick="showTopWin('wjcf_zjlg_lxckcx.do',800,600)">��У�쿴����</a></li>
					<li><a href="#" class="btn_dy" id="btn_lxck" onclick="showTopWin('wjcf_zjlg_lxckcx.do',800,600)">��ӡ���ָ�֪��</a></li>
				</ul>
			</div>
			</div>
		</logic:equal>
		<div class="searchtab">	
			<table width="100%" class="" border="0">
				<tbody>
					<tr>
						<th>ѧ��</th>
						<td><html:select property="xn" styleId="xn" style="width:90px"
								styleClass="select" disabled="true">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select></td>
						<th>���</th>
						<td><html:select property="nd" styleId="nd" style="width:90px" disabled="true">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select></td>
						<th>�꼶</th>
						<td><html:select property="nj" styleId="nj" style="width:80px"
								styleClass="select" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select></td>
					</tr>
					<tr>	
						<th>�������</th>
						<td><html:select property="cflb" styleId="cflb" 
								styleClass="select">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="cflbList" property="cflbdm"
									labelProperty="cflbmc" />
							</html:select></td>
						<th>����ԭ��</th>
						<td><html:select property="cfyy" styleId="cfyy" 
								styleClass="select">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="cfyyList" property="cfyydm"
									labelProperty="cfyymc" />
							</html:select>
						</td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>ѧ��</th>
						<td><html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px">
							</html:text></td>
						<th>����</th>
						<td><html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:100px">
							</html:text>
						</td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td><html:select property="xydm" style="width:150px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select></td>
						<th>רҵ</th>
						<td><html:select property="zydm" style="width:150px" styleId="zy"
								styleClass="select" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select></td>
						<th>�༶</th>
						<td><html:select property="bjdm" style="width: 150px" styleId="bj"
								styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
						<button type="button" id="search_go" onclick="refreshForm('wjcf_zjlg_cfsh.do')">
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
					<div class="con_overlfow">
					<table width="100%" id="rsTable" class="dateline tablenowrap">
						<thead>
							<tr>
								<td>
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;"
								ondblclick="modiAndDel('wjcf_zjlg_cfxxdgsh.do?pkValue=','modi','800','600')">
								<td>
									<input type="checkbox" id="cbv" name="cbv"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" <logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>/>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					</div>
					<!--��ҳ��ʾ-->
				     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfZjlgActionForm"></jsp:include>
					<!--��ҳ��ʾ-->
			</logic:notEmpty>
			</div>
			<div id="tmpdiv"></div>
	</html:form>
		<logic:present name="deleted">
			<logic:equal value="yes" name="deleted">
				<script>
					alert('�����ɹ�!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="no" name="deleted">
				<script>
					alert(''+document.getElementById('failInfo').value);
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
</body>
</html>