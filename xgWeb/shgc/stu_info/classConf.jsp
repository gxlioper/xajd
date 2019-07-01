<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body>
			<html:form action="/arrangeClass.do" method="post">
				<input type="hidden" id="realTable" name="realTable" value="fbcssdb"
							scope="request" />
				<input type="hidden" id="tableName" name="tableName" value="fbcssdb"
							scope="request" />
				<div class="tab_cur" id="jd">
					<p class="location" id="title_m">
						<em>���ĵ�ǰλ��:</em><a>
						�ְ��ѧ�� - �������� - רҵ�༶����
						</a>
					</p>
				</div>
				<div class="tab">
					<table width="100%"  border="0" class="formlist">
					 <thead>
	    				<tr>
	        				<th colspan="6"><span>��������</span></th>
	        			</tr>
	   				 </thead>
	   				 </table>
	   			</div>
	   			<div class="toolbox">
	   				 <div class="toolbox">
					  <logic:equal value="yes" name="writeAble" scope="request">	
					 	<div class="buttonbox">
					    <ul>
						<li> <a href="#" onclick="refreshForm('arrangeClass.do?method=classConf&&doType=save')" class="btn_xg"> ���� </a> </li>
						<li> <a href="#" onclick="delMore('arrangeClass.do?method=classConf&&doType=del&&pkValue=')" class="btn_sc"> ɾ�� </a> </li>
						<li> <a href="#" onclick="dataExport()" class="btn_dc"> ���� </a> </li>
					    </ul>
					 </div>
					 </logic:equal>
					<div class="searchtab">
						<table width="100%" border="0">
						<tbody>
							<tr>							
								<th align="right">
									<div>
									&nbsp;&nbsp;ÿ��������									
									<html:text property="bjrs" />
									</div>
								</th>
								<td width="10">
									<div>
									<input type="hidden" name="go" value="a" />
									<button   id="search_go"
										onclick="allNotEmpThenGo('/xgxt/arrangeClass.do?method=classConf');">
										���ɰ༶����
									</button>
								</div>
							</td>
							</tr>
						</tbody>
					</table>
					</div>
				</div>
				<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
			 		 		��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��˫��һ�п���ѡ����������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
						 <table summary="" id="rsTable" class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">									
									<logic:iterate id="tit" name="topTr" offset="0" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand">									
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
										<input type="hidden" value="<bean:write name="v" />" />
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="3">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>							
						</table>
						</logic:notEmpty>							
						<logic:notEmpty name="result">
						<logic:equal value="true" name="result">
							<script>
								alert("�����ɹ���");
								Close();
								document.getElementById('search_go').click();						
							</script>
						</logic:equal>
						<logic:equal name="result" value="false">
							<script>
								alert("����ʧ��!");
							</script>
						</logic:equal>
					</logic:notEmpty>
			</html:form>			
	</body>
</html>
