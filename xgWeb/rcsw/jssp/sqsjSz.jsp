<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/moveDiv.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">

function chec(){
      for(i=0;i<document.getElementsByName("primarykey_cbv").length;i++){
      	document.getElementsByName("primarykey_cbv")[i].checked=document.getElementsByName("all")[0].checked;
      }
}

function chkAssisOne(url) {
	if (curr_row == null) {
		return false;
	} else {
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(url, 420,300);
		return true;
	}
}

function dgsz(){
	var url = "/xgxt/jsspZds.do?method=zxdksjEdit";
	if((curr_row == null) || (curr_row == "")){
		alert("��ѡ��Ҫ���õļ�¼��");
		return false;
	} else {
		url += "&pkValue=";
		url += curr_row.getElementsByTagName("input")[0].value;
	}
	showTopWin(url,420,300);
}

function csh(){
	var url = "/xgxt/jsspZds.do?method=zxdksjcsh";
	if (!confirm("��ʼ�����������ʱ�����ã��Ƿ������")){
			return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function plsz(){
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var yzchk=true;
	
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked){
		yzchk =false;
				break;
				}
	}
	if(yzchk==true){
		alert("��ѡ��"+jQuery("#xbmc").val()+"!");
		return;
	}
	
	viewTempDiv('��������','tmpdiv1',280,160);
}

function yz(){
			var kssj = document.getElementById('kssj').value;
			var jssj = document.getElementById('jssj').value;
			if((kssj == null) || (kssj == "")){
				alert("���뿪ʼʱ�䲻��Ϊ��!");
				return false;
			}
			if((jssj == null) || (jssj == "")){
				alert("�������ʱ�䲻��Ϊ��!");
				return false;
			}
			if (kssj > jssj){
				alert("���뿪ʼʱ�䲻�ܴ����������ʱ�䣡");
				return false;
			}
			document.forms[0].action = "/xgxt/jsspZds.do?method=zxdksjPlsz&doType=plmodi&kssj="+kssj+"&jssj="+jssj;
			document.forms[0].submit();
		}

	</script>
	
	</head>
	<body >
		
		<html:form action="/jsspZds" method="post">
		
        <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_csh" onclick="csh();">�� ʼ ��</a></li>
						<li><a href="#" class="btn_sz" onclick="dgsz();">��������</a></li>
						<li><a href="#" class="btn_sq" onclick="plsz();">��������</a></li>
					</ul>
				</div>
				</div>
			</logic:equal>
			
			<div class="searchtab">
					<table width="100%" class="" border="0">
						<tbody>
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><html:select property="queryequals_xydm" style="width:150px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
								<div class="btn">
								<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go" onclick="refreshForm('/xgxt/jsspZds.do?method=sqsjSz&doType=query');">
								�� ѯ
								</button>
								 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
									�� ��
								 </button>
								</div>
								</td>
							</tr>
						</tfoot>
					</table>
					</div>
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						</span>
					</h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)"
									ondblclick="dgsz()">
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="primarykey_cbv"
												value="<bean:write name="v"/>" />
										</logic:iterate>
									</td>
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
						</logic:iterate>
					</table>
			</logic:notEmpty>
			</div>
			
<!--==================================================================================================-->
				
		<div id="tmpdiv1" style="display: none">
					<table class="formlist" width="90%">
					<tr>
						<th width="30%">
							 <font color="red">*</font>���뿪ʼʱ��
						</th>
						<td>
							<input type="text" readonly="readonly"
										onclick="return showCalendar('kssj','y-mm-dd');"
										value=""
										name="kssj" id="kssj" />
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>�������ʱ��
						</th>
						<td>
							<input type="text" readonly="readonly"
										onclick="return showCalendar('jssj','y-mm-dd');"
										value=""
										name="jssj" id="jssj" />
						</td>
					</tr>
					<tfoot>
					      <tr>
					        <td colspan="4">
					          <div class="btn">
					          	<button type="button" name="�ύ" onclick="yz();">����</button>
					          </div></td>
					      </tr>
					    </tfoot>
				</table>

			</div>
<!--==================================================================================================-->	
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
