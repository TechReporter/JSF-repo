$(document).ready(function () {
    if ($(".sidebar-collapse").length > 0) {

        var anchor = $("#side-menu a").filter(function () {
            return this.href == location.href.replace(/#.*/, "");
        });
        $(anchor).addClass('active_menu');
        $(anchor).parents().eq(1).addClass('in');
        $(anchor).parents().eq(2).addClass('active');

    }
    $(".ui-dialog").each(function () {
        $(this).appendTo("body");
    });

});


var dialog = $(".ui-dialog");

$(document).on("click", function () {
    $(".bglayer").each(function () {
        $(this).remove();
    });
    dialog.each(function () {
        if ($(this).css('display') == 'block') {
            $("body").append("<div class='bglayer'></div>");
            return false;
        } else {
            $(".bglayer").each(function () {
                $(this).remove();
            });
        }
    });
});