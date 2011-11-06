function initChromeFont() {
    if (!$.browser.msie) {
        $('#site_title>h1').css("font-family", "xs");
        $('.title_a').css("font-size", "48px");

        $('.stripNav>ul>li').css("font-family", "xs");
        $('.stripNav>ul>li>a').css("font-size", "22px");

        $('.wrapper>h2').css("font-family", "xs");
        $('.wrapper>h2').css("font-size", "33px");


        $('.port_content').css("font-size", "13px");
        $('.port_content>a').css("font-family", "xs");
        $('.port_content>a').css("font-size", "22px");

        $('#twitter>a').css("font-family", "xs");
    }
     $('#twitter>a').css("color", "#39B2FF");
}
