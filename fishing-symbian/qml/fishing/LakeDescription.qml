// import QtQuick 1.0 // to target S60 5th Edition or Maemo 5
import QtQuick 1.1
import com.nokia.symbian 1.1
import "UIConstants.js" as UI

Page {
    tools: lakeTools
    orientationLock: PageOrientation.LockPortrait
    property alias title: lakeHeader.title
    property alias description: descriptionText.text

    Header {
        id: lakeHeader
        anchors.top: parent.top
    }

    Flickable {
        id: flickable
        anchors.top: lakeHeader.bottom
        anchors.right: parent.right
        anchors.left: parent.left
        anchors.bottom: parent.bottom
        contentHeight: content_column.height + 2 * UI.DEFAULT_MARGIN

        Column {
            id: content_column
            anchors.left: parent.left
            anchors.right: parent.right
            anchors.top: parent.top
            spacing: UI.DEFAULT_MARGIN
            anchors.margins: UI.DEFAULT_MARGIN

            Text {
                font.pixelSize: UI.FONT_DEFAULT
                font.weight: Font.Normal
                color: UI.COLOR_INVERTED_FOREGROUND
                wrapMode: Text.Wrap
                width: parent.width
                smooth: true
                text: "<b>Координаты:</b> " + lat + ", " + lng
            }

            Text {
                id: descriptionText
                font.pixelSize: UI.FONT_DEFAULT
                font.weight: Font.Normal
                color: UI.COLOR_INVERTED_FOREGROUND
                wrapMode: Text.Wrap
                width: parent.width
                smooth: true
            }
        }
    }

    onStatusChanged: {
        if (status == PageStatus.Activating) {
            flickable.contentY = 0;
        }
    }
}
