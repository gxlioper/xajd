<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript">	
		function sendXx(){			
			if(window.opener == undefined){					 				
				var url = window.dialogArguments.document.forms[0].url.value;
				url+="&stdm="+curr_row.getElementsByTagName('input')[0].value;
				window.dialogArguments.document.forms[0].action = url;
				window.dialogArguments.document.forms[0].submit();
			}else{
				var url = window.opener.document.forms[0].url.value;
				url+="&stdm="+curr_row.getElementsByTagName('input')[0].value;
				window.opener.document.forms[0].action = url;
				window.opener.document.forms[0].submit();
			}
			window.close();
		}			
		jQuery(function(){
			xyDisabled('xy');
		})	
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ϣά�� - ${lx }��Ϣ</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commXgInfo" method="post">	
		<!-- ������ -->
		<%@ include file="/comm/hiddenValue.jsp"%>
		<!-- ������ end-->
			<input type="hidden" id="lx" name="lx" value="${lx }" />
			<input type="hidden" id="zd" name="zd" value="${zd }" />
			<input type="hidden" id="va" name="va" value="${va }" />
			
			<div class="toolbox">
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/commXgInfo.do?method=xsxxManage');">
											��ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
				<logic:empty name="stxxInfo">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="stxxInfo">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">��ʾ��������ͷ��������˫��ѡ��ѧ����Ϣ.</font> 
						</span>
					</h3>
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="stxxInfo" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="sendXx()">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--���� end-->
						</table>
						<!--��ҳ-->
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=cdtyXsstForm"></jsp:include>
						<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>	
		</html:form>
	</body>
</html>
