<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function querygo(){
		 	document.forms[0].action = "/xgxt/zxyyglquery.do?act=go&doType=query";
		 	document.forms[0].submit();
		 }
		
		function zxyygldel(doType2){
		var url = "/xgxt/zxyygldel.do?doType2=del&doType=query&act=go&pkValue=";
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
		
		
		function viewMoreinfo(doType){
		var url ="/xgxt/zxyyglViewMoreInfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 610, 560);
		 }
		}
		
		
		
		function updateYuyue(doType){
		var url ="/xgxt/open_updateYuyue.do?act=go&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 645, 600);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		function answer(xsxh) {	
		var url="/xgxt/answerYuyue.do?doType=view&pkValue=";
		url=url+xsxh;	
		showTopWin(url, 645, 600);
        }
		
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ְҵ��ѯ - ��ѯԤԼ����</a>
			</p>
		</div>
		
	
		<html:form action="/data_search" method="post">
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
					
						<ul>
							<li>
								<a
									href="javascript:updateYuyue('update')"
									class="btn_xg"> �޸� </a>
							</li>
							<li>
								<a
									href="javascript:zxyygldel('del')"
									class="btn_sc"> ɾ�� </a>
							</li>
						</ul>
					</div>
				</logic:equal>
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
									��ѯʦ���
								</th>
								<td>
									<html:text name="rs1" property="num"  />
								</td>
								<th>
									ѧ��
								</th>
								<td>
										<html:text name="rs1" property="xsxh" />
								</td>
								<th>
									ԤԼ�ύʱ��
								</th>
								<td>
									<html:select name="rs1" property="xjsj" style="width:82px">
									<html:option value=""></html:option>
									<html:option value="-0">����</html:option>
									<html:option value="-2">������</html:option>
									<html:option value="-7">һ����</html:option>
									<html:option value="-15">������</html:option>
									<html:option value="-30">һ����</html:option>
								</html:select>
								</td>
							</tr>
							<tr>
								<th>
									Ҫ�����
								</th>
								<td>
									<html:select name="rs1" property="meet" styleId="meet"
									style="width:60px">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
								</td>
								<th>
									ѧ��ȷ��
								</th>
								<td>
									<html:select name="rs1" property="xsqr">
										<html:option value=""></html:option>
										<html:option value="δȷ��">δȷ��</html:option>
										<html:option value="�Ѳ鿴">�Ѳ鿴</html:option>
										<html:option value="����ԼX">����ԼX</html:option>
										<html:option value="��ȷ�ϡ�">��ȷ�ϡ�</html:option>
									</html:select>
								</td>
								<th>
									��ѯʦȷ��
								</th>
								<td>
									<html:select name="rs1" property="zxsqr">
									<html:option value=""></html:option>
									<html:option value="δȷ��">δȷ��</html:option>
									<html:option value="�ѻظ���">�ѻظ���</html:option>
								</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<td align="center">
									����
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewMoreinfo('view')">
								<logic:iterate id="v" name="s" offset="1">
									<td align="center">
										<bean:write name="v" />
										<input type=hidden value="<logic:iterate id="g" name="s" offset="0" length="1"><bean:write name="g" /></logic:iterate>" />
									</td>
								</logic:iterate>
								<td align="center">
									<button class="button2"
										onclick="answer(this.parentNode.parentNode.getElementsByTagName('input')[0].value);">
										�ظ�
									</button>
								</td>
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
