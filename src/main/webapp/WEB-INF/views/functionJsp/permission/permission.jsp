<%--
  Created by IntelliJ IDEA.
  User: Xiaoke Zhang
  Date: 2/25/2016
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>东北大学软件学院论文系统</title>
    <%@ include file="../../baseJsp/baseCss.jsp" %>
    <link rel="stylesheet" type="text/css" href="appResource/css/zTree/demo.css"/>
    <link rel="stylesheet" type="text/css" href="appResource/css/zTree/zTreeStyle/zTreeStyle.css"/>
    <%@ include file="../../baseJsp/baseJS.jsp" %>
    <script type="text/javascript" src="appResource/js/zTree/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="appResource/js/zTree/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="appResource/js/zTree/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="appResource/js/zTree/jquery.ztree.exedit.js"></script>
    <SCRIPT type="text/javascript">
        <!--
        var setting = {

            view: {
                selectedMulti: false
            },
            edit: {
                enable: true,
                showRemoveBtn: false,
                showRenameBtn: false
            },
            data: {
                keep: {
                    parent: true,
                    leaf: true
                },
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeDrag: beforeDrag,
                beforeRemove: beforeRemove,
                beforeRename: beforeRename,
                onRemove: onRemove,
                onRename: onRename,
                onClick: zTreeOnClick
            }
        };

        var log, className = "dark";
        function beforeDrag(treeId, treeNodes) {
            return false;
        }
        function beforeRemove(treeId, treeNode) {
            className = (className === "dark" ? "" : "dark");
            showLog("[ " + getTime() + " beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
            console.log(treeNode);
            return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
        }
        function onRemove(e, treeId, treeNode) {
            showLog("[ " + getTime() + " onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
            console.log(treeNode);
        }
        function beforeRename(treeId, treeNode, newName) {
            if (newName.length == 0) {
                alert("节点名称不能为空.");
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                setTimeout(function () {
                    zTree.editName(treeNode)
                }, 10);
                return false;
            }
            native_name = treeNode.name;
            return true;
        }
        // 显示点击的链接
        function zTreeOnClick(event, treeId, treeNode) {
            console.log(treeNode.id + ", " + treeNode.name);
        }
        ;


        function getTime() {
            var now = new Date(),
                    h = now.getHours(),
                    m = now.getMinutes(),
                    s = now.getSeconds(),
                    ms = now.getMilliseconds();
            return (h + ":" + m + ":" + s + " " + ms);
        }

        var newCount = 1;
        function add(e) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    isParent = e.data.isParent,
                    nodes = zTree.getSelectedNodes(),
                    treeNode = nodes[0];
            if (treeNode) {
                treeNode = zTree.addNodes(treeNode, {
                    id: (100 + newCount),
                    pId: treeNode.id,
                    isParent: isParent,
                    name: "new node" + (newCount++)
                });
            } else {
                treeNode = zTree.addNodes(null, {
                    id: (100 + newCount),
                    pId: 0,
                    isParent: isParent,
                    name: "new node" + (newCount++)
                });
            }
            if (treeNode) {
                zTree.editName(treeNode[0]);
                console.log(treeNode);
            } else {
                alert("叶子节点被锁定，无法增加子节点");
            }
        }
        ;
        function edit() {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    nodes = zTree.getSelectedNodes(),
                    treeNode = nodes[0];
            if (nodes.length == 0) {
                alert("请先选择一个节点");
                return;
            }
            zTree.editName(treeNode);
            console.log(treeNode);

        }
        ;
        function onRename(e, treeId, treeNode) {
            if (native_name == treeNode.name) {
                return;
            }
            var data = {id: treeNode.id, name: treeNode.name};
            console.log(data);
            $.ajax({
                async: false,
                url: "rest/permission/updatePermission",
                data: data,
                type: 'post',
                error: function () {
                    alert('亲，网络有点不给力呀！');
                },
                success: function (data) {
                    console.log(data);
                }

            });
        }

        function remove(e) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    nodes = zTree.getSelectedNodes(),
                    treeNode = nodes[0];
            if (nodes.length == 0) {
                alert("请先选择一个节点");
                return;
            }
            var callbackFlag = $("#callbackTrigger").attr("checked");
            zTree.removeNode(treeNode, callbackFlag);
        }
        ;
        function clearChildren(e) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    nodes = zTree.getSelectedNodes(),
                    treeNode = nodes[0];
            if (nodes.length == 0 || !nodes[0].isParent) {
                alert("请先选择一个父节点");
                return;
            }
            zTree.removeChildNodes(treeNode);
            console.log(treeNode);
        }
        ;

        //加载ztree
        function onLoadZTree() {
            $.ajax({
                async: true, //是否异步
                cache: false, //是否使用缓存
                type: 'post', //请求方式,post
                dataType: "json", //数据传输格式
                url: "rest/permission/permissionList", //请求链接
                error: function () {
                    alert('亲，网络有点不给力呀！');
                },
                success: function (data) {
                    $.fn.zTree.init($("#treeDemo"), setting, data);
                }
            });
        }

        $(document).ready(function () {
            onLoadZTree();
//            console.log(zNodes);
            $("#addParent").bind("click", {isParent: true}, add);
            $("#addLeaf").bind("click", {isParent: false}, add);
            $("#edit").bind("click", edit);
            $("#remove").bind("click", remove);
            $("#clearChildren").bind("click", clearChildren);
        });
        //-->
    </SCRIPT>
</head>
<body>
<div class="header">
    <%@ include file="../../baseJsp/baseHeader.jsp" %>
</div>
<div class="main_content">
    <div class="center_content">
        <div class="left_content">
            <%@ include file="../../baseJsp/baseMenu.jsp" %>
        </div>
        <!--end of left content -->

        <div class="right_content">
            <h2>权限管理</h2>
            <div align="left" style="width:900px; margin:0 auto">

                <div class="content_wrap">
                    <div class="zTreeDemoBackground left">
                        <ul id="treeDemo" class="ztree"></ul>
                    </div>
                    <div class="right">
                        <div align="left" style="width:900px; margin:0 auto">

                        </div>
                        <ul class="info">
                            <li>
                                &nbsp;&nbsp;&nbsp;&nbsp;[ <a id="addParent" href="#" title="增加父节点"
                                                             onclick="return false;">增加父节点</a> ]
                                &nbsp;&nbsp;&nbsp;&nbsp;[ <a id="addLeaf" href="#" title="增加叶子节点"
                                                             onclick="return false;">增加叶子节点</a> ]
                                &nbsp;&nbsp;&nbsp;&nbsp;[ <a id="edit" href="#" title="编辑名称" onclick="return false;">编辑名称</a>
                                ]
                                &nbsp;&nbsp;&nbsp;&nbsp;[ <a id="remove" href="#" title="删除节点" onclick="return false;">删除节点</a>
                                ]
                                &nbsp;&nbsp;&nbsp;&nbsp;[ <a id="clearChildren" href="#" title="清空子节点"
                                                             onclick="return false;">清空子节点</a> ]<br/>
                            </li>

                        </ul>
                    </div>
                </div>

            </div>
        </div>
        <!-- end of right content-->

    </div>
    <!--end of center content -->

</div>
<!--end of main content-->

<div class="footer">
    <%@ include file="../../baseJsp/baseFooter.jsp" %>
</div>
</body>
</html>
