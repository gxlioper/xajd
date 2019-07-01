<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
	</head>

	<body onload="">
		<html:form action="/gyglYcjc">
			<input type="hidden" value="${tableName }" name="tableName"/>
			<input type="hidden" value="${pkKey }" name="pkKey"/>
			<html:hidden property="yclx"/>
			
			<button id="search_go" onclick="allNotEmpThenGo('gyglYcjc.do?method=queryYcsj')" style="display:none"></button>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
			</div>
			<!-- ���� end-->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="batchData('pkValue','gyglYcjc.do?method=delYcsj','del')"
									class="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="#"
								   onclick="expData('gyglYcjc.do?method=expYcsj')"
								   class="btn_dc">����</a>
							</li>
							<li>
								<a href="gyglYcjc.do?method=ycsjjcManage"
									class="btn_fh">����</a>
							</li>	
						</logic:equal>
					</ul>
				</div>
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> �쳣����(<font color="blue">${prompt}</font>)&nbsp;&nbsp; 
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr>
									<td width="17px">
										<input type="checkbox" disabled="true"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="${tit }"
											onclick="tableSort(this)" nowrap>
											${tit }
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" 
									style="cursor:hand" 
									ondblclick="">
									<!-- checkbox -->								
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td>
											<input type="checkbox" id="checkVal"  name="pkValue"  value="${v }"/>	
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" >
										<td align="left">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						<!--���� end-->
						</table>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglCommForm"></jsp:include>
						<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>	
		</html:form>
		<logic:present name="message">
			<script>
				alert("${message}");
			</script>
		</logic:present>
	</body>
</html>