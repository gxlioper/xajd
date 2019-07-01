<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function querygo(){
			 	document.forms[0].action = "/xgxt/byqxTurnInfo.do?act=go";
			 	document.forms[0].submit();
	    }
	   
	   	function stu_send(type)
			{
				var xh=curr_row.cells[3].innerText;
				
				if(type=="byqx"){
					var bystj = document.getElementById("bysjytjb").value;
					if(bystj=="bysjytjb"){
						window.dialogArguments.refreshForm('/xgxt/inputByqxStuInfo.do?act=go&xsxh='+xh+'&bysjytjb='+bystj);
					}else{
						window.dialogArguments.refreshForm('/xgxt/inputByqxStuInfo.do?act=go&xsxh='+xh);
					}
					window.close();
				}
			}
		</script>
	</head>
	
	<body>
		<script language="javascript" src="js/function.js"></script>
		
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>��ҵ���� - ѧ����Ϣ - ѧ����Ϣ��ѯ</a>
				</p>
			</div>
			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
					<tfoot>
							<tr>
								<td colspan="8">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button class="btn_cx" id="search_go" onclick="querygo()">
											�� ѯ
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
						<tr style="cursor:hand">
							<th>
								ѧ��
							</th>
							<td>
								<html:text name="rs1" property="xsxh" />
							</td>
							<th>
								����
							</th>
							<td>
								<html:text name="rs1" property="name" style="width:70px"/>
							</td>
							<th>
								�Ա�
							</th>
							<td>
								<input id="bysjytjb" name="bysjytjb" value="${bysjytjb }" type="hidden"/>
								<html:select name="rs1" property="xbdm" style="width:50px">
									<html:option value=""></html:option>
									<html:option value="1">
										��
									</html:option>
									<html:option value="2">
										Ů
									</html:option>
								</html:select>
							</td>
							<th>
								ѧ�����
							</th>
							<td>
								<html:select name="rs1" property="xslb" style="width:80px">
									<html:option value=""></html:option>
									<html:option value="������">
										������
									</html:option>
									<html:option value="ר����">
										ר����
									</html:option>
									<html:option value="�о���">
										�о���
									</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<logic:equal name="who" value="teacher">
								<th>
								<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
								<html:select name="rs1" property="xymc" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xymc"
											labelProperty="xymc" />
								</html:select>
								</td>
							</logic:equal>
							<logic:equal name="who" value="fudaoyuan">
								<th>
							    <bean:message key="lable.xsgzyxpzxy" />
							    </th>
							    <td>
							    <html:text name="rs1" property="xymc" style="width:150px" readonly="true" />
							    </td>
							</logic:equal>
								<th>
									��ѧ���
								</th>
								<td>
								<html:select name="rs1" property="nd" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="ndList" property="nj"
											labelProperty="nj" />
								</html:select>
								</td>
								<th>
								��ҵ���
								</th>
								<td>
								<select name="bynd" style="width:100px">
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
						</tr>
					</tbody>
				</table>
			</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp;
						<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> 
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п���ѡ�иü�¼��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>
					<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" id="rsTable" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="2">
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
								ondblclick="stu_send('byqx')">
								<td style="display:none">
									<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
						 <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						 <script type="text/javascript">
														$('choose').className="hide";
												</script>
			</logic:notEmpty>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>