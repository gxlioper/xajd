<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript">
			function checkSjshzt(){
				var checkVal = document.getElementsByName('checkVal');
				var sjshztArr = document.getElementsByName('sjshztArr');
				var check = 0;
				for(var i=0;i<checkVal.length;i++){
					if(checkVal[i].checked){
						if(sjshztArr[i].value!='δ���'){
							check++;
						}
					}
				}
				if(check!=0){
					alert('��ѡ��ļ�¼�������ڽ����ϼ���˴�������ݣ������ٴ���ˣ�');
					return false;
				}else{
					return true;
				}
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ճ����� - ��У֤�� - ��У֤�����</a>
			</p>
		</div>
		<html:form action="/zzlgdx_rcsw" method="post">
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<%@ include file="/comm/hiddenValue.jsp"%>

			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<!-- �人��ҵ���ϼ�����������֤ -->
							<logic:equal value="11654" name="xxdm" scope="session">
								<li>
									<a href="#"
										onclick="if(checkSjshzt()){sumitInfo('/xgxt/zzlgdx_rcsw.do?method=zxzmSh&shzt=��ͨ��','sh');}"
										class="btn_shtg">ͨ��</a>
								</li>
								<li>
									<a href="#"
										onclick="if(checkSjshzt()){sumitInfo('/xgxt/zzlgdx_rcsw.do?method=zxzmSh&shzt=��ͨ��','sh');}"
										class="btn_shbtg"> ��ͨ�� </a>
								</li>
							</logic:equal>
							<logic:notEqual value="11654" name="xxdm" scope="session">
								<li>
									<a href="#"
										onclick="sumitInfo('/xgxt/zzlgdx_rcsw.do?method=zxzmSh&shzt=��ͨ��','sh')"
										class="btn_shtg">ͨ��</a>
								</li>
								<li>
									<a href="#"
										onclick="sumitInfo('/xgxt/zzlgdx_rcsw.do?method=zxzmSh&shzt=��ͨ��','sh')"
										class="btn_shbtg"> ��ͨ�� </a>
								</li>
							</logic:notEqual>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/zzlgdx_rcsw.do?method=zxzmSh&go=go')">
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
									�꼶
								</th>
								<td>
									<html:select property="nj" onchange="initBjList()" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="xh" styleId="xh"
										style="inputtext" styleClass="inputtext"></html:text>
								</td>
								<th>
									�Ա�
								</th>
								<td>
									<html:select property="xb" style="width:150px;">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="Ů">Ů</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									���֤��
								</th>
								<td>
									<html:text property="sfzh" styleId="sfzh" maxlength="18"
										styleClass="inputtext"></html:text>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" styleClass="inputtext"></html:text>
								</td>
								<th>
									��Ŀ����
								</th>
								<td>
									<html:select property="xmmc" style="width:150px;">
										<html:options collection="xmmcList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:150px"
										onchange="initZyList();initBjList()" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList()"
										style="width:150px" styleId="zy">
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
							<!-- �人��ҵ -->
							<logic:equal value="11654" name="xxdm" scope="session">
								<tr>
									<th>���״̬</th>
									<td colspan="5">
										<logic:equal value="true" name="isFdy" scope="session">
											<html:select property="fdysh" style="width:150px;">
												<html:option value=""></html:option>
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</logic:equal>
										<logic:notEqual value="true" name="isFdy" scope="session">
											<logic:equal value="xy" name="userType" scope="session">
												<html:select property="xysh" style="width:150px;">
													<html:option value=""></html:option>
													<html:options collection="shztList" property="en"
														labelProperty="cn" />
												</html:select>
											</logic:equal>
											<logic:notEqual value="xy" name="userType" scope="session">
												<html:select property="xxsh" style="width:150px;">
													<html:option value=""></html:option>
													<html:options collection="shztList" property="en"
														labelProperty="cn" />
												</html:select>
											</logic:notEqual>
										</logic:notEqual>
									</td>
								</tr>
							</logic:equal>
							<logic:notEqual value="11654" name="xxdm" scope="session">
								<tr>
									<logic:equal value="xy" name="userType" scope="session">
										<th>
											�������
										</th>
										<td>
											<html:select property="shlx" style="width:150px;">
												<html:option value=""></html:option>
												<html:options collection="shlxList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
											���
										</th>
										<td>
											<html:select property="xysh" style="width:150px;">
												<html:option value=""></html:option>
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									</logic:equal>
									<logic:notEqual value="xy" name="userType" scope="session">
										<th>
											ѧУ���
										</th>
										<td>
											<html:select property="xxsh" style="width:150px;">
												<html:option value=""></html:option>
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									</logic:notEqual>
								</tr>
							</logic:notEqual>
						</tbody>
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
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ����¼���Խ��е�����ˣ�������ͷ��������</font> 
						</span>
					</h3>
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="showInfo('/xgxt/zzlgdx_rcsw.do?method=zxzmShow','sh','800','600');"
										style="cursor:hand;">
										<td align=center>
											<input type="checkbox" id="pk" name="checkVal"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											
											<!-- �人��ҵ���ϼ����״̬�������ж��ϼ�����Ƿ��Ѿ������֮�У�ֻ���ϼ���˴���δ���״̬�µļ�¼����ˣ� -->
											<logic:equal value="11654" name="xxdm" scope="session">
											<logic:equal value="true" name="isFdy" scope="session">
												<logic:iterate id="v" name="s" offset="10" length="1">
													<input type="hidden" id="sjshztArr" name="shshztArr" value="${v }"/>
												</logic:iterate>
											</logic:equal>
											<logic:notEqual value="true" name="isFdy" scope="session">
												<logic:equal value="xy" name="userType" scope="session">
													<logic:iterate id="v" name="s" offset="10" length="1">
														<input type="hidden" id="sjshztArr" name="shshztArr" value="${v }"/>
													</logic:iterate>
												</logic:equal>
												<logic:notEqual value="xy" name="userType" scope="session">
													<logic:iterate id="v" name="s" offset="10" length="1">
														<input type="hidden" id="sjshztArr" name="shshztArr" value="δ���"/>
													</logic:iterate>
												</logic:notEqual>
											</logic:notEqual>
											</logic:equal>
											
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<!--��ҳ��ʾ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=rcswZzlgdxActionForm"></jsp:include>
						<!--��ҳ��ʾ-->
					</logic:notEmpty>
				</div>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("�����ɹ���");
	         	document.getElementById('search_go').click();
	         	</script>
	         	
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
