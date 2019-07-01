<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		
		<script type="text/javascript">
			function xyDisabled(ele) {
				if ($("disabledXy")&&$("disabledXy").value == "true") {
					var tmp = ele.split("-");
					for (i = 0; i < tmp.length; i++) {
						document.getElementById(tmp[i]).disabled = true;
					}
				}
			}
			
			function checkAll(){
				var chec = document.getElementById('chec');
				var checkBoxArr = document.getElementsByName("pkValues");

				for(var i=0;i<checkBoxArr.length;i++){
					checkBoxArr[i].checked = chec.checked;
				}
			}
			
			function batchSave(url,names){
				var checkbox = document.getElementsByName(names);
				
				var flag = false;
				
				for(var i=0;i<checkbox.length;i++){
					if(checkbox[i].checked == true){
						flag = true;
						break;
					}
				}
				
				if(flag){
					refreshForm(url);
				}else{
					alertInfo('��ѡ����Ҫ���������ݣ�');
				}
			}
			
			function showMoreInfo(url, w, h) {
				if (w == null) {
					w = 700;
				}
				if (h == null) {
					h = 400;
				}
				if (curr_row == null) {
					alertInfo("��ѡ��Ҫ�������У�");
					return false;
				} else {
					var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
					url += "&pkValue=";
					url += pk;
					
					showTopWin(url, w, h);	
				}	
			}
		</script>
	
	</head>
	<body onload="xyDisabled('xy');">
    <html:form action="/zjjj_rcsw_qjgl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="userStatus" value="${userStatus }"/>
			<input type="hidden" name="disabledXy" value="${disabledXy }"/>
			<logic:equal value="true" name="disabledXy">
				<input type="hidden" name="xydm" value="${userDep }"/>
			</logic:equal>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_shtg" 
							onclick="batchSave('/xgxt/zjjj_rcsw_qjgl.do?method=qjshManage&doType=sh&shjg=tg','pkValues');return false;">���ͨ��</a></li>
						<li><a href="#" class="btn_shbtg" 
							onclick="batchSave('/xgxt/zjjj_rcsw_qjgl.do?method=qjshManage&doType=sh&shjg=btg','pkValues');return false;">��˲�ͨ��</a></li>						
					</ul>
				</div>
				</div>
			</logic:equal>		
		 	<!-- ����������ʼ -->
				<div class="searchtab">
					<table>
						<tr>
							<th>ѧ��</th>
							<td>
								<html:select property="xn" style="width:100px" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>ѧ��</th>
							<td>
								<html:select property="xq" style="width:90px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
							<th>ѧ��</th>
							<td><html:text property="xh" style="width:85px"></html:text></td>
						</tr>
						<tr>
							<th>����</th>
							<td><html:text property="xm" style="width:85px"></html:text></td>
							<th>
								Ժϵ
							</th>
							<td>
								<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
								</html:select>
							</td>
							<th>רҵ</th>
							<td>
								<html:select property="zydm" style="width:180px" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>�༶</th>
							<td>
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
							<th>���״̬</th>
							<td>
								<html:select property="sh1" style="width:180px" styleId="sh1">
									<html:option value=""></html:option>
									<html:option value="δ���" >δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
									<html:option value="�������">�������</html:option>
								</html:select>
							</td>
							<th colspan="2"></th>
						</tr>
						
						<tfoot>
							<tr>
								<td colspan="6">
								<div class="btn">
								<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/zjjj_rcsw_qjgl.do?method=qjshManage')">
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
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;˫��һ�п��Բ鿴��ϸ��Ϣ;</font> 
						</span>
					</h3>
					
					<div class="con_overlfow">	
					<table width="99%" id="rsTable" class="dateline tablenowrap">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" id="chec" onclick="checkAll();"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td onclick="tableSort(this)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);"
								style="cursor:hand;background-color:"
								ondblclick="showMoreInfo('/xgxt/zjjj_rcsw_qjgl.do?method=qjshUpdate&doType=view',800,600)">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" value="${v }" name="pkValues" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					</div>
					<!--��ҳ��ʾ-->
				     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qjglForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</logic:notEmpty>	
			</div>
	</html:form>	
	
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript" defer="defer">
				alertInfo($('message').value);
			</script>
		</logic:present>
    </body>
</html>	
