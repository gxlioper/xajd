<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		
		function searchRs(){
			allNotEmpThenGo('/xgxt/stu_sfzhinfo_jy.do?method=stuSfzhInfoJy');
		}

		var count=0;
		function modi(){
			count=0;
			var ck=document.getElementsByName("checkVal");
			for(var i=0;i<ck.length;i++){
				if(ck[i].checked){
					count++;
				}
			}
			if(count>0){
				tipsWindown("�޸���ʾ","id:tempDiv","350","200","true","","true","id");
			}else{
				alertInfo("��ѡ����Ҫ�޸ĵļ�¼��");
			}
		}

		function modi_submit(){
			var temp_num=0;
			var ck=document.getElementsByName("updateType");
			for(var i=0;i<ck.length;i++){
				if(ck[i].checked){
					temp_num++;
				}
			}
			if(temp_num==0){
				alertInfo("��ѡ����Ҫ���µ���Ϣ��");
				return false;
			}
			if(count>1000){
				alertInfo("ѡ��ļ�¼�������Գ���1000����");
				return false;
			}
			confirmInfo("ȷ���޸�ѡ�е�"+count+"����¼��",function(ok){
					if("ok"==ok){
						allNotEmpThenGo('/xgxt/stu_sfzhinfo_jy.do?method=stuSfzhInfoJy&doType=update');
						BatAlert.showTips('�����޸ģ����Ե�...');
					}else{
						closeWindown();
					}
				});
		}

		</script>
	</head>
	<body>

		<html:form action="/stu_sfzhinfo_jy" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>			
			<!-- ���� end-->
			<!-- ��ʾ��Ϣ START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					�ô��ĵ��쳣�����У�<br/>
					1.���֤�Ų���ȷ<br/>
					2.���֤�Ű�������Ϣ��ϵͳ�еļ��ᡢ�������ں��Ա𲻶�Ӧ<br/>
					ע�⣺
					1).�����ʽ��xxxxxx/xxxxxx/xxxxxx(��������ǹ���)<br/>
					2).�������ڸ�ʽ��yyyy-MM-dd��yyyy/MM/dd��yyyyMMdd<br/>
					3).�Ա��ʽ��"'��'&'Ů'"��"'1'&'2'"(1:��;2:Ů)<br/>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->	
			<!-- ģ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="modi();return false;" class="btn_xg"> �޸� </a></li>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span>
						��ѯ���&nbsp;&nbsp;<font color="blue"></font>
					</span>
				</h3>

				<logic:notEmpty name="rsList">
				<div class="con_overlfow" >
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec_page()" />

								</td>
								<logic:iterate id="tit" name="topTr"   indexId="index">
									<td>
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rsList" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="">
									<td>
										<input type="checkbox" name="checkVal" id="pkV" 
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
										/>
									</td>
									<logic:iterate id="v" name="s" offset="0" length="1"><td><bean:write name="v"/></td></logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1"><td><bean:write name="v"/></td></logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1"><td><bean:write name="v"/></td></logic:iterate>
									<logic:iterate id="v" name="s" offset="3" length="1"><td><bean:write name="v"/></td></logic:iterate>
									<logic:iterate id="v" name="s" offset="4" length="1">
										<td <logic:iterate id="yczt_v" name="s" offset="10" length="1">
											<logic:equal value="��" name="yczt_v"> style="color: red"</logic:equal>
										</logic:iterate>>
											<bean:write name="v"/>
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="5" length="1"><td><bean:write name="v"/></td></logic:iterate>
									<logic:iterate id="v" name="s" offset="6" length="1">
										<td <logic:iterate id="yczt_v" name="s" offset="11" length="1">
											<logic:equal value="��" name="yczt_v"> style="color: red"</logic:equal>
										</logic:iterate>>
											<bean:write name="v"/>
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="7" length="1"><td><bean:write name="v"/></td></logic:iterate>
									<logic:iterate id="v" name="s" offset="8" length="1">
										<td <logic:iterate id="yczt_v" name="s" offset="12" length="1">
											<logic:equal value="��" name="yczt_v"> style="color: red"</logic:equal>
										</logic:iterate>>
											<bean:write name="v"/>
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="9" length="1"><td><bean:write name="v"/></td></logic:iterate>
									
<%--									<logic:iterate id="v" name="s" offset="0" length="10">--%>
<%--										<td nowrap>--%>
<%--											<bean:write name="v"/>--%>
<%--										</td>--%>
<%--									</logic:iterate>--%>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					<!--��ҳ��ʾ-->
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
					<!--��ҳ��ʾ-->
					</div>
				</logic:notEmpty>
			</div>
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span id="th_span_lable">��ѡ����Ҫ���µ���Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<input type="checkbox" name="updateType" value="xb"/>
								</th>
								<td>
									�Ա�
								</td>
							</tr>
							<tr id="tr_tsyy">
								<th>
									<input type="checkbox" name="updateType" value="csrq"/>
								</th>
								<td>
									��������
								</td>
							</tr>
							<tr id="tr_bz">
								<th>
									<input type="checkbox" name="updateType" value="jg"/>
								</th>
								<td>
									����
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div id="submit_bz" class="bz" style="color: red;">
										
									</div>
									<div class="btn">
										<button type="button" name="����"  onclick="modi_submit()">
											ȷ �� 
										</button>
										<button type="button" name="ȡ��"  onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("����ʧ�ܣ�");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("�����ɹ���");
					</script>
				</logic:equal>
		</logic:notEmpty>
	</body>
</html>
