<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript">
			function modi(url){
				if(curr_row != null){
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
					return true;
				}else{
					alert('��ѡ��Ҫ�޸ĵ������У�');
					return false;
				}
			}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/pjpyLcsz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="showTopWin('/xgxt/pjpyLcsz.do?method=pjlcszOne','600','480')"
									class="btn_zj">����</a>
							</li>
							<li>
								<a href="#"
									onclick="modi('/xgxt/pjpyLcsz.do?method=pjlcszOne')"
									class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="batchData('primarykey_cbv','/xgxt/pjpyLcsz.do?method=pjlcsz&doType=del','del')"
									class="btn_sc">ɾ��</a>
							</li>
<%--							<li>--%>
<%--								<a href="#"--%>
<%--									onclick=""--%>
<%--									class="btn_ccg">����</a>--%>
<%--							</li>--%>
							<li>
								<a href="#"
									onclick=""
									class="btn_dc">��������</a>
							</li>
							<li>
								<a href="#"
									onclick=""
									class="btn_yl">Ԥ������</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->			
				<!-- ��ѯ���-->
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
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������</font> 
						</span>
					</h3>
					<div class="con_overlfow">
						<table summary="" class="dateline" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="all" value="all" onclick="chec()" />
									</td>
									<logic:iterate id="tit" name="topTr">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" >
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">								
											<input type="checkbox" name="primarykey_cbv" id="pkV" value="${v}" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="0">
										<td align="left">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--���� end-->
						</table>
						</div>
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>