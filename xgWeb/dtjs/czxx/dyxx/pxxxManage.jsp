<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	</head>
	
	<body onload="xyDisabled('xy');">
		<html:form action="/czxxDtjsDyxx" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /></font>
				</div>
			</logic:notEmpty>
			
			<logic:empty name="msg">
				<div class="rightcontent">
						<logic:equal value="yes" name="writeAble">
						<div class="toolbox">
						<div class="buttonbox">
							<ul>
								<li><a href="#" class="btn_zj" onclick="showTopWin('/xgxt/czxxDtjsDyxx.do?method=pxxxUpdate',800,600);return false;">����</a></li>
								<li><a href="#" class="btn_xg" onclick="showInfo('/xgxt/czxxDtjsDyxx.do?method=pxxxUpdate','update','800','600');return false;">�޸�</a></li>
								<li><a href="#" class="btn_sc" onclick="sumitInfo('/xgxt/czxxDtjsDyxx.do?method=pxxxManage','del');return false;">ɾ��</a></li>
								<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">����</a></li>
								<li><a href="#" class="btn_dc" onclick="dataExport();return false;">����</a></li>
							</ul>
						</div>
						</div>
						</logic:equal>
						
						<div class="searchtab">
						<table width="100%" border="0">
						<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="a" />
		              		<button type="button" class="btn_cx" id="search_go" 
		              		onclick="allNotEmpThenGo('/xgxt/czxxDtjsDyxx.do?method=pxxxManage');">
		              		�� ѯ
		              		</button>
		             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              			�� ��
		             		 </button>
		            		</div>
		          		</td>
		       			</tr>
		     			</tfoot>
		              	<tbody>
		              	<tr>
		              		<th>��ѵʱ��</th>
		              		<td><html:text property="pxsj" styleId="pxsj"
											onblur="dateFormatChg(this)"
											onclick="return showCalendar('pxsj','y-mm-dd');"/></td>
							<th>��ѵ����</th>
							<td><html:text property="pxmc" styleId="pxmc" maxlength="25"/>
							</td>
						</tr>
		              	</tbody>
						</table>
						</div>
<!--================================================================================================	-->
					<div class="formbox">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font> 
				 		 </logic:empty>
				 		 <logic:notEmpty name="rs">
				 		 	<font color="blue">˫��һ����¼������ʾ��ϸ��Ϣ��</font> 
				 		 </logic:notEmpty>
				    </span>
				    </h3>
				   
				  <logic:notEmpty name="rs">
				  <table summary="" class="dateline" width="100%">
				    <thead>
				      <tr>
				        <td align="center">
							<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
						</td>
						<logic:iterate id="tit" name="topTr" offset="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)">
													<bean:write name="tit" property="cn" />
												</td>
						</logic:iterate>
				      </tr>
				    </thead>
				    <tbody>
				      <logic:iterate name="rs" id="s" indexId="index">
					      <tr onclick="rowOnClick(this);" style="cursor:hand" 
							ondblclick="showInfo('/xgxt/czxxDtjsDyxx.do?method=pxxxUpdate','view','800','600')">
						<td align="center">
							<input type="checkbox" id="checkVal" name="checkVal" 
								value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
						</td>
						<logic:iterate id="v" name="s" offset="1">
							<td align="left">
							<bean:write name="v" />
							</td>
						</logic:iterate>
					    </tr>
				      </logic:iterate>
				    </tbody>
				  </table>
				  <!--��ҳ��ʾ-->
				     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=czxxDtjsForm"></jsp:include>
				  <!--��ҳ��ʾ-->
				  </logic:notEmpty>
				</div></div>	
					</logic:empty></html:form>

		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
