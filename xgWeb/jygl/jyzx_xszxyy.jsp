<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
	function querygo(){
		 	document.forms[0].action = "/xgxt/jyzx_zxsquery.do?act=go&doType=query";
		 	document.forms[0].submit();
    }
    
    function jygljyxyDataExport() {
	       document.forms[0].action = "/xgxt/jygljyxyDataExport.do?tableName=jygl_jyxy";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		
		function viewMoreinfo1(doType){
		var url ="/xgxt/jyzxViewMoreInfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 600, 450);
		 }
		}
		
		
		function jyzxZxsUpdate(doType){
		var url ="/xgxt/jyzxZxsUpdate.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 550, 400);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		function JyglJyxyDel(doType2){
		var url = "/xgxt/jyzx_zxsdel.do?doType2=del&doType=query&act=go&pkValue=";
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
		
		function zxyuyue(num) {	
		var url="/xgxt/jyzxZxsYuyue.do?doType=view&pkValue="
		url=url+num;
	
		showTopWin(url, 600, 500);
        }
		
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ְҵ��ѯ - ѧ����ѯԤԼ</a>
			</p>
		</div>
		<html:form action="/data_search" method="post">
		<div class="toolbox">
				<!-- ��ť -->
				
					<logic:equal name="isstudent" value="no">
					<div class="buttonbox">
					<ul>
						<li>
							<a
								href="#" onclick="javascript:jyzxZxsUpdate('update')"
								class="btn_xg"> �޸� </a>
						</li>
						<li>
							<a
								href="#" onclick="javascript:JyglJyxyDel('del')"
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
											onclick="querygo('/xgxt/JyxyDataSearch.do')">
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
									<html:text name="rs1" property="num" />
								</td>
								<th>
									��ѯʦ����
								</th>
								<td>
									<html:text name="rs1" property="name" />
								</td>
								<th>
									��ѯʦ�Ա�
								</th>
								<td>
									<html:select name="rs1" property="xb" style="width:65px">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="Ů">Ů</html:option>
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
						</logic:empty> 
						<logic:notEmpty name="rs">
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
						</logic:notEmpty>
						</span>
				</h3>

				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<td>
									����
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" ondblclick="viewMoreinfo1('view')">
								<logic:iterate id="v" name="s" offset="1">
									<td align="center">
										<bean:write name="v" />
										<input type="hidden" value="<logic:iterate id="g" name="s" offset="0" length="1"><bean:write name="g"/></logic:iterate>" />
									</td>
								</logic:iterate>
								<td align="center">
									<button class="button2"
										onclick="zxyuyue(this.parentNode.parentNode.cells[1].innerText);">
										ԤԼ
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
