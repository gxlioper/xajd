<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/xsgzgl/qgzx/js/sjwh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				//Ϊbuttonע���¼�
				jQuery("#btn_zj").click(add);
				jQuery("#btn_xg").click(update);
				jQuery("#btn_sc").click(deletes);
				jQuery("#search_go").click(query);
				jQuery("#btn_cz").click(function(){searchReset()});
			});
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" id="btn_zj" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" id="btn_xg" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" id="btn_sc" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			<!-- �������� -->
			<div class="searchtab">
				<html:form action="/cjff.do" method="post" styleId="myForm">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go">
										�� ѯ
									</button>
									<button type="button" class="btn_cz" id="btn_cz">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<html:text property="xh" name="model" ></html:text>
							</td>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<html:select  property="xn" styleId="xn"  style="width:150px">
									<html:options collection="xnList" labelProperty="xn" property="xn" />
								</html:select>
							</td>
							<th width="16%">
								���˲���2
							</th>
							<td width="34%">
								<html:select   property="yrbm" styleId="yrbm"  disabled="${rs.dis}" style="width:150px">
									<option value=''>ȫ��</option>
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�ڸ�״̬
							</th>
							<td>
								<html:select name="rs" property="zgzt" styleId="zgzt" style="width:150px">
									<option value=''>ȫ��</option>
									<option value='zg' selected="selected">�ڸ�</option>
								</html:select>
							</td>
							<th>
								<font class="red">*</font>��������
							</th>
							<td>
								<html:select name="rs" property="ffny" styleId="ffny"  disabled="${rs.dis}" style="width:150px">
									<html:option value="${rs.ffny}">${rs.ffny}</html:option>
									<html:options collection="ffnyList" property="ffny" labelProperty="ffny" />
								</html:select>
							</td>
							<th>
								��λ����
							</th>
							<td>
								<html:select name="rs" property="gwdm" styleId="gwdm" style="width:150px">
									<option value="" selected="selected">ȫ����λ</option>
									<html:options collection="gwList" property="gwmc" labelProperty="gwmc" />
								</html:select>
							</td>
							
						</tr>
					</tbody>
				</table>
				</html:form>
			</div>
		</div>
		
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��𷢷��б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
