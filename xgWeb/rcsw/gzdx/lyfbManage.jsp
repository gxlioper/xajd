<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/rcsw/gzdx/lyfbManage.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/sfXzZxs.js'></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript">	
		function showLyInfo(){
			var userType = $("userType").value;
			if(userType != "stu"){
				$("lydiv").style.height = "400px";
			}
		}
		</script>
	</head>
	<body onload="setAllZxs()">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/gzdxRcsw">
			<input type ="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
			<input type ="hidden" name="userType" id="userType" value="${userType }"/>
			
			<%@ include file="/comm/hiddenValue.jsp"%>


			<div class="formbox">
				<h3 class="datetitle_01">
					<logic:empty name="rs">
						<p align="center">
							<!-- ѧ������ -->
							<logic:equal name="mklx" value="ly">��������Ϣ</logic:equal>
							<!-- ����֪ͨ -->
							<logic:equal name="mklx" value="tz">��֪ͨ��Ϣ</logic:equal>

						</p>
					</logic:empty>
				</h3>

				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
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
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<logic:iterate id="v" name="s" offset="1" length="1">
										<td align="left">
											<input type="hidden" value="<bean:write name="v" />" />
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<a href="#"
												onclick="showTopWin('/xgxt/gzdxRcsw.do?method=xslyUpdate&doType=view&pk=<bean:write name='v'/>','800','600')">
												�鿴������ </a>
										</logic:iterate>
										&nbsp;&nbsp;
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					<!--��ҳ��ʾ-->
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
					<script type="text/javascript">
						$('choose').className="hide";
					</script>
					<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button"
										onclick="saveUpdate('/xgxt/gzdxRcsw.do?method=xslyManage&doType=save','lylx-lybt-lynr')"
										id="buttonSave">
										�ύ
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" type="reset">
										����
									</button>
									<!-- ѧ������ -->
									<logic:equal name="mklx" value="ly">
										<button type="button" style="display: none" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/gzdxRcsw.do?method=xslyManage');">
										</button>
									</logic:equal>
									<!-- ����֪ͨ -->
									<logic:equal name="mklx" value="tz">
										<button type="button" style="display: none" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/gzdxRcsw.do?method=fbtzManage');">
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>����
							</th>
							<td width="80%">
								<html:select property="lylx" styleId="lylx" style=""
									onchange="getXzfw()">
									<html:options collection="lylxList" property="dm"
										labelProperty="mc" />
								</html:select>
								<span id="xzhffw" style="display:none"> 
								 <input type="radio" name="hffw" id="hffw" value="0" 	onclick="setAllZxs()" checked />���� 
								 <input type="radio" name="hffw" id="hffw" value="1"   onclick="$('xlzxs').style.display='';$('guolv').style.display=''" />ѡ�� <!-- ������ѯʦ�б� --> <html:select
										property="lydx" styleId="xlzxs"  style="display:none">
										<html:options collection="zxsList" property="dm"
											labelProperty="mc" />
									</html:select>
									 <button type="button" name="guolv" id="guolv" value="����" style="display:none" onclick="showLyfbDiv();" > ���� </button>
								</span>
								<html:select property="bmdm" style="display : none"
									styleId='bmdm' onchange="">
									<html:option value=""></html:option>
									<html:options collection="bmdmList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����
							</th>
							<td>
								<html:text property="lymc" styleId="lybt" style="width: 100%"
									maxlength="50" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�������ݣ�
								<br />
								<font color="red">(������500��)</font>
							</th>
							<td>
								<html:textarea property="lynr" style="width: 100%;height: 150px"
									onblur="chLeng(this,500)" />
							</td>
						</tr>
					</tbody>

				</table>
			
				<logic:present name="result">
					<logic:equal value="true" name="result">
						<script>
						alert('�����ɹ���');
					</script>
					</logic:equal>
					<logic:equal value="false" name="result">
						<script>
						alert('����ʧ�ܣ�');
					</script>
					</logic:equal>
				</logic:present>
		</html:form>
	</body>
</html>
