// import QtQuick 1.0 // to target S60 5th Edition or Maemo 5
import QtQuick 1.1
import "UIConstants.js" as UI
import com.nokia.symbian 1.1

Rectangle {
    id: header
    height: 60
    width: parent.width
    color: "#007DC9"
    z: 1

    property string title: ""
    signal typeChanged(string typeName)

    Text {
        id: titleText
        anchors.left: parent.left
        anchors.leftMargin: UI.DEFAULT_MARGIN
        anchors.right: selecter.right
        anchors.rightMargin: UI.DEFAULT_MARGIN
        anchors.verticalCenter: parent.verticalCenter
        anchors.verticalCenterOffset: 3
        elide: Text.ElideRight
        text: title
        font.family: fishing_font.name
        font.pixelSize: UI.FONT_LARGE
        font.weight: Font.Normal
        color: "white"
        smooth: true
        width: parent.width
    }

    Image {
        id: selecter
        anchors.verticalCenter: parent.verticalCenter
        anchors.right: parent.right
        anchors.rightMargin: UI.DEFAULT_MARGIN
        source: "qrc:/gfx/combobox-arrow.png"
    }

    MouseArea {
        anchors.fill: parent
        onClicked: filtersDialog.open()
    }

    SelectionDialog {
        id: filtersDialog
        titleText: ""
        selectedIndex: 0
        model: ListModel {
            ListElement { name: "Все" }
            ListElement { name: "Озера" }
            ListElement { name: "Рыболовные магазины" }
            ListElement { name: "Базы отдыха" }
        }

        onSelectedIndexChanged: {
            typeChanged(filtersDialog.model.get(selectedIndex).name);
            placemarksHeader.title = filtersDialog.model.get(selectedIndex).name;
            marksTypesDialog.selectedIndex = selectedIndex
        }

        Connections {
            target: marksTypesDialog
            onSelectedIndexChanged: filtersDialog.selectedIndex = marksTypesDialog.selectedIndex
        }
    }

    function reset() {
        filtersDialog.selectedIndex = 0;
    }
}
