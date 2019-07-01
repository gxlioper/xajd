<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/rcsw/rcswFunction.js"></script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/zjxyRcsw" method="post">
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" />
					</font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
								<tr>
									<td colspan="6">
										<div class="btn">
											<button type="button" class="btn_cx" id="search_go"
												onclick="BatAlert.showTips('��ѯ�����У����Ժ�');allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffJgTjManage');">
												�� ѯ
											</button>
											<input type="hidden" name="go" value="a" />
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
										<html:select property="xn" style="width:150px" onchange="">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:select property="xq" style="width:150px" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
										���
									</th>
									<td>
										<html:select property="nd" style="width:150px" onchange="">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										��Ŀ����
									</th>
									<td>
										<html:select property="xmlx" style="width:150px"
											styleId="xmlx">
											<html:options collection="xmlxList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										��Ŀ����
									</th>
									<td>
										<html:text property="xmmc" styleId="xmmc"/>
									</td>
									<th >
										����ʼʱ��
									</th>
									<td >
										 <html:text  property="kssj"   styleId="kssj" style="width:70px"
										onclick="return showCalendar('kssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />--
										<html:text property="jssj" styleId="jssj"  style="width:70px"
										onclick="return showCalendar('jssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />
									</td>
								</tr>
								</tr>
								<tr>
									<th >
										���������ʼ
									</th>
									<td>
										 <html:text property="ffjskssj" styleId="ffjskssj" style="width:70px"
										onclick="return showCalendar('ffjskssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />--
										<html:text property="ffjsjssj" styleId="ffjsjssj"  style="width:70px"
										onclick="return showCalendar('ffjsjssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />
									</td>
									<th>
									</th>
									<td>
									</td>
									<th>
									</th>
									<td>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="formbox">
						<h3 class="datetitle_01">
							<span> ��ѯ���&nbsp;&nbsp; 
								<logic:empty name="rs">
									<font color="red">δ�ҵ��κμ�¼��</font>
								</logic:empty>
								<logic:notEmpty name="rs">
									<font color="blue">������������ɫ���»������ֿɲ鿴����ѧ��;</font>
								</logic:notEmpty>
							</span>
						</h3>

						<logic:notEmpty name="rs">
						<div class="con_overlfow" >
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand" >
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en" />"
												onclick="tableSort(this)" nowrap >
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s" indexId="index">
										<tr  style="cursor:hand">
											<logic:iterate id="v" name="s" offset="0" length='7'>
												<td align="left" nowrap>
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="7" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#"
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=xffrs&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="8" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#" 
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=yffrs&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="9" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#" 
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=wffrs&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="10" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#" 
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=ytz&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="11" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#" 
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=yfs&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="12" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#" 
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=ypj&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="13" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#" 
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=wpj&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											
										</tr>
									</logic:iterate>
									
								</tbody>
							</table>
						</div>
							<!--��ҳ��ʾ-->
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
							<script type="text/javascript">
								$('choose').className="hide";
							</script>
							<!--��ҳ��ʾ-->
						</logic:notEmpty>
					</div>
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
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
