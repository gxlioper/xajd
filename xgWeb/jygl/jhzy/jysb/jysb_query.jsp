<%@ include file="/syscommon/pagehead.ini"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<body>

		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getScoreinfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>



		<script language="javascript">
	    function byqxquerygo(){
		 	document.forms[0].action = "jhzyjysb.do?method=jysb&go=go";
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
          if(!confirm("��ȷ��Ҫ������м�¼����պ��������ݶ��ᱻɾ��")){
             return false;
          }
          BatAlert.showTips('��������б����Ժ�...');
	      document.forms[0].action=url;
          document.forms[0].submit();
        
        
        }
        
        
		function stuinfodelete(doType){
		var url = "stuxsjyxxquery.do?doType=del&act=query&pkValue=";
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
		 var url ="jhzyjysb.do?method=jysbView&pkValue=";
		 var pkValue ="";
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 600,350);
	
		}
		
		function addxsjyxx(){
		   var url ="jhzyjysb.do?method=jysbadd";
		   showTopWin(url, 600, 350);	 
		}
		
		
		
		function xsjyxxupdate(doType){
		var url ="jhzyjysb.do?method=jysbupdate&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url,600, 350);
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
	       document.forms[0].action = "/xgxt/jhzyjysb.do?method=jysbExpdata&go=expdata";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
	      /*
			���
			*/
		  function shenhe(type){
			    var RowsStr="!!#!!";
		    	var url = "jhzyjysb.do?method=jysbXXsh&go=go&xxsh="+type;
			    for (i=0; i<document.getElementsByName("pk").length; i++){
		    	   if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";		
		    	   }
			    }
			    document.forms[0].pkstring.value = RowsStr;
			       if (RowsStr=="!!#!!"){
			         alert("�빴ѡ��Ҫ��˵ļ�¼��");
				   return false;
		    	}
				if(type == "yes"){
					if (!confirm("��ȷ��Ҫ ͨ�� ��ѡ��¼��")){
						   return false;
					    }
				}else{
					if (!confirm("��ȷ��Ҫ ��ͨ�� ��ѡ��¼��")){
						   return false;
					    }
				}
			    BatAlert.showTips('������ˣ����Ժ�...');
			    document.forms[0].action=url;
		        document.forms[0].submit();
		     }   
		</script>
		<html:form action="/jhzyjysb" method="post">
			<input type="hidden" name="pkstring" value="" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
            <input type="hidden" id="realTable" name="realTable" value="jhzy_jysbsjszb" />
            <input type="hidden" id="querry" name="querry"
				value="" />
				<input type="hidden" id="userType" name="userType" value="${userType}" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ѧ����ҵ��Ϣ - ��ҵ�ϱ�
				</div>
			</div>
			<fieldset>
				<legend>
					�� ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr style="cursor:hand">
							<td>
								<bean:message key="lable.xsgzyxpzxy" />��
								<logic:equal value="xy" name="userType">
								<input id="xydt" name="xydt" value="${bmmc }" disabled="disabled"/>
								<html:select property="xydm" style="display: none"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</logic:equal>
								<logic:notEqual value="xy" name="userType">
								<html:select property="xydm" style=""
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</logic:notEqual>
								רҵ��
								<html:select property="zydm" onchange="initBjList()" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px"
									onclick="byqxquerygo()" id="search_go">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td>
								��ҵ��ݣ�
								<html:select property="bynf" style="width:130px"
												styleId="bynf" onchange="">
									<html:options collection="ndList" property="nd" labelProperty="nd" />
								</html:select>
								ѧ����
								<html:select property="xl">
									<html:option value=""></html:option>
									<html:option value="������">������</html:option>
									<html:option value="ר����">ר����</html:option>
									<html:option value="��ְ��">��ְ��</html:option>
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')">
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor: hand;"
									ondblclick="viewMoreinfo();">
								<td>
										<input type="checkbox" name="pk"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td align=center nowrap="nowrap">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								</tr>
							</logic:iterate>
						
					</table>
					<TABLE width="100%" id="Table" class="tbstyle">
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=jhzyForm"></jsp:include>
							</TD>
						</TR>
					</TABLE>

				</fieldset>
			</logic:notEmpty>



			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="100%">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<logic:equal value="xx" name="userType">
				<button class="button2" id="buttonYes" onclick="shenhe('yes')"
						style="width: 80px">
						ͨ  ��
					</button> &nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="buttonNo" onclick="shenhe('no')"
						style="width: 80px">
						��ͨ��
					</button> &nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
				<button class="button2" onclick="addxsjyxx()" style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="xsjyxxupdate('update')"
					style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2"
					onclick="delall('jhzyjysb.do?method=jysbDel&go=go');"
					style="width:80px">
					����ɾ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2"
					onclick="delallinfo('jhzyjysb.do?method=jysbDelAll&go=go');"
					style="width:80px">
					ȫ�����
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
<%--				<button class="button2"--%>
<%--					onclick="sh('jhzyjysb.do?method=jysbDelAll&go=go');"--%>
<%--					style="width:80px">--%>
<%--					���--%>
<%--				</button>--%>
				<logic:equal value="xx" name="userType">
					<button class="button2" onclick="jyglDataExport()"
						style="width:80px">
						��������
					</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<logic:equal name="who" value="xx1">
					<button class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						��������
					</button>
				</logic:equal>
			</div>
			<button onclick="refreshtheweb()" id="search_go" style="display: none" ></button>
				<logic:notEmpty name="delete">
					<logic:equal name="delete" value="ok">
						<script>
                      alert("ɾ���ɹ�!");
                    </script>
					</logic:equal>
					<logic:equal name="delete" value="no">
						<script>
                      alert("ɾ��ʧ�ܣ�");
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="deleted">
					<logic:equal name="deleted" value="yes">
						<script>
                      alert("ɾ���ɹ�!");
                    </script>
					</logic:equal>
					<logic:equal name="deleted" value="no">
						<script>
                      alert("ɾ��ʧ�ܣ�");
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="xxsh">
					<logic:equal name="xxsh" value="yes">
						<script>
                      alert("��˳ɹ�!");
                    </script>
					</logic:equal>
					<logic:equal name="xxsh" value="no">
						<script>
                      alert("���ʧ�ܣ�");
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
