<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	</head>
	
	<body onload="">
	<html:form action="/zjlgPjpy" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" name="tableName" id="tableName" value="${tableName }" />
		<input type="hidden" name="realTable" id="realTable" value="${realTable }" />
		<input type="hidden" id="isFdy" name="isFdy" value="${isFdy }"/>
		<input type="hidden" id="userName" name="userName" value="${userName }"/>
		<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������� - ��Ϣά�� - �����ɼ�ά��</a>
			</p>
		</div>
		<logic:equal value="yes" name="writeAble">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_dc" onclick="wjcfDataExport('zjlgPjpy.do?method=zycpfwh&act=export')">����</a></li>								
				</ul>
			</div>
			</div>
		</logic:equal>		
			<div class="searchtab">	
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td><html:select property="querylike_xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select></td>
							<th>�꼶</th>
							<td><html:select property="queryequals_nj" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select></td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td><html:text property="querylike_xh" style="width:100px" styleId="xh" maxlength="20"></html:text></td>
							<th>����</th>
							<td><html:text property="querylike_xm" styleId="xm" style="width:80px" maxlength="20"></html:text>
							</td>
							<th></th><td></td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<logic:equal name="userType" value="xy">
								<td>
									<html:hidden property="queryequals_xydm"/>
									<html:select property="queryequals_xydm" styleId="xy" style="width:150px"
										onchange="initZyList();initBjList()" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</logic:equal>
							<logic:notEqual name="userType" value="xy">
								<td>
									<html:select property="queryequals_xydm" styleId="xy" style="width:150px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</logic:notEqual>
							
							<th>רҵ</th>
							<td><html:select property="queryequals_zydm" styleId="zy" style="width:150px"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
							<th>�༶</th>
							<td><html:select property="queryequals_bjdm" styleId="bj" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" onclick="refreshForm('zjlgPjpy.do?method=zycpfwh&act=qry');">
								��ѯ
							</button>
							 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
								����
							 </button>
							</div>
							</td>
						</tr>
					</tfoot>
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
							��¼���� ${rsNum } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��������ͷ��������;</font>
							<font color="red">�����ڲ������� - ���۷�ʽ�����������꼶���۷�ʽ;</font>
						</span>
					</h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" >
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;">
								<logic:iterate id="v" name="s">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					<!--��ҳ��ʾ-->
				     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlgPjpyForm"></jsp:include>
					<!--��ҳ��ʾ-->
					<script type="text/javascript">
						$('choose').className="hide";
					</script>
			</logic:notEmpty>
		</div>
	</html:form>
</body>
</html>
