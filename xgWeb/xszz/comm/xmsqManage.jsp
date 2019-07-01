<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript">
		//��ʾ��Ŀ������ϸ
		function xmSq(num,type){
		
			var id = "xmdm"+ num;
			var sqsjId ="ysqsj"+ num;
			var ysqId ="ysq"+ num;
			
			var xmdm = $(id).value;
			var sqsj = $(sqsjId).value;
			var ysq = $(ysqId).value;
			var iskns = $('iskns').value;
			var url = "/xgxt/commXszz.do?method=xmsqUpdate&iskns="+iskns;	
			url += "&xmdm="+xmdm;
			url += "&sqsj="+sqsj;
			url += "&mklx="+type;
			url += "&ysq="+ysq;
			
			showOpenInfo(url,'sb','','900','600');
		}
		
		//�޸���Ŀ���
		function chXmlb(){
			var url = "/xgxt/commXszz.do?method=xmsqManage";
			refreshForm(url);
		}
		
		function rowOnClick_onshow(){
			if(curr_row == null && $("tr0")){
				curr_row = $("tr0");
			}
			
			var xxdm = jQuery('#xxdm').val();
			if ('10058' == xxdm){
				var tr = jQuery('tr',jQuery('#dataTab'));
				
				for (var i = 0 ; i < tr.length ; i++){
					var button = jQuery('button',tr[i]);
					var disabled = jQuery('input[name=disabled]',tr[i]).val();
					var xmdm = jQuery('input[name=xmdm]',tr[i]).val();
					
					if ('5001' != xmdm && "true"==disabled){
						jQuery(button).attr('disabled',true);
						jQuery(button).attr('class','disabled');
					}
				}
			}
			
		}
		</script>
	</head>
	<body onload="rowOnClick_onshow()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commXszz">
			<!-- ������ -->
			<html:hidden property="iskns" styleId="iskns"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }"/>
			<button type="button" class="button2" style="display:none" id="search_go"
				onclick="refreshForm('/xgxt/xszz_xscz_xmsq.do');">
				���ذ�ť
			</button>
			<!-- ������ end-->
			<div class="toolbox">
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									��ǰѧ��
								</th>
								<td>
									<html:select property="xn" style="width:120px" styleId="xn" disabled="true">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>
									��ǰ���
								</th>
								<td>
									<html:select property="nd" style="" styleId="nd" disabled="true">
										<html:options collection="ndList" property="nd" labelProperty="nd" />
									</html:select>
								</td>
								<th>
									��ǰѧ��
								</th>
								<td>
									<html:select property="xq" style="" styleId="xq" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									��ǰ����		
								</th>
								<td>
									<html:text property="sqsjCn" disabled="true"/>
								</td>
								<th>
									��Ŀ���
								</th>
								<td>
									<html:select property="xmlb" style="" styleId="xmlb" onchange="chXmlb()">
										<html:option value=""></html:option>
										<html:options collection="xmlbList" property="en" labelProperty="cn" />
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
						<span> ��������Ŀ&nbsp;&nbsp; 
							<logic:empty name="rsList">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rsList">
						<table summary="" class="dateline" width="100%" id="dataTab">
							<!-- ��ͷ -->
							<thead>	
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<tbody>
								<logic:iterate name="rsList" id="rs" indexId="index">
									<tr onclick="rowOnClick(this);" 
										id="tr${index }"
										style="cursor:hand" 
										ondblclick="">
										<td>
											${rs.xmmc }
											<html:hidden name="rs" property="xmdm" styleId="xmdm${index }"/>
											<html:hidden name="rs" property="ysqsj" styleId="ysqsj${index }"/>
											<html:hidden name="rs" property="ysq" styleId="ysq${index }"/>
											<html:hidden name="rs" property="disabled" />
										</td>
										<td>
											${rs.xmlb }
										</td>
										<td>
											${rs.shjb }
										</td>
										<td>
											${rs.kzjb }
										</td>
										<td>
											${rs.szrs }
										</td>
										<td>
											${rs.syrs }
										</td>
										<td>
											${rs.shqk }
										</td>
										<td align="center" width="5%">
											<!-- δ��� -->
											<logic:notEqual name="rs" property="ysh" value="yes">
												<!-- δ���� -->
												<logic:notEqual name="rs" property="ysq" value="yes">
													<button type="button" onclick="xmSq(${index },'sq')" 
														style="width:80px;height: 20px" >����</button>
												</logic:notEqual>
												<!-- ������ -->
												<logic:equal name="rs" property="ysq" value="yes">
													<button type="button" onclick="xmSq(${index },'xg')" 
														style="width:80px;height: 20px" >�޸�</button>
												</logic:equal>
											</logic:notEqual>
											<!-- ����� -->
											<logic:equal name="rs" property="ysh" value="yes">	
												<button type="button" disabled="disabled"
														style="width:80px;height: 20px" >�����</button>
											</logic:equal>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
								<!--���� end -->
						</table>
					<!--��ҳ-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
					<script type="text/javascript">
						$('choose').className="hide";
					</script>
					<!--��ҳend-->
					</logic:notEmpty>
				</div>
			</div>
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
	</body>
</html>