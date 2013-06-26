// import QtQuick 1.0 // to target S60 5th Edition or Maemo 5
import QtQuick 1.1
import "UIConstants.js" as UI

Item {
    id: fishDelegate
    height: 60
    width: fishDelegate.ListView.view.width
    property bool is_content: false

    signal delegateClicked()

    Text {
        anchors.left: parent.left
        anchors.leftMargin: UI.DEFAULT_MARGIN
        font.pixelSize: UI.FONT_DEFAULT
        smooth: true
        text: name
        anchors.verticalCenter: parent.verticalCenter
    }

    Image {
        anchors.right: parent.right
        anchors.rightMargin: UI.DEFAULT_MARGIN
        anchors.verticalCenter: parent.verticalCenter
        source: "image://theme/icon-m-common-drilldown-arrow"
//        opacity: is_content ? 1 : 0.3
    }

    MouseArea {
//        enabled: is_content
        anchors.fill: parent
        onClicked: delegateClicked()
    }
}
