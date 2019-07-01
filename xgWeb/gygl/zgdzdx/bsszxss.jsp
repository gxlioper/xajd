<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zgdzdx_Gygl.do?method=getSsinfo" method="post">
		<logic:empty name="errinfo">
			<logic:notEmpty name="view">
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="stu" name="userType">
				<div class="buttonbox">
					<ul>
						
							<li>
								<a href="#"
									onclick="showInfo('/xgxt/zjcmGygl.do?method=wsjcView','update','600','480')"
									class="btn_xg">��  ס</a>
							</li>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/zjcmGygl.do?method=wsjcManage','del')"
									class="btn_sc">�鿴��ס������Ϣ</a>
							</li>		
					
					</ul>
				</div>
				</logic:equal>
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
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/zgdzdx_Gygl.do?method=getSsinfo');">
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
									У��
								</th>
								<td>
									<html:select property="xiaoqu" styleId="xiaoqu" onchange="refreshForm('/xgxt/zgdzdx_Gygl.do?method=getSsinfo');" style="width:120px">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="xiaoQList" labelProperty="mc"
											property="dm" />
									</html:select>												
								</td>
								<th>
									¥������
								</th>
								<td>
									<html:select property="lddm" styleId="lddm" style="width:100px" onchange="refreshForm('/xgxt/zgdzdx_Gygl.do?method=getSsinfo');" style="width:120px">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ldList" labelProperty="mc"
											property="dm" />
									</html:select>
								</td>
								<th>
									¥��
								</th>
								<td>
									<html:select property="cs" styleId="cs" onchange="refreshForm('/xgxt/zgdzdx_Gygl.do?method=getSsinfo');" style="width:120px">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="lcList" labelProperty="mc"
											property="dm" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									�շѱ�׼
								</th>
								<td>
									<html:select property="sfbz" styleId="sfbz" style="width:120px">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="sfbzList" labelProperty="mc"
											property="dm" />
									</html:select>													
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
								<th>
									
								</th>
								<td>
									
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
								<tr style="cursor:hand" onclick="rowOnClick(this);oneOption();" ondblclick="sendinfo()">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pkV"
												value="<bean:write name="v"/>"/>
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align="center" nowrap>
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
			</logic:notEmpty>
			</logic:empty>
			 <logic:empty name="view">
			<script language="javascript">
				alert('ֻ�в�ʿ������ѡ����,\n\n��ʿ���ж���ί�е�ѧ������ѡ���ᣡ');
			</script>
		</logic:empty>
		<logic:notEmpty name="errinfo">
		<script type="text/javascript">
			alert('<bean:write name="errinfo"/>');
		</script>
	</logic:notEmpty>
		</html:form>
		<logic:equal value="ok" name="result">
			<script language="javascript">
				alert('                       ��ס�ɹ�������\n��ͨ���鿴��ס������Ϣ��ť�鿴��������Ϣ');
				document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script language="javascript">
				alert('��סʧ�ܣ�');
				document.getElementById('search_go').click();
			</script>
		</logic:equal>

	</body>
</html>
