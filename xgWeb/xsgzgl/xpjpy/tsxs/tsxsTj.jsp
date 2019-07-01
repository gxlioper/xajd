<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/tsxs/js/tsxsTj.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
	</head>
	<body>	
		<html:form action="/xpj_tsxs" method="post" styleId="form1">
		<html:hidden property="writeAble" styleId="writeAble" value="${writeAble}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal value="yes" name="writeAble">
			<div class="buttonbox">
					<ul>
							<li><a href="javascript:void(0);" onclick="add('${mklx}');return false" title="����������Ա" class="btn_zj" >����</a></li>
							<li><a href="javascript:void(0);" onclick="del();return false;" title="��ѡһ���������¼��ɾ������ѧ��" class="btn_sc">ɾ��</a></li>
							<logic:equal value="10878" name="xxdm">
								<li><a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">����</a></li>
							</logic:equal>
					</ul>
			</div>
			</logic:equal>	
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">ѧ��</th>
						<td>
							<html:select property="xn" styleId="xn" style="width:155px">
								<html:option value=""></html:option>
								<html:options collection="xnList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th width="10%">ѧ��</th>
						<td>
							<html:select property="xq" styleId="xq" style="width:155px">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>								
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>����ѧ��ͳ���б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
		</html:form>
	</body>
</html>
