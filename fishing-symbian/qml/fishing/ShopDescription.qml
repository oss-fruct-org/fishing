// import QtQuick 1.0 // to target S60 5th Edition or Maemo 5
import QtQuick 1.1
import com.nokia.symbian 1.1
import "UIConstants.js" as UI

Page {
    tools: shopTools
    orientationLock: PageOrientation.LockPortrait
    property alias title: lakeHeader.title
    property alias address: addressText.text
    property alias phone: phoneText.text
    property string site: ""

    property real lat
    property real lng

    ToolBarLayout {
        id: shopTools

        ToolButton {
            iconSource: "toolbar-back"
            anchors.left: (parent === undefined) ? undefined : parent.left
            onClicked: appWindow.pageStack.depth <= 1 ? Qt.quit() : appWindow.pageStack.pop()
        }

        ToolButton {
            iconSource: "qrc:/gfx/location.png"
            onClicked: {
                pageStack.pop(mainPage)
                map.zoomLevel = 15
                map.moveMap(lat, lng)
            }
        }

        ToolButton {
            iconSource: "toolbar-view-menu"
            anchors.right: (parent === undefined) ? undefined : parent.right
            onClicked: (myMenu.status === DialogStatus.Closed) ? myMenu.open() : myMenu.close()
        }
    }

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
        contentHeight: infoColumn.height + 2 * UI.DEFAULT_MARGIN

        Column {
            id: infoColumn
            anchors.left: parent.left
            anchors.right: parent.right
            anchors.top: parent.top
            anchors.margins: UI.DEFAULT_MARGIN
            spacing: UI.DEFAULT_MARGIN

            Text {
                font.pixelSize: UI.FONT_DEFAULT
                font.weight: Font.Normal
                color: UI.COLOR_INVERTED_FOREGROUND
                wrapMode: Text.Wrap
                width: parent.width
                smooth: true
                text: "<b>Координаты:</b> " + lat + ", " + lng
            }

            Column {
                width: parent.width
                visible: addressText.text

                Text {
                    id: addressTitle
                    font.pixelSize: UI.FONT_DEFAULT
                    font.weight: Font.Bold
                    color: UI.COLOR_INVERTED_FOREGROUND
                    smooth: true
                    text: "Адрес"
                }

                Text {
                    id: addressText
                    font.pixelSize: UI.FONT_DEFAULT
                    font.weight: Font.Light
                    color: UI.COLOR_INVERTED_FOREGROUND
                    smooth: true
                    width: parent.width
                    wrapMode: Text.Wrap
                    opacity: 0.6
                }
            }

            Column {
                width: parent.width
                visible: phoneText.text

                Text {
                    id: phoneTitle
                    font.pixelSize: UI.FONT_DEFAULT
                    font.weight: Font.Bold
                    color: UI.COLOR_INVERTED_FOREGROUND
                    smooth: true
                    text: "Телефон"
                }

                Text {
                    id: phoneText
                    font.pixelSize: UI.FONT_DEFAULT
                    font.weight: Font.Light
                    color: UI.COLOR_INVERTED_FOREGROUND
                    smooth: true
                    width: parent.width
                    wrapMode: Text.Wrap
                    opacity: 0.6
                }
            }

            Column {
                width: parent.width
                visible: site

                Text {
                    id: siteTitle
                    font.pixelSize: UI.FONT_DEFAULT
                    font.weight: Font.Bold
                    color: UI.COLOR_INVERTED_FOREGROUND
                    smooth: true
                    text: "Сайт"
                }

                Text {
                    id: siteText
                    font.pixelSize: UI.FONT_DEFAULT
                    font.weight: Font.Light
                    color: UI.COLOR_INVERTED_FOREGROUND
                    smooth: true
                    width: parent.width
                    wrapMode: Text.Wrap
                    opacity: 0.6
                    text: "<a href='" + site + "'>" + site + "</a>"
                    onLinkActivated: Qt.openUrlExternally(link)
                }
            }
        }
    }

    onStatusChanged: {
        if (status == PageStatus.Activating) {
            flickable.contentY = 0;
        }
    }
}
