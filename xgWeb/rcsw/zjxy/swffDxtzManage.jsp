<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/rcsw/rcswFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">	
	
	
	function checkTime(){
		if($("querygreaterequal_ffsj") && $("querylessequal_ffsj")){
			if(checkSearchTj("querygreaterequal_ffsj","querylessequal_ffsj")){
				return true;
			}else{
				return false;
			}
		}
	}
	
	//����֪ͨ����
	function getDxtzfk(){
		refreshForm('/xgxt/zjxyRcsw.do?method=swffDxtzManage&doType=dxtzfk');
		BatAlert.showTips('���ڲ��������Ե�...');
	}
	
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/zjxyRcsw" method="post">
			<input type="hidden" name="viewName" id="viewName" value="view_rcsw_swffrqwh"/>
			<input type="hidden" name="tableName" id="tableName" value="rcsw_swffrqb"/>
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /> </font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
					<!-- ��ť -->
					<logic:equal value="yes" name="writeAble">
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#" onclick="getDxtzfk()" class="btn_fs"> ����֪ͨ���� </a>
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
											<button type="button" class="btn_cx" id="search_go"
													onclick="if(checkTime()){allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffDxtzManage&doType=query');}">
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
										<html:select property="queryequals_xn" style="" onchange="">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:select property="queryequals_xq" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
										���
									</th>
									<td>
										<html:select property="queryequals_nd" style="" onchange="">
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
										<html:select property="queryequals_xmlx" style=""
											styleId="xmlx">
											<html:options collection="xmlxList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										����ʱ��
									</th>
									<td colspan="3">
										<html:text property="querygreaterequal_ffsj"
											styleId="querygreaterequal_ffsj" onblur="dateFormatChg(this)"
											style="cursor:hand;"
											onclick="return showCalendar('querygreaterequal_ffsj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)" style="ime-mode:disabled" />
										----
										<html:text property="querylessequal_ffsj"
											styleId="querylessequal_ffsj" onblur="dateFormatChg(this)"
											style="cursor:hand;"
											onclick="return showCalendar('querylessequal_ffsj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)" style="ime-mode:disabled" />
									</td>
								</tr>
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
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������</font> 
						</span>
					</h3>
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s" indexId="index">
										<tr onclick="rowOnClick(this);" style="cursor:hand"
											>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<td align="center">
													<bean:write name="v" />
													<input type="checkbox" id="checkVal"
														name="primarykey_checkVal" style="display : none"
														value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
												</td>
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="2">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
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
				</div>
			</logic:empty>
		</html:form>
		<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				$("doType").value="";
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				$("doType").value="";
				alert("����ʧ��");
			</script>
		</logic:equal>
		</logic:present>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
