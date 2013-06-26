// import QtQuick 1.0 // to target S60 5th Edition or Maemo 5
import QtQuick 1.1
import QtMobility.location 1.2

MapImage {
    id: mark
    property real lat
    property real lng
    property string name
    property string description

    coordinate: Coordinate {
        latitude: lat;
        longitude: lng;
    }

    source: "qrc:/gfx/fishing.png"

    MapMouseArea {
        anchors.fill: parent
        onClicked: {
            lakeDescription.title = name;
            lakeDescription.description = description;
            pageStack.push(lakeDescription)
        }
    }
}
