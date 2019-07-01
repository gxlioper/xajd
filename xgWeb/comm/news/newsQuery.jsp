<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������</a>
			</p>
		</div>
		
		<html:form action="/showNews">
			<html:hidden property="tagId" value="${tagId}"/>

			<div class="toolbox">
			<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('showNews.do')">
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
								<th>���ű���</th>
								<td>
									<html:text property="newsTitle"></html:text>
								</td>
								<th>
									����ʱ��
								</th>
								<td>
									<html:text property="pubKssj"
											   styleId="pubKssj"
											   readonly="true"
											   styleClass="jssj"
											   onclick="return showCalendar(this.id,'y-MM-dd');"
											   ></html:text>
									��
									<html:text property="pubJssj" 
											   styleId="pubJssj"
											   readonly="true"
											   styleClass="jssj"
											   onclick="return showCalendar(this.id,'y-MM-dd');"
											   ></html:text>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> �����б�&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ����������</font>
						</logic:notEmpty> 
					</span>
				</h3>

				<div class="con_overlfow">
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr>
								<td  onclick="tableSort(this)">
									ģ������
								</td>
								<td  onclick="tableSort(this)">
									���ű���
								</td>
								<td  onclick="tableSort(this)">
									������
								</td>
								<td  onclick="tableSort(this)">
									����ʱ��
								</td>
							</tr>
						</thead>
						<tbody>
								<logic:notEmpty name="news">
									<logic:iterate name="news" id="n">
										<tr>
											<td>
												 ${n.mkmc }
											</td>
											<td>
												<a
													href="newsContent.do?newsId=${n.newsid }"
													target="_blank">
												${n.newstitle }
												</a>
											</td>
											<td>${n.fbr }</td>
											<td>${n.pubtime }</td>
										</tr>
									</logic:iterate>
									
									<%
											for (int i = 0; i < (Integer) request
												.getAttribute("maxNum")
												- ((List) request.getAttribute("news")).size(); i++) {
									%>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<%
									}
									%>
								</logic:notEmpty>
								<logic:empty name="news">
								<%
										for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<%
								}
								%>
							</logic:empty>
								
						</tbody>
					</table>
				</div>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
					<script type="text/javascript" defer>
						$('choose').className="hide";
					</script>
				<!--��ҳ��ʾ-->
			</div>
		</html:form>
	</body>
</html>
