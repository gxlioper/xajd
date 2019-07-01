<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script type="text/javascript">	
	
	//showidArr Ҫ��ʾԪ�ص�id���ϣ�hiddenidArr Ҫ����Ԫ�ص�id����
		function changeDisplay(showidArr,hiddenidArr){
			for(var i=0; i<showidArr.length; i++){
				if($(showidArr[i])){
					$(showidArr[i]).style.display = "";
				}
			}
			for(var i=0; i<hiddenidArr.length; i++){
				if($(hiddenidArr[i])){
					$(hiddenidArr[i]).style.display = "none";
				}
			}
		}
	
	//�ı��ѯ���������ͣ�
	function chLx(){
	
		var lx = $("lx").value;
		
		var showEle = [];
		var hiddenEle = [];
			
		if(lx == "ѧ��"){
			showEle = ["stuTr"];
			hiddenEle = ["teaTr","admTr"];				
		}else if(lx == "��ʦ"){
			showEle = ["teaTr"];
			hiddenEle = ["stuTr","admTr"];	
		}else if(lx == "������"){
			showEle = ["admTr"];
			hiddenEle = ["stuTr","teaTr"];	
		}else{
			showEle = ["stuTr"];
			hiddenEle = ["teaTr","admTr"];		
		}
		changeDisplay(showEle,hiddenEle);
	}
		
	//�ö�����
	function zdLy(pkValue,flag){
	
		var msg = "";
		
		if("true" == flag){
			msg = "�ö�������Ϣ��";
		}else{
			msg = "ȡ���ö�������Ϣ��";
		}
		
		if(confirm(msg)){
			var url = "/xgxt/gzdxRcsw.do?method=hflyManage&doType=zd";
				url+="&flag="+flag;
			if($("pkValue")){
				$("pkValue").value = pkValue;
			}
			showTips('���������У���ȴ�......');
			saveUpdate(url,"");
		}
	}
	
	</script>
	</head>
	<body onload="chLx();">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
	
	
		<html:form action="/gzdxRcsw">
			<%@ include file="hiddenValue.jsp"%>
			
			
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="isAdmin" value="true">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/gzdxRcsw.do?method=hflyManage','del')"
									class="btn_sc"> ɾ�� </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<logic:equal name="userType" value="xx">
						<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/gzdxRcsw.do?method=hflyManage','del')"
									class="btn_sc"> ɾ�� </a>
							</li>
						</ul>
					</div>
				</logic:equal>
			<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/gzdxRcsw.do?method=hflyManage&doType=go');">
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
									��������
								</th>
								<td>
									<html:select property="lylx" style="" onchange="" >
											<html:options collection="lylxList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
								<th>
									���
								</th>
								<td>
									<html:select property="lx" style="" onchange="chLx()" >
											<html:option value=""></html:option>
											<html:options collection="lxList" property="en" labelProperty="cn" />
										</html:select>
								</td>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" style="" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
											<!-- ��ʦ�û� -->
											<logic:equal name="isTeacher" value="true">
												<html:select property="xydm" style="width:200px" styleId="xy" onchange="initZyList();initBjList();">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm" labelProperty="xymc" />
												</html:select>
											</logic:equal>
											<!-- �ǽ�ʦ�û� -->
											<logic:equal name="isTeacher" value="false">
												<html:hidden property="xydm"/>
												<html:select property="xydm" style="width:200px" styleId="xy" disabled="true">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
											</logic:equal>
										</logic:equal>
										<!-- ��<bean:message key="lable.xsgzyxpzxy" />�û� -->
										<logic:notEqual name="userType" value="xy">
											<html:select property="xydm" style="width:200px" styleId="xy" onchange="initZyList();initBjList();">
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
									<html:select property="zydm" style="width:200px" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" style="width:200px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
								</td>
								
							</tr>
								<tr>
									<th>ѧ��(����)</th>
									<td>
										<html:text property="lyr" style="" maxlength="20"/>
									</td>
									<th>����</th>
									<td><html:text property="xm" style="" maxlength="20"/></td>
									<th></th>
									<td></td>
								</tr>
								<tr style="display:none" id="teaTr">
									<th>����<bean:message key="lable.xsgzyxpzxy" /></th>
									<td><logic:equal name="userType" value="xy">
											<html:hidden property="bmdm"/>
											<html:select property="bmdm" style="width:200px" styleId="bmdm" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
											</html:select>
										</logic:equal>
										<!-- ��<bean:message key="lable.xsgzyxpzxy" />�û� -->
										<logic:notEqual name="userType" value="xy">
											<html:select property="xydm" style="width:200px" styleId="xy" onchange="">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:notEqual></td>
										<th></th><td></td>
										<th></th><td></td>
								</tr>
								<tr style="display:none" id="admTr">
									<th>��������</th>
									<td align="left">
										<!-- <bean:message key="lable.xsgzyxpzxy" />�û� -->
										<logic:equal name="userType" value="xy">
											<html:hidden property="bmdm"/>
											<html:select property="bmdm" style="width:200px" styleId="bmdm" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
											</html:select>
										</logic:equal>
										<!-- ��<bean:message key="lable.xsgzyxpzxy" />�û� -->
										<logic:notEqual name="userType" value="xy">
											<html:select property="bmdm" style="width:200px" styleId="bmdm" onchange="">
												<html:option value=""></html:option>
												<html:options collection="bmList" property="bmdm"
													labelProperty="bmmc" />
											</html:select>
										</logic:notEqual>
									</td>
								</tr>
							</tbody>
						</table>
						</div>
						
						
						<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
										<!-- ����ԱȨ�� -->
										<logic:equal name="isAdmin" value="true">
											<td align="center">
												<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>
											</td>
										</logic:equal>
										<td>
											�ö�
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" length="6">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
										<td>
											ִ�в���
										</td>
									</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" >
										<!-- ����ԱȨ�� -->
										<logic:equal name="isAdmin" value="true">
											<td align="center">
												<input type="checkbox" id="checkVal" name="checkVal" 
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
											</td>
										</logic:equal>
										<logic:iterate id="v" name="s" offset="8" length="1">
											<td align="center">
												<!-- �Ƿ����ö� 0���� ��0���� -->
												<logic:notEqual name="v" value="0"><font color="red">��</font></logic:notEqual>
												<logic:equal name="v" value="0"></logic:equal>
											</td>
										</logic:iterate>	
										<logic:iterate id="v" name="s" offset="1" length="6">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									<td align="left">
										<logic:iterate id="v" name="s" offset="0" length="1">
										<a href="#" 
											onclick="showTopWin('/xgxt/gzdxRcsw.do?method=xslyUpdate&doType=view&pk=<bean:write name='v'/>','800','600')">
											�鿴
										</a>
										</logic:iterate>
										<logic:notEqual name="userType" value="stu">
											<logic:iterate id="v" name="s" offset="3" length="1">
												<logic:equal name="v" value="ѧ��">
													<a href="#" 
														onclick="showTopWin('/xgxt/gzdxRcsw.do?method=xslyUpdate&doType=update&pk=<logic:iterate id="x" name="s" offset="0" length="1"><bean:write name='x'/></logic:iterate>','800','600')">
														�ظ�
													</a>
												</logic:equal>
											</logic:iterate>
										</logic:notEqual>
										<logic:equal name="isAdmin" value="true">
										<logic:iterate id="v" name="s" offset="8" length="1">
											<!-- �Ƿ����ö� 0���� ��0���� -->
											<logic:notEqual name="v" value="0">
												<logic:iterate id="x" name="s" offset="0" length="1">
													<a href="#" onclick="zdLy('<bean:write name='x'/>','false')">
														ȡ���ö�
													</a>
												</logic:iterate>
											</logic:notEqual>
											<logic:equal name="v" value="0">
												<logic:iterate id="x" name="s" offset="0" length="1">
													<a href="#" onclick="zdLy('<bean:write name='x'/>','true')">
														�ö�
													</a>
												</logic:iterate>
											</logic:equal>
										</logic:iterate>						
										</logic:equal>
									</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<!--��ҳ��ʾ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
						<!--��ҳ��ʾ-->
					</logic:notEmpty>
				</div>
					<!-- ����ԱȨ�� -->
				</div>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>
	</body>
</html>
