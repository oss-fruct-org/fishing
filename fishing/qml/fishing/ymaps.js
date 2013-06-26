function getHtmlString()
{
    var html = '<html></script>' +
            '<meta http-equiv="Content-Type" content="text/html; charset=utf-8">' +
            '<style type="text/css">html,body,#map{width:100%;height:100%;padding:0;margin:0;}</style>' +
            '<script src="http://api-maps.yandex.ru/2.0/?load=package.full&amp;lang=ru-RU" type="text/javascript"></script>' +
            '<script src="http://yandex.st/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>' +
            '<script type="text/javascript">' +
            'var myMap;' +
            'ymaps.ready(init);' +
            'function init () {' +
            'myMap = new ymaps.Map("map", {' +
            'center: [61.78, 34.36],' +
            'zoom: 7' +
            '});' +
            'myMap.controls.add("smallZoomControl", {right: 5, top: 75}).add("mapTools");' +
            '}' +
            '</script>' +
        '<body>' +
        '<div id="map">'+
        '</div>' +
        '</body></html>'
    return html;
}
