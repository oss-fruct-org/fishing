// import QtQuick 1.0 // to target S60 5th Edition or Maemo 5
import QtQuick 1.1
import "UIConstants.js" as UI

Rectangle {
    id: header
    height: 60
    width: parent.width
    color: "#007DC9"
    z: 1

    property alias title: titleText.text
    property alias iconSource: icon.source

    Image {
        id: icon
        anchors.leftMargin: 6
        anchors.left: parent.left
        anchors.verticalCenter: parent.verticalCenter
        smooth: true
    }

    Text {
        id: titleText
        anchors.left: iconSource ? icon.right : parent.left
        anchors.leftMargin: UI.DEFAULT_MARGIN
        anchors.right: parent.right
        anchors.rightMargin: UI.DEFAULT_MARGIN
        anchors.verticalCenter: parent.verticalCenter
        anchors.verticalCenterOffset: 3
        elide: Text.ElideRight
        font.family: fishing_font.name
        font.pixelSize: UI.FONT_LARGE
        font.weight: Font.Normal
        color: "white"
        smooth: true
        width: parent.width
    }
}
