<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
<!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="/xgxt/pjpy/nbzy/nbzyJs.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/calendar-zh.js"></script>
<script type="text/javascript" src="js/calendar-setup.js"></script>
<script type="text/javascript">
function gfscqAdd(){
   var url = "/xgxt/zgdd_jxgl.do?method=gfscqAdd";
   showTopWin(url,"720","450");
}
function gfscqModi(act){
    if (curr_row==null || curr_row=='') {
		alert("��ѡ��Ҫ�޸ĵļ�¼��\n��������Ӧ���У�");
		return false;
    }
    var url = "/xgxt/zgdd_jxgl.do?method=gfscqModi&act="+act+"&pkValue=";
    var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	url += pk;
    showTopWin(url,"820","530");
}
 function gfscqDel(){
          var url = "/xgxt/zgdd_jxgl.do?method=gfscqDel";
		   var RowsStr="!!";		  
		   for (i=0; i<document.getElementsByName("pk").length; i++){
	    	  if(document.getElementsByName("pk")[i].checked){
	    		 RowsStr+=document.getElementsByName("pk")[i].value+"!!";
	    	  }
		   }
		   document.forms[0].delPk.value = RowsStr;		   
		   if (RowsStr=="!!"){
			   alert("��ѡ��Ҫɾ���ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
		   }		
		   if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
			  return false;
		   }
	       refreshForm(url);          
  }
  
function cqqkExpData() {
    var kssj = "";
    var jssj = "";
    if($("kssj")){
       kssj = $("kssj").value;
    }
    if($("jssj")){
       jssj = $("jssj").value;
    }
    if(kssj==""&&jssj==""){
      alert("������ʱ��Σ�");
      return false;
    }
	document.forms[0].action = "/xgxt/zgdd_jxgl.do?method=cqqkExpData";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

	function disabled() {
        if($("userType")){
            var ele="";
	        if ($("userType").value == "xy") {
	             ele ="xy";
	             var tmp = ele.split("-");
		         for (i = 0; i < tmp.length; i++) {
		 	       document.getElementById(tmp[i]).disabled = true;
		         }		      
	        }else if($("userType").value == "stu"){
	             ele ="nj-xy-zy-bj-xh-xm";
	             var tmp = ele.split("-");
		         for (i = 0; i < tmp.length; i++) {
		 	       document.getElementById(tmp[i]).disabled = true;
		         }	        
	        }

        }
    }
</script>
<body onload="disabled();">
	<html:form action="/zgdd_jxgl" method="post">
		<div class="title">
			<div class="title_img" id="title_m">			
				��ǰ����λ��: ��ѵ���� - ���������� - ���ڹ���
			</div>
		</div>
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="userType" name="userType" value="${userType}"/>
		<input type="hidden" id="delPk" name="delPk" value="" />
		<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
		<fieldset>
			<legend>
				����ѡ��
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							�꼶��
							<html:select property="nj" styleId="nj"
								onchange="initZyList();initBjList()" styleClass="select"
								style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp; ѧ��:
							<html:text property="xh" styleId="xh" 
								styleClass="inputtext"></html:text>							
							&nbsp;&nbsp;
							����:
							<html:text property="xm" styleId="xm" 
							style="width:100px" styleClass="inputtext"></html:text>
							&nbsp;&nbsp;
							ʱ���:
						    <html:text property="kssj" styleId="kssj" onblur="dateFormatChg(this)"
							style="cursor:hand;width:80px" 
							onclick="return showCalendar('kssj','y-mm-dd','aa');" />--<html:text property="jssj" styleId="jssj" onblur="dateFormatChg(this)"
							style="cursor:hand;width:80px"
							onclick="return showCalendar('jssj','y-mm-dd','aa');" />
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
							<input type="hidden" name="go" value="go" />
							<button type="button" class="button2" style="height:40px" id="search_go"
								onclick="refreshForm('zgdd_jxgl.do?method=gfscqgl');this.disabled=true;">
								��ѯ
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							<bean:message key="lable.xsgzyxpzxy" />��
							<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select"  styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp; רҵ��
							<html:select property="zydm" onchange="initBjList()"
								 styleClass="select" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp; �༶��
							<html:select property="bjdm" styleClass="select" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</thead>
			</table>
		</fieldset>
		<div id="result">
			<div class="searchcontent">
				<logic:empty name="rs">
					<p align="center">
						δ�ҵ��κμ�¼��
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							��¼���� ${rsNum} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ;������ͷ���Խ�������;</font>
						</legend>
						<table width="99%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;"
									ondblclick="gfscqModi('view')">
									<td align=center>
										<input type="checkbox" id="pk" name="pk"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<br/>
				<br/>
				<logic:equal value="yes" name="writeAble" scope="request">
					<div class="buttontool" align="center" id="btn"
						style="position:absolute;width:95%;top:100px">
						<logic:equal name="userType" value="gfb">
							<button type="button" class="button2" id="btn_add" style="width:80px"
								onclick="gfscqAdd()">
								����
							</button>
						&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" id="btn_modi" style="width:80px"
							onclick="gfscqModi('modi');">
							�޸�
						</button>
						
						&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" id="btn_del" style="width:80px"
							onclick="gfscqDel();">
							ɾ��
						</button>
						&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="impAndChkData();"
							style="width:80px">
							��������
						</button>						
						&nbsp;&nbsp;&nbsp;							
						</logic:equal>
						<button type="button" class="button2" onclick="cqqkExpData()" style="width:80px">
							��������
						</button>										
					</div>
				</logic:equal>
				<div id="tmpdiv"></div>
			</div>
		</div>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
	<logic:present name="deleted">
		<logic:equal value="yes" name="deleted">
			<script>
	 			alert('�����ɹ���');
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
		<logic:equal value="no" name="deleted">
			<script>
	 			alert(''+ document.getElementById('failInfo').value);
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
	</logic:present>
</body>
