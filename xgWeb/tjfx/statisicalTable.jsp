<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ�����         ��-->

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		
		<script language="javascript">
		function expData(url){
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		function chec(){
		  var trArr=document.getElementsByTagName("TR");
		  for(i=0;i<trArr.length;i++){
	    		if($("tr"+i)){
	    			document.getElementById("chbox"+i).checked=document.getElementById("all").checked;
	    		}
	      }
	    }
	    
	    function checkAllBox(){
	    	var blog=false;
	    	var trArr=document.getElementsByTagName("TR");
    		//ȫѡδѡ��
	    	if(!document.getElementById("all").checked ){
	    		
	    	 	for(i=0;i<trArr.length;i++){
		    		if($("tr"+i) && document.getElementById("chbox"+i).checked ){
		    			blog=true;
		    		}
			    }
	    	}else{
	    		blog=true;
	    	}
	    	
	    	if(!blog){
		    	alert("�빴ѡҪ���������ݣ������޷���ӡ���ݣ�");
		    }else{
		    	expTjTab('rsTable',document.forms[0].tjxm.value);
		    }
	    }
		</script>
	</head>
	
	<body onload="chgnumformat()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<a>�߼�ͳ��</a>
			</p>
		</div>
		<!-- ���� end-->
		<a href='#layerTop' id='top_layer' style="display:none">go</a>
		<html:form action="/statisical">
			<html:hidden property="tjxm" styleId="tjxm" />
			<html:hidden property="cols" styleId="cols" />
			<html:hidden property="sql" styleId="sql" />
			<html:hidden property="tableName" styleId="tableName"/>			
			<input type="hidden" id="colNameCN" name="colNameCN" value="<bean:write name="colNameCN"/>"/>

			<div class="toolbox">
			<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<!-- ����ѡ��ͳ�� -->
							<a href="#"
								onclick="checkAllBox()"
								class="btn_fz">���ɱ���</a>
						</li>
						<li>
							<a href="#"
								onclick="analyse()"
								class="btn_zt">����</a>
						</li>	
						<li>
							<a href="#" 
								onclick="expData('dataexport.do')"
								class="btn_dc">����</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ͳ�ƽ��&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
						</span>
					</h3>
					<table	style="display:hidden" id="showTable">
					</table>
					<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align="" width="100%" id="rsTable">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand" id="tr0">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()"/>
									
								</td>
								<logic:iterate id="tit" name="topTr">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rs" id="s"  indexId="index" >
							<tr onclick="rowOnClick(this)" style="cursor:hand" id="tr${index}" >
								<td>
									<input type="checkbox" name="chbox${index}" id="chbox${index}"  />
								</td>
								<logic:iterate id="v" name="s">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
							<!--���� end-->
						</table>
						</div>
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>		
			<div id="tmpdiv1"></div>
		</html:form>
	</body>
</html>
