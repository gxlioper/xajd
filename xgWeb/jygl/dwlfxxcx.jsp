<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getScoreinfo.js'></script>
		<script language="javascript">
	    function byqxquerygo(){
		 	document.forms[0].action = "dwlfxx.do?doType=sect&act=query";
		 	document.forms[0].submit();
        }
        
        /*
    	ȫ��ѡ��
	    */    
      function chec(){
         for(i=0;i<document.getElementsByName("pk").length;i++){
      	    document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
         }
      }	
        
        /*
	����ɾ��
	*/
      function delall(url){
	    var RowsStr="!!#!!";
    
	    for (i=0; i<document.getElementsByName("pk").length; i++){
    	   if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";		
    	   }
	    }
	    document.forms[0].pkstring.value = RowsStr;
	       if (RowsStr=="!!#!!"){
	         alert("�빴ѡ��Ҫɾ���ļ�¼��");
		   return false;
    	}
	
	    if (!confirm("��ȷ��Ҫɾ����ѡ��¼��")){
		   return false;
	    }
	    BatAlert.showTips('����ɾ�������Ժ�...');
	    document.forms[0].action=url;
        document.forms[0].submit();
     }   
        
           /*
	ȫ�����
	*/
        
        function delallinfo(url){
          if(!confirm("��ȷ��Ҫ������м�¼��")){
             return false;
          }
          BatAlert.showTips('��������б����Ժ�...');
	      document.forms[0].action=url;
          document.forms[0].submit();
        
        
        }
        
        
		function stuinfodelete(doType){
		var url = "dwlfxx.do?doType=del&act=query&pkValue=";
		var pkValue = "";
			
		if (doType == "del") {
		   if (curr_row == null) {
			alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			return false;
		    } else {
			if (confirm("ȷ��Ҫɾ������������")) {
			    BatAlert.showTips('����ɾ�������Ժ�...');
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
		
		
		function viewMoreinfo(){
		 var url ="dwlfxx.do?doType=dnset&pkValue=";
	
		 var pkValue ="";
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 600,400);
		}
		
		function addxsjyxx(){
		   var url ="stuxsjyxxinput.do";
		   showTopWin(url, 750, 500);	 
		}
		
		
		
		function xsjyxxupdate(doType){
		var url ="dwlfxx.do?doType="+doType+"&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 670, 480);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
	    function refreshtheweb()
		{
			document.forms[0].action = "stuxsjyxxquery.do";
            document.forms[0].submit();
		}
		
		
		 function  jyglDataExport(){
	       document.forms[0].action = "/xgxt/jyglbyqxDataExport.do?tableName=dwlfxxdjb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��λ������Ϣ - ��λ������Ϣά��</a>
			</p>
		</div>


		<html:form action="/stuxsjyxxquery" method="post">
			<input type="hidden" name="pkstring" value="" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="realTable" name="realTable"
				value="dwlfxxdjb" />
			<input type="hidden" id="tableName" name="tableName"
				value="dwlfxxdjb" />
			<input type="hidden" id="stab" name="stab" value="stab" />

			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="xsjyxxupdate('update');" class="btn_xg">
								�޸� </a>
						</li>
						<li>
							<a href="#" onclick="stuinfodelete('del');" class="btn_sc">
								ɾ�� </a>
						</li>
						<li>
							<a href="#"
								onclick="delall('dwlfxx.do?doType=delall&act=query');"
								class="btn_sc"> ����ɾ�� </a>
						</li>
						<li>
							<a href="#" onclick="impAndChkData()" class="btn_dr"> ���� </a>
						</li>
						<li>
							<a href="#" onclick="jyglDataExport()" class="btn_dc"> ���� </a>
						</li>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<button class="btn_cx" id="search_go" onclick="byqxquerygo()">
											�� ѯ
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									���
								</th>
								<td>
									<html:text property="dwbh" style="width:80px"></html:text>
								</td>
								<th>
									��λ����
								</th>
								<td>
									<html:text property="dwmc" style="width:80px"></html:text>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" style="width:80px"></html:text>
								</td>
								<th>
									��λ�绰
								</th>
								<td>
									<html:text property="dwdh" style="width:80px"></html:text>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> <logic:notEmpty name="rs">
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
							</logic:notEmpty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="qbxz" value="all"
											onclick="chec('qbxz')" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
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
										ondblclick="viewMoreinfo();" align="center">
										<td>
											<input type="hidden" name="dwid" value="${s.dwid }" />
											<input type="checkbox" name="pk" value="${s.dwid }" />
										</td>
										<td>
											<bean:write name="s" property="dwbh" />
										</td>
										<td>
											<bean:write name="s" property="dwmc" />
										</td>
										<td>
											<bean:write name="s" property="xm" />
										</td>
										<td>
											<bean:write name="s" property="dwdh" />
										</td>
										<td>
											<bean:write name="s" property="bm" />
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<!--��ҳ��ʾ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						<!--��ҳ��ʾ-->
					</logic:notEmpty>
				</div>
				<button onclick="refreshtheweb()" id="search_go"
					style="display: none"></button>
				<logic:notEmpty name="delete">
					<logic:equal name="delete" value="ok">
						<script>
                      alert("ɾ���ɹ�!");
                      document.getElementById("search_go").click();
                    </script>
					</logic:equal>
					<logic:equal name="delete" value="no">
						<script>
                      alert("ɾ��ʧ�ܣ�");
                      document.getElementById("search_go").click();
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="delall">
					<logic:equal name="delall" value="ok">
						<script>
                      alert("����ɾ���ɹ�!");
                      document.getElementById("search_go").click();
                    </script>
					</logic:equal>
					<logic:equal name="delall" value="no">
						<script>
                      alert("����ɾ��ʧ�ܣ�");
                      document.getElementById("search_go").click();
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="delallinfo">
					<logic:equal name="delallinfo" value="ok">
						<script>
                      alert("ȫ����������գ�");
                    </script>
					</logic:equal>
					<logic:equal name="delallinfo" value="no">
						<script>
                      alert("����б�ʧ�ܣ�");
                    </script>
					</logic:equal>
				</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>

	</body>
</html>
