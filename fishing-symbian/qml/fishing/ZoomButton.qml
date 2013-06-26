// import QtQuick 1.0 // to target S60 5th Edition or Maemo 5
import QtQuick 1.1
import "UIConstants.js" as UI

Rectangle {
    width: 42
    height: 42
    color: "white"
    opacity: 0.6
    border.width: 2
    border.color: "black"
    radius: 5

    property alias value: buttonText.text

    Text {
        id: buttonText
        anchors.centerIn: parent
        font.pixelSize: 40
        color: "black"
    }
}
