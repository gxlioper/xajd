<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
	</head>
	<body >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ������������ - ��Ա����ֲ�</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zgdzdx_Gygl" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
		    <input type="hidden" id="realTable" name="realTable"
			    value="<bean:write name="realTable" scope="request"/>" />
			
			<!-- ҳǩ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
						
							<a href="#" 
								onclick="dataExport()"
								class="btn_dc">����</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/zgdzdx_Gygl.do?method=dyDormManage')">
											��ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									У����
								</th>
								<td>
									<html:select property="xqdm" style="width:150px" styleId="xqdm" 
										onchange="getYqList()">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="xiaoqquList" property="dm"
											labelProperty="mc" />
									</html:select>												
								</td>
								<th>
									԰����
								</th>
								<td>
									<html:select property="yqdm" style="width:180px" styleId="yqdm" 
										onchange="getYqLdList()">
										<html:options collection="yqList" property="dm"
											labelProperty="mc" />
									</html:select>		
								</td>
								<th>
									¥����
								</th>
								<td>
									<html:select property="lddm" style="width:120px"
										onchange="getqshLb()" styleId="lddm">										
										<html:options collection="ldList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
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
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">									
									<td>
<%--										<logic:iterate id="v" name="s" offset="0" length="1">--%>
<%--											<input type="hidden" value="<bean:write name="v"/>" />--%>
<%--										</logic:iterate>--%>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td >
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--���� end-->
						</table>
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>	
		</html:form>
	</body>
	
</html>
