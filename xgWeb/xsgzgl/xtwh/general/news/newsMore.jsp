<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script language="javascript">
		
		
		//Ԥ��
		function newsyl(){
			//xtwh_news.do?method=newsInfo&newsId=
		}


		//��ѯ���
		function searchRs(){
			allNotEmpThenGo('xtwh_news.do?method=newsMore');
		}

		
		</script>
	</head>
	<body >
		<html:form action="/xtwh_news" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ҳ-֪ͨ����</a>
				</p>
			</div>
			
			<!-- ��ʾ��Ϣ end-->	
			<!-- ģ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<!-- �������� 
				include file="/comm/search/superSearchArea.jsp"
				 �������� end-->
				<div id="tbody_search_query" class="searchtab">
				<table width="100%" class="">
					<tbody>
						<tr>
							<th>֪ͨ���</th>
							<td>
								<html:select property="typeid" styleId="typeid" onchange="searchRs();return false;">
									<option value="">ȫ��</option>
									<html:options collection="typeList" property="typeid" labelProperty="typename" />
								</html:select>
							</td>
							<th>����</th>
							<td>
								<html:text property="bt" styleId="bt" style="width: 230px" maxlength="50"></html:text>
							<th>����ʱ��</th>
							<td colspan="2">
								<html:text property="kssj" styleId="kssj" readonly="readonly"  style="width:100px;" 
										   onclick="return showCalendar('kssj','y-mm-dd');" ></html:text>
								&nbsp;��
								<html:text property="jssj" styleId="jssj" readonly="readonly"  style="width:100px;" 
								   		   onclick="return showCalendar('jssj','y-mm-dd');" ></html:text>
							</td>
							<td>
								<div class="btn">
				              		<button type="button" id="search_go"  
				              		onclick="searchRs();return false;">
				              		�� ѯ
				              		</button>
				              		<button type="button" class="btn_cz" id="btn_cz" 
									onclick="searchReset();return false;">
									�� ��
									</button>
				            	</div>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span>
						��ѯ���&nbsp;&nbsp;
					</span>
				</h3>
				<div class="con_overlfow">
					<table width="99%" id="rsTable" class="dateline ">
						<thead>
							<tr align="center" style="cursor:hand">
								<td width="70%">
									����
								</td>
								<td width="30%">
									����ʱ��
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="newsList">
								<logic:iterate id="n" name="newsList">
									<tr>
										<td width="85%">
											<a style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden;float:left;" class="name" href="javascript:void(0);" onclick="window.open('newsContent.do?newsId=${n.newsid}');">${n.newstitle }</a>
											<logic:equal value="��" property="sfnew" name="n">
												<img  style="float:left;" src="<%=stylePath  %>/images/ico_new02.gif"/>
											</logic:equal>
											<logic:equal value="��" property="sfzd" name="n">
												<font color="red" style="float:left;">���ö���</font>
											</logic:equal>
										</td>
										<td>${n.pubtime }</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=newsManageForm"></jsp:include>
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--��ҳ��ʾ-->
			</div>
		</html:form>

	</body>
</html>
