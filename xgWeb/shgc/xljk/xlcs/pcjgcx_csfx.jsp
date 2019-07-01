<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
		function dtjlb_tj_search()
		{
		    allNotEmpThenGo('/xgxt/xljk_xlcs_pcjgcx.do?act=pcjgcx&doType=dtjlb_tj_search');
		}
	</script>
	</head>
	<body>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/xljk_xlcs_pcjgcx" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips }</a>
				</p>
			</div>
			<div class="toolbox">
			<div class="searchtab">
			<table width="100%" border="0">
				<tbody>
						<tr>
							<th align="left">
								�Ծ���
							</th>
							<td>
								<html:select property="sjlsh" style="width:120px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="sjList" property="SJLSH"
										labelProperty="SJM" />
								</html:select>
							</td>
							
							<th>
								ѧ ��
							</th>
							<td>
								<html:text  property="xh" styleId="xh"
									onkeypress="" readonly="true" style="width:90px"/>
							</td>
							
							<th >
								�� ��
							</th>
							<td>
								<html:text property="dtsj" styleId="dtsj"
									readonly="true" style="width:180px"/>
							</td>
						</tr>				
						<tr>
							<th>
								�Ծ���ˮ��
							</th>
							<td>
								<html:text property="sjlsh" style="width:100px" readonly="true"/>
							</td>
							
							<th >
								�� ������ֵ��
							</th>
							<td>
								<html:text property="cj" style="width:100px" readonly="true"/>
							</td>			
							<td colspan="2">
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
			 		 <logic:notEmpty name="rs">
			 		 ��ǰ��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��������ͷ��������</font>
					</logic:notEmpty>
			    </span>
			    </h3>
			
			<logic:notEmpty name="rs">
					  <table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" ondblclick=""
							style="cursor:hand">
								<td >
									<bean:write name="s" property="SSLXMC"/>
								</td>
								<td>
									<bean:write name="s" property="DF"/>
								</td>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
			</logic:notEmpty>
			</div>
			
		</html:form>
	</body>
</html>
