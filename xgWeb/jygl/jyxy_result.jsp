<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function viewMoreinfo1(doType){
		var url ="/xgxt/JyglJyxyViewMoreinfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 700, 600);
		 }
		}
		
		
		function jyxyupdate(doType){
		var url ="/xgxt/turnjyxyupdate.do?whichArea=result&doType=first&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�鿴���޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 900, 600);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		</script>
	</head>
	
	<body>
		<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ��ҵЭ�鷽�� - ��˽����ѯ</a>
				</p>
			</div>
		<div class="formbox">
			<h3 class="datetitle_01">
				<span> ��ѯ���&nbsp;&nbsp;
					<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font>
					</logic:empty> 
					<logic:notEmpty name="rs">
							��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;
	                       ��½�û���
					    <bean:write name="rs1" property="userName" />
					    &nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ���޸ģ�������ͷ��������</font>
					</logic:notEmpty> </span>
			</h3>
			<logic:notEmpty name="rs">
			<logic:equal name="ableToView" value="teacher">
			<table summary="" class="dateline" align="" id="rsTable" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
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
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="jyxyupdate('update')">
								<td style="display:none">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
					<script type="text/javascript">
							$('choose').className="hide";
					</script>
				</logic:equal>
				<logic:equal name="ableToView" value="student">
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
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
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="jyxyupdate('update')">
								<td style="display:none">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				</logic:equal>
				
		</logic:notEmpty>
		</div>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

