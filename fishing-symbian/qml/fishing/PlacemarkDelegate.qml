// import QtQuick 1.0 // to target S60 5th Edition or Maemo 5
import QtQuick 1.1
import "UIConstants.js" as UI

Item {
    id: fishDelegate
    height: 50
    width: fishDelegate.ListView.view.width

    signal delegateClicked()

    Text {
        anchors.left: parent.left
        anchors.leftMargin: UI.DEFAULT_MARGIN
        font.pixelSize: UI.FONT_LSMALL
        smooth: true
        text: name
        anchors.verticalCenter: parent.verticalCenter
        color: UI.COLOR_INVERTED_FOREGROUND
    }

    Image {
        anchors.right: parent.right
        anchors.rightMargin: UI.DEFAULT_MARGIN
        anchors.verticalCenter: parent.verticalCenter
        source: "qrc:/gfx/drilldown-arrow.png"
    }

    MouseArea {
        anchors.fill: parent
        onClicked: delegateClicked()
    }
}
