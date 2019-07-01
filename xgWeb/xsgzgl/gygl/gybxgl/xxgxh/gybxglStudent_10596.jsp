<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�zhangh -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>

		<script type='text/javascript' src="js/xgutil.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('gyglnew_gybxgl_gybxgl_stu.do');
		}

		function add(){
			jQuery.post('gyglnew_gybxgl.do?method=viewXsxx',{},function(data){
				var lddm = data.lddm;
	
				if(!lddm){
					alertError("����δ��ס��Ԣ���ң��޷��ύ��Ԣ�������룡");
				}else{
					//showTopWin("gyglnew_gybxgl.do?method=gybxglAdd",800,600);
					showDialog("", 760, 400, "gyglnew_gybxgl.do?method=gybxglAdd");
				}					
			},'json');
		}
		
		// �޸Ĳ���
		function update(url){
			var size = jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").size();
			
			if(size != 1){
				alertInfo('��ѡ��һ��Ҫ������¼��');
				return false;
			}

			var flag = false;
			
			jQuery("td:has(input[type='checkbox'][name='primarykey_checkVal']:checked) > input[id*='pjzt_']").each(function(){
				if("δ����" != jQuery(this).val()){
					flag = true;
				}
			});
			
			if(flag){
				alertInfo("��ֻ���ԶԴ���״̬Ϊ'δ����'����Ϣ�������۲�����������ѡ�񣡣�");
			}else{
				url += "&pk="+curr_row.getElementsByTagName('input')[0].value;
				url += "&xh="+curr_row.getElementsByTagName('input')[1].value;
				showTopWin(url,800,600);
			}
		}	
		
		// ���۲���
		function pjUpdate(){
			var size = jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").size();
			
			if(size == 0){
				alertInfo('��ѡ��Ҫ������¼��');
				return false;
			}
			
			var flag = false;
			
			jQuery("td:has(input[type='checkbox'][name='primarykey_checkVal']:checked) > input[id*='pjzt_']").each(function(){
				if("�Ѵ���" != jQuery(this).val()){
					flag = true;
				}
			});
			
			if(flag){
				alertInfo("��ֻ���ԶԴ���״̬Ϊ'�Ѵ���'��'������'����Ϣ�������۲�����������ѡ�񣡣�");
			}else{
				tipsWindown("����̶ȵ���","id:tempDiv","400","230","true","","true","id");
			}
		}
		
		// ɾ������
		function delBatch(){
			var size = jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").size();
			
			if(size == 0){
				alertInfo('��ѡ��Ҫ������¼��');
				return false;
			}
			
			var flag = false;
			
			jQuery("td:has(input[type='checkbox'][name='primarykey_checkVal']:checked) > input[id*='pjzt_']").each(function(){
				if("δ����" != jQuery(this).val()){
					flag = true;
				}
			});
			
			if(flag){
				alertInfo("��ֻ���ԶԴ���״̬Ϊ'δ����'����Ϣ����ɾ��������������ѡ��");
			}else{
				batchData('primarykey_checkVal','gyglnew_gybxgl.do?method=gybxglStudent&doType=del','del');
			}
		}
		</script>
		<style type="text/css">
			.leftMargin{margin-left: 25px;}
		</style>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>������֪��</span>
				</h3><br/>
				<p>
					һ����������ÿ��ֻ���ˮ���硢ľ�������е�һ�����һ���౨������ֶദ��ά�޵��밴���ֵ����ޡ���ˮ���� ˮ��ͷ©ˮ��<br/>
					<span class="leftMargin">ˮ��ľ��ֹ����£�</span><br/>
					<span class="leftMargin">��ˮ��ά�����ݣ�ˮ��ͷ��ˮ��©ˮ��ˮ����ˮ���ȡ�</span><br/>
					<span class="leftMargin">�ڵ繤ά�����ݣ��ƹܡ����ܵơ�������·����Դ���������ص���ȡ�����˿���տ��ȡ�</span><br/>
					<span class="leftMargin">��ľ��ά�����ݣ��������ӡ����ӡ��š��������ӡ������ſۡ�����������ȡ�</span><br/>
					<span class="leftMargin">���๤ά�����ݣ��컨��©ˮ����ˮ����������ש��ǽ���</span><br/>
					����1����ˮ����ϵͳ������ˮ��ά�����ڹ�Ԣֵ�����ά�޵���<br/>
					<span class="leftMargin">2������ӿ�ά���뵽�ִ��������������ά�޵���</span><br/>
					<span class="leftMargin">3����ˮ����ά�������������ˮ����ϵ��</span><br/>
					��������ˮ�ܱ��ѡ���·��·�����ձ��ա���ˮ������������Ӧ����ά�ޣ��������ޣ������ϰ�ʱ�����ֱ�Ӳ����޵绰��<br/>
					<span class="leftMargin">����У����5896843      ��ɽУ����3696660���°�ʱ�估��ĩ�뵽��Ԣֵ���ұ��ޣ�����ά�޲�ֵ����Ա�绰����ά�ޡ�</span><br/>
					�ġ�һ�㲻Ӱ��ѧ���������ѧϰ���������ά�ޣ�ά�޲��Ž���ȫУά����������ͺ��ţ�һ�㲻�������졣<br/>
					�塢��ά���豸��ʩ����Ȼ�𻵵ģ���ά�޲��Ÿ����������ѧ����Ϊ�𻵵ģ�ʵ���г�ά�ޡ�<br/>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/gyglnew_gybxgl">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="search_go" name="search_go" onclick="searchRs();return false;"/>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="add();return false;" class="btn_zj"> �������� </a></li>
						<li><a href="#" onclick="update('gyglnew_gybxgl.do?method=gybxglView');return false;" class="btn_qb"> ��ϸ��Ϣ</a></li>
						<li><a href="#" onclick="pjUpdate();return false;" class="btn_sh"> ���� </a></li>
						<li><a href="#" onclick="update('gyglnew_gybxgl.do?method=gybxglAdd');return false;" class="btn_xg"> �޸� </a></li>
						<logic:equal name="userType" value="admin">
							<li><a href="#" onclick="delBatch();return false;" class="btn_sc"> ɾ�� </a></li>
						</logic:equal>
						<logic:equal name="userType" value="xx">
							<li><a href="#" onclick="delBatch();return false;" class="btn_sc"> ɾ�� </a></li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->	
			</div>
			<!-- �๦�ܲ����� end -->
			
			<!-- ��ѯ���-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						��ѯ���
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;
							<font color="blue">
								˫���鿴��ϸ��Ϣ
							</font> 
						</logic:notEmpty>
					</span>
				</h3>				
				<div class="con_overlfow" style="min-height: 600px">
				<table summary="" class="dateline" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" >
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)">
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:iterate name="rsArrList" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" ondblclick="update('gyglnew_gybxgl.do?method=gybxglView');" style="cursor:hand">
							<td align="center" width="5px">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" id="pk_${index }" name="primarykey_checkVal" value="${v }"/>
								</logic:iterate>
								
								<logic:iterate id="v" name="s" offset="1" length="1">
									<input type="hidden" value="${v }"/>
								</logic:iterate>
								
								<logic:iterate id="v" name="s" offset="7" length="1">
									<input type="hidden" value="${v }" id="pjzt_${index }"/>
								</logic:iterate>
							</td>
							<!-- ��ʾ��Ϣ -->
							<logic:iterate id="v" name="s" offset="2">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					<%
					int rsNum = ((List)request.getAttribute("rsArrList")).size();
					int pageSize = (Integer)(request.getAttribute("pageSize"));
					if(rsNum < pageSize){
						for(int i=0; i<(pageSize-rsNum); i++){
					%>
					<tr>
						<td>
							<input type="checkbox" disabled="disabled"></input>
						</td>
						<logic:iterate id="tit" name="topTr" offset="0">
							<td>
								&nbsp;
							</td>
						</logic:iterate>
				 	</tr>
					<%}}%>
				</table>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewGybxglForm"></jsp:include>
				<!--��ҳend-->
				</div>
			</div>
			<!-- ��ѯ��� end-->
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="35%">
									<span class="red">*</span>����̶�
								</th>
								<td>
									<select name="mycd">
										<option value="�ǳ�����">�ǳ�����</option>
										<option value="�Ƚ�����">�Ƚ�����</option>
										<option value="������">������</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									����<br/><span>(��������200)</span>
								</th>
								<td>
									<textarea name="pj" rows="3" style="width: 90%" cols="1" onblur="chLeng(this,200);"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="����" onclick="saveData('gyglnew_gybxgl.do?method=gybxglStudent&doType=pj','mycd');">
											�� ��
										</button>
										<button type="button" name="ȡ��" onclick="closeWindown();return false;">
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
		<logic:present name="message">
			<input type="hidden" id="message" name="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>