<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
			function sendData() {
				var url = window.dialogArguments.document.getElementById('url').value;
				var xh = curr_row.cells[0].getElementsByTagName("input")[0].value;
				window.dialogArguments.document.forms[0].action = url+'&xh='+xh;
				window.dialogArguments.document.forms[0].submit();
				window.close();
			}
		</script>

	</head>
	<body>

		<html:form action="/jygl" method="post">
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>��ҵ����Ϣ</a>
				</p>
			</div>
			<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button class="btn_cx" id="search_go"
										onclick="allNotEmpThenGo('jygl.do?method=jyglData&doType=query')">
										��ѯ
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
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
								<html:text property="querylike_xsxh" maxlength="20" />
							</td>
							<th>
								����
							</th>
							<td>
								<html:text property="querylike_name" style="width:70px" />
								<input id="cgxys" name="cgxys" value="${cgxys }" type="hidden" />
							</td>
							<th>
								ѧ�����
							</th>
							<td>
								<html:select property="queryequals_xslb" style="width:80px">
									<html:option value=""></html:option>
									<html:option value="ר����">
										ר����
									</html:option>
									<html:option value="������">
										������
									</html:option>
									<html:option value="�о���">
										�о���
									</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��ѧ���
							</th>
							<td>
								<html:select property="queryequals_nd" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								��ҵ���
							</th>
							<td>
								<select name="queryequals_bynd" style="width:100px">
									<option value=""></option>
									<option value="2007">
										2007
									</option>
									<option value="2008">
										2008
									</option>
									<option value="2009">
										2009
									</option>
									<option value="2010">
										2010
									</option>
									<option value="2011">
										2011
									</option>
									<option value="2012">
										2012
									</option>
									<option value="2013">
										2013
									</option>
									<option value="2014">
										2014
									</option>
									<option value="2015">
										2015
									</option>
								</select>
							</td>
							<th>

							</th>
							<td>

							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="formbox" id="result">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п���ѡ�У�������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>
				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
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
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="sendData();">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td align="center">
											<bean:write name="v" />
											<input type="hidden" value="<bean:write name="v"/>" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
					<script type="text/javascript">
				      $('choose').className="hide";
				     </script>

					</fieldset>
				</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
