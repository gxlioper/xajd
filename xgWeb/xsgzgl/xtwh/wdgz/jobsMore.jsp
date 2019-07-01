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
	</head>
	<body >
		<html:form action="/xtwh_wdgz" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ҳ-
						<logic:equal value="stu" name="userType">
							�ҵİ������
						</logic:equal>
						<logic:notEqual value="stu" name="userType">
							���칤��
						</logic:notEqual>
					</a>
				</p>
			</div>
			
			<button type="button" style="display:none;" id="search_go" onclick="allNotEmpThenGo('/xgxt/xtwh_wdgz.do?method=getMoreJobs');" </button>
			
			
			<!-- ��ʾ��Ϣ end-->	
			<!-- ģ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
			</div>
			<div class="formbox" id="cxjg">
<%--				<h3 class="datetitle_01">--%>
<%--					<span>--%>
<%--						��ѯ���&nbsp;&nbsp;--%>
<%--					</span>--%>
<%--				</h3>--%>
				<div class="con_overlfow">
					<table width="99%" id="rsTable" class="dateline tablenowrap">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<logic:equal value="stu" name="userType">
										�ҵİ������
									</logic:equal>
									<logic:notEqual value="stu" name="userType">
										���칤��
									</logic:notEqual>
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="jobsList">
								<logic:iterate id="j" name="jobsList">
									<tr>
										<td>
											
											<logic:equal value="stu" name="userType">
												<a class="name" href="${j.gnmkpath }">��${j.gznr }��${j.shztmc }��</a>
											</logic:equal>
											<logic:notEqual value="stu" name="userType">
												<logic:equal value="szdw_thjl_zdgzxsk.do" property="gnmkpath" name="j">
													<a class="name" href="${j.gnmkpath }">��${j.gznr}���У���&nbsp;<font color='red'>${j.dbs } </font>&nbsp;����Ҫ����ע��</a>
												</logic:equal>
												<logic:notEqual value="szdw_thjl_zdgzxsk.do" property="gnmkpath" name="j">
													<a class="name" href="${j.gnmkpath }">��${j.gznr}�����������У���&nbsp;<font color='red'>${j.dbs } </font>&nbsp;����Ҫ����ˡ�</a>
												</logic:notEqual>
											</logic:notEqual>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=myJobsForm"></jsp:include>
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--��ҳ��ʾ-->
			</div>
		</html:form>

	</body>
</html>
