<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/gygl/wsjc.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
			function modi(url){
				if($("jczc")){
					url+= "&jczc="+$("jczc").value;
				}
				if($("jcsj")){
					url+= "&jcsj="+$("jcsj").value;
				}
				
				if(curr_row != null){
					showTopWin(url + '&xh='+curr_row.getElementsByTagName('input')[0].value
								   + '&xn=' + curr_row.getElementsByTagName('input')[2].value
								   + '&xqmc=' + curr_row.getElementsByTagName('input')[3].value
								   + '&xq=' + curr_row.getElementsByTagName('input')[4].value,800,600);
					return true;
				}else{
					alert('��ѡ��Ҫ�����������У�');
					return false;
				}
			}
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commWsjc">
			<input type="hidden" name="userName" value="${userName }" />
			<logic:equal value="true" name="sftj">		
			<div class="toolbox">
				<div class="buttonbox">	
					<ul>						
						<li><a href="#" class="btn_tj" onclick="showTopWin('/xgxt/commWsjc.do?method=xsfsTj&doType=show', 400, 350)">ͳ��</a></li>
					</ul>
				</div>
			</div>
			</logic:equal>
			<div class="toolbox">
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>ѧ��</th>
								<td>
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn" labelProperty="xn"/>
									</html:select>
								</td>
								<th>ѧ��</th>
								<td>
									<html:select property="xq" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
									</html:select>
								</td>
								<th>ѧ��</th>
								<td><html:text property="xh"></html:text></td>
							</tr>							
							<tr>
								<th>����</th>
								<td><html:text property="xm"></html:text></td>
								<th>
									У��
								</th>
								<td>
									<html:select property="xqdm" style="width: 150px" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>													
								</td>
								<th>
									¥��
								</th>
								<td>
									<html:select property="lddm" style="width: 150px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									��������
								</th>
								<td>
									<html:select property="cs" style="width: 150px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									���Һ�
								</th>
								<td>
									<html:select property="qsh" style="width: 150px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>			
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<logic:equal value="true" name="isxy">
										<html:hidden property="xydm"/>
										<html:select property="xydm" style="width: 150px" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual value="true" name="isxy">
										<html:select property="xydm" onchange="" styleId="xy" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/commWsjc.do?method=xsfsckManage');">
											��ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="searchReset();return false;">
											����
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
				<logic:empty name="rsArrList">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rsArrList">
				
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ����������<bean:message key="lable.xb" />����Ϊ�գ�����������Ƿ������������ģ�鱻���䡣</font> 
						</span>
					</h3>
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rsArrList" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('/xgxt/commWsjc.do?method=xsfsckUpdate');"
								align="left"
								style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0" length="3">
									<td>
										<input type="hidden" value="${v }" />
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="3" length="1">
									<td>
										<input type="hidden" value="${v }" />
										${v }
										<logic:iterate id="v" name="s" offset="4" length="1">
											<input type="hidden" value="${v }" />
										</logic:iterate>
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="5">
									<td>
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						<!--��ҳend-->
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>
		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>