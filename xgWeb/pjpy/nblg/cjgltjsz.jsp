<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript">
<!--
	function modi() {
		if (curr_row==null||curr_row=='') {
			alert('��ѡ��Ҫ������������!');
			return;
		}
		var pk = curr_row.cells[0].getElementsByTabName("input")[0].value;
		var url = 'pjpy_nblg_cjtjmodi.do?pkValue=';
		url += pk;
		showTopWin(url, 400, 250);
	}
//-->
</script>
</head>
<body>
	<html:form action="/pjpynblgwh" method="post">
		<!-- ����ҳ��ʱ,ִ�в�ѯ���� -->
		<input type="hidden" name="search_go" id="search_go"
			onclick="refreshForm('pjpy_nblg_cjtjgl.do');" />
		<div class="tab_cur">
			<p class="location">
				<em>��ǰ����λ�ã�</em><a>�������� - ��Ϣά�� - �γ̳ɼ���ȼ����Գɼ�ά�� - �ɼ�������������</a>
			</p>
		</div>	
		<logic:equal value="yes" name="writable">
					<div class="toolbox">
					<div class="buttonbox">	
						<input type="hidden" name="ts" id="ts"/>
						<ul>
							<li><a href="#" class="btn_zj" onclick="showTopWin('pjpy_nblg_cjtjadd.do','400','250')">����</a></li>
							<li><a href="#" class="btn_xg" onclick="" style="width:80px">�޸�</a></li>
							<li><a href="#" class="btn_sc" onclick="">ɾ��</a></li>
						</ul>
					</div>
					</div>
		</logic:equal>	
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
						<table width="98%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
									<td align="center" onclick="tableSort(this)" width="50%" nowrap>
										����
									</td>
									<td align="center" onclick="tableSort(this)" width="48%" nowrap>
										����(�γ����ƻ�γ���������)
									</td>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" align="center"
									style="cursor:hand;">
									<logic:iterate id="v" name="s">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
			</logic:notEmpty>
			<div id="tmp"></div>
			<!--<logic:equal value="yes" name="writable">
				<div class="buttontool" align="center" id="btn" style="position:absolute;width:100%;">
					<button id="btn_add" class="button2"
						onclick="showTopWin('pjpy_nblg_cjtjadd.do','400','250')" style="width:80px">
						����
					</button>
					&nbsp;&nbsp;&nbsp;
					<button id="btn_add" class="button2"
						onclick="" style="width:80px">
						�޸�
					</button>
										&nbsp;&nbsp;&nbsp;
					<button id="btn_add" class="button2"
						onclick="" style="width:80px">
						ɾ��
					</button>
					
				</div>
			</logic:equal>-->
			<script type="text/javascript" src="js/bottomButton.js"></script>
	</html:form>
</body>
</html>
