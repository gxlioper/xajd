<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function querygo(){
		 	document.forms[0].action = "/xgxt/zxyyglquery.do?act=go&doType=query";
		 	document.forms[0].submit();
		 }
		
		function zxyygldel(doType2){
		var url = "/xgxt/zxyygldel.do?doType2=del&doType=query&act=go&pkValue=";
		var pkValue = "";
			
		if (doType2 == "del") {
		   if (curr_row == null) {
			alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			return false;
		    } else {
			if (confirm("ȷ��Ҫɾ������������")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		  }
		   return;
	      }
		}
		
		
		function viewMoreinfo(doType){
		var url ="/xgxt/zxyyglViewMoreInfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 800, 600);
		 }
		}
		
		
		
		function updateYuyue(doType){
		var url ="/xgxt/open_updateYuyue.do?act=go&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 680, 600);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		function answer(xsxh) {	
		var url="/xgxt/answerYuyue.do?doType=view&pkValue=";
		url=url+xsxh;	
		showTopWin(url, 800, 600);
        }
        
        function answerStu(xsxh) {	
		var url="/xgxt/answerYuyueStu.do?doType=view&pkValue=";
		url=url+xsxh;	
		showTopWin(url, 800, 600);
        }
		
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ְҵ��ѯ - ԤԼ�����ѯ</a>
			</p>
		</div>


		<html:form action="/data_search" method="post">
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="isteacher" value="no">
						<li>
							<a
								href="#" onclick="javascript:updateYuyue('update')"
								class="btn_xg"> �޸� </a>
						</li>
						</logic:equal>
						<li>
							<a
								href="#" onclick="javascript:zxyygldel('del')"
								class="btn_sc"> ɾ�� </a>
						</li>
					</ul>
				</div>
			</div>
			
			
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty>
						<logic:notEmpty name="rs">
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
						</logic:notEmpty>	
				   </span>
				</h3>

				<logic:notEmpty name="rs">
					<logic:notEqual name="userType" value="stu">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td align="center">
										����
									</td>
								</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="viewMoreinfo('view')">
									<logic:iterate id="v" name="s" offset="1">
										<td align="center">
											<bean:write name="v" />
											<input type="hidden" value="<logic:iterate id="g" name="s" offset="0" length="1"><bean:write name="g" /></logic:iterate>" />
										</td>
									</logic:iterate>
									<td align="center">
										<button class="button2"
											onclick="answer(this.parentNode.parentNode.getElementsByTagName('input')[0].value);">
											�ظ�
										</button>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</logic:notEqual>
					<logic:equal name="userType" value="stu" scope="session">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td align="center">
										����
									</td>
								</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="viewMoreinfo('view')">
									<logic:iterate id="v" name="s" offset="1">
										<td align="center">
											<bean:write name="v" />
											<input type="hidden" value="<logic:iterate id="g" name="s" offset="0" length="1"><bean:write name="g" /></logic:iterate>" />
										</td>
									</logic:iterate>
									<td align="center">
										<button class="button2"
											onclick="answerStu(this.parentNode.parentNode.getElementsByTagName('input')[0].value);">
											�ظ�
										</button>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</logic:equal>
				</logic:notEmpty>
			</div>
			<input type="button" id="query_go" style="display:none;"
				onclick="refreshForm('/xgxt/jyzxResultQuery.do?act=go');" />
		</html:form>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                       alert("ɾ���ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("ɾ��ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
