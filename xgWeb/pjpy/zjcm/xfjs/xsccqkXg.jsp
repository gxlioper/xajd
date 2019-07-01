<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xfjs.js'></script>
	<script type="text/javascript">
		var mouseX;
		var mouseY;
		
		function mouseOver(obj) {
		  // �˴���¼���ͣ�����齨�ϵ�ʱ���λ��, �����Լ�ͨ���Ӽ����������������ľ���.
		  mouseX = event.clientX;
		  mouseY = event.screenY;
		 }
		
		function showStuList(id){
			var count = $('tempTb').getElementsByTagName('tr').length;
		
			//��ʾdiv
			viewTempDiv('��˫��ѡ��ѧ��','bjStu',300,400);
			setVal('curr_xh',id);
		}
		
		function changeWjlx(index){
			var value = selText('wjlxdmArr'+index);
			if(value == '����'){
				ele('wjcsArr'+index).readOnly = false;
			}else{
				jQuery('#wjcsArr'+index).val('');
				setVal('wjcsArr'+index,'');
				ele('wjcsArr'+index).readOnly = true;
			}	
		}
		
		function loadSelect(i){
			xfjs.selectDmList("pjpy_xfjs_wjlxdmb",['wjlxdm','wjlxmc'],function(data){
				if(data != null){
					DWRUtil.removeAllOptions("wjlxdmArr"+i);			
					DWRUtil.addOptions("wjlxdmArr"+i,[{wjlxdm:'',wjlxmc:''}],"wjlxdm","wjlxmc");
					DWRUtil.addOptions("wjlxdmArr"+i,data,"wjlxdm","wjlxmc");						
				}
			});	
			
			xfjs.selectDmList("pjpy_xfjs_qjlxdmb",['qjlxdm','qjlxmc'],function(result){
				if(result != null){
					DWRUtil.removeAllOptions("qjlxdmArr"+i);	
					DWRUtil.addOptions("qjlxdmArr"+i,[{qjlxdm:'',qjlxmc:''}],"qjlxdm","qjlxmc");
					DWRUtil.addOptions("qjlxdmArr"+i,result,"qjlxdm","qjlxmc");	
				}
			});	
		}
		
		function trAdd(the_tab,type){
		    var len = document.getElementById(the_tab).rows.length+1;
		    var num = $("numAdd").value;
		    count=len;     
			var cellfu =[
			function(data){
				return count+ "<input type='hidden' style='width:50px'  name='_xuh' value='"+count+"'>";
			},			 
			function(data){
		     var htmltext = "<div><input  name='xhArr' id='xhArr" + count + "' readonly='readonly'  style='width:100px' onclick='showStuList(this.id);' onmousedown='mouseOver(this)'/></div>";	  		
				return htmltext;
		    }, 
		    function(data){
		     var htmltext = "<input  name='xmA' id='xmA" + count + "' disabled='true' style='width:100px'/>";	  		
				return htmltext;
		    },      	
		    function(data){
		        var htmltext = "<select  name='wjlxdmArr' id='wjlxdmArr" + count + "' onchange='changeWjlx(" + count+ ")'/>";	  	      		
		   	    return htmltext;
		    },       
		    function(data){
		 	    var htmltext = "<input  name='wjcsArr' id='wjcsArr" + count + "'  maxLength='4' readonly='readonly' onkeyup='value=value.replace(/[^\\d]/g,\"\");\' style='width:40px'/>";	  		
		   	    return htmltext;
		    },
		    function(data){
		        var htmltext = "<select  name='qjlxdmArr' id='qjlxdmArr" + count + "' />";	  	      		
		   	    return htmltext;
		    },
			function(data){	
			    var htmltext =  "<textarea  name='bzArr'  rows='3' style='width:160px' id='bzArr" + count + "' onblur='chLeng(this,250)'/>";	  	  		
				return htmltext;
		    }	    
			];	
			if(type=='add'){
		      DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
		      //����select�е�����
			  loadSelect(count);
		    }else{
		       if(num==""||num==null){	
		           return false;
		       }
		       for(i=count;i<=num;i++){          
		          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
		          //����select�е�����
				  loadSelect(count);
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
		
		//��ʾ��ϸ��ѧ��Υ����Ϣ
		function showDataList(){
			var pk = val("pk");
			if(pk != null && pk != ""){
				dwr.engine.setAsync(false);
				xfjs.selectXsccqkbByBj(pk,function(data){
					if(data !=null && data.length>0){
				$("numAdd").value=data.length;
				trAdd('flag','madd');  
				  
				for(var i=1;i<=data.length;i++){
					if($("xhArr"+i)){
						var _xh = data[i-1].xh;
						_xh = _xh == null || _xh=="null" ? "" : _xh;
						$("xhArr"+i).value = _xh ;
					}
					if($("xmA"+i)){
						var _xm = data[i-1].xm;
						_xm = _xm == null || _xm=="null" ? "" : _xm;
						$("xmA"+i).value = _xm ;
					}
					if($("wjlxdmArr"+i)){
						//����select�е�����
						xfjs.selectDmList("pjpy_xfjs_wjlxdmb",['wjlxdm','wjlxmc'],function(result){
							if(result != null){
								DWRUtil.removeAllOptions("wjlxdmArr"+i);	
								DWRUtil.addOptions("wjlxdmArr"+i,[{wjlxdm:'',wjlxmc:''}],"wjlxdm","wjlxmc");
								DWRUtil.addOptions("wjlxdmArr"+i,result,"wjlxdm","wjlxmc");			
							}
						});		
						var _wjlxdm = data[i-1].wjlxdm;
						_wjlxdm = _wjlxdm == null ? "" : _wjlxdm;
						$("wjlxdmArr"+i).value = _wjlxdm;	
						
						changeWjlx(i);		
					}
					if($("qjlxdmArr"+i)){
						//����select�е�����
						xfjs.selectDmList("pjpy_xfjs_qjlxdmb",['qjlxdm','qjlxmc'],function(result){
							if(result != null){
								DWRUtil.removeAllOptions("qjlxdmArr"+i);	
								DWRUtil.addOptions("qjlxdmArr"+i,[{qjlxdm:'',qjlxmc:''}],"qjlxdm","qjlxmc");
								DWRUtil.addOptions("qjlxdmArr"+i,result,"qjlxdm","qjlxmc");			
												
							}
						});	
						var _qjlxdm = data[i-1].qjlxdm;
						_qjlxdm = _qjlxdm == null ? "" : _qjlxdm;							
						$("qjlxdmArr"+i).value = _qjlxdm;					
					}
					if($("wjcsArr"+i)){
						var _wjcs = data[i-1].wjcs;
						_wjcs = _wjcs == null ? "" : _wjcs;
						$("wjcsArr"+i).value = _wjcs;
					}
					if($("bzArr"+i)){
						var _bz = data[i-1].bz;
						_bz = _bz == null ? "" : _bz;
						$("bzArr"+i).value = _bz;
					}				
				}
			}
			});
			dwr.engine.setAsync(true);
			}
		}
		
		function xsccqkXg(the_tab){
			var tabobj = document.getElementById(the_tab);
			var rowLen = tabobj != null || tabobj != undefined ? tabobj.rows.length : 0;
			var pk = [];
			var sdrs = toInt(val('sdrs'));
			var wjrs = toInt(val('wjrs'));
			var qqrs = toInt(val('qqrs'));
			
			
			if(qqrs+wjrs>rowLen && sdrs!=0){
				alert('ȱ��������Υ��������' +(qqrs+wjrs)+ '�ˣ���ֻ�����' + rowLen + '����¼��');
				return false;
			}
			
			for(var i=1;i<=rowLen;i++){
		    if($("xhArr"+i)){
				if($("xhArr"+i).value == ""){
					alert("��"+i+"�� ѧ�Ų���Ϊ�գ�");
					return false;
				}
			}
			if($("wjlxdmArr"+i) && $("qjlxdmArr"+i)){
				if($("wjlxdmArr"+i).value == "" && $("qjlxdmArr"+i).value == ""){
					alert("��"+i+"�� Υ�����ͺ�������Ͳ���ͬʱΪ�գ�");
					return false;
				}
				if($("wjlxdmArr"+i).value != "" && $("qjlxdmArr"+i).value != ""){
					alert("��"+i+"�� Υ�����ͺ��������ֻ��ѡ��һ�");
					return false;
				}
			}
			
			if($("wjlxdmArr"+i) && $("wjcsArr"+i)){
				if(selText("wjlxdmArr"+i) == "����" && $("wjcsArr"+i).value == ""){
					alert("��"+i+"�� ���ν�������Ϊ�գ�");
					return false;
				}
			}
			if($("wjcsArr"+i)){
				if($("wjcsArr"+i).value != "" && toInt($("wjcsArr"+i).value)<1){
					alert("��"+i+"�� ���δ���������ڵ���1��");
					return false;
				}
			}
			if($("bzArr"+i)){
				if($("bzArr"+i).value.length > 250){
					alert("��"+i+"�� ��ע�������࣬��250�֣�");
					return false;
				}
			}				
			pk[i-1] = $("xhArr"+i).value;	
		}
		//�жϼ�¼�����ظ�
		if(checkArrayEleRepeat(pk)){
			alert("ѧ���ظ���");
			return false;
		}
		
		//��Ϣ����
		showTips('���������У���ȴ�......');
	    refreshForm('/xgxt/pjpyxfjs.do?method=xsccqkXgSave');
	    $("buttonSave").disabled=true;
	   }
	   
	   function getXh(xh,xm){
	   	setVal(val('curr_xh'),xh);
	   	document.getElementById('xmA'+val('curr_xh').substr(5)).value=xm;
	   	setVal("curr_xh","");	   
	   	hiddenMessage(true,true);
	   }
	</script>
	</head>
	<body onload="showDataList();">
		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title" /></a>
				</p>
		</div>
	
	
		<input type="hidden" id="curr_xh" value=""/>
		<html:form action="/pjpyxfjs" method="post">
			<input type="hidden" name="pk" id="pk" value="${model.pk}"/>
			
			<div id="bjStu" style="display:none;">
				<div style="width:100%;height:250px;overflow-x:hidden;overflow-y:auto;">
					<table class='formlist' id="tempTb">
						<thead>
							<tr><td nowrap="nowrap">ѧ��</td><td nowrap="nowrap">����</td><td nowrap="nowrap">�Ա�&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
						</thead>
						<tbody>
						<logic:iterate name="stuList" id="s">
							<tr ondblclick="getXh(this.cells[0].getElementsByTagName('input')[0].value,this.cells[1].getElementsByTagName('input')[0].value);" style="cursor:hand">						
								<logic:iterate id="v" name="s" offset="0" >
									<td align="left" nowrap="nowrap">
										<bean:write name="v" />
										<input type="hidden" value="${v}"/>
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
						</table>
					</div>
					<div>
						<table class='formlist'>
						<tfoot>
							<tr>
								<td colspan="3">
									<div class='btn'>
										<button type="button" onclick="hiddenMessage(true,true);return false;">
											�ر�
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			
			
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶ѧ����</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<logic:notEqual value="ͨ��" name="model" property="xxsh">
										<button type="button"  onclick="xsccqkXg('flag')" id="buttonSave">
											�� ��
										</button>
									</logic:notEqual>
				
									<button type="button" onclick="window.dialogArguments.document.getElementById('search_go').click();window.close();" id="buttonClose">
										�� ��
									</button> 
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
			<tr>
				<th>
					ѧ��
				</th>
				<td align="left">
					${model.xn}
					<input type="hidden" value="${model.xn}" name="xn"/>
				</td>
				<th>
					�������
				</th>
				<td align="left">
					${model.ccrq}
					<input type="hidden" value="${model.ccrq}" name="ccrq"/>
				</td>
			</tr>
			<tr>
				<th>
					ѧ��
				</th>
				<td align="left">
					${model.xqmc}
					<input type="hidden" value="${model.xq}" name="xq"/>
				</td>
				<th>
					�������
				</th>
				<td align="left">
					${model.jclxmc}
					<input type="hidden" value="${model.jclxdm}" name="jclxdm"/>
				</td>
			</tr>
			<tr>
				<th>
					�༶
				</th>
				<td align="left">
					${model.bjmc}
					<input type="hidden" value="${model.bjdm}" name="bjdm"/>
				</td>
				<th>
					Ӧ������
				</th>
				<td align="left">
					${model.ydrs}					
				</td>
			</tr>
			<tr>
				<th>
					ʵ������
				</th>
				<td align="left">
					${model.sdrs}
					<input type="hidden" value="${model.sdrs}" name="sdrs"/>
				</td>
				<th>
					ȱ������
				</th>
				<td align="left">
					${model.qqrs}
					<input type="hidden" value="${model.qqrs}" name="qqrs"/>					
				</td>
			</tr>		
			<tr>
				<th>
					��ȱ�����Υ������<br/>(��:�Է�,˯����...)
				</th>
				<td align="left">
					${model.wjrs}
					<input type="hidden" value="${model.wjrs}" name="wjrs"/>
				</td>
				<th>
					�涨����ʱ��
				</th>
				<td align="left">
					${model.fdyclsj}
				</td>
			</tr>
			<tr>
				<th>
					����û�
				</th>
				<td align="left">
					<html:hidden property="ccyhlx" name="model"/>
					<logic:equal value="xx" name="model" property="ccyhlx">
					ѧУ
					</logic:equal>
					<logic:equal value="xy" name="model" property="ccyhlx">
					<bean:message key="lable.xsgzyxpzxy" />
					</logic:equal>
				</td>	
				<th>
					����Ա����ʱ��
				</th>
				<td align="left">
					${model.fdysjclsj}
				</td>				
			</tr>	
			<tr>
				<th>
					ѧУ��ע
				</th>
				<td align="left" colspan="3">
					${model.bz}
				</td>
			</tr>
			</tbody>	
		<logic:equal value="true" name="flag">
			<div align="center"><font color="red"><b>��ʾ������ͬ�ļ��ʱ���Ѿ�����������</b></font></div>
			<div class="buttontool" align="center">
				<span class="openbutton">
					<button type="button" id="buttonClose" onclick="window.dialogArguments.document.getElementById('search_go').click();window.close();" >
						�� ��
					</button> 
				</span>
			</div>		
		</logic:equal>
		<logic:equal value="false" name="flag">
				<tbody>
					<tr>
						<td colspan="4">
							<p>
						<!-- ��ѯ�õ�����������ʾ���� -->
						<input  value="+" onclick="trAdd('flag','add')" style="width: 20px"/>
						<input  value="-" onclick="trDel('flag')" style="width: 20px"/>
						&nbsp;&nbsp;<font color="blue">���尲��</font>&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd" style="width: 20px"
							onblur="trAdd('flag','madd')"/>
						&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel" style="width: 20px"
							onblur="trDelAll('flag')"/>
						&nbsp;��
					</p>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT" class="tbstyle">
									<!-- ��ӡʱ��һ�в���ʾ- -->
									<thead style="height: 23px">
										<tr>
											<td nowrap="nowrap">
												���
											</td>										
											<td nowrap="nowrap">
												ѧ��
											</td>		
											<td nowrap="nowrap">
												����
											</td>									
											<td nowrap="nowrap">
												Υ��
											</td>
											<td nowrap="nowrap">
												���ν���
											</td>
											<td nowrap="nowrap">
												���
											</td>	
											<td nowrap="nowrap">
												��ע<font color="red">��250��</font>
											</td>																																										
										</tr>
									</thead>
									<tbody width="100%" class="tbstyle" id="flag">
									</tbody>
								</table>
							</div>
						</td>
					</tr>
					</tbody>
			<tbody>
				<tr>
					<th width="20%">
						��ע
					</th>
					<td align="left" colspan="3">
						<html:textarea property="fdyclbz" name="model" cols="60" rows="4" onblur="chLeng(this,600)" style="width:100%"></html:textarea>
					</td>
				</tr>
			</tbody>
			</logic:equal>
		</table>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��!");
			</script>
		</logic:equal>		
		</html:form>
	</body>
</html>
