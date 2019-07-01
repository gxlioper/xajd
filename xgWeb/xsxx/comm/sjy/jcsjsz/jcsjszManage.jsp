<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʾ������ҳ��
		function showSzGuide(){
		
			var url = "/xgxt/sjyJcsjsz.do?method=jcsjszGuide";
				url+= "&step=step1";
				
			showTopWin(url,800,650);
		}
		
		//��ʾ�����޸�ҳ��
		function showSzUpdate(){
		
			if(curr_row != null){
			
				var zd = curr_row.getElementsByTagName('input')[0].value;
				var url = "/xgxt/sjyJcsjsz.do?method=jcsjszUpdate";
					url+= "&zd="+zd;
					
				showTopWin(url,480,450);
			}else{
				alert("��ѡ����Ҫ���õ��ֶ�^_^");
				return false;
			}
		}
		
		//�����洢����
		function createProcedure(){
			if (confirm("��Ҫ�������ô����洢���̣���ȷ�����á�\nע��Ӱ��洢���̵�������ҪΪ��ѧ��Ϊ׼��")) {
				saveUpdate('/xgxt/sjyJcsjsz.do?method=jcsjszManage&doType=create','');
			}
		}
		
		//��ѯ
		function searchRs(){
			allNotEmpThenGo('/xgxt/sjyJcsjsz.do?method=jcsjszManage');
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
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
				������ǵ�һ�ν��иù���ģ������ã������������򵼡����ܣ�������Ѹ���˽�ù��ܡ�<br/>	
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/sjyJcsjsz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="hadRs" id="hadRs" value="${hadRs }"/>

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="showSzGuide();return false;"
									class="btn_yl">������</a>
							</li>
							<li>
								<a href="#"
									onclick="showSzUpdate();return false;"
									class="btn_sz">�ֶ�����</a>
							</li>
							<li>
								<a href="#"
									onclick="createProcedure();return false;"
									class="btn_sx">�����洢����</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										
										<input type="hidden" name="go" value="a"/>
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('sjy-xgwz-lrxz-wkxz-lrxs-sfqy-zdm-ymxs-xsmc');">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
<%--								<th>--%>
<%--									����Դ--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<html:select property="search_sjy" style="width: 150px" styleId="sjy">--%>
<%--										<html:option value=""></html:option>--%>
<%--										<html:options collection="jbszSjyList" property="en" labelProperty="cn" />--%>
<%--									</html:select>--%>
<%--								</td>--%>
								<th>
									��ʾ����
								</th>
								<td>
									<html:text property="search_ymxs" style="" maxlength="20" styleId="ymxs"
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>
								<th>
									��ѧ��Ϊ׼
								</th>
								<td>
									<html:select property="search_xgwz" style="width: 150px" styleId="xgwz">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									¼������
								</th>
								<td>
									<html:select property="search_lrxz" style="width: 150px" styleId="lrxz">
										<html:option value=""></html:option>
										<html:options collection="jbszLrxzList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									�ɷ�Ϊ��
								</th>
								<td>
									<html:select property="search_wkxz" style="width: 150px" styleId="wkxz">
										<html:option value=""></html:option>
										<html:options collection="jbszWkxzList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									¼����ʽ
								</th>
								<td>
									<html:select property="search_lrxs" style="width: 150px" styleId="lrxs">
										<html:option value=""></html:option>
										<html:options collection="jbszLrxsList" property="en" labelProperty="cn" />
									</html:select>		
								</td>
								<th>
									�Ƿ�����
								</th>
								<td>
									<html:select property="search_sfqy" style="width: 150px" styleId="sfqy">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
<%--							<!-- ������ -->--%>
<%--							<tr>--%>
<%--								<th>--%>
<%--									�ֶ���--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<html:text property="search_zdm" style="" maxlength="20" styleId="zdm"--%>
<%--										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>					--%>
<%--								</td>--%>
<%--								<th>--%>
<%--									--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									--%>
<%--								</td>--%>
<%--								<th>--%>
<%--									--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									--%>
<%--								</td>--%>
<%--							</tr>--%>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ����� end -->
			
			<!-- ��ѯ���-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						��ѯ���
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue">ҳ����ʾ���Ϊ�գ������ֶ���Ϊ׼</font> 
						</logic:notEmpty>
					</span>
				</h3>
				
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<logic:iterate id="tit" name="topTr" offset="1" length="9">
								<td id="<bean:write name="tit" property="en"/>"
<%--									onclick="tableSort(this)" --%>
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:equal name="hadRs" value="yes">
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<logic:iterate id="v" name="s" offset="1" length="1">
									<td align="left">
										${v }
										<input type="hidden" 
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="2">
									<td align="left">
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:equal>
					<!--���� end-->
					<!-- ������ -->
					<%@ include file="/comm/noRows.jsp"%>
					<!-- ������ end-->
				</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=sjyJcsjszForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<!-- ��ʾ��Ϣend -->
		</html:form>
	</body>
</html>