<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/gygl/wsjc.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		function searchRs(){
			allNotEmpThenGo('/xgxt/commWsjc.do?method=fstjManage');
		}
		
		//����
		function wsjcExp(){
		
			var url = "/xgxt/commWsjc.do?method=fstjManage&doType=exp";

			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}
		</script>
	</head>
	<body onload="showGdtj('Three-Four-Five')">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commWsjc">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="lx" styleId="lx"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li id="xgId">
								<a href="#"
									onclick="wsjcExp()"
									class="btn_dc">����</a>
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
									<div class="bz">
										<html:checkbox property="gdtj" styleId="gdtj"  onclick="showGdtj('Three-Four-Five')"/>��������
									</div>
									<div class="btn">
										<input type="hidden" name="go" value="a"/>
										<button class="btn_cx" id="search_go"
											onclick="showTips('����ͳ���У���ȴ�......');searchRs()">
											ͳ ��
										</button>
										<button class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('xn-xq-nd-xqdm-lddm-cs-qsh-xy-sfdf-jcbm-nj-xy-zy-bj-xh-xm-zzmm-dj-fssx-fsxx');return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									���
								</th>
								<td>
									<html:select property="nd" style="width: 150px" onchange="">
										<html:options collection="ndList" property="nd" labelProperty="nd" />
									</html:select>												
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xn" style="width: 150px" onchange="">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>		
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xq" style="width: 150px" onchange="">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
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
									<!-- ��Ԣ�ϰ汾 -->
									<logic:equal name="gyglEdition" value="old">
										<html:select property="lddm" style="width: 150px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
											<html:options collection="ldList" property="dm" labelProperty="mc" />
										</html:select>
									</logic:equal>
									<!-- ��Ԣ�°汾 -->
									<logic:equal name="gyglEdition" value="new">
										<html:select property="lddm" style="width: 150px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
											<html:option value="">----��ѡ��----</html:option>
											<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
										</html:select>
									</logic:equal>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="cs" style="width: 150px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<!-- ������ -->
							<tr id="trThree" style="display: none">
								<th>
									���Һ�
								</th>
								<td>
									<html:select property="qsh" style="width: 150px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>			
								</td>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
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
										<html:select property="xydm" style="width: 150px" styleId="xy" onchange="initZyList();initBjList()" onmouseover="">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
							<!-- ������ -->
							<tr id="trFour" style="display: none">
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList()" styleId="zy" style="width:150px" onmouseover="">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px" onmouseover="">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th>
									���ʱ��
								</th>
								<td>
									<logic:equal name="jczq" value="��">
										<html:select property="kszc" style="width: 70px" styleId="kszc" onchange="">
											<html:options collection="zcList" property="dm" labelProperty="mc" />
										</html:select>	
										��
										<html:select property="jszc" style="width: 70px" styleId="jszc" onchange="">
											<html:options collection="zcList" property="dm" labelProperty="mc" />
										</html:select>	
									</logic:equal>
									<logic:equal name="jczq" value="��">
										<html:text property="kssj" styleId="kssj"
											onblur="dateFormatChg(this)" style="cursor:hand;width: 70px"
											onclick="return showCalendar('kssj','y-mm-dd');"
										/>
										��
										<html:text property="jssj" styleId="jssj"
											onblur="dateFormatChg(this)" style="cursor:hand;width: 70px"
											onclick="return showCalendar('jssj','y-mm-dd');"
										/>
									</logic:equal>
								</td>
							</tr>
							<!-- ������ -->
							<tr id="trFive" style="display: none">
								<th>
									ͳ�ƶ���
								</th>
								<td>
									<html:select property="tjfs" style="width: 150px" styleId="tjfs" onchange="">
										<html:options collection="tjfsList" property="en" labelProperty="cn" />
									</html:select>	
								</td>
								<th>
									ͳ�Ʒ�Χ
								</th>
								<td>
									<html:select property="tjfw" style="width: 150px" styleId="tjfw" onchange="">
										<html:options collection="tjfwList" property="en" labelProperty="cn" />
									</html:select>	
								</td>
								<th>
									<logic:notEmpty name="wsfdj">
										�ȼ����
									</logic:notEmpty>
								</th>
								<td>
									<logic:notEmpty name="wsfdj">
										<html:select property="dj" style="width: 150px" styleId="dj" onchange="">
											<html:option value=""></html:option>
											<html:options collection="wsfdjList" property="wsfdj" labelProperty="wsfdj" />
										</html:select>	
									</logic:notEmpty>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
			</div>
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
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������</font> 
						</span>
					</h3>
					<div class="con_overlfow">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rsArrList" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" >
									<logic:iterate id="v" name="s" offset="1" length="${num-2 }">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="${num }">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--���� end-->
						</table>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						  <script type="text/javascript">
					      $('choose').className="hide";
					     </script>
						<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->

			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>