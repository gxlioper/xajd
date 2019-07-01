<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript">
			function setCompTab(id,otherOne,otherTwo){
				$(id).className="ha";
				$('flg').value=id;
				$(otherOne).className="";
				$(otherTwo).className="";
				refreshForm('jywebUseCheckSession.do?method=tjManage');
			}
			
			function onloadTab(){
				var flg = $('flg').value;
				
				if ('zpxx'==flg){
					$('zpxx').className="ha";
					$('dwxg').className="";
					$('stu').className="";
				} else if('stu'==flg){
					$('stu').className="ha";
					$('dwxg').className="";
					$('zpxx').className="";
					
					setStuTj($('tjfs').value);
				}
			}
			
			
			function setStuTj(text){
				
				if ('xy' == text){
					$('zy').disabled=true;
					$('bj').disabled=true;
					$('xm').disabled=true;
					$('sfzh').disabled=true;
				} else if ('zy' == text){
					$('zy').disabled=false;
					$('bj').disabled=true;
					$('xm').disabled=true;
					$('sfzh').disabled=true;
				} else if ('bj' == text){
					$('zy').disabled=false;
					$('bj').disabled=false;
					$('xm').disabled=true;
					$('sfzh').disabled=true;
				} else {
					$('zy').disabled=false;
					$('bj').disabled=false;
					$('xm').disabled=false;
					$('sfzh').disabled=false;
				}
			
			}
			
		</script>
	</head>
	<body onload="onloadTab();" style="height:800px">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ͳ�Ʒ���</a>
			</p>
		</div>
	
		<html:form action="/jyweb">
			<input type="hidden" name="flg" value="${flg }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />

			<div class="compTab">
				<div class="comp_title">
					<ul>
						<li class="ha" id="dwxg">
							<a href="javascript:setCompTab('dwxg','zpxx','stu');"><span>��λ��Ϣͳ��</span>
							</a>
						</li>
						<li id="zpxx">
							<a href="javascript:setCompTab('zpxx','dwxg','stu');"><span>��Ƹ��Ϣͳ��</span>
							</a>
						</li>
						<li id="stu">
							<a href="javascript:setCompTab('stu','dwxg','zpxx');""><span>ѧ�����ͳ��</span>
							</a>
						</li>
					</ul>
				</div>
				<div class="comp_con">
					<div class="toolbox">
						<!-- ��ť -->
