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
		allNotEmpThenGo('gyglZjjs.do?method=xszdsh');
	}
	
	//��ʾ��ѧ�������ϸ
	function showXszdDetail(){
		var num = jQuery("input[name=primarykey_checkVal]:checked").length;
		
		if(num == 0){
			alertError("�빴ѡһ����ϣ����˵ļ�¼");
			return false;
		}if(num > 1){
			alertError("ֻ�ܹ�ѡһ����¼����ȷ��");
			return false;
		}
		
		var pk = jQuery("input[name=primarykey_checkVal]:checked")[0].value;
		var url = "gyglZjjs.do?method=xszdUpdate";
			url+= "&pk="+pk;

		showTopWin(url,800,600);
	}
	
	//��������
	function saveShzt(shzt){
		var num = jQuery("input[name=primarykey_checkVal]:checked").length;
		
		if(num == 0){
			alertError("�빴ѡ��ϣ����˵ļ�¼");
			return false;
		}
		
		$("shzt").value = shzt;
		confirmInfo("���Ƿ�Ҫ���"+shzt+"�������¼? ��ȷ��",submitShzt);
	}
	
	function submitShzt(tag){
		if(tag == "ok"){
			var shzt = $("shzt").value;
			
			showTips('�����У����Ժ�......');
			var url = "gyglZjjs.do?method=xszdsh&doType=save";
				url+= "&shzt="+shzt;
				
			refreshForm(url);
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
				1.������Ǹ���Ա�Ļ���������н��г�<bean:message key="lable.xb" />�û�δ��˹��������¼���������鿴�Ļ�����ǰ�������ѯģ�顣</br>
				2.�������<bean:message key="lable.xb" />�û��Ļ���������н��г�����Ա�û����ͨ���������¼���������鿴�Ļ�����ǰ�������ѯģ�顣
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
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" id="btn_sh"
									onclick="showXszdDetail();return false;"
									class="btn_sh">
									�������
								</a>
							</li>
							<li>
								<a href="#" id="btn_shtg"
									onclick="saveShzt('ͨ��');return false;"
									class="btn_shtg">
									�������ͨ��
								</a>
							</li>
							<li>
								<a href="#" id="btn_shbtg"
									onclick="saveShzt('��ͨ��');return false;"
									class="btn_shbtg">
									������˲�ͨ��
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
						<tr onclick="rowOnClick(this);" style="cursor:hand">
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
