<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- ͷ�ļ� -->
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script>
		function viewChk(){
		     var url = "/xgxt/pjpyszyqwh.do?method=szyc_zznlViewAndChk";
		     url+="&xh="+(curr_row.cells[0].getElementsByTagName("input"))[0].value;
		     url+="&xn="+$("xn").value;
		     url+="&xq="+$("xq").value;
		     showTopWin(url,"700","800");   
		} 
		
		function jsfs(){
			var xn = $("xn").value;
			var xq = $("xq").value;
			if (xn == ""||xq== ""){
				alert("�����ۺ����ʷֱ���ѡ��ѧ���ѧ�ڣ���");
				return false;
			}
			if (confirm("��Ҫ�����ۺ����ʷ֣�ȷ����\n���\"ȷ��\"���������ݣ�\n���\"ȡ��\"�����������㣡")) {
				showTips('���������У���ȴ�......');
				var url = "/xgxt/pjpyszyqwh.do?method=zhszfhzAutoAccount";
				refreshForm(url);
			}
		}

		function zhszcpfhz() {
				var xn = $("xn").value;
				var xq = $("xq").value;
				var bj = $("bj").value;
				if (xn == ""||xq== ""||bj==""){
					alert("ѧ��,ѧ��,�༶Ϊ��ѡ���");
					return false;
				}
				window.open("/xgxt/pjpyszyqwh.do?method=zhszcpfbjhz&xn="+xn+"&xq="+xq+"&bj="+bj);	
		}
	</script>
</head>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyszyqwh" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ۺ�����-ѧ���ۺ��������ɿ�- �ۺ����ʷֻ���</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����</br>
				<span id="div_help" style="display: none">
				1.�������в������ǻ���<font color="blue">${xn }</font>ѧ�꣬<font color="blue">${xqmc }</font>ѧ�� չ���ġ�</br>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="tableName" name="tableName" value="view_szgy_zhszcp" />
		<input type="hidden" id="realTable" name="realTable" value="szgy_zhszcphzlsb" />
		<input type="hidden" id="userType" name="userType" value="${userType}"/>
		
		<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<logic:equal value="admin" name="userType" scope="session">
					<li><a href="#" class="btn_zj" onclick="showTopWin('pjpyszyqwh.do?method=jsfsUpdate',400,250);return false;">�����ֵ</a></li>
					<li><a href="#" class="btn_xg"  onclick="impAndChkData();return false;">��������</a></li>
					<li><a href="#" class="btn_sc" onclick="dataExport();return false;">��������</a></li>
					</logic:equal>
					<logic:equal value="xx" name="userType" scope="session">
					<li><a href="#" class="btn_zj" onclick="showTopWin('pjpyszyqwh.do?method=jsfsUpdate',400,250);return false;">�����ֵ</a></li>
					<li><a href="#" class="btn_xg"  onclick="impAndChkData();return false;">��������</a></li>
					<li><a href="#" class="btn_sc" onclick="dataExport();return false;">��������</a></li>
					</logic:equal>
					<li><a href="#" class="btn_dr" onclick="zhszcpfhz();return false;">�����༶���ܱ�</a></li>
				</ul>
			</div>
		</div>
		
		<div class="searchtab">
			<table width="100%">
				<tbody>
					<tr>
						<th>�꼶</th>
						<td>
							<html:select property="nj" styleId="nj" styleClass="select"
								onchange="initZyList();initBjList()" >
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xn"  styleClass="select"
								styleId="xn">
								<html:options collection="xnList2" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xq" styleClass="select"
								style="padding-left:80px" styleId="xq">								
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>ѧ��</th>
						<td>
							<html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px"></html:text>
						</td>
						<th>����</th>
						<td>
							<html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:100px"></html:text>															
						</td>
						<th>�ۺ������ܷ�</th>
						<td>
							<html:text property="zhszzf" styleId="zhszzf"></html:text>
						</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" onchange="initZyList();initBjList()"
								style="width:180px"  styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>רҵ</th>
						<td>
							<html:select property="zydm" onchange="initBjList()" 
								style="width:180px" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
						<th>�༶</th>
						<td>
							<html:select property="bjdm" 
								style="width:180px" styleId="bj">
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
						<input type="hidden" name="go" value="a" />
						<button type="button" id="search_go" onclick="refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_zhszcphzManage&go=go');this.disabled=true;">
							�� ѯ
						</button>
						 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							�� ��
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
				<table width="99%" id="rsTable tablenowrap" class="dateline">
					<thead>
						<tr align="center" style="cursor:hand">					
							<logic:iterate id="tit" name="topTr" offset="0" scope="request">
								<td id="${tit.en}" onclick="tableSort(this)">
									${tit.cn}
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							style="cursor:hand;">										
							<td align=center>
								<logic:iterate id="v" name="s" offset="0" length="1">
								  <bean:write name="v" />
								</logic:iterate>
							</td>																
							<logic:iterate id="v" name="s" offset="1">
								<td align=center>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</table>
				</div>
				<!--��ҳ��ʾ-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzyqxyZhszcpActionForm"></jsp:include>
				<!--��ҳ��ʾ-->
		</logic:notEmpty>
		<div id="tmpdiv">
			
		</div>
		
		<script type="text/javascript" src="js/bottomButton.js"></script>
		</div>
	</html:form>
</body>
</html>
