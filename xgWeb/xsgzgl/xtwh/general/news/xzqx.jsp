<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xtwh/general/news/js/selectQx.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
	<html:form action="/xtwh_news.do?method=selectQx&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" value="dsh" id="shzt"/>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden"  id="toWho" value="${toWho}"/>
		<input type="hidden"  id="toWhoIndex" value="${toWhoIndex}"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
		</html:form>
		<div id="body" class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span id="title"> ��ٴ�����б� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="reset();return false;">
									����
								</button>
								<button type="button" onclick="setSearchTj();searchRs();return false;">
									ȷ��
								</button>
								<button type="button" onclick="myClose();">
									�ر�
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>
