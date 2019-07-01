<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		// --------�ı�����������Ϣ---------
		function changeXsdaInfo(){
			var pkValue=document.getElementsByName("pkValue");
			var pkV="";
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					n++;
					pkV=pkValue[i].value;
				}
			}
			
			if(n==0){
				alert("��ѡ����Ҫ�޸�����������Ϣ��ѧ��!");
				return false;
			}else if(n==1){
				changeByOne(pkV);
			}else {
				changeByCheck(pkValue);
			}
		}
		
		// --------�ı�����������Ϣ BYѡ�е�---------
		function changeByCheck(){
			var html="<table width='100%'>";
			var dmArr=document.getElementsByName("dmArr");
			var mcArr=document.getElementsByName("mcArr");
			for(i=1;i<=dmArr.length;i++){
				if(i%3==1 && i!=1){
				
					html+="<tr>";
				}
				html+="<td width='33%'>";
				html+="<input type='checkBox' name='xsdaInfo' id='cb_en"+i+"' value='"+dmArr[i-1].value+"'";
				
				html+=" /><span title='"+mcArr[i-1].value+"'>"+mcArr[i-1].value+"</span>"
				
				html+="</td>";
				if(i%3==0){
				
					html+="</tr>"
				}
			}
			
			var dmLen=dmArr.length;
			var len=0;
			len=dmLen%3;
			hei=dmLen/3;
			if(len!=0){
				for(i=0;i<3-len;i++){
					html+="<td>&nbsp;</td>";
				}
				html+="</tr>";
			}
			if(hei!=0){
				for(i=0;i<3-hei;i++){
					html+="<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
				}
			}
			
			html+="</table>";
		
			$("dadm").innerHTML=html
			tipsWindown("ϵͳ��ʾ","id:plszDiv","350","300","true","","true","id");
		}
		// --------�ı�����������Ϣ BY����---------
		function changeByOne(pk){

			showTopWin("/xgxt/guizdxDagl.do?method=zxdawhUpdate&doType=add&xh="+pk,600,450);
		}
		
		// -------��ʾ------------
		function showView(pk){

			showTopWin("/xgxt/guizdxDagl.do?method=dacxUpdate&xh="+pk,600,450);
		}
		
		function save(){
			

			refreshForm("/xgxt/guizdxDagl.do?method=zxdawhManage&doType=save");
		
		}
		
		</script>
	</head>
	<body>

		<html:form action="/guizdxDagl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName"
				value="${userName}" />
			<input type="hidden" name="realTable" id="realTable"
				value="mdqr_xmnrb" />
			<input type="hidden" name="tabName" id="tabName" value="mdqr_xmnrb" />
			<input type="hidden" name="viewName" id="viewName"
				value="view_mdqr_xmnrb" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<logic:iterate name="lxList" id="lx" indexId="index">
				<input type="hidden" name="dmArr" styleId="dm_${index}"
					value="${lx.dm}" />
				<input type="hidden" name="mcArr" styleId="mc_${index}"
					value="${lx.mc}" />
			</logic:iterate>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/guizdx_dagl_dacx.do')">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>

						<tbody>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="xh" styleId="xh" />
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" />
								</td>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="xydm" styleId="xy" disabled="true"
											value="${userDep }" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" styleId="zy" style="width:150px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rsArrList">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							<font color="blue">��ʾ��������ͷ��������;˫��һ����¼���Բ鿴��ϸ��Ϣ</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<%--								<td width="15px">--%>
								<%--									<input type="checkbox" name="all" value="all" onclick="chec()" />--%>
								<%--								</td>--%>
								<logic:iterate id="tit" name="topTr" offset="0" indexId="index">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rsArrList">
								<logic:iterate name="rsArrList" id="s">
									<tr onclick="rowMoreClick(this,'',event);"
										ondblclick="showView('<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>')"
										style="cursor:hand">
										<%--										<td width="15px">--%>
										<%--											<input type="checkbox" name="pkValue" id="pkV"--%>
										<%--												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"--%>
										<%--												<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate> />--%>
										<%--										</td>--%>
										<logic:iterate id="v" name="s" offset="0">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<%@ include file="/comm/noRows.jsp"%>

						</tbody>
					</table>
				</div>
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xsxxAhjzgyxyForm"></jsp:include>
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<div id="plszDiv" style="display:none">
					<div class="open_win01">
						<table align="center" class="formlist">
							<thead>
								<tr>
									<th colspan="2">
										<span>��������</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="2">
										<div id="dadm">
										</div>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2">
										<div class="bz">
											"
											<span class="red">*</span>"Ϊ������
										</div>
										<div class="btn">
											<button type="button" name="�� ��" onclick="save();return false;">
												ȷ ��
											</button>
											<button type="button" name="ȡ��" onclick="closeWindown();return false;">
												ȡ ��
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:present name="result">
			<script>
				alert('${message}');
				
			</script>
		</logic:present>
	</body>
</html>
