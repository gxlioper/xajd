<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		
		function addSpgw(){
			var url="/xgxt/xtwhSpgw.do?method=spgwManage&doType=add";
			showTopWin(url);
		}
		
		function modiSpgw(){
			if (null == curr_row) {
				alert('��ѡ��һ��');
			} else {
				var tid = curr_row.getElementsByTagName('input')[0].value;
				var url = '/xgxt/xtwhSpgw.do?method=spgwManage&doType=update&id='+tid;
				showTopWin(url);
			}
		}
		
		function delSpgw(){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
				}
			}
			if(blog){
				var url="/xgxt/xtwhSpgw.do?method=spgwManage&";
				url+="&doType=del";
				refreshForm(url);
				hiddenMessage(true,true);
				BatAlert.showTips('���ڲ��������Ե�...');
			}else{
				alert("û����Ҫ����ļ�¼!");
				return false;
			}
			
		}
		
		function editAssessor(spgw){
			if (null == curr_row) {
				alert('��ѡ��һ��');
			} else {
				var tid = curr_row.getElementsByTagName('input')[0].value;
				var url = '/xgxt/xtwhSpgw.do?method=spgwYhManage&spgw='+tid;
				showTopWin(url,620,500);
			}
		}
		
		function lookPostUser(name){
			return false;
			var url=action+"?theAction=roleUser&busiRole="+encodeStr(name);
			showTopWin(url,480,390,"Y");
		}
		
		/*
		function audit(){
			var url = "/xgxt/xtwhSpjl.do?method=spjlAudit&sqdh=1&name="+encodeURI(encodeURI("������"))+"&splc=A438379C7DD5A1E8E040007F01001975&spgw=A438379C7DD4A1E8E040007F01001971&actionname=actionname";
			showTopWin(url,700,560);
		
		}
		*/
		
		
		</script>
	</head>
	<body>

		<html:form action="/xtwhSpgw" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<!-- ģ������ -->
			<html:hidden property="realTable" styleId="realTable"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="addSpgw();return false;" class="btn_zj"> ���� </a></li>
						<li><a href="#" onclick="modiSpgw();return false;" class="btn_xg"> �޸� </a></li>
						<li><a href="#" onclick="delSpgw();return false;" class="btn_sc"> ɾ�� </a></li>
                        <li><a href="#" onclick="editAssessor();return false;" class="btn_sz">���������û�</a></li>
					</ul>
				</div>
				
				
				
				<!-- new �汾 -->
<%--			     <logic:equal name="superSearch" value="yes">--%>
<%--			     	<%@ include file="/comm/search/superSearchArea.jsp"%>--%>
<%--			     </logic:equal>--%>
			     
			     <!-- old �汾 -->
<%--			     <logic:equal name="superSearch" value="no">--%>
				
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xtwhSpgw.do?method=spgwManage')">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
<%--				</logic:equal>--%>
			</div>
			<div class="formbox">
			  <h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty>  
						<logic:notEmpty name="rs">
						<font color="blue"></font>	
						</logic:notEmpty>
					</span>
				</h3>

			  <logic:notEmpty name="rs">
				<div class="con_overlfow" >
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />

								</td>
								<logic:iterate id="tit" name="topTr"   indexId="index">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
									<td>
										<input type="checkbox" name="checkVal" id="pkV"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
										<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>
										/>
									</td>
									<logic:iterate id="v" name="s" offset="1" >
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				  </div>
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=spgwForm"></jsp:include>
			  </logic:notEmpty>
			</div>
		</html:form>
		<logic:present  name="dxResult">
		<logic:equal value="false" name="dxResult">
			<script>
				alert("���Ͷ���ʧ��!");
			</script>
		</logic:equal>
		<logic:equal value="true" name="dxResult">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		</logic:present>
		<logic:present  name="result">
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��!");
			</script>
		</logic:equal>
		</logic:present>
	</body>
</html>
