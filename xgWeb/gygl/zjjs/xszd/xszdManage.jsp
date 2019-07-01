<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script language="javascript">
	//��ѯ�����
	function searchRs(){
		allNotEmpThenGo('gyglZjjs.do?method=xszdManage');
	}
	
		//��ʾ��ѧ�������ϸ
	function showXszdDetail(){
		
		var pk = curr_row.getElementsByTagName('input')[0].value;
		var url = "gyglZjjs.do?method=xszdUpdate&mklx=view";
			url+= "&pk="+pk;

		showTopWin(url,800,600);
	}
	
	function expXszdToExcle(){
			
		var url = "gyglZjjs.do?method=expXszdToExcle";

		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}

	function printSqb(){
		var checkbox = jQuery('input[name=primarykey_checkVal]:checked');
		if (checkbox.length != 1){
			alertInfo('��ѡ��һ����Ҫ��ӡ�ļ�¼');
			return false;
		}else {
			var pkValue = jQuery(checkbox).val();
			window.open('gyglZjjs.do?method=printSqb&pk='+pkValue);
		}
	}
	</script>
	</head>
	
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.���������е����״̬���������������˽���ģ�Ҳ����<font color="blue"><bean:message key="lable.xb" />���</font>��</br>
				2.��ѡһ����¼��ִ��<font color="blue">��ӡ�����</font>���������������ѧ���������֮���ִ�д�ӡ������</br>
				3.ִ��<font color="blue">��������</font>�������������Excel��ʽ��ͳ�Ʊ���ͳ�Ʒ�Χ�Ǹ�������ѡ��Ĺ������������ġ�</br>
				4.��һ����¼����<font color="blue">˫��</font>���������Բ鿴��ϸ�����
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/gyglZjjs" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="shzt" styleId="shzt" value="" />
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_dy"
								onclick="printSqb();return false;return false;"
								class="btn_dc">
								��ӡ�����
							</a>
						</li>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" id="btn_dc"
									onclick="expXszdToExcle();return false;return false;"
									class="btn_dc">
									��������
								</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->	
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				
			</div>
			<!-- �๦�ܲ����� end -->
			
			<!-- ��ѯ���-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						��ѯ���
					</span>
				</h3>

				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="1">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					
					<!--���� -->
					<logic:iterate name="rs" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="showXszdDetail();">
							<logic:iterate id="v" name="s" offset="0" length="1">
								<td align="center" width="5px">
									<input type="checkbox" id="pk_${index }"
										name="primarykey_checkVal"  
										value="${v }"		
										<logic:empty name="v">disabled="disabled"</logic:empty>
									/>
								</td>
								
							</logic:iterate>
							<!-- ��ʾ��Ϣ -->
							<logic:iterate id="v" name="s" offset="1">
								<td align="left">
									${v }
								</td>
							</logic:iterate>						
						</tr>
					</logic:iterate>
					<!--���� end-->
				</table>

				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglZjjsXszdForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
			
		</html:form>
	</body>
</html>
