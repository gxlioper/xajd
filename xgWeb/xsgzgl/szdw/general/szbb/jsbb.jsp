<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript">
			function bc(){
				var checkboxs = jQuery("[name='grid_key']:checked");
				if(checkboxs.length < 1){
					showAlert("������ѡ��һ����ʦ��");
					return false;
				}
				 showAlert("����ɹ�",{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				//iFClose();
			}
		</script>
	</head>

	<body>
<%--		<div class="tab_cur">--%>
<%--			<p class="location">--%>
<%--				����ǰѡ����10λ��ʦ--%>
<%--			</p>--%>
<%--		</div>--%>
		<div class="prompt" id="div_help" style="">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					<font style="color: red">
						����ǰѡ����10���༶
					</font>				
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/xgygl_zdsq">	
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>					  
						<li><a href="javascript:void(0);" onclick="bc();" class="btn_xg">����</a></li>
					</ul>
				</div>
				<div class="searchtab">
					<table>
						<tbody>
							<tr>
								<td width="5%">
									��ʦ����
								</td>
								<td>
									<input type="text" id="xmmc" name="xmmc" maxleng="50" style="width: 100px">
								</td>
								<td width="5%">
									ְ����
								</td>
								<td width="5%">
									<input type="text" id="xmmc" name="xmmc" maxleng="50" style="width: 100px">
								</td>
								<td width="5%">
									�Ƿ����
								</td>
								<td>
									<select name="sfdb" style="width: 100px">
										<option>---��ѡ��---</option>
										<option value="1">��</option>
										<option value="2">��</option>
									</select>
								</td>
								<td width="5%">
									��������
								</td>
								<td>
									<select name="xydm" id="xy" style="width:100px">
										<option value=""></option>
										<option value="509000">�����뻯ѧ����ѧԺ</option>
										<option value="510000">��������ѧԺ</option>
										<option value="517000">�����ѧѧԺ</option>
										<option value="502000">��ѧԺ</option>
										<option value="511113">��ѧԺ</option>
										<option value="523000">��������ѧԺ</option>
										<option value="520000">����ѧԺ</option>
										<option value="412000">���ʽ���ѧԺ</option>
										<option value="514000">��������ԴѧԺ</option>
										<option value="508000">��е����Դ����ѧԺ</option>
										<option value="521000">�������ѧ�뼼��ѧԺ</option>
										<option value="512000">��������ѧԺ</option>
										<option value="21020000">��ʦ����ѧԺ</option>
										<option value="31020000">����ϵ</option>
										<option value="503000">����ѧԺ</option>
										<option value="31010000">����ϵ</option>
										<option value="501000">����ѧԺ</option>
										<option value="31070000">��ѧϵ</option>
										<option value="21070000">��ѧԺ</option>
										<option value="506000">��ѧԺ001</option>
										<option value="610000">��ѧԺ</option>
										<option value="516000">ũҵ�����＼��ѧԺ</option>
										<option value="504000">����ѧԺ</option>
										<option value="522000">���ѧԺ</option>
										<option value="21010000">��ѧԺ</option>
										<option value="31110000">��ᷢչϵ </option>
										<option value="21110000">��ᷢչѧԺ</option>
										<option value="31090000">������ѧϵ</option>
										<option value="507000">������ѧѧԺ1</option>
										<option value="21090000">������ѧѧԺ2</option>
										<option value="513000">����ϵͳ������ʳƷ��ѧѧԺ</option>
										<option value="515000">����ҽѧ������������ѧѧԺ</option>
										<option value="31030000">����ϵ</option>
										<option value="21050000">�����ѧԺ</option>
										<option value="5700">�����ѧԺ1</option>
										<option value="505000">��������Ļ�����ʽ���ѧԺ</option>
										<option value="31050000">����ϵ</option>
										<option value="553000">����ҽѧԺ</option>
										<option value="21040000">��ѧԺ</option>
										<option value="692105">У����רҵ</option>
										<option value="511000">��Ϣ��ѧ�빤��ѧԺ</option>
										<option value="31080000">��Ϣ�빤��ϵ</option>
										<option value="21080000">��Ϣ�빤��ѧԺ</option>
										<option value="519000">ҩѧԺ</option>
										<option value="31100000">ҽѧϵ</option>
										<option value="518000">ҽѧԺ</option>
										<option value="31060000">����ϵ</option>
										<option value="21060000">����ѧԺ</option>
										<option value="552000">��ҽѧԺ</option>
										<option value="410000">�ÿ���ѧԺ</option>
									</select>
								</td>
								<td>
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go">
											�� ѯ
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ʦ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" class="dateline">
					<thead>
						<tr class="nowrap">
							<td width="2%">
								<input type="checkbox" name="grid_selectAll" disabled="">
							</td>
							<th width="13%" sortname="xh">ְ����</th>
							<th width="10%" sortname="xm">����</th>
							<th width="15%" sortname="xydm">��������</th>
							<th width="13%" sortname="bjdm">�Ƿ����</th>
							<th width="15%" sortname="xn">������</th>
						</tr>
						<tbody>
							<tr rowindex="0">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									���پ�
								</td>
								<td style="word-break:break-all;">
									ҽѧԺ
								</td>
								<td style="word-break:break-all;">
									��
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="1">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									���پ�
								</td>
								<td style="word-break:break-all;">
									ҽѧԺ
								</td>
								<td style="word-break:break-all;">
									��
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="2">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									���پ�
								</td>
								<td style="word-break:break-all;">
									ҽѧԺ
								</td>
								<td style="word-break:break-all;">
									��
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="3">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									���پ�
								</td>
								<td style="word-break:break-all;">
									ҽѧԺ
								</td>
								<td style="word-break:break-all;">
									��
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="4">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									���پ�
								</td>
								<td style="word-break:break-all;">
									ҽѧԺ
								</td>
								<td style="word-break:break-all;">
									��
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="5">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									���پ�
								</td>
								<td style="word-break:break-all;">
									ҽѧԺ
								</td>
								<td style="word-break:break-all;">
									��
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="6">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									���پ�
								</td>
								<td style="word-break:break-all;">
									ҽѧԺ
								</td>
								<td style="word-break:break-all;">
									��
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="7">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									���پ�
								</td>
								<td style="word-break:break-all;">
									ҽѧԺ
								</td>
								<td style="word-break:break-all;">
									��
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="8">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									���پ�
								</td>
								<td style="word-break:break-all;">
									ҽѧԺ
								</td>
								<td style="word-break:break-all;">
									��
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="9">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									���پ�
								</td>
								<td style="word-break:break-all;">
									ҽѧԺ
								</td>
								<td style="word-break:break-all;">
									��
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
						</tbody>
					</thead>
				</table>
				<div id="pager" class="pagination"><div class="pageleft"><p class="pagenum"><span style="display:none">��<input type="text" id="pageNum" value="1" style="text-align:center;width:20px;">/<span class="red" id="pageCount">1</span>ҳ��</span>ÿҳ��ʾ<select name="numList" id="numList"><option value="10">10</option><option value="20">20</option><option value="50">50</option><option value="100">100</option><option value="200">200</option></select>�� /  ��<span class="red" id="rowConut">2</span>����¼ </p></div><div class="pageright"><div class="paging"> <a id="first" class="first pointer" title="��ҳ">��&nbsp;ҳ</a>&nbsp;<a id="back" class="prev pointer" title="��һҳ">��һҳ</a>&nbsp;<a id="next" class="next pointer" title="��һҳ">��һҳ</a>&nbsp;<a id="last" class="last pointer" title="ĩҳ">ĩ&nbsp;ҳ</a></div></div></div>
			</div>
		</div>
	</body>
</html>
