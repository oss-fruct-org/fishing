// import QtQuick 1.0 // to target S60 5th Edition or Maemo 5
import QtQuick 1.1
import com.nokia.symbian 1.1
import "UIConstants.js" as UI
import fishing 1.0

Page {
    tools: fishTools
    orientationLock: PageOrientation.LockPortrait
    property alias title: fishHeader.title
    property alias description: descriptionText.text

    Header {
        id: fishHeader
        anchors.top: parent.top
    }

    ToolBarLayout {
        id: fishTools

        ToolButton {
            iconSource: "toolbar-back"
            anchors.left: (parent === undefined) ? undefined : parent.left
            onClicked: appWindow.pageStack.depth <= 1 ? Qt.quit() : appWindow.pageStack.pop()
        }

        ToolButton {
            iconSource: "toolbar-view-menu"
            anchors.right: (parent === undefined) ? undefined : parent.right
            onClicked: (myMenu.status === DialogStatus.Closed) ? myMenu.open() : myMenu.close()
        }
    }

    Flickable {
        id: flickable
        anchors.top: fishHeader.bottom
        anchors.right: parent.right
        anchors.left: parent.left
        anchors.bottom: parent.bottom
        contentHeight: content_column.height + 2 * UI.DEFAULT_MARGIN

        Column {
            id: content_column
            spacing: UI.DEFAULT_MARGIN
            anchors.left: parent.left
            anchors.right: parent.right
            anchors.top: parent.top
            anchors.margins: UI.DEFAULT_MARGIN

            Item {
                anchors.left: parent.left
                anchors.right: parent.right
                height: 300

                ImageWithBorder {
                    id: mPic
                    anchors.fill: parent
                    anchors.margins: UI.DEFAULT_MARGIN
                    source: applicationPath + "fishes/" + title + ".jpg"
                    borderWidth: 2
                }
            }

            Text {
                id: descriptionText
                font.pixelSize: UI.FONT_DEFAULT
                font.weight: Font.Normal
                color: UI.COLOR_INVERTED_FOREGROUND
                wrapMode: Text.Wrap
                width: parent.width
                smooth: true
                visible: text
            }
        }
    }

    onStatusChanged: {
        if (status == PageStatus.Activating) {
            flickable.contentY = 0;
        }
    }
}
