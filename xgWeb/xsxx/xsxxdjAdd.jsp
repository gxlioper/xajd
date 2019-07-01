<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>	
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function trAdd(the_tab,type){
		    var len = document.getElementById(the_tab).rows.length+1;
		    var num = $("numAdd").value;
		    count=len;     
			var cellfu =[	
			function(data){
				return count+ "<input type='hidden' style='width:50px'  name='_xuh' value='"+count+"'>";
			},				 
			function(data){
		     var htmltext = "<input  name='saveb_kssj' id='kssj" + count + "' style='width:100px' onclick='return showCalendar(\"kssj" +count+ "\",\"y-mm-dd\");'/>";	  		
				return htmltext;
		    }, 
		    function(data){
		     var htmltext = "<input  name='saveb_jssj' id='jssj" + count + "' style='width:100px' onclick='return showCalendar(\"jssj" +count+ "\",\"y-mm-dd\");'/>";		  		
				return htmltext;
		    },      	
		    function(data){
		        var htmltext = "<input  name='saveb_szdw' id='szdw" + count + "' maxLength='100'/>";	  	      		
		   	    return htmltext;
		    },       
		    function(data){
		 	    var htmltext = "<input  name='saveb_drzw' id='drzw" + count + "'  maxLength='100' style='width:40px'/>";	  		
		   	    return htmltext;
		    },
		    function(data){
		         var htmltext = "<input  name='saveb_zmr' id='zmr" + count + "'  maxLength='25' style='width:40px'/>";	  		
		   	    return htmltext;
		    }    
			];	
			if(type=='add'){
		      DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});		      
		    }else{
		       if(num==""||num==null){	
		           return false;
		       }
		       for(i=count;i<=num;i++){          
		          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});		         
		          count++;
		       }
		       $("numAdd").value = "";
		    }        
		}
		
		function trDel(the_tab){
		    var tabobj = document.getElementById(the_tab);
		    var length = tabobj.rows.length;   
		    if(length==0){
		       return false;
		    }
		    if(confirm("ȷ��Ҫɾ����"+(length)+"�У�")){       
		         tabobj.deleteRow(tabobj.rows.length-1);                
		    }
		}
		
		function trDelAll(the_tab){
		    var tabobj = document.getElementById(the_tab);
		    var length = tabobj.rows.length;
		    var num = $("numDel").value; 
		    if(length==0){
		       $("numDel").value = "";
		       return false;     
		    }
		    if(num==""||num==null){	
		       return false;
		    }
		    if(num>length){
		      num = length;
		    }
		    if(confirm("ȷ��Ҫɾ�����"+num+"�У�")){ 
		         for(i=1;i<=num;i++){                     
		            length--;
		         }
		       for(i=1;i<=num;i++){
		          length--;
		          tabobj.deleteRow(tabobj.rows.length-1);
		       }
		    }
		    $("numDel").value = "";
		}
		
		function commitData(){
			//���ѧϰ������Ϣ�Ƿ���д����
			var tabobj = document.getElementById('flag');
			var rowLen = tabobj != null || tabobj != undefined ? tabobj.rows.length : 0;
			for(var i=1;i<=rowLen;i++){
				if($("kssj" + i) && $("kssj"+i).value != ""){
				    if($("jssj"+i) && $("jssj"+i).value == ""){
						alert("��"+i+"�н���ʱ�䲻��Ϊ�գ�");
						return false;
					}
					if($("szdw"+i) && $("szdw"+i).value == ""){
						alert("��"+i+"������ѧУ ����Ϊ�գ�");
						return false;
					}
					if($("drzw"+i) && $("drzw"+i).value == ""){
						alert("��"+i+"�е���ְ����Ϊ�գ�");
						return false;
					}
					if($("zmr"+i) && $("zmr"+i).value == ""){
						alert("��"+i+"��֤���˲���Ϊ�գ�");
						return false;
					}	
				}
			}
			saveData('xsxxdj.do?method=xsxxdjAdd&type=save','xh');
		}
		
		function loadXsxxjl(){
			getStuDetails.getXsxxjl(val('save_xh'),function(data){
				if(data !=null && data.length>0){
					$("numAdd").value=data.length;
					trAdd('flag','madd');  
					  
					for(var i=1;i<=data.length;i++){
						if($("kssj"+i)){
							var _kssj = data[i-1].kssj;
							_kssj = _kssj == null || _kssj=="null" ? "" : _kssj;
							$("kssj"+i).value = _kssj ;
						}
						if($("jssj"+i)){
							var _jssj = data[i-1].jssj;
							_jssj = _jssj == null || _jssj=="null" ? "" : _jssj;
							$("jssj"+i).value = _jssj ;
						}					
						if($("szdw"+i)){
							var _szdw = data[i-1].szdw;
							_szdw = _szdw == null ? "" : _szdw;
							$("szdw"+i).value = _szdw;
						}
						if($("drzw"+i)){
							var _drzw = data[i-1].drzw;
							_drzw = _drzw == null ? "" : _drzw;
							$("drzw"+i).value = _drzw;
						}
						if($("zmr"+i)){
							var _zmr = data[i-1].zmr;
							_zmr = _zmr == null ? "" : _zmr;
							$("zmr"+i).value = _zmr;
						}				
					}
				}
			});
		}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();loadXsxxjl();">		
		<html:form action="/xsxxdj.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ����Ϣ - ѧ����Ϣ - ѧ����Ϣ�Ǽ� - ����
				</div>
			</div>
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("�������ѧ����Ч!");
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xh-xm-xb-xy-nj-zy-bj-xz-sfzh-sjhm" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-sfzh-sjhm-xz" />
				<input type="hidden" id="url" name="url" value="/xsxxdj.do?method=xsxxdjAdd" />
				<fieldset>
					<legend>
						ѧ����Ϣά��
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									ѧ����Ϣ�Ǽ�
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' 
								           property="save_xh" 
								           readonly="readonly"
									       styleId="xh" 
									       onkeypress="autoFillStuInfo(event.keyCode,this);" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="button2" 
									id="buttonFindStu">
									ѡ��
								</button>
							</td>
							<td align="right">
								�����أ�
							</td>
							<td align="left">
								<html:text name='rs' property="save_csd" styleId="csd" maxlength="50"/>								
							</td>
						</tr>
						<tr>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" disabled="true"/>
							</td>
							<td align="right">
								Ѫ�ͣ�
							</td>
							<td align="left">
								<html:select property="save_xuex" styleId="xuex">
								<html:option value=""></html:option>
								<html:option value="A">A</html:option>
								<html:option value="B">B</html:option>
								<html:option value="O">O</html:option>
								<html:option value="AB">AB</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" disabled="true" />
							</td>
							<td align="right">
								�Ƿ�����Ա��
							</td>
							<td align="left">
								<html:radio property="save_sfsty" value="��">��</html:radio>
								<html:radio property="save_sfsty" value="��">��</html:radio>
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" disabled="true" />
							</td>
							<td align="right">
								����ʱ�䣺
							</td>
							<td align="left">
								<html:text name='rs' property="save_jrgqtsj" styleId="jrgqtsj"
									       style="cursor:hand;"
									       onclick="return showCalendar('jrgqtsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" disabled="true" />
							</td>
							<td align="right">
								����ʱ�䣺
							</td>
							<td align="left">
								<html:text name='rs' property="save_tytysj" styleId="tytysj"
									       style="cursor:hand;"
									       onclick="return showCalendar('tytysj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" disabled="true" />
							</td>
							<td align="right">
								תԤ����Աʱ�䣺
							</td>
							<td align="left">
								<html:text name='rs' property="save_zybdysj" styleId="zybdysj"
									       style="cursor:hand;"
									       onclick="return showCalendar('zybdysj','y-mm-dd');" />
							</td>	
						</tr>
						<tr>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" disabled="true" />
							</td>
							<td align="right">
								��ѧǰ����״����
							</td>
							<td align="left">
								<html:text name='rs' property="save_rxqjkzk" styleId="rxqjkzk" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								ѧ�ƣ�
							</td>
							<td align="left">
								<html:text name='rs' property="xz" styleId="xz" disabled="true" />
							</td>
						    <td align="right">
								��ѧ�󽡿�״����
							</td>
							<td align="left">
								<html:text name='rs' property="save_rxhjkzk" styleId="rxhjkzk" maxlength="50"/>
							</td>	
						</tr>
						<tr>
							<td align="right">
								���֤�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="sfzh" styleId="sfzh" disabled="true" />
							</td>
							<td align="right">
								������ʷ��
							</td>
							<td align="left">
								<html:text name='rs' property="save_ywbs" styleId="ywbs" maxlength="500"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�ֻ����룺
							</td>
							<td align="left">
								<html:text name='rs' property="sjhm" styleId="sjhm" disabled="true" />
							</td>
							 <td align="right">
								�ֻ��̺ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="save_sjdh" styleId="sjdh" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
							</td>
						</tr>
						<tr>
							<td align="right">
								QQ���룺
							</td>
							<td align="left">
								<html:text name='rs' property="save_qqhm" styleId="qqhm" maxlength="15" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
							</td>
							 <td align="right">
								�ڽ�������
							</td>
							<td align="left">
								<html:text name='rs' property="save_zjxy" styleId="zjxy" maxlength="50"/>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								ͨѶ��ַ��
							</td>
							<td colspan="3">
								<html:text name='rs' property="save_txdz" styleId="txdz" maxlength="50" style="width:100%"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								ʵϰҽԺ��
							</td>
							<td align="left">
								<html:text name='rs' property="save_sxyy" styleId="sxyy" maxlength="150" />
							</td>
							 <td align="right">
								ʵϰס�޵�ַ��
							</td>
							<td align="left">
								<html:text name='rs' property="save_sxzsdz" styleId="sxzsdz" maxlength="300"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								רҵ����֤�飺
							</td>
							<td align="left">
								<html:text name='rs' property="save_zyjnzs" styleId="zyjnzs" maxlength="300" />
							</td>
							 <td align="right">
								�����Ƿ�Ǩ�룺
							</td>
							<td align="left">
								<html:select name="rs" property="save_hksfqr" styleId="hksfqr">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								���������
							</td>
							<td colspan="3">
								<html:text name='rs' property="save_jcqk" styleId="jcqk" maxlength="300" style="width:100%"/>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								���ҽ��ܣ�
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='save_zwjs' style="width:99%" rows='7'  onblur="chLeng(this,1000)"/>
							</td>
						</tr>
					</table>
					<fieldset>
				<legend>
					<p>
						<!-- ��ѯ�õ�����������ʾ���� -->
						<input  value="+" onclick="trAdd('flag','add')" style="width: 20px"/>
						<input  value="-" onclick="trDel('flag')" style="width: 20px"/>
						&nbsp;&nbsp;<font color="blue">��Ҫѧϰ����</font>&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd" style="width: 20px"
							onblur="trAdd('flag','madd')">
						&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel" style="width: 20px"
							onblur="trDelAll('flag')">
						&nbsp;��
					</p>
				</legend>
				<table class="tbstyle" align="center" width="93%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT" class="tbstyle">
									<!-- ��ӡʱ��һ�в���ʾ- -->
									<thead style="height: 23px">
										<tr>
											<td nowrap="nowrap">
												���
											</td>										
											<td nowrap="nowrap">
												��ʼʱ��
											</td>		
											<td nowrap="nowrap">
												����ʱ��
											</td>									
											<td nowrap="nowrap">
												����ѧУ
											</td>
											<td nowrap="nowrap">
												����ְ��
											</td>
											<td nowrap="nowrap">
												֤����
											</td>																																									
										</tr>
									</thead>
									<tbody width="100%" class="tbstyle" id="flag">
									
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
					<div class="buttontool" align="center">
						<button type="button" class="button2" onclick="commitData()"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
					</div>
				</fieldset>
		</html:form>
		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>
