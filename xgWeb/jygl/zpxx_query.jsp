<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

		<script language="javascript">
	function querygo(){
		 	document.forms[0].action = "/xgxt/zpxxquery.do?act=go&doType=query";
		 	document.forms[0].submit();
    }
	</script>
		<script language="javascript">
		function zpxxdel(doType2){
		var url = "/xgxt/zpxxdel.do?doType2=del&doType=query&act=go&pkValue=";
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
		var url ="/xgxt/zpxxViewMoreinfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 700, 650);
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
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ��ҵ��Ƹ - ��Ƹ��Ϣ��ѯ</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">

			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="who" value="teacher">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="zpxxupdate('update');" class="btn_xg">
									�޸� </a>
							</li>
							<li>
								<a href="#" onclick="zpxxdel('del');" class="btn_sc"> ɾ�� </a>
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
							<tr>
								<th>
									��˾����
								</th>
								<td>
									<html:text name="rs1" property="gsmc" style="width:175px" />
								</td>
								<th>
									��Ƹְλ
								</th>
								<td>
									<html:text name="rs1" property="zpzw" />
								</td>
								<th>
									�����ص�
								</th>
								<td>
									<html:text name="rs1" property="gzdd" style="width:215px" />
								</td>
							</tr>
							<tr>
								<th>
									��ҵ����
								</th>
								<td>
									<html:select name="rs1" property="hyfl" styleId="hyfl"
										style="width:220px">
										<html:option value=""></html:option>
										<html:options collection="hyflList" property="hyfl"
											labelProperty="hyfl" />
									</html:select>
								</td>
								<th>
									ת��нˮ
								</th>
								<td>
									<html:select name="rs1" property="zzxs" style="width:106px">
										<html:option value=""></html:option>
										<html:option value="����">����</html:option>
										<html:option value="1000����">1000����</html:option>
										<html:option value="1000-1500">1000-1500</html:option>
										<html:option value="1500-2500">1500-2500</html:option>
										<html:option value="2500-3500">2500-3500</html:option>
										<html:option value="3500-5000">3500-5000</html:option>
										<html:option value="5000-7000">5000-7000</html:option>
										<html:option value="7000-10000">7000-10000</html:option>
										<html:option value="10000����">10000����</html:option>
									</html:select>
								</td>
								<th>
									ѧ��Ҫ��
								</th>
								<td>
									<html:select name="rs1" property="xlyq" style="width:70px">
										<html:option value=""></html:option>
										<html:option value="ר��">ר��</html:option>
										<html:option value="����">����</html:option>
										<html:option value="˶ʿ">˶ʿ</html:option>
										<html:option value="��ʿ">��ʿ</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td>
									����ʱ��
								</td>
								<td>
									<html:select name="rs1" property="xjsj" style="width:75px">
										<html:option value=""></html:option>
										<html:option value="-1">����</html:option>
										<html:option value="-2">������</html:option>
										<html:option value="-7">һ����</html:option>
										<html:option value="-15">������</html:option>
										<html:option value="-30">һ����</html:option>
										<html:option value="-90">������</html:option>
										<html:option value="-180">������</html:option>
										<html:option value="-365">һ����</html:option>
									</html:select>
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
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
								<tr onclick="rowOnClick(this)" ondblclick="viewMoreinfo('view')">
									
									<logic:iterate id="v" name="s" offset="1">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<button class="button2"
											onclick="sendgrjl(this.parentNode.parentNode.cells[1].innerText,this.parentNode.parentNode.cells[2].innerText)"
											style="width:60px">
											Ͷ����
										</button>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
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
