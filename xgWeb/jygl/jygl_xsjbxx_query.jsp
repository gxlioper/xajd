<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
	function querygo(){
			
		 	document.forms[0].action = "/xgxt/jyxyTurnInfo.do?act=go";
		 	document.forms[0].submit();
    }
   
   	function stu_send(type)
		{
			var xh=curr_row.cells[4].innerText;
			if(type=="jyxy"){
			var ct = document.getElementById("cgxys").value;
				if(ct == "cgxys"){
					window.dialogArguments.refreshForm('/xgxt/inputJyxyStuInfo.do?act=go&cgxys=cgxys&xsxh='+xh);
				}else{
					var url='/xgxt/inputJyxyStuInfo.do?act=go&xsxh='+xh;
					window.dialogArguments.document.forms[0].action = url;
					window.dialogArguments.document.forms[0].submit();
					window.close();
					//window.dialogArguments.refreshForm('/xgxt/inputJyxyStuInfo.do?act=go&xsxh='+xh);
				}
			window.close();
			}else{
				window.dialogArguments.refreshForm('/xgxt/jyxxtjwh.do?act=add&xsxh='+xh);
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
					<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ѧ����Ϣ - ѧ����Ϣ��ѯ</a>
				</p>
			</div>
			<div class="searchtab">		
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<th>ѧ��</th>
							<td><html:text name="rs1" property="xsxh" /></td>
							<th>����</th>
							<td><html:text name="rs1" property="name" style="width:70px"/></td>
							<th><input id="cgxys" name="cgxys" value="${cgxys }" type="hidden" />�Ա�</th>
							<td><html:select name="rs1" property="xbdm" style="width:50px">
									<html:option value=""></html:option>
									<html:option value="1">
										��
									</html:option>
									<html:option value="2">
										Ů
									</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>ѧ�����</th>
							<td><html:select name="rs1" property="xslb" style="width:80px">
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
							<th>��ѧ���</th>
								<td><html:select name="rs1" property="nd" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="ndList" property="nj"
											labelProperty="nj" />
								</html:select></td>
								<th>��ҵ���</th>
								<td><select name="bynd" style="width:100px">
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
						<logic:equal name="who" value="teacher">
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><html:select name="rs1" property="xymc" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xymc"
											labelProperty="xymc" />
								</html:select></td>
								<th colspan="4"></th>
							</tr>
						</logic:equal>
							
						<logic:equal name="who" value="fudaoyuan">
						    <tr>
						    	<th><bean:message key="lable.xsgzyxpzxy" /></th>
						    	<td><html:text name="rs1" property="xymc" style="width:150px" readonly="true" /></td>
						    	<th colspan="4"></th>
							</tr>
						</logic:equal>
					</thead>
					<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
							<button class="button2"
									onclick="querygo()">
									��ѯ
								</button>
						</div>
						</td>
					</tr>
				</tfoot>
				</table>
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
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
						<logic:equal value="10338" name="xxdm" scope="session">
							<tr onclick="rowOnClick(this)"
								ondblclick="stu_send('zjlgjy')">
								<logic:iterate id="v" name="s" offset="0" length="1">
								<td style="display: none">
									<input type="hidden" value="<bean:write name="v"/>" />
								</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
							</logic:equal>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="stu_send('jyxy')">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:notEqual>
						</logic:iterate>
					</table>
			</logic:notEmpty>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>