<%--						<div class="buttonbox">--%>
<%--							<ul>--%>
<%--							</ul>--%>
<%--						</div>--%>
						<!-- �������� -->
						<div class="searchtab">
							<table width="100%" border="0">
								<tfoot>
									<tr>
										<td colspan="6">
											<div class="btn">
												<button class="btn_cx" id="search_go"
													onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=tjManage&doType=query')">
													�� ѯ
												</button>
												<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
													�� ��
												</button>
											</div>
										</td>
									</tr>
								</tfoot>
								<tbody>
									<logic:equal value="dwxg" name="flg">
										<tr>
											<th>
												ע��ʱ��
											</th>
											<td colspan="3">
												<html:text property="querygreaterequal_zcsj"
													onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="querygreaterequal_zcsj"
													onblur="dateFormatChg(this)"
												></html:text>
												-
												<html:text property="querylessequal_zcsj"
													onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="querylessequal_zcsj"
													onblur="dateFormatChg(this)"
												></html:text>
											</td>
										</tr>
									</logic:equal>
									<logic:equal value="zpxx" name="flg">
										<tr>
											<th>
												����ʱ��
											</th>
											<td colspan="3">
												<html:text property="querygreaterequal_fbsj"
													onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="querygreaterequal_fbsj"
													onblur="dateFormatChg(this)"
												></html:text>
												-
												<html:text property="querylessequal_fbsj"
													onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="querylessequal_fbsj"
													onblur="dateFormatChg(this)"
												></html:text>
											</td>
										</tr>
									</logic:equal>
									<logic:notEqual value="stu" name="flg">
										<tr>
											<th>
												��λ����
											</th>
											<td>
												<html:select property="dwxz">
													<html:options collection="dwxzList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
											<th>
												������ҵ
											</th>
											<td>
												<html:select property="hyfl">
													<html:options collection="hyflList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
										</tr>
									</logic:notEqual>

									<logic:equal value="stu" name="flg">
										<tr>
											<th>
												�꼶
											</th>
											<td>
												<html:select property="nj"
													onchange="initZyList();initBjList()">
													<html:option value=""></html:option>
													<html:options collection="njList" property="nj"
														labelProperty="nj" />
												</html:select>
											</td>
											<th>
												����
											</th>
											<td>
												<html:text property="xm" maxlength="20"></html:text>
											</td>
											<th>
												���֤��
											</th>
											<td>
												<html:text property="sfzh" maxlength="20"></html:text>
											</td>
										</tr>
										<tr>
											<th>
												<bean:message key="lable.xb" />
											</th>
											<td>
												<html:select property="xydm"
													onchange="initZyList();initBjList()" styleId="xy"
													style="width:200px">
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
													styleId="zy" style="width:200px">
													<html:option value=""></html:option>
													<html:options collection="zyList" property="zydm"
														labelProperty="zymc" />
												</html:select>
											</td>
											<th>
												�༶
											</th>
											<td>
												<html:select property="bjdm" styleId="bj"
													style="width:200px">
													<html:option value=""></html:option>
													<html:options collection="bjList" property="bjdm"
														labelProperty="bjmc" />
												</html:select>
											</td>
										</tr>
										<tr>
											<th>
												ͳ�Ʒ�ʽ
											</th>
											<td>
												<html:select property="tjfs"
													onchange="setStuTj(this.value);">
													<html:option value="stu">��ѧ��ͳ��</html:option>
													<html:option value="xy">��<bean:message key="lable.xb" />ͳ��</html:option>
													<html:option value="zy">��רҵͳ��</html:option>
													<html:option value="bj">���༶ͳ��</html:option>
												</html:select>
											</td>
											<th>
												ʱ���
											</th>
											<td colspan="3">
												<html:text property="querygreaterequal_tdrq"
													onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="querygreaterequal_tdrq"
													onblur="dateFormatChg(this)"></html:text>
												-
												<html:text property="querylessequal_tdrq"
													onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="querylessequal_tdrq"
													onblur="dateFormatChg(this)"></html:text>
											</td>
										</tr>

									</logic:equal>

								</tbody>
							</table>
						</div>
					</div>
					
					<!-- From���� start-->
					<!---------------------���--�и�ѡ������ݱ�------------------->
					<div class="formbox">
						<h3 class="datetitle_01">
							<span> ��ѯ���&nbsp;&nbsp; </span>
						</h3>
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr>
										<logic:iterate id="tit" name="topTr" offset="0"
											scope="request">
											<td id="${tit}" >
												${tit}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:notEmpty name="rs">
										<logic:iterate name="rs" id="s">
											<tr>
												<logic:iterate id="v" name="s" offset="0">
													<td>
														${v }
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
										<%
										for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
										%>
										<tr>
											<logic:iterate id="t" name="topTr" offset="0" scope="request">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
										</tr>
										<%
										}
										%>
									</logic:notEmpty>
									<logic:empty name="rs">
										<%
											for (int i = 0; i < (Integer) request
													.getAttribute("maxNum"); i++) {
										%>
										<tr>
											<logic:iterate id="t" name="topTr" offset="0" scope="request">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
										</tr>
										<%
										}
										%>
									</logic:empty>
								</tbody>
							</table>

							<logic:notEqual value="zpxx" name="flg">
								<jsp:include flush="true"
									page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
								<script type="text/javascript">
									$('choose').className="hide";
								</script>
							</logic:notEqual>
					</div>
				</div>
					
		</html:form>
	</body>
</html>
