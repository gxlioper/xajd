<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getScoreinfo.js'></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
			<script language="javascript">
		function loadShi(){
		var shen = document.getElementById("jgshen").value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "jgshi";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}	
			if (data.xianList !=null){
				var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}	
		
	    function byqxquerygo(){
		 	document.forms[0].action = "dwxxmkwh.do?act=query";
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
		var url = "dwxxmkwh.do?doType=del&act=query&pkValue=";
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
		 var url ="dwxxInput.do?pkValue=";
		 var pkValue ="";
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue+"&act=go";
		 showTopWin(url, 600,400);
	
		}
		
		function addxsjyxx(){
		   var url ="dwxxInput.do";
		   showTopWin(url, 750, 500);	 
		}
		
		
		
		function xsjyxxupdate(doType){
		var url ="dwxxInput.do?pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue+"&dwupdate=dwupdate&act=go";
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
	       document.forms[0].action = "/xgxt/jyglbyqxDataExport.do?tableName=dwxxb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		</script>
	</head>


	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ģ��ά�� - ��λ��Ϣģ���ά��</a>
			</p>
		</div>


		
		<html:form action="/dwxxmkwh" method="post">
			<input type="hidden" name="pkstring" value="" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
            <input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
            <input type="hidden" id="querry" name="querry"
				value="<bean:write name='querry' scope="request" />" />
			
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:notEqual value="12104" name="xxdm" scope="session">
						<li>
							<a href="#"
								onclick="addxsjyxx();"
								class="btn_zj"> ���� </a>
						</li>
						<li>
							<a href="#"
								onclick="xsjyxxupdate('update');"
								class="btn_xg"> �޸� </a>
						</li>
						</logic:notEqual>
							<li>
								<a href="#"
									onclick="stuinfodelete('del');"
									class="btn_sc"> ɾ�� </a>
							</li>
							<li>
								<a href="#"
									onclick="delall('dwxxmkwh.do?doType2=delall&act=query');"
									class="btn_sc"> ����ɾ�� </a>
							</li>
							<li>
								<a href="#" onclick="delallinfo('dwxxmkwh.do?doType2=delallinfo&act=query');" class="btn_hsz"> ȫ����� </a>
							</li>
							<logic:equal name="who" value="xx">
							<li>
								<a href="#" onclick="impAndChkData()" class="btn_dr"> ���� </a>
							</li>
							<li>
								<a href="#"
									onclick="jyglDataExport()"
									class="btn_dc"> ���� </a>
							</li>
							</logic:equal>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="byqxquerygo()">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
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
									��λ����
								</th>
								<td>
									<html:text property="dwmc"/>
								</td>
								<th>
									���ܲ���
								</th>
								<td>
									<html:select name="rs1" property="zgbm" styleId="zgbm"
										style="width:175px">
										<html:option value=""></html:option>
										<html:options collection="sydzgbmList" property="zgbm"
											labelProperty="zgbm" />
									</html:select>
								</td>
								<th>
									������ҵ
								</th>
								<td>
									<html:select name="rs1" property="hyfl" styleId="sshy" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="hyflList" property="hyfl" labelProperty="hyfl" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									���ڵ���
								</th>
								<td colspan="3">
									<html:select property="szdqsh" onchange="loadShi()"
										styleId="jgshen">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
									<html:select property="szdqsi" styleId="jgshi">
										<html:options collection="shiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
								</td>
								<th>
									��λ����
								</th>
								<td>
									<html:select name="rs1" property="dwxz" styleId="dwxz">
										<html:option value=""></html:option>
										<html:options collection="dwxxdm" property="dwxz"/>
									</html:select>
								</td>
							</tr>
							</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> 
							<logic:notEmpty name="rs">
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
							</logic:notEmpty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')"/>
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
								<input type="hidden" name="pk"
									value="<bean:write name="s" property="dwid" />" />
								<input type="checkbox" name="pk"
									value="<bean:write name="s" property="dwid"/>" />
								</td>
								<td>
									<bean:write name="s" property="dwmc" />
								</td>
								<td>
									<bean:write name="s" property="zgbm" />
								</td>
								<td>
									<bean:write name="s" property="clrq" />
								</td>
								<td>
									<bean:write name="s" property="lxbm" />
								</td>
								<td>
									<bean:write name="s" property="lxr" />
								</td>
								<td>
									<bean:write name="s" property="lxdh" />
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
				<logic:notEmpty name="delall">
					<logic:equal name="delall" value="ok">
						<script>
                      alert("����ɾ���ɹ�!");
                    </script>
					</logic:equal>
					<logic:equal name="delall" value="no">
						<script>
                      alert("����ɾ��ʧ�ܣ�");
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
	</body>
</html>
