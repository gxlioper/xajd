<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function querygo(){
			 	document.forms[0].action = "/xgxt/zpxxReturnMessQuery.do?act=go&doType=query";
			 	document.forms[0].submit();
	    }
	    
		function yjfkdel(doType2){
		var url = "/xgxt/zpxxReturnMessDel.do?doType2=del&doType=query&act=go&pkValue=";
		var pkValue = "";
			
		if (doType2 == "del") {
		   if (curr_row == null) {
			alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			return false;
		    } else {
			if (confirm("ȷ��Ҫɾ������������")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		  }
		   return;
	      }
		}
		
		
		function viewyjfkMoreinfo(doType){
		var url ="/xgxt/messmorequery.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 850, 680);
		 }
		}
		
		
		
		function zpxxupdate(doType){
		var url ="/xgxt/zpxxupdate.do?doType=update&rowid=";
		var rowid ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 rowid = curr_row.getElementsByTagName("input")[0].value;
		         url += rowid;
		         showTopWin(url, 680, 635);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		function sendgrjl(gsmc,zpzw) {	
		var url="/xgxt/opensendgrjlweb.do?doType=view&gsmc=";
		url=url+gsmc+"&zpzw="+zpzw;	
		showTopWin(url, 680, 660);
        }
		
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ��ҵ��Ƹ - ���������ѯ</a>
			</p>
		</div>


		<html:form action="/data_search" method="post">
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
							<li>
								<a href="#"
									onclick="yjfkdel('del');"
									class="btn_sc"> ɾ�� </a>
							</li>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="querygo()">
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
							<tr>
								<th>
									��λ����
								</th>
								<td>
									<html:text name="rs1" property="gsmc" style="width:200px" />
								</td>
								<th>
									���������
								</th>
								<td>
									<html:select name="rs1" property="myd" style="width:150px">
										<html:option value=""></html:option>
										<html:option value="�ǳ�����">�ǳ�����</html:option>
										<html:option value="����">����</html:option>
										<html:option value="һ��">һ��</html:option>
										<html:option value="������">������</html:option>
										<html:option value="�ǳ�������">�ǳ�������</html:option>
									</html:select>
								</td>
								<th>
									�������
								</th>
								<td>
									<html:select name="rs1" property="fknd" style="width:100px">
										<html:option value=""></html:option>
										<html:option value="2007">2007</html:option>
										<html:option value="2008">2008</html:option>
										<html:option value="2009">2009</html:option>
										<html:option value="2010">2010</html:option>
										<html:option value="2011">2011</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									��λ����
								</th>
								<td>
									<html:select name="rs1" property="dwxz" styleId="dwxz"
									style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="dwxzList" property="dwxz"
										labelProperty="dwxz" />
								</html:select>
								</td>
								<th>
									 ��ҵ����
								</th>
								<td>
									<html:select name="rs1" property="hyfl" styleId="hyfl"
									style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="hyflList" property="hyfl"
										labelProperty="hyfl" />
								</html:select>
								</td>
								<th>
								</th>
								<td>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> 
							<logic:notEmpty name="rs">
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
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
										ondblclick="viewyjfkMoreinfo('view')">
										<logic:iterate id="v" name="s" offset="1">
											<td align="center">
												<bean:write name="v" />
												<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>
		</html:form>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                       alert("ɾ���ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("ɾ��ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
