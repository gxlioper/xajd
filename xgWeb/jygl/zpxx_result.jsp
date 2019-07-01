<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
			function deleteinfo(gsmc,zpzw,xsxh){
			 var url = "/xgxt/deletegrjlinfo.do?act=go&doType=del&gsmc=";
			 url+=gsmc+"&zpzw="+zpzw+"&xsxh="+xsxh;
			 
			 if (confirm("ȷ��Ҫɾ������������")) {
					refreshForm(url);
					return true;
		        }else{
		        return false;
		        }
		    }
			
			function viewMoreinfo(xsxh){
				var url ="/xgxt/zpxxViewMoreGrjlInfo.do?doType=view&xsxh=";
			        url+=xsxh;
			        showTopWin(url, 840, 600);
			 }
		</script>
		
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ��ҵ��Ƹ - ��ҵ��ѯ����</a>
			</p>
		</div>
	
		<html:form action="/data_search" method="post">
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
									<td align="center">
										����
									</td>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="viewMoreinfo(this.cells[3].innerText)">
									<logic:iterate id="v" name="s" offset="0">
										<td align="center">
											<bean:write name="v" />
											<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>"/>
										</td>
									</logic:iterate>
									<td align="center">
										<button
											onclick="deleteinfo(this.parentNode.parentNode.cells[1].innerText,this.parentNode.parentNode.cells[2].innerText,this.parentNode.parentNode.cells[3].innerText);"
										>
											ɾ��
										</button>
									</td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>

			<input type="button" id="query_go" style="display:none;"
				onclick="refreshForm('/xgxt/jyzxResultQuery.do?act=go');" />
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
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
