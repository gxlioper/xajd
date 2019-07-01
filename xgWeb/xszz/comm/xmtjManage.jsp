<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript">
		
		//�����Ŀ
		function shxm(){
			if(curr_row == null){
				alert('��ѡ��Ҫ��˵���Ŀ��');
				return false;
			}
			
			var iskns = $('iskns').value;
			var xmdm = curr_row.getElementsByTagName('input')[0].value;
			var url = "/xgxt/commXszz.do?method=xmshManage&iskns="+iskns;
			url += "&xmdm="+xmdm;
			
			refreshForm(url);
		}
		
		//�޸���Ŀ���
		function chXmlb(){
			var url = "/xgxt/commXszz.do?method=xmtjManage";
			refreshForm(url);
		}
		</script>
	</head>

	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commXszz">
			<!-- ������ -->
			<html:hidden property="iskns" styleId="iskns"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }"/>
			<input type="hidden" name="go" value="a" />
			<button type="button" class="button2" style="height:25px;display : none" id="search_go"
				onclick="allNotEmpThenGo('/xgxt/commXszz.do?method=xmtjManage')">
				���ذ�ť
			</button>
			<!-- ������ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" 
									onclick="shxm();"
									class="btn_sh">���</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- ��ť end-->		
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									��ǰѧ��								
								</th>
								<td>
									<html:select property="xn" style="width:120px" styleId="xn" disabled="true">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>		
								</td>
								<th>
									��ǰ���
								</th>
								<td>
									<html:select property="nd" style="" styleId="nd" disabled="true">
										<html:options collection="ndList" property="nd" labelProperty="nd" />
									</html:select>
								</td>
								<th>
									��ǰѧ��
								</th>
								<td>
									<html:select property="xq" style="" styleId="xq" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									��ǰ����
								</th>
								<td>
									<html:text property="sqsjCn" disabled="true"/>
								</td>
								<!-- ����������ģ�鲻Ҫ����������� �� 2011.7.12 qph -->
								<logic:notEqual value="yes" property="iskns" name="xszzTyForm">
									<th>
										��Ŀ���
									</th>
									<td>
										<html:select property="xmlb" style="" styleId="xmlb" onchange="chXmlb()">
											<html:option value=""></html:option>
											<html:options collection="xmlbList" property="en" labelProperty="cn" />
										</html:select>
									</td>
								</logic:notEqual>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> �������Ŀ&nbsp;&nbsp; 
							<font color="red">��ע���ϼ������Ѿ����ͨ����ѧ������������б��г��֣����ڽ����ѯ�����в鿴��</font>
							<logic:empty name="rsList">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rsList">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>	
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							</tbody>
								<logic:iterate name="rsList" id="rs" indexId="index">
									<tr onclick="rowOnClick(this);" 
										style="cursor:hand" 
										ondblclick="">
										<td>
											${rs.xmmc }		
											<input type="hidden" name="xmdm" value="${rs.xmdm }"/>			
										</td>
										<td>
											${rs.xmlb }													
										</td>	
										<td>
											${rs.sqrs }													
										</td>	
										<td>
											${rs.xshrs }													
										</td>					
										<td>
											${rs.shrs }	
										</td>
									</tr>
								</logic:iterate>
							<!--���� end-->
							</tbody>
						</table>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
							 <script type="text/javascript">
						      $('choose').className="hide";
						     </script>
						<!--��ҳend-->
					</logic:notEmpty>
					<!-- ��ѯ��� end-->
				</div>
			</div>
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
	</body>
</html>