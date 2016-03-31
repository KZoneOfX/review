<%--
  Created by IntelliJ IDEA.
  User: Xiaoke Zhang
  Date: 2/29/2016
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<script type="text/javascript" language="javascript" src="appResource/js/clockp.js"></script>
<script type="text/javascript" language="javascript" src="appResource/js/clockh.js"></script>
<script type="text/javascript" language="javascript" src="appResource/js/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="appResource/js/ddaccordion.js"></script>
<script type="text/javascript" language="javascript" src="appResource/js/jconfirmaction.jquery.js"></script>
<script type="text/javascript" language="javascript" src="appResource/js/niceforms.js"></script>
<script type="text/javascript" language="javascript" src="appResource/js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="appResource/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="appResource/js/demo.js"></script>

<script type="text/javascript" language="javascript" src="appResource/js/lobIBox/jquery.1.11.min.js"></script>
<script type="text/javascript" language="javascript" src="appResource/js/lobIBox/lobibox.min.js"></script>
<script type="text/javascript" language="javascript" class="init"></script>
<script type="text/javascript">
    ddaccordion.init({
        headerclass: "submenuheader", //Shared CSS class name of headers group
        contentclass: "submenu", //Shared CSS class name of contents group
        revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
        mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
        collapseprev: true, //Collapse previous content (so only one open at any time)? true/false
        defaultexpanded: [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
        onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
        animatedefault: false, //Should contents open by default be animated into view?
        persiststate: true, //persist state of opened contents within browser session?
        toggleclass: ["", ""], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
        togglehtml: ["suffix", "<img src='appResource/images/plus.gif' class='statusicon' />", "<img src='appResource/images/minus.gif' class='statusicon' />"], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
        animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
        oninit: function (headers, expandedindices) { //custom code to run when headers have initalized
            //do nothing
        },
        onopenclose: function (header, index, state, isuseractivated) { //custom code to run whenever a header is opened or closed
            //do nothing
        }
    });
    $(document).ready(function () {
        $("#logout").bind("click", function () {
            logout();
        });
    });
    function logout() {
        $.ajax({
            type: "GET",
            url: 'rest/logout',
            success: function (data) {
                var map = eval("(" + data + ")");
                var result = map["result"];
                if (result == "yes") {
                    window.location.href = 'login.html';
                } else {

                }
            },
            error: function (a, b, v) {
                if (v == "Not Found") {
                    alert("您请求的地址不存在");

                } else if (b == "timeout") {
                    alert("请求超时");
                } else {
//                        alert("wrong");
                }

            }
        });
    }
</script>