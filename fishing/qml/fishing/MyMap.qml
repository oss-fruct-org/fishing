import QtQuick 1.1
import QtMobility.location 1.2

Item {
    id: myMapRoot

    property alias mapRef: map
    property Component itemMapDelegate
    property variant itemsModel
    property alias zoomLevel: map.zoomLevel

    signal viewportChanged(variant from, variant to)
    signal loadFinished()
    signal showMapMenu()

    function moveMap(lat, lng) {
        map.center.latitude = lat;
        map.center.longitude = lng;
    }

    onOpacityChanged: {
        if (opacity == 1) {
            updateViewport();
        }
    }

    function updateViewport() {
        viewportChanged(
                    map.toCoordinate(Qt.point(-map.anchors.leftMargin,-map.anchors.topMargin)),
                    map.toCoordinate(Qt.point(map.size.width + map.anchors.rightMargin,
                                              map.size.height + map.anchors.bottomMargin)))
    }

    function resetPosition() {
        map.center.latitude = me.position.coordinate.latitude
        map.center.longitude = me.position.coordinate.longitude
    }

    function addPlacemark(mark) {
        map.addMapObject(mark);
    }

    PositionSource {
        id: me
        active: true
        updateInterval: 1000
    }

    Map {
        id: map
        z: 2
        anchors.fill: parent
        zoomLevel: 7

        plugin: Plugin {
            name : "nokia";
            parameters: [
                PluginParameter { name: "mapping.app_id"; value: "HF0K-1DzItC5oT2TutKY" },
                PluginParameter { name: "mapping.token"; value: "BpD6k2KX0Q8uvAviKIwH5Q" }
            ]
        }
        center: Coordinate {
            latitude: 61.78574
            longitude: 34.363883
        }

        onZoomLevelChanged: {
            myMapRoot.updateViewport()
        }

        Repeater {
            model: itemsModel
            onItemAdded: {
                map.addMapObject(item)
            }

            onItemRemoved: {
                map.removeMapObject(item)
            }
            delegate: MapImage {
                id: mark

                coordinate: Coordinate {
                    latitude: lat;
                    longitude: lng;
                }

                source: (mark_type == "lake") ? "qrc:/gfx/fishing.png" :
                                                ((mark_type == "shop") ? "qrc:/gfx/shop.png" : "qrc:/gfx/hostel.png")

                MapMouseArea {
                    anchors.fill: parent
                    onClicked: {
                        if (mark_type == "lake") {
                            lakeTools.isReturnToMap = true;
                            lakeDescription.lat = lat;
                            lakeDescription.lng = lng;
                            lakeDescription.title = name;
                            lakeDescription.description = description;
                            lakeTools.checkFirst();
                            pageStack.push(lakeDescription)
                        } else if (mark_type == "shop") {
                            shopDescription.lat = lat;
                            shopDescription.lng = lng;
                            shopDescription.title = name;
                            shopDescription.address = address;
                            shopDescription.phone = phone;
                            shopDescription.site = site;
                            pageStack.push(shopDescription)
                        } else {
                            hostelDescription.lat = lat;
                            hostelDescription.lng = lng;
                            hostelDescription.title = name;
                            hostelDescription.description = description;
                            hostelDescription.phone = phone;
                            hostelDescription.site = site;
                            pageStack.push(hostelDescription)
                        }
                    }
                }
            }
        }

        Component.onCompleted: {
            loadFinished()
        }
    }

    PinchArea {
        id: pincharea

        property double __oldZoom

        anchors.fill: parent

        function calcZoomDelta(zoom, percent) {
            return zoom + Math.log(percent)/Math.log(2)
        }

        onPinchStarted: {
            __oldZoom = map.zoomLevel
        }

        onPinchUpdated: {
            map.zoomLevel = calcZoomDelta(__oldZoom, pinch.scale)
        }

        onPinchFinished: {
            map.zoomLevel = calcZoomDelta(__oldZoom, pinch.scale)
        }
    }


    Flickable {
        id: flickable
        anchors.fill: parent
        contentWidth: 8000
        contentHeight: 8000

        property double prevX: 0
        property double prevY: 0
        property bool lock: false

        Component.onCompleted: setCenter()
        onMovementEnded: {
            setCenter()
            myMapRoot.updateViewport()
        }

        function setCenter() {
            lock = true
            contentX = contentWidth / 2
            contentY = contentHeight / 2
            lock = false
            prevX = contentX
            prevY = contentY
        }

        onContentXChanged: panMap()
        onContentYChanged: panMap()

        function panMap() {
            if (lock) return
            map.pan(contentX - prevX, contentY - prevY)
            prevX = contentX
            prevY = contentY
        }

        MouseArea {
            anchors.fill: parent
            onDoubleClicked: {
                map.center = map.toCoordinate(Qt.point(mouseX - flickable.contentX, mouseY - flickable.contentY));
                map.zoomLevel += 1;
            }
        }
    }

    Image {
        z: 2
        anchors.right: parent.right
        anchors.bottom: parent.bottom
        anchors.margins: 10
        source: "qrc:/gfx/map_settings.png"
        sourceSize.width: 60
        opacity: 0.7

        MouseArea {
            anchors.fill: parent
            onClicked: {
                showMapMenu()
            }
        }
    }

    Column {
        z: 2
        anchors.verticalCenter: parent.verticalCenter
        anchors.right: parent.right
        anchors.rightMargin: 6
        spacing: 10

        ZoomButton {
            id: plus
            value: "+"

            MouseArea {
                anchors.fill: parent
                onClicked: {
                    map.zoomLevel += 1
                }
            }
        }

        ZoomButton {
            id: minus
            value: "-"

            MouseArea {
                anchors.fill: parent
                onClicked: {
                    if (zoomLevel > 4)
                        map.zoomLevel -= 1;
                }
            }
        }

    }
}
