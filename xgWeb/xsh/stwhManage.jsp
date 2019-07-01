<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script type="text/javascript" src="js/xtwh/bdsz.js"></script>
		<script type="text/javascript">
		function yxstdjb(){
			var pkValue=document.getElementsByName("checkVal");
			
			var n=0;
			var pk="";
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					pk=pkValue[i].value;
					n++;
				}	
			}
			
			if(n!=1){
				alert("�빴ѡһ������!");
				return false;
			}
			showOpenWindow('/xgxt/xsh.do?method=yxstdjb&pk='+pk,800,600)
		}
		</script>
	</head>

	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/xsh" method="post">
			<input type="hidden" id="userType" name="userType"
				value="${userType }>" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			
			<input type="hidden" name="likeCol" value="stmc!!stcsr" />
			<input type="hidden" name="queryCol" value="stxz" />

			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showTopWin('/xgxt/xsh.do?method=stwhUpdate','750','550')"
									class="btn_zj"> ����</a>
							</li>
							<li>
								<a href="#"
									onclick="showInfo('/xgxt/xsh.do?method=stwhUpdate','update','750','550');"
									class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/xsh.do?method=stwhManage','del')"
									class="btn_sc"> ɾ��</a>
							</li>
							<li>
								<a href="#" onclick="impAndChkDataForZdy('ty_bdsz')"
									class="btn_dr">����</a>
							</li>
							<li>
								<a href="#" onclick="expZdyData()"
									class="btn_dc"> ���� </a>
							</li>
							<logic:present name="cdtyxg">
							<li>
								<a href="#"
									onclick="yxstdjb()"
									class="btn_dy">�������ŵǼǱ�</a>
							</li>
							</logic:present>
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
											onclick="allNotEmpThenGo('/xgxt/xsh.do?method=stwhManage&doType=query')">
											�� ѯ
										</button>
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
									��������
								</th>
								<td>
									<html:text property="stmc" maxlength="20"></html:text>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="stxz" style="width:130px">
										<html:option value=""></html:option>
										<html:options collection="list" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
									��ʼ��
								</th>
								<td>
									<html:text property="stcsr" maxlength="20"></html:text>
								</td>
							</tr>
						</tbody>
					</table>
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
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" id="cb" name="cb" disabled="true"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="showInfo('/xgxt/xsh.do?method=stwhUpdate','view','650','450');"
										style="cursor:hand;">
										<td>
											<input type="checkbox" id="pk" name="checkVal"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<!--��ҳ��ʾ-->
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xshForm"></jsp:include>
						<!--��ҳ��ʾ-->
					</logic:notEmpty>
				</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("�����ɹ���");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("�����ɹ���");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>