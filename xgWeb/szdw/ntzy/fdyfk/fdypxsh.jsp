<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ�����������У�');
				return false;
			}
		}
	</script>
	</head>
	<body>
		<html:form action="/ntzy_fdyfk.do?method=fdypxsh" method="post">
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<logic:equal value="yes" name="writeAble">
				<div id="btn" class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_shtg" onclick="batchData('primarykey_cbv','ntzy_fdyfk.do?method=fdypxsh&shjg=tg&doType=sh&go=go','sh')">ͨ��</a></li>
						<li><a href="#" class="btn_shbtg" onclick="batchData('primarykey_cbv','ntzy_fdyfk.do?method=fdypxsh&shjg=btg&doType=sh&go=go','sh')"">��ͨ��</a></li>
						<li><a href="#" class="btn_sh" onclick="modi('ntzy_fdyfk.do?method=fdypxshone&doType=shone');">�������</a></li>
					</ul>
				</div>
				</div>
			</logic:equal>
			
			<div class="searchtab">
				<table width="100%" border="0" class="">
					<tbody> 
						<tr>
							<th>ְ����</th>
							<td><html:text property="querylike_zgh" styleId="zgh"></html:text></td>
							<th>����</th>
							<td><html:text property="querylike_xm" styleId="xm"></html:text></td>
							<th>���ڲ���</th>
							<td><html:select property="queryequals_bmdm" style="width:180px"
									styleId="bmdm">
									<html:option value=""></html:option>
									<html:options collection="bmList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>��ѵ��Χ</th>
							<td><html:select property="queryequals_pxfw" styleId="pxfw">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="pxfwList" property="xmmc" labelProperty="xmmc"/>
							</html:select>
							</td>
							<th>��ѵ����</th>
							<td><html:select property="queryequals_pxlx" styleId="pxlx">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="pxlxList" property="xmmc" labelProperty="xmmc"/>
							</html:select>
							</td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
						<input type="hidden" name="go" value="a" />
						<button type="button" id="search_go" 
						onclick="allNotEmpThenGo('/xgxt/ntzy_fdyfk.do?method=fdypxsh&go=go')">
						�� ѯ
						</button>
						 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							�� ��
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
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
								<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('ntzy_fdyfk.do?method=fdypxview&doType=view',700,500);"
								align="center"
								style="cursor:hand">
								<td>
									<input type="checkbox" name="primarykey_cbv" 
										id="pkV" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
								</td>
							
								<logic:iterate id="v" name="s" offset="1">
									<td>${v }</td>
								</logic:iterate>
							
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					
				<!--��ҳ��ʾ-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=fdypxForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</logic:notEmpty>
			
			</div>
			
		</html:form>
		<logic:present name="result">
			<input type="hidden" id="message" value="${message }" />
			<script type="text/javascript">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>
