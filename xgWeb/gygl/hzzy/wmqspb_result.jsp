<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" >
		function printBb(){
			
			var url = "/xgxt/wmqspb_result.do?czType=pbjj";
		
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
				
		}
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ͳ�Ʒ��� - �����������Ƚ��</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/wmqspb_result" method="post">
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:notPresent name="isGDBYXY">
							<li>
								<a href="#"
									onclick="refreshForm('/xgxt/wmqspb_result.do?czType=pbjg')"
									class="btn_up">���Ƚ������</a>
							</li>
							<li>
								<a href="#"
									onclick="printBb()"
									class="btn_dc">���𷢷��嵥����</a>
							</li>
						</logic:notPresent>
						<logic:present name="isGDBYXY">
							<li>
								<a href="#" 
									onclick="refreshForm('/xgxt/wmqspb_result.do?czType=gdby_pbjg')"
									class="btn_dc">�����������ȵǼǱ���</a>
							</li>	
						</logic:present>
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/wmqspb_result.do');this.disabled=true">
											��ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									���
								</th>
								<td>
									<html:select  property="nd" style="width:100px" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>															
								</td>
								<th>
									�·�
								</th>
								<td>
									<html:select property="yf" style="width:120px">								    
										<html:option value="01">01</html:option>
										<html:option value="02">02</html:option>
										<html:option value="03">03</html:option>
										<html:option value="04">04</html:option>
										<html:option value="05">05</html:option>
										<html:option value="06">06</html:option>
										<html:option value="07">07</html:option>
										<html:option value="08">08</html:option>
										<html:option value="09">09</html:option>
										<html:option value="10">10</html:option>
										<html:option value="11">11</html:option>
										<html:option value="12">12</html:option>																		
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
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
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">							
									<logic:iterate id="v" name="s" offset="0">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--���� end-->
						</table>
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>
		</html:form>
	</body>
</html>		

		
