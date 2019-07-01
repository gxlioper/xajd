<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption:"家教注册用户列表",
            pager:"pager",
            multiselect:false,
            radioselect:true,
            rowNum:20,
            url:"jjgl_zcyhgl.do?method=queryZcyhList&type=w",
            colList:[
                {label:'用户名',name:'yhm', index: 'yhm',key:true},
                {label:'姓名',name:'xm', index: 'xm'},
                {label:'联系电话',name:'lxdh', index: 'lxdh'},
                {label:'家庭住址',name:'jtzz', index: 'jtzz'},
                {label:'注册时间',name:'zcsj', index: 'zcsj'},
                {label:'操作',name:'yhm', index: 'yhm',formatter:czLink}
            ]
        };

        function searchRs(){
            var map = {};
            map["yhm"] = jQuery("#yhm").val();
            map["xm"] = jQuery("#xm").val();
            jQuery("#dataTable").reloadGrid(map);
        }

        /**
         *初始化
         */

        jQuery(function(){
            jQuery("#dataTable").initGrid(gridSetting);
        });

        /**
         *重新加载数据
         */
        function reloadWindow(){
            jQuery("#dataTable").reloadGrid();
        }

        function czLink(cellValue,rowObject){
            var xm = rowObject["xm"];
            var lxdh = rowObject["lxdh"];
            return "<button class='btn_01' onclick=\"selectJzxx('"+cellValue+"','"+xm+"','"+lxdh+"')\" class='name'>"+"选择"+"</button>";
        }

        function selectJzxx(yhm,xm,lxdh) {
            var api = frameElement.api;
            var wd = api.get('parentDialog').document;

            //查询家长及子女相关信息并回显到父页面
            jQuery.post("jjgl_zcyhgl.do?method=getJzxxByYhm",{yhm:yhm},function (data) {
                jQuery("#sqr",wd).val(yhm);
                jQuery("#jzxm",wd).val(xm);
                jQuery("#lxdh",wd).text(lxdh);

                var html = "<option value=''></option>";
                if(data != null){
                    for(i=0;i<data.length;i++){
                        var jzxx = data[i];
                        html += "<option value="+data[i]["znid"]+" znxb="+data[i]["xb"]+">"+data[i]["xm"]+"</option>"
                    }
                }

                jQuery("#znid",wd).empty().append(html);
                jQuery("#znxb",wd).empty();
                api.close();

            },"JSON");
        }
    </script>
</head>
<body>
<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>

<div class="toolbox">
    <!-- 过滤条件 -->
    <div class="searchtab">
        <html:form action="/jjgl_zcyhgl" method="post" >

            <table width="100%" border="0">
                <tr>
                    <th width="10%">用户名</th>
                    <td>
                        <html:text property="yhm" styleId="yhm" ></html:text>
                    </td>
                    <th width="10%">姓名</th>
                    <td>
                        <html:text property="xm" styleId="xm" ></html:text>
                    </td>
                    <td>
                        <div class="btn">
                            <button type="button" class="btn_cx" id="search_go" onclick="searchRs()">
                                查 询
                            </button>
                        </div>
                    </td>
                </tr>
            </table>
        </html:form>
    </div>
</div>
<div class="formbox">
    <div>
        <h3 class="datetitle_01">
					<span id="listName">
						家长信息列表
					</span>
        </h3>
    </div>
    <table id="dataTable"></table>
</div>
<div id="pager"></div>

</body>
</html>
