<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
			function querygo(){
				 	document.forms[0].action = "/xgxt/zcwjquery.do?act=go&doType=query";
				 	document.forms[0].submit();
		    }
		</script>
		<script language="javascript">
			function viewMoreinfo(doType){
			var url ="/xgxt/showmorezcwj.do?wjbt=";
			var pkValue ="";
			
			 if (doType == "view"){
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
				 url += pkValue;
				window.open(url);
			 }
			}		
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ��ҵָ�� - �����ļ���ѯ</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<button class="btn_cx" id="search_go" onclick="querygo()">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
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
									�ļ�����
								</th>
								<td>
									<html:text name="rs1" property="wjbt" />
								</td>
								<th>
									�ļ�����
								</th>
								<td>
									<html:select name="rs1" property="wjlx" style="width:120px">
										<html:option value=""></html:option>
										<html:options collection="wjlxList" property="lxdm" labelProperty="lxmc"/>
									</html:select>
								</td>
								<th>
									������
								</th>
								<td>
									<html:text name="rs1" property="fbr" style="width:70px" />
								</td>
								<th>
									����ʱ��
								</th>
								<td>
									<html:select name="rs1" property="xjsj" style="width:90px">
										<html:option value=""></html:option>
										<html:option value="-1">����</html:option>
										<html:option value="-2">������</html:option>
										<html:option value="-7">һ����</html:option>
										<html:option value="-15">������</html:option>
										<html:option value="-30">һ����</html:option>
										<html:option value="-90">������</html:option>
										<html:option value="-180">������</html:option>
										<html:option value="-365">һ����</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> 
							<logic:notEmpty name="rs">
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
							</logic:notEmpty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<table class="dateline" width="100%" >
							<thead>
								<tr>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onmouseover="rowOnClick(this)" style="cursor:hand"
										ondblclick="viewMoreinfo('view')">
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<bean:write name="v" />
												<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<!--��ҳ��ʾ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						<!--��ҳ��ʾ-->
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
					</logic:notEmpty>
				</div>
				
		</html:form>
	</body>
</html>

